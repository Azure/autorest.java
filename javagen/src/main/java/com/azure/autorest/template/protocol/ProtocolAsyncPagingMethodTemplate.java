package com.azure.autorest.template.protocol;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.*;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaVisibility;

import java.util.stream.Collectors;

/**
 * ClientMethodType.PagingAsync
 */
public class ProtocolAsyncPagingMethodTemplate extends ProtocolMethodBaseTemplate {
    private static ProtocolAsyncPagingMethodTemplate _instance = new ProtocolAsyncPagingMethodTemplate();

    protected ProtocolAsyncPagingMethodTemplate() {
    }

    public static ProtocolAsyncPagingMethodTemplate getInstance() {
        return _instance;
    }

    @Override
    public void write(ClientMethod clientMethod, JavaClass typeBlock) {
        // Java doc
        generateJavadoc(clientMethod, typeBlock);

        // Method name
        String methodName;
        if (clientMethod.getName().endsWith("Async")) {
            methodName = clientMethod.getName().substring(0, clientMethod.getName().length() - "Async".length());
        } else {
            methodName = clientMethod.getName();
        }

        // Return type
        String returnType = "PageFlux<BinaryData>";

        // Annotation
        typeBlock.annotation("ServiceMethod(returns = ReturnType.COLLECTION)");

        // Arguments
        StringBuilder methodArgsDeclare = new StringBuilder();
        StringBuilder methodArgsInvoke = new StringBuilder();
        for (ProxyMethodParameter p : clientMethod.getProxyMethod().getParameters()) {
            if (p.getIsRequired() && !p.getFromClient() && !p.getIsConstant()) {
                IType clientType = p.getClientType();
                if (clientType instanceof ClassType || clientType instanceof EnumType) {
                    clientType = ClassType.String;
                }
                if (p.getRequestParameterLocation() == RequestParameterLocation.Body) {
                    clientType = ClassType.BinaryData;
                }
                methodArgsDeclare.append(clientType).append(" ").append(p.getName()).append(", ");
                methodArgsInvoke.append(p.getName()).append(", ");
            }
        }
        methodArgsDeclare.append("RequestOptions options");
        methodArgsInvoke.append("options");

        JavaVisibility visibility = JavaVisibility.Public;

        // TODO: unknown function
        if (JavaSettings.getInstance().isContextClientMethodParameter()) {
            visibility = JavaVisibility.PackagePrivate;
            methodArgsInvoke.append(", c");

            // Actual method without context param
            typeBlock.publicMethod(String.format("%s %s(%s)", returnType, methodName, methodArgsDeclare), methodBlock -> {
                methodBlock.methodReturn(String.format("FluxUtil.withContext(c -> %s(%s))", methodName, methodArgsInvoke));
            });
            methodArgsDeclare.append(", Context context");
        }

        String methodSignature = String.format("%s %s(%s)", returnType, methodName, methodArgsDeclare);
        typeBlock.method(visibility, null, methodSignature, methodBlock -> {
            // Method body
            if (clientMethod.getMethodPageDetails().nonNullNextLink()) {
                // nextLink exists
                methodBlock.line("return new PagedFlux<>(");
                methodBlock.indent(() -> {
                    methodBlock.line("() -> %s(%s),",
                            clientMethod.getProxyMethod().getPagingAsyncSinglePageMethodName(),
                            clientMethod.getArgumentList());
                    methodBlock.line("nextLink -> %s(%s));",
                            clientMethod.getMethodPageDetails().getNextMethod().getProxyMethod().getPagingAsyncSinglePageMethodName(),
                            clientMethod.getMethodPageDetails().getNextMethod().getArgumentList());
                });
            } else {
                // No nextLink
                methodBlock.line("return new PagedFlux<>(");
                methodBlock.indent(() -> {
                    methodBlock.line("() -> %s(%s));",
                            clientMethod.getProxyMethod().getPagingAsyncSinglePageMethodName(),
                            clientMethod.getArgumentList());
                });
            }
        });
    }
}
