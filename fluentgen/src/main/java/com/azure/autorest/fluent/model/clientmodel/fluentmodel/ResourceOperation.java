/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.fluent.model.ResourceTypeName;
import com.azure.autorest.fluent.model.arm.UrlPathSegments;
import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentModelProperty;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ResourceOperation {

    protected final FluentResourceModel resourceModel;
    protected final FluentResourceCollection resourceCollection;

    protected final UrlPathSegments urlPathSegments;

    protected final String methodName;

    protected final ClientModel bodyParameterModel;

    protected final List<FluentCollectionMethod> methodReferences = new ArrayList<>();

    public ResourceOperation(FluentResourceModel resourceModel, FluentResourceCollection resourceCollection,
                             UrlPathSegments urlPathSegments, String methodName, ClientModel bodyParameterModel) {
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

    public boolean isBodyParameterSameAsFluentModel() {
        return bodyParameterModel == resourceModel.getInnerModel();
    }

    protected List<ClientModelProperty> getProperties() {
        List<ClientModelProperty> properties = new ArrayList<>();

        List<String> commonPropertyNames = Arrays.asList(ResourceTypeName.FIELD_LOCATION, ResourceTypeName.FIELD_TAGS);

        if (this.isBodyParameterSameAsFluentModel()) {
            for (String commonPropertyName : commonPropertyNames) {
                if (resourceModel.hasProperty(commonPropertyName)) {
                    FluentModelProperty property = resourceModel.getProperty(commonPropertyName);
                    properties.add(property.getInnerProperty());
                }
            }
            for (FluentModelProperty property : resourceModel.getProperties()) {
                if (!commonPropertyNames.contains(property.getName())) {
                    properties.add(property.getInnerProperty());
                }
            }
        } else {
            // TODO
        }

        return properties.stream()
                .filter(p -> !p.getIsReadOnly() && !p.getIsConstant())
                .collect(Collectors.toList());
    }

    protected List<ClientMethodParameter> getPathParameters() {
        return getParametersByLocation(RequestParameterLocation.Path);
    }

    protected List<ClientMethodParameter> getMiscParameters() {
        // header or query
        return getParametersByLocation(new HashSet<>(Arrays.asList(RequestParameterLocation.Header, RequestParameterLocation.Query)));
    }

    protected List<ClientMethodParameter> getParametersByLocation(RequestParameterLocation parameterLocation) {
        return getParametersByLocation(new HashSet<>(Collections.singletonList(parameterLocation)));
    }

    protected List<ClientMethodParameter> getParametersByLocation(Set<RequestParameterLocation> parameterLocations) {
        ClientMethod clientMethod = getMethodReferencesOfFullParameters().iterator().next().getInnerClientMethod();
        Set<String> paramNames = clientMethod.getProxyMethod().getParameters().stream()
                .filter(p -> parameterLocations.contains(p.getRequestParameterLocation()))
                .map(ProxyMethodParameter::getName)
                .collect(Collectors.toSet());
        return clientMethod.getParameters().stream()
                .filter(p -> paramNames.contains(p.getName()))
                .collect(Collectors.toList());
    }

    protected List<FluentCollectionMethod> getMethodReferencesOfFullParameters() {
        return this.getMethodReferences().stream()
                .filter(m -> !m.getInnerClientMethod().getOnlyRequiredParameters())
                .collect(Collectors.toList());
    }

    protected Optional<FluentCollectionMethod> findMethod(boolean hasContextParameter, List<ClientMethodParameter> parameters) {
        Optional<FluentCollectionMethod> methodOpt = this.getMethodReferencesOfFullParameters().stream()
                .filter(m -> hasContextParameter
                        ? m.getInnerClientMethod().getParameters().stream().anyMatch(FluentUtils::isContextParameter)
                        : m.getInnerClientMethod().getParameters().stream().noneMatch(FluentUtils::isContextParameter))
                .findFirst();
        if (methodOpt.isPresent() && hasContextParameter) {
            ClientMethodParameter contextParameter = methodOpt.get()
                    .getInnerClientMethod().getParameters().stream()
                    .filter(FluentUtils::isContextParameter)
                    .findFirst().get();
            parameters.add(contextParameter);
        }
        return methodOpt;
    }
}
