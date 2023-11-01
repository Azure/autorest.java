// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.openai.implementation.models;

import com.generic.core.annotation.Fluent;
import com.generic.core.annotation.Generated;
import com.generic.json.JsonReader;
import com.generic.json.JsonSerializable;
import com.generic.json.JsonToken;
import com.generic.json.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The CreateTranscriptionRequest model.
 */
@Fluent
public final class CreateTranscriptionRequest implements JsonSerializable<CreateTranscriptionRequest> {
    /*
     * The audio file object (not file name) to transcribe, in one of these formats: flac, mp3, mp4,
     * mpeg, mpga, m4a, ogg, wav, or webm.
     */
    @Generated
    private final byte[] file;

    /*
     * ID of the model to use. Only `whisper-1` is currently available.
     */
    @Generated
    private final CreateTranscriptionRequestModel model;

    /*
     * An optional text to guide the model's style or continue a previous audio segment. The
     * [prompt](/docs/guides/speech-to-text/prompting) should match the audio language.
     */
    @Generated
    private String prompt;

    /*
     * The format of the transcript output, in one of these options: json, text, srt, verbose_json, or
     * vtt.
     */
    @Generated
    private CreateTranscriptionRequestResponseFormat responseFormat;

    /*
     * The sampling temperature, between 0 and 1. Higher values like 0.8 will make the output more
     * random, while lower values like 0.2 will make it more focused and deterministic. If set to 0,
     * the model will use [log probability](https://en.wikipedia.org/wiki/Log_probability) to
     * automatically increase the temperature until certain thresholds are hit.
     */
    @Generated
    private Double temperature;

    /*
     * The language of the input audio. Supplying the input language in
     * [ISO-639-1](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes) format will improve accuracy
     * and latency.
     */
    @Generated
    private String language;

    /**
     * Creates an instance of CreateTranscriptionRequest class.
     * 
     * @param file the file value to set.
     * @param model the model value to set.
     */
    @Generated
    public CreateTranscriptionRequest(byte[] file, CreateTranscriptionRequestModel model) {
        this.file = file;
        this.model = model;
    }

    /**
     * Get the file property: The audio file object (not file name) to transcribe, in one of these formats: flac, mp3,
     * mp4,
     * mpeg, mpga, m4a, ogg, wav, or webm.
     * 
     * @return the file value.
     */
    @Generated
    public byte[] getFile() {
        return this.file;
    }

    /**
     * Get the model property: ID of the model to use. Only `whisper-1` is currently available.
     * 
     * @return the model value.
     */
    @Generated
    public CreateTranscriptionRequestModel getModel() {
        return this.model;
    }

    /**
     * Get the prompt property: An optional text to guide the model's style or continue a previous audio segment. The
     * [prompt](/docs/guides/speech-to-text/prompting) should match the audio language.
     * 
     * @return the prompt value.
     */
    @Generated
    public String getPrompt() {
        return this.prompt;
    }

    /**
     * Set the prompt property: An optional text to guide the model's style or continue a previous audio segment. The
     * [prompt](/docs/guides/speech-to-text/prompting) should match the audio language.
     * 
     * @param prompt the prompt value to set.
     * @return the CreateTranscriptionRequest object itself.
     */
    @Generated
    public CreateTranscriptionRequest setPrompt(String prompt) {
        this.prompt = prompt;
        return this;
    }

    /**
     * Get the responseFormat property: The format of the transcript output, in one of these options: json, text, srt,
     * verbose_json, or
     * vtt.
     * 
     * @return the responseFormat value.
     */
    @Generated
    public CreateTranscriptionRequestResponseFormat getResponseFormat() {
        return this.responseFormat;
    }

    /**
     * Set the responseFormat property: The format of the transcript output, in one of these options: json, text, srt,
     * verbose_json, or
     * vtt.
     * 
     * @param responseFormat the responseFormat value to set.
     * @return the CreateTranscriptionRequest object itself.
     */
    @Generated
    public CreateTranscriptionRequest setResponseFormat(CreateTranscriptionRequestResponseFormat responseFormat) {
        this.responseFormat = responseFormat;
        return this;
    }

