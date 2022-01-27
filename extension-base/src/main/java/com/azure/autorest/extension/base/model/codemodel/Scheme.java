package com.azure.autorest.extension.base.model.codemodel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Scheme {

    private Scheme.SecuritySchemeType type;

    private Set<String> scopes = new HashSet<>();

    private String headerName;

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

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public enum SecuritySchemeType {

        AADTOKEN("AADToken"),
        AZUREKEY("AzureKey"),
        ANONYMOUS("Anonymous");

        private final String value;
        private final static Map<String, Scheme.SecuritySchemeType> CONSTANTS = new HashMap<>();

        static {
            for (Scheme.SecuritySchemeType c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private SecuritySchemeType(String value) {
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
