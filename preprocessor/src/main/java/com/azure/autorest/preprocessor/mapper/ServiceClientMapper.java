package com.azure.autorest.preprocessor.mapper;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.OperationGroup;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.preprocessor.model.clientmodel.ClassType;
import com.azure.autorest.preprocessor.model.clientmodel.ClientMethod;
import com.azure.autorest.preprocessor.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.preprocessor.namer.CodeNamer;
import com.azure.autorest.preprocessor.model.clientmodel.Constructor;
import com.azure.autorest.preprocessor.model.clientmodel.IType;
import com.azure.autorest.preprocessor.model.clientmodel.MethodGroupClient;
import com.azure.autorest.preprocessor.model.clientmodel.Proxy;
import com.azure.autorest.preprocessor.model.clientmodel.ProxyMethod;
import com.azure.autorest.preprocessor.model.clientmodel.ServiceClient;
import com.azure.autorest.preprocessor.model.clientmodel.ServiceClientProperty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServiceClientMapper implements IMapper<CodeModel, ServiceClient> {
    private static ServiceClientMapper instance = new ServiceClientMapper();

    private ServiceClientMapper() {
    }

    public static ServiceClientMapper getInstance() {
        return instance;
    }

    @Override
    public ServiceClient map(CodeModel codeModel) {
        JavaSettings settings = JavaSettings.getInstance();

        String serviceClientInterfaceName = (settings.getClientTypePrefix() == null ? "" : settings.getClientTypePrefix())
                + codeModel.getLanguage().getJava().getName();

        String serviceClientClassName = serviceClientInterfaceName;
        if (settings.shouldGenerateClientAsImpl()) {
            serviceClientClassName += "Impl";
        }
        String subpackage = settings.shouldGenerateClientAsImpl() ? settings.getImplementationSubpackage() : null;
        if (settings.isCustomType(serviceClientClassName)) {
            subpackage = settings.getCustomTypesSubpackage();
        }
        String packageName = settings.getPackage(subpackage);

        Proxy serviceClientRestAPI = null;
        List<ClientMethod> serviceClientMethods = new ArrayList<>();
        List<Operation> codeModelRestAPIMethods = codeModel.getOperationGroups().stream()
                .filter(og -> og.getLanguage().getJava().getName() == null || og.getLanguage().getJava().getName().isEmpty())
                .flatMap(og -> og.getOperations().stream())
                .collect(Collectors.toList());
        if (!codeModelRestAPIMethods.isEmpty()) {
            String restAPIName = serviceClientInterfaceName + "Service";
            // TODO: Assume all operations share the same base url
            String proxyBaseUrl = codeModel.getOperationGroups().stream()
                    .filter(og -> og.getLanguage().getJava().getName() == null || og.getLanguage().getJava().getName().isEmpty())
                    .map(og -> og.getOperations().get(0))
                    .findFirst().get().getRequest()
                    .getProtocol().getHttp().getUri();
            List<ProxyMethod> restAPIMethods = new ArrayList<>();
            for (Operation codeModelRestAPIMethod : codeModelRestAPIMethods) {
                ProxyMethod restAPIMethod = Mappers.getProxyMethodMapper().map(codeModelRestAPIMethod);
                restAPIMethods.add(restAPIMethod);
            }
            serviceClientRestAPI = new Proxy(restAPIName, serviceClientInterfaceName, proxyBaseUrl, restAPIMethods);
            serviceClientMethods = codeModelRestAPIMethods.stream()
                    .flatMap(m -> Mappers.getClientMethodMapper().map(m).stream())
                    .collect(Collectors.toList());
        }

        List<MethodGroupClient> serviceClientMethodGroupClients = new ArrayList<>();
        List<OperationGroup> codeModelMethodGroups = codeModel.getOperationGroups().stream()
                .filter(og -> og.getLanguage().getJava().getName() != null && !og.getLanguage().getJava().getName().isEmpty())
                .collect(Collectors.toList());
        for (OperationGroup codeModelMethodGroup : codeModelMethodGroups) {
            serviceClientMethodGroupClients.add(Mappers.getMethodGroupMapper().map(codeModelMethodGroup));
        }

        boolean usesCredentials = false;

        List<ServiceClientProperty> serviceClientProperties = new ArrayList<>();
        for (Parameter p : Stream.concat(codeModel.getGlobalParameters().stream(),
                codeModel.getOperationGroups().stream()
                        .flatMap(og -> og.getOperations().stream())
                        .flatMap(o -> o.getRequest().getParameters().stream()))
                .filter(p -> p.getImplementation() == Parameter.ImplementationLocation.CLIENT)
                .distinct()
                .collect(Collectors.toList())) {
            String serviceClientPropertyDescription = p.getDescription() != null ? p.getDescription() : p.getLanguage().getJava().getDescription();

            String serviceClientPropertyName = CodeNamer.getPropertyName(p.getLanguage().getJava().getName());

            IType serviceClientPropertyClientType = Mappers.getSchemaMapper().map(p.getSchema());
            if (p.isNullable() && serviceClientPropertyClientType != null) {
                serviceClientPropertyClientType = serviceClientPropertyClientType.asNullable();
            }

            boolean serviceClientPropertyIsReadOnly = serviceClientPropertyClientType instanceof ConstantSchema;

            String serviceClientPropertyDefaultValueExpression = serviceClientPropertyClientType.defaultValueExpression(p.getClientDefaultValue());

            if (serviceClientPropertyClientType == ClassType.TokenCredential) {
                usesCredentials = true;
            } else {
                ServiceClientProperty serviceClientProperty = new ServiceClientProperty(serviceClientPropertyDescription, serviceClientPropertyClientType, serviceClientPropertyName, serviceClientPropertyIsReadOnly, serviceClientPropertyDefaultValueExpression);
                if (!serviceClientProperties.contains(serviceClientProperty)) {
                    // Ignore duplicate client property.
                    serviceClientProperties.add(serviceClientProperty);
                }
            }
        }
        serviceClientProperties.add(new ServiceClientProperty("The HTTP pipeline to send requests through", ClassType.HttpPipeline, "httpPipeline", true, null));

        ClientMethodParameter tokenCredentialParameter = new ClientMethodParameter(
                "the credentials for Azure",
                false,
                ClassType.TokenCredential,
                "credential",
                true,
                false,
                true,
                null,
                JavaSettings.getInstance().shouldNonNullAnnotations()
                        ? Arrays.asList(ClassType.NonNull)
                        : new ArrayList<>());

        ClientMethodParameter httpPipelineParameter = new ClientMethodParameter(
                "The HTTP pipeline to send requests through",
                false,
                ClassType.HttpPipeline,
                "httpPipeline",
                true,
                false,
                true,
                null,
                JavaSettings.getInstance().shouldNonNullAnnotations()
                        ? Arrays.asList(ClassType.NonNull)
                        : new ArrayList<>());

        ClientMethodParameter azureEnvironmentParameter = new ClientMethodParameter(
                "The Azure environment",
                false,
                ClassType.AzureEnvironment,
                "environment",
                true,
                false,
                true,
                "AzureEnvironment.AZURE",
                JavaSettings.getInstance().shouldNonNullAnnotations()
                        ? Arrays.asList(ClassType.NonNull)
                        : new ArrayList<>());

        List<Constructor> serviceClientConstructors = new ArrayList<>();
        String constructorDescription = String.format("Initializes an instance of %s client.", serviceClientInterfaceName);
        // TODO: Azure Fluent
        if (settings.isAzureOrFluent())
        {
//            if (usesCredentials)
//            {
//                serviceClientConstructors.add(new Constructor(codeModel.ServiceClientCredentialsParameter.Value));
//                serviceClientConstructors.add(new Constructor(codeModel.ServiceClientCredentialsParameter.Value, codeModel.AzureEnvironmentParameter.Value));
//            }
//            else
//            {
//                serviceClientConstructors.add(new Constructor());
//                serviceClientConstructors.add(new Constructor(codeModel.AzureEnvironmentParameter.Value));
//            }

            serviceClientConstructors.add(new Constructor(new ArrayList<>()));
            serviceClientConstructors.add(new Constructor(Arrays.asList(httpPipelineParameter)));
            serviceClientConstructors.add(new Constructor(Arrays.asList(httpPipelineParameter, azureEnvironmentParameter)));
        }
        else
        {
            serviceClientConstructors.add(new Constructor(new ArrayList<>()));
            serviceClientConstructors.add(new Constructor(Arrays.asList(httpPipelineParameter)));
        }

        return new ServiceClient(
                packageName,
                serviceClientClassName,
                serviceClientInterfaceName,
                serviceClientRestAPI,
                serviceClientMethodGroupClients,
                serviceClientProperties,
                serviceClientConstructors,
                serviceClientMethods,
                azureEnvironmentParameter,
                tokenCredentialParameter,
                httpPipelineParameter);
    }
}
