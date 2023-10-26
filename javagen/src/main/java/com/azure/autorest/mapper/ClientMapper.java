// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ArraySchema;
import com.azure.autorest.extension.base.model.codemodel.ChoiceSchema;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.DictionarySchema;
import com.azure.autorest.extension.base.model.codemodel.Header;
import com.azure.autorest.extension.base.model.codemodel.Language;
import com.azure.autorest.extension.base.model.codemodel.Languages;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.OperationGroup;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.extension.base.model.codemodel.Request;
import com.azure.autorest.extension.base.model.codemodel.Response;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.model.codemodel.SchemaContext;
import com.azure.autorest.extension.base.model.codemodel.Scheme;
import com.azure.autorest.extension.base.model.codemodel.SealedChoiceSchema;
import com.azure.autorest.extension.base.model.extensionmodel.XmsExtensions;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.AsyncSyncClient;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.clientmodel.ClientBuilder;
import com.azure.autorest.model.clientmodel.ClientBuilderTrait;
import com.azure.autorest.model.clientmodel.ClientException;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodExample;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientResponse;
import com.azure.autorest.model.clientmodel.ConvenienceMethod;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ModuleInfo;
import com.azure.autorest.model.clientmodel.PackageInfo;
import com.azure.autorest.model.clientmodel.ProtocolExample;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.XmlSequenceWrapper;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.template.Templates;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.SchemaUtil;
import com.azure.core.util.CoreUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A mapper that maps a {@link CodeModel} to a {@link Client}.
 */
public class ClientMapper implements IMapper<CodeModel, Client> {
    private static final ClientMapper INSTANCE = new ClientMapper();

    protected ClientMapper() {
    }

