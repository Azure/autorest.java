package com.azure.autorest.android.template;

import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.template.MethodGroupTemplate;

import static com.azure.autorest.model.javamodel.JavaVisibility.PackagePrivate;

public class AndroidMethodGroupTemplate extends MethodGroupTemplate {
    private static MethodGroupTemplate _instance = new AndroidMethodGroupTemplate();

    protected AndroidMethodGroupTemplate() {
    }

    public static MethodGroupTemplate getInstance() {
        return _instance;
    }

    @Override
    protected void writeServiceProxyConstruction(JavaBlock constructor, MethodGroupClient methodGroupClient) {
        ClassType proxyType = ClassType.AndroidRestProxy;
        constructor.line(String.format("this.service = %1$s.create(%2$s.class, client.getHttpPipeline(), client.getJacksonSerder());",
                proxyType.getName(), methodGroupClient.getProxy().getName()));
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

