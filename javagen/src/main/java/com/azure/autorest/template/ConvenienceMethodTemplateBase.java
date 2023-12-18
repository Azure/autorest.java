// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.Annotation;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ConvenienceMethod;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ImplementationDetails;
import com.azure.autorest.model.clientmodel.IterableType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.model.clientmodel.MethodTransformationDetail;
import com.azure.autorest.model.clientmodel.ParameterMapping;
import com.azure.autorest.model.clientmodel.ParameterSynthesizedOrigin;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaType;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.template.util.ModelTemplateHeaderHelper;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.TemplateUtil;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.serializer.CollectionFormat;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.TypeReference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

abstract class ConvenienceMethodTemplateBase {

    protected ConvenienceMethodTemplateBase() {
    }

    public void write(ConvenienceMethod convenienceMethodObj, JavaClass classBlock, Set<GenericType> typeReferenceStaticClasses) {
        if (!isMethodIncluded(convenienceMethodObj)) {
            return;
        }

        ClientMethod protocolMethod = convenienceMethodObj.getProtocolMethod();
        convenienceMethodObj.getConvenienceMethods().stream()
                .filter(this::isMethodIncluded)
                .forEach(convenienceMethod -> {
                    // javadoc
                    classBlock.javadocComment(comment -> {
                        ClientMethodTemplate.generateJavadoc(convenienceMethod, comment, convenienceMethod.getProxyMethod(), false);
                    });

                    addGeneratedAnnotation(classBlock);
                    TemplateUtil.writeClientMethodServiceMethodAnnotation(convenienceMethod, classBlock);

                    JavaVisibility methodVisibility = convenienceMethod.getMethodVisibilityInWrapperClient();

                    // convenience method
                    String methodDeclaration = String.format("%1$s %2$s(%3$s)", convenienceMethod.getReturnValue().getType(), getMethodName(convenienceMethod), convenienceMethod.getParametersDeclaration());
                    classBlock.method(methodVisibility, null, methodDeclaration, methodBlock -> {
                        methodBlock.line("// Generated convenience method for " + getMethodName(protocolMethod));

                        writeMethodImplementation(protocolMethod, convenienceMethod, methodBlock, typeReferenceStaticClasses);
                    });
                });
    }

