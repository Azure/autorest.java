package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.javamodel.JavaFile;
import java.util.HashSet;
import java.util.Set;

/**
 * Template to create a synchronous client.
 */
public class ServiceSyncClientTemplate  implements IJavaTemplate<ServiceClient, JavaFile>  {

  private static ServiceSyncClientTemplate _instance = new ServiceSyncClientTemplate();
  private ServiceSyncClientTemplate() {
  }

  public static ServiceSyncClientTemplate getInstance() {
    return _instance;
  }

  @Override
  public final void write(ServiceClient serviceClient, JavaFile javaFile) {
    JavaSettings settings = JavaSettings.getInstance();
    String syncClassName = serviceClient.getClientBaseName().endsWith("Client") ? serviceClient.getClientBaseName()
        : serviceClient.getClientBaseName() + "Client";

    Set<String> imports = new HashSet<>();
    if (serviceClient.getProxy() != null) {
      serviceClient.addImportsTo(imports, true, false, settings);
      imports.add(serviceClient.getPackage() + "." + serviceClient.getClassName());
    } else {
      serviceClient.getMethodGroupClients().forEach(methodGroupClient -> {
        methodGroupClient.addImportsTo(imports, true,
            settings);
        imports.add(methodGroupClient.getPackage() + "." + methodGroupClient.getClassName());
      });
    }
    imports.add("com.azure.core.annotation.ServiceClient");

    javaFile.declareImport(imports);
    javaFile.javadocComment(comment ->
        comment.description(String.format("Initializes a new instance of the synchronous %1$s type.",
            serviceClient.getInterfaceName())));

    javaFile.annotation(String.format("ServiceClient(builder = %sBuilder.class)", serviceClient.getClientBaseName()));
    javaFile.publicFinalClass(syncClassName, classBlock ->
    {
      // Add service client member variable
      if (serviceClient.getProxy() != null) {
        classBlock.privateMemberVariable(serviceClient.getClassName(), "serviceClient");
      } else {
        classBlock.privateMemberVariable(serviceClient.getMethodGroupClients().get(0).getClassName(), "serviceClient");
      }

      // Service Client Constructor
      classBlock.javadocComment(comment ->
          comment
              .description(String.format("Initializes an instance of %1$s client.", serviceClient.getInterfaceName()))
      );

      if (serviceClient.getProxy() != null) {
        classBlock.packagePrivateConstructor(String.format("%1$s(%2$s %3$s)", syncClassName,
            serviceClient.getClassName(), "serviceClient"), constructorBlock -> {
          constructorBlock.line("this.serviceClient = serviceClient;");
        });
      } else {
        classBlock.packagePrivateConstructor(String.format("%1$s(%2$s %3$s)", syncClassName,
            serviceClient.getMethodGroupClients().get(0).getClassName(), "serviceClient"), constructorBlock -> {
          constructorBlock.line("this.serviceClient = serviceClient;");
        });
      }

      if (serviceClient.getProxy() != null) {
        serviceClient.getClientMethods()
            .stream()
            .filter(clientMethod -> !clientMethod.getType().name().contains("Async"))
            .forEach(clientMethod -> {
              Templates.getWrapperClientMethodTemplate().write(clientMethod, classBlock);
            });
      } else {
        serviceClient.getMethodGroupClients().get(0)
            .getClientMethods()
            .stream()
            .filter(clientMethod -> !clientMethod.getType().name().contains("Async"))
            .forEach(clientMethod -> {
              Templates.getWrapperClientMethodTemplate().write(clientMethod, classBlock);
            });
      }
    });
  }


}
