// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

/**
 * A page class that contains results that are received from a service request.
 */
public class PageDetails {
    public String packageName;
    private String nextLinkName;
    private String itemName;
    private String className;

    public PageDetails(String package_Keyword, String nextLinkName, String itemName, String className) {
        packageName = package_Keyword;
        this.nextLinkName = nextLinkName;
        this.itemName = itemName;
        this.className = className;
    }

    public final String getNextLinkName() {
        return nextLinkName;
    }

    public final String getItemName() {
        return itemName;
    }

    public final String getClassName() {
        return className;
    }
}
