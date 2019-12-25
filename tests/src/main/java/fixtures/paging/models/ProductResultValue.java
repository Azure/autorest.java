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
     * MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA
     */
    @JsonProperty(value = "value")
    private List<Product> value;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "nextLink")
    private String nextLink;

    /**
     * Get the value property: MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * 
     * @return the value value.
     */
    public List<Product> getValue() {
        return this.value;
    }

    /**
     * Set the value property: MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * 
     * @param value the value value to set.
     * @return the ProductResultValue object itself.
     */
    public ProductResultValue setValue(List<Product> value) {
        this.value = value;
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
