/*
 * // Copyright (c) Microsoft Corporation. All rights reserved.
 * // Licensed under the MIT License.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.template.FluentExampleTemplate;
import com.azure.autorest.model.clientmodel.ClassType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FluentLiveTests {

    private String className;
    private final Set<String> imports = new HashSet<>();
    private final Set<FluentExampleTemplate.HelperFeature> helperFeatures = new HashSet<>();
    private final List<FluentLiveTestCase> testCases = new ArrayList<>();
    private ClassType entryType;
    private String entryName;

    public ClassType getEntryType() {
        return entryType;
    }

    public void setEntryType(ClassType entryType) {
        this.entryType = entryType;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Set<String> getImports() {
        return imports;
    }

    public Set<FluentExampleTemplate.HelperFeature> getHelperFeatures() {
        return helperFeatures;
    }

    public List<FluentLiveTestCase> getTestCases() {
        return testCases;
    }

    public String getPackageName() {
        return JavaSettings.getInstance().getPackage("livetests");
    }
}
