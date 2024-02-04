// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

import com.azure.autorest.Javagen;
import com.azure.autorest.extension.base.model.codemodel.KnownMediaType;
import com.azure.autorest.extension.base.model.codemodel.Language;
import com.azure.autorest.extension.base.model.codemodel.Languages;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.SchemaContext;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ClientModels;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ImplementationDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TypeUtil {

    private static final PluginLogger LOGGER = new PluginLogger(Javagen.getPluginInstance(), TypeUtil.class);

    private static final ConcurrentMap<String, Optional<Class<?>>> TYPE_CLASS_MAP = new ConcurrentHashMap<>();

    private TypeUtil() {
    }

    /**
     * Whether the given type is GenericType and is subclass of either of the given classes.
     * @param type the type to check
     * @param parentClasses classes to match either one
     * @return whether the given type is GenericType and is subclass of either of the given classes
     */
    public static boolean isGenericTypeClassSubclassOf(IType type, Class<?>... parentClasses) {
        if (!(type instanceof GenericType) || parentClasses == null || parentClasses.length == 0) return false;
        Class<?> genericClass = getGenericClass((GenericType) type);
        return genericClass != null && Arrays.stream(parentClasses).anyMatch(p -> p.isAssignableFrom(genericClass));
    }

    private static Class<?> getGenericClass(GenericType type) {
        String className = String.format("%s.%s", type.getPackage(), type.getName());
        return TYPE_CLASS_MAP.computeIfAbsent(className, key -> {
            try {
                return Optional.of(Class.forName(key));
            } catch (ClassNotFoundException e) {
                LOGGER.warn(String.format("class %s not found!", key), e);
                return Optional.empty();
            }
        }).orElse(null);
    }

    /**
     * Gets or creates a new FileDetails model for a multipart/form-data request
     *
     * @param compositeType the object schema of the multipart/form-data request model.
     * @param filePropertyName the property name of the file in the multipart/form-data request model.
     * @return the ##FileDetails model
     */
    public static IType getMultipartFileDetailsModel(
            ObjectSchema compositeType,
            String filePropertyName) {
        // TODO (weidxu): this ##FileDetails model may get renamed and moved to azure-core

        // The ##FileDetails model would inherit the usages from compositeType (the request model). So if the request is INTERNAL, FileDetails model would also be INTERNAL.
        // But it may reside in a different package, depending on the options e.g. "custom-types"/"custom-types-subpackage".

        String fileDetailsModelName = getFileDetailsModelName(filePropertyName);
        ClientModel clientModel = ClientModelUtil.getClientModel(fileDetailsModelName);
        if (clientModel != null) {
            return clientModel.getType();
        }

        // create ClassType
        ObjectSchema objectSchema = new ObjectSchema();
        objectSchema.setLanguage(new Languages());
        objectSchema.getLanguage().setJava(new Language());
        objectSchema.getLanguage().getJava().setName(fileDetailsModelName);
        // usages
        Set<SchemaContext> usages = compositeType.getUsage();
        // let it inherit the usage (PUBLIC/INTERNAL) from the multipart/form-data request model
        if (usages != null && usages.contains(SchemaContext.ANONYMOUS)) {
            // but, if the request model is ANONYMOUS, the FileDetails should not be ANONYMOUS
            usages = new HashSet<>(usages);
            usages.remove(SchemaContext.ANONYMOUS);
            usages.remove(SchemaContext.INTERNAL);
        }
        objectSchema.setUsage(usages);
        ClassType type = Mappers.getObjectMapper().map(objectSchema);

        // create ClientModel
        List<ClientModelProperty> properties = new ArrayList<>();
        properties.add(new ClientModelProperty.Builder()
                .name("content")
                .description("The content of the file")
                .required(true)
                .readOnly(false)
                .wireType(ClassType.BINARY_DATA)
                .clientType(ClassType.BINARY_DATA)
                .build());
        properties.add(new ClientModelProperty.Builder()
                .name("filename")
                .description("The filename of the file")
                .required(false)
                .readOnly(false)
                .wireType(ClassType.STRING)
                .clientType(ClassType.STRING)
                .build());
        properties.add(new ClientModelProperty.Builder()
                .name("contentType")
                .description("The content-type of the file")
                .required(false)
                .readOnly(false)
                .wireType(ClassType.STRING)
                .clientType(ClassType.STRING)
                .defaultValue("\"application/octet-stream\"")
                .build());
        clientModel = new ClientModel.Builder()
                .name(fileDetailsModelName)
                .description("The file details model for the " + filePropertyName)
                .packageName(type.getPackage())
                .type(type)
                .serializationFormats(Set.of(KnownMediaType.MULTIPART.value()))
                .implementationDetails(new ImplementationDetails.Builder()
                        .usages(SchemaUtil.mapSchemaContext(compositeType.getUsage()))
                        .build())
                .properties(properties)
                .build();
        ClientModels.getInstance().addModel(clientModel);
        return clientModel.getType();
    }

    private static String getFileDetailsModelName(String filePropertyName) {
        return com.azure.autorest.preprocessor.namer.CodeNamer.getTypeName(
                filePropertyName.toLowerCase(Locale.ROOT).endsWith("file")
                        ? filePropertyName + "Details"
                        : filePropertyName + "FileDetails");
    }

    /**
     * Gets the FileDetails model for a multipart/form-data request
     *
     * @param filePropertyName the property name of the file in the multipart/form-data request model.
     * @return the ##FileDetails model
     */
    public static IType getMultipartFileDetailsModel(String filePropertyName) {
        String fileDetailsModelName = getFileDetailsModelName(filePropertyName);
        return ClientModelUtil.getClientModel(fileDetailsModelName).getType();
    }
}
