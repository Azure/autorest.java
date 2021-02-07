package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/** The ModelWithByteProperty model. */
@JacksonXmlRootElement(localName = "ModelWithByteProperty")
@Fluent
public final class ModelWithByteProperty {
    /*
     * The Bytes property.
     */
    @JsonProperty(value = "Bytes")
    private byte[] bytes;

    /**
     * Get the bytes property: The Bytes property.
     *
     * @return the bytes value.
     */
    public byte[] getBytes() {
        return CoreUtils.clone(this.bytes);
    }

    /**
     * Set the bytes property: The Bytes property.
     *
     * @param bytes the bytes value to set.
     * @return the ModelWithByteProperty object itself.
     */
    public ModelWithByteProperty setBytes(byte[] bytes) {
        this.bytes = CoreUtils.clone(bytes);
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
