package com.azure.autorest.android.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.*;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.template.ServiceAsyncClientTemplate;
import com.azure.autorest.template.Templates;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AndroidServiceAsyncClientTemplate extends ServiceAsyncClientTemplate {
    private static final AndroidServiceAsyncClientTemplate instance = new AndroidServiceAsyncClientTemplate();

    public static AndroidServiceAsyncClientTemplate getInstance() {
        return instance;
    }

    private AndroidServiceAsyncClientTemplate() {
    }

    @Override
    public void write(AsyncSyncClient asyncClient, JavaFile javaFile) {
        ServiceClient serviceClient = asyncClient.getServiceClient();

        AndroidEmbeddedBuilderTemplate embeddedBuilderTemplate = new AndroidEmbeddedBuilderTemplate(asyncClient);

        JavaSettings settings = JavaSettings.getInstance();
        String asyncClassName = asyncClient.getClassName();
        MethodGroupClient methodGroupClient = asyncClient.getMethodGroupClient();
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
        javaFile.javadocComment(comment -> comment.description(String.format(
                "Initializes a new instance of the asynchronous %1$s type.", serviceClient.getInterfaceName())));

        javaFile.publicFinalClass(asyncClassName, classBlock -> {
            // Add service client member variable
            if (wrapServiceClient) {
                classBlock.privateMemberVariable(serviceClient.getClassName(), "serviceClient");
            } else {
                classBlock.privateMemberVariable(methodGroupClient.getClassName(), "serviceClient");
            }

            // Service Client Constructor
            classBlock.javadocComment(comment -> comment.description(String.format(
                    "Initializes an instance of %1$s client.",
                    wrapServiceClient ? serviceClient.getInterfaceName() : methodGroupClient.getInterfaceName())));

            if (wrapServiceClient) {
                classBlock.packagePrivateConstructor(
                        String.format("%1$s(%2$s %3$s)", asyncClassName, serviceClient.getClassName(), "serviceClient"),
                        constructorBlock -> {
                            constructorBlock.line("this.serviceClient = serviceClient;");
                        });
            } else {
                classBlock.packagePrivateConstructor(String.format("%1$s(%2$s %3$s)", asyncClassName,
                        methodGroupClient.getClassName(), "serviceClient"), constructorBlock -> {
                            constructorBlock.line("this.serviceClient = serviceClient;");
                        });
            }

            if (wrapServiceClient) {
                serviceClient.getClientMethods().stream().filter(this::shouldWriteMethod).forEach(clientMethod -> {
                    Templates.getWrapperClientMethodTemplate().write(clientMethod, classBlock);
                });
            } else {
                methodGroupClient.getClientMethods().stream().filter(this::shouldWriteMethod).forEach(clientMethod -> {
                    Templates.getWrapperClientMethodTemplate().write(clientMethod, classBlock);
                });
            }

            // Add embedded builder for this AsyncServiceClient
            embeddedBuilderTemplate.write(classBlock);
        });
    }

    private boolean shouldWriteMethod(ClientMethod clientMethod) {
        ClientMethodType methodType = clientMethod.getType();
        if (methodType == ClientMethodType.SimpleAsyncRestResponse || methodType == ClientMethodType.SimpleAsync)
            return true;

        if (methodType != ClientMethodType.PagingAsync)
            return false;

        String collectionMarker = GenericType.AndroidAsyncPagedDataCollection(ClassType.Object).toString();
        int leadingIndex = collectionMarker.indexOf("<");
        final String finalCollectionMarker = collectionMarker.substring(0, leadingIndex);
        Optional<ClientMethodParameter> callbackParam = clientMethod.getParameters().stream()
                .filter(p -> p.getClientType().toString().contains(finalCollectionMarker))
                .reduce((current, next) -> next);
        return callbackParam.isPresent();
    }
}
