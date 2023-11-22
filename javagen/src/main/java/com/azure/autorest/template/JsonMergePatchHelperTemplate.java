// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.CodeNamer;

import java.util.HashSet;
import java.util.Set;

public class JsonMergePatchHelperTemplate implements IJavaTemplate<Client, JavaFile>{

    private static final JsonMergePatchHelperTemplate INSTANCE = new JsonMergePatchHelperTemplate();

    protected JsonMergePatchHelperTemplate() {
    }

    public static JsonMergePatchHelperTemplate getInstance() {
        return INSTANCE;
    }

    @Override
    public void write(Client client, JavaFile javaFile) {
        // imports
        JavaSettings settings = JavaSettings.getInstance();
        Set<String> imports = new HashSet<>();
        addImports(imports, client, settings);
        javaFile.declareImport(imports);


        javaFile.publicClass(null, "JsonMergePatchHelper", javaClass -> {
            addAccessorProperties(client, javaClass);
            addAccessorInterfaces(client, javaClass);
            addGettersAndSetters(client, javaClass);
        });
    }

    /**
     * Add imports for JsonMergePatchHelper.
     * @param imports
     * @param client
     * @param settings
     */
    private void addImports(Set<String> imports, Client client, JavaSettings settings) {
        if (client.getModels() != null && !client.getModels().isEmpty()) {
            client.getModels().forEach(model -> {
                model.addImportsTo(imports, settings);
            });
        }
    }

    /**
     * Add accessor properties for each model that is used in json-merge-patch.
     * @param client
     * @param javaClass
     */
    private void addAccessorProperties(Client client, JavaClass javaClass) {
        if (client.getModels() != null && !client.getModels().isEmpty()) {
            client.getModels().forEach(model -> {
                if (ClientModelUtil.isJsonMergePatchModel(model)) {
                    javaClass.privateMemberVariable(String.format("static %sAccessor %sAccessor", model.getName(), CodeNamer.getModelNamer().modelPropertySetterName(model.getName())));
                }
            });
        }
    }

    /**
     * Add accessor interfaces for each model that is used in json-merge-patch.
     *
     * @param client
     * @param javaClass
     */
    private void addAccessorInterfaces(Client client, JavaClass javaClass) {
        if (client.getModels() != null && !client.getModels().isEmpty()) {
            client.getModels().forEach(model -> {
                if (ClientModelUtil.isJsonMergePatchModel(model)) {
                    javaClass.interfaceBlock(JavaVisibility.Public, String.format("%sAccessor", model.getName()), interfaceBlock -> {
                        interfaceBlock.publicMethod(String.format("%s prepareModelForJsonMergePatch(%s %s, boolean jsonMergePatchEnabled)", model.getName(), model.getName(), CodeNamer.getModelNamer().modelPropertySetterName(model.getName())));
                    });
                }
            });
        }
    }


    /**
     * Add getter and setter for each model that is used in json-merge-patch.
     * @param client
     * @param javaClass
     */
    private void addGettersAndSetters(Client client, JavaClass javaClass) {
        if (client.getModels() != null && !client.getModels().isEmpty()) {
            client.getModels().forEach(model -> {
                if (ClientModelUtil.isJsonMergePatchModel(model)) {
                    // setters
                    javaClass.publicStaticMethod(String.format("void set%sAccessor(%sAccessor accessor)", model.getName(), model.getName()),methodBlock -> {
                        methodBlock.line(String.format("%sAccessor = accessor;", CodeNamer.getModelNamer().modelPropertySetterName(model.getName())));
                    });
                    // getters
                    javaClass.publicStaticMethod(String.format("%sAccessor get%sAccessor()", model.getName(), model.getName()), methodBlock -> {
                        methodBlock.line(String.format("return %sAccessor;",CodeNamer.getModelNamer().modelPropertySetterName(model.getName())));
                    });

                }
            });
        }
    }
}
