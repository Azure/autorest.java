package petstore;

import com.azure.core.annotation.Fluent;

/** */
@Fluent
public final class Toy {
    /*
     * null
     */
    private Long id;

    /*
     * null
     */
    private Long petId;

    /*
     * null
     */
    private String name;

    /**
     * Get the id property: null.
     *
     * @return the id value.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Set the id property.
     *
     * @param id the id value to set.
     * @return the Toy object itself.
     */
    public Toy setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Get the petId property: null.
     *
     * @return the petId value.
     */
    public Long getPetId() {
        return this.petId;
    }

    /**
     * Set the petId property.
     *
     * @param petId the petId value to set.
     * @return the Toy object itself.
     */
    public Toy setPetId(Long petId) {
        this.petId = petId;
        return this;
    }

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
     * @return the Toy object itself.
     */
    public Toy setName(String name) {
        this.name = name;
        return this;
    }
}
