// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.Map;

/**
 * An Azure Storage container.
 */
@JacksonXmlRootElement(localName = "Container")
@Fluent
public final class Container {
    /*
     * The Name property.
     */
    @Generated
    @JsonProperty(value = "Name", required = true)
    private String name;

    /*
     * Properties of a container
     */
    @Generated
    @JsonProperty(value = "Properties", required = true)
    private ContainerProperties properties;

    /*
     * Dictionary of <string>
     */
    @Generated
    @JsonProperty(value = "Metadata")
    private Map<String, String> metadata;

    /**
     * Creates an instance of Container class.
     */
    @Generated
    public Container() {
    }

    /**
     * Get the name property: The Name property.
     * 
     * @return the name value.
     */
    @Generated
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: The Name property.
     * 
     * @param name the name value to set.
     * @return the Container object itself.
     */
    @Generated
    public Container setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the properties property: Properties of a container.
     * 
     * @return the properties value.
     */
    @Generated
    public ContainerProperties getProperties() {
        return this.properties;
    }

    /**
     * Set the properties property: Properties of a container.
     * 
     * @param properties the properties value to set.
     * @return the Container object itself.
     */
    @Generated
    public Container setProperties(ContainerProperties properties) {
        this.properties = properties;
        return this;
    }

    /**
     * Get the metadata property: Dictionary of &lt;string&gt;.
     * 
     * @return the metadata value.
     */
    @Generated
    public Map<String, String> getMetadata() {
        return this.metadata;
    }

    /**
     * Set the metadata property: Dictionary of &lt;string&gt;.
     * 
     * @param metadata the metadata value to set.
     * @return the Container object itself.
     */
    @Generated
    public Container setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getName() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Missing required property name in model Container"));
        }
        if (getProperties() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Missing required property properties in model Container"));
        } else {
            getProperties().validate();
        }
    }

    private static final ClientLogger LOGGER = new ClientLogger(Container.class);
}
