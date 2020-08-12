package com.azure.autorest.android.mapper;

import com.azure.autorest.android.model.AndroidProxy;
import com.azure.autorest.android.model.AndroidServiceClient;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.OperationGroup;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.mapper.ServiceClientMapper;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.Constructor;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.Proxy;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.ServiceClientProperty;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AndroidServiceClientMapper extends ServiceClientMapper {
    private static AndroidServiceClientMapper instance = new AndroidServiceClientMapper();

    public static AndroidServiceClientMapper getInstance() {
        return instance;
    }

    protected ServiceClient.Builder createClientMethodBuilder() {
        return new AndroidServiceClient.Builder();
    }

    @Override
    public ServiceClient map(CodeModel codeModel) {
        JavaSettings settings = JavaSettings.getInstance();

        ServiceClient.Builder builder = createClientMethodBuilder();

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
            Proxy.Builder proxyBuilder = new AndroidProxy.Builder()
                    .name(serviceClientInterfaceName + "Service")
                    .clientTypeName(serviceClientInterfaceName)
                    .baseURL(codeModel.getOperationGroups().stream()
                            .filter(og -> og.getLanguage().getJava().getName() == null || og.getLanguage().getJava().getName().isEmpty())
                            .map(og -> og.getOperations().get(0))
                            .findFirst().get().getRequests().get(0)
                            .getProtocol().getHttp().getUri());
            //
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

            String serviceClientPropertyDefaultValueExpression
                    = serviceClientPropertyClientType.defaultValueExpression(p.getClientDefaultValue());

            if (serviceClientPropertyClientType != ClassType.TokenCredential) {
                ServiceClientProperty serviceClientProperty
                        = new ServiceClientProperty(serviceClientPropertyDescription,
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
        serviceClientProperties.add(new ServiceClientProperty("The ServiceClient to use for REST calls", ClassType.AndroidServiceClient, "serviceClient", true, null));
        builder.properties(serviceClientProperties);

        ClientMethodParameter serviceClientParameter = new ClientMethodParameter.Builder()
                .description("The ServiceClient to use for REST calls")
                .isFinal(false)
                .wireType(ClassType.AndroidServiceClient)
                .name("serviceClient")
                .isRequired(true)
                .isConstant(false)
                .fromClient(true)
                .defaultValue(null)
                .annotations(JavaSettings.getInstance().shouldNonNullAnnotations()
                        ? Arrays.asList(ClassType.NonNull)
                        : new ArrayList<>())
                .build();

        List<Constructor> serviceClientConstructors = new ArrayList<>();
        serviceClientConstructors.add(new Constructor(Arrays.asList(serviceClientParameter)));
        builder.httpPipelineParameter(serviceClientParameter)
                .constructors(serviceClientConstructors);

        return builder.build();

//        ClientMethodParameter tokenCredentialParameter = new ClientMethodParameter.Builder()
//                .description("the credentials for Azure")
//                .isFinal(false)
//                .wireType(ClassType.TokenCredential)
//                .name("credential")
//                .isRequired(true)
//                .isConstant(false)
//                .fromClient(true)
//                .defaultValue(null)
//                .annotations(JavaSettings.getInstance().shouldNonNullAnnotations()
//                        ? Arrays.asList(ClassType.NonNull)
//                        : new ArrayList<>())
//                .build();
//        builder.tokenCredentialParameter(tokenCredentialParameter)
//                .httpPipelineParameter(httpPipelineParameter)
//                .constructors(serviceClientConstructors);
    }
}
