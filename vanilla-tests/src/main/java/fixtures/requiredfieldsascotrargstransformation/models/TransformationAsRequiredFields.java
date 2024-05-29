// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.requiredfieldsascotrargstransformation.models;

import com.azure.core.annotation.Immutable;
import com.azure.core.util.Base64Url;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.DateTimeRfc1123;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The TransformationAsRequiredFields model.
 */
@Immutable
public class TransformationAsRequiredFields implements JsonSerializable<TransformationAsRequiredFields> {
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    /*
     * The rfc1123NonRequired property.
     */
    private DateTimeRfc1123 rfc1123NonRequired;

    /*
     * The rfc1123Required property.
     */
    private final DateTimeRfc1123 rfc1123Required;

    /*
     * The nameRequired property.
     */
    private final String nameRequired;

    /*
     * The urlBase64EncodedRequired property.
     */
    private final Base64Url urlBase64EncodedRequired;

    /*
     * The unixTimeLongRequired property.
     */
    private final long unixTimeLongRequired;

    /*
     * The unixTimeDateTimeRequired property.
     */
    private final OffsetDateTime unixTimeDateTimeRequired;

    /**
     * Creates an instance of TransformationAsRequiredFields class.
     * 
     * @param rfc1123Required the rfc1123Required value to set.
     * @param nameRequired the nameRequired value to set.
     * @param urlBase64EncodedRequired the urlBase64EncodedRequired value to set.
     * @param unixTimeLongRequired the unixTimeLongRequired value to set.
     * @param unixTimeDateTimeRequired the unixTimeDateTimeRequired value to set.
     */
    protected TransformationAsRequiredFields(OffsetDateTime rfc1123Required, String nameRequired,
        byte[] urlBase64EncodedRequired, OffsetDateTime unixTimeLongRequired, OffsetDateTime unixTimeDateTimeRequired) {
        if (rfc1123Required == null) {
            this.rfc1123Required = null;
        } else {
            this.rfc1123Required = new DateTimeRfc1123(rfc1123Required);
        }
        this.nameRequired = nameRequired;
        if (urlBase64EncodedRequired == null) {
            this.urlBase64EncodedRequired = null;
        } else {
            this.urlBase64EncodedRequired = Base64Url.encode(urlBase64EncodedRequired);
        }
        if (unixTimeLongRequired == null) {
            this.unixTimeLongRequired = 0L;
        } else {
            this.unixTimeLongRequired = unixTimeLongRequired.toEpochSecond();
        }
        this.unixTimeDateTimeRequired = unixTimeDateTimeRequired;
    }

    /**
     * Get the rfc1123NonRequired property: The rfc1123NonRequired property.
     * 
     * @return the rfc1123NonRequired value.
     */
    public OffsetDateTime getRfc1123NonRequired() {
        if (this.rfc1123NonRequired == null) {
            return null;
        }
        return this.rfc1123NonRequired.getDateTime();
    }

    /**
     * Set the rfc1123NonRequired property: The rfc1123NonRequired property.
     * 
     * @param rfc1123NonRequired the rfc1123NonRequired value to set.
     * @return the TransformationAsRequiredFields object itself.
     */
    public TransformationAsRequiredFields setRfc1123NonRequired(OffsetDateTime rfc1123NonRequired) {
        if (rfc1123NonRequired == null) {
            this.rfc1123NonRequired = null;
        } else {
            this.rfc1123NonRequired = new DateTimeRfc1123(rfc1123NonRequired);
        }
        return this;
    }

    /**
     * Get the rfc1123Required property: The rfc1123Required property.
     * 
     * @return the rfc1123Required value.
     */
    public OffsetDateTime getRfc1123Required() {
        if (this.rfc1123Required == null) {
            return null;
        }
        return this.rfc1123Required.getDateTime();
    }

    /**
     * Get the nameRequired property: The nameRequired property.
     * 
     * @return the nameRequired value.
     */
    public String getNameRequired() {
        return this.nameRequired;
    }

    /**
     * Get the urlBase64EncodedRequired property: The urlBase64EncodedRequired property.
     * 
     * @return the urlBase64EncodedRequired value.
     */
    public byte[] getUrlBase64EncodedRequired() {
        if (this.urlBase64EncodedRequired == null) {
            return EMPTY_BYTE_ARRAY;
        }
        return this.urlBase64EncodedRequired.decodedBytes();
    }

    /**
     * Get the unixTimeLongRequired property: The unixTimeLongRequired property.
     * 
     * @return the unixTimeLongRequired value.
     */
    public OffsetDateTime getUnixTimeLongRequired() {
        return OffsetDateTime.ofInstant(Instant.ofEpochSecond(this.unixTimeLongRequired), ZoneOffset.UTC);
    }

