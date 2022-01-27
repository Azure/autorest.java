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

    private CodeModel codeModel;

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


    public CodeModel getCodeModel() {
        return codeModel;
    }

    public void setCodeModel(CodeModel codeModel) {
        this.codeModel = codeModel;
    }
}