    /**
     * Gets the global {@link ClientMapper} instance.
     *
     * @return The global {@link ClientMapper} instance.
     */
    public static ClientMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Client map(CodeModel codeModel) {
        JavaSettings settings = JavaSettings.getInstance();
        Client.Builder builder = new Client.Builder();

        // enum model
        final List<EnumType> enumTypes = new ArrayList<>();
        Set<String> enumNames = new HashSet<>();
        for (ChoiceSchema choiceSchema : codeModel.getSchemas().getChoices()) {
            IType iType = Mappers.getChoiceMapper().map(choiceSchema);
            if (iType != ClassType.String) {
                EnumType enumType = (EnumType) iType;
                if (!enumNames.contains(enumType.getName())) {
                    enumTypes.add(enumType);
                    enumNames.add(enumType.getName());
                }
            }
        }
        for (SealedChoiceSchema choiceSchema : codeModel.getSchemas().getSealedChoices()) {
            IType iType = Mappers.getSealedChoiceMapper().map(choiceSchema);
            if (iType != ClassType.String) {
                EnumType enumType = (EnumType) iType;
                if (!enumNames.contains(enumType.getName())) {
                    enumTypes.add(enumType);
                    enumNames.add(enumType.getName());
                }
            }
        }
        builder.enums(enumTypes);

        // exception
        List<ClientException> exceptions = codeModel.getOperationGroups().stream()
            .flatMap(og -> og.getOperations().stream())
            .flatMap(o -> o.getExceptions().stream())
            .map(Response::getSchema)
            .distinct()
            .filter(s -> s instanceof ObjectSchema)
            .map(s -> Mappers.getExceptionMapper().map((ObjectSchema) s))
            .filter(Objects::nonNull)
            .distinct()
            .collect(Collectors.toList());
        builder.exceptions(exceptions);

        builder.xmlSequenceWrappers(parseXmlSequenceWrappers(codeModel, settings));

        // class model
        Stream<ObjectSchema> autoRestModelTypes = Stream.concat(
            codeModel.getSchemas().getObjects().stream(),
            codeModel.getOperationGroups().stream().flatMap(og -> og.getOperations().stream())
                .map(o -> parseHeader(o, settings)).filter(Objects::nonNull));

        final List<ClientModel> clientModels = autoRestModelTypes
            .distinct()
            .map(autoRestCompositeType -> Mappers.getModelMapper().map(autoRestCompositeType))
            .filter(Objects::nonNull)
            .distinct()
            .collect(Collectors.toList());

        builder.models(clientModels);

        // union model (class)
        builder.unionModels(codeModel.getSchemas().getOrs().stream().distinct()
            .flatMap(schema -> Mappers.getUnionModelMapper().map(schema).stream())
            .filter(Objects::nonNull)
            .distinct()
            .collect(Collectors.toList()));

        // response model (subclass of Response with headers)
        final List<ClientResponse> responseModels = codeModel.getOperationGroups().stream()
            .flatMap(og -> og.getOperations().stream())
            .distinct()
            .map(m -> parseResponse(m, clientModels, settings))
            .filter(Objects::nonNull)
            .distinct()
            .collect(Collectors.toList());
        builder.responseModels(responseModels);

        String serviceClientName = codeModel.getLanguage().getJava().getName();
        String serviceClientDescription = codeModel.getInfo().getDescription();
        builder.clientName(serviceClientName).clientDescription(serviceClientDescription);

        Map<ServiceClient, com.azure.autorest.extension.base.model.codemodel.Client> serviceClientsMap = new LinkedHashMap<>();
        if (!CoreUtils.isNullOrEmpty(codeModel.getClients())) {
            serviceClientsMap = processClients(codeModel.getClients(), codeModel);
            builder.serviceClients(new ArrayList(serviceClientsMap.keySet()));
        } else {
            // service client
            ServiceClient serviceClient = Mappers.getServiceClientMapper().map(codeModel);
            builder.serviceClient(serviceClient);

            serviceClientsMap.put(serviceClient, codeModel);
        }

        // package info
        Map<String, PackageInfo> packageInfos = new HashMap<>();
        if (settings.isGenerateClientInterfaces() || !settings.isGenerateClientAsImpl()
            || settings.getImplementationSubpackage() == null || settings.getImplementationSubpackage().isEmpty()
            || settings.isFluent() || settings.isGenerateSyncAsyncClients() || settings.isDataPlaneClient()) {
            packageInfos.put(settings.getPackage(), new PackageInfo(
                settings.getPackage(),
                String.format("Package containing the classes for %s.\n%s", serviceClientName,
                    serviceClientDescription)));
        }
        if (settings.isFluent()) {
            if (settings.isFluentLite() && !CoreUtils.isNullOrEmpty(settings.getImplementationSubpackage())) {
                String implementationPackage = settings.getPackage(settings.getImplementationSubpackage());
                if (!packageInfos.containsKey(implementationPackage)) {
                    packageInfos.put(implementationPackage, new PackageInfo(
                        implementationPackage,
                        String.format("Package containing the implementations for %s.\n%s",
                            serviceClientName, serviceClientDescription)));
                }
            }
            if (!CoreUtils.isNullOrEmpty(settings.getFluentSubpackage())) {
                String fluentPackage = settings.getPackage(settings.getFluentSubpackage());
                if (!packageInfos.containsKey(fluentPackage)) {
                    packageInfos.put(fluentPackage, new PackageInfo(
                        fluentPackage,
                        String.format("Package containing the service clients for %s.\n%s",
                            serviceClientName, serviceClientDescription)));
                }
                String fluentInnerPackage = settings.getPackage(settings.getFluentModelsSubpackage());
                if (!packageInfos.containsKey(fluentInnerPackage)) {
                    packageInfos.put(fluentInnerPackage, new PackageInfo(
                        fluentInnerPackage,
                        String.format("Package containing the inner data models for %s.\n%s",
                            serviceClientName, serviceClientDescription)));
                }
            }
        } else {
            if (settings.isGenerateClientAsImpl() && settings.getImplementationSubpackage() != null
                && !settings.getImplementationSubpackage().isEmpty()) {

                String implementationPackage = settings.getPackage(settings.getImplementationSubpackage());
                if (!packageInfos.containsKey(implementationPackage)) {
                    packageInfos.put(implementationPackage, new PackageInfo(
                        implementationPackage,
                        String.format("Package containing the implementations for %s.\n%s",
                            serviceClientName, serviceClientDescription)));
                }
            }
        }
        final List<String> modelsPackages = getModelsPackages(clientModels, enumTypes, responseModels);
        for (String modelsPackage : modelsPackages) {
            if (!packageInfos.containsKey(modelsPackage)) {
                packageInfos.put(modelsPackage, new PackageInfo(
                    modelsPackage,
                    String.format("Package containing the data models for %s.\n%s", serviceClientName,
                        serviceClientDescription)));
            }
        }
        if (settings.getCustomTypes() != null && !settings.getCustomTypes().isEmpty()
            && settings.getCustomTypesSubpackage() != null && !settings.getCustomTypesSubpackage().isEmpty()) {
            String customTypesPackage = settings.getPackage(settings.getCustomTypesSubpackage());
            if (!packageInfos.containsKey(customTypesPackage)) {
                packageInfos.put(customTypesPackage, new PackageInfo(
                    customTypesPackage,
                    String.format("Package containing the data models for %s.\n%s", serviceClientName,
                        serviceClientDescription)));
            }
        }
        builder.packageInfos(new ArrayList<>(packageInfos.values()));

        // module info
        builder.moduleInfo(getModuleInfo(modelsPackages));

        // async/sync service client (wrapper for the ServiceClient)
        List<AsyncSyncClient> syncClients = new ArrayList<>();
        List<AsyncSyncClient> asyncClients = new ArrayList<>();
        List<ClientBuilder> clientBuilders = new ArrayList<>();
        for (Map.Entry<ServiceClient, ? extends com.azure.autorest.extension.base.model.codemodel.Client> entry : serviceClientsMap.entrySet()) {
            List<AsyncSyncClient> syncClientsLocal = new ArrayList<>();
            List<AsyncSyncClient> asyncClientsLocal = new ArrayList<>();

            ServiceClient serviceClient = entry.getKey();
            com.azure.autorest.extension.base.model.codemodel.Client client = entry.getValue();
            if (settings.isGenerateSyncAsyncClients()) {
                ClientModelUtil.getAsyncSyncClients(client, serviceClient, asyncClientsLocal, syncClientsLocal);
            }
            builder.syncClients(syncClients);
            builder.asyncClients(asyncClients);

            // service client builder
            if (!serviceClient.builderDisabled()) {
                String builderSuffix = ClientModelUtil.getBuilderSuffix();
                String builderName = serviceClient.getInterfaceName() + builderSuffix;
                String builderPackage = ClientModelUtil.getServiceClientBuilderPackageName(serviceClient);
                if (settings.isGenerateSyncAsyncClients() && settings.isGenerateBuilderPerClient()) {
                    // service client builder per service client
                    for (int i = 0; i < asyncClientsLocal.size(); ++i) {
                        AsyncSyncClient asyncClient = asyncClientsLocal.get(i);
                        AsyncSyncClient syncClient = (i >= syncClientsLocal.size()) ? null : syncClientsLocal.get(i);
                        String clientName = ((syncClient != null)
                            ? syncClient.getClassName()
                            : asyncClient.getClassName().replace("AsyncClient", "Client"));
                        String clientBuilderName = clientName + builderSuffix;
                        ClientBuilder clientBuilder = new ClientBuilder(
                            builderPackage, clientBuilderName, serviceClient,
                            (syncClient == null) ? Collections.emptyList() : Collections.singletonList(syncClient),
                            Collections.singletonList(asyncClient));

                        addBuilderTraits(clientBuilder, serviceClient);
                        clientBuilders.add(clientBuilder);

                        // there is a cross-reference between service client and service client builder
                        asyncClient.setClientBuilder(clientBuilder);
                        if (syncClient != null) {
                            syncClient.setClientBuilder(clientBuilder);
                        }
                    }
                } else {
                    // service client builder
                    ClientBuilder clientBuilder = new ClientBuilder(builderPackage, builderName,
                        serviceClient, syncClientsLocal, asyncClientsLocal);
                    addBuilderTraits(clientBuilder, serviceClient);
                    clientBuilders.add(clientBuilder);

                    // there is a cross-reference between service client and service client builder
                    asyncClientsLocal.forEach(c -> c.setClientBuilder(clientBuilder));
                    syncClientsLocal.forEach(c -> c.setClientBuilder(clientBuilder));
                }
            }

            syncClients.addAll(syncClientsLocal);
            asyncClients.addAll(asyncClientsLocal);
        }
        builder.clientBuilders(clientBuilders);

        // example/test
        if (settings.isDataPlaneClient() && (settings.isGenerateSamples() || settings.isGenerateTests())) {
            addProtocolExamples(builder, syncClients);
            addConvenienceExamples(builder, syncClients);
        }

        if (settings.isGenerateTests() && codeModel.getTestModel() != null) {
            builder.liveTests(LiveTestsMapper.getInstance().map(codeModel.getTestModel()));
        }

        builder.graalVmConfig(Mappers.getGraalVmConfigMapper()
                .map(new GraalVmConfigMapper.ServiceAndModel(
                        serviceClientsMap.keySet(),
                        exceptions,
                        clientModels,
                        enumTypes)));

        return builder.build();
    }

