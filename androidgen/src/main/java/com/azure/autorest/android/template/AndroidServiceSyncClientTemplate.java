package com.azure.autorest.android.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.template.ServiceSyncClientTemplate;
import com.azure.autorest.template.Templates;

import java.util.HashSet;
import java.util.Set;

public class AndroidServiceSyncClientTemplate  extends ServiceSyncClientTemplate {
    private static final AndroidServiceSyncClientTemplate instance = new AndroidServiceSyncClientTemplate();

    public static AndroidServiceSyncClientTemplate getInstance() { return instance;}

    private AndroidServiceSyncClientTemplate() {
    }

    @Override
    public void write(AsyncSyncClient syncClient, JavaFile javaFile) {
        ServiceClient serviceClient = syncClient.getServiceClient();

        AndroidEmbeddedBuilderTemplate embeddedBuilderTemplate = new AndroidEmbeddedBuilderTemplate(syncClient);

        JavaSettings settings = JavaSettings.getInstance();
        String syncClassName = syncClient.getClassName();
        MethodGroupClient methodGroupClient = syncClient.getMethodGroupClient();
        final boolean wrapServiceClient = methodGroupClient == null;

        Set<String> imports = new HashSet<>();
        if (wrapServiceClient) {
            serviceClient.addImportsTo(imports, true, false, settings);
            imports.add(serviceClient.getPackage() + "." + serviceClient.getClassName());
        } else {
            methodGroupClient.addImportsTo(imports, true, settings);
            imports.add(methodGroupClient.getPackage() + "." + methodGroupClient.getClassName());
        }

        embeddedBuilderTemplate.addImportsTo(imports);

        javaFile.declareImport(imports);
        javaFile.javadocComment(comment ->
                comment.description(String.format("Initializes a new instance of the synchronous %1$s type.",
                        serviceClient.getInterfaceName())));

        javaFile.publicFinalClass(syncClassName, classBlock ->
        {
            // Add service client member variable
            if (wrapServiceClient) {
                classBlock.privateMemberVariable(serviceClient.getClassName(), "serviceClient");
            } else {
                classBlock.privateMemberVariable(methodGroupClient.getClassName(), "serviceClient");
            }

            // Service Client Constructor
            classBlock.javadocComment(comment ->
                    comment
                            .description(String.format("Initializes an instance of %1$s client.",
                                    wrapServiceClient ? serviceClient.getInterfaceName() : methodGroupClient.getInterfaceName()))
            );

            if (wrapServiceClient) {
                classBlock.packagePrivateConstructor(String.format("%1$s(%2$s %3$s)", syncClassName,
                        serviceClient.getClassName(), "serviceClient"), constructorBlock -> {
                    constructorBlock.line("this.serviceClient = serviceClient;");
                });
            } else {
                classBlock.packagePrivateConstructor(String.format("%1$s(%2$s %3$s)", syncClassName,
                        methodGroupClient.getClassName(), "serviceClient"), constructorBlock -> {
                    constructorBlock.line("this.serviceClient = serviceClient;");
                });
            }

            if (wrapServiceClient) {
                serviceClient.getClientMethods()
                        .stream()
                        .filter(clientMethod -> clientMethod.getType() == ClientMethodType.SimpleSync
                            || (clientMethod.getType() == ClientMethodType.PagingSync
                                && clientMethod.getName().contains("WithPage")))
                        .forEach(clientMethod -> {
                            Templates.getWrapperClientMethodTemplate().write(clientMethod, classBlock);
                        });
            } else {
                methodGroupClient
                        .getClientMethods()
                        .stream()
                        .filter(clientMethod -> clientMethod.getType() == ClientMethodType.SimpleSync
                                || (clientMethod.getType() == ClientMethodType.PagingSync
                                && clientMethod.getName().contains("WithPage")))
                        .forEach(clientMethod -> {
                            Templates.getWrapperClientMethodTemplate().write(clientMethod, classBlock);
                        });
            }

            // Add embedded builder for this Sync ServiceClient
            embeddedBuilderTemplate.write(classBlock);
        });
    }
}
