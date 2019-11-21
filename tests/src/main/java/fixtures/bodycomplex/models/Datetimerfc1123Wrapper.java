package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

/**
 * The Datetimerfc1123Wrapper model.
 */
@Fluent
public final class Datetimerfc1123Wrapper {
    /*
     * The field property.
     */
    @JsonProperty(value = "field")
    private OffsetDateTime field;

    /*
     * The now property.
     */
    @JsonProperty(value = "now")
    private OffsetDateTime now;

    /**
     * Get the field property: The field property.
     * 
     * @return the field value.
     */
    public OffsetDateTime getField() {
        return this.field;
    }

    /**
     * Set the field property: The field property.
     * 
     * @param field the field value to set.
     * @return the Datetimerfc1123Wrapper object itself.
     */
    public Datetimerfc1123Wrapper setField(OffsetDateTime field) {
        this.field = field;
        return this;
    }

    /**
     * Get the now property: The now property.
     * 
     * @return the now value.
     */
    public OffsetDateTime getNow() {
        return this.now;
    }

    /**
     * Set the now property: The now property.
     * 
     * @param now the now value to set.
     * @return the Datetimerfc1123Wrapper object itself.
     */
    public Datetimerfc1123Wrapper setNow(OffsetDateTime now) {
        this.now = now;
        return this;
    }
}
