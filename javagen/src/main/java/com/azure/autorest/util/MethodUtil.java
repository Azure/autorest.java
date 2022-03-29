// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

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
    public static final String REPEATABILITY_REQUEST_ID_VARIABLE_NAME = CodeNamer.toCamelCase(REPEATABILITY_REQUEST_ID_HEADER);
    public static final String REPEATABILITY_FIRST_SENT_VARIABLE_NAME = CodeNamer.toCamelCase(REPEATABILITY_FIRST_SENT_HEADER);

    private static final Set<HttpMethod> REPEATABILITY_REQUEST_HTTP_METHODS = new HashSet<>(Arrays.asList(
         HttpMethod.PUT,
         HttpMethod.PATCH,
         HttpMethod.DELETE,
         HttpMethod.POST
    ));

    /**
     * Checks that method include special headers for Repeatable Requests Version 1.0
     * @param proxyMethod the proxy method
     * @return whether method include special headers for Repeatable Requests Version 1.0
     */
    public static boolean isMethodIncludeRepeatableRequestHeaders(ProxyMethod proxyMethod) {
        // Repeatable Requests Version 1.0
        // https://docs.oasis-open.org/odata/repeatable-requests/v1.0/cs01/repeatable-requests-v1.0-cs01.html

        boolean ret = false;
        if (proxyMethod != null && !CoreUtils.isNullOrEmpty(proxyMethod.getSpecialHeaders())) {
            // check supported HTTP method
            if (isHttpMethodSupportRepeatableRequestHeaders(proxyMethod.getHttpMethod())) {
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

    public static boolean isHttpMethodSupportRepeatableRequestHeaders(HttpMethod httpMethod) {
        return REPEATABILITY_REQUEST_HTTP_METHODS.contains(httpMethod);
    }
}