    /**
     * Get the temperature property: The sampling temperature, between 0 and 1. Higher values like 0.8 will make the
     * output more
     * random, while lower values like 0.2 will make it more focused and deterministic. If set to 0,
     * the model will use [log probability](https://en.wikipedia.org/wiki/Log_probability) to
     * automatically increase the temperature until certain thresholds are hit.
     * 
     * @return the temperature value.
     */
    @Generated
    public Double getTemperature() {
        return this.temperature;
    }

    /**
     * Set the temperature property: The sampling temperature, between 0 and 1. Higher values like 0.8 will make the
     * output more
     * random, while lower values like 0.2 will make it more focused and deterministic. If set to 0,
     * the model will use [log probability](https://en.wikipedia.org/wiki/Log_probability) to
     * automatically increase the temperature until certain thresholds are hit.
     * 
     * @param temperature the temperature value to set.
     * @return the CreateTranscriptionRequest object itself.
     */
    @Generated
    public CreateTranscriptionRequest setTemperature(Double temperature) {
        this.temperature = temperature;
        return this;
    }

    /**
     * Get the language property: The language of the input audio. Supplying the input language in
     * [ISO-639-1](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes) format will improve accuracy
     * and latency.
     * 
     * @return the language value.
     */
    @Generated
    public String getLanguage() {
        return this.language;
    }

    /**
     * Set the language property: The language of the input audio. Supplying the input language in
     * [ISO-639-1](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes) format will improve accuracy
     * and latency.
     * 
     * @param language the language value to set.
     * @return the CreateTranscriptionRequest object itself.
     */
    @Generated
    public CreateTranscriptionRequest setLanguage(String language) {
        this.language = language;
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeBinaryField("file", this.file);
        jsonWriter.writeStringField("model", Objects.toString(this.model, null));
        jsonWriter.writeStringField("prompt", this.prompt);
        jsonWriter.writeStringField("response_format", Objects.toString(this.responseFormat, null));
        jsonWriter.writeNumberField("temperature", this.temperature);
        jsonWriter.writeStringField("language", this.language);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of CreateTranscriptionRequest from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of CreateTranscriptionRequest if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the CreateTranscriptionRequest.
     */
    public static CreateTranscriptionRequest fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            boolean fileFound = false;
            byte[] file = null;
            boolean modelFound = false;
            CreateTranscriptionRequestModel model = null;
            String prompt = null;
            CreateTranscriptionRequestResponseFormat responseFormat = null;
            Double temperature = null;
            String language = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("file".equals(fieldName)) {
                    file = reader.getBinary();
                    fileFound = true;
                } else if ("model".equals(fieldName)) {
                    model = CreateTranscriptionRequestModel.fromString(reader.getString());
                    modelFound = true;
                } else if ("prompt".equals(fieldName)) {
                    prompt = reader.getString();
                } else if ("response_format".equals(fieldName)) {
                    responseFormat = CreateTranscriptionRequestResponseFormat.fromString(reader.getString());
                } else if ("temperature".equals(fieldName)) {
                    temperature = reader.getNullable(JsonReader::getDouble);
                } else if ("language".equals(fieldName)) {
                    language = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            if (fileFound && modelFound) {
                CreateTranscriptionRequest deserializedCreateTranscriptionRequest
                    = new CreateTranscriptionRequest(file, model);
                deserializedCreateTranscriptionRequest.prompt = prompt;
                deserializedCreateTranscriptionRequest.responseFormat = responseFormat;
                deserializedCreateTranscriptionRequest.temperature = temperature;
                deserializedCreateTranscriptionRequest.language = language;

                return deserializedCreateTranscriptionRequest;
            }
            List<String> missingProperties = new ArrayList<>();
            if (!fileFound) {
                missingProperties.add("file");
            }
            if (!modelFound) {
                missingProperties.add("model");
            }

            throw new IllegalStateException(
                "Missing required property/properties: " + String.join(", ", missingProperties));
        });
    }
}
