// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.model.codemodel.DictionaryAny;

import java.util.Objects;

public class MethodDocumentation {
    private String description;
    private String url;
    private DictionaryAny extensions;

    protected MethodDocumentation(String description, String url, DictionaryAny extensions) {
        this.description = description;
        this.url = url;
        this.extensions = extensions;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public DictionaryAny getExtensions() {
        return extensions;
    }

    @Override
    public String toString() {
        return "MethodDocumentation{" +
                "description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", extensions=" + extensions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodDocumentation that = (MethodDocumentation) o;
        return Objects.equals(description, that.description) && Objects.equals(url, that.url) && Objects.equals(extensions, that.extensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, url, extensions);
    }

    public static class Builder {

        protected String description;

        protected String url;

        protected DictionaryAny extensions;

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

        /**
         * Sets the extensions of this MethodDocumentation.
         * @param extensions of this MethodDocumentation
         * @return the Builder itself
         */
        public MethodDocumentation.Builder extensions(DictionaryAny extensions) {
            this.extensions = extensions;
            return this;
        }

        public MethodDocumentation build() {
            return new MethodDocumentation(
                    description,
                    url,
                    extensions);
        }

    }
}
