package com.azure.autorest.model.codemodel;

import java.util.List;

public class Encoding {
    private String key;
    private String contentType;
    private List<Header> headers;
    private QueueEncodingStyle style;
    private boolean explode;
    private boolean allowReserved;
}
