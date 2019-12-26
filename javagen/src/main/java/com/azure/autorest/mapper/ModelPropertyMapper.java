package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ArraySchema;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.model.codemodel.XmlSerlializationFormat;
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
        String description;
        if (property.getLanguage().getJava().getDescription() == null || property.getLanguage().getJava().getDescription().isEmpty()) {
            description = String.format("The %s property.", property.getSerializedName());
        } else {
            description = property.getLanguage().getJava().getDescription();
        }

        XmlSerlializationFormat xmlSerlializationFormat = null;
        if (property.getSchema().getSerialization() != null) {
            xmlSerlializationFormat = property.getSchema().getSerialization().getXml();
        }

        String xmlName = null;
        boolean isXmlWrapper = false;
        boolean isXmlAttribute = false;
        if (xmlSerlializationFormat != null) {
            isXmlWrapper = xmlSerlializationFormat.isWrapped();
            isXmlAttribute = xmlSerlializationFormat.isAttribute();
            xmlName = xmlSerlializationFormat.getName();
        }

        final String xmlParamName = xmlName == null ? property.getSerializedName() : xmlName;

        List<String> annotationArgumentList = new ArrayList<String>() {{
            add(String.format("value = \"%s\"", xmlParamName));
        }};
        if (property.isRequired()) {
            annotationArgumentList.add("required = true");
        }
        if (property.isReadOnly()) {
            annotationArgumentList.add("access = JsonProperty.Access.WRITE_ONLY");
        }
        String annotationArguments = String.join(", ", annotationArgumentList);

        String serializedName = property.getSerializedName();


//        String headerCollectionPrefix = property.Extensions?.GetValue<string>(SwaggerExtensions.HeaderCollectionPrefix);

        IType propertyWireType = Mappers.getSchemaMapper().map(property.getSchema());
        if (propertyWireType != null && property.isNullable()) {
            propertyWireType = propertyWireType.asNullable();
        }

        IType propertyClientType = Mappers.getSchemaMapper().map((property.getSchema())).getClientType();

        if (!property.isRequired()) {
            propertyClientType = propertyClientType.asNullable();
            propertyWireType = propertyWireType.asNullable();
        }

        Schema autoRestPropertyModelType = property.getSchema();
        String xmlListElementName = null;
        if (autoRestPropertyModelType instanceof ArraySchema) {
            ArraySchema sequence = (ArraySchema) autoRestPropertyModelType;
            if (sequence.getElementType().getSerialization() != null
                && sequence.getElementType().getSerialization().getXml() != null
                && sequence.getElementType().getSerialization().getXml().getName() != null) {
                xmlListElementName = sequence.getElementType().getSerialization().getXml().getName();
            } else if(sequence.getSerialization() != null && sequence.getSerialization().getXml() != null){
                xmlListElementName = sequence.getSerialization().getXml().getName();
            }
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
                isXmlAttribute,
                xmlParamName,
                serializedName,
                isXmlWrapper,
                xmlListElementName,
                propertyWireType,
                propertyClientType,
                false,
                null,
                isReadOnly,
                false,
                property.isRequired(),
                null);
    }
}
