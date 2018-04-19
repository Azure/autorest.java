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
using AutoRest.Java.Azure.Model;
using AutoRest.Java.Model;
using static AutoRest.Core.Utilities.DependencyInjection;
using AutoRest.Java.Azure.Fluent.Model;
using AutoRest.Java.Azure.Fluent;
using Pluralize.NET;

namespace AutoRest.Java.Azure
{
    public class TransformerJvaf : TransformerJva, ITransformer<CodeModelJvaf>
    {
        private Pluralizer pluralizer = new Pluralizer();
        private List<string> addInner = new List<string>();
        private List<string> removeInner = new List<string>();

        public override CodeModelJv TransformCodeModel(CodeModel cm)
        {
            var codeModel = cm as CodeModelJva;

            // we're guaranteed to be in our language-specific context here.
            Settings.Instance.AddCredentials = true;

            // This extension from general extensions must be run prior to Azure specific extensions.
            AzureExtensions.ProcessParameterizedHost(codeModel);
            AzureExtensions.ProcessClientRequestIdExtension(codeModel);
            AzureExtensions.UpdateHeadMethods(codeModel);
            AzureExtensions.ProcessGlobalParameters(codeModel);
            AzureExtensions.FlattenModels(codeModel);
            AzureExtensions.FlattenMethodParameters(codeModel);
            ParameterGroupExtensionHelper.AddParameterGroups(codeModel);
            AddLongRunningOperations(codeModel);
            AzureExtensions.AddAzureProperties(codeModel);
            AzureExtensions.SetDefaultResponses(codeModel);
            MoveResourceTypeProperties(codeModel);
            NormalizeListMethods(codeModel);

            // set Parent on responses (required for pageable)
            foreach (MethodJva method in codeModel.Methods)
            {
                foreach (ResponseJva response in method.Responses.Values)
                {
                    response.Parent = method;
                }
                (method.DefaultResponse as ResponseJva).Parent = method;
                method.ReturnTypeJva.Parent = method;
            }
            AzureExtensions.AddPageableMethod(codeModel);

            // pluralize method groups
            foreach (var mg in codeModel.Operations)
            {
                mg.Name.OnGet += name => name.IsNullOrEmpty() || name.EndsWith("s", StringComparison.OrdinalIgnoreCase) ? $"{name}" : $"{name}s";
            }

            NormalizePaginatedMethods(codeModel, codeModel.pageClasses);

            // determine inner models
            NormalizeTopLevelTypes(codeModel);

            // param order (PATH first)
            foreach (MethodJva method in codeModel.Methods)
            {
                var ps = method.Parameters.ToList();
                method.ClearParameters();
                foreach (var p in ps.Where(x => x.Location == ParameterLocation.Path))
                {
                    method.Add(p);
                }
                foreach (var p in ps.Where(x => x.Location != ParameterLocation.Path))
                {
                    method.Add(p);
                }
            }

            return codeModel;
        }

        CodeModelJvaf ITransformer<CodeModelJvaf>.TransformCodeModel(CodeModel cm)
        {
            return TransformCodeModel(cm) as CodeModelJvaf;
        }

