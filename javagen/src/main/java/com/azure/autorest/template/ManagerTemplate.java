// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.model.clientmodel.Manager;
import com.azure.autorest.model.javamodel.JavaFile;

/**
 * Writes a Manager to a JavaFile.
 */
public class ManagerTemplate implements IJavaTemplate<Manager, JavaFile> {
    private static ManagerTemplate _instance = new ManagerTemplate();

    private ManagerTemplate() {
    }

    public static ManagerTemplate getInstance() {
        return _instance;
    }

    public final void write(Manager manager, JavaFile javaFile) {
        String className = String.format("%1$sManager", manager.getServiceName());

        javaFile.javadocComment(comment -> comment.description(String.format("Entry point to Azure %1$s resource management.", manager.getServiceName())));
        javaFile.publicFinalClass(String.format("%1$s extends Manager<%2$s, %3$s>", className, className, manager.getServiceClientName() + "Impl"), classBlock ->
        {
            classBlock.javadocComment(comment ->
            {
                comment.description(String.format("Get a Configurable instance that can be used to create %1$s with optional configuration.", className));
                comment.methodReturns("the instance allowing configurations");
            });
            classBlock.publicStaticMethod("Configurable configure()", function ->
            {
                function.methodReturn(String.format("new %1$s.ConfigurableImpl()", className));
            });

            classBlock.javadocComment(comment ->
            {
                comment.description(String.format("Creates an instance of %1$s that exposes %2$s resource management API entry points.", className, manager.getServiceName()));
                comment.param(manager.getAzureTokenCredentialsParameter().getName(), manager.getAzureTokenCredentialsParameter().getDescription());
                comment.param("subscriptionId", "the subscription UUID");
                comment.methodReturns(String.format("the %1$s", className));
            });
            classBlock.publicStaticMethod(String.format("%1$s authenticate(%2$s, String subscriptionId)", className, manager.getAzureTokenCredentialsParameter().getDeclaration()), function ->
            {
                function.line(String.format("final %1$s %2$s = AzureProxy.defaultPipeline(%3$s.class, %4$s);", manager.getHttpPipelineParameter().getClientType(), manager.getHttpPipelineParameter().getName(), className, manager.getAzureTokenCredentialsParameter().getName()));
                function.methodReturn(String.format("new %1$s(%2$s, subscriptionId)", className, manager.getHttpPipelineParameter().getName()));
            });

            classBlock.javadocComment(comment ->
            {
                comment.description(String.format("Creates an instance of %1$s that exposes %2$s resource management API entry points.", className, manager.getServiceName()));
                comment.param(manager.getHttpPipelineParameter().getName(), manager.getHttpPipelineParameter().getDescription());
                comment.param("subscriptionId", "the subscription UUID");
                comment.methodReturns(String.format("the %1$s", className));
            });
            classBlock.publicStaticMethod(String.format("%1$s authenticate(%2$s %3$s, String subscriptionId)", className, manager.getHttpPipelineParameter().getClientType(), manager.getHttpPipelineParameter().getName()), function ->
            {
                function.methodReturn(String.format("new %1$s(%2$s, subscriptionId)", className, manager.getHttpPipelineParameter().getName()));
            });

            classBlock.javadocComment(comment ->
            {
                comment.description("The interface allowing configurations to be set.");
            });
            classBlock.publicInterface("Configurable extends AzureConfigurable<Configurable>", interfaceBlock ->
            {
                interfaceBlock.javadocComment(comment ->
                {
                    comment.description(String.format("Creates an instance of %1$s that exposes %2$s management API entry points.", className, manager.getServiceName()));
                    comment.param(manager.getAzureTokenCredentialsParameter().getName(), manager.getAzureTokenCredentialsParameter().getDescription());
                    comment.param("subscriptionId", "the subscription UUID");
                    comment.methodReturns(String.format("the interface exposing %1$s management API entry points that work across subscriptions", manager.getServiceName()));
                });
                interfaceBlock.publicMethod(String.format("%1$s authenticate(%2$s, String subscriptionId)", className, manager.getAzureTokenCredentialsParameter().getDeclaration()));
            });

            classBlock.javadocComment(comment ->
            {
                comment.description("The implementation for Configurable interface.");
            });
            classBlock.privateStaticFinalClass("ConfigurableImpl extends AzureConfigurableImpl<Configurable> implements Configurable", innerClass ->
            {
                innerClass.publicMethod(String.format("%1$s authenticate(%2$s, String subscriptionId)", className, manager.getAzureTokenCredentialsParameter().getDeclaration()), function ->
                {
                    function.methodReturn(String.format("%1$s.authenticate(build%2$s(%3$s), subscriptionId)", className, manager.getHttpPipelineParameter().getClientType(), manager.getAzureTokenCredentialsParameter().getName()));
                });
            });

            classBlock.privateMethod(String.format("private %1$s(%2$s, String subscriptionId)", className, manager.getHttpPipelineParameter().getDeclaration()), constructor ->
            {
                constructor.line("super(");
                constructor.indent(() ->
                {
                    constructor.line(String.format("%1$s,", manager.getHttpPipelineParameter().getName()));
                    constructor.line("subscriptionId,");
                    constructor.line(String.format("new %1$sImpl(%2$s).withSubscriptionId(subscriptionId));", manager.getServiceClientName(), manager.getHttpPipelineParameter().getName()));
                });
            });
        });
    }
}
