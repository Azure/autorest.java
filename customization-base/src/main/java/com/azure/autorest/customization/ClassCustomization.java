package com.azure.autorest.customization;

import com.azure.autorest.customization.implementation.CodeCustomization;
import com.azure.autorest.customization.implementation.Utils;
import com.azure.autorest.customization.implementation.ls.EclipseLanguageClient;
import com.azure.autorest.customization.implementation.ls.models.FileChangeType;
import com.azure.autorest.customization.implementation.ls.models.FileEvent;
import com.azure.autorest.customization.implementation.ls.models.SymbolInformation;
import com.azure.autorest.customization.implementation.ls.models.SymbolKind;
import com.azure.autorest.customization.implementation.ls.models.TextEdit;
import com.azure.autorest.customization.implementation.ls.models.WorkspaceEdit;
import com.azure.autorest.customization.models.Position;
import com.azure.autorest.customization.models.Range;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.lang.reflect.Modifier;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * The class level customization for an AutoRest generated class.
 */
public final class ClassCustomization extends CodeCustomization {
    /*
     * This pattern attempts to find the first line of a method string that doesn't have a first non-space character of
     * '*' or '/'. From there it captures all word and space characters before and inside '( )' ignoring any trailing
     * spaces and an opening '{'.
     */
    private static final Pattern METHOD_SIGNATURE_PATTERN =
        Pattern.compile("^\\s*([^/*][\\w\\s]+\\([\\w\\s<>,\\.]*\\))\\s*\\{?$", Pattern.MULTILINE);

    /*
     * This pattern attempts to find the first line of a constructor string that doesn't have a first non-space
     * character of '*' or '/', effectively the first non-Javadoc line. From there it captures all word and space
     * characters before and inside '( )' ignoring any trailing spaces and an opening '{'.
     */
    private static final Pattern CONSTRUCTOR_SIGNATURE_PATTERN =
        Pattern.compile("^\\s*([^/*][\\w\\s]+\\([\\w\\s<>,\\.]*\\))\\s*\\{?$", Pattern.MULTILINE);

    private static final Pattern BLOCK_OPEN = Pattern.compile("\\) *\\{");
    private static final Pattern PUBLIC_MODIFIER = Pattern.compile(" *public ");
    private static final Pattern PRIVATE_MODIFIER = Pattern.compile(" *private ");
    private static final Pattern MEMBER_PARAMS = Pattern.compile("\\(.*\\)");

    private final String packageName;
    private final String className;

    ClassCustomization(Editor editor, EclipseLanguageClient languageClient, String packageName, String className,
        SymbolInformation classSymbol) {
        super(editor, languageClient, classSymbol);

        this.packageName = packageName;
        this.className = className;
    }

    /**
     * Gets the name of the class this customization is using.
     *
     * @return The name of the class.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Gets the method level customization for a method in the class.
     *
     * @param methodNameOrSignature the method name or signature
     * @return the method level customization
     */
    public MethodCustomization getMethod(String methodNameOrSignature) {
        String methodName;
        String methodSignature = null;
        if (methodNameOrSignature.contains("(")) {
            // method signature
            methodSignature = BLOCK_OPEN.matcher(methodNameOrSignature).replaceFirst("");
            methodSignature = PUBLIC_MODIFIER.matcher(methodSignature).replaceFirst("");
            methodSignature = PRIVATE_MODIFIER.matcher(methodSignature).replaceFirst("");

            String returnTypeAndMethodName = methodNameOrSignature.split("\\(")[0];
            if (returnTypeAndMethodName.contains(" ")) {
                methodName = Utils.ANYTHING_THEN_SPACE_PATTERN.matcher(returnTypeAndMethodName).replaceAll("");
            } else {
                methodName = returnTypeAndMethodName;
            }
        } else {
            methodName = methodNameOrSignature;
        }
        Optional<SymbolInformation> methodSymbol = languageClient.listDocumentSymbols(fileUri)
            .stream().filter(si -> MEMBER_PARAMS.matcher(si.getName()).replaceFirst("").equals(methodName)
                && si.getKind() == SymbolKind.METHOD)
            .filter(si -> editor.getFileLine(fileName, si.getLocation().getRange().getStart().getLine()).contains(methodNameOrSignature))
            .findFirst();
        if (!methodSymbol.isPresent()) {
            throw new IllegalArgumentException("Method " + methodNameOrSignature + " does not exist in class " + className);
        }
        if (methodSignature == null) {
            methodSignature = editor.getFileLine(fileName, methodSymbol.get().getLocation().getRange().getStart().getLine());
            methodSignature = BLOCK_OPEN.matcher(methodSignature).replaceFirst("");
            methodSignature = PUBLIC_MODIFIER.matcher(methodSignature).replaceFirst("");
            methodSignature = PRIVATE_MODIFIER.matcher(methodSignature).replaceFirst("");
        }
        return new MethodCustomization(editor, languageClient, packageName, className, methodName, methodSignature, methodSymbol.get());
    }

