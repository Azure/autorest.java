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

        // class javadoc
        javaFile.javadocComment(comment ->
            comment.description("This is the Helper class to enable json merge patch serialization for a model"));
        // class code
        javaFile.publicClass(null, ClientModelUtil.JSON_MERGE_PATCH_HELPER_CLASS_NAME,
            javaClass -> createJsonMergePatchAccessHelpers(models, javaClass));
    }

    /**
     * Add imports for JsonMergePatchHelper.
     *
     * @param imports Set of imports to add to.
     * @param models List of models in the service that are used in json-merge-patch.
     * @param settings JavaSettings to use.
     */
    private static void addImports(Set<String> imports, List<ClientModel> models, JavaSettings settings) {
        if (models != null && !models.isEmpty()) {
            models.forEach(model -> model.addImportsTo(imports, settings));
        }
    }

    /**
     * Creates the access helpers for the json-merge-patch models in a service.
     * <p>
     * This will create the accessor property, interface, and method for each model that is used in json-merge-patch.
     * Instead of following standard patterns where all fields are declared together, the field, interface, and static
     * methods for each model are declared together.
     *
     * @param models List of models in the service that are used in json-merge-patch.
     * @param javaClass JavaClass to add accessor properties.
     */
    private static void createJsonMergePatchAccessHelpers(List<ClientModel> models, JavaClass javaClass) {
        if (models == null || models.isEmpty()) {
            return;
        }

        for (ClientModel model : models) {
            if (model.getParentModelName() != null && !model.getParentModelName().isEmpty()) {
                // Only polymorphic parent models generate an accessor.
                continue;
            }

            if (!model.getImplementationDetails().isInput()) {
                // Model is only used as output and doesn't need to support json-merge-patch.
                continue;
            }

            String modelName = model.getName();
            String camelModelName = CodeNamer.toCamelCase(modelName);

            // Accessor field declaration.
            javaClass.privateMemberVariable(String.format("static %sAccessor %sAccessor", modelName, camelModelName));

            // Accessor interface declaration.
            javaClass.interfaceBlock(JavaVisibility.Public, String.format("%sAccessor", modelName),
                interfaceBlock -> interfaceBlock.publicMethod(
                    String.format("%1$s prepareModelForJsonMergePatch(%1$s %2$s, boolean jsonMergePatchEnabled)",
                        modelName, camelModelName)));

            // Accessor field setter.
            javaClass.publicStaticMethod(String.format("void set%1$sAccessor(%1$sAccessor accessor)", modelName),
                methodBlock -> methodBlock.line(camelModelName + "Accessor = accessor;"));

            // Accessor field getter.
            javaClass.publicStaticMethod(String.format("%1$sAccessor get%1$sAccessor()", modelName),
                methodBlock -> methodBlock.methodReturn(camelModelName + "Accessor"));
        }
    }
}
