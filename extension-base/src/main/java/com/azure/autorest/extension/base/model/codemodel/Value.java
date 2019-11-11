
package com.azure.autorest.extension.base.model.codemodel;

import java.util.ArrayList;
import java.util.List;

public class Value {

    /**
     * custom extensible metadata for individual language generators
     * (Required)
     *
     */
    private Schema schema;
    /**
     * all schema types
     * (Required)
     *
     */
    private boolean required;

    private boolean nullable;
    /**
     * common name of the aspect -- in OAI3 this was typically the key in the parent dictionary
     * (Required)
     *
     */
    private String $key;
    /**
     * description of the aspect.
     * (Required)
     *
     */
    private String description;
    /**
     *
     * (Required)
     *
     */
    private String uid;
    /**
     * a short description
     *
     */
    private String summary;
    /**
     * API versions that this applies to. Undefined means all versions
     *
     */
    private List<ApiVersion> apiVersions = new ArrayList<ApiVersion>();
    /**
     * represents  deprecation information for a given aspect
     *
     */
    private Deprecation deprecated;
    /**
     * a reference to external documentation
     *
     */
    private ExternalDocumentation externalDocs;
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
     * a short description
     *
     */
    public String getSummary() {
        return summary;
    }

    /**
     * a short description
     *
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     *
     * (Required)
     *
     */
    public String getUid() {
        return uid;
    }

    /**
     *
     * (Required)
     *
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * common name of the aspect -- in OAI3 this was typically the key in the parent dictionary
     * (Required)
     *
     */
    public String get$key() {
        return $key;
    }

    /**
     * common name of the aspect -- in OAI3 this was typically the key in the parent dictionary
     * (Required)
     *
     */
    public void set$key(String $key) {
        this.$key = $key;
    }

    /**
     * description of the aspect.
     * (Required)
     *
     */
    public String getDescription() {
        return description;
    }

    /**
     * description of the aspect.
     * (Required)
     *
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * API versions that this applies to. Undefined means all versions
     *
     */
    public List<ApiVersion> getApiVersions() {
        return apiVersions;
    }

    /**
     * API versions that this applies to. Undefined means all versions
     *
     */
    public void setApiVersions(List<ApiVersion> apiVersions) {
        this.apiVersions = apiVersions;
    }

    /**
     * represents  deprecation information for a given aspect
     *
     */
    public Deprecation getDeprecated() {
        return deprecated;
    }

    /**
     * represents  deprecation information for a given aspect
     *
     */
    public void setDeprecated(Deprecation deprecated) {
        this.deprecated = deprecated;
    }

    /**
     * a reference to external documentation
     *
     */
    public ExternalDocumentation getExternalDocs() {
        return externalDocs;
    }

    /**
     * a reference to external documentation
     *
     */
    public void setExternalDocs(ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
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

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }
}
