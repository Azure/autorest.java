
package com.azure.autorest.extension.base.model.codemodel;


import java.util.List;

/**
 * the bare-minimum fields for per-protocol metadata on a given aspect
 * 
 */
public class Protocol {
    private RequestParameterLocation in;
    private String path;
    private String method;
    private String knownMediaType;
    private List<String> mediaTypes;
    private List<Server> servers;
    private List<String> statusCodes;
    private List<Header> headers;

    public RequestParameterLocation getIn() {
        return in;
    }

    public void setIn(RequestParameterLocation in) {
        this.in = in;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getKnownMediaType() {
        return knownMediaType;
    }

    public void setKnownMediaType(String knownMediaType) {
        this.knownMediaType = knownMediaType;
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public List<String> getMediaTypes() {
        return mediaTypes;
    }

    public void setMediaTypes(List<String> mediaTypes) {
        this.mediaTypes = mediaTypes;
    }

    public List<String> getStatusCodes() {
        return statusCodes;
    }

    public void setStatusCodes(List<String> statusCodes) {
        this.statusCodes = statusCodes;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }
}
