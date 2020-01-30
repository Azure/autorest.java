package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The Sawshark model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "fishtype")
@JsonTypeName("sawshark")
@Fluent
public final class Sawshark extends Shark {
    /*
     * The picture property.
     */
    @JsonProperty(value = "picture")
    private byte[] picture;

    /**
     * Get the picture property: The picture property.
     * 
     * @return the picture value.
     */
    public byte[] getPicture() {
        return CoreUtils.clone(this.picture);
    }

    /**
     * Set the picture property: The picture property.
     * 
     * @param picture the picture value to set.
     * @return the Sawshark object itself.
     */
    public Sawshark setPicture(byte[] picture) {
        this.picture = CoreUtils.clone(picture);
        return this;
    }

    @Override
    public void validate() {
        super.validate();
    }
}
