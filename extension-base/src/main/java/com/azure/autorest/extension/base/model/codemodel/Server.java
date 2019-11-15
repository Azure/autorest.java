
package com.azure.autorest.extension.base.model.codemodel;


import java.util.List;

/**
 * the bare-minimum fields for per-protocol metadata on a given aspect
 * 
 */
public class Server {
    private String url;
    private Languages language;
    private List<Value> variables;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Languages getLanguage() {
        return language;
    }

    public void setLanguage(Languages language) {
        this.language = language;
    }

    public List<Value> getVariables() {
        return variables;
    }

    public void setVariables(List<Value> variables) {
        this.variables = variables;
    }
}
