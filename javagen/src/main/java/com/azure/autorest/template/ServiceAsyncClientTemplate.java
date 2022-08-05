// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientBuilder;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ConvenienceMethod;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.ParameterSynthesizedOrigin;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaContext;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.ModelNamer;
import com.azure.core.client.traits.EndpointTrait;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Template to create an asynchronous client.
 */
public class ServiceAsyncClientTemplate implements IJavaTemplate<AsyncSyncClient, JavaFile> {

  private static final ServiceAsyncClientTemplate INSTANCE = new ServiceAsyncClientTemplate();

  protected ServiceAsyncClientTemplate() {
  }

  public static ServiceAsyncClientTemplate getInstance() {
    return INSTANCE;
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

    addImportsToConvenienceMethods(imports, asyncClient.getConvenienceMethods());

    javaFile.declareImport(imports);
    javaFile.javadocComment(comment ->
        comment.description(String.format("Initializes a new instance of the asynchronous %1$s type.",
            serviceClient.getInterfaceName())));

    if (asyncClient.getClientBuilder() != null) {
      javaFile.annotation(String.format("ServiceClient(builder = %s.class, isAsync = true)", asyncClient.getClientBuilder().getClassName()));
    }
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
        comment.description(String.format("Initializes an instance of %1$s class.", asyncClient.getClassName()));
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
        serviceClient.getClientMethods().stream()
            .filter(clientMethod -> clientMethod.getMethodVisibility() == JavaVisibility.Public)
            .filter(clientMethod -> !clientMethod.isImplementationOnly())
            .filter(clientMethod -> clientMethod.getType().name().contains("Async"))
            .filter(clientMethod -> !clientMethod.getMethodParameters()
                    .stream()
                    .anyMatch(methodParam -> methodParam.getWireType().contains(ClassType.Context)))
            .forEach(clientMethod -> {
              Templates.getWrapperClientMethodTemplate().write(clientMethod, classBlock);
            });
      } else {
        methodGroupClient.getClientMethods().stream()
            .filter(clientMethod -> clientMethod.getMethodVisibility() == JavaVisibility.Public)
            .filter(clientMethod -> !clientMethod.isImplementationOnly())
            .filter(clientMethod -> clientMethod.getType().name().contains("Async"))
            .filter(clientMethod -> !clientMethod.getMethodParameters()
                    .stream()
                    .anyMatch(methodParam -> methodParam.getWireType().contains(ClassType.Context)))
            .forEach(clientMethod -> {
              Templates.getWrapperClientMethodTemplate().write(clientMethod, classBlock);
            });

        asyncClient.getConvenienceMethods().forEach(m -> writeConvenienceMethods(classBlock, m));
      }

