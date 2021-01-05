package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.time.OffsetDateTime;

/** A banana. */
@JacksonXmlRootElement(localName = "banana")
@Fluent
public final class Banana {
    /*
     * The name property.
     */
    @JsonProperty(value = "name")
    private String name;

    /*
     * The flavor property.
     */
    @JsonProperty(value = "flavor")
    private String flavor;

    /*
     * The time at which you should reconsider eating this banana
     */
    @JsonProperty(value = "expiration")
    private OffsetDateTime expiration;

    /**
     * Get the name property: The name property.
     *
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: The name property.
     *
     * @param name the name value to set.
     * @return the Banana object itself.
     */
    public Banana setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the flavor property: The flavor property.
     *
     * @return the flavor value.
     */
    public String getFlavor() {
        return this.flavor;
    }

    /**
     * Set the flavor property: The flavor property.
     *
     * @param flavor the flavor value to set.
     * @return the Banana object itself.
     */
    public Banana setFlavor(String flavor) {
        this.flavor = flavor;
        return this;
    }

    /**
     * Get the expiration property: The time at which you should reconsider eating this banana.
     *
     * @return the expiration value.
     */
    public OffsetDateTime getExpiration() {
        return this.expiration;
    }

    /**
     * Set the expiration property: The time at which you should reconsider eating this banana.
     *
     * @param expiration the expiration value to set.
     * @return the Banana object itself.
     */
    public Banana setExpiration(OffsetDateTime expiration) {
        this.expiration = expiration;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
