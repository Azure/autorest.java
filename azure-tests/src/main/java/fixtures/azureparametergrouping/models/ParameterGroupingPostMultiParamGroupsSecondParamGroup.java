package fixtures.azureparametergrouping.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Parameter group. */
@Fluent
public final class ParameterGroupingPostMultiParamGroupsSecondParamGroup {
    /*
     * The header-two property.
     */
    @JsonProperty(value = "header-two")
    private String headerTwo;

    /*
     * Query parameter with default
     */
    @JsonProperty(value = "query-two")
    private Integer queryTwo;

    /**
     * Get the headerTwo property: The header-two property.
     *
     * @return the headerTwo value.
     */
    public String getHeaderTwo() {
        return this.headerTwo;
    }

    /**
     * Set the headerTwo property: The header-two property.
     *
     * @param headerTwo the headerTwo value to set.
     * @return the ParameterGroupingPostMultiParamGroupsSecondParamGroup object itself.
     */
    public ParameterGroupingPostMultiParamGroupsSecondParamGroup setHeaderTwo(String headerTwo) {
        this.headerTwo = headerTwo;
        return this;
    }

    /**
     * Get the queryTwo property: Query parameter with default.
     *
     * @return the queryTwo value.
     */
    public Integer getQueryTwo() {
        return this.queryTwo;
    }

    /**
     * Set the queryTwo property: Query parameter with default.
     *
     * @param queryTwo the queryTwo value to set.
     * @return the ParameterGroupingPostMultiParamGroupsSecondParamGroup object itself.
     */
    public ParameterGroupingPostMultiParamGroupsSecondParamGroup setQueryTwo(Integer queryTwo) {
        this.queryTwo = queryTwo;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
