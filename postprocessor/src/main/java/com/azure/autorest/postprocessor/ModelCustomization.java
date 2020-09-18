package com.azure.autorest.postprocessor;

import com.azure.autorest.postprocessor.ls.JDTLanguageClient;
import com.azure.autorest.postprocessor.ls.models.SymbolInformation;
import com.azure.autorest.postprocessor.ls.models.SymbolKind;
import com.azure.autorest.postprocessor.ls.models.WorkspaceEdit;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ModelCustomization extends ClassCustomization{

    ModelCustomization(Editor editor, JDTLanguageClient languageClient, String packageName, String className) {
        super(editor, languageClient, packageName, className);
    }

    public void renameProperty(String propertyName, String newName) {
        String packagePath = packageName.replace(".", "/");
        Optional<SymbolInformation> classSymbol = languageClient.findWorkspaceSymbol(className)
                .stream().filter(si -> si.getLocation().getUri().toString().endsWith(packagePath + "/" + className + ".java"))
                .findFirst();

        if (classSymbol.isPresent()) {
            URI fileUri = classSymbol.get().getLocation().getUri();
            List<SymbolInformation> symbols = languageClient.listDocumentSymbols(fileUri)
                    .stream().filter(si -> si.getName().toLowerCase().contains(propertyName.toLowerCase()))
                    .collect(Collectors.toList());
            String propertyPascalName = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
            String newPascalName = newName.substring(0, 1).toUpperCase() + newName.substring(1);
            for (SymbolInformation symbol : symbols) {
                if (symbol.getKind() == SymbolKind.FIELD) {
                    WorkspaceEdit edit = languageClient.renameSymbol(fileUri, symbol.getLocation().getRange().getStart(), newName);
                    applyWorkspaceEdit(edit);
                } else if (symbol.getKind() == SymbolKind.METHOD) {
                    String methodName = symbol.getName().replace(propertyPascalName, newPascalName)
                            .replace(propertyName, newName).replaceFirst("\\(.*\\)", "");
                    WorkspaceEdit edit = languageClient.renameSymbol(fileUri, symbol.getLocation().getRange().getStart(), methodName);
                    applyWorkspaceEdit(edit);
                }
            }
        }
    }


    public void transformModelProperty(String packageName, String className, String propertyName, String newProperty) {

    }
}
