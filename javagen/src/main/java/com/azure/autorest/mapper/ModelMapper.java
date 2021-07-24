// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ArraySchema;
import com.azure.autorest.extension.base.model.codemodel.DictionarySchema;
import com.azure.autorest.extension.base.model.codemodel.Language;
import com.azure.autorest.extension.base.model.codemodel.Languages;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.model.codemodel.XmlSerlializationFormat;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ClientModelPropertyReference;
import com.azure.autorest.model.clientmodel.ClientModels;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.SchemaUtil;
import com.azure.core.util.CoreUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModelMapper implements IMapper<ObjectSchema, ClientModel> {
    private static ModelMapper instance = new ModelMapper();
    private ClientModels serviceModels = ClientModels.Instance;

    protected ModelMapper() {
    }

    public static ModelMapper getInstance() {
        return instance;
    }

    @Override
    public ClientModel map(ObjectSchema compositeType) {
        JavaSettings settings = JavaSettings.getInstance();
        ObjectMapper objectMapper = Mappers.getObjectMapper();

        ClassType modelType = objectMapper.map(compositeType);
        String modelName = modelType.getName();
        ClientModel result = serviceModels.getModel(modelType.getName());
        if (result == null && !ObjectMapper.isPlainObject(compositeType) && (!settings.isFluent() || !isPredefinedModel(modelType))) {
            ClientModel.Builder builder = createModelBuilder()
                    .name(modelName)
                    .packageName(modelType.getPackage())
                    .type(modelType);

            boolean isPolymorphic = compositeType.getDiscriminator() != null || compositeType.getDiscriminatorValue() != null;
            builder.isPolymorphic(isPolymorphic);

            HashSet<String> modelImports = new HashSet<>();

            String parentModelName = null;
            boolean hasAdditionalProperties = false;
            List<ObjectSchema> parentsNeedFlatten = new ArrayList<>();
            ObjectSchema firstParentComplexSchema = null;
            if (compositeType.getParents() != null && compositeType.getParents().getImmediate() != null) {
                hasAdditionalProperties = compositeType.getParents().getImmediate().stream()
                        .anyMatch(s -> s instanceof DictionarySchema);

                for (Schema parent : compositeType.getParents().getImmediate()) {
                    if (parent instanceof ObjectSchema) {
                        if (firstParentComplexSchema == null) {
                            firstParentComplexSchema = (ObjectSchema) parent;
                        } else {
                            parentsNeedFlatten.add((ObjectSchema) parent);
                        }
                    }
                }

                if (firstParentComplexSchema != null) {
                    ClassType parentType = objectMapper.map(firstParentComplexSchema);
                    parentModelName = parentType.getName();
                    modelImports.add(parentType.getPackage() + "." + parentModelName);
                }
            }
            builder.parentModelName(parentModelName);

            List<Property> compositeTypeProperties = compositeType.getProperties()
                    .stream().filter(p -> !p.isIsDiscriminator()).collect(Collectors.toList());
            if (!parentsNeedFlatten.isEmpty()) {
                // Take properties from base class of multiple inheritance as properties of this class.
                for (ObjectSchema parent : parentsNeedFlatten) {
                    compositeTypeProperties.addAll(parent.getProperties().stream()
                            .filter(p -> !p.isIsDiscriminator())
                            .collect(Collectors.toList()));
                    if (parent.getParents() != null) {
                        compositeTypeProperties.addAll(parent.getParents().getAll().stream()
                                .filter(s -> s instanceof ObjectSchema)
                                .flatMap(s -> ((ObjectSchema) s).getProperties().stream())
                                .filter(p -> !p.isIsDiscriminator())
                                .collect(Collectors.toList()));
                    }
                }
            }
            for (Property autoRestProperty : compositeTypeProperties) {
                IType propertyType = Mappers.getSchemaMapper().map(autoRestProperty.getSchema());
                if (!autoRestProperty.isRequired()) {
                    propertyType = propertyType.asNullable();
                }
                propertyType.addImportsTo(modelImports, false);

                IType propertyClientType = Mappers.getSchemaMapper().map(autoRestProperty.getSchema()).getClientType();
                propertyClientType.addImportsTo(modelImports, false);
            }

            if (!compositeTypeProperties.isEmpty()) {
                if (settings.shouldGenerateXmlSerialization()) {
                    modelImports.add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement");

                    if (compositeTypeProperties.stream().anyMatch(p -> p.getSchema() instanceof ArraySchema)) {
                        modelImports.add("java.util.ArrayList");
                    }

                    if (compositeTypeProperties.stream().anyMatch(p -> {
                        if (p.getSchema().getSerialization() == null || p.getSchema().getSerialization().getXml() == null) {
                            return false;
                        }

                        XmlSerlializationFormat xmlSchema = p.getSchema().getSerialization().getXml();
                        return xmlSchema.isAttribute() || xmlSchema.getNamespace() != null;
                    })) {
                        modelImports.add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty");
                    }

                    if (compositeTypeProperties.stream().anyMatch(p -> p.getSchema().getSerialization() == null
                        || p.getSchema().getSerialization().getXml() == null || !p.getSchema().getSerialization()
                        .getXml().isAttribute())) {
                        modelImports.add("com.fasterxml.jackson.annotation.JsonProperty");
                    }

                    if (compositeTypeProperties.stream().anyMatch(p -> p.getSchema().getSerialization() != null
                        && p.getSchema().getSerialization().getXml() != null && p.getSchema().getSerialization().getXml().isWrapped())) {
                        modelImports.add("com.fasterxml.jackson.annotation.JsonCreator");
                    }

                } else {
                    modelImports.add("com.fasterxml.jackson.annotation.JsonProperty");
                }
            }
            builder.imports(new ArrayList<>(modelImports));
            if (hasAdditionalProperties) {
                for (Property property : compositeTypeProperties) {
                    if (property.getLanguage().getJava().getName().equals("additionalProperties")) {
                        property.getLanguage().getJava().setName("additionalPropertiesProperty");
                    }
                }
            }

            if (compositeType.getLanguage().getDefault() == null
                    || CoreUtils.isNullOrEmpty(compositeType.getLanguage().getDefault().getDescription())) {
                builder.description(String.format("The %s model.", compositeType.getLanguage().getJava().getName()));
            } else {
                builder.description(compositeType.getLanguage().getDefault().getDescription());
            }

            String modelSerializedName = compositeType.getDiscriminatorValue();
            if (modelSerializedName == null && compositeType.getLanguage().getDefault() != null) {
                modelSerializedName = compositeType.getLanguage().getDefault().getName();
            }
            builder.serializedName(modelSerializedName);

            List<ClientModel> derivedTypes = new ArrayList<>();
            if (compositeType.getChildren() != null && compositeType.getChildren().getImmediate() != null) {
                for (Schema childSchema : compositeType.getChildren().getImmediate()) {
                    if (childSchema instanceof ObjectSchema) {
                        ClientModel model = this.map((ObjectSchema) childSchema);
                        derivedTypes.add(model);
                    } else {
                        throw new RuntimeException("Wait what? How? Child is not an object but a " + childSchema.getClass() + "?");
                    }
                }
            }
            builder.derivedModels(derivedTypes);

            if (compositeType.getSerialization() != null && compositeType.getSerialization().getXml() != null) {
                final XmlSerlializationFormat xml = compositeType.getSerialization().getXml();
                 builder.xmlName(xml.getName());
                 builder.xmlNamespace(xml.getNamespace());
            } else if (compositeType.getLanguage().getDefault() != null) {
                 builder.xmlName(compositeType.getLanguage().getDefault().getName());
            }

            boolean needsFlatten = false;
            if (settings.getClientFlattenAnnotationTarget() == JavaSettings.ClientFlattenAnnotationTarget.TYPE) {
                needsFlatten = hasFlattenedProperty(compositeType, parentsNeedFlatten);
                if (isPolymorphic) {
                    String discriminatorSerializedName = SchemaUtil.getDiscriminatorSerializedName(compositeType);
                    // OR the need flattening based on the model containing 'x-ms-flattened' and if the discriminator
                    // contains '.' and 'x-ms-flattened' isn't required for flattening.
                    needsFlatten |= (discriminatorSerializedName.contains(".") && !settings.requireXMsFlattenedToFlatten());
                }
            }
            if (isPolymorphic) {
                String discriminatorSerializedName = SchemaUtil.getDiscriminatorSerializedName(compositeType);
                // Only escape the discriminator if the model will be flattened.
                builder.polymorphicDiscriminator(needsFlatten
                        ? discriminatorSerializedName.replace(".", "\\\\.")
                        : discriminatorSerializedName);
            }

            builder.needsFlatten(needsFlatten);

            List<ClientModelProperty> properties = new ArrayList<>();
            List<ClientModelPropertyReference> propertyReferences = new ArrayList<>();
            for (Property property : compositeTypeProperties) {
                ClientModelProperty modelProperty = Mappers.getModelPropertyMapper().map(property);
                properties.add(modelProperty);

                if (modelProperty.getClientFlatten() && settings.getClientFlattenAnnotationTarget() == JavaSettings.ClientFlattenAnnotationTarget.NONE) {
                    ObjectSchema targetModelSchema = (ObjectSchema) property.getSchema();
                    String originalFlattenedPropertyName = property.getLanguage().getJava().getName();  // not modelProperty.getName()
                    ClientModel targetModel = this.map(targetModelSchema);
                    if (targetModel != null && !CoreUtils.isNullOrEmpty(targetModel.getProperties())) {
                        // gather this type and its parents
                        List<ObjectSchema> objectSchemaAndParents = new ArrayList<>();
                        objectSchemaAndParents.add(compositeType);
                        if (compositeType.getParents() != null && compositeType.getParents().getAll() != null) {
                            objectSchemaAndParents.addAll(
                                    compositeType.getParents().getAll().stream()
                                            .filter(p -> p instanceof ObjectSchema)
                                            .map(p -> (ObjectSchema) p)
                                            .collect(Collectors.toList()));
                        }
                        // gather property names
                        Set<String> propertyNames = objectSchemaAndParents.stream()
                                .flatMap(o -> o.getProperties().stream())
                                .filter(p -> p.getExtensions() == null || !p.getExtensions().isXmsClientFlatten())
                                .map(p -> p.getLanguage().getJava().getName())
                                .collect(Collectors.toSet());

                        for (ClientModelProperty referenceProperty : targetModel.getProperties()) {
                            String name = disambiguatePropertyNameOfFlattenedSchema(propertyNames, originalFlattenedPropertyName, referenceProperty.getName());
                            propertyReferences.add(ClientModelPropertyReference.ofFlattenProperty(modelProperty, targetModel, referenceProperty, name));
                        }
                        // properties from its parents
                        if (targetModelSchema.getParents() != null && !CoreUtils.isNullOrEmpty(targetModelSchema.getParents().getAll())) {
                            // take 1st immediate parent of the target model, as rest parents (if any) is already flattened into the target model
                            targetModelSchema.getParents().getImmediate().stream()
                                    .filter(o -> o instanceof ObjectSchema)
                                    .map(o -> (ObjectSchema) o)
                                    .findFirst()
                                    // then that parent, and all of its parents
                                    .ifPresent(parentSchema -> Stream.concat(
                                            Stream.of(parentSchema),
                                            parentSchema.getParents() != null && parentSchema.getParents().getAll() != null
                                                    ? parentSchema.getParents().getAll().stream()
                                                    : Stream.empty())
                                            .filter(o -> o instanceof ObjectSchema)
                                            .map(o -> (ObjectSchema) o)
                                            .forEach(parentObjectSchema -> {
                                                if (parentObjectSchema.getProperties() != null) {
                                                    for (Property property1 : parentObjectSchema.getProperties()) {
                                                        ClientModelProperty referenceProperty1 = Mappers.getModelPropertyMapper().map(property1);
                                                        String name = disambiguatePropertyNameOfFlattenedSchema(propertyNames, originalFlattenedPropertyName, referenceProperty1.getName());
                                                        propertyReferences.add(ClientModelPropertyReference.ofFlattenProperty(modelProperty, targetModel, referenceProperty1, name));
                                                    }
                                                }
                                            }));
                        }
                    }
                }
            }

            if (hasAdditionalProperties) {
                DictionarySchema schema = (DictionarySchema) compositeType.getParents().getImmediate().stream()
                        .filter(s -> s instanceof DictionarySchema)
                        .findFirst().get();
                Property additionalProperties = new Property();
                additionalProperties.setReadOnly(false);
                additionalProperties.setSchema(schema);
                additionalProperties.setSerializedName("");

                additionalProperties.setLanguage(new Languages());
                additionalProperties.getLanguage().setJava(new Language());
                additionalProperties.getLanguage().getJava().setName("additionalProperties");
                additionalProperties.getLanguage().getJava().setDescription(schema.getLanguage().getJava().getDescription());

                properties.add(Mappers.getModelPropertyMapper().map(additionalProperties));
            }
            builder.properties(properties);
            builder.propertyReferences(propertyReferences);

            result = builder.build();
            serviceModels.addModel(result);
        }

        return result;
    }

    protected ClientModel.Builder createModelBuilder() {
        return new ClientModel.Builder();
    }

    private static boolean hasFlattenedProperty(ObjectSchema compositeType, Collection<ObjectSchema> parentsNeedFlatten) {
        boolean ret = compositeType.getProperties().stream()
                .anyMatch(p -> p.getFlattenedNames() != null && !p.getFlattenedNames().isEmpty());
        if (!ret && !parentsNeedFlatten.isEmpty()) {
            // Check properties from base class of multiple inheritance as properties of this class.
            ret = parentsNeedFlatten.stream()
                    .flatMap(s -> (s.getParents() != null && s.getParents().getAll() != null) ? Stream.concat(Stream.of(s), s.getParents().getAll().stream()) : Stream.of(s))
                    .filter(s -> s instanceof ObjectSchema)
                    .flatMap(s -> ((ObjectSchema) s).getProperties().stream())
                    .anyMatch(p -> p.getFlattenedNames() != null && !p.getFlattenedNames().isEmpty());
        }
        return ret;
    }

    /**
     * Extension for Fluent predefined type.
     *
     * @param compositeType object type
     * @return Whether the type is predefined.
     */
    protected boolean isPredefinedModel(ClassType compositeType) {
        return false;
    }

    private static String disambiguatePropertyNameOfFlattenedSchema(Set<String> propertyNames, String originalFlattenedPropertyName, String propertyName) {
        String ret = propertyName;
        if (propertyNames.contains(propertyName)) {
            // follow pattern from m4
            ret = propertyName + CodeNamer.toPascalCase(originalFlattenedPropertyName) + CodeNamer.toPascalCase(propertyName);
        }
        return ret;
    }
}
