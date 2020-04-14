/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.model;

import com.azure.autorest.model.javamodel.JavaFileContents;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Pattern;

public class JavaFileContentsTests {

    private static final int WIDTH = 80;

    @Test
    public void canWordwrapInComments() {
        final String commentString = "Subscription credentials which uniquely identify Microsoft Azure subscription. The subscription ID forms part of the URI for every service call.";

        JavaFileContents javaFileContents = new JavaFileContents();
        javaFileContents.blockComment(WIDTH, comment -> comment.line(commentString));
        String code = javaFileContents.toString();
        Assert.assertTrue(
                Arrays.stream(code.split(System.lineSeparator()))
                        .noneMatch(line -> line.length() > WIDTH)
        );

        final String commentStringWithLongUrl = "ARM resource ID of the source app. App resource ID is of the form \n/subscriptions/{subId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Web/sites/{siteName} for production slots and \n/subscriptions/{subId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Web/sites/{siteName}/slots/{slotName} for other slots.";

        javaFileContents = new JavaFileContents();
        javaFileContents.blockComment(WIDTH, comment -> comment.line(commentStringWithLongUrl));
        code = javaFileContents.toString();
        Assert.assertTrue(
                Arrays.stream(code.split(System.lineSeparator()))
                        .filter(line -> line.length() > WIDTH)
                        .allMatch(line -> Pattern.matches("^ *\\* *[^ ]+$", line))
        );
    }
}
