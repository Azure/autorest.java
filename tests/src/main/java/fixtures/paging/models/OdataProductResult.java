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
     * MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA
     */
    @JsonProperty(value = "values")
    private List<Product> values;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "odata.nextLink")
    private String odatanextLink;

    /**
     * Get the values property: MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * 
     * @return the values value.
     */
    public List<Product> getValues() {
        return this.values;
    }

    /**
     * Set the values property.
     * 
     * @param values the values value to set.
     * @return the OdataProductResult object itself.
     */
    public OdataProductResult setValues(List<Product> values) {
        this.values = values;
        return this;
    }

    /**
     * Get the odatanextLink property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the odatanextLink value.
     */
    public String getOdatanextLink() {
        return this.odatanextLink;
    }

    /**
     * Set the odatanextLink property.
     * 
     * @param odatanextLink the odatanextLink value to set.
     * @return the OdataProductResult object itself.
     */
    public OdataProductResult setOdatanextLink(String odatanextLink) {
        this.odatanextLink = odatanextLink;
        return this;
    }

    public void validate() {
        if (getValues() != null) {
            getValues().forEach(e -> e.validate());
        }
    }
}
