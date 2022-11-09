// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.ArrayList;
import java.util.List;


/**
 * an operation group represents a container around set of operations
 * 
 */
public class OperationGroup extends Metadata {

    /**
     * 
     * (Required)
     * 
     */
    private String $key;
    /**
     * 
     * (Required)
     * 
     */
    private List<Operation> operations = new ArrayList<Operation>();

    private Client codeModel;

    /**
     * 
     * (Required)
     * 
     */
    public String get$key() {
        return $key;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void set$key(String $key) {
        this.$key = $key;
    }

    /**
     * 
     * (Required)
     * 
     */
    public List<Operation> getOperations() {
        return operations;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }


    /**
     * @return the client which contains the operation group.
     */
    public Client getCodeModel() {
        return codeModel;
    }

    public void setCodeModel(Client codeModel) {
        this.codeModel = codeModel;
    }
}
