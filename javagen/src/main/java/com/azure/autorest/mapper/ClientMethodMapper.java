package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ReturnValue;

import java.util.ArrayList;
import java.util.List;

public class ClientMethodMapper implements IMapper<Operation, List<ClientMethod>> {
    private static ClientMethodMapper instance = new ClientMethodMapper();

    private ClientMethodMapper() {
    }

    public static ClientMethodMapper getInstance() {
        return instance;
    }

    @Override
    public List<ClientMethod> map(Operation operation) {
        JavaSettings settings = JavaSettings.getInstance();

        ProxyMethod proxyMethod = Mappers.getProxyMethodMapper().map(operation);

        List<ClientMethod> methods = new ArrayList<>();

        List<ClientMethodParameter> parameters = new ArrayList<>();
        for (Parameter parameter : operation.getRequest().getParameters()) {
            if (parameter.getImplementation() != Parameter.ImplementationLocation.CLIENT) {
                parameters.add(Mappers.getClientParameterMapper().map(parameter));
            }
        }

        // WithResponseAsync, with required and optional parameters
        methods.add(new ClientMethod(
                operation.getDescription(),
                new ReturnValue(null, proxyMethod.getReturnType().getClientType()),
                proxyMethod.getSimpleAsyncRestResponseMethodName(),
                parameters,
                false,
                ClientMethodType.SimpleAsyncRestResponse,
                proxyMethod,
                new ArrayList<>(),
                new ArrayList<>(),
                false,
                null,
                null,
                new ArrayList<>()));

        // Simple Async
        if (settings.getSyncMethods() != JavaSettings.SyncMethodsGeneration.NONE) {
            IType restAPIMethodReturnBodyClientType = proxyMethod.getReturnValueWireType().getClientType();
            IType asyncMethodReturnType;
            if (restAPIMethodReturnBodyClientType != PrimitiveType.Void)
            {
                asyncMethodReturnType = GenericType.Mono(restAPIMethodReturnBodyClientType);
            }
            else
            {
                asyncMethodReturnType = GenericType.Mono(ClassType.Void);
            }

            methods.add(new ClientMethod(
                    operation.getDescription(),
                    new ReturnValue(null, asyncMethodReturnType),
                    proxyMethod.getSimpleAsyncMethodName(),
                    parameters,
                    false,
                    ClientMethodType.SimpleAsync,
                    proxyMethod,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    false,
                    null,
                    null,
                    new ArrayList<>()));
        }

        // Sync
        if (settings.getSyncMethods() == JavaSettings.SyncMethodsGeneration.ALL) {
            methods.add(new ClientMethod(
                    operation.getDescription(),
                    new ReturnValue(null, proxyMethod.getReturnValueWireType().getClientType()),
                    proxyMethod.getName(),
                    parameters,
                    false,
                    ClientMethodType.SimpleSync,
                    proxyMethod,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    false,
                    null,
                    null,
                    new ArrayList<>()));
        }

        return methods;
    }
}
