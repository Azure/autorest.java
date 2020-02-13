﻿// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Text;
using AutoRest.Core;
using AutoRest.Core.Utilities;
using AutoRest.Extensions;
using AutoRest.Core.Model;
using Newtonsoft.Json;
using AutoRest.Core.Utilities.Collections;

namespace AutoRest.Java.Model
{
    public class MethodJv : Method
    {
        [JsonIgnore]
        public virtual IEnumerable<ParameterJv> RetrofitParameters
        {
            get
            {
                var parameters = LogicalParameters.OfType<ParameterJv>().Where(p => p.Location != ParameterLocation.None)
                    .Where(p => !p.Extensions.ContainsKey("hostParameter")).ToList();
                if (IsParameterizedHost)
                {
                    parameters.Add(new ParameterJv
                    {
                        Name = "parameterizedHost",
                        SerializedName = "x-ms-parameterized-host",
                        Location = ParameterLocation.Header,
                        IsRequired = true,
                        ModelType = new PrimaryTypeJv(KnownPrimaryType.String)
                    });
                }
                return parameters;
            }
        }

        [JsonIgnore]
        public IEnumerable<ParameterJv> OrderedRetrofitParameters
        {
            get
            {
                return RetrofitParameters.Where(p => p.Location == ParameterLocation.Path)
                    .Union(RetrofitParameters.Where(p => p.Location != ParameterLocation.Path));
            }
        }

        /// <summary>
        /// Generate the method parameter declarations for a method
        /// </summary>
        [JsonIgnore]
        public virtual string MethodParameterApiDeclaration
        {
            get
            {
                List<string> declarations = new List<string>();
                foreach (ParameterJv parameter in OrderedRetrofitParameters)
                {
                    bool alreadyEncoded = parameter.Extensions.ContainsKey(SwaggerExtensions.SkipUrlEncodingExtension) &&
                        (bool) parameter.Extensions[SwaggerExtensions.SkipUrlEncodingExtension] == true;

                    StringBuilder declarationBuilder = new StringBuilder();
                    if (Url.Contains("{" + parameter.Name + "}"))
                    {
                        parameter.Location = ParameterLocation.Path;
                    }

                    if ((parameter.Location == ParameterLocation.Path || parameter.Location == ParameterLocation.Query) && alreadyEncoded)
                    {
                        declarationBuilder.Append(string.Format(CultureInfo.InvariantCulture,
                            "@{0}(value = \"{1}\", encoded = true) ",
                            parameter.Location.ToString(),
                            parameter.SerializedName));
                    }
                    else if (parameter.Location == ParameterLocation.Path ||
                        parameter.Location == ParameterLocation.Query ||
                        parameter.Location == ParameterLocation.Header)
                    {
                        declarationBuilder.Append(string.Format(CultureInfo.InvariantCulture,
                            "@{0}(\"{1}\") ",
                            parameter.Location.ToString(),
                            parameter.SerializedName));
                    }
                    else if (parameter.Location == ParameterLocation.Body)
                    {
                        declarationBuilder.Append(string.Format(CultureInfo.InvariantCulture,
                            "@{0} ",
                            parameter.Location.ToString()));
                    }
                    else if (parameter.Location == ParameterLocation.FormData)
                    {
                        string annotation;
                        if (parameter.Method.RequestContentType == "multipart/form-data")
                        {
                            annotation = "Part";
                        }
                        else
                        {
                            annotation = "Field";
                        }
                        declarationBuilder.Append(string.Format(CultureInfo.InvariantCulture,
                            "@{0}(\"{1}\") ",
                            annotation, parameter.SerializedName));
                    }
                    var declarativeName = parameter.ClientProperty != null ? parameter.ClientProperty.Name : parameter.Name;
                    declarationBuilder.Append(parameter.WireType.Name);
                    declarationBuilder.Append(" " + declarativeName);
                    declarations.Add(declarationBuilder.ToString());
                }

                var declaration = string.Join(", ", declarations);
                return declaration;
            }
        }

