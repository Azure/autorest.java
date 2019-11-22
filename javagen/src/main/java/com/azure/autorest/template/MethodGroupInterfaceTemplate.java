package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.javamodel.JavaFile;

import java.util.HashSet;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/**
 * Writes a MethodGroupClient to a JavaFile as an interface.
 */
public class MethodGroupInterfaceTemplate implements IJavaTemplate<MethodGroupClient, JavaFile> {
    private static MethodGroupInterfaceTemplate _instance = new MethodGroupInterfaceTemplate();

    private MethodGroupInterfaceTemplate() {
    }

    public static MethodGroupInterfaceTemplate getInstance() {
        return _instance;
    }

    public final void write(MethodGroupClient methodGroupClient, JavaFile javaFile) {
        JavaSettings settings = JavaSettings.getInstance();
        HashSet<String> imports = new HashSet<String>();
        methodGroupClient.addImportsTo(imports, false, settings);
        javaFile.declareImport(imports);

        javaFile.javadocComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
        {
            comment.description(String.format("An instance of this class provides access to all the operations defined in %1$s.", methodGroupClient.getInterfaceName()));
        });
        javaFile.publicInterface(methodGroupClient.getInterfaceName(), interfaceBlock ->
        {
            for (ClientMethod clientMethod : methodGroupClient.getClientMethods()) {
                Templates.getClientMethodTemplate().write(clientMethod, interfaceBlock);
            }
        });
    }
}