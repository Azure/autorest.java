// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package azure.signalr.implementation;

import azure.signalr.fluent.OperationsClient;
import azure.signalr.fluent.models.OperationInner;
import azure.signalr.models.Operation;
import azure.signalr.models.Operations;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.util.Context;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonIgnore;

public final class OperationsImpl implements Operations {
    @JsonIgnore private final ClientLogger logger = new ClientLogger(OperationsImpl.class);

    private final OperationsClient innerClient;

    private final azure.signalr.SignalRManager serviceManager;

    public OperationsImpl(OperationsClient innerClient, azure.signalr.SignalRManager serviceManager) {
        this.innerClient = innerClient;
        this.serviceManager = serviceManager;
    }

    public PagedIterable<Operation> list() {
        PagedIterable<OperationInner> inner = this.serviceClient().list();
        return Utils.mapPage(inner, inner1 -> new OperationImpl(inner1, this.manager()));
    }

    public PagedIterable<Operation> list(Context context) {
        PagedIterable<OperationInner> inner = this.serviceClient().list(context);
        return Utils.mapPage(inner, inner1 -> new OperationImpl(inner1, this.manager()));
    }

    private OperationsClient serviceClient() {
        return this.innerClient;
    }

    private azure.signalr.SignalRManager manager() {
        return this.serviceManager;
    }
}
