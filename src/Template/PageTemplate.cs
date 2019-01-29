// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Text;
using AutoRest.Core;
using AutoRest.Core.Utilities;
using AutoRest.Extensions;
using AutoRest.Core.Model;
using Newtonsoft.Json;
using AutoRest.Core.Utilities.Collections;
using Newtonsoft.Json.Linq;
using AutoRest.Java.Model;
using System.Text.RegularExpressions;

namespace AutoRest.Java
{
    /// <summary>
    /// Writes a PageDetails to a JavaFile.
    /// </summary>
    public class PageTemplate : IJavaTemplate<PageDetails, JavaFile>
    {
        private static PageTemplate _instance = new PageTemplate();
        public static PageTemplate Instance => _instance;

        private PageTemplate()
        {
        }

        public void Write(PageDetails pageClass, JavaFile javaFile)
        {
            javaFile.Import("com.fasterxml.jackson.annotation.JsonProperty",
                            "com.microsoft.azure.v2.Page",
                            "java.util.List");

            javaFile.JavadocComment(JavaSettings.Instance.MaximumJavadocCommentWidth, comment =>
            {
                comment.Description("An instance of this class defines a page of Azure resources and a link to get the next page of resources, if any.");
                comment.Param("<T>", "type of Azure resource");
            });
            javaFile.PublicFinalClass($"{pageClass.ClassName}<T> implements Page<T>", classBlock =>
            {
                classBlock.JavadocComment(comment =>
                {
                    comment.Description("The link to the next page.");
                });
                classBlock.Annotation($"JsonProperty(\"{pageClass.NextLinkName}\")");
                classBlock.PrivateMemberVariable("String", "nextPageLink");

                classBlock.JavadocComment(comment =>
                {
                    comment.Description("The list of items.");
                });
                classBlock.Annotation($"JsonProperty(\"{pageClass.ItemName}\")");
                classBlock.PrivateMemberVariable("List<T>", "items");

                classBlock.JavadocComment(comment =>
                {
                    comment.Description("Gets the link to the next page.");
                    comment.Return("the link to the next page.");
                });
                classBlock.Annotation("Override");
                classBlock.PublicMethod("String nextPageLink()", function =>
                {
                    function.Return("this.nextPageLink");
                });

                classBlock.JavadocComment(comment =>
                {
                    comment.Description("Gets the list of items.");
                    comment.Return("the list of items in {@link List}.");
                });
                classBlock.Annotation("Override");
                classBlock.PublicMethod("List<T> items()", function =>
                {
                    function.Return("items");
                });

                classBlock.JavadocComment(comment =>
                {
                    comment.Description("Sets the link to the next page.");
                    comment.Param("nextPageLink", "the link to the next page.");
                    comment.Return("this Page object itself.");
                });
                classBlock.PublicMethod($"{pageClass.ClassName}<T> setNextPageLink(String nextPageLink)", function =>
                {
                    function.Line("this.nextPageLink = nextPageLink;");
                    function.Return("this");
                });

                classBlock.JavadocComment(comment =>
                {
                    comment.Description("Sets the list of items.");
                    comment.Param("items", "the list of items in {@link List}.");
                    comment.Return("this Page object itself.");
                });
                classBlock.PublicMethod($"{pageClass.ClassName}<T> setItems(List<T> items)", function =>
                {
                    function.Line("this.items = items;");
                    function.Return("this");
                });
            });
        }
    }
}