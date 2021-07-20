/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.method;

import com.azure.autorest.fluent.model.FluentType;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.fluent.model.clientmodel.ModelProperty;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.FluentInterfaceStage;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.LocalVariable;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.template.prototype.MethodTemplate;
import com.azure.autorest.util.CodeNamer;

import java.util.Set;

public class FluentModelPropertyRegion {

    private FluentModelPropertyRegion() {
    }

    public static class FluentModelPropertyRegionMethod extends FluentModelPropertyMethod {

        public FluentModelPropertyRegionMethod(FluentResourceModel model, FluentMethodType type,
                                               FluentInterfaceStage stage, ClientModel clientModel,
                                               ModelProperty modelProperty,
                                               LocalVariable localVariable, String baseName) {
            super(model, type, stage, clientModel, modelProperty, localVariable,
                    CodeNamer.getModelNamer().modelPropertySetterName(baseName),
                    "Specifies the region for the resource.");

            this.implementationMethodTemplate = MethodTemplate.builder()
                    .methodSignature(this.getImplementationMethodSignature())
                    .method(block -> {
                        if (fluentResourceModel.getInnerModel() == clientModel) {
                            block.line("this.%1$s().%2$s(%3$s.toString());", ModelNaming.METHOD_INNER_MODEL, modelProperty.getSetterName(), modelProperty.getName());
                        } else {
                            block.line("this.%1$s.%2$s(%3$s.toString());", localVariable.getName(), modelProperty.getSetterName(), modelProperty.getName());
                        }
                        block.methodReturn("this");
                    })
                    .build();
        }

        @Override
        protected String getBaseMethodSignature() {
            return String.format("%1$s(%2$s %3$s)",
                    this.name,
                    FluentType.Region,
                    modelProperty.getName());
        }

        @Override
        public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
            super.addImportsTo(imports, includeImplementationImports);
            FluentType.Region.addImportsTo(imports, false);
        }
    }

    public static class FluentModelPropertyRegionNameMethod extends FluentModelPropertyMethod {

        public FluentModelPropertyRegionNameMethod(FluentResourceModel model, FluentMethodType type,
                                                   FluentInterfaceStage stage, ClientModel clientModel,
                                                   ModelProperty modelProperty,
                                                   LocalVariable localVariable, String baseName) {
            super(model, type, stage, clientModel, modelProperty, localVariable,
                    CodeNamer.getModelNamer().modelPropertySetterName(baseName),
                    "Specifies the region for the resource.");
        }
    }
}