      ServiceAsyncClientTemplate.addEndpointMethod(classBlock, asyncClient.getClientBuilder(), "this.serviceClient");
    });
  }

  protected void addServiceClientAnnotationImports(Set<String> imports) {
    imports.add("com.azure.core.annotation.ServiceClient");
    imports.add("com.azure.core.annotation.Generated");
  }

  protected void addGeneratedAnnotation(JavaContext classBlock) {
    classBlock.annotation("Generated");
  }

  /**
   * Adds "getEndpoint" method, if necessary.
   * <p>
   * This method is companion to "sendRequest" method. Without endpoint, the URL in sendRequest is hard to compose.
   *
   * @param classBlock the class block for writing the method.
   * @param clientBuilder the client builder.
   * @param clientReference the code for client reference. E.g. "this.serviceClient" or "this.client".
   */
  static void addEndpointMethod(JavaClass classBlock, ClientBuilder clientBuilder, String clientReference) {
    // expose "getEndpoint" as public, as companion to "sendRequest" method
    if (JavaSettings.getInstance().isGenerateSendRequestMethod()) {
      clientBuilder.getBuilderTraits().stream()
          .filter(t -> EndpointTrait.class.getSimpleName().equals(t.getTraitInterfaceName()))
          .map(t -> t.getTraitMethods().iterator().next().getProperty())
          .findAny().ifPresent(serviceClientProperty -> {
            classBlock.javadocComment(comment -> {
              comment.description("Gets the service endpoint that the client is connected to.");
              comment.methodReturns("the service endpoint that the client is connected to.");
            });
            String methodName = new ModelNamer().modelPropertyGetterName(serviceClientProperty);
            classBlock.method(serviceClientProperty.getMethodVisibility(), null, String.format("%1$s %2$s()",
                serviceClientProperty.getType(), methodName), function -> {
              function.methodReturn(String.format("%1$s.%2$s()", clientReference, methodName));
            });
          });
    }
  }

  private static void addImportsToConvenienceMethods(Set<String> imports, List<ConvenienceMethod> convenienceMethods) {
    JavaSettings settings = JavaSettings.getInstance();
    convenienceMethods.stream().flatMap(m -> m.getConvenienceMethods().stream())
        .forEach(m -> m.addImportsTo(imports, false, settings));

    ClassType.BinaryData.addImportsTo(imports, false);
    ClassType.RequestOptions.addImportsTo(imports, false);
  }

  private static void writeConvenienceMethods(JavaClass classBlock, ConvenienceMethod convenienceMethodObj) {
    ClientMethod clientMethod = convenienceMethodObj.getClientMethod();
    convenienceMethodObj.getConvenienceMethods().stream()
        .filter(m -> m.getType().name().contains("Async"))
        .forEach(convenienceMethod -> {
          classBlock.javadocComment(comment -> {
            ClientMethodTemplate.generateJavadoc(convenienceMethod, comment, convenienceMethod.getProxyMethod(), true);
          });

          classBlock.publicMethod(convenienceMethod.getDeclaration(), methodBlock -> {
            // RequestOptions
            methodBlock.line("RequestOptions requestOptions = new RequestOptions();");

            Map<MethodParameter, MethodParameter> parametersMap =
                findParametersForConvenienceMethod(convenienceMethod, clientMethod);
            Map<String, String> parameterExpressionsMap = new HashMap<>();
            for (Map.Entry<MethodParameter, MethodParameter> entry : parametersMap.entrySet()) {
              MethodParameter parameter = entry.getKey();
              MethodParameter clientParameter = entry.getValue();

              if (parameter.getProxyMethodParameter().getOrigin() == ParameterSynthesizedOrigin.CONTEXT) {
                // Context
                methodBlock.line(String.format("requestOptions.setContext(%s);", parameter.getName()));
              } else if (clientParameter != null) {
                String expression = parameter.getName();
                if (parameter.getProxyMethodParameter().getRequestParameterLocation() == RequestParameterLocation.BODY) {
                  expression = expressionConvertToBinaryData(parameter.getName(), parameter.getClientMethodParameter().getClientType());
                }
                parameterExpressionsMap.put(clientParameter.getName(), expression);
              } else {
                switch (parameter.getProxyMethodParameter().getRequestParameterLocation()) {
                  case HEADER:
                    methodBlock.line(
                            String.format("requestOptions.setHeader(%1$s, %2$s);",
                                    ClassType.String.defaultValueExpression(parameter.getSerializedName()),
                                    expressionConvertToString(parameter.getName(), parameter.getClientMethodParameter().getClientType())));
                    break;

                  case QUERY:
                    methodBlock.line(
                            String.format("requestOptions.addQueryParam(%1$s, %2$s);",
                                    ClassType.String.defaultValueExpression(parameter.getSerializedName()),
                                    expressionConvertToString(parameter.getName(), parameter.getClientMethodParameter().getClientType())));
                    break;

                  case BODY:
                    methodBlock.line(
                            String.format("requestOptions.setBody(%s);",
                                    expressionConvertToBinaryData(parameter.getName(), parameter.getClientMethodParameter().getClientType())));
                    break;
                }
              }
            }

            IType baseReturnType = ((GenericType) convenienceMethod.getReturnValue().getType()).getTypeArguments()[0];
            String methodName = clientMethod.getName().endsWith("Async")
                ? clientMethod.getName().substring(0, clientMethod.getName().length() - "Async".length())
                : clientMethod.getName();
            String invocationExpression = clientMethod.getMethodInputParameters().stream()
                .map(p -> {
                  String expression = parameterExpressionsMap.get(p.getName());
                  if (expression == null) {
                    expression = p.getName();
                  }
                  return expression;
                })
                .collect(Collectors.joining(", "));
            String returnTypeConversionExpression = ClientModelUtil.isClientModel(baseReturnType)
                ? String.format(".map(r -> r.toObject(%s.class))", baseReturnType)
                : "";

            methodBlock.methodReturn(
                String.format("%1$s(%2$s).map(Response::getValue)%3$s",
                    methodName,
                    invocationExpression,
                    returnTypeConversionExpression));
          });
        });
  }

  private static String expressionConvertToBinaryData(String name, IType type) {
    if (type == ClassType.BinaryData) {
      return name;
    } else {
      return String.format("BinaryData.fromObject(%s)", name);
    }
  }

  private static String expressionConvertToString(String name, IType type) {
    if (type == ClassType.String) {
      return name;
    } else if (type instanceof EnumType) {
      return String.format("%s.toString()", name);
    } else {
      return String.format("String.valueOf(%s)", name);
    }
  }

  private static Map<MethodParameter, MethodParameter> findParametersForConvenienceMethod(
          ClientMethod convenienceMethod, ClientMethod clientMethod) {
    Map<MethodParameter, MethodParameter> parameterMap = new LinkedHashMap<>();
    List<MethodParameter> convenienceParameters = getParameters(convenienceMethod, true);
    Map<String, MethodParameter> clientParameters = getParameters(clientMethod, false).stream()
        .collect(Collectors.toMap(MethodParameter::getSerializedName, Function.identity()));
    for (MethodParameter convenienceParameter : convenienceParameters) {
      String name = convenienceParameter.getSerializedName();
      parameterMap.put(convenienceParameter, clientParameters.get(name));
    }
    return parameterMap;
  }

  private static List<MethodParameter> getParameters(ClientMethod clientMethod, boolean useAllParameters) {
    List<ProxyMethodParameter> proxyMethodParameters = useAllParameters ? clientMethod.getProxyMethod().getAllParameters() : clientMethod.getProxyMethod().getParameters();
    Map<String, ProxyMethodParameter> proxyMethodParameterByClientParameterName = proxyMethodParameters.stream()
            .collect(Collectors.toMap(p -> CodeNamer.getEscapedReservedClientMethodParameterName(p.getName()), Function.identity()));
    return clientMethod.getMethodParameters().stream()
        .filter(p -> !p.getIsConstant() && !p.getFromClient())
        .map(p -> new MethodParameter(proxyMethodParameterByClientParameterName.get(p.getName()), p))
        .collect(Collectors.toList());
  }

  private static class MethodParameter {

    private final ProxyMethodParameter proxyMethodParameter;
    private final ClientMethodParameter clientMethodParameter;

    public MethodParameter(ProxyMethodParameter proxyMethodParameter, ClientMethodParameter clientMethodParameter) {
      this.proxyMethodParameter = proxyMethodParameter;
      this.clientMethodParameter = clientMethodParameter;
    }

    public ProxyMethodParameter getProxyMethodParameter() {
      return proxyMethodParameter;
    }

    public ClientMethodParameter getClientMethodParameter() {
      return clientMethodParameter;
    }

    public String getName() {
      return this.getClientMethodParameter().getName();
    }

    public String getSerializedName() {
      if (this.getProxyMethodParameter() == null) {
        return null;
      } else {
        String name = this.getProxyMethodParameter().getRequestParameterName();
        if (name == null && this.getProxyMethodParameter().getRequestParameterLocation() == RequestParameterLocation.BODY) {
          name = "__internal_request_BODY";
        }
        return name;
      }
    }
  }
}
