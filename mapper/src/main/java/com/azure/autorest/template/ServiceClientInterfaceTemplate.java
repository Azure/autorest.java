package com.azure.autorest.template;


// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.ServiceClientProperty;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.util.CodeNamer;

import java.util.HashSet;

/**
 Writes a ServiceClient to a JavaFile as an interface.
*/
public class ServiceClientInterfaceTemplate implements IJavaTemplate<ServiceClient, JavaFile>
{
    private static ServiceClientInterfaceTemplate _instance = new ServiceClientInterfaceTemplate();
    public static ServiceClientInterfaceTemplate getInstance()
    {
        return _instance;
    }

    private ServiceClientInterfaceTemplate()
    {
    }

    public final void write(ServiceClient serviceClient, JavaFile javaFile)
    {
        HashSet<String> imports = new HashSet<String>();
        serviceClient.addImportsTo(imports, false, JavaSettings.getInstance());
        javaFile.declareImport(imports);

        javaFile.javadocComment(comment ->
        {
                comment.description(String.format("The interface for %1$s class.", serviceClient.getInterfaceName()));
        });
        javaFile.publicInterface(serviceClient.getInterfaceName(), interfaceBlock ->
        {
                for (ServiceClientProperty property : serviceClient.getProperties())
                {
                    interfaceBlock.javadocComment(comment ->
                    {
                        comment.description(String.format("Gets %1$s", property.getDescription()));
                        comment.methodReturns(String.format("the %1$s value", property.getName()));
                    });
                    interfaceBlock.publicMethod(String.format("%1$s get%2$s()", property.getType(), CodeNamer.toPascalCase(property.getName())));

                    if (!property.getIsReadOnly())
                    {
                        interfaceBlock.javadocComment(comment ->
                        {
                            comment.description(String.format("Sets %1$s", property.getDescription()));
                            comment.param(property.getName(), String.format("the %1$s value", property.getName()));
                            comment.methodReturns("the service client itself");
                        });
                        interfaceBlock.publicMethod(String.format("%1$s set%2$s(%3$s %4$s)", serviceClient.getInterfaceName(), CodeNamer.toPascalCase(property.getName()), property.getType(), property.getName()));
                    }
                }

                for (MethodGroupClient methodGroupClient : serviceClient.getMethodGroupClients())
                {
                    interfaceBlock.javadocComment(comment ->
                    {
                        comment.description(String.format("Gets the %1$s object to access its operations.", methodGroupClient.getInterfaceName()));
                        comment.methodReturns(String.format("the %1$s object.", methodGroupClient.getInterfaceName()));
                    });
                    interfaceBlock.publicMethod(String.format("%1$s %2$s()", methodGroupClient.getInterfaceName(), methodGroupClient.getVariableName()));
                }

                for (ClientMethod clientMethod : serviceClient.getClientMethods())
                {
                    Templates.getClientMethodTemplate().write(clientMethod, interfaceBlock);
                }
        });
    }
}