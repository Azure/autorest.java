// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.javamodel;

import java.util.function.Consumer;

public interface JavaType extends JavaContext {
    void publicMethod(String methodSignature, Consumer<JavaBlock> functionBlock);

    void javadocComment(Consumer<JavaJavadocComment> commentDescriptionAction, Consumer<JavaJavadocComment> commentTagsAction, boolean withGeneratedMarker);

    void javadocComment(int wordWrapWidth, Consumer<JavaJavadocComment> commentDescriptionAction, Consumer<JavaJavadocComment> commentTagsAction, boolean withGeneratedMarker);
}
