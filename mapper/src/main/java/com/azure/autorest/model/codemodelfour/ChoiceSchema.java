package com.azure.autorest.model.codemodelfour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * a schema that represents a choice of several values (ie, an 'enum')
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "choiceType",
    "choices",
    "language",
    "summary",
    "example",
    "defaultValue",
    "serialization",
    "uid",
    "$key",
    "description",
    "apiVersions",
    "deprecated",
    "externalDocs",
    "protocol",
    "extensions"
})
public class ChoiceSchema {

    /**
     * the schema type
     * (Required)
     * 
     */
    @JsonProperty("type")
    @JsonPropertyDescription("the schema type")
    private Type type;
    /**
     * a Schema that represents a string value
     * (Required)
     * 
     */
    @JsonProperty("choiceType")
    @JsonPropertyDescription("a Schema that represents a string value")
    private StringSchema choiceType;
    /**
     * the possible choices for in the set
     * (Required)
     * 
     */
    @JsonProperty("choices")
    @JsonPropertyDescription("the possible choices for in the set")
    private List<ChoiceValue> choices = new ArrayList<ChoiceValue>();
    /**
     * custom extensible metadata for individual language generators
     * (Required)
     * 
     */
    @JsonProperty("language")
    @JsonPropertyDescription("custom extensible metadata for individual language generators")
    private LanguagesSchemaMetadata language;
    /**
     * a short description
     * 
     */
    @JsonProperty("summary")
    @JsonPropertyDescription("a short description")
    private String summary;
    /**
     * example information
     * 
     */
    @JsonProperty("example")
    @JsonPropertyDescription("example information")
    private Object example;
    /**
     * If the value isn't sent on the wire, the service will assume this
     * 
     */
    @JsonProperty("defaultValue")
    @JsonPropertyDescription("If the value isn't sent on the wire, the service will assume this")
    private Object defaultValue;
    /**
     * custom extensible metadata for individual serialization formats
     * (Required)
     * 
     */
    @JsonProperty("serialization")
    @JsonPropertyDescription("custom extensible metadata for individual serialization formats")
    private SerializationFormats serialization;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("uid")
    private String uid;
    /**
     * common name of the aspect -- in OAI3 this was typically the key in the parent dictionary
     * (Required)
     * 
     */
    @JsonProperty("$key")
    @JsonPropertyDescription("common name of the aspect -- in OAI3 this was typically the key in the parent dictionary")
    private String $key;
    /**
     * description of the aspect.
     * (Required)
     * 
     */
    @JsonProperty("description")
    @JsonPropertyDescription("description of the aspect.")
    private String description;
    /**
     * API versions that this applies to. Undefined means all versions
     * 
     */
    @JsonProperty("apiVersions")
    @JsonPropertyDescription("API versions that this applies to. Undefined means all versions")
    private List<ApiVersion> apiVersions = new ArrayList<ApiVersion>();
    /**
     * represents  deprecation information for a given aspect
     * 
     */
    @JsonProperty("deprecated")
    @JsonPropertyDescription("represents  deprecation information for a given aspect")
    private Deprecation deprecated;
    /**
     * a reference to external documentation
     * 
     */
    @JsonProperty("externalDocs")
    @JsonPropertyDescription("a reference to external documentation")
    private ExternalDocumentation externalDocs;
    /**
     * custom extensible metadata for individual protocols (ie, HTTP, etc)
     * (Required)
     * 
     */
    @JsonProperty("protocol")
    @JsonPropertyDescription("custom extensible metadata for individual protocols (ie, HTTP, etc)")
    private Protocols protocol;
    @JsonProperty("extensions")
    private DictionaryAny extensions;

    /**
     * the schema type
     * (Required)
     * 
     */
    @JsonProperty("type")
    public Type getType() {
        return type;
    }

