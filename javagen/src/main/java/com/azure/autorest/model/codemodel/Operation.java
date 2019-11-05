
package com.azure.autorest.model.codemodel;

import java.util.ArrayList;
import java.util.List;


/**
 * represents a single callable endpoint with a discrete set of inputs, and any number of output possibilities (responses or exceptions)
 * 
 */
public class Operation {

    /**
     * 
     * (Required)
     * 
     */
    private Request request;
    /**
     * responses that indicate a successful call
     * 
     */
    private List<Response> responses = new ArrayList<Response>();
    /**
     * responses that indicate a failed call
     * 
     */
    private List<Response> exceptions = new ArrayList<Response>();
    private DictionaryApiVersion profile;
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
    private String description;
    /**
     * 
     * (Required)
     * 
     */
    private String uid;
    /**
     * a short description
     * 
     */
    private String summary;
    /**
     * API versions that this applies to. Undefined means all versions
     * 
     */
    private List<ApiVersion> apiVersions = new ArrayList<ApiVersion>();
    /**
     * represents  deprecation information for a given aspect
     * 
     */
    private Deprecation deprecated;
    /**
     * a reference to external documentation
     * 
     */
    private ExternalDocumentation externalDocs;
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
     * 
     * (Required)
     * 
     */
    public Request getRequest() {
        return request;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setRequest(Request request) {
        this.request = request;
    }

    /**
     * responses that indicate a successful call
     * 
     */
    public List<Response> getResponses() {
        return responses;
    }

    /**
     * responses that indicate a successful call
     * 
     */
    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    /**
     * responses that indicate a failed call
     * 
     */
    public List<Response> getExceptions() {
        return exceptions;
    }

    /**
     * responses that indicate a failed call
     * 
     */
    public void setExceptions(List<Response> exceptions) {
        this.exceptions = exceptions;
    }

    public DictionaryApiVersion getProfile() {
        return profile;
    }

    public void setProfile(DictionaryApiVersion profile) {
        this.profile = profile;
    }

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
    public String getDescription() {
        return description;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * (Required)
     * 
     */
    public String getUid() {
        return uid;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * a short description
     * 
     */
    public String getSummary() {
        return summary;
    }

    /**
     * a short description
     * 
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * API versions that this applies to. Undefined means all versions
     * 
     */
    public List<ApiVersion> getApiVersions() {
        return apiVersions;
    }

    /**
     * API versions that this applies to. Undefined means all versions
     * 
     */
    public void setApiVersions(List<ApiVersion> apiVersions) {
        this.apiVersions = apiVersions;
    }

    /**
     * represents  deprecation information for a given aspect
     * 
     */
    public Deprecation getDeprecated() {
        return deprecated;
    }

    /**
     * represents  deprecation information for a given aspect
     * 
     */
    public void setDeprecated(Deprecation deprecated) {
        this.deprecated = deprecated;
    }

    /**
     * a reference to external documentation
     * 
     */
    public ExternalDocumentation getExternalDocs() {
        return externalDocs;
    }

    /**
     * a reference to external documentation
     * 
     */
    public void setExternalDocs(ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
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
        sb.append(Operation.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("request");
        sb.append('=');
        sb.append(((this.request == null)?"<null>":this.request));
        sb.append(',');
        sb.append("responses");
        sb.append('=');
        sb.append(((this.responses == null)?"<null>":this.responses));
        sb.append(',');
        sb.append("exceptions");
        sb.append('=');
        sb.append(((this.exceptions == null)?"<null>":this.exceptions));
        sb.append(',');
        sb.append("profile");
        sb.append('=');
        sb.append(((this.profile == null)?"<null>":this.profile));
        sb.append(',');
        sb.append("$key");
        sb.append('=');
        sb.append(((this.$key == null)?"<null>":this.$key));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("uid");
        sb.append('=');
        sb.append(((this.uid == null)?"<null>":this.uid));
        sb.append(',');
        sb.append("summary");
        sb.append('=');
        sb.append(((this.summary == null)?"<null>":this.summary));
        sb.append(',');
        sb.append("apiVersions");
        sb.append('=');
        sb.append(((this.apiVersions == null)?"<null>":this.apiVersions));
        sb.append(',');
        sb.append("deprecated");
        sb.append('=');
        sb.append(((this.deprecated == null)?"<null>":this.deprecated));
        sb.append(',');
        sb.append("externalDocs");
        sb.append('=');
        sb.append(((this.externalDocs == null)?"<null>":this.externalDocs));
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
        result = ((result* 31)+((this.summary == null)? 0 :this.summary.hashCode()));
        result = ((result* 31)+((this.request == null)? 0 :this.request.hashCode()));
        result = ((result* 31)+((this.profile == null)? 0 :this.profile.hashCode()));
        result = ((result* 31)+((this.deprecated == null)? 0 :this.deprecated.hashCode()));
        result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
        result = ((result* 31)+((this.$key == null)? 0 :this.$key.hashCode()));
        result = ((result* 31)+((this.language == null)? 0 :this.language.hashCode()));
        result = ((result* 31)+((this.exceptions == null)? 0 :this.exceptions.hashCode()));
        result = ((result* 31)+((this.uid == null)? 0 :this.uid.hashCode()));
        result = ((result* 31)+((this.protocol == null)? 0 :this.protocol.hashCode()));
        result = ((result* 31)+((this.extensions == null)? 0 :this.extensions.hashCode()));
        result = ((result* 31)+((this.apiVersions == null)? 0 :this.apiVersions.hashCode()));
        result = ((result* 31)+((this.responses == null)? 0 :this.responses.hashCode()));
        result = ((result* 31)+((this.externalDocs == null)? 0 :this.externalDocs.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Operation) == false) {
            return false;
        }
        Operation rhs = ((Operation) other);
        return (((((((((((((((this.summary == rhs.summary)||((this.summary!= null)&&this.summary.equals(rhs.summary)))&&((this.request == rhs.request)||((this.request!= null)&&this.request.equals(rhs.request))))&&((this.profile == rhs.profile)||((this.profile!= null)&&this.profile.equals(rhs.profile))))&&((this.deprecated == rhs.deprecated)||((this.deprecated!= null)&&this.deprecated.equals(rhs.deprecated))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.$key == rhs.$key)||((this.$key!= null)&&this.$key.equals(rhs.$key))))&&((this.language == rhs.language)||((this.language!= null)&&this.language.equals(rhs.language))))&&((this.exceptions == rhs.exceptions)||((this.exceptions!= null)&&this.exceptions.equals(rhs.exceptions))))&&((this.uid == rhs.uid)||((this.uid!= null)&&this.uid.equals(rhs.uid))))&&((this.protocol == rhs.protocol)||((this.protocol!= null)&&this.protocol.equals(rhs.protocol))))&&((this.extensions == rhs.extensions)||((this.extensions!= null)&&this.extensions.equals(rhs.extensions))))&&((this.apiVersions == rhs.apiVersions)||((this.apiVersions!= null)&&this.apiVersions.equals(rhs.apiVersions))))&&((this.responses == rhs.responses)||((this.responses!= null)&&this.responses.equals(rhs.responses))))&&((this.externalDocs == rhs.externalDocs)||((this.externalDocs!= null)&&this.externalDocs.equals(rhs.externalDocs))));
    }

}
