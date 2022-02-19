// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.*;
import com.azure.autorest.extension.base.model.extensionmodel.XmsExtensions;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.*;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.SchemaUtil;
import com.azure.autorest.util.XmsExampleWrapper;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClientMapper implements IMapper<CodeModel, Client> {
    private static final ClientMapper INSTANCE = new ClientMapper();

    private ClientMapper() {
    }

    public static ClientMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Client map(CodeModel codeModel) {
        JavaSettings settings = JavaSettings.getInstance();
        Client.Builder builder = new Client.Builder();

        // enum model
        List<EnumType> enumTypes = new ArrayList<>();
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

        List<ClientModel> clientModels = autoRestModelTypes
                .distinct()
                .map(autoRestCompositeType -> Mappers.getModelMapper().map(autoRestCompositeType))
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
        builder.models(clientModels);

        // response model (subclass of Response with headers)
        builder.responseModels(codeModel.getOperationGroups().stream()
                .flatMap(og -> og.getOperations().stream())
                .distinct()
                .map(m -> parseResponse(m, settings))
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList()));

        // service client
        String serviceClientName = codeModel.getLanguage().getJava().getName();
        String serviceClientDescription = codeModel.getInfo().getDescription();
        ServiceClient serviceClient = Mappers.getServiceClientMapper().map(codeModel);
        builder.clientName(serviceClientName)
                .clientDescription(serviceClientDescription)
                .serviceClient(serviceClient);

        // package info
        Map<String, PackageInfo> packageInfos = new HashMap<>();
        if (settings.shouldGenerateClientInterfaces() || !settings.shouldGenerateClientAsImpl()
                || settings.getImplementationSubpackage() == null || settings.getImplementationSubpackage().isEmpty()
                || settings.isFluent() || settings.shouldGenerateSyncAsyncClients() || settings.isLowLevelClient()) {
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
        } else if (!settings.isLowLevelClient()) {
            if (settings.shouldGenerateClientAsImpl() && settings.getImplementationSubpackage() != null && !settings
                .getImplementationSubpackage().isEmpty()) {
                String implementationPackage = settings.getPackage(settings.getImplementationSubpackage());
                if (!packageInfos.containsKey(implementationPackage)) {
                    packageInfos.put(implementationPackage, new PackageInfo(
                        implementationPackage,
                        String.format("Package containing the implementations for %s.\n%s",
                            serviceClientName, serviceClientDescription)));
                }
            }
        }
        if (!settings.isLowLevelClient()) {
            if (settings.getModelsSubpackage() != null && !settings.getModelsSubpackage().isEmpty()
                    && !settings.getModelsSubpackage().equals(settings.getImplementationSubpackage())
                    // add package-info models package only if the models package is not empty
                    && !clientModels.isEmpty()) {
                String modelsPackage = settings.getPackage(settings.getModelsSubpackage());
                if (!packageInfos.containsKey(modelsPackage) && !settings.isLowLevelClient()) {
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
        }
        builder.packageInfos(new ArrayList<>(packageInfos.values()));

        // module info
        builder.moduleInfo(moduleInfo());

        // async/sync service client (wrapper for the ServiceClient)
        List<AsyncSyncClient> syncClients = new ArrayList<>();
        List<AsyncSyncClient> asyncClients = new ArrayList<>();
        if (settings.shouldGenerateSyncAsyncClients()) {
            ClientModelUtil.getAsyncSyncClients(serviceClient, asyncClients, syncClients);
        }
        builder.syncClients(syncClients);
        builder.asyncClients(asyncClients);

        // service client builder
        if (!serviceClient.builderDisabled()) {
            List<ClientBuilder> clientBuilders = new ArrayList<>();
            String builderSuffix = ClientModelUtil.getBuilderSuffix();
            String builderName = serviceClient.getInterfaceName() + builderSuffix;
            String builderPackage = ClientModelUtil.getServiceClientBuilderPackageName(serviceClient);
            if (settings.shouldGenerateSyncAsyncClients() && settings.isGenerateBuilderPerClient()) {
                // service client builder per service client
                for (int i = 0; i < asyncClients.size(); ++i) {
                    AsyncSyncClient asyncClient = asyncClients.get(i);
                    AsyncSyncClient syncClient = (i > syncClients.size()) ? null : syncClients.get(i);
                    String clientName = ((syncClient != null)
                            ? syncClient.getClassName()
                            : asyncClient.getClassName().replace("AsyncClient", "Client"));
                    String clientBuilderName = clientName + builderSuffix;
                    ClientBuilder clientBuilder = new ClientBuilder(
                            builderPackage, clientBuilderName, serviceClient,
                            (syncClient == null) ? Collections.emptyList() : Collections.singletonList(syncClient),
                            Collections.singletonList(asyncClient));
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
                clientBuilders.add(clientBuilder);

                // there is a cross-reference between service client and service client builder
                asyncClients.forEach(c -> c.setClientBuilder(clientBuilder));
                syncClients.forEach(c -> c.setClientBuilder(clientBuilder));
            }
            builder.clientBuilders(clientBuilders);
        }

        // example/test
        if (settings.isLowLevelClient() && (settings.isGenerateSamples() || settings.isGenerateTests())) {
            List<ProtocolExample> protocolExamples = new ArrayList<>();
            Set<String> protocolExampleNameSet = new HashSet<>();
            syncClients.stream().filter(c -> c.getMethodGroupClient() != null)
                    .forEach(c -> c.getMethodGroupClient().getClientMethods().stream()
                            .filter(m -> m.getType() == ClientMethodType.SimpleSyncRestResponse || m.getType() == ClientMethodType.PagingSync || m.getType() == ClientMethodType.LongRunningBeginSync)
                            .forEach(m -> {
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
                            }));
            builder.protocolExamples(protocolExamples);
        }

        if (settings.isGenerateLiveTests() && codeModel.getTestModel() != null) {
            builder.liveTests(parseLiveTests(codeModel.getTestModel()));
        }

        return builder.build();
    }

    private List<LiveTests> parseLiveTests(TestModel testModel) {
        if (testModel.getScenarioTests() == null) {
            return Lists.newArrayList();
        }
        return testModel.getScenarioTests().stream().map(scenarioTest -> {
            LiveTests liveTests = new LiveTests();
            liveTests.setFilename(getFilename(scenarioTest.getFilePath()));
            liveTests.setTestCases(scenarioTest.getScenarios().stream().map(testScenario -> {
                LiveTestCase liveTestCase = new LiveTestCase();
                liveTestCase.setName(CodeNamer.toCamelCase(testScenario.getScenario()));
                liveTestCase.setTestSteps(testScenario.getResolvedSteps().stream().map((Function<ScenarioStep, LiveTestStep>) scenarioStep -> {
                    // future work: support other step types, for now only support example file
                    if (scenarioStep.getType() != TestScenarioStepType.REST_CALL ||
                        scenarioStep.getExampleFile() == null) {
                        throw new UnsupportedOperationException(String.format("Scenario test step: %s is not supported", scenarioStep.getType()));
                    }
                    Map<String, Object> example = new HashMap<>();
                    example.put("parameters", scenarioStep.getRequestParameters());
                    XmsExampleWrapper exampleWrapper = new XmsExampleWrapper(example, scenarioStep.getOperationId(), scenarioStep.getExampleName());
                    ProxyMethodExample proxyMethodExample = ProxyMethodExampleMapper.getInstance().map(exampleWrapper);
                    return new ExampleLiveTestStep(scenarioStep.getOperationId(), proxyMethodExample);
                }).collect(Collectors.toList()));
                return liveTestCase;
            }).collect(Collectors.toList()));
            return liveTests;
        }).collect(Collectors.toList());
    }

    private static String getFilename(String filePath) {
        String[] split = filePath.replaceAll("\\\\", "/").split("/");
        String filename = split[split.length - 1];
        filename = filename.split("\\.")[0];
        return CodeNamer.toCamelCase(filename);
    }

    private List<XmlSequenceWrapper> parseXmlSequenceWrappers(CodeModel codeModel) {
        List<XmlSequenceWrapper> xmlSequenceWrappers = new ArrayList<>();
        JavaSettings settings = JavaSettings.getInstance();
        if (settings.shouldGenerateXmlSerialization()) {
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

    private ObjectSchema parseHeader(Operation operation, JavaSettings settings) {
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

    private ClientResponse parseResponse(Operation method, JavaSettings settings) {
        ClientResponse.Builder builder = new ClientResponse.Builder();
        ObjectSchema headerSchema = parseHeader(method, settings);
        if (headerSchema == null) {
            return null;
        }
        ClassType classType = ClientMapper.getClientResponseClassType(method, settings);
        builder.name(classType.getName()).packageName(classType.getPackage());
        builder.description(String.format("Contains all response data for the %s operation.", method.getLanguage().getJava().getName()));
        builder.headersType(Mappers.getSchemaMapper().map(headerSchema));
        builder.bodyType(SchemaUtil.getOperationResponseType(method));
        return builder.build();
    }

    private static ModuleInfo moduleInfo() {
        // WARNING: Only tested for low level clients
        JavaSettings settings = JavaSettings.getInstance();
        ModuleInfo moduleInfo = new ModuleInfo(settings.getPackage());

        List<ModuleInfo.RequireModule> requireModules = moduleInfo.getRequireModules();
        requireModules.add(new ModuleInfo.RequireModule("com.azure.core", true));

        List<ModuleInfo.ExportModule> exportModules = moduleInfo.getExportModules();
        exportModules.add(new ModuleInfo.ExportModule(settings.getPackage()));

        return moduleInfo;
    }

    static ClassType getClientResponseClassType(Operation method, JavaSettings settings) {
        String name = CodeNamer.getPlural(method.getOperationGroup().getLanguage().getJava().getName())
                + CodeNamer.toPascalCase(method.getLanguage().getJava().getName()) + "Response";
        String packageName = settings.getPackage(settings.getModelsSubpackage());
        if (settings.isCustomType(name)) {
            packageName = settings.getPackage(settings.getCustomTypesSubpackage());
        }
        return new ClassType.Builder().packageName(packageName).name(name).build();
    }
}
