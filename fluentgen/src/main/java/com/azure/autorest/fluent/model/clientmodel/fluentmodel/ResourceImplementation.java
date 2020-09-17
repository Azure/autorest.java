/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel;

import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethodType;
import com.azure.autorest.model.javamodel.JavaJavadocComment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ResourceImplementation {

    private List<FluentMethod> fluentMethods = new ArrayList<>();

    public ResourceImplementation(Collection<FluentMethod> fluentMethods) {
        this.groupMethods(fluentMethods);
    }

    private void groupMethods(Collection<FluentMethod> methods) {
        Map<String, GroupedMethods> groupedMethodsMap = new HashMap<>();
        for (FluentMethod method : methods) {
            if (method.getType() == FluentMethodType.CREATE_WITH || method.getType() == FluentMethodType.UPDATE_WITH) {
                GroupedMethods groupedMethods = groupedMethodsMap.computeIfAbsent(method.getName(), key -> new GroupedMethods());
                if (method.getType() == FluentMethodType.CREATE_WITH) {
                    groupedMethods.methodCreateWith = method;
                } else {
                    groupedMethods.methodUpdateWith = method;
                }
            } else {
                this.fluentMethods.add(method);
            }
        }

        for (GroupedMethods groupedMethods : groupedMethodsMap.values()) {
            if (groupedMethods.size() == 1) {
                this.fluentMethods.add(groupedMethods.single());
            } else {
                this.fluentMethods.add(new MergedFluentMethod(groupedMethods));
            }
        }
    }

    public List<FluentMethod> getFluentMethods() {
        return this.fluentMethods;
    }

    private static class MergedFluentMethod extends FluentMethod {

        private final GroupedMethods groupedMethods;

        public MergedFluentMethod(GroupedMethods groupedMethods) {
            super(groupedMethods.methodCreateWith.getFluentResourceModel(), FluentMethodType.OTHER);

            this.groupedMethods = groupedMethods;

            if (groupedMethods.methodCreateWith.equals(groupedMethods.methodUpdateWith)) {
                this.implementationMethodTemplate = groupedMethods.methodCreateWith.getMethodTemplate();
            } else {
                // TODO
            }
        }

        @Override
        protected String getBaseMethodSignature() {
            throw new UnsupportedOperationException();
        }

        @Override
        public String getImplementationMethodSignature() {
            return groupedMethods.methodCreateWith.getImplementationMethodSignature();
        }

        @Override
        public void writeJavadoc(JavaJavadocComment commentBlock) {
            // NOOP
        }

        @Override
        public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
            groupedMethods.methodCreateWith.addImportsTo(imports, includeImplementationImports);
            groupedMethods.methodUpdateWith.addImportsTo(imports, includeImplementationImports);
        }
    }

    private static class GroupedMethods {
        private FluentMethod methodCreateWith;
        private FluentMethod methodUpdateWith;

        private int size() {
            return (methodCreateWith == null ? 0 : 1) + (methodUpdateWith == null ? 0 : 1);
        }

        private FluentMethod single() {
            return methodUpdateWith == null ? methodCreateWith : methodUpdateWith;
        }
    }
}
