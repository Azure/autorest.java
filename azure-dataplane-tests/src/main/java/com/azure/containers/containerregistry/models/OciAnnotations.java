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
import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Additional information provided through arbitrary metadata.
 */
@Fluent
public final class OciAnnotations implements JsonSerializable<OciAnnotations> {
    /*
     * Date and time on which the image was built (string, date-time as defined by
     * https://tools.ietf.org/html/rfc3339#section-5.6)
     */
    private OffsetDateTime createdOn;

    /*
     * Contact details of the people or organization responsible for the image.
     */
    private String authors;

    /*
     * URL to find more information on the image.
     */
    private String url;

    /*
     * URL to get documentation on the image.
     */
    private String documentation;

    /*
     * URL to get source code for building the image.
     */
    private String source;

    /*
     * Version of the packaged software. The version MAY match a label or tag in the source code repository, may also
     * be Semantic versioning-compatible
     */
    private String version;

    /*
     * Source control revision identifier for the packaged software.
     */
    private String revision;

    /*
     * Name of the distributing entity, organization or individual.
     */
    private String vendor;

    /*
     * License(s) under which contained software is distributed as an SPDX License Expression.
     */
    private String licenses;

    /*
     * Name of the reference for a target.
     */
    private String name;

    /*
     * Human-readable title of the image
     */
    private String title;

    /*
     * Human-readable description of the software packaged in the image
     */
    private String description;

    /*
     * Additional information provided through arbitrary metadata.
     */
    private Map<String, Object> additionalProperties;

    /**
     * Creates an instance of OciAnnotations class.
     */
    public OciAnnotations() {}

    /**
     * Get the createdOn property: Date and time on which the image was built (string, date-time as defined by
     * https://tools.ietf.org/html/rfc3339#section-5.6).
     * 
     * @return the createdOn value.
     */
    public OffsetDateTime getCreatedOn() {
        return this.createdOn;
    }

