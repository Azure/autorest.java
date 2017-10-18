// Copyright (c) Microsoft Corporation. All rights reserved.
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
using System.Collections.Immutable;

namespace AutoRest.Java.Model
{
    public struct ParameterVariants
    {
        public ImmutableArray<ParameterJv> AllParameters { get; }
        public ImmutableArray<ParameterJv> RequiredParameters { get; }

        public bool HasOptionalParameters => AllParameters.Length != RequiredParameters.Length;

        public ParameterVariants(ImmutableArray<ParameterJv> allParameters, ImmutableArray<ParameterJv> requiredParameters)
        {
            AllParameters = allParameters;
            RequiredParameters = requiredParameters;
        }
    }

    public class MethodJv : Method
    {

        [JsonIgnore]
        public virtual IEnumerable<ParameterJv> RetrofitParameters
        {
            get
            {
                var parameters = LogicalParameters.OfType<ParameterJv>()
                    .Where(p => p.Location != ParameterLocation.None)
                    .ToList();

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
                bool shouldGenerateXmlSerialization = CodeModel.ShouldGenerateXmlSerialization;

                List<string> declarations = new List<string>();
                foreach (ParameterJv parameter in OrderedRetrofitParameters)
                {
                    StringBuilder declarationBuilder = new StringBuilder();
                    if (Url.Contains("{" + parameter.Name + "}"))
                    {
                        parameter.Location = ParameterLocation.Path;
                    }

                    var name = parameter.SerializedName;
                    if (parameter.Extensions.ContainsKey("hostParameter"))
                    {
                        declarationBuilder.Append($"@HostParam(\"{name}\") ");
                    }
                    else if (parameter.Location == ParameterLocation.Path ||
                        parameter.Location == ParameterLocation.Query ||
                        parameter.Location == ParameterLocation.Header)
                    {
                        var location = parameter.Location;
                        declarationBuilder.Append($"@{location}Param(\"{name}\") ");
                    }
                    else if (parameter.Location == ParameterLocation.Body)
                    {
                        declarationBuilder.Append($"@BodyParam(\"{RequestContentType}\") ");
                    }
                    else if (parameter.Location == ParameterLocation.FormData)
                    {
                        declarationBuilder.Append($"/* @Part(\"{name}\") not supported by RestProxy */");
                    }

                    var declarativeName = parameter.ClientProperty != null ? parameter.ClientProperty.Name : parameter.Name;

                    bool shouldUseXmlListWrapper = shouldGenerateXmlSerialization && (parameter.ModelType is SequenceType);
                    if (shouldUseXmlListWrapper)
                    {
                        declarationBuilder.Append(parameter.ModelType.XmlName + "Wrapper");
                    }
                    else
                    {
                        declarationBuilder.Append(parameter.WireType.Name);
                    }

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
                var shouldUseXmlSerialization = CodeModel.ShouldGenerateXmlSerialization;
                
                var arguments = OrderedRetrofitParameters.Select(
                    p => shouldUseXmlSerialization && (p.WireType is SequenceType)
                        ? $"new {p.WireType.XmlName}Wrapper({p.WireName})"
                        : p.WireName);
                
                var declaration = string.Join(", ", arguments);
                return declaration;
            }
        }

        [JsonIgnore]
        public ParameterJv CallbackParam
        {
            get
            {
                var modelType = new CompositeTypeJv();
                modelType.Name.FixedValue = $"ServiceCallback<{ReturnTypeJv.GenericBodyClientTypeString}>";
                var callbackParam = new ParameterJv()
                {
                    ModelType = modelType,
                    Name = "serviceCallback",
                    SerializedName = "serviceCallback",
                    Documentation = "the async ServiceCallback to handle successful and failed responses."
                };
                return callbackParam;
            }
        }

        [JsonIgnore]
        public ParameterVariants ParameterVariants
        {
            get
            {
                return new ParameterVariants(
                    allParameters: LocalParameters.Where(p => !p.IsConstant).ToImmutableArray(),
                    requiredParameters: LocalParameters.Where(p => p.IsRequired && !p.IsConstant).ToImmutableArray()
                );
            }
        }

        public string Javadoc(IEnumerable<ParameterJv> parameters, IEnumerable<string> exceptionsDocumentation, string optionalReturnDocumentation)
        {
            var builder = new IndentedStringBuilder(IndentedStringBuilder.FourSpaces);
            builder.AppendLine("/**");
            if (!string.IsNullOrEmpty(Summary))
            {
                builder.AppendLine(" * " + Summary.EscapeXmlComment().Period());
            }

            if (!string.IsNullOrEmpty(Description))
            {
                builder.AppendLine(" * " + Description.EscapeXmlComment().Period());
            }

            builder.AppendLine(" * ");

            foreach (var param in parameters)
            {
                var paramDoc = param.Documentation.Else($"the {param.ModelType.Name} value").EscapeXmlComment().Trim();
                builder.AppendLine($" * @param {param.Name} {paramDoc}");
            }

            foreach (var exception in exceptionsDocumentation)
            {
                builder.AppendLine(exception);
            }

            if (!string.IsNullOrEmpty(optionalReturnDocumentation))
            {
                builder.AppendLine($" * @return {optionalReturnDocumentation}");
            }

            builder.AppendLine(" */");
            return builder.ToString();
        }

        public static string ParameterDeclaration(IEnumerable<ParameterJv> parameters)
        {
            var paramDecls = parameters.Select(parameter => parameter.ClientType.ParameterVariant.Name + " " + parameter.Name);
            var paramString = string.Join(", ", paramDecls);
            return paramString;
        }
        
        public static string Arguments(IEnumerable<ParameterJv> parameters)
        {
            var args = parameters.Select(parameter => parameter.Name.Value);
            var argsString = string.Join(", ", args);
            return argsString;
        }

        public IEnumerable<string> AsyncExceptionDocumentation => new[] { " * @throws IllegalArgumentException thrown if parameters fail the validation" };

        // Observable overload generation helpers



        public string ObservableReturnDocumentation => string.IsNullOrEmpty(ReturnTypeResponseName) ? "" : $"a {{@link Single}} emitting the RestResponse<{ReturnTypeJv.ServiceResponseGenericParameterString.EscapeXmlComment()}> object";

        public string ObservableRestResponseImpl(IEnumerable<ParameterJv> parameters, bool filterRequired)
        {
            var builder = new IndentedStringBuilder(IndentedStringBuilder.FourSpaces);
            builder.AppendLine($"public Single<{ReturnTypeJv.ClientResponseTypeString}> {Name}WithRestResponseAsync({ParameterDeclaration(parameters)}) {{");
            builder.Indent();

            // Check presence of required parameters
            foreach (var param in parameters)
            {
                builder.AppendLine($"if ({param.Name} == null) {{");
                builder.Indent();
                builder.AppendLine($"throw new IllegalArgumentException(\"Parameter {param.Name} is required and cannot be null.\");");
                builder.Outdent();
                builder.AppendLine("}");
            }

            // Validate
            var parametersToValidate = parameters.Where(p => !(p.ModelType is PrimaryType && p.ModelType is EnumType));
            foreach (var param in parametersToValidate)
            {
                builder.AppendLine($"Validator.validate({param.Name});");
            }

            // Declare constants
            foreach (var param in LocalParameters.Where(p => p.IsConstant))
            {
                builder.AppendLine($"final {param.ModelType.Name} {param.Name} = {param.DefaultValue ?? "null"});");
            }
            
            var beginning = builder.ToString();
            var mappings = BuildInputMappings(filterRequired);
            var parameterConversion = ParameterConversion;
            var epilogue = $"    return service.{Name}({MethodParameterApiInvocation});\n}}";

            return string.Join("\n", beginning, mappings, parameterConversion, epilogue);
        }

        public string ObservableImpl(IEnumerable<ParameterJv> parameters, bool filterRequired)
        {
            var builder = new IndentedStringBuilder(IndentedStringBuilder.FourSpaces);
            builder.AppendLine($"public Single<{ReturnTypeJv.ClientResponseTypeString}> {Name}Async({ParameterDeclaration(parameters)}) {{");
            builder.Indent();
        }

        // Callback overload generation helpers

        public string CallbackReturnDocumentation => "the {@link ServiceFuture} object";

        public string CallbackImpl(IEnumerable<ParameterJv> parameters, ParameterJv callbackParam)
        {
            var builder = new IndentedStringBuilder(IndentedStringBuilder.FourSpaces);
            builder.AppendLine($"public ServiceFuture<{ReturnTypeJv.ServiceFutureGenericParameterString}> {Name}Async({ParameterDeclaration(parameters.ConcatSingleItem(callbackParam))}) {{");
            builder.Indent();
            builder.AppendLine($"return ServiceFuture.{ServiceFutureFactoryMethod}({Name}Async({Arguments(parameters)}), {callbackParam.Name});");
            builder.Outdent();
            builder.AppendLine("}");

            return builder.ToString();
        }

        // Sync overload generation helpers

        public IEnumerable<string> SyncExceptionDocumentation => new[]
        {
            " * @throws IllegalArgumentException thrown if parameters fail the validation",
            $" * @throws {OperationExceptionTypeString} thrown if the request is rejected by server",
            " * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent"
        };

        public string SyncReturnDocumentation => string.IsNullOrEmpty(ReturnTypeResponseName) && ReturnTypeResponseName != "void"
            ? ""
            : $"the {ReturnTypeResponseName.EscapeXmlComment()} object if successful.";

        public string SyncImpl(IEnumerable<ParameterJv> parameters)
        {
            var paramString = ParameterDeclaration(parameters);
            var argsString = Arguments(parameters);

            var builder = new IndentedStringBuilder(IndentedStringBuilder.FourSpaces);
            builder.AppendLine($"public {ReturnTypeResponseName} {Name}({paramString}) {{");
            builder.Indent();

            if (ReturnTypeJv.BodyClientType.ResponseVariant.Name == "void")
            {
                builder.AppendLine($"{Name}Async({argsString}).toBlocking().value();");
            }
            else
            {
                builder.AppendLine($"return {Name}Async({argsString}).toBlocking().value();");
            }

            builder.Outdent();
            builder.AppendLine("}");

            return builder.ToString();
        }

        [JsonIgnore]
        [Obsolete("Use MethodParameterApiInvocation")]
        public string MethodRequiredParameterApiInvocation => MethodParameterApiInvocation;

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
                            ((ParameterJv)transformation.OutputParameter).ClientType.ParameterVariant.Name,
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

        [JsonIgnore]
        public virtual string ServiceFutureFactoryMethod
        {
            get
            {
                string factoryMethod = "fromBody";
                if (ReturnType.Headers != null)
                {
                    // FIXME
                    factoryMethod = "fromBody/* RestProxy doesn't support headers */";
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
                imports.Add("rx.Single");
                imports.Add("com.microsoft.rest.ServiceFuture");
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
                    });

                return imports.ToList();
            }
        }

