package com.azure.autorest.android.model.clientmodel;

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

    protected AndroidServiceClient(String packageName,
                                   String className,
                                   String interfaceName,
                                   Proxy proxy,
                                   List<MethodGroupClient> methodGroupClients,
                                   List<ServiceClientProperty> properties,
                                   List<Constructor> constructors,
                                   List<ClientMethod> clientMethods,
                                   ClientMethodParameter azureEnvironmentParameter,
                                   ClientMethodParameter tokenCredentialParameter,
                                   ClientMethodParameter httpPipelineParameter,
                                   ClientMethodParameter serializerAdapterParameter,
                                   ClientMethodParameter defaultPollIntervalParameter,
                                   String defaultCredentialScopes,
                                   boolean builderDisabled) {
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
                httpPipelineParameter,
                serializerAdapterParameter,
                defaultPollIntervalParameter,
                defaultCredentialScopes,
                builderDisabled);
    }

    @Override
    protected void addRestProxyImport(Set<String> imports) {
        imports.add("com.azure.android.core.rest.RestProxy");
    }

    @Override
    protected void addPipelineBuilderImport(Set<String> imports) {
        imports.add("com.azure.android.core.http.HttpPipelineBuilder");
    }

    @Override
    protected void addHttpPolicyImports(Set<String> imports) {
        imports.add("com.azure.android.core.http.policy.CookiePolicy");
        imports.add("com.azure.android.core.http.policy.RetryPolicy");
        imports.add("com.azure.android.core.http.policy.UserAgentPolicy");
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
                    httpPipelineParameter,
                    serializerAdapterParameter,
                    defaultPollIntervalParameter,
                    defaultCredentialScopes,
                    builderDisabled);
        }
    }
}
