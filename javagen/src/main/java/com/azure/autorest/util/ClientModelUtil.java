// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

import com.azure.autorest.extension.base.model.codemodel.ApiVersion;
import com.azure.autorest.extension.base.model.codemodel.Client;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.extension.base.model.codemodel.OperationGroup;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ClientModelPropertyAccess;
import com.azure.autorest.model.clientmodel.ClientModelPropertyReference;
import com.azure.autorest.model.clientmodel.ClientModels;
import com.azure.autorest.model.clientmodel.ConvenienceMethod;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ImplementationDetails;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.core.util.CoreUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utilities for client model.
 */
public class ClientModelUtil {

    private static final Pattern SPACE = Pattern.compile("\\s");
    private static final Pattern SPLIT_FLATTEN_PROPERTY_PATTERN = Pattern.compile("((?<!\\\\))\\.");

    /**
     * Prepare async/sync clients for service client.
     *
     * @param serviceClient the service client.
     * @param asyncClients output, the async clients.
     * @param syncClients output, the sync client.
     */
    public static void getAsyncSyncClients(Client client, ServiceClient serviceClient,
                                           List<AsyncSyncClient> asyncClients, List<AsyncSyncClient> syncClients) {
        String packageName = getAsyncSyncClientPackageName(serviceClient);
        boolean generateAsyncMethods = JavaSettings.getInstance().isGenerateAsyncMethods();
        boolean generateSyncMethods = JavaSettings.getInstance().isGenerateSyncMethods();

        if (serviceClient.getProxy() != null) {
            AsyncSyncClient.Builder builder = new AsyncSyncClient.Builder()
                    .packageName(packageName)
                    .serviceClient(serviceClient);

            final List<ConvenienceMethod> convenienceMethods = client.getOperationGroups().stream()
                    .filter(og -> CoreUtils.isNullOrEmpty(og.getLanguage().getJava().getName()))    // no resource group
                    .findAny()
                    .map(og -> getConvenienceMethods(serviceClient::getClientMethods, og))
                    .orElse(Collections.emptyList());
            builder.convenienceMethods(convenienceMethods);

            if (generateAsyncMethods) {
                String asyncClassName = clientNameToAsyncClientName(serviceClient.getClientBaseName());
                asyncClients.add(builder.className(asyncClassName).build());
            }

            if (generateSyncMethods) {
                String syncClassName =
                        serviceClient.getClientBaseName().endsWith("Client")
                                ? serviceClient.getClientBaseName()
                                : serviceClient.getClientBaseName() + "Client";
                syncClients.add(builder.className(syncClassName).build());
            }
        }

        final int count = serviceClient.getMethodGroupClients().size() + asyncClients.size();
        for (MethodGroupClient methodGroupClient : serviceClient.getMethodGroupClients()) {
            AsyncSyncClient.Builder builder = new AsyncSyncClient.Builder()
                    .packageName(packageName)
                    .serviceClient(serviceClient)
                    .methodGroupClient(methodGroupClient);

            final List<ConvenienceMethod> convenienceMethods = client.getOperationGroups().stream()
                    .filter(og -> methodGroupClient.getClassBaseName().equals(og.getLanguage().getJava().getName()))
                    .findAny()
                    .map(og -> getConvenienceMethods(methodGroupClient::getClientMethods, og))
                    .orElse(Collections.emptyList());
            builder.convenienceMethods(convenienceMethods);

            if (count == 1) {
                // if it is the only method group, use service client name as base.

                if (generateAsyncMethods) {
                    String asyncClassName = clientNameToAsyncClientName(serviceClient.getClientBaseName());
                    asyncClients.add(builder.className(asyncClassName).build());
                }

                if (generateSyncMethods) {
                    String syncClassName =
                            serviceClient.getClientBaseName().endsWith("Client")
                                    ? serviceClient.getClientBaseName()
                                    : serviceClient.getClientBaseName() + "Client";
                    syncClients.add(builder.className(syncClassName).build());
                }
            } else {
                if (generateAsyncMethods) {
                    String asyncClassName = clientNameToAsyncClientName(methodGroupClient.getClassBaseName());
                    asyncClients.add(builder.className(asyncClassName).build());
                }

                if (generateSyncMethods) {
                    String syncClassName =
                            methodGroupClient.getClassBaseName().endsWith("Client")
                                    ? methodGroupClient.getClassBaseName()
                                    : methodGroupClient.getClassBaseName() + "Client";
                    syncClients.add(builder.className(syncClassName).build());
                }
            }
        }
    }

