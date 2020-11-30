package com.azure.autorest.extension.base.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MessageChannel {
    INFORMATION("information"),
    HINT("hint"),
    WARNING("warning"),
    DEBUG("debug"),
    VERBOSE("verbose"),
    ERROR("error"),
    FATAL("fatal"),
    FILE("file"),
    CONFIGURATION("configuration");

    private final String value;

    private MessageChannel(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }
}
