/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.examplemodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.model.FluentType;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class FluentClientMethodExample implements FluentMethodExample {

    private final String name;
    private final MethodGroupClient methodGroup;
    private final ClientMethod clientMethod;
    private final List<ParameterExample> parameters = new ArrayList<>();

    public FluentClientMethodExample(String name, MethodGroupClient methodGroup, ClientMethod clientMethod) {
        this.name = name;
        this.methodGroup = methodGroup;
        this.clientMethod = clientMethod;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public ClassType getEntryType() {
        return FluentType.AzureResourceManager;
    }

    @Override
    public String getEntryName() {
        return "azure";
    }

    @Override
    public String getEntryDescription() {
        return "The entry point for accessing resource management APIs in Azure.";
    }

    @Override
    public List<ParameterExample> getParameters() {
        return parameters;
    }

    @Override
    public String getMethodReference() {
        String namespace = JavaSettings.getInstance().getPackage();
        String[] identifiers = namespace.split(Pattern.quote("."));
        String lastIdentifier = identifiers[identifiers.length - 1];

        if (!MANAGER_REFERENCE.containsKey(lastIdentifier)) {
            throw new IllegalStateException("Package '" + namespace + "' is not supported by Fluent Premium");
        }

        String managerReference = MANAGER_REFERENCE.get(lastIdentifier) + "()";
        String serviceClientReference = ModelNaming.METHOD_SERVICE_CLIENT + "()";   // TODO resources contains multiple service client reference
        String methodGroupReference =  "get" + CodeNamer.toPascalCase(methodGroup.getVariableName()) + "()";
        return managerReference + "." + serviceClientReference + "." + methodGroupReference;
    }

    @Override
    public String getMethodName() {
        return clientMethod.getName();
    }

    private final static Map<String, String> MANAGER_REFERENCE = new HashMap<>();
    static {
        MANAGER_REFERENCE.put("containerservice", "kubernetesClusters");
    }
}
