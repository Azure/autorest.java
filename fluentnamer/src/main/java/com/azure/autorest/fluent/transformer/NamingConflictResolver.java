/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.Metadata;
import com.azure.autorest.extension.base.model.codemodel.ValueSchema;
import com.azure.autorest.fluent.util.Utils;
import com.azure.autorest.preprocessor.namer.CodeNamer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class NamingConflictResolver {

    private static final Logger logger = LoggerFactory.getLogger(NamingConflictResolver.class);

    public CodeModel process(CodeModel codeModel) {
        Set<String> operationGroupNames = new HashSet<>();
        Set<String> objectNames = codeModel.getSchemas().getObjects().stream()
                .map(Utils::getDefaultName)
                .collect(Collectors.toSet());
        codeModel.getOperationGroups().forEach(og -> {
            String name = Utils.getDefaultName(og);
            String methodGroupName = CodeNamer.getPlural(CodeNamer.getMethodGroupName(name));
            String newMethodGroupName = methodGroupName;
            if (objectNames.contains(methodGroupName)) {
                String newName = renameOperationGroup(og);
                newMethodGroupName = CodeNamer.getPlural(CodeNamer.getMethodGroupName(newName));
            } else if (operationGroupNames.contains(methodGroupName)) {
                String newName = renameOperationGroup(og);
                newMethodGroupName = CodeNamer.getPlural(CodeNamer.getMethodGroupName(newName));
            }
            operationGroupNames.add(newMethodGroupName);
        });

        codeModel.getSchemas().getChoices().forEach(c -> validateChoiceName(c, objectNames));
        codeModel.getSchemas().getSealedChoices().forEach(c -> validateChoiceName(c, objectNames));
        return codeModel;
    }

    private static String renameOperationGroup(Metadata m) {
        String name = Utils.getDefaultName(m);
        String newName = name + "Operation";
        logger.info("Rename operation group from {} to {}", name, newName);
        m.getLanguage().getDefault().setName(newName);
        return newName;
    }

    private static void validateChoiceName(ValueSchema choice, Set<String> objectNames) {
        String name = Utils.getDefaultName(choice);
        if (objectNames.contains(name)) {
            String newName = name + "Value";
            logger.warn("Name conflict of choice with object {}", name);
            logger.info("Rename choice from {} to {}", name, newName);
            choice.getLanguage().getDefault().setName(newName);
        }
    }
}
