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
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "shark", defaultImpl = Shark.class)
@JsonTypeName("Shark")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "Sawshark", value = Sawshark.class),
    @JsonSubTypes.Type(name = "Goblinshark", value = Goblinshark.class),
    @JsonSubTypes.Type(name = "Cookiecuttershark", value = Cookiecuttershark.class)
})
@Fluent
public class Shark extends Fish {
    /*
     * The age property.
     */
    @JsonProperty(value = "age")
    private int age;

    /*
     * The birthday property.
     */
    @JsonProperty(value = "birthday", required = true)
    private OffsetDateTime birthday;

    /**
     * Get the age property: The age property.
     * 
     * @return the age value.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Set the age property: The age property.
     * 
     * @param age the age value to set.
     * @return the Shark object itself.
     */
    public Shark setAge(int age) {
        this.age = age;
        return this;
    }

    /**
     * Get the birthday property: The birthday property.
     * 
     * @return the birthday value.
     */
    public OffsetDateTime getBirthday() {
        return this.birthday;
    }

    /**
     * Set the birthday property: The birthday property.
     * 
     * @param birthday the birthday value to set.
     * @return the Shark object itself.
     */
    public Shark setBirthday(OffsetDateTime birthday) {
        this.birthday = birthday;
        return this;
    }
}
