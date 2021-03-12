package com.azure.autorest.android.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
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
            ConvertClientTypesToWireTypes(function, clientMethod, restAPIMethod.getParameters(), clientMethod.getClientReference(), settings);

            
        });
    }

    @Override
    protected void generateSimpleAsync(ClientMethod clientMethod, JavaType typeBlock, ProxyMethod restAPIMethod, JavaSettings settings) {
        typeBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
        writeMethod(typeBlock, clientMethod.getMethodVisibility(), clientMethod.getDeclaration(), (function -> {
            AddOptionalVariables(function, clientMethod, restAPIMethod.getParameters(), settings);
            function.line("return %s(%s);", clientMethod.getProxyMethod().getSimpleAsyncRestResponseMethodName(), clientMethod.getArgumentList());
            /*
            function.indent(() -> {
                GenericType restAPIMethodClientReturnType = (GenericType) restAPIMethod.getReturnType().getClientType();
                IType returnValueTypeArgumentClientType = restAPIMethodClientReturnType.getTypeArguments()[0];
                if (GenericType.Flux(ClassType.ByteBuffer).equals(clientMethod.getReturnValue().getType())) {
                    function.text(".flatMapMany(StreamResponse::getValue);");
                } else if (!GenericType.Mono(ClassType.Void).equals(clientMethod.getReturnValue().getType()) &&
                        !GenericType.Flux(ClassType.Void).equals(clientMethod.getReturnValue().getType())) {
                    function.text(".flatMap(");
                    function.lambda(returnValueTypeArgumentClientType.toString(), "res", lambda -> {
                        lambda.ifBlock("res.getValue() != null", ifAction -> {
                            ifAction.methodReturn("Mono.just(res.getValue())");
                        }).elseBlock(elseAction -> {
                            elseAction.methodReturn("Mono.empty()");
                        });
                    });
                    function.line(");");
                } else {
                    function.text(".flatMap(");
                    function.lambda(returnValueTypeArgumentClientType.toString(), "res", "Mono.empty()");
                    function.line(");");
                }
            });

             */
        }));
    }

}