    /**
     * the schema type
     * (Required)
     * 
     */
    @JsonProperty("type")
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * a Schema that represents a string value
     * (Required)
     * 
     */
    @JsonProperty("choiceType")
    public StringSchema getChoiceType() {
        return choiceType;
    }

    /**
     * a Schema that represents a string value
     * (Required)
     * 
     */
    @JsonProperty("choiceType")
    public void setChoiceType(StringSchema choiceType) {
        this.choiceType = choiceType;
    }

    /**
     * the possible choices for in the set
     * (Required)
     * 
     */
    @JsonProperty("choices")
    public List<ChoiceValue> getChoices() {
        return choices;
    }

    /**
     * the possible choices for in the set
     * (Required)
     * 
     */
    @JsonProperty("choices")
    public void setChoices(List<ChoiceValue> choices) {
        this.choices = choices;
    }

    /**
     * custom extensible metadata for individual language generators
     * (Required)
     * 
     */
    @JsonProperty("language")
    public LanguagesSchemaMetadata getLanguage() {
        return language;
    }

    /**
     * custom extensible metadata for individual language generators
     * (Required)
     * 
     */
    @JsonProperty("language")
    public void setLanguage(LanguagesSchemaMetadata language) {
        this.language = language;
    }

    /**
     * a short description
     * 
     */
    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    /**
     * a short description
     * 
     */
    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * example information
     * 
     */
    @JsonProperty("example")
    public Object getExample() {
        return example;
    }

    /**
     * example information
     * 
     */
    @JsonProperty("example")
    public void setExample(Object example) {
        this.example = example;
    }

    /**
     * If the value isn't sent on the wire, the service will assume this
     * 
     */
    @JsonProperty("defaultValue")
    public Object getDefaultValue() {
        return defaultValue;
    }

    /**
     * If the value isn't sent on the wire, the service will assume this
     * 
     */
    @JsonProperty("defaultValue")
    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * custom extensible metadata for individual serialization formats
     * (Required)
     * 
     */
    @JsonProperty("serialization")
    public SerializationFormats getSerialization() {
        return serialization;
    }

