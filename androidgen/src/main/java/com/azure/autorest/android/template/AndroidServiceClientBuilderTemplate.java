package com.azure.autorest.android.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.Constructor;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.model.javamodel.JavaClass;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.template.ServiceClientBuilderTemplate;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.CodeNamer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AndroidServiceClientBuilderTemplate extends ServiceClientBuilderTemplate {
    private static AndroidServiceClientBuilderTemplate _instance = new AndroidServiceClientBuilderTemplate();

    protected AndroidServiceClientBuilderTemplate() {
    }

    public static ServiceClientBuilderTemplate getInstance() {
        return _instance;
    }

    @Override
    public void write(ServiceClient serviceClient, JavaFile javaFile) {
        JavaSettings settings = JavaSettings.getInstance();
        String serviceClientBuilderName = serviceClient.getInterfaceName() + ClientModelUtil.getBuilderSuffix();

        final Constructor serviceClientCtr = serviceClient.getConstructors().get(0); // AndroidServiceClient has only single Ctr.
        List<ClientMethodParameter> commonProperties = commonPropertiesFromCtr(serviceClientCtr);
        commonProperties.add(credentialInterceptorParameter());

        Set<String> imports = new HashSet<String>();
        serviceClient.addImportsTo(imports, false, true, settings);
        commonProperties.stream().forEach(p -> p.addImportsTo(imports, false));
        fixImportForBuilderParameters(commonProperties, imports);

        String buildReturnType;
        if (settings.shouldGenerateClientInterfaces()) {
            buildReturnType = serviceClient.getInterfaceName();
        } else {
            buildReturnType = serviceClient.getClassName();
        }

        List<AsyncSyncClient> asyncClients = new ArrayList<>();
        List<AsyncSyncClient> syncClients = new ArrayList<>();
        if (JavaSettings.getInstance().shouldGenerateSyncAsyncClients()) {
            ClientModelUtil.getAsyncSyncClients(serviceClient, asyncClients, syncClients);
        }
        final boolean singleBuilder = asyncClients.size() == 1;

        StringBuilder builderTypes = new StringBuilder();
        builderTypes.append("{");
        if (JavaSettings.getInstance().shouldGenerateSyncAsyncClients()) {
            List<AsyncSyncClient> clients = new ArrayList<>(asyncClients);
            clients.addAll(syncClients);
            boolean first = true;
            for (AsyncSyncClient client : clients) {
                if (first) {
                    first = false;
                } else {
                    builderTypes.append(", ");
                }
                builderTypes.append(client.getClassName()).append(".class");
            }
        } else {
            builderTypes.append(serviceClient.getClassName()).append(".class");
        }
        builderTypes.append("}");
        javaFile.declareImport(imports);

        javaFile.javadocComment(comment ->
        {
            comment.description(String.format("A builder for creating a new instance of the %1$s type.", serviceClient.getInterfaceName()));
        });

        javaFile.publicFinalClass(serviceClientBuilderName, classBlock ->
        {
            // Add class level variable and setter for all ServiceClient client properties.
            serviceClient.getProperties()
                    .stream()
                    .filter(p -> !p.isReadOnly())
                    .forEach(p -> {
                        writeBuilderProperty(settings,
                                classBlock,
                                serviceClientBuilderName,
                                p.getDescription(),
                                p.getName(),
                                p.getType());
                    });

            // Add class level variables and setters for all common properties.
            // e.g. ServiceClient.Builder, Credential Interceptor
            commonProperties.stream()
                    .forEach(p -> {
                        writeBuilderProperty(settings,
                                classBlock,
                                serviceClientBuilderName,
                                p.getDescription(),
                                p.getName(),
                                p.getWireType());
                    });

            String buildMethodName = this.primaryBuildMethodName(settings);
            JavaVisibility visibility
                    = settings.shouldGenerateSyncAsyncClients()
                    ? JavaVisibility.Private
                    : JavaVisibility.Public;

            // build method
            classBlock.javadocComment(comment ->
            {
                comment.description(String.format("Builds an instance of %1$s with the provided parameters", buildReturnType));
                comment.methodReturns(String.format("an instance of %1$s", buildReturnType));
            });
            classBlock.method(visibility, null, String.format("%1$s %2$s()", buildReturnType, buildMethodName), function ->
            {
                final List<String> constructorArgsSet1 = new ArrayList<>();
                serviceClient.getProperties()
                        .stream()
                        .filter(p -> !p.isReadOnly())
                        .forEach(p -> {
                            // 1. Collect ServiceClient Ctr args.
                            constructorArgsSet1.add(p.getName());
                            // 2. Set default value for ServiceClient properties whose builder setters are not called by the app.
                            if (p.getDefaultValueExpression() != null) {
                                function.ifBlock(String.format("%1$s == null", p.getName()), ifBlock ->
                                {
                                    function.line("this.%1$s = %2$s;", p.getName(), p.getDefaultValueExpression());
                                });
                            }
                        });

                final List<String> constructorArgsSet2 = new ArrayList<>();
                commonProperties.stream()
                        .filter(p -> p.getWireType() != ClassType.AndroidOkHttpInterceptor)
                        .forEach(p -> {
                            // 1. Collect ServiceClient Ctr args.
                            if (isBuilderType(p)) {
                                constructorArgsSet2.add(p.getName() + ".build()");
                            } else {
                                constructorArgsSet2.add(p.getName());
                            }
                            // 2. Set-up/Init common properties and
                            //    Set default value for common properties whose builder setters are not called by the app.
                            if (p.getWireType() == ClassType.AndroidRestClientBuilder) {
                                setupRestClientBuilder(function, serviceClient, commonProperties, p);
                            } else {
                                if (p.getDefaultValue() != null) {
                                    function.ifBlock(String.format("%1$s == null", p.getName()), ifBlock ->
                                    {
                                        function.line("this.%1$s = %2$s;", p.getName(), p.getDefaultValue());
                                    });
                                }
                            }
                        });

                String constructorArgsStr = Stream
                        .concat(constructorArgsSet2.stream(), constructorArgsSet1.stream())
                        .collect(Collectors.joining(", "));

                function.line(String.format("%1$s client = new %2$s(%3$s);", serviceClient.getClassName(),
                        serviceClient.getClassName(),
                        constructorArgsStr));
                function.line("return client;");
            });

            if (JavaSettings.getInstance().shouldGenerateSyncAsyncClients()) {
                for (AsyncSyncClient asyncClient : asyncClients) {
                    final boolean wrapServiceClient = asyncClient.getMethodGroupClient() == null;

                    classBlock.javadocComment(comment ->
                    {
                        comment.description(String
                                .format("Builds an instance of %1$s async client", asyncClient.getClassName()));
                        comment.methodReturns(String.format("an instance of %1$s", asyncClient.getClassName()));
                    });
                    classBlock.publicMethod(String.format("%1$s build%2$s()", asyncClient.getClassName(), singleBuilder ? "AsyncClient" : asyncClient.getClassName()),
                            function -> {
                                if (wrapServiceClient) {
                                    function.line("return new %1$s(%2$s());", asyncClient.getClassName(), buildMethodName);
                                } else {
                                    function.line("return new %1$s(%2$s().get%3$s());", asyncClient.getClassName(), buildMethodName,
                                            CodeNamer.toPascalCase(asyncClient.getMethodGroupClient().getVariableName()));
                                }
                            });
                }

                for (AsyncSyncClient syncClient : syncClients) {
                    final boolean wrapServiceClient = syncClient.getMethodGroupClient() == null;

                    classBlock.javadocComment(comment ->
                    {
                        comment.description(String
                                .format("Builds an instance of %1$s sync client", syncClient.getClassName()));
                        comment.methodReturns(String.format("an instance of %1$s", syncClient.getClassName()));
                    });
                    classBlock.publicMethod(String.format("%1$s build%2$s()", syncClient.getClassName(), singleBuilder ? "Client" : syncClient.getClassName()),
                            function -> {
                                if (wrapServiceClient) {
                                    function.line("return new %1$s(%2$s());", syncClient.getClassName(), buildMethodName);
                                } else {
                                    function.line("return new %1$s(%2$s().get%3$s());", syncClient.getClassName(), buildMethodName,
                                            CodeNamer.toPascalCase(syncClient.getMethodGroupClient().getVariableName()));
                                }
                            });
                }
            }
        });

    }

    /**
     * Extension for the name of build method.
     *
     * @return The name of build method.
     */
    protected String primaryBuildMethodName(JavaSettings settings) {
        return settings.shouldGenerateSyncAsyncClients()
                ? "buildInnerClient"
                : "buildClient";
    }

    private List<ClientMethodParameter> commonPropertiesFromCtr(final Constructor constructor) {
        return constructor.getParameters()
                .stream()
                .map(p -> {
                    if (p.getWireType() == ClassType.AndroidRestClient) {
                        return new ClientMethodParameter.Builder()
                                .description("The Azure Core generic ServiceClient Builder.")
                                .isFinal(false)
                                .wireType(ClassType.AndroidRestClientBuilder)
                                .name("serviceClientBuilder")
                                .isRequired(true)
                                .isConstant(false)
                                .fromClient(true)
                                .annotations(new ArrayList<>())
                                .defaultValue("new ServiceClient.Builder()")
                                .build();
                    } else {
                        return p;
                    }
                }).collect(Collectors.toList());
    }

    private ClientMethodParameter credentialInterceptorParameter() {
        return new ClientMethodParameter.Builder()
                .description("The Interceptor to set intercept request and set credentials.")
                .isFinal(false)
                .wireType(ClassType.AndroidOkHttpInterceptor)
                .name("credentialInterceptor")
                .isRequired(true)
                .isConstant(false)
                .fromClient(true)
                .annotations(new ArrayList<>())
                .build();
    }

    private static void writeBuilderProperty(JavaSettings settings,
                                      JavaClass classBlock,
                                      String builderName,
                                      String propDescription,
                                      String propName,
                                      IType propType) {
        classBlock.blockComment(settings.getMaximumJavadocCommentWidth(), comment ->
        {
            comment.line(propDescription);
        });
        classBlock.privateMemberVariable(String.format("%1$s %2$s", propType, propName));

        classBlock.javadocComment(comment ->
        {
            comment.description(String.format("Sets %1$s", propDescription));
            comment.param(propName, String.format("the %1$s value.", propName));
            comment.methodReturns(String.format("the %1$s", builderName));
        });
        classBlock.publicMethod(String.format("%1$s %2$s(%3$s %4$s)",
                builderName,
                CodeNamer.toCamelCase(propName),
                propType,
                propName), function ->
        {
            function.line(String.format("this.%1$s = %2$s;", propName, propName));
            function.methodReturn("this");
        });
    }

    private void fixImportForBuilderParameters(List<ClientMethodParameter> parameters, Set<String> imports) {
        parameters.stream()
                .forEach(p -> {
                    fixImportIfBuilderParameter(p, imports);
                });
    }

    private void fixImportIfBuilderParameter(ClientMethodParameter parameter, Set<String> imports) {
        if (isBuilderType(parameter)) {
            ClassType builderClsType = ((ClassType) parameter.getWireType());
            final String builderFullName = builderClsType.getFullName();
            if (imports.remove(builderFullName)) {
                final int idx = builderFullName.length() - ".Builder".length();
                imports.add(builderFullName.substring(0, idx));
            }
        }
    }

    private boolean isBuilderType(ClientMethodParameter parameter) {
        if (!(parameter.getWireType() instanceof ClassType)) {
            return false;
        }
        ClassType builderClsType = ((ClassType) parameter.getWireType());
        return builderClsType.getName().endsWith(".Builder");
    }

    private void setupRestClientBuilder(JavaBlock function,
                                        ServiceClient serviceClient,
                                        List<ClientMethodParameter> commonProperties,
                                        ClientMethodParameter restClientBuilder) {
        final HostMapping hostMapping = HostMapping.create(serviceClient);
        function.ifBlock(String.format("%1$s == null", restClientBuilder.getName()), ifBlock ->
        {
            String anyHostParamAbsentExpression = hostMapping.anyHostParamAbsentExpression();
            if (!anyHostParamAbsentExpression.isEmpty()) {
                function.ifBlock(anyHostParamAbsentExpression, illegalArgBlock ->
                {
                    illegalArgBlock.line("throw new IllegalArgumentException(\"Missing required parameters '%s'.\");",
                            String.join(", ", hostMapping.getHostParams()));
                });
            }
            ifBlock.line("this.%1$s = %2$s;", restClientBuilder.getName(), restClientBuilder.getDefaultValue());
        });
        String allHostParamPresentExpression = hostMapping.allHostParamPresentExpression();
        if (!allHostParamPresentExpression.isEmpty()) {
            function.ifBlock(allHostParamPresentExpression, ifBlock ->
            {
                ifBlock.line("final String retrofitBaseUrl = %s", hostMapping.getBaseUrlExpression());
                ifBlock.line("%s.setBaseUrl(retrofitBaseUrl);", restClientBuilder.getName());
            });
        }

        Optional<ClientMethodParameter> credInterceptorOpt = commonProperties
                .stream()
                .filter(p -> p.getWireType() == ClassType.AndroidOkHttpInterceptor)
                .findFirst();

        if (credInterceptorOpt.isPresent()) {
            ClientMethodParameter credInterceptor = credInterceptorOpt.get();
            function.ifBlock(String.format("%s != null", credInterceptor.getName()), ifBlock -> {
                ifBlock.line("%s.setCredentialsInterceptor(%s);", restClientBuilder.getName(), credInterceptor.getName());
            });
        }
    }
}
