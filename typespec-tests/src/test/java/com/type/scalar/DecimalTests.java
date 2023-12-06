// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.scalar;

import com.azure.core.util.BinaryData;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.math.BigDecimal;

public class DecimalTests {

    public interface Getter {
        BigDecimal getProperty();
    }

    public static class DecimalStream implements JsonSerializable<DecimalStream>, Getter {
        private BigDecimal property;

        public BigDecimal getProperty() {
            return property;
        }

        public DecimalStream setProperty(BigDecimal property) {
            this.property = property;
            return this;
        }

        @Override
        public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
            jsonWriter.writeStartObject();
            jsonWriter.writeNumberField("property", this.property == null ? null : this.property);
            return jsonWriter.writeEndObject();
        }

        public static DecimalStream fromJson(JsonReader jsonReader) throws IOException {
            return jsonReader.readObject(reader -> {
                DecimalStream decimalProperty = new DecimalStream();
                while (reader.nextToken() != JsonToken.END_OBJECT) {
                    String fieldName = reader.getFieldName();
                    reader.nextToken();

                    if ("property".equals(fieldName)) {
                        BigDecimal property = reader.getNullable(nonNullReader -> new BigDecimal(nonNullReader.getString()));
                        decimalProperty.setProperty(property);
                    } else {
                        reader.skipChildren();
                    }
                }
                return decimalProperty;
            });
        }
    }

    public static class DecimalJackson implements Getter {
        @JsonProperty("property")
        private BigDecimal property;

        @Override
        public BigDecimal getProperty() {
            return property;
        }

        public void setProperty(BigDecimal property) {
            this.property = property;
        }
    }

    @ParameterizedTest
    @ValueSource(classes = {DecimalStream.class, DecimalJackson.class})
    public <T extends Getter> void testBigDecimal(Class<T> clazz) {
        // precision larger than double
        BigDecimal value = new BigDecimal("12345678901234567890.1234567890");
        String json = BinaryData.fromObject(new DecimalStream().setProperty(value)).toString();
        var test = BinaryData.fromString(json).toObject(clazz);
        Assertions.assertEquals(value, test.getProperty());

        // make sure precision difference would cause NotEquals
        Assertions.assertNotEquals(value, new BigDecimal("12345678901234567890.123456789"));

        // scientific
        value = new BigDecimal("1.2345678901234567890E23");
        json = BinaryData.fromObject(new DecimalStream().setProperty(value)).toString();
        test = BinaryData.fromString(json).toObject(clazz);
        Assertions.assertEquals(value, test.getProperty());

        value = new BigDecimal("-1.2345678901234567890e-105");
        json = BinaryData.fromObject(new DecimalStream().setProperty(value)).toString();
        test = BinaryData.fromString(json).toObject(clazz);
        Assertions.assertEquals(value, test.getProperty());
    }
}