    /**
     * Write the implementation of the convenience method.
     *
     * @param protocolMethod the protocol method.
     * @param convenienceMethod the convenience method.
     * @param methodBlock the code block.
     */
    protected void writeMethodImplementation(
            ClientMethod protocolMethod,
            ClientMethod convenienceMethod,
            JavaBlock methodBlock,
            Set<GenericType> typeReferenceStaticClasses) {

        // matched parameters from convenience method to protocol method
        Map<MethodParameter, MethodParameter> parametersMap =
                findParametersForConvenienceMethod(convenienceMethod, protocolMethod);

        // RequestOptions
        methodBlock.line("RequestOptions requestOptions = new RequestOptions();");

        // parameter transformation
        if (!CoreUtils.isNullOrEmpty(convenienceMethod.getMethodTransformationDetails())) {
            convenienceMethod.getMethodTransformationDetails().forEach(d -> writeParameterTransformation(d, convenienceMethod, protocolMethod, methodBlock, parametersMap));
        }

        writeValidationForVersioning(convenienceMethod, parametersMap.keySet(), methodBlock);

        Map<String, String> parameterExpressionsMap = new HashMap<>();
        for (Map.Entry<MethodParameter, MethodParameter> entry : parametersMap.entrySet()) {
            MethodParameter parameter = entry.getKey();
            MethodParameter protocolParameter = entry.getValue();

            if (parameter.getProxyMethodParameter() != null && parameter.getProxyMethodParameter().getOrigin() == ParameterSynthesizedOrigin.CONTEXT) {
                // Context
                methodBlock.line(String.format("requestOptions.setContext(%s);", parameter.getName()));
            } else if (protocolParameter != null) {
                // protocol method parameter exists
                String expression = expressionConvertToType(parameter.getName(), parameter);
                parameterExpressionsMap.put(protocolParameter.getName(), expression);
            } else if (parameter.getProxyMethodParameter() != null) {
                // protocol method parameter not exist, set the parameter via RequestOptions
                switch (parameter.getProxyMethodParameter().getRequestParameterLocation()) {
                    case HEADER:
                        writeHeader(parameter, methodBlock);
                        break;

                    case QUERY:
                        writeQueryParam(parameter, methodBlock);
                        break;

                    case BODY: {
                        Consumer<JavaBlock> writeLine = javaBlock -> javaBlock.line(
                                String.format("requestOptions.setBody(%s);",
                                        expressionConvertToBinaryData(parameter.getName(), parameter.getClientMethodParameter().getWireType())));
                        if (!parameter.getClientMethodParameter().isRequired()) {
                            methodBlock.ifBlock(String.format("%s != null", parameter.getName()), writeLine);
                        } else {
                            writeLine.accept(methodBlock);
                        }
                    }
                    break;
                }
            }
        }

        // invocation with protocol method parameters and RequestOptions
        String invocationExpression = protocolMethod.getMethodInputParameters().stream()
                .map(p -> {
                    String parameterName = p.getName();
                    String expression = parameterExpressionsMap.get(parameterName);
                    IType parameterClientType = p.getClientType();
                    IType parameterRawType = p.getRawType();

                    if (ClientModelUtil.isClientModel(parameterRawType) && ClientModelUtil.isJsonMergePatchModel(ClientModelUtil.getClientModel(((ClassType) parameterRawType).getName()))) {
                        String parameterRawTypeName = ((ClassType) parameterRawType).getName();
                        String variableName = expression == null ? parameterName : parameterName + "In" + parameterClientType.toString();
                        methodBlock.line(String.format("JsonMergePatchHelper.get%1$sAccessor().prepareModelForJsonMergePatch(%2$s, true);", parameterRawTypeName, parameterName));
                        methodBlock.line(String.format("%1$s %2$s = %3$s;", parameterClientType, variableName, expression));
                        methodBlock.line(String.format("JsonMergePatchHelper.get%1$sAccessor().prepareModelForJsonMergePatch(%2$s, false);", parameterRawTypeName, parameterName));
                        return variableName;
                    } else {
                        return expression == null ? parameterName : expression;
                    }
                })
                .collect(Collectors.joining(", "));

        // write the invocation of protocol method, and related type conversion
        writeInvocationAndConversion(convenienceMethod, protocolMethod, invocationExpression, methodBlock, typeReferenceStaticClasses);
    }


    /**
     * Write the validation for parameters against current api-version.
     *
     * @param parameters the parameters
     * @param methodBlock the method block
     */
    protected void writeValidationForVersioning(ClientMethod convenienceMethod, Set<MethodParameter> parameters, JavaBlock methodBlock) {
        // validate parameter for versioning
        for (MethodParameter parameter : parameters) {
            if (parameter.getClientMethodParameter().getVersioning() != null && parameter.getClientMethodParameter().getVersioning().getAdded() != null) {
                String condition = String.format(
                        "!Arrays.asList(%1$s).contains(serviceClient.getServiceVersion().getVersion())",
                        parameter.getClientMethodParameter().getVersioning().getAdded().stream().map(ClassType.STRING::defaultValueExpression).collect(Collectors.joining(", ")));
                methodBlock.ifBlock(condition, ifBlock -> {
                    String exceptionExpression = String.format(
                            "new IllegalArgumentException(\"Parameter %1$s is only available in api-version %2$s.\")",
                            parameter.getName(),
                            String.join(", ", parameter.getClientMethodParameter().getVersioning().getAdded()));
                    writeThrowException(convenienceMethod.getType(), exceptionExpression, ifBlock);
                });
            }
        }
    }

    abstract void writeThrowException(ClientMethodType methodType, String exceptionExpression, JavaBlock methodBlock);

    private static boolean isGroupByTransformation(MethodTransformationDetail detail) {
        return !CoreUtils.isNullOrEmpty(detail.getParameterMappings())
                && detail.getParameterMappings().iterator().next().getOutputParameterPropertyName() == null;
    }

