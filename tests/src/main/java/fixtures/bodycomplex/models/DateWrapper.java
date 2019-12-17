package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

/**
 * The DateWrapper model.
 */
@Fluent
public final class DateWrapper {
    /*
     * MISSING·SCHEMA-DESCRIPTION-DATE
     */
    @JsonProperty(value = "field")
    private LocalDate field;

    /*
     * MISSING·SCHEMA-DESCRIPTION-DATE
     */
    @JsonProperty(value = "leap")
    private LocalDate leap;

    /**
     * Get the field property: MISSING·SCHEMA-DESCRIPTION-DATE.
     * 
     * @return the field value.
     */
    public LocalDate getField() {
        return this.field;
    }

    /**
     * Set the field property: MISSING·SCHEMA-DESCRIPTION-DATE.
     * 
     * @param field the field value to set.
     * @return the DateWrapper object itself.
     */
    public DateWrapper setField(LocalDate field) {
        this.field = field;
        return this;
    }

    /**
     * Get the leap property: MISSING·SCHEMA-DESCRIPTION-DATE.
     * 
     * @return the leap value.
     */
    public LocalDate getLeap() {
        return this.leap;
    }

    /**
     * Set the leap property: MISSING·SCHEMA-DESCRIPTION-DATE.
     * 
     * @param leap the leap value to set.
     * @return the DateWrapper object itself.
     */
    public DateWrapper setLeap(LocalDate leap) {
        this.leap = leap;
        return this;
    }

    public void validate() {
    }
}
