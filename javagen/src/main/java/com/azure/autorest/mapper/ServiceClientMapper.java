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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServiceClientMapper implements IMapper<CodeModel, ServiceClient> {
    private static ServiceClientMapper instance = new ServiceClientMapper();

    protected ServiceClientMapper() {
    }

    public static ServiceClientMapper getInstance() {
        return instance;
    }

    @Override
    public ServiceClient map(CodeModel codeModel) {
        JavaSettings settings = JavaSettings.getInstance();

        ServiceClient.Builder builder = createClientBuilder();
        builder.builderDisabled(JavaSettings.getInstance().clientBuilderDisabled());

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

        Proxy proxy = null;
        if (!codeModelRestAPIMethods.isEmpty()) {
            // TODO: Assume all operations share the same base url
            Proxy.Builder proxyBuilder = getProxyBuilder()
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
            proxy = proxyBuilder.build();
            builder.proxy(proxy);
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
        List<Parameter> clientParameters = Stream.concat(codeModel.getGlobalParameters().stream(),
                codeModel.getOperationGroups().stream()
                        .flatMap(og -> og.getOperations().stream())
                        .flatMap(o -> o.getRequests().stream())
                        .flatMap(r -> r.getParameters().stream()))
                .filter(p -> p.getImplementation() == Parameter.ImplementationLocation.CLIENT)
                .distinct()
                .collect(Collectors.toList());
        for (Parameter p : clientParameters) {
            String serviceClientPropertyDescription = p.getDescription() != null ? p.getDescription() : p.getLanguage().getJava().getDescription();

            String serviceClientPropertyName = CodeNamer.getPropertyName(p.getLanguage().getJava().getName());

            String serviceClientPropertySerializedName = p.getLanguage().getJava().getSerializedName();

            IType serviceClientPropertyClientType = Mappers.getSchemaMapper().map(p.getSchema());
            if (p.isNullable() && serviceClientPropertyClientType != null) {
                serviceClientPropertyClientType = serviceClientPropertyClientType.asNullable();
            }

            boolean serviceClientPropertyIsReadOnly = p.getSchema() instanceof ConstantSchema;
            if (!settings.isFluent()) {
                serviceClientPropertyIsReadOnly = false;
            }
            String serviceClientPropertyDefaultValueExpression = serviceClientPropertyClientType.defaultValueExpression(ClientModelUtil.getClientDefaultValueOrConstantValue(p));

            if (settings.isLowLevelClient() && serviceClientPropertyName.equals("apiVersion")) {
                String serviceName;
                if (settings.getServiceName() == null) {
                    serviceName = serviceClientInterfaceName;
                } else {
                    serviceName = settings.getServiceName().replaceAll("\\s", "");
                }
                String enumTypeName = serviceName + (serviceName.endsWith("Service") ? "Version" : "ServiceVersion");
                serviceClientPropertyDescription = "Service version";
                serviceClientPropertyClientType = new ClassType.Builder()
                        .name(enumTypeName)
                        .packageName(settings.getPackage())
                        .build();
                serviceClientPropertyName = "serviceVersion";
                serviceClientPropertyIsReadOnly = false;
                serviceClientPropertyDefaultValueExpression = enumTypeName + ".getLatest()";
            }

            if (serviceClientPropertyClientType == ClassType.TokenCredential) {
                usesCredentials = true;
            } else {
                ServiceClientProperty serviceClientProperty =
                        new ServiceClientProperty(serviceClientPropertyDescription,
                                serviceClientPropertyClientType,
                                serviceClientPropertyName,
                                serviceClientPropertyIsReadOnly,
                                serviceClientPropertyDefaultValueExpression);
                if (!serviceClientProperties.contains(serviceClientProperty)) {
                    // Ignore duplicate client property.
                    serviceClientProperties.add(serviceClientProperty);
                }
            }
        }
        addHttpPipelineProperty(serviceClientProperties);
        addSerializerAdapterProperty(serviceClientProperties, settings);
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
                .wireType(getHttpPipelineClassType())
                .name("httpPipeline")
                .isRequired(true)
                .isConstant(false)
                .fromClient(true)
                .defaultValue(null)
                .annotations(JavaSettings.getInstance().shouldNonNullAnnotations()
                        ? Arrays.asList(ClassType.NonNull)
                        : new ArrayList<>())
                .build();

        ClientMethodParameter serializerAdapterParameter = createSerializerAdapterParameter();

        if (settings.getCredentialTypes().contains(JavaSettings.CredentialType.TOKEN_CREDENTIAL)) {
            Set<String> scopes = JavaSettings.getInstance().getCredentialScopes();
            String scopeParams;
            if (scopes != null && !scopes.isEmpty()) {
                scopeParams = "DEFAULT_SCOPES";
            } else {
                // Remove trailing / and all relative paths
                if (proxy == null) {
                    proxy = serviceClientMethodGroupClients.get(0).getProxy();
                }
                String host = proxy.getBaseURL().replaceAll("/+$", "").replaceAll("(?<!/)[/][^/]+", "");
                List<String> parameters = new ArrayList<>();
                int start = host.indexOf("{");
                while (start >= 0) {
                    int end = host.indexOf("}", start);
                    String serializedName = host.substring(start + 1, end);
                    Optional<Parameter> hostParam = clientParameters.stream().filter(p -> serializedName.equals(p.getLanguage().getJava().getSerializedName())).findFirst();
                    if (hostParam.isPresent()) {
                        parameters.add(hostParam.get().getLanguage().getJava().getName());
                        host = host.substring(0, start) + "%s" + host.substring(end + 1);
                    }
                    start = host.indexOf("{", start + 1);
                }
                if (parameters.isEmpty()) {
                    scopeParams = String.format("\"%s/.default\"", host);
                } else {
                    scopeParams = String.format("String.format(\"%s/.default\", %s)", host, String.join(", ", parameters));
                }
            }
            builder.defaultCredentialScopes(scopeParams);
        }

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

    protected Proxy.Builder getProxyBuilder() {
        return new Proxy.Builder();
    }

    protected ClientMethodParameter createSerializerAdapterParameter() {
        return  new ClientMethodParameter.Builder()
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
    }

    protected IType getHttpPipelineClassType() {
        return ClassType.HttpPipeline;
    }

    protected void addSerializerAdapterProperty(List<ServiceClientProperty> serviceClientProperties, com.azure.autorest.extension.base.plugin.JavaSettings settings) {
        serviceClientProperties.add(new ServiceClientProperty("The serializer to serialize an object into a string.",
                ClassType.SerializerAdapter, "serializerAdapter", true, null,
                settings.isFluent() ? JavaVisibility.PackagePrivate : JavaVisibility.Public));
    }

    protected void addHttpPipelineProperty(List<ServiceClientProperty> serviceClientProperties) {
        serviceClientProperties.add(new ServiceClientProperty("The HTTP pipeline to send requests through.",
                ClassType.HttpPipeline, "httpPipeline", true, null));
    }

    protected ServiceClient.Builder createClientBuilder() {
        return new ServiceClient.Builder();
    }
}
