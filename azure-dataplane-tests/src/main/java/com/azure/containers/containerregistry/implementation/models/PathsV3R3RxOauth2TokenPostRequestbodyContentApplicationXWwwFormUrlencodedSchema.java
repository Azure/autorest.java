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
import java.util.Objects;

/**
 * The PathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema model.
 */
@Fluent
public final class PathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
    implements JsonSerializable<PathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema> {
    /*
     * Indicates the name of your Azure container registry.
     */
    private String service;

    /*
     * Which is expected to be a valid scope, and can be specified more than once for multiple scope requests. You
     * obtained this from the Www-Authenticate response header from the challenge.
     */
    private String scope;

    /*
     * Must be a valid ACR refresh token
     */
    private String acrRefreshToken;

    /*
     * Grant type is expected to be refresh_token
     */
    private TokenGrantType grantType = TokenGrantType.REFRESH_TOKEN;

    /**
     * Creates an instance of PathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema class.
     */
    public PathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema() {
    }

    /**
     * Get the service property: Indicates the name of your Azure container registry.
     * 
     * @return the service value.
     */
    public String getService() {
        return this.service;
    }

    /**
     * Set the service property: Indicates the name of your Azure container registry.
     * 
     * @param service the service value to set.
     * @return the PathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema object itself.
     */
    public PathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema setService(String service) {
        this.service = service;
        return this;
    }

    /**
     * Get the scope property: Which is expected to be a valid scope, and can be specified more than once for multiple
     * scope requests. You obtained this from the Www-Authenticate response header from the challenge.
     * 
     * @return the scope value.
     */
    public String getScope() {
        return this.scope;
    }

    /**
     * Set the scope property: Which is expected to be a valid scope, and can be specified more than once for multiple
     * scope requests. You obtained this from the Www-Authenticate response header from the challenge.
     * 
     * @param scope the scope value to set.
     * @return the PathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema object itself.
     */
    public PathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema setScope(String scope) {
        this.scope = scope;
        return this;
    }

    /**
     * Get the acrRefreshToken property: Must be a valid ACR refresh token.
     * 
     * @return the acrRefreshToken value.
     */
    public String getAcrRefreshToken() {
        return this.acrRefreshToken;
    }

    /**
     * Set the acrRefreshToken property: Must be a valid ACR refresh token.
     * 
     * @param acrRefreshToken the acrRefreshToken value to set.
     * @return the PathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema object itself.
     */
    public PathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
        setAcrRefreshToken(String acrRefreshToken) {
        this.acrRefreshToken = acrRefreshToken;
        return this;
    }

    /**
     * Get the grantType property: Grant type is expected to be refresh_token.
     * 
     * @return the grantType value.
     */
    public TokenGrantType getGrantType() {
        return this.grantType;
    }

    /**
     * Set the grantType property: Grant type is expected to be refresh_token.
     * 
     * @param grantType the grantType value to set.
     * @return the PathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema object itself.
     */
    public PathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
        setGrantType(TokenGrantType grantType) {
        this.grantType = grantType;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("service", this.service);
        jsonWriter.writeStringField("scope", this.scope);
        jsonWriter.writeStringField("refresh_token", this.acrRefreshToken);
        jsonWriter.writeStringField("grant_type", Objects.toString(this.grantType, null));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of PathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema from the
     * JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of PathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema if the
     * JsonReader was pointing to an instance of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the
     * PathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema.
     */
    public static PathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
        fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            PathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema deserializedPathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
                = new PathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("service".equals(fieldName)) {
                    deserializedPathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema.service
                        = reader.getString();
                } else if ("scope".equals(fieldName)) {
                    deserializedPathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema.scope
                        = reader.getString();
                } else if ("refresh_token".equals(fieldName)) {
                    deserializedPathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema.acrRefreshToken
                        = reader.getString();
                } else if ("grant_type".equals(fieldName)) {
                    deserializedPathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema.grantType
                        = TokenGrantType.fromString(reader.getString());
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedPathsV3R3RxOauth2TokenPostRequestbodyContentApplicationXWwwFormUrlencodedSchema;
        });
    }
}
