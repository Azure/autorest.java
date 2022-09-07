// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstylexmlserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaders;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/** The XmlsGetHeadersHeaders model. */
@Fluent
public final class XmlsGetHeadersHeaders implements JsonSerializable<XmlsGetHeadersHeaders> {
    /*
     * The Custom-Header property.
     */
    private String customHeader;

    // HttpHeaders containing the raw property values.
    /**
     * Creates an instance of XmlsGetHeadersHeaders class.
     *
     * @param rawHeaders The raw HttpHeaders that will be used to create the property values.
     */
    public XmlsGetHeadersHeaders(HttpHeaders rawHeaders) {
        this.customHeader = rawHeaders.getValue("Custom-Header");
    }

    /**
     * Get the customHeader property: The Custom-Header property.
     *
     * @return the customHeader value.
     */
    public String getCustomHeader() {
        return this.customHeader;
    }

    /**
     * Set the customHeader property: The Custom-Header property.
     *
     * @param customHeader the customHeader value to set.
     * @return the XmlsGetHeadersHeaders object itself.
     */
    public XmlsGetHeadersHeaders setCustomHeader(String customHeader) {
        this.customHeader = customHeader;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("Custom-Header", this.customHeader);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of XmlsGetHeadersHeaders from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of XmlsGetHeadersHeaders if the JsonReader was pointing to an instance of it, or null if it
     *     was pointing to JSON null.
     */
    public static XmlsGetHeadersHeaders fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(
                reader -> {
                    String customHeader = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("Custom-Header".equals(fieldName)) {
                            customHeader = reader.getString();
                        } else {
                            reader.skipChildren();
                        }
                    }
                    XmlsGetHeadersHeaders deserializedValue = new XmlsGetHeadersHeaders();
                    deserializedValue.customHeader = customHeader;

                    return deserializedValue;
                });
    }
}
