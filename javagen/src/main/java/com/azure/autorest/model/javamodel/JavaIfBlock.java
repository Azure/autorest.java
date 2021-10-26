package com.azure.autorest.model.javamodel;

import java.util.function.Consumer;

public class JavaIfBlock {
    private JavaFileContents contents;

    public JavaIfBlock(JavaFileContents contents) {
        this.contents = contents;
    }

    public final JavaIfBlock elseIfBlock(String condition, Consumer<JavaBlock> ifAction) {
        contents.elseIfBlock(condition, ifAction);
        return new JavaIfBlock(contents);
    }

    public final void elseBlock(Consumer<JavaBlock> elseAction) {
        contents.elseBlock(elseAction);
    }
}
