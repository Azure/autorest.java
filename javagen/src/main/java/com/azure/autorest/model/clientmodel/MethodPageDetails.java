// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

/**
 * A page class that contains results that are received from a service request.
 */
public class MethodPageDetails {
    /**
     * Get whether or not this method is a request to get the next page of a sequence of pages.
     */
    private String nextLinkName;
    /**
     * Raw nextLink name. It is the name in swagger and in response.
     */
    private String rawNextLinkName;
    private String itemName;
    /**
     * Raw item name. It is the name in swagger and in response.
     */
    private String rawItemName;
    private ClientMethod nextMethod;

    // Proxy method return type is Flux<ByteBuffer>. Client method return type is PagedResponse<>.
    // This intermediate type is the type of pagination response (the type with values and nextLink).
    private IType lroIntermediateType;

    public MethodPageDetails(String nextLinkName, String itemName, ClientMethod nextMethod, IType lroIntermediateType,
                             String rawNextLinkName, String rawItemName) {
        this.nextLinkName = nextLinkName;
        this.itemName = itemName;
        this.nextMethod = nextMethod;
        this.lroIntermediateType = lroIntermediateType;
        this.rawNextLinkName = rawNextLinkName;
        this.rawItemName = rawItemName;
    }

    public String getNextLinkName() {
        return nextLinkName;
    }

    public String getRawNextLinkName() {
        return rawNextLinkName;
    }

    public String getItemName() {
        return itemName;
    }

    public String getRawItemName() {
        return rawItemName;
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
