/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.Metadata;
import com.azure.autorest.extension.base.model.codemodel.ValueSchema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.fluentnamer.FluentNamer;
import com.azure.autorest.preprocessor.namer.CodeNamer;
import org.slf4j.Logger;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class NamingConflictResolver {

    private static final Logger logger = new PluginLogger(FluentNamer.getPluginInstance(), NamingConflictResolver.class);

    public CodeModel process(CodeModel codeModel) {
        // conform to lowercase, to avoid problem on Windows system, where file name is case-insensitive
        Set<String> methodGroupNamesLowerCase = new HashSet<>();
        Set<String> objectNamesLowerCase = codeModel.getSchemas().getObjects().stream()
                .map(Utils::getJavaName)
                .map(n -> n.toLowerCase(Locale.ROOT))
                .collect(Collectors.toSet());
        codeModel.getOperationGroups().forEach(og -> {
            String methodGroupName = CodeNamer.getPlural(Utils.getJavaName(og));
            String newMethodGroupName = methodGroupName;
            if (objectNamesLowerCase.contains(methodGroupName.toLowerCase(Locale.ROOT))) {
                // deduplicate from objects
                String newName = renameOperationGroup(og);
                newMethodGroupName = CodeNamer.getPlural(CodeNamer.getMethodGroupName(newName));
            } else if (methodGroupNamesLowerCase.contains(methodGroupName.toLowerCase(Locale.ROOT))) {
                // deduplicate from other operation groups
                String newName = renameOperationGroup(og);
                newMethodGroupName = CodeNamer.getPlural(CodeNamer.getMethodGroupName(newName));
            }

            methodGroupNamesLowerCase.add(newMethodGroupName.toLowerCase(Locale.ROOT));
            if (JavaSettings.getInstance().shouldGenerateClientInterfaces()) {
                methodGroupNamesLowerCase.add((newMethodGroupName + "Client").toLowerCase(Locale.ROOT));
            }
        });

        String clientNameLowerCase = Utils.getJavaName(codeModel).toLowerCase(Locale.ROOT);
        if (methodGroupNamesLowerCase.contains(clientNameLowerCase) || objectNamesLowerCase.contains(clientNameLowerCase)) {
            String name = Utils.getJavaName(codeModel);
            String newName;

            final String keywordManagementClient = "ManagementClient";
            final String keywordClient = "Client";
            if (name.endsWith(keywordClient) && !name.endsWith(keywordManagementClient)) {
                newName = name.substring(0, name.length() - keywordClient.length()) + keywordManagementClient;
            } else if (name.endsWith(keywordManagementClient)) {
                newName = name.substring(0, name.length() - keywordManagementClient.length()) + "Main" + keywordManagementClient;
            } else {
                newName = name + keywordManagementClient;
            }

            logger.info("Rename code model from '{}' to '{}'", name, newName);
            codeModel.getLanguage().getJava().setName(newName);
        }

        // deduplicate enums from objects
        codeModel.getSchemas().getChoices().forEach(c -> validateChoiceName(c, objectNamesLowerCase));
        codeModel.getSchemas().getSealedChoices().forEach(c -> validateChoiceName(c, objectNamesLowerCase));

        return codeModel;
    }

    private static String renameOperationGroup(Metadata m) {
        String name = Utils.getJavaName(m);
        String newName = name + "Operation";
        logger.info("Rename operation group from '{}' to '{}'", name, newName);
        m.getLanguage().getJava().setName(newName);
        return newName;
    }

    private static void validateChoiceName(ValueSchema choice, Set<String> objectNames) {
        String name = Utils.getJavaName(choice);
        if (objectNames.contains(name.toLowerCase(Locale.ROOT))) {
            String newName = name + "Value";
            logger.warn("Name conflict of choice with object '{}'", name);
            logger.info("Rename choice from '{}' to '{}'", name, newName);
            choice.getLanguage().getJava().setName(newName);
        }
    }
}
