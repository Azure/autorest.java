package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ArraySchema;
import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.model.codemodel.XmlSerlializationFormat;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.SchemaUtil;
import com.azure.core.util.CoreUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ModelPropertyMapper implements IMapper<Property, ClientModelProperty> {
    private static ModelPropertyMapper instance = new ModelPropertyMapper();

    private ModelPropertyMapper() {
    }

    public static ModelPropertyMapper getInstance() {
        return instance;
    }

    @Override
    public ClientModelProperty map(Property property) {
        JavaSettings settings = JavaSettings.getInstance();

        ClientModelProperty.Builder builder = new ClientModelProperty.Builder()
                .name(property.getLanguage().getJava().getName())
                .isRequired(property.isRequired())
                .isReadOnly(property.isReadOnly());


        String description;
        String summaryInProperty = property.getSchema() == null ? null : property.getSchema().getSummary();
        String descriptionInProperty = property.getLanguage().getJava() == null ? null : property.getLanguage().getJava().getDescription();
        if (CoreUtils.isNullOrEmpty(summaryInProperty) && CoreUtils.isNullOrEmpty(descriptionInProperty)) {
            description = String.format("The %s property.", property.getSerializedName());
        } else {
            description = SchemaUtil.mergeDescription(summaryInProperty, descriptionInProperty);
        }
        builder.description(description);

        boolean flattened = false;
        if (settings.getModelerSettings().isFlattenModel()) {   // enabled by modelerfour
            if (settings.getClientFlattenAnnotationTarget() == JavaSettings.ClientFlattenAnnotationTarget.TYPE) {
                if (property.getParentSchema() != null) {
                    flattened = property.getParentSchema().getProperties().stream()
                            .anyMatch(p -> p.getFlattenedNames() != null && !p.getFlattenedNames().isEmpty());
                    if (!flattened) {
                        String discriminatorSerializedName = SchemaUtil.getDiscriminatorSerializedName(property.getParentSchema());
                        flattened = discriminatorSerializedName != null && discriminatorSerializedName.contains(".");
                    }
                }
            } else if (settings.getClientFlattenAnnotationTarget() == JavaSettings.ClientFlattenAnnotationTarget.FIELD) {
                flattened = property.getFlattenedNames() != null && !property.getFlattenedNames().isEmpty();
            }
        }
        builder.needsFlatten(flattened);

        if (property.getExtensions() != null && property.getExtensions().isXmsClientFlatten()
                // avoid non-object schema or a plain object schema without any properties
                && property.getSchema() instanceof ObjectSchema && !ObjectMapper.isPlainObject((ObjectSchema) property.getSchema())
                && settings.getClientFlattenAnnotationTarget() == JavaSettings.ClientFlattenAnnotationTarget.NONE) {
            // avoid naming conflict
            builder.name("inner" + CodeNamer.toPascalCase(property.getLanguage().getJava().getName()));
            builder.clientFlatten(true);
        }

        StringBuilder serializedName = new StringBuilder();
        if (property.getFlattenedNames() != null && !property.getFlattenedNames().isEmpty()) {
            for (String flattenedName : property.getFlattenedNames()) {
                serializedName.append(flattenedName.replace(".", "\\\\.")).append(".");
            }
            serializedName.deleteCharAt(serializedName.length() - 1);
        } else if (flattened) {
            serializedName.append(property.getSerializedName().replace(".", "\\\\."));
        } else {
            serializedName.append(property.getSerializedName());
        }
        builder.serializedName(serializedName.toString());
        if (serializedName.toString().isEmpty() && "additionalProperties".equals(property.getLanguage().getJava().getName())) {
            builder.isAdditionalProperties(true);
        }

        XmlSerlializationFormat xmlSerlializationFormat = null;
        if (property.getSchema().getSerialization() != null) {
            xmlSerlializationFormat = property.getSchema().getSerialization().getXml();
        }

        String xmlName = null;
        String xmlNamespace = null;
        boolean isXmlWrapper = false;
        boolean isXmlAttribute = false;
        if (xmlSerlializationFormat != null) {
            isXmlWrapper = xmlSerlializationFormat.isWrapped();
            isXmlAttribute = xmlSerlializationFormat.isAttribute();
            xmlName = xmlSerlializationFormat.getName();
            xmlNamespace = xmlSerlializationFormat.getNamespace();
        }

        final String xmlParamName = xmlName == null ? serializedName.toString() : xmlName;
        builder.xmlName(xmlParamName)
                .isXmlWrapper(isXmlWrapper)
                .isXmlAttribute(isXmlAttribute)
                .xmlNamespace(xmlNamespace);

        List<String> annotationArgumentList = new ArrayList<String>() {{
            add(String.format("value = \"%s\"", xmlParamName));
        }};
        if (property.isRequired()) {
            annotationArgumentList.add("required = true");
        }
        if (property.isReadOnly()) {
            annotationArgumentList.add("access = JsonProperty.Access.WRITE_ONLY");
        }
        builder.annotationArguments(String.join(", ", annotationArgumentList));

        String headerCollectionPrefix = null;
        if (property.getExtensions() != null && property.getExtensions().getXmsHeaderCollectionPrefix() != null) {
            headerCollectionPrefix = property.getExtensions().getXmsHeaderCollectionPrefix();
        }
        builder.headerCollectionPrefix(headerCollectionPrefix);

        IType propertyWireType = Mappers.getSchemaMapper().map(property.getSchema());
        if (property.isNullable() || !property.isRequired()) {
            propertyWireType = propertyWireType.asNullable();
        }
        // Invariant: clientType == wireType.getClientType()
        IType propertyClientType = propertyWireType.getClientType();
        builder.wireType(propertyWireType).clientType(propertyClientType);

        Schema autoRestPropertyModelType = property.getSchema();
        if (autoRestPropertyModelType instanceof ArraySchema) {
            ArraySchema sequence = (ArraySchema) autoRestPropertyModelType;
            if (sequence.getElementType().getSerialization() != null
                && sequence.getElementType().getSerialization().getXml() != null
                && sequence.getElementType().getSerialization().getXml().getName() != null) {
                builder.xmlListElementName(sequence.getElementType().getSerialization().getXml().getName());
            } else {
                builder.xmlListElementName(sequence.getElementType().getLanguage().getDefault().getName());
            }
        }

        if (property.getSchema() instanceof ConstantSchema) {
            Object objValue = ((ConstantSchema) property.getSchema()).getValue().getValue();
            builder.isConstant(true);
            builder.defaultValue(objValue == null ? null : propertyClientType.defaultValueExpression(String.valueOf(objValue)));
        }

        // x-ms-mutability
        if (property.getExtensions() != null) {
            List<String> xmsMutability = property.getExtensions().getXmsMutability();
            if (xmsMutability != null) {
                List<ClientModelProperty.Mutability> mutabilities = xmsMutability.stream()
                        .map(m -> ClientModelProperty.Mutability.valueOf(m.toUpperCase(Locale.ROOT)))
                        .collect(Collectors.toList());
                builder.mutabilities(mutabilities);
            }
        }

        // handle x-ms-client-default for primitive type, enum, boxed type and string
        if (property.getClientDefaultValue() != null &&
                (propertyWireType instanceof PrimitiveType || propertyWireType instanceof EnumType ||
                        (propertyWireType instanceof ClassType && ((ClassType) propertyWireType).isBoxedType()) ||
                        propertyWireType.equals(ClassType.String))) {
            String autoRestPropertyDefaultValueExpression = propertyWireType.defaultValueExpression(property.getClientDefaultValue());
            builder.defaultValue(autoRestPropertyDefaultValueExpression);
        }

        return builder.build();
    }
}
