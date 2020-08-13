package com.azure.autorest.android.model;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.Proxy;
import com.azure.autorest.model.clientmodel.ProxyMethod;

import java.util.List;
import java.util.Set;

public class AndroidProxy extends Proxy {
    protected AndroidProxy(String name, String clientTypeName, String baseURL, List<ProxyMethod> methods) {
        super(name, clientTypeName, baseURL, methods);
    }

    @Override
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports, JavaSettings settings) {
        for (ProxyMethod method : getMethods()) {
            method.addImportsTo(imports, includeImplementationImports, settings);
        }
    }
    public static final class Builder extends Proxy.Builder {
        public Proxy build() {
            return new AndroidProxy(super.name,
                    super.clientTypeName,
                    super.baseURL,
                    super.methods);
        }
    }
}
