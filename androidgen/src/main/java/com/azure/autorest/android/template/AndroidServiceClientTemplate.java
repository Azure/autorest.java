//====================================================================================================
//The Free Edition of C# to Java Converter limits conversion output to 100 lines per snippet.

//To subscribe to the Premium Edition, visit our website:
//https://www.tangiblesoftwaresolutions.com/order/order-csharp-to-java.html
//====================================================================================================

package com.azure.autorest.android.template;

import com.azure.autorest.android.model.HostMapping;
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
import com.azure.autorest.model.javamodel.JavaModifier;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.template.ServiceClientTemplate;
import com.azure.autorest.template.Templates;
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
public class AndroidServiceClientTemplate extends ServiceClientTemplate {
    private static AndroidServiceClientTemplate _instance = new AndroidServiceClientTemplate();

    protected AndroidServiceClientTemplate() {
    }

    public static AndroidServiceClientTemplate getInstance() {
        return _instance;
    }

    public void write(ServiceClient serviceClient, JavaFile javaFile) {
        JavaSettings settings = JavaSettings.getInstance();
        String serviceClientClassDeclaration = String.format("%1$s", serviceClient.getClassName());
        if (settings.shouldGenerateClientInterfaces()) {
            serviceClientClassDeclaration += String.format(" implements %1$s", serviceClient.getInterfaceName());
        }

        Set<String> imports = new HashSet<String>();

        serviceClient.addImportsTo(imports, true, false, settings);
        imports.add("com.azure.android.core.internal.util.serializer.SerializerAdapter");
        imports.add("com.azure.android.core.internal.util.serializer.SerializerFormat");

        javaFile.declareImport(imports);

        final JavaVisibility visibility = serviceClient.getPackage()
            .equals(ClientModelUtil.getServiceClientBuilderPackageName(serviceClient))
            ? JavaVisibility.PackagePrivate
            : JavaVisibility.Public;

        javaFile.javadocComment(comment -> {
            comment.description(String.format("Initializes a new instance of the %1$s type.", serviceClient.getInterfaceName()));
        });
        javaFile.publicFinalClass(serviceClientClassDeclaration, classBlock ->
        {
            if (settings.shouldClientLogger()) {
                classBlock.privateFinalMemberVariable(ClassType.ClientLogger.toString(), String.format("logger = new ClientLogger(%1$s.class)", serviceClient.getClassName()));
            }

            // Add proxy service member variable
            if (serviceClient.getProxy() != null) {
                classBlock.javadocComment("The proxy service used to perform REST calls.");
                classBlock.privateFinalMemberVariable(serviceClient.getProxy().getName(), "service");
            }

            // Serializer
            classBlock.javadocComment("The serializer.");
            classBlock.packagePrivateFinalVariable("SerializerAdapter serializerAdapter = SerializerAdapter.createDefault()");

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
//                    HostMapping hostMapping = HostMapping.create(serviceClient);
//                    constructorBlock.line("String foo = " + hostMapping.getEndpointExpression());
                    if (constructor.getParameters().equals(Arrays.asList(serviceClient.getHttpPipelineParameter()))) {
                        constructorBlock.line(String.format("this.serviceClient = serviceClient;"));
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
                            constructorBlock.line("this.service = serviceClient.getRetrofit().create(%s.class);", serviceClient.getProxy().getName());
                        }
                    }
                });
            }

            Templates.getProxyTemplate().write(serviceClient.getProxy(), classBlock);

            for (ClientMethod clientMethod : serviceClient.getClientMethods()) {
                Templates.getClientMethodTemplate().write(clientMethod, classBlock);
            }

            // Add util methods (will be moved to core-utils)
            //
            List<JavaModifier> modifiers = new ArrayList<>();

            classBlock.method(JavaVisibility.PackagePrivate,
                    modifiers,
                    "String readAsString(okhttp3.ResponseBody body)",
                    methodBodyBlock -> {
                        methodBodyBlock
                                .ifBlock("body == null",
                                        handleNullBlock -> handleNullBlock.methodReturn("\"\""));
                        List<String> exceptions = new ArrayList<>();
                        exceptions.add("java.io.IOException");
                        methodBodyBlock.tryCatch(
                                tryBlock -> tryBlock.methodReturn("new String(body.bytes())"),
                                exceptions,
                                "ex",
                                catchBlock -> catchBlock.line("throw new RuntimeException(ex);"),
                                finallyBlock -> finallyBlock.line("body.close();"));
                    });

            classBlock.method(JavaVisibility.PackagePrivate,
                    modifiers,
                    "<T> T deserializeContent(okhttp3.Headers headers, okhttp3.ResponseBody body, java.lang.reflect.Type type)",
                    methodBodyBlock -> {
                        methodBodyBlock.line("final String str = readAsString(body);");
                        List<String> exceptions = new ArrayList<>();
                        exceptions.add("java.io.IOException");
                        methodBodyBlock.tryCatch(
                                tryBlock -> {
                                    tryBlock.line("final String mimeContentType = headers.get(CONTENT_TYPE);");
                                    tryBlock.methodReturn("this.serializerAdapter.deserialize(str, type, resolveSerializerFormat(mimeContentType))");
                                },
                                exceptions,
                                "ex",
                                catchBlock -> catchBlock.line("throw new RuntimeException(ex);"),
                                null);
                    });

            classBlock.method(JavaVisibility.PackagePrivate,
                    modifiers,
                    "<T> T deserializeHeaders(okhttp3.Headers headers, java.lang.reflect.Type type)",
                    methodBodyBlock -> {
                        List<String> exceptions = new ArrayList<>();
                        exceptions.add("java.io.IOException");
                        methodBodyBlock.tryCatch(
                                tryBlock -> tryBlock.methodReturn("this.serializerAdapter.deserialize(headers, type)"),
                                exceptions,
                                "ex",
                                catchBlock -> catchBlock.line("throw new RuntimeException(ex);"),
                                null);
                    });


            classBlock.method(JavaVisibility.PackagePrivate,
                    modifiers,
                    "<T> retrofit2.Response<T> executeRetrofitCall(retrofit2.Call<T> call)",
                    methodBodyBlock -> {
                        List<String> exceptions = new ArrayList<>();
                        exceptions.add("java.io.IOException");
                        methodBodyBlock.tryCatch(
                                tryBlock -> tryBlock.methodReturn("call.execute()"),
                                exceptions,
                                "ex",
                                catchBlock -> catchBlock.line("throw new RuntimeException(ex);"),
                                null);
                    });

            classBlock.method(JavaVisibility.PackagePrivate,
                    modifiers,
                    "java.lang.reflect.ParameterizedType createParameterizedType(Class<?> rawClass, java.lang.reflect.Type... genericTypes)",
                    methodBodyBlock -> {
                        methodBodyBlock.line("return new java.lang.reflect.ParameterizedType() {");
                        methodBodyBlock.increaseIndent();
                        //
                        methodBodyBlock.line("@Override");
                        methodBodyBlock.line("public java.lang.reflect.Type[] getActualTypeArguments() {");
                        methodBodyBlock.increaseIndent();
                        methodBodyBlock.line("return genericTypes;");
                        methodBodyBlock.decreaseIndent();
                        methodBodyBlock.line("}");

                        methodBodyBlock.line("@Override");
                        methodBodyBlock.line("public java.lang.reflect.Type getRawType() {");
                        methodBodyBlock.increaseIndent();
                        methodBodyBlock.line("return rawClass;");
                        methodBodyBlock.decreaseIndent();
                        methodBodyBlock.line("}");

                        methodBodyBlock.line("@Override");
                        methodBodyBlock.line("public java.lang.reflect.Type getOwnerType() {");
                        methodBodyBlock.increaseIndent();
                        methodBodyBlock.line("return null;");
                        methodBodyBlock.decreaseIndent();
                        methodBodyBlock.line("}");
                        //
                        methodBodyBlock.decreaseIndent();
                        methodBodyBlock.line("};");
                    });

            classBlock.privateStaticFinalVariable("String CONTENT_TYPE = \"Content-Type\"");
            classBlock.privateStaticFinalVariable("java.util.Map<String, SerializerFormat> SUPPORTED_MIME_TYPES = new java.util.TreeMap<>(String.CASE_INSENSITIVE_ORDER)");
            classBlock.privateStaticFinalVariable("java.util.TreeMap<String, SerializerFormat> SUPPORTED_SUFFIXES = new java.util.TreeMap<>(String.CASE_INSENSITIVE_ORDER)");
            classBlock.privateStaticFinalVariable("SerializerFormat DEFAULT_ENCODING = SerializerFormat.JSON");

            classBlock.staticBlock(staticBlock -> {

                staticBlock.line("SUPPORTED_MIME_TYPES.put(\"text/xml\", SerializerFormat.XML);");
                staticBlock.line("SUPPORTED_MIME_TYPES.put(\"application/xml\", SerializerFormat.XML);");
                staticBlock.line("SUPPORTED_MIME_TYPES.put(\"application/json\", SerializerFormat.JSON);");
                staticBlock.line("SUPPORTED_SUFFIXES.put(\"xml\", SerializerFormat.XML);");
                staticBlock.line("SUPPORTED_SUFFIXES.put(\"json\", SerializerFormat.JSON);");
            });

            classBlock.method(JavaVisibility.PackagePrivate,
                    modifiers,
                    "SerializerFormat resolveSerializerFormat(String mimeContentType)",
                    methodBlock -> {
                methodBlock.ifBlock("mimeContentType == null || mimeContentType.isEmpty()", noMimeBlock -> {
                    noMimeBlock.methodReturn("DEFAULT_ENCODING");
                });

                methodBlock.line("final String[] parts = mimeContentType.split(\";\");");
                methodBlock.line("final SerializerFormat encoding = SUPPORTED_MIME_TYPES.get(parts[0]);");
                methodBlock.ifBlock("encoding != null", nonNullEncodingBlock -> {
                    nonNullEncodingBlock.methodReturn("encoding");
                });

                methodBlock.line("final String[] mimeTypeParts = parts[0].split(\"/\");");
                methodBlock.ifBlock("mimeTypeParts.length != 2", noMimePartBlock -> {
                    noMimePartBlock.methodReturn("DEFAULT_ENCODING");
                });

                methodBlock.line("final String subtype = mimeTypeParts[1];");
                methodBlock.line("final int lastIndex = subtype.lastIndexOf(\"+\");");
                methodBlock.ifBlock("lastIndex == -1", noSubTypeBlock -> {
                    noSubTypeBlock.methodReturn("DEFAULT_ENCODING");
                });

                methodBlock.line("final String mimeTypeSuffix = subtype.substring(lastIndex + 1);");
                methodBlock.line("final SerializerFormat serializerEncoding = SUPPORTED_SUFFIXES.get(mimeTypeSuffix);");
                methodBlock.ifBlock("serializerEncoding != null", nonNullEncodingBlock -> {
                    nonNullEncodingBlock.methodReturn("serializerEncoding");
                });

                methodBlock.methodReturn("DEFAULT_ENCODING");
            });
        });
    }
}
