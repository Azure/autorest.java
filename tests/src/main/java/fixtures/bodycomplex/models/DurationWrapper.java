package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Duration;

/**
 * The DurationWrapper model.
 */
@Fluent
public final class DurationWrapper {
    /*
     * MISSING·SCHEMA-DESCRIPTION-DURATION
     */
    @JsonProperty(value = "field")
    private Duration field;

    /**
     * Get the field property: MISSING·SCHEMA-DESCRIPTION-DURATION.
     * 
     * @return the field value.
     */
    public Duration getField() {
        return this.field;
    }

    /**
     * Set the field property.
     * 
     * @param field the field value to set.
     * @return the DurationWrapper object itself.
     */
    public DurationWrapper setField(Duration field) {
        this.field = field;
        return this;
    }

    public void validate() {
    }
}
