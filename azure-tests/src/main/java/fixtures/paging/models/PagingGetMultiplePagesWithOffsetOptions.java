package fixtures.paging.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Parameter group. */
@Fluent
public final class PagingGetMultiplePagesWithOffsetOptions {
    /*
     * Sets the maximum number of items to return in the response.
     */
    @JsonProperty(value = "maxresults")
    private Integer maxresults;

    /*
     * Offset of return value
     */
    @JsonProperty(value = "offset", required = true)
    private int offset;

    /*
     * Sets the maximum time that the server can spend processing the request,
     * in seconds. The default is 30 seconds.
     */
    @JsonProperty(value = "timeout")
    private Integer timeout;

    /**
     * Get the maxresults property: Sets the maximum number of items to return in the response.
     *
     * @return the maxresults value.
     */
    public Integer getMaxresults() {
        return this.maxresults;
    }

    /**
     * Set the maxresults property: Sets the maximum number of items to return in the response.
     *
     * @param maxresults the maxresults value to set.
     * @return the PagingGetMultiplePagesWithOffsetOptions object itself.
     */
    public PagingGetMultiplePagesWithOffsetOptions setMaxresults(Integer maxresults) {
        this.maxresults = maxresults;
        return this;
    }

    /**
     * Get the offset property: Offset of return value.
     *
     * @return the offset value.
     */
    public int getOffset() {
        return this.offset;
    }

    /**
     * Set the offset property: Offset of return value.
     *
     * @param offset the offset value to set.
     * @return the PagingGetMultiplePagesWithOffsetOptions object itself.
     */
    public PagingGetMultiplePagesWithOffsetOptions setOffset(int offset) {
        this.offset = offset;
        return this;
    }

    /**
     * Get the timeout property: Sets the maximum time that the server can spend processing the request, in seconds. The
     * default is 30 seconds.
     *
     * @return the timeout value.
     */
    public Integer getTimeout() {
        return this.timeout;
    }

    /**
     * Set the timeout property: Sets the maximum time that the server can spend processing the request, in seconds. The
     * default is 30 seconds.
     *
     * @param timeout the timeout value to set.
     * @return the PagingGetMultiplePagesWithOffsetOptions object itself.
     */
    public PagingGetMultiplePagesWithOffsetOptions setTimeout(Integer timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
