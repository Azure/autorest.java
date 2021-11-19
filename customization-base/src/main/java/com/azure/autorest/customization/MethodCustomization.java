package com.azure.autorest.customization;

import com.azure.autorest.customization.implementation.CodeCustomization;
import com.azure.autorest.customization.implementation.Utils;
import com.azure.autorest.customization.implementation.ls.EclipseLanguageClient;
import com.azure.autorest.customization.implementation.ls.models.FileChangeType;
import com.azure.autorest.customization.implementation.ls.models.FileEvent;
import com.azure.autorest.customization.implementation.ls.models.SymbolInformation;
import com.azure.autorest.customization.implementation.ls.models.TextEdit;
import com.azure.autorest.customization.implementation.ls.models.WorkspaceEdit;
import com.azure.autorest.customization.models.Position;
import com.azure.autorest.customization.models.Range;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.azure.autorest.customization.implementation.Utils.replaceModifier;


/**
 * The method level customization for an AutoRest generated method.
 */
public final class MethodCustomization extends CodeCustomization {
    private final String packageName;
    private final String className;
    private final String methodName;
    private final String methodSignature;

    MethodCustomization(Editor editor, EclipseLanguageClient languageClient, String packageName, String className,
        String methodName, String methodSignature, SymbolInformation symbol) {
        super(editor, languageClient, symbol);
        this.packageName = packageName;
        this.className = className;
        this.methodName = methodName;
        this.methodSignature = methodSignature;
    }

    SymbolInformation getSymbol() {
        return symbol;
    }

    /**
     * Gets the name of the method this customization is using.
     *
     * @return The name of the method.
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Gets the name of the class containing the method.
     *
     * @return The name of the class containing the method.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Gets the Javadoc customization for this method.
     *
     * @return the Javadoc customization
     */
    public JavadocCustomization getJavadoc() {
        return new JavadocCustomization(editor, languageClient, fileUri, fileName,
            symbol.getLocation().getRange().getStart().getLine());
    }

    /**
     * Rename a method in the class. This is a refactor operation. All references to this method across the client
     * library will be automatically modified.
     *
     * @param newName the new name for the method
     * @return the current class customization for chaining
     */
    public MethodCustomization rename(String newName) {
        WorkspaceEdit edit = languageClient.renameSymbol(fileUri, symbol.getLocation().getRange().getStart(), newName);
        Utils.applyWorkspaceEdit(edit, editor, languageClient);

        return refreshCustomization(methodSignature.replace(methodName + "(", newName + "("));
    }

    /**
     * Add an annotation to a method in the class.
     *
     * @param annotation the annotation to add. The leading @ can be omitted.
     * @return the current class customization for chaining
     */
    public MethodCustomization addAnnotation(String annotation) {
        return Utils.addAnnotation(annotation, this, () -> refreshCustomization(methodSignature));
    }

    /**
     * Remove an annotation from the method.
     *
     * @param annotation the annotation to remove from the method. The leading @ can be omitted.
     * @return the current method customization for chaining
     */
    public MethodCustomization removeAnnotation(String annotation) {
        return Utils.removeAnnotation(this, compilationUnit -> compilationUnit.getClassByName(className).get()
            .getMethodsByName(methodName)
            .stream()
            .filter(method -> Utils.declarationContainsSymbol(method.getRange().get(), symbol.getLocation().getRange()))
            .findFirst().get()
            .getAnnotationByName(Utils.cleanAnnotationName(annotation)), () -> refreshCustomization(methodSignature));
    }

    /**
     * Replace the modifier for this method.
     * <p>
     * For compound modifiers such as {@code public abstract} use bitwise OR ({@code |}) of multiple Modifiers, {@code
     * Modifier.PUBLIC | Modifier.ABSTRACT}.
     * <p>
     * Pass {@code 0} for {@code modifiers} to indicate that the method has no modifiers.
     *
     * @param modifiers The {@link Modifier Modifiers} for the method.
     * @return The updated MethodCustomization object.
     * @throws IllegalArgumentException If the {@code modifier} is less than to {@code 0} or any {@link Modifier}
     * included in the bitwise OR isn't a valid method {@link Modifier}.
     */
    public MethodCustomization setModifier(int modifiers) {
        replaceModifier(symbol, editor, languageClient, "(?:.+ )?(\\w+ )" + methodName + "\\(",
            "$1" + methodName + "(", Modifier.methodModifiers(), modifiers);

        return refreshCustomization(methodSignature);
    }

    /**
     * Replace the parameters of the method.
     *
     * @param newParameters New method parameters.
     * @return The updated MethodCustomization object.
     */
    public MethodCustomization replaceParameters(String newParameters) {
        return Utils.replaceParameters(newParameters, this,
            () -> refreshCustomization(String.format("%s(%s)", methodName, newParameters)));
    }

    /**
     * Replace the body of the method.
     *
     * @param newBody New method body.
     * @return The updated MethodCustomization object.
     */
    public MethodCustomization replaceBody(String newBody) {
        return Utils.replaceBody(newBody, this, () -> refreshCustomization(methodSignature));
    }

    /**
     * Change the return type of a method. The new return type will be automatically imported.
     *
     * <p>
     * The {@code returnValueFormatter} can be used to transform the return value. If the original return type is {@code
     * void}, simply pass the new return expression to {@code returnValueFormatter}; if the new return type is {@code
     * void}, pass {@code null} to {@code returnValueFormatter}; if either the original return type nor the new return
     * type is {@code void}, the {@code returnValueFormatter} should be a String formatter that contains exactly 1
     * instance of {@code %s}.
     *
     * @param newReturnType the simple name of the new return type
     * @param returnValueFormatter the return value String formatter as described above
     * @return the current class customization for chaining
     */
    public MethodCustomization setReturnType(String newReturnType, String returnValueFormatter) {
        return setReturnType(newReturnType, returnValueFormatter, false);
    }

