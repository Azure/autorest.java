package com.azure.autorest.android.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.*;

import java.util.List;
import java.util.Set;

public class AndroidServiceClient extends ServiceClient {

    public AndroidServiceClient(String packageName, String className, String interfaceName, Proxy proxy,
                                List<MethodGroupClient> methodGroupClients, List<ServiceClientProperty> properties,
                                List<Constructor> constructors, List<ClientMethod> clientMethods,
                                ClientMethodParameter azureEnvironmentParameter,
                                ClientMethodParameter tokenCredentialParameter,
                                ClientMethodParameter httpPipelineParameter) {
        super(packageName,
                className,
                interfaceName,
                proxy,
                methodGroupClients,
                properties,
                constructors,
                clientMethods,
                azureEnvironmentParameter,
                tokenCredentialParameter,
                httpPipelineParameter);
    }

    @Override
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports, boolean includeBuilderImports, JavaSettings settings) {
        for (ClientMethod clientMethod : getClientMethods()) {
            clientMethod.addImportsTo(imports, includeImplementationImports, settings);
        }

        for (ServiceClientProperty serviceClientProperty : getProperties()) {
            serviceClientProperty.addImportsTo(imports, includeImplementationImports);
        }

        for (Constructor constructor : getConstructors()) {
            constructor.addImportsTo(imports, includeImplementationImports);
        }

        imports.add("com.azure.android.core.http.interceptor.AddDateInterceptor");
        imports.add("com.azure.android.core.internal.util.serializer.SerializerFormat");
        imports.add("com.azure.android.core.http.ServiceClient.Builder");

        // TODO: Add Android annotation imports;
    }

    public static class Builder extends ServiceClient.Builder {
        @Override
        public ServiceClient build() {
            return new AndroidServiceClient(packageName,
                    className,
                    interfaceName,
                    proxy,
                    methodGroupClients,
                    properties,
                    constructors,
                    clientMethods,
                    azureEnvironmentParameter,
                    tokenCredentialParameter,
                    httpPipelineParameter);
        }
    }
}
