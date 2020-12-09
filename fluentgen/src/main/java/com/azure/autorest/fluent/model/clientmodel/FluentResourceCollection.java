/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.model.WellKnownMethodName;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.ResourceCreate;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.delete.ResourceDelete;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.get.ResourceRefresh;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.update.ResourceUpdate;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.template.prototype.MethodTemplate;
import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Model for Azure resource collection.
 */
// Fluent resource collection API. E.g. StorageAccounts.
public class FluentResourceCollection {

    // implementation client. E.g. StorageAccountsClientImpl.
    private final MethodGroupClient groupClient;

    // class type for inner client. E.g. StorageAccountsClient (which is a layer over StorageAccountsClientImpl).
    private final ClassType innerClientType;

    // class type for interface and implementation
    private final ClassType interfaceType;
    private final ClassType implementationType;

    // API methods
    private final List<FluentCollectionMethod> methods = new ArrayList<>();

    // resource models
    private final List<ResourceCreate> resourceCreates = new ArrayList<>();
    private final List<ResourceUpdate> resourceUpdates = new ArrayList<>();
    private final List<ResourceRefresh> resourceRefreshes = new ArrayList<>();
    private final List<ResourceDelete> resourceDeletes = new ArrayList<>();
    private final List<MethodTemplate> additionalMethods = new ArrayList<>();

    public FluentResourceCollection(MethodGroupClient groupClient) {
        JavaSettings settings = JavaSettings.getInstance();

        this.groupClient = groupClient;

        String baseClassName = CodeNamer.getPlural(groupClient.getClassBaseName());

        this.interfaceType = new ClassType.Builder()
                .packageName(settings.getPackage(settings.getModelsSubpackage()))
                .name(baseClassName)
                .build();
        this.implementationType = new ClassType.Builder()
                .packageName(settings.getPackage(settings.getImplementationSubpackage()))
                .name(baseClassName + ModelNaming.COLLECTION_IMPL_SUFFIX)
                .build();

        this.innerClientType = new ClassType.Builder()
                .packageName(settings.getPackage(settings.getFluentSubpackage()))
                .name(groupClient.getInterfaceName())
                .build();

        Set<String> existingMethodNames = this.groupClient.getClientMethods().stream()
                .map(ClientMethod::getName)
                .collect(Collectors.toSet());

        this.methods.addAll(this.groupClient.getClientMethods().stream()
                .filter(m -> m.getType() == ClientMethodType.SimpleSync
                        || m.getType() == ClientMethodType.PagingSync
                        || m.getType() == ClientMethodType.LongRunningSync
                        || m.getType() == ClientMethodType.SimpleSyncRestResponse)
                .map(m -> {
                    // map "delete" in client to "deleteByResourceGroup" in collection
                    if (WellKnownMethodName.DELETE.getMethodName().equals(m.getName())
                            && (m.getType() == ClientMethodType.SimpleSync || m.getType() == ClientMethodType.LongRunningSync)
                            && !existingMethodNames.contains(WellKnownMethodName.DELETE_BY_RESOURCE_GROUP.getMethodName())
                            && m.getMethodParameters().size() == 2 && m.getMethodParameters().stream().allMatch(p -> p.getClientType() == ClassType.String)) {
                        FluentCollectionMethod method = new FluentCollectionMethod(m, WellKnownMethodName.DELETE_BY_RESOURCE_GROUP.getMethodName());
                        existingMethodNames.add(method.getMethodName());
                        return method;
                    } else if ((WellKnownMethodName.DELETE.getMethodName() + Utils.METHOD_POSTFIX_WITH_RESPONSE).equals(m.getName())
                            && m.getType() == ClientMethodType.SimpleSyncRestResponse
                            && !existingMethodNames.contains((WellKnownMethodName.DELETE.getMethodName() + Utils.METHOD_POSTFIX_WITH_RESPONSE))
                            && m.getMethodParameters().size() == 3 && m.getMethodParameters().stream().limit(2).allMatch(p -> p.getClientType() == ClassType.String)) {
                        FluentCollectionMethod method = new FluentCollectionMethod(m, WellKnownMethodName.DELETE_BY_RESOURCE_GROUP.getMethodName() + Utils.METHOD_POSTFIX_WITH_RESPONSE);
                        existingMethodNames.add(method.getMethodName());
                        return method;
                    } else {
                        return new FluentCollectionMethod(m);
                    }
                })
                .collect(Collectors.toList()));
    }

    public MethodGroupClient getInnerGroupClient() {
        return groupClient;
    }

    public ClassType getInterfaceType() {
        return interfaceType;
    }

    public ClassType getImplementationType() {
        return implementationType;
    }

    public List<FluentCollectionMethod> getMethodsForTemplate() {
        List<FluentCollectionMethod> fluentMethods = new ArrayList<>(methods);

        Set<FluentCollectionMethod> excludeMethods = new HashSet<>();
        excludeMethods.addAll(this.getResourceCreates().stream().flatMap(rc -> rc.getMethodReferences().stream()).collect(Collectors.toSet()));
        excludeMethods.addAll(this.getResourceUpdates().stream().flatMap(ru -> ru.getMethodReferences().stream()).collect(Collectors.toSet()));
        fluentMethods.removeAll(excludeMethods);

        return fluentMethods;
    }

    public List<FluentCollectionMethod> getMethods() {
        return this.methods;
    }

    public String getDescription() {
        return String.format("Resource collection API of %s.", interfaceType.getName());
    }

    public ClassType getInnerClientType() {
        return innerClientType;
    }

    // method signature for inner client
    public String getInnerMethodSignature() {
        return String.format("%1$s %2$s()", this.getInnerClientType().getName(), FluentUtils.getGetterName(ModelNaming.METHOD_SERVICE_CLIENT));
    }

    public List<ResourceCreate> getResourceCreates() {
        return resourceCreates;
    }

    public List<ResourceUpdate> getResourceUpdates() {
        return resourceUpdates;
    }

    public List<ResourceRefresh> getResourceGets() {
        return resourceRefreshes;
    }

    public List<ResourceDelete> getResourceDeletes() {
        return resourceDeletes;
    }

    public List<MethodTemplate> getAdditionalMethods() {
        return additionalMethods;
    }

    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        innerClientType.addImportsTo(imports, false);

        this.getMethods().forEach(m -> m.addImportsTo(imports, includeImplementationImports));

        if (includeImplementationImports) {
            interfaceType.addImportsTo(imports, false);
        }

        additionalMethods.forEach(m -> m.addImportsTo(imports));
    }
}