        [JsonIgnore]
        public virtual string MethodParameterDeclaration
        {
            get
            {
                List<string> declarations = new List<string>();
                foreach (var parameter in LocalParameters.Where(p => !p.IsConstant))
                {
                    declarations.Add(parameter.ClientType.ParameterVariant.Name + " " + parameter.Name);
                }

                var declaration = string.Join(", ", declarations);
                return declaration;
            }
        }

        [JsonIgnore]
        public virtual string MethodRequiredParameterDeclaration
        {
            get
            {
                List<string> declarations = new List<string>();
                foreach (var parameter in LocalParameters.Where(p => !p.IsConstant && p.IsRequired))
                {
                    declarations.Add(parameter.ClientType.ParameterVariant.Name + " " + parameter.Name);
                }

                var declaration = string.Join(", ", declarations);
                return declaration;
            }
        }

        [JsonIgnore]
        public string MethodParameterInvocation
        {
            get
            {
                List<string> invocations = new List<string>();
                foreach (var parameter in LocalParameters.Where(p => !p.IsConstant))
                {
                    invocations.Add(parameter.Name);
                }

                var declaration = string.Join(", ", invocations);
                return declaration;
            }
        }

        [JsonIgnore]
        public string MethodDefaultParameterInvocation
        {
            get
            {
                List<string> invocations = new List<string>();
                foreach (var parameter in LocalParameters)
                {
                    if (parameter.IsRequired)
                    {
                        invocations.Add(parameter.Name);
                    }
                    else
                    {
                        invocations.Add("null");
                    }
                }

                var declaration = string.Join(", ", invocations);
                return declaration;
            }
        }

        [JsonIgnore]
        public string MethodRequiredParameterInvocation
        {
            get
            {
                List<string> invocations = new List<string>();
                foreach (var parameter in LocalParameters)
                {
                    if (parameter.IsRequired && !parameter.IsConstant)
                    {
                        invocations.Add(parameter.Name);
                    }
                }

                var declaration = string.Join(", ", invocations);
                return declaration;
            }
        }

        [JsonIgnore]
        public string MethodParameterApiInvocation
        {
            get
            {
                List<string> invocations = new List<string>();
                foreach (var parameter in OrderedRetrofitParameters)
                {
                    invocations.Add(parameter.WireName);
                }

                var declaration = string.Join(", ", invocations);
                return declaration;
            }
        }

        [JsonIgnore]
        public string MethodRequiredParameterApiInvocation
        {
            get
            {
                List<string> invocations = new List<string>();
                foreach (var parameter in OrderedRetrofitParameters)
                {
                    invocations.Add(parameter.WireName);
                }

                var declaration = string.Join(", ", invocations);
                return declaration;
            }
        }

        [JsonIgnore]
        public virtual bool IsParameterizedHost => CodeModel.Extensions.ContainsKey(SwaggerExtensions.ParameterizedHostExtension);

        /// <summary>
        /// Generate a reference to the ServiceClient
        /// </summary>
        [JsonIgnore]
        public string ClientReference => Group.IsNullOrEmpty() ? "this" : "this.client";

        [JsonIgnore]
        public string ParameterConversion
        {
            get
            {
                IndentedStringBuilder builder = new IndentedStringBuilder();
                foreach (var p in RetrofitParameters)
                {
                    if (p.NeedsConversion)
                    {
                        builder.Append(p.ConvertToWireType(p.Name, ClientReference));
                    }
                }
                return builder.ToString();
            }
        }

        [JsonIgnore]
        public string RequiredParameterConversion
        {
            get
            {
                IndentedStringBuilder builder = new IndentedStringBuilder();
                foreach (var p in RetrofitParameters.Where(p => p.IsRequired))
                {
                    if (p.NeedsConversion)
                    {
                        builder.Append(p.ConvertToWireType(p.Name, ClientReference));
                    }
                }
                return builder.ToString();
            }
        }

