// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ImplementationDetails {

    public enum Usage {
        INPUT("input"),

        OUTPUT("output"),

        EXCEPTION("exception"),

        CONVENIENCE_METHOD("convenience-method");

        private final static Map<String, Usage> CONSTANTS = new HashMap<>();
        static {
            for (Usage c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }
        private final String value;

        Usage(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }

        public static Usage fromValue(String value) {
            Usage constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }
    }

    private final boolean implementationOnly;

    private final Set<Usage> usages;

    public boolean isImplementationOnly() {
        return implementationOnly;
    }

    public Set<Usage> getUsages() {
        return usages;
    }

    public boolean isConvenienceMethod() {
        return usages.contains(Usage.CONVENIENCE_METHOD);
    }

    protected ImplementationDetails(boolean implementationOnly, Set<Usage> usages) {
        this.implementationOnly = implementationOnly;
        this.usages = usages;
    }

    public static final class Builder {
        private boolean implementationOnly = false;
        private Set<Usage> usages = new HashSet<>();

        public Builder() {
        }

        public Builder implementationOnly(boolean implementationOnly) {
            this.implementationOnly = implementationOnly;
            return this;
        }

        public Builder usages(Set<Usage> usages) {
            this.usages = usages;
            return this;
        }

        public ImplementationDetails build() {
            return new ImplementationDetails(implementationOnly, usages);
        }
    }
}
