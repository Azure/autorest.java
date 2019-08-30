package com.azure.autorest.mapper.model;

import java.util.List;
import java.util.Map;

public class Response {
    private String description;
    private List<Header> headers;
    private Map<String, MediaType> content;
}
