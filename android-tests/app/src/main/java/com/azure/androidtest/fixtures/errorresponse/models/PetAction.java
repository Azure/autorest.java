package com.azure.androidtest.fixtures.errorresponse.models;

import com.azure.android.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The PetAction model.
 */
@Fluent
public class PetAction {
    /*
     * action feedback
     */
    @JsonProperty(value = "actionResponse")
    private String actionResponse;

    /**
     * Get the actionResponse property: action feedback.
     * 
     * @return the actionResponse value.
     */
    public String getActionResponse() {
        return this.actionResponse;
    }

    /**
     * Set the actionResponse property: action feedback.
     * 
     * @param actionResponse the actionResponse value to set.
     * @return the PetAction object itself.
     */
    public PetAction setActionResponse(String actionResponse) {
        this.actionResponse = actionResponse;
        return this;
    }
}
