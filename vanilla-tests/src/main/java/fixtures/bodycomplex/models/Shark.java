package fixtures.bodycomplex.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;

/** The Shark model. */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "fishtype",
        defaultImpl = Shark.class)
@JsonTypeName("shark")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "sawshark", value = Sawshark.class),
    @JsonSubTypes.Type(name = "goblin", value = Goblinshark.class),
    @JsonSubTypes.Type(name = "cookiecuttershark", value = Cookiecuttershark.class)
})
@Fluent
public class Shark extends Fish {
    /*
     * The age property.
     */
    @JsonProperty(value = "age")
    private Integer age;

    /*
     * The birthday property.
     */
    @JsonProperty(value = "birthday", required = true)
    private OffsetDateTime birthday;

    /**
     * Creates an instance of Shark class.
     *
     * @param length the length value to set.
     * @param birthday the birthday value to set.
     */
    @JsonCreator
    public Shark(
            @JsonProperty(value = "length", required = true) float length,
            @JsonProperty(value = "birthday", required = true) OffsetDateTime birthday) {
        super(length);
        this.birthday = birthday;
    }

    /**
     * Get the age property: The age property.
     *
     * @return the age value.
     */
    public Integer getAge() {
        return this.age;
    }

    /**
     * Set the age property: The age property.
     *
     * @param age the age value to set.
     * @return the Shark object itself.
     */
    public Shark setAge(Integer age) {
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
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    @Override
    public void validate() {
        super.validate();
        if (getBirthday() == null) {
            throw new IllegalArgumentException("Missing required property birthday in model Shark");
        }
    }
}
