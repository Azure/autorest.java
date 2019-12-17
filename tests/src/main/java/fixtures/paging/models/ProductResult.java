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
     * MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA
     */
    @JsonProperty(value = "values")
    private List<Product> values;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "nextLink")
    private String nextLink;

    /**
     * Get the values property: MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * 
     * @return the values value.
     */
    public List<Product> getValues() {
        return this.values;
    }

    /**
     * Set the values property: MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * 
     * @param values the values value to set.
     * @return the ProductResult object itself.
     */
    public ProductResult setValues(List<Product> values) {
        this.values = values;
        return this;
    }

    /**
     * Get the nextLink property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the nextLink value.
     */
    public String getNextLink() {
        return this.nextLink;
    }

    /**
     * Set the nextLink property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param nextLink the nextLink value to set.
     * @return the ProductResult object itself.
     */
    public ProductResult setNextLink(String nextLink) {
        this.nextLink = nextLink;
        return this;
    }

    public void validate() {
        if (getValues() != null) {
            getValues().forEach(e -> e.validate());
        }
    }
}
