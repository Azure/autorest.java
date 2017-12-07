// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Extensions;
using AutoRest.Extensions.Azure;
using AutoRest.Java.Azure.Model;
using AutoRest.Java.Model;
using AutoRest.Core.Utilities.Collections;
using static AutoRest.Core.Utilities.DependencyInjection;
using Newtonsoft.Json;
using System.Text.RegularExpressions;
using System.Text;
using System.Collections.Immutable;
using AutoRest.Java.DanModel;

namespace AutoRest.Java.Azure.Model
{
    public class MethodJva : MethodJv
    {
        [JsonIgnore]
        public string ClientRequestIdString => AzureExtensions.GetClientRequestIdString(this);

        [JsonIgnore]
        public string RequestIdString => AzureExtensions.GetRequestIdString(this);

        /// <summary>
        /// Returns true if method has x-ms-long-running-operation extension.
        /// </summary>
        [JsonIgnore]
        public bool IsLongRunningOperation
        {
            get { return Extensions.ContainsKey(AzureExtensions.LongRunningExtension); }
        }

        [JsonIgnore]
        public bool IsPagingNextOperation
        {
            get { return Extensions.ContainsKey("nextLinkMethod") && (bool) Extensions["nextLinkMethod"]; }
        }

        [JsonIgnore]
        public bool IsPagingOperation => Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                    Extensions[AzureExtensions.PageableExtension] != null &&
                    !IsPagingNextOperation;

        [JsonIgnore]
        public bool IsPagingNonPollingOperation => Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                    Extensions[AzureExtensions.PageableExtension] == null &&
                    !IsPagingNextOperation;

        [JsonIgnore]
        public bool SimulateAsPagingOperation { set; get; } = false;

        [JsonIgnore]
        public ResponseJva ReturnTypeJva => ReturnType as ResponseJva;

        /// <summary>
        /// Get the type for operation exception.
        /// </summary>
        [JsonIgnore]
        public override string OperationExceptionTypeString
        {
            get
            {
                if (DefaultResponse.Body == null || DanCodeGenerator.GetIModelTypeName(DefaultResponse.Body) == "CloudError")
                {
                    return "CloudException";
                }
                else if (this.DefaultResponse.Body is CompositeType)
                {
                    CompositeTypeJva type = this.DefaultResponse.Body as CompositeTypeJva;
                    return type.ExceptionTypeDefinitionName;
                }
                else
                {
                    return "RestException";
                }
            }
        }

        [JsonIgnore]
        public override IEnumerable<ParameterJv> RetrofitParameters
        {
            get
            {
                List<ParameterJv> parameters = base.RetrofitParameters.ToList();
                
                if (IsPagingNextOperation)
                {
                    parameters.RemoveAll(p => p.Location == ParameterLocation.Path);

                    var nextUrlParam = new ParameterJv
                    {
                        Name = "nextUrl",
                        SerializedName = "nextUrl",
                        ModelType = New<PrimaryType>(KnownPrimaryType.String),
                        Documentation = "The URL to get the next page of items.",
                        Location = ParameterLocation.Path,
                        IsRequired = true
                    };
                    nextUrlParam.Extensions.Add(AzureExtensions.SkipUrlEncodingExtension, true);
                    parameters.Insert(0, nextUrlParam);
                }

                return parameters;
            }
        }

        [JsonIgnore]
        public override string MethodParameterApiDeclaration
        {
            get
            {
                var declaration = base.MethodParameterApiDeclaration;
                foreach (var parameter in RetrofitParameters.Where(p => 
                    p.Location == ParameterLocation.Path || p.Location == ParameterLocation.Query))
                {
                    if (parameter.Extensions.ContainsKey(AzureExtensions.SkipUrlEncodingExtension) &&
                        (bool) parameter.Extensions[AzureExtensions.SkipUrlEncodingExtension] == true)
                    {
                        declaration = declaration.Replace(
                            string.Format(CultureInfo.InvariantCulture, "@{0}Param(\"{1}\")", parameter.Location.ToString(), parameter.SerializedName),
                            string.Format(CultureInfo.InvariantCulture, "@{0}Param(value = \"{1}\", encoded = true)", parameter.Location.ToString(), parameter.SerializedName));
                    }
                }
                return declaration;
            }
        }

