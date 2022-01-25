/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.javamodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.fluent.model.clientmodel.FluentExample;
import com.azure.autorest.fluent.model.clientmodel.FluentManager;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.fluent.model.projectmodel.Changelog;
import com.azure.autorest.fluent.model.projectmodel.FluentProject;
import com.azure.autorest.model.projectmodel.TextFile;
import com.azure.autorest.fluent.template.ChangelogTemplate;
import com.azure.autorest.fluent.template.FluentExampleTemplate;
import com.azure.autorest.fluent.template.FluentManagerTemplate;
import com.azure.autorest.fluent.template.FluentResourceCollectionImplementationTemplate;
import com.azure.autorest.fluent.template.FluentResourceCollectionInterfaceTemplate;
import com.azure.autorest.fluent.template.FluentResourceModelImplementationTemplate;
import com.azure.autorest.fluent.template.FluentResourceModelInterfaceTemplate;
import com.azure.autorest.fluent.template.ReadmeTemplate;
import com.azure.autorest.fluent.template.SampleTemplate;
import com.azure.autorest.fluent.template.UtilsTemplate;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaPackage;

import java.util.List;

public class FluentJavaPackage extends JavaPackage {

    public FluentJavaPackage(NewPlugin host) {
        super(host);
    }

    public void addReadmeMarkdown(FluentProject project) {
        TextFile textFile = new TextFile("README.md", new ReadmeTemplate().write(project));
        this.checkDuplicateFile(textFile.getFilePath());
        textFiles.add(textFile);
    }

    public void addChangelogMarkdown(Changelog changelog) {
        TextFile textFile = new TextFile("CHANGELOG.md", new ChangelogTemplate().write(changelog));
        this.checkDuplicateFile(textFile.getFilePath());
        textFiles.add(textFile);
    }

    public final void addSampleMarkdown(List<FluentExample> examples, List<JavaFile> sampleJavaFiles) {
        TextFile textFile = new TextFile("SAMPLE.md", new SampleTemplate().write(examples, sampleJavaFiles));
        this.checkDuplicateFile(textFile.getFilePath());
        textFiles.add(textFile);
    }

    public final void addFluentResourceModel(FluentResourceModel model) {
        JavaFile javaFile = getJavaFileFactory().createSourceFile(
                model.getInterfaceType().getPackage(),
                model.getInterfaceType().getName());
        FluentResourceModelInterfaceTemplate.getInstance().write(model, javaFile);
        addJavaFile(javaFile);

        javaFile = getJavaFileFactory().createSourceFile(
                model.getImplementationType().getPackage(),
                model.getImplementationType().getName());
        FluentResourceModelImplementationTemplate.getInstance().write(model, javaFile);
        addJavaFile(javaFile);
    }

    public final void addFluentResourceCollection(FluentResourceCollection collection) {
        JavaFile javaFile = getJavaFileFactory().createSourceFile(
                collection.getInterfaceType().getPackage(),
                collection.getInterfaceType().getName());
        FluentResourceCollectionInterfaceTemplate.getInstance().write(collection, javaFile);
        addJavaFile(javaFile);

        javaFile = getJavaFileFactory().createSourceFile(
                collection.getImplementationType().getPackage(),
                collection.getImplementationType().getName());
        FluentResourceCollectionImplementationTemplate.getInstance().write(collection, javaFile);
        addJavaFile(javaFile);
    }

    public final void addFluentManager(FluentManager model, FluentProject project) {
        JavaFile javaFile = getJavaFileFactory().createSourceFile(
                model.getType().getPackage(),
                model.getType().getName());
        FluentManagerTemplate.getInstance().write(model, project, javaFile);
        addJavaFile(javaFile);
    }

    public final void addUtils() {
        JavaSettings settings = JavaSettings.getInstance();
        JavaFile javaFile = getJavaFileFactory().createSourceFile(
                settings.getPackage(settings.getImplementationSubpackage()),
                ModelNaming.CLASS_UTILS);
        UtilsTemplate.getInstance().write(javaFile);
        addJavaFile(javaFile);
    }

    public final JavaFile addSample(FluentExample example) {
        JavaFile javaFile = getJavaFileFactory().createSampleFile(
                example.getPackageName(), example.getClassName());
        FluentExampleTemplate.getInstance().write(example, javaFile);
        addJavaFile(javaFile);
        return javaFile;
    }
}
