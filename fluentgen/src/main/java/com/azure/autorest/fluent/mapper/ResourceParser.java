/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.fluent.model.ResourceTypeName;
import com.azure.autorest.fluent.model.arm.ModelCategory;
import com.azure.autorest.fluent.model.arm.UrlPathSegments;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceCreate;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.core.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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

                            if (urlPathSegments.getReverseSegments().iterator().next().isParameterSegment() && urlPathSegments.hasSubscription()) {
                                foundModels.add(fluentModel);
                                supportsCreateList.add(new ResourceCreate());
                                ModelCategory category;
                                if (urlPathSegments.isNested()) {
                                    category = ModelCategory.NESTED_CHILD;
                                } else if (urlPathSegments.hasResourceGroup()) {
                                    category = ModelCategory.RESOURCE_GROUP_AS_PARENT;
                                } else {
                                    category = ModelCategory.SUBSCRIPTION_AS_PARENT;
                                }
                                logger.info("name " + fluentModel.getInterfaceType().getName());
                                logger.info("path " + urlPathSegments.getReverseSegments());
                                logger.info("category " + category);
                            }
                        }
                    }
                }
            }
        });

        return supportsCreateList;
    }
}
