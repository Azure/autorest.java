package com.azure.autorest.android.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
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
        callbackBuilder.append(String.format("Callback<%1$s> %2$s = new Callback<%1$s>() {\n", responseType, callbackVariableName));
        callbackBuilder.append("\t@Override\n");
        callbackBuilder.append(String.format("\tpublic void onSuccess(%s response) {\n", responseType));
        callbackBuilder.append(String.format("\t\t%s.complete(response);\n", completeFutureVariableName));
        callbackBuilder.append("\t}\n");
        callbackBuilder.append("\t@Override\n");
        callbackBuilder.append("\tpublic void onFailure(Throwable error) {\n");
        callbackBuilder.append(String.format("\t\t%s.completeExceptionally(error);\n", completeFutureVariableName));
        callbackBuilder.append("\t}\n");
        callbackBuilder.append("};\n");

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
                        return "Context.None";
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

}
