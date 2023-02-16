// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.ArrayList;
import java.util.List;


/**
 * CodeModel
 * <p>
 * the model that contains all the information required to generate a service api
 * 
 */
public class CodeModel extends Client {

    /**
     * code model information
     * (Required)
     * 
     */
    private Info info;
    /**
     * the full set of schemas for a given service, categorized into convenient collections
     * (Required)
     * 
     */
    private Schemas schemas;

    private List<Client> clients = new ArrayList<>();

    /**
     * test model definition
     */
    private TestModel testModel;

    /**
     * code model information
     * (Required)
     * 
     */
    public Info getInfo() {
        return info;
    }

    /**
     * code model information
     * (Required)
     * 
     */
    public void setInfo(Info info) {
        this.info = info;
    }

    /**
     * the full set of schemas for a given service, categorized into convenient collections
     * (Required)
     * 
     */
    public Schemas getSchemas() {
        return schemas;
    }

    /**
     * the full set of schemas for a given service, categorized into convenient collections
     * (Required)
     * 
     */
    public void setSchemas(Schemas schemas) {
        this.schemas = schemas;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public TestModel getTestModel() {
        return testModel;
    }

    public void setTestModel(TestModel testModel) {
        this.testModel = testModel;
    }
}
