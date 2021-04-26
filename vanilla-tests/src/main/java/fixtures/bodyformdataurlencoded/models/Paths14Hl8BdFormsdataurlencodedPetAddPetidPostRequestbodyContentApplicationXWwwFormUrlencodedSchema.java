package fixtures.bodyformdataurlencoded.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The Paths14Hl8BdFormsdataurlencodedPetAddPetidPostRequestbodyContentApplicationXWwwFormUrlencodedSchema model. */
@Fluent
public final class Paths14Hl8BdFormsdataurlencodedPetAddPetidPostRequestbodyContentApplicationXWwwFormUrlencodedSchema {
    /*
     * Can take a value of dog, or cat, or fish
     */
    @JsonProperty(value = "pet_type", required = true)
    private PetType petType;

    /*
     * Can take a value of meat, or fish, or plant
     */
    @JsonProperty(value = "pet_food", required = true)
    private PetFood petFood;

    /*
     * How many years is it old?
     */
    @JsonProperty(value = "pet_age", required = true)
    private int petAge;

    /*
     * Updated name of the pet
     */
    @JsonProperty(value = "name")
    private String name;

    /*
     * Updated status of the pet
     */
    @JsonProperty(value = "status")
    private String status;

    /**
     * Get the petType property: Can take a value of dog, or cat, or fish.
     *
     * @return the petType value.
     */
    public PetType getPetType() {
        return this.petType;
    }

    /**
     * Set the petType property: Can take a value of dog, or cat, or fish.
     *
     * @param petType the petType value to set.
     * @return the Paths14Hl8BdFormsdataurlencodedPetAddPetidPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
     *     object itself.
     */
    public Paths14Hl8BdFormsdataurlencodedPetAddPetidPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
            setPetType(PetType petType) {
        this.petType = petType;
        return this;
    }

    /**
     * Get the petFood property: Can take a value of meat, or fish, or plant.
     *
     * @return the petFood value.
     */
    public PetFood getPetFood() {
        return this.petFood;
    }

    /**
     * Set the petFood property: Can take a value of meat, or fish, or plant.
     *
     * @param petFood the petFood value to set.
     * @return the Paths14Hl8BdFormsdataurlencodedPetAddPetidPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
     *     object itself.
     */
    public Paths14Hl8BdFormsdataurlencodedPetAddPetidPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
            setPetFood(PetFood petFood) {
        this.petFood = petFood;
        return this;
    }

    /**
     * Get the petAge property: How many years is it old?.
     *
     * @return the petAge value.
     */
    public int getPetAge() {
        return this.petAge;
    }

    /**
     * Set the petAge property: How many years is it old?.
     *
     * @param petAge the petAge value to set.
     * @return the Paths14Hl8BdFormsdataurlencodedPetAddPetidPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
     *     object itself.
     */
    public Paths14Hl8BdFormsdataurlencodedPetAddPetidPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
            setPetAge(int petAge) {
        this.petAge = petAge;
        return this;
    }

    /**
     * Get the name property: Updated name of the pet.
     *
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: Updated name of the pet.
     *
     * @param name the name value to set.
     * @return the Paths14Hl8BdFormsdataurlencodedPetAddPetidPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
     *     object itself.
     */
    public Paths14Hl8BdFormsdataurlencodedPetAddPetidPostRequestbodyContentApplicationXWwwFormUrlencodedSchema setName(
            String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the status property: Updated status of the pet.
     *
     * @return the status value.
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Set the status property: Updated status of the pet.
     *
     * @param status the status value to set.
     * @return the Paths14Hl8BdFormsdataurlencodedPetAddPetidPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
     *     object itself.
     */
    public Paths14Hl8BdFormsdataurlencodedPetAddPetidPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
            setStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getPetType() == null) {
            throw new IllegalArgumentException(
                    "Missing required property petType in model Paths14Hl8BdFormsdataurlencodedPetAddPetidPostRequestbodyContentApplicationXWwwFormUrlencodedSchema");
        }
        if (getPetFood() == null) {
            throw new IllegalArgumentException(
                    "Missing required property petFood in model Paths14Hl8BdFormsdataurlencodedPetAddPetidPostRequestbodyContentApplicationXWwwFormUrlencodedSchema");
        }
    }
}
