package com.azure.autorest.model.clientmodel;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/**
 * A page class that contains results that are received from a service request.
 */
public class MethodPageDetails {
    /**
     * Get whether or not this method is a request to get the next page of a sequence of pages.
     */
    private String nextLinkName;
    private String itemName;
    private ClientMethod nextMethod;

    public MethodPageDetails(String nextLinkName, String itemName, ClientMethod nextMethod) {
        this.nextLinkName = nextLinkName;
        this.itemName = itemName;
        this.nextMethod = nextMethod;
    }

    public String getNextLinkName() {
        return nextLinkName;
    }

    public String getItemName() {
        return itemName;
    }

    public final ClientMethod getNextMethod() {
        return nextMethod;
    }
}