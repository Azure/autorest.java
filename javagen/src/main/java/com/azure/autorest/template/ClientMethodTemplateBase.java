package com.azure.autorest.template;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.*;
import com.azure.autorest.model.javamodel.JavaJavadocComment;
import com.azure.autorest.model.javamodel.JavaType;
import com.azure.autorest.util.CodeNamer;
import com.azure.core.util.CoreUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ClientMethodTemplateBase implements IJavaTemplate<ClientMethod, JavaType> {

    protected static void generateProtocolMethodJavadoc(ClientMethod clientMethod, JavaJavadocComment commentBlock) {
        commentBlock.description(clientMethod.getDescription());

        List<ProxyMethodParameter> queryParameters = clientMethod.getProxyMethod().getAllParameters()
                .stream().filter(p -> RequestParameterLocation.Query.equals(p.getRequestParameterLocation()))
                .collect(Collectors.toList());
        if (!queryParameters.isEmpty()) {
            optionalParametersJavadoc("Query Parameters", queryParameters, commentBlock);
        }

        List<ProxyMethodParameter> headerParameters = clientMethod.getProxyMethod().getAllParameters()
                .stream().filter(p -> !p.getName().equals("accept") && RequestParameterLocation.Header.equals(p.getRequestParameterLocation()))
                .collect(Collectors.toList());
        if (!headerParameters.isEmpty()) {
            optionalParametersJavadoc("Header Parameters", headerParameters, commentBlock);
        }

        // Request body
        Set<IType> typesInJavadoc = new HashSet<>();

        clientMethod.getProxyMethod().getAllParameters()
                .stream().filter(p -> RequestParameterLocation.Body.equals(p.getRequestParameterLocation()))
                .map(ProxyMethodParameter::getRawType)
                .findFirst()
                .ifPresent(type -> requestBodySchemaJavadoc(type, commentBlock, typesInJavadoc));

        // Response body
        IType responseBodyType;
        if (JavaSettings.getInstance().isLowLevelClient()) {
            responseBodyType = clientMethod.getProxyMethod().getRawResponseBodyType();
        } else {
            responseBodyType = clientMethod.getProxyMethod().getResponseBodyType();
        }
        if (responseBodyType != null && !responseBodyType.equals(PrimitiveType.Void)) {
            responseBodySchemaJavadoc(responseBodyType, commentBlock, typesInJavadoc);
        }

        clientMethod.getParameters().forEach(p -> commentBlock.param(p.getName(), methodParameterDescriptionOrDefault(p)));
        commentBlock.methodThrows("HttpResponseException", "thrown if status code is 400 or above, if throwOnError in requestOptions is not false");
        commentBlock.methodReturns(clientMethod.getReturnValue().getDescription());
    }

    private static void optionalParametersJavadoc(String title, List<ProxyMethodParameter> parameters, JavaJavadocComment commentBlock) {
        commentBlock.line(String.format("<p><strong>%s</strong></p>", title));
        commentBlock.line("<table border=\"1\">");
        commentBlock.line(String.format("    <caption>%s</caption>", title));
        commentBlock.line("    <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>");
        for (ProxyMethodParameter parameter : parameters) {
            commentBlock.line(String.format(
                    "    <tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",
                    parameter.getName(),
                    CodeNamer.escapeXmlComment(parameter.getClientType().toString()),
                    parameter.getIsRequired() ? "Yes" : "No",
                    parameterDescriptionOrDefault(parameter)));
        }
        commentBlock.line("</table>");
    }

    private static void requestBodySchemaJavadoc(IType requestBodyType, JavaJavadocComment commentBlock, Set<IType> typesInJavadoc) {
        typesInJavadoc.clear();

        if (requestBodyType == null) {
            return;
        }
        commentBlock.line("<p><strong>Request Body Schema</strong></p>");
        commentBlock.line("<pre>{@code");
        bodySchemaJavadoc(requestBodyType, commentBlock, "", null, typesInJavadoc);
        commentBlock.line("}</pre>");
    }

    private static void responseBodySchemaJavadoc(IType responseBodyType, JavaJavadocComment commentBlock, Set<IType> typesInJavadoc) {
        typesInJavadoc.clear();

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

    private static String methodParameterDescriptionOrDefault(ClientMethodParameter p) {
        String doc = p.getDescription();
        if (CoreUtils.isNullOrEmpty(doc)) {
            doc = String.format("The %1$s parameter", p.getName());
        }
        return CodeNamer.escapeXmlComment(doc);
    }
}
