package com.azure.autorest.template.llc;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ArrayType;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientEnumValue;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ClientModels;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.javamodel.JavaInterface;
import com.azure.autorest.model.javamodel.JavaJavadocComment;
import com.azure.autorest.model.javamodel.JavaType;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.template.IJavaTemplate;
import com.azure.autorest.util.CodeNamer;
import com.azure.core.util.CoreUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Writes a ClientMethod to a JavaType block.
 */
public class LowLevelMethodTemplate implements IJavaTemplate<ClientMethod, JavaType> {
    private static LowLevelMethodTemplate _instance = new LowLevelMethodTemplate();

    protected LowLevelMethodTemplate() {
    }

    public static LowLevelMethodTemplate getInstance() {
        return _instance;
    }

    public final void write(ClientMethod clientMethod, JavaType typeBlock) {
        final boolean writingInterface = typeBlock instanceof JavaInterface;
        if (clientMethod.getMethodVisibility() != JavaVisibility.Public && writingInterface) {
            return;
        }

        generateJavadoc(clientMethod, typeBlock);

        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        String methodArgs = clientMethod.getProxyMethod().getParameters().stream()
                .filter(p -> p.getIsRequired() && !p.getFromClient() && !p.getIsConstant()
                        && p.getRequestParameterLocation() != RequestParameterLocation.Body)
                .map(p -> {
                    IType clientType = p.getClientType();
                    if (clientType instanceof ClassType || clientType instanceof EnumType) {
                        clientType = ClassType.String;
                    }
                    return clientType + " " + p.getName();
                })
                .collect(Collectors.joining(", "));
        typeBlock.publicMethod(String.format("DynamicRequest %s(%s)", clientMethod.getProxyMethod().getName(), methodArgs), methodBlock -> {
            String url = clientMethod.getProxyMethod().getBaseUrl().replaceAll("/$", "")
                    + "/" + clientMethod.getProxyMethod().getUrlPath().replaceAll("^/", "");
            methodBlock.line("return new DynamicRequest(serializer, httpPipeline)");
            methodBlock.line("    .setUrl(\"%s\")", url);
            for (ProxyMethodParameter hostParam : clientMethod.getProxyMethod().getParameters()
                    .stream().filter(p -> RequestParameterLocation.Uri.equals(p.getRequestParameterLocation()))
                    .collect(Collectors.toList())) {
                String parameterReference = convertClientTypeToWireType(hostParam, JavaSettings.getInstance());
                String value = hostParam.getWireType() == ClassType.String? parameterReference : String.format("String.valueOf(%s)", parameterReference);
                methodBlock.line("    .setPathParam(\"%s\", %s)", hostParam.getRequestParameterName(), value);
            }
            for (ProxyMethodParameter pathParam : clientMethod.getProxyMethod().getParameters()
                    .stream().filter(p -> RequestParameterLocation.Path.equals(p.getRequestParameterLocation()))
                    .collect(Collectors.toList())) {
                String parameterReference = convertClientTypeToWireType(pathParam, JavaSettings.getInstance());
                String value = pathParam.getWireType() == ClassType.String? parameterReference : String.format("String.valueOf(%s)", parameterReference);
                methodBlock.line("    .setPathParam(\"%s\", %s)", pathParam.getRequestParameterName(), value);
            }
            for (ProxyMethodParameter queryParam : clientMethod.getProxyMethod().getParameters()
                    .stream().filter(p -> p.getIsRequired() && RequestParameterLocation.Query.equals(p.getRequestParameterLocation()))
                    .collect(Collectors.toList())) {
                String value;
                if (!queryParam.getFromClient() && queryParam.getIsConstant()) {
                    value = "\"" + queryParam.getDefaultValue() + "\"";
                } else {
                    String parameterReference = convertClientTypeToWireType(queryParam, JavaSettings.getInstance());
                    value = queryParam.getWireType() == ClassType.String ? parameterReference : String.format("String.valueOf(%s)", parameterReference);
                }
                methodBlock.line("    .addQueryParam(\"%s\", %s)", queryParam.getRequestParameterName(), value);
            }
            Set<String> headerParams = new HashSet<>();
            for (ProxyMethodParameter headerParam : clientMethod.getProxyMethod().getParameters()
                    .stream().filter(p -> p.getIsRequired() && RequestParameterLocation.Header.equals(p.getRequestParameterLocation()))
                    .collect(Collectors.toList())) {
                headerParams.add(headerParam.getRequestParameterName());
                String value;
                if (!headerParam.getFromClient() && headerParam.getIsConstant()) {
                    value = "\"" + headerParam.getDefaultValue() + "\"";
                } else {
                    String parameterReference = convertClientTypeToWireType(headerParam, JavaSettings.getInstance());
                    value = headerParam.getWireType() == ClassType.String ? parameterReference : String.format("String.valueOf(%s)", parameterReference);
                }
                methodBlock.line("    .addHeader(\"%s\", %s)", headerParam.getRequestParameterName(), value);
            }
            if (!headerParams.contains("Content-Type")) {
                methodBlock.line("    .addHeader(\"Content-Type\", \"%s\")", clientMethod.getProxyMethod().getRequestContentType());
            }
            if (!headerParams.contains("Accept")) {
                methodBlock.line("    .addHeader(\"Accept\", \"%s\")", String.join(", ", clientMethod.getProxyMethod().getResponseContentTypes()));
            }
            methodBlock.line("    .setHttpMethod(HttpMethod.%s);", clientMethod.getProxyMethod().getHttpMethod());
        });
    }

