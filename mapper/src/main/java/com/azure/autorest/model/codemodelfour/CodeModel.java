package com.azure.autorest.model.codemodelfour;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * CodeModel
 * <p>
 * the model that contains all the information required to generate a service api
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "info",
    "schemas",
    "operationGroups",
    "globalParameters",
    "language",
    "protocol",
    "extensions"
})
public class CodeModel {

    /**
     * code model information
     * (Required)
     * 
     */
    @JsonProperty("info")
    @JsonPropertyDescription("code model information")
    private Info info;
    /**
     * the full set of schemas for a given service, categorized into convenient collections
     * (Required)
     * 
     */
    @JsonProperty("schemas")
    @JsonPropertyDescription("the full set of schemas for a given service, categorized into convenient collections")
    private Schemas schemas;
    /**
     * All operations
     * (Required)
     * 
     */
    @JsonProperty("operationGroups")
    @JsonPropertyDescription("All operations")
    private List<OperationGroup> operationGroups = new ArrayList<OperationGroup>();
    /**
     * all global parameters (ie, ImplementationLocation = client )
     * (Required)
     * 
     */
    @JsonProperty("globalParameters")
    @JsonPropertyDescription("all global parameters (ie, ImplementationLocation = client )")
    private List<Parameter> globalParameters = new ArrayList<Parameter>();
    /**
     * custom extensible metadata for individual language generators
     * (Required)
     * 
     */
    @JsonProperty("language")
    @JsonPropertyDescription("custom extensible metadata for individual language generators")
    private Languages language;
    /**
     * custom extensible metadata for individual protocols (ie, HTTP, etc)
     * (Required)
     * 
     */
    @JsonProperty("protocol")
    @JsonPropertyDescription("custom extensible metadata for individual protocols (ie, HTTP, etc)")
    private Protocols protocol;
    @JsonProperty("extensions")
    private DictionaryAny extensions;

    /**
     * code model information
     * (Required)
     * 
     */
    @JsonProperty("info")
    public Info getInfo() {
        return info;
    }

    /**
     * code model information
     * (Required)
     * 
     */
    @JsonProperty("info")
    public void setInfo(Info info) {
        this.info = info;
    }

    /**
     * the full set of schemas for a given service, categorized into convenient collections
     * (Required)
     * 
     */
    @JsonProperty("schemas")
    public Schemas getSchemas() {
        return schemas;
    }

    /**
     * the full set of schemas for a given service, categorized into convenient collections
     * (Required)
     * 
     */
    @JsonProperty("schemas")
    public void setSchemas(Schemas schemas) {
        this.schemas = schemas;
    }

    /**
     * All operations
     * (Required)
     * 
     */
    @JsonProperty("operationGroups")
    public List<OperationGroup> getOperationGroups() {
        return operationGroups;
    }

    /**
     * All operations
     * (Required)
     * 
     */
    @JsonProperty("operationGroups")
    public void setOperationGroups(List<OperationGroup> operationGroups) {
        this.operationGroups = operationGroups;
    }

    /**
     * all global parameters (ie, ImplementationLocation = client )
     * (Required)
     * 
     */
    @JsonProperty("globalParameters")
    public List<Parameter> getGlobalParameters() {
        return globalParameters;
    }

    /**
     * all global parameters (ie, ImplementationLocation = client )
     * (Required)
     * 
     */
    @JsonProperty("globalParameters")
    public void setGlobalParameters(List<Parameter> globalParameters) {
        this.globalParameters = globalParameters;
    }

    /**
     * custom extensible metadata for individual language generators
     * (Required)
     * 
     */
    @JsonProperty("language")
    public Languages getLanguage() {
        return language;
    }

    /**
     * custom extensible metadata for individual language generators
     * (Required)
     * 
     */
    @JsonProperty("language")
    public void setLanguage(Languages language) {
        this.language = language;
    }

    /**
     * custom extensible metadata for individual protocols (ie, HTTP, etc)
     * (Required)
     * 
     */
    @JsonProperty("protocol")
    public Protocols getProtocol() {
        return protocol;
    }

    /**
     * custom extensible metadata for individual protocols (ie, HTTP, etc)
     * (Required)
     * 
     */
    @JsonProperty("protocol")
    public void setProtocol(Protocols protocol) {
        this.protocol = protocol;
    }

    @JsonProperty("extensions")
    public DictionaryAny getExtensions() {
        return extensions;
    }

    @JsonProperty("extensions")
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
