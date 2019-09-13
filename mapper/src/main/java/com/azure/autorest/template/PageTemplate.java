package com.azure.autorest.template;


// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.PageDetails;
import com.azure.autorest.model.javamodel.JavaFile;

/**
 Writes a PageDetails to a JavaFile.
*/
public class PageTemplate implements IJavaTemplate<PageDetails, JavaFile>
{
    private static PageTemplate _instance = new PageTemplate();
    public static PageTemplate getInstance()
    {
        return _instance;
    }

    private PageTemplate()
    {
    }

    public final void Write(PageDetails pageClass, JavaFile javaFile)
    {
        javaFile.Import("com.fasterxml.jackson.annotation.JsonProperty", "com.microsoft.azure.v3.Page", "java.util.List");

        javaFile.JavadocComment(JavaSettings.getInstance().getMaximumJavadocCommentWidth(), comment ->
        {
                comment.Description("An instance of this class defines a page of Azure resources and a link to get the next page of resources, if any.");
                comment.Param("<T>", "type of Azure resource");
        });
        javaFile.PublicFinalClass(String.format("%1$s<T> implements Page<T>", pageClass.getClassName()), classBlock ->
        {
                classBlock.JavadocComment(comment ->
                {
                    comment.Description("The link to the next page.");
                });
                classBlock.Annotation(String.format("JsonProperty(\"%1$s\")", pageClass.getNextLinkName()));
                classBlock.PrivateMemberVariable("String", "nextPageLink");

                classBlock.JavadocComment(comment ->
                {
                    comment.Description("The list of items.");
                });
                classBlock.Annotation(String.format("JsonProperty(\"%1$s\")", pageClass.getItemName()));
                classBlock.PrivateMemberVariable("List<T>", "items");

                classBlock.JavadocComment(comment ->
                {
                    comment.Description("Gets the link to the next page.");
                    comment.Return("the link to the next page.");
                });
                classBlock.Annotation("Override");
                classBlock.PublicMethod("String nextPageLink()", function ->
                {
                    function.Return("this.nextPageLink");
                });

                classBlock.JavadocComment(comment ->
                {
                    comment.Description("Gets the list of items.");
                    comment.Return("the list of items in {@link List}.");
                });
                classBlock.Annotation("Override");
                classBlock.PublicMethod("List<T> items()", function ->
                {
                    function.Return("items");
                });

                classBlock.JavadocComment(comment ->
                {
                    comment.Description("Sets the link to the next page.");
                    comment.Param("nextPageLink", "the link to the next page.");
                    comment.Return("this Page object itself.");
                });
                classBlock.PublicMethod(String.format("%1$s<T> setNextPageLink(String nextPageLink)", pageClass.getClassName()), function ->
                {
                    function.Line("this.nextPageLink = nextPageLink;");
                    function.Return("this");
                });

                classBlock.JavadocComment(comment ->
                {
                    comment.Description("Sets the list of items.");
                    comment.Param("items", "the list of items in {@link List}.");
                    comment.Return("this Page object itself.");
                });
                classBlock.PublicMethod(String.format("%1$s<T> setItems(List<T> items)", pageClass.getClassName()), function ->
                {
                    function.Line("this.items = items;");
                    function.Return("this");
                });
        });
    }
}