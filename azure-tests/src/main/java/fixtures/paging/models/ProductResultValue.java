package fixtures.paging.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The ProductResultValue model.
 */
@Fluent
public final class ProductResultValue {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private List<Product> value;

    /*
     * The nextLink property.
     */
    @JsonProperty(value = "nextLink")
    private String nextLink;

    /**
     * Get the value property: The value property.
     * 
     * @return the value value.
     */
    public List<Product> getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     * 
     * @param value the value value to set.
     * @return the ProductResultValue object itself.
     */
    public ProductResultValue setValue(List<Product> value) {
        this.value = value;
        return this;
    }

    /**
     * Get the nextLink property: The nextLink property.
     * 
     * @return the nextLink value.
     */
    public String getNextLink() {
        return this.nextLink;
    }

    /**
     * Set the nextLink property: The nextLink property.
     * 
     * @param nextLink the nextLink value to set.
     * @return the ProductResultValue object itself.
     */
    public ProductResultValue setNextLink(String nextLink) {
        this.nextLink = nextLink;
        return this;
    }

    public void validate() {
        if (getValue() != null) {
            getValue().forEach(e -> e.validate());
        }
    }
}
