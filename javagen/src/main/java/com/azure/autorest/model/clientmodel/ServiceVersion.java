/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.model.clientmodel;

import java.util.List;

public class ServiceVersion {

    private final String className;
    private final String serviceName;
    private final List<String> serviceVersions;

    public ServiceVersion(String className, String serviceName, List<String> serviceVersions) {
        this.className = className;
        this.serviceName = serviceName;
        this.serviceVersions = serviceVersions;
    }

    public String getClassName() {
        return className;
    }

    public String getServiceName() {
        return serviceName;
    }

    public List<String> getServiceVersions() {
        return serviceVersions;
    }
}
