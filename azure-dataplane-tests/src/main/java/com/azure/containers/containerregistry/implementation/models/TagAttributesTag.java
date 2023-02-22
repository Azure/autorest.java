// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.containers.containerregistry.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/** Tag. */
@Fluent
public final class TagAttributesTag implements JsonSerializable<TagAttributesTag> {
    /*
     * SignatureRecord value
     */
    private String signatureRecord;

    /** Creates an instance of TagAttributesTag class. */
    public TagAttributesTag() {}

    /**
     * Get the signatureRecord property: SignatureRecord value.
     *
     * @return the signatureRecord value.
     */
    public String getSignatureRecord() {
        return this.signatureRecord;
    }

    /**
     * Set the signatureRecord property: SignatureRecord value.
     *
     * @param signatureRecord the signatureRecord value to set.
     * @return the TagAttributesTag object itself.
     */
    public TagAttributesTag setSignatureRecord(String signatureRecord) {
        this.signatureRecord = signatureRecord;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("signatureRecord", this.signatureRecord);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of TagAttributesTag from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of TagAttributesTag if the JsonReader was pointing to an instance of it, or null if it was
     *     pointing to JSON null.
     * @throws IOException If an error occurs while reading the TagAttributesTag.
     */
    public static TagAttributesTag fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(
                reader -> {
                    String signatureRecord = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("signatureRecord".equals(fieldName)) {
                            signatureRecord = reader.getString();
                        } else {
                            reader.skipChildren();
                        }
                    }
                    TagAttributesTag deserializedValue = new TagAttributesTag();
                    deserializedValue.signatureRecord = signatureRecord;

                    return deserializedValue;
                });
    }
}
