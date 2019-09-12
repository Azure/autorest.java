package com.azure.autorest.model.codemodel;

import java.util.List;
import java.util.Map;

public class Schema {
    private Map<String, SchemaDetails> details;
    private List<String> required;
    
    private List<Schema> allOf;
    private List<Schema> oneOf;
    private List<Schema> anyOf;
    private Map<String, Object> extensions;
    private boolean deprecated;
}
