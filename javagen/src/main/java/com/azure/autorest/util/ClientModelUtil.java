// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

import com.azure.autorest.extension.base.model.codemodel.ApiVersion;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ClientModels;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.core.util.CoreUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    public static void getAsyncSyncClients(ServiceClient serviceClient,
                                           List<AsyncSyncClient> asyncClients, List<AsyncSyncClient> syncClients) {
        String packageName = getAsyncSyncClientPackageName(serviceClient);

        if (serviceClient.getProxy() != null) {
            AsyncSyncClient.Builder builder = new AsyncSyncClient.Builder()
                    .packageName(packageName)
                    .serviceClient(serviceClient);

            String asyncClassName = clientNameToAsyncClientName(serviceClient.getClientBaseName());
            asyncClients.add(builder.className(asyncClassName).build());

            if (JavaSettings.SyncMethodsGeneration.ALL.equals(JavaSettings.getInstance().getSyncMethods())) {
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

            if (count == 1) {
                // if it is the only method group, use service client name as base.

                String asyncClassName = clientNameToAsyncClientName(serviceClient.getClientBaseName());
                asyncClients.add(builder.className(asyncClassName).build());

                if (JavaSettings.SyncMethodsGeneration.ALL.equals(JavaSettings.getInstance().getSyncMethods())) {
                    String syncClassName =
                            serviceClient.getClientBaseName().endsWith("Client")
                                    ? serviceClient.getClientBaseName()
                                    : serviceClient.getClientBaseName() + "Client";
                    syncClients.add(builder.className(syncClassName).build());
                }
            } else {
                String asyncClassName = clientNameToAsyncClientName(methodGroupClient.getClassBaseName());
                asyncClients.add(builder.className(asyncClassName).build());

                if (JavaSettings.SyncMethodsGeneration.ALL.equals(JavaSettings.getInstance().getSyncMethods())) {
                    String syncClassName =
                            methodGroupClient.getClassBaseName().endsWith("Client")
                                    ? methodGroupClient.getClassBaseName()
                                    : methodGroupClient.getClassBaseName() + "Client";
                    syncClients.add(builder.className(syncClassName).build());
                }
            }
        }
    }

    /**
     * @param codeModel the code model
     * @return the interface name of service client.
     */
    public static String getClientInterfaceName(CodeModel codeModel) {
        JavaSettings settings = JavaSettings.getInstance();
        String serviceClientInterfaceName = (settings.getClientTypePrefix() == null ? "" : settings.getClientTypePrefix())
                + codeModel.getLanguage().getJava().getName();
        if (settings.isDataPlaneClient()) {
            // mandate ending Client for LLC
            if (!serviceClientInterfaceName.endsWith("Client")) {
                String serviceName = settings.getServiceName();
                if (serviceName != null) {
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
    public static String getClientImplementClassName(CodeModel codeModel) {
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
        if (settings.shouldGenerateClientAsImpl()) {
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
     *
     * The class name of the Builder is usually the service client interface name + builder suffix.
     *
     * @return the suffix of the builder class.
     */
    public static String getBuilderSuffix() {
        JavaSettings settings = JavaSettings.getInstance();
        StringBuilder builderSuffix = new StringBuilder();
        if (!settings.isFluent()
                && settings.shouldGenerateClientAsImpl()
                && !settings.shouldGenerateSyncAsyncClients()
                && !settings.isDataPlaneClient()) {
            builderSuffix.append("Impl");
        }
        builderSuffix.append("Builder");
        return builderSuffix.toString();
    }

    public static String getServiceClientBuilderPackageName(ServiceClient serviceClient) {
        JavaSettings settings = JavaSettings.getInstance();
        String builderPackage = serviceClient.getPackage();
        if ((settings.shouldGenerateSyncAsyncClients() || settings.isDataPlaneClient()) && !settings.isFluent()) {
            builderPackage = settings.getPackage();
        } else if (settings.isFluent()) {
            builderPackage = settings.getPackage(settings.getImplementationSubpackage());
        }
        return builderPackage;
    }

    public static String getServiceClientPackageName(String serviceClientClassName) {
        JavaSettings settings = JavaSettings.getInstance();
        String subpackage = settings.shouldGenerateClientAsImpl() ? settings.getImplementationSubpackage() : null;
        if (settings.isFluent()) {
            if (settings.shouldGenerateSyncAsyncClients() || settings.shouldGenerateClientInterfaces()) {
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

    public static String getFirstApiVersion(CodeModel codeModel) {
        String apiVersion = codeModel.getOperationGroups().stream()
                .flatMap(og -> og.getOperations().stream())
                .filter(o -> o.getApiVersions() != null)
                .flatMap(o -> o.getApiVersions().stream())
                .filter(Objects::nonNull)
                .map(ApiVersion::getVersion)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
        return apiVersion;
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

    public static List<ClientModelProperty> getRequiredParentProperties(ClientModel model) {
        String lastParentName = model.getName();
        ClientModel parentModel = getClientModel(model.getParentModelName());
        List<ClientModelProperty> requiredParentProperties = new ArrayList<>();
        while (parentModel != null && !lastParentName.equals(parentModel.getName())) {
            List<ClientModelProperty> ctorArgs =
                    parentModel.getProperties().stream().filter(ClientModelProperty::isRequired)
                            .filter(property -> !property.getIsConstant())
                            .collect(Collectors.toList());
            // this will be reversed again, so, it will be in the right order if a
            // super class has multiple ctor args
            Collections.reverse(ctorArgs);
            requiredParentProperties.addAll(ctorArgs);

            lastParentName = parentModel.getName();
            parentModel = ClientModelUtil.getClientModel(parentModel.getParentModelName());
        }
        Collections.reverse(requiredParentProperties);
        return requiredParentProperties;
    }
}
