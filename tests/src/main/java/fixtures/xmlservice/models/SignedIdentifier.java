package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * The SignedIdentifier model.
 */
@JacksonXmlRootElement(localName = "SignedIdentifier")
@Fluent
public final class SignedIdentifier {
    /*
     * a unique id
     */
    @JsonProperty(value = "Id", required = true)
    private String id;

    /*
     * An Access policy
     */
    @JsonProperty(value = "AccessPolicy", required = true)
    private AccessPolicy accessPolicy;

    /**
     * Get the id property: a unique id.
     * 
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set the id property: a unique id.
     * 
     * @param id the id value to set.
     * @return the SignedIdentifier object itself.
     */
    public SignedIdentifier setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get the accessPolicy property: An Access policy.
     * 
     * @return the accessPolicy value.
     */
    public AccessPolicy getAccessPolicy() {
        return this.accessPolicy;
    }

    /**
     * Set the accessPolicy property: An Access policy.
     * 
     * @param accessPolicy the accessPolicy value to set.
     * @return the SignedIdentifier object itself.
     */
    public SignedIdentifier setAccessPolicy(AccessPolicy accessPolicy) {
        this.accessPolicy = accessPolicy;
        return this;
    }

    public void validate() {
        if (getId() == null) {
            throw new IllegalArgumentException("Missing required property id in model SignedIdentifier");
        }
        if (getAccessPolicy() == null) {
            throw new IllegalArgumentException("Missing required property accessPolicy in model SignedIdentifier");
        } else {
            getAccessPolicy().validate();
        }
    }
}
