package com.azure.autorest.android.template;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.model.clientmodel.Proxy;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.clientmodel.ServiceClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class HostMapping {
    private final String baseUrl;
    private final List<ProxyMethodParameter> hostParams;

    public List<ProxyMethodParameter> getHostParams() {
        return this.hostParams;
    }

    public String anyHostParamAbsentExpression() {
        return this.hostParams
                .stream()
                .map(h -> String.format("%s == null", h.getName()))
                .collect(Collectors.joining(" || "));
    }

    public String allHostParamPresentExpression() {
        return this.hostParams
                .stream()
                .map(h -> String.format("%s != null", h.getName()))
                .collect(Collectors.joining(" && "));
    }

    public String getBaseUrlPattern() {
        return baseUrl;
    }

    public String getBaseUrlExpression(String baseUrlVariableName) {
        if (this.baseUrl == null || this.baseUrl.isEmpty()) {
            if (this.hostParams.isEmpty()) {
                return baseUrlVariableName;
            } else {
                return this.hostParams.get(0).getName();
            }
        } else {
            if (this.hostParams.isEmpty()) {
                return this.baseUrl;
            } else {
                if (this.baseUrl.equalsIgnoreCase(String.format("{%s}", this.hostParams.get(0).getName()))) {
                    return this.hostParams.get(0).getName();
                } else {
                    StringBuilder endpoint = new StringBuilder();
                    endpoint.append(baseUrlVariableName);
                    for (ProxyMethodParameter hostParam : this.hostParams) {
                        endpoint.append(String.format(".replace(\"{%s}\", %s)", hostParam.getName(), hostParam.getName()));
                    }
                    endpoint.append(";");
                    return endpoint.toString();
                }
            }
        }
    }

    private HostMapping(String host, List<ProxyMethodParameter> hostParams) {
        this.baseUrl = host;
        this.hostParams = hostParams;
    }

    public static HostMapping create(ServiceClient serviceClient) {
        Proxy proxy = null;
        // Find a proxy with host set and at least one method.
        //
        if (serviceClient.getProxy() != null &&
                serviceClient.getProxy().getBaseURL() != null &&
                !serviceClient.getProxy().getMethods().isEmpty()) {
            proxy = serviceClient.getProxy();
        } else {
            Optional<Proxy> proxyOpt = serviceClient.getMethodGroupClients()
                    .stream()
                    .filter(mg -> mg.getProxy() != null &&
                            mg.getProxy().getBaseURL() != null &&
                            !mg.getProxy().getMethods().isEmpty())
                    .map(mg -> mg.getProxy())
                    .findFirst();
            if (proxyOpt.isPresent()) {
                proxy = proxyOpt.get();
            }
        }
        if (proxy == null) {
            return new HostMapping(null, new ArrayList<>());
        }

        // @Host("{endpoint}/anomalydetector-ee/v1.0")
        final String host = proxy.getBaseURL();
        if (host == null || host.isEmpty()) {
            return new HostMapping(host, new ArrayList<>());
        }
        List<ProxyMethodParameter> hostParams;
        ProxyMethod proxyMethod = proxy.getMethods().get(0);
        // find all @HostParam("endpoint")
        // In ServiceClient these are global parameters
        // http://azure.github.io/autorest/extensions/#x-ms-parameterized-host
        hostParams = proxyMethod
                .getParameters()
                .stream()
                .filter(p -> {
                    return p.getRequestParameterLocation() == RequestParameterLocation.Uri &&
                            !p.getIsConstant();
                })
                .collect(Collectors.toList());

        String[] resolvedHost = new String[1];
        resolvedHost[0] = host;

        proxyMethod
                .getParameters()
                .stream()
                .filter(p -> {
                    return p.getRequestParameterLocation() == RequestParameterLocation.Uri &&
                            p.getIsConstant();
                }).forEach(parameter -> {
            if (resolvedHost[0].contains(String.format("{%s}", parameter.getName()))) {
                resolvedHost[0]
                        = resolvedHost[0].replace(String.format("{%s}",
                        parameter.getName()),
                        parameter.getDefaultValue());
            }
        });
        return new HostMapping(resolvedHost[0], hostParams);
    }
}