        [JsonIgnore]
        public override string MethodParameterDeclaration
        {
            get
            {
                if (this.IsPagingOperation || this.IsPagingNextOperation)
                {
                    List<string> declarations = new List<string>();
                    foreach (var parameter in LocalParameters.Where(p => !p.IsConstant))
                    {
                        declarations.Add("final " + DanCodeGenerator.GetIModelTypeName(DanCodeGenerator.GetIModelTypeParameterVariant(parameter.ClientType)) + " " + parameter.Name);
                    }

                    var declaration = string.Join(", ", declarations);
                    return declaration;
                }
                return base.MethodParameterDeclaration;
            }
        }

        [JsonIgnore]
        public override string MethodRequiredParameterDeclaration
        {
            get
            {
                if (this.IsPagingOperation || this.IsPagingNextOperation)
                {
                    List<string> declarations = new List<string>();
                    foreach (var parameter in LocalParameters.Where(p => !p.IsConstant && p.IsRequired))
                    {
                        declarations.Add("final " + DanCodeGenerator.GetIModelTypeName(DanCodeGenerator.GetIModelTypeParameterVariant(parameter.ClientType)) + " " + parameter.Name);
                    }

                    var declaration = string.Join(", ", declarations);
                    return declaration;
                }
                return base.MethodRequiredParameterDeclaration;
            }
        }

        [JsonIgnore]
        public override string MethodParameterDeclarationWithCallback
        {
            get
            {
                var parameters = MethodParameterDeclaration;
                if (!parameters.IsNullOrEmpty())
                {
                    parameters += ", ";
                }
                if (this.IsPagingOperation)
                {
                    parameters += string.Format(CultureInfo.InvariantCulture, "final ListOperationCallback<{0}> serviceCallback",
                        ReturnTypeJva.SequenceElementTypeString);
                }
                else if (this.IsPagingNextOperation)
                {
                    parameters += string.Format(CultureInfo.InvariantCulture, "final ServiceFuture<{0}> serviceFuture, final ListOperationCallback<{1}> serviceCallback",
                        ReturnTypeJva.ServiceFutureGenericParameterString, ReturnTypeJva.SequenceElementTypeString);
                }
                else
                {
                    parameters += string.Format(CultureInfo.InvariantCulture, "final ServiceCallback<{0}> serviceCallback", ReturnTypeJva.GenericBodyClientTypeString);
                }
                
                return parameters;
            }
        }

        [JsonIgnore]
        public override string MethodRequiredParameterDeclarationWithCallback
        {
            get
            {
                var parameters = MethodRequiredParameterDeclaration;
                if (!parameters.IsNullOrEmpty())
                {
                    parameters += ", ";
                }
                if (this.IsPagingOperation)
                {
                    parameters += string.Format(CultureInfo.InvariantCulture, "final ListOperationCallback<{0}> serviceCallback",
                        ReturnTypeJva.SequenceElementTypeString);
                }
                else if (this.IsPagingNextOperation)
                {
                    parameters += string.Format(CultureInfo.InvariantCulture, "final ServiceFuture<{0}> serviceFuture, final ListOperationCallback<{1}> serviceCallback",
                        ReturnTypeJva.ServiceFutureGenericParameterString, ReturnTypeJva.SequenceElementTypeString);
                }
                else
                {
                    parameters += string.Format(CultureInfo.InvariantCulture, "final ServiceCallback<{0}> serviceCallback", ReturnTypeJva.GenericBodyClientTypeString);
                }

                return parameters;
            }
        }

        [JsonIgnore]
        public override string MethodParameterInvocationWithCallback
        {
            get
            {
                if (this.IsPagingOperation || this.IsPagingNextOperation)
                {
                    return base.MethodParameterInvocationWithCallback.Replace("serviceCallback", "serviceFuture, serviceCallback");
                }
                return base.MethodParameterInvocationWithCallback;
            }
        }

