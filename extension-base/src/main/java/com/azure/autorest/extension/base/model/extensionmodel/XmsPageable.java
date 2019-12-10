package com.azure.autorest.extension.base.model.extensionmodel;

import com.azure.autorest.extension.base.model.codemodel.Operation;

public class XmsPageable {
    private String itemName = "value";
    private String nextLinkName;
    private String operationName;
    private Operation nextOperation;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getNextLinkName() {
        return nextLinkName;
    }

    public void setNextLinkName(String nextLinkName) {
        this.nextLinkName = nextLinkName;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public Operation getNextOperation() {
        return nextOperation;
    }

    public void setNextOperation(Operation nextOperation) {
        this.nextOperation = nextOperation;
    }
}
