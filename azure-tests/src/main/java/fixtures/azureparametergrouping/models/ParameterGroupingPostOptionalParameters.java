package fixtures.azureparametergrouping.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Parameter group. */
@Fluent
public final class ParameterGroupingPostOptionalParameters {
    /*
     * The customHeader property.
     */
    @JsonProperty(value = "customHeader")
    private String customHeader;

    /*
     * Query parameter with default
     */
    @JsonProperty(value = "query")
    private Integer query;

    /**
     * Get the customHeader property: The customHeader property.
     *
     * @return the customHeader value.
     */
    public String getCustomHeader() {
        return this.customHeader;
    }

    /**
     * Set the customHeader property: The customHeader property.
     *
     * @param customHeader the customHeader value to set.
     * @return the ParameterGroupingPostOptionalParameters object itself.
     */
    public ParameterGroupingPostOptionalParameters setCustomHeader(String customHeader) {
        this.customHeader = customHeader;
        return this;
    }

    /**
     * Get the query property: Query parameter with default.
     *
     * @return the query value.
     */
    public Integer getQuery() {
        return this.query;
    }

    /**
     * Set the query property: Query parameter with default.
     *
     * @param query the query value to set.
     * @return the ParameterGroupingPostOptionalParameters object itself.
     */
    public ParameterGroupingPostOptionalParameters setQuery(Integer query) {
        this.query = query;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
