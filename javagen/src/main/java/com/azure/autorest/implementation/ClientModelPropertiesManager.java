// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.implementation;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.core.util.CoreUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.xml.XmlReader;
import com.azure.xml.XmlSerializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.regex.Pattern;

import static com.azure.autorest.util.ClientModelUtil.getClientModel;

/**
 * Manages metadata about properties in a {@link ClientModel} and how they correlate with model class generation.
 * <p>
 * This will bucket all properties, and super type properties, into the buckets of required, optional, and additional
 * properties. In addition to bucketing, each property will be checked if it requires flattening and be used to generate
 * the flattened properties structure for the model.
 * <p>
 * This will also handle getting the discriminator property and the expected value for the field.
 */
public final class ClientModelPropertiesManager {
    private static final Pattern SPLIT_KEY_PATTERN = Pattern.compile("((?<!\\\\))\\.");

    private final ClientModel model;
    private final String deserializedModelName;
    private final boolean hasRequiredProperties;
    private final boolean hasConstructorArguments;
    private final List<ClientModelProperty> superConstructorProperties;
    private final List<ClientModelProperty> superRequiredProperties;
    private final List<ClientModelProperty> superSetterProperties;
    private final List<ClientModelProperty> superReadOnlyProperties;
    private final List<ClientModelProperty> constructorProperties;
    private final List<ClientModelProperty> requiredProperties;
    private final List<ClientModelProperty> setterProperties;
    private final List<ClientModelProperty> readOnlyProperties;
    private final ClientModelProperty additionalProperties;
    private final ClientModelProperty discriminatorProperty;
    private final String expectedDiscriminator;
    private final JsonFlattenedPropertiesTree jsonFlattenedPropertiesTree;
    private final String jsonReaderFieldNameVariableName;

    private final String xmlRootElementName;
    private final boolean hasXmlElements;
    private final boolean hasXmlTexts;
    private final String xmlReaderNameVariableName;
    private final List<ClientModelProperty> superXmlAttributes;
    private final List<ClientModelProperty> xmlAttributes;
    private final List<ClientModelProperty> superXmlTexts;
    private final List<ClientModelProperty> xmlTexts;
    private final List<ClientModelProperty> superXmlElements;
    private final List<ClientModelProperty> xmlElements;
    private final Map<String, String> xmlNameSpaceWithPrefix;

