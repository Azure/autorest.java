package fixtures.lro.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The LROsPatch200SucceededIgnoreHeadersHeaders model. */
@Fluent
public final class LROsPatch200SucceededIgnoreHeadersHeaders {
    /*
     * The Azure-AsyncOperation property.
     */
    @JsonProperty(value = "Azure-AsyncOperation")
    private String azureAsyncOperation;

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
     * @return the LROsPatch200SucceededIgnoreHeadersHeaders object itself.
     */
    public LROsPatch200SucceededIgnoreHeadersHeaders setAzureAsyncOperation(String azureAsyncOperation) {
        this.azureAsyncOperation = azureAsyncOperation;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
