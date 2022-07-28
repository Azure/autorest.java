// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;


import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.PageDetails;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonWriter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.List;

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
        JavaSettings settings = JavaSettings.getInstance();
        boolean generateWithJacksonDatabindAnnotations = !settings.isStreamStyleSerialization();

        List<String> imports = new ArrayList<>(4);
        imports.add(List.class.getName());

        if (generateWithJacksonDatabindAnnotations) {
            imports.add(JsonProperty.class.getName());
            imports.add(JsonGetter.class.getName());
            imports.add(JsonSetter.class.getName());
        } else {
            imports.add(JsonSerializable.class.getName());
            imports.add(JsonWriter.class.getName());
            imports.add(JsonReader.class.getName());
        }

        javaFile.declareImport(imports);

        javaFile.javadocComment(settings.getMaximumJavadocCommentWidth(), comment -> {
            comment.description("An instance of this class defines a page of Azure resources and a link to get the next page of resources, if any.");
            comment.param("<T>", "type of Azure resource");
        });

        String implementedInterfaces = "Page<T>";
        if (!generateWithJacksonDatabindAnnotations) {
            implementedInterfaces += ", JsonCapable<" + pageClass.getClassName() + "<T>>";
        }

        javaFile.publicFinalClass(pageClass.getClassName() + "<T> implements " + implementedInterfaces, classBlock -> {
            // Create the private Field for the next page link.
            classBlock.javadocComment(comment -> comment.description("The link to the next page."));
            if (generateWithJacksonDatabindAnnotations) {
                classBlock.annotation("JsonProperty(\"" + pageClass.getNextLinkName() + "\"");
            }
            classBlock.privateMemberVariable("String", "nextPageLink");

            // Create the private Field for the page elements.
            classBlock.javadocComment(comment -> comment.description("The list of items."));
            if (generateWithJacksonDatabindAnnotations) {
                classBlock.annotation("JsonProperty(\"" + pageClass.getItemName() + "\")");
            }
            classBlock.privateMemberVariable("List<T>", "items");

            // Create the getter Method for the next page link.
            classBlock.javadocComment(comment -> {
                comment.description("Gets the link to the next page.");
                comment.methodReturns("the link to the next page.");
            });
            classBlock.annotation("Override");
            addJsonGetter(classBlock, settings, pageClass.getNextLinkName());
            classBlock.publicMethod("String nextPageLink()", function -> function.methodReturn("this.nextPageLink"));

            // Create the getter Method for the page elements.
            classBlock.javadocComment(comment -> {
                comment.description("Gets the list of items.");
                comment.methodReturns("the list of items in {@link List}.");
            });
            classBlock.annotation("Override");
            addJsonGetter(classBlock, settings, pageClass.getItemName());
            classBlock.publicMethod("List<T> items()", function -> function.methodReturn("items"));

            // Create the setter Method for the next page link.
            classBlock.javadocComment(comment -> {
                comment.description("Sets the link to the next page.");
                comment.param("nextPageLink", "the link to the next page.");
                comment.methodReturns("this Page object itself.");
            });
            addJsonSetter(classBlock, settings, pageClass.getNextLinkName());
            classBlock.publicMethod(pageClass.getClassName() + "<T> setNextPageLink(String nextPageLink)", function -> {
                function.line("this.nextPageLink = nextPageLink;");
                function.methodReturn("this");
            });

            // Create the setter Method for the page elements.
            classBlock.javadocComment(comment -> {
                comment.description("Sets the list of items.");
                comment.param("items", "the list of items in {@link List}.");
                comment.methodReturns("this Page object itself.");
            });
            addJsonSetter(classBlock, settings, pageClass.getItemName());
            classBlock.publicMethod(pageClass.getClassName() + "<T> setItems(List<T> items)", function -> {
                function.line("this.items = items;");
                function.methodReturn("this");
            });

            if (!generateWithJacksonDatabindAnnotations) {
                classBlock.annotation("Override");
                classBlock.publicMethod("JsonWriter toJson(JsonWriter jsonWriter)", function -> {
                    function.line("jsonWriter.writeStartObject()");
                    function.indent(() -> function.line(".writeStringField(\"%s\", nextPageLink, false)",
                        pageClass.getNextLinkName()));
                    function.line();

                    // TODO (alzimmer): Introspect the item type to determine how it should be serialized.
                    function.line("jsonWriter.writeArray(\"%s\", items, String::valueOf);", pageClass.getItemName());

                    function.line();
                    function.methodReturn("jsonWriter.writeEndObject().flush()");
                });
            }
        });
    }
}
