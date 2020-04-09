/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.model;

import com.azure.autorest.model.javamodel.JavaFileContents;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

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
    }
}
