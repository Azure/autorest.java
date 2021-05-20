package com.azure.autorest.template.protocol;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.javamodel.JavaFile;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.template.IJavaTemplate;
import com.azure.autorest.template.Templates;
import com.azure.autorest.util.ClientModelUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Template to create a synchronous client.
 */
public class ProtocolSyncClientTemplate implements IJavaTemplate<AsyncSyncClient, JavaFile>  {

    private static ProtocolSyncClientTemplate _instance = new ProtocolSyncClientTemplate();
    private ProtocolSyncClientTemplate() {
    }

    public static ProtocolSyncClientTemplate getInstance() {
      return _instance;
    }

    @Override
    public final void write(AsyncSyncClient client, JavaFile javaFile) {
        String asyncClientName = client.getClassName().replace("Client", "AsyncClient");
        Set<String> imports = new HashSet<>();
        client.getServiceClient().addImportsTo(imports, false, false, JavaSettings.getInstance());
        imports.remove(ClassType.HttpPipeline.getFullName());
        if (client.getMethodGroupClient() != null) {
            client.getMethodGroupClient().addImportsTo(imports, false, JavaSettings.getInstance());
        }
        imports.add("com.azure.core.annotation.ServiceClient");

        javaFile.declareImport(imports);
        javaFile.javadocComment(comment ->
            comment.description(String.format("Initializes a new instance of the %1$s type.",
                client.getClassName())));

        javaFile.annotation(String.format("ServiceClient(builder = %s.class)", client.getServiceClient().getInterfaceName() + ClientModelUtil.getBuilderSuffix()));
        javaFile.publicFinalClass(client.getClassName(), classBlock -> {
            classBlock.privateFinalMemberVariable(asyncClientName, "asyncClient");

            // Service Client Constructor
            classBlock.javadocComment(comment -> {
                comment.description(String.format("Initializes an instance of %1$s client.", client.getClassName()));
                    comment.param("asyncClient", "The " + asyncClientName + " underneath.");
            });

            String constructorArgs = asyncClientName + " asyncClient";

            classBlock.constructor(JavaVisibility.PackagePrivate,
                    client.getClassName() + "(" + constructorArgs + ")",
                    constructor -> {
                constructor.line("this.asyncClient = asyncClient;");
            });

            List<ClientMethod> methods;
            if (client.getMethodGroupClient() != null) {
                methods = client.getMethodGroupClient().getClientMethods();
            } else {
                methods = client.getServiceClient().getClientMethods();
            }
            methods.stream().filter(m -> m.getType() == ClientMethodType.SimpleAsyncRestResponse).forEach(method -> {
                Templates.getProtocolSyncMethodTemplate().write(method, classBlock);
            });

            // invoke() method
            String invokeMethodArgs = "String url, HttpMethod httpMethod, BinaryData body, RequestOptions options";
            String invokeMethodArgsInvoke;
            if (JavaSettings.getInstance().isContextClientMethodParameter()) {
                invokeMethodArgs = invokeMethodArgs + ", Context context";
                invokeMethodArgsInvoke = "url, httpMethod, body, options, context";
            } else {
                invokeMethodArgsInvoke = "url, httpMethod, body, options";
            }
            classBlock.javadocComment(comment -> {
                comment.description("Create an empty DynamicRequest with the serializer and pipeline initialized for this client.");
                comment.methodReturns("a DynamicRequest where customizations can be made before sent to the service.");
            });
            classBlock.annotation("ServiceMethod(returns = ReturnType.SINGLE)");
            classBlock.publicMethod(String.format("Response<BinaryData> invoke(%s)", invokeMethodArgs), method -> {
                method.methodReturn(String.format("asyncClient.invoke(%s).block()", invokeMethodArgsInvoke));
            });
        });
    }
}
