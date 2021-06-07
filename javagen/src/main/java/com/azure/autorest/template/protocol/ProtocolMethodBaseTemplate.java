package com.azure.autorest.template.protocol;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
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
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaJavadocComment;
import com.azure.autorest.model.javamodel.JavaType;
import com.azure.autorest.template.IJavaTemplate;
import com.azure.autorest.util.CodeNamer;
import com.azure.core.util.CoreUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Writes a protocol method to a JavaType block.
 */
public abstract class ProtocolMethodBaseTemplate implements IJavaTemplate<ClientMethod, JavaClass> {
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
}
