// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.requiredfieldsascotrargstransformation.models;

import com.azure.core.annotation.Immutable;
import com.azure.core.util.Base64Url;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.DateTimeRfc1123;
import com.azure.json.JsonReader;
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
 * The TransformationAsParentRequiredFields model.
 */
@Immutable
public final class TransformationAsParentRequiredFields extends TransformationAsRequiredFields {
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    /*
     * The rfc1123RequiredChild property.
     */
    private final DateTimeRfc1123 rfc1123RequiredChild;

    /*
     * The rfc1123NonRequiredChild property.
     */
    private DateTimeRfc1123 rfc1123NonRequiredChild;

    /*
     * The rfc1123NonRequired property.
     */
    private DateTimeRfc1123 rfc1123NonRequired;

    /**
     * Creates an instance of TransformationAsParentRequiredFields class.
     * 
     * @param rfc1123Required the rfc1123Required value to set.
     * @param nameRequired the nameRequired value to set.
     * @param urlBase64EncodedRequired the urlBase64EncodedRequired value to set.
     * @param unixTimeLongRequired the unixTimeLongRequired value to set.
     * @param unixTimeDateTimeRequired the unixTimeDateTimeRequired value to set.
     * @param rfc1123RequiredChild the rfc1123RequiredChild value to set.
     */
    private TransformationAsParentRequiredFields(OffsetDateTime rfc1123Required, String nameRequired,
        byte[] urlBase64EncodedRequired, OffsetDateTime unixTimeLongRequired, OffsetDateTime unixTimeDateTimeRequired,
        OffsetDateTime rfc1123RequiredChild) {
        super(rfc1123Required, nameRequired, urlBase64EncodedRequired, unixTimeLongRequired, unixTimeDateTimeRequired);
        if (rfc1123RequiredChild == null) {
            this.rfc1123RequiredChild = null;
        } else {
            this.rfc1123RequiredChild = new DateTimeRfc1123(rfc1123RequiredChild);
        }
    }

    /**
     * Get the rfc1123RequiredChild property: The rfc1123RequiredChild property.
     * 
     * @return the rfc1123RequiredChild value.
     */
    public OffsetDateTime getRfc1123RequiredChild() {
        if (this.rfc1123RequiredChild == null) {
            return null;
        }
        return this.rfc1123RequiredChild.getDateTime();
    }

    /**
     * Get the rfc1123NonRequiredChild property: The rfc1123NonRequiredChild property.
     * 
     * @return the rfc1123NonRequiredChild value.
     */
    public OffsetDateTime getRfc1123NonRequiredChild() {
        if (this.rfc1123NonRequiredChild == null) {
            return null;
        }
        return this.rfc1123NonRequiredChild.getDateTime();
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
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    @Override
    public void validate() {
        if (getRfc1123RequiredChild() == null) {
            throw new IllegalArgumentException(
                "Missing required property rfc1123RequiredChild in model TransformationAsParentRequiredFields");
        }
        if (getRfc1123Required() == null) {
            throw new IllegalArgumentException(
                "Missing required property rfc1123Required in model TransformationAsParentRequiredFields");
        }
        if (getNameRequired() == null) {
            throw new IllegalArgumentException(
                "Missing required property nameRequired in model TransformationAsParentRequiredFields");
        }
        if (getUrlBase64EncodedRequired() == null) {
            throw new IllegalArgumentException(
                "Missing required property urlBase64EncodedRequired in model TransformationAsParentRequiredFields");
        }
        if (getUnixTimeLongRequired() == null) {
            throw new IllegalArgumentException(
                "Missing required property unixTimeLongRequired in model TransformationAsParentRequiredFields");
        }
        if (getUnixTimeDateTimeRequired() == null) {
            throw new IllegalArgumentException(
                "Missing required property unixTimeDateTimeRequired in model TransformationAsParentRequiredFields");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        if (getRfc1123Required() != null) {
            jsonWriter.writeStringField("rfc1123Required",
                Objects.toString(new DateTimeRfc1123(getRfc1123Required()), null));
        }
        jsonWriter.writeStringField("nameRequired", getNameRequired());
        if (getUrlBase64EncodedRequired() != null) {
            jsonWriter.writeStringField("urlBase64EncodedRequired",
                Objects.toString(Base64Url.encode(getUrlBase64EncodedRequired()), null));
        }
        if (getUnixTimeLongRequired() != null) {
            jsonWriter.writeLongField("unixTimeLongRequired", getUnixTimeLongRequired().toEpochSecond());
        }
        jsonWriter.writeStringField("unixTimeDateTimeRequired",
            getUnixTimeDateTimeRequired() == null
                ? null
                : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(getUnixTimeDateTimeRequired()));
        if (getRfc1123NonRequired() != null) {
            jsonWriter.writeStringField("rfc1123NonRequired",
                Objects.toString(new DateTimeRfc1123(getRfc1123NonRequired()), null));
        }
        jsonWriter.writeStringField("rfc1123RequiredChild", Objects.toString(this.rfc1123RequiredChild, null));
        jsonWriter.writeStringField("rfc1123NonRequiredChild", Objects.toString(this.rfc1123NonRequiredChild, null));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of TransformationAsParentRequiredFields from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of TransformationAsParentRequiredFields if the JsonReader was pointing to an instance of it,
     * or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the TransformationAsParentRequiredFields.
     */
    public static TransformationAsParentRequiredFields fromJson(JsonReader jsonReader) throws IOException {
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
            boolean rfc1123RequiredChildFound = false;
            OffsetDateTime rfc1123RequiredChild = null;
            DateTimeRfc1123 rfc1123NonRequiredChild = null;
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
                } else if ("rfc1123RequiredChild".equals(fieldName)) {
                    DateTimeRfc1123 rfc1123RequiredChildHolder
                        = reader.getNullable(nonNullReader -> new DateTimeRfc1123(nonNullReader.getString()));
                    if (rfc1123RequiredChildHolder != null) {
                        rfc1123RequiredChild = rfc1123RequiredChildHolder.getDateTime();
                    }
                    rfc1123RequiredChildFound = true;
                } else if ("rfc1123NonRequiredChild".equals(fieldName)) {
                    rfc1123NonRequiredChild
                        = reader.getNullable(nonNullReader -> new DateTimeRfc1123(nonNullReader.getString()));
                } else {
                    reader.skipChildren();
                }
            }
            if (rfc1123RequiredFound
                && nameRequiredFound
                && urlBase64EncodedRequiredFound
                && unixTimeLongRequiredFound
                && unixTimeDateTimeRequiredFound
                && rfc1123RequiredChildFound) {
                TransformationAsParentRequiredFields deserializedTransformationAsParentRequiredFields
                    = new TransformationAsParentRequiredFields(rfc1123Required, nameRequired, urlBase64EncodedRequired,
                        unixTimeLongRequired, unixTimeDateTimeRequired, rfc1123RequiredChild);
                deserializedTransformationAsParentRequiredFields.rfc1123NonRequired = rfc1123NonRequired;
                deserializedTransformationAsParentRequiredFields.rfc1123NonRequiredChild = rfc1123NonRequiredChild;

                return deserializedTransformationAsParentRequiredFields;
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
            if (!rfc1123RequiredChildFound) {
                missingProperties.add("rfc1123RequiredChild");
            }

            throw new IllegalStateException(
                "Missing required property/properties: " + String.join(", ", missingProperties));
        });
    }
}
