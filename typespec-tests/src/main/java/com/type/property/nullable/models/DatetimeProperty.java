// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.property.nullable.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import com.type.property.nullable.implementation.JsonMergePatchHelper;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * Model with a datetime property.
 */
@Fluent
public final class DatetimeProperty implements JsonSerializable<DatetimeProperty> {
    /*
     * Required property
     */
    @Generated
    private String requiredProperty;

    /*
     * Property
     */
    @Generated
    private OffsetDateTime nullableProperty;

    @Generated
    private boolean jsonMergePatch;

    @Generated
    boolean isJsonMergePatch() {
        return this.jsonMergePatch;
    }

    /**
     * Stores updated model property, the value is property name, not serialized name.
     */
    @Generated
    private final Set<String> updatedProperties = new HashSet<>();

    @Generated
    private void serializeAsJsonMergePatch(boolean jsonMergePatch) {
        this.jsonMergePatch = jsonMergePatch;
    }

    static {
        JsonMergePatchHelper.setDatetimePropertyAccessor((model, jsonMergePatchEnabled) -> {
            model.serializeAsJsonMergePatch(jsonMergePatchEnabled);
            return model;
        });
    }

    /**
     * Creates an instance of DatetimeProperty class.
     */
    @Generated
    public DatetimeProperty() {
    }

    /**
     * Get the requiredProperty property: Required property.
     * 
     * @return the requiredProperty value.
     */
    @Generated
    public String getRequiredProperty() {
        return this.requiredProperty;
    }

    /**
     * Set the requiredProperty property: Required property.
     * <p>Required when create the resource.</p>
     * 
     * @param requiredProperty the requiredProperty value to set.
     * @return the DatetimeProperty object itself.
     */
    @Generated
    public DatetimeProperty setRequiredProperty(String requiredProperty) {
        this.requiredProperty = requiredProperty;
        this.updatedProperties.add("requiredProperty");
        return this;
    }

    /**
     * Get the nullableProperty property: Property.
     * 
     * @return the nullableProperty value.
     */
    @Generated
    public OffsetDateTime getNullableProperty() {
        return this.nullableProperty;
    }

    /**
     * Set the nullableProperty property: Property.
     * <p>Required when create the resource.</p>
     * 
     * @param nullableProperty the nullableProperty value to set.
     * @return the DatetimeProperty object itself.
     */
    @Generated
    public DatetimeProperty setNullableProperty(OffsetDateTime nullableProperty) {
        this.nullableProperty = nullableProperty;
        this.updatedProperties.add("nullableProperty");
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        if (isJsonMergePatch()) {
            return toJsonMergePatch(jsonWriter);
        } else {
            jsonWriter.writeStartObject();
            jsonWriter.writeStringField("requiredProperty", this.requiredProperty);
            jsonWriter.writeStringField("nullableProperty",
                this.nullableProperty == null
                    ? null
                    : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.nullableProperty));
            return jsonWriter.writeEndObject();
        }
    }

    @Generated
    private JsonWriter toJsonMergePatch(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        if (updatedProperties.contains("requiredProperty")) {
            if (this.requiredProperty == null) {
                jsonWriter.writeNullField("requiredProperty");
            } else {
                jsonWriter.writeStringField("requiredProperty", this.requiredProperty);
            }
        }
        if (updatedProperties.contains("nullableProperty")) {
            if (this.nullableProperty == null) {
                jsonWriter.writeNullField("nullableProperty");
            } else {
                jsonWriter.writeStringField("nullableProperty",
                    this.nullableProperty == null
                        ? null
                        : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.nullableProperty));
            }
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of DatetimeProperty from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of DatetimeProperty if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the DatetimeProperty.
     */
    @Generated
    public static DatetimeProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            DatetimeProperty deserializedDatetimeProperty = new DatetimeProperty();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("requiredProperty".equals(fieldName)) {
                    deserializedDatetimeProperty.requiredProperty = reader.getString();
                } else if ("nullableProperty".equals(fieldName)) {
                    deserializedDatetimeProperty.nullableProperty
                        = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedDatetimeProperty;
        });
    }
}
