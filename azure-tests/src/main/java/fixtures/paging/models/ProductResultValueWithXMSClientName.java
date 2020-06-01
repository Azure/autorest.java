package fixtures.paging.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** The ProductResultValueWithXMSClientName model. */
@Fluent
public final class ProductResultValueWithXMSClientName {
    /*
     * The values property.
     */
    @JsonProperty(value = "values")
    private List<Product> indexes;

    /*
     * The nextLink property.
     */
    @JsonProperty(value = "nextLink")
    private String nextLink;

    /**
     * Get the indexes property: The values property.
     *
     * @return the indexes value.
     */
    public List<Product> getIndexes() {
        return this.indexes;
    }

    /**
     * Set the indexes property: The values property.
     *
     * @param indexes the indexes value to set.
     * @return the ProductResultValueWithXMSClientName object itself.
     */
    public ProductResultValueWithXMSClientName setIndexes(List<Product> indexes) {
        this.indexes = indexes;
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
     * @return the ProductResultValueWithXMSClientName object itself.
     */
    public ProductResultValueWithXMSClientName setNextLink(String nextLink) {
        this.nextLink = nextLink;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getIndexes() != null) {
            getIndexes().forEach(e -> e.validate());
        }
    }
}
