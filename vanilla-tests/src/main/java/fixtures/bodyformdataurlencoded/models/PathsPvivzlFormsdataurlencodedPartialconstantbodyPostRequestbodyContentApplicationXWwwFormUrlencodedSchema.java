// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodyformdataurlencoded.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.util.logging.ClientLogger;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema model.
 */
@Fluent
public final class PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
    implements
    JsonSerializable<PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema> {
    /*
     * Constant part of a formdata body.
     */
    @Generated
    private final String grantType = "access_token";

    /*
     * Indicates the name of your Azure container registry.
     */
    @Generated
    private String service;

    /*
     * AAD access token, mandatory when grant_type is access_token_refresh_token or access_token.
     */
    @Generated
    private String aadAccessToken;

    /**
     * Creates an instance of
     * PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema class.
     */
    @Generated
    public PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema() {
    }

    /**
     * Get the grantType property: Constant part of a formdata body.
     * 
     * @return the grantType value.
     */
    @Generated
    public String getGrantType() {
        return this.grantType;
    }

    /**
     * Get the service property: Indicates the name of your Azure container registry.
     * 
     * @return the service value.
     */
    @Generated
    public String getService() {
        return this.service;
    }

    /**
     * Set the service property: Indicates the name of your Azure container registry.
     * 
     * @param service the service value to set.
     * @return the
     * PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema object
     * itself.
     */
    @Generated
    public PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
        setService(String service) {
        this.service = service;
        return this;
    }

    /**
     * Get the aadAccessToken property: AAD access token, mandatory when grant_type is access_token_refresh_token or
     * access_token.
     * 
     * @return the aadAccessToken value.
     */
    @Generated
    public String getAadAccessToken() {
        return this.aadAccessToken;
    }

    /**
     * Set the aadAccessToken property: AAD access token, mandatory when grant_type is access_token_refresh_token or
     * access_token.
     * 
     * @param aadAccessToken the aadAccessToken value to set.
     * @return the
     * PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema object
     * itself.
     */
    @Generated
    public PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
        setAadAccessToken(String aadAccessToken) {
        this.aadAccessToken = aadAccessToken;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getService() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException(
                    "Missing required property service in model PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema"));
        }
        if (getAadAccessToken() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException(
                    "Missing required property aadAccessToken in model PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema"));
        }
    }

    private static final ClientLogger LOGGER = new ClientLogger(
        PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema.class);

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("grant_type", this.grantType);
        jsonWriter.writeStringField("service", this.service);
        jsonWriter.writeStringField("access_token", this.aadAccessToken);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of
     * PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema from
     * the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of
     * PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema if the
     * JsonReader was pointing to an instance of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the
     * PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema.
     */
    @Generated
    public static
        PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
        fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema deserializedPathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
                = new PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("service".equals(fieldName)) {
                    deserializedPathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema.service
                        = reader.getString();
                } else if ("access_token".equals(fieldName)) {
                    deserializedPathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema.aadAccessToken
                        = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedPathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema;
        });
    }
}
