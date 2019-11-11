package com.azure.autorest.extension.base.model.codemodel;

import java.util.List;

public class Relations {
    private List<ComplexSchema> all;

    private List<ComplexSchema> immediate;

    public List<ComplexSchema> getAll() {
        return all;
    }

    public void setAll(List<ComplexSchema> all) {
        this.all = all;
    }

    public List<ComplexSchema> getImmediate() {
        return immediate;
    }

    public void setImmediate(List<ComplexSchema> immediate) {
        this.immediate = immediate;
    }
}
