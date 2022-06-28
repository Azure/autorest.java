// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import java.util.Objects;

public class MethodDocumentation {
    private String description;
    private String url;

    protected MethodDocumentation(String description, String url) {
        this.description = description;
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }


    @Override
    public String toString() {
        return "MethodDocumentation{" +
                "description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodDocumentation that = (MethodDocumentation) o;
        return Objects.equals(description, that.description) && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, url);
    }

    public static class Builder {

        protected String description;

        protected String url;

        /**
         * Sets the description of this MethodDocumentation.
         * @param description the description of this MethodDocumentation
         * @return the Builder itself
         */
        public MethodDocumentation.Builder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the url of this MethodDocumentation.
         * @param url of this MethodDocumentation
         * @return the Builder itself
         */
        public MethodDocumentation.Builder url(String url) {
            this.url = url;
            return this;
        }

        public MethodDocumentation build() {
            return new MethodDocumentation(
                    description,
                    url);
        }

    }
}
