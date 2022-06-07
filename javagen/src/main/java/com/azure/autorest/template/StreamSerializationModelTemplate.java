// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ArrayType;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.IterableType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaIfBlock;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.serializer.JsonUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;

import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Writes a ClientModel to a JavaFile using stream-style serialization.
 */
// TODO (alzimmer): Determine if this can be merged back into ModelTemplate with if checks
public class StreamSerializationModelTemplate extends ModelTemplate {
    private static final StreamSerializationModelTemplate INSTANCE = new StreamSerializationModelTemplate();

    protected StreamSerializationModelTemplate() {
    }

    public static StreamSerializationModelTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    protected void addSerializationImports(Set<String> imports, JavaSettings settings) {
        imports.add(JsonSerializable.class.getName());
        imports.add(JsonWriter.class.getName());
        imports.add(JsonReader.class.getName());
        imports.add(JsonToken.class.getName());
        imports.add(JsonUtils.class.getName());

        imports.add(CoreUtils.class.getName());

        imports.add(ArrayList.class.getName());
        imports.add(Base64.class.getName());
        imports.add(IllegalStateException.class.getName());
        imports.add(LinkedHashMap.class.getName());
        imports.add(List.class.getName());
    }

    @Override
    protected void handlePolymorphism(ClientModel model, boolean hasDerivedModels,
        boolean isDiscriminatorPassedToChildDeserialization, JavaFile javaFile) {
        // no-op as stream-style serialization doesn't need to add anything for polymorphic types.
    }

    @Override
    protected void addClassLevelAnnotations(ClientModel model, JavaFile javaFile, JavaSettings settings) {
        // no-op as stream-style serialization doesn't add any class-level annotations.
    }

    @Override
    protected String addSerializationImplementations(String classSignature, ClientModel model, JavaSettings settings) {
        // TODO (alzimmer): Once XML stream-style serialization is supported this will need to determine whether
        //  JsonCapable and/or XmlCapable need to be added as an implemented interface.
        if (settings.isStreamStyleSerialization()) {
            classSignature += " implements " + JsonSerializable.class.getSimpleName() + "<" + model.getName() + ">";
        }

        return classSignature;
    }

    @Override
    protected void addModelConstructorParameter(ClientModelProperty property,
        StringBuilder constructorSignatureBuilder) {
        constructorSignatureBuilder.append(property.getClientType()).append(" ").append(property.getName());
    }

    @Override
    protected void addFieldAnnotations(ClientModelProperty property, JavaClass classBlock, JavaSettings settings) {
        // no-op as stream-style serialization doesn't add any field-level annotations.
    }

    @Override
    protected void writeStreamStyleSerialization(JavaClass classBlock, ClientModel model, JavaSettings settings) {
        writeToJson(classBlock, model, settings);
        writeFromJson(classBlock, model, settings);
    }

    private static void writeToJson(JavaClass classBlock, ClientModel model, JavaSettings settings) {
        classBlock.annotation("Override");
        classBlock.publicMethod("JsonWriter toJson(JsonWriter jsonWriter)", methodBlock -> {
            methodBlock.methodReturn("jsonWriter.flush()");
        });
    }