    private void addConvenienceExamples(Client.Builder builder, List<AsyncSyncClient> syncClients) {
        // convenience examples
        List<ClientMethodExample> convenienceExamples = new ArrayList<>();
        Set<String> convenienceExampleNameSet = new HashSet<>();

        BiConsumer<AsyncSyncClient, ConvenienceMethod> handleConvenienceExample = (c, convenienceMethod) -> {
            ClientBuilder clientBuilder = c.getClientBuilder();
            if (clientBuilder != null && convenienceMethod.getProtocolMethod().getProxyMethod().getExamples() != null) {
                // only generate sample for convenience methods with max overload parameters
                convenienceMethod.getConvenienceMethods().stream()
                    .filter(clientMethod -> clientMethod.getMethodVisibility() == JavaVisibility.Public && clientMethod.getMethodVisibilityInWrapperClient() == JavaVisibility.Public)
                    .filter(clientMethod -> Templates.getClientMethodSampleTemplate()
                        .isExampleIncluded(clientMethod, convenienceMethod))
                    .max((clientMethod1, clientMethod2) -> {
                        int m1ParameterCount = clientMethod1.getMethodInputParameters().size();
                        int m2ParameterCount = clientMethod2.getMethodInputParameters().size();
                        return m1ParameterCount - m2ParameterCount;
                    })
                    .ifPresent(clientMethod ->
                        clientMethod.getProxyMethod().getExamples().forEach((name, example) -> {
                            String filename = CodeNamer.toPascalCase(CodeNamer.removeInvalidCharacters(name));
                            if (!convenienceExampleNameSet.contains(filename)) {
                                ClientMethodExample convenienceExample =
                                    new ClientMethodExample(clientMethod, c, clientBuilder, filename, example);
                                convenienceExamples.add(convenienceExample);
                                convenienceExampleNameSet.add(filename);
                            }
                        }));
            }
        };

        // convenience examples
        syncClients.stream().filter(c -> !CoreUtils.isNullOrEmpty(c.getConvenienceMethods()))
            .forEach(c -> c.getConvenienceMethods()
                .forEach(m -> handleConvenienceExample.accept(c, m)));
        builder.clientMethodExamples(convenienceExamples);
    }

