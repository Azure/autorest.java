/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.javamodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.model.clientmodel.FluentManager;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.fluent.model.projectmodel.Project;
import com.azure.autorest.fluent.model.projectmodel.TextFile;
import com.azure.autorest.fluent.template.ChangelogTemplate;
import com.azure.autorest.fluent.template.FluentManagerTemplate;
import com.azure.autorest.fluent.template.FluentResourceCollectionImplementationTemplate;
import com.azure.autorest.fluent.template.FluentResourceCollectionInterfaceTemplate;
import com.azure.autorest.fluent.template.FluentResourceModelImplementationTemplate;
import com.azure.autorest.fluent.template.FluentResourceModelInterfaceTemplate;
import com.azure.autorest.fluent.template.ReadmeTemplate;
import com.azure.autorest.fluent.template.UtilsTemplate;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaPackage;

import java.util.ArrayList;
import java.util.List;

public class FluentJavaPackage extends JavaPackage {

    private final List<TextFile> textFiles = new ArrayList<>();

    public final List<TextFile> getTextFiles() {
        return textFiles;
    }

    public final void addReadme(Project project) {
        textFiles.add(new TextFile("README.md", new ReadmeTemplate().write(project)));
    }

    public final void addChangelog(Project project) {
        textFiles.add(new TextFile("CHANGELOG.md", new ChangelogTemplate().write(project)));
    }

    public final void addFluentResourceModel(FluentResourceModel model) {
        JavaFile javaFile = getJavaFileFactory().createSourceFile(
                model.getInterfaceType().getPackage(),
                model.getInterfaceType().getName());
        FluentResourceModelInterfaceTemplate.getInstance().write(model, javaFile);
        getJavaFiles().add(javaFile);

        javaFile = getJavaFileFactory().createSourceFile(
                model.getImplementationType().getPackage(),
                model.getImplementationType().getName());
        FluentResourceModelImplementationTemplate.getInstance().write(model, javaFile);
        getJavaFiles().add(javaFile);
    }

    public final void addFluentResourceCollection(FluentResourceCollection collection) {
        JavaFile javaFile = getJavaFileFactory().createSourceFile(
                collection.getInterfaceType().getPackage(),
                collection.getInterfaceType().getName());
        FluentResourceCollectionInterfaceTemplate.getInstance().write(collection, javaFile);
        getJavaFiles().add(javaFile);

        javaFile = getJavaFileFactory().createSourceFile(
                collection.getImplementationType().getPackage(),
                collection.getImplementationType().getName());
        FluentResourceCollectionImplementationTemplate.getInstance().write(collection, javaFile);
        getJavaFiles().add(javaFile);
    }

    public final void addFluentManager(FluentManager model, Project project) {
        JavaFile javaFile = getJavaFileFactory().createSourceFile(
                model.getType().getPackage(),
                model.getType().getName());
        FluentManagerTemplate.getInstance().write(model, project, javaFile);
        getJavaFiles().add(javaFile);
    }

    public final void addUtils() {
        JavaSettings settings = JavaSettings.getInstance();
        JavaFile javaFile = getJavaFileFactory().createSourceFile(
                settings.getPackage(settings.getImplementationSubpackage()),
                ModelNaming.CLASS_UTILS);
        UtilsTemplate.getInstance().write(javaFile);
        getJavaFiles().add(javaFile);
    }
}
