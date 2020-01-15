package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ArraySchema;
import com.azure.autorest.extension.base.model.codemodel.ComplexSchema;
import com.azure.autorest.extension.base.model.codemodel.DictionarySchema;
import com.azure.autorest.extension.base.model.codemodel.Language;
import com.azure.autorest.extension.base.model.codemodel.Languages;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.extension.base.model.codemodel.XmlSerlializationFormat;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ClientModels;
import com.azure.autorest.model.clientmodel.IType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class ModelMapper implements IMapper<ObjectSchema, ClientModel> {
    private static ModelMapper instance = new ModelMapper();
    private ClientModels serviceModels = ClientModels.Instance;

    private ModelMapper() {
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
        if (result == null && !ObjectMapper.isPlainObject(compositeType) && (!settings.isFluent() || ObjectMapper.nonResourceType(modelType))) {
            String modelPackage = modelType.getPackage();

            boolean isPolymorphic = compositeType.getDiscriminator() != null || compositeType.getDiscriminatorValue() != null;

            String parentModel = null;
            boolean hasAdditionalProperties = false;
            if (compositeType.getParents() != null && compositeType.getParents().getImmediate() != null) {
                if (!(compositeType.getParents().getImmediate().get(0) instanceof DictionarySchema)) {
//                ComplexSchema baseSchema = compositeType.getParents().getImmediate().get(0);
//                if (baseSchema instanceof ObjectSchema) {
//                    parentModel = map((ObjectSchema) baseSchema);
//                    serviceModels.addModel(parentModel);
//                } else {
//                    throw new RuntimeException("Wait what? How? Parent is not an object but a " + baseSchema.getClass() + "?");
//                }
                ComplexSchema parentComplexSchema = compositeType.getParents().getImmediate().get(0);
                parentModel = parentComplexSchema instanceof ObjectSchema
                        ? objectMapper.map((ObjectSchema) compositeType.getParents().getImmediate().get(0)).getName()
                        : compositeType.getParents().getImmediate().get(0).getLanguage().getJava().getName();
            }

            HashSet<String> modelImports = new HashSet<>();
            List<Property> compositeTypeProperties = compositeType.getProperties()
                    .stream().filter(p -> !p.isIsDiscriminator()).collect(Collectors.toList());
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

                    if (compositeTypeProperties.stream().anyMatch(p -> p.getSchema().getSerialization() != null
                        && p.getSchema().getSerialization().getXml() != null && p.getSchema().getSerialization().getXml().isAttribute())) {
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

            String modelDescription;
            if ((compositeType.getSummary() == null || compositeType.getSummary().isEmpty()) && (compositeType.getDescription() == null || compositeType.getDescription().isEmpty())) {
                modelDescription = String.format("The %s model.", compositeType.getLanguage().getJava().getName());
            } else {
                modelDescription = String.format("%s%s", compositeType.getSummary(), compositeType.getDescription());
            }

            String polymorphicDiscriminator = null;
            if (compositeType.getDiscriminator() != null) {
                polymorphicDiscriminator = compositeType.getDiscriminator().getProperty().getSerializedName();
            } else if (isPolymorphic) {
                for (ComplexSchema parent : compositeType.getParents().getAll()) {
                    if (((ObjectSchema) parent).getDiscriminator() != null) {
                        polymorphicDiscriminator = ((ObjectSchema) parent).getDiscriminator().getProperty().getSerializedName();
                        break;
                    }
                }
            }

            String modelSerializedName = compositeType.getDiscriminatorValue();
            if (modelSerializedName == null && compositeType.getLanguage().getDefault() != null) {
                modelSerializedName = compositeType.getLanguage().getDefault().getName();
            }

//            List<ClientModel> derivedTypes = serviceModels.getDerivedTypes(compositeType.getLanguage().getJava().getName());
            List<ClientModel> derivedTypes = new ArrayList<>();
            if (compositeType.getChildren() != null && compositeType.getChildren().getImmediate() != null) {
                for (ComplexSchema childSchema : compositeType.getChildren().getImmediate()) {
                    if (childSchema instanceof ObjectSchema) {
                        ClientModel model = map((ObjectSchema) childSchema);
                        derivedTypes.add(model);
                        serviceModels.addModel(model);
                    } else {
                        throw new RuntimeException("Wait what? How? Child is not an object but a " + childSchema.getClass() + "?");
                    }
                }
            }

            String modelXmlName = null;
            if (compositeType.getSerialization() != null && compositeType.getSerialization().getXml() != null) {
                 modelXmlName = compositeType.getSerialization().getXml().getName();
            } else if (compositeType.getLanguage().getDefault() != null) {
                modelXmlName = compositeType.getLanguage().getDefault().getName();
            }

            boolean needsFlatten = false;
            List<ClientModelProperty> properties = new ArrayList<ClientModelProperty>();
            for (Property property : compositeTypeProperties) {
                properties.add(Mappers.getModelPropertyMapper().map(property));
                // TODO: Flattening
//                if (!needsFlatten && property.WasFlattened())
//                {
//                    needsFlatten = true;
//                }
            }

            if (hasAdditionalProperties) {
                DictionarySchema schema = (DictionarySchema) compositeType.getParents().getImmediate().get(0);
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

            result = new ClientModel(modelPackage, modelName,
                new ArrayList<>(modelImports), modelDescription, isPolymorphic, polymorphicDiscriminator,
                modelSerializedName, needsFlatten, parentModel, derivedTypes, modelXmlName, properties);

            serviceModels.addModel(result);
        }

        return result;
    }
}