    /**
     * Gets the constructor level customization for a constructor in the class.
     * <p>
     * If only the constructor name is passed and the class has multiple constructors an error will be thrown to prevent
     * ambiguous runtime behavior.
     *
     * @param constructorNameOrSignature The constructor name or signature.
     * @return The constructor level customization.
     * @throws IllegalStateException If only the constructor name is passed and the class has multiple constructors.
     */
    public ConstructorCustomization getConstructor(String constructorNameOrSignature) {
        String constructorName;
        String constructorSignature = null;
        if (constructorNameOrSignature.contains("(")) {
            // method signature
            constructorSignature = BLOCK_OPEN.matcher(constructorNameOrSignature).replaceFirst("");
            constructorSignature = PUBLIC_MODIFIER.matcher(constructorSignature).replaceFirst("");
            constructorSignature = PRIVATE_MODIFIER.matcher(constructorSignature).replaceFirst("");
            String returnTypeAndMethodName = constructorNameOrSignature.split("\\(")[0];
            if (returnTypeAndMethodName.contains(" ")) {
                constructorName = Utils.ANYTHING_THEN_SPACE_PATTERN.matcher(returnTypeAndMethodName).replaceAll("");
            } else {
                constructorName = returnTypeAndMethodName;
            }
        } else {
            constructorName = constructorNameOrSignature;
        }

        List<SymbolInformation> constructorSymbol = languageClient.listDocumentSymbols(fileUri)
            .stream().filter(si -> MEMBER_PARAMS.matcher(si.getName()).replaceFirst("").equals(constructorName)
                && si.getKind() == SymbolKind.CONSTRUCTOR)
            .filter(si -> editor.getFileLine(fileName, si.getLocation().getRange().getStart().getLine())
                .contains(constructorNameOrSignature))
            .collect(Collectors.toList());

        if (constructorSymbol.size() > 1) {
            throw new IllegalStateException("Multiple instances of " + constructorNameOrSignature + " exist in the "
                + "class. Use a more specific constructor signature.");
        }

        if (constructorSymbol.size() == 0) {
            throw new IllegalArgumentException("Constructor " + constructorNameOrSignature + " does not exist in class "
                + className);
        }

        if (constructorSignature == null) {
            constructorSignature = editor.getFileLine(fileName, constructorSymbol.get(0).getLocation().getRange().getStart().getLine());
            constructorSignature = BLOCK_OPEN.matcher(constructorSignature).replaceFirst("");
            constructorSignature = PUBLIC_MODIFIER.matcher(constructorSignature).replaceFirst("");
            constructorSignature = PRIVATE_MODIFIER.matcher(constructorSignature).replaceFirst("");
        }
        return new ConstructorCustomization(editor, languageClient, packageName, className, constructorSignature,
            constructorSymbol.get(0));
    }

