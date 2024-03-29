// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.OperationGroup;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.fluentnamer.FluentNamer;
import com.azure.autorest.preprocessor.namer.CodeNamer;
import org.slf4j.Logger;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OperationGroupFilter {

    private final Logger logger = new PluginLogger(FluentNamer.getPluginInstance(), OperationGroupFilter.class);

    private final Set<String> javaNamesForPreserveModel;

    public OperationGroupFilter(Set<String> javaNamesForPreserveModel) {
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

        return codeModel;
    }
}
