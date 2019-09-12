package com.azure.autorest.model.codemodel;

import java.util.List;

public class NewResponse {
    private String responseCode;
    private String description;
    private List<Header> headers;
    private Schema headerSchema;
    private List<String> mimeTypes;
    private Schema schema;
}