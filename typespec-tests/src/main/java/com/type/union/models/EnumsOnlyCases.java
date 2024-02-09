// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.type.union.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.core.util.BinaryData;

import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;

import java.io.IOException;

/**
 * The EnumsOnlyCases model.
 */
@Immutable
public final class EnumsOnlyCases implements JsonSerializable<EnumsOnlyCases> {
    /*
     * This should be receive/send the left variant
     */
    @Generated
    private final BinaryData lr;

    /*
     * This should be receive/send the up variant
     */
    @Generated
    private final BinaryData ud;

    /**
     * Creates an instance of EnumsOnlyCases class.
     * 
     * @param lr the lr value to set.
     * @param ud the ud value to set.
     */
    @Generated
    public EnumsOnlyCases(BinaryData lr, BinaryData ud) {
        this.lr = lr;
        this.ud = ud;
    }

    /**
     * Get the lr property: This should be receive/send the left variant.
     * 
     * @return the lr value.
     */
    @Generated
    public BinaryData getLr() {
        return this.lr;
    }

    /**
     * Get the ud property: This should be receive/send the up variant.
     * 
     * @return the ud value.
     */
    @Generated
    public BinaryData getUd() {
        return this.ud;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeUntypedField("lr", this.lr.toObject(Object.class));
        jsonWriter.writeUntypedField("ud", this.ud.toObject(Object.class));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of EnumsOnlyCases from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of EnumsOnlyCases if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the EnumsOnlyCases.
     */
    public static EnumsOnlyCases fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BinaryData lr = null;
            BinaryData ud = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("lr".equals(fieldName)) {
                    lr = reader.getNullable(nonNullReader -> BinaryData.fromObject(nonNullReader.readUntyped()));
                } else if ("ud".equals(fieldName)) {
                    ud = reader.getNullable(nonNullReader -> BinaryData.fromObject(nonNullReader.readUntyped()));
                } else {
                    reader.skipChildren();
                }
            }
            return new EnumsOnlyCases(lr, ud);
        });
    }
}
