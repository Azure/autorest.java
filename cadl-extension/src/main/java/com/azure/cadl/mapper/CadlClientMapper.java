// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.cadl.mapper;

import com.azure.autorest.extension.base.model.codemodel.Client;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.ClientTrait;
import com.azure.autorest.mapper.ClientMapper;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientResponse;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.cadl.util.ModelUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CadlClientMapper extends ClientMapper {

    private static final ClientMapper INSTANCE = new CadlClientMapper();

    public static ClientMapper getInstance() {
        return INSTANCE;
    }

    protected CadlClientMapper() {
    }

    @Override
    protected Map<ServiceClient, ClientTrait> processClients(List<Client> clients, CodeModel codeModel) {
        Map<ServiceClient, ClientTrait> serviceClientsMap = new LinkedHashMap<>();
        CadlServiceClientMapper mapper = new CadlServiceClientMapper();
        for (Client client : clients) {
            serviceClientsMap.put(mapper.map(client, codeModel), client);
        }
        return serviceClientsMap;
    }

    @Override
    protected List<String> getModelsPackages(List<ClientModel> clientModels, List<EnumType> enumTypes, List<ClientResponse> responseModels) {

        Set<String> packages = clientModels.stream()
                .filter(ModelUtil::isGeneratingModel)
                .map(ClientModel::getPackage)
                .collect(Collectors.toSet());

        packages.addAll(enumTypes.stream()
                .filter(ModelUtil::isGeneratingModel)
                .map(EnumType::getPackage)
                .collect(Collectors.toSet()));

        packages.addAll(responseModels.stream()
                .filter(ModelUtil::isGeneratingModel)
                .map(ClientResponse::getPackage)
                .collect(Collectors.toSet()));

        return new ArrayList<>(packages);
    }
}
