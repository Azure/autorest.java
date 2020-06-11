package fixtures.bodycomplex.models;

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;

/** The Cookiecuttershark model. */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "fishtype")
@JsonTypeName("cookiecuttershark")
@Immutable
public final class Cookiecuttershark extends Shark {
    /** Creates an instance of Cookiecuttershark class. */
    public Cookiecuttershark(float length, OffsetDateTime birthday) {
        super(length, birthday);
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
