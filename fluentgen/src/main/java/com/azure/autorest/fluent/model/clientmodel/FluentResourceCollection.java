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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FluentResourceCollection {

    private final MethodGroupClient groupClient;

    private final ClassType innerClassType;
    private final ClassType collectionInterfaceClassType;
    private final ClassType collectionImplementationClassType;

    private final List<FluentCollectionMethod> methods = new ArrayList<>();;

    public FluentResourceCollection(MethodGroupClient groupClient) {
        JavaSettings settings = JavaSettings.getInstance();

        this.groupClient = groupClient;

        this.collectionInterfaceClassType = new ClassType.Builder()
                .packageName(settings.getPackage(settings.getModelsSubpackage()))
                .name(groupClient.getInterfaceName())
                .build();
        this.collectionImplementationClassType = new ClassType.Builder()
                .packageName(settings.getPackage(settings.getImplementationSubpackage()))
                .name(groupClient.getInterfaceName() + "Impl")
                .build();

        this.innerClassType = new ClassType.Builder()
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

    public ClassType getCollectionInterfaceClassType() {
        return collectionInterfaceClassType;
    }

    public ClassType getCollectionImplementationClassType() {
        return collectionImplementationClassType;
    }

    public List<FluentCollectionMethod> getMethods() {
        return methods;
    }

    public String getDescription() {
        return String.format("Resource collection API of %s.", collectionInterfaceClassType.getName());
    }

    public ClassType getInnerClassType() {
        return innerClassType;
    }

    public String getInnerMethodSignature() {
        return String.format("%1$s %2$s()", this.getInnerClassType().getName(), FluentUtils.getGetterName(ModelNaming.PROPERTY_INNER));
    }

    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        innerClassType.addImportsTo(imports, false);

        this.getMethods().forEach(m -> m.addImportsTo(imports, includeImplementationImports));

        if (includeImplementationImports) {
            collectionInterfaceClassType.addImportsTo(imports, false);
        }
    }
}
