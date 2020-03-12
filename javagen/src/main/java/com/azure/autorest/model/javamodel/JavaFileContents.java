//====================================================================================================
//The Free Edition of C# to Java Converter limits conversion output to 100 lines per snippet.

//To subscribe to the Premium Edition, visit our website:
//https://www.tangiblesoftwaresolutions.com/order/order-csharp-to-java.html
//====================================================================================================

package com.azure.autorest.model.javamodel;

import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class JavaFileContents {
    private static final String singleIndent = "    ";

    private StringBuilder contents;
    private StringBuilder linePrefix;

    private Integer wordWrapWidth = null;

    private CurrentLineType currentLineType = CurrentLineType.values()[0];

    public JavaFileContents() {
        this(null);
    }

    public JavaFileContents(String fileContents) {
        contents = new StringBuilder();
        linePrefix = new StringBuilder();

        if (fileContents != null && !fileContents.isEmpty()) {
            contents.append(fileContents);
        }
    }

    private static String toString(JavaVisibility visiblity) {
        String result;
        switch (visiblity) {
            case PackagePrivate:
                result = "";
                break;

            default:
                result = visiblity.toString().toLowerCase() + ' ';
                break;
        }
        return result;
    }

    private static String toString(List<JavaModifier> modifiers) {
        return modifiers == null ? "" : modifiers.stream().map(modifier -> modifier.toString().toLowerCase() + ' ').collect(Collectors.joining(""));
    }

    @Override
    public String toString() {
        return contents.toString();
    }

    public final String[] getLines() {
        return toString().split(java.util.regex.Pattern.quote("\n"), -1);
    }

    public final void addToPrefix(String toAdd) {
        linePrefix.append(toAdd);
    }

    private void removeFromPrefix(String toRemove) {
        int toRemoveLength = toRemove.length();
        if (linePrefix.length() <= toRemoveLength) {
            linePrefix.setLength(0);
        } else {
            linePrefix.delete(linePrefix.length() - toRemoveLength, linePrefix.length() - toRemoveLength + toRemoveLength);
        }
    }

    public final void setWordWrapWidth(Integer wordWrapWidth) {
        this.wordWrapWidth = wordWrapWidth;
    }

    private void withWordWrap(int wordWrapWidth, Runnable action) {
        setWordWrapWidth(wordWrapWidth);
        action.run();
        setWordWrapWidth(null);
    }

    public final void indent(Runnable action) {
        increaseIndent();
        action.run();
        decreaseIndent();
    }

    public final void increaseIndent() {
        addToPrefix(singleIndent);
    }

    public final void decreaseIndent() {
        removeFromPrefix(singleIndent);
    }

    private List<String> wordWrap(String line, boolean addPrefix) {
        ArrayList<String> lines = new ArrayList<String>();

        if (wordWrapWidth == null) {
            lines.add(line);
        } else {
            // Subtract an extra column from the word wrap width because columns generally are
            // 1 -based instead of 0-based.
            int wordWrapIndexMinusLinePrefixLength = wordWrapWidth - (addPrefix ? linePrefix.length() : 0) - 1;
            List<String> wrappedLines = CodeNamer.wordWrap(line, wordWrapIndexMinusLinePrefixLength);
            for (int i = 0; i < wrappedLines.size() - 1; i++) {
                lines.add(wrappedLines.get(i) + System.lineSeparator());
            }

            String lastWrappedLine = wrappedLines.isEmpty() ? null : wrappedLines.get(wrappedLines.size() - 1);
            if (lastWrappedLine != null && !lastWrappedLine.isEmpty()) {
                lines.add(lastWrappedLine);
            }
        }

        return lines;
    }

    private void text(String text, boolean addPrefix) {
        ArrayList<String> lines = new ArrayList<String>();

        if (text == null || text.isEmpty()) {
            lines.add("");
        } else {
            int lineStartIndex = 0;
            int textLength = text.length();
            while (lineStartIndex < textLength) {
                int newLineCharacterIndex = text.indexOf('\n', lineStartIndex);
                if (newLineCharacterIndex == -1) {
                    String line = text.substring(lineStartIndex);
                    List<String> wrappedLines = wordWrap(line, addPrefix);
                    lines.addAll(wrappedLines);
                    lineStartIndex = textLength;
                } else {
                    int nextLineStartIndex = newLineCharacterIndex + 1;
                    String line = text.substring(lineStartIndex, nextLineStartIndex);
                    List<String> wrappedLines = wordWrap(line, addPrefix);
                    lines.addAll(wrappedLines);
                    lineStartIndex = nextLineStartIndex;
                }
            }
        }

        String prefix = addPrefix ? linePrefix.toString() : null;
        for (String line : lines) {
            if (addPrefix && prefix != null && !prefix.trim().isEmpty() || (prefix != null && !prefix.isEmpty() && line != null && !line.trim().isEmpty())) {
                contents.append(prefix);
            }

            contents.append(line);
        }
    }

    public final void text(String text) {
        if (currentLineType == CurrentLineType.Empty) {
            text(text, true);
        } else if (currentLineType == CurrentLineType.Text) {
            text(text, false);
        } else if (currentLineType == CurrentLineType.AfterIf) {
            line("", false);
            text(text, true);
        }
        currentLineType = CurrentLineType.Text;
    }

    private void line(String text, boolean addPrefix) {
        text(String.format("%s%s", text, System.lineSeparator()), addPrefix);
        currentLineType = CurrentLineType.Empty;
    }

    public void line(String text, Object... formattedArguments) {
        if (formattedArguments != null && formattedArguments.length > 0) {
            text = String.format(text, formattedArguments);
        }

        if (currentLineType == CurrentLineType.Empty) {
            line(text, true);
        } else if (currentLineType == CurrentLineType.Text) {
            line(text, false);
        } else if (currentLineType == CurrentLineType.AfterIf) {
            line("", false);
            line(text, true);
        }
        currentLineType = CurrentLineType.Empty;
    }

    public void line() {
        line("");
    }

    public void declarePackage(String pkg) {
        line("package %s;", pkg);
    }

    public void block(String text, Consumer<JavaBlock> bodyAction) {
        line("%s {", text);
        indent(() ->
                bodyAction.accept(new JavaBlock(this)));
        line("}");
    }

    public void declareImport(String... imports) {
        declareImport(Arrays.asList(imports));
    }

    public void declareImport(List<String> imports) {
        if (imports != null && !imports.isEmpty()) {
            Set<String> importSet = new TreeSet<>(new JavaImportComparer());
            importSet.addAll(imports);
            for (String toImport : importSet) {
                if (toImport != null && !toImport.isEmpty()) {
                    line("import %s;", toImport);
                }
            }
            line();
        }
    }

    public void lineComment(String text) {
        lineComment(comment -> comment.line(text));
    }

    public void lineComment(Consumer<JavaLineComment> commentAction) {
        addToPrefix("// ");
        commentAction.accept(new JavaLineComment(this));
        removeFromPrefix("// ");
    }

    public void lineComment(int wordWrapWidth, Consumer<JavaLineComment> commentAction) {
        lineComment((comment) -> withWordWrap(wordWrapWidth, () ->
                commentAction.accept(new JavaLineComment(this))));
    }

    public void blockComment(String text) {
        blockComment(comment -> comment.line(text));
    }

    public void blockComment(Consumer<JavaLineComment> commentAction) {
        line("/*");
        addToPrefix(" * ");
        commentAction.accept(new JavaLineComment(this));
        removeFromPrefix(" * ");
        line(" */");
    }

    public void blockComment(int wordWrapWidth, Consumer<JavaLineComment> commentAction) {
        blockComment((comment) -> withWordWrap(wordWrapWidth, () ->
                commentAction.accept(new JavaLineComment(this))));
    }

    public void javadocComment(String text) {
        javadocComment(comment -> comment.description(text));
    }

    public void javadocComment(Consumer<JavaJavadocComment> commentAction) {
        line("/**");
        addToPrefix(" * ");
        commentAction.accept(new JavaJavadocComment(this));
        removeFromPrefix(" * ");
        line(" */");
    }

    public void javadocComment(int wordWrapWidth, Consumer<JavaJavadocComment> commentAction) {
        javadocComment((comment) -> withWordWrap(wordWrapWidth, () ->
                commentAction.accept(new JavaJavadocComment(this))));
    }

    public void methodReturn(String text) {
        line("return %s;", text);
    }

    public void returnAnonymousClass(String anonymousClassDeclaration, Consumer<JavaClass> anonymousClassBlock) {
        line("return %s {", anonymousClassDeclaration);
        indent(() -> {
            JavaClass javaClass = new JavaClass(this);
            anonymousClassBlock.accept(javaClass);
        });
        line("};");
    }

    public void annotation(String... annotations) {
        annotation(Arrays.asList(annotations));
    }

    public void annotation(List<String> annotations) {
        if (annotations != null && !annotations.isEmpty()) {
            for (String annotation : annotations) {
                if (annotation != null && !annotation.isEmpty()) {
                    line("@%s", annotation);
                }
            }
        }
    }

    public void classBlock(JavaVisibility visibility, List<JavaModifier> modifiers, String classDeclaration, Consumer<JavaClass> classAction) {
        block(String.format("%s%sclass %s", toString(visibility), toString(modifiers), classDeclaration), blockAction -> {
            if (classAction != null) {
                JavaClass javaClass = new JavaClass(this);
                classAction.accept(javaClass);
            }
        });
    }

    public void method(JavaVisibility visibility, List<JavaModifier> modifiers, String methodSignature, Consumer<JavaBlock> method) {
        block(String.format("%s%s%s", toString(visibility), toString(modifiers), methodSignature), method);
    }

    public void constructor(JavaVisibility visibility, String constructorSignature, Consumer<JavaBlock> constructor) {
        block(String.format("%s%s", toString(visibility), constructorSignature), constructor);
    }

    public void enumBlock(JavaVisibility visibility, String enumName, Consumer<JavaEnum> enumAction) {
        block(String.format("%senum %s", toString(visibility), enumName), block -> {
            if (enumAction != null) {
                JavaEnum javaEnum = new JavaEnum(this);
                enumAction.accept(javaEnum);
                javaEnum.addExpectedNewLineAfterLastValue();
            }
        });
    }

    public void interfaceBlock(JavaVisibility visibility, String interfaceSignature, Consumer<JavaInterface> interfaceAction) {
        line("%sinterface %s {", toString(visibility), interfaceSignature);
        indent(() -> interfaceAction.accept(new JavaInterface(this)));
        line("}");
    }

    public void ifBlock(String condition, Consumer<JavaBlock> ifAction) {
        line("if (%s) {", condition);
        indent(() ->
        {
            ifAction.accept(new JavaBlock(this));
        });
        text("}");
        currentLineType = CurrentLineType.AfterIf;
    }

    public void elseBlock(Consumer<JavaBlock> elseAction) {
        line(" else {", false);
        indent(() -> elseAction.accept(new JavaBlock(this)));
        line("}");
    }

    public void lambda(String parameterType, String parameterName, Consumer<JavaLambda> body) {
        text(String.format("(%s %s) -> ", parameterType, parameterName));
        try (JavaLambda lambda = new JavaLambda(this)) {
            body.accept(lambda);
        }
    }

    public void lambda(String parameterType, String parameterName, String returnExpression) {
        lambda(parameterType, parameterName, lambda -> lambda.lambdaReturn(returnExpression));
    }

    private enum CurrentLineType {
        Empty,
        AfterIf,
        Text;

        public static final int SIZE = java.lang.Integer.SIZE;

        public static CurrentLineType forValue(int value) {
            return values()[value];
        }

        public int getValue() {
            return this.ordinal();
        }
    }
}
