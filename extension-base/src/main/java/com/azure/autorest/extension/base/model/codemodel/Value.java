
package com.azure.autorest.extension.base.model.codemodel;

import java.util.ArrayList;
import java.util.List;

public class Value extends Metadata {

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

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        // This should ideally be done by the modeler if the header collection prefix is set.
        // The schema should be of type dictionary
        // TODO: remove this when modeler is fixed.
        if (this.getExtensions() != null
                && this.getExtensions().getXmsHeaderCollectionPrefix() != null
                && schema instanceof StringSchema) {
            DictionarySchema dictionarySchema = new DictionarySchema();
            dictionarySchema.setElementType(schema);
            this.schema = dictionarySchema;
            // return;
        }
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
