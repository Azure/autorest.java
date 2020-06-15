package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.util.ClientModelUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * Template to create an asynchronous client.
 */
public class ServiceAsyncClientTemplate implements IJavaTemplate<AsyncSyncClient, JavaFile> {

  private static ServiceAsyncClientTemplate _instance = new ServiceAsyncClientTemplate();
  private ServiceAsyncClientTemplate() {
  }

  public static ServiceAsyncClientTemplate getInstance() {
    return _instance;
  }

  @Override
  public final void write(AsyncSyncClient asyncClient, JavaFile javaFile) {
    ServiceClient serviceClient = asyncClient.getServiceClient();

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
    imports.add("com.azure.core.annotation.ServiceClient");

    javaFile.declareImport(imports);
    javaFile.javadocComment(comment ->
        comment.description(String.format("Initializes a new instance of the asynchronous %1$s type.",
            serviceClient.getInterfaceName())));

    javaFile.annotation(String.format("ServiceClient(builder = %s.class)",
            serviceClient.getInterfaceName() + ClientModelUtil.getBuilderSuffix()));
    javaFile.publicFinalClass(asyncClassName, classBlock ->
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
    });
  }
}
