package com.azure.autorest.template.protocol;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ArrayType;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaVisibility;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Writes a protocol method to a JavaType block.
 */
public class ProtocolAsyncMethodTemplate extends ProtocolMethodBaseTemplate {
    private static ProtocolAsyncMethodTemplate _instance = new ProtocolAsyncMethodTemplate();

    protected ProtocolAsyncMethodTemplate() {
    }

    public static ProtocolAsyncMethodTemplate getInstance() {
        return _instance;
    }

    public final void write(ClientMethod clientMethod, JavaClass typeBlock) {
        generateJavadoc(clientMethod, typeBlock);

        String methodName;
        if (clientMethod.getName().endsWith("Async")) {
            methodName = clientMethod.getName().substring(0, clientMethod.getName().length() - "Async".length());
        } else {
            methodName = clientMethod.getName();
        }
        String returnType = "Mono<Response<BinaryData>>";

        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        StringBuilder methodArgsDeclare = new StringBuilder();
        StringBuilder methodArgsInvoke = new StringBuilder();
        for (ProxyMethodParameter p : clientMethod.getProxyMethod().getParameters()) {
            if (p.getIsRequired() && !p.getFromClient() && !p.getIsConstant()) {
                IType clientType = p.getClientType();
                if (clientType instanceof ClassType || clientType instanceof EnumType) {
                    clientType = ClassType.String;
                }
                if (p.getRequestParameterLocation() == RequestParameterLocation.Body) {
                    clientType = ArrayType.ByteArray;
                }
                methodArgsDeclare.append(clientType).append(" ").append(p.getName()).append(", ");
                methodArgsInvoke.append(p.getName()).append(", ");
            }
        }
        methodArgsDeclare.append("RequestOptions options");
        JavaVisibility visibility = JavaVisibility.Public;
        if (JavaSettings.getInstance().isContextClientMethodParameter()) {
            visibility = JavaVisibility.PackagePrivate;
            String publicMethodArgs = methodArgsDeclare.toString();
            methodArgsDeclare.append(", Context context");

            // Actual method without context param
            typeBlock.publicMethod(String.format("%s %s(%s)", returnType, methodName, publicMethodArgs), methodBlock -> {
                methodBlock.methodReturn(String.format("FluxUtil.withContext(c -> %s(%s))", methodName, methodArgsInvoke));
            });
        }
        typeBlock.method(visibility, null, String.format("%s %s(%s)", returnType, methodName, methodArgsDeclare), methodBlock -> {
            String url = clientMethod.getProxyMethod().getBaseUrl().replaceAll("/$", "")
                    + "/" + clientMethod.getProxyMethod().getUrlPath().replaceAll("^/", "");
            methodBlock.line("String url = \"%s\";", url);
            for (ProxyMethodParameter hostParam : clientMethod.getProxyMethod().getParameters()
                    .stream().filter(p -> RequestParameterLocation.Uri.equals(p.getRequestParameterLocation()))
                    .collect(Collectors.toList())) {
                String parameterReference = convertClientTypeToWireType(hostParam, JavaSettings.getInstance());
                String value = hostParam.getWireType() == ClassType.String? parameterReference : String.format("String.valueOf(%s)", parameterReference);
                methodBlock.line("url = url.replace(\"%s\", %s);", hostParam.getRequestParameterName(), value);
            }
            for (ProxyMethodParameter pathParam : clientMethod.getProxyMethod().getParameters()
                    .stream().filter(p -> RequestParameterLocation.Path.equals(p.getRequestParameterLocation()))
                    .collect(Collectors.toList())) {
                String parameterReference = convertClientTypeToWireType(pathParam, JavaSettings.getInstance());
                String value = pathParam.getWireType() == ClassType.String? parameterReference : String.format("String.valueOf(%s)", parameterReference);
                methodBlock.line("url = url.replace(\"%s\", %s);", pathParam.getRequestParameterName(), value);
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
                methodBlock.line("url = url + (url.contains(\"?\") ? \"&\" : \"?\") + \"%s=\" + value;", queryParam.getRequestParameterName(), value);
            }
            methodBlock.line("HttpRequest request = new HttpRequest(HttpMethod.%s, url);", clientMethod.getProxyMethod().getHttpMethod());
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
                methodBlock.line("request.getHeaders().set(\"%s\", %s);", headerParam.getRequestParameterName(), value);
            }
            if (!headerParams.contains("Content-Type")) {
                methodBlock.line("request.getHeaders().set(\"Content-Type\", \"%s\");", clientMethod.getProxyMethod().getRequestContentType());
            }
            if (!headerParams.contains("Accept")) {
                methodBlock.line("request.getHeaders().set(\"Accept\", \"%s\");", String.join(", ", clientMethod.getProxyMethod().getResponseContentTypes()));
            }
            Optional<ProxyMethodParameter> bodyParameter = clientMethod.getProxyMethod().getParameters()
                    .stream().filter(p -> p.getIsRequired() && p.getRequestParameterLocation() == RequestParameterLocation.Body)
                    .findFirst();
            if (bodyParameter.isPresent()) {
                methodBlock.line("request.setBody(%s);", bodyParameter.get().getName());
            }
            methodBlock.ifBlock("options != null", ifBlock -> {
                methodBlock.line("options.getRequestCallback().accept(request);");
            });
            if (JavaSettings.getInstance().isContextClientMethodParameter()) {
                methodBlock.line("return httpPipeline.send(request, context)");
            } else if (JavaSettings.getInstance().getAddContextParameter()) {
                methodBlock.line("return FluxUtil.withContext(c -> httpPipeline.send(request, c))");
            } else {
                methodBlock.line("return httpPipeline.send(request)");
            }
            methodBlock.increaseIndent();
            methodBlock.line(".flatMap(httpResponse -> BinaryData.fromFlux(httpResponse.getBody())");
            methodBlock.line(".map(binaryData -> new SimpleResponse<>(httpResponse.getRequest(), httpResponse.getStatusCode(), httpResponse.getHeaders(), binaryData)));");
            methodBlock.decreaseIndent();
        });
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
