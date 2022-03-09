// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.core.http.HttpMethod;
import com.azure.core.util.CoreUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class MethodUtil {

    public static final String REPEATABILITY_REQUEST_ID_HEADER = "repeatability-request-id";
    public static final String REPEATABILITY_FIRST_SENT_HEADER = "repeatability-first-sent";

    private static final Set<HttpMethod> REPEATABILITY_REQUEST_HTTP_METHODS = new HashSet<>(Arrays.asList(
         HttpMethod.PUT,
         HttpMethod.PATCH,
         HttpMethod.DELETE,
         HttpMethod.POST
    ));

    /**
     * Checks that method include special headers for Repeatable Requests Version 1.0
     * @param clientMethod the client method
     * @return whether method include special headers for Repeatable Requests Version 1.0
     */
    public static boolean isMethodIncludeRepeatableRequestHeaders(ClientMethod clientMethod) {
        // Repeatable Requests Version 1.0
        // https://docs.oasis-open.org/odata/repeatable-requests/v1.0/cs01/repeatable-requests-v1.0-cs01.html

        boolean ret = false;
        if (clientMethod.getProxyMethod() != null
                && !CoreUtils.isNullOrEmpty(clientMethod.getProxyMethod().getSpecialHeaders())) {
            ProxyMethod proxyMethod = clientMethod.getProxyMethod();
            // check supported HTTP method
            if (REPEATABILITY_REQUEST_HTTP_METHODS.contains(proxyMethod.getHttpMethod())) {
                // check 2 headers exists
                List<String> specialHeaders = proxyMethod.getSpecialHeaders().stream()
                        .map(s -> s.toLowerCase(Locale.ROOT))
                        .collect(Collectors.toList());
                ret = specialHeaders.contains(REPEATABILITY_REQUEST_ID_HEADER)
                        && specialHeaders.contains(REPEATABILITY_FIRST_SENT_HEADER);
            }
        }
        return ret;
    }
}
