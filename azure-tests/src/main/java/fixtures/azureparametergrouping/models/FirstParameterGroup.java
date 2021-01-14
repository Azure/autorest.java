package fixtures.azureparametergrouping.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Parameter group. */
@Fluent
public final class FirstParameterGroup {
    /*
     * The header-one property.
     */
    @JsonProperty(value = "header-one")
    private String headerOne;

    /*
     * Query parameter with default
     */
    @JsonProperty(value = "query-one")
    private Integer queryOne;

    /**
     * Get the headerOne property: The header-one property.
     *
     * @return the headerOne value.
     */
    public String getHeaderOne() {
        return this.headerOne;
    }

    /**
     * Set the headerOne property: The header-one property.
     *
     * @param headerOne the headerOne value to set.
     * @return the FirstParameterGroup object itself.
     */
    public FirstParameterGroup setHeaderOne(String headerOne) {
        this.headerOne = headerOne;
        return this;
    }

    /**
     * Get the queryOne property: Query parameter with default.
     *
     * @return the queryOne value.
     */
    public Integer getQueryOne() {
        return this.queryOne;
    }

    /**
     * Set the queryOne property: Query parameter with default.
     *
     * @param queryOne the queryOne value to set.
     * @return the FirstParameterGroup object itself.
     */
    public FirstParameterGroup setQueryOne(Integer queryOne) {
        this.queryOne = queryOne;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
