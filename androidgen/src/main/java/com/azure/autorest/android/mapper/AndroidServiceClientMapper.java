// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.android.mapper;

import com.azure.autorest.android.model.clientmodel.AndroidProxy;
import com.azure.autorest.android.model.clientmodel.AndroidServiceClient;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.mapper.ServiceClientMapper;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.Proxy;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.ServiceClientProperty;
import com.azure.autorest.model.javamodel.JavaVisibility;

import java.util.List;

public class AndroidServiceClientMapper extends ServiceClientMapper {

    private static ServiceClientMapper instance = new AndroidServiceClientMapper();

    protected AndroidServiceClientMapper() {
    }

    public static ServiceClientMapper getInstance() {
        return instance;
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
        serviceClientProperties.add(new ServiceClientProperty("The HTTP pipeline to send requests through.",
                ClassType.AndroidHttpPipeline, "httpPipeline", true, null));
    }

    @Override
    protected void addSerializerAdapterProperty(List<ServiceClientProperty> serviceClientProperties, JavaSettings settings) {
        serviceClientProperties.add(new ServiceClientProperty("The serializer to serialize an object into a string.",
                ClassType.AndroidJacksonSerder, "jacksonSerder", true, null,
                settings.isFluent() ? JavaVisibility.PackagePrivate : JavaVisibility.Public));
    }

    @Override
    protected com.azure.autorest.model.clientmodel.IType getHttpPipelineClassType() {
        return ClassType.AndroidHttpPipeline;
    }

    @Override
    protected com.azure.autorest.model.clientmodel.ClientMethodParameter createSerializerAdapterParameter() {
        return  new com.azure.autorest.model.clientmodel.ClientMethodParameter.Builder()
                .description("The serializer to serialize an object into a string")
                .isFinal(false)
                .wireType(com.azure.autorest.model.clientmodel.ClassType.AndroidJacksonSerder)
                .name("jacksonSerder")
                .isRequired(true)
                .isConstant(false)
                .fromClient(true)
                .defaultValue(null)
                .annotations(JavaSettings.getInstance().shouldNonNullAnnotations()
                        ? java.util.Arrays.asList(ClassType.NonNull)
                        : new java.util.ArrayList<>())
                .build();
    }
}
