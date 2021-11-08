package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.Request;
import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.model.codemodel.Response;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.JavaSettings.SyncMethodsGeneration;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethod.Builder;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.model.clientmodel.MethodPageDetails;
import com.azure.autorest.model.clientmodel.MethodPollingDetails;
import com.azure.autorest.model.clientmodel.MethodTransformationDetail;
import com.azure.autorest.model.clientmodel.ParameterMapping;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.clientmodel.ReturnValue;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.SchemaUtil;
import com.azure.core.http.HttpMethod;
import com.azure.core.util.CoreUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ClientMethodMapper implements IMapper<Operation, List<ClientMethod>> {
    private static final ClientMethodMapper instance = new ClientMethodMapper();

    private static final Pattern ANYTHING_THEN_PERIOD = Pattern.compile(".*\\.");

    private final Map<Operation, List<ClientMethod>> parsed = new ConcurrentHashMap<>();

    protected ClientMethodMapper() {
    }

    public static ClientMethodMapper getInstance() {
        return instance;
    }

    @Override
    public List<ClientMethod> map(Operation operation) {
        List<ClientMethod> clientMethods = parsed.get(operation);
        if (clientMethods != null) {
            return clientMethods;
        }

        clientMethods = createClientMethods(operation);
        parsed.put(operation, clientMethods);

        return clientMethods;
    }

    private List<ClientMethod> createClientMethods(Operation operation) {
        JavaSettings settings = JavaSettings.getInstance();

        Map<Request, ProxyMethod> proxyMethods = Mappers.getProxyMethodMapper().map(operation);

        List<ClientMethod> methods = new ArrayList<>();

        ClientMethod.Builder builder = getClientMethodBuilder()
            .description(operation.getLanguage().getJava().getDescription())
            .clientReference((operation.getOperationGroup() == null || operation.getOperationGroup().getLanguage().getJava().getName().isEmpty()) ? "this": "this.client");

        IType asyncRestResponseReturnType;
        IType asyncReturnType = PrimitiveType.Void;
        IType syncReturnType = PrimitiveType.Void;
        IType syncReturnWithResponse;

        if (operation.getExtensions() != null && operation.getExtensions().getXmsPageable() != null) {
            // Mono<SimpleResponse<Page>>
            Schema responseBodySchema = SchemaUtil.getLowestCommonParent(
                operation.getResponses().stream().map(Response::getSchema).filter(Objects::nonNull).collect(Collectors.toList()));
            if (!(responseBodySchema instanceof ObjectSchema)) {
                throw new IllegalArgumentException(String.format("[JavaCheck/SchemaError] no common parent found for client models %s",
                    operation.getResponses().stream().map(Response::getSchema).filter(Objects::nonNull).map(s -> s.getLanguage().getJava().getName()).collect(Collectors.toList())));
            }
            ClientModel responseBodyModel = Mappers.getModelMapper().map((ObjectSchema) responseBodySchema);
            Optional<ClientModelProperty> itemPropertyOpt = responseBodyModel.getProperties().stream()
                .filter(p -> p.getSerializedName().equals(operation.getExtensions().getXmsPageable().getItemName()))
                .findFirst();
            if (!itemPropertyOpt.isPresent()) {
                throw new IllegalArgumentException(String.format("[JavaCheck/SchemaError] item name %s not found among properties of client model %s",
                    operation.getExtensions().getXmsPageable().getItemName(), responseBodyModel.getName()));
            }
            IType listType = itemPropertyOpt.get().getWireType();
            IType elementType = ((ListType) listType).getElementType();
            if (settings.isLowLevelClient()) {
                asyncRestResponseReturnType = createProtocolPagedRestResponseReturnType();
                asyncReturnType = createProtocolPagedAsyncReturnType();
                syncReturnType = createProtocolPagedSyncReturnType();
            } else {
                asyncRestResponseReturnType = createPagedRestResponseReturnType(elementType);
                asyncReturnType = createPagedAsyncReturnType(elementType);
                syncReturnType = createPagedSyncReturnType(elementType);
            }
        } else {
            asyncRestResponseReturnType = null;
            IType responseBodyType = SchemaUtil.getOperationResponseType(operation);
            if (settings.isLowLevelClient()) {
                if (responseBodyType instanceof ClassType || responseBodyType instanceof ListType || responseBodyType instanceof MapType) {
                    responseBodyType = ClassType.BinaryData;
                } else if (responseBodyType instanceof EnumType) {
                    responseBodyType = ClassType.String;
                }
            }
            IType restAPIMethodReturnBodyClientType = responseBodyType.getClientType();
            if (operation.getResponses().stream().anyMatch(r -> Boolean.TRUE.equals(r.getBinary()))
                && !settings.isLowLevelClient() /* TODO: #1059 */) {
                asyncReturnType = createAsyncBinaryReturnType();
            } else if (restAPIMethodReturnBodyClientType != PrimitiveType.Void) {
                asyncReturnType = createAsyncBodyReturnType(restAPIMethodReturnBodyClientType);
            } else {
                asyncReturnType = createAsyncVoidReturnType();
            }
            if (operation.getResponses().stream().anyMatch(r -> Boolean.TRUE.equals(r.getBinary()))
                && !settings.isLowLevelClient() /* TODO: #1059 */) {
                syncReturnType = ClassType.InputStream;
            } else {
                syncReturnType = responseBodyType.getClientType();
            }
        }

        if (syncReturnType == ClassType.InputStream) {
            syncReturnWithResponse = ClassType.StreamResponse;
        } else {
            syncReturnWithResponse = createSyncReturnWithResponseType(syncReturnType, operation, settings);
        }

        // Low-level client only requires one request per operation
        List<Request> requests = operation.getRequests();
        if (settings.isLowLevelClient()) {
            requests = Collections.singletonList(requests.get(0));
        }

        for (Request request : requests) {
            ProxyMethod proxyMethod = proxyMethods.get(request);
            builder.proxyMethod(proxyMethod);

            List<ClientMethodParameter> parameters = new ArrayList<>();
            List<String> requiredParameterExpressions = new ArrayList<>();
            Map<String, String> validateExpressions = new HashMap<>();
            List<MethodTransformationDetail> methodTransformationDetails = new ArrayList<>();

            List<Parameter> codeModelParameters;
            if (settings.isLowLevelClient()) {
                // Required path and body parameters are allowed
                codeModelParameters = request.getParameters().stream().filter(p -> p.isRequired() &&
                        (p.getProtocol().getHttp().getIn() == RequestParameterLocation.Path ||
                            p.getProtocol().getHttp().getIn() == RequestParameterLocation.Body))
                    .collect(Collectors.toList());
            } else {
                codeModelParameters = request.getParameters().stream().filter(p -> !p.isFlattened()).collect(Collectors.toList());
            }

            Set<Parameter> originalParameters = new HashSet<>();
            for (Parameter parameter : codeModelParameters) {
                ClientMethodParameter clientMethodParameter = Mappers.getClientParameterMapper().map(parameter);

                if (request.getProtocol() != null
                    && request.getProtocol().getHttp() != null
                    && request.getProtocol().getHttp().getMediaTypes() != null
                    && request.getProtocol().getHttp().getMediaTypes().stream().anyMatch(mediaType -> mediaType.equals(
                    "application/json-patch+json"))) {
                    clientMethodParameter = CustomClientParameterMapper.getInstance().map(parameter);
                }

                if (request.getSignatureParameters().contains(parameter)) {
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
                    && !(parameter.getSchema() instanceof ConstantSchema) && !settings.isLowLevelClient()) {
                    ClientMethodParameter outParameter;
                    if (parameter.getOriginalParameter() != null) {
                        originalParameters.add(parameter.getOriginalParameter());
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
                        ClientModel groupModel = Mappers.getModelMapper().map((ObjectSchema) parameter.getGroupedBy().getSchema());
                        ClientModelProperty inputProperty = groupModel.getProperties().stream()
                            .filter(p -> parameter.getLanguage().getJava().getName().equals(p.getName()))
                            .findFirst().get();
                        mapping.setInputParameterProperty(inputProperty);
                    } else {
                        mapping.setInputParameter(clientMethodParameter);
                    }
                    if (parameter.getOriginalParameter() != null) {
                        mapping.setOutputParameterProperty(parameter.getTargetProperty().getLanguage().getJava().getName());
                    }
                    detail.getParameterMappings().add(mapping);
                }
            }
            // handle the case that the flattened parameter is model with all its properties read-only
            // in this case, it is not original parameter from any other parameters
            for (Parameter parameter : request.getParameters().stream()
                .filter(p -> p.isFlattened() && p.getProtocol() != null && p.getProtocol().getHttp() != null)   // flattened proxy parameter
                .filter(p -> !originalParameters.contains(p))                                                   // but not original parameter from any other parameters
                .collect(Collectors.toList())) {
                ClientMethodParameter outParameter = Mappers.getClientParameterMapper().map(parameter);
                methodTransformationDetails.add(new MethodTransformationDetail(outParameter, new ArrayList<>()));
            }

            if (settings.isLowLevelClient()) {
                ClientMethodParameter requestOptions = new ClientMethodParameter.Builder()
                    .description("The options to configure the HTTP request before HTTP client sends it")
                    .wireType(ClassType.RequestOptions)
                    .name("requestOptions")
                    .location(RequestParameterLocation.None)
                    .isConstant(false)
                    .isRequired(false)
                    .fromClient(false)
                    .annotations(Collections.emptyList())
                    .build();
                parameters.add(requestOptions);
            }

            final boolean generateClientMethodWithOnlyRequiredParameters = settings.getRequiredParameterClientMethods() && hasNonRequiredParameters(parameters);

            builder.parameters(parameters)
                .requiredNullableParameterExpressions(requiredParameterExpressions)
                .validateExpressions(validateExpressions)
                .methodTransformationDetails(methodTransformationDetails)
                .methodPageDetails(null);

            if (operation.getExtensions() != null
                && operation.getExtensions().getXmsPageable() != null
                && shouldGeneratePagingMethods()) {
                String pageableItemName = getPageableItemName(operation);
                if (pageableItemName != null) {
                    boolean isNextMethod = operation.getExtensions().getXmsPageable().getNextOperation() == operation;

                    IType lroIntermediateType = null;
                    if (operation.getExtensions().isXmsLongRunningOperation() && !isNextMethod) {
                        lroIntermediateType = SchemaUtil.getOperationResponseType(operation);
                    }

                    List<ClientMethod> nextMethods = (isNextMethod || operation.getExtensions().getXmsPageable().getNextOperation() == null) ? null : Mappers.getClientMethodMapper().map(operation.getExtensions().getXmsPageable().getNextOperation());

                    MethodPageDetails details = new MethodPageDetails(
                        CodeNamer.getPropertyName(operation.getExtensions().getXmsPageable().getNextLinkName()),
                        pageableItemName,
                        (nextMethods == null) ? null : nextMethods.stream().findFirst().get(),
                        lroIntermediateType,
                        operation.getExtensions().getXmsPageable().getNextLinkName(),
                        operation.getExtensions().getXmsPageable().getItemName());
                    builder.methodPageDetails(details);

                    if (!(!settings.getRequiredParameterClientMethods() && settings.isContextClientMethodParameter()
                        && SyncMethodsGeneration.NONE.equals(settings.getSyncMethods()))) {
                        methods.add(builder
                            .returnValue(createPagingAsyncSinglePageReturnValue(operation, asyncRestResponseReturnType, syncReturnType))
                            .name(proxyMethod.getPagingAsyncSinglePageMethodName())
                            .onlyRequiredParameters(false)
                            .type(ClientMethodType.PagingAsyncSinglePage)
                            .isGroupedParameterRequired(false)
                            .methodVisibility(methodVisibility(ClientMethodType.PagingAsyncSinglePage, false))
                            .build());
                    }
                    if (settings.isContextClientMethodParameter()) {
                        builder.methodVisibility(methodVisibility(ClientMethodType.PagingAsyncSinglePage, true));
                        addClientMethodWithContext(methods, builder, proxyMethod, parameters,
                            ClientMethodType.PagingAsyncSinglePage, proxyMethod.getPagingAsyncSinglePageMethodName(),
                            createPagingAsyncSinglePageReturnValue(operation, asyncRestResponseReturnType, syncReturnType),
                            details);
                    }

                    if (!isNextMethod) {
                        if (settings.getSyncMethods() != JavaSettings.SyncMethodsGeneration.NONE) {
                            methods.add(builder
                                .returnValue(createPagingAsyncReturnValue(operation, asyncReturnType, syncReturnType))
                                .name(proxyMethod.getSimpleAsyncMethodName())
                                .onlyRequiredParameters(false)
                                .type(ClientMethodType.PagingAsync)
                                .isGroupedParameterRequired(false)
                                .methodVisibility(methodVisibility(ClientMethodType.PagingAsync, false))
                                .build());

                            if (generateClientMethodWithOnlyRequiredParameters) {
                                methods.add(builder
                                    .onlyRequiredParameters(true)
                                    .build());
                            }

                            if (settings.isContextClientMethodParameter()) {
                                MethodPageDetails detailsWithContext = details;
                                if (nextMethods != null) {
                                    detailsWithContext = new MethodPageDetails(
                                        CodeNamer.getPropertyName(operation.getExtensions().getXmsPageable().getNextLinkName()),
                                        pageableItemName,
                                        nextMethods.stream()
                                            .filter(m -> m.getType() == ClientMethodType.PagingAsyncSinglePage)
                                            .filter(m -> m.getMethodParameters().stream().anyMatch(p -> getContextType().equals(p.getClientType()))).findFirst().get(),
                                        lroIntermediateType,
                                        operation.getExtensions().getXmsPageable().getNextLinkName(),
                                        operation.getExtensions().getXmsPageable().getItemName());
                                }

                                addClientMethodWithContext(methods,
                                    builder.methodVisibility(methodVisibility(ClientMethodType.PagingAsync, true)),
                                    proxyMethod, parameters,
                                    ClientMethodType.PagingAsync, proxyMethod.getSimpleAsyncMethodName(),
                                    createPagingAsyncReturnValue(operation, asyncReturnType, syncReturnType),
                                    detailsWithContext);
                            }
                        }

                        if (settings.getSyncMethods() == JavaSettings.SyncMethodsGeneration.ALL) {
                            builder.methodVisibility(methodVisibility(ClientMethodType.PagingSync, false));

                            builder
                                .returnValue(createPagingSyncReturnValue(operation, syncReturnType))
                                .name(proxyMethod.getName())
                                .onlyRequiredParameters(false)
                                .type(ClientMethodType.PagingSync)
                                .isGroupedParameterRequired(false)
                                .build();

                            if (!settings.isFluent() || !settings.isContextClientMethodParameter() || !generateClientMethodWithOnlyRequiredParameters) {
                                // if context parameter is required, that method will do the overload with max parameters
                                methods.add(builder.build());
                            }

                            if (generateClientMethodWithOnlyRequiredParameters) {
                                methods.add(builder
                                    .onlyRequiredParameters(true)
                                    .build());
                            }

                            if (settings.isContextClientMethodParameter()) {
                                addClientMethodWithContext(methods, builder.methodVisibility(methodVisibility(ClientMethodType.PagingSync, true)), parameters);
                            }
                        }
                    }
                }
            } else if (operation.getExtensions() != null && operation.getExtensions().isXmsLongRunningOperation()
                && (settings.isFluent() || settings.getPollingConfig("default") != null)
                && !syncReturnType.equals(ClassType.InputStream)) {         // temporary skip InputStream, no idea how to do this in PollerFlux
                // WithResponseAsync, with required and optional parameters
                // TODO: Build protocol LRO methods
                methods.add(builder
                    .returnValue(createSimpleAsyncRestResponseReturnValue(operation, proxyMethod, syncReturnType))
                    .name(proxyMethod.getSimpleAsyncRestResponseMethodName())
                    .onlyRequiredParameters(false)
                    .type(ClientMethodType.SimpleAsyncRestResponse)
                    .isGroupedParameterRequired(false)
                    .methodVisibility(methodVisibility(ClientMethodType.SimpleAsyncRestResponse, false))
                    .build());

                if (settings.isContextClientMethodParameter()) {
                    addClientMethodWithContext(methods,
                        builder.methodVisibility(methodVisibility(ClientMethodType.SimpleAsyncRestResponse, true)),
                        parameters);
                }

                JavaSettings.PollingDetails pollingDetails = settings.getPollingConfig(proxyMethod.getOperationId());

                MethodPollingDetails methodPollingDetails = null;
                if (pollingDetails != null) {
                    methodPollingDetails = new MethodPollingDetails(
                        pollingDetails.getStrategy(),
                        getPollingIntermediateType(pollingDetails, syncReturnType),
                        getPollingFinalType(pollingDetails, syncReturnType),
                        pollingDetails.getPollIntervalInSeconds());
                    builder = builder.methodPollingDetails(methodPollingDetails);
                }

                if (settings.getSyncMethods() != JavaSettings.SyncMethodsGeneration.NONE) {
                    // begin method async
                    methods.add(builder
                        .returnValue(createLongRunningBeginAsyncReturnValue(operation, proxyMethod, syncReturnType, methodPollingDetails))
                        .name("begin" + CodeNamer.toPascalCase(proxyMethod.getSimpleAsyncMethodName()))
                        .onlyRequiredParameters(false)
                        .type(ClientMethodType.LongRunningBeginAsync)
                        .isGroupedParameterRequired(false)
                        .methodVisibility(methodVisibility(ClientMethodType.LongRunningBeginAsync, false))
                        .build());

                    if (settings.isContextClientMethodParameter()) {
                        addClientMethodWithContext(methods,
                            builder.methodVisibility(methodVisibility(ClientMethodType.LongRunningBeginAsync, true)),
                            parameters);
                    }
                }

                if (settings.getSyncMethods() == JavaSettings.SyncMethodsGeneration.ALL) {
                    // begin method sync
                    methods.add(builder
                        .returnValue(createLongRunningBeginSyncReturnValue(operation, proxyMethod, syncReturnType, methodPollingDetails))
                        .name("begin" + CodeNamer.toPascalCase(proxyMethod.getName()))
                        .onlyRequiredParameters(false)
                        .type(ClientMethodType.LongRunningBeginSync)
                        .isGroupedParameterRequired(false)
                        .methodVisibility(methodVisibility(ClientMethodType.LongRunningBeginSync, false))
                        .build());

                    if (settings.isContextClientMethodParameter()) {
                        addClientMethodWithContext(methods, builder.methodVisibility(methodVisibility(ClientMethodType.LongRunningBeginSync, true)), parameters);
                    }
                }

                if (settings.isFluent()
                    && settings.getSyncMethods() != JavaSettings.SyncMethodsGeneration.NONE) {
                    methods.add(builder
                        .returnValue(createLongRunningAsyncReturnValue(operation, asyncReturnType, syncReturnType))
                        .name(proxyMethod.getSimpleAsyncMethodName())
                        .onlyRequiredParameters(false)
                        .type(ClientMethodType.LongRunningAsync)
                        .isGroupedParameterRequired(false)
                        .methodVisibility(methodVisibility(ClientMethodType.LongRunningAsync, false))
                        .build());

                    if (generateClientMethodWithOnlyRequiredParameters) {
                        methods.add(builder
                            .onlyRequiredParameters(true)
                            .build());
                    }

                    if (settings.isContextClientMethodParameter()) {
                        addClientMethodWithContext(methods,
                            builder.methodVisibility(methodVisibility(ClientMethodType.LongRunningAsync, true)),
                            parameters);
                    }
                }

                if (settings.isFluent()
                    && settings.getSyncMethods() == JavaSettings.SyncMethodsGeneration.ALL) {
                    builder.methodVisibility(VISIBLE);

                    methods.add(builder
                        .returnValue(createLongRunningSyncReturnValue(operation, syncReturnType))
                        .name(proxyMethod.getName())
                        .onlyRequiredParameters(false)
                        .type(ClientMethodType.LongRunningSync)
                        .isGroupedParameterRequired(false)
                        .build());

                    if (generateClientMethodWithOnlyRequiredParameters) {
                        methods.add(builder
                            .onlyRequiredParameters(true)
                            .build());
                    }

                    if (settings.isContextClientMethodParameter()) {
                        addClientMethodWithContext(methods, builder, parameters);
                    }
                }
            } else {
                // WithResponseAsync, with required and optional parameters
                if (!(!settings.getRequiredParameterClientMethods() && settings.isContextClientMethodParameter()
                    && SyncMethodsGeneration.NONE.equals(settings.getSyncMethods()))) {

                    methods.add(builder
                        .parameters(parameters) // update builder parameters to include context
                        .returnValue(createSimpleAsyncRestResponseReturnValue(operation, proxyMethod, syncReturnType))
                        .name(proxyMethod.getSimpleAsyncRestResponseMethodName())
                        .onlyRequiredParameters(false)
                        .type(ClientMethodType.SimpleAsyncRestResponse)
                        .isGroupedParameterRequired(false)
                        .methodVisibility(methodVisibility(ClientMethodType.SimpleAsyncRestResponse, false))
                        .build());
                }

                if (settings.isContextClientMethodParameter()) {
                    addClientMethodWithContext(methods,
                        builder.methodVisibility(methodVisibility(ClientMethodType.SimpleAsyncRestResponse, true)),
                        proxyMethod, parameters,
                        ClientMethodType.SimpleAsyncRestResponse, proxyMethod.getSimpleAsyncRestResponseMethodName(),
                        createSimpleAsyncRestResponseReturnValue(operation, proxyMethod, syncReturnType),
                        null);
                }

                if (settings.getSyncMethods() != JavaSettings.SyncMethodsGeneration.NONE) {
                    methods.add(builder
                        .returnValue(createSimpleAsyncReturnValue(operation, asyncReturnType, syncReturnType))
                        .name(proxyMethod.getSimpleAsyncMethodName())
                        .onlyRequiredParameters(false)
                        .type(ClientMethodType.SimpleAsync)
                        .isGroupedParameterRequired(false)
                        .methodVisibility(methodVisibility(ClientMethodType.SimpleAsync, false))
                        .build());

                    if (generateClientMethodWithOnlyRequiredParameters) {
                        methods.add(builder
                            .onlyRequiredParameters(true)
                            .build());
                    }

                    if (settings.isContextClientMethodParameter()) {
                        addClientMethodWithContext(methods,
                            builder.methodVisibility(methodVisibility(ClientMethodType.SimpleAsync, true)),
                            parameters);
                    }
                }

                if (settings.getSyncMethods() == JavaSettings.SyncMethodsGeneration.ALL) {
                    builder
                        .returnValue(createSimpleSyncReturnValue(operation, syncReturnType))
                        .name(proxyMethod.getName())
                        .onlyRequiredParameters(false)
                        .type(ClientMethodType.SimpleSync)
                        .isGroupedParameterRequired(false)
                        .methodVisibility(methodVisibility(ClientMethodType.SimpleSync, false));

                    if (!settings.isFluent() || !settings.isContextClientMethodParameter() || !generateClientMethodWithOnlyRequiredParameters) {
                        // if context parameter is required, that method will do the overload with max parameters
                        methods.add(builder.build());
                    }

                    if (generateClientMethodWithOnlyRequiredParameters) {
                        methods.add(builder
                            .onlyRequiredParameters(true)
                            .build());
                    }

                    if (settings.isContextClientMethodParameter()) {
                        builder.type(ClientMethodType.SimpleSyncRestResponse)
                            .onlyRequiredParameters(false)
                            .name(proxyMethod.getSimpleRestResponseMethodName())
                            .returnValue(createSimpleSyncRestResponseReturnValue(operation, syncReturnWithResponse))
                            .methodVisibility(methodVisibility(ClientMethodType.SimpleSyncRestResponse, false));

                        if (settings.isLowLevelClient()) {
                            // SimpleSyncRestResponse with RequestOptions but without Context
                            methods.add(builder.build());
                        }

                        addClientMethodWithContext(methods,
                                builder.methodVisibility(methodVisibility(ClientMethodType.SimpleSyncRestResponse, true)),
                                parameters);
                    }
                }
            }
        }
        return methods.stream()
            .filter(m -> m.getMethodVisibility() != NOT_GENERATE)
            .collect(Collectors.toList());
    }

    protected IType getContextType() {
        return ClassType.Context;
    }

    protected IType createSyncReturnWithResponseType(IType syncReturnType, Operation operation, JavaSettings settings) {
        // method with schema in headers would require a ClientResponse, only high level
        return SchemaUtil.responseContainsHeaderSchemas(operation) && !settings.isLowLevelClient() ?
                ClientMapper.getClientResponseClassType(operation, settings) :
                GenericType.Response(syncReturnType);
    }

    protected ReturnValue createSimpleSyncRestResponseReturnValue(Operation operation, IType syncReturnWithResponse) {
        return new ReturnValue(returnTypeDescription(operation, syncReturnWithResponse,
                syncReturnWithResponse), syncReturnWithResponse);
    }

    protected ReturnValue createSimpleAsyncRestResponseReturnValue(Operation operation, ProxyMethod proxyMethod, IType syncReturnType) {
        return new ReturnValue(returnTypeDescription(operation, proxyMethod.getReturnType().getClientType(), syncReturnType),
                proxyMethod.getReturnType().getClientType());
    }

    protected ReturnValue createSimpleSyncReturnValue(Operation operation, IType syncReturnType) {
        return new ReturnValue(returnTypeDescription(operation, syncReturnType, syncReturnType),
                syncReturnType);
    }

    protected ReturnValue createSimpleAsyncReturnValue(Operation operation, IType asyncReturnType, IType syncReturnType) {
        return new ReturnValue(returnTypeDescription(operation, asyncReturnType, syncReturnType),
                asyncReturnType);
    }

    protected ReturnValue createLongRunningSyncReturnValue(Operation operation, IType syncReturnType) {
        return new ReturnValue(returnTypeDescription(operation, syncReturnType, syncReturnType),
                syncReturnType);
    }

    protected ReturnValue createLongRunningAsyncReturnValue(Operation operation, IType asyncReturnType, IType syncReturnType) {
        return new ReturnValue(returnTypeDescription(operation, asyncReturnType, syncReturnType),
                asyncReturnType);
    }

    private ReturnValue createLongRunningBeginSyncReturnValue(Operation operation, ProxyMethod proxyMethod, IType syncReturnType, MethodPollingDetails pollingDetails) {
        if (JavaSettings.getInstance().isFluent()) {
            return new ReturnValue(returnTypeDescription(operation, proxyMethod.getReturnType().getClientType(), syncReturnType),
                    GenericType.SyncPoller(GenericType.PollResult(syncReturnType.asNullable()), syncReturnType.asNullable()));
        } else {
            return new ReturnValue(returnTypeDescription(operation, proxyMethod.getReturnType().getClientType(), pollingDetails.getFinalType()),
                    GenericType.SyncPoller(pollingDetails.getIntermediateType(), pollingDetails.getFinalType()));
        }
    }

    protected ReturnValue createLongRunningBeginAsyncReturnValue(Operation operation, ProxyMethod proxyMethod, IType syncReturnType, MethodPollingDetails pollingDetails) {
        if (JavaSettings.getInstance().isFluent()) {
            return new ReturnValue(returnTypeDescription(operation, proxyMethod.getReturnType().getClientType(), syncReturnType),
                    GenericType.PollerFlux(GenericType.PollResult(syncReturnType.asNullable()), syncReturnType.asNullable()));
        } else {
            return new ReturnValue(returnTypeDescription(operation, proxyMethod.getReturnType().getClientType(), pollingDetails.getFinalType()),
                    GenericType.PollerFlux(pollingDetails.getIntermediateType(), pollingDetails.getFinalType()));
        }
    }

    protected ReturnValue createPagingSyncReturnValue(Operation operation, IType syncReturnType) {
        return new ReturnValue(returnTypeDescription(operation, syncReturnType, syncReturnType),
                syncReturnType);
    }

    protected ReturnValue createPagingAsyncReturnValue(Operation operation, IType asyncReturnType, IType syncReturnType) {
        return new ReturnValue(returnTypeDescription(operation, asyncReturnType, syncReturnType),
                asyncReturnType);
    }

    protected ReturnValue createPagingAsyncSinglePageReturnValue(Operation operation, IType asyncRestResponseReturnType, IType syncReturnType) {
        return new ReturnValue(returnTypeDescription(operation, asyncRestResponseReturnType, syncReturnType),
                asyncRestResponseReturnType);
    }

    protected ReturnValue createProtocolSimpleAsyncReturnValue(Operation operation, ProxyMethod proxyMethod, IType syncReturnType) {
        return new ReturnValue(returnTypeDescription(operation, proxyMethod.getReturnType().getClientType(), syncReturnType),
                proxyMethod.getReturnType().getClientType());
    }

    protected boolean shouldGeneratePagingMethods() {
        return true;
    }

    protected IType createAsyncVoidReturnType() {
        return GenericType.Mono(ClassType.Void);
    }

    protected IType createAsyncBodyReturnType(IType restAPIMethodReturnBodyClientType) {
        return GenericType.Mono(restAPIMethodReturnBodyClientType);
    }

    protected IType createAsyncBinaryReturnType() {
        return GenericType.Flux(ClassType.ByteBuffer);
    }

    protected IType createPagedSyncReturnType(IType elementType) {
        return GenericType.PagedIterable(elementType);
    }

    protected IType createPagedAsyncReturnType(IType elementType) {
        return GenericType.PagedFlux(elementType);
    }

    protected IType createPagedRestResponseReturnType(IType elementType) {
        return GenericType.Mono(GenericType.PagedResponse(elementType));
    }

    protected IType createProtocolPagedSyncReturnType() {
        return GenericType.PagedIterable(ClassType.BinaryData);
    }

    protected IType createProtocolPagedAsyncReturnType() {
        return GenericType.PagedFlux(ClassType.BinaryData);
    }

    protected IType createProtocolPagedRestResponseReturnType() {
        return GenericType.Mono(GenericType.PagedResponse(ClassType.BinaryData));
    }

    protected ClientMethod.Builder getClientMethodBuilder() {
        return new ClientMethod.Builder();
    }

    protected static final JavaVisibility NOT_VISIBLE = JavaVisibility.Private;
    protected static final JavaVisibility VISIBLE = JavaVisibility.Public;
    protected static final JavaVisibility NOT_GENERATE = null;

    /**
     * Extension for configuration on method visibility.
     * <p>
     * ClientMethodTemplate.writeMethod (and whether it is called) would also decide the visibility in generated code.
     *
     * @param methodType the type of the client method.
     * @param hasContextParameter whether the method has Context parameter.
     * @return method visibility, null if do not generate.
     */
    protected JavaVisibility methodVisibility(ClientMethodType methodType, boolean hasContextParameter) {
        if (JavaSettings.getInstance().isLowLevelClient()) {
            /*
            Rule for LLC

            1. Only generate "WithResponse" method for simple API (hence exclude SimpleAsync and SimpleSync).
            2. For sync method, Context is included in "RequestOptions", hence do not generate method with Context parameter.
            3. For async method, Context is not included in the first place (this rule is valid for all clients).
             */

            return (methodType == ClientMethodType.SimpleAsync || methodType == ClientMethodType.SimpleSync
                    || (methodType == ClientMethodType.PagingSync && hasContextParameter)
                    || (methodType == ClientMethodType.LongRunningBeginSync && hasContextParameter)
                    || (methodType == ClientMethodType.SimpleSyncRestResponse && hasContextParameter))
                    ? NOT_GENERATE
                    : VISIBLE;
        } else {
            return VISIBLE;
        }
    }

    private static final ClientMethodParameter CONTEXT_PARAM = new ClientMethodParameter.Builder()
            .description("The context to associate with this operation.")
            .wireType(ClassType.Context)
            .name("context")
            .annotations(new ArrayList<>())
            .isConstant(false)
            .defaultValue(null)
            .fromClient(false)
            .isFinal(false)
            .isRequired(false)
            .build();

    private void addClientMethodWithContext(List<ClientMethod> methods, Builder builder, ProxyMethod proxyMethod,
        List<ClientMethodParameter> parameters, ClientMethodType clientMethodType, String proxyMethodName,
        ReturnValue returnValue, MethodPageDetails details) {
        List<ClientMethodParameter> withContextParameters = new ArrayList<>(parameters);
        withContextParameters.add(getContextParameter());

        methods.add(builder
            .parameters(withContextParameters) // update builder parameters to include context
            .returnValue(returnValue)
            .name(proxyMethodName)
            .onlyRequiredParameters(false)
            .type(clientMethodType)
            .isGroupedParameterRequired(false)
            .methodPageDetails(details)
            .build());
        // reset the parameters to original params
        builder.parameters(parameters);
    }

    protected ClientMethodParameter getContextParameter() {
        return CONTEXT_PARAM;
    }

    private void addClientMethodWithContext(List<ClientMethod> methods, Builder builder,
                                            List<ClientMethodParameter> parameters) {
        List<ClientMethodParameter> withContextParameters = new ArrayList<>(parameters);
        withContextParameters.add(getContextParameter());

        methods.add(builder
                .parameters(withContextParameters) // update builder parameters to include context
                .onlyRequiredParameters(false)
                .build());
        // reset the parameters to original params
        builder.parameters(parameters);
    }

    private String getPageableItemName(Operation operation) {
        Schema responseBodySchema = SchemaUtil.getLowestCommonParent(
            operation.getResponses().stream().map(Response::getSchema).filter(Objects::nonNull).collect(Collectors.toList()));
        ClientModel responseBodyModel = Mappers.getModelMapper().map((ObjectSchema) responseBodySchema);
        return responseBodyModel.getProperties().stream()
            .filter(p -> p.getSerializedName().equals(operation.getExtensions().getXmsPageable().getItemName()))
            .map(ClientModelProperty::getName).findAny().orElse(null);
    }

    private IType getPollingIntermediateType(JavaSettings.PollingDetails details, IType syncReturnType) {
        IType pollResponseType = syncReturnType.asNullable();
        if (JavaSettings.getInstance().isFluent()) {
            return pollResponseType;
        }
        if (details != null && details.getIntermediateType() != null) {
            String intermediateTypeName;
            String intermediateTypePackage;
            if (details.getIntermediateType().contains(".")) {
                intermediateTypeName = ANYTHING_THEN_PERIOD.matcher(details.getIntermediateType()).replaceAll("");
                intermediateTypePackage = details.getIntermediateType().replace("." + intermediateTypeName, "");
            } else {
                intermediateTypeName = details.getIntermediateType();
                intermediateTypePackage = JavaSettings.getInstance().getPackage();
            }
            pollResponseType = new ClassType.Builder().packageName(intermediateTypePackage).name(intermediateTypeName).build();
        }
        // azure-core wants poll response to be non-null
        if (pollResponseType == ClassType.Void) {
            pollResponseType = ClassType.BinaryData;
        }

        return pollResponseType;
    }

    private IType getPollingFinalType(JavaSettings.PollingDetails details, IType syncReturnType) {
        IType resultType = syncReturnType.asNullable();
        if (JavaSettings.getInstance().isFluent()) {
            return resultType;
        }
        if (details != null && details.getFinalType() != null) {
            String finalTypeName;
            String finalTypePackage;
            if (details.getFinalType().contains(".")) {
                finalTypeName = ANYTHING_THEN_PERIOD.matcher(details.getFinalType()).replaceAll("");
                finalTypePackage = details.getFinalType().replace("." + finalTypeName, "");
            } else {
                finalTypeName = details.getFinalType();
                finalTypePackage = JavaSettings.getInstance().getPackage();
            }
            resultType = new ClassType.Builder().packageName(finalTypePackage).name(finalTypeName).build();
        }
        // azure-core wants poll response to be non-null
        if (resultType == ClassType.Void) {
            resultType = ClassType.BinaryData;
        }

        return resultType;
    }

    private static boolean hasNonRequiredParameters(List<ClientMethodParameter> parameters) {
        return parameters.stream().anyMatch(p -> !p.getIsRequired());
    }

    protected static String returnTypeDescription(Operation operation, IType returnType, IType baseType) {
        String description = null;
        if (returnType != PrimitiveType.Void) {
            if (operation.getLanguage() != null && operation.getLanguage().getDefault() != null) {
                String operationDescription = operation.getLanguage().getDefault().getDescription();
                if (!CoreUtils.isNullOrEmpty(operationDescription)) {
                    if (operationDescription.toLowerCase().startsWith("get ") || operationDescription.toLowerCase().startsWith("gets ")) {
                        int startIndex = operationDescription.indexOf(" ") + 1;
                        description = formatReturnTypeDescription(operationDescription.substring(startIndex));
                    }
                }
            }

            if (description == null && operation.getResponses() != null && !operation.getResponses().isEmpty()) {
                Schema responseSchema = operation.getResponses().get(0).getSchema();
                if (responseSchema != null && !CoreUtils.isNullOrEmpty(responseSchema.getSummary())) {
                    description = formatReturnTypeDescription(responseSchema.getSummary());
                } else if (responseSchema != null && responseSchema.getLanguage() != null && responseSchema.getLanguage().getDefault() != null) {
                    String responseSchemaDescription = responseSchema.getLanguage().getDefault().getDescription();
                    if (!CoreUtils.isNullOrEmpty(responseSchemaDescription)) {
                        description = formatReturnTypeDescription(responseSchemaDescription);
                    }
                }
            }

            if (description == null) {
                if (baseType == PrimitiveType.Void) {
                    // Mono<Void>
                    description = "the completion";
                }
                if (baseType == PrimitiveType.Boolean
                        && operation.getRequests() != null && !operation.getRequests().isEmpty()
                        && operation.getRequests().get(0).getProtocol() != null
                        && operation.getRequests().get(0).getProtocol().getHttp() != null
                        && HttpMethod.HEAD.name().equalsIgnoreCase(operation.getRequests().get(0).getProtocol().getHttp().getMethod())) {
                    // Mono<Boolean> of HEAD method
                    description = "whether resource exists";
                }
            }

            if (description == null) {
                description = "the response";
            }
        }
        return description;
    }

    private static String formatReturnTypeDescription(String description) {
        description = description.trim();
        int endIndex = description.indexOf(". ");   // Get 1st sentence.
        if (endIndex == -1 && description.length() > 0 && description.charAt(description.length() - 1) == '.') {
            // Remove last period.
            endIndex = description.length() - 1;
        }
        if (endIndex != -1) {
            description = description.substring(0, endIndex);
        }
        if (description.length() > 0 && Character.isUpperCase(description.charAt(0))) {
            description = description.substring(0, 1).toLowerCase() + description.substring(1);
        }
        return description;
    }
}
