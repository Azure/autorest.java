// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ArraySchema;
import com.azure.autorest.extension.base.model.codemodel.ComplexSchema;
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
import com.azure.autorest.model.clientmodel.ClientModels;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.util.SchemaUtil;
import com.azure.core.util.CoreUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
            ClientModel.Builder builder = new ClientModel.Builder()
                    .name(modelName)
                    .packageName(modelType.getPackage());

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
                        property.getLanguage().getJava().setName("AdditionalPropertiesProperty");
                    }
                }
            }

            if (compositeType.getLanguage().getDefault() == null
                    || CoreUtils.isNullOrEmpty(compositeType.getLanguage().getDefault().getDescription())) {
                builder.description(String.format("The %s model.", compositeType.getLanguage().getJava().getName()));
            } else {
                builder.description(compositeType.getLanguage().getDefault().getDescription());
            }

            boolean discriminatorNeedEscape = false;
            if (isPolymorphic) {
                String discriminatorSerializedName = SchemaUtil.getDiscriminatorSerializedName(compositeType);
                discriminatorNeedEscape = discriminatorSerializedName.contains(".");
                discriminatorSerializedName = discriminatorNeedEscape ? discriminatorSerializedName.replace(".", "\\\\.") : discriminatorSerializedName;
                builder.polymorphicDiscriminator(discriminatorSerializedName);
            }

            String modelSerializedName = compositeType.getDiscriminatorValue();
            if (modelSerializedName == null && compositeType.getLanguage().getDefault() != null) {
                modelSerializedName = compositeType.getLanguage().getDefault().getName();
            }
            builder.serializedName(modelSerializedName);

            List<ClientModel> derivedTypes = new ArrayList<>();
            if (compositeType.getChildren() != null && compositeType.getChildren().getImmediate() != null) {
                for (ComplexSchema childSchema : compositeType.getChildren().getImmediate()) {
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

            builder.needsFlatten(discriminatorNeedEscape || hasFlattenedProperty(compositeType, parentsNeedFlatten));

            List<ClientModelProperty> properties = new ArrayList<ClientModelProperty>();
            for (Property property : compositeTypeProperties) {
                properties.add(Mappers.getModelPropertyMapper().map(property));
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

            result = builder.build();
            serviceModels.addModel(result);
        }

        return result;
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
}