    private static List<ConvenienceMethod> getConvenienceMethods(Supplier<List<ClientMethod>> clientMethods, OperationGroup og) {
        return og.getOperations().stream()
                .filter(o -> o.getConvenienceApi() != null)
                .flatMap(o -> {
                    List<ClientMethod> cMethods = Mappers.getClientMethodMapper().map(o, false)
                            .stream()
                            .filter(m -> m.getMethodVisibility() == JavaVisibility.Public)
                            .collect(Collectors.toList());
                    if (!cMethods.isEmpty()) {
                        // sync stack generates additional proxy methods with name suffix "Sync"
                        String proxyMethodBaseName = cMethods.iterator().next().getProxyMethod().getBaseName();
                        return clientMethods.get().stream()
                                .filter(m ->
                                        proxyMethodBaseName.equals(m.getProxyMethod().getBaseName())
                                                && m.getMethodVisibility() == JavaVisibility.Public)
                                .map(m -> new ConvenienceMethod(m, cMethods));
                    } else {
                        return Stream.empty();
                    }
                }).collect(Collectors.toList());
    }

    /**
     * @param codeModel the code model
     * @return the interface name of service client.
     */
    public static String getClientInterfaceName(Client codeModel) {
        JavaSettings settings = JavaSettings.getInstance();
        String serviceClientInterfaceName = (settings.getClientTypePrefix() == null ? "" : settings.getClientTypePrefix())
                + codeModel.getLanguage().getJava().getName();
        if (settings.isDataPlaneClient()) {
            // mandate ending Client for LLC
            if (!serviceClientInterfaceName.endsWith("Client")) {
                String serviceName = settings.getServiceName();
                if (serviceName != null && codeModel instanceof CodeModel) {
                    serviceName = SPACE.matcher(serviceName).replaceAll("");
                    serviceClientInterfaceName = serviceName.endsWith("Client") ? serviceName : (serviceName + "Client");
                } else {
                    serviceClientInterfaceName += "Client";
                }
            }
        }
        return serviceClientInterfaceName;
    }

    /**
     * @param codeModel the code model
     * @return the class name of service client implementation.
     */
    public static String getClientImplementClassName(Client codeModel) {
        String serviceClientInterfaceName = getClientInterfaceName(codeModel);
        return getClientImplementClassName(serviceClientInterfaceName);
    }

    /**
     * @param serviceClientInterfaceName the interface name of service client
     * @return the class name of service client implementation.
     */
    public static String getClientImplementClassName(String serviceClientInterfaceName) {
        JavaSettings settings = JavaSettings.getInstance();
        String serviceClientClassName = serviceClientInterfaceName;
        if (settings.isGenerateClientAsImpl()) {
            serviceClientClassName += "Impl";
        }
        return serviceClientClassName;
    }

    /**
     * @param serviceClientInterfaceName the interface name of service client
     * @return the class name of service version.
     */
    public static String getServiceVersionClassName(String serviceClientInterfaceName) {
        JavaSettings settings = JavaSettings.getInstance();
        String serviceName;
        if (settings.getServiceName() == null) {
            if (serviceClientInterfaceName.endsWith("Client")) {
                // remove ending Client
                serviceName = serviceClientInterfaceName.substring(0, serviceClientInterfaceName.length() - "Client".length());
            } else {
                serviceName = serviceClientInterfaceName;
            }
        } else {
            serviceName = SPACE.matcher(settings.getServiceName()).replaceAll("");
        }
        return serviceName + (serviceName.endsWith("Service") ? "Version" : "ServiceVersion");
    }

    /**
     * Gets the suffix of the builder class.
     * <p>
     * The class name of the Builder is usually the service client interface name + builder suffix.
     *
     * @return the suffix of the builder class.
     */
    public static String getBuilderSuffix() {
        JavaSettings settings = JavaSettings.getInstance();
        StringBuilder builderSuffix = new StringBuilder();
        if (!settings.isFluent()
                && settings.isGenerateClientAsImpl()
                && !settings.isGenerateSyncAsyncClients()
                && !settings.isDataPlaneClient()) {
            builderSuffix.append("Impl");
        }
        builderSuffix.append("Builder");
        return builderSuffix.toString();
    }