        /// <summary>
        /// Generates input mapping code block.
        /// </summary>
        /// <returns></returns>
        public virtual string BuildInputMappings(bool filterRequired = false)
        {
            var builder = new IndentedStringBuilder();
            foreach (var transformation in InputParameterTransformation)
            {
                var outParamName = transformation.OutputParameter.Name;
                while (Parameters.Any(p => p.Name == outParamName))
                {
                    outParamName += "1";
                }
                transformation.OutputParameter.Name = outParamName;
                var nullCheck = BuildNullCheckExpression(transformation);
                bool conditionalAssignment = !string.IsNullOrEmpty(nullCheck) && !transformation.OutputParameter.IsRequired && !filterRequired;
                if (conditionalAssignment)
                {
                    builder.AppendLine("{0} {1} = null;",
                            ((ParameterJv) transformation.OutputParameter).ClientType.ParameterVariant.Name,
                            outParamName);
                    builder.AppendLine("if ({0}) {{", nullCheck).Indent();
                }

                if (transformation.ParameterMappings.Any(m => !string.IsNullOrEmpty(m.OutputParameterProperty)) &&
                    transformation.OutputParameter.ModelType is CompositeType)
                {
                    builder.AppendLine("{0}{1} = new {2}();",
                        !conditionalAssignment ? ((ParameterJv)transformation.OutputParameter).ClientType.ParameterVariant.Name + " " : "",
                        outParamName,
                        transformation.OutputParameter.ModelType.Name);
                }

                foreach (var mapping in transformation.ParameterMappings)
                {
                    builder.AppendLine("{0}{1}{2};",
                        !conditionalAssignment && !(transformation.OutputParameter.ModelType is CompositeType) ?
                            ((ParameterJv)transformation.OutputParameter).ClientType.ParameterVariant.Name + " " : "",
                        outParamName,
                        GetMapping(mapping, filterRequired));
                }

                if (conditionalAssignment)
                {
                    builder.Outdent()
                       .AppendLine("}");
                }
            }

            return builder.ToString();
        }

        private static string GetMapping(ParameterMapping mapping, bool filterRequired = false)
        {
            string inputPath = mapping.InputParameter.Name;
            if (mapping.InputParameterProperty != null)
            {
                inputPath += "." + CodeNamer.Instance.CamelCase(mapping.InputParameterProperty) + "()";
            }
            if (filterRequired && !mapping.InputParameter.IsRequired)
            {
                inputPath = "null";
            }

            string outputPath = "";
            if (mapping.OutputParameterProperty != null)
            {
                outputPath += ".with" + CodeNamer.Instance.PascalCase(mapping.OutputParameterProperty);
                return string.Format(CultureInfo.InvariantCulture, "{0}({1})", outputPath, inputPath);
            }
            else
            {
                return string.Format(CultureInfo.InvariantCulture, "{0} = {1}", outputPath, inputPath);
            }
        }

        private static string BuildNullCheckExpression(ParameterTransformation transformation)
        {
            if (transformation == null)
            {
                throw new ArgumentNullException("transformation");
            }

            return string.Join(" || ",
                transformation.ParameterMappings
                    .Where(m => !m.InputParameter.IsRequired)
                    .Select(m => m.InputParameter.Name + " != null"));
        }

        [JsonIgnore]
        public IEnumerable<ParameterJv> RequiredNullableParameters
        {
            get
            {
                foreach (ParameterJv param in Parameters)
                {
                    if (!param.ModelType.IsPrimaryType(KnownPrimaryType.Int) &&
                        !param.ModelType.IsPrimaryType(KnownPrimaryType.Double) &&
                        !param.ModelType.IsPrimaryType(KnownPrimaryType.Boolean) &&
                        !param.ModelType.IsPrimaryType(KnownPrimaryType.Long) &&
                        !param.ModelType.IsPrimaryType(KnownPrimaryType.UnixTime) &&
                        !param.IsConstant && param.IsRequired)
                    {
                        yield return param;
                    }
                }
            }
        }

