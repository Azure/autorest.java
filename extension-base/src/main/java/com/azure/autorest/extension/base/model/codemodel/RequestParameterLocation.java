// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.HashMap;
import java.util.Map;

/**
 The location of a parameter within a HTTP request.
*/
public enum RequestParameterLocation
{
    BODY("body"),

    COOKIE("cookie"),

    URI("uri"),

    PATH("path"),

    HEADER("header"),

    NONE("none"),

    QUERY("query");

    private final String value;
    private final static Map<String, RequestParameterLocation> CONSTANTS = new HashMap<>();

    static {
        for (RequestParameterLocation c: values()) {
            CONSTANTS.put(c.value, c);
        }
    }
    public int getValue()
    {
        return this.ordinal();
    }

    RequestParameterLocation(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if ("uri".equals(this.value)) {
            return "host";
        } else {
            return this.value;
        }
    }

    public String value() {
        return this.value;
    }

    public static RequestParameterLocation fromValue(String value) {
        RequestParameterLocation constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }
}
