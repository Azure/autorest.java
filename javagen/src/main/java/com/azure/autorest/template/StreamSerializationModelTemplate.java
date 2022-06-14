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
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Pattern;

/**
 * Writes a ClientModel to a JavaFile using stream-style serialization.
 */
// TODO (alzimmer): Determine if this can be merged back into ModelTemplate with if checks
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
        boolean hasPolymorphicDiscriminator = !CoreUtils.isNullOrEmpty(model.getPolymorphicDiscriminator());
        boolean isSuperType = !CoreUtils.isNullOrEmpty(model.getDerivedModels());

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
        if (isSuperType && hasPolymorphicDiscriminator) {
            writeSuperTypeFromJson(classBlock, model, settings);
        } else {
            writeTerminalTypeFromJson(classBlock, model, settings);
        }
    }

    /**
     * Writes a super type's {@code fromJson(JsonReader)} method.
     *
     * @param classBlock The class having {@code fromJson(JsonReader)} written to it.
     * @param model The Autorest representation of the model.
     * @param settings The Autorest generation settings.
     */
    private static void writeSuperTypeFromJson(JavaClass classBlock, ClientModel model, JavaSettings settings) {
        readObject(classBlock, model.getName(), false, methodBlock -> {
            methodBlock.line("String discriminatorValue = null;");
            methodBlock.line("JsonReader readerToUse = null;");
            methodBlock.line();
            methodBlock.line("// Read the first field name and determine if it's the discriminator field.");
            methodBlock.line("reader.nextToken();");
            methodBlock.ifBlock("\"" + model.getPolymorphicDiscriminator() + "\".equals(reader.getFieldName())",
                ifBlock -> {
                    ifBlock.line("reader.nextToken();");
                    ifBlock.line("discriminatorValue = reader.getStringValue();");
                    ifBlock.line("readerToUse = reader;");
                }).elseBlock(elseBlock -> {
                    elseBlock.line("// If it isn't the discriminator field buffer the JSON structure to make it");
                    elseBlock.line("// replayable and find the discriminator field value.");
                    elseBlock.line("String json = JsonUtils.bufferJsonObject(reader);");
                    elseBlock.line("JsonReader replayReader = DefaultJsonReader.fromString(json);");
                    elseBlock.line("while (replayReader.nextToken() != JsonToken.END_OBJECT) {");
                    elseBlock.indent(() -> {
                        elseBlock.line("String fieldName = replayReader.getFieldName();");
                        elseBlock.line("replayReader.nextToken();");
                        elseBlock.ifBlock("\"" + model.getPolymorphicDiscriminator() + "\".equals(fieldName)", ifBlock -> {
                            ifBlock.line("discriminatorValue = replayReader.getStringValue();");
                            ifBlock.line("break;");
                        }).elseBlock(elseBlock2 -> elseBlock2.line("replayReader.skipChildren();"));
                    });
                    elseBlock.line("}");
                    elseBlock.ifBlock("discriminatorValue != null", ifBlock ->
                        ifBlock.line("readerToUse = DefaultJsonReader.fromString(json);"));
                });

            methodBlock.line("// Use the discriminator value to determine which subtype should be deserialized.");

            // Add a throw statement if the discriminator value didn't match anything known.
            StringBuilder exceptionMessage = new StringBuilder("Discriminator field '")
                .append(model.getPolymorphicDiscriminator())
                .append("' was present and didn't match one of the expected values '")
                .append(model.getSerializedName())
                .append("'");

            // Add deserialization for the super type itself.
            JavaIfBlock ifBlock = methodBlock.ifBlock(
                "discriminatorValue == null || \"" + model.getSerializedName() + "\".equals(discriminatorValue)",
                ifStatement -> ifStatement.methodReturn("fromJsonKnownDiscriminator(readerToUse)"));

            // Add deserialization for all child types.
            List<ClientModel> childTypes = getAllChildTypes(model, new ArrayList<>());
            for (int i = 0; i < childTypes.size() - 1; i++) {
                ClientModel childType = childTypes.get(i);

                String condition = "\"" + childType.getSerializedName() + "\".equals(discriminatorValue)";
                String returnStatement = childType.getName() + ".fromJson(readerToUse)";
                ifBlock = ifBlock.elseIfBlock(condition, ifStatement -> ifStatement.methodReturn(returnStatement));

                if (i < childTypes.size() - 2) {
                    exceptionMessage.append(", '").append(childType.getSerializedName()).append("'");
                } else {
                    exceptionMessage.append(", or '").append(childType.getSerializedName())
                        .append("'. It was: '\" + discriminatorValue + \"'.");
                }
            }

            ifBlock.elseBlock(elseBlock -> elseBlock.line("throw new IllegalStateException(\"" + exceptionMessage + "\");"));
        });

        readObject(classBlock, model.getName(), true,
            methodBlock -> writeFromJsonDeserialization(model, methodBlock, settings));
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
     * @param model The Autorest representation of the model.
     * @param settings The Autorest generation settings.
     */
    private static void writeTerminalTypeFromJson(JavaClass classBlock, ClientModel model, JavaSettings settings) {
        readObject(classBlock, model.getName(), false,
            methodBlock -> writeFromJsonDeserialization(model, methodBlock, settings));
    }

    private static void writeFromJsonDeserialization(ClientModel model, JavaBlock methodBlock, JavaSettings settings) {
        PropertiesManager properties = new PropertiesManager(model);

        // Add the deserialization logic.
        methodBlock.indent(() -> {
            // Initialize local variables to track what has been deserialized.
            initializeLocalVariables(methodBlock, properties);

            // Add the outermost while loop to read the JSON object.
            addReaderWhileLoop(methodBlock, true, whileBlock -> {
                JavaIfBlock ifBlock = handleDiscriminatorDeserialization(whileBlock, properties.discriminatorProperty);

                // Loop over all properties and generate their deserialization handling.
                ifBlock = handlePropertiesDeserialization(properties.superRequiredProperties, whileBlock, ifBlock);
                ifBlock = handlePropertiesDeserialization(properties.superSetterProperties, whileBlock, ifBlock);
                ifBlock = handlePropertiesDeserialization(properties.requiredProperties, whileBlock, ifBlock);
                ifBlock = handlePropertiesDeserialization(properties.setterProperties, whileBlock, ifBlock);

                handleFlattenedPropertiesDeserialization(properties.flattenedPropertiesStructure, methodBlock,
                    ifBlock, properties.additionalProperties);

                // All properties have been checked for, add an else block that will either ignore unknown properties
                // or add them into an additional properties bag.
                handleUnknownFieldDeserialization(whileBlock, ifBlock, properties.additionalProperties);
            });
        });

        // Add the validation and return logic.
        handleJsonReadReturn(methodBlock, model.getName(), properties, settings);
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
        Map<String, ClientModelProperty> flattenedProperties) {
        FlattenedPropertiesStructure structure = FlattenedPropertiesStructure.createBaseNode();

        if (!CoreUtils.isNullOrEmpty(discriminatorProperty)) {
            String[] propertyHierarchy = SPLIT_KEY_PATTERN.split(discriminatorProperty);
            if (propertyHierarchy.length > 1) {
                structure = FlattenedPropertiesStructure.createBaseNode();

            }
        }

        for (ClientModelProperty property : flattenedProperties.values()) {
            if (!property.getNeedsFlatten()) {
                // Property doesn't need flattening, ignore it.
                continue;
            }

            // Splits the flattened property into the individual properties in the JSON path.
            // For example "im.deeper.flattened" becomes ["im", "deeper", "flattened"].
            String[] propertyHierarchy = SPLIT_KEY_PATTERN.split(property.getSerializedName());

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
     * type being deserialized. The specific type being deserialized may be the super type itself, so it cannot pass
     * to {@code fromJson} as this will be a circular call and if the specific type being deserialized is an
     * intermediate type (a type having both super and subclasses) it will attempt to perform discriminator validation
     * which has already been done.
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
        if (!CoreUtils.isNullOrEmpty(propertiesManager.discriminatorProperty)) {
            // Discriminator properties are always required.
            methodBlock.line("boolean discriminatorPropertyFound = false;");
            methodBlock.line("String discriminatorProperty = null;");
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
        methodBlock.line(property.getClientType() + " " + property.getName() + " = "
            + property.getClientType().defaultValueExpression() + ";");
    }

    /**
     * Adds the while loop that handles reading the JSON object until it is fully consumed.
     *
     * @param methodBlock The method handling deserialization.
     * @param initializeFieldNameVariable Whether the {@code fieldName} variable needs to be initialized. If this is a
     * nested while loop the variable doesn't need to be initialized.
     * @param whileBlock The consumer that adds deserialization logic into the while loop.
     */
    private static void addReaderWhileLoop(JavaBlock methodBlock, boolean initializeFieldNameVariable,
        Consumer<JavaBlock> whileBlock) {
        methodBlock.line("while (reader.nextToken() != JsonToken.END_OBJECT) {");
        methodBlock.increaseIndent();
        if (initializeFieldNameVariable) {
            methodBlock.line("String fieldName = reader.getFieldName();");
        } else {
            methodBlock.line("fieldName = reader.getFieldName();");
        }
        methodBlock.line("reader.nextToken();");
        methodBlock.line("");

        whileBlock.accept(methodBlock);

        methodBlock.decreaseIndent();
        methodBlock.line("}");
    }

    private static JavaIfBlock handleDiscriminatorDeserialization(JavaBlock methodBlock,
        String discriminatorProperty) {
        if (!CoreUtils.isNullOrEmpty(discriminatorProperty)) {
            return methodBlock.ifBlock("fieldName.equals(\"" + discriminatorProperty + "\")",
                ifAction -> {
                    ifAction.line("discriminatorPropertyFound = true;");
                    ifAction.line("discriminatorProperty = reader.getStringValue();");
                });
        }

        return null;
    }

    private static JavaIfBlock handlePropertiesDeserialization(List<ClientModelProperty> properties,
        JavaBlock methodBlock, JavaIfBlock ifBlock) {
        for (ClientModelProperty property : properties) {
            // Property will be handled later by flattened deserialization.
            if (property.getNeedsFlatten()) {
                continue;
            }

            ifBlock = handlePropertyDeserialization(property, methodBlock, ifBlock);
        }

        return ifBlock;
    }

    private static JavaIfBlock handlePropertyDeserialization(ClientModelProperty property, JavaBlock methodBlock,
        JavaIfBlock ifBlock) {
        String jsonPropertyName = property.getSerializedName();
        if (CoreUtils.isNullOrEmpty(jsonPropertyName)) {
            return ifBlock;
        }

        String condition = "\"" + jsonPropertyName + "\".equals(fieldName)";

        Consumer<JavaBlock> deserializationHandling = deserializationBlock ->
            generateDeserializationLogic(deserializationBlock, property);
        return (ifBlock == null)
            ? methodBlock.ifBlock(condition, deserializationHandling)
            : ifBlock.elseIfBlock(condition, deserializationHandling);
    }

    private static void handleFlattenedPropertiesDeserialization(
        FlattenedPropertiesStructure flattenedProperties, JavaBlock methodBlock, JavaIfBlock ifBlock,
        ClientModelProperty additionalProperties) {
        // The initial call to handle flattened properties is using the base node which is just a holder.
        for (FlattenedPropertiesStructure structure : flattenedProperties.subNodes.values()) {
            handleFlattenedPropertiesDeserializationHelper(structure, methodBlock, ifBlock, additionalProperties);
        }
    }

    private static JavaIfBlock handleFlattenedPropertiesDeserializationHelper(
        FlattenedPropertiesStructure flattenedProperties, JavaBlock methodBlock, JavaIfBlock ifBlock,
        ClientModelProperty additionalProperties) {
        if (flattenedProperties.property != null) {
            // This is a terminal location, so only need to handle checking for the property name.
            String condition = "\"" + flattenedProperties.nodeName + "\".equals(fieldName)";
            Consumer<JavaBlock> deserializationHandling = deserializationBlock ->
                generateDeserializationLogic(deserializationBlock, flattenedProperties.property);

            return (ifBlock == null)
                ? methodBlock.ifBlock(condition, deserializationHandling)
                : ifBlock.elseIfBlock(condition, deserializationHandling);

        } else {
            // Otherwise this is an intermediate location and a while loop reader needs to be added.
            String condition = "\"" + flattenedProperties.nodeName + "\".equals(fieldName) && reader.currentToken() == JsonToken.START_OBJECT";
            Consumer<JavaBlock> ifActionConsumer = ifAction -> addReaderWhileLoop(ifAction, false, whileBlock -> {
                JavaIfBlock innerIfBlock = null;
                for (FlattenedPropertiesStructure structure : flattenedProperties.subNodes.values()) {
                    innerIfBlock = handleFlattenedPropertiesDeserializationHelper(structure, methodBlock, innerIfBlock,
                        additionalProperties);
                }

                handleUnknownFieldDeserialization(whileBlock, innerIfBlock, additionalProperties);
            });

            return (ifBlock == null)
                ? methodBlock.ifBlock(condition, ifActionConsumer)
                : ifBlock.elseIfBlock(condition, ifActionConsumer);
        }
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

    private static void generateDeserializationLogic(JavaBlock deserializationBlock, ClientModelProperty property) {
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
        ClientModelProperty additionalProperties) {
        Consumer<JavaBlock> unknownFieldConsumer = javaBlock -> {
            if (additionalProperties != null) {
                javaBlock.ifBlock(additionalProperties.getName() + " == null",
                    ifAction -> ifAction.line(additionalProperties.getName() + " = new LinkedHashMap<>();"));
                javaBlock.line();

                // Assumption, additional properties is a Map of String-Object
                IType valueType = ((MapType) additionalProperties.getWireType()).getValueType();
                if (valueType == ClassType.Object) {
                    // String fieldName should be a local variable accessible in this spot of code.
                    javaBlock.line(additionalProperties.getName() + ".put(fieldName, JsonUtils.readUntypedField(reader));");
                } else {
                    // Another assumption, the additional properties value type is simple.
                    javaBlock.line(additionalProperties.getName() + ".put(fieldName, "
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
        if (!CoreUtils.isNullOrEmpty(propertiesManager.discriminatorProperty)) {
            methodBlock.line();
            methodBlock.ifBlock(
                "!discriminatorPropertyFound || !Objects.equals(discriminatorProperty, \"" + propertiesManager.expectedDiscriminator + "\")",
                ifAction -> ifAction.line("throw new IllegalStateException(\"'" + propertiesManager.discriminatorProperty
                    + "' was expected to be non-null and equal to '" + propertiesManager.expectedDiscriminator + "'. "
                    + "The found " + "'" + propertiesManager.discriminatorProperty + "' was '\" + discriminatorProperty + \"'.\");"));
            methodBlock.line();
        }

        StringBuilder constructorArgs = new StringBuilder();
        propertiesManager.superRequiredProperties.stream().map(ClientModelProperty::getName)
            .forEach(arg -> addConstructorParameter(constructorArgs, arg));
        propertiesManager.requiredProperties.stream().map(ClientModelProperty::getName)
            .forEach(arg -> addConstructorParameter(constructorArgs, arg));

        // If there are required properties of any type we must check that all required fields were found.
        if (!CoreUtils.isNullOrEmpty(propertiesManager.superRequiredProperties) || !CoreUtils.isNullOrEmpty(propertiesManager.requiredProperties)) {
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
        private final List<ClientModelProperty> superRequiredProperties;
        private final List<ClientModelProperty> superSetterProperties;
        private final List<ClientModelProperty> requiredProperties;
        private final List<ClientModelProperty> setterProperties;
        private final ClientModelProperty additionalProperties;
        private final String discriminatorProperty;
        private final String expectedDiscriminator;
        private final FlattenedPropertiesStructure flattenedPropertiesStructure;


        PropertiesManager(ClientModel model) {
            this.discriminatorProperty = model.getPolymorphicDiscriminator();
            this.expectedDiscriminator = model.getSerializedName();

            Map<String, ClientModelProperty> flattenedProperties = new LinkedHashMap<>();
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

                if (property.getNeedsFlatten()) {
                    flattenedProperties.put(property.getName(), property);
                }
            }

            List<ClientModelProperty> requiredProperties = new ArrayList<>();
            List<ClientModelProperty> setterProperties = new ArrayList<>();
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
                } else {
                    setterProperties.add(property);
                }

                if (property.getNeedsFlatten()) {
                    flattenedProperties.put(property.getName(), property);
                }
            }

            this.superRequiredProperties = superRequiredProperties;
            this.superSetterProperties = superSetterProperties;
            this.requiredProperties = requiredProperties;
            this.setterProperties = setterProperties;
            this.additionalProperties = additionalProperties;
            this.flattenedPropertiesStructure = getFlattenedPropertiesHierarchy(discriminatorProperty,
                flattenedProperties);
        }
    }

    private static final class FlattenedPropertiesStructure {
        // A property being set indicates that this is a terminal value.
        private final ClientModelProperty property;
        private final String nodeName;
        private final Map<String, FlattenedPropertiesStructure> subNodes;

        static FlattenedPropertiesStructure createBaseNode() {
            return new FlattenedPropertiesStructure(null, null);
        }

        static FlattenedPropertiesStructure createIntermediateNode(String nodeName) {
            return new FlattenedPropertiesStructure(null, nodeName);
        }

        static FlattenedPropertiesStructure createTerminalNode(String nodeName, ClientModelProperty property) {
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

        private FlattenedPropertiesStructure(ClientModelProperty property, String nodeName) {
            this.property = property;
            this.nodeName = nodeName;
            this.subNodes = new LinkedHashMap<>();
        }
    }
}
