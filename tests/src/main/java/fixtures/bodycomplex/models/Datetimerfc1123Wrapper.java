package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.DateTimeRfc1123;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

/**
 * The Datetimerfc1123Wrapper model.
 */
@Fluent
public final class Datetimerfc1123Wrapper {
    /*
     * MISSING·SCHEMA-DESCRIPTION-DATETIME
     */
    @JsonProperty(value = "field")
    private DateTimeRfc1123 field;

    /*
     * MISSING·SCHEMA-DESCRIPTION-DATETIME
     */
    @JsonProperty(value = "now")
    private DateTimeRfc1123 now;

    /**
     * Get the field property: MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * 
     * @return the field value.
     */
    public OffsetDateTime getField() {
        if (this.field == null) {
            return null;
        }
        return this.field.getDateTime();
    }

    /**
     * Set the field property.
     * 
     * @param field the field value to set.
     * @return the Datetimerfc1123Wrapper object itself.
     */
    public Datetimerfc1123Wrapper setField(OffsetDateTime field) {
        if (field == null) {
            this.field = null;
        } else {
            this.field = new DateTimeRfc1123(field);
        }
        return this;
    }

    /**
     * Get the now property: MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * 
     * @return the now value.
     */
    public OffsetDateTime getNow() {
        if (this.now == null) {
            return null;
        }
        return this.now.getDateTime();
    }

    /**
     * Set the now property.
     * 
     * @param now the now value to set.
     * @return the Datetimerfc1123Wrapper object itself.
     */
    public Datetimerfc1123Wrapper setNow(OffsetDateTime now) {
        if (now == null) {
            this.now = null;
        } else {
            this.now = new DateTimeRfc1123(now);
        }
        return this;
    }

    public void validate() {
    }
}