    /**
     * Gets the property level customization for a property in the class.
     *
     * @param propertyName the property name
     * @return the property level customization
     */
    public PropertyCustomization getProperty(String propertyName) {
        Optional<SymbolInformation> propertySymbol = languageClient.listDocumentSymbols(fileUri)
            .stream().filter(si -> si.getName().equals(propertyName) && si.getKind() == SymbolKind.FIELD)
            .findFirst();

        if (!propertySymbol.isPresent()) {
            throw new IllegalArgumentException("Property " + propertyName + " does not exist in class " + className);
        }

        return new PropertyCustomization(editor, languageClient, packageName, className, propertySymbol.get(),
            propertyName);
    }

    /**
     * Gets the Javadoc customization for this class.
     *
     * @return the Javadoc customization
     */
    public JavadocCustomization getJavadoc() {
        return new JavadocCustomization(editor, languageClient, fileUri, fileName,
            symbol.getLocation().getRange().getStart().getLine());
    }

    /**
     * Adds a constructor to this class.
     *
     * @param constructor The entire constructor as a literal string.
     * @return The constructor level customization for the added constructor.
     */
    public ConstructorCustomization addConstructor(String constructor) {
        return addConstructor(constructor, null);
    }

    /**
     * Adds a constructor to this class.
     *
     * @param constructor The entire constructor as a literal string.
     * @param importsToAdd Any additional imports required by the constructor. These will be custom types or types that
     * are ambiguous on which to use such as {@code List} or the utility class {@code Arrays}.
     * @return The constructor level customization for the added constructor.
     */
    public ConstructorCustomization addConstructor(String constructor, List<String> importsToAdd) {
        // Get the signature of the constructor.
        Matcher constructorSignatureMatcher = CONSTRUCTOR_SIGNATURE_PATTERN.matcher(constructor);
        String constructorSignature = null;
        if (constructorSignatureMatcher.find()) {
            constructorSignature = constructorSignatureMatcher.group(1);
        }

        // Find all constructor and field symbols.
        List<SymbolInformation> constructorLocationFinder = languageClient.listDocumentSymbols(fileUri).stream()
            .filter(symbol -> symbol.getKind() == SymbolKind.FIELD || symbol.getKind() == SymbolKind.CONSTRUCTOR)
            .collect(Collectors.toList());

        // If no constructors or fields exist in the class place the constructor after the class declaration line.
        // Otherwise place the constructor after the last constructor or field.
        int constructorStartLine;
        if (Utils.isNullOrEmpty(constructorLocationFinder)) {
            constructorStartLine = symbol.getLocation().getRange().getStart().getLine();
        } else {
            SymbolInformation symbol = constructorLocationFinder.get(constructorLocationFinder.size() - 1);

            // If the last symbol before the new constructor is a field only a new line needs to be inserted.
            // Otherwise if the last symbol is a constructor its closing '}' needs to be found and then a new line
            // needs to be inserted.
            if (symbol.getKind() == SymbolKind.FIELD) {
                constructorStartLine = symbol.getLocation().getRange().getStart().getLine();
            } else {
                constructorStartLine = symbol.getLocation().getRange().getStart().getLine();

                List<String> fileLines = editor.getFileLines(fileName);
                String currentLine = fileLines.get(constructorStartLine);
                String constructorIdent = Utils.getIndent(currentLine);
                while (!currentLine.endsWith("}") || !currentLine.equals(constructorIdent + "}")) {
                    currentLine = fileLines.get(++constructorStartLine);
                }
            }
        }

        int indentAmount = Utils.getIndent(editor.getFileLine(fileName, constructorStartLine)).length();

        editor.insertBlankLine(fileName, ++constructorStartLine, false);
        Position constructorPosition = editor.insertBlankLineWithIndent(fileName, ++constructorStartLine, indentAmount);

        editor.replaceWithIndentedContent(fileName, constructorPosition, constructorPosition, constructor,
            constructorPosition.getCharacter());

        final String ctorSignature = (constructorSignature == null)
            ? editor.getFileLine(fileName, constructorStartLine)
            : constructorSignature;

        return Utils.addImports(importsToAdd, this, () -> getConstructor(ctorSignature));
    }

