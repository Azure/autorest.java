package com.azure.autorest.mapper.model;

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
