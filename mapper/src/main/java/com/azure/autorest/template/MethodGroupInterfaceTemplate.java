package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.javamodel.JavaFile;

import java.util.HashSet;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/** 
 Writes a MethodGroupClient to a JavaFile as an interface.
*/
public class MethodGroupInterfaceTemplate implements IJavaTemplate<MethodGroupClient, JavaFile>
{
    private static MethodGroupInterfaceTemplate _instance = new MethodGroupInterfaceTemplate();
    public static MethodGroupInterfaceTemplate getInstance()
    {
        return _instance;
    }

    private MethodGroupInterfaceTemplate()
    {
    }

    public final void Write(MethodGroupClient methodGroupClient, JavaFile javaFile)
    {
        JavaSettings settings = JavaSettings.getInstance();
        HashSet<String> imports = new HashSet<String>();
        methodGroupClient.AddImportsTo(imports, false, settings);
        javaFile.Import(imports);

        javaFile.JavadocComment(settings.getMaximumJavadocCommentWidth(), (comment) ->
        {
                comment.Description(String.format("An instance of this class provides access to all the operations defined in %1$s.", methodGroupClient.getInterfaceName()));
        });
        javaFile.PublicInterface(methodGroupClient.getInterfaceName(), interfaceBlock ->
        {
                for (ClientMethod clientMethod : methodGroupClient.getClientMethods())
                {
                    Templates.getClientMethodTemplate().Write(clientMethod, interfaceBlock);
                }
        });
    }
}