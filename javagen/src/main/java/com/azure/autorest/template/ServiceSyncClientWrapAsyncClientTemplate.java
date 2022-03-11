// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaVisibility;

import java.util.List;
import java.util.stream.Collectors;

public class ServiceSyncClientWrapAsyncClientTemplate extends ServiceSyncClientTemplate {

    private static final ServiceSyncClientTemplate INSTANCE = new ServiceSyncClientWrapAsyncClientTemplate();

    public static ServiceSyncClientTemplate getInstance() {
        return INSTANCE;
    }

    private static final String ASYNC_CLIENT_VAR_NAME = "asyncClient";

    @Override
    protected void writeClass(AsyncSyncClient syncClient, JavaClass classBlock,
                              String className, JavaVisibility constructorVisibility) {
        // class variable
        String asyncClassName = className.replace("Client", "AsyncClient");

        // constructor
        addGeneratedAnnotation(classBlock);
        classBlock.privateFinalMemberVariable(asyncClassName, ASYNC_CLIENT_VAR_NAME);
        addGeneratedAnnotation(classBlock);
        classBlock.constructor(constructorVisibility, String.format("%1$s(%2$s %3$s)", className,
                asyncClassName, ASYNC_CLIENT_VAR_NAME), constructorBlock -> {
            constructorBlock.line(String.format("this.%1$s = %1$s;", ASYNC_CLIENT_VAR_NAME));
        });

        // methods
        writeMethods(syncClient, classBlock);
    }

    @Override
    protected void writeMethod(ClientMethod clientMethod, JavaClass classBlock) {
        METHOD_TEMPLATE_INSTANCE.write(clientMethod, classBlock);
    }

    private static final WrapperClientMethodTemplate METHOD_TEMPLATE_INSTANCE = new ClientMethodTemplateImpl();

    private static class ClientMethodTemplateImpl extends WrapperClientMethodTemplate {

        @Override
        protected void writeMethodInvocation(ClientMethod clientMethod, JavaBlock function, boolean shouldReturn) {
            List<String> parameterNames = clientMethod.getMethodInputParameters().stream()
                    .map(ClientMethodParameter::getName).collect(Collectors.toList());

            String methodInvoke = String.format("this.asyncClient.%1$s(%2$s)", clientMethod.getName(), String.join(", ", parameterNames));
            switch (clientMethod.getType()) {
                case PagingSync:
                    methodInvoke = "new PagedIterable<>(" + methodInvoke + ")";
                    break;

                case LongRunningBeginSync:
                    methodInvoke = methodInvoke + ".getSyncPoller()";
                    break;

                case SendRequestSync:
                    parameterNames.remove("context");
                    methodInvoke = String.format("this.asyncClient.%1$s(%2$s)", clientMethod.getName(), String.join(", ", parameterNames));
                    methodInvoke = methodInvoke + ".contextWrite(c -> c.putAll(FluxUtil.toReactorContext(context).readOnly())).block()";
                    break;

                default:
                    methodInvoke = methodInvoke + ".block()";
                    break;
            }

            function.line((shouldReturn ? "return " : "") + methodInvoke + ";");
        }
    }
}
