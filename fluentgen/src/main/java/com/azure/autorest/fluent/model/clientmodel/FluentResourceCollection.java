/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.MethodGroupClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private final List<FluentCollectionMethod> methods = new ArrayList<>();;

    public FluentResourceCollection(MethodGroupClient groupClient) {
        JavaSettings settings = JavaSettings.getInstance();

        this.groupClient = groupClient;

        this.interfaceType = new ClassType.Builder()
                .packageName(settings.getPackage(settings.getModelsSubpackage()))
                .name(groupClient.getInterfaceName())
                .build();
        this.implementationType = new ClassType.Builder()
                .packageName(settings.getPackage(settings.getImplementationSubpackage()))
                .name(groupClient.getInterfaceName() + ModelNaming.COLLECTION_IMPL_SUFFIX)
                .build();

        this.innerClientType = new ClassType.Builder()
                .packageName(settings.getPackage())
                .name(groupClient.getClassBaseName() + "Client")
                .build();

        this.methods.addAll(this.groupClient.getClientMethods().stream()
                .filter(m -> m.getType() == ClientMethodType.SimpleSync
                        || m.getType() == ClientMethodType.PagingSync
                        || m.getType() == ClientMethodType.LongRunningSync)
                .map(FluentCollectionMethod::new)
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

    public Collection<FluentCollectionMethod> getMethods() {
        return methods;
    }

    public String getDescription() {
        return String.format("Resource collection API of %s.", interfaceType.getName());
    }

    public ClassType getInnerClientType() {
        return innerClientType;
    }

    // method signature for inner client
    public String getInnerMethodSignature() {
        return String.format("%1$s %2$s()", this.getInnerClientType().getName(), FluentUtils.getGetterName(ModelNaming.METHOD_INNER));
    }

    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        innerClientType.addImportsTo(imports, false);

        this.getMethods().forEach(m -> m.addImportsTo(imports, includeImplementationImports));

        if (includeImplementationImports) {
            interfaceType.addImportsTo(imports, false);
        }
    }
}
