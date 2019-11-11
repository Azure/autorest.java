package com.azure.autorest.extension.base.model;

import java.util.List;

public class Message {
    public String channel;
    public Object details;
    public String text;
    public List<String> key;
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

    public String getChannel() {
        return channel;
    }

    public String getText() {
        return text;
    }

    public void setChannel(String channel) {
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