    public static String getServiceClientBuilderPackageName(ServiceClient serviceClient) {
        JavaSettings settings = JavaSettings.getInstance();
        String builderPackage = serviceClient.getPackage();
        if ((settings.isGenerateSyncAsyncClients() || settings.isDataPlaneClient()) && !settings.isFluent()) {
            builderPackage = settings.getPackage();
        } else if (settings.isFluent()) {
            builderPackage = settings.getPackage(settings.getImplementationSubpackage());
        }
        return builderPackage;
    }

    public static String getServiceClientPackageName(String serviceClientClassName) {
        JavaSettings settings = JavaSettings.getInstance();
        String subpackage = settings.isGenerateClientAsImpl() ? settings.getImplementationSubpackage() : null;
        if (settings.isFluent()) {
            if (settings.isGenerateSyncAsyncClients() || settings.isGenerateClientInterfaces()) {
                subpackage = settings.getImplementationSubpackage();
            } else {
                subpackage = settings.getFluentSubpackage();
            }
        }
        if (settings.isCustomType(serviceClientClassName)) {
            subpackage = settings.getCustomTypesSubpackage();
        }
        return settings.getPackage(subpackage);
    }

    public static String getAsyncSyncClientPackageName(ServiceClient serviceClient) {
        JavaSettings settings = JavaSettings.getInstance();
        if (settings.isFluent()) {
            return settings.getPackage(settings.getFluentSubpackage());
        } else {
            return getServiceClientBuilderPackageName(serviceClient);
        }
    }

    public static String getServiceClientInterfacePackageName() {
        JavaSettings settings = JavaSettings.getInstance();
        if (settings.isFluent()) {
            return settings.getPackage(settings.getFluentSubpackage());
        } else {
            return settings.getPackage();
        }
    }

    public static String getClientDefaultValueOrConstantValue(Parameter parameter) {
        String clientDefaultValueOrConstantValue = parameter.getClientDefaultValue();
        if (clientDefaultValueOrConstantValue == null) {
            if (parameter.getSchema() != null && parameter.getSchema() instanceof ConstantSchema) {
                ConstantSchema constantSchema = (ConstantSchema) parameter.getSchema();
                if (constantSchema.getValue() != null) {
                    clientDefaultValueOrConstantValue = constantSchema.getValue().getValue().toString();
                }
            }
        }
        return clientDefaultValueOrConstantValue;
    }

