package com.azure.autorest.mapper.model;

public enum QueueEncodingStyle {
    FORM(EncodingStyle.FORM),
    SPACE_DELIMITED(EncodingStyle.SPACE_DELIMITED),
    PIPE_DELIMITED(EncodingStyle.PIPE_DELIMITED),
    DEEP_OBJECT(EncodingStyle.DEEP_OBJECT);

    private final EncodingStyle style;

    private QueueEncodingStyle(EncodingStyle style) {
        this.style = style;
    }
}
