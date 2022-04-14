// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.Header;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.OperationGroup;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.fluentnamer.FluentNamer;
import com.azure.autorest.preprocessor.namer.CodeNamer;
import com.azure.core.util.CoreUtils;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class LongRunningOperationProcessor {

    private final Logger logger = new PluginLogger(FluentNamer.getPluginInstance(), LongRunningOperationProcessor.class);

    private static final Set<String> KNOWN_LRO_HEADERS = new HashSet<>(Arrays.asList(
            "retry-after",
            "location",
            "azure-asyncoperation"
    ));

    private final Set<String> javaNamesForPreserveModel;

    public LongRunningOperationProcessor(Set<String> javaNamesForPreserveModel) {
        this.javaNamesForPreserveModel = javaNamesForPreserveModel;
    }

    public CodeModel process(CodeModel codeModel) {
        // remove operation group
        List<OperationGroup> operationGroups = codeModel.getOperationGroups().stream().filter(og -> {
            String methodGroupName = CodeNamer.getPlural(Utils.getJavaName(og));
            boolean remove = javaNamesForPreserveModel.contains(methodGroupName);
            if (remove) {
                logger.info("Removed operation group '{}'", methodGroupName);
            }
            return !remove;
        }).collect(Collectors.toList());
        if (operationGroups.size() < codeModel.getOperationGroups().size()) {
            codeModel.setOperationGroups(operationGroups);
        }

        // remove headers in LRO response
        codeModel.getOperationGroups().stream()
                .flatMap(og -> og.getOperations().stream())
                .filter(LongRunningOperationProcessor::hasLongRunningOperationExtension)
                .forEach(o -> o.getResponses().forEach(r -> {
                    if (r.getProtocol() != null && r.getProtocol().getHttp() != null && !CoreUtils.isNullOrEmpty(r.getProtocol().getHttp().getHeaders())) {
                        Set<String> headerNames = r.getProtocol().getHttp().getHeaders().stream()
                                .map(Header::getHeader)
                                .map(s -> s.toLowerCase(Locale.ROOT))
                                .collect(Collectors.toSet());
                        headerNames.removeAll(KNOWN_LRO_HEADERS);
                        if (!headerNames.isEmpty()) {
                            logger.warn("Removed unknown headers {} from operation '{}'", headerNames, o.getOperationId());
                        }
                        r.getProtocol().getHttp().setHeaders(null);
                    }
                }));

        return codeModel;
    }

    private static boolean hasLongRunningOperationExtension(Operation operation) {
        return operation.getExtensions() != null && operation.getExtensions().isXmsLongRunningOperation();
    }
}