    /**
     * Creates a new instance of {@link ClientModelPropertiesManager}.
     *
     * @param model The {@link ClientModel}.
     */
    public ClientModelPropertiesManager(ClientModel model, JavaSettings settings) {
        // The reader name variable needs to be mutable as it may match a property name in the class.
        Set<String> possibleReaderFieldNameVariableNames = new LinkedHashSet<>(Arrays.asList(
            "fieldName", "jsonFieldName", "deserializationFieldName"));
        Set<String> possibleXmlNameVariableNames = new LinkedHashSet<>(Arrays.asList(
            "elementName", "xmlElementName", "deserializationElementName"));
        this.model = model;
        this.deserializedModelName = "deserialized" + model.getName();
        this.expectedDiscriminator = model.getSerializedName();

        Map<String, ClientModelPropertyWithMetadata> flattenedProperties = new LinkedHashMap<>();
        boolean hasRequiredProperties = false;
        superConstructorProperties = new ArrayList<>();
        superRequiredProperties = new ArrayList<>();
        superSetterProperties = new ArrayList<>();
        superReadOnlyProperties = new ArrayList<>();
        boolean hasXmlElements = false;
        boolean hasXmlTexts = false;
        xmlNameSpaceWithPrefix = new LinkedHashMap<>();
        superXmlAttributes = new ArrayList<>();
        xmlAttributes = new ArrayList<>();
        superXmlTexts = new ArrayList<>();
        xmlTexts = new ArrayList<>();
        superXmlElements = new ArrayList<>();
        xmlElements = new ArrayList<>();

        if (model.isPolymorphic()) {
            ClientModel superTypeModel = model;
            ClientModel parentModel = getClientModel(model.getParentModelName());
            while (parentModel != null) {
                superTypeModel = parentModel;
                parentModel = getClientModel(superTypeModel.getParentModelName());
            }

            xmlRootElementName = superTypeModel.getXmlName();
        } else {
            xmlRootElementName = model.getXmlName();
        }

        for (ClientModelProperty property : ClientModelUtil.getParentProperties(model)) {
            // Ignore additional and discriminator properties.
            if (property.isAdditionalProperties() || property.isPolymorphicDiscriminator()) {
                continue;
            }

            if (property.isRequired()) {
                hasRequiredProperties = true;
                superRequiredProperties.add(property);

                if (ClientModelUtil.includePropertyInConstructor(property, settings)) {
                    superConstructorProperties.add(property);
                } else {
                    superReadOnlyProperties.add(property);
                }
            } else {
                superSetterProperties.add(property);
            }

            if (property.getNeedsFlatten()) {
                flattenedProperties.put(property.getName(), new ClientModelPropertyWithMetadata(model, property, true));
            }

            possibleReaderFieldNameVariableNames.remove(property.getName());
            possibleXmlNameVariableNames.remove(property.getName());

            if (property.isXmlAttribute()) {
                superXmlAttributes.add(property);
            } else if (property.isXmlText()) {
                hasXmlTexts = true;
                superXmlTexts.add(property);
            } else {
                hasXmlElements = true;
                superXmlElements.add(property);
            }

            if (!CoreUtils.isNullOrEmpty(property.getXmlPrefix())) {
                xmlNameSpaceWithPrefix.put(property.getXmlPrefix(), property.getXmlNamespace());
            }
        }

        constructorProperties = new ArrayList<>();
        requiredProperties = new ArrayList<>();
        setterProperties = new ArrayList<>();
        readOnlyProperties = new ArrayList<>();
        ClientModelProperty discriminatorProperty = null;
        ClientModelProperty additionalProperties = null;
        for (ClientModelProperty property : model.getProperties()) {
            if (property.isRequired()) {
                hasRequiredProperties = true;
                requiredProperties.add(property);

                if (ClientModelUtil.includePropertyInConstructor(property, settings)) {
                    constructorProperties.add(property);
                } else {
                    readOnlyProperties.add(property);
                }
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
                flattenedProperties.put(property.getName(), new ClientModelPropertyWithMetadata(model, property, false));
            }

            possibleReaderFieldNameVariableNames.remove(property.getName());
            possibleXmlNameVariableNames.remove(property.getName());

            if (property.isXmlAttribute()) {
                xmlAttributes.add(property);
            } else if (property.isXmlText()) {
                hasXmlTexts = true;
                xmlTexts.add(property);
            } else {
                hasXmlElements = true;
                xmlElements.add(property);
            }

            if (!CoreUtils.isNullOrEmpty(property.getXmlPrefix())) {
                xmlNameSpaceWithPrefix.put(property.getXmlPrefix(), property.getXmlNamespace());
            }
        }

        boolean requiredConstructorProperties = hasRequiredProperties && settings.isRequiredFieldsAsConstructorArgs();
        boolean readOnlyConstructorProperties = settings.isRequiredFieldsAsConstructorArgs()
            && settings.isIncludeReadOnlyInConstructorArgs()
            && (!CoreUtils.isNullOrEmpty(readOnlyProperties) || !CoreUtils.isNullOrEmpty(superReadOnlyProperties));

        this.hasRequiredProperties = hasRequiredProperties;
        this.hasConstructorArguments = requiredConstructorProperties || readOnlyConstructorProperties;
        this.hasXmlElements = hasXmlElements;
        this.hasXmlTexts = hasXmlTexts;
        this.discriminatorProperty = discriminatorProperty;
        this.additionalProperties = additionalProperties;
        this.jsonFlattenedPropertiesTree = getFlattenedPropertiesHierarchy(model.getPolymorphicDiscriminator(),
            flattenedProperties);
        Iterator<String> possibleReaderFieldNameVariableNamesIterator = possibleReaderFieldNameVariableNames.iterator();
        if (possibleReaderFieldNameVariableNamesIterator.hasNext()) {
            this.jsonReaderFieldNameVariableName = possibleReaderFieldNameVariableNamesIterator.next();
        } else {
            throw new IllegalStateException("Model properties exhausted all possible JsonReader field name variables. "
                + "Add additional possible JsonReader field name variables to resolve this issue.");
        }

        Iterator<String> possibleXmlNameVariableNamesIterator = possibleXmlNameVariableNames.iterator();
        if (possibleXmlNameVariableNamesIterator.hasNext()) {
            this.xmlReaderNameVariableName = possibleXmlNameVariableNamesIterator.next();
        } else {
            throw new IllegalStateException("Model properties exhausted all possible XmlReader name variables. "
                + "Add additional possible XmlReader name variables to resolve this issue.");
        }
    }

