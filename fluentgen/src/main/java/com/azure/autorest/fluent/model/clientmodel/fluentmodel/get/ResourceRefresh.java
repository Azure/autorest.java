/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.get;

import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.model.arm.UrlPathSegments;
import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.MethodParameter;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceOperation;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.CollectionMethodOperationByIdTemplate;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethodType;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentRefreshMethod;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.template.prototype.MethodTemplate;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ResourceRefresh extends ResourceOperation {

    private static final Logger logger = new PluginLogger(FluentGen.getPluginInstance(), ResourceRefresh.class);

    private List<FluentMethod> refreshMethods;

    public ResourceRefresh(FluentResourceModel resourceModel, FluentResourceCollection resourceCollection,
                           UrlPathSegments urlPathSegments, String methodName) {
        super(resourceModel, resourceCollection, urlPathSegments, methodName, null);

        logger.info("ResourceRefresh: Fluent model {}, method reference {}",
                resourceModel.getName(), methodName);
    }

    @Override
    public List<FluentMethod> getFluentMethods() {
        return this.getRefreshMethods();
    }

    @Override
    public String getLocalVariablePrefix() {
        return "local";
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
                    resourceCollection, methodOpt.get(),
                    resourceModel.getResourceCreate().getResourceLocalVariables());
        } else {
            throw new IllegalStateException("refresh method not found on model " + resourceModel.getName());
        }
    }

    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        if (includeImplementationImports) {
            getRefreshMethods().forEach(m -> m.addImportsTo(imports, true));
        }
    }

    public List<MethodTemplate> getGetByIdCollectionMethods() {
        List<MethodTemplate> methods = new ArrayList<>();
        List<ClientMethodParameter> parameters = new ArrayList<>();
        Optional<FluentCollectionMethod> methodOpt = this.findMethod(true, parameters);
        if (methodOpt.isPresent()) {
            FluentCollectionMethod collectionMethod = methodOpt.get();

            String name = getGetByIdMethodName(collectionMethod.getInnerClientMethod().getName());
            List<MethodParameter> pathParameters = this.getPathParameters();

            methods.add(new CollectionMethodOperationByIdTemplate(
                    resourceModel, name,
                    pathParameters, urlPathSegments, false, getResourceLocalVariables(),
                    collectionMethod)
                    .getMethodTemplate());

            methods.add(new CollectionMethodOperationByIdTemplate(
                    resourceModel, name,
                    pathParameters, urlPathSegments, true, getResourceLocalVariables(),
                    collectionMethod)
                    .getMethodTemplate());
        }
        return methods;
    }

    private static String getGetByIdMethodName(String methodName) {
        String getByIdMethodName = methodName;
        int indexOfBy = methodName.indexOf("By");
        if (indexOfBy > 0) {
            getByIdMethodName = methodName.substring(0, indexOfBy);
        } else if (methodName.endsWith(Utils.METHOD_POSTFIX_WITH_RESPONSE)) {
            getByIdMethodName = methodName.substring(0, methodName.length() - Utils.METHOD_POSTFIX_WITH_RESPONSE.length());
        }
        getByIdMethodName += "ById";
        return getByIdMethodName;
    }
}
