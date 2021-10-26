package com.azure.autorest.customization;

import com.azure.autorest.customization.implementation.CodeCustomization;
import com.azure.autorest.customization.implementation.Utils;
import com.azure.autorest.customization.implementation.ls.EclipseLanguageClient;
import com.azure.autorest.customization.implementation.ls.models.CodeAction;
import com.azure.autorest.customization.implementation.ls.models.JavaCodeActionKind;
import com.azure.autorest.customization.implementation.ls.models.SymbolInformation;
import com.azure.autorest.customization.implementation.ls.models.SymbolKind;
import com.azure.autorest.customization.implementation.ls.models.TextEdit;
import com.azure.autorest.customization.implementation.ls.models.WorkspaceEdit;
import com.azure.autorest.customization.implementation.ls.models.WorkspaceEditCommand;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * The Javadoc customization for an AutoRest generated classes and methods.
 */
public final class PropertyCustomization extends CodeCustomization {
    private static final Pattern METHOD_PARAMS_CAPTURE = Pattern.compile("\\(.*\\)");

    private final String packageName;
    private final String className;
    private final String propertyName;

    PropertyCustomization(Editor editor, EclipseLanguageClient languageClient, String packageName, String className,
        SymbolInformation symbol, String propertyName) {
        super(editor, languageClient, symbol);
        this.packageName = packageName;
        this.className = className;
        this.propertyName = propertyName;
    }

    /**
     * Rename a property in the class. This is a refactor operation. All references of the property will be renamed and
     * the getter and setter method(s) for this property will be renamed accordingly as well.
     *
     * @param newName the new name for the property
     * @return the current class customization for chaining
     */
    public PropertyCustomization rename(String newName) {
        List<SymbolInformation> symbols = languageClient.listDocumentSymbols(fileUri)
            .stream().filter(si -> si.getName().toLowerCase().contains(propertyName.toLowerCase()))
            .collect(Collectors.toList());
        String propertyPascalName = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
        String newPascalName = newName.substring(0, 1).toUpperCase() + newName.substring(1);
        for (SymbolInformation symbol : symbols) {
            if (symbol.getKind() == SymbolKind.FIELD) {
                WorkspaceEdit edit = languageClient.renameSymbol(fileUri, symbol.getLocation().getRange().getStart(), newName);
                Utils.applyWorkspaceEdit(edit, editor, languageClient);
            } else if (symbol.getKind() == SymbolKind.METHOD) {
                String methodName = symbol.getName().replace(propertyPascalName, newPascalName)
                    .replace(propertyName, newName);
                methodName = METHOD_PARAMS_CAPTURE.matcher(methodName).replaceFirst("");
                WorkspaceEdit edit = languageClient.renameSymbol(fileUri, symbol.getLocation().getRange().getStart(), methodName);
                Utils.applyWorkspaceEdit(edit, editor, languageClient);
            }
        }
        return refreshCustomization(newName);
    }

    /**
     * Add an annotation to a property in the class.
     *
     * @param annotation the annotation to add. The leading @ can be omitted.
     * @return the current property customization for chaining
     */
    public PropertyCustomization addAnnotation(String annotation) {
        return Utils.addAnnotation(annotation, this, () -> refreshCustomization(propertyName));
    }

    /**
     * Remove an annotation from the property.
     *
     * @param annotation the annotation to remove from the property. The leading @ can be omitted.
     * @return the current property customization for chaining
     */
    public PropertyCustomization removeAnnotation(String annotation) {
        return Utils.removeAnnotation(annotation, this, () -> refreshCustomization(propertyName));
    }

    /**
     * Generates a getter and a setter method(s) for a property in the class. This is a refactor operation. If a getter
     * or a setter is already available on the class, the current getter or setter will be kept.
     *
     * @return the current class customization for chaining
     */
    public PropertyCustomization generateGetterAndSetter() {
        Optional<CodeAction> generateAccessors = languageClient.listCodeActions(fileUri, symbol.getLocation().getRange())
            .stream().filter(ca -> ca.getKind().equals(JavaCodeActionKind.SOURCE_GENERATE_ACCESSORS.toString()))
            .findFirst();
        if (generateAccessors.isPresent()) {
            WorkspaceEditCommand command;
            if (generateAccessors.get().getCommand() instanceof WorkspaceEditCommand) {
                command = (WorkspaceEditCommand) generateAccessors.get().getCommand();
                for (WorkspaceEdit workspaceEdit : command.getArguments()) {
                    Utils.applyWorkspaceEdit(workspaceEdit, editor, languageClient);
                }
                List<TextEdit> formats = languageClient.format(fileUri);
                Utils.applyTextEdits(fileUri, formats, editor, languageClient);

                String setterMethod = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
                new PackageCustomization(editor, languageClient, packageName)
                    .getClass(className)
                    .getMethod(setterMethod).setReturnType(className, "this");
            }
        }

        return this;
    }

    private PropertyCustomization refreshCustomization(String propertyName) {
        return new PackageCustomization(editor, languageClient, packageName)
            .getClass(className)
            .getProperty(propertyName);
    }
}
