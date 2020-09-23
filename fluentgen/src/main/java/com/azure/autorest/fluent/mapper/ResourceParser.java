/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.fluent.model.ResourceTypeName;
import com.azure.autorest.fluent.model.arm.ModelCategory;
import com.azure.autorest.fluent.model.arm.UrlPathSegments;
import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.ResourceCreate;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.update.ResourceUpdate;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.core.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ResourceParser {

    private static final Logger logger = LoggerFactory.getLogger(ResourceParser.class);

    public static List<ResourceCreate> resolveResourceCreate(
            FluentResourceCollection collection,
            List<FluentResourceModel> availableFluentModels,
            List<ClientModel> availableModels) {

        Map<String, FluentResourceModel> fluentModels = availableFluentModels.stream()
                .collect(Collectors.toMap(m -> m.getInterfaceType().toString(), Function.identity()));

        List<ResourceCreate> supportsCreateList = new ArrayList<>();
        Set<FluentResourceModel> foundModels = new HashSet<>();

        collection.getMethods().forEach(m -> {
            HttpMethod method = m.getInnerProxyMethod().getHttpMethod();

            // PUT
            if (method == HttpMethod.PUT) {
                // not only "update", usually be "createOrUpdate" or "create"
                String methodNameLowerCase = m.getInnerClientMethod().getName().toLowerCase(Locale.ROOT);
                if (!(methodNameLowerCase.contains("update") && !methodNameLowerCase.contains("create"))) {
                    // body in request
                    if (m.getInnerProxyMethod().getParameters().stream().anyMatch(p -> p.getRequestParameterLocation() == RequestParameterLocation.Body)) {
                        String returnTypeName = m.getFluentReturnType().toString();
                        FluentResourceModel fluentModel = fluentModels.get(returnTypeName);
                        // "id", "name", "type" in resource instance
                        if (fluentModel != null && !foundModels.contains(fluentModel)
                                && fluentModel.hasProperty(ResourceTypeName.FIELD_ID)
                                && fluentModel.hasProperty(ResourceTypeName.FIELD_NAME)
                                && fluentModel.hasProperty(ResourceTypeName.FIELD_TYPE)) {
                            String url = m.getInnerProxyMethod().getUrlPath();
                            UrlPathSegments urlPathSegments = new UrlPathSegments(url);

                            // has "subscriptions" segment, and last segment should be resource name
                            if (urlPathSegments.getReverseSegments().iterator().next().isParameterSegment() && urlPathSegments.hasSubscription()) {
                                foundModels.add(fluentModel);

                                ResourceCreate resourceCreate = new ResourceCreate(fluentModel, collection, urlPathSegments,
                                        m.getInnerClientMethod().getName(), getBodyClientModel(m, availableModels));
                                supportsCreateList.add(resourceCreate);
                                fluentModel.setResourceCreate(resourceCreate);
                                collection.getResourceCreates().add(resourceCreate);

                                ModelCategory category = ModelCategory.SUBSCRIPTION_AS_PARENT;
                                if (urlPathSegments.isNested()) {
                                    category = ModelCategory.NESTED_CHILD;
                                } else if (urlPathSegments.hasResourceGroup()) {
                                    category = ModelCategory.RESOURCE_GROUP_AS_PARENT;
                                }
                                fluentModel.setCategory(category);

                                logger.info("Fluent model {} as category {}", fluentModel.getName(), category);
                            }
                        }
                    }
                }
            }
        });

        supportsCreateList.forEach(rc -> {
            rc.getMethodReferences().addAll(collectMethodReferences(collection,  rc.getMethodName()));
        });

        return supportsCreateList;
    }

    public static Optional<ResourceUpdate> resolveResourceUpdate(
            FluentResourceCollection collection,
            ResourceCreate resourceCreate,
            List<ClientModel> availableModels) {

        ResourceUpdate resourceUpdate = null;

        for (FluentCollectionMethod m : collection.getMethods()) {
            HttpMethod method = m.getInnerProxyMethod().getHttpMethod();

            // PATCH
            if (method == HttpMethod.PATCH) {
                if (isValidResourceUpdate(m, resourceCreate)) {
                    resourceUpdate = new ResourceUpdate(resourceCreate.getResourceModel(), collection,
                            resourceCreate.getUrlPathSegments(), m.getInnerClientMethod().getName(),
                            getBodyClientModel(m, availableModels));
                    break;
                }
            }
        }
        if (resourceUpdate == null) {
            for (FluentCollectionMethod m : collection.getMethods()) {
                HttpMethod method = m.getInnerProxyMethod().getHttpMethod();

                // PUT
                if (method == HttpMethod.PUT) {
                    if (isValidResourceUpdate(m, resourceCreate)) {
                        resourceUpdate = new ResourceUpdate(resourceCreate.getResourceModel(), collection,
                                resourceCreate.getUrlPathSegments(), m.getInnerClientMethod().getName(),
                                getBodyClientModel(m, availableModels));
                        break;
                    }
                }
            }
        }

        if (resourceUpdate != null) {
            resourceCreate.getResourceModel().setResourceUpdate(resourceUpdate);
            collection.getResourceUpdates().add(resourceUpdate);

            resourceUpdate.getMethodReferences().addAll(collectMethodReferences(collection, resourceUpdate.getMethodName()));
        }

        return Optional.ofNullable(resourceUpdate);
    }

    private static ClientModel getBodyClientModel(FluentCollectionMethod method, List<ClientModel> availableModels) {
        String bodyTypeName = method.getInnerClientMethod().getProxyMethod().getParameters()
                .stream()
                .filter(p -> p.getRequestParameterLocation() == RequestParameterLocation.Body)
                .map(p -> p.getClientType().toString())
                .findAny().orElse(null);

        return availableModels.stream()
                .filter(model -> model.getName().equals(bodyTypeName))
                .findAny().orElse(null);
    }

    private static boolean isValidResourceUpdate(FluentCollectionMethod method, ResourceCreate resourceCreate) {
        boolean valid = false;
        String methodNameLowerCase = method.getInnerClientMethod().getName().toLowerCase(Locale.ROOT);
        if (!(methodNameLowerCase.contains("create") && !methodNameLowerCase.contains("update"))) {
            // body in request
            if (method.getInnerProxyMethod().getParameters().stream().anyMatch(p -> p.getRequestParameterLocation() == RequestParameterLocation.Body)) {
                String returnTypeName = method.getFluentReturnType().toString();
                // same model as create
                if (returnTypeName.equals(resourceCreate.getResourceModel().getInterfaceType().getName())) {
                    String url = method.getInnerProxyMethod().getUrlPath();
                    // same url
                    if (url.equals(resourceCreate.getUrlPathSegments().getPath())) {
                        valid = true;
                    }
                }
            }
        }
        return valid;
    }

    private static List<FluentCollectionMethod> collectMethodReferences(FluentResourceCollection collection, String methodName) {
        return collection.getMethods().stream()
                .filter(m -> m.getInnerClientMethod().getName().equals(methodName)
                        || (m.getInnerClientMethod().getType() == ClientMethodType.SimpleSyncRestResponse && m.getInnerClientMethod().getName().equals(methodName + "WithResponse")))
                .collect(Collectors.toList());
    }
}
