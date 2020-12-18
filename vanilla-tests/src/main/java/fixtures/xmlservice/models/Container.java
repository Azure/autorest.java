package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.Map;

/** An Azure Storage container. */
@JacksonXmlRootElement(localName = "Container")
@Fluent
public final class Container {
    /*
     * The Name property.
     */
    @JsonProperty(value = "Name", required = true)
    private String name;

    /*
     * Properties of a container
     */
    @JsonProperty(value = "Properties", required = true)
    private ContainerProperties properties;

    /*
     * Dictionary of <string>
     */
    @JsonProperty(value = "Metadata")
    private Map<String, String> metadata;

    /**
     * Get the name property: The Name property.
     *
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: The Name property.
     *
     * @param name the name value to set.
     * @return the Container object itself.
     */
    public Container setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the properties property: Properties of a container.
     *
     * @return the properties value.
     */
    public ContainerProperties getProperties() {
        return this.properties;
    }

    /**
     * Set the properties property: Properties of a container.
     *
     * @param properties the properties value to set.
     * @return the Container object itself.
     */
    public Container setProperties(ContainerProperties properties) {
        this.properties = properties;
        return this;
    }

    /**
     * Get the metadata property: Dictionary of &lt;string&gt;.
     *
     * @return the metadata value.
     */
    public Map<String, String> getMetadata() {
        return this.metadata;
    }

    /**
     * Set the metadata property: Dictionary of &lt;string&gt;.
     *
     * @param metadata the metadata value to set.
     * @return the Container object itself.
     */
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
            throw new IllegalArgumentException("Missing required property name in model Container");
        }
        if (getProperties() == null) {
            throw new IllegalArgumentException("Missing required property properties in model Container");
        } else {
            getProperties().validate();
        }
    }
}
