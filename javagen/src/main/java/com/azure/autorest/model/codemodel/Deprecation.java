package com.azure.autorest.model.codemodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;


/**
 * represents  deprecation information for a given aspect
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "message",
    "apiVersions"
})
public class Deprecation {

    /**
     * the reason why this aspect
     * (Required)
     * 
     */
    @JsonProperty("message")
    @JsonPropertyDescription("the reason why this aspect")
    private String message;
    /**
     * the api versions that this deprecation is applicable to.
     * (Required)
     * 
     */
    @JsonProperty("apiVersions")
    @JsonPropertyDescription("the api versions that this deprecation is applicable to.")
    private List<ApiVersion> apiVersions = new ArrayList<ApiVersion>();

    /**
     * the reason why this aspect
     * (Required)
     * 
     */
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    /**
     * the reason why this aspect
     * (Required)
     * 
     */
    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * the api versions that this deprecation is applicable to.
     * (Required)
     * 
     */
    @JsonProperty("apiVersions")
    public List<ApiVersion> getApiVersions() {
        return apiVersions;
    }

    /**
     * the api versions that this deprecation is applicable to.
     * (Required)
     * 
     */
    @JsonProperty("apiVersions")
    public void setApiVersions(List<ApiVersion> apiVersions) {
        this.apiVersions = apiVersions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Deprecation.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("message");
        sb.append('=');
        sb.append(((this.message == null)?"<null>":this.message));
        sb.append(',');
        sb.append("apiVersions");
        sb.append('=');
        sb.append(((this.apiVersions == null)?"<null>":this.apiVersions));
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
        result = ((result* 31)+((this.message == null)? 0 :this.message.hashCode()));
        result = ((result* 31)+((this.apiVersions == null)? 0 :this.apiVersions.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Deprecation) == false) {
            return false;
        }
        Deprecation rhs = ((Deprecation) other);
        return (((this.message == rhs.message)||((this.message!= null)&&this.message.equals(rhs.message)))&&((this.apiVersions == rhs.apiVersions)||((this.apiVersions!= null)&&this.apiVersions.equals(rhs.apiVersions))));
    }

}
