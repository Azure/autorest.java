package fixtures.azureparametergrouping.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Parameter group. */
@Fluent
public final class ParameterGroupingPostReservedWordsParameters {
    /*
     * 'from' is a reserved word. Pass in 'bob' to pass.
     */
    @JsonProperty(value = "from")
    private String from;

    /*
     * 'accept' is a reserved word. Pass in 'yes' to pass.
     */
    @JsonProperty(value = "accept")
    private String accept;

    /**
     * Get the from property: 'from' is a reserved word. Pass in 'bob' to pass.
     *
     * @return the from value.
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Set the from property: 'from' is a reserved word. Pass in 'bob' to pass.
     *
     * @param from the from value to set.
     * @return the ParameterGroupingPostReservedWordsParameters object itself.
     */
    public ParameterGroupingPostReservedWordsParameters setFrom(String from) {
        this.from = from;
        return this;
    }

    /**
     * Get the accept property: 'accept' is a reserved word. Pass in 'yes' to pass.
     *
     * @return the accept value.
     */
    public String getAccept() {
        return this.accept;
    }

    /**
     * Set the accept property: 'accept' is a reserved word. Pass in 'yes' to pass.
     *
     * @param accept the accept value to set.
     * @return the ParameterGroupingPostReservedWordsParameters object itself.
     */
    public ParameterGroupingPostReservedWordsParameters setAccept(String accept) {
        this.accept = accept;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
