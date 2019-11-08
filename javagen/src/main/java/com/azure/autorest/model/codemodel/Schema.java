
package com.azure.autorest.model.codemodel;

import com.azure.autorest.model.extensionmodel.XmsExtensions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schema {

    /**
     * custom extensible metadata for individual language generators
     * (Required)
     * 
     */
    private Languages language;
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
     * custom extensible metadata for individual protocols (ie, HTTP, etc)
     * (Required)
     * 
     */
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Schema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("language");
        sb.append('=');
        sb.append(((this.language == null)?"<null>":this.language));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("summary");
        sb.append('=');
        sb.append(((this.summary == null)?"<null>":this.summary));
        sb.append(',');
        sb.append("example");
        sb.append('=');
        sb.append(((this.example == null)?"<null>":this.example));
        sb.append(',');
        sb.append("defaultValue");
        sb.append('=');
        sb.append(((this.defaultValue == null)?"<null>":this.defaultValue));
        sb.append(',');
        sb.append("serialization");
        sb.append('=');
        sb.append(((this.serialization == null)?"<null>":this.serialization));
        sb.append(',');
        sb.append("uid");
        sb.append('=');
        sb.append(((this.uid == null)?"<null>":this.uid));
        sb.append(',');
        sb.append("$key");
        sb.append('=');
        sb.append(((this.$key == null)?"<null>":this.$key));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("apiVersions");
        sb.append('=');
        sb.append(((this.apiVersions == null)?"<null>":this.apiVersions));
        sb.append(',');
        sb.append("deprecated");
        sb.append('=');
        sb.append(((this.deprecated == null)?"<null>":this.deprecated));
        sb.append(',');
        sb.append("externalDocs");
        sb.append('=');
        sb.append(((this.externalDocs == null)?"<null>":this.externalDocs));
        sb.append(',');
        sb.append("protocol");
        sb.append('=');
        sb.append(((this.protocol == null)?"<null>":this.protocol));
        sb.append(',');
        sb.append("extensions");
        sb.append('=');
        sb.append(((this.extensions == null)?"<null>":this.extensions));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.summary == null)? 0 :this.summary.hashCode()));
        result = ((result* 31)+((this.defaultValue == null)? 0 :this.defaultValue.hashCode()));
        result = ((result* 31)+((this.deprecated == null)? 0 :this.deprecated.hashCode()));
        result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
        result = ((result* 31)+((this.language == null)? 0 :this.language.hashCode()));
        result = ((result* 31)+((this.$key == null)? 0 :this.$key.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.example == null)? 0 :this.example.hashCode()));
        result = ((result* 31)+((this.serialization == null)? 0 :this.serialization.hashCode()));
        result = ((result* 31)+((this.uid == null)? 0 :this.uid.hashCode()));
        result = ((result* 31)+((this.protocol == null)? 0 :this.protocol.hashCode()));
        result = ((result* 31)+((this.extensions == null)? 0 :this.extensions.hashCode()));
        result = ((result* 31)+((this.apiVersions == null)? 0 :this.apiVersions.hashCode()));
        result = ((result* 31)+((this.externalDocs == null)? 0 :this.externalDocs.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Schema) == false) {
            return false;
        }
        Schema rhs = ((Schema) other);
        return (((((((((((((((this.summary == rhs.summary)||((this.summary!= null)&&this.summary.equals(rhs.summary)))&&((this.defaultValue == rhs.defaultValue)||((this.defaultValue!= null)&&this.defaultValue.equals(rhs.defaultValue))))&&((this.deprecated == rhs.deprecated)||((this.deprecated!= null)&&this.deprecated.equals(rhs.deprecated))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.language == rhs.language)||((this.language!= null)&&this.language.equals(rhs.language))))&&((this.$key == rhs.$key)||((this.$key!= null)&&this.$key.equals(rhs.$key))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.example == rhs.example)||((this.example!= null)&&this.example.equals(rhs.example))))&&((this.serialization == rhs.serialization)||((this.serialization!= null)&&this.serialization.equals(rhs.serialization))))&&((this.uid == rhs.uid)||((this.uid!= null)&&this.uid.equals(rhs.uid))))&&((this.protocol == rhs.protocol)||((this.protocol!= null)&&this.protocol.equals(rhs.protocol))))&&((this.extensions == rhs.extensions)||((this.extensions!= null)&&this.extensions.equals(rhs.extensions))))&&((this.apiVersions == rhs.apiVersions)||((this.apiVersions!= null)&&this.apiVersions.equals(rhs.apiVersions))))&&((this.externalDocs == rhs.externalDocs)||((this.externalDocs!= null)&&this.externalDocs.equals(rhs.externalDocs))));
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