        [JsonIgnore]
        public override string MethodRequiredParameterInvocationWithCallback
        {
            get
            {
                if (this.IsPagingOperation || this.IsPagingNextOperation)
                {
                    return base.MethodRequiredParameterInvocationWithCallback.Replace("serviceCallback", "serviceFuture, serviceCallback");
                }
                return base.MethodRequiredParameterInvocationWithCallback;
            }
        }

        [JsonIgnore]
        public override bool IsParameterizedHost => 
            (CodeModel?.Extensions?.ContainsKey(SwaggerExtensions.ParameterizedHostExtension) ?? false) && 
            !IsPagingNextOperation;

        [JsonIgnore]
        public override IEnumerable<string> Exceptions
        {
            get
            {
                var exceptions = base.Exceptions.ToList();
                if (this.IsLongRunningOperation)
                {
                    exceptions.Add("InterruptedException");
                }
                return exceptions;
            }
        }

        [JsonIgnore]
        public override List<string> ExceptionStatements
        {
            get
            {
                List<string> exceptions = base.ExceptionStatements;
                if (this.IsLongRunningOperation)
                {
                    exceptions.Add("InterruptedException exception thrown when long running operation is interrupted");
                }
                return exceptions;
            }
        }

        [JsonIgnore]
        public string PollingMethod
        {
            get
            {
                string method;
                if (this.HttpMethod == HttpMethod.Put || this.HttpMethod == HttpMethod.Patch)
                {
                    method = "getPutOrPatchResult";
                }
                else if (this.HttpMethod == HttpMethod.Delete || this.HttpMethod == HttpMethod.Post)
                {
                    method = "getPostOrDeleteResult";
                }
                else
                {
                    throw new InvalidOperationException("Invalid long running operation HTTP method " + this.HttpMethod);
                }
                if (ReturnType.Headers != null)
                {
                    method += "WithHeaders";
                }
                return method;
            }
        }

        [JsonIgnore]
        public string PollingResourceTypeArgs
        {
            get
            {
                string args = "new TypeToken<" + ReturnTypeJva.GenericBodyClientTypeString + ">() { }.getType()";
                if (ReturnType.Headers != null)
                {
                    args += ", " + DanCodeGenerator.GetIModelTypeName(ReturnTypeJva.HeaderWireType) + ".class";
                }
                return args;
            }
        }

        [JsonIgnore]
        public string AsyncClientReturnTypeString
        {
            get
            {
                if (IsLongRunningOperation)
                {
                    return $"Observable<OperationStatus<{ReturnTypeJv.ServiceResponseGenericParameterString}>>";
                }
                else if (IsPagingOperation || IsPagingNextOperation)
                {
                    return $"Observable<{ReturnTypeJv.ServiceResponseGenericParameterString}>";
                }
                else
                {
                    return $"Single<{ReturnTypeJv.ServiceResponseGenericParameterString}>";
                }
            }
        }

        [JsonIgnore]
        public override string ReturnTypeResponseName =>
            DanCodeGenerator.GetIModelTypeName(ReturnTypeJv?.BodyClientType?.ServiceResponseVariant());

        public string PagingGroupedParameterTransformation(bool filterRequired = false)
        {
            var builder = new IndentedStringBuilder();
            if (IsPagingOperation)
            {
                string invocation;
                MethodJva nextMethod = GetPagingNextMethodWithInvocation(out invocation);
                TransformPagingGroupedParameter(builder, nextMethod, filterRequired);
            }
            return builder.ToString();
        }

        public string NextMethodParameterInvocation(bool filterRequired = false)
        {
            string invocation;
            MethodJva nextMethod = GetPagingNextMethodWithInvocation(out invocation);
            if (filterRequired)
            {
                if (this.InputParameterTransformation.IsNullOrEmpty() || nextMethod.InputParameterTransformation.IsNullOrEmpty())
                {
                    return nextMethod.MethodDefaultParameterInvocation;
                }
                var groupedType = this.InputParameterTransformation.First().ParameterMappings[0].InputParameter;
                var nextGroupType = nextMethod.InputParameterTransformation.First().ParameterMappings[0].InputParameter;
                List<string> invocations = new List<string>();
                foreach (var parameter in nextMethod.LocalParameters)
                {
                    if (parameter.IsRequired)
                    {
                        invocations.Add(parameter.Name);
                    }
                    else if (parameter.Name == nextGroupType.Name && groupedType.IsRequired)
                    {
                        invocations.Add(parameter.Name);
                    }
                    else
                    {
                        invocations.Add("null");
                    }
                }
                return string.Join(", ", invocations);
            }
            else
            {
                return nextMethod.MethodParameterInvocation;
            }
        }

