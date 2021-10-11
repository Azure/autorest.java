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
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
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
            if (hasAdditionalProperties) {
                for (Property property : compositeTypeProperties) {
                    if (property.getLanguage().getJava().getName().equals("additionalProperties")) {
                        property.getLanguage().getJava().setName("additionalPropertiesProperty");
                    }
                }
            }

            String summary = compositeType.getSummary();
            String description = compositeType.getLanguage().getJava() == null ? null : compositeType.getLanguage().getJava().getDescription();
            if (CoreUtils.isNullOrEmpty(summary) && CoreUtils.isNullOrEmpty(description)) {
                builder.description(String.format("The %s model.", compositeType.getLanguage().getJava().getName()));
            } else {
                builder.description(SchemaUtil.mergeDescription(summary, description));
            }

            String modelSerializedName = compositeType.getDiscriminatorValue();
            if (modelSerializedName == null && compositeType.getLanguage().getDefault() != null) {
                modelSerializedName = compositeType.getLanguage().getDefault().getName();
            }
            builder.serializedName(modelSerializedName);

            List<ClientModel> derivedTypes = new ArrayList<>();
            boolean hasChildren = compositeType.getChildren() != null && compositeType.getChildren().getImmediate() != null;
            if (hasChildren) {
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

            List<ClientModelProperty> properties = new ArrayList<>();

            boolean needsFlatten = false;
            if (settings.getModelerSettings().isFlattenModel()  // enabled by modelerfour
                    && settings.getClientFlattenAnnotationTarget() == JavaSettings.ClientFlattenAnnotationTarget.TYPE) {
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
                String polymorphicDiscriminator = needsFlatten
                    ? discriminatorSerializedName.replace(".", "\\\\.")
                    : discriminatorSerializedName;

                builder.polymorphicDiscriminator(polymorphicDiscriminator);

                ClientModelProperty discriminatorProperty = createDiscriminatorProperty(
                    settings.isDiscriminatorPassedToChildDeserialization(), hasChildren, compositeType,
                    annotationArgs -> annotationArgs.replace(discriminatorSerializedName, polymorphicDiscriminator),
                    polymorphicDiscriminator);

                if (discriminatorProperty != null) {
                    properties.add(discriminatorProperty);
                    modelImports.add("com.fasterxml.jackson.annotation.JsonTypeId");
                }
            }

            builder.needsFlatten(needsFlatten);
            builder.imports(new ArrayList<>(modelImports));

            List<ClientModelPropertyReference> propertyReferences = new ArrayList<>();
            for (Property property : compositeTypeProperties) {
                ClientModelProperty modelProperty = Mappers.getModelPropertyMapper().map(property);
                properties.add(modelProperty);

                if (modelProperty.getClientFlatten() && settings.getClientFlattenAnnotationTarget() == JavaSettings.ClientFlattenAnnotationTarget.NONE) {
                    propertyReferences.addAll(collectPropertiesFromFlattenedModel(compositeType, property, modelProperty));
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

    /**
     * Creates a {@link ClientModelProperty} for the discriminator type in a polymorphic Swagger model.
     * <p>
     * By default if the discriminator isn't passed to child type deserialization or if the type isn't a terminal, or
     * leaf type, in the hierarchy no {@link ClientModelProperty} will be created.
     * <p>
     * This method serves as an extension point for Fluent generator.
     *
     * @param isDiscriminatorPassedToChildDeserialization Flag indicating whether the generator should pass the
     * discriminator property to child classes.
     * @param hasChildren Flag indicating whether the Swagger model has children models.
     * @param compositeType The Swagger schema of the model.
     * @param annotationArgumentsMapper Function that maps the {@link ClientModelProperty#getAnnotationArguments()} of
     * the {@code compositeType} into the attributes of {@link JsonProperty} for the discriminator property.
     * @param serializedName The serialized name of the discriminator property.
     * @return A {@link ClientModelProperty} that is the discriminator field property, or null if either the
     * discriminator shouldn't be made into a property or if the model isn't a terminal, or leaf, type.
     */
    protected ClientModelProperty createDiscriminatorProperty(boolean isDiscriminatorPassedToChildDeserialization,
        boolean hasChildren, ObjectSchema compositeType, Function<String, String> annotationArgumentsMapper,
        String serializedName) {
        if (!isDiscriminatorPassedToChildDeserialization || hasChildren) {
            return null;
        }
        ClientModelProperty discriminatorProperty = Mappers.getModelPropertyMapper()
            .map(SchemaUtil.getDiscriminatorProperty(compositeType));

        return new ClientModelProperty.Builder()
            .name(discriminatorProperty.getName())
            .description(discriminatorProperty.getDescription())
            .annotationArguments(annotationArgumentsMapper.apply(discriminatorProperty.getAnnotationArguments()))
            .isXmlAttribute(discriminatorProperty.getIsXmlAttribute())
            .xmlName(discriminatorProperty.getXmlName())
            .serializedName(serializedName)
            .isXmlWrapper(discriminatorProperty.getIsXmlWrapper())
            .xmlListElementName(discriminatorProperty.getXmlListElementName())
            .wireType(discriminatorProperty.getWireType())
            .clientType(discriminatorProperty.getClientType())
            .isConstant(discriminatorProperty.getIsConstant())
            .defaultValue("\"" + compositeType.getDiscriminatorValue() + "\"")
            .isReadOnly(true)
            .isRequired(false)
            .headerCollectionPrefix(discriminatorProperty.getHeaderCollectionPrefix())
            .isAdditionalProperties(discriminatorProperty.isAdditionalProperties())
            .xmlNamespace(discriminatorProperty.getXmlNamespace())
            .mutabilities(discriminatorProperty.getMutabilities())
            .needsFlatten(discriminatorProperty.getNeedsFlatten())
            .clientFlatten(discriminatorProperty.getClientFlatten())
            .polymorphicDiscriminator(true)
            .build();
    }

    protected ClientModel.Builder createModelBuilder() {
        return new ClientModel.Builder();
    }

    private List<ClientModelPropertyReference> collectPropertiesFromFlattenedModel(
            ObjectSchema compositeType, Property property, ClientModelProperty modelProperty) {

        List<ClientModelPropertyReference> propertyReferences = new ArrayList<>();
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

            Set<String> referencePropertyNames = new HashSet<>();
            // properties from the target model
            for (ClientModelProperty property1 : targetModel.getProperties()) {
                if (!property1.getClientFlatten() && !property1.isAdditionalProperties()) {
                    String name = disambiguatePropertyNameOfFlattenedSchema(propertyNames, originalFlattenedPropertyName, property1.getName());
                    if (!referencePropertyNames.contains(name)) {
                        propertyReferences.add(ClientModelPropertyReference.ofFlattenProperty(modelProperty, targetModel, property1, name));
                        referencePropertyNames.add(name);
                    }
                }
            }
            for (ClientModelPropertyReference property1 : targetModel.getPropertyReferences()) {
                if (property1.isFromFlattenedProperty()) {
                    String name = disambiguatePropertyNameOfFlattenedSchema(propertyNames, originalFlattenedPropertyName, property1.getName());
                    if (!referencePropertyNames.contains(name)) {
                        propertyReferences.add(ClientModelPropertyReference.ofFlattenProperty(modelProperty, targetModel, property1, name));
                        referencePropertyNames.add(name);
                    }
                }
            }
            // properties from the parents of the target model
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
                                .forEach(objectSchema1 -> objectSchema1.getProperties().stream()
                                        .filter(p -> !p.isIsDiscriminator())
                                        .forEach(property1 -> {
                                            if (property1.getExtensions() == null || !property1.getExtensions().isXmsClientFlatten()) {
                                                ClientModelProperty referenceProperty1 = Mappers.getModelPropertyMapper().map(property1);
                                                String name = disambiguatePropertyNameOfFlattenedSchema(propertyNames, originalFlattenedPropertyName, referenceProperty1.getName());
                                                if (!referencePropertyNames.contains(name)) {
                                                    propertyReferences.add(ClientModelPropertyReference.ofFlattenProperty(modelProperty, targetModel, referenceProperty1, name));
                                                    referencePropertyNames.add(name);
                                                }
                                            } else {
                                                // nested flattened model
                                                if (property1.getSchema() instanceof ObjectSchema && !ObjectMapper.isPlainObject((ObjectSchema) property.getSchema())) {
                                                    ClientModelProperty modelProperty1 = Mappers.getModelPropertyMapper().map(property1);
                                                    List<ClientModelPropertyReference> nestedReferences = collectPropertiesFromFlattenedModel(
                                                            objectSchema1, property1, modelProperty1);
                                                    nestedReferences.forEach(property2 -> {
                                                        String name = disambiguatePropertyNameOfFlattenedSchema(propertyNames, originalFlattenedPropertyName, property2.getName());
                                                        if (!referencePropertyNames.contains(name)) {
                                                            propertyReferences.add(ClientModelPropertyReference.ofFlattenProperty(modelProperty, targetModel, property2, name));
                                                            referencePropertyNames.add(name);
                                                        }
                                                    });
                                                }
                                            }
                                        })));
            }
        }
        return propertyReferences;
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
