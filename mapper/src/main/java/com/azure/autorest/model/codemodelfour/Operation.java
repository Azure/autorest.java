package com.azure.autorest.model.codemodelfour;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * represents a single callable endpoint with a discrete set of inputs, and any number of output possibilities (responses or exceptions)
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "request",
    "responses",
    "exceptions",
    "profile",
    "$key",
    "description",
    "uid",
    "summary",
    "apiVersions",
    "deprecated",
    "externalDocs",
    "language",
    "protocol",
    "extensions"
})
public class Operation {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("request")
    private Request request;
    /**
     * responses that indicate a successful call
     * (Required)
     * 
     */
    @JsonProperty("responses")
    @JsonPropertyDescription("responses that indicate a successful call")
    private List<Response> responses = new ArrayList<Response>();
    /**
     * responses that indicate a failed call
     * (Required)
     * 
     */
    @JsonProperty("exceptions")
    @JsonPropertyDescription("responses that indicate a failed call")
    private List<Response> exceptions = new ArrayList<Response>();
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("profile")
    private DictionaryApiVersion profile;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("$key")
    private String $key;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("description")
    private String description;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("uid")
    private String uid;
    /**
     * a short description
     * 
     */
    @JsonProperty("summary")
    @JsonPropertyDescription("a short description")
    private String summary;
    /**
     * API versions that this applies to. Undefined means all versions
     * 
     */
    @JsonProperty("apiVersions")
    @JsonPropertyDescription("API versions that this applies to. Undefined means all versions")
    private List<ApiVersion> apiVersions = new ArrayList<ApiVersion>();
    /**
     * represents  deprecation information for a given aspect
     * 
     */
    @JsonProperty("deprecated")
    @JsonPropertyDescription("represents  deprecation information for a given aspect")
    private Deprecation deprecated;
    /**
     * a reference to external documentation
     * 
     */
    @JsonProperty("externalDocs")
    @JsonPropertyDescription("a reference to external documentation")
    private ExternalDocumentation externalDocs;
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
     * 
     * (Required)
     * 
     */
    @JsonProperty("request")
    public Request getRequest() {
        return request;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("request")
    public void setRequest(Request request) {
        this.request = request;
    }

    /**
     * responses that indicate a successful call
     * (Required)
     * 
     */
    @JsonProperty("responses")
    public List<Response> getResponses() {
        return responses;
    }

    /**
     * responses that indicate a successful call
     * (Required)
     * 
     */
    @JsonProperty("responses")
    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    /**
     * responses that indicate a failed call
     * (Required)
     * 
     */
    @JsonProperty("exceptions")
    public List<Response> getExceptions() {
        return exceptions;
    }

    /**
     * responses that indicate a failed call
     * (Required)
     * 
     */
    @JsonProperty("exceptions")
    public void setExceptions(List<Response> exceptions) {
        this.exceptions = exceptions;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("profile")
    public DictionaryApiVersion getProfile() {
        return profile;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("profile")
    public void setProfile(DictionaryApiVersion profile) {
        this.profile = profile;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("$key")
    public String get$key() {
        return $key;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("$key")
    public void set$key(String $key) {
        this.$key = $key;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("uid")
    public String getUid() {
        return uid;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("uid")
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * a short description
     * 
     */
    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    /**
     * a short description
     * 
     */
    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * API versions that this applies to. Undefined means all versions
     * 
     */
    @JsonProperty("apiVersions")
    public List<ApiVersion> getApiVersions() {
        return apiVersions;
    }

    /**
     * API versions that this applies to. Undefined means all versions
     * 
     */
    @JsonProperty("apiVersions")
    public void setApiVersions(List<ApiVersion> apiVersions) {
        this.apiVersions = apiVersions;
    }

    /**
     * represents  deprecation information for a given aspect
     * 
     */
    @JsonProperty("deprecated")
    public Deprecation getDeprecated() {
        return deprecated;
    }

    /**
     * represents  deprecation information for a given aspect
     * 
     */
    @JsonProperty("deprecated")
    public void setDeprecated(Deprecation deprecated) {
        this.deprecated = deprecated;
    }

    /**
     * a reference to external documentation
     * 
     */
    @JsonProperty("externalDocs")
    public ExternalDocumentation getExternalDocs() {
        return externalDocs;
    }

    /**
     * a reference to external documentation
     * 
     */
    @JsonProperty("externalDocs")
    public void setExternalDocs(ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
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
