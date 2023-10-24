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

/**
 * Signature of a signed manifest.
 */
@Fluent
public final class ImageSignature implements JsonSerializable<ImageSignature> {
    /*
     * A JSON web signature
     */
    private JWK headerProperty;

    /*
     * A signature for the image manifest, signed by a libtrust private key
     */
    private String signature;

    /*
     * The signed protected header
     */
    private String protectedProperty;

    /**
     * Creates an instance of ImageSignature class.
     */
    public ImageSignature() {}

    /**
     * Get the headerProperty property: A JSON web signature.
     * 
     * @return the headerProperty value.
     */
    public JWK getHeaderProperty() {
        return this.headerProperty;
    }

    /**
     * Set the headerProperty property: A JSON web signature.
     * 
     * @param headerProperty the headerProperty value to set.
     * @return the ImageSignature object itself.
     */
    public ImageSignature setHeaderProperty(JWK headerProperty) {
        this.headerProperty = headerProperty;
        return this;
    }

    /**
     * Get the signature property: A signature for the image manifest, signed by a libtrust private key.
     * 
     * @return the signature value.
     */
    public String getSignature() {
        return this.signature;
    }

    /**
     * Set the signature property: A signature for the image manifest, signed by a libtrust private key.
     * 
     * @param signature the signature value to set.
     * @return the ImageSignature object itself.
     */
    public ImageSignature setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    /**
     * Get the protectedProperty property: The signed protected header.
     * 
     * @return the protectedProperty value.
     */
    public String getProtectedProperty() {
        return this.protectedProperty;
    }

    /**
     * Set the protectedProperty property: The signed protected header.
     * 
     * @param protectedProperty the protectedProperty value to set.
     * @return the ImageSignature object itself.
     */
    public ImageSignature setProtectedProperty(String protectedProperty) {
        this.protectedProperty = protectedProperty;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("header", this.headerProperty);
        jsonWriter.writeStringField("signature", this.signature);
        jsonWriter.writeStringField("protected", this.protectedProperty);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ImageSignature from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ImageSignature if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the ImageSignature.
     */
    public static ImageSignature fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ImageSignature deserializedImageSignature = new ImageSignature();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("header".equals(fieldName)) {
                    deserializedImageSignature.headerProperty = JWK.fromJson(reader);
                } else if ("signature".equals(fieldName)) {
                    deserializedImageSignature.signature = reader.getString();
                } else if ("protected".equals(fieldName)) {
                    deserializedImageSignature.protectedProperty = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedImageSignature;
        });
    }
}