    /**
     * Generate javadoc for client method.
     *
     * @param clientMethod client method
     * @param typeBlock code block
     */
    public static void generateJavadoc(ClientMethod clientMethod, JavaType typeBlock) {
        typeBlock.javadocComment(comment -> generateJavadoc(clientMethod, comment));
    }

    /**
     * Generate javadoc for client method.
     *
     * @param clientMethod client method
     * @param commentBlock comment block
     */
    public static void generateJavadoc(ClientMethod clientMethod, JavaJavadocComment commentBlock) {
        commentBlock.description(clientMethod.getDescription());

        List<ProxyMethodParameter> optionalQueryParameters = clientMethod.getProxyMethod().getParameters()
                .stream().filter(p -> RequestParameterLocation.Query.equals(p.getRequestParameterLocation()) && !p.getIsRequired())
                .collect(Collectors.toList());
        if (!optionalQueryParameters.isEmpty()) {
            optionalParametersJavadoc("Optional Query Parameters", optionalQueryParameters, commentBlock);
        }

        List<ProxyMethodParameter> optionalHeaderParameters = clientMethod.getProxyMethod().getParameters()
                .stream().filter(p -> RequestParameterLocation.Header.equals(p.getRequestParameterLocation()) && !p.getIsRequired())
                .collect(Collectors.toList());
        if (!optionalHeaderParameters.isEmpty()) {
            optionalParametersJavadoc("Optional Header Parameters", optionalHeaderParameters, commentBlock);
        }

        Set<IType> typesInJavadoc = new HashSet<>();
        clientMethod.getMethodInputParameters()
                .stream().filter(p -> RequestParameterLocation.Body.equals(p.getLocation()))
                .map(ClientMethodParameter::getClientType)
                .findFirst()
                .ifPresent(iType -> requestBodySchemaJavadoc(iType, commentBlock, typesInJavadoc));

        IType responseBodyType = clientMethod.getProxyMethod().getResponseBodyType();
        if (responseBodyType != null && !responseBodyType.equals(PrimitiveType.Void)) {
            responseBodySchemaJavadoc(responseBodyType, commentBlock, typesInJavadoc);
        }

        clientMethod.getProxyMethod().getParameters()
                .stream().filter(p -> p.getIsRequired() && !p.getFromClient() && !p.getIsConstant()
                    && p.getRequestParameterLocation() != RequestParameterLocation.Body)
                .forEach(parameter ->
                        commentBlock.param(parameter.getName(), parameterDescriptionOrDefault(parameter)));

        commentBlock.methodReturns("a DynamicRequest where customizations can be made before sent to the service");
    }

    private static void optionalParametersJavadoc(String title, List<ProxyMethodParameter> parameters, JavaJavadocComment commentBlock) {
        commentBlock.line(String.format("<p><strong>%s</strong></p>", title));
        commentBlock.line("<table border=\"1\">");
        commentBlock.line(String.format("    <caption>%s</caption>", title));
        commentBlock.line("    <tr><th>Name</th><th>Type</th><th>Description</th></tr>");
        for (ProxyMethodParameter parameter : parameters) {
            commentBlock.line(String.format("    <tr><td>%s</td><td>%s</td><td>%s</td></tr>",
                    parameter.getName(), CodeNamer.escapeXmlComment(parameter.getClientType().toString()), parameterDescriptionOrDefault(parameter)));
        }
        commentBlock.line("</table>");
    }

    private static void requestBodySchemaJavadoc(IType requestBodyType, JavaJavadocComment commentBlock, Set<IType> typesInJavadoc) {
        if (requestBodyType == null) {
            return;
        }
        commentBlock.line("<p><strong>Request Body Schema</strong></p>");
        commentBlock.line("<pre>{@code");
        bodySchemaJavadoc(requestBodyType, commentBlock, "", null, typesInJavadoc);
        commentBlock.line("}</pre>");
    }

    private static void responseBodySchemaJavadoc(IType responseBodyType, JavaJavadocComment commentBlock, Set<IType> typesInJavadoc) {
        if (responseBodyType == null) {
            return;
        }
        commentBlock.line("<p><strong>Response Body Schema</strong></p>");
        commentBlock.line("<pre>{@code");
        bodySchemaJavadoc(responseBodyType, commentBlock, "", null, typesInJavadoc);
        commentBlock.line("}</pre>");
    }

