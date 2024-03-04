// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

/**
 * Represents the metadata for long-running operations.
 */
public class LongRunningMetadata {
    private ObjectSchema pollResultType;
    private ObjectSchema finalResultType;
    private Metadata pollingStrategy;

    /**
     * Creates a new instance of the LongRunningMetadata class.
     */
    public LongRunningMetadata() {
    }

    /**
     * Gets the poll result type.
     *
     * @return The poll result type.
     */
    public ObjectSchema getPollResultType() {
        return pollResultType;
    }

    /**
     * Sets the poll result type.
     *
     * @param pollResultType The poll result type.
     */
    public void setPollResultType(ObjectSchema pollResultType) {
        this.pollResultType = pollResultType;
    }

    /**
     * Gets the final result type.
     *
     * @return The final result type.
     */
    public ObjectSchema getFinalResultType() {
        return finalResultType;
    }

    /**
     * Sets the final result type.
     *
     * @param finalResultType The final result type.
     */
    public void setFinalResultType(ObjectSchema finalResultType) {
        this.finalResultType = finalResultType;
    }

    /**
     * Gets the polling strategy.
     *
     * @return The polling strategy.
     */
    public Metadata getPollingStrategy() {
        return pollingStrategy;
    }

    /**
     * Sets the polling strategy.
     *
     * @param pollingStrategy The polling strategy.
     */
    public void setPollingStrategy(Metadata pollingStrategy) {
        this.pollingStrategy = pollingStrategy;
    }
}
