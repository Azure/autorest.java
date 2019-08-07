package com.azure.autorest.extension.base.implementation;

import com.azure.autorest.extension.base.ExtensionService;
import com.googlecode.jsonrpc4j.JsonRpcClient;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ExtensionServiceImpl implements ExtensionService {
    private final List<String> plugins;

    public ExtensionServiceImpl(List<String> plugins) {
        this.plugins = plugins;
    }

    public List<String> listPlugins() {
        return plugins;
    }

    @SuppressWarnings("unchecked")
    public void process(String plugin, String session) {
        JsonRpcClient client = new JsonRpcClient();
        try {
            List<String> items = (List<String>) client.invokeAndReadResponse(
                    "ListInputs",
                    new String[] { session, null },
                    getParameterizedType(List.class, String.class),
                    System.out,
                    System.in);
            if (items != null && items.size() > 0) {
                for (String item : items) {
                    client.invokeAndReadResponse(
                            "ReadFile",
                            new String[] { session, item },
                            String.class,
                            System.out,
                            System.in);
                    System.out.println(item);
                }
            }
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    private static final Type getParameterizedType(final Class<?> rawClass, final Type... genericTypes) {
        return new ParameterizedType() {
            public Type[] getActualTypeArguments() {
                return genericTypes;
            }

            public Type getRawType() {
                return rawClass;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }
}
