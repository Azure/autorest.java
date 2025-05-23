// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.multipleinheritance.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The Feline model.
 */
@Fluent
public class Feline implements JsonSerializable<Feline> {
    /*
     * The meows property.
     */
    @Generated
    private Boolean meows;

    /*
     * The hisses property.
     */
    @Generated
    private Boolean hisses;

    /**
     * Creates an instance of Feline class.
     */
    @Generated
    public Feline() {
    }

    /**
     * Get the meows property: The meows property.
     * 
     * @return the meows value.
     */
    @Generated
    public Boolean isMeows() {
        return this.meows;
    }

    /**
     * Set the meows property: The meows property.
     * 
     * @param meows the meows value to set.
     * @return the Feline object itself.
     */
    @Generated
    public Feline setMeows(Boolean meows) {
        this.meows = meows;
        return this;
    }

    /**
     * Get the hisses property: The hisses property.
     * 
     * @return the hisses value.
     */
    @Generated
    public Boolean isHisses() {
        return this.hisses;
    }

    /**
     * Set the hisses property: The hisses property.
     * 
     * @param hisses the hisses value to set.
     * @return the Feline object itself.
     */
    @Generated
    public Feline setHisses(Boolean hisses) {
        this.hisses = hisses;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeBooleanField("meows", this.meows);
        jsonWriter.writeBooleanField("hisses", this.hisses);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Feline from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Feline if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IOException If an error occurs while reading the Feline.
     */
    @Generated
    public static Feline fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Feline deserializedFeline = new Feline();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("meows".equals(fieldName)) {
                    deserializedFeline.meows = reader.getNullable(JsonReader::getBoolean);
                } else if ("hisses".equals(fieldName)) {
                    deserializedFeline.hisses = reader.getNullable(JsonReader::getBoolean);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedFeline;
        });
    }
}
