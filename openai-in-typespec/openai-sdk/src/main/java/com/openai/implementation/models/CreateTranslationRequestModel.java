// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.openai.implementation.models;

import com.generic.core.annotation.Generated;
import com.generic.core.models.ExpandableStringEnum;
import java.util.Collection;

/** Defines values for CreateTranslationRequestModel. */
public final class CreateTranslationRequestModel extends ExpandableStringEnum<CreateTranslationRequestModel> {
    /** Static value whisper-1 for CreateTranslationRequestModel. */
    @Generated public static final CreateTranslationRequestModel WHISPER_1 = fromString("whisper-1");

    /**
     * Creates a new instance of CreateTranslationRequestModel value.
     *
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public CreateTranslationRequestModel() {}

    /**
     * Creates or finds a CreateTranslationRequestModel from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding CreateTranslationRequestModel.
     */
    @Generated
    public static CreateTranslationRequestModel fromString(String name) {
        return fromString(name, CreateTranslationRequestModel.class);
    }

    /**
     * Gets known CreateTranslationRequestModel values.
     *
     * @return known CreateTranslationRequestModel values.
     */
    @Generated
    public static Collection<CreateTranslationRequestModel> values() {
        return values(CreateTranslationRequestModel.class);
    }
}
