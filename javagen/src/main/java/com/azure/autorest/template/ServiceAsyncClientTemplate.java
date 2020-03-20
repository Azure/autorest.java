package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.javamodel.JavaFile;
import java.util.HashSet;
import java.util.Set;

public class ServiceAsyncClientTemplate implements IJavaTemplate<ServiceClient, JavaFile> {

  private static ServiceAsyncClientTemplate _instance = new ServiceAsyncClientTemplate();
  private ServiceAsyncClientTemplate() {
  }

  public static ServiceAsyncClientTemplate getInstance() {
    return _instance;
  }

  @Override
  public final void write(ServiceClient serviceClient, JavaFile javaFile) {
    JavaSettings settings = JavaSettings.getInstance();
    String serviceClientClassDeclaration = String.format("%1$sAsync", serviceClient.getClassName());
    Set<String> imports = new HashSet<>();
    if (serviceClient.getProxy() != null) {
      serviceClient.addImportsTo(imports, true, false, settings);
    } else {
      serviceClient.getMethodGroupClients().forEach(methodGroupClient -> methodGroupClient.addImportsTo(imports, true,
          settings));
    }
    imports.add("com.azure.core.annotation.ServiceClient");
    javaFile.declareImport(imports);
    javaFile.javadocComment(comment ->
        comment.description(String.format("Initializes a new instance of the asynchronous %1$s type.",
            serviceClient.getInterfaceName())));

    javaFile.annotation(String.format("ServiceClient(builder = %sBuilder.class)", serviceClient.getClassName()));
    javaFile.publicFinalClass(serviceClientClassDeclaration, classBlock ->
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
        classBlock.packagePrivateConstructor(String.format("%1$sAsync(%2$s %3$s)", serviceClient.getClassName(),
            serviceClient.getClassName(), "serviceClient"), constructorBlock -> {
          constructorBlock.line("this.serviceClient = serviceClient;");
        });
      } else {
        classBlock.packagePrivateConstructor(String.format("%1$sAsync(%2$s %3$s)", serviceClient.getClassName(),
            serviceClient.getMethodGroupClients().get(0).getClassName(), "serviceClient"), constructorBlock -> {
          constructorBlock.line("this.serviceClient = serviceClient;");
        });
      }

      if (serviceClient.getProxy() != null) {
        serviceClient.getClientMethods()
            .stream()
            .filter(clientMethod -> clientMethod.getType().name().contains("Async"))
            .forEach(clientMethod -> {
              Templates.getWrapperClientMethodTemplate().write(clientMethod, classBlock);
            });
      } else {
        serviceClient.getMethodGroupClients().get(0)
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
