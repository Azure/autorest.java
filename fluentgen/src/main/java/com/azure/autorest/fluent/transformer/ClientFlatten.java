/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.fluent.FluentJavaSettings;
import com.azure.autorest.fluent.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class ClientFlatten {

    private final static Logger logger = LoggerFactory.getLogger(ClientFlatten.class);

    private final FluentJavaSettings fluentJavaSettings;

    public ClientFlatten(FluentJavaSettings fluentJavaSettings) {
        this.fluentJavaSettings = fluentJavaSettings;
    }

    public CodeModel process(CodeModel codeModel) {
        Set<ObjectSchema> typesToDelete = new HashSet<>();

        Set<FluentJavaSettings.ClientFlattenProperty> addedClientFlattenProperties = fluentJavaSettings.getJavaNamesForClientFlatten();

        if (codeModel.getSchemas().getObjects() != null) {
            for (ObjectSchema compositeType : codeModel.getSchemas().getObjects()) {
                if (hasPropertyToFlatten(compositeType, addedClientFlattenProperties)) {
                    List<Property> properties = new ArrayList<>();

                    for (Property property : compositeType.getProperties()) {
                        if (property.getSchema() instanceof ObjectSchema) {
                            ObjectSchema innerType = (ObjectSchema) property.getSchema();
                            if (hasClientFlattenExtension(innerType)
                                    || (addedClientFlattenProperties != null && addedClientFlattenProperties.contains(new FluentJavaSettings.ClientFlattenProperty(Utils.getJavaName(compositeType), property.getSerializedName())))) {
                                properties.addAll(flattenProperty(property, innerType));
                                typesToDelete.add(innerType);
                            } else {
                                properties.add(property);
                            }
                        } else {
                            properties.add(property);
                        }
                    }

                    compositeType.setProperties(properties);
                }
            }
        }

        if (!typesToDelete.isEmpty()) {
            List<ObjectSchema> newObjectSchemas = codeModel.getSchemas().getObjects().stream()
                    .filter(s -> !typesToDelete.contains(s))
                    .collect(Collectors.toList());
            codeModel.getSchemas().setObjects(newObjectSchemas);
        }

        return codeModel;
    }

    private static boolean hasPropertyToFlatten(ObjectSchema compositeType, Set<FluentJavaSettings.ClientFlattenProperty> addedClientFlattenProperties) {
        List<Property> properties = compositeType.getProperties();
        if (properties != null) {
            for (Property property : properties) {
                if (property.getSchema() instanceof ObjectSchema) {
                    ObjectSchema innerType = (ObjectSchema) property.getSchema();
                    if (hasClientFlattenExtension(innerType)
                            || (addedClientFlattenProperties != null && addedClientFlattenProperties.contains(new FluentJavaSettings.ClientFlattenProperty(Utils.getJavaName(compositeType), property.getSerializedName())))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean hasClientFlattenExtension(ObjectSchema compositeType) {
        return compositeType.getExtensions() != null && compositeType.getExtensions().isXmsClientFlatten();
    }

    private static List<Property> flattenProperty(Property property, ObjectSchema compositeType) {
        List<Property> properties = new ArrayList<>();
        for (Property p : compositeType.getProperties()) {
            Property newProperty = shallowCopy(p);
            updateSerializedName(newProperty, property.getSerializedName());
            properties.add(newProperty);
        }
        return properties;
    }

    private static void updateSerializedName(Property property, String parentName) {
        property.setSerializedName(parentName + "." + property.getSerializedName());
    }

    private static Property shallowCopy(Property property) {
        Property newProperty = new Property();
        Utils.shallowCopy(property, newProperty, Property.class, logger);
        return newProperty;
    }
}
