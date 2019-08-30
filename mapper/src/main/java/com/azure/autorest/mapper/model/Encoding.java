package com.azure.autorest.mapper.model;

import java.util.List;

public class Encoding {
    private String key;
    private String contentType;
    private List<Header> headers;
    private QueueEncodingStyle style;
    private boolean explode;
    private boolean allowReserved;
}
