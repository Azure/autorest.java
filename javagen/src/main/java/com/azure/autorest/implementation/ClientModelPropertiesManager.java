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
import java.util.regex.Pattern;

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
    private final List<ClientModelProperty> superRequiredProperties;
    private final List<ClientModelProperty> superSetterProperties;
    private final List<ClientModelProperty> requiredProperties;
    private final List<ClientModelProperty> setterProperties;
    private final ClientModelProperty additionalProperties;
    private final ClientModelProperty discriminatorProperty;
    private final String expectedDiscriminator;
    private final JsonFlattenedPropertiesTree jsonFlattenedPropertiesTree;
    private final String jsonReaderFieldNameVariableName;

    private final String xmlReaderNameVariableName;
    private final List<ClientModelProperty> xmlAttributes;
    private final List<ClientModelProperty> xmlElements;

    /**
     * Creates a new instance of {@link ClientModelPropertiesManager}.
     *
     * @param model The {@link ClientModel}.
     */
    public ClientModelPropertiesManager(ClientModel model, JavaSettings settings) {
        boolean requiredFieldsAsConstructorArgs = settings.isRequiredFieldsAsConstructorArgs();

        // The reader name variable needs to be mutable as it may match a property name in the class.
        Set<String> possibleReaderFieldNameVariableNames = new LinkedHashSet<>(Arrays.asList(
            "fieldName", "jsonFieldName", "deserializationFieldName"));
        Set<String> possibleXmlNameVariableNames = new LinkedHashSet<>(Arrays.asList(
            "elementName", "xmlElementName", "deserializationElementName"));
        this.model = model;
        this.expectedDiscriminator = model.getSerializedName();

        Map<String, ClientModelPropertyWithMetadata> flattenedProperties = new LinkedHashMap<>();
        List<ClientModelProperty> superRequiredProperties = new ArrayList<>();
        List<ClientModelProperty> superSetterProperties = new ArrayList<>();
        List<ClientModelProperty> xmlAttributes = new ArrayList<>();
        List<ClientModelProperty> xmlElements = new ArrayList<>();
        for (ClientModelProperty property : ClientModelUtil.getParentProperties(model)) {
            // Ignore additional and discriminator properties.
            if (property.isAdditionalProperties() || property.isPolymorphicDiscriminator()) {
                continue;
            } else if (property.isRequired() && requiredFieldsAsConstructorArgs) {
                superRequiredProperties.add(property);
            } else {
                superSetterProperties.add(property);
            }

            if (property.getNeedsFlatten()) {
                flattenedProperties.put(property.getName(), new ClientModelPropertyWithMetadata(model, property, true));
            }

            possibleReaderFieldNameVariableNames.remove(property.getName());
            possibleXmlNameVariableNames.remove(property.getName());

            if (property.getIsXmlAttribute()) {
                xmlAttributes.add(property);
            } else {
                xmlElements.add(property);
            }
        }

        List<ClientModelProperty> requiredProperties = new ArrayList<>();
        List<ClientModelProperty> setterProperties = new ArrayList<>();
        ClientModelProperty discriminatorProperty = null;
        ClientModelProperty additionalProperties = null;
        for (ClientModelProperty property : model.getProperties()) {
            if (property.isRequired() && requiredFieldsAsConstructorArgs) {
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
                flattenedProperties.put(property.getName(), new ClientModelPropertyWithMetadata(model, property, false));
            }

            possibleReaderFieldNameVariableNames.remove(property.getName());
            possibleXmlNameVariableNames.remove(property.getName());

            if (property.getIsXmlAttribute()) {
                xmlAttributes.add(property);
            } else {
                xmlElements.add(property);
            }
        }

        this.discriminatorProperty = discriminatorProperty;
        this.superRequiredProperties = superRequiredProperties;
        this.superSetterProperties = superSetterProperties;
        this.requiredProperties = requiredProperties;
        this.setterProperties = setterProperties;
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

        this.xmlAttributes = xmlAttributes;
        this.xmlElements = xmlElements;
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
     * Gets the required {@link ClientModelProperty properties} that are defined by super classes of the
     * {@link #getModel() model}.
     *
     * @return The required {@link ClientModelProperty properties} that are defined by super classes of the
     * {@link #getModel() model}.
     */
    public List<ClientModelProperty> getSuperRequiredProperties() {
        return superRequiredProperties;
    }

    /**
     * Gets the non-required {@link ClientModelProperty properties} that are defined by super classes of the
     * {@link #getModel() model}.
     *
     * @return The non-required {@link ClientModelProperty properties} that are defined by super classes of the
     * {@link #getModel() model}.
     */
    public List<ClientModelProperty> getSuperSetterProperties() {
        return superSetterProperties;
    }

    /**
     * Gets the required {@link ClientModelProperty properties} that are defined by the {@link #getModel() model}.
     *
     * @return The required {@link ClientModelProperty properties} that are defined by the {@link #getModel() model}.
     */
    public List<ClientModelProperty> getRequiredProperties() {
        return requiredProperties;
    }

    /**
     * Gets the non-required {@link ClientModelProperty properties} that are defined by the {@link #getModel() model}.
     *
     * @return The non-required {@link ClientModelProperty properties} that are defined by the
     * {@link #getModel() model}.
     */
    public List<ClientModelProperty> getSetterProperties() {
        return setterProperties;
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
     * @return The {@link ClientModelProperty} that defines the discriminator property for polymorphic types, or null
     * if the model isn't a polymorphic type.
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
     * Gets the XML attribute {@link ClientModelProperty properties} for the model.
     * <p>
     * If the model type isn't XML this will return null.
     *
     * @return The XML attribute {@link ClientModelProperty properties} for the model.
     */
    public List<ClientModelProperty> getXmlAttributes() {
        return xmlAttributes;
    }

    /**
     * Gets the XML element {@link ClientModelProperty properties} for the model.
     * <p>
     * If the model type isn't XML this will return null.
     *
     * @return The XML element {@link ClientModelProperty properties} for the model.
     */
    public List<ClientModelProperty> getXmlElements() {
        return xmlElements;
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
