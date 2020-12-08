/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.Metadata;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.fluentnamer.FluentNamer;
import org.slf4j.Logger;

import java.util.Map;

public class SchemaRenamer {

    private static final Logger logger = new PluginLogger(FluentNamer.getPluginInstance(), SchemaRenamer.class);

    private final Map<String, String> renameModel;

    public SchemaRenamer(Map<String, String> renameModel) {
        this.renameModel = renameModel;
    }

    public CodeModel process(CodeModel codeModel) {
        if (renameModel == null || renameModel.isEmpty()) {
            return codeModel;
        }

        codeModel.getSchemas().getObjects().forEach(s -> checkRename(s, renameModel));
        codeModel.getSchemas().getChoices().forEach(s -> checkRename(s, renameModel));
        codeModel.getSchemas().getSealedChoices().forEach(s -> checkRename(s, renameModel));
        return codeModel;
    }

    private static void checkRename(Metadata m, Map<String, String> renameModel) {
        String name = Utils.getJavaName(m);
        String newName = renameModel.get(name);
        if (newName != null) {
            logger.info("Rename model from '{}' to '{}'", name, newName);
            m.getLanguage().getJava().setName(newName);
        }
    }
}
