// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.
// 

using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using Newtonsoft.Json.Linq;
using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Core.Utilities.Collections;
using AutoRest.Extensions;
using AutoRest.Extensions.Azure;
using AutoRest.Java.Model;
using static AutoRest.Core.Utilities.DependencyInjection;
using System.Net;
using System.Text.RegularExpressions;

namespace AutoRest.Java
{
    public class TransformerJv : CodeModelTransformer<CodeModelJv>
    {
        private static readonly Regex methodTypeLeading = new Regex("^/+");
        private static readonly Regex methodTypeTrailing = new Regex("/+$");

        /// <summary>
        /// A type-specific method for code model tranformation.
        /// Note: This is the method you want to override.
        /// </summary>
        /// <param name="codeModel"></param>
        /// <returns></returns>
        public override CodeModelJv TransformCodeModel(CodeModel cm)
        {
            var codeModel = cm as CodeModelJv;
            JavaSettings.Instance.ShouldGenerateXmlSerialization = codeModel.ShouldGenerateXmlSerialization;
            if (!JavaSettings.Instance.IsAzureOrFluent)
            {
                SwaggerExtensions.NormalizeClientModel(codeModel);
                ProcessCustomTypeCompositeProperties(codeModel, JavaSettings.Instance);
            }
            else
            {
                JavaSettings.Instance.AddCredentials = true;

                // This extension from general extensions must be run prior to Azure specific extensions.
                SwaggerExtensions.ProcessParameterizedHost(codeModel);
                AzureExtensions.ProcessClientRequestIdExtension(codeModel);
                AzureExtensions.UpdateHeadMethods(codeModel);
                SwaggerExtensions.ProcessGlobalParameters(codeModel);
                SwaggerExtensions.FlattenModels(codeModel);
                SwaggerExtensions.FlattenMethodParameters(codeModel);
                ParameterGroupExtensionHelper.AddParameterGroups(codeModel);

                foreach (MethodGroupJv methodGroup in codeModel.Operations)
                {
                    Method[] methods = methodGroup.Methods.ToArray();
                    methodGroup.ClearMethods();
                    foreach (MethodJv method in methods)
                    {
                        methodGroup.Add(method);
                        if (method.Extensions?.Get<bool>(AzureExtensions.LongRunningExtension) == true)
                        {
                            Response response = method.Responses.Values.First();
                            if (!method.Responses.ContainsKey(HttpStatusCode.OK))
                            {
                                method.Responses[HttpStatusCode.OK] = response;
                            }
                            if (!method.Responses.ContainsKey(HttpStatusCode.Accepted))
                            {
                                method.Responses[HttpStatusCode.Accepted] = response;
                            }
                            if (method.HttpMethod != HttpMethod.Get && !method.Responses.ContainsKey(HttpStatusCode.NoContent))
                            {
                                method.Responses[HttpStatusCode.NoContent] = response;
                            }

                            MethodJv m = DependencyInjection.Duplicate(method);
                            var methodName = m.Name.ToPascalCase();
                            method.Name = "begin" + methodName;
                            m.Extensions.Remove(AzureExtensions.LongRunningExtension);
                            methodGroup.Add(m);

                            m = DependencyInjection.Duplicate(method);
                            m.Name = "resume" + methodName;
                            m.Extensions.Add("java-resume", new object());
                            methodGroup.Add(m);
                        }
                    }
                }

                AzureExtensions.AddAzureProperties(codeModel);
                AzureExtensions.SetDefaultResponses(codeModel);

                AzureExtensions.AddPageableMethod(codeModel);

                IDictionary<IModelType, IModelType> convertedTypes = new Dictionary<IModelType, IModelType>();

                foreach (MethodJv method in codeModel.Methods)
                {
                    bool simulateMethodAsPagingOperation = false;
                    MethodGroupJv methodGroup = method.MethodGroup as MethodGroupJv;
                    if (!string.IsNullOrEmpty(methodGroup?.Name?.ToString()))
                    {
                        MethodType restAPIMethodType = MethodType.Other;
                        string methodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(method.Url, ""), "");
                        string[] urlSplits = methodUrl.Split('/');
                        switch (method.HttpMethod)
                        {
                            case HttpMethod.Get:
                                if ((urlSplits.Length == 5 || urlSplits.Length == 7)
                                    && urlSplits[0].EqualsIgnoreCase("subscriptions")
                                    && method.ReturnType.Body.MethodHasSequenceType())
                                {
                                    if (urlSplits.Length == 5)
                                    {
                                        if (urlSplits[2].EqualsIgnoreCase("providers"))
                                        {
                                            restAPIMethodType = MethodType.ListBySubscription;
                                        }
                                        else
                                        {
                                            restAPIMethodType = MethodType.ListByResourceGroup;
                                        }
                                    }
                                    else if (urlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                    {
                                        restAPIMethodType = MethodType.ListByResourceGroup;
                                    }
                                }
                                else if (urlSplits.IsTopLevelResourceUrl())
                                {
                                    restAPIMethodType = MethodType.Get;
                                }
                                break;

                            case HttpMethod.Delete:
                                if (urlSplits.IsTopLevelResourceUrl())
                                {
                                    restAPIMethodType = MethodType.Delete;
                                }
                                break;
                        }

                        simulateMethodAsPagingOperation = (restAPIMethodType == MethodType.ListByResourceGroup || restAPIMethodType == MethodType.ListBySubscription) &&
                            1 == methodGroup.Methods.Count((Method methodGroupMethod) =>
                            {
                                MethodType methodGroupMethodType = MethodType.Other;
                                string methodGroupMethodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(methodGroupMethod.Url, ""), "");
                                string[] methodGroupUrlSplits = methodGroupMethodUrl.Split('/');
                                switch (methodGroupMethod.HttpMethod)
                                {
                                    case HttpMethod.Get:
                                        if ((methodGroupUrlSplits.Length == 5 || methodGroupUrlSplits.Length == 7)
                                        && methodGroupUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                        && methodGroupMethod.ReturnType.Body.MethodHasSequenceType())
                                        {
                                            if (methodGroupUrlSplits.Length == 5)
                                            {
                                                if (methodGroupUrlSplits[2].EqualsIgnoreCase("providers"))
                                                {
                                                    methodGroupMethodType = MethodType.ListBySubscription;
                                                }
                                                else
                                                {
                                                    methodGroupMethodType = MethodType.ListByResourceGroup;
                                                }
                                            }
                                            else if (methodGroupUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                            {
                                                methodGroupMethodType = MethodType.ListByResourceGroup;
                                            }
                                        }
                                        else if (methodGroupUrlSplits.IsTopLevelResourceUrl())
                                        {
                                            methodGroupMethodType = MethodType.Get;
                                        }
                                        break;

                                    case HttpMethod.Delete:
                                        if (methodGroupUrlSplits.IsTopLevelResourceUrl())
                                        {
                                            methodGroupMethodType = MethodType.Delete;
                                        }
                                        break;
                                }
                                return methodGroupMethodType == restAPIMethodType;
                            });
                    }

                    bool methodHasPageableExtensions = method.Extensions.ContainsKey(AzureExtensions.PageableExtension);
                    JContainer methodPageableExtensions = !methodHasPageableExtensions ? null : method.Extensions[AzureExtensions.PageableExtension] as JContainer;
                    if (methodPageableExtensions != null || simulateMethodAsPagingOperation)
                    {
                        string subPackage = JavaSettings.Instance.IsFluent ? JavaSettings.Instance.ImplementationSubpackage : JavaSettings.Instance.ModelsSubpackage;
                        string package = JavaSettings.Instance.GetPackage(subPackage);
                        string nextLinkName = null;
                        string itemName = "value";
                        string className = null;

                        bool shouldCreatePageDetails = false;

                        if (methodHasPageableExtensions)
                        {
                            if (methodPageableExtensions != null)
                            {
                                shouldCreatePageDetails = true;

                                nextLinkName = (string)methodPageableExtensions["nextLinkName"];
                                itemName = (string)methodPageableExtensions["itemName"] ?? "value";
                                className = (string)methodPageableExtensions["className"];
                            }
                        }
                        else if (simulateMethodAsPagingOperation)
                        {
                            shouldCreatePageDetails = true;
                        }

                        PageDetails pageDetails = null;
                        if (shouldCreatePageDetails)
                        {
                            pageDetails = codeModel.PageClasses.FirstOrDefault(page => page.NextLinkName == nextLinkName && page.ItemName == itemName);
                            if (pageDetails == null)
                            {
                                if (string.IsNullOrWhiteSpace(className))
                                {
                                    if (codeModel.PageClasses.Count > 0)
                                    {
                                        className = $"PageImpl{codeModel.PageClasses.Count}";
                                    }
                                    else
                                    {
                                        className = "PageImpl";
                                    }
                                }

                                pageDetails = new PageDetails(package, nextLinkName, itemName, className);
                                codeModel.PageClasses.Add(pageDetails);
                            }

                            if (!string.IsNullOrEmpty(pageDetails.ClassName))
                            {
                                if (string.IsNullOrEmpty(pageDetails.NextLinkName))
                                {
                                    method.Extensions[AzureExtensions.PageableExtension] = null;
                                }

                                bool anyTypeConverted = false;
                                foreach (HttpStatusCode responseStatus in method.Responses.Where(r => r.Value.Body is CompositeType).Select(s => s.Key).ToArray())
                                {
                                    anyTypeConverted = true;
                                    CompositeType compositeType = (CompositeType)method.Responses[responseStatus].Body;
                                    SequenceType sequenceType = compositeType.Properties
                                        .Select((Property property) =>
                                        {
                                            IModelType propertyModelType = property.ModelType;
                                            if (propertyModelType != null && !property.IsNullable() && propertyModelType is PrimaryTypeJv propertyModelPrimaryType)
                                            {
                                                PrimaryTypeJv propertyModelNonNullablePrimaryType = DependencyInjection.New<PrimaryTypeJv>(propertyModelPrimaryType.KnownPrimaryType);
                                                propertyModelNonNullablePrimaryType.Format = propertyModelPrimaryType.Format;
                                                propertyModelNonNullablePrimaryType.IsNullable = false;

                                                propertyModelType = propertyModelNonNullablePrimaryType;
                                            }
                                            return propertyModelType;
                                        })
                                        .FirstOrDefault(t => t is SequenceType) as SequenceType;

                                    // if the type is a wrapper over page-able response
                                    if (sequenceType != null)
                                    {
                                        SequenceTypeJv pagedResult = DependencyInjection.New<SequenceTypeJv>();
                                        pagedResult.ElementType = sequenceType.ElementType;
                                        pagedResult.PageImplType = pageDetails.ClassName;

                                        convertedTypes[method.Responses[responseStatus].Body] = pagedResult;
                                        Response resp = DependencyInjection.New<Response>(pagedResult, method.Responses[responseStatus].Headers);
                                        method.Responses[responseStatus] = resp;
                                    }
                                }

                                if (!anyTypeConverted && simulateMethodAsPagingOperation)
                                {
                                    foreach (HttpStatusCode responseStatus in method.Responses.Where(r => r.Value.Body is SequenceType).Select(s => s.Key).ToArray())
                                    {
                                        SequenceTypeJv sequenceType = (SequenceTypeJv)method.Responses[responseStatus].Body;

                                        SequenceTypeJv pagedResult = DependencyInjection.New<SequenceTypeJv>();
                                        pagedResult.ElementType = sequenceType.ElementType;
                                        pagedResult.PageImplType = pageDetails.ClassName;

                                        convertedTypes[method.Responses[responseStatus].Body] = pagedResult;
                                        Response resp = DependencyInjection.New<Response>(pagedResult, method.Responses[responseStatus].Headers);
                                        method.Responses[responseStatus] = resp;
                                    }
                                }

                                if (convertedTypes.ContainsKey(method.ReturnType.Body))
                                {
                                    Response resp = DependencyInjection.New<Response>(convertedTypes[method.ReturnType.Body], method.ReturnType.Headers);
                                    method.ReturnType = resp;
                                }
                            }
                        }
                    }
                }

                SwaggerExtensions.RemoveUnreferencedTypes(codeModel,
                    new HashSet<string>(convertedTypes.Keys
                        .Where(x => x is CompositeType)
                        .Cast<CompositeTypeJv>()
                        .Select((CompositeTypeJv compositeType) =>
                        {
                            string compositeTypeName = compositeType.Name.ToString();
                            if (JavaSettings.Instance.IsFluent && !string.IsNullOrEmpty(compositeTypeName) && compositeType.IsInnerModel)
                            {
                                compositeTypeName += "Inner";
                            }
                            return compositeTypeName;
                        })));

                if (JavaSettings.Instance.IsFluent)
                {
                    // determine inner models
                    foreach (AutoRest.Core.Model.Parameter parameter in codeModel.Methods.SelectMany(m => m.Parameters))
                    {
                        IModelType parameterModelType = parameter.ModelType;
                        if (parameterModelType != null && !parameter.IsNullable())
                        {
                            if (parameterModelType is PrimaryTypeJv parameterModelPrimaryType)
                            {
                                PrimaryTypeJv nonNullableParameterModelPrimaryType = DependencyInjection.New<PrimaryTypeJv>(parameterModelPrimaryType.KnownPrimaryType);
                                nonNullableParameterModelPrimaryType.Format = parameterModelPrimaryType.Format;
                                nonNullableParameterModelPrimaryType.IsNullable = false;

                                parameterModelType = nonNullableParameterModelPrimaryType;
                            }
                        }
                        AppendInnerToTopLevelType(parameterModelType, codeModel, JavaSettings.Instance);
                    }
                    foreach (Response response in codeModel.Methods.SelectMany(m => m.Responses).Select(r => r.Value))
                    {
                        AppendInnerToTopLevelType(response.Body, codeModel, JavaSettings.Instance);
                        AppendInnerToTopLevelType(response.Headers, codeModel, JavaSettings.Instance);
                    }
                    foreach (CompositeTypeJv model in codeModel.ModelTypes)
                    {
                        IModelTypeJv baseModelType = (IModelTypeJv) model.BaseModelType;
                        if (baseModelType != null && (baseModelType.ModelTypeName == "Resource" || baseModelType.ModelTypeName == "SubResource"))
                        {
                            AppendInnerToTopLevelType(model, codeModel, JavaSettings.Instance);
                        }
                    }
                }

                // param order (PATH first)
                foreach (MethodJv method in codeModel.Methods)
                {
                    List<AutoRest.Core.Model.Parameter> parameters = method.Parameters.ToList();
                    method.ClearParameters();
                    foreach (AutoRest.Core.Model.Parameter parameter in parameters.Where(x => x.Location == ParameterLocation.Path))
                    {
                        method.Add(parameter);
                    }
                    foreach (AutoRest.Core.Model.Parameter parameter in parameters.Where(x => x.Location != ParameterLocation.Path))
                    {
                        method.Add(parameter);
                    }
                }

                ProcessCustomTypeCompositeProperties(codeModel, JavaSettings.Instance);
            }
            return codeModel;
        }

