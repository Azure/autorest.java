package fixtures.azureparametergrouping.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Parameter group. */
@Fluent
public final class ParameterGroupingPostRequiredParameters {
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

    /*
     * Path parameter
     */
    @JsonProperty(value = "path", required = true)
    private String path;

    /*
     * The body property.
     */
    @JsonProperty(value = "body", required = true)
    private int body;

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
     * @return the ParameterGroupingPostRequiredParameters object itself.
     */
    public ParameterGroupingPostRequiredParameters setCustomHeader(String customHeader) {
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
     * @return the ParameterGroupingPostRequiredParameters object itself.
     */
    public ParameterGroupingPostRequiredParameters setQuery(Integer query) {
        this.query = query;
        return this;
    }

    /**
     * Get the path property: Path parameter.
     *
     * @return the path value.
     */
    public String getPath() {
        return this.path;
    }

    /**
     * Set the path property: Path parameter.
     *
     * @param path the path value to set.
     * @return the ParameterGroupingPostRequiredParameters object itself.
     */
    public ParameterGroupingPostRequiredParameters setPath(String path) {
        this.path = path;
        return this;
    }

    /**
     * Get the body property: The body property.
     *
     * @return the body value.
     */
    public int getBody() {
        return this.body;
    }

    /**
     * Set the body property: The body property.
     *
     * @param body the body value to set.
     * @return the ParameterGroupingPostRequiredParameters object itself.
     */
    public ParameterGroupingPostRequiredParameters setBody(int body) {
        this.body = body;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getPath() == null) {
            throw new IllegalArgumentException(
                    "Missing required property path in model ParameterGroupingPostRequiredParameters");
        }
    }
}
