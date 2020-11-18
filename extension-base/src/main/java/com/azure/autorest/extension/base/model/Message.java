package com.azure.autorest.extension.base.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Message {
    @JsonProperty("Channel")
    public MessageChannel channel;
    @JsonProperty("Details")
    public Object details;
    @JsonProperty("Text")
    public String text;
    @JsonProperty("Key")
    public List<String> key;
    @JsonProperty("Source")
    public List<SourceLocation> source;

    public List<SourceLocation> getSource() {
        return source;
    }

    public List<String> getKey() {
        return key;
    }

    public Object getDetails() {
        return details;
    }

    public MessageChannel getChannel() {
        return channel;
    }

    public String getText() {
        return text;
    }

    public void setChannel(MessageChannel channel) {
        this.channel = channel;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public void setKey(List<String> key) {
        this.key = key;
    }

    public void setSource(List<SourceLocation> source) {
        this.source = source;
    }

    public void setText(String text) {
        this.text = text;
    }
}
