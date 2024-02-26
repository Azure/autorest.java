// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.Objects;

/**
 * - since API version formats range from
 * Azure ARM API date style (2018-01-01) to semver (1.2.3)
 * and virtually any other text, this value tends to be an
 * opaque string with the possibility of a modifier to indicate
 * that it is a range.
 * <p>
 * options:
 * - prepend a dash or append a plus to indicate a range
 * (ie, '2018-01-01+' or '-2019-01-01', or '1.0+' )
 * <p>
 * - semver-range style (ie, '^1.0.0' or '~1.0.0' )
 */
public class ApiVersion {
    private String version;
    private ApiVersion.Range range;

    /**
     * Gets the API version string used in the API. (Required)
     *
     * @return The API version string used in the API.
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the API version string used in the API. (Required)
     *
     * @param version The API version string used in the API.
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Gets the range of the API version.
     *
     * @return The range of the API version.
     */
    public ApiVersion.Range getRange() {
        return range;
    }

    /**
     * Sets the range of the API version.
     *
     * @param range The range of the API version.
     */
    public void setRange(ApiVersion.Range range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return ApiVersion.class.getName() + "@" + Integer.toHexString(System.identityHashCode(this)) + "[version="
            + Objects.toString(version, "<null>") + ",range=" + Objects.toString(range, "<null>") + ']';
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, range);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ApiVersion)) {
            return false;
        }

        ApiVersion rhs = ((ApiVersion) other);
        return Objects.equals(version, rhs.version) && Objects.equals(range, rhs.range);
    }

    public enum Range {
        __EMPTY__("+"),
        __EMPTY___("-");
        private final String value;

        Range(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        /**
         * Gets the value of the range.
         *
         * @return The value of the range.
         */
        public String value() {
            return this.value;
        }

        public static ApiVersion.Range fromValue(String value) {
            if ("+".equals(value)) {
                return __EMPTY__;
            } else if ("-".equals(value)) {
                return __EMPTY___;
            } else {
                throw new IllegalArgumentException(value);
            }
        }
    }
}
