// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.autorest.extension.base.util;

import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.WriteValueCallback;

import java.io.IOException;
import java.util.function.Supplier;

/**
 * Utility classes that help simplify repetitive code with {@code azure-json}.
 */
public final class JsonUtils {
    public static <T> T readObject(JsonReader jsonReader, Supplier<T> objectCreator, ReadObjectCallback<T> callback)
        throws IOException {
        return jsonReader.readObject(reader -> {
            T object = objectCreator.get();
            fieldReaderLoop(reader, (fieldName, r) -> callback.read(object, fieldName, r));
            return object;
        });
    }

    public static <T> T readEmptyObject(JsonReader jsonReader, Supplier<T> objectCreator) throws IOException {
        return jsonReader.readObject(reader -> {
            T object = objectCreator.get();
            fieldReaderLoop(reader, (fieldName, r) -> r.skipChildren());
            return object;
        });
    }

    public interface ReadObjectCallback<T> {
        void read(T object, String fieldName, JsonReader jsonReader) throws IOException;
    }

    /**
     * Helper method to iterate over the field of a JSON object.
     * <p>
     * This method will reader the passed {@code jsonReader} until the end of the object is reached. For each field it
     * will get the field name and iterate the reader to the next token. This method will then pass the field name and
     * reader to the {@code fieldConsumer} for it to consume the JSON field as needed for the object being read.
     *
     * @param jsonReader The JSON reader to read the object from.
     * @param fieldConsumer The consumer that will consume the field name and reader for each field in the object.
     * @throws IOException If an error occurs while reading the JSON object.
     */
    public static void fieldReaderLoop(JsonReader jsonReader, WriteValueCallback<String, JsonReader> fieldConsumer)
        throws IOException {
        while (jsonReader.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = jsonReader.getFieldName();
            jsonReader.nextToken();

            fieldConsumer.write(fieldName, jsonReader);
        }
    }

    private JsonUtils() {
    }
}