    private void addProtocolExamples(Client.Builder builder, List<AsyncSyncClient> syncClients) {
        List<ProtocolExample> protocolExamples = new ArrayList<>();
        Set<String> protocolExampleNameSet = new HashSet<>();

        BiConsumer<AsyncSyncClient, ClientMethod> handleExample = (c, m) -> {
            if (m.getMethodVisibility() == JavaVisibility.Public
                && m.getMethodVisibilityInWrapperClient() == JavaVisibility.Public
                && !m.isImplementationOnly() &&
                (m.getType() == ClientMethodType.SimpleSyncRestResponse
                    || m.getType() == ClientMethodType.PagingSync
                    || m.getType() == ClientMethodType.LongRunningBeginSync)) {
                ClientBuilder clientBuilder = c.getClientBuilder();
                if (clientBuilder != null && m.getProxyMethod().getExamples() != null) {
                    m.getProxyMethod().getExamples().forEach((name, example) -> {
                        String filename = CodeNamer.toPascalCase(CodeNamer.removeInvalidCharacters(name));
                        if (!protocolExampleNameSet.contains(filename)) {
                            ProtocolExample protocolExample = new ProtocolExample(m, c, clientBuilder, filename, example);
                            protocolExamples.add(protocolExample);
                            protocolExampleNameSet.add(filename);
                        }
                    });
                }
            }
        };

        // protocol examples, exclude those that have convenience methods
        syncClients.stream().filter(c -> c.getServiceClient() != null)
            .forEach(c -> {
                Set<String> convenienceProxyMethodNames = new HashSet<>();
                if (c.getConvenienceMethods() != null) {
                    convenienceProxyMethodNames.addAll(c.getConvenienceMethods().stream()
                        .map(convenienceMethod -> convenienceMethod
                            .getProtocolMethod().getProxyMethod().getBaseName())
                        .collect(Collectors.toSet()));
                }
                c.getServiceClient().getClientMethods()
                    .stream()
                    .filter(m -> !convenienceProxyMethodNames.contains(m.getProxyMethod().getBaseName()))
                    .forEach(m -> handleExample.accept(c, m));
            });
        syncClients.stream().filter(c -> c.getMethodGroupClient() != null)
            .forEach(c -> {
                Set<String> convenienceProxyMethodNames = new HashSet<>();
                if (c.getConvenienceMethods() != null) {
                    convenienceProxyMethodNames.addAll(c.getConvenienceMethods().stream()
                        .map(convenienceMethod -> convenienceMethod
                            .getProtocolMethod().getProxyMethod().getBaseName())
                        .collect(Collectors.toSet()));
                }
                c.getMethodGroupClient().getClientMethods()
                    .stream()
                    .filter(m -> !convenienceProxyMethodNames.contains(m.getProxyMethod().getBaseName()))
                    .forEach(m -> handleExample.accept(c, m));
            });
        builder.protocolExamples(protocolExamples);
    }

