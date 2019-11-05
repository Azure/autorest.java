
package com.azure.autorest.model.codemodel;

public class Metadata {

    /**
     * custom extensible metadata for individual language generators
     * (Required)
     *
     */
    private Languages language;
    private Protocols protocol;
    private DictionaryAny extensions;

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

    public DictionaryAny getExtensions() {
        return extensions;
    }

    public void setExtensions(DictionaryAny extensions) {
        this.extensions = extensions;
    }

}