    /**
     * Gets the {@link ClientModel} that the properties are based on.
     *
     * @return The {@link ClientModel} that the properties are based on.
     */
    public ClientModel getModel() {
        return model;
    }

    /**
     * Gets the name of the variable used when deserializing an instance of the {@link #getModel() model}.
     *
     * @return The name of the variable used during deserialization.
     */
    public String getDeserializedModelName() {
        return deserializedModelName;
    }

    /**
     * Whether the {@link #getModel() model} contains required properties, either directly or through super classes.
     *
     * @return Whether the {@link #getModel() model} contains required properties.
     */
    public boolean hasRequiredProperties() {
        return hasRequiredProperties;
    }

    /**
     * Whether the {@link #getModel() model} has constructor arguments, either directly or required through super
     * classes.
     *
     * @return Whether the {@link #getModel() model} contains constructor arguments.
     */
    public boolean hasConstructorArguments() {
        return hasConstructorArguments;
    }

    /**
     * Consumes each constructor {@link ClientModelProperty property} defined by super classes of the
     * {@link #getModel() model}.
     *
     * @param consumer The {@link ClientModelProperty} consumer.
     */
    public void forEachSuperConstructorProperty(Consumer<ClientModelProperty> consumer) {
        superConstructorProperties.forEach(consumer);
    }

    /**
     * Consumes each required {@link ClientModelProperty property} defined by super classes of the
     * {@link #getModel() model}.
     *
     * @param consumer The {@link ClientModelProperty} consumer.
     */
    public void forEachSuperRequiredProperty(Consumer<ClientModelProperty> consumer) {
        superRequiredProperties.forEach(consumer);
    }

    /**
     * Consumes each non-required {@link ClientModelProperty property} defined by super classes of the
     * {@link #getModel() model}.
     *
     * @param consumer The {@link ClientModelProperty} consumer.
     */
    public void forEachSuperSetterProperty(Consumer<ClientModelProperty> consumer) {
        superSetterProperties.forEach(consumer);
    }

    /**
     * Consumes each read-only {@link ClientModelProperty property} defined by super classes of the
     * {@link #getModel() model}.
     *
     * @param consumer The {@link ClientModelProperty} consumer.
     */
    public void forEachSuperReadOnlyProperty(Consumer<ClientModelProperty> consumer) {
        superReadOnlyProperties.forEach(consumer);
    }

    /**
     * Consumes each constructor {@link ClientModelProperty property} defined by the {@link #getModel() model}.
     *
     * @param consumer The {@link ClientModelProperty} consumer.
     */
    public void forEachConstructorProperty(Consumer<ClientModelProperty> consumer) {
        constructorProperties.forEach(consumer);
    }

    /**
     * Consumes each required {@link ClientModelProperty property} defined by the {@link #getModel() model}.
     *
     * @param consumer The {@link ClientModelProperty} consumer.
     */
    public void forEachRequiredProperty(Consumer<ClientModelProperty> consumer) {
        requiredProperties.forEach(consumer);
    }

    /**
     * Consumes each non-required {@link ClientModelProperty property} defined by the {@link #getModel() model}.
     *
     * @param consumer The {@link ClientModelProperty} consumer.
     */
    public void forEachSetterProperty(Consumer<ClientModelProperty> consumer) {
        setterProperties.forEach(consumer);
    }

    /**
     * Consumes each read-only {@link ClientModelProperty property} defined by the {@link #getModel() model}.
     *
     * @param consumer The {@link ClientModelProperty} consumer.
     */
    public void forEachReadOnlyProperty(Consumer<ClientModelProperty> consumer) {
        readOnlyProperties.forEach(consumer);
    }