        [JsonIgnore]
        public IEnumerable<ParameterJv> ParametersToValidate
        {
            get
            {
                foreach (ParameterJv param in Parameters)
                {
                    if (param.ModelType is PrimaryType ||
                        param.ModelType is EnumType ||
                        param.IsConstant)
                    {
                        continue;
                    }
                    yield return param;
                }
            }
        }

        /// <summary>
        /// Gets the expression for response body initialization
        /// </summary>
        [JsonIgnore]
        public virtual string InitializeResponseBody
        {
            get
            {
                return string.Empty;
            }
        }

        [JsonIgnore]
        public virtual string MethodParameterDeclarationWithCallback
        {
            get
            {
                var parameters = MethodParameterDeclaration;
                if (!parameters.IsNullOrEmpty())
                {
                    parameters += ", ";
                }
                parameters += string.Format(CultureInfo.InvariantCulture, "final ServiceCallback<{0}> serviceCallback",
                    ReturnTypeJv.GenericBodyClientTypeString);
                return parameters;
            }
        }

        [JsonIgnore]
        public virtual string MethodRequiredParameterDeclarationWithCallback
        {
            get
            {
                var parameters = MethodRequiredParameterDeclaration;
                if (!parameters.IsNullOrEmpty())
                {
                    parameters += ", ";
                }
                parameters += string.Format(CultureInfo.InvariantCulture, "final ServiceCallback<{0}> serviceCallback",
                    ReturnTypeJv.GenericBodyClientTypeString);
                return parameters;
            }
        }

        [JsonIgnore]
        public virtual string MethodParameterInvocationWithCallback
        {
            get
            {
                var parameters = MethodParameterInvocation;
                if (!parameters.IsNullOrEmpty())
                {
                    parameters += ", ";
                }
                parameters += "serviceCallback";
                return parameters;
            }
        }

        [JsonIgnore]
        public virtual string MethodRequiredParameterInvocationWithCallback
        {
            get
            {
                var parameters = MethodDefaultParameterInvocation;
                if (!parameters.IsNullOrEmpty())
                {
                    parameters += ", ";
                }
                parameters += "serviceCallback";
                return parameters;
            }
        }

        /// <summary>
        /// Get the parameters that are actually method parameters in the order they appear in the method signature
        /// exclude global parameters
        /// </summary>
        [JsonIgnore]
        public IEnumerable<ParameterJv> LocalParameters
        {
            get
            {
                //Omit parameter-group properties for now since Java doesn't support them yet
                var par = Parameters
                    .OfType<ParameterJv>()
                    .Where(p => p != null && !p.IsClientProperty && !string.IsNullOrWhiteSpace(p.Name))
                    .OrderBy(item => !item.IsRequired)
                    .ToList();
                return par;
            }
        }

        [JsonIgnore]
        public string HostParameterReplacementArgs
        {
            get
            {
                var args = new List<string>();
                foreach (var param in Parameters.Where(p => p.Extensions.ContainsKey("hostParameter")))
                {
                    args.Add("\"{" + param.SerializedName + "}\", " + param.Name);
                }
                return string.Join(", ", args);
            }
        }

        /// <summary>
        /// Get the type for operation exception
        /// </summary>
        [JsonIgnore]
        public virtual string OperationExceptionTypeString
        {
            get
            {
                if (this.DefaultResponse.Body is CompositeType)
                {
                    var type = this.DefaultResponse.Body as CompositeTypeJv;
                    return type.ExceptionTypeDefinitionName;
                }
                else
                {
                    return "RestException";
                }
            }
        }

        [JsonIgnore]
        public virtual IEnumerable<string> Exceptions
        {
            get
            {
                yield return OperationExceptionTypeString;
                yield return "IOException";
                if (RequiredNullableParameters.Any())
                {
                    yield return "IllegalArgumentException";
                }
            }
        }