    /**
     * Adds a method to this class.
     *
     * @param method The entire method as a literal string.
     * @return The method level customization for the added method.
     */
    public MethodCustomization addMethod(String method) {
        return addMethod(method, null);
    }

    /**
     * Adds a method to this class.
     *
     * @param method The entire method as a literal string.
     * @param importsToAdd Any additional imports required by the constructor. These will be custom types or types that
     * are ambiguous on which to use such as {@code List} or the utility class {@code Arrays}.
     * @return The method level customization for the added method.
     */
    public MethodCustomization addMethod(String method, List<String> importsToAdd) {
        // Get the signature of the method.
        Matcher methodSignatureMatcher = METHOD_SIGNATURE_PATTERN.matcher(method);
        String methodSignature = null;
        if (methodSignatureMatcher.find()) {
            methodSignature = methodSignatureMatcher.group(1);
        }

        // find position
        List<String> fileLines = editor.getFileLines(fileName);
        int lineNum = fileLines.size();
        String currentLine = fileLines.get(--lineNum);
        while (!currentLine.endsWith("}") || currentLine.startsWith("}")) {
            currentLine = fileLines.get(--lineNum);
        }

        int indentAmount = Utils.getIndent(currentLine).length();

        editor.insertBlankLine(fileName, ++lineNum, false);
        Position newMethod = editor.insertBlankLineWithIndent(fileName, ++lineNum, indentAmount);

        // replace
        editor.replaceWithIndentedContent(fileName, newMethod, newMethod, method, newMethod.getCharacter());

        final String mSig = (methodSignature == null) ? editor.getFileLine(fileName, lineNum) : methodSignature;

        return Utils.addImports(importsToAdd, this, () -> getMethod(mSig));
    }

    /**
     * Removes a method from this class.
     * <p>
     * If there exists multiple methods with the same name or signature only the first one found will be removed.
     * <p>
     * This method doesn't update usages of the method being removed. If the method was used elsewhere those usages will
     * have to be updated or removed in another customization, or customizations.
     * <p>
     * If this removes the only method contained in the class this will result in a class with no methods.
     *
     * @param methodNameOrSignature The name or signature of the method being removed.
     * @return The current ClassCustomization.
     */
    public ClassCustomization removeMethod(String methodNameOrSignature) {
        // Begin by getting the method.
        SymbolInformation methodSymbol = getMethod(methodNameOrSignature).getSymbol();

        int methodSignatureLine = methodSymbol.getLocation().getRange().getStart().getLine();

        // Find the beginning location of the method being removed.
        // If the method has a multi-line Javadoc walk until the start line is found.
        // Else if the method has a single line Javadoc use the beginning of that line.
        // Else using the beginning of the method signature.
        Position start;
        String lineAboveMethodSignature = editor.getFileLine(fileName, methodSignatureLine - 1);
        if (Utils.JAVADOC_END_PATTERN.matcher(lineAboveMethodSignature).matches()) {
            int startLine = Utils.walkUpFileUntilLineMatches(editor, fileName, methodSignatureLine - 2,
                lineContent -> Utils.JAVADOC_START_PATTERN.matcher(lineContent).matches());
            start = new Position(startLine, 0);
        } else if (Utils.SINGLE_LINE_JAVADOC_PATTERN.matcher(lineAboveMethodSignature).matches()) {
            start = new Position(methodSignatureLine - 1, 0);
        } else {
            start = new Position(methodSignatureLine, 0);
        }

        // Find the ending location of the method being removed.
        String bodyPositionFinder = editor.getFileLine(fileName, methodSignatureLine);
        String methodBlockIndent = Utils.getIndent(bodyPositionFinder);

        // Go until the beginning of the next method is found.
        int endLine = Utils.walkDownFileUntilLineMatches(editor, fileName, methodSignatureLine,
            lineContent -> lineContent.matches(methodBlockIndent + "."));
        Position end = new Position(endLine, editor.getFileLine(fileName, endLine).length());

        // Remove the method.
        editor.replace(fileName, start, end, "");
        FileEvent fileEvent = new FileEvent();
        fileEvent.setUri(fileUri);
        fileEvent.setType(FileChangeType.CHANGED);
        languageClient.notifyWatchedFilesChanged(Collections.singletonList(fileEvent));

        return this;
    }

