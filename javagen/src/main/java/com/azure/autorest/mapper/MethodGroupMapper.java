package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.OperationGroup;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientModels;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.Proxy;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodGroupMapper implements IMapper<OperationGroup, MethodGroupClient> {
    private static MethodGroupMapper instance = new MethodGroupMapper();
    private Map<OperationGroup, MethodGroupClient> parsed = new HashMap<>();

    private MethodGroupMapper() {
    }

    public static MethodGroupMapper getInstance() {
        return instance;
    }

    @Override
    public MethodGroupClient map(OperationGroup methodGroup) {
        JavaSettings settings = JavaSettings.getInstance();
        if (parsed.containsKey(methodGroup)) {
            return parsed.get(methodGroup);
        }

        String interfaceName = methodGroup.getLanguage().getJava().getName();
        if (ClientModels.Instance.getTypes().stream().anyMatch(cm -> cm.getName().equals(methodGroup.getLanguage().getJava().getName()))) {
            interfaceName += "Operations";
        }
        String className = interfaceName;
        if (settings.isFluent()) {
            className += "Inner";
        } else if (settings.shouldGenerateClientAsImpl()) {
            className += "Impl";
        }

        String restAPIName = CodeNamer.toPascalCase(methodGroup.getLanguage().getJava().getName());
        if (!restAPIName.endsWith("s")) {
            restAPIName += 's';
        }
        restAPIName += "Service";
        String serviceClientName = methodGroup.getCodeModel().getLanguage().getJava().getName();

        // TODO: Assume all operations share the same base url
        String proxyBaseUrl = methodGroup.getOperations().get(0).getRequest()
                .getProtocol().getHttp().getUri();

        List<ProxyMethod> restAPIMethods = new ArrayList<>();
        for (Operation method : methodGroup.getOperations()) {
            // azure-core does not support OPTIONS HTTP method.
            // https://github.com/Azure/autorest.java/issues/453
            if (!"options".equals(method.getRequest().getProtocol().getHttp().getMethod())) {
                restAPIMethods.add(Mappers.getProxyMethodMapper().map(method));
            }
        }
        Proxy proxy = new Proxy(restAPIName, serviceClientName + interfaceName, proxyBaseUrl, restAPIMethods);

        List<String> implementedInterfaces = new ArrayList<>();
        if (!settings.isFluent() && settings.shouldGenerateClientInterfaces()) {
            implementedInterfaces.add(interfaceName);
        }

        String variableType = settings.shouldGenerateClientInterfaces() ? interfaceName : className;
        String variableName = CodeNamer.toCamelCase(interfaceName);

        if (settings.shouldGenerateClientAsImpl()) {
            serviceClientName += "Impl";
        }

        boolean isCustomType = settings.isCustomType(className);
        String packageName = settings.getPackage(isCustomType ? settings.getCustomTypesSubpackage() : (settings.shouldGenerateClientAsImpl() ? settings.getImplementationSubpackage() : null));

        List<ClientMethod> clientMethods = new ArrayList<>();
        for (Operation operation : methodGroup.getOperations()) {
            // "options" is not supported in HttpMethod in azure-core
            // https://github.com/Azure/autorest.java/issues/453
            if (!"options".equals(operation.getRequest().getProtocol().getHttp().getMethod())) {
                clientMethods.addAll(Mappers.getClientMethodMapper().map(operation));
            }
        }

        return new MethodGroupClient(
                packageName,
                className,
                interfaceName,
                implementedInterfaces,
                proxy,
                serviceClientName,
                variableType,
                variableName,
                clientMethods);
    }
}
