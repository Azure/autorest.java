// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.openai.implementation.models;

import com.generic.core.annotation.Fluent;
import com.generic.core.annotation.Generated;
import com.generic.json.JsonReader;
import com.generic.json.JsonSerializable;
import com.generic.json.JsonToken;
import com.generic.json.JsonWriter;
import com.openai.models.CreateImageRequestResponseFormat;
import com.openai.models.CreateImageRequestSize;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** The CreateImageVariationRequest model. */
@Fluent
public final class CreateImageVariationRequest implements JsonSerializable<CreateImageVariationRequest> {
    /*
     * The image to use as the basis for the variation(s). Must be a valid PNG file, less than 4MB,
     * and square.
     */
    @Generated private final byte[] image;

    /*
     * The number of images to generate. Must be between 1 and 10.
     */
    @Generated private Long n;

    /*
     * The size of the generated images. Must be one of `256x256`, `512x512`, or `1024x1024`.
     */
    @Generated private CreateImageRequestSize size;

    /*
     * The format in which the generated images are returned. Must be one of `url` or `b64_json`.
     */
    @Generated private CreateImageRequestResponseFormat responseFormat;

    /*
     * The user property.
     */
    @Generated private String user;

    /**
     * Creates an instance of CreateImageVariationRequest class.
     *
     * @param image the image value to set.
     */
    @Generated
    public CreateImageVariationRequest(byte[] image) {
        this.image = image;
    }

    /**
     * Get the image property: The image to use as the basis for the variation(s). Must be a valid PNG file, less than
     * 4MB, and square.
     *
     * @return the image value.
     */
    @Generated
    public byte[] getImage() {
        return this.image;
    }

    /**
     * Get the n property: The number of images to generate. Must be between 1 and 10.
     *
     * @return the n value.
     */
    @Generated
    public Long getN() {
        return this.n;
    }

    /**
     * Set the n property: The number of images to generate. Must be between 1 and 10.
     *
     * @param n the n value to set.
     * @return the CreateImageVariationRequest object itself.
     */
    @Generated
    public CreateImageVariationRequest setN(Long n) {
        this.n = n;
        return this;
    }

    /**
     * Get the size property: The size of the generated images. Must be one of `256x256`, `512x512`, or `1024x1024`.
     *
     * @return the size value.
     */
    @Generated
    public CreateImageRequestSize getSize() {
        return this.size;
    }

    /**
     * Set the size property: The size of the generated images. Must be one of `256x256`, `512x512`, or `1024x1024`.
     *
     * @param size the size value to set.
     * @return the CreateImageVariationRequest object itself.
     */
    @Generated
    public CreateImageVariationRequest setSize(CreateImageRequestSize size) {
        this.size = size;
        return this;
    }

    /**
     * Get the responseFormat property: The format in which the generated images are returned. Must be one of `url` or
     * `b64_json`.
     *
     * @return the responseFormat value.
     */
    @Generated
    public CreateImageRequestResponseFormat getResponseFormat() {
        return this.responseFormat;
    }

    /**
     * Set the responseFormat property: The format in which the generated images are returned. Must be one of `url` or
     * `b64_json`.
     *
     * @param responseFormat the responseFormat value to set.
     * @return the CreateImageVariationRequest object itself.
     */
    @Generated
    public CreateImageVariationRequest setResponseFormat(CreateImageRequestResponseFormat responseFormat) {
        this.responseFormat = responseFormat;
        return this;
    }

    /**
     * Get the user property: The user property.
     *
     * @return the user value.
     */
    @Generated
    public String getUser() {
        return this.user;
    }

    /**
     * Set the user property: The user property.
     *
     * @param user the user value to set.
     * @return the CreateImageVariationRequest object itself.
     */
    @Generated
    public CreateImageVariationRequest setUser(String user) {
        this.user = user;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeBinaryField("image", this.image);
        jsonWriter.writeNumberField("n", this.n);
        jsonWriter.writeStringField("size", Objects.toString(this.size, null));
        jsonWriter.writeStringField("response_format", Objects.toString(this.responseFormat, null));
        jsonWriter.writeStringField("user", this.user);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of CreateImageVariationRequest from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of CreateImageVariationRequest if the JsonReader was pointing to an instance of it, or null
     *     if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the CreateImageVariationRequest.
     */
    public static CreateImageVariationRequest fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(
                reader -> {
                    boolean imageFound = false;
                    byte[] image = null;
                    Long n = null;
                    CreateImageRequestSize size = null;
                    CreateImageRequestResponseFormat responseFormat = null;
                    String user = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("image".equals(fieldName)) {
                            image = reader.getBinary();
                            imageFound = true;
                        } else if ("n".equals(fieldName)) {
                            n = reader.getNullable(JsonReader::getLong);
                        } else if ("size".equals(fieldName)) {
                            size = CreateImageRequestSize.fromString(reader.getString());
                        } else if ("response_format".equals(fieldName)) {
                            responseFormat = CreateImageRequestResponseFormat.fromString(reader.getString());
                        } else if ("user".equals(fieldName)) {
                            user = reader.getString();
                        } else {
                            reader.skipChildren();
                        }
                    }
                    if (imageFound) {
                        CreateImageVariationRequest deserializedCreateImageVariationRequest =
                                new CreateImageVariationRequest(image);
                        deserializedCreateImageVariationRequest.n = n;
                        deserializedCreateImageVariationRequest.size = size;
                        deserializedCreateImageVariationRequest.responseFormat = responseFormat;
                        deserializedCreateImageVariationRequest.user = user;

                        return deserializedCreateImageVariationRequest;
                    }
                    List<String> missingProperties = new ArrayList<>();
                    if (!imageFound) {
                        missingProperties.add("image");
                    }

                    throw new IllegalStateException(
                            "Missing required property/properties: " + String.join(", ", missingProperties));
                });
    }
}
