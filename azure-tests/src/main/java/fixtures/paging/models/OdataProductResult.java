package fixtures.paging.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** The OdataProductResult model. */
@Fluent
public final class OdataProductResult {
    /*
     * The values property.
     */
    @JsonProperty(value = "values")
    private List<Product> values;

    /*
     * The odata.nextLink property.
     */
    @JsonProperty(value = "odata.nextLink")
    private String odataNextLink;

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
     * @return the OdataProductResult object itself.
     */
    public OdataProductResult setValues(List<Product> values) {
        this.values = values;
        return this;
    }

    /**
     * Get the odataNextLink property: The odata.nextLink property.
     *
     * @return the odataNextLink value.
     */
    public String getOdataNextLink() {
        return this.odataNextLink;
    }

    /**
     * Set the odataNextLink property: The odata.nextLink property.
     *
     * @param odataNextLink the odataNextLink value to set.
     * @return the OdataProductResult object itself.
     */
    public OdataProductResult setOdataNextLink(String odataNextLink) {
        this.odataNextLink = odataNextLink;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getValues() != null) {
            getValues().forEach(e -> e.validate());
        }
    }
}
