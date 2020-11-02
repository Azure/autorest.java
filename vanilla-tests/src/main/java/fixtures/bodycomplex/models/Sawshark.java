package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import java.util.List;

/** The Sawshark model. */
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
     * Creates an instance of Sawshark class.
     *
     * @param length the length value to set.
     * @param birthday the birthday value to set.
     */
    @JsonCreator
    public Sawshark(
            @JsonProperty(value = "length", required = true) float length,
            @JsonProperty(value = "birthday", required = true) OffsetDateTime birthday) {
        super(length, birthday);
    }

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

    /** {@inheritDoc} */
    @Override
    public Sawshark setAge(Integer age) {
        super.setAge(age);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Sawshark setSpecies(String species) {
        super.setSpecies(species);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Sawshark setSiblings(List<Fish> siblings) {
        super.setSiblings(siblings);
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    @Override
    public void validate() {
        super.validate();
    }
}
