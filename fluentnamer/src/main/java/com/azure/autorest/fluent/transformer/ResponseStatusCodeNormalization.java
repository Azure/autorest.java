/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.Response;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.fluentnamer.FluentNamer;
import com.azure.core.http.HttpMethod;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ResponseStatusCodeNormalization {

    private static final Logger logger = new PluginLogger(FluentNamer.getPluginInstance(), ResponseStatusCodeNormalization.class);

    private static final boolean REMOVE_404_IN_GET_RESPONSE = true;

    public CodeModel process(CodeModel codeModel) {
        codeModel.getOperationGroups().stream().flatMap(og -> og.getOperations().stream())
                // only for GET method
                .filter(o -> o.getRequests().stream()
                        .anyMatch(r -> r.getProtocol() != null && r.getProtocol().getHttp() != null
                                && HttpMethod.GET.name().equalsIgnoreCase(r.getProtocol().getHttp().getMethod())))
                .forEach(operation -> {
                    List<Response> responsesToRemove = new ArrayList<>();
                    for (Response response : operation.getResponses()) {
                        if (response.getProtocol() != null && response.getProtocol().getHttp() != null && response.getProtocol().getHttp().getStatusCodes() != null) {
                            if (response.getProtocol().getHttp().getStatusCodes().contains("404")) {
                                logger.warn("Operation '{}' expect '404' status code, in group '{}'",
                                        Utils.getJavaName(operation), Utils.getJavaName(operation.getOperationGroup()));

                                if (REMOVE_404_IN_GET_RESPONSE) {
                                    String operationNameInLower = Utils.getJavaName(operation).toLowerCase(Locale.ROOT);
                                    if (operationNameInLower.startsWith("get") || operationNameInLower.startsWith("list")) {
                                        logger.info("Remove '404' status code in operation '{}', in group '{}'",
                                                Utils.getJavaName(operation), Utils.getJavaName(operation.getOperationGroup()));
                                        if (response.getProtocol().getHttp().getStatusCodes().size() == 1) {
                                            // remove the response with only 404
                                            responsesToRemove.add(response);
                                        } else {
                                            response.getProtocol().getHttp().getStatusCodes().remove("404");
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!responsesToRemove.isEmpty()) {
                        operation.getResponses().removeAll(responsesToRemove);
                    }
                });

        return codeModel;
    }
}