    private static String getFirstApiVersionFromOperation(CodeModel codeModel) {
        return codeModel.getOperationGroups().stream()
                .flatMap(og -> og.getOperations().stream())
                .filter(o -> o.getApiVersions() != null)
                .flatMap(o -> o.getApiVersions().stream())
                .filter(Objects::nonNull)
                .map(ApiVersion::getVersion)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    public static List<String> getApiVersions(CodeModel codeModel) {
        List<String> versions = codeModel.getClients().stream()
                .filter(c -> !CoreUtils.isNullOrEmpty(c.getApiVersions()))
                .map(c -> c.getApiVersions().stream().map(ApiVersion::getVersion).collect(Collectors.toList()))
                .findFirst().orElse(null);
        if (versions == null) {
            String version = getFirstApiVersionFromOperation(codeModel);
            if (version != null) {
                versions = Collections.singletonList(version);
            } else {
                versions = Collections.emptyList();
            }
        }
        return versions;
    }

    public static String getArtifactId() {
        JavaSettings settings = JavaSettings.getInstance();
        String artifactId = settings.getArtifactId();
        if (settings.isDataPlaneClient() && CoreUtils.isNullOrEmpty(artifactId)) {
            // convert package/namespace to artifact
            artifactId = settings.getPackage().toLowerCase(Locale.ROOT)
                    .replace("com.", "")
                    .replace(".", "-");
        }
        return artifactId;
    }

    public static String clientNameToAsyncClientName(String clientName) {
        if (clientName.endsWith("Client")) {
            clientName = clientName.substring(0, clientName.length() - "Client".length()) + "AsyncClient";
        } else {
            clientName += "AsyncClient";
        }
        return clientName;
    }

    /**
     * Split and unescape the possible flattened serialized property name to its components.
     *
     * @param serializedName the serialized property name belongs to either model or property that has {@code @JsonFlatten} annotation.
     * @return the components of the serialized property names
     */
    public static List<String> splitFlattenedSerializedName(String serializedName) {
        if (serializedName == null) {
            return Collections.emptyList();
        }

        String[] values = SPLIT_FLATTEN_PROPERTY_PATTERN.split(serializedName);
        for (int i = 0; i < values.length; ++i) {
            values[i] = values[i].replace("\\\\.", ".");
        }
        return Arrays.asList(values);
    }

    private static Function<String, ClientModel> getClientModelFunction = name -> ClientModels.getInstance().getModel(name);

    /**
     * Replace the default function of getting ClientModel by name.
     * <p>
     * Used in Fluent for providing additional ClientModel that exists in azure-core-management,
     * e.g. Resource, ManagementError
     *
     * @param function the function of getting ClientModel by name
     */
    public static void setGetClientModelFunction(Function<String, ClientModel> function) {
        getClientModelFunction = function;
    }

    /**
     * Get ClientModel by name.
     *
     * @param name the name of the ClientModel (without package)
     * @return the ClientModel instance. <code>null</code> if not found.
     */
    public static ClientModel getClientModel(String name) {
        return getClientModelFunction.apply(name);
    }

    /**
     * Check if the type is a ClientModel.
     *
     * @param type the type
     * @return whether the type is a ClientModel.
     */
    public static boolean isClientModel(IType type) {
        if (type instanceof ClassType) {
            ClassType classType = (ClassType) type;
            return classType.getPackage().startsWith(JavaSettings.getInstance().getPackage())
                    && getClientModel(classType.getName()) != null;
        } else {
            return false;
        }
    }

    /**
     * Check if the type is an external model.
     *
     * @param type the type
     * @return whether the type is an external model.
     */
    public static boolean isExternalModel(IType type) {
        if (type instanceof ClassType) {
            ClassType classType = (ClassType) type;
            ClientModel model = getClientModel(classType.getName());
            return model != null && model.getImplementationDetails() != null && model.getImplementationDetails().getUsages() != null
                    && model.getImplementationDetails().getUsages().contains(ImplementationDetails.Usage.EXTERNAL);
        } else {
            return false;
        }
    }

    /**
     * Gets all parent properties.
     *
     * @param model The client model.
     * @return Returns all properties that are defined by super types of the client model.
     */
    public static List<ClientModelProperty> getParentProperties(ClientModel model) {
        String lastParentName = model.getName();
        ClientModel parentModel = getClientModel(model.getParentModelName());
        List<ClientModelProperty> parentProperties = new ArrayList<>();
        while (parentModel != null && !lastParentName.equals(parentModel.getName())) {
            // Add the properties in inverse order as they be reverse at the end.
            List<ClientModelProperty> parentProps = new ArrayList<>(parentModel.getProperties());
            for (int i = parentProps.size() - 1; i >= 0; i--) {
                parentProperties.add(parentProps.get(i));
            }

            lastParentName = parentModel.getName();
            parentModel = getClientModel(parentModel.getParentModelName());
        }
        Collections.reverse(parentProperties);
        return parentProperties;
    }

    public static List<ClientModelProperty> getRequiredWritableParentProperties(ClientModel model) {
        String lastParentName = model.getName();
        ClientModel parentModel = getClientModel(model.getParentModelName());
        List<ClientModelProperty> requiredParentProperties = new ArrayList<>();
        while (parentModel != null && !lastParentName.equals(parentModel.getName())) {
            // Add the properties in inverse order as they be reverse at the end.
            List<ClientModelProperty> ctorArgs = parentModel.getProperties().stream()
                .filter(property -> property.isRequired() && !property.isConstant() && !property.isReadOnly())
                .collect(Collectors.toList());

            for (int i = ctorArgs.size() - 1; i >= 0; i--) {
                requiredParentProperties.add(ctorArgs.get(i));
            }

            lastParentName = parentModel.getName();
            parentModel = getClientModel(parentModel.getParentModelName());
        }
        Collections.reverse(requiredParentProperties);
        return requiredParentProperties;
    }

    /**
     * Indicates whether the property will have a setter method generated for it.
     *
     * @param property The client model property, or a reference.
     * @param settings Autorest generation settings.
     * @return Whether the property will have a setter method.
     */
    public static boolean hasSetter(ClientModelPropertyAccess property, JavaSettings settings) {
        // If the property isn't read-only or required and part of the constructor, and it isn't private,
        // add a setter.
        return !isReadOnlyOrInConstructor(property, settings) && !isPrivateAccess(property);
    }

    // A property has private access when it is to be flattened.
    // Only applies to mgmt, no effect on vanilla or DPG.
    private static boolean isPrivateAccess(ClientModelPropertyAccess property) {
        boolean privateAccess = false;
        // ClientModelPropertyReference never refers to a private access property, so only check ClientModelProperty here.
        if (property instanceof ClientModelProperty) {
            privateAccess = ((ClientModelProperty) property).getClientFlatten();
        }
        return privateAccess;
    }

    private static boolean isReadOnlyOrInConstructor(ClientModelPropertyAccess property, JavaSettings settings) {
        return property.isReadOnly() || (settings.isRequiredFieldsAsConstructorArgs() && property.isRequired());
    }

    /**
     * Determines whether the {@link ClientModelProperty} should be included in the model's constructor.
     * <p>
     * {@link ClientModelProperty Properties} are included in the constructor if the following hold true
     * <ul>
     * <li>{@link ClientModelProperty#isRequired()} is true</li>
     * <li>{@link JavaSettings#isRequiredFieldsAsConstructorArgs()} is true</li>
     * <li>{@link ClientModelProperty#isReadOnly()} is false or {@link JavaSettings#isIncludeReadOnlyInConstructorArgs()} is true</li>
     * </ul>
     *
     * @param property The {@link ClientModelProperty}
     * @param settings The Autorest generation settings.
     * @return Whether the {@code property} should be included in the model's constructor.
     */
    public static boolean includePropertyInConstructor(ClientModelProperty property, JavaSettings settings) {
        return property.isRequired() && settings.isRequiredFieldsAsConstructorArgs()
            && (!property.isReadOnly() || settings.isIncludeReadOnlyInConstructorArgs());
    }

    /**
     * Checks whether wire type and client type mismatch on this client model property.
     *
     * @param clientModelProperty the client model property.
     * @param ignoreGenericType whether to ignore the mismatch, if both wire type and client type is generic type.
     *                          <p>For example, ignore the case of {@code List<OffsetDateTime>} vs {@code List<Long>}.
     * @return whether wire type and client type mismatch.
     */
    public static boolean isWireTypeMismatch(ClientModelProperty clientModelProperty, boolean ignoreGenericType) {
        if (clientModelProperty.getClientType() == clientModelProperty.getWireType()) {
            // same type
            return false;
        } else {
            // type mismatch
            if (ignoreGenericType
                    && clientModelProperty.getClientType() instanceof GenericType
                    && clientModelProperty.getWireType() instanceof GenericType) {
                // at present, ignore generic type, as type erasure causes conflict of 2 constructors
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * Checks where {@code CoreToCodegenBridgeUtils} should be generated.
     * <p>
     * At this time it is required if {@link JavaSettings#isStreamStyleSerialization()} is true and the model uses
     * either {@code ResponseError} or {@link Duration} as both types have special serialization requirements that
     * aren't exposed by azure-core.
     *
     * @param model the model
     * @param settings Java settings
     * @return Whether to generate the {@code CoreToCodegenBridgeUtils} utility class.
     */
    public static boolean generateCoreToCodegenBridgeUtils(ClientModel model, JavaSettings settings) {
        if (!settings.isStreamStyleSerialization()) {
            return false;
        }

        boolean ret = model.getProperties().stream()
            .anyMatch(p -> p.getClientType() == ClassType.ResponseError || p.getClientType() == ClassType.Duration);

        // flatten properties
        if (!ret && settings.getClientFlattenAnnotationTarget() == JavaSettings.ClientFlattenAnnotationTarget.NONE) {
            ret = model.getPropertyReferences().stream()
                .filter(ClientModelPropertyReference::isFromFlattenedProperty)
                .anyMatch(p -> p.getClientType() == ClassType.ResponseError || p.getClientType() == ClassType.Duration);
        }

        return ret;
    }
}
