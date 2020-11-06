package com.azure.autorest.customization.ls.models;

import java.util.List;

public class SymbolKindCapabilities {
    public List<SymbolKind> getValueSet() {
        return valueSet;
    }

    public void setValueSet(List<SymbolKind> valueSet) {
        this.valueSet = valueSet;
    }

    List<SymbolKind> valueSet;
}
