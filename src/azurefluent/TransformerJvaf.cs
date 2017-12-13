// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.
// 

using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Core.Utilities.Collections;
using AutoRest.Extensions;
using AutoRest.Extensions.Azure;
using AutoRest.Java.Azure.Fluent.Model;
using AutoRest.Java.Azure.Model;
using AutoRest.Java.DanModel;
using System;
using System.Linq;

namespace AutoRest.Java.Azure
{
    public class TransformerJvaf : TransformerJva
    {
        public override CodeModel TransformCodeModel(CodeModel codeModel)
        {
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

            // set Parent on responses (required for pageable)
            foreach (Method method in codeModel.Methods)
            {
                foreach (Response response in method.Responses.Values)
                {
                    DanCodeGenerator.ResponseSetParent(response, method);
                }
                DanCodeGenerator.ResponseSetParent(method.DefaultResponse, method);
                DanCodeGenerator.ResponseSetParent(method.ReturnType, method);
            }
            AzureExtensions.AddPageableMethod(codeModel);

            // pluralize method groups
            foreach (var mg in codeModel.Operations)
            {
                mg.Name.OnGet += name => name.IsNullOrEmpty() || name.EndsWith("s", StringComparison.OrdinalIgnoreCase) ? $"{name}" : $"{name}s";
            }

            NormalizePaginatedMethods(codeModel, DanCodeGenerator.pageClasses);

            // determine inner models
            NormalizeTopLevelTypes(codeModel);

            // param order (PATH first)
            foreach (Method method in codeModel.Methods)
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

        public void NormalizeTopLevelTypes(CodeModel serviceClient)
        {
            foreach (Parameter param in serviceClient.Methods.SelectMany(m => m.Parameters))
            {
                AppendInnerToTopLevelType(param.ModelType, serviceClient);
            }
            foreach (Response response in serviceClient.Methods.SelectMany(m => m.Responses).Select(r => r.Value))
            {
                AppendInnerToTopLevelType(response.Body, serviceClient);
                AppendInnerToTopLevelType(response.Headers, serviceClient);
            }
            foreach (CompositeType model in serviceClient.ModelTypes)
            {
                if (model.BaseModelType != null && (DanCodeGenerator.GetIModelTypeName(model.BaseModelType) == "Resource" || DanCodeGenerator.GetIModelTypeName(model.BaseModelType) == "SubResource"))
                    AppendInnerToTopLevelType(model, serviceClient);
            }
        }

        private void AppendInnerToTopLevelType(IModelType type, CodeModel serviceClient)
        {
            if (type == null)
            {
                return;
            }
            CompositeType compositeType = type as CompositeType;
            SequenceType sequenceType = type as SequenceType;
            DictionaryType dictionaryType = type as DictionaryType;
            if (compositeType != null && !DanCodeGenerator.CompositeTypeIsResource(compositeType))
            {
                DanCodeGenerator.innerModelCompositeType.Add(compositeType);
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
    }
}
