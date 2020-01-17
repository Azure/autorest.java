package fixtures.extensibleenums.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Pet model.
 */
@Fluent
public final class Pet {
    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "name")
    private String name;

    /*
     * Type of Pet
     */
    @JsonProperty(value = "DaysOfWeek")
    private DaysOfWeekExtensibleEnum daysOfWeek;

    /*
     * MISSING·SCHEMA-DESCRIPTION-CHOICE
     */
    @JsonProperty(value = "IntEnum", required = true)
    private IntEnum intEnum;

    /**
     * Get the name property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property.
     * 
     * @param name the name value to set.
     * @return the Pet object itself.
     */
    public Pet setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the daysOfWeek property: Type of Pet.
     * 
     * @return the daysOfWeek value.
     */
    public DaysOfWeekExtensibleEnum getDaysOfWeek() {
        return this.daysOfWeek;
    }

    /**
     * Set the daysOfWeek property: Type of Pet.
     * 
     * @param daysOfWeek the daysOfWeek value to set.
     * @return the Pet object itself.
     */
    public Pet setDaysOfWeek(DaysOfWeekExtensibleEnum daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
        return this;
    }

    /**
     * Get the intEnum property: MISSING·SCHEMA-DESCRIPTION-CHOICE.
     * 
     * @return the intEnum value.
     */
    public IntEnum getIntEnum() {
        return this.intEnum;
    }

    /**
     * Set the intEnum property.
     * 
     * @param intEnum the intEnum value to set.
     * @return the Pet object itself.
     */
    public Pet setIntEnum(IntEnum intEnum) {
        this.intEnum = intEnum;
        return this;
    }

    public void validate() {
        if (getIntEnum() == null) {
            throw new IllegalArgumentException("Missing required property intEnum in model Pet");
        }
    }
}