    private static void writeParameterTransformation(
            MethodTransformationDetail detail,
            ClientMethod convenienceMethod, ClientMethod protocolMethod,
            JavaBlock methodBlock,
            Map<MethodParameter, MethodParameter> parametersMap) {

        if (isGroupByTransformation(detail)) {
            // grouping

            ParameterMapping mapping = detail.getParameterMappings().iterator().next();
            ClientMethodParameter sourceParameter = mapping.getInputParameter();

            boolean sourceParameterInMethod = false;
            for (MethodParameter parameter: parametersMap.keySet()) {
                if (parameter.clientMethodParameter != null && parameter.clientMethodParameter.getName() != null
                        && Objects.equals(parameter.clientMethodParameter.getName(), sourceParameter.getName())) {
                    sourceParameterInMethod = true;
                    break;
                }
            }

            if (sourceParameterInMethod) {
                // null check on input parameter
                String assignmentExpression = "%1$s %2$s = %3$s.%4$s();";
                if (!sourceParameter.isRequired()) {
                    assignmentExpression = "%1$s %2$s = %3$s == null ? null : %3$s.%4$s();";
                }

                methodBlock.line(String.format(assignmentExpression,
                        detail.getOutParameter().getClientType(),
                        detail.getOutParameter().getName(),
                        sourceParameter.getName(),
                        CodeNamer.getModelNamer().modelPropertyGetterName(mapping.getInputParameterProperty())));

                if (detail.getOutParameter().getRequestParameterLocation() != null) {
                    ClientMethodParameter clientMethodParameter = detail.getOutParameter();
                    ProxyMethodParameter proxyMethodParameter = convenienceMethod.getProxyMethod().getAllParameters().stream()
                            .filter(p -> clientMethodParameter.getName().equals(CodeNamer.getEscapedReservedClientMethodParameterName(p.getName())))
                            .findFirst().orElse(null);
                    if (proxyMethodParameter != null) {
                        MethodParameter methodParameter = new MethodParameter(proxyMethodParameter, clientMethodParameter);
                        parametersMap.put(methodParameter, findParameterForConvenienceMethod(methodParameter, protocolMethod));
                    }
                }
            }
        } else {
            // flatten (possible with grouping)
            ClientMethodParameter targetParameter = detail.getOutParameter();
            if (targetParameter.getWireType() == ClassType.BINARY_DATA) {
                String targetParameterName = targetParameter.getName();
                String targetParameterObjectName = targetParameterName + "Obj";
                methodBlock.line(String.format("Map<String, Object> %1$s = new HashMap<>();", targetParameterObjectName));
                for (ParameterMapping mapping : detail.getParameterMappings()) {
                    if (mapping.getInputParameter().isRequired() || !convenienceMethod.getOnlyRequiredParameters()) {
                        String parameterName = mapping.getInputParameter().getName();
                        String mappingUsage;
                        if (mapping.getInputParameter().getClientType() instanceof EnumType) {
                            String enumConversion = ((EnumType) mapping.getInputParameter().getClientType()).getToMethodName() + "()";
                            mappingUsage = "(" + parameterName + " == null ? null : " + parameterName + "." + enumConversion + ")";
                        } else {
                            mappingUsage = parameterName;
                        }

                        String inputPath;
                        if (mapping.getInputParameterProperty() != null) {
                            inputPath = String.format("%s.%s()", mapping.getInputParameter().getName(),
                                    CodeNamer.getModelNamer().modelPropertyGetterName(mapping.getInputParameterProperty()));
                        } else {
                            inputPath = mappingUsage;
                        }

                        methodBlock.line(String.format("%1$s.put(\"%2$s\", %3$s);",
                                targetParameterObjectName,
                                mapping.getOutputParameterProperty().getSerializedName(),
                                inputPath));
                    }
                }
                methodBlock.line(String.format("BinaryData %1$s = BinaryData.fromObject(%2$s);", targetParameterName, targetParameterObjectName));
            }
        }
    }

