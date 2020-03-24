package com.azure.autorest.model.javamodel;

import com.azure.autorest.model.clientmodel.IType;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class JavaEnum {
    private JavaFileContents contents;
    private boolean previouslyAddedValue;
    private boolean addNewLine;

    public JavaEnum(JavaFileContents contents) {
        this.contents = contents;
    }

    private void addExpectedNewLine() {
        if (addNewLine) {
            contents.line();
            addNewLine = false;
        }
    }

    private void addExpectedCommaAndNewLine() {
        if (previouslyAddedValue) {
            contents.line(",");
            previouslyAddedValue = false;
        }

        addExpectedNewLine();
    }

    private void addExpectedSemicolonAndNewLine() {
        if (previouslyAddedValue) {
            contents.line(";");
            previouslyAddedValue = false;
        }

        addExpectedNewLine();
    }

    public final void addExpectedNewLineAfterLastValue() {
        if (previouslyAddedValue) {
            contents.line();
            previouslyAddedValue = false;
            addNewLine = false;
        }
    }

    public final void value(String name, String value) {
        addExpectedCommaAndNewLine();
        contents.javadocComment(String.format("Enum value %1$s.", value));
        contents.text(String.format("%1$s(\"%2$s\")", name, value));
        previouslyAddedValue = true;
        addNewLine = true;
    }

    public final void value(String name, String value, IType type) {
        addExpectedCommaAndNewLine();
        contents.javadocComment(String.format("Enum value %1$s.", value));
        contents.text(String.format("%1$s(%2$s)", name, type.defaultValueExpression(value)));
        previouslyAddedValue = true;
        addNewLine = true;
    }

    public final void privateFinalMemberVariable(String variableType, String variableName) {
        addExpectedSemicolonAndNewLine();
        contents.line(String.format("private final %1$s %2$s;", variableType, variableName));
        addNewLine = true;
    }

    public final void constructor(String constructorSignature, Consumer<JavaBlock> constructor) {
        addExpectedSemicolonAndNewLine();
        contents.block(String.format("%1$s", constructorSignature), constructor);
        previouslyAddedValue = false;
        addNewLine = true;
    }

    public final void method(JavaVisibility visibility, List<JavaModifier> modifiers, String methodSignature, Consumer<JavaBlock> method) {
        addExpectedSemicolonAndNewLine();
        contents.method(visibility, modifiers, methodSignature, method);
        previouslyAddedValue = false;
        addNewLine = true;
    }

    public final void PublicMethod(String methodSignature, Consumer<JavaBlock> method) {
        method(JavaVisibility.Public, null, methodSignature, method);
    }

    public final void PublicStaticMethod(String methodSignature, Consumer<JavaBlock> method) {
        method(JavaVisibility.Public, Collections.singletonList(JavaModifier.Static), methodSignature, method);
    }

    public final void javadocComment(String description) {
        addExpectedSemicolonAndNewLine();
        contents.javadocComment(description);
    }

    public final void javadocComment(Consumer<JavaJavadocComment> commentAction) {
        addExpectedSemicolonAndNewLine();
        contents.javadocComment(commentAction);
    }

    public final void annotation(String... annotations) {
        addExpectedSemicolonAndNewLine();
        contents.annotation(annotations);
    }
}
