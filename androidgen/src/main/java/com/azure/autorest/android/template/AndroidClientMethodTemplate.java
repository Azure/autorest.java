package com.azure.autorest.android.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.javamodel.JavaType;
import com.azure.autorest.template.ClientMethodTemplate;

import java.util.stream.Stream;

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
                function.line(declareCompletableFuture(clientMethod, completeFutureVariableName));

                final String callbackVariableName = "callbackVariable";
                function.line(declareSinglePageCallback(restAPIMethod, completeFutureVariableName, callbackVariableName));

                String serviceMethodCall = generateProxyMethodCall(clientMethod, restAPIMethod, settings, callbackVariableName);
                function.line(serviceMethodCall);
                function.methodReturn(completeFutureVariableName);
            } else {
                // REVISIT: Is there a use case for this?
            }
        });
    }

    private String declareSinglePageCallback(ProxyMethod restAPIMethod, String completeFutureVariableName, String callbackVariableName) {
        ProxyMethodParameter callbackParam = restAPIMethod.getParameters().stream().filter(param -> param.getName().equals("callback")).findFirst().get();
        IType callbackDataType = ((GenericType) callbackParam.getClientType()).getTypeArguments()[0];

        StringBuilder callbackBuilder = new StringBuilder();
        callbackBuilder.append(String.format("Callback<%1$s> %2$s = new Callback<%1$s>() {%n", callbackDataType, callbackVariableName));
        callbackBuilder.append(String.format("\t@Override%n"));
        callbackBuilder.append(String.format("\tpublic void onSuccess(%s response) {%n", callbackDataType));
        callbackBuilder.append(String.format("\t\t%s.complete(new PagedResponseBase<>(response.getRequest(),%n" +
                "response.getStatusCode(),%n" +
                "response.getHeaders(),%n" +
                "response.getValue().getValue(),%n" +
                "response.getValue().getNextLink(),%n" +
                "null));%n", completeFutureVariableName));
        callbackBuilder.append(String.format("\t}%n"));
        callbackBuilder.append(String.format("\t@Override%n"));
        callbackBuilder.append(String.format("\tpublic void onFailure(Throwable error) {%n"));
        callbackBuilder.append(String.format("\t\t%s.completeExceptionally(error);%n", completeFutureVariableName));
        callbackBuilder.append(String.format("\t}%n"));
        callbackBuilder.append(String.format("};%n"));

        return callbackBuilder.toString();
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
            function.line(declareCompletableFuture(clientMethod, completeFutureVariableName));

            final String callbackVariableName = "callbackVariable";
            function.line(declareCallback(clientMethod, completeFutureVariableName, callbackVariableName));

            String serviceMethodCall = generateProxyMethodCall(clientMethod, restAPIMethod, settings, callbackVariableName);
            function.line(serviceMethodCall);
            function.methodReturn(completeFutureVariableName);
        });
    }

    private String declareCompletableFuture(ClientMethod clientMethod, String completeFutureVariableName) {
        return String.format("%1$s %2$s = new CompletableFuture<>();", clientMethod.getReturnValue().getType(), completeFutureVariableName);
    }

    private String declareCallback(ClientMethod clientMethod, String completeFutureVariableName, String callbackVariableName) {
        GenericType clientReturnGenericType = (GenericType) clientMethod.getReturnValue().getType().getClientType();
        IType responseType = clientReturnGenericType.getTypeArguments()[0];
        StringBuilder callbackBuilder = new StringBuilder();
        callbackBuilder.append(String.format("Callback<%1$s> %2$s = new Callback<%1$s>() {%n", responseType, callbackVariableName));
        callbackBuilder.append(String.format("\t@Override%n"));
        callbackBuilder.append(String.format("\tpublic void onSuccess(%s response) {%n", responseType));
        callbackBuilder.append(String.format("\t\t%s.complete(response);%n", completeFutureVariableName));
        callbackBuilder.append(String.format("\t}%n"));
        callbackBuilder.append(String.format("\t@Override%n"));
        callbackBuilder.append(String.format("\tpublic void onFailure(Throwable error) {%n"));
        callbackBuilder.append(String.format("\t\t%s.completeExceptionally(error);%n", completeFutureVariableName));
        callbackBuilder.append(String.format("\t}%n"));
        callbackBuilder.append(String.format("};%n"));

        return callbackBuilder.toString();
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
                if (returnType instanceof PrimitiveType
                        && returnType != PrimitiveType.Void) {
                    function.methodReturn(String.format("%s(%s).get()", effectiveAsyncMethodName, clientMethod.getArgumentList()));
                } else {
                    String proxyMethodCall = String.format("%s(%s).get()", effectiveAsyncMethodName, clientMethod.getArgumentList());

                    function.line("try {");
                    if (returnType != PrimitiveType.Void) {
                        function.methodReturn(proxyMethodCall);
                    } else {
                        function.line(proxyMethodCall + ";");
                    }
                    function.line("} catch (InterruptedException e) {");
                    function.line("throw new RuntimeException(e);");
                    function.line("} catch (ExecutionException e) {");
                    function.line("throw new RuntimeException(e);");
                    function.line("}");
                }
            }
        });
    }


}
