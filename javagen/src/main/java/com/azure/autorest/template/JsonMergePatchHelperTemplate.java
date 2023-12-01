// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.CodeNamer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JsonMergePatchHelperTemplate implements IJavaTemplate<List<ClientModel>, JavaFile>{

    private static final JsonMergePatchHelperTemplate INSTANCE = new JsonMergePatchHelperTemplate();

    protected JsonMergePatchHelperTemplate() {
    }

    public static JsonMergePatchHelperTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    public void write(List<ClientModel> models, JavaFile javaFile) {
        // imports
        JavaSettings settings = JavaSettings.getInstance();
        Set<String> imports = new HashSet<>();
        addImports(imports, models, settings);
        javaFile.declareImport(imports);


        javaFile.publicClass(null, ClientModelUtil.JSON_MERGE_PATCH_HELPER_CLASS_NAME, javaClass -> {
            addAccessorProperties(models, javaClass);
            addAccessorInterfaces(models, javaClass);
            addGettersAndSetters(models, javaClass);
        });
    }

    /**
     * Add imports for JsonMergePatchHelper.
     * @param imports
     * @param models
     * @param settings
     */
    private static void addImports(Set<String> imports, List<ClientModel> models, JavaSettings settings) {
        if (models != null && !models.isEmpty()) {
            models.forEach(model -> {
                model.addImportsTo(imports, settings);
            });
        }
    }

    /**
     * Add accessor properties for each model that is used in json-merge-patch.
     *
     * @param models
     * @param javaClass
     */
    private static void addAccessorProperties(List<ClientModel> models, JavaClass javaClass) {
        if (models != null && !models.isEmpty()) {
            models.forEach(model -> {
                javaClass.privateMemberVariable(String.format("static %sAccessor %sAccessor", model.getName(), CodeNamer.toCamelCase(model.getName())));
            });
        }
    }

    /**
     * Add accessor interfaces for each model that is used in json-merge-patch.
     *
     * @param models
     * @param javaClass
     */
    private static void addAccessorInterfaces(List<ClientModel> models, JavaClass javaClass) {
        if (models != null && !models.isEmpty()) {
            models.forEach(model -> {
                javaClass.interfaceBlock(JavaVisibility.Public, String.format("%sAccessor", model.getName()), interfaceBlock -> {
                    interfaceBlock.publicMethod(String.format("%1$s prepareModelForJsonMergePatch(%1$s %2$s, boolean jsonMergePatchEnabled)", model.getName(), CodeNamer.toCamelCase(model.getName())));
                });
            });
        }
    }


    /**
     * Add getter and setter for each model that is used in json-merge-patch.
     *
     * @param models
     * @param javaClass
     */
    private static void addGettersAndSetters(List<ClientModel> models, JavaClass javaClass) {
        if (models != null && !models.isEmpty()) {
            models.forEach(model -> {
                // setters
                javaClass.publicStaticMethod(String.format("void set%1$sAccessor(%1$sAccessor accessor)", model.getName()),methodBlock -> {
                    methodBlock.line(String.format("%sAccessor = accessor;", CodeNamer.toCamelCase(model.getName())));
                });
                // getters
                javaClass.publicStaticMethod(String.format("%1$sAccessor get%1$sAccessor()", model.getName()), methodBlock -> {
                    methodBlock.methodReturn(String.format("%sAccessor",CodeNamer.toCamelCase(model.getName())));
                });
            });
        }
    }
}
