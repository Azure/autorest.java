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
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.clientmodel.ReturnValue;
import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ResourceCreate {

    private final FluentResourceModel resourceModel;
    private final FluentResourceCollection resourceCollection;

    private final UrlPathSegments urlPathSegments;

    private final String methodName;
    private final List<FluentCollectionMethod> methodReferences = new ArrayList<>();

    private final ClientModel bodyParameterModel;

    private List<FluentDefinitionStage> fluentDefinitionStages;

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

    public boolean hasResourceGroup() {
        return urlPathSegments.hasResourceGroup();
    }

    public boolean hasLocation() {
        return resourceModel.hasProperty(ResourceTypeName.FIELD_LOCATION)
                && resourceModel.getProperty(ResourceTypeName.FIELD_LOCATION).getFluentType() == ClassType.String;
    }

    public boolean hasTags() {
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

    public List<ClientModelProperty> getProperties() {
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

    public List<ClientModelProperty> getRequiredProperties() {
        return this.getProperties().stream()
                .filter(p -> p.isRequired())
                .collect(Collectors.toList());
    }

    public List<ClientModelProperty> getNonRequiredProperties() {
        return this.getProperties().stream()
                .filter(p -> !p.isRequired())
                .collect(Collectors.toList());
    }

    public List<FluentDefinitionStage> getDefinitionStages() {
        if (fluentDefinitionStages != null) {
            return fluentDefinitionStages;
        }

        fluentDefinitionStages = new ArrayList<>();

        // blank
        FluentBlankStage blankStage = new FluentBlankStage();

        // parent
        FluentParentStage parentStage = null;
        switch (this.getResourceModel().getCategory()) {
            case RESOURCE_GROUP_AS_PARENT:
                parentStage = new FluentParentStage("WithResourceGroup");
                break;

            case NESTED_CHILD:
                parentStage = new FluentParentStage("WithParentResource");
                break;
        }

        // create
        FluentCreateStage createStage = new FluentCreateStage();

        final boolean hasLocation = this.hasLocation();

        fluentDefinitionStages.add(blankStage);

        // required properties
        List<ClientModelProperty> requiredProperties = this.getRequiredProperties();

        FluentDefinitionStage lastStage = null;
        if (!requiredProperties.isEmpty()) {
            for (ClientModelProperty property : requiredProperties) {
                FluentDefinitionStage stage = new FluentDefinitionStage("With" + CodeNamer.toPascalCase(property.getName()), property);
                if (lastStage == null) {
                    // first property
                    if (hasLocation && property.getName().equals(ResourceTypeName.FIELD_LOCATION)) {
                        blankStage.setExtendStages(stage.getName());
                        fluentDefinitionStages.add(stage);

                        lastStage = stage;
                        stage = parentStage;
                    } else if (parentStage != null) {
                        blankStage.setExtendStages(parentStage.getName());

                        fluentDefinitionStages.add(parentStage);
                        lastStage = parentStage;
                    } else {
                        blankStage.setExtendStages(stage.getName());
                    }
                }

                if (lastStage != null) {
                    lastStage.setNextStage(stage);
                }

                fluentDefinitionStages.add(stage);
                lastStage = stage;
            }
        } else {
            if (parentStage == null) {
                blankStage.setExtendStages(createStage.getName());
                lastStage = blankStage;
            } else {
                blankStage.setExtendStages(parentStage.getName());
                lastStage = parentStage;
                fluentDefinitionStages.add(parentStage);
            }
        }

        lastStage.setNextStage(createStage);
        fluentDefinitionStages.add(createStage);

        // create method
        createStage.setCreateMethod(this.getCreateMethod(false));
        ClientMethod createMethodWithContext = this.getCreateMethod(true);
        if (createMethodWithContext != null) {
            createStage.setCreateMethodWithContext(createMethodWithContext);
        }

        // existing parent method after all stages is connected.
        parentStage.setExistingParentMethod(this.getExistingParentMethod(parentStage));

        // non-required properties
        List<FluentDefinitionStage> optionalFluentDefinitionStages = new ArrayList<>();
        List<ClientModelProperty> nonRequiredProperties = this.getNonRequiredProperties();
        for (ClientModelProperty property : nonRequiredProperties) {
            FluentDefinitionStage stage = new FluentDefinitionStage("With" + CodeNamer.toPascalCase(property.getName()), property);
            stage.setNextStage(createStage);

            optionalFluentDefinitionStages.add(stage);
        }
        if (!optionalFluentDefinitionStages.isEmpty()) {
            createStage.setExtendStages(optionalFluentDefinitionStages.stream()
                    .map(s -> String.format("%1$s.%2$s", ModelNaming.MODEL_FLUENT_INTERFACE_DEFINITION_STAGES, s.getName()))
                    .collect(Collectors.joining(", ")));
        }

        fluentDefinitionStages.addAll(optionalFluentDefinitionStages);

        return fluentDefinitionStages;
    }

    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        getDefinitionStages().forEach(s -> s.addImportsTo(imports, includeImplementationImports));
    }

    private ClientMethod getExistingParentMethod(FluentParentStage stage) {
        String parentResourceName = CodeNamer.toPascalCase(FluentUtils.getSingular(urlPathSegments.getReverseParameterSegments().get(1).getSegmentName()));
        List<ClientMethodParameter> parameters = this.getPathParameters();
        parameters.remove(parameters.size() - 1);
        return new ClientMethod.Builder()
                .name("withExisting" + parentResourceName)
                .description(String.format("Specifies %1$s.", parameters.stream().map(ClientMethodParameter::getName).collect(Collectors.joining(", "))))
                .returnValue(new ReturnValue("the next definition stage.", new ClassType.Builder().packageName(resourceModel.getInterfaceType().getPackage()).name(stage.getNextStage().getName()).build()))
                .parameters(parameters)
                .build();
    }

    private ClientMethod getCreateMethod(boolean addContextParameter) {
        List<ClientMethodParameter> parameters = new ArrayList<>();
        if (addContextParameter) {
            Optional<ClientMethodParameter> contextParameter = this.getMethodReferences().stream()
                    .flatMap(m -> m.getInnerClientMethod().getParameters().stream())
                    .filter(p -> ClassType.Context.getName().equals(p.getClientType().toString()))
                    .findFirst();
            if (contextParameter.isPresent()) {
                parameters.add(contextParameter.get());
            } else {
                return null;
            }
        }
        return new ClientMethod.Builder()
                .name("create")
                .description("Executes the create request.")
                .returnValue(new ReturnValue("the created resource.", resourceModel.getInterfaceType()))
                .parameters(parameters)
                .build();
    }
}
