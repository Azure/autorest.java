package fixtures.validation.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The product documentation. */
@Fluent
public final class ChildProduct {
    /*
     * Constant string
     */
    @JsonProperty(value = "constProperty", required = true)
    private String constProperty;

    /*
     * Count
     */
    @JsonProperty(value = "count")
    private Integer count;

    /** Creates an instance of ChildProduct class. */
    public ChildProduct() {
        constProperty = "constant";
    }

    /**
     * Get the constProperty property: Constant string.
     *
     * @return the constProperty value.
     */
    public String getConstProperty() {
        return this.constProperty;
    }

    /**
     * Set the constProperty property: Constant string.
     *
     * @param constProperty the constProperty value to set.
     * @return the ChildProduct object itself.
     */
    public ChildProduct setConstProperty(String constProperty) {
        this.constProperty = constProperty;
        return this;
    }

    /**
     * Get the count property: Count.
     *
     * @return the count value.
     */
    public Integer getCount() {
        return this.count;
    }

    /**
     * Set the count property: Count.
     *
     * @param count the count value to set.
     * @return the ChildProduct object itself.
     */
    public ChildProduct setCount(Integer count) {
        this.count = count;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
