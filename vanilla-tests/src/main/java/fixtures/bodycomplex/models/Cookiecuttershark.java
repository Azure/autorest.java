package fixtures.bodycomplex.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import java.util.List;

/** The Cookiecuttershark model. */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "fishtype")
@JsonTypeName("cookiecuttershark")
@Immutable
public final class Cookiecuttershark extends Shark {
    /**
     * Creates an instance of Cookiecuttershark class.
     *
     * @param length the length value to set.
     * @param birthday the birthday value to set.
     */
    @JsonCreator
    public Cookiecuttershark(
            @JsonProperty(value = "length", required = true) float length,
            @JsonProperty(value = "birthday", required = true) OffsetDateTime birthday) {
        super(length, birthday);
    }

    /** {@inheritDoc} */
    @Override
    public Cookiecuttershark setAge(Integer age) {
        super.setAge(age);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Cookiecuttershark setSpecies(String species) {
        super.setSpecies(species);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Cookiecuttershark setSiblings(List<Fish> siblings) {
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
