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
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.javamodel.JavaClass;

/**
 * Writes a protocol method to a JavaType block.
 */
public class ProtocolSyncMethodTemplate extends ProtocolMethodBaseTemplate {
    private static ProtocolSyncMethodTemplate _instance = new ProtocolSyncMethodTemplate();

    protected ProtocolSyncMethodTemplate() {
    }

    public static ProtocolSyncMethodTemplate getInstance() {
        return _instance;
    }

    public final void write(ClientMethod clientMethod, JavaClass typeBlock) {
        generateJavadoc(clientMethod, typeBlock);

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
                    clientType = ClassType.BinaryData;
                }
                methodArgsDeclare.append(clientType).append(" ").append(p.getName()).append(", ");
                methodArgsInvoke.append(p.getName()).append(", ");
            }
        }
        methodArgsDeclare.append("RequestOptions options");
        methodArgsInvoke.append("options");
        if (JavaSettings.getInstance().isContextClientMethodParameter()) {
            methodArgsDeclare.append(", Context context");
            methodArgsInvoke.append(", context");
        }
        String methodName;
        if (clientMethod.getName().endsWith("Async")) {
            methodName = clientMethod.getName().substring(0, clientMethod.getName().length() - "Async".length());
        } else {
            methodName = clientMethod.getName();
        }
        String returnType = "Response<BinaryData>";
        typeBlock.publicMethod(String.format("%s %s(%s)", returnType, methodName, methodArgsDeclare.toString()), methodBlock -> {
            methodBlock.methodReturn(String.format("asyncClient.%s(%s).block()", methodName, methodArgsInvoke.toString()));
        });
    }
}
