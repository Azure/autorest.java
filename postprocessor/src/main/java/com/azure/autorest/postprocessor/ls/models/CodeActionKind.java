package com.azure.autorest.postprocessor.ls.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CodeActionKind {
    /**
     * Base kind for quickfix actions: 'quickfix'
     */
    QUICK_FIX("quickfix"),

    /**
     * Base kind for refactoring actions: 'refactor'
     */
    REFACTOR("refactor"),

    /**
     * Base kind for refactoring extraction actions: 'refactor.extract'
     *
     * Example extract actions:
     *
     * - Extract method - Extract function - Extract variable - Extract interface
     * from class - ...
     */
    REFACTOR_EXTRACT("refactor.extract"),

    /**
     * Base kind for refactoring inline actions: 'refactor.inline'
     *
     * Example inline actions:
     *
     * - Inline function - Inline variable - Inline constant - ...
     */
    REFACTOR_INLINE("refactor.inline"),

    /**
     * Base kind for refactoring rewrite actions: 'refactor.rewrite'
     *
     * Example rewrite actions:
     *
     * - Convert JavaScript function to class - Add or remove parameter -
     * Encapsulate field - Make method static - Move method to base class - ...
     */
    REFACTOR_REWRITE("refactor.rewrite"),

    /**
     * Base kind for source actions: `source`
     *
     * Source code actions apply to the entire file.
     */
    SOURCE("source"),

    /**
     * Base kind for an organize imports source action: `source.organizeImports`
     */
    SOURCE_ORGANIZEIMPORTS("source.organizeImports");


    private String value;

    CodeActionKind(String value) {
        this.value = value;
    }

    @JsonCreator
    public static CodeActionKind fromString(String value) {
        CodeActionKind[] items = CodeActionKind.values();
        for (CodeActionKind item : items) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        return null;
    }

    @JsonValue
    @Override
    public String toString() {
        return value;
    }
}
