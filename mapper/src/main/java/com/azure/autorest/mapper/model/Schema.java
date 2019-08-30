package com.azure.autorest.mapper.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class Schema {
    private Map<String, SchemaDetails> details;
    private List<String> required;
    
    @JsonProperty("enum")
    private List<Object> enums;
    private List<Schema> allOf;
    private List<Schema> oneOf;
    private List<Schema> anyOf;
    private Map<String, Object> extensions;
}
