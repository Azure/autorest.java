package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The LifetimeAction model.
 */
@Fluent
public final class LifetimeAction {
    /*
     * A condition to be satisfied for an action to be executed.
     */
    @JsonProperty(value = "trigger")
    private Trigger trigger;

    /*
     * The action that will be executed.
     */
    @JsonProperty(value = "action")
    private Action action;

    /**
     * Get the trigger property: A condition to be satisfied for an action to
     * be executed.
     * 
     * @return the trigger value.
     */
    public Trigger getTrigger() {
        return this.trigger;
    }

    /**
     * Set the trigger property: A condition to be satisfied for an action to
     * be executed.
     * 
     * @param trigger the trigger value to set.
     * @return the LifetimeAction object itself.
     */
    public LifetimeAction setTrigger(Trigger trigger) {
        this.trigger = trigger;
        return this;
    }

    /**
     * Get the action property: The action that will be executed.
     * 
     * @return the action value.
     */
    public Action getAction() {
        return this.action;
    }

    /**
     * Set the action property: The action that will be executed.
     * 
     * @param action the action value to set.
     * @return the LifetimeAction object itself.
     */
    public LifetimeAction setAction(Action action) {
        this.action = action;
        return this;
    }

    public void validate() {
        if (getTrigger() != null) {
            getTrigger().validate();
        }
        if (getAction() != null) {
            getAction().validate();
        }
    }
}
