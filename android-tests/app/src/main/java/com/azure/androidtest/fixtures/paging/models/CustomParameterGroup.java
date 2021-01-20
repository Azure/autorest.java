package com.azure.androidtest.fixtures.paging.models;

import com.azure.android.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The CustomParameterGroup model.
 */
@Fluent
public final class CustomParameterGroup {
    /*
     * Sets the api version to use.
     */
    @JsonProperty(value = "api_version", required = true)
    private String apiVersion;

    /*
     * Sets the tenant to use.
     */
    @JsonProperty(value = "tenant", required = true)
    private String tenant;

    /**
     * Get the apiVersion property: Sets the api version to use.
     * 
     * @return the apiVersion value.
     */
    public String getApiVersion() {
        return this.apiVersion;
    }

    /**
     * Set the apiVersion property: Sets the api version to use.
     * 
     * @param apiVersion the apiVersion value to set.
     * @return the CustomParameterGroup object itself.
     */
    public CustomParameterGroup setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }

    /**
     * Get the tenant property: Sets the tenant to use.
     * 
     * @return the tenant value.
     */
    public String getTenant() {
        return this.tenant;
    }

    /**
     * Set the tenant property: Sets the tenant to use.
     * 
     * @param tenant the tenant value to set.
     * @return the CustomParameterGroup object itself.
     */
    public CustomParameterGroup setTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }
}
