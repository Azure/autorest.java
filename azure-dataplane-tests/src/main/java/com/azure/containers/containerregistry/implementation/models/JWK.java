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
 * A JSON web signature.
 */
@Fluent
public final class JWK implements JsonSerializable<JWK> {

    /*
     * JSON web key parameter
     */
    private JWKHeader jwk;

    /*
     * The algorithm used to sign or encrypt the JWT
     */
    private String alg;

    /**
     * Creates an instance of JWK class.
     */
    public JWK() {
    }

    /**
     * Get the jwk property: JSON web key parameter.
     *
     * @return the jwk value.
     */
    public JWKHeader getJwk() {
        return this.jwk;
    }

    /**
     * Set the jwk property: JSON web key parameter.
     *
     * @param jwk the jwk value to set.
     * @return the JWK object itself.
     */
    public JWK setJwk(JWKHeader jwk) {
        this.jwk = jwk;
        return this;
    }

    /**
     * Get the alg property: The algorithm used to sign or encrypt the JWT.
     *
     * @return the alg value.
     */
    public String getAlg() {
        return this.alg;
    }

    /**
     * Set the alg property: The algorithm used to sign or encrypt the JWT.
     *
     * @param alg the alg value to set.
     * @return the JWK object itself.
     */
    public JWK setAlg(String alg) {
        this.alg = alg;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("jwk", this.jwk);
        jsonWriter.writeStringField("alg", this.alg);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of JWK from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of JWK if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IOException If an error occurs while reading the JWK.
     */
    public static JWK fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            JWK deserializedJWK = new JWK();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("jwk".equals(fieldName)) {
                    deserializedJWK.jwk = JWKHeader.fromJson(reader);
                } else if ("alg".equals(fieldName)) {
                    deserializedJWK.alg = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            return deserializedJWK;
        });
    }
}
