package com.azure.autorest.android.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.Constructor;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.template.ServiceAsyncClientTemplate;

import java.util.Set;
import java.util.stream.Collectors;

public class AndroidServiceAsyncClientTemplate extends ServiceAsyncClientTemplate {
    private static final AndroidServiceAsyncClientTemplate instance = new AndroidServiceAsyncClientTemplate();

    public static AndroidServiceAsyncClientTemplate getInstance() { return instance;}

    private AndroidServiceAsyncClientTemplate() {
    }

    @Override
    protected void addServiceClientImports(ServiceClient serviceClient,
                                           JavaSettings settings,
                                           MethodGroupClient methodGroupClient,
                                           Set<String> imports) {
        if (methodGroupClient == null) {
            serviceClient.addImportsTo(imports, true, false, settings);
        } else {
            methodGroupClient.addImportsTo(imports, true, settings);
            imports.add(methodGroupClient.getPackage() + "." + methodGroupClient.getClassName());
        }
    }

    @Override
    protected void addAnnotationImports(Set<String> imports) {
    }

    @Override
    protected void addClassAnnotation(JavaFile javaFile, ServiceClient serviceClient) {
    }

    @Override
    protected void writeMemberVariablesAndConstructors(ServiceClient serviceClient,
                                                       String asyncClassName,
                                                       MethodGroupClient methodGroupClient,
                                                       boolean wrapServiceClient,
                                                       JavaClass classBlock) {
        for (Constructor constructor : serviceClient.getConstructors()) {
            if (constructor.getParameters().size() == 0) {
                continue;
            }
            for(ClientMethodParameter constructorParameter: constructor.getParameters()) {
                classBlock.privateMemberVariable(constructorParameter.getClientType().toString(), constructorParameter.getName());
            }
            classBlock.javadocComment(comment -> {
                comment.description(String.format("Initializes an instance of %1$s client.", asyncClassName));
            });
            String argumentList = constructor.getParameters().stream()
                    .map(parameter -> String.format("%1$s %2$s", parameter.getClientType().toString(), parameter.getName()))
                    .collect(Collectors.joining(","));
            classBlock.packagePrivateConstructor(String.format("%1$s(%2$s)", asyncClassName, argumentList), cconstructorBlock -> {
                for(ClientMethodParameter constructorParameter: constructor.getParameters()) {
                    cconstructorBlock.line(String.format("this.%1$s = %1$s;", constructorParameter.getName()));
                }
            });
        }
    }

    @Override
    protected void embedClientBuilder(AsyncSyncClient serviceClient, JavaClass classBlock) {
        AndroidEmbeddedBuilderTemplate builderTemplate = AndroidEmbeddedBuilderTemplate.getInstance();
        builderTemplate.write(serviceClient, classBlock);
    }

    @Override
    protected boolean shouldEmbedClientBuilder() {
        return true;
    }
}
