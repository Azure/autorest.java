package fixtures.paging.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The PagingGetMultiplePagesLroOptions model. */
@Fluent
public final class PagingGetMultiplePagesLroOptions {
    /*
     * Sets the maximum number of items to return in the response.
     */
    @JsonProperty(value = "maxresults")
    private Integer maxresults;

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
     * @return the PagingGetMultiplePagesLroOptions object itself.
     */
    public PagingGetMultiplePagesLroOptions setMaxresults(Integer maxresults) {
        this.maxresults = maxresults;
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
     * @return the PagingGetMultiplePagesLroOptions object itself.
     */
    public PagingGetMultiplePagesLroOptions setTimeout(Integer timeout) {
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
