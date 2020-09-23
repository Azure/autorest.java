package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.OperationGroup;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.Constructor;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.Proxy;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.ServiceClientProperty;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.CodeNamer;

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

        ServiceClient.Builder builder = new ServiceClient.Builder();

        String serviceClientInterfaceName = (settings.getClientTypePrefix() == null ? "" : settings.getClientTypePrefix())
                + codeModel.getLanguage().getJava().getName();

        String serviceClientClassName = serviceClientInterfaceName;
        if (settings.shouldGenerateClientAsImpl()) {
            serviceClientClassName += "Impl";
        }
        String packageName = ClientModelUtil.getServiceClientPackageName(serviceClientClassName);
        builder.interfaceName(serviceClientInterfaceName)
                .className(serviceClientClassName)
                .packageName(packageName);

        List<Operation> codeModelRestAPIMethods = codeModel.getOperationGroups().stream()
                .filter(og -> og.getLanguage().getJava().getName() == null || og.getLanguage().getJava().getName().isEmpty())
                .flatMap(og -> og.getOperations().stream())
                .collect(Collectors.toList());
        if (!codeModelRestAPIMethods.isEmpty()) {
            // TODO: Assume all operations share the same base url
            Proxy.Builder proxyBuilder = new Proxy.Builder()
                    .name(serviceClientInterfaceName + "Service")
                    .clientTypeName(serviceClientInterfaceName)
                    .baseURL(codeModel.getOperationGroups().stream()
                        .filter(og -> og.getLanguage().getJava().getName() == null || og.getLanguage().getJava().getName().isEmpty())
                        .map(og -> og.getOperations().get(0))
                        .findFirst().get().getRequests().get(0)
                        .getProtocol().getHttp().getUri());
            List<ProxyMethod> restAPIMethods = new ArrayList<>();
            for (Operation codeModelRestAPIMethod : codeModelRestAPIMethods) {
                restAPIMethods.addAll(Mappers.getProxyMethodMapper().map(codeModelRestAPIMethod).values());
            }
            proxyBuilder.methods(restAPIMethods);
            builder.proxy(proxyBuilder.build());
            builder.clientMethods(codeModelRestAPIMethods.stream()
                    .flatMap(m -> Mappers.getClientMethodMapper().map(m).stream())
                    .collect(Collectors.toList()));
        } else {
            builder.clientMethods(new ArrayList<>());
        }

        List<MethodGroupClient> serviceClientMethodGroupClients = new ArrayList<>();
        List<OperationGroup> codeModelMethodGroups = codeModel.getOperationGroups().stream()
                .filter(og -> og.getLanguage().getJava().getName() != null && !og.getLanguage().getJava().getName().isEmpty())
                .collect(Collectors.toList());
        for (OperationGroup codeModelMethodGroup : codeModelMethodGroups) {
            serviceClientMethodGroupClients.add(Mappers.getMethodGroupMapper().map(codeModelMethodGroup));
        }
        builder.methodGroupClients(serviceClientMethodGroupClients);

        boolean usesCredentials = false;

        List<ServiceClientProperty> serviceClientProperties = new ArrayList<>();
        for (Parameter p : Stream.concat(codeModel.getGlobalParameters().stream(),
                codeModel.getOperationGroups().stream()
                        .flatMap(og -> og.getOperations().stream())
                        .flatMap(o -> o.getRequests().stream())
                        .flatMap(r -> r.getParameters().stream()))
                .filter(p -> p.getImplementation() == Parameter.ImplementationLocation.CLIENT)
                .distinct()
                .collect(Collectors.toList())) {
            String serviceClientPropertyDescription = p.getDescription() != null ? p.getDescription() : p.getLanguage().getJava().getDescription();

            String serviceClientPropertyName = CodeNamer.getPropertyName(p.getLanguage().getJava().getName());

            IType serviceClientPropertyClientType = Mappers.getSchemaMapper().map(p.getSchema());
            if (p.isNullable() && serviceClientPropertyClientType != null) {
                serviceClientPropertyClientType = serviceClientPropertyClientType.asNullable();
            }

            boolean serviceClientPropertyIsReadOnly = p.getSchema() instanceof ConstantSchema;

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
        serviceClientProperties.add(new ServiceClientProperty("The HTTP pipeline to send requests through.",
                ClassType.HttpPipeline, "httpPipeline", true, null));
        serviceClientProperties.add(new ServiceClientProperty("The serializer to serialize an object into a string.",
                ClassType.SerializerAdapter, "serializerAdapter", true, null,
                settings.isFluent() ? JavaVisibility.PackagePrivate : JavaVisibility.Public));
        if (settings.isFluent()) {
            serviceClientProperties.add(new ServiceClientProperty("The default poll interval for long-running operation.",
                    ClassType.Duration, "defaultPollInterval", true, null));
        }

        builder.properties(serviceClientProperties);

        ClientMethodParameter tokenCredentialParameter = new ClientMethodParameter.Builder()
                .description("the credentials for Azure")
                .isFinal(false)
                .wireType(ClassType.TokenCredential)
                .name("credential")
                .isRequired(true)
                .isConstant(false)
                .fromClient(true)
                .defaultValue(null)
                .annotations(JavaSettings.getInstance().shouldNonNullAnnotations()
                        ? Arrays.asList(ClassType.NonNull)
                        : new ArrayList<>())
                .build();

        ClientMethodParameter httpPipelineParameter = new ClientMethodParameter.Builder()
                .description("The HTTP pipeline to send requests through")
                .isFinal(false)
                .wireType(ClassType.HttpPipeline)
                .name("httpPipeline")
                .isRequired(true)
                .isConstant(false)
                .fromClient(true)
                .defaultValue(null)
                .annotations(JavaSettings.getInstance().shouldNonNullAnnotations()
                        ? Arrays.asList(ClassType.NonNull)
                        : new ArrayList<>())
                .build();

        ClientMethodParameter serializerAdapterParameter = new ClientMethodParameter.Builder()
                .description("The serializer to serialize an object into a string")
                .isFinal(false)
                .wireType(ClassType.SerializerAdapter)
                .name("serializerAdapter")
                .isRequired(true)
                .isConstant(false)
                .fromClient(true)
                .defaultValue(null)
                .annotations(JavaSettings.getInstance().shouldNonNullAnnotations()
                        ? Arrays.asList(ClassType.NonNull)
                        : new ArrayList<>())
                .build();

        List<Constructor> serviceClientConstructors = new ArrayList<>();

        if (settings.isFluent()) {
            ClientMethodParameter azureEnvironmentParameter = new ClientMethodParameter.Builder()
                    .description("The Azure environment")
                    .isFinal(false)
                    .wireType(ClassType.AzureEnvironment)
                    .name("environment")
                    .isRequired(true)
                    .isConstant(false)
                    .fromClient(true)
                    .defaultValue("AzureEnvironment.AZURE")
                    .annotations(JavaSettings.getInstance().shouldNonNullAnnotations()
                            ? Arrays.asList(ClassType.NonNull)
                            : new ArrayList<>())
                    .build();

            ClientMethodParameter defaultPollIntervalParameter = new ClientMethodParameter.Builder()
                    .description("The default poll interval for long-running operation")
                    .isFinal(false)
                    .wireType(ClassType.Duration)
                    .name("defaultPollInterval")
                    .isRequired(true)
                    .isConstant(false)
                    .fromClient(true)
                    .defaultValue("Duration.ofSeconds(30)")
                    .annotations(JavaSettings.getInstance().shouldNonNullAnnotations()
                            ? Arrays.asList(ClassType.NonNull)
                            : new ArrayList<>())
                    .build();

            serviceClientConstructors.add(new Constructor(Arrays.asList(httpPipelineParameter, serializerAdapterParameter, defaultPollIntervalParameter, azureEnvironmentParameter)));
            builder.tokenCredentialParameter(tokenCredentialParameter)
                    .httpPipelineParameter(httpPipelineParameter)
                    .serializerAdapterParameter(serializerAdapterParameter)
                    .defaultPollIntervalParameter(defaultPollIntervalParameter)
                    .azureEnvironmentParameter(azureEnvironmentParameter)
                    .constructors(serviceClientConstructors);
        } else {
            serviceClientConstructors.add(new Constructor(new ArrayList<>()));
            serviceClientConstructors.add(new Constructor(Arrays.asList(httpPipelineParameter)));
            serviceClientConstructors.add(new Constructor(Arrays.asList(httpPipelineParameter, serializerAdapterParameter)));
            builder.tokenCredentialParameter(tokenCredentialParameter)
                    .httpPipelineParameter(httpPipelineParameter)
                    .serializerAdapterParameter(serializerAdapterParameter)
                    .constructors(serviceClientConstructors);
        }

        return builder.build();
    }
}