        private static void AppendInnerToTopLevelType(IModelType type, CodeModelJv serviceClient, JavaSettings settings)
        {
            if (type != null)
            {
                if (type is CompositeTypeJv compositeType)
                {
                    string compositeTypeName = compositeType.Name.ToString();
                    if (!string.IsNullOrEmpty(compositeTypeName) && compositeType.IsInnerModel)
                    {
                        compositeTypeName += "Inner";
                    }

                    bool compositeTypeIsAzureResourceExtension = compositeType.Extensions.Get<bool>(AzureExtensions.AzureResourceExtension) == true;
                    if (compositeTypeName != "Resource" && (compositeTypeName != "SubResource" || !compositeTypeIsAzureResourceExtension))
                    {
                        compositeType.IsInnerModel = true;
                    }
                }
                else if (type is SequenceType sequenceType)
                {
                    AppendInnerToTopLevelType(sequenceType.ElementType, serviceClient, settings);
                }
                else if (type is DictionaryType dictionaryType)
                {
                    AppendInnerToTopLevelType(dictionaryType.ValueType, serviceClient, settings);
                }
            }
        }

        private static void ProcessCustomTypeCompositeProperties(CodeModelJv codeModel, JavaSettings settings)
        {
            HashSet<string> customTypes = new HashSet<string>(settings.CustomTypes);
            foreach (CompositeType ct in codeModel.ModelTypes)
            {
                if (customTypes.Contains(ct.ClassName))
                {
                    ProcessCustomTypeCompositePropertiesInternal(ct, customTypes);
                }
            }
            settings.CustomTypes = customTypes;
        }

        private static void ProcessCustomTypeCompositePropertiesInternal(CompositeType compositeType, HashSet<string> customTypes)
        {
            foreach (var t in compositeType.Properties.Select(p => p.ModelType))
            {
                if (t is CompositeType)
                {
                    if (!customTypes.Contains(t.ClassName))
                    {
                        customTypes.Add(t.ClassName);
                        ProcessCustomTypeCompositePropertiesInternal((CompositeType) t, customTypes);
                    }
                }
            }
        }
    }
}
