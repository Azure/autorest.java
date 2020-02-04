/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 *
 */

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.Language;
import com.azure.autorest.extension.base.model.codemodel.Languages;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.extension.base.model.codemodel.Relations;
import com.azure.autorest.fluent.model.ResourceType;
import com.azure.autorest.fluent.model.ResourceTypeName;
import com.azure.autorest.fluent.util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

class ResourceTypeNormalization {

    public CodeModel process(CodeModel codeModel) {
        codeModel.getSchemas().getObjects().forEach(compositeType -> {
            if (compositeType.getParents() == null) {
                if (compositeType.getExtensions() != null && compositeType.getExtensions().isXmsAzureResource()) {
                    tryAdaptAsResource(compositeType);
                }
            } else {
                getParentSchemaResourceType(compositeType).ifPresent(type -> adaptForParentSchema(compositeType, type));
            }
        });

        return codeModel;
    }

    private static final Set<String> SUB_RESOURCE_FIELDS = new HashSet<>(Arrays.asList(ResourceTypeName.FIELD_ID));
    private static final Set<String> PROXY_RESOURCE_FIELDS = new HashSet<>(Arrays.asList(ResourceTypeName.FIELD_ID, ResourceTypeName.FIELD_NAME, ResourceTypeName.FIELD_TYPE));
    private static final Set<String> RESOURCE_FIELDS = new HashSet<>(Arrays.asList(ResourceTypeName.FIELD_ID, ResourceTypeName.FIELD_NAME, ResourceTypeName.FIELD_TYPE, ResourceTypeName.FIELD_LOCATION, ResourceTypeName.FIELD_TAGS));

    private static final ObjectSchema DUMMY_PROXY_RESOURCE = new ObjectSchema();
    private static final ObjectSchema DUMMY_RESOURCE = new ObjectSchema();
    static {
        DUMMY_PROXY_RESOURCE.setLanguage(new Languages());
        DUMMY_PROXY_RESOURCE.getLanguage().setJava(new Language());
        DUMMY_PROXY_RESOURCE.getLanguage().getJava().setName(ResourceTypeName.PROXY_RESOURCE);

        DUMMY_RESOURCE.setLanguage(new Languages());
        DUMMY_RESOURCE.getLanguage().setJava(new Language());
        DUMMY_RESOURCE.getLanguage().getJava().setName(ResourceTypeName.RESOURCE);
    };

    private static void tryAdaptAsResource(ObjectSchema compositeType) {
        if (hasProperties(compositeType, RESOURCE_FIELDS)) {
            compositeType.setParents(new Relations());
            compositeType.getParents().setImmediate(new ArrayList<>());
            compositeType.getParents().getImmediate().add(DUMMY_RESOURCE);

            compositeType.getProperties().removeIf(p -> (PROXY_RESOURCE_FIELDS.contains(Utils.getJavaName(p)) && p.isReadOnly())
                    || ResourceTypeName.FIELD_LOCATION.equals(Utils.getJavaName(p))
                    || ResourceTypeName.FIELD_TAGS.equals(Utils.getJavaName(p)));
        } else if (hasProperties(compositeType, PROXY_RESOURCE_FIELDS)) {
            compositeType.setParents(new Relations());
            compositeType.getParents().setImmediate(new ArrayList<>());
            compositeType.getParents().getImmediate().add(DUMMY_PROXY_RESOURCE);

            compositeType.getProperties().removeIf(p -> PROXY_RESOURCE_FIELDS.contains(Utils.getJavaName(p)) && p.isReadOnly());
        }
    }

    private static Optional<ResourceType> getParentSchemaResourceType(ObjectSchema compositeType) {
        ResourceType type = null;

        if (compositeType.getParents() != null && compositeType.getParents().getImmediate() != null
                && compositeType.getParents().getImmediate().get(0) instanceof ObjectSchema) {
            ObjectSchema parentType = (ObjectSchema) compositeType.getParents().getImmediate().get(0);
            switch (Utils.getJavaName(parentType)) {
                case ResourceTypeName.SUB_RESOURCE:
                    type = ResourceType.SUB_RESOURCE;
                    break;
                case ResourceTypeName.PROXY_RESOURCE:
                    type = ResourceType.PROXY_RESOURCE;
                    break;
                case ResourceTypeName.TRACKED_RESOURCE:
                    type = ResourceType.RESOURCE;
                    break;
                case ResourceTypeName.RESOURCE:
                {
                    if (hasProperties(parentType, RESOURCE_FIELDS)) {
                        type = ResourceType.RESOURCE;
                    } else if (hasProperties(parentType, PROXY_RESOURCE_FIELDS)) {
                        type = ResourceType.PROXY_RESOURCE;
                    } else if (hasProperties(parentType, SUB_RESOURCE_FIELDS)) {
                        type = ResourceType.SUB_RESOURCE;
                    }
                    break;
                }
            }
        }

        return Optional.ofNullable(type);
    }

    private static void adaptForParentSchema(ObjectSchema compositeType, ResourceType type) {
        ObjectSchema parentType = (ObjectSchema) compositeType.getParents().getImmediate().get(0);
        switch (type) {
            case SUB_RESOURCE:
            {
                List<Property> extraProperties = parentType.getProperties().stream()
                        .filter(p -> !SUB_RESOURCE_FIELDS.contains(Utils.getJavaName(p)))
                        .collect(Collectors.toList());
                compositeType.getProperties().addAll(extraProperties);
                break;
            }
            case PROXY_RESOURCE:
            {
                List<Property> extraProperties = parentType.getProperties().stream()
                        .filter(p -> !PROXY_RESOURCE_FIELDS.contains(Utils.getJavaName(p)))
                        .collect(Collectors.toList());
                compositeType.getProperties().addAll(extraProperties);

                List<Property> mutableProperties = parentType.getProperties().stream()
                        .filter(p -> PROXY_RESOURCE_FIELDS.contains(Utils.getJavaName(p)))
                        .filter(p -> !p.isReadOnly())
                        .collect(Collectors.toList());
                compositeType.getProperties().addAll(mutableProperties);
                break;
            }
            case RESOURCE:
            {
                List<Property> extraProperties = parentType.getProperties().stream()
                        .filter(p -> !RESOURCE_FIELDS.contains(Utils.getJavaName(p)))
                        .collect(Collectors.toList());
                compositeType.getProperties().addAll(extraProperties);

                // extra 2 properties in Resource is defined as mutable. So only check for properties in ProxyResource.
                List<Property> mutableProperties = parentType.getProperties().stream()
                        .filter(p -> PROXY_RESOURCE_FIELDS.contains(Utils.getJavaName(p)))
                        .filter(p -> !p.isReadOnly())
                        .collect(Collectors.toList());
                compositeType.getProperties().addAll(mutableProperties);
                break;
            }
        }
    }

    private static boolean hasProperties(ObjectSchema compositeType, Set<String> fieldNames) {
        if (compositeType.getProperties() == null) {
            return false;
        }
        return compositeType.getProperties().stream().map(Utils::getJavaName).collect(Collectors.toSet()).containsAll(fieldNames);
    }
}
