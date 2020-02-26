package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.Response;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MethodPageDetails;
import com.azure.autorest.model.clientmodel.MethodTransformationDetail;
import com.azure.autorest.model.clientmodel.ParameterMapping;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.clientmodel.ReturnValue;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.SchemaUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ClientMethodMapper implements IMapper<Operation, List<ClientMethod>> {
    private static ClientMethodMapper instance = new ClientMethodMapper();
    private Map<Operation, List<ClientMethod>> parsed = new HashMap<>();

    private ClientMethodMapper() {
    }

    public static ClientMethodMapper getInstance() {
        return instance;
    }

    @Override
    public List<ClientMethod> map(Operation operation) {
        JavaSettings settings = JavaSettings.getInstance();
        if (parsed.containsKey(operation)) {
            return parsed.get(operation);
        }

        ProxyMethod proxyMethod = Mappers.getProxyMethodMapper().map(operation);

        List<ClientMethod> methods = new ArrayList<>();

        List<ClientMethodParameter> parameters = new ArrayList<>();
        List<String> requiredParameterExpressions = new ArrayList<>();
        Map<String, String> validateExpressions = new HashMap<>();
        List<MethodTransformationDetail> methodTransformationDetails = new ArrayList<>();

        for (Parameter parameter : operation.getRequest().getParameters().stream().filter(p -> !p.isFlattened()).collect(Collectors.toList())) {
            ClientMethodParameter clientMethodParameter = Mappers.getClientParameterMapper().map(parameter);
            if (operation.getRequest().getSignatureParameters().contains(parameter)) {
                parameters.add(clientMethodParameter);
            }

            if (!(parameter.getSchema() instanceof ConstantSchema) && parameter.getGroupedBy() == null) {
                if (parameter.getImplementation() != Parameter.ImplementationLocation.CLIENT) {
                    // Validations
                    if (clientMethodParameter.getIsRequired() && !(clientMethodParameter.getClientType() instanceof PrimitiveType)) {
                        requiredParameterExpressions.add(clientMethodParameter.getName());
                    }
                    String validation = clientMethodParameter.getClientType().validate(clientMethodParameter.getName());
                    if (validation != null) {
                        validateExpressions.put(clientMethodParameter.getName(), validation);
                    }
                } else {
                    ProxyMethodParameter proxyParameter = Mappers.getProxyParameterMapper().map(parameter);
                    String exp = proxyParameter.getParameterReference();

                    if (proxyParameter.getIsRequired() && !(proxyParameter.getClientType() instanceof PrimitiveType)) {
                        requiredParameterExpressions.add(exp);
                    }

                    String validation = proxyParameter.getClientType().validate(exp);
                    if (validation != null) {
                        validateExpressions.put(exp, validation);
                    }
                }
            }

            // Transformations
            if ((parameter.getOriginalParameter() != null || parameter.getGroupedBy() != null)
                && !(parameter.getSchema() instanceof ConstantSchema)) {
                ClientMethodParameter outParameter;
                if (parameter.getOriginalParameter() != null) {
                    outParameter = Mappers.getClientParameterMapper().map(parameter.getOriginalParameter());
                } else {
                    outParameter = clientMethodParameter;
                }
                MethodTransformationDetail detail = methodTransformationDetails.stream()
                        .filter(d -> outParameter.getName().equals(d.getOutParameter().getName()))
                        .findFirst().orElse(null);
                if (detail == null) {
                    detail = new MethodTransformationDetail(outParameter, new ArrayList<>());
                    methodTransformationDetails.add(detail);
                }
                ParameterMapping mapping = new ParameterMapping();
                if (parameter.getGroupedBy() != null) {
                    mapping.setInputParameter(Mappers.getClientParameterMapper().map(parameter.getGroupedBy()));
                    mapping.setInputParameterProperty(parameter.getLanguage().getJava().getName());
                } else {
                    mapping.setInputParameter(clientMethodParameter);
                }
                if (parameter.getOriginalParameter() != null) {
                    mapping.setOutputParameterProperty(parameter.getTargetProperty().getLanguage().getJava().getName());
                }
                detail.getParameterMappings().add(mapping);
            }
        }

        final boolean generateClientMethodWithOnlyRequiredParameters = settings.getRequiredParameterClientMethods() && hasNonRequiredParameters(operation);

        if (operation.getExtensions() != null && operation.getExtensions().getXmsPageable() != null) {
            boolean isNextMethod = operation.getExtensions().getXmsPageable().getNextOperation() == operation;

            MethodPageDetails details = new MethodPageDetails(
                    CodeNamer.getPropertyName(operation.getExtensions().getXmsPageable().getNextLinkName()),
                    CodeNamer.getPropertyName(operation.getExtensions().getXmsPageable().getItemName()),
                    (isNextMethod || operation.getExtensions().getXmsPageable().getNextOperation() == null) ? null : Mappers.getClientMethodMapper().map(operation.getExtensions().getXmsPageable().getNextOperation())
                            .stream().findFirst().get());

            // Mono<SimpleResponse<Page>>
            Schema responseBodySchema = SchemaUtil.getLowestCommonParent(
                    operation.getResponses().stream().map(Response::getSchema).filter(Objects::nonNull).collect(Collectors.toList()));
            ClientModel responseBodyModel = Mappers.getModelMapper().map((ObjectSchema) responseBodySchema);
            IType listType = responseBodyModel.getProperties().stream()
                    .filter(p -> p.getSerializedName().equals(operation.getExtensions().getXmsPageable().getItemName()))
                    .findFirst().get().getWireType();
            IType elementType = ((ListType) listType).getElementType();
            IType asyncSinglePageReturnType = GenericType.Mono(GenericType.PagedResponse(elementType));
            IType asyncReturnType = GenericType.PagedFlux(elementType);
            IType syncReturnType = GenericType.PagedIterable(elementType);

            methods.add(new ClientMethod(
                    operation.getLanguage().getJava().getDescription(),
                    new ReturnValue(null, asyncSinglePageReturnType),
                    proxyMethod.getPagingAsyncSinglePageMethodName(),
                    parameters,
                    false,
                    ClientMethodType.PagingAsyncSinglePage,
                    proxyMethod,
                    validateExpressions,
                    requiredParameterExpressions,
                    false,
                    null,
                    details,
                    methodTransformationDetails));

            if (!isNextMethod) {
                if (settings.getSyncMethods() != JavaSettings.SyncMethodsGeneration.NONE) {
                    methods.add(new ClientMethod(
                            operation.getLanguage().getJava().getDescription(),
                            new ReturnValue(null, asyncReturnType),
                            proxyMethod.getSimpleAsyncMethodName(),
                            parameters,
                            false,
                            ClientMethodType.PagingAsync,
                            proxyMethod,
                            validateExpressions,
                            requiredParameterExpressions,
                            false,
                            null,
                            details,
                            methodTransformationDetails));

                    if (generateClientMethodWithOnlyRequiredParameters) {
                        methods.add(new ClientMethod(
                                operation.getLanguage().getJava().getDescription(),
                                new ReturnValue(null, asyncReturnType),
                                proxyMethod.getSimpleAsyncMethodName(),
                                parameters,
                                true,
                                ClientMethodType.PagingAsync,
                                proxyMethod,
                                validateExpressions,
                                requiredParameterExpressions,
                                false,
                                null,
                                details,
                                methodTransformationDetails));
                    }
                }

                if (settings.getSyncMethods() == JavaSettings.SyncMethodsGeneration.ALL) {
                    methods.add(new ClientMethod(
                            operation.getLanguage().getJava().getDescription(),
                            new ReturnValue(null, syncReturnType),
                            proxyMethod.getName(),
                            parameters,
                            false,
                            ClientMethodType.PagingSync,
                            proxyMethod,
                            validateExpressions,
                            requiredParameterExpressions,
                            false,
                            null,
                            details,
                            methodTransformationDetails));

                    if (generateClientMethodWithOnlyRequiredParameters) {
                        methods.add(new ClientMethod(
                                operation.getLanguage().getJava().getDescription(),
                                new ReturnValue(null, syncReturnType),
                                proxyMethod.getName(),
                                parameters,
                                true,
                                ClientMethodType.PagingSync,
                                proxyMethod,
                                validateExpressions,
                                requiredParameterExpressions,
                                false,
                                null,
                                details,
                                methodTransformationDetails));
                    }
                }
            }
        } else if (operation.getExtensions() != null && operation.getExtensions().isXmsLongRunningOperation() && settings.isFluent()) {
            // WithResponseAsync, with required and optional parameters
            methods.add(new ClientMethod(
                    operation.getLanguage().getJava().getDescription(),
                    new ReturnValue(null, proxyMethod.getReturnType().getClientType()),
                    proxyMethod.getSimpleAsyncRestResponseMethodName(),
                    parameters,
                    false,
                    ClientMethodType.SimpleAsyncRestResponse,
                    proxyMethod,
                    validateExpressions,
                    requiredParameterExpressions,
                    false,
                    null,
                    null,
                    methodTransformationDetails));

            IType responseBodyType = SchemaUtil.operationResponseType(operation);

            // Simple Async
            if (settings.getSyncMethods() != JavaSettings.SyncMethodsGeneration.NONE) {
                IType restAPIMethodReturnBodyClientType = responseBodyType.getClientType();
                IType asyncMethodReturnType;
                if (operation.getResponses().stream().anyMatch(r -> Boolean.TRUE.equals(r.getBinary()))) {
                    asyncMethodReturnType = GenericType.Flux(ClassType.ByteBuffer);
                } else if (restAPIMethodReturnBodyClientType != PrimitiveType.Void) {
                    asyncMethodReturnType = GenericType.Mono(restAPIMethodReturnBodyClientType);
                } else {
                    asyncMethodReturnType = GenericType.Mono(ClassType.Void);
                }

                methods.add(new ClientMethod(
                        operation.getLanguage().getJava().getDescription(),
                        new ReturnValue(null, asyncMethodReturnType),
                        proxyMethod.getSimpleAsyncMethodName(),
                        parameters,
                        false,
                        ClientMethodType.LongRunningAsync,
                        proxyMethod,
                        validateExpressions,
                        requiredParameterExpressions,
                        false,
                        null,
                        null,
                        methodTransformationDetails));
            }

            // Sync
            if (settings.getSyncMethods() == JavaSettings.SyncMethodsGeneration.ALL) {
                IType syncReturnType;
                if (operation.getResponses().stream().anyMatch(r -> Boolean.TRUE.equals(r.getBinary()))) {
                    syncReturnType = ClassType.InputStream;
                } else {
                    syncReturnType = responseBodyType.getClientType();
                }
                methods.add(new ClientMethod(
                        operation.getLanguage().getJava().getDescription(),
                        new ReturnValue(null, syncReturnType),
                        proxyMethod.getName(),
                        parameters,
                        false,
                        ClientMethodType.LongRunningSync,
                        proxyMethod,
                        validateExpressions,
                        requiredParameterExpressions,
                        false,
                        null,
                        null,
                        methodTransformationDetails));
            }

            if (generateClientMethodWithOnlyRequiredParameters) {
                // Simple Async
                if (settings.getSyncMethods() != JavaSettings.SyncMethodsGeneration.NONE) {
                    IType restAPIMethodReturnBodyClientType = responseBodyType.getClientType();
                    IType asyncMethodReturnType;
                    if (operation.getResponses().stream().anyMatch(r -> Boolean.TRUE.equals(r.getBinary()))) {
                        asyncMethodReturnType = GenericType.Flux(ClassType.ByteBuffer);
                    } else if (restAPIMethodReturnBodyClientType != PrimitiveType.Void) {
                        asyncMethodReturnType = GenericType.Mono(restAPIMethodReturnBodyClientType);
                    } else {
                        asyncMethodReturnType = GenericType.Mono(ClassType.Void);
                    }

                    methods.add(new ClientMethod(
                            operation.getLanguage().getJava().getDescription(),
                            new ReturnValue(null, asyncMethodReturnType),
                            proxyMethod.getSimpleAsyncMethodName(),
                            parameters,
                            true,
                            ClientMethodType.LongRunningAsync,
                            proxyMethod,
                            validateExpressions,
                            requiredParameterExpressions,
                            false,
                            null,
                            null,
                            methodTransformationDetails));
                }

                // Sync
                if (settings.getSyncMethods() == JavaSettings.SyncMethodsGeneration.ALL) {
                    IType syncReturnType;
                    if (operation.getResponses().stream().anyMatch(r -> Boolean.TRUE.equals(r.getBinary()))) {
                        syncReturnType = ClassType.InputStream;
                    } else {
                        syncReturnType = responseBodyType.getClientType();
                    }
                    methods.add(new ClientMethod(
                            operation.getLanguage().getJava().getDescription(),
                            new ReturnValue(null, syncReturnType),
                            proxyMethod.getName(),
                            parameters,
                            true,
                            ClientMethodType.LongRunningSync,
                            proxyMethod,
                            validateExpressions,
                            requiredParameterExpressions,
                            false,
                            null,
                            null,
                            methodTransformationDetails));
                }
            }
        } else {

            // WithResponseAsync, with required and optional parameters
            methods.add(new ClientMethod(
                    operation.getLanguage().getJava().getDescription(),
                    new ReturnValue(null, proxyMethod.getReturnType().getClientType()),
                    proxyMethod.getSimpleAsyncRestResponseMethodName(),
                    parameters,
                    false,
                    ClientMethodType.SimpleAsyncRestResponse,
                    proxyMethod,
                    validateExpressions,
                    requiredParameterExpressions,
                    false,
                    null,
                    null,
                    methodTransformationDetails));

            IType responseBodyType = SchemaUtil.operationResponseType(operation);

            // Simple Async
            if (settings.getSyncMethods() != JavaSettings.SyncMethodsGeneration.NONE) {
                IType restAPIMethodReturnBodyClientType = responseBodyType.getClientType();
                IType asyncMethodReturnType;
                if (operation.getResponses().stream().anyMatch(r -> Boolean.TRUE.equals(r.getBinary()))) {
                    asyncMethodReturnType = GenericType.Flux(ClassType.ByteBuffer);
                } else if (restAPIMethodReturnBodyClientType != PrimitiveType.Void) {
                    asyncMethodReturnType = GenericType.Mono(restAPIMethodReturnBodyClientType);
                } else {
                    asyncMethodReturnType = GenericType.Mono(ClassType.Void);
                }

                methods.add(new ClientMethod(
                        operation.getLanguage().getJava().getDescription(),
                        new ReturnValue(null, asyncMethodReturnType),
                        proxyMethod.getSimpleAsyncMethodName(),
                        parameters,
                        false,
                        ClientMethodType.SimpleAsync,
                        proxyMethod,
                        validateExpressions,
                        requiredParameterExpressions,
                        false,
                        null,
                        null,
                        methodTransformationDetails));

                if (generateClientMethodWithOnlyRequiredParameters) {
                    methods.add(new ClientMethod(
                            operation.getLanguage().getJava().getDescription(),
                            new ReturnValue(null, asyncMethodReturnType),
                            proxyMethod.getSimpleAsyncMethodName(),
                            parameters,
                            true,
                            ClientMethodType.SimpleAsync,
                            proxyMethod,
                            validateExpressions,
                            requiredParameterExpressions,
                            false,
                            null,
                            null,
                            methodTransformationDetails));
                }
            }

            // Sync
            if (settings.getSyncMethods() == JavaSettings.SyncMethodsGeneration.ALL) {
                IType syncReturnType;
                if (operation.getResponses().stream().anyMatch(r -> Boolean.TRUE.equals(r.getBinary()))) {
                    syncReturnType = ClassType.InputStream;
                } else {
                    syncReturnType = responseBodyType.getClientType();
                }
                methods.add(new ClientMethod(
                        operation.getLanguage().getJava().getDescription(),
                        new ReturnValue(null, syncReturnType),
                        proxyMethod.getName(),
                        parameters,
                        false,
                        ClientMethodType.SimpleSync,
                        proxyMethod,
                        validateExpressions,
                        requiredParameterExpressions,
                        false,
                        null,
                        null,
                        methodTransformationDetails));

                if (generateClientMethodWithOnlyRequiredParameters) {
                    methods.add(new ClientMethod(
                            operation.getLanguage().getJava().getDescription(),
                            new ReturnValue(null, syncReturnType),
                            proxyMethod.getName(),
                            parameters,
                            true,
                            ClientMethodType.SimpleSync,
                            proxyMethod,
                            validateExpressions,
                            requiredParameterExpressions,
                            false,
                            null,
                            null,
                            methodTransformationDetails));
                }
            }
        }

        parsed.put(operation, methods);
        return methods;
    }

    public static boolean nonNullNextLink(Operation operation) {
        return operation.getExtensions().getXmsPageable().getNextLinkName() != null && !operation.getExtensions().getXmsPageable().getNextLinkName().isEmpty();
    }

    private static boolean hasNonRequiredParameters(Operation operation) {
        return operation.getRequest().getParameters().stream().anyMatch(p -> p.getImplementation() == Parameter.ImplementationLocation.METHOD && !p.isRequired() && !(p.getSchema() instanceof ConstantSchema))
                && operation.getRequest().getParameters().stream().noneMatch(Parameter::isFlattened);   // for now, ignore operation with flattened parameters
    }

//
//    private static void addRequiredProperties(IType clientType, String hierarchy, List<String> expressions) {
//        if (clientType instanceof ClassType) {
//            ClientModel typeModel = ClientModels.Instance.getModel(((ClassType) clientType).getName());
//            if (typeModel != null) {
//                for (ClientModelProperty property : typeModel.getProperties()) {
//                    if (property.isRequired() && !property.getIsConstant() && !property.getIsReadOnly()) {
//                        String exp = hierarchy + "." + property.getGetterName();
//                        expressions.add(exp);
//                        addRequiredProperties(property.getClientType(), exp, expressions);
//                    }
//                }
//            }
//        }
//    }
}