        [JsonIgnore]
        public string PagingNextPageLinkParameterName
        {
            get
            {
                string invocation;
                MethodJva nextMethod = GetPagingNextMethodWithInvocation(out invocation);
                return nextMethod.Parameters.First(p => p.Name.ToString().StartsWith("next", StringComparison.OrdinalIgnoreCase)).Name;
            }
        }

        private MethodJva GetPagingNextMethodWithInvocation(out string invocation, bool async = false, bool singlePage = true)
        {
            String methodSuffixString = "";
            if (singlePage)
            {
                methodSuffixString = "SinglePage";
            }
            if (IsPagingNextOperation)
            {
                invocation = Name + methodSuffixString + (async ? "Async" : "");
                return this;
            }
            string name = this.Extensions.GetValue<Fixable<string>>("nextMethodName")?.ToCamelCase();
            string group = this.Extensions.GetValue<Fixable<string>>("nextMethodGroup")?.ToCamelCase();
            group = CodeNamerJva.Instance.GetMethodGroupName(group);

            // The PagingNextMethod can be located in a different method group than this one.
            // It may or may not be explicitly declared.
            var methodModel =
                CodeModel.Methods.FirstOrDefault(m =>
                    (group == null ? m.Group == null : group.Equals(m.Group, StringComparison.OrdinalIgnoreCase))
                    && m.Name.ToString().Equals(name, StringComparison.OrdinalIgnoreCase)) as MethodJva;
            group = group.ToPascalCase();
            name = name + methodSuffixString;
            if (async)
            {
                name = name + "Async";
            }
            if (group == null || this.Group == methodModel.Group)
            {
                invocation = name;
            }
            else
            {
                invocation = string.Format(CultureInfo.InvariantCulture, "{0}.get{1}().{2}", ClientReference.Replace("this.", ""), group, name);
            }
            return methodModel;
        }

        public string GetPagingNextMethodInvocation(bool async = false, bool singlePage = true)
        {
            string invocation;
            GetPagingNextMethodWithInvocation(out invocation, async, singlePage);
            return invocation;
        }

        protected virtual void TransformPagingGroupedParameter(IndentedStringBuilder builder, MethodJva nextMethod, bool filterRequired = false)
        {
            if (this.InputParameterTransformation.IsNullOrEmpty() || nextMethod.InputParameterTransformation.IsNullOrEmpty())
            {
                return;
            }
            var groupedType = this.InputParameterTransformation.First().ParameterMappings[0].InputParameter;
            var nextGroupType = nextMethod.InputParameterTransformation.First().ParameterMappings[0].InputParameter;
            if (nextGroupType.Name == groupedType.Name)
            {
                return;
            }
            var nextGroupTypeName = CodeNamerJva.Instance.GetTypeName(nextGroupType.Name);
            if (filterRequired && !groupedType.IsRequired)
            {
                return;
            }
            if (!groupedType.IsRequired)
            {
                builder.AppendLine("{0} {1} = null;", nextGroupTypeName, nextGroupType.Name.ToCamelCase());
                builder.AppendLine("if ({0} != null) {{", groupedType.Name.ToCamelCase());
                builder.Indent();
                builder.AppendLine("{0} = new {1}();", nextGroupType.Name.ToCamelCase(), nextGroupTypeName);
            }
            else
            {
                builder.AppendLine("{1} {0} = new {1}();", nextGroupType.Name.ToCamelCase(), nextGroupTypeName);
            }
            foreach (var outParam in nextMethod.InputParameterTransformation.Select(t => t.OutputParameter))
            {
                builder.AppendLine("{0}.with{1}({2}.{3}());", nextGroupType.Name.ToCamelCase(), outParam.Name.ToPascalCase(), groupedType.Name.ToCamelCase(), outParam.Name.ToCamelCase());
            }
            if (!groupedType.IsRequired)
            {
                builder.Outdent().AppendLine(@"}");
            }
        }

