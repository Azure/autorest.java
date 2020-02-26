
package com.azure.autorest.extension.base.model.codemodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schema extends Metadata {
    /**
     * all schema types
     * (Required)
     * 
     */
    private Schema.AllSchemaTypes type;
    /**
     * a short description
     * 
     */
    private String summary;
    /**
     * example information
     * 
     */
    private Object example;
    /**
     * If the value isn't sent on the wire, the service will assume this
     * 
     */
    private Object defaultValue;
    /**
     * custom extensible metadata for individual serialization formats
     * 
     */
    private SerializationFormats serialization;
    /**
     * 
     * (Required)
     * 
     */
    private String uid;
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
     * all schema types
     * (Required)
     * 
     */
    public Schema.AllSchemaTypes getType() {
        return type;
    }

    /**
     * all schema types
     * (Required)
     * 
     */
    public void setType(Schema.AllSchemaTypes type) {
        this.type = type;
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
     * example information
     * 
     */
    public Object getExample() {
        return example;
    }

    /**
     * example information
     * 
     */
    public void setExample(Object example) {
        this.example = example;
    }

    /**
     * If the value isn't sent on the wire, the service will assume this
     * 
     */
    public Object getDefaultValue() {
        return defaultValue;
    }

    /**
     * If the value isn't sent on the wire, the service will assume this
     * 
     */
    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * custom extensible metadata for individual serialization formats
     * 
     */
    public SerializationFormats getSerialization() {
        return serialization;
    }

    /**
     * custom extensible metadata for individual serialization formats
     * 
     */
    public void setSerialization(SerializationFormats serialization) {
        this.serialization = serialization;
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

    public enum AllSchemaTypes {
        ANY("any"),
        AND("and"),
        ARRAY("array"),
        BINARY("binary"),
        BOOLEAN("boolean"),
        BYTE_ARRAY("byte-array"),
        CHAR("char"),
        CHOICE("choice"),
        CONSTANT("constant"),
        CREDENTIAL("credential"),
        DATE("date"),
        DATE_TIME("date-time"),
        DICTIONARY("dictionary"),
        DURATION("duration"),
        FLAG("flag"),
        GROUP("group"),
        INTEGER("integer"),
        NOT("not"),
        NUMBER("number"),
        OBJECT("object"),
        ODATA_QUERY("odata-query"),
        OR("or"),
        PARAMETER_GROUP("parameter-group"),
        SEALED_CHOICE("sealed-choice"),
        STRING("string"),
        UNIXTIME("unixtime"),
        URI("uri"),
        UUID("uuid"),
        XOR("xor");
        private final String value;
        private final static Map<String, Schema.AllSchemaTypes> CONSTANTS = new HashMap<String, Schema.AllSchemaTypes>();

        static {
            for (Schema.AllSchemaTypes c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private AllSchemaTypes(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static Schema.AllSchemaTypes fromValue(String value) {
            Schema.AllSchemaTypes constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
