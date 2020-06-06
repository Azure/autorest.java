package fixtures.modelflattening.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/** The ResourceCollection model. */
@Fluent
public final class ResourceCollection {
    /*
     * Flattened product.
     */
    @JsonProperty(value = "productresource")
    private FlattenedProduct productresource;

    /*
     * The arrayofresources property.
     */
    @JsonProperty(value = "arrayofresources")
    private List<FlattenedProduct> arrayofresources;

    /*
     * Dictionary of <FlattenedProduct>
     */
    @JsonProperty(value = "dictionaryofresources")
    private Map<String, FlattenedProduct> dictionaryofresources;

    /**
     * Get the productresource property: Flattened product.
     *
     * @return the productresource value.
     */
    public FlattenedProduct getProductresource() {
        return this.productresource;
    }

    /**
     * Set the productresource property: Flattened product.
     *
     * @param productresource the productresource value to set.
     * @return the ResourceCollection object itself.
     */
    public ResourceCollection setProductresource(FlattenedProduct productresource) {
        this.productresource = productresource;
        return this;
    }

    /**
     * Get the arrayofresources property: The arrayofresources property.
     *
     * @return the arrayofresources value.
     */
    public List<FlattenedProduct> getArrayofresources() {
        return this.arrayofresources;
    }

    /**
     * Set the arrayofresources property: The arrayofresources property.
     *
     * @param arrayofresources the arrayofresources value to set.
     * @return the ResourceCollection object itself.
     */
    public ResourceCollection setArrayofresources(List<FlattenedProduct> arrayofresources) {
        this.arrayofresources = arrayofresources;
        return this;
    }

    /**
     * Get the dictionaryofresources property: Dictionary of &lt;FlattenedProduct&gt;.
     *
     * @return the dictionaryofresources value.
     */
    public Map<String, FlattenedProduct> getDictionaryofresources() {
        return this.dictionaryofresources;
    }

    /**
     * Set the dictionaryofresources property: Dictionary of &lt;FlattenedProduct&gt;.
     *
     * @param dictionaryofresources the dictionaryofresources value to set.
     * @return the ResourceCollection object itself.
     */
    public ResourceCollection setDictionaryofresources(Map<String, FlattenedProduct> dictionaryofresources) {
        this.dictionaryofresources = dictionaryofresources;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getProductresource() != null) {
            getProductresource().validate();
        }
        if (getArrayofresources() != null) {
            getArrayofresources().forEach(e -> e.validate());
        }
        if (getDictionaryofresources() != null) {
            getDictionaryofresources()
                    .values()
                    .forEach(
                            e -> {
                                if (e != null) {
                                    e.validate();
                                }
                            });
        }
    }
}
