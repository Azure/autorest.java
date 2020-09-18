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
import com.azure.core.util.CoreUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ResourceOperation {

    private static final Logger logger = LoggerFactory.getLogger(ResourceOperation.class);

    protected final FluentResourceModel resourceModel;
    protected final FluentResourceCollection resourceCollection;

    protected final UrlPathSegments urlPathSegments;

    protected final String methodName;

    protected final ClientModel bodyParameterModel;

    protected final List<FluentCollectionMethod> methodReferences = new ArrayList<>();

    private ClientModel bodyClientModel;
    private Map<String, ClientModelProperty> bodyModelPropertiesMap;
    private List<ClientModelProperty> bodyModelProperties;

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

    private void initBodyClientModel() {
        if (bodyClientModel == null) {
            bodyModelPropertiesMap = new HashMap<>();
            bodyModelProperties = new ArrayList<>();

            ClientMethodParameter bodyParameter = this.getBodyParameter();
            bodyClientModel = FluentUtils.getClientModel(bodyParameter.getClientType().toString());
            List<ClientModel> parentModels = new ArrayList<>();
            if (bodyClientModel != null) {
                String parentModelName = bodyClientModel.getParentModelName();
                while (!CoreUtils.isNullOrEmpty(parentModelName)) {
                    ClientModel parentModel = FluentUtils.getClientModel(parentModelName);
                    if (parentModel != null) {
                        parentModels.add(parentModel);
                    }
                    parentModelName = parentModel == null ? null :parentModel.getParentModelName();
                }
            }

            List<List<ClientModelProperty>> propertiesFromTypeAndParents = new ArrayList<>();
            propertiesFromTypeAndParents.add(new ArrayList<>());
            bodyClientModel.getProperties().forEach(p -> {
                if (bodyModelPropertiesMap.putIfAbsent(p.getName(), p) == null) {
                    propertiesFromTypeAndParents.get(propertiesFromTypeAndParents.size() - 1).add(p);
                }
            });


            for (ClientModel parent : parentModels) {
                propertiesFromTypeAndParents.add(new ArrayList<>());

                parent.getProperties().stream()
                        .forEach(p -> {
                            if (bodyModelPropertiesMap.putIfAbsent(p.getName(), p) == null) {
                                propertiesFromTypeAndParents.get(propertiesFromTypeAndParents.size() - 1).add(p);
                            }
                        });
            }

            Collections.reverse(propertiesFromTypeAndParents);
            for (List<ClientModelProperty> properties1 : propertiesFromTypeAndParents) {
                bodyModelProperties.addAll(properties1);
            }
        }
    }

    protected ClientModel getBodyClientModel() {
        initBodyClientModel();
        return this.bodyClientModel;
    }

    private List<ClientModelProperty> getBodyModelProperties() {
        initBodyClientModel();
        return this.bodyModelProperties;
    }

    private Map<String, ClientModelProperty> getBodyModelPropertiesMap() {
        initBodyClientModel();
        return this.bodyModelPropertiesMap;
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
            Map<String, ClientModelProperty> propertyMap = this.getBodyModelPropertiesMap();
            for (String commonPropertyName : commonPropertyNames) {
                if (propertyMap.containsKey(commonPropertyName)) {
                    ClientModelProperty property = propertyMap.get(commonPropertyName);
                    properties.add(property);
                }
            }
            for (ClientModelProperty property : this.getBodyModelProperties()) {
                if (!commonPropertyNames.contains(property.getName())) {
                    properties.add(property);
                }
            }
        }

        return properties.stream()
                .filter(p -> !p.getIsReadOnly() && !p.getIsConstant())
                .collect(Collectors.toList());
    }

    protected ClientMethodParameter getBodyParameter() {
        List<ClientMethodParameter> parameters = getParametersByLocation(RequestParameterLocation.Body);
        return parameters.isEmpty() ? null : parameters.iterator().next();
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
