// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GraalVmConfig {

    private List<String> proxies = new ArrayList<>();
    private List<String> reflects = new ArrayList<>();
    private List<String> resources = new ArrayList<>();

    private GraalVmConfig() {

    }

    public List<String> getProxies() {
        return proxies;
    }

    public List<String> getReflects() {
        return reflects;
    }

    public List<String> getResources() {
        return resources;
    }

    // TODO: Builder
    public static GraalVmConfig fromClient(Collection<ClientModel> models, Collection<ServiceClient> serviceClients) {
        GraalVmConfig result = new GraalVmConfig();
        result.reflects = models.stream()
                .map(m -> m.getPackage() + "." + m.getName())
                .collect(Collectors.toList());
        // TODO: include Proxy from ServiceClient
        result.proxies = serviceClients.stream()
                .flatMap(sc -> {
                    if (sc.getMethodGroupClients() != null) {
                        return sc.getMethodGroupClients().stream();
                    } else {
                        return Stream.empty();
                    }
                })
                .map(m -> m.getPackage() + "." + m.getClassName() + "$" + m.getProxy().getName())
                .collect(Collectors.toList());
        return result;
    }
}
