// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ArraySchema;
import com.azure.autorest.extension.base.model.codemodel.DictionarySchema;
import com.azure.autorest.extension.base.model.codemodel.Language;
import com.azure.autorest.extension.base.model.codemodel.Languages;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.model.codemodel.SchemaContext;
import com.azure.autorest.extension.base.model.codemodel.XmlSerlializationFormat;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ArrayType;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ClientModelPropertyReference;
import com.azure.autorest.model.clientmodel.ClientModels;
import com.azure.autorest.model.clientmodel.ExternalPackage;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ImplementationDetails;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.SchemaUtil;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModelMapper implements IMapper<ObjectSchema, ClientModel> {
    private static final ModelMapper INSTANCE = new ModelMapper();
    private final ClientModels serviceModels = ClientModels.getInstance();

    private final static String PROPERTY_NAME_ADDITIONAL_PROPERTIES = "additionalProperties";

    protected ModelMapper() {
    }

    public static ModelMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public ClientModel map(ObjectSchema compositeType) {
        JavaSettings settings = JavaSettings.getInstance();
        ObjectMapper objectMapper = Mappers.getObjectMapper();

        ClassType modelType = objectMapper.map(compositeType);
        String modelName = modelType.getName();
        ClientModel result = serviceModels.getModel(modelType.getName());
        if (result == null && !ObjectMapper.isPlainObject(compositeType)) {
            Set<ImplementationDetails.Usage> usages = SchemaUtil.mapSchemaContext(compositeType.getUsage());
            if (isPredefinedModel(modelType)) {
                // TODO (weidxu): a more consistent handling of external model for all data-plane
                if (settings.isDataPlaneClient()) {
                    usages = new HashSet<>(usages);
                    usages.add(ImplementationDetails.Usage.EXTERNAL);
                } else {
                    // abort handling external model, if not DPG
                    // vanilla and fluent currently does not have mechanism to handle model that not to be outputted.
                    return result;
                }
            }

            ClientModel.Builder builder = createModelBuilder()
                .name(modelName)
                .packageName(modelType.getPackage())
                .type(modelType)
                .stronglyTypedHeader(compositeType.isStronglyTypedHeader())
                .usedInXml(SchemaUtil.treatAsXml(compositeType))
                .implementationDetails(new ImplementationDetails.Builder()
                    .usages(usages)
                    .build());

            boolean isPolymorphic = compositeType.getDiscriminator() != null || compositeType.getDiscriminatorValue() != null;
            builder.polymorphic(isPolymorphic);

            HashSet<String> modelImports = new HashSet<>();

            String parentModelName = null;
            boolean hasAdditionalProperties = false;
            List<ObjectSchema> parentsNeedFlatten = Collections.emptyList();
            if (compositeType.getParents() != null && compositeType.getParents().getImmediate() != null) {
                hasAdditionalProperties = compositeType.getParents().getImmediate().stream()
                    .anyMatch(s -> s instanceof DictionarySchema);

                ParentSchemaInfo parentSchemaInfo = getParentSchemaInfo(compositeType);
                if (parentSchemaInfo.hasParentSchema()) {
                    parentsNeedFlatten = parentSchemaInfo.getFlattenedParentSchemas();

                    ClassType parentType = objectMapper.map(parentSchemaInfo.getParentSchema());
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

            boolean compositeTypeUsedWithXml = SchemaUtil.treatAsXml(compositeType);
            if (!compositeTypeProperties.isEmpty()) {
                if (compositeTypeUsedWithXml) {
                    modelImports.add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement");

                    if (compositeTypeProperties.stream().anyMatch(p -> p.getSchema() instanceof ArraySchema)) {
                        modelImports.add(ArrayList.class.getName());
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

                    if (compositeTypeProperties.stream().anyMatch(p -> {
                        if (p.getSchema().getSerialization() == null || p.getSchema().getSerialization().getXml() == null) {
                            return false;
                        }

                        return p.getSchema().getSerialization().getXml().isText();
                    })) {
                        modelImports.add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText");
                    }

                    if (compositeTypeProperties.stream().anyMatch(p -> p.getSchema().getSerialization() == null
                        || p.getSchema().getSerialization().getXml() == null || !p.getSchema().getSerialization()
                        .getXml().isAttribute())) {
                        modelImports.add(JsonProperty.class.getName());
                    }

                    if (compositeTypeProperties.stream().anyMatch(p -> p.getSchema().getSerialization() != null
                        && p.getSchema().getSerialization().getXml() != null && p.getSchema().getSerialization().getXml().isWrapped())) {
                        modelImports.add(JsonCreator.class.getName());
                    }

                } else {
                    modelImports.add(JsonProperty.class.getName());
                }
            }
            if (hasAdditionalProperties) {
                for (Property property : compositeTypeProperties) {
                    if (property.getLanguage().getJava().getName().equals(PROPERTY_NAME_ADDITIONAL_PROPERTIES)) {
                        property.getLanguage().getJava().setName(PROPERTY_NAME_ADDITIONAL_PROPERTIES + "Property");
                    }
                }
            }

            String summary = compositeType.getSummary();
            String description = compositeType.getLanguage().getJava() == null ? null : compositeType.getLanguage().getJava().getDescription();
            if (CoreUtils.isNullOrEmpty(summary) && CoreUtils.isNullOrEmpty(description)) {
                builder.description(String.format("The %s model.", compositeType.getLanguage().getJava().getName()));
            } else {
                builder.description(SchemaUtil.mergeSummaryWithDescription(summary, description));
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

            // Only configure XML information if XML is listed as one of the serialization formats in the ObjectSchema.
            if (SchemaUtil.treatAsXml(compositeType)) {
                boolean hasXmlFormat = compositeType.getSerialization() != null
                    && compositeType.getSerialization().getXml() != null;
                if (hasXmlFormat) {
                    final XmlSerlializationFormat xml = compositeType.getSerialization().getXml();
                    String xmlName = CoreUtils.isNullOrEmpty(xml.getName())
                        ? compositeType.getLanguage().getDefault().getName()
                        : xml.getName();
                    builder.xmlName(xmlName);
                    builder.xmlNamespace(xml.getNamespace());
                } else {
                    builder.xmlName(compositeType.getLanguage().getDefault().getName());
                }
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
                    settings, hasChildren, compositeType,
                    annotationArgs -> annotationArgs.replace(discriminatorSerializedName, polymorphicDiscriminator),
                    polymorphicDiscriminator);

                if (discriminatorProperty != null) {
                    properties.add(discriminatorProperty);
                    modelImports.add(JsonTypeId.class.getName());
                }
            }

            builder.needsFlatten(needsFlatten);
            builder.imports(new ArrayList<>(modelImports));

            List<ClientModelPropertyReference> propertyReferences = new ArrayList<>();
            for (Property property : compositeTypeProperties) {
                ClientModelProperty modelProperty = Mappers.getModelPropertyMapper().map(property);
                properties.add(modelProperty);

                if (modelProperty.getClientFlatten() && settings.getClientFlattenAnnotationTarget() == JavaSettings.ClientFlattenAnnotationTarget.NONE) {
                    propertyReferences.addAll(collectPropertiesFromFlattenedModel(compositeType, property, modelProperty, propertyReferences));
                }
            }

            if (hasAdditionalProperties) {
                DictionarySchema schema = (DictionarySchema) compositeType.getParents().getImmediate().stream()
                    .filter(s -> s instanceof DictionarySchema)
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException(
                        "Unable to find DictionarySchema for additional properties property."));
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

            // handle multipart/form-data
            if (!CoreUtils.isNullOrEmpty(compositeType.getUsage()) && compositeType.getUsage().contains(SchemaContext.MULTIPART_FORM_DATA)) {
                processMultipartFormDataProperties(properties);
            }

            builder.properties(properties);
            builder.propertyReferences(propertyReferences);

            result = builder.build();
            serviceModels.addModel(result);
        }

        return result;
    }

    private static class ParentSchemaInfo {
        private final ObjectSchema parentSchema;
        private final List<ObjectSchema> flattenedParentSchemas;

        public ParentSchemaInfo(ObjectSchema parentSchema, List<ObjectSchema> flattenedParentSchemas) {
            this.parentSchema = parentSchema;
            this.flattenedParentSchemas = flattenedParentSchemas;
        }

        public boolean hasParentSchema() {
            return parentSchema != null;
        }

        /**
         * @return the single parent schema to keep.
         */
        public ObjectSchema getParentSchema() {
            return parentSchema;
        }

        /**
         * @return the list of parent schemas to flatten into this schema.
         */
        public List<ObjectSchema> getFlattenedParentSchemas() {
            return flattenedParentSchemas;
        }
    }

    /**
     * Separate all immediate parents into: one to keep, and the rest to flatten.
     * <p>
     * If schema is not polymorphic, keep the first parent of type ObjectSchema. If schema is polymorphic (but not the
     * supertype), keep the parent in polymorphic hierarchy.
     *
     * @param compositeType the object schema
     * @return the info on parent schema.
     */
    private ParentSchemaInfo getParentSchemaInfo(ObjectSchema compositeType) {
        ObjectSchema parentSchema = null;
        List<ObjectSchema> flattenedParentSchemas = new ArrayList<>();

        if (compositeType.getDiscriminatorValue() != null) {
            for (Schema parent : compositeType.getParents().getImmediate()) {
                if (parent instanceof ObjectSchema) {
                    ObjectSchema parentAsObjectSchema = (ObjectSchema) parent;
                    if (parentSchema == null
                        && (parentAsObjectSchema.getDiscriminatorValue() != null || parentAsObjectSchema.getDiscriminator() != null)) {
                        parentSchema = parentAsObjectSchema;
                    } else {
                        flattenedParentSchemas.add((ObjectSchema) parent);
                    }
                }
            }

            if (parentSchema == null) {
                // failed to find a parent being polymorphic
                // clean up and fallback to flow without polymorphic
                flattenedParentSchemas.clear();
            }
        }

        if (parentSchema == null) {
            for (Schema parent : compositeType.getParents().getImmediate()) {
                if (parent instanceof ObjectSchema) {
                    if (parentSchema == null) {
                        parentSchema = (ObjectSchema) parent;
                    } else {
                        flattenedParentSchemas.add((ObjectSchema) parent);
                    }
                }
            }
        }

        return new ParentSchemaInfo(parentSchema, flattenedParentSchemas);
    }

    /**
     * Creates a {@link ClientModelProperty} for the discriminator type in a polymorphic Swagger model.
     * <p>
     * By default if the discriminator isn't passed to child type deserialization or if the type isn't a terminal, or
     * leaf type, in the hierarchy no {@link ClientModelProperty} will be created.
     * <p>
     * This method serves as an extension point for Fluent generator.
     *
     * @param settings The Autorest generation settings, used to determine whether a discriminator property should be
     * created.
     * @param hasChildren Flag indicating whether the Swagger model has children models.
     * @param compositeType The Swagger schema of the model.
     * @param annotationArgumentsMapper Function that maps the {@link ClientModelProperty#getAnnotationArguments()} of
     * the {@code compositeType} into the attributes of {@link JsonProperty} for the discriminator property.
     * @param serializedName The serialized name of the discriminator property.
     * @return A {@link ClientModelProperty} that is the discriminator field property, or null if either the
     * discriminator shouldn't be made into a property or if the model isn't a terminal, or leaf, type.
     */
    protected ClientModelProperty createDiscriminatorProperty(JavaSettings settings, boolean hasChildren,
        ObjectSchema compositeType, Function<String, String> annotationArgumentsMapper, String serializedName) {
        // Don't create a discriminator property for the model if the discriminator shouldn't be passed to subtype
        // deserialization or the model type has children and stream-style serialization isn't being used.
        if ((!settings.isDiscriminatorPassedToChildDeserialization() || hasChildren)
            && !settings.isStreamStyleSerialization()) {
            return null;
        }
        ClientModelProperty discriminatorProperty = Mappers.getModelPropertyMapper()
            .map(SchemaUtil.getDiscriminatorProperty(compositeType));

        return discriminatorProperty.toNewBuilder()
            .annotationArguments(annotationArgumentsMapper.apply(discriminatorProperty.getAnnotationArguments()))
            .serializedName(serializedName)
            .defaultValue(discriminatorProperty.getClientType().defaultValueExpression(compositeType.getDiscriminatorValue()))
            .readOnly(true)
            .required(false)
            .polymorphicDiscriminator(true)
            .build();
    }

    protected ClientModel.Builder createModelBuilder() {
        return new ClientModel.Builder();
    }

    /**
     * Collect property reference from flattened model.
     *
     * @param compositeType the model
     * @param property the property of the model that specified to be flattened
     * @param modelProperty the ClientModelProperty of the property
     * @param existingPropertyReferences the list of existing property references from previously flattened models, for
     * disambiguate purpose
     * @return the list of property references from flattened model.
     */
    private List<ClientModelPropertyReference> collectPropertiesFromFlattenedModel(
        ObjectSchema compositeType, Property property, ClientModelProperty modelProperty,
        List<ClientModelPropertyReference> existingPropertyReferences) {

        List<ClientModelPropertyReference> propertyReferences = new ArrayList<>();
        ObjectSchema targetModelSchema = (ObjectSchema) property.getSchema();
        String originalFlattenedPropertyName = property.getLanguage().getJava().getName();  // not modelProperty.getName()
        ClientModel targetModel = this.map(targetModelSchema);
        if (targetModel != null && targetModel.getProperties() != null) {
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
            // gather property names for disambiguate
            Set<String> propertyNames = objectSchemaAndParents.stream()
                .flatMap(o -> o.getProperties().stream())
                .filter(p -> p.getExtensions() == null || !p.getExtensions().isXmsClientFlatten())
                .map(p -> p.getLanguage().getJava().getName())
                .collect(Collectors.toSet());
            propertyNames.addAll(existingPropertyReferences.stream().map(ClientModelPropertyReference::getName).collect(Collectors.toList()));
            // additional properties
            if (compositeType.getParents() != null && compositeType.getParents().getAll() != null
                && compositeType.getParents().getAll().stream().anyMatch(s -> s instanceof DictionarySchema)) {
                propertyNames.add(PROPERTY_NAME_ADDITIONAL_PROPERTIES);
            }

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
                // take parent of the target model, as rest parents (if any) is already flattened into the target model
                ParentSchemaInfo parentSchemaInfo = getParentSchemaInfo(targetModelSchema);
                if (parentSchemaInfo.hasParentSchema()) {
                    ObjectSchema parentSchema = parentSchemaInfo.getParentSchema();
                    Stream.concat(
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
                                            objectSchema1, property1, modelProperty1, existingPropertyReferences);
                                        nestedReferences.forEach(property2 -> {
                                            String name = disambiguatePropertyNameOfFlattenedSchema(propertyNames, originalFlattenedPropertyName, property2.getName());
                                            if (!referencePropertyNames.contains(name)) {
                                                propertyReferences.add(ClientModelPropertyReference.ofFlattenProperty(modelProperty, targetModel, property2, name));
                                                referencePropertyNames.add(name);
                                            }
                                        });
                                    }
                                }
                            }));
                }
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
     * Extension for predefined types in azure-core.
     *
     * @param compositeType object type
     * @return Whether the type is predefined.
     */
    protected boolean isPredefinedModel(ClassType compositeType) {
        if (JavaSettings.getInstance().isDataPlaneClient()) {
            // see ObjectMapper.mapPredefinedModel
            // this might be too simplified, and Android might require a different implementation
            return compositeType.getPackage().startsWith(ExternalPackage.CORE.getPackageName() + ".");
        } else {
            return false;
        }
    }

    private static String disambiguatePropertyNameOfFlattenedSchema(Set<String> propertyNames, String originalFlattenedPropertyName, String propertyName) {
        String ret = propertyName;
        if (propertyNames.contains(propertyName)) {
            // follow pattern from m4
            ret = propertyName + CodeNamer.toPascalCase(originalFlattenedPropertyName) + CodeNamer.toPascalCase(propertyName);
        }
        return ret;
    }

    private static void processMultipartFormDataProperties(List<ClientModelProperty> properties) {
        ListIterator<ClientModelProperty> iterator = properties.listIterator();
        while (iterator.hasNext()) {
            ClientModelProperty property = iterator.next();

            if (property.getWireType() == ArrayType.BYTE_ARRAY) {
                // replace byte[] with BinaryData
                iterator.remove();
                iterator.add(property.toNewBuilder()
                        .wireType(ClassType.BinaryData)
                        .clientType(ClassType.BinaryData)
                        .build());

                // add (optional) filename property
                iterator.add(property.toNewBuilder()
                        .name(property.getName() + ClientModelUtil.FILENAME_SUFFIX)
                        .defaultValue(ClassType.String.defaultValueExpression(property.getSerializedName()))
                        .description("The filename for " + property.getName())
                        .wireType(ClassType.String)
                        .clientType(ClassType.String)
                        .required(false)
                        .build());
            }
        }
    }
}