    /*
     * Writes the fromJson(JsonReader) implementation.
     */
    private static void writeFromJson(JavaClass classBlock, ClientModel model, JavaSettings settings) {
        List<ClientModelProperty> superRequiredProperties = new ArrayList<>();
        List<ClientModelProperty> superSetterProperties = new ArrayList<>();
        for (ClientModelProperty property : ClientModelUtil.getParentProperties(model)) {
            // Ignore constants and additional properties.
            if (property.getIsConstant() || property.isAdditionalProperties()) {
                continue;
            } else if (property.isRequired()) {
                superRequiredProperties.add(property);
            } else {
                superSetterProperties.add(property);
            }
        }

        List<ClientModelProperty> requiredProperties = new ArrayList<>();
        List<ClientModelProperty> setterProperties = new ArrayList<>();
        for (ClientModelProperty property : model.getProperties()) {
            // Ignore constant properties.
            if (property.getIsConstant()) {
                continue;
            } else if (property.isRequired()) {
                requiredProperties.add(property);
            } else {
                setterProperties.add(property);
            }
        }

        classBlock.publicStaticMethod(model.getName() + " fromJson(JsonReader jsonReader)", methodBlock -> {
            // For now, use the basic JsonUtils.readObject which will return null if the JsonReader is pointing
            // to JsonToken.NULL.
            //
            // Support for a default value if null will need to be supported and for objects that get their value
            // from a JSON value instead of JSON object or are an array type.
            methodBlock.line("return JsonUtils.readObject(jsonReader, reader -> {");
            methodBlock.indent(() -> {
                // Create local variables for all properties that may be deserialized.
                superRequiredProperties.forEach(property -> createLocalVariable(methodBlock, property));
                superSetterProperties.forEach(property -> createLocalVariable(methodBlock, property));
                requiredProperties.forEach(property -> createLocalVariable(methodBlock, property));
                setterProperties.forEach(property -> createLocalVariable(methodBlock, property));

                methodBlock.line("while (reader.nextToken() != JsonToken.END_OBJECT) {");
                methodBlock.increaseIndent();
                methodBlock.line("String fieldName = reader.getFieldName();");
                methodBlock.line("reader.nextToken();");
                methodBlock.line("");

                JavaIfBlock ifBlock = null;

                // Loop over all properties and generate their deserialization handling.
                for (ClientModelProperty property : superRequiredProperties) {
                    ifBlock = handlePropertyDeserialization(property, methodBlock, ifBlock, settings);
                }

                for (ClientModelProperty property : superSetterProperties) {
                    ifBlock = handlePropertyDeserialization(property, methodBlock, ifBlock, settings);
                }

                for (ClientModelProperty property : requiredProperties) {
                    ifBlock = handlePropertyDeserialization(property, methodBlock, ifBlock, settings);
                }

                for (ClientModelProperty property : setterProperties) {
                    ifBlock = handlePropertyDeserialization(property, methodBlock, ifBlock, settings);
                }

                // All properties have been checked for, add an else block that will either ignore unknown properties
                // or add them into an additional properties bag.
                handleUnknownFieldDeserialization(methodBlock, ifBlock,
                    model.getProperties().stream().filter(ClientModelProperty::isAdditionalProperties).findFirst());
            });

            methodBlock.line("}");
            methodBlock.decreaseIndent();
            handleJsonReadReturn(methodBlock, model.getName(), superRequiredProperties, requiredProperties,
                superSetterProperties, setterProperties, settings);
            methodBlock.line("});");
        });
    }

    private static void createLocalVariable(JavaBlock methodBlock, ClientModelProperty property) {
        // If the property is required an additional tracking boolean is required. For example, if the property does
        // exist but is null it won't be possible to determine based on checking != null.
        if (property.isRequired()) {
            methodBlock.line("boolean " + property.getName() + "Found = false;");
        }

        // Always instantiate the local variable.
        methodBlock.line(property.getClientType() + " " + property.getName() + " = " + property.getClientType().defaultValueExpression() + ";");
    }

    private static JavaIfBlock handlePropertyDeserialization(ClientModelProperty property, JavaBlock methodBlock,
        JavaIfBlock ifBlock, JavaSettings settings) {
        String jsonPropertyName = property.getSerializedName();
        if (CoreUtils.isNullOrEmpty(jsonPropertyName)) {
            return ifBlock;
        }

        String condition = "\"" + jsonPropertyName + "\".equals(fieldName)";

        // TODO (alzimmer): Need to handle flattened values.
        Consumer<JavaBlock> deserializationHandling = deserializationBlock ->
            generateDeserializationLogic(deserializationBlock, property, settings);
        return (ifBlock == null)
            ? methodBlock.ifBlock(condition, deserializationHandling)
            : ifBlock.elseIfBlock(condition, deserializationHandling);
    }

    /*
     * Determines if the wire type needs to be JsonToken.NULL checked before attempting to be read.
     * At this time JsonReader doesn't have APIs for reading boxed primitives.
     */
    private static boolean wireTypeNeedsNullCheck(IType wireType) {
        return wireType == ClassType.Boolean
            || wireType == ClassType.Double
            || wireType == ClassType.Float
            || wireType == ClassType.Integer
            || wireType == ClassType.Long
            || wireType == ArrayType.ByteArray
            || wireType == ClassType.DateTimeRfc1123
            || wireType == ClassType.DateTime
            || wireType == ClassType.LocalDate
            || wireType == ClassType.Duration
            // Only non-ExpandableStringEnums need null checking. ExpandableStringEnum returns null if null is passed.
            || (wireType instanceof EnumType && !((EnumType) wireType).getExpandable())
            || wireType instanceof MapType;
    }

