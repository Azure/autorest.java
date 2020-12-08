/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.model.ResourceTypeName;
import com.azure.autorest.fluent.model.arm.ModelCategory;
import com.azure.autorest.fluent.model.arm.UrlPathSegments;
import com.azure.autorest.fluent.model.clientmodel.FluentClient;
import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.ResourceCreate;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.delete.ResourceDelete;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.get.ResourceRefresh;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.update.ResourceUpdate;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.template.prototype.MethodTemplate;
import com.azure.core.http.HttpMethod;
import com.azure.core.management.Region;
import com.azure.core.util.CoreUtils;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ResourceParser {

    private static final Logger logger = new PluginLogger(FluentGen.getPluginInstance(), ResourceParser.class);

    public static void parseResourcesCategory(FluentResourceCollection collection,
                                              List<FluentResourceModel> availableFluentModels,
                                              List<ClientModel> availableModels) {
        // resource create
        List<ResourceCreate> resourceCreates = ResourceParser.resolveResourceCreate(collection, availableFluentModels, availableModels);

        // resource update
        resourceCreates.forEach(rc -> ResourceParser.resolveResourceUpdate(collection, rc, availableModels));

        // resource refresh (and get in collection)
        resourceCreates.forEach(rc -> ResourceParser.resolveResourceRefresh(collection, rc));

        // delete in collection
        resourceCreates.forEach(rc -> ResourceParser.resolveResourceDelete(collection, rc));
    }

    public static void processAdditionalMethods(FluentClient fluentClient) {
        fluentClient.getResourceModels().forEach(ResourceParser::processAdditionalProperties);

        fluentClient.getResourceCollections().forEach(ResourceParser::processAdditionalCollectionMethods);
    }

    private static void processAdditionalProperties(FluentResourceModel model) {
        if (model.getCategory() != ModelCategory.IMMUTABLE) {
            if (FluentUtils.modelHasLocationProperty(model) && model.getProperty("region") == null) {
                // if resource instance has location property, add region() method
                List<MethodTemplate> methods = model.getAdditionalMethods();
                methods.add(MethodTemplate.builder()
                        .imports(Collections.singletonList(Region.class.getName()))
                        .comment(commentBlock -> {
                            commentBlock.description("Gets the region of the resource.");
                            commentBlock.methodReturns("the region of the resource.");
                        })
                        .methodSignature("Region region()")
                        .method(methodBlock -> {
                            methodBlock.methodReturn("Region.fromName(this.regionName())");
                        })
                        .build());
                methods.add(MethodTemplate.builder()
                        .comment(commentBlock -> {
                            commentBlock.description("Gets the name of the resource region.");
                            commentBlock.methodReturns("the name of the resource region.");
                        })
                        .methodSignature("String regionName()")
                        .method(methodBlock -> {
                            methodBlock.methodReturn("this.location()");
                        })
                        .build());
            }
        }
    }

    private static void processAdditionalCollectionMethods(FluentResourceCollection collection) {
        // getById method
        collection.getAdditionalMethods().addAll(
                collection.getResourceGets().stream()
                        .flatMap(rg -> rg.getGetByIdCollectionMethods().stream())
                        .collect(Collectors.toList()));

        // deleteById method
        collection.getAdditionalMethods().addAll(
                collection.getResourceDeletes().stream()
                        .flatMap(rg -> rg.getDeleteByIdCollectionMethods().stream())
                        .collect(Collectors.toList()));
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
                ModelCategory.NESTED_CHILD,
                ModelCategory.SCOPE_AS_PARENT,
                ModelCategory.SCOPE_NESTED_CHILD);

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

                logger.info("Fluent model '{}' as category {}", fluentModel.getName(), category);
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
            ClientModel bodyClientModel = getBodyClientModel(method, availableModels);
            if (bodyClientModel == null) {
                logger.warn("client model not found for collection '{}', method '{}'", collection.getInterfaceType().getName(), method.getInnerClientMethod().getName());
            } else {
                resourceUpdate = new ResourceUpdate(resourceCreate.getResourceModel(), collection,
                        resourceCreate.getUrlPathSegments(), method.getInnerClientMethod().getName(),
                        bodyClientModel);

                resourceCreate.getResourceModel().setResourceUpdate(resourceUpdate);
                collection.getResourceUpdates().add(resourceUpdate);

                resourceUpdate.getMethodReferences().addAll(collectMethodReferences(collection, resourceUpdate.getMethodName()));
            }
        }

        return Optional.ofNullable(resourceUpdate);
    }

    private static Optional<ResourceRefresh> resolveResourceRefresh(
            FluentResourceCollection collection,
            ResourceCreate resourceCreate) {

        ResourceRefresh resourceRefresh = null;

        FluentCollectionMethod method = findCollectionMethod(collection, resourceCreate, HttpMethod.GET, name -> name.contains("get"));
        if (method != null) {
            resourceRefresh = new ResourceRefresh(resourceCreate.getResourceModel(), collection,
                    resourceCreate.getUrlPathSegments(), method.getInnerClientMethod().getName());

            resourceCreate.getResourceModel().setResourceRefresh(resourceRefresh);
            collection.getResourceGets().add(resourceRefresh);

            resourceRefresh.getMethodReferences().addAll(collectMethodReferences(collection, resourceRefresh.getMethodName()));
        }

        return Optional.ofNullable(resourceRefresh);
    }

    private static Optional<ResourceDelete> resolveResourceDelete(
            FluentResourceCollection collection,
            ResourceCreate resourceCreate) {

        ResourceDelete resourceDelete = null;

        FluentCollectionMethod method = findCollectionMethod(collection, resourceCreate, HttpMethod.DELETE, name -> name.contains("delete"));
        if (method != null) {
            resourceDelete = new ResourceDelete(resourceCreate.getResourceModel(), collection,
                    resourceCreate.getUrlPathSegments(), method.getInnerClientMethod().getName());

            collection.getResourceDeletes().add(resourceDelete);

            resourceDelete.getMethodReferences().addAll(collectMethodReferences(collection, resourceDelete.getMethodName()));
        }

        return Optional.ofNullable(resourceDelete);
    }

    private static Map<FluentResourceModel, ResourceCreate> findResourceCreateForCategory(
            FluentResourceCollection collection,
            Map<String, FluentResourceModel> fluentModelMapByName,
            List<ClientModel> availableModels,
            Set<FluentResourceModel> excludeModels,
            ModelCategory category) {

        Map<FluentResourceModel, ResourceCreate> foundModels = new LinkedHashMap<>();

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
                        // at present, cannot handle derived models
                        if (fluentModel != null && fluentModel.getInnerModel().getDerivedModels().isEmpty()) {
                            // "id", "name", "type" in resource instance
                            if (fluentModel != null && fluentModel.getResourceCreate() == null
                                    && !foundModels.containsKey(fluentModel) && !excludeModels.contains(fluentModel)
                                    && fluentModel.hasProperty(ResourceTypeName.FIELD_ID)
                                    && fluentModel.hasProperty(ResourceTypeName.FIELD_NAME)
                                    && fluentModel.hasProperty(ResourceTypeName.FIELD_TYPE)) {
                                String url = m.getInnerProxyMethod().getUrlPath();
                                UrlPathSegments urlPathSegments = new UrlPathSegments(url);

                                //logger.info("Candidate fluent model '{}', hasSubscription '{}', hasResourceGroup '{}', isNested '{}', method name '{}'", fluentModel.getName(), urlPathSegments.hasSubscription(), urlPathSegments.hasResourceGroup(), urlPathSegments.isNested(), m.getInnerClientMethod().getName());

                                // has "subscriptions" segment, and last segment should be resource name
                                if (!urlPathSegments.getReverseSegments().isEmpty() && urlPathSegments.getReverseSegments().iterator().next().isParameterSegment()) {

                                    // requires named parameters in URL
                                    boolean urlParameterSegmentsNamed = urlPathSegments.getReverseParameterSegments().stream()
                                            .noneMatch(s -> CoreUtils.isNullOrEmpty(s.getSegmentName()));

                                    boolean categoryMatch = false;
                                    if (urlParameterSegmentsNamed && urlPathSegments.hasSubscription()) {
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
                                    }
                                    if (!categoryMatch && (category == ModelCategory.SCOPE_AS_PARENT || category == ModelCategory.SCOPE_NESTED_CHILD)) {
                                        // check for scope, required named parameters except scope
                                        boolean urlParameterSegmentsNamedExceptScope = urlPathSegments.getReverseParameterSegments().stream()
                                                .noneMatch(s -> s.getType() != UrlPathSegments.ParameterSegmentType.SCOPE && CoreUtils.isNullOrEmpty(s.getSegmentName()));

                                        if (urlParameterSegmentsNamedExceptScope && urlPathSegments.hasScope()
                                                && !urlPathSegments.hasSubscription() && !urlPathSegments.hasResourceGroup()) {
                                            switch (category) {
                                                case SCOPE_AS_PARENT:
                                                    if (!urlPathSegments.isNested()) {
                                                        categoryMatch = true;
                                                    }
                                                    break;

                                                case SCOPE_NESTED_CHILD:
                                                    if (urlPathSegments.isNested()) {
                                                        categoryMatch = true;
                                                    }
                                                    break;
                                            }
                                        }
                                    }

                                    if (categoryMatch) {
                                        ClientModel bodyClientModel = getBodyClientModel(m, availableModels);
                                        if (bodyClientModel == null) {
                                            logger.warn("client model not found for collection '{}', method '{}'", collection.getInterfaceType().getName(), m.getInnerClientMethod().getName());
                                        } else {
                                            ResourceCreate resourceCreate = new ResourceCreate(fluentModel, collection, urlPathSegments,
                                                    m.getInnerClientMethod().getName(), bodyClientModel);

                                            foundModels.put(fluentModel, resourceCreate);
                                        }
                                    }
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
        Optional<String> bodyTypeNameOpt = method.getInnerClientMethod().getProxyMethod().getParameters()
                .stream()
                .filter(p -> p.getRequestParameterLocation() == RequestParameterLocation.Body)
                .map(p -> p.getClientType().toString())
                .findAny();

        if (!bodyTypeNameOpt.isPresent()) {
            throw new IllegalStateException("body type not found for method " + method.getInnerClientMethod().getName());
        }

        Optional<ClientModel> clientModelOpt = availableModels.stream()
                .filter(model -> model.getName().equals(bodyTypeNameOpt.get()))
                .findAny();

        if (!clientModelOpt.isPresent()) {
            logger.warn("client model not found for type name '{}', method '{}'", bodyTypeNameOpt.get(), method.getInnerClientMethod().getName());
        }
        return clientModelOpt.orElse(null);
    }

    private static FluentCollectionMethod findCollectionMethod(FluentResourceCollection collection,
                                                               ResourceCreate resourceCreate,
                                                               HttpMethod matchingMethod, Predicate<String> nameMatcher) {
        boolean isGetOrDelete = matchingMethod == HttpMethod.GET || matchingMethod == HttpMethod.DELETE;
        boolean isDelete = matchingMethod == HttpMethod.DELETE;

        for (FluentCollectionMethod method : collection.getMethods()) {
            HttpMethod httpMethod = method.getInnerProxyMethod().getHttpMethod();
            // match http method
            if (httpMethod == matchingMethod) {
                String methodNameLowerCase = method.getInnerClientMethod().getName().toLowerCase(Locale.ROOT);
                // match name
                if (nameMatcher.test(methodNameLowerCase)) {
                    String returnTypeName = method.getFluentReturnType().toString();
                    // same model as create
                    if (isDelete || returnTypeName.equals(resourceCreate.getResourceModel().getInterfaceType().getName())) {
                        String url = method.getInnerProxyMethod().getUrlPath();
                        // same url as create
                        if (url.equals(resourceCreate.getUrlPathSegments().getPath())) {
                            boolean hasBodyParam = methodHasBodyParameter(method);
                            boolean hasRequiredQueryParam = method.getInnerProxyMethod().getParameters().stream()
                                    .anyMatch(p -> p.getRequestParameterLocation() == RequestParameterLocation.Query
                                            && p.getIsRequired()
                                            && !p.getFromClient() && !p.getIsConstant());
                            boolean hasNewNonConstantPathParam = method.getInnerProxyMethod().getParameters().stream()
                                    .anyMatch(p -> p.getRequestParameterLocation() == RequestParameterLocation.Path
                                            && !p.getIsConstant() && !p.getFromClient()
                                            && resourceCreate.getMethodReferences().stream().allMatch(
                                                    m -> m.getInnerProxyMethod().getParameters().stream().anyMatch(
                                                            p1 -> p1.getRequestParameterLocation() == RequestParameterLocation.Path
                                                                    && p1.getRequestParameterName().equals(p.getRequestParameterName())
                                                                    && p1.getIsConstant() && !p1.getFromClient())));
                            // if for update, need a body parameter
                            // if for get or delete, do not allow required query parameter (that not from client, and not constant), since it cannot be deduced from resource id
                            if ((isGetOrDelete && !hasRequiredQueryParam && !hasNewNonConstantPathParam)
                                    || (!isGetOrDelete && hasBodyParam)) {
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
                        || (m.getInnerClientMethod().getType() == ClientMethodType.SimpleSyncRestResponse && m.getInnerClientMethod().getName().equals(methodName + Utils.METHOD_POSTFIX_WITH_RESPONSE)))
                .filter(m -> m.getInnerProxyMethod().getHttpMethod() == HttpMethod.GET
                        || m.getInnerProxyMethod().getHttpMethod() == HttpMethod.DELETE
                        || methodHasBodyParameter(m))
                .collect(Collectors.toList());
    }

    private static boolean methodHasBodyParameter(FluentCollectionMethod method) {
        return method.getInnerProxyMethod().getParameters().stream()
                .filter(p -> nonSimpleJavaType(p.getClientType()))
                .anyMatch(p -> p.getRequestParameterLocation() == RequestParameterLocation.Body);
    }

    private static boolean nonSimpleJavaType(IType type) {
        boolean ret = false;
        if (type instanceof ClassType) {
            ClassType classType = (ClassType) type;
            ret = !classType.getPackage().startsWith("java");
        }
        return ret;
    }
}
