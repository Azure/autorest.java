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
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.get.ResourceRefresh;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.update.ResourceUpdate;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.core.http.HttpMethod;
import com.azure.core.util.CoreUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ResourceParser {

    private static final Logger logger = LoggerFactory.getLogger(ResourceParser.class);

    public static void parseResourcesCategory(FluentResourceCollection collection,
                                              List<FluentResourceModel> availableFluentModels,
                                              List<ClientModel> availableModels) {
        // resource create
        List<ResourceCreate> resourceCreates = ResourceParser.resolveResourceCreate(collection, availableFluentModels, availableModels);

        // resource update
        resourceCreates.forEach(rc -> ResourceParser.resolveResourceUpdate(collection, rc, availableModels));

        // resource refresh
        resourceCreates.forEach(rc -> ResourceParser.resolveResourceRefresh(collection, rc, availableModels));
    }

    private static List<ResourceCreate> resolveResourceCreate(
            FluentResourceCollection collection,
            List<FluentResourceModel> availableFluentModels,
            List<ClientModel> availableModels) {

        Map<String, FluentResourceModel> fluentModelMapByName = availableFluentModels.stream()
                .collect(Collectors.toMap(m -> m.getInterfaceType().toString(), Function.identity()));

        List<ResourceCreate> supportsCreateList = new ArrayList<>();
        Set<FluentResourceModel> foundModels = new HashSet<>();

        List<ModelCategory> categories = Arrays.asList(
                ModelCategory.RESOURCE_GROUP_AS_PARENT,
                ModelCategory.SUBSCRIPTION_AS_PARENT,
                ModelCategory.NESTED_CHILD);

        for (ModelCategory category : categories) {
            Map<FluentResourceModel, ResourceCreate> modelOfResourceGroupAsParent =
                    findResourceCreateForCategory(collection, fluentModelMapByName, availableModels, foundModels, category);

            foundModels.addAll(modelOfResourceGroupAsParent.keySet());

            for (Map.Entry<FluentResourceModel, ResourceCreate> entry : modelOfResourceGroupAsParent.entrySet()) {
                FluentResourceModel fluentModel = entry.getKey();
                ResourceCreate resourceCreate = entry.getValue();

                fluentModel.setCategory(category);
                fluentModel.setResourceCreate(resourceCreate);
                collection.getResourceCreates().add(resourceCreate);

                supportsCreateList.add(resourceCreate);

                logger.info("Fluent model {} as category {}", fluentModel.getName(), category);
            }
        }

        supportsCreateList.forEach(rc -> {
            rc.getMethodReferences().addAll(collectMethodReferences(collection, rc.getMethodName()));
        });

        return supportsCreateList;
    }

    private static Optional<ResourceUpdate> resolveResourceUpdate(
            FluentResourceCollection collection,
            ResourceCreate resourceCreate,
            List<ClientModel> availableModels) {

        ResourceUpdate resourceUpdate = null;

        Predicate<String> nameMatcher = name -> !(name.contains("create") && !name.contains("update"));
        // PATCH takes priority
        FluentCollectionMethod method = findCollectionMethod(collection, resourceCreate, HttpMethod.PATCH, nameMatcher);
        if (method == null) {
            // fallback to PUT
            method = findCollectionMethod(collection, resourceCreate, HttpMethod.PUT, nameMatcher);
        }
        if (method != null) {
            resourceUpdate = new ResourceUpdate(resourceCreate.getResourceModel(), collection,
                    resourceCreate.getUrlPathSegments(), method.getInnerClientMethod().getName(),
                    getBodyClientModel(method, availableModels));

            resourceCreate.getResourceModel().setResourceUpdate(resourceUpdate);
            collection.getResourceUpdates().add(resourceUpdate);

            resourceUpdate.getMethodReferences().addAll(collectMethodReferences(collection, resourceUpdate.getMethodName()));
        }

        return Optional.ofNullable(resourceUpdate);
    }

    private static Optional<ResourceRefresh> resolveResourceRefresh(
            FluentResourceCollection collection,
            ResourceCreate resourceCreate,
            List<ClientModel> availableModels) {

        ResourceRefresh resourceRefresh = null;

        FluentCollectionMethod method = findCollectionMethod(collection, resourceCreate, HttpMethod.GET, name -> name.contains("get"));
        if (method != null) {
            resourceRefresh = new ResourceRefresh(resourceCreate.getResourceModel(), collection,
                    resourceCreate.getUrlPathSegments(), method.getInnerClientMethod().getName(),
                    getBodyClientModel(method, availableModels));

            resourceCreate.getResourceModel().setResourceRefresh(resourceRefresh);

            resourceRefresh.getMethodReferences().addAll(collectMethodReferences(collection, resourceRefresh.getMethodName()));
        }

        return Optional.ofNullable(resourceRefresh);
    }

    private static Map<FluentResourceModel, ResourceCreate> findResourceCreateForCategory(
            FluentResourceCollection collection,
            Map<String, FluentResourceModel> fluentModelMapByName,
            List<ClientModel> availableModels,
            Set<FluentResourceModel> excludeModels,
            ModelCategory category) {

        Map<FluentResourceModel, ResourceCreate> foundModels = new HashMap<>();

        collection.getMethods().forEach(m -> {
            HttpMethod method = m.getInnerProxyMethod().getHttpMethod();

            // PUT
            if (method == HttpMethod.PUT) {
                // not only "update", usually "createOrUpdate" or "create", sometimes "put"
                String methodNameLowerCase = m.getInnerClientMethod().getName().toLowerCase(Locale.ROOT);
                if (!(methodNameLowerCase.contains("update") && !methodNameLowerCase.contains("create"))) {
                    // body in request
                    if (m.getInnerProxyMethod().getParameters().stream().anyMatch(p -> p.getRequestParameterLocation() == RequestParameterLocation.Body)) {
                        String returnTypeName = m.getFluentReturnType().toString();
                        FluentResourceModel fluentModel = fluentModelMapByName.get(returnTypeName);
                        // "id", "name", "type" in resource instance
                        if (fluentModel != null && fluentModel.getResourceCreate() == null
                                && !foundModels.containsKey(fluentModel) && !excludeModels.contains(fluentModel)
                                && fluentModel.hasProperty(ResourceTypeName.FIELD_ID)
                                && fluentModel.hasProperty(ResourceTypeName.FIELD_NAME)
                                && fluentModel.hasProperty(ResourceTypeName.FIELD_TYPE)) {
                            String url = m.getInnerProxyMethod().getUrlPath();
                            UrlPathSegments urlPathSegments = new UrlPathSegments(url);

                            //logger.info("Candidate fluent model {}, hasSubscription {}, hasResourceGroup {}, isNested {}, method name {}", fluentModel.getName(), urlPathSegments.hasSubscription(), urlPathSegments.hasResourceGroup(), urlPathSegments.isNested(), m.getInnerClientMethod().getName());

                            // has "subscriptions" segment, and last segment should be resource name
                            if (!urlPathSegments.getReverseSegments().isEmpty()
                                    && urlPathSegments.getReverseSegments().iterator().next().isParameterSegment()
                                    && urlPathSegments.hasSubscription()) {

                                boolean categoryMatch = false;
                                switch (category) {
                                    case RESOURCE_GROUP_AS_PARENT:
                                        if (urlPathSegments.hasResourceGroup() && !urlPathSegments.isNested()) {
                                            categoryMatch = true;
                                        }
                                        break;

                                    case SUBSCRIPTION_AS_PARENT:
                                        if (!urlPathSegments.hasResourceGroup() && !urlPathSegments.isNested()) {
                                            categoryMatch = true;
                                        }
                                        break;

                                    case NESTED_CHILD:
                                        if (urlPathSegments.isNested()) {
                                            categoryMatch = true;
                                        }
                                        break;
                                }

                                if (categoryMatch) {
                                    ResourceCreate resourceCreate = new ResourceCreate(fluentModel, collection, urlPathSegments,
                                            m.getInnerClientMethod().getName(), getBodyClientModel(m, availableModels));

                                    foundModels.put(fluentModel, resourceCreate);
                                }
                            }
                        }
                    }
                }
            }
        });

        return foundModels;
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

    private static FluentCollectionMethod findCollectionMethod(FluentResourceCollection collection,
                                                               ResourceCreate resourceCreate,
                                                               HttpMethod matchingMethod, Predicate<String> nameMatcher) {
        boolean isRefreshMethod = matchingMethod == HttpMethod.GET;

        for (FluentCollectionMethod method : collection.getMethods()) {
            HttpMethod httpMethod = method.getInnerProxyMethod().getHttpMethod();
            // match http method
            if (httpMethod == matchingMethod) {
                String methodNameLowerCase = method.getInnerClientMethod().getName().toLowerCase(Locale.ROOT);
                // match name
                if (nameMatcher.test(methodNameLowerCase)) {
                    String returnTypeName = method.getFluentReturnType().toString();
                    // same model as create
                    if (returnTypeName.equals(resourceCreate.getResourceModel().getInterfaceType().getName())) {
                        String url = method.getInnerProxyMethod().getUrlPath();
                        // same url as create
                        if (url.equals(resourceCreate.getUrlPathSegments().getPath())) {
                            boolean hasBodyParam = method.getInnerProxyMethod().getParameters().stream()
                                    .anyMatch(p -> p.getRequestParameterLocation() == RequestParameterLocation.Body);
                            boolean hasRequiredQueryParam = method.getInnerProxyMethod().getParameters().stream()
                                    .anyMatch(p -> p.getRequestParameterLocation() == RequestParameterLocation.Query && p.getIsRequired());
                            boolean urlParameterSegmentsNamed = resourceCreate.getUrlPathSegments().getReverseParameterSegments().stream()
                                    .noneMatch(s -> CoreUtils.isNullOrEmpty(s.getSegmentName()));
                            // if for update, need a body parameter, also need named parameters in URL (this might be relaxed after better solution to parse URL to parameters)
                            // if for refresh, do not allow required query parameter, since it cannot be deduced from resource id
                            if ((isRefreshMethod && !hasRequiredQueryParam) || (!isRefreshMethod && hasBodyParam && urlParameterSegmentsNamed)) {
                                return method;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    private static List<FluentCollectionMethod> collectMethodReferences(FluentResourceCollection collection, String methodName) {
        return collection.getMethods().stream()
                .filter(m -> m.getInnerClientMethod().getName().equals(methodName)
                        || (m.getInnerClientMethod().getType() == ClientMethodType.SimpleSyncRestResponse && m.getInnerClientMethod().getName().equals(methodName + "WithResponse")))
                .collect(Collectors.toList());
    }
}