    private static void generateDeserializationLogic(JavaBlock deserializationBlock, ClientModelProperty property,
        JavaSettings settings) {
        IType wireType = property.getWireType();
        IType clientType = property.getClientType();

        // Attempt to determine whether the wire type is simple deserialization.
        // This is primitives, boxed primitives, a small set of string based models, and other ClientModels.
        String simpleDeserialization = getSimpleDeserialization(wireType, clientType);
        if (simpleDeserialization != null) {
            if (wireTypeNeedsNullCheck(wireType)) {
                deserializationBlock.line(property.getName() + " = JsonUtils.getNullableProperty(reader, r -> " + simpleDeserialization + ");");
            } else {
                deserializationBlock.line(property.getName() + " = " + simpleDeserialization + ";");
            }
        } else if (wireType instanceof IterableType) {
            IType elementType = ((IterableType) wireType).getElementType();
            String elementDeserialization = getSimpleDeserialization(elementType, clientType);

            deserializationBlock.text(property.getName() + " = ");
            if (wireTypeNeedsNullCheck(elementType)) {
                deserializationBlock.block("JsonUtils.readArray(reader, r ->", readArrayBlock ->
                    readArrayBlock.methodReturn("JsonUtils.getNullableProperty(r, r1 -> " + elementDeserialization + ");"));
                deserializationBlock.text(");");
            } else {
                deserializationBlock.line("JsonUtils.readArray(reader, r -> " + elementDeserialization + ");");
            }
        } else if (wireType instanceof MapType) {
            // Assumption is that the key type for the Map is a String. This may not always hold true and when that
            // becomes reality this will need to be reworked to handle that case.
            IType valueType = ((MapType) wireType).getValueType();
            String valueDeserialization = getSimpleDeserialization(valueType, clientType);

            deserializationBlock.ifBlock(property.getName() + " == null",
                ifAction -> ifAction.line(property.getName() + " = new LinkedHashMap<>();"));
            deserializationBlock.line();

            deserializationBlock.line("while (reader.nextToken() != JsonToken.END_OBJECT) {");
            deserializationBlock.increaseIndent();

            deserializationBlock.line("fieldName = reader.getFieldName();");
            deserializationBlock.line("reader.nextToken();");
            deserializationBlock.line();
            if (wireTypeNeedsNullCheck(valueType)) {
                deserializationBlock.line(property.getName() + ".put(fieldName, JsonUtils.getNullableProperty(reader, r -> " + valueDeserialization + "));");
            } else {
                deserializationBlock.line(property.getName() + ".put(fieldName, " + valueDeserialization + ");");
            }

            deserializationBlock.decreaseIndent();
            deserializationBlock.line("}");
        } else {
            // TODO (alzimmer): Resolve this as deserialization logic generation needs to handle all cases.
            throw new RuntimeException("Unknown wire type " + wireType + ". Need to add support for it.");
        }

        // If the property was required, mark it as found.
        if (property.isRequired()) {
            deserializationBlock.line(property.getName() + "Found = true;");
        }
    }

    private static String getSimpleDeserialization(IType wireType, IType clientType) {
        String wireTypeDeserialization;
        if (wireType == PrimitiveType.Boolean || wireType == ClassType.Boolean) {
            wireTypeDeserialization = "reader.getBooleanValue()";
        } else if (wireType == PrimitiveType.Double || wireType == ClassType.Double) {
            wireTypeDeserialization = "reader.getDoubleValue()";
        } else if (wireType == PrimitiveType.Float || wireType == ClassType.Float) {
            wireTypeDeserialization = "reader.getFloatValue()";
        } else if (wireType == PrimitiveType.Int || wireType == ClassType.Integer) {
            wireTypeDeserialization = "reader.getIntValue()";
        } else if (wireType == PrimitiveType.Long || wireType == ClassType.Long) {
            wireTypeDeserialization = "reader.getLongValue()";
        } else if (wireType == ArrayType.ByteArray) {
            wireTypeDeserialization = "Base64.getDecoder().decode(reader.getStringValue())";
        } else if (wireType == ClassType.String) {
            wireTypeDeserialization = "reader.getStringValue()";
        } else if (wireType == ClassType.DateTimeRfc1123) {
            wireTypeDeserialization = "new DateTimeRfc1123(reader.getStringValue())";
        } else if (wireType == ClassType.DateTime) {
            wireTypeDeserialization = "OffsetDateTime.parse(reader.getStringValue())";
        } else if (wireType == ClassType.LocalDate) {
            wireTypeDeserialization = "LocalDate.parse(reader.getStringValue())";
        } else if (wireType == ClassType.Duration) {
            wireTypeDeserialization = "Duration.parse(reader.getStringValue())";
        } else if (wireType instanceof EnumType) {
            EnumType enumType = (EnumType) wireType;
            wireTypeDeserialization = enumType.getName() + "." + enumType.getFromJsonMethodName() + "(reader.getStringValue())";
        } else if (wireType instanceof ClassType) {
            wireTypeDeserialization = wireType + ".fromJson(reader)";
        } else {
            wireTypeDeserialization = null;
        }

        if (wireType != clientType && wireTypeDeserialization != null) {
            return wireType.convertToClientType(wireTypeDeserialization);
        } else {
            return wireTypeDeserialization;
        }
    }