    /**
     * Renames a class in the package.
     *
     * @param newName the new simple name for this class
     * @return The current ClassCustomization.
     */
    public ClassCustomization rename(String newName) {
        WorkspaceEdit workspaceEdit = languageClient.renameSymbol(fileUri,
            symbol.getLocation().getRange().getStart(), newName);
        List<FileEvent> changes = new ArrayList<>();
        for (Map.Entry<URI, List<TextEdit>> edit : workspaceEdit.getChanges().entrySet()) {
            int i = edit.getKey().toString().indexOf("src/main/java/");
            String oldEntry = edit.getKey().toString().substring(i);
            if (editor.getContents().containsKey(oldEntry)) {
                for (TextEdit textEdit : edit.getValue()) {
                    editor.replace(oldEntry, textEdit.getRange().getStart(), textEdit.getRange().getEnd(), textEdit.getNewText());
                }
                FileEvent fileEvent = new FileEvent();
                fileEvent.setUri(edit.getKey());
                if (oldEntry.endsWith(className + ".java")) {
                    String newEntry = oldEntry.replace(className + ".java", newName + ".java");
                    editor.renameFile(oldEntry, newEntry);
                    URI newUri = URI.create(edit.getKey().toString().replace(className + ".java", newName + ".java"));
                    fileEvent.setType(FileChangeType.DELETED);
                    changes.add(fileEvent);
                    FileEvent newFile = new FileEvent();
                    newFile.setUri(newUri);
                    newFile.setType(FileChangeType.CREATED);
                    changes.add(newFile);
                } else {
                    fileEvent.setType(FileChangeType.CHANGED);
                    changes.add(fileEvent);
                }
            }
        }
        languageClient.notifyWatchedFilesChanged(changes);

        String packagePath = packageName.replace(".", "/");
        Optional<SymbolInformation> newClassSymbol = languageClient.findWorkspaceSymbol(newName)
            .stream().filter(si -> si.getLocation().getUri().toString().endsWith(packagePath + "/" + newName + ".java"))
            .findFirst();
        if (!newClassSymbol.isPresent()) {
            throw new IllegalArgumentException("Renamed failed with new class " + newName + " not found.");
        }
        return new ClassCustomization(editor, languageClient, packageName, newName, newClassSymbol.get());
    }

    /**
     * Replace the modifier for this class.
     * <p>
     * For compound modifiers such as {@code public abstract} use bitwise OR ({@code |}) of multiple Modifiers, {@code
     * Modifier.PUBLIC | Modifier.ABSTRACT}.
     * <p>
     * Pass {@code 0} for {@code modifiers} to indicate that the method has no modifiers.
     *
     * @param modifiers The {@link Modifier Modifiers} for the class.
     * @return The updated ClassCustomization object.
     * @throws IllegalArgumentException If the {@code modifier} is less than {@code 0} or any {@link Modifier} included
     * in the bitwise OR isn't a valid class {@link Modifier}.
     */
    public ClassCustomization setModifier(int modifiers) {
        languageClient.listDocumentSymbols(symbol.getLocation().getUri())
            .stream().filter(si -> si.getName().equals(className) && si.getKind() == SymbolKind.CLASS)
            .findFirst()
            .ifPresent(symbolInformation -> Utils.replaceModifier(symbolInformation, editor, languageClient,
                "(?:.+ )?class " + className, "class " + className, Modifier.classModifiers(), modifiers));

        return refreshSymbol();
    }

