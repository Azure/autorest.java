package fixtures.lro.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The LrosaDsPutAsyncRelativeRetryInvalidHeaderHeaders model. */
@Fluent
public final class LrosaDsPutAsyncRelativeRetryInvalidHeaderHeaders {
    /*
     * The Retry-After property.
     */
    @JsonProperty(value = "Retry-After")
    private Integer retryAfter;

    /*
     * The Azure-AsyncOperation property.
     */
    @JsonProperty(value = "Azure-AsyncOperation")
    private String azureAsyncOperation;

    /*
     * The Location property.
     */
    @JsonProperty(value = "Location")
    private String location;

    /**
     * Get the retryAfter property: The Retry-After property.
     *
     * @return the retryAfter value.
     */
    public Integer getRetryAfter() {
        return this.retryAfter;
    }

    /**
     * Set the retryAfter property: The Retry-After property.
     *
     * @param retryAfter the retryAfter value to set.
     * @return the LrosaDsPutAsyncRelativeRetryInvalidHeaderHeaders object itself.
     */
    public LrosaDsPutAsyncRelativeRetryInvalidHeaderHeaders setRetryAfter(Integer retryAfter) {
        this.retryAfter = retryAfter;
        return this;
    }

    /**
     * Get the azureAsyncOperation property: The Azure-AsyncOperation property.
     *
     * @return the azureAsyncOperation value.
     */
    public String getAzureAsyncOperation() {
        return this.azureAsyncOperation;
    }

    /**
     * Set the azureAsyncOperation property: The Azure-AsyncOperation property.
     *
     * @param azureAsyncOperation the azureAsyncOperation value to set.
     * @return the LrosaDsPutAsyncRelativeRetryInvalidHeaderHeaders object itself.
     */
    public LrosaDsPutAsyncRelativeRetryInvalidHeaderHeaders setAzureAsyncOperation(String azureAsyncOperation) {
        this.azureAsyncOperation = azureAsyncOperation;
        return this;
    }

    /**
     * Get the location property: The Location property.
     *
     * @return the location value.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Set the location property: The Location property.
     *
     * @param location the location value to set.
     * @return the LrosaDsPutAsyncRelativeRetryInvalidHeaderHeaders object itself.
     */
    public LrosaDsPutAsyncRelativeRetryInvalidHeaderHeaders setLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
