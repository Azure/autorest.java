// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper.android;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.mapper.ServiceClientMapper;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.Proxy;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.ServiceClientProperty;
import com.azure.autorest.model.clientmodel.android.AndroidProxy;
import com.azure.autorest.model.clientmodel.android.AndroidServiceClient;
import com.azure.autorest.model.javamodel.JavaVisibility;

import java.util.List;

public class AndroidServiceClientMapper extends ServiceClientMapper {

    private static final ServiceClientMapper INSTANCE = new AndroidServiceClientMapper();

    protected AndroidServiceClientMapper() {
    }

    public static ServiceClientMapper getInstance() {
        return INSTANCE;
    }

    @Override
    protected ServiceClient.Builder createClientBuilder() {
        return new AndroidServiceClient.Builder();
    }

    @Override
    protected Proxy.Builder getProxyBuilder() {
        return new AndroidProxy.Builder();
    }

    @Override
    protected void addHttpPipelineProperty(List<ServiceClientProperty> serviceClientProperties) {
        serviceClientProperties.add(
            new ServiceClientProperty("The HTTP pipeline to send requests through.", ClassType.ANDROID_HTTP_PIPELINE,
                "httpPipeline", true, null));
    }

    @Override
    protected void addSerializerAdapterProperty(List<ServiceClientProperty> serviceClientProperties,
        JavaSettings settings) {
        serviceClientProperties.add(new ServiceClientProperty("The serializer to serialize an object into a string.",
            ClassType.ANDROID_JACKSON_SERDER, "jacksonSerder", true, null,
            settings.isFluent() ? JavaVisibility.PackagePrivate : JavaVisibility.Public));
    }

    @Override
    protected com.azure.autorest.model.clientmodel.IType getHttpPipelineClassType() {
        return ClassType.ANDROID_HTTP_PIPELINE;
    }

    @Override
    protected com.azure.autorest.model.clientmodel.ClientMethodParameter createSerializerAdapterParameter() {
        return new com.azure.autorest.model.clientmodel.ClientMethodParameter.Builder().description(
                "The serializer to serialize an object into a string")
            .finalParameter(false)
            .wireType(ClassType.ANDROID_JACKSON_SERDER)
            .name("jacksonSerder")
            .required(true)
            .constant(false)
            .fromClient(true)
            .defaultValue(null)
            .annotations(new java.util.ArrayList<>())
            .build();
    }
}
