package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientMethodType;
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
            if (!"$host".equals(parameter.getLanguage().getDefault().getName())
                    && parameter.getImplementation() != Parameter.ImplementationLocation.CLIENT) {
                parameters.add(Mappers.getClientParameterMapper().map(parameter));
            }
        }

        // WithResponseAsync, with required and optional parameters
        methods.add(new ClientMethod(
                operation.getDescription(),
                new ReturnValue(null, proxyMethod.getReturnType()),
                proxyMethod.getName() + "WithResponseAsync",
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

        return methods;
    }
}
