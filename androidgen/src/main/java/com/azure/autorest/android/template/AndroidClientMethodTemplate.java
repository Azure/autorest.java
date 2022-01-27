// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.android.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.javamodel.JavaType;
import com.azure.autorest.template.ClientMethodTemplate;

public class AndroidClientMethodTemplate extends ClientMethodTemplate {

    private static ClientMethodTemplate _instance = new AndroidClientMethodTemplate();

    protected AndroidClientMethodTemplate() {
    }

    public static ClientMethodTemplate getInstance() {
        return _instance;
    }

    @Override
    protected IType getContextType() {
        return ClassType.AndroidContext;
    }

    @Override
    protected void generatePagingSync(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
    }

    @Override
    protected void generatePagingAsync(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
    }

    @Override
    protected void generatePagedAsyncSinglePage(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        writeMethod(typeBlock, clientMethod.getMethodVisibility(), clientMethod.getDeclaration(), function -> {
            AddValidations(function, clientMethod.getRequiredNullableParameterExpressions(), clientMethod.getValidateExpressions(), settings);
            AddOptionalAndConstantVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
            ApplyParameterTransformations(function, clientMethod, settings);
            //ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.getParameters(), clientMethod.getClientReference(), settings);

            if (clientMethod.getMethodPageDetails().nonNullNextLink()) {
                final String completeFutureVariableName = "completableFuture";
                function.line(declarePagedCompletableFuture(clientMethod, restAPIMethod, completeFutureVariableName));

                String serviceMethodCall = generateProxyMethodCall(clientMethod, restAPIMethod, settings, completeFutureVariableName);
                function.line(serviceMethodCall);
                function.methodReturn(completeFutureVariableName);
            } else {
                // REVISIT: Is there a use case for this?
            }
        });
    }

    private String declarePagedCompletableFuture(ClientMethod clientMethod,
                                                 ProxyMethod restAPIMethod,
                                                 String completeFutureVariableName) {
        ProxyMethodParameter callbackParam = restAPIMethod.getParameters().stream().filter(param -> param.getName().equals("callback")).findFirst().get();
        GenericType callbackResponseType = (com.azure.autorest.model.clientmodel.GenericType) ((GenericType) callbackParam.getClientType()).getTypeArguments()[0];
        IType callbackDataType = callbackResponseType.getTypeArguments()[0];

        GenericType clientReturnGenericType = (GenericType) clientMethod.getReturnValue().getType().getClientType();
        GenericType responseType = (GenericType) clientReturnGenericType.getTypeArguments()[0];
        IType modelType = responseType.getTypeArguments()[0];

        StringBuilder completableBuilder = new StringBuilder();
        completableBuilder.append(String.format("PagedResponseCompletableFuture<%1$s, %2$s> %3$s =%n", callbackDataType, modelType, completeFutureVariableName));
        completableBuilder.append(String.format("\tnew PagedResponseCompletableFuture<>(%n"));
        completableBuilder.append(String.format("\t\t response -> {%n"));
        completableBuilder.append(String.format("\t\t\t return new PagedResponseBase<>(%n"));
        completableBuilder.append(String.format("\t\t\t\t response.getRequest(),%n"));
        completableBuilder.append(String.format("\t\t\t\t response.getStatusCode(),%n"));
        completableBuilder.append(String.format("\t\t\t\t response.getHeaders(),%n"));
        completableBuilder.append(String.format("\t\t\t\t response.getValue().getValue(),%n"));
        completableBuilder.append(String.format("\t\t\t\t response.getValue().getNextLink(),%n"));
        completableBuilder.append(String.format("\t\t\t\t null);%n"));
        completableBuilder.append(String.format("\t\t\t });%n"));
        return completableBuilder.toString();
    }


    @Override
    protected void generateResumable(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
    }

    @Override
    protected void generateSimpleAsyncRestResponse(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        writeMethod(typeBlock, clientMethod.getMethodVisibility(), clientMethod.getDeclaration(), function -> {
            AddValidations(function, clientMethod.getRequiredNullableParameterExpressions(), clientMethod.getValidateExpressions(), settings);
            AddOptionalAndConstantVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
            ApplyParameterTransformations(function, clientMethod, settings);
            // REVISIT: Restore this call for Android
            // ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.getParameters(), clientMethod.getClientReference(), settings);

            final String completeFutureVariableName = "completableFuture";
            function.line(declareResponseCompletableFuture(clientMethod, completeFutureVariableName));

            String serviceMethodCall = generateProxyMethodCall(clientMethod, restAPIMethod, settings, completeFutureVariableName);
            function.line(serviceMethodCall);
            function.methodReturn(completeFutureVariableName);
        });
    }

