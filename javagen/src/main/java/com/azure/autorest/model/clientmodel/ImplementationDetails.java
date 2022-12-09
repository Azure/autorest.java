// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.model.codemodel.SchemaContext;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * An instance on implementation details of method or model.
 */
public class ImplementationDetails {

    /**
     * Usage of the model or method. See {@link SchemaContext}.
     */
    public enum Usage {
        /**
         * Model used in input of operation.
         */
        INPUT("input"),

        /**
         * Model used in output of operation.
         */
        OUTPUT("output"),

        /**
         * Model used in error output of operation.
         */
        EXCEPTION("exception"),

        /**
         * Model used in input or output of methods marked as convenience API.
         * <p>
         * In DPG, it means the model need to be written to Java class.
         * Else, it may only exist in memory for Javadoc purpose.
         */
        CONVENIENCE_API("convenience-api"),

        /**
         * Model used in paged response.
         */
        PAGED("paged");

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

    /**
     * Usually on a method, that it is only required in implementation class (FooClientImpl),
     * but not in public class (FooClient).
     *
     * @return whether only required in implementation class.
     */
    public boolean isImplementationOnly() {
        return implementationOnly;
    }

    /**
     * @return the usage of the model or method.
     */
    public Set<Usage> getUsages() {
        return usages;
    }

    /**
     * @return whether the model used for convenience method, or the method requires a convenience method.
     */
    public boolean isConvenienceMethod() {
        return usages.contains(Usage.CONVENIENCE_API);
    }

    public boolean isInput() {
        return usages.contains(Usage.INPUT);
    }

    public boolean isOutput() {
        return usages.contains(Usage.OUTPUT);
    }

    public boolean isException() {
        return usages.contains(Usage.EXCEPTION);
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
