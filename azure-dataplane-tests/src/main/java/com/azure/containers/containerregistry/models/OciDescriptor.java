// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.containers.containerregistry.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.List;

/** Docker V2 image layer descriptor including config and layers. */
@Fluent
public final class OciDescriptor implements JsonSerializable<OciDescriptor> {
    /*
     * Layer media type
     */
    private String mediaType;

    /*
     * Layer size
     */
    private Long sizeInBytes;

    /*
     * Layer digest
     */
    private String digest;

    /*
     * Specifies a list of URIs from which this object may be downloaded.
     */
    private List<String> urls;

    /*
     * Additional information provided through arbitrary metadata.
     */
    private OciAnnotations annotations;

    /** Creates an instance of OciDescriptor class. */
    public OciDescriptor() {}

    /**
     * Get the mediaType property: Layer media type.
     *
     * @return the mediaType value.
     */
    public String getMediaType() {
        return this.mediaType;
    }

    /**
     * Set the mediaType property: Layer media type.
     *
     * @param mediaType the mediaType value to set.
     * @return the OciDescriptor object itself.
     */
    public OciDescriptor setMediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    /**
     * Get the sizeInBytes property: Layer size.
     *
     * @return the sizeInBytes value.
     */
    public Long getSizeInBytes() {
        return this.sizeInBytes;
    }

    /**
     * Set the sizeInBytes property: Layer size.
     *
     * @param sizeInBytes the sizeInBytes value to set.
     * @return the OciDescriptor object itself.
     */
    public OciDescriptor setSizeInBytes(Long sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
        return this;
    }

    /**
     * Get the digest property: Layer digest.
     *
     * @return the digest value.
     */
    public String getDigest() {
        return this.digest;
    }

    /**
     * Set the digest property: Layer digest.
     *
     * @param digest the digest value to set.
     * @return the OciDescriptor object itself.
     */
    public OciDescriptor setDigest(String digest) {
        this.digest = digest;
        return this;
    }

    /**
     * Get the urls property: Specifies a list of URIs from which this object may be downloaded.
     *
     * @return the urls value.
     */
    public List<String> getUrls() {
        return this.urls;
    }

    /**
     * Set the urls property: Specifies a list of URIs from which this object may be downloaded.
     *
     * @param urls the urls value to set.
     * @return the OciDescriptor object itself.
     */
    public OciDescriptor setUrls(List<String> urls) {
        this.urls = urls;
        return this;
    }

    /**
     * Get the annotations property: Additional information provided through arbitrary metadata.
     *
     * @return the annotations value.
     */
    public OciAnnotations getAnnotations() {
        return this.annotations;
    }

    /**
     * Set the annotations property: Additional information provided through arbitrary metadata.
     *
     * @param annotations the annotations value to set.
     * @return the OciDescriptor object itself.
     */
    public OciDescriptor setAnnotations(OciAnnotations annotations) {
        this.annotations = annotations;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("mediaType", this.mediaType);
        jsonWriter.writeNumberField("size", this.sizeInBytes);
        jsonWriter.writeStringField("digest", this.digest);
        jsonWriter.writeArrayField("urls", this.urls, (writer, element) -> writer.writeString(element));
        jsonWriter.writeJsonField("annotations", this.annotations);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of OciDescriptor from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of OciDescriptor if the JsonReader was pointing to an instance of it, or null if it was
     *     pointing to JSON null.
     * @throws IOException If an error occurs while reading the OciDescriptor.
     */
    public static OciDescriptor fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(
                reader -> {
                    String mediaType = null;
                    Long sizeInBytes = null;
                    String digest = null;
                    List<String> urls = null;
                    OciAnnotations annotations = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("mediaType".equals(fieldName)) {
                            mediaType = reader.getString();
                        } else if ("size".equals(fieldName)) {
                            sizeInBytes = reader.getNullable(JsonReader::getLong);
                        } else if ("digest".equals(fieldName)) {
                            digest = reader.getString();
                        } else if ("urls".equals(fieldName)) {
                            urls = reader.readArray(reader1 -> reader1.getString());
                        } else if ("annotations".equals(fieldName)) {
                            annotations = OciAnnotations.fromJson(reader);
                        } else {
                            reader.skipChildren();
                        }
                    }
                    OciDescriptor deserializedValue = new OciDescriptor();
                    deserializedValue.mediaType = mediaType;
                    deserializedValue.sizeInBytes = sizeInBytes;
                    deserializedValue.digest = digest;
                    deserializedValue.urls = urls;
                    deserializedValue.annotations = annotations;

                    return deserializedValue;
                });
    }
}
