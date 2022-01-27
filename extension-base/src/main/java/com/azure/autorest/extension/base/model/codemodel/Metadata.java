// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import com.azure.autorest.extension.base.model.extensionmodel.XmsExtensions;

public class Metadata {

    /**
     * custom extensible metadata for individual language generators
     * (Required)
     *
     */
    private Languages language;
    private Protocols protocol;
    private XmsExtensions extensions;

    /**
     * custom extensible metadata for individual language generators
     * (Required)
     *
     */
    public Languages getLanguage() {
        return language;
    }

    /**
     * custom extensible metadata for individual language generators
     * (Required)
     *
     */
    public void setLanguage(Languages language) {
        this.language = language;
    }

    /**
     * custom extensible metadata for individual protocols (ie, HTTP, etc)
     * (Required)
     *
     */
    public Protocols getProtocol() {
        return protocol;
    }

    /**
     * custom extensible metadata for individual protocols (ie, HTTP, etc)
     * (Required)
     *
     */
    public void setProtocol(Protocols protocol) {
        this.protocol = protocol;
    }

    public XmsExtensions getExtensions() {
        return extensions;
    }

    public void setExtensions(XmsExtensions extensions) {
        this.extensions = extensions;
    }

}