    /**
     * Extension for processing multi-client. Supported in Cadl.
     *
     * @param clients List of clients.
     * @return List of service clients.
     */
    protected Map<ServiceClient, com.azure.autorest.extension.base.model.codemodel.Client> processClients(List<com.azure.autorest.extension.base.model.codemodel.Client> clients, CodeModel codeModel) {
        return Collections.emptyMap();
    }

    private void addBuilderTraits(ClientBuilder clientBuilder, ServiceClient serviceClient) {
        clientBuilder.addBuilderTrait(ClientBuilderTrait.HTTP_TRAIT);
        clientBuilder.addBuilderTrait(ClientBuilderTrait.CONFIGURATION_TRAIT);
        if (serviceClient.getSecurityInfo().getSecurityTypes().contains(Scheme.SecuritySchemeType.OAUTH2)) {
            clientBuilder.addBuilderTrait(ClientBuilderTrait.TOKEN_CREDENTIAL_TRAIT);
        }
        if (serviceClient.getSecurityInfo().getSecurityTypes().contains(Scheme.SecuritySchemeType.KEY)) {
            if (JavaSettings.getInstance().isUseKeyCredential()) {
                clientBuilder.addBuilderTrait(ClientBuilderTrait.KEY_CREDENTIAL_TRAIT);
            } else {
                clientBuilder.addBuilderTrait(ClientBuilderTrait.AZURE_KEY_CREDENTIAL_TRAIT);
            }
        }
        serviceClient.getProperties().stream()
            .filter(property -> property.getName().equals("endpoint"))
            .findFirst()
            .ifPresent(property -> clientBuilder.addBuilderTrait(ClientBuilderTrait.getEndpointTrait(property)));
    }

    private List<XmlSequenceWrapper> parseXmlSequenceWrappers(CodeModel codeModel, JavaSettings settings) {
        Map<String, XmlSequenceWrapper> xmlSequenceWrappers = new LinkedHashMap<>();
        for (OperationGroup operationGroup : codeModel.getOperationGroups()) {
            for (Operation operation : operationGroup.getOperations()) {
                Schema responseBodySchema = SchemaUtil.getLowestCommonParent(operation.getResponses().stream()
                    .map(Response::getSchema)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList()));

                if (responseBodySchema instanceof ArraySchema) {
                    parseXmlSequenceWrappers((ArraySchema) responseBodySchema, xmlSequenceWrappers, settings);
                }

                for (Parameter parameter : operation.getParameters()) {
                    if (!(parameter.getSchema() instanceof ArraySchema)) {
                        continue;
                    }
                    parseXmlSequenceWrappers((ArraySchema) parameter.getSchema(), xmlSequenceWrappers, settings);
                }

                for (Request request : operation.getRequests()) {
                    for (Parameter parameter : request.getParameters()) {
                        if (!(parameter.getSchema() instanceof ArraySchema)) {
                            continue;
                        }
                        parseXmlSequenceWrappers((ArraySchema) parameter.getSchema(), xmlSequenceWrappers, settings);
                    }
                }
            }
        }

