// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package azure.signalr.implementation;

import azure.signalr.fluent.models.OperationInner;
import azure.signalr.models.Operation;
import azure.signalr.models.OperationDisplay;
import azure.signalr.models.OperationProperties;

public final class OperationImpl implements Operation {
    private OperationInner innerObject;

    private final azure.signalr.SignalRManager serviceManager;

    OperationImpl(OperationInner innerObject, azure.signalr.SignalRManager serviceManager) {
        this.innerObject = innerObject;
        this.serviceManager = serviceManager;
    }

    public String name() {
        return this.innerModel().name();
    }

    public Boolean isDataAction() {
        return this.innerModel().isDataAction();
    }

    public OperationDisplay display() {
        return this.innerModel().display();
    }

    public String origin() {
        return this.innerModel().origin();
    }

    public OperationProperties properties() {
        return this.innerModel().properties();
    }

    public OperationInner innerModel() {
        return this.innerObject;
    }

    private azure.signalr.SignalRManager manager() {
        return this.serviceManager;
    }
}
