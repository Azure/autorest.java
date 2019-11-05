
package com.azure.autorest.model.codemodel;

import java.util.ArrayList;
import java.util.List;


/**
 * CodeModel
 * <p>
 * the model that contains all the information required to generate a service api
 * 
 */
public class CodeModel {

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
     * custom extensible metadata for individual language generators
     * (Required)
     * 
     */
    private Languages language;
    /**
     * custom extensible metadata for individual protocols (ie, HTTP, etc)
     * (Required)
     * 
     */
    private Protocols protocol;
    private DictionaryAny extensions;

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
     * custom extensible metadata for individual language generators
     * (Required)
     * 
     */
    public Languages getLanguage() {
        return language;
    }

    /**
     * custom extensible metadata for individual language generators
     * (Required)
     * 
     */
    public void setLanguage(Languages language) {
        this.language = language;
    }

    /**
     * custom extensible metadata for individual protocols (ie, HTTP, etc)
     * (Required)
     * 
     */
    public Protocols getProtocol() {
        return protocol;
    }

    /**
     * custom extensible metadata for individual protocols (ie, HTTP, etc)
     * (Required)
     * 
     */
    public void setProtocol(Protocols protocol) {
        this.protocol = protocol;
    }

    public DictionaryAny getExtensions() {
        return extensions;
    }

    public void setExtensions(DictionaryAny extensions) {
        this.extensions = extensions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CodeModel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("info");
        sb.append('=');
        sb.append(((this.info == null)?"<null>":this.info));
        sb.append(',');
        sb.append("schemas");
        sb.append('=');
        sb.append(((this.schemas == null)?"<null>":this.schemas));
        sb.append(',');
        sb.append("operationGroups");
        sb.append('=');
        sb.append(((this.operationGroups == null)?"<null>":this.operationGroups));
        sb.append(',');
        sb.append("globalParameters");
        sb.append('=');
        sb.append(((this.globalParameters == null)?"<null>":this.globalParameters));
        sb.append(',');
        sb.append("language");
        sb.append('=');
        sb.append(((this.language == null)?"<null>":this.language));
        sb.append(',');
        sb.append("protocol");
        sb.append('=');
        sb.append(((this.protocol == null)?"<null>":this.protocol));
        sb.append(',');
        sb.append("extensions");
        sb.append('=');
        sb.append(((this.extensions == null)?"<null>":this.extensions));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.operationGroups == null)? 0 :this.operationGroups.hashCode()));
        result = ((result* 31)+((this.protocol == null)? 0 :this.protocol.hashCode()));
        result = ((result* 31)+((this.extensions == null)? 0 :this.extensions.hashCode()));
        result = ((result* 31)+((this.globalParameters == null)? 0 :this.globalParameters.hashCode()));
        result = ((result* 31)+((this.schemas == null)? 0 :this.schemas.hashCode()));
        result = ((result* 31)+((this.language == null)? 0 :this.language.hashCode()));
        result = ((result* 31)+((this.info == null)? 0 :this.info.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CodeModel) == false) {
            return false;
        }
        CodeModel rhs = ((CodeModel) other);
        return ((((((((this.operationGroups == rhs.operationGroups)||((this.operationGroups!= null)&&this.operationGroups.equals(rhs.operationGroups)))&&((this.protocol == rhs.protocol)||((this.protocol!= null)&&this.protocol.equals(rhs.protocol))))&&((this.extensions == rhs.extensions)||((this.extensions!= null)&&this.extensions.equals(rhs.extensions))))&&((this.globalParameters == rhs.globalParameters)||((this.globalParameters!= null)&&this.globalParameters.equals(rhs.globalParameters))))&&((this.schemas == rhs.schemas)||((this.schemas!= null)&&this.schemas.equals(rhs.schemas))))&&((this.language == rhs.language)||((this.language!= null)&&this.language.equals(rhs.language))))&&((this.info == rhs.info)||((this.info!= null)&&this.info.equals(rhs.info))));
    }

}
