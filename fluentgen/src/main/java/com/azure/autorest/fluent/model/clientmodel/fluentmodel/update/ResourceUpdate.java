/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.update;

import com.azure.autorest.fluent.model.arm.UrlPathSegments;
import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceOperation;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentApplyMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentConstructorByInner;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethodParameterMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethodType;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentModelPropertyMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentUpdateMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.util.CodeNamer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ResourceUpdate extends ResourceOperation {

    private static final Logger logger = LoggerFactory.getLogger(ResourceUpdate.class);

    private List<UpdateStage> updateStages;

    private FluentUpdateMethod updateMethod;
    private List<FluentMethod> applyMethods;

    public ResourceUpdate(FluentResourceModel resourceModel, FluentResourceCollection resourceCollection,
                          UrlPathSegments urlPathSegments, String methodName, ClientModel bodyParameterModel) {
        super(resourceModel, resourceCollection, urlPathSegments, methodName, bodyParameterModel);

        logger.info("ResourceUpdate: Fluent model {}, method reference {}, body parameter {}",
                resourceModel.getName(), methodName, bodyParameterModel.getName());
    }

    public List<UpdateStage> getUpdateStages() {
        if (updateStages != null) {
            return updateStages;
        }

        updateStages = new ArrayList<>();

        UpdateStage updateStageApply = new UpdateStageApply();
        // updateStageApply does not belong to updateStages

        List<ClientModelProperty> properties = this.getProperties();
        for (ClientModelProperty property : properties) {
            UpdateStage stage = new UpdateStage("With" + CodeNamer.toPascalCase(property.getName()), property);
            stage.setNextStage(updateStageApply);

            stage.getMethods().add(this.getPropertyMethod(stage, requestBodyParameterModel, property));

            updateStages.add(stage);
        }
        // header and query parameters
        List<ClientMethodParameter> miscParameters = this.getMiscParameters();
        for (ClientMethodParameter parameter : miscParameters) {
            UpdateStage stage = new UpdateStageMisc("With" + CodeNamer.toPascalCase(parameter.getName()), parameter);
            stage.setNextStage(updateStageApply);

            stage.getMethods().add(this.getParameterSetterMethod(stage, parameter));

            updateStages.add(stage);
        }

        return updateStages;
    }

    public List<FluentMethod> getFluentMethods() {
        List<FluentMethod> methods = this.getUpdateStages().stream()
                .flatMap(s -> s.getMethods().stream())
                .collect(Collectors.toList());
        methods.add(this.getUpdateMethod());
        methods.addAll(this.getApplyMethods());
        methods.add(this.getConstructor());
        return methods;
    }

    private FluentMethod getParameterSetterMethod(UpdateStage stage, ClientMethodParameter parameter) {
        return new FluentMethodParameterMethod(this.getResourceModel(), FluentMethodType.UPDATE_WITH,
                stage, parameter, this.getLocalVariableByMethodParameter(parameter));
    }

    private FluentMethod getPropertyMethod(UpdateStage stage, ClientModel model, ClientModelProperty property) {
        return new FluentModelPropertyMethod(this.getResourceModel(), FluentMethodType.UPDATE_WITH,
                stage, model, property,
                this.getLocalVariableByMethodParameter(this.getBodyParameter()));
    }

    public FluentMethod getUpdateMethod() {
        if (updateMethod == null) {
            updateMethod = new FluentUpdateMethod(resourceModel, FluentMethodType.UPDATE, this.getResourceLocalVariables());
        }
        return updateMethod;
    }

    public List<FluentMethod> getApplyMethods() {
        if (applyMethods == null) {
            applyMethods = new ArrayList<>();

            applyMethods.add(this.getApplyMethod(false));
            FluentMethod updateMethodWithContext = this.getApplyMethod(true);
            if (updateMethodWithContext != null) {
                applyMethods.add(updateMethodWithContext);
            }
        }
        return applyMethods;
    }

    private FluentMethod getConstructor() {
        List<ClientMethodParameter> pathParameters = this.getPathParameters();
        return new FluentConstructorByInner(resourceModel, FluentMethodType.CONSTRUCTOR,
                pathParameters, this.getResourceLocalVariables(),
                FluentStatic.getFluentManager().getType(), urlPathSegments);
    }

    private FluentMethod getApplyMethod(boolean hasContextParameter) {
        List<ClientMethodParameter> parameters = new ArrayList<>();
        Optional<FluentCollectionMethod> methodOpt = this.findMethod(hasContextParameter, parameters);
        if (methodOpt.isPresent()) {
            return new FluentApplyMethod(resourceModel, FluentMethodType.APPLY,
                    parameters, this.getResourceLocalVariables(),
                    resourceCollection, methodOpt.get());
        } else {
            if (hasContextParameter) {
                return null;
            } else {
                throw new IllegalStateException("update method not found on model " + resourceModel.getName());
            }
        }
    }

    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        getUpdateStages().forEach(s -> s.addImportsTo(imports, includeImplementationImports));
        if (includeImplementationImports) {
            getConstructor().addImportsTo(imports, true);
            getUpdateMethod().addImportsTo(imports, true);
            getApplyMethods().forEach(m -> m.addImportsTo(imports, true));
        }
    }
}
