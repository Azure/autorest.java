/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.model.ResourceTypeName;
import com.azure.autorest.fluent.model.arm.UrlPathSegments;
import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentModelProperty;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.MethodParameter;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethod;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.util.CodeNamer;
import com.azure.core.util.CoreUtils;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class ResourceOperation {

    private static final Logger logger = new PluginLogger(FluentGen.getPluginInstance(), ResourceOperation.class);

    protected final FluentResourceModel resourceModel;
    protected final FluentResourceCollection resourceCollection;

    protected final UrlPathSegments urlPathSegments;

    protected final String methodName;

    protected final ClientModel requestBodyParameterModel;

    protected final List<FluentCollectionMethod> methodReferences = new ArrayList<>();

    public ResourceOperation(FluentResourceModel resourceModel, FluentResourceCollection resourceCollection,
                             UrlPathSegments urlPathSegments, String methodName, ClientModel requestBodyParameterModel) {
        this.resourceModel = resourceModel;
        this.resourceCollection = resourceCollection;
        this.urlPathSegments = urlPathSegments;
        this.methodName = methodName;
        this.requestBodyParameterModel = requestBodyParameterModel;
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

    public ClientModel getRequestBodyParameterModel() {
        return requestBodyParameterModel;
    }

    abstract public List<FluentMethod> getFluentMethods();

    abstract public String getLocalVariablePrefix();

    // properties on model inner object, or request body model
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
            Map<String, ClientModelProperty> propertyMap = this.getRequestBodyModelPropertiesMap();
            for (String commonPropertyName : commonPropertyNames) {
                if (propertyMap.containsKey(commonPropertyName)) {
                    ClientModelProperty property = propertyMap.get(commonPropertyName);
                    properties.add(property);
                }
            }
            for (ClientModelProperty property : this.getRequestBodyModelProperties()) {
                if (!commonPropertyNames.contains(property.getName())) {
                    properties.add(property);
                }
            }
        }

        return properties.stream()
                .filter(p -> !p.getIsReadOnly() && !p.getIsConstant())
                .collect(Collectors.toList());
    }

    // method parameters
    private List<MethodParameter> getParametersByLocation(RequestParameterLocation parameterLocation) {
        return getParametersByLocation(new HashSet<>(Collections.singletonList(parameterLocation)));
    }

    private List<MethodParameter> getParametersByLocation(Set<RequestParameterLocation> parameterLocations) {
        ClientMethod clientMethod = getMethodReferencesOfFullParameters().iterator().next().getInnerClientMethod();
        Map<String, ProxyMethodParameter> proxyMethodParameterByClientParameterName = clientMethod.getProxyMethod().getParameters().stream()
                .filter(p -> parameterLocations.contains(p.getRequestParameterLocation()))
                .collect(Collectors.toMap(p -> CodeNamer.getEscapedReservedClientMethodParameterName(p.getName()), Function.identity()));
        return clientMethod.getMethodParameters().stream()
                .filter(p -> proxyMethodParameterByClientParameterName.containsKey(p.getName()))
                .map(p -> new MethodParameter(proxyMethodParameterByClientParameterName.get(p.getName()), p))
                .collect(Collectors.toList());
    }

    public ClientMethodParameter getBodyParameter() {
        List<MethodParameter> parameters = getParametersByLocation(RequestParameterLocation.Body);
        return parameters.isEmpty() ? null : parameters.iterator().next().getClientMethodParameter();
    }

    public List<MethodParameter> getPathParameters() {
        return getParametersByLocation(RequestParameterLocation.Path);
    }

    public List<ClientMethodParameter> getMiscParameters() {
        // header or query
        return getParametersByLocation(new HashSet<>(Arrays.asList(RequestParameterLocation.Header, RequestParameterLocation.Query)))
                .stream().map(MethodParameter::getClientMethodParameter).collect(Collectors.toList());
    }

    public Collection<LocalVariable> getLocalVariables() {
        return this.getResourceLocalVariables().getLocalVariablesMap().values();
    }

    protected List<FluentCollectionMethod> getMethodReferencesOfFullParameters() {
        // method references of full parameters (include optional parameters)
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

    // local variables
    private ResourceLocalVariables resourceLocalVariables;

    public ResourceLocalVariables getResourceLocalVariables() {
        if (resourceLocalVariables == null) {
            resourceLocalVariables = new ResourceLocalVariables(this);
        }
        return resourceLocalVariables;
    }

    protected LocalVariable getLocalVariableByMethodParameter(ClientMethodParameter methodParameter) {
        return this.getResourceLocalVariables().getLocalVariablesMap().get(methodParameter);
    }

    // request body model and properties, used when request body is not fluent model inner object
    private Map<String, ClientModelProperty> requestBodyModelPropertiesMap;
    private List<ClientModelProperty> requestBodyModelProperties;

    protected boolean isBodyParameterSameAsFluentModel() {
        return requestBodyParameterModel == resourceModel.getInnerModel();
    }

    private void initRequestBodyClientModel() {
        if (requestBodyModelPropertiesMap == null) {
            requestBodyModelPropertiesMap = new LinkedHashMap<>();
            requestBodyModelProperties = new ArrayList<>();

            List<ClientModel> parentModels = new ArrayList<>();
            String parentModelName = requestBodyParameterModel.getParentModelName();
            while (!CoreUtils.isNullOrEmpty(parentModelName)) {
                ClientModel parentModel = FluentUtils.getClientModel(parentModelName);
                if (parentModel != null) {
                    parentModels.add(parentModel);
                }
                parentModelName = parentModel == null ? null :parentModel.getParentModelName();
            }

            List<List<ClientModelProperty>> propertiesFromTypeAndParents = new ArrayList<>();
            propertiesFromTypeAndParents.add(new ArrayList<>());
            requestBodyParameterModel.getProperties().forEach(p -> {
                if (requestBodyModelPropertiesMap.putIfAbsent(p.getName(), p) == null) {
                    propertiesFromTypeAndParents.get(propertiesFromTypeAndParents.size() - 1).add(p);
                }
            });


            for (ClientModel parent : parentModels) {
                propertiesFromTypeAndParents.add(new ArrayList<>());

                parent.getProperties().forEach(p -> {
                    if (requestBodyModelPropertiesMap.putIfAbsent(p.getName(), p) == null) {
                        propertiesFromTypeAndParents.get(propertiesFromTypeAndParents.size() - 1).add(p);
                    }
                });
            }

            Collections.reverse(propertiesFromTypeAndParents);
            for (List<ClientModelProperty> properties1 : propertiesFromTypeAndParents) {
                requestBodyModelProperties.addAll(properties1);
            }
        }
    }

    private List<ClientModelProperty> getRequestBodyModelProperties() {
        initRequestBodyClientModel();
        return this.requestBodyModelProperties;
    }

    private Map<String, ClientModelProperty> getRequestBodyModelPropertiesMap() {
        initRequestBodyClientModel();
        return this.requestBodyModelPropertiesMap;
    }

    protected boolean isIdProperty(ClientModelProperty property) {
        return property.getName().equals(ResourceTypeName.FIELD_ID);
    }

    protected boolean isLocationProperty(ClientModelProperty property) {
        return FluentUtils.modelHasLocationProperty(resourceModel) && property.getName().equals(ResourceTypeName.FIELD_LOCATION);
    }

    protected boolean hasConflictingMethod(String name) {
        return resourceCollection.getMethods().stream()
                .anyMatch(m -> name.equals(m.getInnerClientMethod().getName()));
    }
}