    protected void addImports(Set<String> imports, List<ConvenienceMethod> convenienceMethods) {
        // methods
        JavaSettings settings = JavaSettings.getInstance();
        convenienceMethods.stream().flatMap(m -> m.getConvenienceMethods().stream())
                .forEach(m -> {
                    m.addImportsTo(imports, false, settings);
                    // hack, add wire type of parameters, as they are not added in ClientMethod, even when includeImplementationImports=true
                    for (ClientMethodParameter p : m.getParameters()) {
                        p.getWireType().addImportsTo(imports, false);

                        // add imports from models, as some convenience API need to process model properties
                        if (p.getWireType() instanceof ClassType) {
                            ClientModel model = ClientModelUtil.getClientModel(p.getWireType().toString());
                            if (model != null) {
                                model.addImportsTo(imports, settings);
                            }
                        }
                    }
                });

        ClassType.HTTP_HEADER_NAME.addImportsTo(imports, false);
        ClassType.BINARY_DATA.addImportsTo(imports, false);
        ClassType.REQUEST_OPTIONS.addImportsTo(imports, false);
        imports.add(Collectors.class.getName());
        imports.add(Objects.class.getName());
        imports.add(FluxUtil.class.getName());

        // collection format
        imports.add(JacksonAdapter.class.getName());
        imports.add(CollectionFormat.class.getName());
        imports.add(TypeReference.class.getName());

        // byte[]
        ClassType.BASE_64_URL.addImportsTo(imports, false);

        // flatten payload
        imports.add(Map.class.getName());
        imports.add(HashMap.class.getName());

        // MultipartFormDataHelper class
        imports.add(settings.getPackage(settings.getImplementationSubpackage()) + "." + ClientModelUtil.MULTI_PART_FORM_DATA_HELPER_CLASS_NAME);

        // versioning
        imports.add(Arrays.class.getName());

        // JsonMergePatchHelper class
        imports.add(settings.getPackage(settings.getImplementationSubpackage()) + "." + ClientModelUtil.JSON_MERGE_PATCH_HELPER_CLASS_NAME);
    }

    protected void addGeneratedAnnotation(JavaType typeBlock) {
        typeBlock.annotation(Annotation.GENERATED.getName());
    }

    /**
     * Whether the convenience method should be included.
     *
     * @param method the convenience method.
     * @return Whether include the convenience method.
     */
    protected abstract boolean isMethodIncluded(ClientMethod method);

    /**
     * Whether the convenience/protocol method should be included.
     *
     * @param method the convenience/protocol method.
     * @return Whether include the convenience/protocol method.
     */
    protected abstract boolean isMethodIncluded(ConvenienceMethod method);

    protected boolean isMethodAsync(ClientMethod method) {
        return method.getType().name().contains("Async");
    }

    protected boolean isMethodVisible(ClientMethod method) {
        return method.getMethodVisibility() == JavaVisibility.Public;
    }

    protected String getMethodName(ClientMethod method) {
        if (isMethodAsync(method)) {
            return method.getName().endsWith("Async")
                    ? method.getName().substring(0, method.getName().length() - "Async".length())
                    : method.getName();
        } else {
            return method.getName();
        }
    }

    /**
     * Write the code of the method invocation of client method, and the conversion of parameters and return value.
     *
     * @param convenienceMethod the convenience method.
     * @param protocolMethod the protocol method.
     * @param invocationExpression the prepared expression of invocation on client method.
     * @param methodBlock the code block.
     */
    protected abstract void writeInvocationAndConversion(
            ClientMethod convenienceMethod, ClientMethod protocolMethod,
            String invocationExpression,
            JavaBlock methodBlock,
            Set<GenericType> typeReferenceStaticClasses);

    protected boolean isModelOrBuiltin(IType type) {
        // TODO: other built-in types
        return type == ClassType.STRING // string
            || type == ClassType.OBJECT // unknown
            || type == ClassType.BIG_DECIMAL // decimal
            || (type instanceof PrimitiveType && type.asNullable() != ClassType.VOID) // boolean, int, float, etc.
            || ClientModelUtil.isClientModel(type); // client model
    }

    private static String expressionConvertToBinaryData(String name, IType type) {
        if (type == ClassType.BINARY_DATA) {
            return name;
        } else {
            if (type == ClassType.BASE_64_URL) {
                return "BinaryData.fromObject(Base64Url.encode(" + name + "))";
            } else if (type instanceof EnumType) {
                return "BinaryData.fromObject(" + name + " == null ? null : " + name + "." + ((EnumType) type).getToMethodName() + "())";
            } else {
                return "BinaryData.fromObject(" + name + ")";
            }
        }
    }

    private static void writeHeader(MethodParameter parameter, JavaBlock methodBlock) {
        Consumer<JavaBlock> writeLine = javaBlock -> javaBlock.line(
                String.format("requestOptions.setHeader(%1$s, %2$s);",
                        ModelTemplateHeaderHelper.getHttpHeaderNameInstanceExpression(parameter.getSerializedName()),
                        expressionConvertToString(parameter.getName(), parameter.getClientMethodParameter().getWireType(), parameter.getProxyMethodParameter())));
        if (!parameter.getClientMethodParameter().isRequired()) {
            methodBlock.ifBlock(String.format("%s != null", parameter.getName()), writeLine);
        } else {
            writeLine.accept(methodBlock);
        }
    }

