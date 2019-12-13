package fixtures.paging.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The OdataProductResult model.
 */
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
    private String odatanextLink;

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
     * Get the odatanextLink property: The odata.nextLink property.
     * 
     * @return the odatanextLink value.
     */
    public String getOdatanextLink() {
        return this.odatanextLink;
    }

    /**
     * Set the odatanextLink property: The odata.nextLink property.
     * 
     * @param odatanextLink the odatanextLink value to set.
     * @return the OdataProductResult object itself.
     */
    public OdataProductResult setOdatanextLink(String odatanextLink) {
        this.odatanextLink = odatanextLink;
        return this;
    }
}
