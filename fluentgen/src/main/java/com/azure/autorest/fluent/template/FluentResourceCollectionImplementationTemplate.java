/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.template.IJavaTemplate;
import com.azure.autorest.template.prototype.MethodTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FluentResourceCollectionImplementationTemplate implements IJavaTemplate<FluentResourceCollection, JavaFile> {

    private static final FluentResourceCollectionImplementationTemplate INSTANCE = new FluentResourceCollectionImplementationTemplate();

    public static FluentResourceCollectionImplementationTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    public void write(FluentResourceCollection collection, JavaFile javaFile) {
        List<MethodTemplate> methodTemplates = new ArrayList<>();
        collection.getMethods().forEach(p -> methodTemplates.add(p.getImplementationMethodTemplate()));

        Set<String> imports = new HashSet<>();
        collection.addImportsTo(imports, true);
        javaFile.declareImport(imports);

        javaFile.publicFinalClass(String.format("%1$s implements %2$s", collection.getCollectionImplementationClassType().getName(), collection.getCollectionInterfaceClassType().getName()), classBlock -> {
            // variable for inner model
            classBlock.privateFinalMemberVariable(collection.getInnerClassType().getName(), ModelNaming.COLLECTION_PROPERTY_INNER);

            // constructor
            classBlock.publicConstructor(String.format("%1$s(%2$s %3$s)", collection.getCollectionImplementationClassType().getName(), collection.getInnerClassType().getName(), ModelNaming.COLLECTION_PROPERTY_INNER), methodBlock -> {
                methodBlock.line(String.format("this.%1$s = %2$s;", ModelNaming.COLLECTION_PROPERTY_INNER, ModelNaming.COLLECTION_PROPERTY_INNER));
            });

            // method for properties
            methodTemplates.forEach(m -> m.writeMethod(classBlock));

            // method for inner model
            classBlock.publicMethod(collection.getInnerMethodSignature(), methodBlock -> {
                methodBlock.methodReturn(String.format("this.%s", ModelNaming.COLLECTION_PROPERTY_INNER));
            });
        });
    }
}
