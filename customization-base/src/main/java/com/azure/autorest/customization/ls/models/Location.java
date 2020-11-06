package com.azure.autorest.customization.ls.models;

import java.net.URI;

public class Location {
    private URI uri;
    private Range range;

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }
}
