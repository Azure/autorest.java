/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.create;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.fluent.model.ResourceTypeName;
import com.azure.autorest.fluent.model.arm.UrlPathSegments;
import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentModelProperty;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceOperation;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentConstructor;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentCreateMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentDefineMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethodParameterMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethodType;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentModelPropertyMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentParentMethod;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.util.CodeNamer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ResourceCreate extends ResourceOperation  {

    private static final Logger logger = LoggerFactory.getLogger(ResourceCreate.class);

    private List<DefinitionStage> definitionStages;

    private FluentDefineMethod defineMethod;

    public ResourceCreate(FluentResourceModel resourceModel, FluentResourceCollection resourceCollection,
                          UrlPathSegments urlPathSegments, String methodName, ClientModel bodyParameterModel) {
        super(resourceModel, resourceCollection, urlPathSegments, methodName, bodyParameterModel);
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

    public List<DefinitionStage> getDefinitionStages() {
        if (definitionStages != null) {
            return definitionStages;
        }

        definitionStages = new ArrayList<>();

        // blank
        DefinitionStageBlank definitionStageBlank = new DefinitionStageBlank();

        // parent
        DefinitionStageParent definitionStageParent = null;
        switch (this.getResourceModel().getCategory()) {
            case RESOURCE_GROUP_AS_PARENT:
                definitionStageParent = new DefinitionStageParent("WithResourceGroup");
                break;

            case NESTED_CHILD:
                definitionStageParent = new DefinitionStageParent("WithParentResource");
                break;
        }

        // create
        DefinitionStageCreate definitionStageCreate = new DefinitionStageCreate();

        final boolean hasLocation = this.hasLocation();

        definitionStages.add(definitionStageBlank);

        // required properties
        List<ClientModelProperty> requiredProperties = this.getRequiredProperties();

        DefinitionStage lastStage = null;
        if (!requiredProperties.isEmpty()) {
            for (ClientModelProperty property : requiredProperties) {
                DefinitionStage stage = new DefinitionStage("With" + CodeNamer.toPascalCase(property.getName()), property);
                if (lastStage == null) {
                    // first property
                    if (hasLocation && property.getName().equals(ResourceTypeName.FIELD_LOCATION)) {
                        definitionStageBlank.setExtendStages(stage.getName());
                        definitionStages.add(stage);

                        lastStage = stage;
                        stage = definitionStageParent;
                    } else if (definitionStageParent != null) {
                        definitionStageBlank.setExtendStages(definitionStageParent.getName());

                        definitionStages.add(definitionStageParent);
                        lastStage = definitionStageParent;
                    } else {
                        definitionStageBlank.setExtendStages(stage.getName());
                    }
                }

                if (lastStage != null) {
                    lastStage.setNextStage(stage);
                }

                definitionStages.add(stage);
                lastStage = stage;
            }
        } else {
            if (definitionStageParent == null) {
                definitionStageBlank.setExtendStages(definitionStageCreate.getName());
                lastStage = definitionStageBlank;
            } else {
                definitionStageBlank.setExtendStages(definitionStageParent.getName());
                lastStage = definitionStageParent;
                definitionStages.add(definitionStageParent);
            }
        }

        lastStage.setNextStage(definitionStageCreate);
        definitionStages.add(definitionStageCreate);

        // create method
        definitionStageCreate.setCreateMethod(this.getCreateMethod(false));
        FluentMethod createMethodWithContext = this.getCreateMethod(true);
        if (createMethodWithContext != null) {
            definitionStageCreate.setCreateMethodWithContext(createMethodWithContext);
        }

        if (definitionStageParent != null) {
            // existing parent method after all stages is connected.
            definitionStageParent.setExistingParentMethod(this.getExistingParentMethod(definitionStageParent));
        }

        List<DefinitionStage> optionalDefinitionStages = new ArrayList<>();
        // non-required properties
        List<ClientModelProperty> nonRequiredProperties = this.getNonRequiredProperties();
        for (ClientModelProperty property : nonRequiredProperties) {
            DefinitionStage stage = new DefinitionStage("With" + CodeNamer.toPascalCase(property.getName()), property);
            stage.setNextStage(definitionStageCreate);

            // TODO, clientModel could be createdParameter
            stage.getMethods().add(this.getPropertyMethod(stage, this.getResourceModel().getInnerModel(), property));

            optionalDefinitionStages.add(stage);
        }
        // header and query parameters
        List<ClientMethodParameter> miscParameters = this.getMiscParameters();
        for (ClientMethodParameter parameter : miscParameters) {
            DefinitionStage stage = new DefinitionStageMisc("With" + CodeNamer.toPascalCase(parameter.getName()), parameter);
            stage.setNextStage(definitionStageCreate);

            stage.getMethods().add(this.getParameterSetterMethod(stage, parameter));

            optionalDefinitionStages.add(stage);
        }

        if (!optionalDefinitionStages.isEmpty()) {
            definitionStageCreate.setExtendStages(optionalDefinitionStages.stream()
                    .map(s -> String.format("%1$s.%2$s", ModelNaming.MODEL_FLUENT_INTERFACE_DEFINITION_STAGES, s.getName()))
                    .collect(Collectors.joining(", ")));
        }

        definitionStages.addAll(optionalDefinitionStages);

        return definitionStages;
    }

    private List<FluentCollectionMethod> getMethodReferencesOfFullParameters() {
        return this.getMethodReferences().stream()
                .filter(m -> !m.getInnerClientMethod().getOnlyRequiredParameters())
                .collect(Collectors.toList());
    }

    private List<ClientMethodParameter> getPathParameters() {
        return getParametersByLocation(RequestParameterLocation.Path);
    }

    private ClientMethodParameter getBodyParameter() {
        List<ClientMethodParameter> parameters = getParametersByLocation(RequestParameterLocation.Body);
        return parameters.isEmpty() ? null : parameters.iterator().next();
    }

    private List<ClientMethodParameter> getMiscParameters() {
        // header or query
        return getParametersByLocation(new HashSet<>(Arrays.asList(RequestParameterLocation.Header, RequestParameterLocation.Query)));
    }

    private List<ClientMethodParameter> getParametersByLocation(RequestParameterLocation parameterLocation) {
        return getParametersByLocation(new HashSet<>(Collections.singletonList(parameterLocation)));
    }

    private List<ClientMethodParameter> getParametersByLocation(Set<RequestParameterLocation> parameterLocations) {
        ClientMethod clientMethod = getMethodReferencesOfFullParameters().iterator().next().getInnerClientMethod();
        Set<String> paramNames = clientMethod.getProxyMethod().getParameters().stream()
                .filter(p -> parameterLocations.contains(p.getRequestParameterLocation()))
                .map(ProxyMethodParameter::getName)
                .collect(Collectors.toSet());
        return clientMethod.getParameters().stream()
                .filter(p -> paramNames.contains(p.getName()))
                .collect(Collectors.toList());
    }

    private List<ClientModelProperty> getRequiredProperties() {
        return this.getProperties().stream()
                .filter(p -> p.isRequired())
                .collect(Collectors.toList());
    }

    private List<ClientModelProperty> getNonRequiredProperties() {
        return this.getProperties().stream()
                .filter(p -> !p.isRequired())
                .collect(Collectors.toList());
    }

    public List<FluentMethod> getFluentMethods() {
        List<FluentMethod> methods = this.getDefinitionStages().stream()
                .flatMap(s -> s.getMethods().stream())
                .collect(Collectors.toList());
        methods.add(this.getConstructor());
        return methods;
    }

    public FluentMethod getParameterSetterMethod(DefinitionStage stage, ClientMethodParameter parameter) {
        return new FluentMethodParameterMethod(this.getResourceModel(), FluentMethodType.CREATE_WITH,
                stage, parameter);
    }

    public FluentMethod getDefineMethod() {
        if (defineMethod == null) {
            String resourceName = CodeNamer.toPascalCase(FluentUtils.getSingular(urlPathSegments.getReverseParameterSegments().get(0).getSegmentName()));
            List<ClientMethodParameter> parameters = this.getPathParameters();
            IType resourceNameType = parameters.get(parameters.size() - 1).getClientType();
            defineMethod = new FluentDefineMethod(this.getResourceModel(), FluentMethodType.DEFINE,
                    resourceName, resourceNameType);
        }
        return defineMethod;
    }

    private FluentMethod getConstructor() {
        List<ClientMethodParameter> parameters = this.getPathParameters();
        IType resourceNameType = parameters.get(parameters.size() - 1).getClientType();
        String propertyName = parameters.get(parameters.size() - 1).getName();
        return new FluentConstructor(this.getResourceModel(), FluentMethodType.CONSTRUCTOR,
                resourceNameType, propertyName, FluentStatic.getFluentManager().getType());
    }

    private FluentMethod getPropertyMethod(DefinitionStage stage, ClientModel model, ClientModelProperty property) {
        return new FluentModelPropertyMethod(this.getResourceModel(), FluentMethodType.CREATE_WITH,
                stage, model, property);
    }

    private FluentMethod getExistingParentMethod(DefinitionStageParent stage) {
        String parentResourceName = CodeNamer.toPascalCase(FluentUtils.getSingular(urlPathSegments.getReverseParameterSegments().get(1).getSegmentName()));
        List<ClientMethodParameter> parameters = this.getPathParameters();
        parameters.remove(parameters.size() - 1);
        return new FluentParentMethod(resourceModel, FluentMethodType.CREATE_WITH, stage, parentResourceName, parameters);
    }

    private FluentMethod getCreateMethod(boolean addContextParameter) {
        List<ClientMethodParameter> parameters = new ArrayList<>();
        FluentCollectionMethod collectionMethod;
        if (addContextParameter) {
            Optional<FluentCollectionMethod> clientMethodOpt = this.getMethodReferencesOfFullParameters().stream()
                    .filter(m -> m.getInnerClientMethod().getParameters().stream().anyMatch(FluentUtils::isContextParameter))
                    .findFirst();
            if (clientMethodOpt.isPresent()) {
                collectionMethod = clientMethodOpt.get();
                ClientMethodParameter contextParameter = collectionMethod.getInnerClientMethod().getParameters().stream()
                        .filter(FluentUtils::isContextParameter)
                        .findFirst().get();
                parameters.add(contextParameter);
            } else {
                return null;
            }
        } else {
            Optional<FluentCollectionMethod> collectionMethodOpt = this.getMethodReferencesOfFullParameters().stream()
                    .filter(m -> m.getInnerClientMethod().getParameters().stream().noneMatch(p -> ClassType.Context.getName().equals(p.getClientType().toString())))
                    .findFirst();
            if (collectionMethodOpt.isPresent()) {
                collectionMethod = collectionMethodOpt.get();
            } else {
                throw new IllegalStateException("create method not found");
            }
        }
        return new FluentCreateMethod(resourceModel, FluentMethodType.CREATE, parameters, resourceCollection, collectionMethod);
    }

    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        getDefinitionStages().forEach(s -> s.addImportsTo(imports, includeImplementationImports));
    }
}
