package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Action model.
 */
@Fluent
public final class Action {
    /*
     * The type of the action.
     */
    @JsonProperty(value = "action_type")
    private ActionType actionType;

    /**
     * Get the actionType property: The type of the action.
     * 
     * @return the actionType value.
     */
    public ActionType getActionType() {
        return this.actionType;
    }

    /**
     * Set the actionType property: The type of the action.
     * 
     * @param actionType the actionType value to set.
     * @return the Action object itself.
     */
    public Action setActionType(ActionType actionType) {
        this.actionType = actionType;
        return this;
    }

    public void validate() {
    }
}