        /**
         * Adding inners to model types to preserve the good name for the fluent interface.
         * 
         * -	Add "inner" to all method responses and types extending "Resource" (e.g. VirtualMachine)
         * -	Otherwise add "inner" to all model types whose plural form is a collection name (e.g. Deployment)
         * -	Otherwise add "inner" to all model types containing properties of above types
         * 
         * If any of this missed a type you want to add "inner", you can provide them on the AutoRest command line with --add-inner=<comma separated model names>.
         * If any of this added an unnecessary "inner", you can exclude them with --remove-inner=<comma separated model names>.
         */
        public void NormalizeTopLevelTypes(CodeModel serviceClient)
        {
            var included = AutoRest.Core.Settings.Instance.Host?.GetValue<string>("add-inner").Result;
            if (included != null)
            {
                included.Split(',', StringSplitOptions.RemoveEmptyEntries).ForEach(addInner.Add);
            }
            var excluded = AutoRest.Core.Settings.Instance.Host?.GetValue<string>("remove-inner").Result;
            if (excluded != null)
            {
                excluded.Split(',', StringSplitOptions.RemoveEmptyEntries).ForEach(removeInner.Add);
            }

            foreach (var response in serviceClient.Methods
                .SelectMany(m => m.Responses)
                .Select(r => r.Value))
            {
                AppendInnerToTopLevelType(response.Body, serviceClient);
            }
            foreach (var model in serviceClient.ModelTypes)
            {
                if (addInner.Contains(model.Name))
                {
                    AppendInnerToTopLevelType(model, serviceClient);
                }
                if (model.BaseModelType != null && model.BaseModelType.IsResource())
                {
                    AppendInnerToTopLevelType(model, serviceClient);
                }
                else if (serviceClient.Operations.Any(o => o.Name.EqualsIgnoreCase(model.Name) || o.Name.EqualsIgnoreCase(pluralizer.Pluralize(model.Name)))) // Naive plural check
                {
                    AppendInnerToTopLevelType(model, serviceClient);
                }
            }
        }

        private void AppendInnerToTopLevelType(IModelType type, CodeModel serviceClient)
        {
            if (type == null || removeInner.Contains(type.Name))
            {
                return;
            }
            CompositeTypeJvaf compositeType = type as CompositeTypeJvaf;
            SequenceType sequenceType = type as SequenceType;
            DictionaryType dictionaryType = type as DictionaryType;
            if (compositeType != null && !compositeType.IsResource)
            {
                compositeType.IsInnerModel = true;
                foreach (var t in serviceClient.ModelTypes)
                {
                    foreach (var p in t.Properties.Where(p => p.ModelType is CompositeTypeJvaf && !((CompositeTypeJvaf)p.ModelType).IsInnerModel))
                    {
                        if (p.ModelTypeName.EqualsIgnoreCase(compositeType.Name)
                            || (p.ModelType is SequenceType && ((SequenceType)p.ModelType).ElementType.Name.EqualsIgnoreCase(compositeType.Name))
                            || (p.ModelType is DictionaryType && ((DictionaryType)p.ModelType).ValueType.Name.EqualsIgnoreCase(compositeType.Name)))
                        {
                            AppendInnerToTopLevelType(t, serviceClient);
                            break;
                        }
                    }
                }
            }
            else if (sequenceType != null)
            {
                AppendInnerToTopLevelType(sequenceType.ElementType, serviceClient);
            }
            else if (dictionaryType != null)
            {
                AppendInnerToTopLevelType(dictionaryType.ValueType, serviceClient);
            }
        }

        public virtual void NormalizeListMethods(CodeModel client)
        {
            if (client == null)
            {
                throw new ArgumentNullException("client");
            }

            foreach (var method in client.Methods)
            {
                if (method.Name.EqualsIgnoreCase(WellKnownMethodNames.List) && HasNonClientNonConstantRequiredParameters(method, 1) && method.Parameters.First().Name.StartsWith("resourceGroup"))
                {
                    method.Name = WellKnownMethodNames.ListByResourceGroup;
                }

                if ((method.Name.EqualsIgnoreCase(WellKnownMethodNames.ListAll) || method.Name.EqualsIgnoreCase(WellKnownMethodNames.ListBySubscription))
                    && HasNonClientNonConstantRequiredParameters(method, 0) && !client.Methods.Any(m => m.Name.RawValue == WellKnownMethodNames.List))
                {
                    method.Name = WellKnownMethodNames.List;
                }
            }
        }

        private static Method FindFirstMethodByName(IEnumerable<Method> methods, String methodName)
        {
            return methods.FirstOrDefault(method => method.Name.EqualsIgnoreCase(methodName));
        }

        private static bool HasNonClientNonConstantRequiredParameters(Method method, int requiredParameterCount)
        {
            // When parameters are optional we generate more methods.
            return method.Parameters.Count(x => !x.IsClientProperty && !x.IsConstant && x.IsRequired) == requiredParameterCount;
        }
    }
}
