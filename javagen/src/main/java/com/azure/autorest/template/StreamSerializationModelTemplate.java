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
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.serializer.JsonUtils;
import com.azure.json.DefaultJsonReader;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Writes a ClientModel to a JavaFile using stream-style serialization.
 */
public class StreamSerializationModelTemplate extends ModelTemplate {
    private static final StreamSerializationModelTemplate INSTANCE = new StreamSerializationModelTemplate();
    private static final Pattern SPLIT_KEY_PATTERN = Pattern.compile("((?<!\\\\))\\.");

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
        imports.add(DefaultJsonReader.class.getName());
        imports.add(JsonToken.class.getName());
        imports.add(JsonUtils.class.getName());

        imports.add(CoreUtils.class.getName());

        imports.add(ArrayList.class.getName());
        imports.add(Base64.class.getName());
        imports.add(IllegalStateException.class.getName());
        imports.add(LinkedHashMap.class.getName());
        imports.add(List.class.getName());
        imports.add(Objects.class.getName());
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
        PropertiesManager propertiesManager = new PropertiesManager(model);

        writeToJson(classBlock, propertiesManager);
        writeFromJson(classBlock, model, propertiesManager, settings);
    }

    private static void writeToJson(JavaClass classBlock, PropertiesManager propertiesManager) {
        classBlock.annotation("Override");
        classBlock.publicMethod("JsonWriter toJson(JsonWriter jsonWriter)", methodBlock -> {
            methodBlock.line("jsonWriter.writeStartObject();");

            // If the model has a discriminator property serialize it first.
            if (propertiesManager.discriminatorProperty != null
                && !CoreUtils.isNullOrEmpty(propertiesManager.discriminatorProperty.getDefaultValue())) {
                ClientModelProperty discriminatorProperty = propertiesManager.discriminatorProperty;
                methodBlock.line("jsonWriter.writeStringField(\"" + discriminatorProperty.getSerializedName()
                    + "\", " + discriminatorProperty.getName() + ");");
            }

            propertiesManager.superRequiredProperties.forEach(property ->
                serializeProperty(methodBlock, property, property.getSerializedName(), true, true));
            propertiesManager.superSetterProperties.forEach(property ->
                serializeProperty(methodBlock, property, property.getSerializedName(), true, true));
            propertiesManager.requiredProperties.forEach(property ->
                serializeProperty(methodBlock, property, property.getSerializedName(), false, true));
            propertiesManager.setterProperties.forEach(property ->
                serializeProperty(methodBlock, property, property.getSerializedName(), false, true));

            handleFlattenedPropertiesSerialization(methodBlock, propertiesManager.flattenedPropertiesStructure);

            ClientModelProperty additionalProperties = propertiesManager.additionalProperties;
            if (additionalProperties != null) {
                methodBlock.ifBlock(additionalProperties.getName() + " != null", ifAction -> {
                    ifAction.line(additionalProperties.getName() + ".forEach((key, value) -> {");
                    ifAction.indent(() -> {
                        ifAction.line("jsonWriter.writeFieldName(key);");
                        ifAction.line("JsonUtils.writeUntypedField(jsonWriter, value);");
                    });
                    ifAction.line("});");
                });
            }

            methodBlock.methodReturn("jsonWriter.writeEndObject().flush()");
        });
    }

    /**
     * Serializes a non-flattened, non-additional properties property.
     * <p>
     * If the property needs to be flattened or is additional properties this is a no-op as those require special
     * handling that will occur later.
     *
     * @param methodBlock The method handling serialization.
     * @param property The property being serialized.
     * @param serializedName The serialized property name. Generally, this is just the {@code property property's}
     * serialized name but if a flattened property is being serialized it'll be the last segment of the flattened JSON
     * name.
     * @param fromSuperType Whether the property is defined by a super type of the model. If the property is declared by
     * a super type a getter method will be used to retrieve the value instead of accessing the field directly.
     * @param ignoreFlattening Whether flattened properties should be skipped. Will only be false when handling the
     * terminal location of a flattened structure.
     */
    private static void serializeProperty(JavaBlock methodBlock, ClientModelProperty property, String serializedName,
        boolean fromSuperType, boolean ignoreFlattening) {
        if ((ignoreFlattening && property.getNeedsFlatten()) || property.isAdditionalProperties()) {
            // Property will be handled later by flattened or additional properties serialization.
            return;
        }

        IType wireType = property.getWireType();
        String propertyValueGetter = fromSuperType ? property.getGetterName() + "()" : "this." + property.getName();

        // Attempt to determine whether the wire type is simple serialization.
        // This is primitives, boxed primitives, a small set of string based models, and other ClientModels.
        String fieldSerializationMethod = wireType.streamStyleJsonFieldSerializationMethod();
        if (fieldSerializationMethod != null) {
            if (wireType instanceof EnumType) {
                methodBlock.line("jsonWriter." + fieldSerializationMethod + "(\"" + serializedName + "\", "
                    + propertyValueGetter + " == null ? null : " + propertyValueGetter + ".toString(), false);");
            } else if (wireType.isNullable()) {
                methodBlock.line("jsonWriter." + fieldSerializationMethod + "(\"" + serializedName + "\", "
                    + addPotentialSerializationNullCheck(wireType, fieldSerializationMethod, propertyValueGetter) + ", false);");
            } else {
                methodBlock.line("jsonWriter." + fieldSerializationMethod + "(\"" + serializedName + "\", " + propertyValueGetter + ");");
            }
        } else if (wireType == ClassType.Object) {
            methodBlock.line("JsonUtils.writeUntypedField(jsonWriter, " + propertyValueGetter + ");");
        } else if (wireType instanceof IterableType) {
            serializeContainerProperty(methodBlock, "writeArray", wireType, ((IterableType) wireType).getElementType(),
                serializedName, propertyValueGetter, 0);
        } else if (wireType instanceof MapType) {
            // Assumption is that the key type for the Map is a String. This may not always hold true and when that
            // becomes reality this will need to be reworked to handle that case.
            serializeContainerProperty(methodBlock, "writeMap", wireType, ((MapType) wireType).getValueType(),
                serializedName, propertyValueGetter, 0);
        } else {
            // TODO (alzimmer): Resolve this as deserialization logic generation needs to handle all cases.
            throw new RuntimeException("Unknown wire type " + wireType + " in serialization. Need to add support for it.");
        }
    }

    /**
     * Helper method to serialize a container property (such as {@link List} and {@link Map}).
     *
     * @param methodBlock The method handling serialization.
     * @param utilityMethod The {@link JsonUtils} method aiding in the serialization of the container.
     * @param containerType The container type.
     * @param elementType The element type for the container, for a {@link List} this is the element type and for a
     * {@link Map} this is the value type.
     * @param serializedName The serialized property name.
     * @param propertyValueGetter The property or property getter for the field being serialized.
     * @param depth Depth of recursion for container types, such as {@code Map<String, List<String>>} would be 0 when
     * {@code Map} is being handled and then 1 when {@code List} is being handled.
     */
    private static void serializeContainerProperty(JavaBlock methodBlock, String utilityMethod, IType containerType,
        IType elementType, String serializedName, String propertyValueGetter, int depth) {
        String valueSerializationMethod = elementType.streamStyleJsonValueSerializationMethod();
        String lambdaWriterName = depth == 0 ? "writer" : "writer" + depth;
        String elementName = depth == 0 ? "element" : "element" + depth;
        String serializeValue = depth == 0 ? propertyValueGetter
            : ((depth == 1) ? "element" : "element" + (depth - 1));

        // TODO (alzimmer): Determine if the writeNull property should ever be set to true for writing the container
        //  property. Right now this won't serialize anything if the container value is null.
        methodBlock.line("JsonUtils." + utilityMethod + "(jsonWriter, \"" + serializedName + "\", " + serializeValue + ", (" + lambdaWriterName + ", " + elementName + ") -> ");
        methodBlock.indent(() -> {
            if (valueSerializationMethod != null) {
                // TODO (alzimmer): Determine if null list elements and null values in a key-value pair should be serialized.
                if (elementType instanceof EnumType) {
                    methodBlock.line(lambdaWriterName + "." + valueSerializationMethod + "(" + elementName + " == null ? null : " + elementName + ".toString())");
                } else if (elementType.isNullable()) {
                    methodBlock.line(lambdaWriterName + "." + valueSerializationMethod + "("
                        + addPotentialSerializationNullCheck(elementType, valueSerializationMethod, elementName) + ", false)");
                } else {
                    methodBlock.line(lambdaWriterName + "." + valueSerializationMethod + "(" + elementName + ")");
                }
            } else if (elementType == ClassType.Object) {
                methodBlock.line("JsonUtils.writeUntypedField(" + lambdaWriterName + ", " + elementName + ")");
            } else if (elementType instanceof IterableType) {
                serializeContainerProperty(methodBlock, "writeArray", elementType, ((IterableType) elementType).getElementType(),
                    serializedName, propertyValueGetter, depth + 1);
            } else if (elementType instanceof MapType) {
                // Assumption is that the key type for the Map is a String. This may not always hold true and when that
                // becomes reality this will need to be reworked to handle that case.
                serializeContainerProperty(methodBlock, "writeMap", elementType, ((MapType) elementType).getValueType(),
                    serializedName, propertyValueGetter, depth + 1);
            } else {
                throw new RuntimeException("Unknown value type " + elementType + " in " + containerType
                    + " serialization. Need to add support for it.");
            }
        });

        if (depth > 0) {
            methodBlock.line(")");
        } else {
            methodBlock.line(");");
        }
    }

    /**
     * Helper method to serialize flattened properties in a model.
     * <p>
     * Flattened properties are unique as for each level of flattening they'll create a JSON sub-object. But before a
     * sub-object is created any field needs to be checked for either being a primitive value or non-null. Primitive
     * values are usually serialized no matter their value so those will automatically trigger the JSON sub-object to be
     * created, nullable values will be checked for being non-null.
     * <p>
     * In addition to primitive or non-null checking fields, all properties from the same JSON sub-object must be
     * written at the same time to prevent an invalid JSON structure. For example if a model has three flattened
     * properties with JSON paths "im.flattened", "im.deeper.flattened", and "im.deeper.flattenedtoo" this will create
     * the following structure:
     *
     * <pre>
     * im -> flattened
     *     | deeper -> flattened
     *               | flattenedtoo
     * </pre>
     *
     * So, "im.deeper.flattened" and "im.deeper.flattenedtoo" will need to be serialized at the same time to get the
     * correct JSON where there is only one "im: deeper" JSON sub-object.
     *
     * @param methodBlock The method handling serialization.
     * @param flattenedProperties The flattened properties structure.
     */
    private static void handleFlattenedPropertiesSerialization(JavaBlock methodBlock,
        FlattenedPropertiesStructure flattenedProperties) {
        // The initial call to handle flattened properties is using the base node which is just a holder.
        for (FlattenedPropertiesStructure flattened : flattenedProperties.subNodes.values()) {
            handleFlattenedPropertiesSerializationHelper(methodBlock, flattened);
        }
    }

    private static void handleFlattenedPropertiesSerializationHelper(JavaBlock methodBlock,
        FlattenedPropertiesStructure flattenedProperties) {
        ClientModelPropertyWithMetadata flattenedProperty = flattenedProperties.property;
        if (flattenedProperty != null) {
            // This is a terminal location, only need to add property serialization.
            serializeProperty(methodBlock, flattenedProperty.property, flattenedProperties.nodeName,
                flattenedProperty.fromSuperClass, false);
        } else {
            // Otherwise this is an intermediate location.
            // Check for either any of the properties in this subtree being primitives or add an if block checking that
            // any of the properties are non-null.
            List<ClientModelPropertyWithMetadata> propertiesInFlattenedGroup
                = flattenedProperties.getClientModelPropertiesInTree();
            boolean hasPrimitivePropertyInGroup = propertiesInFlattenedGroup.stream()
                .map(property -> property.property.getWireType())
                .anyMatch(wireType -> wireType instanceof PrimitiveType);

            if (hasPrimitivePropertyInGroup) {
                // Simple case where the flattened group has a primitive type where non-null checking doesn't need
                // to be done.
                methodBlock.line("jsonWriter.writeStartObject(\"" + flattenedProperties.nodeName + "\");");
                for (FlattenedPropertiesStructure flattened : flattenedProperties.subNodes.values()) {
                    handleFlattenedPropertiesSerializationHelper(methodBlock, flattened);
                }
                methodBlock.line("jsonWriter.writeEndObject();");
            } else {
                // Complex case where all properties in the flattened group are nullable and a check needs to be made
                // if any value is non-null.
                String condition = propertiesInFlattenedGroup.stream()
                    .map(property -> (property.fromSuperClass)
                        ? property.property.getGetterName() + "() != null"
                        : property.property.getName() + " != null")
                    .collect(Collectors.joining(" || "));

                methodBlock.ifBlock(condition, ifAction -> {
                    ifAction.line("jsonWriter.writeStartObject(\"" + flattenedProperties.nodeName + "\");");
                    for (FlattenedPropertiesStructure flattened : flattenedProperties.subNodes.values()) {
                        handleFlattenedPropertiesSerializationHelper(ifAction, flattened);
                    }
                    ifAction.line("jsonWriter.writeEndObject();");
                });
            }
        }
    }

    /**
     * Adds a potential null check to the serialized value.
     * <p>
     * This will only modify the serialization value if the {@code type} {@link IType#isNullable() is nullable} and
     * isn't a boxed type and the {@code serializationMethod} is either {@code writeString} or {@code writeStringField}.
     * In this case the property will be stringified before being written so it needs to be null checked to prevent a
     * {@link NullPointerException}.
     *
     * @param type The type being serialized.
     * @param serializationMethod The serialization method for the type.
     * @param value The value property or value getter.
     * @return A potentially null checked to string value.
     */
    private static String addPotentialSerializationNullCheck(IType type, String serializationMethod, String value) {
        if (type.isNullable()
            && (!(type instanceof ClassType) || !((ClassType) type).isBoxedType())
            && type != ClassType.String
            && serializationMethod.startsWith("writeString")) {
            return value + " == null ? null : " + value + ".toString()";
        } else {
            return value;
        }
    }

    /*
     * Writes the fromJson(JsonReader) implementation.
     */
    private static void writeFromJson(JavaClass classBlock, ClientModel model, PropertiesManager propertiesManager,
        JavaSettings settings) {
        // All classes will create a public fromJson(JsonReader) method that initiates reading.
        // How the implementation looks depends on whether the type is a super type, subtype, both, or is a
        // stand-alone type.
        //
        // Intermediate types, those that are both a super type and subtype, will pass null as the type discriminator
        // value. This is done as super types are written to only support themselves or their subtypes, passing a
        // discriminator into its own super type would confuse this scenario. For example, one of the test Swaggers
        // generates the following hierarchy
        //
        //                     Fish
        //       Salmon                    Shark
        //     SmartSalmon    Sawshark  GoblinShark  Cookiecuttershark
        //
        // If Salmon called into Fish with its discriminator and an error occurred it would mention the Shark subtypes
        // as potential legal values for deserialization, confusing the Salmon deserializer. So, calling into Salmon
        // will only attempt to handle Salmon and SmartSalmon, and same goes for Shark with Shark, Sawshark,
        // GoblinShark, and Cookiecuttershark. It's only Fish that will handle all subtypes, as this is the most generic
        // super type. This also creates a well-defined bounds for code generation in regard to type hierarchies.
        //
        // In a real scenario someone deserializing to Salmon should have an exception about discriminator types
        // if the JSON payload is a Shark and not get a ClassCastException as it'd be more confusing on why a Shark
        // was trying to be converted to a Salmon.
        if (isSuperTypeWithDiscriminator(model)) {
            writeSuperTypeFromJson(classBlock, model, propertiesManager, settings);
        } else {
            writeTerminalTypeFromJson(classBlock, propertiesManager, settings);
        }
    }

    /**
     * Writes a super type's {@code fromJson(JsonReader)} method.
     *
     * @param classBlock The class having {@code fromJson(JsonReader)} written to it.
     * @param model The Autorest representation of the model.
     * @param propertiesManager The properties for the model.
     * @param settings The Autorest generation settings.
     */
    private static void writeSuperTypeFromJson(JavaClass classBlock, ClientModel model,
        PropertiesManager propertiesManager, JavaSettings settings) {
        String fieldNameVariableName = propertiesManager.readerFieldNameVariableName;
        ClientModelProperty discriminatorProperty = propertiesManager.discriminatorProperty;
        readObject(classBlock, model.getName(), false, methodBlock -> {
            methodBlock.line(String.join("\n",
                "String discriminatorValue = null;",
                "JsonReader readerToUse = null;",
                "",
                "// Read the first field name and determine if it's the discriminator field.",
                "reader.nextToken();",
                "if (\"" + discriminatorProperty.getSerializedName() + "\".equals(reader.getFieldName())) {",
                "    reader.nextToken();",
                "    discriminatorValue = reader.getStringValue();",
                "    readerToUse = reader;",
                "} else {",
                "    // If it isn't the discriminator field buffer the JSON to make it replayable and find the discriminator field value.",
                "    String json = JsonUtils.bufferJsonObject(reader);",
                "    JsonReader replayReader = DefaultJsonReader.fromString(json);",
                "    while (replayReader.nextToken() != JsonToken.END_OBJECT) {",
                "        String " + fieldNameVariableName + " = replayReader.getFieldName();",
                "        replayReader.nextToken();",
                "        if (\"" + discriminatorProperty.getSerializedName() + "\".equals(" + fieldNameVariableName + ")) {",
                "            discriminatorValue = replayReader.getStringValue();",
                "            break;",
                "        } else {",
                "            replayReader.skipChildren();",
                "        }",
                "    }",
                "",
                "    if (discriminatorValue != null) {",
                "        readerToUse = DefaultJsonReader.fromString(json);",
                "    }",
                "}"
            ));

            methodBlock.line("// Use the discriminator value to determine which subtype should be deserialized.");

            // Add a throw statement if the discriminator value didn't match anything known.
            StringBuilder exceptionMessage = new StringBuilder("Discriminator field '")
                .append(discriminatorProperty.getSerializedName())
                .append("' didn't match one of the expected values ");

            if (!CoreUtils.isNullOrEmpty(discriminatorProperty.getDefaultValue())) {
                exceptionMessage.append("'").append(propertiesManager.expectedDiscriminator).append("'");
            }

            // Add deserialization for the super type itself.
            JavaIfBlock ifBlock = (CoreUtils.isNullOrEmpty(discriminatorProperty.getDefaultValue())) ? null
                : methodBlock.ifBlock("discriminatorValue == null || \"" + propertiesManager.expectedDiscriminator + "\".equals(discriminatorValue)",
                ifStatement -> ifStatement.methodReturn("fromJsonKnownDiscriminator(readerToUse)"));

            // Add deserialization for all child types.
            List<ClientModel> childTypes = getAllChildTypes(model, new ArrayList<>());
            for (int i = 0; i < childTypes.size(); i++) {
                ClientModel childType = childTypes.get(i);

                ifBlock = ifOrElseIf(methodBlock, ifBlock, "\"" + childType.getSerializedName() + "\".equals(discriminatorValue)",
                    ifStatement -> ifStatement.methodReturn(childType.getName() + (isSuperTypeWithDiscriminator(childType)
                        ? ".fromJsonKnownDiscriminator(readerToUse)"
                        : ".fromJson(readerToUse)")));

                if (CoreUtils.isNullOrEmpty(discriminatorProperty.getDefaultValue()) && i == 0) {
                    exceptionMessage.append("'").append(childType.getSerializedName()).append("'");
                } else if (i < childTypes.size() - 1) {
                    exceptionMessage.append(", '").append(childType.getSerializedName()).append("'");
                } else {
                    ((childTypes.size() == 1) ? exceptionMessage.append(" or '") : exceptionMessage.append(", or '"))
                        .append(childType.getSerializedName())
                        .append("'. It was: '\" + discriminatorValue + \"'.");
                }
            }

            if (ifBlock == null) {
                methodBlock.line("throw new IllegalStateException(\"" + exceptionMessage + "\");");
            } else {
                ifBlock.elseBlock(elseBlock -> elseBlock.line("throw new IllegalStateException(\"" + exceptionMessage + "\");"));
            }
        });

        if (!CoreUtils.isNullOrEmpty(discriminatorProperty.getDefaultValue())) {
            readObject(classBlock, model.getName(), true,
                methodBlock -> writeFromJsonDeserialization(methodBlock, propertiesManager, settings));
        }
    }

    private static List<ClientModel> getAllChildTypes(ClientModel model, List<ClientModel> childTypes) {
        for (ClientModel childType : model.getDerivedModels()) {
            childTypes.add(childType);
            if (!CoreUtils.isNullOrEmpty(childType.getDerivedModels())) {
                getAllChildTypes(childType, childTypes);
            }
        }

        return childTypes;
    }

    /**
     * Writes a terminal type's {@code fromJson(JsonReader)} method.
     * <p>
     * A terminal type is either a type without polymorphism or is the terminal type in a polymorphic hierarchy.
     *
     * @param classBlock The class having {@code fromJson(JsonReader)} written to it.
     * @param propertiesManager The properties for the model.
     * @param settings The Autorest generation settings.
     */
    private static void writeTerminalTypeFromJson(JavaClass classBlock, PropertiesManager propertiesManager,
        JavaSettings settings) {
        readObject(classBlock, propertiesManager.modelName, false,
            methodBlock -> writeFromJsonDeserialization(methodBlock, propertiesManager, settings));
    }

    private static void writeFromJsonDeserialization(JavaBlock methodBlock, PropertiesManager propertiesManager,
        JavaSettings settings) {
        // Add the deserialization logic.
        methodBlock.indent(() -> {
            // Initialize local variables to track what has been deserialized.
            initializeLocalVariables(methodBlock, propertiesManager);

            // Add the outermost while loop to read the JSON object.
            String fieldNameVariableName = propertiesManager.readerFieldNameVariableName;
            addReaderWhileLoop(methodBlock, true, fieldNameVariableName, whileBlock -> {
                JavaIfBlock ifBlock = handleDiscriminatorDeserialization(whileBlock,
                    propertiesManager.discriminatorProperty, propertiesManager.readerFieldNameVariableName);

                // Loop over all properties and generate their deserialization handling.
                ifBlock = handlePropertiesDeserialization(propertiesManager.superRequiredProperties, whileBlock,
                    ifBlock, fieldNameVariableName);
                ifBlock = handlePropertiesDeserialization(propertiesManager.superSetterProperties, whileBlock,
                    ifBlock, fieldNameVariableName);
                ifBlock = handlePropertiesDeserialization(propertiesManager.requiredProperties, whileBlock,
                    ifBlock, fieldNameVariableName);
                ifBlock = handlePropertiesDeserialization(propertiesManager.setterProperties, whileBlock,
                    ifBlock, fieldNameVariableName);

                handleFlattenedPropertiesDeserialization(propertiesManager.flattenedPropertiesStructure, methodBlock,
                    ifBlock, propertiesManager.additionalProperties, propertiesManager.readerFieldNameVariableName);

                // All properties have been checked for, add an else block that will either ignore unknown properties
                // or add them into an additional properties bag.
                handleUnknownFieldDeserialization(whileBlock, ifBlock, propertiesManager.additionalProperties,
                    propertiesManager.readerFieldNameVariableName);
            });
        });

        // Add the validation and return logic.
        handleJsonReadReturn(methodBlock, propertiesManager.modelName, propertiesManager, settings);
    }

    /**
     * Takes all properties that will be included in a {@code fromJson(JsonReader)} call and for all properties that are
     * flattened creates a tree representation of their paths.
     * <p>
     * Flattened properties require additional processing as they must be handled at the same time. For example if a
     * model has three flattened properties with JSON paths "im.flattened", "im.deeper.flattened", and
     * "im.deeper.flattenedtoo" this will create the following structure:
     *
     * <pre>
     * im -> flattened
     *     | deeper -> flattened
     *               | flattenedtoo
     * </pre>
     *
     * This structure is then used while generating deserialization logic to ensure that when the "im" JSON sub-object
     * is found that it'll look for both "flattened" and the "deeper" JSON sub-object before either reading or skipping
     * unknown properties. If this isn't done and deserialization logic is generated on a property-by-property basis,
     * this could result in the "im.flattened" check skipping the "deeper" JSON sub-object.
     *
     * @param discriminatorProperty A potential discriminator property for hierarchical models.
     * @param flattenedProperties All flattened properties that are part of a model's deserialization.
     * @return The flattened JSON properties structure, or an empty structure if the model doesn't contained flattened
     * properties.
     */
    private static FlattenedPropertiesStructure getFlattenedPropertiesHierarchy(String discriminatorProperty,
        Map<String, ClientModelPropertyWithMetadata> flattenedProperties) {
        FlattenedPropertiesStructure structure = FlattenedPropertiesStructure.createBaseNode();

        if (!CoreUtils.isNullOrEmpty(discriminatorProperty)) {
            String[] propertyHierarchy = SPLIT_KEY_PATTERN.split(discriminatorProperty);
            if (propertyHierarchy.length > 1) {
                structure = FlattenedPropertiesStructure.createBaseNode();

            }
        }

        for (ClientModelPropertyWithMetadata property : flattenedProperties.values()) {
            if (!property.property.getNeedsFlatten()) {
                // Property doesn't need flattening, ignore it.
                continue;
            }

            // Splits the flattened property into the individual properties in the JSON path.
            // For example "im.deeper.flattened" becomes ["im", "deeper", "flattened"].
            String[] propertyHierarchy = SPLIT_KEY_PATTERN.split(property.property.getSerializedName());

            if (propertyHierarchy.length == 1) {
                // Property is marked for flattening but points directly to its JSON path, ignore it.
                continue;
            }

            // Loop over all the property names in the JSON path, either getting or creating that node in the
            // flattened JSON properties structure.
            FlattenedPropertiesStructure pointer = structure;
            for (int i = 0; i < propertyHierarchy.length; i++) {
                String nodeName = propertyHierarchy[i];

                // Structure doesn't contain the flattened property.
                if (!pointer.hasSubNode(nodeName)) {
                    // Depending on whether this is the last property in the flattened property either a terminal
                    // or intermediate node will be inserted.
                    FlattenedPropertiesStructure newPointer = (i == propertyHierarchy.length - 1)
                        ? FlattenedPropertiesStructure.createTerminalNode(nodeName, property)
                        : FlattenedPropertiesStructure.createIntermediateNode(nodeName);

                    pointer.addSubNode(newPointer);
                    pointer = newPointer;
                } else {
                    pointer = pointer.getSubNode(nodeName);
                }
            }
        }

        return structure;
    }

    /**
     * Adds a static method to the class with the signature that handles reading the JSON string into the object type.
     * <p>
     * If {@code superTypeReading} is true the method will be package-private and named
     * {@code fromJsonWithKnownDiscriminator} instead of being public and named {@code fromJson}. This is done as super
     * types use their {@code fromJson} method to determine the discriminator value and pass the reader to the specific
     * type being deserialized. The specific type being deserialized may be the super type itself, so it cannot pass to
     * {@code fromJson} as this will be a circular call and if the specific type being deserialized is an intermediate
     * type (a type having both super and subclasses) it will attempt to perform discriminator validation which has
     * already been done.
     *
     * @param classBlock The class where the {@code fromJson} method is being written.
     * @param modelName The name of the class.
     * @param superTypeReading Whether the object reading is for a super type.
     * @param deserializationBlock Logic for deserializing the object.
     */
    private static void readObject(JavaClass classBlock, String modelName, boolean superTypeReading,
        Consumer<JavaBlock> deserializationBlock) {
        JavaVisibility visibility = superTypeReading ? JavaVisibility.PackagePrivate : JavaVisibility.Public;
        String methodName = superTypeReading ? "fromJsonKnownDiscriminator" : "fromJson";
        classBlock.staticMethod(visibility, modelName + " " + methodName + "(JsonReader jsonReader)", methodBlock -> {
            // For now, use the basic JsonUtils.readObject which will return null if the JsonReader is pointing
            // to JsonToken.NULL.
            //
            // Support for a default value if null will need to be supported and for objects that get their value
            // from a JSON value instead of JSON object or are an array type.
            methodBlock.line("return JsonUtils.readObject(jsonReader, reader -> {");

            deserializationBlock.accept(methodBlock);

            methodBlock.line("});");
        });
    }

    /**
     * Initializes the local variables needed to maintain what has been deserialized.
     *
     * @param methodBlock The method handling deserialization.
     * @param propertiesManager The property manager for the model.
     */
    private static void initializeLocalVariables(JavaBlock methodBlock, PropertiesManager propertiesManager) {
        // Create local variables for all properties that may be deserialized.
        //
        // Begin with adding a potential discriminator property, for now assume this is a String property.
        // Then add all instance level properties that associated with JSON property names.
        // Last add a potential additional properties Map.
        if (propertiesManager.discriminatorProperty != null) {
            // Discriminator properties are always required.
            String propertyName = propertiesManager.discriminatorProperty.getName();
            methodBlock.line("boolean " + propertyName + "Found = false;");
            methodBlock.line("String " + propertyName + " = null;");
        }

        propertiesManager.superRequiredProperties.forEach(property -> initializeLocalVariable(methodBlock, property));
        propertiesManager.superSetterProperties.forEach(property -> initializeLocalVariable(methodBlock, property));
        propertiesManager.requiredProperties.forEach(property -> initializeLocalVariable(methodBlock, property));
        propertiesManager.setterProperties.forEach(property -> initializeLocalVariable(methodBlock, property));

        if (propertiesManager.additionalProperties != null) {
            initializeLocalVariable(methodBlock, propertiesManager.additionalProperties);
        }
    }

    private static void initializeLocalVariable(JavaBlock methodBlock, ClientModelProperty property) {
        if (property.isRequired()) {
            // Required properties need an additional boolean variable to indicate they've been found.
            methodBlock.line("boolean " + property.getName() + "Found = false;");
        }

        // Always instantiate the local variable.
        IType clientType = property.getClientType();
        if (clientType.isNullable()) {
            methodBlock.line(clientType + " " + property.getName() + " = null;");
        } else {
            methodBlock.line(clientType + " " + property.getName() + " = " + clientType.defaultValueExpression() + ";");
        }
    }

    /**
     * Adds the while loop that handles reading the JSON object until it is fully consumed.
     *
     * @param methodBlock The method handling deserialization.
     * @param initializeFieldNameVariable Whether the {@code fieldNameVariableName} variable needs to be initialized. If
     * this is a nested while loop the variable doesn't need to be initialized.
     * @param fieldNameVariableName The name for the variable that tracks the JSON field name.
     * @param whileBlock The consumer that adds deserialization logic into the while loop.
     */
    private static void addReaderWhileLoop(JavaBlock methodBlock, boolean initializeFieldNameVariable,
        String fieldNameVariableName, Consumer<JavaBlock> whileBlock) {
        methodBlock.block("while (reader.nextToken() != JsonToken.END_OBJECT)", whileAction -> {
            if (initializeFieldNameVariable) {
                methodBlock.line("String " + fieldNameVariableName + " = reader.getFieldName();");
            } else {
                methodBlock.line(fieldNameVariableName + " = reader.getFieldName();");
            }
            methodBlock.line("reader.nextToken();");
            methodBlock.line("");

            whileBlock.accept(methodBlock);
        });
    }

    private static JavaIfBlock handleDiscriminatorDeserialization(JavaBlock methodBlock,
        ClientModelProperty discriminatorProperty, String fieldNameVariableName) {
        if (discriminatorProperty != null) {
            return methodBlock.ifBlock("\"" + discriminatorProperty.getSerializedName() + "\".equals(" + fieldNameVariableName + ")",
                ifAction -> {
                    ifAction.line(discriminatorProperty.getName() + "Found = true;");
                    ifAction.line(discriminatorProperty.getName() + " = reader.getStringValue();");
                });
        }

        return null;
    }

    private static JavaIfBlock handlePropertiesDeserialization(List<ClientModelProperty> properties,
        JavaBlock methodBlock, JavaIfBlock ifBlock, String fieldNameVariableName) {
        for (ClientModelProperty property : properties) {
            // Property will be handled later by flattened deserialization.
            if (property.getNeedsFlatten()) {
                continue;
            }

            ifBlock = handlePropertyDeserialization(property, methodBlock, ifBlock, fieldNameVariableName);
        }

        return ifBlock;
    }

    private static JavaIfBlock handlePropertyDeserialization(ClientModelProperty property, JavaBlock methodBlock,
        JavaIfBlock ifBlock, String fieldNameVariableName) {
        String jsonPropertyName = property.getSerializedName();
        if (CoreUtils.isNullOrEmpty(jsonPropertyName)) {
            return ifBlock;
        }

        return ifOrElseIf(methodBlock, ifBlock, "\"" + jsonPropertyName + "\".equals(" + fieldNameVariableName + ")",
            deserializationBlock -> generateDeserializationLogic(deserializationBlock, property, fieldNameVariableName));
    }

    private static void handleFlattenedPropertiesDeserialization(
        FlattenedPropertiesStructure flattenedProperties, JavaBlock methodBlock, JavaIfBlock ifBlock,
        ClientModelProperty additionalProperties, String fieldNameVariableName) {
        // The initial call to handle flattened properties is using the base node which is just a holder.
        for (FlattenedPropertiesStructure structure : flattenedProperties.subNodes.values()) {
            handleFlattenedPropertiesDeserializationHelper(structure, methodBlock, ifBlock, additionalProperties,
                fieldNameVariableName);
        }
    }

    private static JavaIfBlock handleFlattenedPropertiesDeserializationHelper(
        FlattenedPropertiesStructure flattenedProperties, JavaBlock methodBlock, JavaIfBlock ifBlock,
        ClientModelProperty additionalProperties, String fieldNameVariableName) {
        if (flattenedProperties.property != null) {
            // This is a terminal location, so only need to handle checking for the property name.
            return ifOrElseIf(methodBlock, ifBlock,
                "\"" + flattenedProperties.nodeName + "\".equals(" + fieldNameVariableName + ")", deserializationBlock ->
                    generateDeserializationLogic(deserializationBlock, flattenedProperties.property.property, fieldNameVariableName));
        } else {
            // Otherwise this is an intermediate location and a while loop reader needs to be added.
            return ifOrElseIf(methodBlock, ifBlock,
                "\"" + flattenedProperties.nodeName + "\".equals(" + fieldNameVariableName + ") && reader.currentToken() == JsonToken.START_OBJECT",
                ifAction -> addReaderWhileLoop(ifAction, false, fieldNameVariableName, whileBlock -> {
                    JavaIfBlock innerIfBlock = null;
                    for (FlattenedPropertiesStructure structure : flattenedProperties.subNodes.values()) {
                        innerIfBlock = handleFlattenedPropertiesDeserializationHelper(structure, methodBlock, innerIfBlock,
                            additionalProperties, fieldNameVariableName);
                    }

                    handleUnknownFieldDeserialization(whileBlock, innerIfBlock, additionalProperties, fieldNameVariableName);
                }));
        }
    }

    private static void generateDeserializationLogic(JavaBlock deserializationBlock, ClientModelProperty property,
        String fieldNameVariableName) {
        IType wireType = property.getWireType();
        IType clientType = property.getClientType();

        // Attempt to determine whether the wire type is simple deserialization.
        // This is primitives, boxed primitives, a small set of string based models, and other ClientModels.
        String simpleDeserialization = getSimpleDeserialization(wireType, clientType);
        if (simpleDeserialization != null) {
            if (deserializationNeedsNullGuard(wireType)) {
                deserializationBlock.line(property.getName() + " = JsonUtils.getNullableProperty(reader, r -> " + simpleDeserialization + ");");
            } else {
                deserializationBlock.line(property.getName() + " = " + simpleDeserialization + ";");
            }
        } else if (wireType == ClassType.Object) {
            deserializationBlock.line(property.getName() + " = JsonUtils.readUntypedField(reader);");
        } else if (wireType instanceof IterableType) {
            IType elementType = ((IterableType) wireType).getElementType();
            String elementDeserialization = getSimpleDeserialization(elementType, clientType);

            deserializationBlock.text(property.getName() + " = ");
            if (deserializationNeedsNullGuard(elementType)) {
                deserializationBlock.line("JsonUtils.readArray(reader, r -> JsonUtils.getNullableProperty(r, r1 -> " + elementDeserialization + "));");
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

            deserializationBlock.line(fieldNameVariableName + " = reader.getFieldName();");
            deserializationBlock.line("reader.nextToken();");
            deserializationBlock.line();
            if (deserializationNeedsNullGuard(valueType)) {
                deserializationBlock.line(property.getName() + ".put(" + fieldNameVariableName + ", JsonUtils.getNullableProperty(reader, r -> " + valueDeserialization + "));");
            } else {
                deserializationBlock.line(property.getName() + ".put(" + fieldNameVariableName + ", " + valueDeserialization + ");");
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
        } else if (wireType == PrimitiveType.Char || wireType == ClassType.Character) {
            wireTypeDeserialization = "reader.getStringValue().charAt(0)";
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
        } else if (wireType == ClassType.UUID) {
            wireTypeDeserialization = "UUID.fromString(reader.getStringValue())";
        } else if (wireType == ClassType.Object) {
            wireTypeDeserialization = "JsonUtils.readUntypedField(reader)";
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

    private static boolean deserializationNeedsNullGuard(IType wireType) {
        return wireType.isNullable()
            && wireType != ClassType.String // Strings are deserialization null safe.
            && !(wireType instanceof EnumType && ((EnumType) wireType).getExpandable()) // ExpandableStringEnums are deserialization null safe.
            && !(wireType instanceof ClassType && ((ClassType) wireType).isSwaggerType()); // Swagger types will use fromJson which is null safe.
    }

    private static void handleUnknownFieldDeserialization(JavaBlock methodBlock, JavaIfBlock ifBlock,
        ClientModelProperty additionalProperties, String fieldNameVariableName) {
        Consumer<JavaBlock> unknownFieldConsumer = javaBlock -> {
            if (additionalProperties != null) {
                javaBlock.ifBlock(additionalProperties.getName() + " == null",
                    ifAction -> ifAction.line(additionalProperties.getName() + " = new LinkedHashMap<>();"));
                javaBlock.line();

                // Assumption, additional properties is a Map of String-Object
                IType valueType = ((MapType) additionalProperties.getWireType()).getValueType();
                if (valueType == ClassType.Object) {
                    // String fieldName should be a local variable accessible in this spot of code.
                    javaBlock.line(additionalProperties.getName() + ".put(" + fieldNameVariableName + ", JsonUtils.readUntypedField(reader));");
                } else {
                    // Another assumption, the additional properties value type is simple.
                    javaBlock.line(additionalProperties.getName() + ".put(" + fieldNameVariableName + ", "
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

    /**
     * Handles validating that all required properties have been found and creating the return type.
     * <p>
     * Properties are split into two concepts, required and optional properties, and those concepts are split into an
     * additional two groups, properties declared by super types and by the model type.
     *
     * @param methodBlock The method handling deserialization.
     * @param modelName The name of the model.
     * @param propertiesManager The property manager for the model.
     * @param settings The Autorest generation settings.
     */
    private static void handleJsonReadReturn(JavaBlock methodBlock, String modelName,
        PropertiesManager propertiesManager, JavaSettings settings) {

        // If the model has a discriminator property it needs to be null checked and validated that it matches
        // the discriminator of the model.
        if (propertiesManager.discriminatorProperty != null) {
            ClientModelProperty discriminatorProperty = propertiesManager.discriminatorProperty;
            methodBlock.line();
            methodBlock.ifBlock(
                "!" + discriminatorProperty.getName() + "Found || !Objects.equals(" + discriminatorProperty.getName() + ", \"" + propertiesManager.expectedDiscriminator + "\")",
                ifAction -> ifAction.line("throw new IllegalStateException(\"'" + discriminatorProperty.getSerializedName()
                    + "' was expected to be non-null and equal to '" + propertiesManager.expectedDiscriminator + "'. "
                    + "The found " + "'" + discriminatorProperty.getSerializedName() + "' was '\" + " + discriminatorProperty.getName() + " + \"'.\");"));
            methodBlock.line();
        }

        StringBuilder constructorArgs = new StringBuilder();
        propertiesManager.superRequiredProperties.stream().map(ClientModelProperty::getName)
            .forEach(arg -> addConstructorParameter(constructorArgs, arg));
        propertiesManager.requiredProperties.stream().map(ClientModelProperty::getName)
            .forEach(arg -> addConstructorParameter(constructorArgs, arg));

        // If there are required properties of any type we must check that all required fields were found.
        if (!CoreUtils.isNullOrEmpty(propertiesManager.superRequiredProperties)
            || !CoreUtils.isNullOrEmpty(propertiesManager.requiredProperties)) {
            methodBlock.line("List<String> missingProperties = new ArrayList<>();");
            propertiesManager.superRequiredProperties.forEach(property -> addFoundValidationIfCheck(methodBlock, property));
            propertiesManager.requiredProperties.forEach(property -> addFoundValidationIfCheck(methodBlock, property));

            methodBlock.line();
            methodBlock.ifBlock("!CoreUtils.isNullOrEmpty(missingProperties)", ifAction ->
                ifAction.line("throw new IllegalStateException(\"Missing required property/properties: \""
                    + "+ String.join(\", \", missingProperties));"));
        }

        methodBlock.line(modelName + " deserializedValue = new " + modelName + "(" + constructorArgs + ");");

        propertiesManager.superSetterProperties.forEach(property -> handleSettingDeserializedValue(methodBlock, property, settings));
        propertiesManager.setterProperties.forEach(property -> handleSettingDeserializedValue(methodBlock, property, settings));

        methodBlock.line();
        methodBlock.methodReturn("deserializedValue");
    }

    private static void addConstructorParameter(StringBuilder constructor, String parameterName) {
        if (constructor.length() > 0) {
            constructor.append(", ");
        }

        constructor.append(parameterName);
    }

    private static void addFoundValidationIfCheck(JavaBlock methodBlock, ClientModelProperty property) {
        methodBlock.ifBlock("!" + property.getName() + "Found",
            ifAction -> ifAction.line("missingProperties.add(\"" + property.getSerializedName() + "\");"));
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

    private static boolean isSuperTypeWithDiscriminator(ClientModel model) {
        return !CoreUtils.isNullOrEmpty(model.getPolymorphicDiscriminator())
            && !CoreUtils.isNullOrEmpty(model.getDerivedModels());
    }

    /**
     * Helper method for adding a base if condition or an else if condition.
     *
     * @param baseBlock Base code block where an if condition would be added.
     * @param ifBlock If block where an else if condition would be added.
     * @param condition The conditional statement.
     * @param action The conditional action.
     * @return An if block for further chaining.
     */
    private static JavaIfBlock ifOrElseIf(JavaBlock baseBlock, JavaIfBlock ifBlock, String condition,
        Consumer<JavaBlock> action) {
        return (ifBlock == null) ? baseBlock.ifBlock(condition, action) : ifBlock.elseIfBlock(condition, action);
    }

    /**
     * Helper class to manage properties in the model and how they correlate with serialization and deserialization.
     * <p>
     * This will bucket all properties, and super type properties, into the buckets of required, optional, and
     * additional properties. In addition to bucketing, each property will be checked if it requires flattening and be
     * used to generate the flattened properties structure for the model.
     * <p>
     * This will also handle getting the discriminator property and the expected value for the field.
     */
    private static final class PropertiesManager {
        private final String modelName;
        private final List<ClientModelProperty> superRequiredProperties;
        private final List<ClientModelProperty> superSetterProperties;
        private final List<ClientModelProperty> requiredProperties;
        private final List<ClientModelProperty> setterProperties;
        private final ClientModelProperty additionalProperties;
        private final ClientModelProperty discriminatorProperty;
        private final String expectedDiscriminator;
        private final FlattenedPropertiesStructure flattenedPropertiesStructure;
        private final String readerFieldNameVariableName;

        PropertiesManager(ClientModel model) {
            // The reader JSON field name variable needs to be mutable as it may match a JSON property name in the class.
            Set<String> possibleReaderFieldNameVariableNames = new LinkedHashSet<>(Arrays.asList(
                "fieldName", "jsonFieldName", "deserializationFieldName"));
            this.modelName = model.getName();
            this.expectedDiscriminator = model.getSerializedName();

            Map<String, ClientModelPropertyWithMetadata> flattenedProperties = new LinkedHashMap<>();
            List<ClientModelProperty> superRequiredProperties = new ArrayList<>();
            List<ClientModelProperty> superSetterProperties = new ArrayList<>();
            for (ClientModelProperty property : ClientModelUtil.getParentProperties(model)) {
                // Ignore constants, additional, and discriminator properties.
                if (property.getIsConstant()
                    || property.isAdditionalProperties()
                    || property.isPolymorphicDiscriminator()) {
                    continue;
                } else if (property.isRequired()) {
                    superRequiredProperties.add(property);
                } else {
                    superSetterProperties.add(property);
                }

                if (property.getNeedsFlatten()) {
                    flattenedProperties.put(property.getName(), new ClientModelPropertyWithMetadata(property, true));
                }

                possibleReaderFieldNameVariableNames.remove(property.getName());
            }

            List<ClientModelProperty> requiredProperties = new ArrayList<>();
            List<ClientModelProperty> setterProperties = new ArrayList<>();
            ClientModelProperty discriminatorProperty = null;
            ClientModelProperty additionalProperties = null;
            for (ClientModelProperty property : model.getProperties()) {
                // Ignore constant properties.
                if (property.getIsConstant()) {
                    continue;
                } else if (property.isRequired()) {
                    requiredProperties.add(property);
                } else if (property.isAdditionalProperties()) {
                    // Extract the additionalProperties property as this will need to be passed into all deserialization
                    // logic creation calls.
                    additionalProperties = property;
                } else if (property.isPolymorphicDiscriminator()) {
                    discriminatorProperty = property;
                } else {
                    setterProperties.add(property);
                }

                if (property.getNeedsFlatten()) {
                    flattenedProperties.put(property.getName(), new ClientModelPropertyWithMetadata(property, false));
                }

                possibleReaderFieldNameVariableNames.remove(property.getName());
            }

            this.discriminatorProperty = discriminatorProperty;
            this.superRequiredProperties = superRequiredProperties;
            this.superSetterProperties = superSetterProperties;
            this.requiredProperties = requiredProperties;
            this.setterProperties = setterProperties;
            this.additionalProperties = additionalProperties;
            this.flattenedPropertiesStructure = getFlattenedPropertiesHierarchy(model.getPolymorphicDiscriminator(),
                flattenedProperties);
            this.readerFieldNameVariableName = possibleReaderFieldNameVariableNames.iterator().next();
        }
    }

    private static final class FlattenedPropertiesStructure {
        // A property being set indicates that this is a terminal value.
        private final ClientModelPropertyWithMetadata property;
        private final String nodeName;
        private final Map<String, FlattenedPropertiesStructure> subNodes;

        static FlattenedPropertiesStructure createBaseNode() {
            return new FlattenedPropertiesStructure(null, null);
        }

        static FlattenedPropertiesStructure createIntermediateNode(String nodeName) {
            return new FlattenedPropertiesStructure(null, nodeName);
        }

        static FlattenedPropertiesStructure createTerminalNode(String nodeName,
            ClientModelPropertyWithMetadata property) {
            return new FlattenedPropertiesStructure(property, nodeName);
        }

        void addSubNode(FlattenedPropertiesStructure subNode) {
            if (property != null) {
                throw new IllegalStateException("JSON flatten structure contains a terminal node and intermediate "
                    + "node with the same JSON property. This isn't valid as it would require the JSON property "
                    + "to be both a sub-object and a value node.");
            }

            subNodes.put(subNode.nodeName, subNode);
        }

        boolean hasSubNode(String propertyName) {
            return subNodes.containsKey(propertyName);
        }

        FlattenedPropertiesStructure getSubNode(String propertyName) {
            return subNodes.get(propertyName);
        }

        List<ClientModelPropertyWithMetadata> getClientModelPropertiesInTree() {
            if (property != null) {
                // Terminal node only contains a property.
                return Collections.singletonList(property);
            } else {
                List<ClientModelPropertyWithMetadata> modelProperties = new ArrayList<>();
                for (FlattenedPropertiesStructure flattenedProperties : subNodes.values()) {
                    modelProperties.addAll(flattenedProperties.getClientModelPropertiesInTree());
                }

                return modelProperties;
            }
        }

        private FlattenedPropertiesStructure(ClientModelPropertyWithMetadata property, String nodeName) {
            this.property = property;
            this.nodeName = nodeName;
            this.subNodes = new LinkedHashMap<>();
        }
    }

    private static final class ClientModelPropertyWithMetadata {
        private final ClientModelProperty property;
        private final boolean fromSuperClass;

        ClientModelPropertyWithMetadata(ClientModelProperty property, boolean fromSuperClass) {
            this.property = property;
            this.fromSuperClass = fromSuperClass;
        }
    }
}
