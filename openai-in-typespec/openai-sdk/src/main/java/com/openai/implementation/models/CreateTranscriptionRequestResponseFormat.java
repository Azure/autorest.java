// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.openai.implementation.models;

import com.generic.core.annotation.Generated;
import com.generic.core.models.ExpandableStringEnum;
import java.util.Collection;

/**
 * Defines values for CreateTranscriptionRequestResponseFormat.
 */
public final class CreateTranscriptionRequestResponseFormat
    extends ExpandableStringEnum<CreateTranscriptionRequestResponseFormat> {
    /**
     * Static value json for CreateTranscriptionRequestResponseFormat.
     */
    @Generated
    public static final CreateTranscriptionRequestResponseFormat JSON = fromString("json");

    /**
     * Static value text for CreateTranscriptionRequestResponseFormat.
     */
    @Generated
    public static final CreateTranscriptionRequestResponseFormat TEXT = fromString("text");

    /**
     * Static value srt for CreateTranscriptionRequestResponseFormat.
     */
    @Generated
    public static final CreateTranscriptionRequestResponseFormat SRT = fromString("srt");

    /**
     * Static value verbose_json for CreateTranscriptionRequestResponseFormat.
     */
    @Generated
    public static final CreateTranscriptionRequestResponseFormat VERBOSE_JSON = fromString("verbose_json");

    /**
     * Static value vtt for CreateTranscriptionRequestResponseFormat.
     */
    @Generated
    public static final CreateTranscriptionRequestResponseFormat VTT = fromString("vtt");

    /**
     * Creates a new instance of CreateTranscriptionRequestResponseFormat value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public CreateTranscriptionRequestResponseFormat() {
    }

    /**
     * Creates or finds a CreateTranscriptionRequestResponseFormat from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding CreateTranscriptionRequestResponseFormat.
     */
    @Generated
    public static CreateTranscriptionRequestResponseFormat fromString(String name) {
        return fromString(name, CreateTranscriptionRequestResponseFormat.class);
    }

    /**
     * Gets known CreateTranscriptionRequestResponseFormat values.
     * 
     * @return known CreateTranscriptionRequestResponseFormat values.
     */
    @Generated
    public static Collection<CreateTranscriptionRequestResponseFormat> values() {
        return values(CreateTranscriptionRequestResponseFormat.class);
    }
}
