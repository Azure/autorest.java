/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.transformer;

import com.azure.autorest.extension.base.model.codemodel.ArraySchema;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.OperationGroup;
import com.azure.autorest.extension.base.model.codemodel.Response;
import com.azure.autorest.fluent.Utils;
import com.azure.autorest.fluent.model.WellKnownMethodName;
import com.azure.core.http.HttpMethod;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class OperationNameNormalization {

    private final static Logger logger = LoggerFactory.getLogger(OperationNameNormalization.class);

    public CodeModel process(CodeModel codeModel) {
        codeModel.getOperationGroups().forEach(OperationNameNormalization::process);
        return codeModel;
    }

    private final static String SEGMENT_SUBSCRIPTIONS = "subscriptions";
    private final static String SEGMENT_RESOURCE_GROUPS = "resourceGroups";
    private final static String SEGMENT_PROVIDERS = "providers";

    private static void process(OperationGroup operationGroup) {
        Map<String, String> renamePlan = makeRenamePlan(operationGroup);
        applyRename(operationGroup, renamePlan);
    }

    private static void applyRename(OperationGroup operationGroup, Map<String, String> renamePlan) {
        Optional<Set<String>> conflictNames = checkConflict(operationGroup, renamePlan);
        conflictNames.ifPresent(names -> {
            logger.warn("Conflict operation name found after attempted rename {}, in operation group {}", names, Utils.getJavaName(operationGroup));
            renamePlan.keySet().removeAll(names);
        });

        rename(operationGroup, renamePlan);
    }

    private static Optional<Set<String>> checkConflict(OperationGroup operationGroup, Map<String, String> renamePlan) {
        Set<String> names = operationGroup.getOperations().stream()
                .map(Utils::getJavaName)
                .map(name -> renamePlan.getOrDefault(name, name)).collect(Collectors.toSet());

        Set<String> namesWithConflict = names.stream()
                .collect(Collectors.groupingBy(Function.identity()))
                .entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        return namesWithConflict.isEmpty() ? Optional.empty() : Optional.of(namesWithConflict);
    }

    private static void rename(OperationGroup operationGroup, Map<String, String> renamePlan) {
        operationGroup.getOperations().stream()
                .filter(operation -> renamePlan.containsKey(Utils.getJavaName(operation)))
                .forEach(operation -> {
                    String newName = renamePlan.get(Utils.getJavaName(operation));
                    logger.info("Rename operation from {} to {}, in operation group {}", Utils.getJavaName(operation), newName, Utils.getJavaName(operationGroup));
                    operation.getLanguage().getJava().setName(newName);
                });
    }

    private static Map<String, String> makeRenamePlan(OperationGroup operationGroup) {
        final Set<WellKnownMethodName> candidateWellKnownName = new HashSet<>(Arrays.asList(
                WellKnownMethodName.LIST,
                WellKnownMethodName.LIST_BY_RESOURCE_GROUP,
                WellKnownMethodName.GET_BY_RESOURCE_GROUP,
                WellKnownMethodName.DELETE));

        Map<String, String> renamePlan = new HashMap<>();

        for (Operation operation : operationGroup.getOperations()) {
            String path = operation.getRequest().getProtocol().getHttp().getPath();
            path = StringUtils.strip(path.trim(), "/");
            String[] urlSegments = path.split(Pattern.quote("/"));

            String newName = null;
            if (HttpMethod.GET.name().equalsIgnoreCase(operation.getRequest().getProtocol().getHttp().getMethod())) {
                if (urlSegments.length == 8
                        && urlSegments[0].equalsIgnoreCase(SEGMENT_SUBSCRIPTIONS)
                        && urlSegments[2].equalsIgnoreCase(SEGMENT_RESOURCE_GROUPS)
                        && urlSegments[4].equalsIgnoreCase(SEGMENT_PROVIDERS)) {
                    if (candidateWellKnownName.contains(WellKnownMethodName.GET_BY_RESOURCE_GROUP)) {
                        newName = WellKnownMethodName.GET_BY_RESOURCE_GROUP.getMethodName();
                    }
                } else if ((urlSegments.length == 5 || urlSegments.length == 7)
                        && urlSegments[0].equalsIgnoreCase(SEGMENT_SUBSCRIPTIONS)
                        && hasArrayInResponse(operation.getResponses())) {
                    if (candidateWellKnownName.contains(WellKnownMethodName.LIST_BY_RESOURCE_GROUP)) {
                        if ((urlSegments.length == 7 && urlSegments[2].equalsIgnoreCase(SEGMENT_RESOURCE_GROUPS))
                                || (urlSegments.length == 5 && !urlSegments[2].equalsIgnoreCase(SEGMENT_PROVIDERS))) {
                            newName = WellKnownMethodName.LIST_BY_RESOURCE_GROUP.getMethodName();
                        }
                    }
                    if (candidateWellKnownName.contains(WellKnownMethodName.LIST)) {
                        if (urlSegments.length == 5 && urlSegments[2].equalsIgnoreCase(SEGMENT_PROVIDERS)) {
                            newName = WellKnownMethodName.LIST.getMethodName();
                        }
                    }
                }
            }
            // TODO WellKnownMethodName.DELETE

            if (newName != null) {
                if (!newName.equals(Utils.getJavaName(operation))) {
                    renamePlan.put(Utils.getJavaName(operation), newName);
                }
                candidateWellKnownName.remove(WellKnownMethodName.fromMethodName(newName));
            }
        }

        return renamePlan;
    }

    private static boolean hasArrayInResponse(List<Response> responses) {
        return responses.stream()
                .anyMatch(r -> r.getSchema() instanceof ObjectSchema
                        && ((ObjectSchema) r.getSchema()).getProperties().stream().anyMatch(p -> Utils.getJavaName(p).equals("value") && p.getSchema() instanceof ArraySchema));
    }
}