    /**
     * Gets the {@link ClientModelProperty} that defines the additional properties property.
     * <p>
     * If the model doesn't contain additional properties this will return null.
     *
     * @return The {@link ClientModelProperty} that defines the additional properties property, or null if the model
     * doesn't define additional properties.
     */
    public ClientModelProperty getAdditionalProperties() {
        return additionalProperties;
    }

    /**
     * Gets the {@link ClientModelProperty} that defines the discriminator property for polymorphic types.
     * <p>
     * If the model isn't polymorphic this will return null.
     *
     * @return The {@link ClientModelProperty} that defines the discriminator property for polymorphic types, or null if
     * the model isn't a polymorphic type.
     */
    public ClientModelProperty getDiscriminatorProperty() {
        return discriminatorProperty;
    }

    /**
     * Gets the expected discriminator value for the polymorphic model.
     * <p>
     * If the model isn't polymorphic this will return null.
     *
     * @return The expected discriminator value for the polymorphic model, or null if the model isn't a polymorphic
     * type.
     */
    public String getExpectedDiscriminator() {
        return expectedDiscriminator;
    }

    /**
     * Gets the JSON flattened properties tree for the model.
     * <p>
     * If the model doesn't contain any JSON flattening this will return null.
     *
     * @return The JSON flattened properties tree for the model, or null if the model doesn't contain any JSON
     * flattening.
     */
    public JsonFlattenedPropertiesTree getJsonFlattenedPropertiesTree() {
        return jsonFlattenedPropertiesTree;
    }

    /**
     * Gets the variable name for {@link JsonReader#getFieldName()} in {@link JsonSerializable#fromJson(JsonReader)}
     * implementations.
     * <p>
     * This is used instead of a static variable name as deserialization maintains holders for required properties which
     * could conflict with the static variable name. The constructor manages determination of the variable name by
     * tracking a set of possible names, if all possible names are exhausted the constructor will throw an exception to
     * indicate more possible names need to be added to support all code generation expectations.
     *
     * @return The variable name that tracks the current JSON field name.
     */
    public String getJsonReaderFieldNameVariableName() {
        return jsonReaderFieldNameVariableName;
    }

    /**
     * Gets the variable name for {@link XmlReader#getElementName()} in {@link XmlSerializable#fromXml(XmlReader)}
     * implementations.
     * <p>
     * This is used instead of a static variable name as deserialization maintains holders for required properties which
     * could conflict with the static variable name. The constructor manages determination of the variable name by
     * tracking a set of possible names, if all possible names are exhausted the constructor will throw an exception to
     * indicate more possible names need to be added to support all code generation expectations.
     *
     * @return The variable name that tracks the current XML name.
     */
    public String getXmlReaderNameVariableName() {
        return xmlReaderNameVariableName;
    }

    /**
     * Gets the default XML root element name for the model.
     * <p>
     * Polymorphism for XML works differently from JSON where the discriminator to determine which type to deserialize
     * is determined by an attribute rather than a special property. This results in the super type and all subtypes
     * using the same root element name determined by the super type.
     *
     * @return The default XML root element name.
     */
    public String getXmlRootElementName() {
        return xmlRootElementName;
    }

    /**
     * Whether the {@link #getModel() model} defines XML elements, XML properties that aren't
     * {@link ClientModelProperty#isXmlAttribute() attributes} or {@link ClientModelProperty#isXmlText() text}.
     *
     * @return Whether the {@link #getModel() model} defines XML elements
     */
    public boolean hasXmlElements() {
        return hasXmlElements;
    }

    /**
     * Whether the {@link #getModel() model} defines XML texts, XML properties that are
     * {@link ClientModelProperty#isXmlText() text}.
     *
     * @return Whether the {@link #getModel() model} defines XML texts
     */
    public boolean hasXmlTexts() {
        return hasXmlTexts;
    }

    /**
     * Consumes each XML namespace that has a prefix.
     *
     * @param consumer XML namespace with prefix consumer.
     */
    public void forEachXmlNamespaceWithPrefix(BiConsumer<String, String> consumer) {
        xmlNameSpaceWithPrefix.forEach(consumer);
    }