    /**
     * Change the return type of the method. The new return type will be automatically imported.
     *
     * <p>
     * The {@code returnValueFormatter} can be used to transform the return value. If the original return type is {@code
     * void}, simply pass the new return expression to {@code returnValueFormatter}; if the new return type is {@code
     * void}, pass {@code null} to {@code returnValueFormatter}; if either the original return type nor the new return
     * type is {@code void}, the {@code returnValueFormatter} should be a String formatter that contains exactly 1
     * instance of {@code %s}.
     *
     * @param newReturnType the simple name of the new return type
     * @param returnValueFormatter the return value String formatter as described above
     * @param replaceReturnStatement if set to {@code true}, the return statement will be replaced by the provided
     * returnValueFormatter text with exactly one instance of {@code %s}. If set to true, appropriate semi-colons,
     * parentheses, opening and closing of code blocks have to be taken care of in the {@code returnValueFormatter}.
     * @return the current class customization for chaining
     */
    public MethodCustomization setReturnType(String newReturnType, String returnValueFormatter, boolean replaceReturnStatement) {
        List<TextEdit> edits = new ArrayList<>();

        int line = symbol.getLocation().getRange().getStart().getLine();
        Position start = new Position(line, 0);
        String oldLineContent = editor.getFileLine(fileName, line);
        Position end = new Position(line, oldLineContent.length());
        String newLineContent = oldLineContent.replaceFirst("(\\w.* )?(\\w+) " + methodName + "\\(",
            "$1" + newReturnType + " " + methodName + "(");
        TextEdit signatureEdit = new TextEdit();
        signatureEdit.setNewText(newLineContent);
        signatureEdit.setRange(new Range(start, end));
        edits.add(signatureEdit);

        String methodIndent = Utils.getIndent(editor.getFileLine(fileName, line));
        String methodContentIndent = Utils.getIndent(editor.getFileLine(fileName, line + 1));
        String oldReturnType = oldLineContent.replaceAll(" " + methodName + "\\(.*", "")
            .replaceFirst(methodIndent + "(\\w.* )?", "").trim();
        int returnLine = -1;
        while (!oldLineContent.startsWith(methodIndent + "}")) {
            if (oldLineContent.contains("return ")) {
                returnLine = line;
            }
            oldLineContent = editor.getFileLine(fileName, ++line);
        }
        if (returnLine == -1) {
            // no return statement, originally void return type
            editor.insertBlankLine(fileName, line, false);
            FileEvent blankLineEvent = new FileEvent();
            blankLineEvent.setUri(fileUri);
            blankLineEvent.setType(FileChangeType.CHANGED);
            languageClient.notifyWatchedFilesChanged(Collections.singletonList(blankLineEvent));

            TextEdit returnEdit = new TextEdit();
            returnEdit.setRange(new Range(new Position(line, 0), new Position(line, 0)));
            returnEdit.setNewText(methodContentIndent + "return " + returnValueFormatter + ";");
            edits.add(returnEdit);
        } else if (newReturnType.equals("void")) {
            // remove return statement
            TextEdit returnEdit = new TextEdit();
            returnEdit.setNewText("");
            returnEdit.setRange(new Range(new Position(returnLine, 0), new Position(line, 0)));
            edits.add(returnEdit);
        } else {
            // replace return statement
            TextEdit returnValueEdit = new TextEdit();
            String returnLineText = editor.getFileLine(fileName, returnLine);
            returnValueEdit.setRange(new Range(new Position(returnLine, 0), new Position(returnLine, returnLineText.length())));
            returnValueEdit.setNewText(returnLineText.replace("return ", oldReturnType + " returnValue = "));
            edits.add(returnValueEdit);

            editor.insertBlankLine(fileName, line, false);
            FileEvent blankLineEvent = new FileEvent();
            blankLineEvent.setUri(fileUri);
            blankLineEvent.setType(FileChangeType.CHANGED);
            languageClient.notifyWatchedFilesChanged(Collections.singletonList(blankLineEvent));

            TextEdit returnEdit = new TextEdit();
            returnEdit.setRange(new Range(new Position(line, 0), new Position(line, 0)));

            if (replaceReturnStatement) {
                returnEdit.setNewText(String.format(returnValueFormatter, "returnValue"));
            } else {
                returnEdit.setNewText(methodContentIndent + "return " + String.format(returnValueFormatter, "returnValue") + ";");
            }

            edits.add(returnEdit);
        }

        WorkspaceEdit workspaceEdit = new WorkspaceEdit();
        workspaceEdit.setChanges(Collections.singletonMap(fileUri, edits));
        Utils.applyWorkspaceEdit(workspaceEdit, editor, languageClient);

        Utils.organizeImportsOnRange(languageClient, editor, fileUri, new Range(start, end));

        String newMethodSignature = methodSignature.replace(oldReturnType + " " + methodName, newReturnType + " " + methodName);

        return refreshCustomization(newMethodSignature);
    }

    private MethodCustomization refreshCustomization(String methodSignature) {
        return new PackageCustomization(editor, languageClient, packageName)
            .getClass(className)
            .getMethod(methodSignature);
    }
}
