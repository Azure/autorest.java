// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

public enum ParameterSynthesizedOrigin {

    /**
     * host url parameter
     */
    HOST("modelerfour:synthesized/host"),

    /**
     * accept header
     */
    ACCEPT("modelerfour:synthesized/accept"),

    /**
     * content-type header
     */
    CONTENT_TYPE("modelerfour:synthesized/content-type"),

    /**
     * api-version (usually) query parameter
     */
    API_VERSION("modelerfour:synthesized/api-version"),

    /**
     * The parameter is not synthesized.
     */
    NONE("none");   // NONE is not defined as m4 output


    private final String origin;

    ParameterSynthesizedOrigin(String origin) {
        this.origin = origin;
    }

    public String getOrigin() {
        return this.origin;
    }

    @Override
    public String toString() {
        return this.getOrigin();
    }

    public static ParameterSynthesizedOrigin fromValue(String value) {
        if (value == null) {
            return NONE;
        }

        for (ParameterSynthesizedOrigin v : values()) {
            if (v.getOrigin().equalsIgnoreCase(value)) {
                return v;
            }
        }
        return NONE;
    }
}
