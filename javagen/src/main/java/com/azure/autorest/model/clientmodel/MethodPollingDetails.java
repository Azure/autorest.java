// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

public class MethodPollingDetails {
    private final String pollingStrategy;
    private final IType intermediateType;
    private final IType finalType;
    private final int pollIntervalInSeconds;

    public MethodPollingDetails(String pollingStrategy, IType intermediateType, IType finalType, int pollIntervalInSeconds) {
        this.pollingStrategy = pollingStrategy;
        this.intermediateType = intermediateType;
        this.finalType = finalType;
        this.pollIntervalInSeconds = pollIntervalInSeconds;
    }

    public String getPollingStrategy() {
        return pollingStrategy;
    }

    public IType getIntermediateType() {
        return intermediateType;
    }

    public IType getFinalType() {
        return finalType;
    }

    public int getPollIntervalInSeconds() {
        return pollIntervalInSeconds;
    }
}