    private static void writeQueryParam(MethodParameter parameter, JavaBlock methodBlock) {
        Consumer<JavaBlock> writeLine;
        if (parameter.proxyMethodParameter.getExplode() && parameter.getClientMethodParameter().getWireType() instanceof IterableType) {
            // multi
            IType elementType = ((IterableType) parameter.getClientMethodParameter().getWireType()).getElementType();
            String elementTypeExpression = expressionConvertToString("paramItemValue", elementType, parameter.getProxyMethodParameter());
            writeLine = javaBlock -> {
                String addQueryParamLine = getAddQueryParamExpression(parameter, elementTypeExpression);

                javaBlock.line(String.format("for (%1$s paramItemValue : %2$s) {", elementType, parameter.getName()));
                javaBlock.indent(() -> {
                    if (elementType instanceof PrimitiveType) {
                        javaBlock.line(addQueryParamLine);
                    } else {
                        javaBlock.ifBlock("paramItemValue != null", ifBlock -> ifBlock.line(addQueryParamLine));
                    }
                });
                javaBlock.line("}");
            };
        } else {
            writeLine = javaBlock -> javaBlock.line(
                    getAddQueryParamExpression(parameter,
                            expressionConvertToString(parameter.getName(), parameter.getClientMethodParameter().getWireType(), parameter.getProxyMethodParameter())));
        }
        if (!parameter.getClientMethodParameter().isRequired()) {
            methodBlock.ifBlock(String.format("%s != null", parameter.getName()), writeLine);
        } else {
            writeLine.accept(methodBlock);
        }
    }

    private static String getAddQueryParamExpression(MethodParameter parameter, String variable) {
        // TODO: generic not having 3rd parameter "encoded"
        if (JavaSettings.getInstance().isBranded()) {
            return String.format("requestOptions.addQueryParam(%1$s, %2$s, %3$s);",
                    ClassType.STRING.defaultValueExpression(parameter.getSerializedName()),
                    variable,
                    parameter.getProxyMethodParameter().getAlreadyEncoded());
        } else {
            return String.format("requestOptions.addQueryParam(%1$s, %2$s);",
                    ClassType.STRING.defaultValueExpression(parameter.getSerializedName()),
                    variable);
        }
    }

    private static String expressionConvertToString(String name, IType type, ProxyMethodParameter parameter) {
        if (type == ClassType.STRING) {
            return name;
        } else if (type instanceof EnumType) {
            // enum
            EnumType enumType = (EnumType) type;
            if (enumType.getElementType() == ClassType.STRING) {
                return name + ".toString()";
            } else {
                return String.format("String.valueOf(%1$s.%2$s())", name, enumType.getToMethodName());
            }
        } else if (type instanceof IterableType) {
            if (parameter.getCollectionFormat() == CollectionFormat.MULTI && parameter.getExplode()) {
                // multi, RestProxy will handle the array with "multipleQueryParams = true"
                return name;
            } else {
                String delimiter = parameter.getCollectionFormat().getDelimiter();
                IType elementType = ((IterableType) type).getElementType();
                if (elementType instanceof EnumType) {
                    // EnumTypes should provide a toString implementation that represents the wire value.
                    // Circumvent the use of JacksonAdapter and handle this manually.
                    EnumType enumType = (EnumType) elementType;
                    // Not enums will be backed by Strings. Get the backing value before stringifying it, this
                    // will prevent using the enum name rather than the enum value when it isn't a String-based
                    // enum. Ex, a long-based enum with value 100 called HIGH will return "100" rather than
                    // "HIGH".
                    String enumToString = enumType.getElementType() == ClassType.STRING
                        ? "paramItemValue"
                        : "paramItemValue == null ? null : paramItemValue." + enumType.getToMethodName() + "()";
                    return name + ".stream()\n" +
                        "    .map(paramItemValue -> Objects.toString(" + enumToString + ", \"\"))\n" +
                        "    .collect(Collectors.joining(\"" + delimiter + "\"))";
                } else if (elementType == ClassType.STRING
                    || (elementType instanceof ClassType && ((ClassType) elementType).isBoxedType())) {
                    return name + ".stream()\n" +
                        "    .map(paramItemValue -> Objects.toString(paramItemValue, \"\"))\n" +
                        "    .collect(Collectors.joining(\"" + delimiter + "\"))";
                } else {
                    // Always use serializeIterable as Iterable supports both Iterable and List.

                    // this logic depends on rawType of proxy method parameter be List<WireType>
                    // alternative would be check wireType of client method parameter
                    IType elementWireType = parameter.getRawType() instanceof IterableType
                        ? ((IterableType) parameter.getRawType()).getElementType()
                        : elementType;

                    String serializeIterableInput = name;
                    if (elementWireType != elementType) {
                        // convert List<ClientType> to List<WireType>, if necessary
                        serializeIterableInput = String.format(
                            "%s.stream().map(paramItemValue -> %s).collect(Collectors.toList())",
                            name, elementWireType.convertFromClientType("paramItemValue"));
                    }

                    // convert List<WireType> to String
                    return String.format(
                        "JacksonAdapter.createDefaultSerializerAdapter().serializeIterable(%s, CollectionFormat.%s)",
                        serializeIterableInput, parameter.getCollectionFormat().toString().toUpperCase(Locale.ROOT));
                }
            }
        } else {
            // primitive or date-time
            String conversionExpression = type.convertFromClientType(name);
            return String.format("String.valueOf(%s)", conversionExpression);
        }
    }

