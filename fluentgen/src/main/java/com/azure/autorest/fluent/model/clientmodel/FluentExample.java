/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentClientMethodExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentCollectionMethodExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentResourceCreateExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentResourceUpdateExample;

import java.util.ArrayList;
import java.util.List;

public class FluentExample implements Comparable<FluentExample> {

    private final String groupName;
    private final String methodName;
    private final String apiVersion;
    private final String exampleName;

    private final List<FluentCollectionMethodExample> collectionMethodExamples = new ArrayList<>();
    private final List<FluentResourceCreateExample> resourceCreateExamples = new ArrayList<>();
    private final List<FluentResourceUpdateExample> resourceUpdateExamples = new ArrayList<>();

    private final List<FluentClientMethodExample> clientMethodExamples = new ArrayList<>();

    public FluentExample(String groupName, String methodName, String apiVersion) {
        this.groupName = groupName;
        this.methodName = methodName;
        this.apiVersion = apiVersion;
        this.exampleName = null;
    }

    public FluentExample(String groupName, String methodName, String exampleName, String apiVersion) {
        this.groupName = groupName;
        this.methodName = methodName;
        this.apiVersion = apiVersion;
        this.exampleName = exampleName;
    }

    public List<FluentCollectionMethodExample> getCollectionMethodExamples() {
        return collectionMethodExamples;
    }

    public List<FluentResourceCreateExample> getResourceCreateExamples() {
        return resourceCreateExamples;
    }

    public List<FluentResourceUpdateExample> getResourceUpdateExamples() {
        return resourceUpdateExamples;
    }

    public List<FluentClientMethodExample> getClientMethodExamples() {
        return clientMethodExamples;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getPackageName() {
        JavaSettings settings = JavaSettings.getInstance();
        if (isAggregatedExamples()) {
            return settings.getPackage();
        } else {
            return settings.getPackage("examples");
        }
    }

    public String getClassName() {
        if (isAggregatedExamples()) {
            return groupName + methodName + "Samples";
        } else {
            return groupName + methodName +
                    com.azure.autorest.preprocessor.namer.CodeNamer.getTypeName(this.exampleName) +
                    "Samples";
        }
    }

    private boolean isAggregatedExamples() {
        return exampleName == null;
    }

    @Override
    public int compareTo(FluentExample o) {
        int ret = this.groupName.compareTo(o.groupName);
        if (ret == 0) {
            ret = this.methodName.compareTo(o.methodName);
        }
        if (ret == 0 && this.exampleName != null && o.exampleName != null) {
            ret = this.exampleName.compareTo(o.exampleName);
        }
        return ret;
    }
}