    private static void handleUnknownFieldDeserialization(JavaBlock methodBlock, JavaIfBlock ifBlock,
        Optional<ClientModelProperty> additionalProperties) {
        Consumer<JavaBlock> unknownFieldConsumer = javaBlock -> {
            if (additionalProperties.isPresent()) {
                ClientModelProperty additionalProps = additionalProperties.get();
                javaBlock.ifBlock(additionalProps.getName() + " == null",
                    ifAction -> ifAction.line(additionalProps.getName() + " = new LinkedHashMap<>();"));
                javaBlock.line();

                // Assumption, additional properties is a Map of String-Object
                IType valueType = ((MapType) additionalProps.getWireType()).getValueType();
                if (valueType == ClassType.Object) {
                    // String fieldName should be a local variable accessible in this spot of code.
                    javaBlock.line(additionalProps.getName() + ".put(fieldName, JsonUtils.readUntypedField(reader));");
                } else {
                    // Another assumption, the additional properties value type is simple.
                    javaBlock.line(additionalProps.getName() + ".put(fieldName, "
                        + getSimpleDeserialization(valueType, valueType) + ");");
                }
            } else {
                javaBlock.line("reader.skipChildren();");
            }
        };

        if (ifBlock == null) {
            unknownFieldConsumer.accept(methodBlock);
        } else {
            ifBlock.elseBlock(unknownFieldConsumer);
        }
    }

    private static void handleJsonReadReturn(JavaBlock methodBlock, String modelName,
        List<ClientModelProperty> superRequiredProperties, List<ClientModelProperty> requiredProperties,
        List<ClientModelProperty> superSetterProperties, List<ClientModelProperty> setterProperties,
        JavaSettings settings) {

        StringBuilder constructorArgs = new StringBuilder(
            (requiredProperties.size() + superRequiredProperties.size()) * 64);

        superRequiredProperties.stream().map(ClientModelProperty::getName)
            .forEach(arg -> {
                if (constructorArgs.length() > 0) {
                    constructorArgs.append(", ");
                }
                constructorArgs.append(arg);
            });

        requiredProperties.stream().map(ClientModelProperty::getName)
            .forEach(arg -> {
                if (constructorArgs.length() > 0) {
                    constructorArgs.append(", ");
                }
                constructorArgs.append(arg);
            });

        // If there are required properties of any type we must check that all required fields were found.
        if (!CoreUtils.isNullOrEmpty(superRequiredProperties) || !CoreUtils.isNullOrEmpty(requiredProperties)) {
            methodBlock.line("List<String> missingProperties = new ArrayList<>();");
            superRequiredProperties.forEach(property -> methodBlock.ifBlock("!" + property.getName() + "Found",
                ifAction -> ifAction.line("missingProperties.add(\"" + property.getSerializedName() + "\");")));
            requiredProperties.forEach(property -> methodBlock.ifBlock("!" + property.getName() + "Found",
                ifAction -> ifAction.line("missingProperties.add(\"" + property.getSerializedName() + "\");")));

            methodBlock.line();
            methodBlock.ifBlock("!CoreUtils.isNullOrEmpty(missingProperties)", ifAction ->
                ifAction.line("throw new IllegalStateException(\"Missing required property/properties: \""
                    + "+ String.join(\", \", missingProperties));"));
        }

        methodBlock.line(modelName + " deserializedValue = new " + modelName + "(" + constructorArgs + ");");

        superSetterProperties.forEach(property -> handleSettingDeserializedValue(methodBlock, property, settings));
        setterProperties.forEach(property -> handleSettingDeserializedValue(methodBlock, property, settings));

        methodBlock.line();
        methodBlock.methodReturn("deserializedValue");
    }

    private static void handleSettingDeserializedValue(JavaBlock methodBlock, ClientModelProperty property,
        JavaSettings settings) {
        // If the property has a setter use it.
        // Otherwise, set the property directly.
        if (ClientModelUtil.hasSetter(property, settings)) {
            methodBlock.line("deserializedValue." + property.getSetterName() + "(" + property.getName() + ");");
        } else {
            methodBlock.line("deserializedValue." + property.getName() + " = " + property.getName() + ";");
        }
    }
}
