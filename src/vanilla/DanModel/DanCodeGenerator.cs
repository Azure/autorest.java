using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Extensions.Azure;
using AutoRest.Java.Azure;
using AutoRest.Java.Azure.Fluent.Model;
using AutoRest.Java.Azure.Model;
using AutoRest.Java.Model;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace AutoRest.Java.DanModel
{
    public static class DanCodeGenerator
    {
        public static JavaFile GetMethodGroupInterfaceJavaFile(CodeModel codeModel, Settings settings, MethodGroupJv methodGroup)
        {
            string headerComment = settings.Header;

            int maximumHeaderCommentWidth = settings.MaximumCommentColumns;

            string package = GetPackage(codeModel);
            string folderPath = GetFolderPath(package);

            IEnumerable<string> imports = methodGroup.Methods.SelectMany(method => ((MethodJv)method).InterfaceImports);
            
            string interfaceName = methodGroup.TypeName;

            IEnumerable<JavaMethod> methods = methodGroup.Methods.SelectMany(ParseMethod);

            JavaMethodGroupInterface methodGroupInterface = new JavaMethodGroupInterface(imports, interfaceName, methods);
            JavaFile javaFile = methodGroupInterface.GenerateJavaFile(folderPath, headerComment, package, maximumHeaderCommentWidth);
            return javaFile;
        }

        private static IEnumerable<JavaMethod> ParseMethod(Method method)
        {
            string description = "";
            if(!string.IsNullOrEmpty(method.Summary))
            {
                description += method.Summary.EscapeXmlComment().Period();
            }
            if(!string.IsNullOrEmpty(method.Description))
            {
                if (!string.IsNullOrEmpty(description))
                {
                    description += "\n";
                }
                description += method.Description.EscapeXmlComment().Period();
            }

            MethodJv methodJv = (MethodJv) method;
            MethodJva methodJva = methodJv as MethodJva;

            JavaThrow operationExceptionThrow = new JavaThrow(methodJv.OperationExceptionTypeString, "thrown if the request is rejected by server");
            JavaThrow runtimeExceptionThrow = new JavaThrow("RuntimeException", "all other wrapped checked exceptions if the request fails to be sent");
            JavaThrow illegalArgumentExceptionThrow = new JavaThrow("IllegalArgumentException", "thrown if parameters fail the validation");

            string syncReturnType = methodJv.ReturnTypeResponseName;
            string xmlEscapedSyncReturnType = syncReturnType.EscapeXmlComment();
            string asyncInnerReturnType = methodJv.ReturnTypeJv.GenericBodyClientTypeString;
            string serviceFutureReturnType = $"ServiceFuture<{asyncInnerReturnType}>";
            
            string asyncReturnType;
            if (methodJva == null)
            {
                // TODO: consolidate this conversion
                if (methodJv.ReturnType.Body == null)
                {
                    asyncReturnType = "Completable";
                }
                else
                {
                    asyncReturnType = $"Single<{asyncInnerReturnType}>";
                }
            }
            else
            {
                asyncReturnType = methodJva.AsyncClientReturnTypeString;
            }
            
            string asyncRestResponseReturnType = $"Single<{methodJv.RestResponseAbstractTypeName}>";
            
            JavaMethodReturn syncReturn = new JavaMethodReturn(
                syncReturnType,
                syncReturnType == "void"
                    ? null
                    : $"the {xmlEscapedSyncReturnType} object if successful.");

            JavaMethodReturn callbackReturn = new JavaMethodReturn(
                serviceFutureReturnType,
                $"the {{@link {serviceFutureReturnType.EscapeXmlComment()}}} object");

            JavaMethodReturn asyncReturn = new JavaMethodReturn(
                asyncReturnType,
                $"the {{@link {asyncReturnType.EscapeXmlComment()}}} object if successful.");

            JavaMethodReturn asyncRestResponseReturn = new JavaMethodReturn(
                asyncRestResponseReturnType,
                $"the {{@link {asyncRestResponseReturnType.EscapeXmlComment()}}} object if successful.");

            string methodName = method.Name;
            string asyncMethodName = $"{methodName}Async";
            string asyncRestResponseMethodName = $"{methodName}WithRestResponseAsync";

            IEnumerable<ParameterJv> nonConstantParameters = methodJv.LocalParameters.Where(p => !p.IsConstant);
            IEnumerable<ParameterJv> nonConstantRequiredParameters = nonConstantParameters.Where(p => p.IsRequired);
            IEnumerable<JavaMethodParameter> parameters = nonConstantParameters.Select(ParseParameter);
            IEnumerable<JavaMethodParameter> requiredParameters = nonConstantRequiredParameters.Select(ParseParameter);

            JavaMethodParameter callbackParameter = new JavaMethodParameter(
                "the async ServiceCallback to handle successful and failed responses.",
                $"ServiceCallback<{asyncInnerReturnType}>",
                "serviceCallback",
                final: true);

            bool shouldGenerateCallbackMethod =
                methodJva == null ||
                (!methodJva.IsPagingOperation &&
                 !methodJva.IsPagingNextOperation);

            bool shouldGenerateRestResponseMethod =
                methodJva == null ||
                (!methodJva.IsLongRunningOperation &&
                 !methodJva.IsPagingOperation &&
                 !methodJva.IsPagingNextOperation);

            List<JavaMethod> javaMethods = new List<JavaMethod>();

            void addMethods(IEnumerable<JavaMethodParameter> methodParameters)
            {
                // Sync
                javaMethods.Add(new JavaMethod(
                    description,
                    new[] { illegalArgumentExceptionThrow, operationExceptionThrow, runtimeExceptionThrow },
                    syncReturn,
                    methodName,
                    methodParameters));

                if (shouldGenerateCallbackMethod)
                {
                    // Callback
                    javaMethods.Add(new JavaMethod(
                        description,
                        new[] { illegalArgumentExceptionThrow },
                        callbackReturn,
                        asyncMethodName,
                        methodParameters.Concat(new[] { callbackParameter })));
                }

                // Async
                javaMethods.Add(new JavaMethod(
                    description,
                    new[] { illegalArgumentExceptionThrow },
                    asyncReturn,
                    asyncMethodName,
                    methodParameters));

                if (shouldGenerateRestResponseMethod)
                {
                    // RestResponse Async
                    javaMethods.Add(new JavaMethod(
                        description,
                        new[] { illegalArgumentExceptionThrow },
                        asyncRestResponseReturn,
                        asyncRestResponseMethodName,
                        methodParameters));
                }
            };

            if (nonConstantParameters.Any(p => !p.IsRequired))
            {
                addMethods(requiredParameters);
            }

            addMethods(parameters);

            return javaMethods;
        }

        private static JavaMethodParameter ParseParameter(ParameterJv parameter)
        {
            string description = parameter.Documentation.Else($"the {parameter.ModelType.Name} value").EscapeXmlComment();
            string type = parameter.ClientType.ParameterVariant.Name;
            string name = parameter.Name;
            return new JavaMethodParameter(description, type, name);
        }

        public static IEnumerable<JavaFile> GetPackageInfoJavaFiles(CodeModel codeModel, Settings settings, IEnumerable<string> subPackages)
        {
            List<JavaFile> packageInfoJavaFiles = new List<JavaFile>();
            AddPackageInfoJavaFiles(codeModel, settings, subPackages, packageInfoJavaFiles);
            return packageInfoJavaFiles;
        }

        public static void AddPackageInfoJavaFiles(CodeModel codeModel, Settings settings, IEnumerable<string> subPackages, IList<JavaFile> javaFiles)
        {
            string headerComment = settings.Header;

            int maximumHeaderCommentWidth = settings.MaximumCommentColumns;

            string title = codeModel.Name;
            string description = codeModel.Documentation;

            foreach (string subPackage in subPackages)
            {
                string package = GetPackage(codeModel, subPackage);
                string folderPath = GetFolderPath(package);

                JavaPackageInfo packageInfo = new JavaPackageInfo(title, subPackage, description);
                JavaFile javaFile = packageInfo.GenerateJavaFile(folderPath, headerComment, package, maximumHeaderCommentWidth);
                javaFiles.Add(javaFile);
            }
        }

        public static IEnumerable<JavaFile> GetModelJavaFiles(CodeModel codeModel, Settings settings)
        {
            List<JavaFile> exceptionJavaFiles = new List<JavaFile>();
            AddModelJavaFiles(codeModel, settings, exceptionJavaFiles);
            return exceptionJavaFiles;
        }

        public static void AddModelJavaFiles(CodeModel codeModel, Settings settings, IList<JavaFile> javaFiles)
        {
            string headerComment = settings.Header;

            int maximumHeaderCommentWidth = settings.MaximumCommentColumns;

            foreach (CompositeTypeJv modelType in codeModel.ModelTypes.Union(codeModel.HeaderTypes))
            {
                bool shouldGenerate = true;

                if (modelType is CompositeTypeJva modelTypeJva)
                {
                    bool isExternalExtension =
                        modelType.Extensions.ContainsKey(AzureExtensions.ExternalExtension) &&
                        (bool)modelType.Extensions[AzureExtensions.ExternalExtension];

                    shouldGenerate = !isExternalExtension && !modelTypeJva.IsResource;
                }

                if (shouldGenerate)
                {
                    string package = GetPackage(codeModel, modelType.ModelsPackage);
                    string folderPath = GetFolderPath(package);

                    JavaModel javaModel = ParseModel(modelType);
                    JavaFile javaFile = javaModel.GenerateJavaFile(folderPath, headerComment, package, maximumHeaderCommentWidth);
                    javaFiles.Add(javaFile);
                }
            }
        }

        private static JavaModel ParseModel(CompositeTypeJv modelType)
        {
            IEnumerable<string> imports = ParseModelImports(modelType);

            string classComment = ParseModelClassComment(modelType);

            IEnumerable<string> classAnnotations = ParseModelClassAnnotations(modelType);

            string className = modelType.Name;

            string baseTypeName = modelType.BaseModelType?.Name?.Value;

            IEnumerable<JavaMemberVariable> memberVariables = ParseModelMemberVariables(modelType);

            return new JavaModel(imports, classComment, classAnnotations, className, baseTypeName, memberVariables);
        }

        private static IEnumerable<string> ParseModelImports(CompositeTypeJv modelType)
        {
            List<string> imports = new List<string>();
            imports.AddRange(modelType.Properties.SelectMany(pm => (pm as PropertyJv).Imports));

            if (modelType.Properties.Any(p => !p.GetJsonProperty().IsNullOrEmpty()))
            {
                imports.Add("com.fasterxml.jackson.annotation.JsonProperty");
            }

            if (modelType.Properties.Any(p => p.XmlIsAttribute))
            {
                imports.Add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty");
            }

            // For polymorphism
            if (modelType.BaseIsPolymorphic)
            {
                imports.Add("com.fasterxml.jackson.annotation.JsonTypeInfo");
                imports.Add("com.fasterxml.jackson.annotation.JsonTypeName");
                if (modelType.SubTypes.Any())
                {
                    imports.Add("com.fasterxml.jackson.annotation.JsonSubTypes");
                }
            }

            // For flattening
            if (modelType.NeedsFlatten)
            {
                imports.Add("com.microsoft.rest.v2.serializer.JsonFlatten");
            }

            if (modelType is CompositeTypeJva azureModelType)
            {
                foreach (Property property in azureModelType.Properties)
                {
                    if (property.ModelType.IsResource())
                    {
                        imports.Add($"com.microsoft.azure.v2.{property.ModelType.Name}");
                    }
                }

                if (azureModelType.BaseModelType != null && (azureModelType.BaseModelType.Name == "Resource" || azureModelType.BaseModelType.Name == "SubResource"))
                {
                    imports.Add("com.microsoft.azure.v2." + azureModelType.BaseModelType.Name);
                }

                if (azureModelType is CompositeTypeJvaf fluentModelType)
                {
                    if (fluentModelType.BaseModelType != null && fluentModelType.BaseModelType.Name.ToString().EndsWith("Inner", StringComparison.Ordinal) ^ fluentModelType.IsInnerModel)
                    {
                        imports.AddRange(fluentModelType.BaseModelType.ImportSafe());
                    }
                }
            }

            return imports;
        }

        private static string ParseModelClassComment(CompositeTypeJv modelType)
        {
            string classComment;
            if (string.IsNullOrEmpty(modelType.Summary) && string.IsNullOrEmpty(modelType.Documentation))
            {
                classComment = $"The {modelType.Name} model.";
            }
            else
            {
                classComment = $"{modelType.Summary.EscapeXmlComment().Period()}{modelType.Documentation.EscapeXmlComment().Period()}";
            }
            return classComment;
        }

        private static IEnumerable<string> ParseModelClassAnnotations(CompositeTypeJv modelType)
        {
            List<string> classAnnotations = new List<string>();
            if (modelType.BaseIsPolymorphic)
            {
                classAnnotations.Add($"JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = \"{modelType.BasePolymorphicDiscriminator}\")");
                classAnnotations.Add($"JsonTypeName(\"{modelType.SerializedName}\")");

                List<CompositeType> types = modelType.SubTypes.ToList();
                if (types.Any())
                {
                    StringBuilder subTypeAnnotationBuilder = new StringBuilder();
                    subTypeAnnotationBuilder.AppendLine("JsonSubTypes({");

                    foreach (CompositeType subType in types.SkipLast(1))
                    {
                        subTypeAnnotationBuilder.AppendLine(GetSubTypeAnnotation(subType));
                    }
                    subTypeAnnotationBuilder.AppendLine(GetSubTypeAnnotation(types.Last(), true));

                    subTypeAnnotationBuilder.Append("})");

                    classAnnotations.Add(subTypeAnnotationBuilder.ToString());
                }
            }

            if (modelType.NeedsFlatten)
            {
                classAnnotations.Add("JsonFlatten");
            }

            return classAnnotations;
        }

        private static IEnumerable<JavaMemberVariable> ParseModelMemberVariables(CompositeTypeJv modelType)
        {
            return modelType.Properties.Select(ParseModelMemberVariable);
        }

        private static JavaMemberVariable ParseModelMemberVariable(Property property)
        {
            string comment;
            if (string.IsNullOrEmpty(property.Summary) && string.IsNullOrEmpty(property.Documentation))
            {
                comment = $"The {property.Name} property.";
            }
            else
            {
                string summary = property.Summary.EscapeXmlComment().Period();
                string documentation = property.Documentation.EscapeXmlComment().Period();

                comment = summary;
                if (!string.IsNullOrEmpty(documentation))
                {
                    if (!string.IsNullOrEmpty(comment))
                    {
                        comment += "\n";
                    }
                    comment += documentation;
                }
            }

            string annotation = null;
            string jsonSetting = property.GetJsonProperty();
            if (!string.IsNullOrEmpty(jsonSetting))
            {
                if (property.XmlIsAttribute)
                {
                    annotation = $"JacksonXmlProperty(localName = \"{property.SerializedName}\", isAttribute = true)";
                }
                else
                {
                    annotation = $"JsonProperty({jsonSetting})";
                }
            }

            bool isConstant = property.IsConstant;
            bool isReadOnly = property.IsReadOnly;

            bool isPrimitive = !(property.ModelType is CompositeType);
            JavaType wireType = new JavaType(property.ModelType.Name, isPrimitive);
            JavaType clientType = new JavaType(((IModelTypeJv)property.ModelType).ResponseVariant.Name, isPrimitive);

            string name = property.Name;

            string defaultValue;
            try
            {
                defaultValue = property.DefaultValue;
            }
            catch (NotSupportedException)
            {
                defaultValue = null;
            }

            return new JavaMemberVariable(comment, annotation, isConstant, isReadOnly, wireType, clientType, name, defaultValue);
        }

        private static string GetSubTypeAnnotation(CompositeType subType, bool isLast = false)
        {
            string subTypeAnnotation = $"@JsonSubTypes.Type(name = \"{subType.SerializedName}\", value = {subType.Name}.class)";
            if (!isLast)
            {
                subTypeAnnotation += ",";
            }
            return subTypeAnnotation;
        }

        public static IEnumerable<JavaFile> GetExceptionJavaFiles(CodeModelJv codeModel, Settings settings)
        {
            List<JavaFile> exceptionJavaFiles = new List<JavaFile>();
            AddExceptionJavaFiles(codeModel, settings, exceptionJavaFiles);
            return exceptionJavaFiles;
        }

        public static void AddExceptionJavaFiles(CodeModelJv codeModel, Settings settings, IList<JavaFile> javaFiles)
        {
            string headerComment = settings.Header;

            int maximumHeaderCommentWidth = settings.MaximumCommentColumns;

            foreach (CompositeTypeJv exceptionType in codeModel.ErrorTypes)
            {
                string exceptionBodyTypeName = exceptionType.Name;
                string exceptionName = exceptionType.ExceptionTypeDefinitionName;

                // Skip any exceptions that are named "CloudErrorException" or have a body named
                // "CloudError" because those types already exist in the runtime.
                if (exceptionBodyTypeName != "CloudError" && exceptionName != "CloudErrorException")
                {
                    string package = GetPackage(codeModel, exceptionType.ModelsPackage);
                    string folderPath = GetFolderPath(package);

                    JavaException javaException = new JavaException(exceptionName, exceptionBodyTypeName);
                    JavaFile javaFile = javaException.GenerateJavaFile(folderPath, headerComment, package, maximumHeaderCommentWidth);
                    javaFiles.Add(javaFile);
                }
            }
        }

        public static IEnumerable<JavaFile> GetEnumJavaFiles(CodeModelJv codeModel, Settings settings)
        {
            List<JavaFile> enumJavaFiles = new List<JavaFile>();
            AddEnumJavaFiles(codeModel, settings, enumJavaFiles);
            return enumJavaFiles;
        }

        public static void AddEnumJavaFiles(CodeModelJv codeModel, Settings settings, IList<JavaFile> javaFiles)
        {
            string headerComment = settings.Header;

            string package = GetPackage(codeModel, codeModel.ModelsPackage);
            string folderPath = GetFolderPath(package);

            int maximumHeaderCommentWidth = settings.MaximumCommentColumns;

            foreach (EnumType enumType in codeModel.EnumTypes)
            {
                string enumName = enumType.Name;

                IEnumerable<JavaEnumValue> enumValues = enumType.Values
                    .Select((EnumValue value) => new JavaEnumValue(value.MemberName, value.SerializedName));

                JavaEnum javaEnum = enumType.ModelAsString ?
                    new JavaExpandableStringEnum(enumName, enumValues) :
                    new JavaEnum(enumName, enumValues);

                JavaFile javaFile = javaEnum.GenerateJavaFile(folderPath, headerComment, package, maximumHeaderCommentWidth);
                javaFiles.Add(javaFile);
            }
        }

        private static string GetPackage(CodeModel codeModel, string packageSuffix = null)
        {
            string package = codeModel.Namespace.ToLowerInvariant();
            if (!string.IsNullOrEmpty(packageSuffix))
            {
                package = $"{package}.{packageSuffix.Trim('.')}";
            }
            return package;
        }

        private static string GetFolderPath(string package)
        {
            return Path.Combine("src", "main", "java", package.Replace('.', Path.DirectorySeparatorChar));
        }
    }
}