        [JsonIgnore]
        public virtual string ExceptionString
        {
            get
            {
                return string.Join(", ", Exceptions);
            }
        }

        [JsonIgnore]
        public virtual List<string> ExceptionStatements
        {
            get
            {
                List<string> exceptions = new List<string>();
                exceptions.Add(OperationExceptionTypeString + " exception thrown from REST call");
                exceptions.Add("IOException exception thrown from serialization/deserialization");
                if (RequiredNullableParameters.Any())
                {
                    exceptions.Add("IllegalArgumentException exception thrown from invalid parameters");
                }
                return exceptions;
            }
        }

        [JsonIgnore]
        public string CallType
        {
            get
            {
                if (this.HttpMethod == HttpMethod.Head)
                {
                    return "Void";
                }
                else
                {
                    return "ResponseBody";
                }
            }
        }

        [JsonIgnore]
        public virtual string ResponseBuilder
        {
            get
            {
                return "ServiceResponseBuilder";
            }
        }

        [JsonIgnore]
        public virtual string RuntimeBasePackage
        {
            get
            {
                return "com.microsoft.rest";
            }
        }

        [JsonIgnore]
        public ResponseJv ReturnTypeJv => ReturnType as ResponseJv;

        [JsonIgnore]
        public virtual string ReturnTypeResponseName => ReturnTypeJv?.BodyClientType?.ServiceResponseVariant()?.Name;

        public virtual string ResponseGeneration(bool filterRequired = false)
        {
            if (ReturnTypeJv.NeedsConversion)
            {
                IndentedStringBuilder builder= new IndentedStringBuilder();
                builder.AppendLine("ServiceResponse<{0}> response = {1}Delegate(call.execute());",
                    ReturnTypeJv.GenericBodyWireTypeString, this.Name.ToCamelCase());
                builder.AppendLine("{0} body = null;", ReturnTypeJv.BodyClientType.Name)
                    .AppendLine("if (response.body() != null) {")
                    .Indent().AppendLine("{0}", ReturnTypeJv.ConvertBodyToClientType("response.body()", "body"))
                    .Outdent().AppendLine("}");
                return builder.ToString();
            }
            return "";
        }

        [JsonIgnore]
        public virtual string ReturnValue
        {
            get
            {
                if (ReturnTypeJv.NeedsConversion)
                {
                    return "new ServiceResponse<" + ReturnTypeJv.GenericBodyClientTypeString + ">(body, response.response())";
                }
                return this.Name + "Delegate(call.execute())";
            }
        }

        public virtual string ClientResponse(bool filterRequired = false)
        {
            IndentedStringBuilder builder = new IndentedStringBuilder();
            if (ReturnTypeJv.NeedsConversion)
            {
                builder.AppendLine("ServiceResponse<{0}> result = {1}Delegate(response);", ReturnTypeJv.GenericBodyWireTypeString, this.Name);
                builder.AppendLine("{0} body = null;", ReturnTypeJv.BodyClientType.Name)
                    .AppendLine("if (result.body() != null) {")
                    .Indent().AppendLine("{0}", ReturnTypeJv.ConvertBodyToClientType("result.body()", "body"))
                    .Outdent().AppendLine("}");
                builder.AppendLine("ServiceResponse<{0}> clientResponse = new ServiceResponse<{0}>(body, result.response());",
                    ReturnTypeJv.GenericBodyClientTypeString);
            }
            else
            {
                builder.AppendLine("{0} clientResponse = {1}Delegate(response);", ReturnTypeJv.WireResponseTypeString, this.Name);
            }
            return builder.ToString();
        }

        [JsonIgnore]
        public virtual string ServiceFutureFactoryMethod
        {
            get
            {
                string factoryMethod = "fromResponse";
                if (ReturnType.Headers != null)
                {
                    factoryMethod = "fromHeaderResponse";
                }
                return factoryMethod;
            }
        }

