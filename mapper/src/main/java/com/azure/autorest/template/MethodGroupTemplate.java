package com.azure.autorest.template;


// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.javamodel.JavaFile;

import java.util.HashSet;
import java.util.Set;

/**
 Writes a MethodGroupClient to a JavaFile.
*/
public class MethodGroupTemplate implements IJavaTemplate<MethodGroupClient, JavaFile>
{
    private static MethodGroupTemplate _instance = new MethodGroupTemplate();
    public static MethodGroupTemplate getInstance()
    {
        return _instance;
    }

    private MethodGroupTemplate()
    {
    }

    public final void Write(MethodGroupClient methodGroupClient, JavaFile javaFile)
    {
        JavaSettings settings = JavaSettings.getInstance();
        Set<String> imports = new HashSet<String>();
        methodGroupClient.AddImportsTo(imports, true, settings);
        javaFile.Import(imports);

        String parentDeclaration = !methodGroupClient.getImplementedInterfaces().isEmpty() ? String.format(" implements %1$s", String.join(", ", methodGroupClient.getImplementedInterfaces())) : "";

        javaFile.JavadocComment(settings.getMaximumJavadocCommentWidth(), comment ->
        {
                comment.Description(String.format("An instance of this class provides access to all the operations defined in %1$s.", methodGroupClient.getInterfaceName()));
        });
        javaFile.PublicFinalClass(String.format("%1$s%2$s", methodGroupClient.getClassName(), parentDeclaration), classBlock ->
        {
                classBlock.JavadocComment(String.format("The proxy service used to perform REST calls."));
                classBlock.PrivateMemberVariable(methodGroupClient.getProxy().getName(), "service");

                classBlock.JavadocComment("The service client containing this operation class.");
                classBlock.PrivateMemberVariable(methodGroupClient.getServiceClientName(), "client");

                classBlock.JavadocComment(comment ->
                {
                    comment.Description(String.format("Initializes an instance of %1$s.", methodGroupClient.getClassName()));
                    comment.Param("client", "the instance of the service client containing this operation class.");
                });
                classBlock.PublicConstructor(String.format("%1$s(%2$s client)", methodGroupClient.getClassName(), methodGroupClient.getServiceClientName()), constructor ->
                {
                    if (methodGroupClient.getProxy() != null)
                    {
                        ClassType proxyType = (settings.getIsAzureOrFluent() ? ClassType.AzureProxy : ClassType.RestProxy);
                        constructor.Line(String.format("this.service = %1$s.create(%2$s.class, client.getHttpPipeline());", proxyType.getName(), methodGroupClient.getProxy().getName()));
                    }
                    constructor.Line("this.client = client;");
                });

                Templates.getProxyTemplate().Write(methodGroupClient.getProxy(), classBlock);

                for (ClientMethod clientMethod : methodGroupClient.getClientMethods())
                {
                    Templates.getClientMethodTemplate().Write(clientMethod, classBlock);
                }
        });
    }
}