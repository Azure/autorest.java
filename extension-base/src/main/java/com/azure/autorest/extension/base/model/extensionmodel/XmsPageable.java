// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.extensionmodel;

import com.azure.autorest.extension.base.model.codemodel.Operation;

/**
 * Represents the pageable settings of a model.
 */
public class XmsPageable {
    private String itemName = "value";
    private String nextLinkName;
    private String operationName;
    private Operation nextOperation;

    /**
     * Creates a new instance of the XmsPageable class.
     */
    public XmsPageable() {
    }

    /**
     * Gets the name of the item in the pageable response.
     *
     * @return The name of the item in the pageable response.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the name of the item in the pageable response.
     *
     * @param itemName The name of the item in the pageable response.
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Gets the name of the next link in the pageable response.
     *
     * @return The name of the next link in the pageable response.
     */
    public String getNextLinkName() {
        return nextLinkName;
    }

    /**
     * Sets the name of the next link in the pageable response.
     *
     * @param nextLinkName The name of the next link in the pageable response.
     */
    public void setNextLinkName(String nextLinkName) {
        this.nextLinkName = nextLinkName;
    }

    /**
     * Gets the name of the operation that retrieves the next page of items.
     *
     * @return The name of the operation that retrieves the next page of items.
     */
    public String getOperationName() {
        return operationName;
    }

    /**
     * Sets the name of the operation that retrieves the next page of items.
     *
     * @param operationName The name of the operation that retrieves the next page of items.
     */
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    /**
     * Gets the operation that retrieves the next page of items.
     *
     * @return The operation that retrieves the next page of items.
     */
    public Operation getNextOperation() {
        return nextOperation;
    }

    /**
     * Sets the operation that retrieves the next page of items.
     *
     * @param nextOperation The operation that retrieves the next page of items.
     */
    public void setNextOperation(Operation nextOperation) {
        this.nextOperation = nextOperation;
    }
}
