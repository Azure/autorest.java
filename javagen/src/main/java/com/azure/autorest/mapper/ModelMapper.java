package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ArraySchema;
import com.azure.autorest.extension.base.model.codemodel.ComplexSchema;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ClientModels;
import com.azure.autorest.model.clientmodel.IType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
        ClientModel result = serviceModels.getModel(compositeType.getLanguage().getJava().getName());
        if (result == null) {
            String modelSubPackage = !settings.isFluent() ? settings.getModelsSubpackage() : (false /*compositeType.IsInnerModel*/ ? settings.getImplementationSubpackage() : "");
            if (settings.isCustomType(compositeType.getLanguage().getJava().getName())) {
                modelSubPackage = settings.getCustomTypesSubpackage();
            }
            String modelPackage = settings.getPackage(modelSubPackage);

            boolean isPolymorphic = compositeType.getDiscriminator() != null || compositeType.getDiscriminatorValue() != null;

            String parentModel = null;
            if (compositeType.getParents() != null && compositeType.getParents().getImmediate() != null) {
//                ComplexSchema baseSchema = compositeType.getParents().getImmediate().get(0);
//                if (baseSchema instanceof ObjectSchema) {
//                    parentModel = map((ObjectSchema) baseSchema);
//                    serviceModels.addModel(parentModel);
//                } else {
//                    throw new RuntimeException("Wait what? How? Parent is not an object but a " + baseSchema.getClass() + "?");
//                }
                parentModel = compositeType.getParents().getImmediate().get(0).getLanguage().getJava().getName();
            }

            HashSet<String> modelImports = new HashSet<>();
            List<Property> compositeTypeProperties = compositeType.getProperties();
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

                    // TODO: XML
//                    if (compositeTypeProperties.stream().anyMatch(p -> p.XmlIsAttribute))
//                    {
//                        modelImports.add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty");
//                    }
//
//                    if (compositeTypeProperties.Any(p => !p.XmlIsAttribute))
//                    {
//                        modelImports.add("com.fasterxml.jackson.annotation.JsonProperty");
//                    }
//
//                    if (compositeTypeProperties.Any(p => p.XmlIsWrapped))
//                    {
//                        modelImports.add("com.fasterxml.jackson.annotation.JsonCreator");
//                    }
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

            // TODO: XML
//            String modelXmlName = compositeType.XmlName;

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

            result = new ClientModel(modelPackage, compositeType.getLanguage().getJava().getName(), new ArrayList<>(modelImports), modelDescription, isPolymorphic, polymorphicDiscriminator, modelSerializedName, needsFlatten, parentModel, derivedTypes, null, properties);

            serviceModels.addModel(result);
        }

        return result;
    }
}
