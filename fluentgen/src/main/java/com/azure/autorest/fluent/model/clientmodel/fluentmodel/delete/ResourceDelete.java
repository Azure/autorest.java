/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.delete;

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
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.template.prototype.MethodTemplate;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ResourceDelete extends ResourceOperation {

    private static final Logger logger = new PluginLogger(FluentGen.getPluginInstance(), ResourceDelete.class);

    public ResourceDelete(FluentResourceModel resourceModel, FluentResourceCollection resourceCollection,
                           UrlPathSegments urlPathSegments, String methodName) {
        super(resourceModel, resourceCollection, urlPathSegments, methodName, null);

        logger.info("ResourceDelete: Fluent model {}, method reference {}",
                resourceModel.getName(), methodName);
    }

    @Override
    public List<FluentMethod> getFluentMethods() {
        return Collections.emptyList();
    }

    @Override
    public String getLocalVariablePrefix() {
        return "local";
    }

    public List<MethodTemplate> getDeleteByIdCollectionMethods() {
        List<MethodTemplate> methods = new ArrayList<>();
        List<ClientMethodParameter> parameters = new ArrayList<>();
        Optional<FluentCollectionMethod> methodOpt = this.findMethod(true, parameters);
        if (methodOpt.isPresent()) {
            FluentCollectionMethod collectionMethod = methodOpt.get();

            String name = getDeleteByIdMethodName(collectionMethod.getInnerClientMethod().getName());
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

    private static String getDeleteByIdMethodName(String methodName) {
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
