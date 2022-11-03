// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.cadl.mapper;

import com.azure.autorest.extension.base.model.codemodel.Client;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.Metadata;
import com.azure.autorest.extension.base.model.codemodel.OperationGroup;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.mapper.ServiceClientMapper;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.Proxy;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.core.util.CoreUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CadlServiceClientMapper extends ServiceClientMapper {

    public ServiceClient map(Client client, CodeModel codeModel) {
        ServiceClient.Builder builder = createClientBuilder();

        String baseName = getJavaName(client);
        String className = ClientModelUtil.getClientImplementClassName(baseName);
        String packageName = ClientModelUtil.getServiceClientPackageName(className);
        builder.interfaceName(baseName)
                .className(className)
                .packageName(packageName);

        Proxy proxy = null;
        OperationGroup clientOperationGroup = client.getOperationGroups().stream()
                .filter(og -> CoreUtils.isNullOrEmpty(getJavaName(og)))
                .findFirst().orElse(null);
        if (clientOperationGroup != null) {
            proxy = processClientOperations(builder, clientOperationGroup.getOperations(), baseName);
        } else {
            builder.clientMethods(Collections.emptyList());
        }

        List<MethodGroupClient> methodGroupClients = new ArrayList<>();
        client.getOperationGroups().stream()
                .filter(og -> !CoreUtils.isNullOrEmpty(getJavaName(og)))
                .forEach(og -> methodGroupClients.add(Mappers.getMethodGroupMapper().map(og)));
        builder.methodGroupClients(methodGroupClients);

        if (proxy == null) {
            proxy = methodGroupClients.iterator().next().getProxy();
        }

        // TODO (weidxu): security definition could be different for different client
        processParametersAndConstructors(builder, codeModel, baseName, proxy);

        return builder.build();
    }

    private static String getJavaName(Metadata m) {
        if (m.getLanguage() == null || m.getLanguage().getJava() == null) {
            return null;
        }
        return m.getLanguage().getJava().getName();
    }
}
