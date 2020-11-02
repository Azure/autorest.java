/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.create;

import com.azure.autorest.fluent.model.ResourceTypeName;
import com.azure.autorest.fluent.model.arm.UrlPathSegments;
import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceOperation;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentConstructorByName;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentCreateMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentDefineMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethodParameterMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethodType;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentModelPropertyMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentModelPropertyRegion;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentParentMethod;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.util.CodeNamer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ResourceCreate extends ResourceOperation {

    private static final Logger logger = LoggerFactory.getLogger(ResourceCreate.class);

    private List<DefinitionStage> definitionStages;

    private FluentDefineMethod defineMethod;

    public ResourceCreate(FluentResourceModel resourceModel, FluentResourceCollection resourceCollection,
                          UrlPathSegments urlPathSegments, String methodName, ClientModel bodyParameterModel) {
        super(resourceModel, resourceCollection, urlPathSegments, methodName, bodyParameterModel);

        logger.info("ResourceCreate: Fluent model {}, method reference {}, body parameter {}",
                resourceModel.getName(), methodName, bodyParameterModel.getName());
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

        definitionStages.add(definitionStageBlank);

        // required properties
        List<ClientModelProperty> requiredProperties = this.getRequiredProperties();

        DefinitionStage lastStage = null;
        if (!requiredProperties.isEmpty()) {
            for (ClientModelProperty property : requiredProperties) {
                DefinitionStage stage = new DefinitionStage("With" + CodeNamer.toPascalCase(property.getName()), property);
                if (lastStage == null) {
                    // first property
                    if (isLocationProperty(property)) {
                        definitionStageBlank.setExtendStages(stage.getName());

                        if (definitionStageParent != null) {
                            // insert parent stage as 2nd stage
                            definitionStages.add(stage);

                            lastStage = stage;
                            stage = definitionStageParent;
                        }
                    } else if (definitionStageParent != null) {
                        // insert parent stage as 1st stage
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

        for (DefinitionStage stage : definitionStages) {
            if (stage.getModelProperty() != null) {
                this.generatePropertyMethods(stage, requestBodyParameterModel, stage.getModelProperty());
            }
        }

        // create method
        definitionStageCreate.getMethods().add(this.getCreateMethod(false));
        definitionStageCreate.getMethods().add(this.getCreateMethod(true));

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

            this.generatePropertyMethods(stage, requestBodyParameterModel, property);

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

    @Override
    protected List<ClientModelProperty> getProperties() {
        return super.getProperties().stream()
                .filter(p -> !p.getIsReadOnlyForCreate())
                .filter(p -> !isIdProperty(p))           // create should not be able to set id
                .collect(Collectors.toList());
    }

    @Override
    public List<FluentMethod> getFluentMethods() {
        List<FluentMethod> methods = this.getDefinitionStages().stream()
                .flatMap(s -> s.getMethods().stream())
                .collect(Collectors.toList());
        methods.add(this.getConstructor());
        return methods;
    }

    @Override
    public String getLocalVariablePrefix() {
        return "create";
    }

    private FluentMethod getParameterSetterMethod(DefinitionStage stage, ClientMethodParameter parameter) {
        return new FluentMethodParameterMethod(this.getResourceModel(), FluentMethodType.CREATE_WITH,
                stage, parameter, this.getLocalVariableByMethodParameter(parameter));
    }

    public FluentMethod getDefineMethod() {
        if (defineMethod == null) {
            String resourceName = this.getResourceName();
            logger.info("ResourceCreate: Fluent model {}, define method {}", resourceModel.getName(), "define" + resourceName);

            List<ClientMethodParameter> parameters = this.getPathParameters();
            IType resourceNameType = parameters.get(parameters.size() - 1).getClientType();
            defineMethod = new FluentDefineMethod(this.getResourceModel(), FluentMethodType.DEFINE,
                    resourceName, resourceNameType);
        }
        return defineMethod;
    }

    private String getResourceName() {
        String strCreateOrUpdate = "createOrUpdate";
        String strCreate = "create";
        String resourceName;
        if (methodName.startsWith(strCreateOrUpdate)) {
            resourceName = methodName.substring(strCreateOrUpdate.length());
        } else if (methodName.startsWith(strCreate)) {
            resourceName = methodName.substring(strCreate.length());
        } else {
            resourceName = resourceModel.getName();
        }
        return CodeNamer.toPascalCase(resourceName);
    }

    private FluentMethod getConstructor() {
        List<ClientMethodParameter> parameters = this.getPathParameters();
        IType resourceNameType = parameters.get(parameters.size() - 1).getClientType();
        String propertyName = parameters.get(parameters.size() - 1).getName();
        return new FluentConstructorByName(this.getResourceModel(), FluentMethodType.CONSTRUCTOR,
                resourceNameType, propertyName, FluentStatic.getFluentManager().getType(),
                this.getResourceLocalVariables());
    }

    private void generatePropertyMethods(DefinitionStage stage, ClientModel model, ClientModelProperty property) {
        if (FluentUtils.modelHasLocationProperty(resourceModel) && property.getName().equals(ResourceTypeName.FIELD_LOCATION)) {
            // location -> region
            stage.getMethods().add(new FluentModelPropertyRegion.FluentModelPropertyRegionMethod(
                    this.getResourceModel(), FluentMethodType.CREATE_WITH,
                    stage, model, property,
                    this.getLocalVariableByMethodParameter(this.getBodyParameter())));
            stage.getMethods().add(new FluentModelPropertyRegion.FluentModelPropertyRegionNameMethod(
                    this.getResourceModel(), FluentMethodType.CREATE_WITH,
                    stage, model, property,
                    this.getLocalVariableByMethodParameter(this.getBodyParameter())));
        } else {
            stage.getMethods().add(getPropertyMethod(stage, model, property));
        }
    }

    private FluentMethod getPropertyMethod(DefinitionStage stage, ClientModel model, ClientModelProperty property) {
        return new FluentModelPropertyMethod(this.getResourceModel(), FluentMethodType.CREATE_WITH,
                stage, model, property,
                this.getLocalVariableByMethodParameter(this.getBodyParameter()));
    }

    private FluentMethod getExistingParentMethod(DefinitionStageParent stage) {
        String parentResourceName = CodeNamer.toPascalCase(FluentUtils.getSingular(urlPathSegments.getReverseParameterSegments().get(1).getSegmentName()));
        List<ClientMethodParameter> parameters = this.getPathParameters();
        parameters.remove(parameters.size() - 1);
        return new FluentParentMethod(resourceModel, FluentMethodType.CREATE_PARENT,
                stage, parentResourceName,
                parameters, this.getResourceLocalVariables());
    }

    private FluentMethod getCreateMethod(boolean hasContextParameter) {
        List<ClientMethodParameter> parameters = new ArrayList<>();
        Optional<FluentCollectionMethod> methodOpt = this.findMethod(true, parameters);
        if (methodOpt.isPresent()) {
            if (!hasContextParameter) {
                parameters.clear();
            }
            return new FluentCreateMethod(resourceModel, FluentMethodType.CREATE,
                    parameters, this.getResourceLocalVariables(),
                    resourceCollection, methodOpt.get());
        } else {
            throw new IllegalStateException("create method not found on model " + resourceModel.getName());
        }
    }

    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        getDefinitionStages().forEach(s -> s.addImportsTo(imports, includeImplementationImports));
    }
}