        [JsonIgnore]
        public virtual string CallbackDocumentation
        {
            get
            {
                return " * @param serviceCallback the async ServiceCallback to handle successful and failed responses.";
            }
        }

        [JsonIgnore]
        public virtual List<string> InterfaceImports
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                // static imports
                imports.Add("rx.Observable");
                imports.Add("com.microsoft.rest.ServiceFuture");
                imports.Add("com.microsoft.rest." + ReturnTypeJv.ClientResponseType);
                imports.Add("com.microsoft.rest.ServiceCallback");
                // parameter types
                this.Parameters.OfType<ParameterJv>().ForEach(p => imports.AddRange(p.InterfaceImports));
                // return type
                imports.AddRange(this.ReturnTypeJv.InterfaceImports);
                // exceptions
                this.ExceptionString.Split(new string[] { ", " }, StringSplitOptions.RemoveEmptyEntries)
                    .ForEach(ex =>
                    {
                        string exceptionImport = CodeNamerJv.GetJavaException(ex, CodeModel);
                        if (exceptionImport != null) imports.Add(CodeNamerJv.GetJavaException(ex, CodeModel));
                    });                return imports.ToList();
            }
        }

        [JsonIgnore]
        public virtual List<string> ImplImports
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                // static imports
                imports.Add("rx.Observable");
                imports.Add("rx.functions.Func1");
                if (RequestContentType == "multipart/form-data")
                {
                    imports.Add("retrofit2.http.Multipart");
                }
                else if (RequestContentType == "application/x-www-form-urlencoded")
                {
                    imports.Add("retrofit2.http.FormUrlEncoded");
                }
                else
                {
                    imports.Add("retrofit2.http.Headers");
                }
                imports.Add("retrofit2.Response");
                if (this.HttpMethod != HttpMethod.Head)
                {
                    imports.Add("okhttp3.ResponseBody");
                }
                imports.Add("com.microsoft.rest.ServiceFuture");
                imports.Add("com.microsoft.rest." + ReturnTypeJv.ClientResponseType);
                imports.Add("com.microsoft.rest.ServiceCallback");
                this.RetrofitParameters.ForEach(p => imports.AddRange(p.RetrofitImports));
                // Http verb annotations
                imports.Add(this.HttpMethod.ImportFrom());
                // response type conversion
                if (this.Responses.Any())
                {
                    imports.Add("com.google.common.reflect.TypeToken");
                }
                // validation
                if (!ParametersToValidate.IsNullOrEmpty())
                {
                    imports.Add("com.microsoft.rest.Validator");
                }
                // parameters
                this.LocalParameters.Concat(this.LogicalParameters.OfType<ParameterJv>())
                    .ForEach(p => imports.AddRange(p.ClientImplImports));
                this.RetrofitParameters.ForEach(p => imports.AddRange(p.WireImplImports));
                // return type
                imports.AddRange(this.ReturnTypeJv.ImplImports);
                if (ReturnType.Body.IsPrimaryType(KnownPrimaryType.Stream))
                {
                    imports.Add("retrofit2.http.Streaming");
                }
                // response type (can be different from return type)
                this.Responses.ForEach(r => imports.AddRange((r.Value as ResponseJv).ImplImports));
                // exceptions
                this.ExceptionString.Split(new string[] { ", " }, StringSplitOptions.RemoveEmptyEntries)
                    .ForEach(ex =>
                    {
                        string exceptionImport = CodeNamerJv.GetJavaException(ex, CodeModel);
                        if (exceptionImport != null) imports.Add(CodeNamerJv.GetJavaException(ex, CodeModel));
                    });
                // parameterized host
                if (IsParameterizedHost)
                {
                    imports.Add("com.google.common.base.Joiner");
                }
                return imports.ToList();
            }
        }
    }
}