    private String declareResponseCompletableFuture(ClientMethod clientMethod, String completeFutureVariableName) {
        GenericType clientReturnGenericType = (GenericType) clientMethod.getReturnValue().getType().getClientType();
        GenericType responseType = (GenericType) clientReturnGenericType.getTypeArguments()[0];
        IType modelType = responseType.getTypeArguments()[0];
        if (modelType.equals(PrimitiveType.Void)) {
            modelType = ClassType.Void;
        } else if (modelType.equals(PrimitiveType.Boolean)) {
            modelType = ClassType.Boolean;
        } else if (modelType.equals(PrimitiveType.Double)) {
            modelType = ClassType.Double;
        } else if (modelType.equals(PrimitiveType.Float)) {
            modelType = ClassType.Float;
        } else if (modelType.equals(PrimitiveType.Int)) {
            modelType = ClassType.Integer;
        } else if (modelType.equals(PrimitiveType.Long)) {
            modelType = ClassType.Long;
        }

        return String.format("ResponseCompletableFuture<%1$s> %2$s = new ResponseCompletableFuture<>(); ", modelType, completeFutureVariableName);
    }

    private String generateProxyMethodCall(ClientMethod clientMethod, ProxyMethod restAPIMethod, JavaSettings settings, String callbackVariableName) {
        java.util.List<String> serviceMethodArgs = clientMethod.getProxyMethodArguments(settings)
                .stream()
                .map(argVal -> {
                    if (clientMethod.getParameters().stream().filter(param -> param.getName().equals(argVal))
                            .anyMatch(param -> clientMethod.getMethodTransformationDetails().stream()
                                    .anyMatch(transformation -> param.getName().equals(transformation.getOutParameter().getName())))) {
                        return argVal + "Local";
                    }

                    if (!contextInParameters(clientMethod) && argVal.equals("context")) {
                        return "Context.NONE";
                    }

                    if (argVal.startsWith("callback")) {
                        return callbackVariableName;
                    }
                    return argVal;
                })
                .collect(java.util.stream.Collectors.toList());
        String restAPIMethodArgumentList = String.join(", ", serviceMethodArgs);
        return String.format("service.%s(%s);", restAPIMethod.getName(), restAPIMethodArgumentList);
    }

    @Override
    protected void generateSimpleAsync(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        writeMethod(typeBlock, clientMethod.getMethodVisibility(), clientMethod.getDeclaration(), (function -> {
            AddOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
            function.line("return %s(%s).thenApply(response -> response.getValue());", clientMethod.getProxyMethod().getSimpleAsyncRestResponseMethodName(), clientMethod.getArgumentList());
        }));
    }

    @Override
    protected void generateSyncMethod(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        String asyncMethodName = clientMethod.getSimpleAsyncMethodName();
        if (clientMethod.getType() == ClientMethodType.SimpleSyncRestResponse) {
            asyncMethodName = clientMethod.getSimpleWithResponseAsyncMethodName();
        }
        String effectiveAsyncMethodName = asyncMethodName;
        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        typeBlock.publicMethod(clientMethod.getDeclaration(), function -> {
            AddOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
            if (clientMethod.getReturnValue().getType() == ClassType.InputStream) {
                throw new UnsupportedOperationException("Return type 'ClassType.InputStream' not implemented for android");
            } else {
                IType returnType = clientMethod.getReturnValue().getType();
                String proxyMethodCall = String.format("%s(%s).get()", effectiveAsyncMethodName, clientMethod.getArgumentList());

                function.line("try {");
                if (returnType != PrimitiveType.Void) {
                    function.methodReturn(proxyMethodCall);
                } else {
                    function.line("\t" + proxyMethodCall + ";");
                }
                function.line("} catch (InterruptedException e) {");
                function.line("\tthrow new RuntimeException(e);");
                function.line("} catch (ExecutionException e) {");
                function.line("\tthrow new RuntimeException(e);");
                function.line("}");

            }
        });
    }

}
