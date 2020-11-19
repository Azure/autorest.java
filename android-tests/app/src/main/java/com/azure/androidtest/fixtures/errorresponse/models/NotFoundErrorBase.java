package com.azure.androidtest.fixtures.errorresponse.models;

import com.azure.android.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The NotFoundErrorBase model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "whatNotFound", defaultImpl = NotFoundErrorBase.class)
@JsonTypeName("NotFoundErrorBase")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "InvalidResourceLink", value = LinkNotFound.class),
    @JsonSubTypes.Type(name = "AnimalNotFound", value = AnimalNotFound.class)
})
@Fluent
public class NotFoundErrorBase extends BaseError {
    /*
     * The reason property.
     */
    @JsonProperty(value = "reason")
    private String reason;

    /**
     * Get the reason property: The reason property.
     * 
     * @return the reason value.
     */
    public String getReason() {
        return this.reason;
    }

    /**
     * Set the reason property: The reason property.
     * 
     * @param reason the reason value to set.
     * @return the NotFoundErrorBase object itself.
     */
    public NotFoundErrorBase setReason(String reason) {
        this.reason = reason;
        return this;
    }
}
