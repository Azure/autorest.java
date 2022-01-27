// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.ArrayList;
import java.util.List;


/**
 * represents  deprecation information for a given aspect
 * 
 */
public class Deprecation {

    /**
     * the reason why this aspect
     * (Required)
     * 
     */
    private String message;
    /**
     * the api versions that this deprecation is applicable to.
     * (Required)
     * 
     */
    private List<ApiVersion> apiVersions = new ArrayList<ApiVersion>();

    /**
     * the reason why this aspect
     * (Required)
     * 
     */
    public String getMessage() {
        return message;
    }

    /**
     * the reason why this aspect
     * (Required)
     * 
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * the api versions that this deprecation is applicable to.
     * (Required)
     * 
     */
    public List<ApiVersion> getApiVersions() {
        return apiVersions;
    }

    /**
     * the api versions that this deprecation is applicable to.
     * (Required)
     * 
     */
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
