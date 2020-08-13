package com.azure.autorest.model.clientmodel;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

/**
 * The details of an exception type that is used by a client.
 */
public class ClientException {
    private final String name;
    private final String errorName;
    private final String package_Keyword;
    private final IType parentType;

    /**
     * Create a new ServiceException with the provided properties.
     * @param package_Keyword The package that this Exception will appear in.
     * @param name The name of the ServiceException type.
     * @param errorName The name of the error type contained by the ServiceException.
     * @param parentType The type of parent exception.
     */
    protected ClientException(String package_Keyword, String name, String errorName, IType parentType) {
        this.package_Keyword = package_Keyword;
        this.name = name;
        this.errorName = errorName;
        this.parentType = parentType;
    }

    /**
     * @return The name of the ServiceException type.
     */
    public final String getName() {
        return name;
    }

    /**
     * @return The name of the error type contained by the ServiceException.
     */
    public final String getErrorName() {
        return errorName;
    }

    /**
     * @return type of parent exception.The package that this Enum will appear in.
     */
    public final String getPackage() {
        return package_Keyword;
    }

    /**
     * @return The type of parent exception.
     */
    public IType getParentType() {
        return parentType;
    }

    /**
     * Builder for ClientException.
     */
    public static class Builder {
        protected String name;
        protected String errorName;
        protected String packageName;
        protected IType parentType = ClassType.HttpResponseException;

        /**
         * Sets exception name.
         * @param name exception name.
         * @return the Builder.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets error name.
         * @param errorName error name.
         * @return the Builder.
         */
        public Builder errorName(String errorName) {
            this.errorName = errorName;
            return this;
        }

        /**
         * Sets package name.
         * @param packageName package name.
         * @return the Builder.
         */
        public Builder packageName(String packageName) {
            this.packageName = packageName;
            return this;
        }

        /**
         * Sets parent exception IType.
         * @param parentType parent exception IType.
         * @return the Builder.
         */
        public Builder parentType(IType parentType) {
            this.parentType = parentType;
            return this;
        }

        /**
         * Builds ClientException
         * @return the new ClientException instance.
         */
        public ClientException build() {
            return new ClientException(packageName,
                    name,
                    errorName,
                    parentType);
        }
    }
}