    /**
     * Get the unixTimeDateTimeRequired property: The unixTimeDateTimeRequired property.
     * 
     * @return the unixTimeDateTimeRequired value.
     */
    public OffsetDateTime getUnixTimeDateTimeRequired() {
        return this.unixTimeDateTimeRequired;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getRfc1123Required() == null) {
            throw new IllegalArgumentException(
                "Missing required property rfc1123Required in model TransformationAsRequiredFields");
        }
        if (getNameRequired() == null) {
            throw new IllegalArgumentException(
                "Missing required property nameRequired in model TransformationAsRequiredFields");
        }
        if (getUrlBase64EncodedRequired() == null) {
            throw new IllegalArgumentException(
                "Missing required property urlBase64EncodedRequired in model TransformationAsRequiredFields");
        }
        if (getUnixTimeLongRequired() == null) {
            throw new IllegalArgumentException(
                "Missing required property unixTimeLongRequired in model TransformationAsRequiredFields");
        }
        if (getUnixTimeDateTimeRequired() == null) {
            throw new IllegalArgumentException(
                "Missing required property unixTimeDateTimeRequired in model TransformationAsRequiredFields");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("rfc1123Required", Objects.toString(this.rfc1123Required, null));
        jsonWriter.writeStringField("nameRequired", this.nameRequired);
        jsonWriter.writeStringField("urlBase64EncodedRequired", Objects.toString(this.urlBase64EncodedRequired, null));
        jsonWriter.writeLongField("unixTimeLongRequired", this.unixTimeLongRequired);
        jsonWriter.writeStringField("unixTimeDateTimeRequired",
            this.unixTimeDateTimeRequired == null
                ? null
                : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.unixTimeDateTimeRequired));
        jsonWriter.writeStringField("rfc1123NonRequired", Objects.toString(this.rfc1123NonRequired, null));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of TransformationAsRequiredFields from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of TransformationAsRequiredFields if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the TransformationAsRequiredFields.
     */
    public static TransformationAsRequiredFields fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            boolean rfc1123RequiredFound = false;
            OffsetDateTime rfc1123Required = null;
            boolean nameRequiredFound = false;
            String nameRequired = null;
            boolean urlBase64EncodedRequiredFound = false;
            byte[] urlBase64EncodedRequired = EMPTY_BYTE_ARRAY;
            boolean unixTimeLongRequiredFound = false;
            OffsetDateTime unixTimeLongRequired = null;
            boolean unixTimeDateTimeRequiredFound = false;
            OffsetDateTime unixTimeDateTimeRequired = null;
            DateTimeRfc1123 rfc1123NonRequired = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("rfc1123Required".equals(fieldName)) {
                    DateTimeRfc1123 rfc1123RequiredHolder
                        = reader.getNullable(nonNullReader -> new DateTimeRfc1123(nonNullReader.getString()));
                    if (rfc1123RequiredHolder != null) {
                        rfc1123Required = rfc1123RequiredHolder.getDateTime();
                    }
                    rfc1123RequiredFound = true;
                } else if ("nameRequired".equals(fieldName)) {
                    nameRequired = reader.getString();
                    nameRequiredFound = true;
                } else if ("urlBase64EncodedRequired".equals(fieldName)) {
                    Base64Url urlBase64EncodedRequiredHolder
                        = reader.getNullable(nonNullReader -> new Base64Url(nonNullReader.getString()));
                    if (urlBase64EncodedRequiredHolder != null) {
                        urlBase64EncodedRequired = urlBase64EncodedRequiredHolder.decodedBytes();
                    }
                    urlBase64EncodedRequiredFound = true;
                } else if ("unixTimeLongRequired".equals(fieldName)) {
                    unixTimeLongRequired
                        = OffsetDateTime.ofInstant(Instant.ofEpochSecond(reader.getLong()), ZoneOffset.UTC);
                    unixTimeLongRequiredFound = true;
                } else if ("unixTimeDateTimeRequired".equals(fieldName)) {
                    unixTimeDateTimeRequired = reader
                        .getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()));
                    unixTimeDateTimeRequiredFound = true;
                } else if ("rfc1123NonRequired".equals(fieldName)) {
                    rfc1123NonRequired
                        = reader.getNullable(nonNullReader -> new DateTimeRfc1123(nonNullReader.getString()));
                } else {
                    reader.skipChildren();
                }
            }
            if (rfc1123RequiredFound
                && nameRequiredFound
                && urlBase64EncodedRequiredFound
                && unixTimeLongRequiredFound
                && unixTimeDateTimeRequiredFound) {
                TransformationAsRequiredFields deserializedTransformationAsRequiredFields
                    = new TransformationAsRequiredFields(rfc1123Required, nameRequired, urlBase64EncodedRequired,
                        unixTimeLongRequired, unixTimeDateTimeRequired);
                deserializedTransformationAsRequiredFields.rfc1123NonRequired = rfc1123NonRequired;

                return deserializedTransformationAsRequiredFields;
            }
            List<String> missingProperties = new ArrayList<>();
            if (!rfc1123RequiredFound) {
                missingProperties.add("rfc1123Required");
            }
            if (!nameRequiredFound) {
                missingProperties.add("nameRequired");
            }
            if (!urlBase64EncodedRequiredFound) {
                missingProperties.add("urlBase64EncodedRequired");
            }
            if (!unixTimeLongRequiredFound) {
                missingProperties.add("unixTimeLongRequired");
            }
            if (!unixTimeDateTimeRequiredFound) {
                missingProperties.add("unixTimeDateTimeRequired");
            }

            throw new IllegalStateException(
                "Missing required property/properties: " + String.join(", ", missingProperties));
        });
    }
}
