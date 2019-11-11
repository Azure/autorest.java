
package com.azure.autorest.extension.base.model.codemodel;

import java.util.HashMap;
import java.util.Map;


/**
 * - since API version formats range from 
 * Azure ARM API date style (2018-01-01) to semver (1.2.3) 
 * and virtually any other text, this value tends to be an 
 * opaque string with the possibility of a modifier to indicate
 * that it is a range.
 * 
 * options: 
 * - prepend a dash or append a plus to indicate a range 
 * (ie, '2018-01-01+' or '-2019-01-01', or '1.0+' )
 * 
 * - semver-range style (ie, '^1.0.0' or '~1.0.0' )
 * 
 */
public class ApiVersion {

    /**
     * the actual api version string used in the API
     * (Required)
     * 
     */
    private String version;
    private ApiVersion.Range range;

    /**
     * the actual api version string used in the API
     * (Required)
     * 
     */
    public String getVersion() {
        return version;
    }

    /**
     * the actual api version string used in the API
     * (Required)
     * 
     */
    public void setVersion(String version) {
        this.version = version;
    }

    public ApiVersion.Range getRange() {
        return range;
    }

    public void setRange(ApiVersion.Range range) {
        this.range = range;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ApiVersion.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("version");
        sb.append('=');
        sb.append(((this.version == null)?"<null>":this.version));
        sb.append(',');
        sb.append("range");
        sb.append('=');
        sb.append(((this.range == null)?"<null>":this.range));
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
        result = ((result* 31)+((this.version == null)? 0 :this.version.hashCode()));
        result = ((result* 31)+((this.range == null)? 0 :this.range.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ApiVersion) == false) {
            return false;
        }
        ApiVersion rhs = ((ApiVersion) other);
        return (((this.version == rhs.version)||((this.version!= null)&&this.version.equals(rhs.version)))&&((this.range == rhs.range)||((this.range!= null)&&this.range.equals(rhs.range))));
    }

    public enum Range {

        __EMPTY__("+"),
        __EMPTY___("-");
        private final String value;
        private final static Map<String, ApiVersion.Range> CONSTANTS = new HashMap<String, ApiVersion.Range>();

        static {
            for (ApiVersion.Range c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Range(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static ApiVersion.Range fromValue(String value) {
            ApiVersion.Range constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
