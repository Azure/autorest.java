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
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.util.CodeNamer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 Writes a ServiceClient to a JavaFile.
*/
public class ServiceClientTemplate implements IJavaTemplate<ServiceClient, JavaFile>
{
    private static ServiceClientTemplate _instance = new ServiceClientTemplate();
    public static ServiceClientTemplate getInstance()
    {
        return _instance;
    }

    private ServiceClientTemplate()
    {
    }

    public final void write(ServiceClient serviceClient, JavaFile javaFile)
    {
        JavaSettings settings = JavaSettings.getInstance();
        String serviceClientClassDeclaration = String.format("%1$s", serviceClient.getClassName());
        if (!settings.isFluent() && settings.shouldGenerateClientInterfaces())
        {
            serviceClientClassDeclaration += String.format(" implements %1$s", serviceClient.getInterfaceName());
        }

        Set<String> imports = new HashSet<String>();
        serviceClient.addImportsTo(imports, true, settings);
        javaFile.declareImport(imports);

        javaFile.javadocComment(comment ->
        {
                String serviceClientTypeName = settings.isFluent() ? serviceClient.getClassName() : serviceClient.getInterfaceName();
                comment.description(String.format("Initializes a new instance of the %1$s type.", serviceClientTypeName));
        });
        javaFile.publicFinalClass(serviceClientClassDeclaration, classBlock ->
            {
                // Add proxy service member variable
                if (serviceClient.getRestAPI() != null)
                {
                    classBlock.javadocComment(String.format("The proxy service used to perform REST calls."));
                    classBlock.privateMemberVariable(serviceClient.getRestAPI().getName(), "service");
                }

                // Add ServiceClient client property variables, getters, and setters
                for (ServiceClientProperty serviceClientProperty : serviceClient.getProperties())
                {
                    classBlock.javadocComment(comment ->
                    {
                        comment.description(serviceClientProperty.getDescription());
                    });
                    classBlock.privateMemberVariable(String.format("%1$s %2$s", serviceClientProperty.getType(), serviceClientProperty.getName()));

                    classBlock.javadocComment(comment ->
                    {
                        comment.description(String.format("Gets %1$s", serviceClientProperty.getDescription()));
                        comment.methodReturns(String.format("the %1$s value.", serviceClientProperty.getName()));
                    });
                    classBlock.publicMethod(String.format("%1$s get%2$s()", serviceClientProperty.getType(), CodeNamer.toPascalCase(serviceClientProperty.getName())), function ->
                    {
                        function.methodReturn(String.format("this.%1$s", serviceClientProperty.getName()));
                    });

                    if (!serviceClientProperty.getIsReadOnly())
                    {
                        classBlock.javadocComment(comment ->
                        {
                            comment.description(String.format("Sets %1$s", serviceClientProperty.getDescription()));
                            comment.param(serviceClientProperty.getName(), String.format("the %1$s value.", serviceClientProperty.getName()));
                            comment.methodReturns("the service client itself");
                        });
                        classBlock.packagePrivateMethod(String.format("%1$s set%2$s(%3$s %4$s)", serviceClient.getClassName(), CodeNamer.toPascalCase(serviceClientProperty.getName()), serviceClientProperty.getType(), serviceClientProperty.getName()), function ->
                        {
                            function.line(String.format("this.%1$s = %2$s;", serviceClientProperty.getName(), serviceClientProperty.getName()));
                            function.methodReturn("this");
                        });
                    }
                }

                // AutoRestMethod Group Client declarations and getters
                for (MethodGroupClient methodGroupClient : serviceClient.getMethodGroupClients())
                {
                    classBlock.javadocComment(comment ->
                    {
                        comment.description(String.format("The %1$s object to access its operations.", methodGroupClient.getVariableType()));
                    });
                    classBlock.privateMemberVariable(methodGroupClient.getVariableType(), methodGroupClient.getVariableName());

                    classBlock.javadocComment(comment ->
                    {
                        comment.description(String.format("Gets the %1$s object to access its operations.", methodGroupClient.getVariableType()));
                        comment.methodReturns(String.format("the %1$s object.", methodGroupClient.getVariableType()));
                    });
                    classBlock.publicMethod(String.format("%1$s %2$s()", methodGroupClient.getVariableType(), methodGroupClient.getVariableName()), function ->
                    {
                        function.methodReturn(String.format("this.%1$s", methodGroupClient.getVariableName()));
                    });
                }

                // Service Client Constructors
                boolean serviceClientUsesCredentials = serviceClient.getConstructors().stream().anyMatch(constructor -> constructor.getParameters().contains(serviceClient.getServiceClientCredentialsParameter()));
                for (Constructor constructor : serviceClient.getConstructors())
                {
                    classBlock.javadocComment(comment ->
                    {
                        comment.description(String.format("Initializes an instance of %1$s client.", serviceClient.getInterfaceName()));
                        for (ClientMethodParameter parameter : constructor.getParameters())
                        {
                            comment.param(parameter.getName(), parameter.getDescription());
                        }
                    });

                    classBlock.publicConstructor(String.format("%1$s(%2$s)", serviceClient.getClassName(), constructor.getParameters().stream().map(ClientMethodParameter::getDeclaration).collect(Collectors.joining(", "))), constructorBlock ->
                    {
                        if (settings.isAzureOrFluent())
                        {
                            if (constructor.getParameters().equals(Arrays.asList(serviceClient.getServiceClientCredentialsParameter())))
                            {
                                constructorBlock.line(String.format("this(%1$s.createDefaultPipeline(%2$s.class, %3$s));", ClassType.AzureProxy.getName(), serviceClient.getClassName(), serviceClient.getServiceClientCredentialsParameter().getName()));
                            }
                            else if (constructor.getParameters().equals(Arrays.asList(serviceClient.getServiceClientCredentialsParameter(), serviceClient.getAzureEnvironmentParameter())))
                            {
                                constructorBlock.line(String.format("this(%1$s.createDefaultPipeline(%2$s.class, %3$s), %4$s);", ClassType.AzureProxy.getName(), serviceClient.getClassName(), serviceClient.getServiceClientCredentialsParameter().getName(), serviceClient.getAzureEnvironmentParameter().getName()));
                            }
                            else if (constructor.getParameters().isEmpty())
                            {
                                constructorBlock.line(String.format("this(%1$s.createDefaultPipeline(%2$s.class));", ClassType.AzureProxy.getName(), serviceClient.getClassName()));
                            }
                            else if (constructor.getParameters().equals(Arrays.asList(serviceClient.getAzureEnvironmentParameter())))
                            {
                                constructorBlock.line(String.format("this(%1$s.createDefaultPipeline(%2$s.class), %3$s);", ClassType.AzureProxy.getName(), serviceClient.getClassName(), serviceClient.getAzureEnvironmentParameter().getName()));
                            }
                            else if (constructor.getParameters().equals(Arrays.asList(serviceClient.getHttpPipelineParameter())))
                            {
                                constructorBlock.line(String.format("this(%1$s, null);", serviceClient.getHttpPipelineParameter().getName()));
                            }
                            else if (constructor.getParameters().equals(Arrays.asList(serviceClient.getHttpPipelineParameter(), serviceClient.getAzureEnvironmentParameter())))
                            {
                                constructorBlock.line(String.format("super(%1$s, %2$s);", serviceClient.getHttpPipelineParameter().getName(), serviceClient.getAzureEnvironmentParameter().getName()));

                                for (ServiceClientProperty serviceClientProperty : serviceClient.getProperties().stream().filter(ServiceClientProperty::getIsReadOnly).collect(Collectors.toList()))
                                {
                                    if (serviceClientProperty.getDefaultValueExpression() != null)
                                    {
                                        constructorBlock.line(String.format("this.%1$s = %2$s;", serviceClientProperty.getName(), serviceClientProperty.getDefaultValueExpression()));
                                    }
                                }

                                for (MethodGroupClient methodGroupClient : serviceClient.getMethodGroupClients())
                                {
                                    constructorBlock.line(String.format("this.%1$s = new %2$s(this);", methodGroupClient.getVariableName(), methodGroupClient.getClassName()));
                                }

                                if (serviceClient.getRestAPI() != null)
                                {
                                    constructorBlock.line(String.format("this.service = %1$s.create(%2$s.class, httpPipeline);", ClassType.AzureProxy.getName(), serviceClient.getRestAPI().getName()));
                                }
                            }
                        }
                        else
                        {
                            if (constructor.getParameters().isEmpty())
                            {
                                constructorBlock.line(String.format("this(%1$s.createDefaultPipeline());", ClassType.RestProxy.getName()));
                            }
                            else if (constructor.getParameters().equals(Arrays.asList(serviceClient.getHttpPipelineParameter())))
                            {
                                for (ServiceClientProperty serviceClientProperty : serviceClient.getProperties().stream().filter(ServiceClientProperty::getIsReadOnly).collect(Collectors.toList()))
                                {
                                    constructorBlock.line(String.format("this.httpPipeline = httpPipeline;"));
                                    if (serviceClientProperty.getDefaultValueExpression() != null)
                                    {
                                        constructorBlock.line("this.{serviceClientProperty.Name} = {serviceClientProperty.DefaultValueExpression};");
                                    }
                                }

                                for (MethodGroupClient methodGroupClient : serviceClient.getMethodGroupClients())
                                {
                                    constructorBlock.line("this.{methodGroupClient.VariableName} = new {methodGroupClient.ClassName}(this);");
                                }

                                if (serviceClient.getRestAPI() != null)
                                {
                                    constructorBlock.line("this.service = {ClassType.RestProxy.Name}.create({serviceClient.RestAPI.Name}.class, this.httpPipeline);");
                                }
                            }
                        }
                    });
                }

                Templates.getProxyTemplate().write(serviceClient.getRestAPI(), classBlock);

                for (ClientMethod clientMethod : serviceClient.getClientMethods())
                {
                    Templates.getClientMethodTemplate().write(clientMethod, classBlock);
                }
            });
    }
}