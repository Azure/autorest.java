// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Scheme {

    private Scheme.SecuritySchemeType type;

    // OAuth2
    private Set<String> scopes = new HashSet<>();

    // Key
    private String name;
    private String in;

    public Scheme.SecuritySchemeType getType() {
        return type;
    }

    public void setType(Scheme.SecuritySchemeType type) {
        this.type = type;
    }

    public Set<String> getScopes() {
        return scopes;
    }

    public void setScopes(Set<String> scopes) {
        this.scopes = scopes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public enum SecuritySchemeType {

        OAUTH2("OAuth2"),
        KEY("Key");

        private final String value;
        private final static Map<String, Scheme.SecuritySchemeType> CONSTANTS = new HashMap<>();

        static {
            for (Scheme.SecuritySchemeType c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        SecuritySchemeType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static Scheme.SecuritySchemeType fromValue(String value) {
            Scheme.SecuritySchemeType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }
}
