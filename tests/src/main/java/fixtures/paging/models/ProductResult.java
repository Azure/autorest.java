package fixtures.paging.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The ProductResult model.
 */
@Fluent
public final class ProductResult {
    /*
     * The values property.
     */
    @JsonProperty(value = "values")
    private List<Product> values;

    /*
     * The nextLink property.
     */
    @JsonProperty(value = "nextLink")
    private String nextLink;

    /**
     * Get the values property: The values property.
     * 
     * @return the values value.
     */
    public List<Product> getValues() {
        return this.values;
    }

    /**
     * Set the values property: The values property.
     * 
     * @param values the values value to set.
     * @return the ProductResult object itself.
     */
    public ProductResult setValues(List<Product> values) {
        this.values = values;
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
     * @return the ProductResult object itself.
     */
    public ProductResult setNextLink(String nextLink) {
        this.nextLink = nextLink;
        return this;
    }
}
