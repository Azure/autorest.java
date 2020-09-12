package com.azure.autorest.postprocessor.customization;

import org.openrewrite.Change;
import org.openrewrite.SourceFile;
import org.openrewrite.java.ChangeType;
import org.openrewrite.java.JavaParser;
import org.openrewrite.java.tree.J;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Refactor {
    private List<J.CompilationUnit> compilationUnits;

    Refactor(List<J.CompilationUnit> compilationUnits) {
        this.compilationUnits = compilationUnits;
    }

    public void renameType(String originalName, String newName) {
        ChangeType changeType = new ChangeType();
        changeType.setType(originalName);
        changeType.setTargetType(newName);
        for (Change change : new org.openrewrite.Refactor().visit(changeType).fix(compilationUnits, 10)) {
            compilationUnits.remove(change.getOriginal());
            compilationUnits.add((J.CompilationUnit) change.getFixed());
        }
    }
}
