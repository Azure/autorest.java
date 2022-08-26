// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.implementation.ClientModelPropertiesManager;
import com.azure.autorest.implementation.ClientModelPropertyWithMetadata;
import com.azure.autorest.implementation.JsonFlattenedPropertiesTree;
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
import com.azure.core.util.CoreUtils;
import com.azure.json.JsonProviders;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import com.azure.xml.XmlProviders;
import com.azure.xml.XmlReader;
import com.azure.xml.XmlSerializable;
import com.azure.xml.XmlToken;
import com.azure.xml.XmlWriter;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Writes a ClientModel to a JavaFile using stream-style serialization.
 */
public class StreamSerializationModelTemplate extends ModelTemplate {
    private static final StreamSerializationModelTemplate INSTANCE = new StreamSerializationModelTemplate();

    // TODO (alzimmer): Future enhancements:
    //  - Create a utility class in the implementation package containing base serialization for polymorphic types.
    //     This will enable a central location for shared logic, reducing package size and hopefully JIT optimizations.

    protected StreamSerializationModelTemplate() {
    }

    public static StreamSerializationModelTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    protected void addSerializationImports(Set<String> imports, ClientModel model, JavaSettings settings) {
        if (settings.shouldGenerateXmlSerialization() && model.getXmlName() != null) {
            imports.add(XmlProviders.class.getName());
            imports.add(XmlSerializable.class.getName());
            imports.add(XmlWriter.class.getName());
            imports.add(XmlReader.class.getName());
            imports.add(XmlToken.class.getName());
        } else {
            imports.add(JsonProviders.class.getName());
            imports.add(JsonSerializable.class.getName());
            imports.add(JsonWriter.class.getName());
            imports.add(JsonReader.class.getName());
            imports.add(JsonToken.class.getName());
        }

        imports.add(CoreUtils.class.getName());

        imports.add(ArrayList.class.getName());
        imports.add(Base64.class.getName());
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
        if (!settings.isStreamStyleSerialization()) {
            return classSignature;
        }

        String interfaceName = (settings.shouldGenerateXmlSerialization() && model.getXmlName() != null)
            ? XmlSerializable.class.getSimpleName()
            : JsonSerializable.class.getSimpleName();

        return classSignature + " implements " + interfaceName + "<" + model.getName() + ">";
    }

    @Override
    protected void addModelConstructorParameter(ClientModelProperty property,
        StringBuilder constructorSignatureBuilder) {
        constructorSignatureBuilder.append(property.getClientType()).append(" ").append(property.getName());
    }

    @Override
    protected void addXmlWrapperClass(JavaClass classBlock, ClientModelProperty property, String wrapperClassName,
        JavaSettings settings) {
        // While using a wrapping class for XML elements that are wrapped may seem inconvenient it is required.
        // There has been previous attempts to remove this by using JacksonXmlElementWrapper, which based on its
        // documentation should cover this exact scenario, but it doesn't. Jackson unfortunately doesn't always
        // respect the JacksonXmlRootName, or JsonRootName, value when handling types wrapped by an enumeration,
        // such as List<CorsRule> or Iterable<CorsRule>. Instead, it uses the JacksonXmlProperty local name as the
        // root XML node name for each element in the enumeration. There are configurations for ObjectMapper, and
        // XmlMapper, that always forces Jackson to use the root name but those also add the class name as a root
        // XML node name if the class doesn't have a root name annotation which results in an addition XML level
        // resulting in invalid service XML. There is also one last work around to use JacksonXmlElementWrapper
        // and JacksonXmlProperty together as the wrapper will configure the wrapper name and property will configure
        // the element name but this breaks down in cases where the same element name is used in two different
        // wrappers, a case being Storage BlockList which uses two block elements for its committed and uncommitted
        // block lists.
        IType propertyClientType = property.getWireType().getClientType();

        classBlock.privateFinalMemberVariable(propertyClientType.toString(), "items");

        classBlock.privateConstructor(wrapperClassName + "(" + propertyClientType + " items)",
            constructor -> constructor.line("this.items = items;"));

        xmlWrapperClassXmlSerializableImplementation(classBlock, wrapperClassName, propertyClientType,
            property.getXmlName(), property.getXmlListElementName(), "items", property.getXmlNamespace());
    }

    static void xmlWrapperClassXmlSerializableImplementation(JavaClass classBlock, String wrapperClassName,
        IType iterableType, String xmlRootElementName, String xmlListElementName, String xmlElementNameCamelCase,
        String xmlNamespace) {
        IType elementType = ((IterableType) iterableType).getElementType();

        classBlock.annotation("Override");
        classBlock.publicMethod("XmlWriter toXml(XmlWriter xmlWriter)", writerMethod -> {
            String writeStartElement = (xmlNamespace != null)
                ? "xmlWriter.writeStartElement(null, \"" + xmlNamespace + "\", \"" + xmlRootElementName + "\");"
                : "xmlWriter.writeStartElement(\"" + xmlRootElementName + "\");";
            writerMethod.line(writeStartElement);

            writerMethod.ifBlock(xmlElementNameCamelCase + " != null", ifAction -> {
                if (elementType instanceof ClassType && ((ClassType) elementType).isSwaggerType()) {
                    ifAction.line(xmlElementNameCamelCase + ".forEach(xmlWriter::writeXml);");
                } else {
                    String xmlWrite = elementType.xmlSerializationMethodCall("xmlWriter", xmlListElementName,
                        xmlNamespace, "element", false);
                    ifAction.line(xmlElementNameCamelCase + ".forEach(element -> " + xmlWrite + ");");
                }
            });

            writerMethod.methodReturn("xmlWriter.writeEndElement()");
        });

        classBlock.publicStaticMethod(wrapperClassName + " fromXml(XmlReader xmlReader)", readerMethod -> {
            String readObject = (xmlNamespace != null)
                ? "return xmlReader.readObject(\"" + xmlNamespace + "\", \"" + xmlRootElementName + "\", reader -> {"
                : "return xmlReader.readObject(\"" + xmlRootElementName + "\", reader -> {";

            readerMethod.line(readObject);
            readerMethod.indent(() -> {
                readerMethod.line(iterableType + " items = null;");
                readerMethod.line();
                readerMethod.line("while (reader.nextElement() != XmlToken.END_ELEMENT) {");
                readerMethod.indent(() -> {
                    // TODO (alzimmer): Support namespace validation.
                    readerMethod.line("String elementName = reader.getElementName().getLocalPart();");
                    readerMethod.line();
                    readerMethod.ifBlock("\"" + xmlListElementName + "\".equals(elementName)", ifBlock -> {
                        ifBlock.ifBlock("items == null", ifBlock2 -> ifBlock2.line("items = new ArrayList<>();"));
                        ifBlock.line();

                        // TODO (alzimmer): Insert XML object reading logic.
                        ifBlock.line("items.add(" + getSimpleXmlElementDeserialization(elementType, elementType, "reader") + ");");
                    }).elseBlock(elseBlock -> elseBlock.line("reader.nextElement();"));
                });
                readerMethod.line("}");

                readerMethod.methodReturn("new " + wrapperClassName + "(items)");
            });
            readerMethod.line("});");
        });
    }

