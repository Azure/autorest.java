package com.azure.autorest.extension.base;

import com.googlecode.jsonrpc4j.JsonRpcMethod;

import java.util.List;

public interface ExtensionService {
    @JsonRpcMethod("GetPluginNames")
    List<String> listPlugins();

    @JsonRpcMethod("Process")
    void process(String plugin, String session);
}
