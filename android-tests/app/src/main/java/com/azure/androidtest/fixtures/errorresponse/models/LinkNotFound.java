package com.azure.androidtest.fixtures.errorresponse.models;

import com.azure.android.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The LinkNotFound model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "whatNotFound")
@JsonTypeName("InvalidResourceLink")
@Fluent
public final class LinkNotFound extends NotFoundErrorBase {
    /*
     * The whatSubAddress property.
     */
    @JsonProperty(value = "whatSubAddress")
    private String whatSubAddress;

    /**
     * Get the whatSubAddress property: The whatSubAddress property.
     * 
     * @return the whatSubAddress value.
     */
    public String getWhatSubAddress() {
        return this.whatSubAddress;
    }

    /**
     * Set the whatSubAddress property: The whatSubAddress property.
     * 
     * @param whatSubAddress the whatSubAddress value to set.
     * @return the LinkNotFound object itself.
     */
    public LinkNotFound setWhatSubAddress(String whatSubAddress) {
        this.whatSubAddress = whatSubAddress;
        return this;
    }
}