    private static String expressionConvertToType(String name, MethodParameter convenienceParameter) {
        if (convenienceParameter.getProxyMethodParameter().getRequestParameterLocation() == RequestParameterLocation.BODY) {
            IType bodyType = convenienceParameter.getProxyMethodParameter().getRawType();
            if (bodyType instanceof ClassType) {
                ClientModel model = ClientModelUtil.getClientModel(bodyType.toString());
                // serialize model for multipart/form-data
                if (model != null && model.getImplementationDetails() != null && model.getImplementationDetails().getUsages().contains(ImplementationDetails.Usage.MULTIPART_FORM_DATA)) {
                    return expressionMultipartFormDataToBinaryData(name, model);
                }
            }
            return expressionConvertToBinaryData(name, convenienceParameter.getClientMethodParameter().getWireType());
        } else {
            IType type = convenienceParameter.getClientMethodParameter().getWireType();
            if (type instanceof EnumType) {
                return expressionConvertToString(name, type, convenienceParameter.getProxyMethodParameter());
            } else if (type instanceof IterableType && ((IterableType) type).getElementType() instanceof EnumType) {
                IType enumType = ((IterableType) type).getElementType();
                IType enumValueType = ((EnumType) enumType).getElementType().asNullable();
                if (enumValueType == ClassType.STRING) {
                    return String.format(
                            "%1$s.stream().map(paramItemValue -> Objects.toString(paramItemValue, \"\")).collect(Collectors.toList())",
                            name);
                } else {
                    return String.format(
                            "%1$s.stream().map(paramItemValue -> paramItemValue == null ? \"\" : String.valueOf(paramItemValue.to%2$s())).collect(Collectors.toList())",
                            name, enumValueType);
                }
            } else {
                return name;
            }
        }
    }

