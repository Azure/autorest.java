package com.azure.autorest.template;


// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.PageDetails;
import com.azure.autorest.model.javamodel.JavaFile;

import static com.azure.autorest.util.TemplateUtil.addJsonGetter;
import static com.azure.autorest.util.TemplateUtil.addJsonSetter;

/**
 * Writes a PageDetails to a JavaFile.
 */
public class PageTemplate implements IJavaTemplate<PageDetails, JavaFile> {
    private static final PageTemplate INSTANCE = new PageTemplate();

    private PageTemplate() {
    }

    public static PageTemplate getInstance() {
        return INSTANCE;
    }

    public final void write(PageDetails pageClass, JavaFile javaFile) {
        // What is com.microsoft.azure.v3.page? Is it still required here?
        javaFile.declareImport("com.fasterxml.jackson.annotation.JsonProperty",
            "com.fasterxml.jackson.annotation.JsonGetter", "com.fasterxml.jackson.annotation.JsonSetter",
            "com.microsoft.azure.v3.Page", "java.util.List");

        javaFile.javadocComment(JavaSettings.getInstance().getMaximumJavadocCommentWidth(), comment ->
        {
            comment.description("An instance of this class defines a page of Azure resources and a link to get the next page of resources, if any.");
            comment.param("<T>", "type of Azure resource");
        });
        javaFile.publicFinalClass(String.format("%1$s<T> implements Page<T>", pageClass.getClassName()), classBlock ->
        {
            // Create the private Field for the next page link.
            classBlock.javadocComment(comment -> comment.description("The link to the next page."));
            classBlock.annotation(String.format("JsonProperty(\"%1$s\")", pageClass.getNextLinkName()));
            classBlock.privateMemberVariable("String", "nextPageLink");

            // Create the private Field for the page elements.
            classBlock.javadocComment(comment -> comment.description("The list of items."));
            classBlock.annotation(String.format("JsonProperty(\"%1$s\")", pageClass.getItemName()));
            classBlock.privateMemberVariable("List<T>", "items");

            // Create the getter Method for the next page link.
            classBlock.javadocComment(comment ->
            {
                comment.description("Gets the link to the next page.");
                comment.methodReturns("the link to the next page.");
            });
            classBlock.annotation("Override");
            addJsonGetter(classBlock, JavaSettings.getInstance(), pageClass.getNextLinkName());
            classBlock.publicMethod("String nextPageLink()", function -> function.methodReturn("this.nextPageLink"));

            // Create the getter Method for the page elements.
            classBlock.javadocComment(comment ->
            {
                comment.description("Gets the list of items.");
                comment.methodReturns("the list of items in {@link List}.");
            });
            classBlock.annotation("Override");
            addJsonGetter(classBlock, JavaSettings.getInstance(), pageClass.getItemName());
            classBlock.publicMethod("List<T> items()", function -> function.methodReturn("items"));

            // Create the setter Method for the next page link.
            classBlock.javadocComment(comment ->
            {
                comment.description("Sets the link to the next page.");
                comment.param("nextPageLink", "the link to the next page.");
                comment.methodReturns("this Page object itself.");
            });
            addJsonSetter(classBlock, JavaSettings.getInstance(), pageClass.getNextLinkName());
            classBlock.publicMethod(String.format("%1$s<T> setNextPageLink(String nextPageLink)",
                pageClass.getClassName()), function -> {
                function.line("this.nextPageLink = nextPageLink;");
                function.methodReturn("this");
            });

            // Create the setter Method for the page elements.
            classBlock.javadocComment(comment ->
            {
                comment.description("Sets the list of items.");
                comment.param("items", "the list of items in {@link List}.");
                comment.methodReturns("this Page object itself.");
            });
            addJsonSetter(classBlock, JavaSettings.getInstance(), pageClass.getItemName());
            classBlock.publicMethod(String.format("%1$s<T> setItems(List<T> items)", pageClass.getClassName()),
                function -> {
                function.line("this.items = items;");
                function.methodReturn("this");
            });
        });
    }
}
