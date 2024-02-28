// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.formrecognizer.documentanalysis.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * An object representing the content and location of a field value.
 */
@Fluent
public final class DocumentField implements JsonSerializable<DocumentField> {
    /*
     * Data type of the field value.
     */
    private DocumentFieldType type;

    /*
     * String value.
     */
    private String valueString;

    /*
     * Date value in YYYY-MM-DD format (ISO 8601).
     */
    private LocalDate valueDate;

    /*
     * Time value in hh:mm:ss format (ISO 8601).
     */
    private String valueTime;

    /*
     * Phone number value in E.164 format (ex. +19876543210).
     */
    private String valuePhoneNumber;

    /*
     * Floating point value.
     */
    private Float valueNumber;

    /*
     * Integer value.
     */
    private Long valueInteger;

    /*
     * Selection mark value.
     */
    private SelectionMarkState valueSelectionMark;

    /*
     * Presence of signature.
     */
    private DocumentSignatureType valueSignature;

    /*
     * 3-letter country code value (ISO 3166-1 alpha-3).
     */
    private String valueCountryRegion;

    /*
     * Array of field values.
     */
    private List<DocumentField> valueArray;

    /*
     * Dictionary of named field values.
     */
    private Map<String, DocumentField> valueObject;

    /*
     * Currency value.
     */
    private CurrencyValue valueCurrency;

    /*
     * Address value.
     */
    private AddressValue valueAddress;

    /*
     * Field content.
     */
    private String content;

    /*
     * Bounding regions covering the field.
     */
    private List<BoundingRegion> boundingRegions;

    /*
     * Location of the field in the reading order concatenated content.
     */
    private List<DocumentSpan> spans;

    /*
     * Confidence of correctly extracting the field.
     */
    private Float confidence;

    /**
     * Creates an instance of DocumentField class.
     */
    public DocumentField() {
    }

    /**
     * Get the type property: Data type of the field value.
     * 
     * @return the type value.
     */
    public DocumentFieldType getType() {
        return this.type;
    }

    /**
     * Set the type property: Data type of the field value.
     * 
     * @param type the type value to set.
     * @return the DocumentField object itself.
     */
    public DocumentField setType(DocumentFieldType type) {
        this.type = type;
        return this;
    }

    /**
     * Get the valueString property: String value.
     * 
     * @return the valueString value.
     */
    public String getValueString() {
        return this.valueString;
    }

    /**
     * Set the valueString property: String value.
     * 
     * @param valueString the valueString value to set.
     * @return the DocumentField object itself.
     */
    public DocumentField setValueString(String valueString) {
        this.valueString = valueString;
        return this;
    }

    /**
     * Get the valueDate property: Date value in YYYY-MM-DD format (ISO 8601).
     * 
     * @return the valueDate value.
     */
    public LocalDate getValueDate() {
        return this.valueDate;
    }