    private static void bodySchemaJavadoc(IType type, JavaJavadocComment commentBlock, String indent, String name, Set<IType> typesInJavadoc) {
        String nextIndent = indent + "    ";
        if (type instanceof ClassType
                && ((ClassType) type).getPackage().startsWith(JavaSettings.getInstance().getPackage())
                && !typesInJavadoc.contains(type)) {
            typesInJavadoc.add(type);
            ClientModel model = ClientModels.Instance.getModel(((ClassType) type).getName());
            if (name != null) {
                commentBlock.line(indent + name + ": {");
            } else {
                commentBlock.line(indent + "{");
            }
            List<ClientModelProperty> properties = new ArrayList<>();
            traverseProperties(model, properties);
            for (ClientModelProperty property : properties) {
                bodySchemaJavadoc(property.getClientType(), commentBlock, nextIndent, property.getName(), typesInJavadoc);
            }
            commentBlock.line(indent + "}");
        } else if (typesInJavadoc.contains(type)) {
            if (name != null) {
                commentBlock.line(indent + name + ": (recursive schema, see " + name + " above)");
            } else {
                commentBlock.line(indent + "(recursive schema, see above)");
            }
        } else if (type instanceof ListType) {
            if (name != null) {
                commentBlock.line(indent + name + ": [");
            } else {
                commentBlock.line(indent + "[");
            }
            bodySchemaJavadoc(((ListType) type).getElementType(), commentBlock, nextIndent, null, typesInJavadoc);
            commentBlock.line(indent + "]");
        } else if (type instanceof EnumType) {
            String values = ((EnumType) type).getValues().stream()
                    .map(ClientEnumValue::getValue)
                    .collect(Collectors.joining("/"));
            if (name != null) {
                commentBlock.line(indent + name + ": String(" + values + ")");
            } else {
                commentBlock.line(indent + "String(" + values + ")");
            }
        } else if (type instanceof MapType) {
            if (name != null) {
                commentBlock.line(indent + name + ": {");
            } else {
                commentBlock.line(indent + "{");
            }
            bodySchemaJavadoc(((MapType) type).getValueType(), commentBlock, nextIndent, "String", typesInJavadoc);
            commentBlock.line(indent + "}");
        } else {
            if (name != null) {
                commentBlock.line(indent + name + ": " + type.toString());
            } else {
                commentBlock.line(indent + type.toString());
            }
        }
    }

    private static void traverseProperties(ClientModel model, List<ClientModelProperty> properties) {
        if (model.getParentModelName() != null) {
            traverseProperties(ClientModels.Instance.getModel(model.getParentModelName()), properties);
        }
        properties.addAll(model.getProperties());
    }

    private static String parameterDescriptionOrDefault(ProxyMethodParameter parameter) {
        String paramJavadoc = parameter.getDescription();
        if (CoreUtils.isNullOrEmpty(paramJavadoc)) {
            paramJavadoc = String.format("The %1$s parameter", parameter.getName());
        }
        return CodeNamer.escapeXmlComment(paramJavadoc);
    }

    private static String convertClientTypeToWireType(ProxyMethodParameter parameter, JavaSettings settings) {
        IType parameterWireType = parameter.getWireType();

        if (parameter.getIsNullable()) {
            parameterWireType = parameterWireType.asNullable();
        }
        IType parameterClientType = parameter.getClientType();

        if (parameterWireType != ClassType.Base64Url &&
                parameter.getRequestParameterLocation() != RequestParameterLocation.Body &&
                //parameter.getRequestParameterLocation() != RequestParameterLocation.FormData &&
                (parameterClientType instanceof ArrayType || parameterClientType instanceof ListType)) {
            parameterWireType = ClassType.String;
        }

        String parameterName = parameter.getName();

        if (parameterWireType.equals(parameterClientType)) {
            return parameterName;
        }

        RequestParameterLocation parameterLocation = parameter.getRequestParameterLocation();
        if (parameterClientType instanceof ArrayType || parameterClientType instanceof ListType) {
            String parameterWireTypeName = parameterWireType.toString();

            if (parameterClientType == ArrayType.ByteArray) {
                if (parameterWireType == ClassType.String) {
                    return String.format("Base64Util.encodeToString(%s)", parameterName);
                } else {
                    return String.format("Base64Url.encode(%s)", parameterName);
                }
            } else if (parameterClientType instanceof ListType) {
                return String.format("serializeIterable(%s, CollectionFormat.%s)", parameterName,
                        parameter.getCollectionFormat().toString().toUpperCase());
            }
        }

        return parameterClientType.convertFromClientType(parameterName);
    }
}
