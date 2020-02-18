// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import java.util.List;
import java.util.Set;


/**
 * Details that describe the dynamic proxy.
 */
public class Proxy {
    /**
     * Get the name of the REST API interface.
     */
    private String name;
    /**
     * Get the name of the method group.
     */
    private String clientTypeName;
    /**
     * Get the base URL that will be used for each REST API method.
     */
    private String baseURL;
    /**
     * Get the methods of this REST API.
     */
    private List<ProxyMethod> methods;

    /**
     * Create a new Proxy using the provided properties.
     * @param name The name of the REST API interface.
     * @param clientTypeName The name of the method group.
     * @param baseURL The base URL that will be used for each REST API method.
     * @param methods The methods of this REST API.
     */
    public Proxy(String name, String clientTypeName, String baseURL, List<ProxyMethod> methods) {
        this.name = name;
        this.clientTypeName = clientTypeName;
        this.baseURL = baseURL;
        this.methods = methods;
    }

    public final String getName() {
        return name;
    }

    public final String getClientTypeName() {
        return clientTypeName;
    }

    public final String getBaseURL() {
        return baseURL;
    }

    public final List<ProxyMethod> getMethods() {
        return methods;
    }

    /**
     * Add this property's imports to the provided ISet of imports.
     * @param imports The set of imports to add to.
     * @param includeImplementationImports Whether or not to include imports that are only necessary for method implementations.
     */
    public final void addImportsTo(Set<String> imports, boolean includeImplementationImports, JavaSettings settings) {
        if (includeImplementationImports) {
            imports.add("com.azure.core.annotation.Host");
            imports.add("com.azure.core.annotation.ServiceInterface");
        }

        for (ProxyMethod method : getMethods()) {
            method.addImportsTo(imports, includeImplementationImports, settings);
        }
    }
}
