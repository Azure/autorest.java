// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.fluentnamer.FluentNamer;
import com.azure.autorest.preprocessor.namer.CodeNamer;
import org.slf4j.Logger;

import java.util.Map;

public class OperationGroupRenamer {

    private final Logger logger = new PluginLogger(FluentNamer.getPluginInstance(), OperationGroupRenamer.class);

    private final Map<String, String> renameOperationGroup;

    public OperationGroupRenamer(Map<String, String> renameOperationGroup) {
        this.renameOperationGroup = renameOperationGroup;
    }

    public CodeModel process(CodeModel codeModel) {
        // rename operation group
        codeModel.getOperationGroups().forEach(og -> {
            String methodGroupName = CodeNamer.getPlural(Utils.getJavaName(og));
            String rename = renameOperationGroup.get(methodGroupName);
            if (rename != null) {
                og.getLanguage().getJava().setName(rename);
                logger.info("Renamed operation group from '{}' to '{}'.", methodGroupName, rename);
            }
        });
        return codeModel;
    }
}
