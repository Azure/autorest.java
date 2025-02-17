// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.autorest.fluent.provisioning;

import com.microsoft.provisioning.http.client.generator.provisioning.model.Specification;
import com.microsoft.typespec.http.client.generator.core.extension.jsonrpc.Connection;
import com.microsoft.typespec.http.client.generator.core.extension.plugin.JavaSettings;
import com.microsoft.typespec.http.client.generator.mgmt.FluentGen;
import com.microsoft.typespec.http.client.generator.mgmt.model.clientmodel.FluentStatic;
import com.microsoft.typespec.http.client.generator.mgmt.model.javamodel.FluentJavaPackage;

public class ProvisioningGen extends FluentGen {
    public ProvisioningGen(Connection connection, String plugin, String sessionId) {
        super(connection, plugin, sessionId);
    }

    @Override
    protected void writeFiles(FluentJavaPackage javaPackage) {
        if (JavaSettings.getInstance().isProvisioning()) {
            Specification specification
                = new Specification("azure-provisioning-keyvault", JavaSettings.getInstance().getPackage(),
                JavaSettings.getInstance().getAutorestSettings().getOutputFolder(), FluentStatic.getFluentClient().getResourceModels()) {
                @Override
                protected void customize() {

                }
            };
            specification.build();
        } else {
            super.writeFiles(javaPackage);
        }
    }
}
