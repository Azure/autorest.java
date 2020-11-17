package com.azure.autorest.extension.base.model.codemodel;

import java.util.List;

public class Relations {
    private List<Schema> all;

    private List<Schema> immediate;

    public List<Schema> getAll() {
        return all;
    }

    public void setAll(List<Schema> all) {
        this.all = all;
    }

    public List<Schema> getImmediate() {
        return immediate;
    }

    public void setImmediate(List<Schema> immediate) {
        this.immediate = immediate;
    }
}
