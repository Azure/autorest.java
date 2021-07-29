package com.azure.autorest.template;

import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.javamodel.JavaFile;

import java.util.HashSet;
import java.util.Set;

public class ServiceVersionTemplate implements IJavaTemplate<ServiceClient, JavaFile> {
    private static final ServiceVersionTemplate _instance = new ServiceVersionTemplate();

    private String className = "";

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

        javaFile.publicEnum(className, classBlock -> {
            classBlock.privateFinalMemberVariable("String", "version");
        });
    }

    public ServiceVersionTemplate className(String className) {
        this.className = className;
        return this;
    }
}
