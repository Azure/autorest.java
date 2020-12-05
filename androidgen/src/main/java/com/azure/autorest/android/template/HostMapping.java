package com.azure.autorest.android.template;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.model.clientmodel.Proxy;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.ServiceClientProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class HostMapping {
    public static final String HOST_PROPERTY_NAME = "host";

    private final String baseUrlTemplate;
    private final List<ProxyMethodParameter> hostParams;
    private final boolean hostIsBaseUrl;

    public List<ProxyMethodParameter> getHostParams() {
        return this.hostParams;
    }

    public String anyHostParamAbsentExpression() {
        return this.hostParams
                .stream()
                .map(h -> String.format("%s == null", h.getName()))
                .collect(Collectors.joining(" || "));
    }

    public boolean serviceHostPropertyIsBaseUrl() {
        return this.hostIsBaseUrl;
    }

    public String allHostParamPresentExpression() {
        return this.hostParams
                .stream()
                .map(h -> String.format("%s != null", h.getName()))
                .collect(Collectors.joining(" && "));
    }

    public String getBaseUrlPattern() {
        return baseUrlTemplate;
    }

    public String getBaseUrlExpression(String baseUrlVariableName) {
        if (this.baseUrlTemplate == null || this.baseUrlTemplate.isEmpty()) {
            return baseUrlVariableName;
        } else {
            if (this.hostParams.isEmpty()) {
                return baseUrlVariableName;
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

    private HostMapping(String baseUrl, List<ProxyMethodParameter> hostParams, boolean hostIsBaseUrl) {
        this.baseUrlTemplate = baseUrl;
        this.hostParams = hostParams;
        this.hostIsBaseUrl = hostIsBaseUrl;
    }

    public static HostMapping create(ServiceClient serviceClient) {
        Proxy proxy = null;
        // Find a proxy with host set and at least one method.
        //
        if (serviceClient.getProxy() != null
            && serviceClient.getProxy().getBaseURL() != null
            && !serviceClient.getProxy().getMethods().isEmpty()) {
            proxy = serviceClient.getProxy();
        } else {
            Optional<Proxy> proxyOpt = serviceClient
                    .getMethodGroupClients()
                    .stream()
                    .filter(mg -> mg.getProxy() != null
                            && mg.getProxy().getBaseURL() != null
                            && !mg.getProxy().getMethods().isEmpty())
                    .map(mg -> mg.getProxy())
                    .findFirst();
            if (proxyOpt.isPresent()) {
                proxy = proxyOpt.get();
            }
        }
        if (proxy == null) {
            return new HostMapping(null, new ArrayList<>(), true);
        }

        // @Host("{endpoint}/anomalydetector-ee/v1.0")
        final String baseURL = proxy.getBaseURL();
        if (baseURL == null || baseURL.isEmpty()) {
            return new HostMapping(baseURL, new ArrayList<>(), true);
        }

        Optional<ServiceClientProperty> hostProperty = serviceClient
            .getProperties()
            .stream()
            .filter(p -> p.getName().equals(HOST_PROPERTY_NAME))
            .findFirst();
        final boolean hostIsBaseUrl = hostProperty.isPresent()
                && hostProperty.get().getDefaultValueExpression().contains("http");

        List<ProxyMethodParameter> hostParams;
        ProxyMethod proxyMethod = proxy.getMethods().get(0);
        // find all @HostParam("endpoint")
        // In ServiceClient these are global parameters
        // http://azure.github.io/autorest/extensions/#x-ms-parameterized-host
        hostParams = proxyMethod
            .getParameters()
            .stream()
            .filter(p -> {
                return p.getRequestParameterLocation() == RequestParameterLocation.Uri
                        && !p.getIsConstant()
                        && (!hostIsBaseUrl
                            || !p.getName().equals(HOST_PROPERTY_NAME)); // exclude "host" when host is used
                                                                                    // for base url
                })
            .collect(Collectors.toList());

        return new HostMapping(baseURL, hostParams, hostIsBaseUrl);
    }
}
