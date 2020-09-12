package com.azure.autorest.postprocessor.customization;

import org.openrewrite.java.ChangeType;
import org.openrewrite.java.tree.J;

import java.util.List;

public class Edit {
    private List<J.CompilationUnit> compilationUnits;

    Edit(List<J.CompilationUnit> compilationUnits) {
        this.compilationUnits = compilationUnits;
    }

    public void renameType(String originalName, String newName) {
        ChangeType changeType = new ChangeType();
        changeType.setType(originalName);
        changeType.setTargetType(newName);
        new org.openrewrite.Refactor().visit(changeType).fix(compilationUnits);
    }
}
