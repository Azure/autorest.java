// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.Map;

public class Discriminator {
    private Property property;
    private Map<String, ComplexSchema> immediate;
    private Map<String, ComplexSchema> all;

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Map<String, ComplexSchema> getImmediate() {
        return immediate;
    }

    public void setImmediate(Map<String, ComplexSchema> immediate) {
        this.immediate = immediate;
    }

    public Map<String, ComplexSchema> getAll() {
        return all;
    }

    public void setAll(Map<String, ComplexSchema> all) {
        this.all = all;
    }
}