    @Override
    protected void addFieldAnnotations(ClientModelProperty property, JavaClass classBlock, JavaSettings settings) {
        // no-op as stream-style serialization doesn't add any field-level annotations.
    }

    @Override
    protected void writeStreamStyleSerialization(JavaClass classBlock, ClientModel model, JavaSettings settings) {
        ClientModelPropertiesManager propertiesManager = new ClientModelPropertiesManager(model);

        if (settings.shouldGenerateXmlSerialization() && model.getXmlName() != null) {
            writeToXml(classBlock, propertiesManager);
            writeFromXml(classBlock, model, propertiesManager, settings);
        } else {
            writeToJson(classBlock, propertiesManager);
            writeFromJson(classBlock, model, propertiesManager, settings);
        }
    }

    private static void writeToJson(JavaClass classBlock, ClientModelPropertiesManager propertiesManager) {
        classBlock.annotation("Override");
        classBlock.publicMethod("JsonWriter toJson(JsonWriter jsonWriter)", methodBlock -> {
            methodBlock.line("jsonWriter.writeStartObject();");

            // If the model has a discriminator property serialize it first.
            if (propertiesManager.getDiscriminatorProperty() != null
                && !CoreUtils.isNullOrEmpty(propertiesManager.getDiscriminatorProperty().getDefaultValue())) {
                ClientModelProperty discriminatorProperty = propertiesManager.getDiscriminatorProperty();
                serializeJsonProperty(methodBlock, discriminatorProperty, discriminatorProperty.getSerializedName(), false,
                    true);
            }

            propertiesManager.getSuperRequiredProperties().forEach(property ->
                serializeJsonProperty(methodBlock, property, property.getSerializedName(), true, true));
            propertiesManager.getSuperSetterProperties().forEach(property ->
                serializeJsonProperty(methodBlock, property, property.getSerializedName(), true, true));
            propertiesManager.getRequiredProperties().forEach(property ->
                serializeJsonProperty(methodBlock, property, property.getSerializedName(), false, true));
            propertiesManager.getSetterProperties().forEach(property ->
                serializeJsonProperty(methodBlock, property, property.getSerializedName(), false, true));

            handleFlattenedPropertiesSerialization(methodBlock, propertiesManager.getJsonFlattenedPropertiesTree());

            ClientModelProperty additionalProperties = propertiesManager.getAdditionalProperties();
            if (additionalProperties != null) {
                methodBlock.ifBlock(additionalProperties.getName() + " != null", ifAction ->
                    ifAction.line(additionalProperties.getName() + ".forEach(jsonWriter::writeUntypedField);"));
            }

            methodBlock.methodReturn("jsonWriter.writeEndObject()");
        });
    }

    /**
     * Serializes a non-flattened, non-additional properties JSON property.
     * <p>
     * If the JSON property needs to be flattened or is additional properties this is a no-op as those require special
     * handling that will occur later.
     *
     * @param methodBlock The method handling serialization.
     * @param property The property being serialized.
     * @param serializedName The serialized JSON property name. Generally, this is just the {@code property property's}
     * serialized name but if a flattened property is being serialized it'll be the last segment of the flattened JSON
     * name.
     * @param fromSuperType Whether the property is defined by a super type of the model. If the property is declared by
     * a super type a getter method will be used to retrieve the value instead of accessing the field directly.
     * @param ignoreFlattening Whether flattened properties should be skipped. Will only be false when handling the
     * terminal location of a flattened structure.
     */
    private static void serializeJsonProperty(JavaBlock methodBlock, ClientModelProperty property,
        String serializedName, boolean fromSuperType, boolean ignoreFlattening) {
        if ((ignoreFlattening && property.getNeedsFlatten()) || property.isAdditionalProperties()) {
            // Property will be handled later by flattened or additional properties serialization.
            return;
        }

        IType wireType = property.getWireType();
        String propertyValueGetter = fromSuperType ? property.getGetterName() + "()" : "this." + property.getName();

        // Attempt to determine whether the wire type is simple serialization.
        // This is primitives, boxed primitives, a small set of string based models, and other ClientModels.
        String fieldSerializationMethod = wireType.jsonSerializationMethodCall("jsonWriter", serializedName,
            propertyValueGetter);
        if (fieldSerializationMethod != null) {
            methodBlock.line(fieldSerializationMethod + ";");
        } else if (wireType == ClassType.Object) {
            methodBlock.line("jsonWriter.writeUntyped(" + propertyValueGetter + ");");
        } else if (wireType instanceof IterableType) {
            serializeJsonContainerProperty(methodBlock, "writeArrayField", wireType, ((IterableType) wireType).getElementType(),
                serializedName, propertyValueGetter, 0);
        } else if (wireType instanceof MapType) {
            // Assumption is that the key type for the Map is a String. This may not always hold true and when that
            // becomes reality this will need to be reworked to handle that case.
            serializeJsonContainerProperty(methodBlock, "writeMapField", wireType, ((MapType) wireType).getValueType(),
                serializedName, propertyValueGetter, 0);
        } else {
            // TODO (alzimmer): Resolve this as deserialization logic generation needs to handle all cases.
            throw new RuntimeException("Unknown wire type " + wireType + " in serialization. Need to add support for it.");
        }
    }

