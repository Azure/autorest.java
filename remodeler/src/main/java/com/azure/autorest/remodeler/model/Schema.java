package com.azure.autorest.remodeler.model;

import java.util.List;
import java.util.Map;

public class Schema {
    private Map<String, SchemaDetails> details;
    private List<String> required;
    private List<Object> enums;
    private List<Schema> allOf;
    private List<Schema> oneOf;
    private List<Schema> anyOf;
    private Map<String, Object> extensions;
}
