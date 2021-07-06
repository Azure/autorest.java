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
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.model.extensionmodel.XmsExtensions;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.model.FluentType;
import com.azure.autorest.fluent.model.ResourceType;
import com.azure.autorest.fluent.model.ResourceTypeName;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.fluentnamer.FluentNamer;
import com.azure.core.util.CoreUtils;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Normalizes the base resource types based on its base type and properties.
 */
class ResourceTypeNormalization {

    private static final Logger logger = new PluginLogger(FluentNamer.getPluginInstance(), ResourceTypeNormalization.class);

    public CodeModel process(CodeModel codeModel) {
        codeModel.getSchemas().getObjects().forEach(compositeType -> {
            Optional<ObjectSchema> parentType = getObjectParent(compositeType);
            if (parentType.isPresent()) {
                getSchemaResourceType(parentType.get())
                        .ifPresent(type -> adaptForParentSchema(compositeType, type));

                if (FluentType.SystemData.getName().equals(Utils.getJavaName(parentType.get()))) {
                    adaptAsSystemData(compositeType);
                }
            } else {
                if (compositeType.getExtensions() != null && compositeType.getExtensions().isXmsAzureResource()) {
                    tryAdaptAsResource(compositeType);
                }
            }
        });

        return codeModel;
    }

    public static ObjectSchema subResourceSchema() {
        return DUMMY_SUB_RESOURCE;
    }

    private static final Set<String> SUB_RESOURCE_FIELDS = new HashSet<>(Arrays.asList(ResourceTypeName.FIELD_ID));
    private static final Set<String> PROXY_RESOURCE_FIELDS = new HashSet<>(Arrays.asList(ResourceTypeName.FIELD_ID, ResourceTypeName.FIELD_NAME, ResourceTypeName.FIELD_TYPE));
    private static final Set<String> RESOURCE_FIELDS = new HashSet<>(Arrays.asList(ResourceTypeName.FIELD_ID, ResourceTypeName.FIELD_NAME, ResourceTypeName.FIELD_TYPE, ResourceTypeName.FIELD_LOCATION, ResourceTypeName.FIELD_TAGS));

    private static final Set<String> RESOURCE_EXTRA_FIELDS = new HashSet<>(Arrays.asList(ResourceTypeName.FIELD_LOCATION, ResourceTypeName.FIELD_TAGS));

    private static final ObjectSchema DUMMY_SUB_RESOURCE = dummyResourceSchema(ResourceTypeName.SUB_RESOURCE);
    private static final ObjectSchema DUMMY_PROXY_RESOURCE = dummyResourceSchema(ResourceTypeName.PROXY_RESOURCE);
    private static final ObjectSchema DUMMY_RESOURCE = dummyResourceSchema(ResourceTypeName.RESOURCE);

    private static ObjectSchema dummyResourceSchema(String javaName) {
        ObjectSchema schema = new ObjectSchema();
        schema.setLanguage(new Languages());
        schema.getLanguage().setJava(new Language());
        schema.getLanguage().getJava().setName(javaName);
        schema.setExtensions(new XmsExtensions());
        schema.getExtensions().setXmsAzureResource(true);
        return schema;
    }

    private static Optional<ObjectSchema> getObjectParent(ObjectSchema compositeType) {
        if (compositeType.getParents() == null || compositeType.getParents().getImmediate() == null) {
            return Optional.empty();
        } else {
            return compositeType.getParents().getImmediate().stream()
                    .filter(s -> s instanceof ObjectSchema)
                    .map(s -> (ObjectSchema) s)
                    .findFirst();
        }
    }

    private static void tryAdaptAsResource(ObjectSchema compositeType) {
        if (!getSchemaResourceType(compositeType).isPresent()) {
            if (hasProperties(compositeType, RESOURCE_FIELDS)) {
                addDummyParentType(compositeType, DUMMY_RESOURCE);

                compositeType.getProperties().removeIf(p -> (PROXY_RESOURCE_FIELDS.contains(p.getSerializedName()) && p.isReadOnly())
                        || RESOURCE_EXTRA_FIELDS.contains(p.getSerializedName()));

                logger.info("Add parent Resource, for '{}'", Utils.getJavaName(compositeType));
            } else if (hasProperties(compositeType, PROXY_RESOURCE_FIELDS)) {
                addDummyParentType(compositeType, DUMMY_PROXY_RESOURCE);

                compositeType.getProperties().removeIf(p -> PROXY_RESOURCE_FIELDS.contains(p.getSerializedName()) && p.isReadOnly());

                logger.info("Add parent ProxyResource, for '{}'", Utils.getJavaName(compositeType));
            }
        }
    }

    private static void adaptAsSystemData(ObjectSchema compositeType) {
        String previousName = Utils.getJavaName(compositeType);
        compositeType.getLanguage().getJava().setName(FluentType.SystemData.getName());

        logger.info("Rename system data from '{}' to 'SystemData'", previousName);

        if (CoreUtils.isNullOrEmpty(compositeType.getProperties())) {
            logger.warn("Ignored properties {}, for {}",
                    compositeType.getProperties().stream().map(Utils::getJavaName).collect(Collectors.toList()),
                    previousName);
        }
    }

//    private static Optional<ResourceType> getParentSchemaResourceType(ObjectSchema compositeType, ObjectSchema parantType) {
//        return getSchemaResourceType(parantType);
//    }

