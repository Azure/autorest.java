/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.fluent.model.ResourceTypeName;
import com.azure.autorest.fluent.model.arm.UrlPathSegments;
import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ResourceCreate {

    private final FluentResourceModel resourceModel;
    private final FluentResourceCollection resourceCollection;

    private final UrlPathSegments urlPathSegments;

    private final String methodName;
    private final List<FluentCollectionMethod> methodReferences = new ArrayList<>();

    private final ClientModel bodyParameterModel;

    public ResourceCreate(FluentResourceModel resourceModel, FluentResourceCollection resourceCollection, UrlPathSegments urlPathSegments, String methodName, ClientModel bodyParameterModel) {
        this.resourceModel = resourceModel;
        this.resourceCollection = resourceCollection;
        this.urlPathSegments = urlPathSegments;
        this.methodName = methodName;
        this.bodyParameterModel = bodyParameterModel;
    }

    public FluentResourceModel getResourceModel() {
        return resourceModel;
    }

    public FluentResourceCollection getResourceCollection() {
        return resourceCollection;
    }

    public UrlPathSegments getUrlPathSegments() {
        return urlPathSegments;
    }

    public String getMethodName() {
        return methodName;
    }

    public List<FluentCollectionMethod> getMethodReferences() {
        return methodReferences;
    }

    public boolean isHasResourceGroup() {
        return urlPathSegments.hasResourceGroup();
    }

    public boolean isHasLocation() {
        return resourceModel.hasProperty(ResourceTypeName.FIELD_LOCATION)
                && resourceModel.getProperty(ResourceTypeName.FIELD_LOCATION).getFluentType() == ClassType.String;
    }

    public boolean isHasTags() {
        IType type = resourceModel.getProperty(ResourceTypeName.FIELD_TAGS).getFluentType();
        return type instanceof ListType && ((ListType) type).getElementType() == ClassType.String;
    }

    public List<ClientMethodParameter> getPathParameters() {
        ClientMethod clientMethod = methodReferences.iterator().next().getInnerClientMethod();
        Set<String> pathParamNames = clientMethod.getProxyMethod().getParameters().stream()
                .filter(p -> p.getRequestParameterLocation() == RequestParameterLocation.Path)
                .map(ProxyMethodParameter::getName)
                .collect(Collectors.toSet());
        return clientMethod.getMethodRequiredParameters().stream()
                .filter(p -> pathParamNames.contains(p.getName()))
                .collect(Collectors.toList());
    }

    public ClientMethodParameter getBodyParameter() {
        ClientMethod clientMethod = methodReferences.iterator().next().getInnerClientMethod();
        String bodyParamName = clientMethod.getProxyMethod().getParameters().stream()
                .filter(p -> p.getRequestParameterLocation() == RequestParameterLocation.Body)
                .map(ProxyMethodParameter::getName)
                .findAny().orElse(null);
        return clientMethod.getMethodRequiredParameters().stream()
                .filter(p -> p.getName().equals(bodyParamName))
                .findAny().orElse(null);
    }

    public boolean isBodyParameterSameAsFluentModel() {
        return bodyParameterModel == resourceModel.getInnerModel();
    }

    public List<ClientModelProperty> getRequiredProperties() {
        List<ClientModelProperty> properties = new ArrayList<>();

        List<String> commonPropertyNames = Arrays.asList(ResourceTypeName.FIELD_LOCATION, ResourceTypeName.FIELD_TAGS);

        if (this.isBodyParameterSameAsFluentModel()) {
            for (String commonPropertyName : commonPropertyNames) {
                if (resourceModel.hasProperty(commonPropertyName)) {
                    properties.add(resourceModel.getProperty(commonPropertyName).getInnerProperty());
                }
            }

        } else {
            // TODO
        }

        return properties;
    }
}
