package com.azure.autorest.model.javamodel;

import java.util.function.Consumer;

public interface JavaType extends JavaContext
{
    void PublicMethod(String methodSignature, Consumer<JavaBlock> functionBlock);
}