package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.util.ClientModelUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * Template to create an asynchronous client.
 */
public class ServiceAsyncClientTemplate implements IJavaTemplate<AsyncSyncClient, JavaFile> {

  private static ServiceAsyncClientTemplate _instance = new ServiceAsyncClientTemplate();
  protected ServiceAsyncClientTemplate() {
  }

  public static ServiceAsyncClientTemplate getInstance() {
    return _instance;
  }

  protected void addAnnotationImports(Set<String> imports) {
    imports.add("com.azure.core.annotation.ServiceClient");
  }

  protected void addClassAnnotation(JavaFile javaFile, ServiceClient serviceClient) {
    javaFile.annotation(String.format("ServiceClient(builder = %s.class, isAsync = true)",
            serviceClient.getInterfaceName() + ClientModelUtil.getBuilderSuffix()));
  }

  @Override
  public final void write(AsyncSyncClient asyncClient, JavaFile javaFile) {
    ServiceClient serviceClient = asyncClient.getServiceClient();

    JavaSettings settings = JavaSettings.getInstance();
    String asyncClassName = asyncClient.getClassName();
    MethodGroupClient methodGroupClient = asyncClient.getMethodGroupClient();
    final boolean wrapServiceClient = methodGroupClient == null;

    Set<String> imports = new HashSet<>();
    addServiceClientImports(serviceClient, settings, methodGroupClient, imports);
    addAnnotationImports(imports);

    javaFile.declareImport(imports);
    javaFile.javadocComment(comment ->
        comment.description(String.format("Initializes a new instance of the asynchronous %1$s type.",
            serviceClient.getInterfaceName())));

    addClassAnnotation(javaFile, serviceClient);
    javaFile.publicFinalClass(asyncClassName, classBlock ->
    {
      writeMemberVariablesAndConstructors(serviceClient, asyncClassName, methodGroupClient, wrapServiceClient, classBlock);

      if (wrapServiceClient) {
        serviceClient.getClientMethods()
            .stream()
            .filter(clientMethod -> clientMethod.getType().name().contains("Async"))
            .forEach(clientMethod -> {
              Templates.getWrapperClientMethodTemplate().write(clientMethod, classBlock);
            });
      } else {
        methodGroupClient
            .getClientMethods()
            .stream()
            .filter(clientMethod -> clientMethod.getType().name().contains("Async"))
            .forEach(clientMethod -> {
              Templates.getWrapperClientMethodTemplate().write(clientMethod, classBlock);
            });
      }

      if (shouldEmbedClientBuilder()) {
        embedClientBuilder(asyncClient, classBlock);
      }
    });
  }

  protected void writeMemberVariablesAndConstructors(ServiceClient serviceClient,
                                                     String asyncClassName,
                                                     MethodGroupClient methodGroupClient,
                                                     boolean wrapServiceClient,
                                                     JavaClass classBlock) {
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
      classBlock.packagePrivateConstructor(String.format("%1$s(%2$s %3$s)", asyncClassName,
          serviceClient.getClassName(), "serviceClient"), constructorBlock -> {
        constructorBlock.line("this.serviceClient = serviceClient;");
      });
    } else {
      classBlock.packagePrivateConstructor(String.format("%1$s(%2$s %3$s)", asyncClassName,
          methodGroupClient.getClassName(), "serviceClient"), constructorBlock -> {
        constructorBlock.line("this.serviceClient = serviceClient;");
      });
    }
  }

  protected void addServiceClientImports(ServiceClient serviceClient,
                                       JavaSettings settings,
                                       MethodGroupClient methodGroupClient,
                                       Set<String> imports) {
    if (methodGroupClient == null) {
      serviceClient.addImportsTo(imports, true, false, settings);
      imports.add(serviceClient.getPackage() + "." + serviceClient.getClassName());
    } else {
      methodGroupClient.addImportsTo(imports, true, settings);
      imports.add(methodGroupClient.getPackage() + "." + methodGroupClient.getClassName());
    }
  }

  protected void embedClientBuilder(AsyncSyncClient serviceClient, JavaClass classBlock) {
  }

  protected boolean shouldEmbedClientBuilder() {
    return false;
  }

}
