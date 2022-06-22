// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientEnumValue;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.model.clientmodel.ParameterSynthesizedOrigin;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.javamodel.JavaJavadocComment;
import com.azure.autorest.model.javamodel.JavaType;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.CodeNamer;
import com.azure.core.util.CoreUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ClientMethodTemplateBase implements IJavaTemplate<ClientMethod, JavaType> {

    protected static void generateProtocolMethodJavadoc(ClientMethod clientMethod, JavaJavadocComment commentBlock) {
        commentBlock.description(clientMethod.getDescription());

        if (clientMethod.getProxyMethod() != null) {
            List<ProxyMethodParameter> queryParameters = clientMethod.getProxyMethod().getAllParameters().stream()
                    .filter(p -> RequestParameterLocation.QUERY.equals(p.getRequestParameterLocation()))
                    // ignore if synthesized by modelerfour, i.e. api-version
                    .filter(p -> p.getOrigin() == ParameterSynthesizedOrigin.NONE)
                    .collect(Collectors.toList());
            if (!queryParameters.isEmpty()) {
                optionalParametersJavadoc("Query Parameters", queryParameters, commentBlock);
                commentBlock.line("You can add these to a request with {@code new RequestOptions.addQueryParam()}");
            }

            List<ProxyMethodParameter> headerParameters = clientMethod.getProxyMethod().getAllParameters().stream()
                    .filter(p -> RequestParameterLocation.HEADER.equals(p.getRequestParameterLocation()))
                    // ignore if synthesized by modelerfour and is constant
                    // we would want user to provide a correct "content-type" if it is not a constant
                    .filter(p -> p.getOrigin() == ParameterSynthesizedOrigin.NONE || !p.getIsConstant())
                    .collect(Collectors.toList());
            if (!headerParameters.isEmpty()) {
                optionalParametersJavadoc("Header Parameters", headerParameters, commentBlock);
                commentBlock.line("You can add these to a request with {@code new RequestOptions.addHeader()}");
            }

            // Request body
            Set<IType> typesInJavadoc = new HashSet<>();

            boolean isBodyParamRequired = clientMethod.getProxyMethod().getAllParameters()
                    .stream().filter(p -> RequestParameterLocation.BODY.equals(p.getRequestParameterLocation()))
                            .map(ProxyMethodParameter::getIsRequired).findFirst().orElse(false);

            clientMethod.getProxyMethod().getAllParameters()
                    .stream().filter(p -> RequestParameterLocation.BODY.equals(p.getRequestParameterLocation()))
                    .map(ProxyMethodParameter::getRawType)
                    .findFirst()
                    .ifPresent(type -> requestBodySchemaJavadoc(type, commentBlock, typesInJavadoc, isBodyParamRequired));

            // Response body
            IType responseBodyType;
            if (JavaSettings.getInstance().isDataPlaneClient()) {
                responseBodyType = clientMethod.getProxyMethod().getRawResponseBodyType();
            } else {
                responseBodyType = clientMethod.getProxyMethod().getResponseBodyType();
            }
            if (responseBodyType != null && !responseBodyType.equals(PrimitiveType.Void)) {
                responseBodySchemaJavadoc(responseBodyType, commentBlock, typesInJavadoc);
            }
        }

        clientMethod.getParameters().forEach(p -> commentBlock.param(p.getName(), methodParameterDescriptionOrDefault(p)));
        if (clientMethod.getProxyMethod() != null) {
            generateJavadocExceptions(clientMethod, commentBlock, false);
        }
        commentBlock.methodReturns(clientMethod.getReturnValue().getDescription());
    }

    protected static void generateJavadocExceptions(ClientMethod clientMethod, JavaJavadocComment commentBlock, boolean useFullClassName) {
        ProxyMethod restAPIMethod = clientMethod.getProxyMethod();
        if (restAPIMethod != null && restAPIMethod.getUnexpectedResponseExceptionType() != null) {
            commentBlock.methodThrows(useFullClassName
                            ? restAPIMethod.getUnexpectedResponseExceptionType().getFullName()
                            : restAPIMethod.getUnexpectedResponseExceptionType().getName(),
                    "thrown if the request is rejected by server");
        }
        if (restAPIMethod != null && restAPIMethod.getUnexpectedResponseExceptionTypes() != null) {
            for (Map.Entry<ClassType, List<Integer>> exception : restAPIMethod.getUnexpectedResponseExceptionTypes().entrySet()) {
                commentBlock.methodThrows(exception.getKey().toString(),
                        String.format("thrown if the request is rejected by server on status code %s",
                                exception.getValue().stream().map(String::valueOf).collect(Collectors.joining(", "))));
            }
        }
    }

    private static void optionalParametersJavadoc(String title, List<ProxyMethodParameter> parameters, JavaJavadocComment commentBlock) {
        if (!hasParametersToPrintInJavadoc(parameters)) {
            return;
        }
        commentBlock.line(String.format("<p><strong>%s</strong></p>", title));
        commentBlock.line("<table border=\"1\">");
        commentBlock.line(String.format("    <caption>%s</caption>", title));
        commentBlock.line("    <tr><th>Name</th><th>Type</th><th>Required</th><th>Description</th></tr>");
        for (ProxyMethodParameter parameter : parameters) {
            boolean parameterIsConstantOrFromClient = parameter.getIsConstant() || parameter.getFromClient();
            if (!parameter.getIsRequired() && !parameterIsConstantOrFromClient) {
                commentBlock.line(String.format(
                        "    <tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",
                        parameter.getRequestParameterName(),
                        CodeNamer.escapeXmlComment(parameter.getClientType().toString()),
                        parameter.getIsRequired() ? "Yes" : "No",
                        parameterDescriptionOrDefault(parameter)));
            }

        }
        commentBlock.line("</table>");
    }

    private static boolean hasParametersToPrintInJavadoc(List<ProxyMethodParameter> parameters) {
        return parameters.stream().anyMatch(parameter -> {
            boolean parameterIsConstantOrFromClient = parameter.getIsConstant() || parameter.getFromClient();
            boolean parameterIsRequired = parameter.getIsRequired();
            return !parameterIsRequired && !parameterIsConstantOrFromClient;
        });
    }

    private static void requestBodySchemaJavadoc(IType requestBodyType, JavaJavadocComment commentBlock, Set<IType> typesInJavadoc, boolean isBodyParamRequired) {
        typesInJavadoc.clear();

        if (requestBodyType == null) {
            return;
        }
        commentBlock.line("<p><strong>Request Body Schema</strong></p>");
        commentBlock.line("<pre>{@code");
        bodySchemaJavadoc(requestBodyType, commentBlock, "", null, typesInJavadoc, isBodyParamRequired, true);
        commentBlock.line("}</pre>");
    }

    private static void responseBodySchemaJavadoc(IType responseBodyType, JavaJavadocComment commentBlock, Set<IType> typesInJavadoc) {
        typesInJavadoc.clear();

        if (responseBodyType == null) {
            return;
        }
        commentBlock.line("<p><strong>Response Body Schema</strong></p>");
        commentBlock.line("<pre>{@code");
        bodySchemaJavadoc(responseBodyType, commentBlock, "", null, typesInJavadoc, true, true);
        commentBlock.line("}</pre>");
    }

    private static void bodySchemaJavadoc(IType type, JavaJavadocComment commentBlock, String indent, String name, Set<IType> typesInJavadoc, boolean isRequired, boolean isRootSchema) {
        String nextIndent = indent + "    ";
        if (ClientModelUtil.isClientModel(type) && !typesInJavadoc.contains(type)) {
            typesInJavadoc.add(type);
            ClientModel model = ClientModelUtil.getClientModel(((ClassType) type).getName());
            if (name != null) {
                commentBlock.line(indent + name + appendOptionalOrRequiredAttribute(isRequired, isRootSchema) + ": {");
            } else {
                commentBlock.line(indent + appendOptionalOrRequiredAttribute(isRequired, isRootSchema) + "{");
            }
            List<ClientModelProperty> properties = new ArrayList<>();
            traverseProperties(model, properties);
            for (ClientModelProperty property : properties) {
                bodySchemaJavadoc(property.getClientType(), commentBlock, nextIndent, property.getName(), typesInJavadoc, property.isRequired(), false);
            }
            commentBlock.line(indent + "}");
        } else if (typesInJavadoc.contains(type)) {
            if (name != null) {
                commentBlock.line(indent + name + appendOptionalOrRequiredAttribute(isRequired, isRootSchema) + ": (recursive schema, see " + name + " above)");
            } else {
                commentBlock.line(indent + "(recursive schema, see above)");
            }
        } else if (type instanceof ListType) {
            if (name != null) {
                commentBlock.line(indent + name + appendOptionalOrRequiredAttribute(isRequired, isRootSchema) + ": [");
            } else {
                commentBlock.line(indent + appendOptionalOrRequiredAttribute(isRequired, isRootSchema) + "[");
            }
            bodySchemaJavadoc(((ListType) type).getElementType(), commentBlock, nextIndent, null, typesInJavadoc, isRequired, false);
            commentBlock.line(indent + "]");
        } else if (type instanceof EnumType) {
            String values = ((EnumType) type).getValues().stream()
                    .map(ClientEnumValue::getValue)
                    .collect(Collectors.joining("/"));
            if (name != null) {
                commentBlock.line(indent + name + ": String(" + values + ")" + appendOptionalOrRequiredAttribute(isRequired, isRootSchema));
            } else {
                commentBlock.line(indent + "String(" + values + ")" + appendOptionalOrRequiredAttribute(isRequired, isRootSchema));
            }
        } else if (type instanceof MapType) {
            if (name != null) {
                commentBlock.line(indent + name + appendOptionalOrRequiredAttribute(isRequired, isRootSchema) + ": {");
            } else {
                commentBlock.line(indent + appendOptionalOrRequiredAttribute(isRequired, isRootSchema) + "{");
            }
            bodySchemaJavadoc(((MapType) type).getValueType(), commentBlock, nextIndent, "String", typesInJavadoc, isRequired, false);
            commentBlock.line(indent + "}");
        } else {
            if (name != null) {
                commentBlock.line(indent + name + ": " + type.toString() + appendOptionalOrRequiredAttribute(isRequired, isRootSchema));
            } else {
                commentBlock.line(indent + type.toString() + appendOptionalOrRequiredAttribute(isRequired, isRootSchema));
            }
        }
    }

    private static void traverseProperties(ClientModel model, List<ClientModelProperty> properties) {
        if (model.getParentModelName() != null) {
            traverseProperties(ClientModelUtil.getClientModel(model.getParentModelName()), properties);
        }
        properties.addAll(model.getProperties());
    }

    private static String parameterDescriptionOrDefault(ProxyMethodParameter parameter) {
        String paramJavadoc = parameter.getDescription();
        if (CoreUtils.isNullOrEmpty(paramJavadoc)) {
            paramJavadoc = String.format("The %1$s parameter", parameter.getName());
        }
        String description = CodeNamer.escapeXmlComment(paramJavadoc);
        // query with array, add additional description
        if (parameter.getRequestParameterLocation() == RequestParameterLocation.QUERY && parameter.getCollectionFormat() != null) {
            description = (CoreUtils.isNullOrEmpty(description) || description.endsWith(".")) ? description : (description + ".");
            if (parameter.getExplode()) {
                // collectionFormat: multi
                description += " Call {@link RequestOptions#addQueryParam} to add string to array.";
            } else {
                // collectionFormat: csv, ssv, tsv, pipes
                description += String.format(" In the form of \"%s\" separated string.", parameter.getCollectionFormat().getDelimiter());
            }
        }
        return description;
    }

    private static String methodParameterDescriptionOrDefault(ClientMethodParameter p) {
        String doc = p.getDescription();
        if (CoreUtils.isNullOrEmpty(doc)) {
            doc = String.format("The %1$s parameter", p.getName());
        }
        return doc;
    }

    private static String appendOptionalOrRequiredAttribute(boolean isRequired, boolean isRootSchema) {
        return isRootSchema ? "" : isRequired ? " (Required)" : " (Optional)";
    }
}
