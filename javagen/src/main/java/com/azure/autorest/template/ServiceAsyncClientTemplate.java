package com.azure.autorest.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.javamodel.JavaContext;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaVisibility;
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

  @Override
  public final void write(AsyncSyncClient asyncClient, JavaFile javaFile) {
    ServiceClient serviceClient = asyncClient.getServiceClient();

    JavaSettings settings = JavaSettings.getInstance();
    String asyncClassName = asyncClient.getClassName();
    MethodGroupClient methodGroupClient = asyncClient.getMethodGroupClient();
    final boolean wrapServiceClient = methodGroupClient == null;
    final String builderPackageName = ClientModelUtil.getServiceClientBuilderPackageName(serviceClient);
    final String builderClassName = serviceClient.getInterfaceName() + ClientModelUtil.getBuilderSuffix();
    final boolean samePackageAsBuilder = builderPackageName.equals(asyncClient.getPackageName());
    final JavaVisibility constructorVisibility = samePackageAsBuilder ? JavaVisibility.PackagePrivate : JavaVisibility.Public;

    Set<String> imports = new HashSet<>();
    if (wrapServiceClient) {
      serviceClient.addImportsTo(imports, true, false, settings);
      imports.add(serviceClient.getPackage() + "." + serviceClient.getClassName());
    } else {
      methodGroupClient.addImportsTo(imports, true, settings);
      imports.add(methodGroupClient.getPackage() + "." + methodGroupClient.getClassName());
    }
    imports.add(builderPackageName + "." + builderClassName);
    addServiceClientAnnotationImports(imports);

    javaFile.declareImport(imports);
    javaFile.javadocComment(comment ->
        comment.description(String.format("Initializes a new instance of the asynchronous %1$s type.",
            serviceClient.getInterfaceName())));

    javaFile.annotation(String.format("ServiceClient(builder = %s.class, isAsync = true)", builderClassName));
    javaFile.publicFinalClass(asyncClassName, classBlock ->
    {
      // Add service client member variable
      addGeneratedAnnotation(classBlock);
      if (wrapServiceClient) {
        classBlock.privateFinalMemberVariable(serviceClient.getClassName(), "serviceClient");
      } else {
        classBlock.privateFinalMemberVariable(methodGroupClient.getClassName(), "serviceClient");
      }

      // Service Client Constructor
      classBlock.javadocComment(comment -> {
        comment.description(String.format("Initializes an instance of %1$s client.", wrapServiceClient ? serviceClient.getInterfaceName() : methodGroupClient.getInterfaceName()));
        comment.param("serviceClient", "the service client implementation.");
      });
      addGeneratedAnnotation(classBlock);
      if (wrapServiceClient) {
        classBlock.constructor(constructorVisibility, String.format("%1$s(%2$s %3$s)", asyncClassName,
            serviceClient.getClassName(), "serviceClient"), constructorBlock -> {
          constructorBlock.line("this.serviceClient = serviceClient;");
        });
      } else {
        classBlock.constructor(constructorVisibility, String.format("%1$s(%2$s %3$s)", asyncClassName,
            methodGroupClient.getClassName(), "serviceClient"), constructorBlock -> {
          constructorBlock.line("this.serviceClient = serviceClient;");
        });
      }

      if (wrapServiceClient) {
        serviceClient.getClientMethods()
            .stream()
            .filter(clientMethod -> clientMethod.getType().name().contains("Async"))
            .filter(clientMethod -> !clientMethod.getMethodParameters()
                    .stream()
                    .anyMatch(methodParam -> methodParam.getWireType().contains(ClassType.Context)))
            .forEach(clientMethod -> {
              Templates.getWrapperClientMethodTemplate().write(clientMethod, classBlock);
            });
      } else {
        methodGroupClient
            .getClientMethods()
            .stream()
            .filter(clientMethod -> clientMethod.getType().name().contains("Async"))
            .filter(clientMethod -> !clientMethod.getMethodParameters()
                    .stream()
                    .anyMatch(methodParam -> methodParam.getWireType().contains(ClassType.Context)))
            .forEach(clientMethod -> {
              Templates.getWrapperClientMethodTemplate().write(clientMethod, classBlock);
            });
      }
    });
  }

  protected void addServiceClientAnnotationImports(Set<String> imports) {
    imports.add("com.azure.core.annotation.ServiceClient");
    imports.add("com.azure.core.annotation.Generated");
  }

  protected void addGeneratedAnnotation(JavaContext classBlock) {
    classBlock.annotation("Generated");
  }
}
