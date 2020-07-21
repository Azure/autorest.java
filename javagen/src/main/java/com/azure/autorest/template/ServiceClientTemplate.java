//====================================================================================================
//The Free Edition of C# to Java Converter limits conversion output to 100 lines per snippet.

//To subscribe to the Premium Edition, visit our website:
//https://www.tangiblesoftwaresolutions.com/order/order-csharp-to-java.html
//====================================================================================================

package com.azure.autorest.template;


import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.Constructor;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.ServiceClientProperty;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.template.prototype.MethodTemplate;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Writes a ServiceClient to a JavaFile.
 */
public class ServiceClientTemplate implements IJavaTemplate<ServiceClient, JavaFile> {
    private static ServiceClientTemplate _instance = new ServiceClientTemplate();

    protected List<MethodTemplate> additionalMethods = new ArrayList<>();

    protected ServiceClientTemplate() {
    }

    public static ServiceClientTemplate getInstance() {
        return _instance;
    }

    public final void write(ServiceClient serviceClient, JavaFile javaFile) {
        JavaSettings settings = JavaSettings.getInstance();
        String serviceClientClassDeclaration = String.format("%1$s", serviceClient.getClassName());
        if (!settings.isFluent() && settings.shouldGenerateClientInterfaces()) {
            serviceClientClassDeclaration += String.format(" implements %1$s", serviceClient.getInterfaceName());
        }
        if (settings.isFluentPremium()) {
            serviceClientClassDeclaration += String.format(" extends %1$s", "AzureServiceClient");
        }

        Set<String> imports = new HashSet<String>();
        if (settings.shouldClientLogger()) {
            ClassType.ClientLogger.addImportsTo(imports, false);
        }

        if (settings.isFluent() && !settings.shouldGenerateSyncAsyncClients()) {
            imports.add("com.azure.core.annotation.ServiceClient");
            imports.add(String.format("%1$s.%2$s",
                ClientModelUtil.getServiceClientBuilderPackageName(serviceClient),
                serviceClient.getInterfaceName() + ClientModelUtil.getBuilderSuffix()));
        }

        serviceClient.addImportsTo(imports, true, false, settings);
        additionalMethods.forEach(method -> method.addImportsTo(imports));
        javaFile.declareImport(imports);

        final JavaVisibility visibility = serviceClient.getPackage()
            .equals(ClientModelUtil.getServiceClientBuilderPackageName(serviceClient))
            ? JavaVisibility.PackagePrivate
            : JavaVisibility.Public;

        javaFile.javadocComment(comment ->
        {
            String serviceClientTypeName = settings.isFluent() ? serviceClient.getClassName() : serviceClient.getInterfaceName();
            comment.description(String.format("Initializes a new instance of the %1$s type.", serviceClientTypeName));
        });
        if (settings.isFluent() && !settings.shouldGenerateSyncAsyncClients()) {
            javaFile.annotation(String.format("ServiceClient(builder = %s.class)",
                serviceClient.getInterfaceName() + ClientModelUtil.getBuilderSuffix()));
        }
        javaFile.publicFinalClass(serviceClientClassDeclaration, classBlock ->
        {
            if (settings.shouldClientLogger()) {
                classBlock.privateFinalMemberVariable(ClassType.ClientLogger.toString(), String.format("logger = new ClientLogger(%1$s.class)", serviceClient.getClassName()));
            }

            // Add proxy service member variable
            if (serviceClient.getProxy() != null) {
                classBlock.javadocComment(String.format("The proxy service used to perform REST calls."));
                classBlock.privateFinalMemberVariable(serviceClient.getProxy().getName(), "service");
            }

            // Add ServiceClient client property variables, getters, and setters
            for (ServiceClientProperty serviceClientProperty : serviceClient.getProperties()) {
                classBlock.javadocComment(comment ->
                {
                    comment.description(serviceClientProperty.getDescription());
                });
                classBlock.privateFinalMemberVariable(serviceClientProperty.getType().toString(), serviceClientProperty.getName());

                classBlock.javadocComment(comment ->
                {
                    comment.description(String.format("Gets %1$s", serviceClientProperty.getDescription()));
                    comment.methodReturns(String.format("the %1$s value.", serviceClientProperty.getName()));
                });
                classBlock.publicMethod(String.format("%1$s get%2$s()", serviceClientProperty.getType(), CodeNamer.toPascalCase(serviceClientProperty.getName())), function ->
                {
                    function.methodReturn(String.format("this.%1$s", serviceClientProperty.getName()));
                });

                /* if (!serviceClientProperty.isReadOnly()) {
                    classBlock.javadocComment(comment ->
                    {
                        comment.description(String.format("Sets %1$s", serviceClientProperty.getDescription()));
                        comment.param(serviceClientProperty.getName(), String.format("the %1$s value.", serviceClientProperty.getName()));
                        comment.methodReturns("the service client itself");
                    });

                    String methodSignature = String.format("%1$s set%2$s(%3$s %4$s)",
                        serviceClient.getClassName(), CodeNamer.toPascalCase(serviceClientProperty.getName()),
                        serviceClientProperty.getType(), serviceClientProperty.getName());

                    Consumer<JavaBlock> methodBody = function ->
                    {
                        function.line(String.format("this.%1$s = %2$s;", serviceClientProperty.getName(),
                            serviceClientProperty.getName()));
                        function.methodReturn("this");
                    };
                    classBlock.method(visibility, null, methodSignature, methodBody);
                } */
            }

            // AutoRestMethod Group Client declarations and getters
            for (MethodGroupClient methodGroupClient : serviceClient.getMethodGroupClients()) {
                classBlock.javadocComment(comment ->
                {
                    comment.description(String.format("The %1$s object to access its operations.", methodGroupClient.getVariableType()));
                });
                classBlock.privateFinalMemberVariable(methodGroupClient.getVariableType(), methodGroupClient.getVariableName());

                classBlock.javadocComment(comment ->
                {
                    comment.description(String.format("Gets the %1$s object to access its operations.", methodGroupClient.getVariableType()));
                    comment.methodReturns(String.format("the %1$s object.", methodGroupClient.getVariableType()));
                });
                classBlock.publicMethod(String.format("%1$s get%2$s()", methodGroupClient.getVariableType(),
                    CodeNamer.toPascalCase(methodGroupClient.getVariableName())), function ->
                {
                    function.methodReturn(String.format("this.%1$s", methodGroupClient.getVariableName()));
                });
            }

            // additional service client properties in constructor arguments
            String constructorArgs = serviceClient.getProperties().stream()
                    .filter(p -> !p.isReadOnly())
                    .map(ServiceClientProperty::getName)
                    .collect(Collectors.joining(", "));
            if (!constructorArgs.isEmpty()) {
                constructorArgs = ", " + constructorArgs;
            }
            final String constructorArgsFinal = constructorArgs;
            // code lines
            Consumer<JavaBlock> constructorParametersCodes = javaBlock -> {
                serviceClient.getProperties().stream()
                        .filter(p -> !p.isReadOnly()).forEach(p -> javaBlock.line(String.format("this.%1$s = %2$s;", p.getName(), p.getName())));
            };

            // Service Client Constructors
            //boolean serviceClientUsesCredentials = serviceClient.getConstructors().stream().anyMatch(constructor -> constructor.getParameters().contains(serviceClient.getTokenCredentialParameter()));
            for (Constructor constructor : serviceClient.getConstructors()) {
                classBlock.javadocComment(comment ->
                {
                    comment.description(String.format("Initializes an instance of %1$s client.", serviceClient.getInterfaceName()));
                    for (ClientMethodParameter parameter : constructor.getParameters()) {
                        comment.param(parameter.getName(), parameter.getDescription());
                    }
                });

                // service client properties in constructor parameters
                String constructorParams = Stream.concat(constructor.getParameters().stream().map(ClientMethodParameter::getDeclaration),
                        serviceClient.getProperties().stream()
                                .filter(p -> !p.isReadOnly())
                                .map(p -> String.format("%1$s %2$s", p.getType(), p.getName())))
                        .collect(Collectors.joining(", "));

                classBlock.constructor(visibility, String.format("%1$s(%2$s)", serviceClient.getClassName(), constructorParams), constructorBlock ->
                {
                    if (settings.isFluent()) {
                        if (constructor.getParameters().isEmpty()) {
                            constructorBlock.line(String.format("this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build(), %1$s%2$s);",
                                    serviceClient.getAzureEnvironmentParameter().getDefaultValue(), constructorArgsFinal));
                        } else if (constructor.getParameters().equals(Arrays.asList(serviceClient.getHttpPipelineParameter()))) {
                            constructorBlock.line(String.format("this(%1$s, %2$s%3$s);",
                                    serviceClient.getHttpPipelineParameter().getName(),
                                    serviceClient.getAzureEnvironmentParameter().getDefaultValue(),
                                    constructorArgsFinal));
                        } else if (constructor.getParameters().equals(Arrays.asList(serviceClient.getHttpPipelineParameter(), serviceClient.getAzureEnvironmentParameter()))) {
                            if (settings.isFluentPremium()) {
                                constructorBlock.line(String.format("super(%1$s, %2$s);", serviceClient.getHttpPipelineParameter().getName(), serviceClient.getAzureEnvironmentParameter().getName()));
                            }
                            constructorBlock.line(String.format("this.httpPipeline = httpPipeline;"));

                            constructorParametersCodes.accept(constructorBlock);

                            for (ServiceClientProperty serviceClientProperty : serviceClient.getProperties().stream().filter(ServiceClientProperty::isReadOnly).collect(Collectors.toList())) {
                                if (serviceClientProperty.getDefaultValueExpression() != null) {
                                    constructorBlock.line(String.format("this.%1$s = %2$s;", serviceClientProperty.getName(), serviceClientProperty.getDefaultValueExpression()));
                                }
                            }

                            for (MethodGroupClient methodGroupClient : serviceClient.getMethodGroupClients()) {
                                constructorBlock.line(String.format("this.%1$s = new %2$s(this);", methodGroupClient.getVariableName(), methodGroupClient.getClassName()));
                            }

                            if (serviceClient.getProxy() != null) {
                                constructorBlock.line(String.format("this.service = %1$s.create(%2$s.class, this.httpPipeline, this.getSerializerAdapter());", ClassType.RestProxy.getName(), serviceClient.getProxy().getName()));
                            }
                        }
                    } else {
                        if (constructor.getParameters().isEmpty()) {
                            constructorBlock.line("this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build()%1$s);", constructorArgsFinal);
                        } else if (constructor.getParameters().equals(Arrays.asList(serviceClient.getHttpPipelineParameter()))) {
                            constructorBlock.line(String.format("this.httpPipeline = httpPipeline;"));
                            constructorParametersCodes.accept(constructorBlock);

                            for (ServiceClientProperty serviceClientProperty : serviceClient.getProperties().stream().filter(ServiceClientProperty::isReadOnly).collect(Collectors.toList())) {
                                if (serviceClientProperty.getDefaultValueExpression() != null) {
                                    constructorBlock.line("this.%s = %s;", serviceClientProperty.getName(), serviceClientProperty.getDefaultValueExpression());
                                }
                            }

                            for (MethodGroupClient methodGroupClient : serviceClient.getMethodGroupClients()) {
                                constructorBlock.line("this.%s = new %s(this);", methodGroupClient.getVariableName(), methodGroupClient.getClassName());
                            }

                            if (serviceClient.getProxy() != null) {
                                constructorBlock.line("this.service = %s.create(%s.class, this.httpPipeline);", ClassType.RestProxy.getName(), serviceClient.getProxy().getName());
                            }
                        }
                    }
                });
            }

            Templates.getProxyTemplate().write(serviceClient.getProxy(), classBlock);

            for (ClientMethod clientMethod : serviceClient.getClientMethods()) {
                Templates.getClientMethodTemplate().write(clientMethod, classBlock);
            }

            additionalMethods.forEach(method -> method.writeMethod(classBlock));
        });
    }
}
