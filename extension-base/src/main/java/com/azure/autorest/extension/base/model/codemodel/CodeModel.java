
package com.azure.autorest.extension.base.model.codemodel;

import java.util.ArrayList;
import java.util.List;


/**
 * CodeModel
 * <p>
 * the model that contains all the information required to generate a service api
 * 
 */
public class CodeModel extends Metadata {

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
    /**
     * All operations
     * (Required)
     * 
     */
    private List<OperationGroup> operationGroups = new ArrayList<OperationGroup>();
    /**
     * all global parameters (ie, ImplementationLocation = client )
     * 
     */
    private List<Parameter> globalParameters = new ArrayList<Parameter>();
    /**
     * Security Configuration
     * (Optional)
     *
     */
    private Security security;

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

    /**
     * All operations
     * (Required)
     * 
     */
    public List<OperationGroup> getOperationGroups() {
        return operationGroups;
    }

    /**
     * All operations
     * (Required)
     * 
     */
    public void setOperationGroups(List<OperationGroup> operationGroups) {
        this.operationGroups = operationGroups;
    }

    /**
     * all global parameters (ie, ImplementationLocation = client )
     * 
     */
    public List<Parameter> getGlobalParameters() {
        return globalParameters;
    }

    /**
     * all global parameters (ie, ImplementationLocation = client )
     * 
     */
    public void setGlobalParameters(List<Parameter> globalParameters) {
        this.globalParameters = globalParameters;
    }

    /**
     * Security Configuration
     * (Optional)
     *
     */
    public Security getSecurity() {
        return security;
    }

    /**
     * Security Configuration
     * (Optional)
     *
     */
    public void setSecurity(Security security) {
        this.security = security;
    }

    public TestModel getTestModel() {
        return testModel;
    }

    public void setTestModel(TestModel testModel) {
        this.testModel = testModel;
    }
}
