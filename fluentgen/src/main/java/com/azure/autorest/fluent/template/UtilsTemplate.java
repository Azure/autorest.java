/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaModifier;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.template.IJavaTemplate;
import com.azure.autorest.template.prototype.MethodTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class UtilsTemplate implements IJavaTemplate<Void, JavaFile> {

    private static final UtilsTemplate INSTANCE = new UtilsTemplate();

    public static UtilsTemplate getInstance() {
        return INSTANCE;
    }

    private static final List<MethodTemplate> METHOD_TEMPLATES = new ArrayList<>();
    static {
        MethodTemplate getValueFromIdByNameMethod = MethodTemplate.builder()
                .imports(Arrays.asList(Arrays.class.getName(), Iterator.class.getName()))
                .visibility(JavaVisibility.PackagePrivate)
                .modifiers(Collections.singletonList(JavaModifier.Static))
                .methodSignature("String getValueFromIdByName(String id, String name)")
                .method(block -> block.line(FluentUtils.loadTextFromResource("Utils_getValueFromIdByName.txt")))
                .build();
        METHOD_TEMPLATES.add(getValueFromIdByNameMethod);
    }

    public void write(JavaFile javaFile) {
        write(null, javaFile);
    }

    @Override
    public void write(Void ignored, JavaFile javaFile) {
        Set<String> imports = new HashSet<>();
        METHOD_TEMPLATES.forEach(mt -> mt.addImportsTo(imports));
        javaFile.declareImport(imports);

        javaFile.classBlock(JavaVisibility.PackagePrivate, Collections.singletonList(JavaModifier.Final), ModelNaming.CLASS_UTILS, classBlock -> {
            METHOD_TEMPLATES.forEach(mt -> mt.writeMethod(classBlock));
        });
    }
}