    private static Optional<ResourceType> getSchemaResourceType(ObjectSchema compositeType) {
        ResourceType type = null;

        switch (Utils.getJavaName(compositeType)) {
            case ResourceTypeName.SUB_RESOURCE:
            case ResourceTypeName.SUB_RESOURCE_AUTO_GENERATED:
                type = ResourceType.SUB_RESOURCE;
                break;
            case ResourceTypeName.PROXY_RESOURCE:
            case ResourceTypeName.PROXY_RESOURCE_AUTO_GENERATED:
                type = ResourceType.PROXY_RESOURCE;
                break;
            case ResourceTypeName.TRACKED_RESOURCE:
            case ResourceTypeName.TRACKED_RESOURCE_AUTO_GENERATED:
                type = ResourceType.RESOURCE;
                break;
            case ResourceTypeName.RESOURCE:
            case ResourceTypeName.RESOURCE_AUTO_GENERATED:
            {
                if (hasProperties(compositeType, RESOURCE_EXTRA_FIELDS)) {
                    type = ResourceType.RESOURCE;
                } else if (hasProperties(compositeType, PROXY_RESOURCE_FIELDS)) {
                    type = ResourceType.PROXY_RESOURCE;
                } else if (hasProperties(compositeType, SUB_RESOURCE_FIELDS)) {
                    type = ResourceType.SUB_RESOURCE;
                }
                break;
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
                        .filter(p -> !SUB_RESOURCE_FIELDS.contains(p.getSerializedName()))
                        .collect(Collectors.toList());
                compositeType.getProperties().addAll(extraProperties);
                break;
            }
            case PROXY_RESOURCE:
            {
                List<Property> extraProperties = parentType.getProperties().stream()
                        .filter(p -> !PROXY_RESOURCE_FIELDS.contains(p.getSerializedName()))
                        .collect(Collectors.toList());
                compositeType.getProperties().addAll(extraProperties);

                List<Property> mutableProperties = parentType.getProperties().stream()
                        .filter(p -> PROXY_RESOURCE_FIELDS.contains(p.getSerializedName()))
                        .filter(p -> !p.isReadOnly())
                        .collect(Collectors.toList());
                compositeType.getProperties().addAll(mutableProperties);
                break;
            }
            case RESOURCE:
            {
                List<Property> extraProperties = parentType.getProperties().stream()
                        .filter(p -> !RESOURCE_FIELDS.contains(p.getSerializedName()))
                        .collect(Collectors.toList());
                compositeType.getProperties().addAll(extraProperties);

                // extra 2 properties in Resource is defined as mutable. So only check for properties in ProxyResource.
                List<Property> mutableProperties = parentType.getProperties().stream()
                        .filter(p -> PROXY_RESOURCE_FIELDS.contains(p.getSerializedName()))
                        .filter(p -> !p.isReadOnly())
                        .collect(Collectors.toList());
                compositeType.getProperties().addAll(mutableProperties);
                break;
            }
        }

        if (!type.getClassName().equals(Utils.getJavaName(parentType))) {
            switch (type) {
                case RESOURCE:
                {
                    replaceDummyParentType(compositeType, DUMMY_RESOURCE);
                    break;
                }
               case PROXY_RESOURCE:
               {
                   replaceDummyParentType(compositeType, DUMMY_PROXY_RESOURCE);
                   break;
               }
                case SUB_RESOURCE:
                {
                    replaceDummyParentType(compositeType, DUMMY_SUB_RESOURCE);
                    break;
                }
            }

            logger.info("Change parent from '{}' to '{}', for '{}'", Utils.getJavaName(parentType), type.getClassName(), Utils.getJavaName(compositeType));
        }
    }

    private static void addDummyParentType(ObjectSchema compositeType, ObjectSchema parentType) {
        if (compositeType.getParents() == null) {
            compositeType.setParents(new Relations());
        }
        if (compositeType.getParents().getImmediate() == null) {
            compositeType.getParents().setImmediate(new ArrayList<>());
        }
        if (compositeType.getParents().getAll() == null) {
            compositeType.getParents().setAll(new ArrayList<>());
        }
        compositeType.getParents().getImmediate().add(parentType);
        compositeType.getParents().getAll().add(parentType);
    }

    private static void replaceDummyParentType(ObjectSchema compositeType, ObjectSchema parentType) {
        ObjectSchema currentParentType = getObjectParent(compositeType).get();

        // remove parent from type
        Iterator<Schema> itor = compositeType.getParents().getImmediate().iterator();
        while (itor.hasNext()) {
            Schema type = itor.next();
            if (type == currentParentType) {
                itor.remove();
                break;
            }
        }
        itor = compositeType.getParents().getAll().iterator();
        while (itor.hasNext()) {
            Schema type = itor.next();
            if (type == currentParentType) {
                itor.remove();
                break;
            }
        }

        // remove type from parent
        if (currentParentType.getChildren() != null) {
            if (currentParentType.getChildren().getImmediate() != null) {
                itor = currentParentType.getChildren().getImmediate().iterator();
                while (itor.hasNext()) {
                    Schema type = itor.next();
                    if (type == compositeType) {
                        itor.remove();
                        break;
                    }
                }
            }
            if (currentParentType.getChildren().getAll() != null) {
                itor = currentParentType.getChildren().getAll().iterator();
                while (itor.hasNext()) {
                    Schema type = itor.next();
                    if (type == compositeType) {
                        itor.remove();
                        break;
                    }
                }
            }
        }

        // add parent type
        addDummyParentType(compositeType, parentType);
    }

    private static boolean hasProperties(ObjectSchema compositeType, Set<String> fieldNames) {
        if (compositeType.getProperties() == null) {
            return false;
        }
        return compositeType.getProperties().stream().map(Property::getSerializedName).collect(Collectors.toSet()).containsAll(fieldNames);
    }
}