    /**
     * Consumes each XML attribute {@link ClientModelProperty property} defined by super classes of the
     * {@link #getModel() model}.
     *
     * @param consumer The {@link ClientModelProperty} consumer.
     */
    public void forEachSuperXmlAttribute(Consumer<ClientModelProperty> consumer) {
        superXmlAttributes.forEach(consumer);
    }

    /**
     * Consumes each XML attribute {@link ClientModelProperty property} defined by the {@link #getModel() model}.
     *
     * @param consumer The {@link ClientModelProperty} consumer.
     */
    public void forEachXmlAttribute(Consumer<ClientModelProperty> consumer) {
        xmlAttributes.forEach(consumer);
    }

    /**
     * Consumes each XML text {@link ClientModelProperty property} defined by super classes of the
     * {@link #getModel() model}.
     *
     * @param consumer The {@link ClientModelProperty} consumer.
     */
    public void forEachSuperXmlText(Consumer<ClientModelProperty> consumer) {
        superXmlTexts.forEach(consumer);
    }

    /**
     * Consumes each XML text {@link ClientModelProperty property} defined by the {@link #getModel() model}.
     *
     * @param consumer The {@link ClientModelProperty} consumer.
     */
    public void forEachXmlText(Consumer<ClientModelProperty> consumer) {
        xmlTexts.forEach(consumer);
    }

    /**
     * Consumes each XML element {@link ClientModelProperty property} defined by super classes of the
     * {@link #getModel() model}.
     *
     * @param consumer The {@link ClientModelProperty} consumer.
     */
    public void forEachSuperXmlElement(Consumer<ClientModelProperty> consumer) {
        superXmlElements.forEach(consumer);
    }

    /**
     * Consumes each XML element {@link ClientModelProperty property} defined by the {@link #getModel() model}.
     *
     * @param consumer The {@link ClientModelProperty} consumer.
     */
    public void forEachXmlElement(Consumer<ClientModelProperty> consumer) {
        xmlElements.forEach(consumer);
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
    private static JsonFlattenedPropertiesTree getFlattenedPropertiesHierarchy(String discriminatorProperty,
        Map<String, ClientModelPropertyWithMetadata> flattenedProperties) {
        JsonFlattenedPropertiesTree structure = JsonFlattenedPropertiesTree.createBaseNode();

        if (!CoreUtils.isNullOrEmpty(discriminatorProperty)) {
            String[] propertyHierarchy = SPLIT_KEY_PATTERN.split(discriminatorProperty);
            if (propertyHierarchy.length > 1) {
                structure = JsonFlattenedPropertiesTree.createBaseNode();

            }
        }

        for (ClientModelPropertyWithMetadata property : flattenedProperties.values()) {
            if (!property.getProperty().getNeedsFlatten()) {
                // Property doesn't need flattening, ignore it.
                continue;
            }

            // Splits the flattened property into the individual properties in the JSON path.
            // For example "im.deeper.flattened" becomes ["im", "deeper", "flattened"].
            String[] propertyHierarchy = SPLIT_KEY_PATTERN.split(property.getProperty().getSerializedName());

            if (propertyHierarchy.length == 1) {
                // Property is marked for flattening but points directly to its JSON path, ignore it.
                continue;
            }

            // Loop over all the property names in the JSON path, either getting or creating that node in the
            // flattened JSON properties structure.
            JsonFlattenedPropertiesTree pointer = structure;
            for (int i = 0; i < propertyHierarchy.length; i++) {
                String nodeName = propertyHierarchy[i];

                // Structure doesn't contain the flattened property.
                if (!pointer.hasChildNode(nodeName)) {
                    // Depending on whether this is the last property in the flattened property either a terminal
                    // or intermediate node will be inserted.
                    JsonFlattenedPropertiesTree newPointer = (i == propertyHierarchy.length - 1)
                        ? JsonFlattenedPropertiesTree.createTerminalNode(nodeName, property)
                        : JsonFlattenedPropertiesTree.createIntermediateNode(nodeName);

                    pointer.addChildNode(newPointer);
                    pointer = newPointer;
                } else {
                    pointer = pointer.getChildNode(nodeName);
                }
            }
        }

        return structure;
    }
}
