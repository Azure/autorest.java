/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.get;

import com.azure.autorest.fluent.model.arm.UrlPathSegments;
import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceOperation;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethodType;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentRefreshMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ResourceRefresh extends ResourceOperation {

    private static final Logger logger = LoggerFactory.getLogger(ResourceRefresh.class);

    private List<FluentMethod> refreshMethods;

    public ResourceRefresh(FluentResourceModel resourceModel, FluentResourceCollection resourceCollection,
                           UrlPathSegments urlPathSegments, String methodName, ClientModel bodyParameterModel) {
        super(resourceModel, resourceCollection, urlPathSegments, methodName, bodyParameterModel);

        logger.info("ResourceRefresh: Fluent model {}, method reference {}",
                resourceModel.getName(), methodName);
    }

    @Override
    public List<FluentMethod> getFluentMethods() {
        return this.getRefreshMethods();
    }

    @Override
    public String getLocalVariablePrefix() {
        return "refresh";
    }

    public List<FluentMethod> getRefreshMethods() {
        if (refreshMethods == null) {
            refreshMethods = new ArrayList<>();

            refreshMethods.add(this.getRefreshMethod(false));
            refreshMethods.add(this.getRefreshMethod(true));
        }
        return refreshMethods;
    }

    private FluentMethod getRefreshMethod(boolean hasContextParameter) {
        List<ClientMethodParameter> parameters = new ArrayList<>();
        Optional<FluentCollectionMethod> methodOpt = this.findMethod(true, parameters);
        if (methodOpt.isPresent()) {
            if (!hasContextParameter) {
                parameters.clear();
            }
            return new FluentRefreshMethod(resourceModel, FluentMethodType.REFRESH,
                    parameters, this.getResourceLocalVariables(),
                    resourceCollection, methodOpt.get());
        } else {
            throw new IllegalStateException("refresh method not found on model " + resourceModel.getName());
        }
    }

    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        if (includeImplementationImports) {
            getRefreshMethods().forEach(m -> m.addImportsTo(imports, true));
        }
    }
}