    private static String expressionMultipartFormDataToBinaryData(String name, ClientModel model) {
        // serialize model for multipart/form-data
        StringBuilder builder = new StringBuilder().append("new MultipartFormDataHelper(requestOptions)");
        Set<String> filePropertySerializedNames = new HashSet<>();
        for (ClientModelProperty property : model.getProperties()) {
            if (property.getWireType() == ClassType.BINARY_DATA) {
                // application/octet-stream
                String serializedName = property.getSerializedName();
                filePropertySerializedNames.add(serializedName);

                // find corresponding filename property
                String filenameExpression;
                Optional<ClientModelProperty> filenameProperty = model.getProperties().stream()
                        // here is a hack to find matching filename property by finding property of type String and of same serializedName
                        .filter(p -> p.getWireType() == ClassType.STRING && Objects.equals(serializedName, p.getSerializedName()))
                        .findFirst();
                if (filenameProperty.isPresent()) {
                    filenameExpression = name + "." + filenameProperty.get().getGetterName() + "()";
                } else {
                    filenameExpression = ClassType.STRING.defaultValueExpression(property.getSerializedName());
                }

                builder.append(String.format(
                        ".serializeFileField(%1$s, %2$s.%3$s(), %4$s)",
                        ClassType.STRING.defaultValueExpression(property.getSerializedName()),
                        name,
                        property.getGetterName(),
                        filenameExpression
                ));
            } else if (filePropertySerializedNames.contains(property.getSerializedName())) {
                // skip filename property
            } else if (ClientModelUtil.isClientModel(property.getWireType())
                    || property.getWireType() instanceof MapType
                    || property.getWireType() instanceof IterableType) {
                // application/json
                String stringExpression = name + "." + property.getGetterName() + "()";
                builder.append(String.format(
                        ".serializeJsonField(%1$s, %2$s)",
                        ClassType.STRING.defaultValueExpression(property.getSerializedName()),
                        stringExpression
                ));
            } else {
                // text/plain
                String stringExpression = name + "." + property.getGetterName() + "()";
                // convert to String
                if (property.getWireType() instanceof PrimitiveType) {
                    stringExpression = String.format("String.valueOf(%s)", stringExpression);
                } else if (property.getWireType() != ClassType.STRING) {
                    stringExpression = String.format("Objects.toString(%s)", stringExpression);
                }
                builder.append(String.format(
                        ".serializeTextField(%1$s, %2$s)",
                        ClassType.STRING.defaultValueExpression(property.getSerializedName()),
                        stringExpression
                ));
            }
        }
        builder.append(".end().getRequestBody()");
        return builder.toString();
    }

    private static Map<MethodParameter, MethodParameter> findParametersForConvenienceMethod(
            ClientMethod convenienceMethod, ClientMethod protocolMethod) {
        Map<MethodParameter, MethodParameter> parameterMap = new LinkedHashMap<>();
        List<MethodParameter> convenienceParameters = getParameters(convenienceMethod, true);
        Map<String, MethodParameter> clientParameters = getParameters(protocolMethod, false).stream()
                .collect(Collectors.toMap(MethodParameter::getSerializedName, Function.identity()));
        for (MethodParameter convenienceParameter : convenienceParameters) {
            String name = convenienceParameter.getSerializedName();
            parameterMap.put(convenienceParameter, clientParameters.get(name));
        }
        return parameterMap;
    }

    private static MethodParameter findParameterForConvenienceMethod(
            MethodParameter parameter, ClientMethod protocolMethod) {
        List<MethodParameter> protocolParameters = getParameters(protocolMethod, false);
        return protocolParameters.stream().filter(p -> Objects.equals(parameter.getSerializedName(), p.getSerializedName())).findFirst().orElse(null);
    }

    private static List<MethodParameter> getParameters(ClientMethod method, boolean useAllParameters) {
        List<ProxyMethodParameter> proxyMethodParameters = useAllParameters ? method.getProxyMethod().getAllParameters() : method.getProxyMethod().getParameters();
        Map<String, ProxyMethodParameter> proxyMethodParameterByClientParameterName = proxyMethodParameters.stream()
                .collect(Collectors.toMap(p -> CodeNamer.getEscapedReservedClientMethodParameterName(p.getName()), Function.identity()));
        return method.getMethodInputParameters().stream()
                .filter(p -> !p.isConstant() && !p.isFromClient())
                .map(p -> new MethodParameter(proxyMethodParameterByClientParameterName.get(p.getName()), p))
                .collect(Collectors.toList());
    }

    protected static class MethodParameter {

        private final ProxyMethodParameter proxyMethodParameter;
        private final ClientMethodParameter clientMethodParameter;

        public MethodParameter(ProxyMethodParameter proxyMethodParameter, ClientMethodParameter clientMethodParameter) {
            this.proxyMethodParameter = proxyMethodParameter;
            this.clientMethodParameter = clientMethodParameter;
        }

        public ProxyMethodParameter getProxyMethodParameter() {
            return proxyMethodParameter;
        }

        public ClientMethodParameter getClientMethodParameter() {
            return clientMethodParameter;
        }

        public String getName() {
            return this.getClientMethodParameter().getName();
        }

        public String getSerializedName() {
            if (this.getProxyMethodParameter() == null) {
                return null;
            } else {
                String name = this.getProxyMethodParameter().getRequestParameterName();
                if (name == null && this.getProxyMethodParameter().getRequestParameterLocation() == RequestParameterLocation.BODY) {
                    name = "__internal_request_BODY";
                }
                return name;
            }
        }
    }
}
