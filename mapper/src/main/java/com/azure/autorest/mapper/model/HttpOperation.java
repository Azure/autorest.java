package com.azure.autorest.mapper.model;

import com.azure.core.http.HttpMethod;

import java.util.List;
import java.util.Map;

public class HttpOperation {
    private String operationId;
    private String description;
    private boolean deprecated;
    private String summary;
    private List<String> tags;
    private RequestBody requestBody;
    private Map<String, List<NewResponse>> responses;
    private String path;
    private String baseUrl;
    private HttpMethod httpMethod;
    private String pathDescription;
    private String pathSummary;
    private Map<String, Object> pathExtensions;
}