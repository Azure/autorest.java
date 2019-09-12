package com.azure.autorest.models.codemodel;

import java.util.List;

public class HttpOperationParameter {
    private ParameterLocation in;
    private boolean explode;
    private List<Encoding> encoding;
    private String meidaType;
    private EncodingStyle style;
    private boolean allowReserved;
}