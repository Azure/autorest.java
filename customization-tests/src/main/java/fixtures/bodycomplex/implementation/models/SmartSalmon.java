// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The SmartSalmon model.
 */
@Fluent
public final class SmartSalmon extends Salmon {
    /*
     * The fishtype property.
     */
    @Generated
    private String fishtype = "smart_salmon";

    /*
     * The college_degree property.
     */
    @Generated
    private String collegeDegree;

    /*
     * Dictionary of <any>
     */
    @Generated
    private Map<String, Object> additionalProperties;

    /**
     * Creates an instance of SmartSalmon class.
     */
    @Generated
    public SmartSalmon() {
    }

    /**
     * Get the fishtype property: The fishtype property.
     * 
     * @return the fishtype value.
     */
    @Generated
    @Override
    public String getFishtype() {
        return this.fishtype;
    }

    /**
     * Get the collegeDegree property: The college_degree property.
     * 
     * @return the collegeDegree value.
     */
    @Generated
    public String getCollegeDegree() {
        return this.collegeDegree;
    }

    /**
     * Set the collegeDegree property: The college_degree property.
     * 
     * @param collegeDegree the collegeDegree value to set.
     * @return the SmartSalmon object itself.
     */
    @Generated
    public SmartSalmon setCollegeDegree(String collegeDegree) {
        this.collegeDegree = collegeDegree;
        return this;
    }

    /**
     * Get the additionalProperties property: Dictionary of &lt;any&gt;.
     * 
     * @return the additionalProperties value.
     */
    @Generated
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Set the additionalProperties property: Dictionary of &lt;any&gt;.
     * 
     * @param additionalProperties the additionalProperties value to set.
     * @return the SmartSalmon object itself.
     */
    @Generated
    public SmartSalmon setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public SmartSalmon setLocation(String location) {
        super.setLocation(location);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public SmartSalmon setIswild(Boolean iswild) {
        super.setIswild(iswild);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public SmartSalmon setSpecies(String species) {
        super.setSpecies(species);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public SmartSalmon setLength(float length) {
        super.setLength(length);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public SmartSalmon setSiblings(List<Fish> siblings) {
        super.setSiblings(siblings);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeFloatField("length", getLength());
        jsonWriter.writeStringField("species", getSpecies());
        jsonWriter.writeArrayField("siblings", getSiblings(), (writer, element) -> writer.writeJson(element));
        jsonWriter.writeStringField("location", getLocation());
        jsonWriter.writeBooleanField("iswild", iswild());
        jsonWriter.writeStringField("fishtype", this.fishtype);
        jsonWriter.writeStringField("college_degree", this.collegeDegree);
        if (additionalProperties != null) {
            for (Map.Entry<String, Object> additionalProperty : additionalProperties.entrySet()) {
                jsonWriter.writeUntypedField(additionalProperty.getKey(), additionalProperty.getValue());
            }
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of SmartSalmon from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of SmartSalmon if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the SmartSalmon.
     */
    @Generated
    public static SmartSalmon fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            SmartSalmon deserializedSmartSalmon = new SmartSalmon();
            Map<String, Object> additionalProperties = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("length".equals(fieldName)) {
                    deserializedSmartSalmon.setLength(reader.getFloat());
                } else if ("species".equals(fieldName)) {
                    deserializedSmartSalmon.setSpecies(reader.getString());
                } else if ("siblings".equals(fieldName)) {
                    List<Fish> siblings = reader.readArray(reader1 -> Fish.fromJson(reader1));
                    deserializedSmartSalmon.setSiblings(siblings);
                } else if ("location".equals(fieldName)) {
                    deserializedSmartSalmon.setLocation(reader.getString());
                } else if ("iswild".equals(fieldName)) {
                    deserializedSmartSalmon.setIswild(reader.getNullable(JsonReader::getBoolean));
                } else if ("fishtype".equals(fieldName)) {
                    deserializedSmartSalmon.fishtype = reader.getString();
                } else if ("college_degree".equals(fieldName)) {
                    deserializedSmartSalmon.collegeDegree = reader.getString();
                } else {
                    if (additionalProperties == null) {
                        additionalProperties = new LinkedHashMap<>();
                    }

                    additionalProperties.put(fieldName, reader.readUntyped());
                }
            }
            deserializedSmartSalmon.additionalProperties = additionalProperties;

            return deserializedSmartSalmon;
        });
    }
}
