package com.azure.autorest.android.template;

import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.template.ServiceClientTemplate;

import java.util.Set;

import static com.azure.autorest.model.javamodel.JavaVisibility.PackagePrivate;


public class AndroidServiceClientTemplate extends ServiceClientTemplate {
    private static ServiceClientTemplate _instance = new AndroidServiceClientTemplate();


    protected AndroidServiceClientTemplate() {
    }

    public static ServiceClientTemplate getInstance() {
        return _instance;
    }

    @Override
    protected void addServiceClientAnnotationImport(Set<String> imports) {
        imports.add("com.azure.android.core.rest.annotation.ServiceClient");
    }

    @Override
    protected void addSerializerImport(Set<String> imports) {
        imports.add("com.azure.android.core.serde.jackson.JacksonSerder");

        imports.add("com.azure.android.core.rest.Callback");
        imports.add("com.azure.android.core.rest.Response");
        imports.add("com.azure.android.core.rest.PagedResponse");
        imports.add("java9.util.concurrent.CompletableFuture");
        imports.add("java9.util.function.Function");
    }

    @Override
    protected String writeSerializerInitialization() {
        return "JacksonSerder.createDefault()";
    }

    @Override
    protected String writeRetryPolicyInitialization() {
        return "RetryPolicy.withExponentialBackoff()";
    }

    @Override
    protected void writeSerializerMemberInitialization(com.azure.autorest.model.javamodel.JavaBlock constructorBlock) {
        constructorBlock.line("this.jacksonSerder = jacksonSerder;");
    }

    @Override
    protected String getSerializerPhrase() {
        return "this.jacksonSerder";
    }

    @Override
    protected void writeAdditionalClassBlock(JavaClass classBlock) {
        classBlock.privateStaticFinalClass("ResponseCompletableFuture<T> extends CompletableFuture<Response<T>> implements Callback<Response<T>>",
                embeddedClass -> {
                    embeddedClass.annotation("Override");
                    embeddedClass.publicMethod("void onSuccess(Response<T> response)", method -> {
                        method.line("this.complete(response);");
                    });

                    embeddedClass.annotation("Override");
                    embeddedClass.publicMethod("void onFailure(Throwable error)", method -> {
                        method.line("this.completeExceptionally(error);");
                    });
                });

        classBlock.privateStaticFinalClass("PagedResponseCompletableFuture<P, T> extends CompletableFuture<PagedResponse<T>> implements Callback<Response<P>>",
                embeddedClass -> {
                    embeddedClass.privateFinalMemberVariable("Function<Response<P>, PagedResponse<T>>", "converter");
                    embeddedClass.constructor(PackagePrivate,
                            "PagedResponseCompletableFuture(Function<Response<P>, PagedResponse<T>> converter)",
                            constructorBody ->{
                                constructorBody.line("this.converter = converter;");
                            });
                    embeddedClass.annotation("Override");
                    embeddedClass.publicMethod("void onSuccess(Response<P> response)", method -> {
                        method.line("this.complete(this.converter.apply(response));");
                    });

                    embeddedClass.annotation("Override");
                    embeddedClass.publicMethod("void onFailure(Throwable error)", method -> {
                        method.line("this.completeExceptionally(error);");
                    });
                });
    }
}