    /**
     * custom extensible metadata for individual serialization formats
     * (Required)
     * 
     */
    @JsonProperty("serialization")
    public void setSerialization(SerializationFormats serialization) {
        this.serialization = serialization;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("uid")
    public String getUid() {
        return uid;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("uid")
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * common name of the aspect -- in OAI3 this was typically the key in the parent dictionary
     * (Required)
     * 
     */
    @JsonProperty("$key")
    public String get$key() {
        return $key;
    }

    /**
     * common name of the aspect -- in OAI3 this was typically the key in the parent dictionary
     * (Required)
     * 
     */
    @JsonProperty("$key")
    public void set$key(String $key) {
        this.$key = $key;
    }

    /**
     * description of the aspect.
     * (Required)
     * 
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * description of the aspect.
     * (Required)
     * 
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * API versions that this applies to. Undefined means all versions
     * 
     */
    @JsonProperty("apiVersions")
    public List<ApiVersion> getApiVersions() {
        return apiVersions;
    }

    /**
     * API versions that this applies to. Undefined means all versions
     * 
     */
    @JsonProperty("apiVersions")
    public void setApiVersions(List<ApiVersion> apiVersions) {
        this.apiVersions = apiVersions;
    }

    /**
     * represents  deprecation information for a given aspect
     * 
     */
    @JsonProperty("deprecated")
    public Deprecation getDeprecated() {
        return deprecated;
    }

    /**
     * represents  deprecation information for a given aspect
     * 
     */
    @JsonProperty("deprecated")
    public void setDeprecated(Deprecation deprecated) {
        this.deprecated = deprecated;
    }

    /**
     * a reference to external documentation
     * 
     */
    @JsonProperty("externalDocs")
    public ExternalDocumentation getExternalDocs() {
        return externalDocs;
    }

    /**
     * a reference to external documentation
     * 
     */
    @JsonProperty("externalDocs")
    public void setExternalDocs(ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
    }

    /**
     * custom extensible metadata for individual protocols (ie, HTTP, etc)
     * (Required)
     * 
     */
    @JsonProperty("protocol")
    public Protocols getProtocol() {
        return protocol;
    }

    /**
     * custom extensible metadata for individual protocols (ie, HTTP, etc)
     * (Required)
     * 
     */
    @JsonProperty("protocol")
    public void setProtocol(Protocols protocol) {
        this.protocol = protocol;
    }

    @JsonProperty("extensions")
    public DictionaryAny getExtensions() {
        return extensions;
    }

    @JsonProperty("extensions")
    public void setExtensions(DictionaryAny extensions) {
        this.extensions = extensions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ChoiceSchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("choiceType");
        sb.append('=');
        sb.append(((this.choiceType == null)?"<null>":this.choiceType));
        sb.append(',');
        sb.append("choices");
        sb.append('=');
        sb.append(((this.choices == null)?"<null>":this.choices));
        sb.append(',');
        sb.append("language");
        sb.append('=');
        sb.append(((this.language == null)?"<null>":this.language));
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
        result = ((result* 31)+((this.choiceType == null)? 0 :this.choiceType.hashCode()));
        result = ((result* 31)+((this.serialization == null)? 0 :this.serialization.hashCode()));
        result = ((result* 31)+((this.uid == null)? 0 :this.uid.hashCode()));
        result = ((result* 31)+((this.protocol == null)? 0 :this.protocol.hashCode()));
        result = ((result* 31)+((this.extensions == null)? 0 :this.extensions.hashCode()));
        result = ((result* 31)+((this.apiVersions == null)? 0 :this.apiVersions.hashCode()));
        result = ((result* 31)+((this.externalDocs == null)? 0 :this.externalDocs.hashCode()));
        result = ((result* 31)+((this.choices == null)? 0 :this.choices.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ChoiceSchema) == false) {
            return false;
        }
        ChoiceSchema rhs = ((ChoiceSchema) other);
        return (((((((((((((((((this.summary == rhs.summary)||((this.summary!= null)&&this.summary.equals(rhs.summary)))&&((this.defaultValue == rhs.defaultValue)||((this.defaultValue!= null)&&this.defaultValue.equals(rhs.defaultValue))))&&((this.deprecated == rhs.deprecated)||((this.deprecated!= null)&&this.deprecated.equals(rhs.deprecated))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.language == rhs.language)||((this.language!= null)&&this.language.equals(rhs.language))))&&((this.$key == rhs.$key)||((this.$key!= null)&&this.$key.equals(rhs.$key))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.example == rhs.example)||((this.example!= null)&&this.example.equals(rhs.example))))&&((this.choiceType == rhs.choiceType)||((this.choiceType!= null)&&this.choiceType.equals(rhs.choiceType))))&&((this.serialization == rhs.serialization)||((this.serialization!= null)&&this.serialization.equals(rhs.serialization))))&&((this.uid == rhs.uid)||((this.uid!= null)&&this.uid.equals(rhs.uid))))&&((this.protocol == rhs.protocol)||((this.protocol!= null)&&this.protocol.equals(rhs.protocol))))&&((this.extensions == rhs.extensions)||((this.extensions!= null)&&this.extensions.equals(rhs.extensions))))&&((this.apiVersions == rhs.apiVersions)||((this.apiVersions!= null)&&this.apiVersions.equals(rhs.apiVersions))))&&((this.externalDocs == rhs.externalDocs)||((this.externalDocs!= null)&&this.externalDocs.equals(rhs.externalDocs))))&&((this.choices == rhs.choices)||((this.choices!= null)&&this.choices.equals(rhs.choices))));
    }

    public enum Type {

        CHOICE("choice");
        private final String value;
        private final static Map<String, Type> CONSTANTS = new HashMap<String, Type>();

        static {
            for (Type c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static Type fromValue(String value) {
            Type constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
