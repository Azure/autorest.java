package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ClientModels;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.codemodel.ArraySchema;
import com.azure.autorest.model.codemodel.ObjectSchema;
import com.azure.autorest.model.codemodel.Property;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ModelMapper implements IMapper<ObjectSchema, ClientModel> {
    private ModelMapper() {
    }

    private static ModelMapper instance = new ModelMapper();

    public static ModelMapper getInstance() {
        return instance;
    }

    private ClientModels serviceModels = ClientModels.Instance;

    @Override
    public ClientModel map(ObjectSchema compositeType) {
        JavaSettings settings = JavaSettings.getInstance();
        ClientModel result = serviceModels.getModel(compositeType.getLanguage().getDefault().getName());
        if (result == null)
        {
            String modelSubPackage = !settings.isFluent() ? settings.getModelsSubpackage() : (false /*compositeType.IsInnerModel*/ ? settings.getImplementationSubpackage() : "");
            if (settings.IsCustomType(compositeType.getLanguage().getDefault().getName())) {
                modelSubPackage = settings.getCustomTypesSubpackage();
            }
            String modelPackage = settings.getPackage(modelSubPackage);

//            boolean isPolymorphic = compositeType.BaseIsPolymorphic;
//
//            ClientModel parentModel = null;
//            if (compositeType.BaseModelType != null)
//            {
//                parentModel = Map((CompositeTypeJv)compositeType.BaseModelType);
//            }

            HashSet<String> modelImports = new HashSet<>();
            List<Property> compositeTypeProperties = compositeType.getProperties();
            for (Property autoRestProperty : compositeTypeProperties)
            {
                IType propertyType = Mappers.getSchemaMapper().map(autoRestProperty.getSchema());
                propertyType.addImportsTo(modelImports, false);

                IType propertyClientType = Mappers.getSchemaMapper().map(autoRestProperty.getSchema()).getClientType();
                propertyClientType.addImportsTo(modelImports, false);
            }

            if (!compositeTypeProperties.isEmpty())
            {
                if (settings.shouldGenerateXmlSerialization())
                {
                    modelImports.add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement");

                    if (compositeTypeProperties.stream().anyMatch(p -> p.getSchema() instanceof ArraySchema))
                    {
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
                }
                else
                {
                    modelImports.add("com.fasterxml.jackson.annotation.JsonProperty");
                }
            }

            String modelDescription;
            if ((compositeType.getSummary() == null || compositeType.getSummary().isEmpty()) && (compositeType.getDescription() == null || compositeType.getDescription().isEmpty()))
            {
                modelDescription = String.format("The %s model.", compositeType.getLanguage().getDefault().getName());
            }
            else
            {
                modelDescription = String.format("%s%s", compositeType.getSummary(), compositeType.getDescription());
            }

            // TODO: Polymorphism
//            String polymorphicDiscriminator = compositeType.BasePolymorphicDiscriminator;

            String modelSerializedName = compositeType.getLanguage().getDefault().getName();

            List<ClientModel> derivedTypes = serviceModels.getDerivedTypes(compositeType.getLanguage().getDefault().getName());

            // TODO: XML
//            String modelXmlName = compositeType.XmlName;

            boolean needsFlatten = false;
            List<ClientModelProperty> properties = new ArrayList<ClientModelProperty>();
            for (Property property : compositeTypeProperties)
            {
                properties.add(Mappers.getModelPropertyMapper().map(property));
                // TODO: Flattening
//                if (!needsFlatten && property.WasFlattened())
//                {
//                    needsFlatten = true;
//                }
            }

            result = new ClientModel(modelPackage, compositeType.getLanguage().getDefault().getName(), new ArrayList<>(modelImports), modelDescription, false, null, modelSerializedName, needsFlatten, null, derivedTypes, null, properties);

            serviceModels.addModel(result);
        }

        return result;
    }
}
