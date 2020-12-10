package com.azure.autorest.android.model;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.Proxy;

import java.util.List;
import java.util.Set;

public class AndroidMethodGroupClient extends MethodGroupClient {
    protected AndroidMethodGroupClient(String package_Keyword, String className,
                                       String interfaceName, List<String> implementedInterfaces,
                                       Proxy proxy, String serviceClientName,
                                       String variableType, String variableName,
                                       List<ClientMethod> clientMethods, List<IType> supportedInterfaces,
                                       String classBaseName) {
        super(package_Keyword, className,
                interfaceName, implementedInterfaces,
                proxy, serviceClientName,
                variableType, variableName,
                clientMethods, supportedInterfaces,
                classBaseName);
    }

    @Override
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports, JavaSettings settings) {
        if (settings.shouldGenerateClientInterfaces()) {
            imports.add(String.format("%1$s.%2$s", settings.getPackage(), getInterfaceName()));
        }

        for (IType type : super.getSupportedInterfaces()) {
            type.addImportsTo(imports, false);
        }

        getProxy().addImportsTo(imports, includeImplementationImports, settings);

        boolean hasPaging = false;
        for (ClientMethod clientMethod : getClientMethods()) {
            clientMethod.addImportsTo(imports, includeImplementationImports, settings);
            hasPaging = hasPaging || clientMethod.getType().name().contains("Paging");
        }

        if (hasPaging) {
            imports.add("com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever");
            imports.add("com.azure.android.core.http.responsepaging.PagedDataResponseRetriever");
            imports.add("com.azure.android.core.util.paging.PagedDataRetriever");
        }
    }

    public static class Builder extends MethodGroupClient.Builder {
        @Override
        public AndroidMethodGroupClient build() {
            return new AndroidMethodGroupClient(super.packageName,
                    super.className,
                    super.interfaceName,
                    super.implementedInterfaces,
                    super.proxy,
                    super.serviceClientName,
                    super.variableType,
                    super.variableName,
                    super.clientMethods,
                    super.supportedInterfaces,
                    super.classBaseName);
        }
    }
}
