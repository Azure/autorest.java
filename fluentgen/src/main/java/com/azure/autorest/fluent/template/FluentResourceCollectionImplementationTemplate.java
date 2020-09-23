/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.ResourceCreate;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentDefineMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethod;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClassType;
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
        ClassType managerType = FluentStatic.getFluentManager().getType();

        Set<String> imports = new HashSet<>();
        imports.add(managerType.getFullName());
        collection.addImportsTo(imports, true);
        if (collection.getResourceCreates() != null) {
            collection.getResourceCreates().forEach(rc -> rc.getDefineMethod().addImportsTo(imports, true));
        }
        javaFile.declareImport(imports);

        List<MethodTemplate> methodTemplates = new ArrayList<>();
        collection.getMethodsForTemplate().forEach(p -> methodTemplates.add(p.getImplementationMethodTemplate()));

        javaFile.publicFinalClass(String.format("%1$s implements %2$s", collection.getImplementationType().getName(), collection.getInterfaceType().getName()), classBlock -> {
            // variable for inner model
            classBlock.privateFinalMemberVariable(collection.getInnerClientType().getName(), ModelNaming.COLLECTION_PROPERTY_INNER);

            // variable for manager
            classBlock.privateFinalMemberVariable(managerType.getName(), ModelNaming.COLLECTION_PROPERTY_MANAGER);

            // constructor
            classBlock.publicConstructor(String.format("%1$s(%2$s %3$s, %4$s %5$s)", collection.getImplementationType().getName(), collection.getInnerClientType().getName(), ModelNaming.COLLECTION_PROPERTY_INNER, managerType.getName(), ModelNaming.MODEL_PROPERTY_MANAGER), methodBlock -> {
                methodBlock.line(String.format("this.%1$s = %2$s;", ModelNaming.COLLECTION_PROPERTY_INNER, ModelNaming.COLLECTION_PROPERTY_INNER));
                methodBlock.line(String.format("this.%1$s = %2$s;", ModelNaming.COLLECTION_PROPERTY_MANAGER, ModelNaming.COLLECTION_PROPERTY_MANAGER));
            });

            // method for properties
            methodTemplates.forEach(m -> m.writeMethod(classBlock));

            // method for inner model
            classBlock.publicMethod(collection.getInnerMethodSignature(), methodBlock -> {
                methodBlock.methodReturn(String.format("this.%s", ModelNaming.COLLECTION_PROPERTY_INNER));
            });

            // method for manager
            classBlock.privateMethod(String.format("%1$s %2$s()", managerType.getName(), FluentUtils.getGetterName(ModelNaming.METHOD_MANAGER)), methodBlock -> {
                methodBlock.methodReturn(String.format("this.%s", ModelNaming.MODEL_PROPERTY_MANAGER));
            });

            // method for define resource
            int resourceCount = collection.getResourceCreates().size();
            collection.getResourceCreates()
                    .forEach(rc -> {
                        FluentMethod defineMethod = rc.getDefineMethod();
                        if (resourceCount == 1) {
                            ((FluentDefineMethod) defineMethod).setName("define");
                        }

                        defineMethod.getMethodTemplate().writeMethod(classBlock);
                    });
        });
    }
}
