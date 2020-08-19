/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.FluentDefinitionStage;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceCreate;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.javamodel.JavaInterface;
import com.azure.autorest.template.ClientMethodTemplate;
import com.azure.autorest.template.IJavaTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class FluentResourceModelInterfaceDefinitionTemplate implements IJavaTemplate<ResourceCreate, JavaInterface> {

    @Override
    public void write(ResourceCreate resourceCreate, JavaInterface interfaceBlock) {
        if (resourceCreate.isBodyParameterSameAsFluentModel()) {
            List<FluentDefinitionStage> fluentDefinitionStages = resourceCreate.getDefinitionStages();

            final String modelName = resourceCreate.getResourceModel().getInterfaceType().getName();

            // Definition interface
            interfaceBlock.javadocComment(commentBlock -> {
                commentBlock.description(String.format("The entirety of the %1$s definition.", modelName));
            });
            String definitionInterfaceSignature = String.format("%1$s extends %2$s",
                    ModelNaming.MODEL_FLUENT_INTERFACE_DEFINITION,
                    fluentDefinitionStages.stream()
                            .filter(s -> s.getProperty() == null || s.getProperty().isRequired())
                            .map(s -> String.format("%1$s.%2$s", ModelNaming.MODEL_FLUENT_INTERFACE_DEFINITION_STAGES, s.getName()))
                            .collect(Collectors.joining(", ")));
            interfaceBlock.interfaceBlock(definitionInterfaceSignature, block1 -> {
            });

            // DefinitionStages interface
            interfaceBlock.javadocComment(commentBlock -> {
                commentBlock.description(String.format("The %1$s definition stages.", modelName));
            });
            interfaceBlock.interfaceBlock(ModelNaming.MODEL_FLUENT_INTERFACE_DEFINITION_STAGES, block1 -> {
                for (FluentDefinitionStage stage : fluentDefinitionStages) {
                    block1.javadocComment(commentBlock -> {
                        commentBlock.description(stage.getDescription(modelName));
                    });
                    String interfaceSignature = stage.getName();
                    if (stage.getExtendStages() != null) {
                        interfaceSignature += " extends " + stage.getExtendStages();
                    }
                    block1.interfaceBlock(interfaceSignature, block2 -> {
                        if (stage.getProperty() != null) {
                            block2.javadocComment(commentBlock -> {
                                String propertyName = stage.getProperty().getName();
                                commentBlock.description(String.format("Specifies the %1$s property: %2$s.", propertyName, stage.getProperty().getDescription()));
                                commentBlock.param(stage.getProperty().getName(), String.format("the %1$s value to set.", propertyName));
                                commentBlock.methodReturns("the next definition stage.");
                            });
                            block2.publicMethod(stage.getMethodSignature());
                        }

                        List<ClientMethod> methods = stage.getMethods();
                        if (!methods.isEmpty()) {
                            methods.forEach(m -> {
                                ClientMethodTemplate.generateJavadoc(m, block2, null, false);
                                block2.publicMethod(m.getDeclaration());
                            });
                        }
                    });
                }
            });
        } else {
            // TODO
        }
    }
}
