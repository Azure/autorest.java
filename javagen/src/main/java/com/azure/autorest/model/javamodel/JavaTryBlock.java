/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.model.javamodel;

import java.util.function.Consumer;

public class JavaTryBlock {
    private JavaFileContents contents;

    public JavaTryBlock(JavaFileContents contents) {
        this.contents = contents;
    }

    public final JavaCatchBlock catchBlock(String exception, Consumer<JavaBlock> catchAction) {
        contents.catchBlock(exception, catchAction);
        return new JavaCatchBlock(contents);
    }

    public final void finallyBlock(Consumer<JavaBlock> finallyAction) {
        contents.finallyBlock(finallyAction);
    }
}