        [JsonIgnore]
        public string NextUrlConstruction
        {
            get
            {
                var builder = new StringBuilder("String.format(");
                var regex = new Regex("{\\w+}");
                var matches = regex.Matches(Url);
                builder.Append("\"").Append(regex.Replace(Url, "%s").TrimStart('/')).Append("\"");
                foreach (Match match in matches)
                {
                    var sn = match.Value.Trim('{', '}');
                    builder.Append(", " + base.RetrofitParameters.First(p => p.SerializedName == sn).WireName);
                }
                return builder.Append(")").ToString();
            }
        }

        [JsonIgnore]
        public override string RuntimeBasePackage
        {
            get
            {
                return "com.microsoft.azure.v2";
            }
        }

        [JsonIgnore]
        public override List<string> InterfaceImports
        {
            get
            {
                var imports = base.InterfaceImports.ToList();
                if (this.IsLongRunningOperation)
                {
                    imports.Add("com.microsoft.azure.v2.OperationStatus");
                }
                if (this.IsPagingOperation || this.IsPagingNextOperation)
                {
                    imports.Remove("com.microsoft.rest.v2.ServiceCallback");
                    imports.Add("com.microsoft.azure.v2.ListOperationCallback");
                    imports.Add("com.microsoft.azure.v2.Page");
                    imports.Add("com.microsoft.azure.v2.PagedList");
                }
                
                return imports;
            }
        }

        public override bool ShouldGenerateBeginRestResponseMethod()
        {
            return !IsLongRunningOperation && !IsPagingOperation && !IsPagingNextOperation;
        }

        private ImmutableArray<string> cachedImplImports = default(ImmutableArray<string>);

        [JsonIgnore]
        public override ImmutableArray<string> ImplImports
        {
            get
            {
                if (cachedImplImports.IsDefault)
                {
                    var imports = base.ImplImports.ToList();
                    if (this.IsLongRunningOperation)
                    {
                        imports.Add("com.microsoft.azure.v2.OperationStatus");
                        imports.Add("com.microsoft.azure.v2.util.ServiceFutureUtil");
                        imports.Remove("com.microsoft.azure.v2.AzureResponseBuilder");
                        this.Responses.Select(r => r.Value.Body).Concat(new IModelType[] { DefaultResponse.Body })
                            .SelectMany(t => t.ImportSafe())
                            .Where(i => !this.Parameters.Any(p => p.ModelType.ImportSafe().Contains(i)))
                            .ForEach(i => imports.Remove(i));
                        // return type may have been removed as a side effect
                        imports.AddRange(ReturnTypeJva.ImplImports);
                    }
                    string typeName = (ReturnTypeJva.BodyClientType as SequenceTypeJva)?.PageImplType;
                    CompositeType ctype = null;
                    if (typeName != null)
                    {
                        ctype = new CompositeTypeJva();
                        ctype.Name.CopyFrom(typeName);
                        ctype.CodeModel = CodeModel;
                    }
                    if (this.IsPagingOperation || this.IsPagingNextOperation)
                    {
                        imports.Remove("java.util.ArrayList");
                        imports.Remove("com.microsoft.rest.v2.ServiceCallback");
                        imports.Add("com.microsoft.azure.v2.ListOperationCallback");
                        imports.Add("com.microsoft.azure.v2.Page");
                        imports.Add("com.microsoft.azure.v2.PagedList");
                        imports.AddRange(ctype.ImportSafe());
                    }
                    if (this.IsPagingNonPollingOperation)
                    {
                        imports.AddRange(ctype.ImportSafe());
                    }
                    cachedImplImports = imports.ToImmutableArray();
                }

                return cachedImplImports;
            }
        }
    }
}