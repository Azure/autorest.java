// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.implementation.ClientModelPropertiesManager;
import com.azure.autorest.implementation.ClientModelPropertyWithMetadata;
import com.azure.autorest.implementation.JsonFlattenedPropertiesTree;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
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
import com.azure.xml.XmlReader;
import com.azure.xml.XmlSerializable;
import com.azure.xml.XmlToken;
import com.azure.xml.XmlWriter;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.azure.autorest.util.ClientModelUtil.includePropertyInConstructor;

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
        if (model.getXmlName() != null) {
            imports.add(QName.class.getName());
            imports.add(XMLStreamException.class.getName());

            imports.add(XmlSerializable.class.getName());
            imports.add(XmlWriter.class.getName());
            imports.add(XmlReader.class.getName());
            imports.add(XmlToken.class.getName());
        } else {
            imports.add(IOException.class.getName());

            ClassType.JSON_SERIALIZABLE.addImportsTo(imports, false);
            ClassType.JSON_WRITER.addImportsTo(imports, false);
            ClassType.JSON_READER.addImportsTo(imports, false);
            ClassType.JSON_TOKEN.addImportsTo(imports, false);
            imports.add(settings.getPackage(settings.getImplementationSubpackage()) + ".CoreToCodegenBridgeUtils");
        }

        ClassType.CORE_UTILS.addImportsTo(imports, false);

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
        if (!settings.isStreamStyleSerialization() || model.isStronglyTypedHeader()) {
            return classSignature;
        }

        String interfaceName = (model.getXmlName() != null)
            ? XmlSerializable.class.getSimpleName()
            : ClassType.JSON_SERIALIZABLE.getName();

        return classSignature + " implements " + interfaceName + "<" + model.getName() + ">";
    }

    static void xmlWrapperClassXmlSerializableImplementation(JavaClass classBlock, String wrapperClassName,
        IType iterableType, String xmlRootElementName, String xmlRootElementNamespace, String xmlListElementName,
        String xmlElementNameCamelCase, String xmlListElementNamespace) {
        IType elementType = ((IterableType) iterableType).getElementType();

        classBlock.annotation("Override");
        classBlock.publicMethod("XmlWriter toXml(XmlWriter xmlWriter) throws XMLStreamException",
            methodBlock -> methodBlock.methodReturn("toXml(xmlWriter, null)"));

        classBlock.annotation("Override");
        classBlock.publicMethod("XmlWriter toXml(XmlWriter xmlWriter, String rootElementName) throws XMLStreamException", writerMethod -> {
            writerMethod.line("rootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? \"" + xmlRootElementName + "\" : rootElementName;");
            String writeStartElement = (xmlRootElementNamespace != null)
                ? "xmlWriter.writeStartElement(\"" + xmlRootElementNamespace + "\", rootElementName);"
                : "xmlWriter.writeStartElement(rootElementName);";
            writerMethod.line(writeStartElement);

            writerMethod.ifBlock(xmlElementNameCamelCase + " != null", ifAction -> {
                String xmlWrite = elementType.xmlSerializationMethodCall("xmlWriter", xmlListElementName,
                    xmlListElementNamespace, "element", false, false);
                ifAction.line("for (%s element : %s) {", elementType, xmlElementNameCamelCase);
                ifAction.indent(() -> ifAction.line(xmlWrite + ";"));
                ifAction.line("}");
            });

            writerMethod.methodReturn("xmlWriter.writeEndElement()");
        });

        classBlock.publicStaticMethod(wrapperClassName + " fromXml(XmlReader xmlReader) throws XMLStreamException",
            readerMethod -> readerMethod.methodReturn("fromXml(xmlReader, null)"));

        classBlock.publicStaticMethod(wrapperClassName + " fromXml(XmlReader xmlReader, String rootElementName) throws XMLStreamException", readerMethod -> {
            readerMethod.line("rootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? \"" + xmlRootElementName + "\" : rootElementName;");
            String readObject = (xmlRootElementNamespace != null)
                ? "return xmlReader.readObject(\"" + xmlRootElementNamespace + "\", rootElementName, reader -> {"
                : "return xmlReader.readObject(rootElementName, reader -> {";

            readerMethod.line(readObject);
            readerMethod.indent(() -> {
                readerMethod.line(iterableType + " items = null;");
                readerMethod.line();
                readerMethod.line("while (reader.nextElement() != XmlToken.END_ELEMENT) {");
                readerMethod.indent(() -> {
                    readerMethod.line("QName elementName = reader.getElementName();");
                    String condition = getXmlNameConditional(xmlListElementName, xmlListElementNamespace, "elementName");
                    readerMethod.line();
                    readerMethod.ifBlock(condition, ifBlock -> {
                        ifBlock.ifBlock("items == null", ifBlock2 -> ifBlock2.line("items = new ArrayList<>();"));
                        ifBlock.line();

                        // TODO (alzimmer): Insert XML object reading logic.
                        ifBlock.line("items.add(" + getSimpleXmlDeserialization(elementType, "reader", null, null,
                                null) + ");");
                    }).elseBlock(elseBlock -> elseBlock.line("reader.nextElement();"));
                });
                readerMethod.line("}");

                readerMethod.methodReturn("new " + wrapperClassName + "(items)");
            });
            readerMethod.line("});");
        });
    }

    @Override
    protected void addFieldAnnotations(ClientModel model, ClientModelProperty property, JavaClass classBlock, JavaSettings settings) {
        // no-op as stream-style serialization doesn't add any field-level annotations.
    }

    @Override
    protected void writeStreamStyleSerialization(JavaClass classBlock, ClientModel model, JavaSettings settings) {
        // Early out as strongly-typed headers do their own thing.
        if (model.isStronglyTypedHeader()) {
            return;
        }

        ClientModelPropertiesManager propertiesManager = new ClientModelPropertiesManager(model, settings);

        if (model.getXmlName() != null) {
            writeToXml(classBlock, propertiesManager);
            writeFromXml(classBlock, model, propertiesManager, settings);
        } else {
            writeToJson(classBlock, propertiesManager);
            writeFromJson(classBlock, model, propertiesManager, settings);
        }
    }

    private static void writeToJson(JavaClass classBlock, ClientModelPropertiesManager propertiesManager) {
        classBlock.annotation("Override");
        classBlock.publicMethod("JsonWriter toJson(JsonWriter jsonWriter) throws IOException", methodBlock -> {
            methodBlock.line("jsonWriter.writeStartObject();");

            // If the model has a discriminator property serialize it first.
            // Unless the polymorphic discriminator is also a property on the model, in which case it'll be handled
            // later.
            if (propertiesManager.getDiscriminatorProperty() != null
                && !CoreUtils.isNullOrEmpty(propertiesManager.getDiscriminatorProperty().getDefaultValue())
                && !propertiesManager.isDiscriminatorRequired()) {
                ClientModelProperty discriminatorProperty = propertiesManager.getDiscriminatorProperty();
                serializeJsonProperty(methodBlock, discriminatorProperty, discriminatorProperty.getSerializedName(),
                        false, true);
            }

            BiConsumer<ClientModelProperty, Boolean> serializeJsonProperty = (property, fromSuper) ->
                serializeJsonProperty(methodBlock, property, property.getSerializedName(), fromSuper, true);

            propertiesManager.forEachSuperRequiredProperty(property -> serializeJsonProperty.accept(property, true));
            propertiesManager.forEachSuperSetterProperty(property -> serializeJsonProperty.accept(property, true));
            propertiesManager.forEachRequiredProperty(property -> serializeJsonProperty.accept(property, false));
            propertiesManager.forEachSetterProperty(property -> serializeJsonProperty.accept(property, false));

            handleFlattenedPropertiesSerialization(methodBlock, propertiesManager.getJsonFlattenedPropertiesTree());

            ClientModelProperty additionalProperties = propertiesManager.getAdditionalProperties();
            if (additionalProperties != null) {
                methodBlock.ifBlock(additionalProperties.getName() + " != null", ifAction -> {
                    IType valueType = ((MapType) additionalProperties.getWireType()).getValueType().asNullable();
                    ifAction.line("for (Map.Entry<String, %s> additionalProperty : %s.entrySet()) {", valueType, additionalProperties.getName());
                    ifAction.indent(() ->
                        ifAction.line("jsonWriter.writeUntypedField(additionalProperty.getKey(), additionalProperty.getValue());"));
                    ifAction.line("}");
                });
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

        if (property.isReadOnly() && !property.isPolymorphicDiscriminator()) {
            // Non-polymorphic discriminator, readonly properties are never serialized.
            return;
        }

        IType clientType = property.getClientType();
        IType wireType = property.getWireType();
        String propertyValueGetter;
        if (fromSuperType) {
            propertyValueGetter = (clientType != wireType)
                ? wireType.convertFromClientType(property.getGetterName() + "()")
                : property.getGetterName() + "()";
        } else if (property.isPolymorphicDiscriminator()) {
            propertyValueGetter = property.getDefaultValue();
        } else {
            propertyValueGetter = "this." + property.getName();
        }

        // Attempt to determine whether the wire type is simple serialization.
        // This is primitives, boxed primitives, a small set of string based models, and other ClientModels.
        String fieldSerializationMethod = wireType.jsonSerializationMethodCall("jsonWriter", serializedName,
            propertyValueGetter);
        if (wireType == ClassType.RESPONSE_ERROR) {
            // While azure-core hasn't shipped ResponseError implementing JsonSerializable it has special handling.
            methodBlock.line("jsonWriter.writeFieldName(\"" + serializedName + "\");");
            methodBlock.line("CoreToCodegenBridgeUtils.responseErrorToJson(jsonWriter, " + propertyValueGetter + ");");
        } else if (fieldSerializationMethod != null) {
            if (fromSuperType && clientType != wireType && clientType.isNullable()) {
                // If the property is from a super type and the client type is different from the wire type then a null
                // check is required to prevent a NullPointerException when converting the value.
                methodBlock.ifBlock(property.getGetterName() + "() != null", ifAction -> {
                    ifAction.line(fieldSerializationMethod + ";");
                });
            } else {
                methodBlock.line(fieldSerializationMethod + ";");
            }
        } else if (wireType == ClassType.OBJECT) {
            methodBlock.line("jsonWriter.writeUntypedField(\"" + serializedName + "\", " + propertyValueGetter + ");");
        } else if (wireType == ClassType.BINARY_DATA) {
            String writeBinaryDataExpr = "jsonWriter.writeUntypedField(\"" + serializedName + "\", " + propertyValueGetter + ".toObject(Object.class));";
            if (!property.isRequired()) {
                methodBlock.ifBlock(propertyValueGetter + " != null", ifAction -> {
                    ifAction.line(writeBinaryDataExpr);
                });
            } else {
                methodBlock.line(writeBinaryDataExpr);
            }
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
            if (elementType == ClassType.RESPONSE_ERROR) {
                // While azure-core hasn't shipped ResponseError implementing JsonSerializable it has special handling.
                methodBlock.line(lambdaWriterName + ".writeFieldName(\"" + serializedName + "\");");
                methodBlock.line("CoreToCodegenBridgeUtils.responseErrorToJson(" + lambdaWriterName + ", " + propertyValueGetter + ");");
            } else if (valueSerializationMethod != null) {
                methodBlock.line(valueSerializationMethod);
            } else if (elementType == ClassType.OBJECT) {
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
        readJsonObject(classBlock, propertiesManager, false, methodBlock -> {
            // For now, reading polymorphic types will always buffer the current object.
            // In the future this can be enhanced to switch if the first property is the discriminator field and to use
            // a Map to contain all properties found while searching for the discriminator field.
            // TODO (alzimmer): Need to handle non-string wire type discriminator types.
            methodBlock.line(String.join("\n",
                "String discriminatorValue = null;",
                "JsonReader readerToUse = reader.bufferObject();",
                "",
                "readerToUse.nextToken(); // Prepare for reading",
                "while (readerToUse.nextToken() != JsonToken.END_OBJECT) {",
                "    String " + fieldNameVariableName + " = readerToUse.getFieldName();",
                "    readerToUse.nextToken();",
                "    if (\"" + discriminatorProperty.getSerializedName() + "\".equals(" + fieldNameVariableName + ")) {",
                "        discriminatorValue = readerToUse.getString();",
                "        break;",
                "    } else {",
                "        readerToUse.skipChildren();",
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
                : methodBlock.ifBlock("discriminatorValue == null || \"" + propertiesManager.getExpectedDiscriminator()
                    + "\".equals(discriminatorValue)",
                ifStatement -> ifStatement.methodReturn("fromJsonKnownDiscriminator(readerToUse)"));

            // Add deserialization for all child types.
            List<ClientModel> childTypes = getAllChildTypes(model, new ArrayList<>());
            for (int i = 0; i < childTypes.size(); i++) {
                ClientModel childType = childTypes.get(i);

                ifBlock = ifOrElseIf(methodBlock, ifBlock, "\"" + childType.getSerializedName() + "\".equals(discriminatorValue)",
                    ifStatement -> ifStatement.methodReturn(childType.getName() + (isSuperTypeWithDiscriminator(childType)
                        ? ".fromJsonKnownDiscriminator(readerToUse.reset())"
                        : ".fromJson(readerToUse.reset())")));

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

            // TODO (alzimmer): Add a log message if the discriminator didn't match anything that was expected.
            if (ifBlock == null) {
                methodBlock.methodReturn("fromJsonKnownDiscriminator(readerToUse.reset())");
            } else {
                ifBlock.elseBlock(elseBlock ->
                    elseBlock.methodReturn("fromJsonKnownDiscriminator(readerToUse.reset())"));
            }
        });

        readJsonObject(classBlock, propertiesManager, true,
            methodBlock -> writeFromJsonDeserialization(methodBlock, propertiesManager, true, settings));
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
        readJsonObject(classBlock, propertiesManager, false,
            methodBlock -> writeFromJsonDeserialization(methodBlock, propertiesManager, false, settings));
    }

    private static void writeFromJsonDeserialization(JavaBlock methodBlock,
        ClientModelPropertiesManager propertiesManager, boolean fromSuperTypeReading, JavaSettings settings) {
        // Add the deserialization logic.
        methodBlock.indent(() -> {
            // Initialize local variables to track what has been deserialized.
            initializeLocalVariables(methodBlock, propertiesManager, false, settings);

            // Add the outermost while loop to read the JSON object.
            String fieldNameVariableName = propertiesManager.getJsonReaderFieldNameVariableName();
            addReaderWhileLoop(methodBlock, true, fieldNameVariableName, false, whileBlock -> {
                JavaIfBlock ifBlock = null;

                if (propertiesManager.getDiscriminatorProperty() != null
                    && (propertiesManager.isDiscriminatorRequired() || !fromSuperTypeReading)) {
                    ClientModelProperty discriminatorProperty = propertiesManager.getDiscriminatorProperty();
                    String ifStatement = String.format("\"%s\".equals(%s)", discriminatorProperty.getSerializedName(),
                        fieldNameVariableName);

                    ifBlock = methodBlock.ifBlock(ifStatement, ifAction -> {
                        if (propertiesManager.isDiscriminatorRequired()) {
                            ifAction.line(discriminatorProperty.getName() + " = reader.getString();");
                        } else {
                            ifAction.line("String %s = reader.getString();", discriminatorProperty.getName());
                        }

                        // From super type reading indicates we're calling fromJsonKnownDiscriminator, no need to
                        // validate the discriminator value as that's already been done.
                        if (!fromSuperTypeReading) {
                            String ifStatement2 = String.format("!\"%s\".equals(%s)", propertiesManager.getExpectedDiscriminator(),
                                discriminatorProperty.getName());
                            ifAction.ifBlock(ifStatement2, ifAction2 -> ifAction2.line(
                                "throw new IllegalStateException(" + "\"'%s' was expected to be non-null and equal to '%s'. The found '%s' was '\" + %s + \"'.\");",
                                discriminatorProperty.getSerializedName(), propertiesManager.getExpectedDiscriminator(),
                                discriminatorProperty.getSerializedName(), discriminatorProperty.getName()));
                        }

                        if (propertiesManager.isDiscriminatorRequired() && !settings.isDisableRequiredJsonAnnotation()) {
                            ifAction.line(discriminatorProperty.getName() + "Found = true;");
                        }
                    });
                }

                // Loop over all properties and generate their deserialization handling.
                AtomicReference<JavaIfBlock> ifBlockReference = new AtomicReference<>(ifBlock);
                BiConsumer<ClientModelProperty, Boolean> consumer = (property, fromSuper) ->
                    handleJsonPropertyDeserialization(property, propertiesManager.getDeserializedModelName(),
                        whileBlock, ifBlockReference, fieldNameVariableName, fromSuper,
                        propertiesManager.hasConstructorArguments(), settings);
                // Constants and required polymorphic discriminators are skipped.
                // Constants aren't deserialized and polymorphic discriminators are handled separately.
                Predicate<ClientModelProperty> skipRequiredProperty = property -> property.isConstant()
                    || (propertiesManager.isDiscriminatorRequired() && Objects.equals(property.getSerializedName(), propertiesManager.getModel().getPolymorphicDiscriminator()));
                propertiesManager.forEachSuperRequiredProperty(property -> {
                    if (skipRequiredProperty.test(property)) {
                        return;
                    }

                    consumer.accept(property, true);
                });
                propertiesManager.forEachSuperSetterProperty(property -> consumer.accept(property, true));
                propertiesManager.forEachRequiredProperty(property -> {
                    if (skipRequiredProperty.test(property)) {
                        return;
                    }
                    consumer.accept(property, false);
                });
                propertiesManager.forEachSetterProperty(property -> consumer.accept(property, false));

                ifBlock = ifBlockReference.get();

                handleFlattenedPropertiesDeserialization(propertiesManager.getJsonFlattenedPropertiesTree(),
                    methodBlock, ifBlock, propertiesManager.getAdditionalProperties(),
                    propertiesManager.getJsonReaderFieldNameVariableName(), propertiesManager.hasConstructorArguments(),
                    settings);

                // All properties have been checked for, add an else block that will either ignore unknown properties
                // or add them into an additional properties bag.
                handleUnknownJsonFieldDeserialization(whileBlock, ifBlock, propertiesManager.getAdditionalProperties(),
                    propertiesManager.getJsonReaderFieldNameVariableName());
            });
        });

        // Add the validation and return logic.
        handleReadReturn(methodBlock, propertiesManager.getModel().getName(), propertiesManager, false, settings);
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
    private static void readJsonObject(JavaClass classBlock, ClientModelPropertiesManager propertiesManager,
        boolean superTypeReading, Consumer<JavaBlock> deserializationBlock) {
        JavaVisibility visibility = superTypeReading ? JavaVisibility.PackagePrivate : JavaVisibility.Public;
        String methodName = superTypeReading ? "fromJsonKnownDiscriminator" : "fromJson";

        String modelName = propertiesManager.getModel().getName();
        boolean hasRequiredProperties = propertiesManager.hasRequiredProperties();
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

                javadocComment.methodThrows("IOException", "If an error occurs while reading the " + modelName + ".");
            });
        }

        classBlock.staticMethod(visibility, modelName + " " + methodName + "(JsonReader jsonReader) throws IOException", methodBlock -> {
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
    private static void initializeLocalVariables(JavaBlock methodBlock, ClientModelPropertiesManager propertiesManager,
        boolean isXml, JavaSettings settings) {
        if (propertiesManager.hasConstructorArguments()) {
            if (isXml) {
                // XML only needs to initialize the XML element properties. XML attribute properties are initialized with
                // their XML value.
                propertiesManager.forEachSuperXmlElement(element -> initializeLocalVariable(methodBlock, element, settings));
                propertiesManager.forEachXmlElement(element -> initializeLocalVariable(methodBlock, element, settings));
            } else {
                Consumer<ClientModelProperty> initializeLocalVariable = property ->
                    initializeLocalVariable(methodBlock, property, settings);
                propertiesManager.forEachSuperRequiredProperty(property -> {
                    if (property.isConstant()) {
                        // Constants are never deserialized.
                        return;
                    }
                    initializeLocalVariable.accept(property);
                });
                propertiesManager.forEachSuperSetterProperty(initializeLocalVariable);
                propertiesManager.forEachRequiredProperty(property -> {
                    if (property.isConstant()) {
                        // Constants are never deserialized.
                        return;
                    }
                    initializeLocalVariable.accept(property);
                });
                propertiesManager.forEachSetterProperty(initializeLocalVariable);
            }
        } else {
            String modelName = propertiesManager.getModel().getName();
            methodBlock.line(modelName + " " + propertiesManager.getDeserializedModelName() + " = new "
                + modelName + "();");
        }

        if (propertiesManager.getAdditionalProperties() != null) {
            initializeLocalVariable(methodBlock, propertiesManager.getAdditionalProperties(), settings);
        }
    }

    private static void initializeLocalVariable(JavaBlock methodBlock, ClientModelProperty property,
        JavaSettings settings) {
        if (includePropertyInConstructor(property, settings) && !settings.isDisableRequiredJsonAnnotation()) {
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
     * @param isXml Whether the reader while loop is for XML reading.
     * @param whileBlock The consumer that adds deserialization logic into the while loop.
     */
    private static void addReaderWhileLoop(JavaBlock methodBlock, boolean initializeFieldNameVariable,
        String fieldNameVariableName, boolean isXml, Consumer<JavaBlock> whileBlock) {
        String whileCheck = isXml
            ? "reader.nextElement() != XmlToken.END_ELEMENT"
            : "reader.nextToken() != JsonToken.END_OBJECT";

        methodBlock.block("while (" + whileCheck + ")", whileAction -> {
            String fieldNameInitialization = "";
            if (initializeFieldNameVariable) {
                fieldNameInitialization = isXml ? "QName" : "String";
            }

            methodBlock.line("%s %s = reader.get%sName();", fieldNameInitialization, fieldNameVariableName,
                isXml ? "Element" : "Field");

            if (!isXml) {
                methodBlock.line("reader" + ".nextToken();");
            }
            methodBlock.line("");

            whileBlock.accept(methodBlock);
        });
    }

    private static void handleJsonPropertyDeserialization(ClientModelProperty property, String modelVariableName,
        JavaBlock methodBlock, AtomicReference<JavaIfBlock> ifBlockReference, String fieldNameVariableName,
        boolean fromSuper, boolean hasConstructorArguments, JavaSettings settings) {
        // Property will be handled later by flattened deserialization.
        if (property.getNeedsFlatten()) {
            return;
        }

        JavaIfBlock ifBlock = ifBlockReference.get();
        ifBlock = handleJsonPropertyDeserialization(property, modelVariableName, methodBlock, ifBlock,
            fieldNameVariableName, fromSuper, hasConstructorArguments, settings);

        ifBlockReference.set(ifBlock);
    }

    private static JavaIfBlock handleJsonPropertyDeserialization(ClientModelProperty property, String modelVariableName,
        JavaBlock methodBlock, JavaIfBlock ifBlock, String fieldNameVariableName, boolean fromSuper,
        boolean hasConstructorArguments, JavaSettings settings) {
        String jsonPropertyName = property.getSerializedName();
        if (CoreUtils.isNullOrEmpty(jsonPropertyName)) {
            return ifBlock;
        }

        return ifOrElseIf(methodBlock, ifBlock, "\"" + jsonPropertyName + "\".equals(" + fieldNameVariableName + ")",
            deserializationBlock -> generateJsonDeserializationLogic(deserializationBlock, modelVariableName, property,
                fromSuper, hasConstructorArguments, settings));
    }

    private static void handleFlattenedPropertiesDeserialization(
        JsonFlattenedPropertiesTree flattenedProperties, JavaBlock methodBlock, JavaIfBlock ifBlock,
        ClientModelProperty additionalProperties, String fieldNameVariableName, boolean hasConstructorArguments,
        JavaSettings settings) {
        // The initial call to handle flattened properties is using the base node which is just a holder.
        for (JsonFlattenedPropertiesTree structure : flattenedProperties.getChildrenNodes().values()) {
            handleFlattenedPropertiesDeserializationHelper(structure, methodBlock, ifBlock, additionalProperties,
                fieldNameVariableName, hasConstructorArguments, settings);
        }
    }

    private static JavaIfBlock handleFlattenedPropertiesDeserializationHelper(
        JsonFlattenedPropertiesTree flattenedProperties, JavaBlock methodBlock, JavaIfBlock ifBlock,
        ClientModelProperty additionalProperties, String fieldNameVariableName, boolean hasConstructorArguments,
        JavaSettings settings) {
        ClientModelPropertyWithMetadata propertyWithMetadata = flattenedProperties.getProperty();
        if (propertyWithMetadata != null) {
            String modelVariableName = "deserialized" + propertyWithMetadata.getModel().getName();

            // This is a terminal location, so only need to handle checking for the property name.
            return ifOrElseIf(methodBlock, ifBlock,
                "\"" + flattenedProperties.getNodeName() + "\".equals(" + fieldNameVariableName + ")",
                deserializationBlock -> generateJsonDeserializationLogic(deserializationBlock, modelVariableName,
                    propertyWithMetadata.getProperty(), propertyWithMetadata.isFromSuperClass(),
                    hasConstructorArguments, settings));
        } else {
            // Otherwise this is an intermediate location and a while loop reader needs to be added.
            return ifOrElseIf(methodBlock, ifBlock,
                "\"" + flattenedProperties.getNodeName() + "\".equals(" + fieldNameVariableName + ") && reader.currentToken() == JsonToken.START_OBJECT",
                ifAction -> addReaderWhileLoop(ifAction, false, fieldNameVariableName, false, whileBlock -> {
                    JavaIfBlock innerIfBlock = null;
                    for (JsonFlattenedPropertiesTree structure : flattenedProperties.getChildrenNodes().values()) {
                        innerIfBlock = handleFlattenedPropertiesDeserializationHelper(structure, methodBlock,
                            innerIfBlock, additionalProperties, fieldNameVariableName, hasConstructorArguments,
                            settings);
                    }

                    handleUnknownJsonFieldDeserialization(whileBlock, innerIfBlock, additionalProperties,
                        fieldNameVariableName);
                }));
        }
    }

    private static void generateJsonDeserializationLogic(JavaBlock deserializationBlock, String modelVariableName,
        ClientModelProperty property, boolean fromSuper, boolean hasConstructorArguments, JavaSettings settings) {
        IType wireType = property.getWireType();
        IType clientType = property.getClientType();

        // Attempt to determine whether the wire type is simple deserialization.
        // This is primitives, boxed primitives, a small set of string based models, and other ClientModels.
        String simpleDeserialization = getSimpleJsonDeserialization(wireType, "reader");
        if (wireType == ClassType.RESPONSE_ERROR) {
            // While azure-core hasn't shipped ResponseError implementing JsonSerializable it has special handling.
            if (!hasConstructorArguments) {
                handleSettingDeserializedValue(deserializationBlock, modelVariableName, property,
                    "CoreToCodegenBridgeUtils.responseErrorFromJson(reader)", fromSuper);
            } else {
                deserializationBlock.line(property.getName() + " = CoreToCodegenBridgeUtils.responseErrorFromJson(reader);");
            }
        } else if (simpleDeserialization != null) {
            // Need to convert the wire type to the client type for constructors.
            // Need to convert the wire type to the client type for public setters.
            boolean convertToClientType = (clientType != wireType)
                    && (hasConstructorArguments || (fromSuper && !property.isReadOnly()));
            BiConsumer<String, JavaBlock> simpleDeserializationConsumer = (logic, block) -> {
                if (!hasConstructorArguments) {
                    handleSettingDeserializedValue(block, modelVariableName, property, logic,
                            fromSuper);
                } else {
                    block.line(property.getName() + " = " + logic + ";");
                }
            };

            if (convertToClientType) {
                // If the wire type is nullable don't attempt to call the convert to client type until it's known that
                // a value was deserialized. This protects against cases such as UnixTimeLong where the wire type is
                // Long and the client type of OffsetDateTime. This is converted using Instant.ofEpochMilli(long) which
                // would result in a null if the Long is null, which is already guarded using
                // reader.readNullable(nonNullReader -> Instant.ofEpochMillis(nonNullReader.readLong())) but this itself
                // returns null which would have been passed to OffsetDateTime.ofInstant(Instant, ZoneId) which would
                // have thrown a NullPointerException.
                if (wireType.isNullable()) {
                    // Check if the property is required, if so use a holder name as there will be an existing holder
                    // variable for the value that will be used in the constructor.
                    String holderName = property.isRequired() ? property.getName() + "Holder" : property.getName();
                    deserializationBlock.line(wireType + " " + holderName + " = " + simpleDeserialization + ";");
                    deserializationBlock.ifBlock(holderName + " != null", ifBlock ->
                        simpleDeserializationConsumer.accept(wireType.convertToClientType(holderName), ifBlock));
                } else {
                    simpleDeserializationConsumer.accept(wireType.convertToClientType(simpleDeserialization),
                            deserializationBlock);
                }
            } else {
                simpleDeserializationConsumer.accept(simpleDeserialization, deserializationBlock);
            }
        } else if (wireType == ClassType.OBJECT) {
            if (!hasConstructorArguments) {
                handleSettingDeserializedValue(deserializationBlock, modelVariableName, property,
                    "reader.readUntyped()", fromSuper);
            } else {
                deserializationBlock.line(property.getName() + " = reader.readUntyped();");
            }
        } else if (wireType == ClassType.BINARY_DATA) {
            BiConsumer<String, JavaBlock> binaryDataDeserializationConsumer = (logic, block) -> {
                if (!hasConstructorArguments) {
                    handleSettingDeserializedValue(deserializationBlock, modelVariableName, property,
                        "BinaryData.fromObject(" + logic + ")", fromSuper);
                } else {
                    deserializationBlock.line(property.getName() + " = BinaryData.fromObject(" + logic + ");");
                }
            };

            if (!property.isRequired()) {
                String propertyNameAsObject = property.getName() + "AsObject";
                deserializationBlock.line("Object " + propertyNameAsObject + " = reader.readUntyped();");
                deserializationBlock.ifBlock(propertyNameAsObject + " != null", ifBlock -> {
                    binaryDataDeserializationConsumer.accept(propertyNameAsObject, ifBlock);
                });
            } else {
                binaryDataDeserializationConsumer.accept("reader.readUntyped()", deserializationBlock);
            }
        } else if (wireType instanceof IterableType) {
            if (!hasConstructorArguments) {
                deserializationBlock.text(property.getClientType() + " ");
            }

            deserializationBlock.text(property.getName() + " = ");
            deserializeJsonContainerProperty(deserializationBlock, "readArray", wireType,
                ((IterableType) wireType).getElementType(), ((IterableType) clientType).getElementType(), 0);

            if (!hasConstructorArguments) {
                handleSettingDeserializedValue(deserializationBlock, modelVariableName, property, property.getName(),
                    fromSuper);
            }
        } else if (wireType instanceof MapType) {
            if (!hasConstructorArguments) {
                deserializationBlock.text(property.getClientType() + " ");
            }

            // Assumption is that the key type for the Map is a String. This may not always hold true and when that
            // becomes reality this will need to be reworked to handle that case.
            deserializationBlock.text(property.getName() + " = ");
            deserializeJsonContainerProperty(deserializationBlock, "readMap", wireType,
                ((MapType) wireType).getValueType(), ((MapType) clientType).getValueType(), 0);

            if (!hasConstructorArguments) {
                handleSettingDeserializedValue(deserializationBlock, modelVariableName, property, property.getName(),
                    fromSuper);
            }
        } else {
            // TODO (alzimmer): Resolve this as deserialization logic generation needs to handle all cases.
            throw new RuntimeException("Unknown wire type " + wireType + ". Need to add support for it.");
        }

        // If the property was required, mark it as found.
        if (includePropertyInConstructor(property, settings) && !settings.isDisableRequiredJsonAnnotation()) {
            deserializationBlock.line(property.getName() + "Found = true;");
        }
    }

    /**
     * Helper method to deserialize a container property (such as {@link List} and {@link Map}).
     *
     * @param methodBlock The method handling deserialization.
     * @param utilityMethod The method aiding in the deserialization of the container.
     * @param containerType The container type.
     * @param elementWireType The element type for the container, for a {@link List} this is the element type and for a
     * {@link Map} this is the value type.
     * @param depth Depth of recursion for container types, such as {@code Map<String, List<String>>} would be 0 when
     * {@code Map} is being handled and then 1 when {@code List} is being handled.
     */
    private static void deserializeJsonContainerProperty(JavaBlock methodBlock, String utilityMethod, IType containerType,
        IType elementWireType, IType elementClientType, int depth) {
        String callingReaderName = depth == 0 ? "reader" : "reader" + depth;
        String lambdaReaderName = "reader" + (depth + 1);
        String valueDeserializationMethod = getSimpleJsonDeserialization(elementWireType, lambdaReaderName);
        boolean convertToClientType = (elementClientType != elementWireType);
        boolean useCodeBlockLambda = valueDeserializationMethod != null && elementWireType.isNullable()
            && convertToClientType;

        if (useCodeBlockLambda) {
            methodBlock.line(callingReaderName + "." + utilityMethod + "(" + lambdaReaderName + " -> {");
        } else {
            methodBlock.line(callingReaderName + "." + utilityMethod + "(" + lambdaReaderName + " ->");
        }
        methodBlock.indent(() -> {
            if (elementWireType == ClassType.RESPONSE_ERROR) {
                // While azure-core hasn't shipped ResponseError implementing JsonSerializable it has special handling.
                methodBlock.line("CoreToCodegenBridgeUtils.responseErrorFromJson(" + lambdaReaderName + ")");
            } else if (valueDeserializationMethod != null) {
                if (convertToClientType) {
                    // If the wire type is nullable don't attempt to call the convert to client type until it's known that
                    // a value was deserialized. This protects against cases such as UnixTimeLong where the wire type is
                    // Long and the client type of OffsetDateTime. This is converted using Instant.ofEpochMilli(long) which
                    // would result in a null if the Long is null, which is already guarded using
                    // reader.readNullable(nonNullReader -> Instant.ofEpochMillis(nonNullReader.readLong())) but this itself
                    // returns null which would have been passed to OffsetDateTime.ofInstant(Instant, ZoneId) which would
                    // have thrown a NullPointerException.
                    if (elementWireType.isNullable()) {
                        // Check if the property is required, if so use a holder name as there will be an existing holder
                        // variable for the value that will be used in the constructor.
                        String holderName = lambdaReaderName + "ValueHolder";
                        methodBlock.line(elementWireType + " " + holderName + " = " + valueDeserializationMethod + ";");
                        methodBlock.ifBlock(holderName + " != null",
                            ifBlock -> ifBlock.methodReturn(elementWireType.convertToClientType(holderName)))
                            .elseBlock(elseBlock -> elseBlock.methodReturn("null"));
                    } else {
                        methodBlock.line(elementWireType.convertToClientType(valueDeserializationMethod));
                    }
                } else {
                    methodBlock.line(valueDeserializationMethod);
                }
            } else if (elementWireType == ClassType.OBJECT) {
                methodBlock.line(lambdaReaderName + ".readUntyped()");
            } else if (elementWireType instanceof IterableType) {
                deserializeJsonContainerProperty(methodBlock, "readArray", elementWireType,
                    ((IterableType) elementWireType).getElementType(),
                    ((IterableType) elementClientType).getElementType(), depth + 1);
            } else if (elementWireType instanceof MapType) {
                // Assumption is that the key type for the Map is a String. This may not always hold true and when that
                // becomes reality this will need to be reworked to handle that case.
                deserializeJsonContainerProperty(methodBlock, "readMap", elementWireType,
                    ((MapType) elementWireType).getValueType(), ((MapType) elementClientType).getValueType(),
                    depth + 1);
            } else {
                throw new RuntimeException("Unknown value type " + elementWireType + " in " + containerType
                    + " serialization. Need to add support for it.");
            }
        });

        if (useCodeBlockLambda) {
            if (depth > 0) {
                methodBlock.line("})");
            } else {
                methodBlock.line("});");
            }
        } else {
            if (depth > 0) {
                methodBlock.line(")");
            } else {
                methodBlock.line(");");
            }
        }
    }

    private static String getSimpleJsonDeserialization(IType wireType, String readerName) {
        return (wireType instanceof ClassType && ((ClassType) wireType).isSwaggerType())
            ? wireType + ".fromJson(" + readerName + ")"
            : wireType.jsonDeserializationMethod(readerName);
    }

    private static void handleUnknownJsonFieldDeserialization(JavaBlock methodBlock, JavaIfBlock ifBlock,
        ClientModelProperty additionalProperties, String fieldNameVariableName) {
        Consumer<JavaBlock> unknownFieldConsumer = javaBlock -> {
            if (additionalProperties != null) {
                javaBlock.ifBlock(additionalProperties.getName() + " == null",
                    ifAction -> ifAction.line(additionalProperties.getName() + " = new LinkedHashMap<>();"));
                javaBlock.line();

                // Assumption, additional properties is a Map of String-Object
                IType valueType = ((MapType) additionalProperties.getWireType()).getValueType();
                if (valueType == ClassType.OBJECT) {
                    // String fieldName should be a local variable accessible in this spot of code.
                    javaBlock.line(additionalProperties.getName() + ".put(" + fieldNameVariableName + ", reader.readUntyped());");
                } else {
                    // Another assumption, the additional properties value type is simple.
                    javaBlock.line(additionalProperties.getName() + ".put(" + fieldNameVariableName + ", "
                        + getSimpleJsonDeserialization(valueType, "reader") + ");");
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
     */
    private static void handleReadReturn(JavaBlock methodBlock, String modelName,
        ClientModelPropertiesManager propertiesManager, boolean isXml, JavaSettings settings) {
        StringBuilder constructorArgs = new StringBuilder();

        propertiesManager.forEachSuperConstructorProperty(arg -> addConstructorParameter(constructorArgs, arg.getName()));
        propertiesManager.forEachConstructorProperty(arg -> addConstructorParameter(constructorArgs, arg.getName()));

        // If there are required properties of any type we must check that all required fields were found.
        if (propertiesManager.hasRequiredProperties()) {
            StringBuilder ifStatementBuilder = new StringBuilder();
            propertiesManager.forEachSuperRequiredProperty(property -> addRequiredCheck(ifStatementBuilder, property, settings));
            propertiesManager.forEachRequiredProperty(property -> addRequiredCheck(ifStatementBuilder, property, settings));

            if (ifStatementBuilder.length() > 0) {
                methodBlock.ifBlock(ifStatementBuilder.toString(), ifAction ->
                    createObjectAndReturn(methodBlock, modelName, constructorArgs.toString(), propertiesManager, isXml));

                if (propertiesManager.getRequiredPropertiesCount() == 1) {
                    StringBuilder stringBuilder = new StringBuilder();
                    propertiesManager.forEachSuperRequiredProperty(property -> stringBuilder.append(property.getSerializedName()));
                    propertiesManager.forEachRequiredProperty(property -> stringBuilder.append(property.getSerializedName()));
                    methodBlock.line("throw new IllegalStateException(\"Missing required property: " + stringBuilder + "\");");
                } else {
                    methodBlock.line("List<String> missingProperties = new ArrayList<>();");
                    propertiesManager.forEachSuperRequiredProperty(property -> addFoundValidationIfCheck(methodBlock, property, settings));
                    propertiesManager.forEachRequiredProperty(property -> addFoundValidationIfCheck(methodBlock, property, settings));

                    methodBlock.line();
                    methodBlock.line("throw new IllegalStateException(\"Missing required property/properties: \" + String.join(\", \", missingProperties));");
                }
            } else {
                createObjectAndReturn(methodBlock, modelName, constructorArgs.toString(), propertiesManager, isXml);
            }
        } else {
            createObjectAndReturn(methodBlock, modelName, constructorArgs.toString(), propertiesManager, isXml);
        }
    }

    private static void createObjectAndReturn(JavaBlock methodBlock, String modelName, String constructorArgs,
        ClientModelPropertiesManager propertiesManager, boolean isXml) {
        if (propertiesManager.hasConstructorArguments()) {
            if (propertiesManager.getSetterPropertiesCount() == 0
                && propertiesManager.getReadOnlyPropertiesCount() == 0
                && propertiesManager.getAdditionalProperties() == null) {
                methodBlock.methodReturn("new " + modelName + "(" + constructorArgs + ")");
                return;
            }

            methodBlock.line(modelName + " " + propertiesManager.getDeserializedModelName() + " = new " + modelName
                + "(" + constructorArgs + ");");

            BiConsumer<ClientModelProperty, Boolean> handleSettingDeserializedValue = (property, fromSuper) ->
                handleSettingDeserializedValue(methodBlock, propertiesManager.getDeserializedModelName(), property,
                    property.getName(), fromSuper);

            propertiesManager.forEachSuperReadOnlyProperty(property -> handleSettingDeserializedValue.accept(property, true));
            propertiesManager.forEachSuperSetterProperty(property -> handleSettingDeserializedValue.accept(property, true));
            propertiesManager.forEachReadOnlyProperty(property -> handleSettingDeserializedValue.accept(property, false));
            propertiesManager.forEachSetterProperty(property -> handleSettingDeserializedValue.accept(property, false));
        }

        if (propertiesManager.getAdditionalProperties() != null) {
            handleSettingDeserializedValue(methodBlock, propertiesManager.getDeserializedModelName(),
                propertiesManager.getAdditionalProperties(), propertiesManager.getAdditionalProperties().getName(),
                false);
        }

        methodBlock.line();
        methodBlock.methodReturn(propertiesManager.getDeserializedModelName());
    }

    private static void addConstructorParameter(StringBuilder constructor, String parameterName) {
        if (constructor.length() > 0) {
            constructor.append(", ");
        }

        constructor.append(parameterName);
    }

    private static void addRequiredCheck(StringBuilder ifCheck, ClientModelProperty property, JavaSettings settings) {
        // XML attributes and text don't need checks.
        if (property.isXmlAttribute() || property.isXmlText() || !includePropertyInConstructor(property, settings)) {
            return;
        }

        // Constants are ignored during deserialization.
        if (property.isConstant()) {
            return;
        }

        // Required properties aren't being validated for being found.
        if (settings.isDisableRequiredJsonAnnotation()) {
            return;
        }

        if (ifCheck.length() > 0) {
            ifCheck.append(" && ");
        }

        ifCheck.append(property.getName()).append("Found");
    }

    private static void addFoundValidationIfCheck(JavaBlock methodBlock, ClientModelProperty property,
        JavaSettings settings) {
        // XML attributes and text don't need checks.
        if (property.isXmlAttribute() || property.isXmlText() || !includePropertyInConstructor(property, settings)) {
            return;
        }

        // Constants are ignored during deserialization.
        if (property.isConstant()) {
            return;
        }

        // Required properties aren't being validated for being found.
        if (settings.isDisableRequiredJsonAnnotation()) {
            return;
        }

        methodBlock.ifBlock("!" + property.getName() + "Found",
            ifAction -> ifAction.line("missingProperties.add(\"" + property.getSerializedName() + "\");"));
    }

    private static void handleSettingDeserializedValue(JavaBlock methodBlock, String modelVariableName,
        ClientModelProperty property, String value, boolean fromSuper) {
        // If the property is defined in a super class use the setter as this will be able to set the value in the
        // super class.
        if (fromSuper) {
            methodBlock.line(modelVariableName + "." + property.getSetterName() + "(" + value + ");");
        } else {
            methodBlock.line(modelVariableName + "." + property.getName() + " = " + value + ";");
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
        classBlock.publicMethod("XmlWriter toXml(XmlWriter xmlWriter) throws XMLStreamException",
            methodBlock -> methodBlock.methodReturn("toXml(xmlWriter, null)"));

        classBlock.annotation("Override");
        classBlock.publicMethod("XmlWriter toXml(XmlWriter xmlWriter, String rootElementName) throws XMLStreamException", methodBlock -> {
            String modelXmlName = propertiesManager.getXmlRootElementName();
            methodBlock.line("rootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? \"" + modelXmlName + "\" : rootElementName;");
            methodBlock.line("xmlWriter.writeStartElement(rootElementName);");

            String modelXmlNamespace = propertiesManager.getXmlRootElementNamespace();
            if (modelXmlNamespace != null) {
                methodBlock.line("xmlWriter.writeNamespace(\"" + modelXmlNamespace + "\");");
            }

            propertiesManager.forEachXmlNamespaceWithPrefix((prefix, namespace) ->
                methodBlock.line("xmlWriter.writeNamespace(\"" + prefix + "\", \"" + namespace + "\");"));

            String modelName = propertiesManager.getModel().getName();
            propertiesManager.forEachSuperXmlAttribute(property -> serializeXml(methodBlock, property, true, modelName));
            propertiesManager.forEachXmlAttribute(property -> serializeXml(methodBlock, property, false, modelName));

            // Valid XML should only either have elements or text.
            if (propertiesManager.hasXmlElements()) {
                propertiesManager.forEachSuperXmlElement(property -> serializeXml(methodBlock, property, true, modelName));
                propertiesManager.forEachXmlElement(property -> serializeXml(methodBlock, property, false, modelName));
            } else {
                propertiesManager.forEachSuperXmlText(property -> serializeXml(methodBlock, property, true, modelName));
                propertiesManager.forEachXmlText(property -> serializeXml(methodBlock, property, false, modelName));
            }

            methodBlock.methodReturn("xmlWriter.writeEndElement()");
        });
    }

    /**
     * Serializes an XML element.
     *
     * @param methodBlock The method handling serialization.
     * @param element The XML element being serialized.
     * @param fromSuperType Whether the property is defined in the super type.
     */
    private static void serializeXml(JavaBlock methodBlock, ClientModelProperty element, boolean fromSuperType,
        String modelName) {
        IType clientType = element.getClientType();
        IType wireType = element.getWireType();
        String propertyValueGetter;
        if (element.isPolymorphicDiscriminator()) {
            // The most super type won't have a value for the polymorphic discriminator, use the model name.
            if (CoreUtils.isNullOrEmpty(element.getDefaultValue())) {
                propertyValueGetter = "\"" + modelName + "\"";
            } else {
                propertyValueGetter = element.getDefaultValue();
            }
        } else if (fromSuperType) {
            propertyValueGetter = (clientType != wireType)
                    ? wireType.convertFromClientType(element.getGetterName() + "()")
                    : element.getGetterName() + "()";
        } else {
            propertyValueGetter = "this." + element.getName();
        }

        // Attempt to determine whether the wire type is simple serialization.
        // This is primitives, boxed primitives, a small set of string based models, and other ClientModels.
        String xmlSerializationMethodCall = wireType.xmlSerializationMethodCall("xmlWriter", element.getXmlName(),
            element.getXmlNamespace(), propertyValueGetter, element.isXmlAttribute(), false);
        if (xmlSerializationMethodCall != null) {
            Consumer<JavaBlock> serializationLogic = javaBlock -> {
                // XML text has special handling.
                if (element.isXmlText()) {
                    javaBlock.line("xmlWriter.writeString(" + propertyValueGetter + ");");
                } else {
                    javaBlock.line(xmlSerializationMethodCall + ";");
                }
            };

            // If the property is from a super type and the client type is different from the wire type then a null
            // check is required to prevent a NullPointerException when converting the value.
            if (fromSuperType && clientType != wireType && clientType.isNullable()) {
                methodBlock.ifBlock(propertyValueGetter + " != null", serializationLogic);
            } else {
                serializationLogic.accept(methodBlock);
            }
        } else if (wireType instanceof ClassType && ((ClassType) wireType).isSwaggerType()) {
            methodBlock.line("xmlWriter.writeXml(" + propertyValueGetter + ");");
        } else if (wireType instanceof IterableType) {
            IType elementType = ((IterableType) wireType).getElementType();
            boolean sameNames = Objects.equals(element.getXmlName(), element.getXmlListElementName());

            methodBlock.ifBlock(propertyValueGetter + " != null", ifAction -> {
                if (!sameNames) {
                    String writeStartElement = element.getXmlNamespace() == null
                        ? "xmlWriter.writeStartElement(\"" + element.getXmlName() + "\");"
                        : "xmlWriter.writeStartElement(\"" + element.getXmlNamespace() + "\", \"" + element.getXmlName() + "\");";
                    ifAction.line(writeStartElement);
                }

                String xmlWrite = elementType.xmlSerializationMethodCall("xmlWriter", element.getXmlListElementName(),
                    element.getXmlListElementNamespace(), "element", false, false);
                ifAction.line("for (%s element : %s) {", elementType, propertyValueGetter);
                ifAction.indent(() -> ifAction.line(xmlWrite + ";"));
                ifAction.line("}");

                if (!sameNames) {
                    ifAction.line("xmlWriter.writeEndElement();");
                }
            });
        } else if (wireType instanceof MapType) {
            // Assumption is that the key type for the Map is a String. This may not always hold true and when that
            // becomes reality this will need to be reworked to handle that case.
            IType valueType = ((MapType) wireType).getValueType();

            methodBlock.ifBlock(propertyValueGetter + " != null", ifAction -> {
                ifAction.line("xmlWriter.writeStartElement(\"" + element.getXmlName() + "\");");

                if (valueType instanceof ClassType && ((ClassType) valueType).isSwaggerType()) {
                    String writeStartElement = (element.getXmlNamespace() != null)
                        ? "xmlWriter.writeStartElement(\"" + element.getXmlNamespace() + "\", key);"
                        : "xmlWriter.writeStartElement(key);";

                    ifAction.line("for (Map.Entry<String, %s> entry : %s.entrySet()) {", valueType, propertyValueGetter);
                    ifAction.indent(() -> {
                        ifAction.line(writeStartElement);
                        ifAction.line("xmlWriter.writeXml(entry.getValue());");
                        ifAction.line("xmlWriter.writeEndElement();");
                    });
                    ifAction.line("}");
                } else {
                    String xmlWrite = valueType.xmlSerializationMethodCall("xmlWriter", "entry.getKey()",
                        element.getXmlNamespace(), "entry.getValue()", false, true);

                    ifAction.line("for (Map.Entry<String, %s> entry : %s.entrySet()) {", valueType, propertyValueGetter);
                    ifAction.indent(() -> ifAction.line(xmlWrite + ";"));
                    ifAction.line("}");
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
        if (isSuperTypeWithDiscriminator(model)) {
            writeSuperTypeFromXml(classBlock, model, propertiesManager, settings);
        } else {
            writeTerminalTypeFromXml(classBlock, propertiesManager, settings);
        }
    }

    /**
     * Writes a super type's {@code fromXml(XmlReader)} method.
     *
     * @param classBlock The class having {@code fromXml(XmlReader)} written to it.
     * @param model The Autorest representation of the model.
     * @param propertiesManager The properties for the model.
     * @param settings The Autorest generation settings.
     */
    private static void writeSuperTypeFromXml(JavaClass classBlock, ClientModel model,
        ClientModelPropertiesManager propertiesManager, JavaSettings settings) {
        // Handling polymorphic fields while determining which subclass, or the class itself, to deserialize handles the
        // discriminator type always as a String. This is permissible as the found discriminator is never being used in
        // a setter or for setting a field, unlike in the actual deserialization method where it needs to be the same
        // type as the field.
        ClientModelProperty discriminatorProperty = propertiesManager.getDiscriminatorProperty();
        readXmlObject(classBlock, propertiesManager, false, methodBlock -> {
            // Assumption for now for XML, only XML properties are used for handling inheritance.
            // If this found to be wrong in the future copy the concept of bufferObject and resettable from azure-json
            // into azure-xml as bufferElement and resettable.
            methodBlock.line("// Get the XML discriminator attribute.");
            if (discriminatorProperty.getXmlNamespace() != null) {
                methodBlock.line("String discriminatorValue = reader.getStringAttribute("
                    + "\"" + discriminatorProperty.getXmlNamespace() + "\", "
                    + "\"" + discriminatorProperty.getSerializedName() + "\");");
            } else {
                methodBlock.line("String discriminatorValue = reader.getStringAttribute("
                    + "\"" + discriminatorProperty.getSerializedName() + "\");");
            }

            methodBlock.line("// Use the discriminator value to determine which subtype should be deserialized.");

            // Add a throw statement if the discriminator value didn't match anything known.
            StringBuilder exceptionMessage = new StringBuilder("Discriminator field '")
                .append(discriminatorProperty.getSerializedName())
                .append("' didn't match one of the expected values ");

            if (!CoreUtils.isNullOrEmpty(discriminatorProperty.getDefaultValue())) {
                exceptionMessage.append("'").append(propertiesManager.getExpectedDiscriminator()).append("'");
            }

            // Add deserialization for the super type itself.
            String conditionalStatement = "discriminatorValue == null || "
                + "\"" + propertiesManager.getExpectedDiscriminator() + "\".equals(discriminatorValue)";
            JavaIfBlock ifBlock = (CoreUtils.isNullOrEmpty(discriminatorProperty.getDefaultValue())) ? null
                : methodBlock.ifBlock(conditionalStatement,
                ifStatement -> ifStatement.methodReturn("fromXmlKnownDiscriminator(reader, finalRootElementName)"));

            // Add deserialization for all child types.
            List<ClientModel> childTypes = getAllChildTypes(model, new ArrayList<>());
            for (int i = 0; i < childTypes.size(); i++) {
                ClientModel childType = childTypes.get(i);

                ifBlock = ifOrElseIf(methodBlock, ifBlock, "\"" + childType.getSerializedName() + "\".equals(discriminatorValue)",
                    ifStatement -> ifStatement.methodReturn(childType.getName() + (isSuperTypeWithDiscriminator(childType)
                        ? ".fromXmlKnownDiscriminator(reader, finalRootElementName)"
                        : ".fromXml(reader, finalRootElementName)")));

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
            readXmlObject(classBlock, propertiesManager, true,
                methodBlock -> writeFromXmlDeserialization(methodBlock, propertiesManager, settings));
        }
    }

    /**
     * Adds a static method to the class with the signature that handles reading the XML string into the object type.
     * <p>
     * If {@code superTypeReading} is true the method will be package-private and named
     * {@code fromXmlWithKnownDiscriminator} instead of being public and named {@code fromXml}. This is done as super
     * types use their {@code fromXml} method to determine the discriminator value and pass the reader to the specific
     * type being deserialized. The specific type being deserialized may be the super type itself, so it cannot pass to
     * {@code fromXml} as this will be a circular call and if the specific type being deserialized is an intermediate
     * type (a type having both super and subclasses) it will attempt to perform discriminator validation which has
     * already been done.
     *
     * @param classBlock The class where the {@code fromXml} method is being written.
     * @param propertiesManager Properties information about the object being deserialized.
     * @param superTypeReading Whether the object reading is for a super type.
     * @param deserializationBlock Logic for deserializing the object.
     */
    private static void readXmlObject(JavaClass classBlock, ClientModelPropertiesManager propertiesManager,
        boolean superTypeReading, Consumer<JavaBlock> deserializationBlock) {
        JavaVisibility visibility = superTypeReading ? JavaVisibility.PackagePrivate : JavaVisibility.Public;
        String methodName = superTypeReading ? "fromXmlKnownDiscriminator" : "fromXml";

        String modelName = propertiesManager.getModel().getName();
        boolean hasRequiredProperties = propertiesManager.hasRequiredProperties();
        boolean isPolymorphic = propertiesManager.getDiscriminatorProperty() != null;

        if (!superTypeReading) {
            fromXmlJavadoc(classBlock, modelName, false, hasRequiredProperties, isPolymorphic);
            classBlock.publicStaticMethod(modelName + " fromXml(XmlReader xmlReader) throws XMLStreamException",
                methodBlock -> methodBlock.methodReturn("fromXml(xmlReader, null)"));

            fromXmlJavadoc(classBlock, modelName, true, hasRequiredProperties, isPolymorphic);
        } else {
            classBlock.publicStaticMethod(modelName + " fromXmlKnownDiscriminator(XmlReader xmlReader) throws XMLStreamException",
                methodBlock -> methodBlock.methodReturn("fromXmlKnownDiscriminator(xmlReader, null)"));
        }

        classBlock.staticMethod(visibility, modelName + " " + methodName + "(XmlReader xmlReader, String rootElementName) throws XMLStreamException", methodBlock -> {
            // For now, use the basic readObject which will return null if the XmlReader is pointing to JsonToken.NULL.
            //
            // Support for a default value if null will need to be supported and for objects that get their value
            // from a JSON value instead of JSON object or are an array type.
            String requiredElementName = propertiesManager.getXmlRootElementName();
            String requiredNamespace = propertiesManager.getXmlRootElementNamespace();

            methodBlock.line("String finalRootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? "
                + "\"" + requiredElementName + "\" : rootElementName;");
            if (requiredNamespace != null) {
                methodBlock.line("return xmlReader.readObject(\"" + requiredNamespace + "\", finalRootElementName, reader -> {");
            } else {
                methodBlock.line("return xmlReader.readObject(finalRootElementName, reader -> {");
            }

            deserializationBlock.accept(methodBlock);

            methodBlock.line("});");
        });
    }

    private static void fromXmlJavadoc(JavaClass classBlock, String modelName, boolean hasRootElementName,
        boolean hasRequiredProperties, boolean isPolymorphic) {
        classBlock.javadocComment(javadocComment -> {
            javadocComment.description("Reads an instance of " + modelName + " from the XmlReader.");
            javadocComment.param("xmlReader", "The XmlReader being read.");
            if (hasRootElementName) {
                javadocComment.param("rootElementName", "Optional root element name to override the default defined "
                    + "by the model. Used to support cases where the model can deserialize from different root element "
                    + "names.");
            }
            javadocComment.methodReturns("An instance of " + modelName + " if the XmlReader was pointing to an "
                + "instance of it, or null if it was pointing to XML null.");

            // TODO (alzimmer): Make the throws statement more descriptive by including the polymorphic
            //  discriminator property name and the required property names. For now this covers the base functionality.
            String throwsStatement = null;
            if (hasRequiredProperties && isPolymorphic) {
                throwsStatement = "If the deserialized XML object was missing any required properties or the "
                    + "polymorphic discriminator.";
            } else if (hasRequiredProperties) {
                throwsStatement = "If the deserialized XML object was missing any required properties.";
            } else if (isPolymorphic) {
                throwsStatement = "If the deserialized XML object was missing the polymorphic discriminator.";
            }

            if (throwsStatement != null) {
                javadocComment.methodThrows("IllegalStateException", throwsStatement);
            }

            javadocComment.methodThrows("XMLStreamException", "If an error occurs while reading the " + modelName + ".");
        });
    }

    /**
     * Writes a terminal type's {@code fromXml(XmlReader)} method.
     * <p>
     * A terminal type is either a type without polymorphism or is the terminal type in a polymorphic hierarchy.
     *
     * @param classBlock The class having {@code fromXml(XmlReader)} written to it.
     * @param propertiesManager The properties for the model.
     * @param settings The Autorest generation settings.
     */
    private static void writeTerminalTypeFromXml(JavaClass classBlock, ClientModelPropertiesManager propertiesManager,
        JavaSettings settings) {
        readXmlObject(classBlock, propertiesManager, false,
            methodBlock -> writeFromXmlDeserialization(methodBlock, propertiesManager, settings));
    }

    private static void writeFromXmlDeserialization(JavaBlock methodBlock,
        ClientModelPropertiesManager propertiesManager, JavaSettings settings) {

        // Add the deserialization logic.
        methodBlock.indent(() -> {
            // Initialize local variables to track what has been deserialized.
            initializeLocalVariables(methodBlock, propertiesManager, true, settings);

            // Read the XML attribute properties first.
            propertiesManager.forEachSuperXmlAttribute(attribute -> deserializeXmlAttribute(methodBlock, attribute,
                propertiesManager, true));
            propertiesManager.forEachXmlAttribute(attribute -> deserializeXmlAttribute(methodBlock, attribute,
                propertiesManager, false));

            // Read the XML text next.
            propertiesManager.forEachSuperXmlText(text -> deserializeXmlText(methodBlock, text, propertiesManager, true));
            propertiesManager.forEachXmlText(text -> deserializeXmlText(methodBlock, text, propertiesManager, false));

            // Model didn't have any XML elements, return early.
            String fieldNameVariableName = propertiesManager.getXmlReaderNameVariableName();
            if (!propertiesManager.hasXmlElements()) {
                // If the model was attributes only a simplified read loop is needed to ensure the end element token
                // is reached.
                if (!propertiesManager.hasXmlTexts()) {
                    methodBlock.block("while (reader.nextElement() != XmlToken.END_ELEMENT)",
                        whileBlock -> whileBlock.line("reader.skipElement();"));
                }
                return;
            }

            // Add the outermost while loop to read the JSON object.
            addReaderWhileLoop(methodBlock, true, fieldNameVariableName, true, whileBlock -> {
                JavaIfBlock ifBlock = null;

                if (propertiesManager.getDiscriminatorProperty() != null
                    && !propertiesManager.getDiscriminatorProperty().isXmlAttribute()) {
                    ClientModelProperty discriminatorProperty = propertiesManager.getDiscriminatorProperty();
                    String ifStatement = String.format("\"%s\".equals(%s)", propertiesManager.getExpectedDiscriminator(),
                        fieldNameVariableName);

                    ifBlock = methodBlock.ifBlock(ifStatement, ifAction -> {
                        ifAction.line("String %s = reader.getStringElement().getLocalPart();", discriminatorProperty.getName());
                        String ifStatement2 = String.format("!%s.equals(%s)", discriminatorProperty.getDefaultValue(),
                            discriminatorProperty.getName());
                        ifAction.ifBlock(ifStatement2, ifAction2 -> ifAction2.line(
                            "throw new IllegalStateException(\"'%s' was expected to be non-null and equal to '\"%s\"'. "
                                + "The found '%s' was '\" + %s + \"'.\");",
                            discriminatorProperty.getSerializedName(), propertiesManager.getExpectedDiscriminator(),
                            discriminatorProperty.getSerializedName(), discriminatorProperty.getName()));
                    });
                }

                // Loop over all properties and generate their deserialization handling.
                AtomicReference<JavaIfBlock> ifBlockReference = new AtomicReference<>(ifBlock);
                propertiesManager.forEachSuperXmlElement(element -> handleXmlPropertyDeserialization(element,
                    whileBlock, ifBlockReference, fieldNameVariableName, propertiesManager, true, settings));
                propertiesManager.forEachXmlElement(element -> handleXmlPropertyDeserialization(element, whileBlock,
                    ifBlockReference, fieldNameVariableName, propertiesManager, false, settings));

                ifBlock = ifBlockReference.get();

                // All properties have been checked for, add an else block that will either ignore unknown properties
                // or add them into an additional properties bag.
                handleUnknownXmlFieldDeserialization(whileBlock, ifBlock, propertiesManager.getAdditionalProperties(),
                    propertiesManager.getXmlReaderNameVariableName());
            });
        });

        // Add the validation and return logic.
        handleReadReturn(methodBlock, propertiesManager.getModel().getName(), propertiesManager, true, settings);
    }

    private static void deserializeXmlAttribute(JavaBlock methodBlock, ClientModelProperty attribute,
        ClientModelPropertiesManager propertiesManager, boolean fromSuper) {
        String xmlAttributeDeserialization = getSimpleXmlDeserialization(attribute.getWireType(), "reader", null,
                attribute.getXmlName(), attribute.getXmlNamespace());

        if (attribute.isPolymorphicDiscriminator()) {
            methodBlock.line("String discriminatorValue = " + xmlAttributeDeserialization + ";");
            String ifStatement = String.format("!%s.equals(discriminatorValue)", attribute.getDefaultValue());
            methodBlock.ifBlock(ifStatement, ifAction2 -> ifAction2.line(
                "throw new IllegalStateException(\"'%s' was expected to be non-null and equal to '%s'. "
                    + "The found '%s' was '\" + discriminatorValue + \"'.\");",
                attribute.getSerializedName(), propertiesManager.getExpectedDiscriminator(),
                attribute.getSerializedName()));
            return;
        }

        if (propertiesManager.hasConstructorArguments()) {
            methodBlock.line("%s %s = %s;", attribute.getClientType(), attribute.getName(), xmlAttributeDeserialization);
        } else {
            handleSettingDeserializedValue(methodBlock, propertiesManager.getDeserializedModelName(), attribute,
                xmlAttributeDeserialization, fromSuper);
        }
    }

    private static void deserializeXmlText(JavaBlock methodBlock, ClientModelProperty text,
        ClientModelPropertiesManager propertiesManager, boolean fromSuper) {
        String xmlTextDeserialization = getSimpleXmlDeserialization(text.getWireType(), "reader", null, null, null);
        if (propertiesManager.hasConstructorArguments()) {
            methodBlock.line("%s %s = %s;", text.getClientType(), text.getName(), xmlTextDeserialization);
        } else {
            handleSettingDeserializedValue(methodBlock, propertiesManager.getDeserializedModelName(), text,
                xmlTextDeserialization, fromSuper);
        }
    }

    private static void handleXmlPropertyDeserialization(ClientModelProperty property, JavaBlock methodBlock,
        AtomicReference<JavaIfBlock> ifBlockReference, String fieldNameVariableName,
        ClientModelPropertiesManager propertiesManager, boolean fromSuper, JavaSettings settings) {
        // Property will be handled later by flattened deserialization.
        // XML should never have flattening.
        if (property.getNeedsFlatten()) {
            return;
        }

        JavaIfBlock ifBlock = ifBlockReference.get();
        ifBlock = handleXmlPropertyDeserialization(property, methodBlock, ifBlock, fieldNameVariableName,
            propertiesManager, fromSuper, settings);

        ifBlockReference.set(ifBlock);
    }

    private static JavaIfBlock handleXmlPropertyDeserialization(ClientModelProperty property, JavaBlock methodBlock,
        JavaIfBlock ifBlock, String fieldNameVariableName, ClientModelPropertiesManager propertiesManager,
        boolean fromSuper, JavaSettings settings) {
        String xmlElementName = property.getXmlName();
        String xmlNamespace = property.getXmlNamespace();

        if (CoreUtils.isNullOrEmpty(xmlElementName)) {
            return ifBlock;
        }

        String condition = getXmlNameConditional(xmlElementName, xmlNamespace, fieldNameVariableName);
        return ifOrElseIf(methodBlock, ifBlock, condition,
            deserializationBlock -> generateXmlDeserializationLogic(deserializationBlock, property, propertiesManager,
                fromSuper, settings));
    }

    private static void generateXmlDeserializationLogic(JavaBlock deserializationBlock, ClientModelProperty property,
        ClientModelPropertiesManager propertiesManager, boolean fromSuper, JavaSettings settings) {
        IType wireType = property.getWireType();

        // Attempt to determine whether the wire type is simple deserialization.
        // This is primitives, boxed primitives, a small set of string based models, and other ClientModels.
        String simpleDeserialization = getSimpleXmlDeserialization(wireType, "reader", property.getXmlName(), null,
                null);
        if (simpleDeserialization != null) {
            if (propertiesManager.hasConstructorArguments()) {
                deserializationBlock.line(property.getName() + " = " + simpleDeserialization + ";");
            } else {
                handleSettingDeserializedValue(deserializationBlock, propertiesManager.getDeserializedModelName(),
                    property, simpleDeserialization, fromSuper);
            }
        } else if (wireType instanceof IterableType) {
            IType elementType = ((IterableType) wireType).getElementType();
            boolean sameNames = Objects.equals(property.getXmlName(), property.getXmlListElementName());
            String elementDeserialization = getSimpleXmlDeserialization(elementType, "reader",
                sameNames ? property.getXmlName() : property.getXmlListElementName(), null, null);
            String fieldAccess;
            if (propertiesManager.hasConstructorArguments()) {
                // Cases with constructor arguments will have a local variable based on the name of the property.
                fieldAccess = property.getName();
            } else if (fromSuper) {
                // Cases where the property is from the super type will need to access the getter.
                fieldAccess = propertiesManager.getDeserializedModelName() + "." + property.getGetterName() + "()";
            } else {
                // Otherwise access the property directly.
                fieldAccess = propertiesManager.getDeserializedModelName() + "." + property.getName();
            }

            // TODO (alzimmer): Handle nested container types when needed.
            deserializationBlock.ifBlock(fieldAccess + " == null", ifStatement -> {
                if (fromSuper) {
                    ifStatement.line(propertiesManager.getDeserializedModelName() + "." + property.getSetterName()
                        + "(new ArrayList<>());");
                } else {
                    ifStatement.line(fieldAccess + " = new ArrayList<>();");
                }
            });

            if (sameNames) {
                deserializationBlock.line(fieldAccess + ".add(" + elementDeserialization + ");");
            } else {
                deserializationBlock.block("while (reader.nextElement() != XmlToken.END_ELEMENT)", whileBlock -> {
                    whileBlock.line("elementName = reader.getElementName();");
                    String condition = getXmlNameConditional(property.getXmlListElementName(),
                        property.getXmlListElementNamespace(), "elementName");
                    whileBlock.ifBlock(condition, ifBlock -> ifBlock.line(fieldAccess + ".add(" + elementDeserialization + ");"))
                        .elseBlock(elseBlock -> elseBlock.line("reader.skipElement();"));
                });
            }
        } else if (wireType instanceof MapType) {
            IType valueType = ((MapType) wireType).getValueType();
            String fieldAccess = propertiesManager.hasConstructorArguments()
                ? property.getName()
                : propertiesManager.getDeserializedModelName() + "." + property.getName();

            // TODO (alzimmer): Handle nested container types when needed.
            // Assumption is that the key type for the Map is a String. This may not always hold true and when that
            // becomes reality this will need to be reworked to handle that case.
            deserializationBlock.ifBlock(fieldAccess + " == null",
                ifStatement -> ifStatement.line(fieldAccess + " = new LinkedHashMap<>();"));

            String valueDeserialization = getSimpleXmlDeserialization(valueType, "reader", property.getXmlName(), null,
                    null);
            deserializationBlock.block("while (reader.nextElement() != XmlToken.END_ELEMENT)", whileBlock -> whileBlock
                .line(fieldAccess + ".put(reader.getElementName().getLocalPart(), " + valueDeserialization + ");"));
        } else {
            // TODO (alzimmer): Resolve this as deserialization logic generation needs to handle all cases.
            throw new RuntimeException("Unknown wire type " + wireType + ". Need to add support for it.");
        }

        // If the property was required, mark it as found.
        if (includePropertyInConstructor(property, settings) && !settings.isDisableRequiredJsonAnnotation()) {
            deserializationBlock.line(property.getName() + "Found = true;");
        }
    }

    private static void handleUnknownXmlFieldDeserialization(JavaBlock methodBlock, JavaIfBlock ifBlock,
        ClientModelProperty additionalProperties, String fieldNameVariableName) {
        Consumer<JavaBlock> unknownFieldConsumer = javaBlock -> {
            if (additionalProperties != null) {
                javaBlock.ifBlock(additionalProperties.getName() + " == null",
                    ifAction -> ifAction.line(additionalProperties.getName() + " = new LinkedHashMap<>();"));
                javaBlock.line();

                // Assumption, additional properties is a Map of String-Object
                IType valueType = ((MapType) additionalProperties.getWireType()).getValueType();
                if (valueType == ClassType.OBJECT) {
                    // String fieldName should be a local variable accessible in this spot of code.
                    javaBlock.line(additionalProperties.getName() + ".put(" + fieldNameVariableName + ", reader.readUntyped());");
                } else {
                    // Another assumption, the additional properties value type is simple.
                    javaBlock.line(additionalProperties.getName() + ".put(" + fieldNameVariableName + ", "
                        + getSimpleXmlDeserialization(valueType, "reader", null, null, null) + ");");
                }
            } else {
                javaBlock.line("reader.skipElement();");
            }
        };

        if (ifBlock == null) {
            unknownFieldConsumer.accept(methodBlock);
        } else {
            ifBlock.elseBlock(unknownFieldConsumer);
        }
    }

    private static String getSimpleXmlDeserialization(IType wireType, String readerName, String elementName,
                                                      String attributeName, String attributeNamespace) {
        String wireTypeDeserialization = null;
        if (wireType instanceof ClassType && ((ClassType) wireType).isSwaggerType()) {
            wireTypeDeserialization = CoreUtils.isNullOrEmpty(elementName)
                ? wireType + ".fromXml(" + readerName + ")"
                : wireType + ".fromXml(" + readerName + ", \"" + elementName + "\")";
        } else {
            wireTypeDeserialization = wireType.xmlDeserializationMethod(readerName, attributeName, attributeNamespace);
        }

        return wireTypeDeserialization;
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

    private static String getXmlNameConditional(String localPart, String namespace, String elementName) {
        String condition = "\"" + localPart + "\".equals(" + elementName + ".getLocalPart())";
        if (!CoreUtils.isNullOrEmpty(namespace)) {
            condition += " && \"" + namespace + "\".equals(" + elementName + ".getNamespaceURI())";
        }

        return condition;
    }
}
