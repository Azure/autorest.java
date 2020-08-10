package com.azure.autorest.android.mapper;

import com.azure.autorest.model.clientmodel.IType;

import java.util.Set;

class AdHocType implements IType {
    private String packageName;
    private String typeName;
    AdHocType(String packageName, String typeName) {
        this.packageName = packageName;
        this.typeName = typeName;
    }

    @Override
    public IType getClientType() {
        return this;
    }

    @Override
    public String convertToClientType(String expression) {
        return typeName;
    }

    @Override
    public String convertFromClientType(String expression) {
        return expression;
    }

    @Override
    public IType asNullable() {
        return this;
    }

    @Override
    public boolean contains(IType type) {
        return false;
    }

    @Override
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        imports.add(String.format("%1$s.%2$s", packageName, typeName));
    }

    @Override
    public String defaultValueExpression(String sourceExpression) {
        return null;
    }

    @Override
    public String validate(String expression) {
        return expression;
    }

    public String toString() {
        return typeName;
    }
}