        return new ArrayList<>(xmlSequenceWrappers.values());
    }

    private static void parseXmlSequenceWrappers(ArraySchema arraySchema,
        Map<String, XmlSequenceWrapper> xmlSequenceWrappers, JavaSettings settings) {
        if (!SchemaUtil.treatAsXml(arraySchema)) {
            return;
        }

        String modelTypeName = arraySchema.getElementType().getLanguage().getJava() != null
            ? arraySchema.getElementType().getLanguage().getJava().getName()
            : arraySchema.getElementType().getLanguage().getDefault().getName();

        xmlSequenceWrappers.computeIfAbsent(modelTypeName, name -> new XmlSequenceWrapper(name, arraySchema, settings));
    }

    static ObjectSchema parseHeader(Operation operation, JavaSettings settings) {
        if (!SchemaUtil.responseContainsHeaderSchemas(operation, settings)) {
            return null;
        }

        String name = CodeNamer.getPlural(operation.getOperationGroup().getLanguage().getJava().getName())
            + CodeNamer.toPascalCase(operation.getLanguage().getJava().getName()) + "Headers";
        Map<String, Schema> headerMap = new HashMap<>();
        Map<String, XmsExtensions> headerExtensions = new HashMap<>();
        for (Response response : operation.getResponses()) {
            if (response.getProtocol().getHttp().getHeaders() != null) {
                for (Header header : response.getProtocol().getHttp().getHeaders()) {
                    headerExtensions.put(header.getHeader(), header.getExtensions());
                    headerMap.put(header.getHeader(), header.getSchema());
                }
            }
        }
        if (headerMap.isEmpty()) {
            return null;
        }
        ObjectSchema headerSchema = new ObjectSchema();
        headerSchema.setLanguage(new Languages());
        headerSchema.getLanguage().setJava(new Language());
        headerSchema.getLanguage().getJava().setName(name);
        headerSchema.setProperties(new ArrayList<>());
        headerSchema.setStronglyTypedHeader(true);
        headerSchema.setUsage(new HashSet<>(Collections.singletonList(SchemaContext.OUTPUT)));

        // TODO (weidxu): at present we do not generate convenience API with Header model
//        if (operation.getConvenienceApi() != null) {
//            headerSchema.getUsage().add(SchemaContext.CONVENIENCE_API);
//        }

        for (Map.Entry<String, Schema> header : headerMap.entrySet()) {
            Property property = new Property();
            property.setSerializedName(header.getKey());
            property.setLanguage(new Languages());
            property.getLanguage().setJava(new Language());
            property.getLanguage().getJava().setName(CodeNamer.getPropertyName(header.getKey()));
            property.getLanguage().getJava().setDescription(header.getValue().getDescription());
            property.setSchema(header.getValue());
            property.setDescription(header.getValue().getDescription());
            if (headerExtensions.get(header.getKey()) != null) {
                property.setExtensions(headerExtensions.get(header.getKey()));
                if (property.getExtensions().getXmsHeaderCollectionPrefix() != null) {
                    property.setSerializedName(property.getExtensions().getXmsHeaderCollectionPrefix());
                    DictionarySchema dictionarySchema = new DictionarySchema();
                    dictionarySchema.setElementType(header.getValue());
                    property.setSchema(header.getValue());
                }
            }
            headerSchema.getProperties().add(property);
        }
        return headerSchema;
    }

    private ClientResponse parseResponse(Operation method, List<ClientModel> models, JavaSettings settings) {
        ClientResponse.Builder builder = new ClientResponse.Builder();
        ObjectSchema headerSchema = parseHeader(method, settings);
        if (headerSchema == null || settings.isGenericResponseTypes()) {
            return null;
        }

        ClassType classType = ClientMapper.getClientResponseClassType(method, models, settings);
        return builder.name(classType.getName())
            .packageName(classType.getPackage())
            .description(String.format("Contains all response data for the %s operation.", method.getLanguage().getJava().getName()))
            .headersType(Mappers.getSchemaMapper().map(headerSchema))
            .bodyType(SchemaUtil.getOperationResponseType(method, settings))
            .build();
    }

    private static ModuleInfo getModuleInfo(List<String> modelsPackages) {
        // WARNING: Only tested for low level clients
        JavaSettings settings = JavaSettings.getInstance();
        ModuleInfo moduleInfo = new ModuleInfo(settings.getPackage());

        List<ModuleInfo.RequireModule> requireModules = moduleInfo.getRequireModules();
        if (settings.isGeneric()) {
            requireModules.add(new ModuleInfo.RequireModule("com.generic.core", true));
        } else {
            requireModules.add(new ModuleInfo.RequireModule("com.azure.core", true));
        }

        List<ModuleInfo.ExportModule> exportModules = moduleInfo.getExportModules();
        exportModules.add(new ModuleInfo.ExportModule(settings.getPackage()));

        final String implementationSubpackagePrefix = settings.getPackage(settings.getImplementationSubpackage()) + ".";
        for (String modelsPackage : modelsPackages) {
            // export if models is not in implementation
            if (!modelsPackage.startsWith(implementationSubpackagePrefix)) {
                exportModules.add(new ModuleInfo.ExportModule(modelsPackage));
            }

            // open models package to azure-core and jackson
            List<String> openToModules = Arrays.asList("com.azure.core", "com.fasterxml.jackson.databind");
            if(settings.isGeneric()) {
                openToModules = Arrays.asList("com.generic.core");
            }
            List<ModuleInfo.OpenModule> openModules = moduleInfo.getOpenModules();
            openModules.add(new ModuleInfo.OpenModule(modelsPackage, openToModules));
        }

        return moduleInfo;
    }

    /**
     * Extension for the list of "models" package (it could contain "implementation.models" and that of
     * custom-types-subpackage), that need to have "exports" or "opens" in "module-info.java", and have
     * "package-info.java"
     *
     * @param clientModels the list of client models (ObjectSchema).
     * @param enumTypes the list of enum models (ChoiceSchema and SealedChoiceSchema).
     * @param responseModels the list of client response models (for responses that contains headers).
     * @return whether SDK contains "models" package,
     */
    protected List<String> getModelsPackages(List<ClientModel> clientModels, List<EnumType> enumTypes, List<ClientResponse> responseModels) {

        List<String> ret = Collections.emptyList();

        JavaSettings settings = JavaSettings.getInstance();
        boolean hasModels = (!settings.isDataPlaneClient() || settings.isGenerateModels())   // not DPG, or DPG that requires all models
            // defined models package (it is defined by default)
            && (settings.getModelsSubpackage() != null && !settings.getModelsSubpackage().isEmpty())
            // models package is not same as implementation package
            && !settings.getModelsSubpackage().equals(settings.getImplementationSubpackage());

        if (hasModels) {
            Set<String> packages = clientModels.stream()
                .map(ClientModel::getPackage)
                .collect(Collectors.toSet());

            packages.addAll(enumTypes.stream()
                .map(EnumType::getPackage)
                .collect(Collectors.toSet()));
            packages.addAll(responseModels.stream()
                .map(ClientResponse::getPackage)
                .collect(Collectors.toSet()));

            ret = new ArrayList<>(packages);
        }

        return ret;
    }

    static ClassType getClientResponseClassType(Operation method, List<ClientModel> models, JavaSettings settings) {
        String name = CodeNamer.getPlural(method.getOperationGroup().getLanguage().getJava().getName())
            + CodeNamer.toPascalCase(method.getLanguage().getJava().getName()) + "Response";
        String packageName = settings.getPackage(settings.getModelsSubpackage());
        if (settings.isCustomType(name)) {
            packageName = settings.getPackage(settings.getCustomTypesSubpackage());
        }

        // deduplicate from model name
        for (ClientModel model : models) {
            if (model.getName().equalsIgnoreCase(name) && model.getPackage().equals(packageName)) {
                name = name + "Response";
            }
        }

        return new ClassType.Builder().packageName(packageName).name(name).build();
    }
}
