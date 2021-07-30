package com.azure.autorest.template;

import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.javamodel.JavaFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServiceVersionTemplate implements IJavaTemplate<ServiceClient, JavaFile> {
    private static final ServiceVersionTemplate _instance = new ServiceVersionTemplate();

    private String className;

    private List<String> serviceVersions;

    public static ServiceVersionTemplate getInstance() {
        return _instance;
    }

    @Override
    public void write(ServiceClient model, JavaFile javaFile) {
        // imports
        Set<String> imports = new HashSet<>();
        imports.add("com.azure.core.util.ServiceVersion");
        javaFile.declareImport(imports);

        javaFile.javadocComment(comment -> {
            comment.description("Service version");
        });

        javaFile.publicEnum(className + " implements ServiceVersion", classBlock -> {
            serviceVersions.forEach(v -> classBlock.value(v, v));

            classBlock.privateFinalMemberVariable("String", "version");

            classBlock.constructor(
                    className + "(String version)",
                    javaBlock -> javaBlock.line("this.version = version;")
            );

            classBlock.annotation("Override");
            classBlock.PublicMethod(
                    "String getVersion()",
                    javaBlock -> javaBlock.line("return this.version;")
            );

            classBlock.javadocComment(comment -> {
                comment.description("Gets the latest service version supported by this client library");
                comment.methodReturns(String.format("The latest {@link %s}", className));
            });
            classBlock.PublicStaticMethod(
                    className + " getLatest()",
                    javaBlock -> javaBlock.methodReturn("TODO")
            );
        });
    }

    public ServiceVersionTemplate className(String className) {
        this.className = className;
        return this;
    }

    public ServiceVersionTemplate serviceVersions(List<String> serviceVersions) {
        this.serviceVersions = serviceVersions;
        return this;
    }
}
