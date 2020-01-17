package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;

/**
 * The Shark model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "fishtype", defaultImpl = Shark.class)
@JsonTypeName("shark")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "sawshark", value = Sawshark.class),
    @JsonSubTypes.Type(name = "goblin", value = Goblinshark.class),
    @JsonSubTypes.Type(name = "cookiecuttershark", value = Cookiecuttershark.class)
})
@Fluent
public class Shark extends Fish {
    /*
     * MISSING·SCHEMA-DESCRIPTION-INTEGER
     */
    @JsonProperty(value = "age")
    private Integer age;

    /*
     * MISSING·SCHEMA-DESCRIPTION-DATETIME
     */
    @JsonProperty(value = "birthday", required = true)
    private OffsetDateTime birthday;

    /**
     * Get the age property: MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * 
     * @return the age value.
     */
    public Integer getAge() {
        return this.age;
    }

    /**
     * Set the age property.
     * 
     * @param age the age value to set.
     * @return the Shark object itself.
     */
    public Shark setAge(Integer age) {
        this.age = age;
        return this;
    }

    /**
     * Get the birthday property: MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * 
     * @return the birthday value.
     */
    public OffsetDateTime getBirthday() {
        return this.birthday;
    }

    /**
     * Set the birthday property.
     * 
     * @param birthday the birthday value to set.
     * @return the Shark object itself.
     */
    public Shark setBirthday(OffsetDateTime birthday) {
        this.birthday = birthday;
        return this;
    }

    @Override
    public void validate() {
        super.validate();
        if (getBirthday() == null) {
            throw new IllegalArgumentException("Missing required property birthday in model Shark");
        }
    }
}
