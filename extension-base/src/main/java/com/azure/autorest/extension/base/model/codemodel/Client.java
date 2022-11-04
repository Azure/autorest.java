// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.ArrayList;
import java.util.List;

public class Client extends Metadata implements ClientTrait {

    private String summary;

    private List<OperationGroup> operationGroups = new ArrayList<OperationGroup>();

    private Security security;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<OperationGroup> getOperationGroups() {
        return operationGroups;
    }

    public void setOperationGroups(List<OperationGroup> operationGroups) {
        this.operationGroups = operationGroups;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }
}
