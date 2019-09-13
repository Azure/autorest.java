package com.azure.autorest.template;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.Manager;
import com.azure.autorest.model.javamodel.JavaFile;

/**
 Writes a Manager to a JavaFile.
*/
public class ManagerTemplate implements IJavaTemplate<Manager, JavaFile>
{
    private static ManagerTemplate _instance = new ManagerTemplate();
    public static ManagerTemplate getInstance()
    {
        return _instance;
    }

    private ManagerTemplate()
    {
    }

    public final void Write(Manager manager, JavaFile javaFile)
    {
        String className = String.format("%1$sManager", manager.getServiceName());

        javaFile.JavadocComment(comment -> comment.Description(String.format("Entry point to Azure %1$s resource management.", manager.getServiceName())));
        javaFile.PublicFinalClass(String.format("%1$s extends Manager<%2$s, %3$s>", className, className, manager.getServiceClientName() + "Impl"), classBlock ->
        {
                classBlock.JavadocComment(comment ->
                {
                    comment.Description(String.format("Get a Configurable instance that can be used to create %1$s with optional configuration.", className));
                    comment.Return("the instance allowing configurations");
                });
                classBlock.PublicStaticMethod("Configurable configure()", function ->
                {
                    function.Return(String.format("new %1$s.ConfigurableImpl()", className));
                });

                classBlock.JavadocComment(comment ->
                {
                    comment.Description(String.format("Creates an instance of %1$s that exposes %2$s resource management API entry points.", className, manager.getServiceName()));
                    comment.Param(manager.getAzureTokenCredentialsParameter().getName(), manager.getAzureTokenCredentialsParameter().getDescription());
                    comment.Param("subscriptionId", "the subscription UUID");
                    comment.Return(String.format("the %1$s", className));
                });
                classBlock.PublicStaticMethod(String.format("%1$s authenticate(%2$s, String subscriptionId)", className, manager.getAzureTokenCredentialsParameter().getDeclaration()), function ->
                {
                    function.Line(String.format("final %1$s %2$s = AzureProxy.defaultPipeline(%3$s.class, %4$s);", manager.getHttpPipelineParameter().getClientType(), manager.getHttpPipelineParameter().getName(), className, manager.getAzureTokenCredentialsParameter().getName()));
                    function.Return(String.format("new %1$s(%2$s, subscriptionId)", className, manager.getHttpPipelineParameter().getName()));
                });

                classBlock.JavadocComment(comment ->
                {
                    comment.Description(String.format("Creates an instance of %1$s that exposes %2$s resource management API entry points.", className, manager.getServiceName()));
                    comment.Param(manager.getHttpPipelineParameter().getName(), manager.getHttpPipelineParameter().getDescription());
                    comment.Param("subscriptionId", "the subscription UUID");
                    comment.Return(String.format("the %1$s", className));
                });
                classBlock.PublicStaticMethod(String.format("%1$s authenticate(%2$s %3$s, String subscriptionId)", className, manager.getHttpPipelineParameter().getClientType(), manager.getHttpPipelineParameter().getName()), function ->
                {
                    function.Return(String.format("new %1$s(%2$s, subscriptionId)", className, manager.getHttpPipelineParameter().getName()));
                });

                classBlock.JavadocComment(comment ->
                {
                    comment.Description("The interface allowing configurations to be set.");
                });
                classBlock.PublicInterface("Configurable extends AzureConfigurable<Configurable>", interfaceBlock ->
                {
                    interfaceBlock.JavadocComment(comment ->
                    {
                        comment.Description(String.format("Creates an instance of %1$s that exposes %2$s management API entry points.", className, manager.getServiceName()));
                        comment.Param(manager.getAzureTokenCredentialsParameter().getName(), manager.getAzureTokenCredentialsParameter().getDescription());
                        comment.Param("subscriptionId", "the subscription UUID");
                        comment.Return(String.format("the interface exposing %1$s management API entry points that work across subscriptions", manager.getServiceName()));
                    });
                    interfaceBlock.PublicMethod(String.format("%1$s authenticate(%2$s, String subscriptionId)", className, manager.getAzureTokenCredentialsParameter().getDeclaration()));
                });

                classBlock.JavadocComment(comment ->
                {
                    comment.Description("The implementation for Configurable interface.");
                });
                classBlock.PrivateStaticFinalClass("ConfigurableImpl extends AzureConfigurableImpl<Configurable> implements Configurable", innerClass ->
                {
                    innerClass.PublicMethod(String.format("%1$s authenticate(%2$s, String subscriptionId)", className, manager.getAzureTokenCredentialsParameter().getDeclaration()), function ->
                    {
                        function.Return(String.format("%1$s.authenticate(build%2$s(%3$s), subscriptionId)", className, manager.getHttpPipelineParameter().getClientType(), manager.getAzureTokenCredentialsParameter().getName()));
                    });
                });

                classBlock.PrivateMethod(String.format("private %1$s(%2$s, String subscriptionId)", className, manager.getHttpPipelineParameter().getDeclaration()), constructor ->
                {
                    constructor.Line("super(");
                    constructor.Indent(() ->
                    {
                        constructor.Line(String.format("%1$s,", manager.getHttpPipelineParameter().getName()));
                        constructor.Line("subscriptionId,");
                        constructor.Line(String.format("new %1$sImpl(%2$s).withSubscriptionId(subscriptionId));", manager.getServiceClientName(), manager.getHttpPipelineParameter().getName()));
                    });
                });
        });
    }
}