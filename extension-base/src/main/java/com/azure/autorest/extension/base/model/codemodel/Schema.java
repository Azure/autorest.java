// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Represents a schema.
 */
public class Schema extends Metadata {
    private Schema.AllSchemaTypes type;
    private String summary;
    private Object example;
    private Object defaultValue;
    private SerializationFormats serialization;
    private Set<String> serializationFormats;
    private Set<SchemaContext> usage;
    private String uid;
    private String $key;
    private String description;
    private List<ApiVersion> apiVersions = new ArrayList<>();
    private Deprecation deprecated;
    private ExternalDocumentation externalDocs;

    /**
     * Gets the all schema types. (Required)
     *
     * @return The all schema types.
     */
    public Schema.AllSchemaTypes getType() {
        return type;
    }

    /**
     * Sets the all schema types. (Required)
     *
     * @param type The all schema types.
     */
    public void setType(Schema.AllSchemaTypes type) {
        this.type = type;
    }

    /**
     * Gets a short description.
     *
     * @return The short description.
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets a short description.
     *
     * @param summary The short description.
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Gets example information.
     *
     * @return The example information.
     */
    public Object getExample() {
        return example;
    }

    /**
     * Sets example information.
     *
     * @param example The example information.
     */
    public void setExample(Object example) {
        this.example = example;
    }

    /**
     * Gets the default value.
     *
     * @return The default value.
     */
    public Object getDefaultValue() {
        return defaultValue;
    }

    /**
     * Sets the default value.
     *
     * @param defaultValue The default value.
     */
    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Gets the serialization formats.
     *
     * @return The serialization formats.
     */
    public SerializationFormats getSerialization() {
        return serialization;
    }

    /**
     * Sets the serialization formats.
     *
     * @param serialization The serialization formats.
     */
    public void setSerialization(SerializationFormats serialization) {
        this.serialization = serialization;
    }

    /**
     * Gets the set of serialization formats this Schema is used with, ex. JSON, XML, etc.
     *
     * @return The serialization formats.
     */
    public Set<String> getSerializationFormats() {
        return serializationFormats;
    }

    /**
     * Sets the set of serialization formats this Schema is used with, ex. JSON, XML, etc.
     *
     * @param serializationFormats The serialization formats.
     */
    public void setSerializationFormats(Set<String> serializationFormats) {
        this.serializationFormats = serializationFormats;
    }

    /**
     * Gets the UID of the schema. (Required)
     *
     * @return The UID of the schema.
     */
    public String getUid() {
        return uid;
    }

    /**
     * Sets the UID of the schema. (Required)
     *
     * @param uid The UID of the schema.
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Gets the key of the schema. (Required)
     *
     * @return The key of the schema.
     */
    public String get$key() {
        return $key;
    }

    /**
     * Sets the key of the schema. (Required)
     *
     * @param $key The key of the schema.
     */
    public void set$key(String $key) {
        this.$key = $key;
    }

    /**
     * Gets the description of the schema. (Required)
     *
     * @return The description of the schema.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the schema. (Required)
     *
     * @param description The description of the schema.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the API versions that this applies to. Undefined means all versions.
     *
     * @return The API versions that this applies to. Undefined means all versions.
     */
    public List<ApiVersion> getApiVersions() {
        return apiVersions;
    }

    /**
     * Sets the API versions that this applies to. Undefined means all versions.
     *
     * @param apiVersions The API versions that this applies to. Undefined means all versions.
     */
    public void setApiVersions(List<ApiVersion> apiVersions) {
        this.apiVersions = apiVersions;
    }

    /**
     * Gets the deprecation information for the schema.
     *
     * @return The deprecation information for the schema.
     */
    public Deprecation getDeprecated() {
        return deprecated;
    }

    /**
     * Sets the deprecation information for the schema.
     *
     * @param deprecated The deprecation information for the schema.
     */
    public void setDeprecated(Deprecation deprecated) {
        this.deprecated = deprecated;
    }

    /**
     * Gets a reference to external documentation.
     *
     * @return A reference to external documentation.
     */
    public ExternalDocumentation getExternalDocs() {
        return externalDocs;
    }

    /**
     * Sets a reference to external documentation.
     *
     * @param externalDocs A reference to external documentation.
     */
    public void setExternalDocs(ExternalDocumentation externalDocs) {
        this.externalDocs = externalDocs;
    }

    /**
     * Gets the usage of the schema.
     *
     * @return The usage of the schema.
     */
    public Set<SchemaContext> getUsage() {
        return usage;
    }

    /**
     * Sets the usage of the schema.
     *
     * @param usage The usage of the schema.
     */
    public void setUsage(Set<SchemaContext> usage) {
        this.usage = usage;
    }

    /**
     * Represents all schema types.
     */
    public enum AllSchemaTypes {
        ANY("any"),
        ANY_OBJECT("any-object"),
        AND("and"),
        ARM_ID("arm-id"),
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
        TIME("time"),
        UNIXTIME("unixtime"),
        URI("uri"),
        UUID("uuid"),
        XOR("xor");
        private final String value;
        private final static Map<String, Schema.AllSchemaTypes> CONSTANTS = new HashMap<>();

        static {
            for (Schema.AllSchemaTypes c : values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        AllSchemaTypes(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        /**
         * Gets the value of the schema type.
         *
         * @return The value of the schema type.
         */
        public String value() {
            return this.value;
        }

        /**
         * Returns the enum constant of this type with the specified value.
         *
         * @param value The value of the constant.
         * @return The enum constant of this type with the specified value.
         * @throws IllegalArgumentException If the specified value does not map to one of the constants in the enum.
         */
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
