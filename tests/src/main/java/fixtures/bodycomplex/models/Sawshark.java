package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.implementation.util.ImplUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Sawshark model.
 */
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
        return ImplUtils.clone(this.picture);
    }

    /**
     * Set the picture property: The picture property.
     * 
     * @param picture the picture value to set.
     * @return the Sawshark object itself.
     */
    public Sawshark setPicture(byte[] picture) {
        this.picture = ImplUtils.clone(picture);
        return this;
    }
}
