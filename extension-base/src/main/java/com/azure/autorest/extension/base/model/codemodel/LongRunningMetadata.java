// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

public class LongRunningMetadata {

    private ObjectSchema pollResultType;

    private ObjectSchema finalResultType;

    private Metadata pollingStrategy;

    public ObjectSchema getPollResultType() {
        return pollResultType;
    }

    public void setPollResultType(ObjectSchema pollResultType) {
        this.pollResultType = pollResultType;
    }

    public ObjectSchema getFinalResultType() {
        return finalResultType;
    }

    public void setFinalResultType(ObjectSchema finalResultType) {
        this.finalResultType = finalResultType;
    }

    public Metadata getPollingStrategy() {
        return pollingStrategy;
    }

    public void setPollingStrategy(Metadata pollingStrategy) {
        this.pollingStrategy = pollingStrategy;
    }
}