    /**
     * Set the valueDate property: Date value in YYYY-MM-DD format (ISO 8601).
     * 
     * @param valueDate the valueDate value to set.
     * @return the DocumentField object itself.
     */
    public DocumentField setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
        return this;
    }

    /**
     * Get the valueTime property: Time value in hh:mm:ss format (ISO 8601).
     * 
     * @return the valueTime value.
     */
    public String getValueTime() {
        return this.valueTime;
    }

    /**
     * Set the valueTime property: Time value in hh:mm:ss format (ISO 8601).
     * 
     * @param valueTime the valueTime value to set.
     * @return the DocumentField object itself.
     */
    public DocumentField setValueTime(String valueTime) {
        this.valueTime = valueTime;
        return this;
    }

    /**
     * Get the valuePhoneNumber property: Phone number value in E.164 format (ex. +19876543210).
     * 
     * @return the valuePhoneNumber value.
     */
    public String getValuePhoneNumber() {
        return this.valuePhoneNumber;
    }

    /**
     * Set the valuePhoneNumber property: Phone number value in E.164 format (ex. +19876543210).
     * 
     * @param valuePhoneNumber the valuePhoneNumber value to set.
     * @return the DocumentField object itself.
     */
    public DocumentField setValuePhoneNumber(String valuePhoneNumber) {
        this.valuePhoneNumber = valuePhoneNumber;
        return this;
    }

    /**
     * Get the valueNumber property: Floating point value.
     * 
     * @return the valueNumber value.
     */
    public Float getValueNumber() {
        return this.valueNumber;
    }

    /**
     * Set the valueNumber property: Floating point value.
     * 
     * @param valueNumber the valueNumber value to set.
     * @return the DocumentField object itself.
     */
    public DocumentField setValueNumber(Float valueNumber) {
        this.valueNumber = valueNumber;
        return this;
    }

    /**
     * Get the valueInteger property: Integer value.
     * 
     * @return the valueInteger value.
     */
    public Long getValueInteger() {
        return this.valueInteger;
    }

    /**
     * Set the valueInteger property: Integer value.
     * 
     * @param valueInteger the valueInteger value to set.
     * @return the DocumentField object itself.
     */
    public DocumentField setValueInteger(Long valueInteger) {
        this.valueInteger = valueInteger;
        return this;
    }

    /**
     * Get the valueSelectionMark property: Selection mark value.
     * 
     * @return the valueSelectionMark value.
     */
    public SelectionMarkState getValueSelectionMark() {
        return this.valueSelectionMark;
    }

    /**
     * Set the valueSelectionMark property: Selection mark value.
     * 
     * @param valueSelectionMark the valueSelectionMark value to set.
     * @return the DocumentField object itself.
     */
    public DocumentField setValueSelectionMark(SelectionMarkState valueSelectionMark) {
        this.valueSelectionMark = valueSelectionMark;
        return this;
    }

    /**
     * Get the valueSignature property: Presence of signature.
     * 
     * @return the valueSignature value.
     */
    public DocumentSignatureType getValueSignature() {
        return this.valueSignature;
    }

    /**
     * Set the valueSignature property: Presence of signature.
     * 
     * @param valueSignature the valueSignature value to set.
     * @return the DocumentField object itself.
     */
    public DocumentField setValueSignature(DocumentSignatureType valueSignature) {
        this.valueSignature = valueSignature;
        return this;
    }

    /**
     * Get the valueCountryRegion property: 3-letter country code value (ISO 3166-1 alpha-3).
     * 
     * @return the valueCountryRegion value.
     */
    public String getValueCountryRegion() {
        return this.valueCountryRegion;
    }

    /**
     * Set the valueCountryRegion property: 3-letter country code value (ISO 3166-1 alpha-3).
     * 
     * @param valueCountryRegion the valueCountryRegion value to set.
     * @return the DocumentField object itself.
     */
    public DocumentField setValueCountryRegion(String valueCountryRegion) {
        this.valueCountryRegion = valueCountryRegion;
        return this;
    }

    /**
     * Get the valueArray property: Array of field values.
     * 
     * @return the valueArray value.
     */
    public List<DocumentField> getValueArray() {
        return this.valueArray;
    }

    /**
     * Set the valueArray property: Array of field values.
     * 
     * @param valueArray the valueArray value to set.
     * @return the DocumentField object itself.
     */
    public DocumentField setValueArray(List<DocumentField> valueArray) {
        this.valueArray = valueArray;
        return this;
    }

    /**
     * Get the valueObject property: Dictionary of named field values.
     * 
     * @return the valueObject value.
     */
    public Map<String, DocumentField> getValueObject() {
        return this.valueObject;
    }

    /**
     * Set the valueObject property: Dictionary of named field values.
     * 
     * @param valueObject the valueObject value to set.
     * @return the DocumentField object itself.
     */
    public DocumentField setValueObject(Map<String, DocumentField> valueObject) {
        this.valueObject = valueObject;
        return this;
    }

    /**
     * Get the valueCurrency property: Currency value.
     * 
     * @return the valueCurrency value.
     */
    public CurrencyValue getValueCurrency() {
        return this.valueCurrency;
    }

    /**
     * Set the valueCurrency property: Currency value.
     * 
     * @param valueCurrency the valueCurrency value to set.
     * @return the DocumentField object itself.
     */
    public DocumentField setValueCurrency(CurrencyValue valueCurrency) {
        this.valueCurrency = valueCurrency;
        return this;
    }

    /**
     * Get the valueAddress property: Address value.
     * 
     * @return the valueAddress value.
     */
    public AddressValue getValueAddress() {
        return this.valueAddress;
    }

    /**
     * Set the valueAddress property: Address value.
     * 
     * @param valueAddress the valueAddress value to set.
     * @return the DocumentField object itself.
     */
    public DocumentField setValueAddress(AddressValue valueAddress) {
        this.valueAddress = valueAddress;
        return this;
    }

    /**
     * Get the content property: Field content.
     * 
     * @return the content value.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Set the content property: Field content.
     * 
     * @param content the content value to set.
     * @return the DocumentField object itself.
     */
    public DocumentField setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * Get the boundingRegions property: Bounding regions covering the field.
     * 
     * @return the boundingRegions value.
     */
    public List<BoundingRegion> getBoundingRegions() {
        return this.boundingRegions;
    }

    /**
     * Set the boundingRegions property: Bounding regions covering the field.
     * 
     * @param boundingRegions the boundingRegions value to set.
     * @return the DocumentField object itself.
     */
    public DocumentField setBoundingRegions(List<BoundingRegion> boundingRegions) {
        this.boundingRegions = boundingRegions;
        return this;
    }

    /**
     * Get the spans property: Location of the field in the reading order concatenated content.
     * 
     * @return the spans value.
     */
    public List<DocumentSpan> getSpans() {
        return this.spans;
    }

    /**
     * Set the spans property: Location of the field in the reading order concatenated content.
     * 
     * @param spans the spans value to set.
     * @return the DocumentField object itself.
     */
    public DocumentField setSpans(List<DocumentSpan> spans) {
        this.spans = spans;
        return this;
    }

    /**
     * Get the confidence property: Confidence of correctly extracting the field.
     * 
     * @return the confidence value.
     */
    public Float getConfidence() {
        return this.confidence;
    }

    /**
     * Set the confidence property: Confidence of correctly extracting the field.
     * 
     * @param confidence the confidence value to set.
     * @return the DocumentField object itself.
     */
    public DocumentField setConfidence(Float confidence) {
        this.confidence = confidence;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("type", this.type == null ? null : this.type.toString());
        jsonWriter.writeStringField("valueString", this.valueString);
        jsonWriter.writeStringField("valueDate", Objects.toString(this.valueDate, null));
        jsonWriter.writeStringField("valueTime", this.valueTime);
        jsonWriter.writeStringField("valuePhoneNumber", this.valuePhoneNumber);
        jsonWriter.writeNumberField("valueNumber", this.valueNumber);
        jsonWriter.writeNumberField("valueInteger", this.valueInteger);
        jsonWriter.writeStringField("valueSelectionMark",
            this.valueSelectionMark == null ? null : this.valueSelectionMark.toString());
        jsonWriter.writeStringField("valueSignature",
            this.valueSignature == null ? null : this.valueSignature.toString());
        jsonWriter.writeStringField("valueCountryRegion", this.valueCountryRegion);
        jsonWriter.writeArrayField("valueArray", this.valueArray, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeMapField("valueObject", this.valueObject, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeJsonField("valueCurrency", this.valueCurrency);
        jsonWriter.writeJsonField("valueAddress", this.valueAddress);
        jsonWriter.writeStringField("content", this.content);
        jsonWriter.writeArrayField("boundingRegions", this.boundingRegions,
            (writer, element) -> writer.writeJson(element));
        jsonWriter.writeArrayField("spans", this.spans, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeNumberField("confidence", this.confidence);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of DocumentField from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of DocumentField if the JsonReader was pointing to an instance of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the DocumentField.
     */
    public static DocumentField fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            DocumentField deserializedDocumentField = new DocumentField();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("type".equals(fieldName)) {
                    deserializedDocumentField.type = DocumentFieldType.fromString(reader.getString());
                } else if ("valueString".equals(fieldName)) {
                    deserializedDocumentField.valueString = reader.getString();
                } else if ("valueDate".equals(fieldName)) {
                    deserializedDocumentField.valueDate
                        = reader.getNullable(nonNullReader -> LocalDate.parse(nonNullReader.getString()));
                } else if ("valueTime".equals(fieldName)) {
                    deserializedDocumentField.valueTime = reader.getString();
                } else if ("valuePhoneNumber".equals(fieldName)) {
                    deserializedDocumentField.valuePhoneNumber = reader.getString();
                } else if ("valueNumber".equals(fieldName)) {
                    deserializedDocumentField.valueNumber = reader.getNullable(JsonReader::getFloat);
                } else if ("valueInteger".equals(fieldName)) {
                    deserializedDocumentField.valueInteger = reader.getNullable(JsonReader::getLong);
                } else if ("valueSelectionMark".equals(fieldName)) {
                    deserializedDocumentField.valueSelectionMark = SelectionMarkState.fromString(reader.getString());
                } else if ("valueSignature".equals(fieldName)) {
                    deserializedDocumentField.valueSignature = DocumentSignatureType.fromString(reader.getString());
                } else if ("valueCountryRegion".equals(fieldName)) {
                    deserializedDocumentField.valueCountryRegion = reader.getString();
                } else if ("valueArray".equals(fieldName)) {
                    List<DocumentField> valueArray = reader.readArray(reader1 -> DocumentField.fromJson(reader1));
                    deserializedDocumentField.valueArray = valueArray;
                } else if ("valueObject".equals(fieldName)) {
                    Map<String, DocumentField> valueObject = reader.readMap(reader1 -> DocumentField.fromJson(reader1));
                    deserializedDocumentField.valueObject = valueObject;
                } else if ("valueCurrency".equals(fieldName)) {
                    deserializedDocumentField.valueCurrency = CurrencyValue.fromJson(reader);
                } else if ("valueAddress".equals(fieldName)) {
                    deserializedDocumentField.valueAddress = AddressValue.fromJson(reader);
                } else if ("content".equals(fieldName)) {
                    deserializedDocumentField.content = reader.getString();
                } else if ("boundingRegions".equals(fieldName)) {
                    List<BoundingRegion> boundingRegions
                        = reader.readArray(reader1 -> BoundingRegion.fromJson(reader1));
                    deserializedDocumentField.boundingRegions = boundingRegions;
                } else if ("spans".equals(fieldName)) {
                    List<DocumentSpan> spans = reader.readArray(reader1 -> DocumentSpan.fromJson(reader1));
                    deserializedDocumentField.spans = spans;
                } else if ("confidence".equals(fieldName)) {
                    deserializedDocumentField.confidence = reader.getNullable(JsonReader::getFloat);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedDocumentField;
        });
    }
}
