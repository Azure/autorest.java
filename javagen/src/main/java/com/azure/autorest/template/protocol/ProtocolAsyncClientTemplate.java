package com.azure.autorest.template.protocol;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.clientmodel.ServiceClientProperty;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.template.IJavaTemplate;
import com.azure.autorest.template.Templates;
import com.azure.autorest.util.ClientModelUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Template to create a synchronous client.
 */
public class ProtocolAsyncClientTemplate implements IJavaTemplate<AsyncSyncClient, JavaFile>  {

    private static ProtocolAsyncClientTemplate _instance = new ProtocolAsyncClientTemplate();
    private ProtocolAsyncClientTemplate() {
    }

    public static ProtocolAsyncClientTemplate getInstance() {
      return _instance;
    }

    @Override
    public final void write(AsyncSyncClient client, JavaFile javaFile) {
        Set<String> imports = new HashSet<>();
        client.getServiceClient().addImportsTo(imports, true, false, JavaSettings.getInstance());
        if (client.getMethodGroupClient() != null) {
            client.getMethodGroupClient().addImportsTo(imports, true, JavaSettings.getInstance());
            imports.add(client.getMethodGroupClient().getPackage() + "." + client.getMethodGroupClient().getClassName());
        }
        imports.add("com.azure.core.annotation.ServiceClient");
        imports.add("reactor.core.publisher.Mono");

        javaFile.declareImport(imports);
        javaFile.javadocComment(comment ->
            comment.description(String.format("Initializes a new instance of the %1$s type.",
                client.getClassName())));

        javaFile.annotation(String.format("ServiceClient(builder = %s.class)", client.getServiceClient().getInterfaceName() + ClientModelUtil.getBuilderSuffix()));
        javaFile.publicFinalClass(client.getClassName(), classBlock -> {

            for (ServiceClientProperty property : client.getServiceClient().getProperties()) {
                classBlock.privateFinalMemberVariable(property.getType().toString(), property.getName());
            }

            // Service Client Constructor
            classBlock.javadocComment(comment -> {
                comment.description(String.format("Initializes an instance of %1$s client.", client.getClassName()));
                    for (ServiceClientProperty property : client.getServiceClient().getProperties()) {
                        comment.param(property.getName(), property.getDescription());
                    }
            });

            String constructorArgs = client.getServiceClient().getProperties().stream()
                    .map(p -> p.getType() + " " + p.getName())
                    .collect(Collectors.joining(", "));

            classBlock.constructor(JavaVisibility.PackagePrivate,
                    client.getClassName() + "(" + constructorArgs + ")",
                    constructor -> {
                for (ServiceClientProperty property : client.getServiceClient().getProperties()) {
                    constructor.line("this.%1$s = %1$s;", property.getName());
                }
            });

            List<ClientMethod> methods;
            if (client.getMethodGroupClient() != null) {
                methods = client.getMethodGroupClient().getClientMethods();
            } else {
                methods = client.getServiceClient().getClientMethods();
            }
            methods.stream().filter(m -> m.getType() == ClientMethodType.SimpleAsyncRestResponse).forEach(method -> {
                Templates.getProtocolAsyncMethodTemplate().write(method, classBlock);
            });

            // invoke() method
            String invokeMethodArgs = "String url, HttpMethod httpMethod, byte[] body, RequestOptions options";
            JavaVisibility visibility;
            if (!JavaSettings.getInstance().isContextClientMethodParameter()) {
                visibility = JavaVisibility.Public;
            } else {
                visibility = JavaVisibility.PackagePrivate;

                // actual public invoke() without context param
                classBlock.publicMethod(String.format("Mono<HttpResponse> invoke(%s)", invokeMethodArgs), method -> {
                    method.methodReturn(String.format("invoke(%s)", "url, httpMethod, body, options, context"));
                });
                invokeMethodArgs = invokeMethodArgs + ", Context context";
            }
            classBlock.javadocComment(comment -> {
                comment.description("Create an empty DynamicRequest with the serializer and pipeline initialized for this client.");
                comment.methodReturns("a DynamicRequest where customizations can be made before sent to the service.");
            });
            classBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
            classBlock.method(visibility, null, String.format("Mono<Response<BinaryData>> invoke(%s)", invokeMethodArgs), methodBlock -> {
                methodBlock.line("HttpRequest request = new HttpRequest(HttpMethod.GET, url);");
                methodBlock.ifBlock("options != null", ifBlock -> {
                    methodBlock.line("options.getRequestCallback().accept(request);");
                });
                if (JavaSettings.getInstance().isContextClientMethodParameter()) {
                    methodBlock.line("return httpPipeline.send(request, context)");
                } else if (JavaSettings.getInstance().getAddContextParameter()) {
                    methodBlock.line("return FluxUtil.withContext(c -> httpPipeline.send(request, c))");
                } else {
                    methodBlock.line("return httpPipeline.send(request)");
                }
                methodBlock.increaseIndent();
                methodBlock.line(".flatMap(httpResponse -> BinaryData.fromFlux(httpResponse.getBody())");
                methodBlock.line(".map(binaryData -> new SimpleResponse<>(");
                methodBlock.increaseIndent();
                methodBlock.line("httpResponse.getRequest(),");
                methodBlock.line("httpResponse.getStatusCode(),");
                methodBlock.line("httpResponse.getHeaders(),");
                methodBlock.line("binaryData)));");
                methodBlock.decreaseIndent();
                methodBlock.decreaseIndent();
            });

            if (methods.stream().flatMap(m -> m.getProxyMethod().getParameters().stream())
                    .filter(ProxyMethodParameter::getIsRequired)
                    .filter(p -> p.getRequestParameterLocation() == RequestParameterLocation.Query
                            || p.getRequestParameterLocation() == RequestParameterLocation.Path
                            || p.getRequestParameterLocation() == RequestParameterLocation.Header)
                    .anyMatch(p -> p.getClientType() instanceof ListType)) {
                classBlock.privateMethod("String serializeIterable(Iterable<?> iterable, CollectionFormat format)", methodBlock -> {
                    methodBlock.ifBlock("iterable == null", ifBlock -> {
                        ifBlock.methodReturn("null");
                    });
                    methodBlock.line("return StreamSupport.stream(iterable.spliterator(), false)");
                    methodBlock.increaseIndent();
                    methodBlock.line(".map(item -> item == null ? \"\" : new String(serializer.serializeToBytes(item), StandardCharsets.UTF_8))");
                    methodBlock.line(".map(serialized -> serialized.replace(\"^\\\"*|\\\"*$\", \"\"))");
                    methodBlock.line(".collect(Collectors.joining(format.getDelimiter()));");
                });
            }
        });
    }
}