        private ImmutableArray<string> cachedImplImports = default(ImmutableArray<string>);
        [JsonIgnore]
        public virtual ImmutableArray<string> ImplImports
        {
            get
            {
                if (cachedImplImports.IsDefault)
                {
                    HashSet<string> imports = new HashSet<string>();
                    // static imports
                    imports.Add("rx.Observable");
                    imports.Add("rx.Single");
                    imports.Add("rx.functions.Func1");
                    imports.Add("com.microsoft.rest.annotations.Headers");
                    imports.Add("com.microsoft.rest.annotations.ExpectedResponses");
                    imports.Add("com.microsoft.rest.annotations.UnexpectedResponseExceptionType");
                    imports.Add("com.microsoft.rest.annotations.Host");
                    imports.Add("com.microsoft.rest.http.HttpClient");
                    imports.Add("com.microsoft.rest.ServiceFuture");
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
                        imports.Add("com.microsoft.rest.annotations.HostParam");
                    }
                    cachedImplImports = imports.ToImmutableArray();
                }

                return cachedImplImports;
            }
        }

        public string ExpectedResponsesAnnotation
        {
            get
            {
                string result;

                if (Responses.Count == 0)
                {
                    result = "";
                }
                else
                {
                    string annotationArgs = string.Join(", ", Responses.Keys.OrderBy(k => k).Select(k => k.ToString("D")));
                    result = $"@ExpectedResponses({{{annotationArgs}}})";
                }

                return result;
            }
        }
        
        internal static bool HasSequenceType(IModelType mt)
        {
            if (mt is SequenceType)
            {
                return true;
            }

            if (mt is CompositeType ct)
            {
                return ct.Properties.Any(p => HasSequenceType(p.ModelType));
            }

            return false;
        }
    }
}
