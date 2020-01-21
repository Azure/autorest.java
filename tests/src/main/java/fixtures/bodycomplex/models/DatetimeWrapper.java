package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

/**
 * The DatetimeWrapper model.
 */
@Fluent
public final class DatetimeWrapper {
    /*
     * MISSING·SCHEMA-DESCRIPTION-DATETIME
     */
    @JsonProperty(value = "field")
    private OffsetDateTime field;

    /*
     * MISSING·SCHEMA-DESCRIPTION-DATETIME
     */
    @JsonProperty(value = "now")
    private OffsetDateTime now;

    /**
     * Get the field property: MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * 
     * @return the field value.
     */
    public OffsetDateTime getField() {
        return this.field;
    }

    /**
     * Set the field property.
     * 
     * @param field the field value to set.
     * @return the DatetimeWrapper object itself.
     */
    public DatetimeWrapper setField(OffsetDateTime field) {
        this.field = field;
        return this;
    }

    /**
     * Get the now property: MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * 
     * @return the now value.
     */
    public OffsetDateTime getNow() {
        return this.now;
    }

    /**
     * Set the now property.
     * 
     * @param now the now value to set.
     * @return the DatetimeWrapper object itself.
     */
    public DatetimeWrapper setNow(OffsetDateTime now) {
        this.now = now;
        return this;
    }

    public void validate() {
    }
}
