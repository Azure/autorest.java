package petstore;

import com.azure.core.annotation.Fluent;

/** */
@Fluent
public final class Pet {
    /*
     * null
     */
    private String name;

    /*
     * null
     */
    private String tag;

    /*
     * null
     */
    private Integer age;

    /**
     * Get the name property: null.
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
     * Get the tag property: null.
     *
     * @return the tag value.
     */
    public String getTag() {
        return this.tag;
    }

    /**
     * Set the tag property.
     *
     * @param tag the tag value to set.
     * @return the Pet object itself.
     */
    public Pet setTag(String tag) {
        this.tag = tag;
        return this;
    }

    /**
     * Get the age property: null.
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
     * @return the Pet object itself.
     */
    public Pet setAge(Integer age) {
        this.age = age;
        return this;
    }
}
