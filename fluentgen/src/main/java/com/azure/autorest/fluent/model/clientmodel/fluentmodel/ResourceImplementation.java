/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel;

import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethodType;
import com.azure.autorest.fluent.model.clientmodel.immutablemodel.ImmutableMethod;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.javamodel.JavaJavadocComment;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.template.prototype.MethodTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ResourceImplementation {

    private final List<ImmutableMethod> methods = new ArrayList<>();
    private final Map<String, ClientModelProperty> clientProperties = new HashMap<>();

    public ResourceImplementation(Collection<FluentMethod> fluentMethods) {
        this.groupMethods(fluentMethods);
    }

    private void groupMethods(Collection<FluentMethod> fluentMethods) {
        fluentMethods.stream()
                .flatMap(m -> m.getClientProperties().stream())
                .forEach(p -> clientProperties.putIfAbsent(p.getName(), p));

        Map<String, GroupedMethods> groupedMethodsMap = new HashMap<>();
        for (FluentMethod method : fluentMethods) {
            if (method.getType() == FluentMethodType.CREATE_WITH || method.getType() == FluentMethodType.UPDATE_WITH) {
                GroupedMethods groupedMethods = groupedMethodsMap.computeIfAbsent(method.getName(), key -> new GroupedMethods());
                if (method.getType() == FluentMethodType.CREATE_WITH) {
                    groupedMethods.methodCreateWith = method;
                } else {
                    groupedMethods.methodUpdateWith = method;
                }
            } else {
                this.methods.add(method);
            }
        }

        boolean branchMethodNeeded = false;

        for (GroupedMethods groupedMethods : groupedMethodsMap.values()) {
            if (groupedMethods.size() == 1) {
                this.methods.add(groupedMethods.single());
            } else {
                MergedFluentMethod method = new MergedFluentMethod(groupedMethods);
                this.methods.add(method);

                branchMethodNeeded = branchMethodNeeded || method.isBranchMethodNeeded();
            }
        }

        if (branchMethodNeeded) {
            this.methods.add(new FluentMethodCreateMode());
        }
    }

    public List<ImmutableMethod> getMethods() {
        return this.methods;
    }

    public Map<String, ClientModelProperty> getClientProperties() {
        return this.clientProperties;
    }

    private static class FluentMethodCreateMode extends FluentMethod {

        public FluentMethodCreateMode() {
            super(null, FluentMethodType.OTHER);

            this.name = "isInCreateMode";

            this.implementationMethodTemplate = MethodTemplate.builder()
                    .visibility(JavaVisibility.Private)
                    .methodSignature(this.getImplementationMethodSignature())
                    .method(block -> {
                        block.methodReturn(String.format("this.%1$s().id() == null", ModelNaming.METHOD_INNER));
                    })
                    .build();
        }

        @Override
        public String getImplementationMethodSignature() {
            return "boolean isInCreateMode()";
        }

        @Override
        protected String getBaseMethodSignature() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void writeJavadoc(JavaJavadocComment commentBlock) {
            // NOOP
        }

        @Override
        public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {

        }
    }

    private static class MergedFluentMethod extends FluentMethod {

        private final GroupedMethods groupedMethods;
        private final boolean branchMethodNeeded;

        public MergedFluentMethod(GroupedMethods groupedMethods) {
            super(groupedMethods.methodCreateWith.getFluentResourceModel(), FluentMethodType.OTHER);

            this.name = groupedMethods.methodCreateWith.getName();
            this.groupedMethods = groupedMethods;

            if (groupedMethods.methodCreateWith.equals(groupedMethods.methodUpdateWith)) {
                this.implementationMethodTemplate = groupedMethods.methodCreateWith.getMethodTemplate();
                branchMethodNeeded = false;
            } else {
                this.implementationMethodTemplate = MethodTemplate.builder()
                        .methodSignature(this.getImplementationMethodSignature())
                        .method(block -> {
                            block.ifBlock("isInCreateMode()", ifBlock -> {
                                groupedMethods.methodCreateWith.getMethodTemplate().writeMethodContent(ifBlock);
                            }).elseBlock(elseBlock -> {
                                groupedMethods.methodCreateWith.getMethodTemplate().writeMethodContent(elseBlock);
                            });
                        })
                        .build();
                branchMethodNeeded = true;
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

        public boolean isBranchMethodNeeded() {
            return branchMethodNeeded;
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
