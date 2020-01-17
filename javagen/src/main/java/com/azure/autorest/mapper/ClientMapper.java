package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ArraySchema;
import com.azure.autorest.extension.base.model.codemodel.ChoiceSchema;
import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.Header;
import com.azure.autorest.extension.base.model.codemodel.Language;
import com.azure.autorest.extension.base.model.codemodel.Languages;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.extension.base.model.codemodel.Response;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.model.codemodel.SealedChoiceSchema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.clientmodel.ClientException;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientResponse;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.PackageInfo;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.ServiceClient;
import com.azure.autorest.model.clientmodel.XmlSequenceWrapper;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.SchemaUtil;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClientMapper implements IMapper<CodeModel, Client> {
    private static ClientMapper instance = new ClientMapper();

    private ClientMapper() {
    }

    public static ClientMapper getInstance() {
        return instance;
    }

    @Override
    public Client map(CodeModel codeModel) {
        JavaSettings settings = JavaSettings.getInstance();

        List<EnumType> enumTypes = new ArrayList<>();
        for (ChoiceSchema choiceSchema : codeModel.getSchemas().getChoices()) {
            IType iType = Mappers.getChoiceMapper().map(choiceSchema);
            if (iType != ClassType.String) {
                EnumType enumType = (EnumType) iType;
                enumTypes.add(enumType);
            }
        }
        for (SealedChoiceSchema choiceSchema : codeModel.getSchemas().getSealedChoices()) {
            IType iType = Mappers.getSealedChoiceMapper().map(choiceSchema);
            if (iType != ClassType.String) {
                EnumType enumType = (EnumType) iType;
                enumTypes.add(enumType);
            }
        }

        List<ClientException> exceptions = codeModel.getOperationGroups().stream()
                .flatMap(og -> og.getOperations().stream())
                .flatMap(o -> o.getExceptions().stream())
                .map(Response::getSchema)
                .distinct()
                .map(s -> Mappers.getExceptionMapper().map((ObjectSchema) s))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        List<XmlSequenceWrapper> xmlSequenceWrappers = parseXmlSequenceWrappers(codeModel);

        Stream<ObjectSchema> autoRestModelTypes = Stream.concat(
                codeModel.getSchemas().getObjects().stream(),
                codeModel.getOperationGroups().stream().flatMap(og -> og.getOperations().stream())
                        .map(o -> parseHeader(o, settings)).filter(Objects::nonNull));

        List<ClientModel> models = autoRestModelTypes
                .map(autoRestCompositeType -> Mappers.getModelMapper().map(autoRestCompositeType))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        List<ClientResponse> responseModels = codeModel.getOperationGroups().stream()
                .flatMap(og -> og.getOperations().stream())
                .map(m -> parseResponse(m, settings))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        String serviceClientName = codeModel.getLanguage().getJava().getName();
        String serviceClientDescription = codeModel.getInfo().getDescription();

        ServiceClient serviceClient = Mappers.getServiceClientMapper().map(codeModel);

        // TODO: Manager
//        Manager manager = Mappers.ManagerMapper.Map(codeModel);

        Map<String, PackageInfo> packageInfos = new HashMap<>();
        if (settings.shouldGenerateClientInterfaces() || !settings.shouldGenerateClientAsImpl() || settings.getImplementationSubpackage() == null || settings.getImplementationSubpackage().isEmpty()) {
            packageInfos.put(settings.getPackage(), new PackageInfo(
                    settings.getPackage(),
                    String.format("Package containing the classes for %s.\n%s", serviceClientName, serviceClientDescription)));
        }
        if (settings.shouldGenerateClientAsImpl() && settings.getImplementationSubpackage() != null && !settings.getImplementationSubpackage().isEmpty()) {
            String implementationPackage = settings.getPackage(settings.getImplementationSubpackage());
            if (!packageInfos.containsKey(implementationPackage)) {
                packageInfos.put(implementationPackage, new PackageInfo(
                        implementationPackage,
                        String.format("Package containing the implementations and inner classes for %s.\n%s", serviceClientName, serviceClientDescription)));
            }
        }
        if (!settings.isFluent() && settings.getModelsSubpackage() != null && !settings.getModelsSubpackage().isEmpty() && !settings.getModelsSubpackage().equals(settings.getImplementationSubpackage())) {
            String modelsPackage = settings.getPackage(settings.getModelsSubpackage());
            if (!packageInfos.containsKey(modelsPackage)) {
                packageInfos.put(modelsPackage, new PackageInfo(
                        modelsPackage,
                        String.format("Package containing the data models for %s.\n%s", serviceClientName, serviceClientDescription)));
            }
        }
        if (settings.getCustomTypes() != null && !settings.getCustomTypes().isEmpty() && settings.getCustomTypesSubpackage() != null && !settings.getCustomTypesSubpackage().isEmpty()) {
            String customTypesPackage = settings.getPackage(settings.getCustomTypesSubpackage());
            if (!packageInfos.containsKey(customTypesPackage)) {
                packageInfos.put(customTypesPackage, new PackageInfo(
                        customTypesPackage,
                        String.format("Package containing classes for %s.\n%s", serviceClientName, serviceClientDescription)));
            }
        }

        return new Client(serviceClientName,
                serviceClientDescription,
                enumTypes,
                exceptions,
                xmlSequenceWrappers,
                responseModels,
                models,
                new ArrayList<>(packageInfos.values()),
                null,
                serviceClient);
    }

    private List<XmlSequenceWrapper> parseXmlSequenceWrappers(CodeModel codeModel) {
        List<XmlSequenceWrapper> xmlSequenceWrappers = new ArrayList<>();
        JavaSettings settings = JavaSettings.getInstance();
        if (settings.shouldGenerateXmlSerialization()) {
            List<Operation> allMethods = codeModel.getOperationGroups().stream()
                .flatMap(og -> og.getOperations().stream())
                .collect(Collectors.toList());

            allMethods.forEach(operation -> operation.getRequest().getParameters().forEach(param -> {
                if (param.getSchema() instanceof ArraySchema) {
                    ArraySchema arraySchema = (ArraySchema) param.getSchema();
                    ListType listType = (ListType) Mappers.getSchemaMapper().map(arraySchema);
                    String xmlRootElementName = arraySchema.getSerialization().getXml().getName();
                    String xmlListElementName = arraySchema.getElementType().getSerialization().getXml().getName();
                    if (xmlSequenceWrappers.stream().noneMatch(
                        xmlSequenceWrapper -> xmlSequenceWrapper.getXmlListElementName().equals(xmlListElementName)
                            && xmlSequenceWrapper.getXmlRootElementName().equals(xmlRootElementName))) {
                        Set<String> packageImports = new HashSet<>();
                        packageImports.add("com.fasterxml.jackson.annotation.JsonCreator");
                        packageImports.add("com.fasterxml.jackson.annotation.JsonProperty");
                        packageImports.add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty");
                        packageImports.add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement");

                        listType.addImportsTo(packageImports, true);
                        boolean isCustomType = settings
                            .isCustomType(CodeNamer.toPascalCase(xmlRootElementName + "Wrapper"));
                        String packageName = settings.getPackage(isCustomType ? settings.getCustomTypesSubpackage() :
                            settings.getImplementationSubpackage());
                        xmlSequenceWrappers.add(new XmlSequenceWrapper(packageName, listType, xmlRootElementName,
                            xmlListElementName, packageImports));
                    }
                }
            }));
        }
        return xmlSequenceWrappers;
    }

    private ObjectSchema parseHeader(Operation operation, JavaSettings settings) {
        String name = operation.getOperationGroup().getLanguage().getJava().getName() + CodeNamer.toPascalCase(operation.getLanguage().getJava().getName()) + "Headers";
        Map<String, Schema> headerMap = new HashMap<>();
        for (Response response : operation.getResponses()) {
            if (response.getProtocol().getHttp().getHeaders() != null) {
                for (Header header : response.getProtocol().getHttp().getHeaders()) {
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
            headerSchema.getProperties().add(property);
        }
        return headerSchema;
    }

    private ClientResponse parseResponse(Operation method, JavaSettings settings) {
        ObjectSchema headerSchema = parseHeader(method, settings);
        if (headerSchema == null) {
            return null;
        }
        ClassType classType = ClientMapper.getClientResponseClassType(method, settings);
        String description = String.format("Contains all response data for the %s operation.", method.getLanguage().getJava().getName());
        IType headersType = Mappers.getSchemaMapper().map(headerSchema);
        IType bodyType = Mappers.getSchemaMapper().map(SchemaUtil.getLowestCommonParent(
                method.getResponses().stream().map(Response::getSchema).filter(Objects::nonNull).collect(Collectors.toList())));

        if (bodyType == null) {
            bodyType = PrimitiveType.Void;
        }
        return new ClientResponse(classType.getName(), classType.getPackage(), description, headersType, bodyType);
    }

    static ClassType getClientResponseClassType(Operation method, JavaSettings settings) {
        String name = method.getOperationGroup().getLanguage().getJava().getName() + CodeNamer.toPascalCase(method.getLanguage().getJava().getName()) + "Response";
        String packageName = settings.getPackage(settings.getModelsSubpackage());
        if (settings.isCustomType(name)) {
            packageName = settings.getPackage(settings.getCustomTypesSubpackage());
        }
        return new ClassType(packageName, name);
    }
}
