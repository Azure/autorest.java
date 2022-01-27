// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.ArrayList;
import java.util.List;

public class Request extends Metadata {

    /**
     * the parameter inputs to the operation
     * 
     */
    private List<Parameter> parameters = new ArrayList<Parameter>();

    private List<Parameter> signatureParameters = new ArrayList<Parameter>();

    /**
     * the parameter inputs to the operation
     * 
     */
    public List<Parameter> getParameters() {
        return parameters;
    }

    /**
     * the parameter inputs to the operation
     * 
     */
    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public List<Parameter> getSignatureParameters() {
        return signatureParameters;
    }

    public void setSignatureParameters(List<Parameter> signatureParameters) {
        this.signatureParameters = signatureParameters;
    }
}
