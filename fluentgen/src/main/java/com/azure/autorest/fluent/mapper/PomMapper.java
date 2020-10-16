/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.fluent.model.clientmodel.FluentClient;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.model.clientmodel.Pom;

import java.util.Arrays;
import java.util.Locale;

public class PomMapper {

    public Pom map(CodeModel codeModel, FluentClient fluentClient) {
        String clientName = Utils.getJavaName(codeModel);
        String serviceName = FluentUtils.getServiceName(clientName);

        Pom pom = new Pom();
        pom.setGroupId("com.azure.resourcemanager");
        pom.setArtifactId(String.format("azure-resourcemanager-%1$s-generated", serviceName.toLowerCase(Locale.ROOT)));
        pom.setVersion("1.0.0-beta");

        pom.setServiceName(serviceName);
        pom.setServiceDescription(fluentClient.getInnerClient().getClientDescription());

        pom.setDependencyIdentifiers(Arrays.asList(
                "com.azure:azure-core-management:1.0.0"
        ));

        return pom;
    }
}
