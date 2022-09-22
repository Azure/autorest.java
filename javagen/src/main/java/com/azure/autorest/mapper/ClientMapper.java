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
import com.azure.autorest.extension.base.model.codemodel.Property;
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
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientResponse;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ModuleInfo;
import com.azure.autorest.model.clientmodel.PackageInfo;
import com.azure.autorest.model.clientmodel.ProtocolExample;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.XmlSequenceWrapper;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.SchemaUtil;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClientMapper implements IMapper<CodeModel, Client> {
    private static final ClientMapper INSTANCE = new ClientMapper();

    protected ClientMapper() {
    }

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
        builder.exceptions(codeModel.getOperationGroups().stream()
                .flatMap(og -> og.getOperations().stream())
                .flatMap(o -> o.getExceptions().stream())
                .map(Response::getSchema)
                .distinct()
                .filter(s -> s instanceof ObjectSchema)
                .map(s -> Mappers.getExceptionMapper().map((ObjectSchema) s))
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList()));

        builder.xmlSequenceWrappers(parseXmlSequenceWrappers(codeModel));

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

        // response model (subclass of Response with headers)
        final List<ClientResponse> responseModels = codeModel.getOperationGroups().stream()
                .flatMap(og -> og.getOperations().stream())
                .distinct()
                .map(m -> parseResponse(m, clientModels, settings))
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
        builder.responseModels(responseModels);

        // service client
        String serviceClientName = codeModel.getLanguage().getJava().getName();
        String serviceClientDescription = codeModel.getInfo().getDescription();
        ServiceClient serviceClient = Mappers.getServiceClientMapper().map(codeModel);
        builder.clientName(serviceClientName)
                .clientDescription(serviceClientDescription)
                .serviceClient(serviceClient);

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
                        String.format("Package containing classes for %s.\n%s", serviceClientName,
                                serviceClientDescription)));
            }
        }
        builder.packageInfos(new ArrayList<>(packageInfos.values()));

        // module info
        builder.moduleInfo(getModuleInfo(modelsPackages));

        // async/sync service client (wrapper for the ServiceClient)
        List<AsyncSyncClient> syncClients = new ArrayList<>();
        List<AsyncSyncClient> asyncClients = new ArrayList<>();
        if (settings.isGenerateSyncAsyncClients()) {
            ClientModelUtil.getAsyncSyncClients(codeModel, serviceClient, asyncClients, syncClients);
        }
        builder.syncClients(syncClients);
        builder.asyncClients(asyncClients);

        // service client builder
        if (!serviceClient.builderDisabled()) {
            List<ClientBuilder> clientBuilders = new ArrayList<>();
            String builderSuffix = ClientModelUtil.getBuilderSuffix();
            String builderName = serviceClient.getInterfaceName() + builderSuffix;
            String builderPackage = ClientModelUtil.getServiceClientBuilderPackageName(serviceClient);
            if (settings.isGenerateSyncAsyncClients() && settings.isGenerateBuilderPerClient()) {
                // service client builder per service client
                for (int i = 0; i < asyncClients.size(); ++i) {
                    AsyncSyncClient asyncClient = asyncClients.get(i);
                    AsyncSyncClient syncClient = (i >= syncClients.size()) ? null : syncClients.get(i);
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
                        serviceClient, syncClients, asyncClients);
                addBuilderTraits(clientBuilder, serviceClient);
                clientBuilders.add(clientBuilder);

                // there is a cross-reference between service client and service client builder
                asyncClients.forEach(c -> c.setClientBuilder(clientBuilder));
                syncClients.forEach(c -> c.setClientBuilder(clientBuilder));
            }
            builder.clientBuilders(clientBuilders);
        }

        // example/test
        if (settings.isDataPlaneClient() && (settings.isGenerateSamples() || settings.isGenerateTests())) {
            List<ProtocolExample> protocolExamples = new ArrayList<>();
            Set<String> protocolExampleNameSet = new HashSet<>();

            BiConsumer<AsyncSyncClient, ClientMethod> handleExample = (c, m) -> {
                if (m.getType() == ClientMethodType.SimpleSyncRestResponse
                        || m.getType() == ClientMethodType.PagingSync
                        || m.getType() == ClientMethodType.LongRunningBeginSync) {
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

            syncClients.stream().filter(c -> c.getServiceClient() != null)
                    .forEach(c -> c.getServiceClient().getClientMethods()
                            .forEach(m -> handleExample.accept(c, m)));
            syncClients.stream().filter(c -> c.getMethodGroupClient() != null)
                    .forEach(c -> c.getMethodGroupClient().getClientMethods()
                            .forEach(m -> handleExample.accept(c, m)));
            builder.protocolExamples(protocolExamples);
        }

        if (settings.isGenerateTests() && codeModel.getTestModel() != null) {
            builder.liveTests(LiveTestsMapper.getInstance().map(codeModel.getTestModel()));
        }

        return builder.build();
    }

    private void addBuilderTraits(ClientBuilder clientBuilder, ServiceClient serviceClient) {
        clientBuilder.addBuilderTrait(ClientBuilderTrait.HTTP_TRAIT);
        clientBuilder.addBuilderTrait(ClientBuilderTrait.CONFIGURATION_TRAIT);
        if (serviceClient.getSecurityInfo().getSecurityTypes().contains(Scheme.SecuritySchemeType.OAUTH2)) {
            clientBuilder.addBuilderTrait(ClientBuilderTrait.TOKEN_CREDENTIAL_TRAIT);
        }
        if (serviceClient.getSecurityInfo().getSecurityTypes().contains(Scheme.SecuritySchemeType.KEY)) {
            clientBuilder.addBuilderTrait(ClientBuilderTrait.AZURE_KEY_CREDENTIAL_TRAIT);
        }
        serviceClient.getProperties().stream()
            .filter(property -> property.getName().equals("endpoint"))
            .findFirst()
            .ifPresent(property -> clientBuilder.addBuilderTrait(ClientBuilderTrait.getEndpointTrait(property)));
    }

    private List<XmlSequenceWrapper> parseXmlSequenceWrappers(CodeModel codeModel) {
        List<XmlSequenceWrapper> xmlSequenceWrappers = new ArrayList<>();
        JavaSettings settings = JavaSettings.getInstance();
        if (settings.isGenerateXmlSerialization()) {
            List<Operation> allMethods = codeModel.getOperationGroups().stream()
                .flatMap(og -> og.getOperations().stream())
                .collect(Collectors.toList());

            allMethods.forEach(o -> Stream.concat(o.getParameters().stream(), o.getRequests().stream().flatMap(r -> r.getParameters().stream())).forEach(param -> {
                if (param.getSchema() instanceof ArraySchema) {
                    ArraySchema arraySchema = (ArraySchema) param.getSchema();
                    if (arraySchema.getSerialization() != null && arraySchema.getSerialization().getXml() != null) {
                        IType type = Mappers.getSchemaMapper().map(arraySchema);
                        String xmlRootElementName = arraySchema.getSerialization().getXml().getName();
                        String xmlListElementName = arraySchema.getElementType().getSerialization().getXml().getName();
                        if (xmlSequenceWrappers.stream().noneMatch(
                            xmlSequenceWrapper -> xmlSequenceWrapper.getXmlListElementName().equals(xmlListElementName)
                                && xmlSequenceWrapper.getXmlRootElementName().equals(xmlRootElementName))) {
                            Set<String> packageImports = new HashSet<>();
                            packageImports.add(JsonCreator.class.getName());
                            packageImports.add(JsonProperty.class.getName());
                            packageImports.add(JacksonXmlProperty.class.getName());
                            packageImports.add(JacksonXmlRootElement.class.getName());
                            packageImports.add(JacksonXmlText.class.getName());

                            type.addImportsTo(packageImports, true);
                            boolean isCustomType = settings
                                .isCustomType(CodeNamer.toPascalCase(xmlRootElementName + "Wrapper"));
                            String packageName = settings.getPackage(isCustomType ? settings.getCustomTypesSubpackage() :
                                settings.getImplementationSubpackage());
                            xmlSequenceWrappers.add(new XmlSequenceWrapper(packageName, type, xmlRootElementName,
                                xmlListElementName, packageImports));
                        }
                    }
                }
            }));
        }
        return xmlSequenceWrappers;
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
        if (operation.getExtensions() != null && operation.getExtensions().isConvenienceMethod()) {
            headerSchema.getUsage().add(SchemaContext.CONVENIENCE_METHOD);
        }
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
        requireModules.add(new ModuleInfo.RequireModule("com.azure.core", true));

        List<ModuleInfo.ExportModule> exportModules = moduleInfo.getExportModules();
        exportModules.add(new ModuleInfo.ExportModule(settings.getPackage()));

        final String implementationSubpackagePrefix = settings.getPackage(settings.getImplementationSubpackage()) + ".";
        for (String modelsPackage : modelsPackages) {
            // export if models is not in implementation
            if (!modelsPackage.startsWith(implementationSubpackagePrefix)) {
                exportModules.add(new ModuleInfo.ExportModule(modelsPackage));
            }

            // open models package to azure-core and jaskson
            List<String> openToModules = Arrays.asList("com.azure.core", "com.fasterxml.jackson.databind");
            List<ModuleInfo.OpenModule> openModules = moduleInfo.getOpenModules();
            openModules.add(new ModuleInfo.OpenModule(modelsPackage, openToModules));
        }

        return moduleInfo;
    }

    /**
     * Extension for the list of "models" package (it could contain "implementation.models" and that of custom-types-subpackage),
     * that need to have "exports" or "opens" in "module-info.java", and have "package-info.java"
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