    /**
     * Add an annotation on the class. The annotation class will be automatically imported.
     *
     * @param annotation the annotation to add to the class. The leading @ can be omitted.
     * @return the current class customization for chaining
     */
    public ClassCustomization addAnnotation(String annotation) {
        if (!annotation.startsWith("@")) {
            annotation = "@" + annotation;
        }

        Optional<SymbolInformation> symbol = languageClient.listDocumentSymbols(fileUri)
            .stream().filter(si -> si.getKind() == SymbolKind.CLASS)
            .findFirst();
        if (symbol.isPresent()) {
            if (editor.getContents().containsKey(fileName)) {
                int line = symbol.get().getLocation().getRange().getStart().getLine();
                Position position = editor.insertBlankLine(fileName, line, true);
                editor.replace(fileName, position, position, annotation);

                FileEvent fileEvent = new FileEvent();
                fileEvent.setUri(fileUri);
                fileEvent.setType(FileChangeType.CHANGED);
                languageClient.notifyWatchedFilesChanged(Collections.singletonList(fileEvent));

                Utils.organizeImportsOnRange(languageClient, editor, fileUri, symbol.get().getLocation().getRange());
            }
        }

        return refreshSymbol();
    }

    /**
     * Remove an annotation from the class.
     *
     * @param annotation the annotation to remove from the class. The leading @ can be omitted.
     * @return the current class customization for chaining
     */
    public ClassCustomization removeAnnotation(String annotation) {
        if (!annotation.startsWith("@")) {
            annotation = "@" + annotation;
        }

        if (editor.getContents().containsKey(fileName)) {
            Range range = editor.searchTextFirstOccurrence(fileName, annotation);
            if (range != null) {
                Position start = new Position(range.getStart().getLine(), 0);
                Position end = new Position(range.getStart().getLine() + 1, 0);
                editor.replace(fileName, start, end, "");

                FileEvent fileEvent = new FileEvent();
                fileEvent.setUri(fileUri);
                fileEvent.setType(FileChangeType.CHANGED);
                languageClient.notifyWatchedFilesChanged(Collections.singletonList(fileEvent));

                Utils.organizeImportsOnRange(languageClient, editor, fileUri, symbol.getLocation().getRange());
            }
        }

        return refreshSymbol();
    }

    /**
     * Rename an enum member if the current class is an enum class.
     *
     * @param enumMemberName the current enum member name
     * @param newName the new enum member name
     * @return the current class customization for chaining
     */
    public ClassCustomization renameEnumMember(String enumMemberName, String newName) {
        URI fileUri = symbol.getLocation().getUri();
        languageClient.listDocumentSymbols(fileUri).stream()
            .filter(si -> si.getName().toLowerCase().contains(enumMemberName.toLowerCase()))
            .forEach(symbol -> {
                WorkspaceEdit edit = languageClient.renameSymbol(fileUri, symbol.getLocation().getRange().getStart(),
                    newName);
                Utils.applyWorkspaceEdit(edit, editor, languageClient);
            });
        return this;
    }

    /**
     * Allows for a fully controlled modification of the abstract syntax tree that represents this class.
     *
     * @param astCustomization The abstract syntax tree customization callback.
     * @return A new ClassCustomization for this class with the abstract syntax tree changes applied.
     */
    public ClassCustomization customizeAst(Function<CompilationUnit, CompilationUnit> astCustomization) {
        CompilationUnit astToEdit = StaticJavaParser.parse(editor.getFileContent(fileName));
        editor.replaceFile(fileName, astCustomization.apply(astToEdit).toString());

        FileEvent fileEvent = new FileEvent();
        fileEvent.setUri(fileUri);
        fileEvent.setType(FileChangeType.CHANGED);
        languageClient.notifyWatchedFilesChanged(Collections.singletonList(fileEvent));

        return refreshSymbol();
    }

    private ClassCustomization refreshSymbol() {
        return new PackageCustomization(editor, languageClient, packageName).getClass(className);
    }
}
