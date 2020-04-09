package fixtures.additionalproperties.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import fixtures.additionalproperties.models.PetAPTrue;

/**
 * The CatAPTrue model.
 */
@Fluent
public final class CatAPTrue extends PetAPTrue {
    /*
     * The friendly property.
     */
    @JsonProperty(value = "friendly")
    private Boolean friendly;

    /**
     * Get the friendly property: The friendly property.
     * 
     * @return the friendly value.
     */
    public Boolean isFriendly() {
        return this.friendly;
    }

    /**
     * Set the friendly property: The friendly property.
     * 
     * @param friendly the friendly value to set.
     * @return the CatAPTrue object itself.
     */
    public CatAPTrue setFriendly(Boolean friendly) {
        this.friendly = friendly;
        return this;
    }

    @Override
    public void validate() {
        super.validate();
    }
}