    /**
     * Set the createdOn property: Date and time on which the image was built (string, date-time as defined by
     * https://tools.ietf.org/html/rfc3339#section-5.6).
     * 
     * @param createdOn the createdOn value to set.
     * @return the OciAnnotations object itself.
     */
    public OciAnnotations setCreatedOn(OffsetDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    /**
     * Get the authors property: Contact details of the people or organization responsible for the image.
     * 
     * @return the authors value.
     */
    public String getAuthors() {
        return this.authors;
    }

    /**
     * Set the authors property: Contact details of the people or organization responsible for the image.
     * 
     * @param authors the authors value to set.
     * @return the OciAnnotations object itself.
     */
    public OciAnnotations setAuthors(String authors) {
        this.authors = authors;
        return this;
    }

    /**
     * Get the url property: URL to find more information on the image.
     * 
     * @return the url value.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Set the url property: URL to find more information on the image.
     * 
     * @param url the url value to set.
     * @return the OciAnnotations object itself.
     */
    public OciAnnotations setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * Get the documentation property: URL to get documentation on the image.
     * 
     * @return the documentation value.
     */
    public String getDocumentation() {
        return this.documentation;
    }

    /**
     * Set the documentation property: URL to get documentation on the image.
     * 
     * @param documentation the documentation value to set.
     * @return the OciAnnotations object itself.
     */
    public OciAnnotations setDocumentation(String documentation) {
        this.documentation = documentation;
        return this;
    }

    /**
     * Get the source property: URL to get source code for building the image.
     * 
     * @return the source value.
     */
    public String getSource() {
        return this.source;
    }

    /**
     * Set the source property: URL to get source code for building the image.
     * 
     * @param source the source value to set.
     * @return the OciAnnotations object itself.
     */
    public OciAnnotations setSource(String source) {
        this.source = source;
        return this;
    }

    /**
     * Get the version property: Version of the packaged software. The version MAY match a label or tag in the source
     * code repository, may also be Semantic versioning-compatible.
     * 
     * @return the version value.
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Set the version property: Version of the packaged software. The version MAY match a label or tag in the source
     * code repository, may also be Semantic versioning-compatible.
     * 
     * @param version the version value to set.
     * @return the OciAnnotations object itself.
     */
    public OciAnnotations setVersion(String version) {
        this.version = version;
        return this;
    }

    /**
     * Get the revision property: Source control revision identifier for the packaged software.
     * 
     * @return the revision value.
     */
    public String getRevision() {
        return this.revision;
    }

    /**
     * Set the revision property: Source control revision identifier for the packaged software.
     * 
     * @param revision the revision value to set.
     * @return the OciAnnotations object itself.
     */
    public OciAnnotations setRevision(String revision) {
        this.revision = revision;
        return this;
    }

    /**
     * Get the vendor property: Name of the distributing entity, organization or individual.
     * 
     * @return the vendor value.
     */
    public String getVendor() {
        return this.vendor;
    }

    /**
     * Set the vendor property: Name of the distributing entity, organization or individual.
     * 
     * @param vendor the vendor value to set.
     * @return the OciAnnotations object itself.
     */
    public OciAnnotations setVendor(String vendor) {
        this.vendor = vendor;
        return this;
    }

    /**
     * Get the licenses property: License(s) under which contained software is distributed as an SPDX License
     * Expression.
     * 
     * @return the licenses value.
     */
    public String getLicenses() {
        return this.licenses;
    }

    /**
     * Set the licenses property: License(s) under which contained software is distributed as an SPDX License
     * Expression.
     * 
     * @param licenses the licenses value to set.
     * @return the OciAnnotations object itself.
     */
    public OciAnnotations setLicenses(String licenses) {
        this.licenses = licenses;
        return this;
    }

    /**
     * Get the name property: Name of the reference for a target.
     * 
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: Name of the reference for a target.
     * 
     * @param name the name value to set.
     * @return the OciAnnotations object itself.
     */
    public OciAnnotations setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the title property: Human-readable title of the image.
     * 
     * @return the title value.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Set the title property: Human-readable title of the image.
     * 
     * @param title the title value to set.
     * @return the OciAnnotations object itself.
     */
    public OciAnnotations setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Get the description property: Human-readable description of the software packaged in the image.
     * 
     * @return the description value.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description property: Human-readable description of the software packaged in the image.
     * 
     * @param description the description value to set.
     * @return the OciAnnotations object itself.
     */
    public OciAnnotations setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get the additionalProperties property: Additional information provided through arbitrary metadata.
     * 
     * @return the additionalProperties value.
     */
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Set the additionalProperties property: Additional information provided through arbitrary metadata.
     * 
     * @param additionalProperties the additionalProperties value to set.
     * @return the OciAnnotations object itself.
     */
    public OciAnnotations setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("org.opencontainers.image.created", Objects.toString(this.createdOn, null));
        jsonWriter.writeStringField("org.opencontainers.image.authors", this.authors);
        jsonWriter.writeStringField("org.opencontainers.image.url", this.url);
        jsonWriter.writeStringField("org.opencontainers.image.documentation", this.documentation);
        jsonWriter.writeStringField("org.opencontainers.image.source", this.source);
        jsonWriter.writeStringField("org.opencontainers.image.version", this.version);
        jsonWriter.writeStringField("org.opencontainers.image.revision", this.revision);
        jsonWriter.writeStringField("org.opencontainers.image.vendor", this.vendor);
        jsonWriter.writeStringField("org.opencontainers.image.licenses", this.licenses);
        jsonWriter.writeStringField("org.opencontainers.image.ref.name", this.name);
        jsonWriter.writeStringField("org.opencontainers.image.title", this.title);
        jsonWriter.writeStringField("org.opencontainers.image.description", this.description);
        if (additionalProperties != null) {
            for (Map.Entry<String, Object> additionalProperty : additionalProperties.entrySet()) {
                jsonWriter.writeUntypedField(additionalProperty.getKey(), additionalProperty.getValue());
            }
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of OciAnnotations from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of OciAnnotations if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the OciAnnotations.
     */
    public static OciAnnotations fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            OciAnnotations deserializedOciAnnotations = new OciAnnotations();
            Map<String, Object> additionalProperties = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("org.opencontainers.image.created".equals(fieldName)) {
                    deserializedOciAnnotations.createdOn
                        = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else if ("org.opencontainers.image.authors".equals(fieldName)) {
                    deserializedOciAnnotations.authors = reader.getString();
                } else if ("org.opencontainers.image.url".equals(fieldName)) {
                    deserializedOciAnnotations.url = reader.getString();
                } else if ("org.opencontainers.image.documentation".equals(fieldName)) {
                    deserializedOciAnnotations.documentation = reader.getString();
                } else if ("org.opencontainers.image.source".equals(fieldName)) {
                    deserializedOciAnnotations.source = reader.getString();
                } else if ("org.opencontainers.image.version".equals(fieldName)) {
                    deserializedOciAnnotations.version = reader.getString();
                } else if ("org.opencontainers.image.revision".equals(fieldName)) {
                    deserializedOciAnnotations.revision = reader.getString();
                } else if ("org.opencontainers.image.vendor".equals(fieldName)) {
                    deserializedOciAnnotations.vendor = reader.getString();
                } else if ("org.opencontainers.image.licenses".equals(fieldName)) {
                    deserializedOciAnnotations.licenses = reader.getString();
                } else if ("org.opencontainers.image.ref.name".equals(fieldName)) {
                    deserializedOciAnnotations.name = reader.getString();
                } else if ("org.opencontainers.image.title".equals(fieldName)) {
                    deserializedOciAnnotations.title = reader.getString();
                } else if ("org.opencontainers.image.description".equals(fieldName)) {
                    deserializedOciAnnotations.description = reader.getString();
                } else {
                    if (additionalProperties == null) {
                        additionalProperties = new LinkedHashMap<>();
                    }

                    additionalProperties.put(fieldName, reader.readUntyped());
                }
            }
            deserializedOciAnnotations.additionalProperties = additionalProperties;

            return deserializedOciAnnotations;
        });
    }
}
