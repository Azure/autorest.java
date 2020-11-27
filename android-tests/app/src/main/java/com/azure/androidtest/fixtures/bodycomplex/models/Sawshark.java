package com.azure.androidtest.fixtures.bodycomplex.models;

import com.azure.android.core.annotation.Fluent;
import com.azure.android.core.util.CoreUtil;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;
import org.threeten.bp.OffsetDateTime;

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
        return CoreUtil.clone(this.picture);
    }

    /**
     * Set the picture property: The picture property.
     * 
     * @param picture the picture value to set.
     * @return the Sawshark object itself.
     */
    public Sawshark setPicture(byte[] picture) {
        this.picture = CoreUtil.clone(picture);
        return this;
    }
}
