package com.azure.autorest.android.model;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.Constructor;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.Proxy;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.ServiceClientProperty;

import java.util.List;
import java.util.Set;

public class AndroidServiceClient extends ServiceClient {
    protected AndroidServiceClient(String packageName, String className,
                                   String interfaceName, Proxy proxy,
                                   List<MethodGroupClient> methodGroupClients,
                                   List<ServiceClientProperty> properties, List<Constructor> constructors,
                                   List<ClientMethod> clientMethods, ClientMethodParameter azureEnvironmentParameter,
                                   ClientMethodParameter tokenCredentialParameter, ClientMethodParameter httpPipelineParameter) {
        super(packageName, className,
                interfaceName, proxy,
                methodGroupClients, properties,
                constructors, clientMethods,
                azureEnvironmentParameter, tokenCredentialParameter,
                httpPipelineParameter);
    }

    public void addImportsTo(Set<String> imports,
                             boolean includeImplementationImports,
                             boolean includeBuilderImports,
                             JavaSettings settings) {
        if (!includeBuilderImports) {
            for (ClientMethod clientMethod : getClientMethods()) {
                clientMethod.addImportsTo(imports, includeImplementationImports, settings);
            }
        }
        imports.add("com.azure.android.core.http.responsepaging.AsyncPagedDataRetriever");
        imports.add("com.azure.android.core.http.responsepaging.PagedDataResponseRetriever");
        imports.add("com.azure.android.core.util.paging.PagedDataRetriever");

//        for (ClientMethod clientMethod : getClientMethods()) {
//            clientMethod.getProxyMethod()
//                    .getUnexpectedResponseExceptionType()
//                    .addImportsTo(imports, true);
//        }

        for (ServiceClientProperty serviceClientProperty : getProperties()) {
            serviceClientProperty.addImportsTo(imports, includeImplementationImports);
        }

        if (includeImplementationImports) {

            for (Constructor constructor : getConstructors()) {
                constructor.addImportsTo(imports, includeImplementationImports);
            }

            if (!settings.shouldGenerateClientInterfaces()) {
                for (MethodGroupClient methodGroupClient : getMethodGroupClients()) {
                    imports.add(String.format("%1$s.%2$s", methodGroupClient.getPackage(), methodGroupClient.getClassName()));
                }
            }
        }

        if (includeBuilderImports || includeImplementationImports) {
            if (settings.shouldGenerateClientInterfaces()) {
                imports.add(String.format("%1$s.%2$s", settings.getPackage(), getInterfaceName()));
                for (MethodGroupClient methodGroupClient : getMethodGroupClients()) {
                    imports.add(String.format("%1$s.%2$s", settings.getPackage(), methodGroupClient.getInterfaceName()));
                }
            }
        }

        if (includeBuilderImports) {
            imports.add(String.format("%1$s.%2$s", getPackage(), getClassName()));
        }

        Proxy proxy = getProxy();
        if (proxy != null) {
            proxy.addImportsTo(imports, includeImplementationImports, settings);
        }
    }

    public static class Builder extends ServiceClient.Builder {
        public AndroidServiceClient build() {
            return new AndroidServiceClient(super.packageName, super.className,
                    super.interfaceName, super.proxy,
                    super.methodGroupClients, super.properties,
                    super.constructors, super.clientMethods,
                    super.azureEnvironmentParameter, super.tokenCredentialParameter,
                    super.httpPipelineParameter);
        }
    }
}