    /**
     * Helper method to serialize a JSON container property (such as {@link List} and {@link Map}).
     *
     * @param methodBlock The method handling serialization.
     * @param utilityMethod The method aiding in the serialization of the container.
     * @param containerType The container type.
     * @param elementType The element type for the container, for a {@link List} this is the element type and for a
     * {@link Map} this is the value type.
     * @param serializedName The serialized property name.
     * @param propertyValueGetter The property or property getter for the field being serialized.
     * @param depth Depth of recursion for container types, such as {@code Map<String, List<String>>} would be 0 when
     * {@code Map} is being handled and then 1 when {@code List} is being handled.
     */
    private static void serializeJsonContainerProperty(JavaBlock methodBlock, String utilityMethod,
        IType containerType, IType elementType, String serializedName, String propertyValueGetter, int depth) {
        String callingWriterName = depth == 0 ? "jsonWriter" : (depth == 1) ? "writer" : "writer" + (depth - 1);
        String lambdaWriterName = depth == 0 ? "writer" : "writer" + depth;
        String elementName = depth == 0 ? "element" : "element" + depth;
        String valueSerializationMethod = elementType.jsonSerializationMethodCall(lambdaWriterName, null, elementName);
        String serializeValue = depth == 0 ? propertyValueGetter
            : ((depth == 1) ? "element" : "element" + (depth - 1));

        // First call into serialize container property will need to write the property name. Subsequent calls must
        // not write the property name as that would be invalid, ex "myList":["myList":["innerListElement"]].
        if (depth == 0) {
            // Container property shouldn't be written if it's null.
            methodBlock.line("%s.%s(\"%s\", %s, (%s, %s) -> ", callingWriterName, utilityMethod, serializedName,
                serializeValue, lambdaWriterName, elementName);
        } else {
            // But the inner container should be written if it's null.
            methodBlock.line("%s.%s(%s, (%s, %s) -> ", callingWriterName, utilityMethod, serializeValue,
                lambdaWriterName, elementName);
        }

        methodBlock.indent(() -> {
            if (valueSerializationMethod != null) {
                methodBlock.line(valueSerializationMethod);
            } else if (elementType == ClassType.Object) {
                methodBlock.line(lambdaWriterName + ".writeUntyped(" + elementName + ")");
            } else if (elementType instanceof IterableType) {
                serializeJsonContainerProperty(methodBlock, "writeArray", elementType, ((IterableType) elementType).getElementType(),
                    serializedName, propertyValueGetter, depth + 1);
            } else if (elementType instanceof MapType) {
                // Assumption is that the key type for the Map is a String. This may not always hold true and when that
                // becomes reality this will need to be reworked to handle that case.
                serializeJsonContainerProperty(methodBlock, "writeMap", elementType, ((MapType) elementType).getValueType(),
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
        JsonFlattenedPropertiesTree flattenedProperties) {
        // The initial call to handle flattened properties is using the base node which is just a holder.
        for (JsonFlattenedPropertiesTree flattened : flattenedProperties.getChildrenNodes().values()) {
            handleFlattenedPropertiesSerializationHelper(methodBlock, flattened);
        }
    }

    private static void handleFlattenedPropertiesSerializationHelper(JavaBlock methodBlock,
        JsonFlattenedPropertiesTree flattenedProperties) {
        ClientModelPropertyWithMetadata flattenedProperty = flattenedProperties.getProperty();
        if (flattenedProperty != null) {
            // This is a terminal location, only need to add property serialization.
            serializeJsonProperty(methodBlock, flattenedProperty.getProperty(), flattenedProperties.getNodeName(),
                flattenedProperty.isFromSuperClass(), false);
        } else {
            // Otherwise this is an intermediate location.
            // Check for either any of the properties in this subtree being primitives or add an if block checking that
            // any of the properties are non-null.
            List<ClientModelPropertyWithMetadata> propertiesInFlattenedGroup =
                getClientModelPropertiesInJsonTree(flattenedProperties);
            boolean hasPrimitivePropertyInGroup = propertiesInFlattenedGroup.stream()
                .map(property -> property.getProperty().getWireType())
                .anyMatch(wireType -> wireType instanceof PrimitiveType);

            if (hasPrimitivePropertyInGroup) {
                // Simple case where the flattened group has a primitive type where non-null checking doesn't need
                // to be done.
                methodBlock.line("jsonWriter.writeStartObject(\"" + flattenedProperties.getNodeName() + "\");");
                for (JsonFlattenedPropertiesTree flattened : flattenedProperties.getChildrenNodes().values()) {
                    handleFlattenedPropertiesSerializationHelper(methodBlock, flattened);
                }
                methodBlock.line("jsonWriter.writeEndObject();");
            } else {
                // Complex case where all properties in the flattened group are nullable and a check needs to be made
                // if any value is non-null.
                String condition = propertiesInFlattenedGroup.stream()
                    .map(property -> (property.isFromSuperClass())
                        ? property.getProperty().getGetterName() + "() != null"
                        : property.getProperty().getName() + " != null")
                    .collect(Collectors.joining(" || "));

                methodBlock.ifBlock(condition, ifAction -> {
                    ifAction.line("jsonWriter.writeStartObject(\"" + flattenedProperties.getNodeName() + "\");");
                    for (JsonFlattenedPropertiesTree flattened : flattenedProperties.getChildrenNodes().values()) {
                        handleFlattenedPropertiesSerializationHelper(ifAction, flattened);
                    }
                    ifAction.line("jsonWriter.writeEndObject();");
                });
            }
        }
    }

    /*
     * Writes the fromJson(JsonReader) implementation.
     */
    private static void writeFromJson(JavaClass classBlock, ClientModel model,
        ClientModelPropertiesManager propertiesManager, JavaSettings settings) {
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
        ClientModelPropertiesManager propertiesManager, JavaSettings settings) {
        // Handling polymorphic fields while determining which subclass, or the class itself, to deserialize handles the
        // discriminator type always as a String. This is permissible as the found discriminator is never being used in
        // a setter or for setting a field, unlike in the actual deserialization method where it needs to be the same
        // type as the field.
        String fieldNameVariableName = propertiesManager.getJsonReaderFieldNameVariableName();
        ClientModelProperty discriminatorProperty = propertiesManager.getDiscriminatorProperty();
        readObject(classBlock, propertiesManager, false, methodBlock -> {
            methodBlock.line(String.join("\n",
                "String discriminatorValue = null;",
                "JsonReader readerToUse = null;",
                "",
                "// Read the first field name and determine if it's the discriminator field.",
                "reader.nextToken();",
                "if (\"" + discriminatorProperty.getSerializedName() + "\".equals(reader.getFieldName())) {",
                "    reader.nextToken();",
                "    discriminatorValue = reader.getString();",
                "    readerToUse = reader;",
                "} else {",
                "    // If it isn't the discriminator field buffer the JSON to make it replayable and find the discriminator field value.",
                "    JsonReader replayReader = reader.bufferObject();",
                "    replayReader.nextToken(); // Prepare for reading",
                "    while (replayReader.nextToken() != JsonToken.END_OBJECT) {",
                "        String " + fieldNameVariableName + " = replayReader.getFieldName();",
                "        replayReader.nextToken();",
                "        if (\"" + discriminatorProperty.getSerializedName() + "\".equals(" + fieldNameVariableName + ")) {",
                "            discriminatorValue = replayReader.getString();",
                "            break;",
                "        } else {",
                "            replayReader.skipChildren();",
                "        }",
                "    }",
                "",
                "    if (discriminatorValue != null) {",
                "        readerToUse = replayReader.reset();",
                "    }",
                "}"
            ));

            methodBlock.line("// Use the discriminator value to determine which subtype should be deserialized.");

            // Add a throw statement if the discriminator value didn't match anything known.
            StringBuilder exceptionMessage = new StringBuilder("Discriminator field '")
                .append(discriminatorProperty.getSerializedName())
                .append("' didn't match one of the expected values ");

            if (!CoreUtils.isNullOrEmpty(discriminatorProperty.getDefaultValue())) {
                exceptionMessage.append("'").append(propertiesManager.getExpectedDiscriminator()).append("'");
            }

            // Add deserialization for the super type itself.
            JavaIfBlock ifBlock = (CoreUtils.isNullOrEmpty(discriminatorProperty.getDefaultValue())) ? null
                : methodBlock.ifBlock("discriminatorValue == null || \"" + propertiesManager.getExpectedDiscriminator() + "\".equals(discriminatorValue)",
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
            readObject(classBlock, propertiesManager, true,
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
    private static void writeTerminalTypeFromJson(JavaClass classBlock, ClientModelPropertiesManager propertiesManager,
        JavaSettings settings) {
        readObject(classBlock, propertiesManager, false,
            methodBlock -> writeFromJsonDeserialization(methodBlock, propertiesManager, settings));
    }

    private static void writeFromJsonDeserialization(JavaBlock methodBlock,
        ClientModelPropertiesManager propertiesManager, JavaSettings settings) {
        // Add the deserialization logic.
        methodBlock.indent(() -> {
            // Initialize local variables to track what has been deserialized.
            initializeLocalVariables(methodBlock, propertiesManager);

            // Add the outermost while loop to read the JSON object.
            String fieldNameVariableName = propertiesManager.getJsonReaderFieldNameVariableName();
            addReaderWhileLoop(methodBlock, true, fieldNameVariableName, whileBlock -> {
                JavaIfBlock ifBlock = null;

                if (propertiesManager.getDiscriminatorProperty() != null) {
                    ClientModelProperty discriminatorProperty = propertiesManager.getDiscriminatorProperty();
                    String ifStatement = String.format("\"%s\".equals(%s)", discriminatorProperty.getSerializedName(),
                        fieldNameVariableName);

                    ifBlock = methodBlock.ifBlock(ifStatement, ifAction -> {
                        ifAction.line("String %s = reader.getString();", discriminatorProperty.getName());
                        String ifStatement2 = String.format("!%s.equals(%s)", discriminatorProperty.getDefaultValue(),
                            discriminatorProperty.getName());
                        ifAction.ifBlock(ifStatement2, ifAction2 -> ifAction2.line(
                            "throw new IllegalStateException(\"'%s' was expected to be non-null and equal to '%s'. "
                                + "The found '%s' was '\" + %s + \"'.\");",
                            discriminatorProperty.getSerializedName(), propertiesManager.getExpectedDiscriminator(),
                            discriminatorProperty.getSerializedName(), discriminatorProperty.getName()));
                    });
                }

                // Loop over all properties and generate their deserialization handling.
                ifBlock = handlePropertiesDeserialization(propertiesManager.getSuperRequiredProperties(), whileBlock,
                    ifBlock, fieldNameVariableName);
                ifBlock = handlePropertiesDeserialization(propertiesManager.getSuperSetterProperties(), whileBlock,
                    ifBlock, fieldNameVariableName);
                ifBlock = handlePropertiesDeserialization(propertiesManager.getRequiredProperties(), whileBlock,
                    ifBlock, fieldNameVariableName);
                ifBlock = handlePropertiesDeserialization(propertiesManager.getSetterProperties(), whileBlock,
                    ifBlock, fieldNameVariableName);

                handleFlattenedPropertiesDeserialization(propertiesManager.getJsonFlattenedPropertiesTree(),
                    methodBlock, ifBlock, propertiesManager.getAdditionalProperties(),
                    propertiesManager.getJsonReaderFieldNameVariableName());

                // All properties have been checked for, add an else block that will either ignore unknown properties
                // or add them into an additional properties bag.
                handleUnknownFieldDeserialization(whileBlock, ifBlock, propertiesManager.getAdditionalProperties(),
                    propertiesManager.getJsonReaderFieldNameVariableName());
            });
        });

        // Add the validation and return logic.
        handleJsonReadReturn(methodBlock, propertiesManager.getModel().getName(), propertiesManager, settings);
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
     * @param propertiesManager Properties information about the object being deserialized.
     * @param superTypeReading Whether the object reading is for a super type.
     * @param deserializationBlock Logic for deserializing the object.
     */
    private static void readObject(JavaClass classBlock, ClientModelPropertiesManager propertiesManager,
        boolean superTypeReading, Consumer<JavaBlock> deserializationBlock) {
        JavaVisibility visibility = superTypeReading ? JavaVisibility.PackagePrivate : JavaVisibility.Public;
        String methodName = superTypeReading ? "fromJsonKnownDiscriminator" : "fromJson";

        String modelName = propertiesManager.getModel().getName();
        boolean hasRequiredProperties = !CoreUtils.isNullOrEmpty(propertiesManager.getSuperRequiredProperties())
            || !CoreUtils.isNullOrEmpty(propertiesManager.getRequiredProperties());
        boolean isPolymorphic = propertiesManager.getDiscriminatorProperty() != null;

        if (!superTypeReading) {
            classBlock.javadocComment(javadocComment -> {
                javadocComment.description("Reads an instance of " + modelName + " from the JsonReader.");
                javadocComment.param("jsonReader", "The JsonReader being read.");
                javadocComment.methodReturns("An instance of " + modelName + " if the JsonReader was pointing to an "
                    + "instance of it, or null if it was pointing to JSON null.");

                // TODO (alzimmer): Make the throws statement more descriptive by including the polymorphic
                //  discriminator property name and the required property names. For now this covers the base functionality.
                String throwsStatement = null;
                if (hasRequiredProperties && isPolymorphic) {
                    throwsStatement = "If the deserialized JSON object was missing any required properties or the "
                        + "polymorphic discriminator.";
                } else if (hasRequiredProperties) {
                    throwsStatement = "If the deserialized JSON object was missing any required properties.";
                } else if (isPolymorphic) {
                    throwsStatement = "If the deserialized JSON object was missing the polymorphic discriminator.";
                }

                if (throwsStatement != null) {
                    javadocComment.methodThrows("IllegalStateException", throwsStatement);
                }
            });
        }

        classBlock.staticMethod(visibility, modelName + " " + methodName + "(JsonReader jsonReader)", methodBlock -> {
            // For now, use the basic readObject which will return null if the JsonReader is pointing to JsonToken.NULL.
            //
            // Support for a default value if null will need to be supported and for objects that get their value
            // from a JSON value instead of JSON object or are an array type.
            methodBlock.line("return jsonReader.readObject(reader -> {");

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
    private static void initializeLocalVariables(JavaBlock methodBlock,
        ClientModelPropertiesManager propertiesManager) {
        propertiesManager.getSuperRequiredProperties().forEach(property -> initializeLocalVariable(methodBlock, property));
        propertiesManager.getSuperSetterProperties().forEach(property -> initializeLocalVariable(methodBlock, property));
        propertiesManager.getRequiredProperties().forEach(property -> initializeLocalVariable(methodBlock, property));
        propertiesManager.getSetterProperties().forEach(property -> initializeLocalVariable(methodBlock, property));

        if (propertiesManager.getAdditionalProperties() != null) {
            initializeLocalVariable(methodBlock, propertiesManager.getAdditionalProperties());
        }
    }

    private static void initializeLocalVariable(JavaBlock methodBlock, ClientModelProperty property) {
        if (property.isRequired()) {
            // Required properties need an additional boolean variable to indicate they've been found.
            methodBlock.line("boolean " + property.getName() + "Found = false;");
        }

        // Always instantiate the local variable.
        IType clientType = property.getClientType();
        methodBlock.line(clientType + " " + property.getName() + " = " + clientType.defaultValueExpression() + ";");
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
            deserializationBlock -> generateDeserializationLogic(deserializationBlock, property));
    }

    private static void handleFlattenedPropertiesDeserialization(
        JsonFlattenedPropertiesTree flattenedProperties, JavaBlock methodBlock, JavaIfBlock ifBlock,
        ClientModelProperty additionalProperties, String fieldNameVariableName) {
        // The initial call to handle flattened properties is using the base node which is just a holder.
        for (JsonFlattenedPropertiesTree structure : flattenedProperties.getChildrenNodes().values()) {
            handleFlattenedPropertiesDeserializationHelper(structure, methodBlock, ifBlock, additionalProperties,
                fieldNameVariableName);
        }
    }

    private static JavaIfBlock handleFlattenedPropertiesDeserializationHelper(
        JsonFlattenedPropertiesTree flattenedProperties, JavaBlock methodBlock, JavaIfBlock ifBlock,
        ClientModelProperty additionalProperties, String fieldNameVariableName) {
        if (flattenedProperties.getProperty() != null) {
            // This is a terminal location, so only need to handle checking for the property name.
            return ifOrElseIf(methodBlock, ifBlock,
                "\"" + flattenedProperties.getNodeName() + "\".equals(" + fieldNameVariableName + ")", deserializationBlock ->
                    generateDeserializationLogic(deserializationBlock, flattenedProperties.getProperty().getProperty()));
        } else {
            // Otherwise this is an intermediate location and a while loop reader needs to be added.
            return ifOrElseIf(methodBlock, ifBlock,
                "\"" + flattenedProperties.getNodeName() + "\".equals(" + fieldNameVariableName + ") && reader.currentToken() == JsonToken.START_OBJECT",
                ifAction -> addReaderWhileLoop(ifAction, false, fieldNameVariableName, whileBlock -> {
                    JavaIfBlock innerIfBlock = null;
                    for (JsonFlattenedPropertiesTree structure : flattenedProperties.getChildrenNodes().values()) {
                        innerIfBlock = handleFlattenedPropertiesDeserializationHelper(structure, methodBlock, innerIfBlock,
                            additionalProperties, fieldNameVariableName);
                    }

                    handleUnknownFieldDeserialization(whileBlock, innerIfBlock, additionalProperties, fieldNameVariableName);
                }));
        }
    }

    private static void generateDeserializationLogic(JavaBlock deserializationBlock, ClientModelProperty property) {
        IType wireType = property.getWireType();
        IType clientType = property.getClientType();

        // Attempt to determine whether the wire type is simple deserialization.
        // This is primitives, boxed primitives, a small set of string based models, and other ClientModels.
        String simpleDeserialization = getSimpleJsonDeserialization(wireType, clientType, "reader");
        if (simpleDeserialization != null) {
            deserializationBlock.line(property.getName() + " = " + simpleDeserialization + ";");
        } else if (wireType == ClassType.Object) {
            deserializationBlock.line(property.getName() + " = reader.readUntyped();");
        } else if (wireType instanceof IterableType) {
            deserializationBlock.text(property.getName() + " = ");
            deserializeContainerProperty(deserializationBlock, "readArray", wireType,
                ((IterableType) wireType).getElementType(), 0);
        } else if (wireType instanceof MapType) {
            // Assumption is that the key type for the Map is a String. This may not always hold true and when that
            // becomes reality this will need to be reworked to handle that case.
            deserializationBlock.text(property.getName() + " = ");
            deserializeContainerProperty(deserializationBlock, "readMap", wireType, ((MapType) wireType).getValueType(), 0);
        } else {
            // TODO (alzimmer): Resolve this as deserialization logic generation needs to handle all cases.
            throw new RuntimeException("Unknown wire type " + wireType + ". Need to add support for it.");
        }

        // If the property was required, mark it as found.
        if (property.isRequired()) {
            deserializationBlock.line(property.getName() + "Found = true;");
        }
    }

    /**
     * Helper method to deserialize a container property (such as {@link List} and {@link Map}).
     *
     * @param methodBlock The method handling deserialization.
     * @param utilityMethod The method aiding in the deserialization of the container.
     * @param containerType The container type.
     * @param elementType The element type for the container, for a {@link List} this is the element type and for a
     * {@link Map} this is the value type.
     * @param depth Depth of recursion for container types, such as {@code Map<String, List<String>>} would be 0 when
     * {@code Map} is being handled and then 1 when {@code List} is being handled.
     */
    private static void deserializeContainerProperty(JavaBlock methodBlock, String utilityMethod, IType containerType,
        IType elementType, int depth) {
        String callingReaderName = depth == 0 ? "reader" : "reader" + depth;
        String lambdaReaderName = "reader" + (depth + 1);
        String valueDeserializationMethod = getSimpleJsonDeserialization(elementType, elementType, lambdaReaderName);

        methodBlock.line(callingReaderName + "." + utilityMethod + "(" + lambdaReaderName + " -> ");
        methodBlock.indent(() -> {
            if (valueDeserializationMethod != null) {
                methodBlock.line(valueDeserializationMethod);
            } else if (elementType == ClassType.Object) {
                methodBlock.line(lambdaReaderName + ".readUntyped()");
            } else if (elementType instanceof IterableType) {
                deserializeContainerProperty(methodBlock, "readArray", elementType,
                    ((IterableType) elementType).getElementType(), depth + 1);
            } else if (elementType instanceof MapType) {
                // Assumption is that the key type for the Map is a String. This may not always hold true and when that
                // becomes reality this will need to be reworked to handle that case.
                deserializeContainerProperty(methodBlock, "readMap", elementType,
                    ((MapType) elementType).getValueType(), depth + 1);
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

    private static String getSimpleJsonDeserialization(IType wireType, IType clientType, String readerName) {
        String wireTypeDeserialization = null;
        if (wireType instanceof ClassType && ((ClassType) wireType).isSwaggerType()) {
            wireTypeDeserialization = wireType + ".fromJson(" + readerName + ")";
        } else if (wireType instanceof EnumType) {
            EnumType enumType = (EnumType) wireType;
            // This makes the assumption that the enum element type is a simple type.
            wireTypeDeserialization = String.format("%s.%s(%s.%s)", enumType.getName(),
                enumType.getFromJsonMethodName(), readerName, enumType.getElementType().jsonDeserializationMethod());
        } else if (wireType.jsonDeserializationMethod() != null) {
            wireTypeDeserialization = readerName + "." + wireType.jsonDeserializationMethod();
        }

        return (wireType != clientType && wireTypeDeserialization != null)
            ? wireType.convertToClientType(wireTypeDeserialization)
            : wireTypeDeserialization;
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
                    javaBlock.line(additionalProperties.getName() + ".put(" + fieldNameVariableName + ", reader.readUntyped());");
                } else {
                    // Another assumption, the additional properties value type is simple.
                    javaBlock.line(additionalProperties.getName() + ".put(" + fieldNameVariableName + ", "
                        + getSimpleJsonDeserialization(valueType, valueType, "reader") + ");");
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
        ClientModelPropertiesManager propertiesManager, JavaSettings settings) {
        StringBuilder constructorArgs = new StringBuilder();
        propertiesManager.getSuperRequiredProperties().stream().map(ClientModelProperty::getName)
            .forEach(arg -> addConstructorParameter(constructorArgs, arg));
        propertiesManager.getRequiredProperties().stream().map(ClientModelProperty::getName)
            .forEach(arg -> addConstructorParameter(constructorArgs, arg));

        int requiredPropertiesCount = propertiesManager.getSuperRequiredProperties().size()
            + propertiesManager.getRequiredProperties().size();
        // If there are required properties of any type we must check that all required fields were found.
        if (!CoreUtils.isNullOrEmpty(propertiesManager.getSuperRequiredProperties())
            || !CoreUtils.isNullOrEmpty(propertiesManager.getRequiredProperties())) {
            String ifStatement = Stream.concat(propertiesManager.getSuperRequiredProperties().stream(),
                    propertiesManager.getRequiredProperties().stream())
                .map(property -> property.getName() + "Found")
                .collect(Collectors.joining(" && "));

            methodBlock.ifBlock(ifStatement, ifAction ->
                createJsonObjectAndReturn(methodBlock, modelName, constructorArgs.toString(), propertiesManager,
                    settings));

            methodBlock.line("List<String> missingProperties = new ArrayList<>();");
            propertiesManager.getSuperRequiredProperties().forEach(property ->
                addFoundValidationIfCheck(methodBlock, property));
            propertiesManager.getRequiredProperties().forEach(property ->
                addFoundValidationIfCheck(methodBlock, property));

            methodBlock.line();
            methodBlock.line("throw new IllegalStateException(\"Missing required property/properties: \""
                + "+ String.join(\", \", missingProperties));");
        } else {
            createJsonObjectAndReturn(methodBlock, modelName, constructorArgs.toString(), propertiesManager, settings);
        }
    }

    private static void createJsonObjectAndReturn(JavaBlock methodBlock, String modelName, String constructorArgs,
        ClientModelPropertiesManager propertiesManager, JavaSettings settings) {
        methodBlock.line(modelName + " deserializedValue = new " + modelName + "(" + constructorArgs + ");");

        propertiesManager.getSuperSetterProperties().forEach(property ->
            handleSettingDeserializedValue(methodBlock, property, settings, true));
        propertiesManager.getSetterProperties().forEach(property ->
            handleSettingDeserializedValue(methodBlock, property, settings, false));

        if (propertiesManager.getAdditionalProperties() != null) {
            handleSettingDeserializedValue(methodBlock, propertiesManager.getAdditionalProperties(), settings, false);
        }

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
        JavaSettings settings, boolean fromSuper) {
        // If the property is defined in a super class or doesn't match the wire type use the setter as this will
        // be able to set the value in the super class definition or handle converting the wire type.
        if (fromSuper || property.getWireType() != property.getClientType()) {
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

    private static void writeToXml(JavaClass classBlock, ClientModelPropertiesManager propertiesManager) {
        classBlock.annotation("Override");
        classBlock.publicMethod("XmlWriter toXml(XmlWriter xmlWriter)", methodBlock -> {
            String modelXmlName = propertiesManager.getModel().getXmlName();
            methodBlock.line("xmlWriter.writeStartElement(\"" + modelXmlName + "\");");

            String modelXmlNamespace = propertiesManager.getModel().getXmlNamespace();
            if (modelXmlNamespace != null) {
                methodBlock.line("xmlWriter.writeNamespace(\"" + modelXmlNamespace + "\");");
            }

            propertiesManager.getXmlAttributes().forEach(property -> serializeXml(methodBlock, property));
            propertiesManager.getXmlElements().forEach(property -> serializeXml(methodBlock, property));

            methodBlock.methodReturn("xmlWriter.writeEndElement()");
        });
    }

    /**
     * Serializes an XML element.
     *
     * @param methodBlock The method handling serialization.
     * @param element The XML element being serialized.
     */
    private static void serializeXml(JavaBlock methodBlock, ClientModelProperty element) {
        IType wireType = element.getWireType();
        String propertyValueGetter = "this." + element.getName();

        // XML wrappers implement XmlSerializable and always use writeXml, check for this first as it's an early out.
        if (element.getIsXmlWrapper()) {
            methodBlock.line("xmlWriter.writeXml(" + propertyValueGetter + ");");
            return;
        }

        // Attempt to determine whether the wire type is simple serialization.
        // This is primitives, boxed primitives, a small set of string based models, and other ClientModels.
        String xmlSerializationMethodCall = wireType.xmlSerializationMethodCall("xmlWriter", element.getXmlName(),
            element.getXmlNamespace(), propertyValueGetter, element.getIsXmlAttribute());
        if (xmlSerializationMethodCall != null) {
            // XML text has special handling.
            if (element.isXmlText()) {
                methodBlock.line("xmlWriter.writeString(" + propertyValueGetter + ");");
            } else {
                methodBlock.line(xmlSerializationMethodCall + ";");
            }
        } else if (wireType instanceof ClassType && ((ClassType) wireType).isSwaggerType()) {
            methodBlock.line("xmlWriter.writeXml(" + propertyValueGetter + ");");
        } else if (wireType instanceof IterableType) {
            IType elementType = ((IterableType) wireType).getElementType();

            methodBlock.ifBlock(propertyValueGetter + " != null", ifAction -> {
                String xmlWrite = elementType.xmlSerializationMethodCall("xmlWriter", element.getXmlName(),
                    element.getXmlNamespace(), "element", element.getIsXmlAttribute());
                ifAction.line(propertyValueGetter + ".forEach(element -> " + xmlWrite + ");");
            });
        } else if (wireType instanceof MapType) {
            // Assumption is that the key type for the Map is a String. This may not always hold true and when that
            // becomes reality this will need to be reworked to handle that case.
            IType valueType = ((MapType) wireType).getValueType();

            methodBlock.ifBlock(propertyValueGetter + " != null", ifAction -> {
                ifAction.line("xmlWriter.writeStartElement(\"" + element.getXmlName() + "\");");

                if (valueType instanceof ClassType && ((ClassType) valueType).isSwaggerType()) {
                    String writeStartElement = (element.getXmlNamespace() != null)
                        ? "xmlWriter.writeStartElement(null, \"" + element.getXmlNamespace() + "\", key);"
                        : "xmlWriter.writeStartElement(key);";

                    ifAction.line(propertyValueGetter + ".forEach((key, value) -> {\n"
                        + "%s"
                        + "xmlWriter.writeXml(value);\n"
                        + "xmlWriter.writeEndElement();\n"
                        + "});", writeStartElement);
                } else {
                    ifAction.line(propertyValueGetter + ".forEach((key, value) -> %s);",
                        valueType.xmlSerializationMethodCall("xmlWriter", "key", element.getXmlNamespace(), "value",
                            false));
                }

                ifAction.line("xmlWriter.writeEndElement();");
            });
        } else {
            // TODO (alzimmer): Resolve this as serialization logic generation needs to handle all cases.
            throw new RuntimeException("Unknown wire type " + wireType + " in XML element serialization. "
                + "Need to add support for it.");
        }
    }

    private static void writeFromXml(JavaClass classBlock, ClientModel model,
        ClientModelPropertiesManager propertiesManager, JavaSettings settings) {

    }

    private static String getSimpleXmlAttributeDeserialization(IType wireType, IType clientType, String readerName,
        String attributeName, String attributeNamespace) {
        String wireTypeDeserialization;
        if (wireType == PrimitiveType.Boolean) {
            wireTypeDeserialization = readerName + ".getBooleanElement()";
        } else if (wireType == ClassType.Boolean) {
            wireTypeDeserialization = readerName + ".getNullableElement(Boolean::parseBoolean)";
        } else if (wireType == PrimitiveType.Double) {
            wireTypeDeserialization = readerName + ".getDoubleElement()";
        } else if (wireType == ClassType.Double) {
            wireTypeDeserialization = readerName + ".getNullableElement(Double::parseDouble)";
        } else if (wireType == PrimitiveType.Float) {
            wireTypeDeserialization = readerName + ".getFloatElement()";
        } else if (wireType == ClassType.Float) {
            wireTypeDeserialization = readerName + ".getNullableElement(Float::parseFloat)";
        } else if (wireType == PrimitiveType.Int) {
            wireTypeDeserialization = readerName + ".getIntElement()";
        } else if (wireType == ClassType.Integer) {
            wireTypeDeserialization = readerName + ".getNullableElement(Integer::parseInt)";
        } else if (wireType == PrimitiveType.Long) {
            wireTypeDeserialization = readerName + ".getLongElement()";
        } else if (wireType == ClassType.Long) {
            wireTypeDeserialization = readerName + ".getNullableElement(Long::parseLong)";
        } else if (wireType == PrimitiveType.Char || wireType == ClassType.Character) {
            wireTypeDeserialization = readerName + ".getElementStringValue().charAt(0)";
        } else if (wireType == ArrayType.ByteArray) {
            wireTypeDeserialization = ".getBinaryElement()";
        } else if (wireType == ClassType.String) {
            wireTypeDeserialization = readerName + ".getElementStringValue()";
        } else if (wireType == ClassType.DateTimeRfc1123) {
            wireTypeDeserialization = readerName + ".getNullableElement(DateTimeRfc1123::new)";
        } else if (wireType == ClassType.DateTime) {
            wireTypeDeserialization = readerName + ".getNullableElement(OffsetDateTime::parse)";
        } else if (wireType == ClassType.LocalDate) {
            wireTypeDeserialization = readerName + ".getNullableElement(LocalDate::parse)";
        } else if (wireType == ClassType.Duration) {
            wireTypeDeserialization = readerName + ".getNullableElement(Duration::parse)";
        } else if (wireType instanceof EnumType) {
            EnumType enumType = (EnumType) wireType;
            wireTypeDeserialization = readerName + ".getNullableElement(" + enumType.getName() + "::" + enumType.getFromJsonMethodName() + ")";
        } else if (wireType == ClassType.UUID) {
            wireTypeDeserialization = readerName + ".getNullableElement(UUID::fromString)";
        } else if (wireType instanceof ClassType && ((ClassType) wireType).isSwaggerType()) {
            wireTypeDeserialization = wireType + ".fromXml(" + readerName + ")";
        } else {
            wireTypeDeserialization = null;
        }

        if (wireType != clientType && wireTypeDeserialization != null) {
            return wireType.convertToClientType(wireTypeDeserialization);
        } else {
            return wireTypeDeserialization;
        }
    }

    private static String getSimpleXmlElementDeserialization(IType wireType, IType clientType, String readerName) {
        String wireTypeDeserialization;
        if (wireType == PrimitiveType.Boolean) {
            wireTypeDeserialization = readerName + ".getBooleanElement()";
        } else if (wireType == ClassType.Boolean) {
            wireTypeDeserialization = readerName + ".getNullableElement(Boolean::parseBoolean)";
        } else if (wireType == PrimitiveType.Double) {
            wireTypeDeserialization = readerName + ".getDoubleElement()";
        } else if (wireType == ClassType.Double) {
            wireTypeDeserialization = readerName + ".getNullableElement(Double::parseDouble)";
        } else if (wireType == PrimitiveType.Float) {
            wireTypeDeserialization = readerName + ".getFloatElement()";
        } else if (wireType == ClassType.Float) {
            wireTypeDeserialization = readerName + ".getNullableElement(Float::parseFloat)";
        } else if (wireType == PrimitiveType.Int) {
            wireTypeDeserialization = readerName + ".getIntElement()";
        } else if (wireType == ClassType.Integer) {
            wireTypeDeserialization = readerName + ".getNullableElement(Integer::parseInt)";
        } else if (wireType == PrimitiveType.Long) {
            wireTypeDeserialization = readerName + ".getLongElement()";
        } else if (wireType == ClassType.Long) {
            wireTypeDeserialization = readerName + ".getNullableElement(Long::parseLong)";
        } else if (wireType == PrimitiveType.Char || wireType == ClassType.Character) {
            wireTypeDeserialization = readerName + ".getElementStringValue().charAt(0)";
        } else if (wireType == ArrayType.ByteArray) {
            wireTypeDeserialization = ".getBinaryElement()";
        } else if (wireType == ClassType.String) {
            wireTypeDeserialization = readerName + ".getElementStringValue()";
        } else if (wireType == ClassType.DateTimeRfc1123) {
            wireTypeDeserialization = readerName + ".getNullableElement(DateTimeRfc1123::new)";
        } else if (wireType == ClassType.DateTime) {
            wireTypeDeserialization = readerName + ".getNullableElement(OffsetDateTime::parse)";
        } else if (wireType == ClassType.LocalDate) {
            wireTypeDeserialization = readerName + ".getNullableElement(LocalDate::parse)";
        } else if (wireType == ClassType.Duration) {
            wireTypeDeserialization = readerName + ".getNullableElement(Duration::parse)";
        } else if (wireType instanceof EnumType) {
            EnumType enumType = (EnumType) wireType;
            wireTypeDeserialization = readerName + ".getNullableElement(" + enumType.getName() + "::" + enumType.getFromJsonMethodName() + ")";
        } else if (wireType == ClassType.UUID) {
            wireTypeDeserialization = readerName + ".getNullableElement(UUID::fromString)";
        } else if (wireType instanceof ClassType && ((ClassType) wireType).isSwaggerType()) {
            wireTypeDeserialization = wireType + ".fromXml(" + readerName + ")";
        } else {
            wireTypeDeserialization = null;
        }

        if (wireType != clientType && wireTypeDeserialization != null) {
            return wireType.convertToClientType(wireTypeDeserialization);
        } else {
            return wireTypeDeserialization;
        }
    }

    private static List<ClientModelPropertyWithMetadata> getClientModelPropertiesInJsonTree(
        JsonFlattenedPropertiesTree tree) {
        if (tree.getProperty() != null) {
            // Terminal node only contains a property.
            return Collections.singletonList(tree.getProperty());
        } else {
            List<ClientModelPropertyWithMetadata> treeProperties = new ArrayList<>();
            for (JsonFlattenedPropertiesTree childNode : tree.getChildrenNodes().values()) {
                treeProperties.addAll(getClientModelPropertiesInJsonTree(childNode));
            }

            return treeProperties;
        }
    }
}
