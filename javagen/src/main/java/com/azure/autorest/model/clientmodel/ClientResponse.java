package com.azure.autorest.model.clientmodel;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

/**
 * The response that is returned by a ClientMethod.
 */
public final class ClientResponse {
    private String name;
    private String packageName;
    private String description;
    private IType headersType;
    private IType bodyType;

    private ClientResponse(String name, String package_Keyword, String description, IType headersType, IType bodyType) {
        this.name = name;
        packageName = package_Keyword;
        this.description = description;
        this.headersType = headersType;
        this.bodyType = bodyType;
    }

    public String getName() {
        return name;
    }

    public String getPackage() {
        return packageName;
    }

    public String getDescription() {
        return description;
    }

    public IType getHeadersType() {
        return headersType;
    }

    public IType getBodyType() {
        return bodyType;
    }

    public static class Builder {
        private String name;
        private String packageName;
        private String description;
        private IType headersType;
        private IType bodyType;


        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPackage(String packageName) {
            this.packageName = packageName;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setHeadersType(IType headersType) {
            this.headersType = headersType;
            return this;
        }

        public Builder setBodyType(IType bodyType) {
            this.bodyType = bodyType;
            return this;
        }

        public ClientResponse build() {
            return new ClientResponse(name, packageName, description, headersType, bodyType);
        }
    }
}