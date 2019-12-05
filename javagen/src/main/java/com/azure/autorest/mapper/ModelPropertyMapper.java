package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ArraySchema;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.IType;

import java.util.ArrayList;
import java.util.List;

public class ModelPropertyMapper implements IMapper<Property, ClientModelProperty> {
    private static ModelPropertyMapper instance = new ModelPropertyMapper();

    private ModelPropertyMapper() {
    }

    public static ModelPropertyMapper getInstance() {
        return instance;
    }

    @Override
    public ClientModelProperty map(Property property) {
        String description = "";
        if ((property.getSummary() == null || property.getSummary().isEmpty()) && (property.getDescription() == null || property.getDescription().isEmpty())) {
            description = String.format("The %s property.", property.getSerializedName());
        } else {
            description = property.getSummary();

            String documentation = property.getDescription();
            if (documentation != null && !documentation.isEmpty()) {
                if (description != null && !description.isEmpty()) {
                    description += System.lineSeparator();
                }
                description += documentation;
            }
        }

        // TODO: XML
//        String xmlName;
//        try
//        {
//            xmlName = property.getSchema().XmlProperties?.Name
//                ?? property.XmlName;
//        }
//        catch
//        {
//            xmlName = null;
//        }

        List<String> annotationArgumentList = new ArrayList<String>() {{
            add(String.format("value = \"%s\"", (JavaSettings.getInstance().shouldGenerateXmlSerialization() ? "" : property.getSerializedName())));
        }};
        if (property.isRequired()) {
            annotationArgumentList.add("required = true");
        }
        if (property.isReadOnly()) {
            annotationArgumentList.add("access = JsonProperty.Access.WRITE_ONLY");
        }
        String annotationArguments = String.join(", ", annotationArgumentList);

//        boolean isXmlAttribute = property.XmlIsAttribute;

        String serializedName = property.getSerializedName();

//        boolean isXmlWrapper = property.XmlIsWrapped;

//        String headerCollectionPrefix = property.Extensions?.GetValue<string>(SwaggerExtensions.HeaderCollectionPrefix);

        IType propertyWireType = Mappers.getSchemaMapper().map(property.getSchema());
        if (propertyWireType != null && property.isNullable()) {
            propertyWireType = propertyWireType.asNullable();
        }

        IType propertyClientType = Mappers.getSchemaMapper().map((property.getSchema())).getClientType();

        Schema autoRestPropertyModelType = property.getSchema();
        String xmlListElementName = null;
        if (autoRestPropertyModelType instanceof ArraySchema) {
            ArraySchema sequence = (ArraySchema) autoRestPropertyModelType;
//            xmlListElementName = sequence.getElementType().XmlProperties?.Name ?? sequence.ElementXmlName;
        }

//        boolean isConstant = property.IsConstant;

//        String defaultValue;
//        try
//        {
//            defaultValue = propertyWireType.DefaultValueExpression(property.DefaultValue);
//        }
//        catch (NotSupportedException)
//        {
//            defaultValue = null;
//        }

        boolean isReadOnly = property.isReadOnly();

//        boolean wasFlattened = property.WasFlattened();

        return new ClientModelProperty(property.getLanguage().getJava().getName(),
                description,
                annotationArguments,
                false,
                null,
                serializedName,
                false,
                xmlListElementName,
                propertyWireType,
                propertyClientType,
                false,
                null,
                isReadOnly,
                false,
                null);
    }
}
