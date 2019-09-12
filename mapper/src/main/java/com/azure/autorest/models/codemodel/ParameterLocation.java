package com.azure.autorest.models.codemodel;

public enum ParameterLocation {
    URI("uri"),
    QUERY("query"),
    HEADER("header"),
    COOKIE("cookie"),
    PATH("path");

    private String location;

    private ParameterLocation(String location) {
        this.location = location;
    }
}
