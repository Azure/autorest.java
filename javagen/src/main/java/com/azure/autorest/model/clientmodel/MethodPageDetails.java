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

    // Proxy method return type is Flux<ByteBuffer>. Client method return type is PagedResponse<>.
    // This intermediate type is the type of pagination response (the type with values and nextLink).
    private IType lroIntermediateType;

    public MethodPageDetails(String nextLinkName, String itemName, ClientMethod nextMethod, IType lroIntermediateType) {
        this.nextLinkName = nextLinkName;
        this.itemName = itemName;
        this.nextMethod = nextMethod;
        this.lroIntermediateType = lroIntermediateType;
    }

    public String getNextLinkName() {
        return nextLinkName;
    }

    public String getItemName() {
        return itemName;
    }

    public ClientMethod getNextMethod() {
        return nextMethod;
    }

    public IType getLroIntermediateType() {
        return lroIntermediateType;
    }

    public boolean nonNullNextLink() {
        return nextLinkName != null && !nextLinkName.isEmpty();
    }
}
