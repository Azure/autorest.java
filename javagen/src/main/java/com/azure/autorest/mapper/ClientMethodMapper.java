// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper;

import com.azure.autorest.Javagen;
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
import com.azure.autorest.model.clientmodel.ClientModels;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.ExternalDocumentation;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ImplementationDetails;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.model.clientmodel.MethodPageDetails;
import com.azure.autorest.model.clientmodel.MethodParameter;
import com.azure.autorest.model.clientmodel.MethodPollingDetails;
import com.azure.autorest.model.clientmodel.MethodTransformationDetail;
import com.azure.autorest.model.clientmodel.ParameterMapping;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.clientmodel.ReturnValue;
import com.azure.autorest.model.javamodel.JavaVisibility;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.MethodUtil;
import com.azure.autorest.util.ReturnTypeDescriptionAssembler;
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
import java.util.function.BiFunction;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ClientMethodMapper implements IMapper<Operation, List<ClientMethod>> {
    private static final ClientMethodMapper INSTANCE = new ClientMethodMapper();

    private static final Pattern ANYTHING_THEN_PERIOD = Pattern.compile(".*\\.");

    private final Map<CacheKey, List<ClientMethod>> parsed = new ConcurrentHashMap<>();

    private static class CacheKey {
        private final Operation operation;
        private final boolean isProtocolMethod;

        public CacheKey(Operation operation, boolean isProtocolMethod) {
            this.operation = operation;
            this.isProtocolMethod = isProtocolMethod;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CacheKey cacheKey = (CacheKey) o;
            return isProtocolMethod == cacheKey.isProtocolMethod && operation.equals(cacheKey.operation);
        }

        @Override
        public int hashCode() {
            return Objects.hash(operation, isProtocolMethod);
        }
    }

    private static final ReturnTypeDescriptionAssembler DESCRIPTION_ASSEMBLER = new ReturnTypeDescriptionAssembler(Javagen.getPluginInstance());

    protected ClientMethodMapper() {
    }

    public static ClientMethodMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public List<ClientMethod> map(Operation operation) {
        return map(operation, JavaSettings.getInstance().isDataPlaneClient());
    }

    public List<ClientMethod> map(Operation operation, boolean isProtocolMethod) {
        CacheKey cacheKey = new CacheKey(operation, isProtocolMethod);
        List<ClientMethod> clientMethods = parsed.get(cacheKey);
        if (clientMethods != null) {
            return clientMethods;
        }

        clientMethods = createClientMethods(operation, isProtocolMethod);
        parsed.put(cacheKey, clientMethods);

        return clientMethods;
    }

    /**
     * Creates the client methods for the operation.
     *
     * @param operation the operation.
     * @param isProtocolMethod whether the client method to be simplified for resilience to API changes.
     * @return the client methods created.
     */
    private List<ClientMethod> createClientMethods(Operation operation, boolean isProtocolMethod) {
        JavaSettings settings = JavaSettings.getInstance();

        // With the introduction of "enable-sync-stack" data plane clients now have two distinct ways of creating
        // synchronous implementation client methods.
        //
        // 1. Configure "enable-sync-stack" which will create synchronous proxy methods that will use a fully
        //    synchronous code path.
        // 2. Configure "sync-methods" which will create synchronous implementation client methods that will block
        //    on the asynchronous proxy method.
        //
        // If both are support "enable-sync-stack" take precedent. This required substantial changes to the follow code
        // as before asynchronous proxy methods would generate synchronous implementation client methods which
        // shouldn't eagerly be done anymore as it would have resulted in erroneous synchronous implementation client
        // methods.

        Map<Request, List<ProxyMethod>> proxyMethodsMap = Mappers.getProxyMethodMapper().map(operation);

        List<ClientMethod> methods = new ArrayList<>();

        // If this operation is part of a group it'll need to be referenced with a more specific target.
        ClientMethod.Builder builder = getClientMethodBuilder()
            .clientReference((operation.getOperationGroup() == null || operation.getOperationGroup().getLanguage().getJava().getName().isEmpty()) ? "this" : "this.client");

        // merge summary and description
        String summary = operation.getSummary();
        if (summary == null) {
            // summary from m4 is under language
            summary = operation.getLanguage().getDefault() == null ? null : operation.getLanguage().getDefault().getSummary();
        }
        String description = operation.getLanguage().getJava() == null ? null : operation.getLanguage().getJava().getDescription();
        if (CoreUtils.isNullOrEmpty(summary) && CoreUtils.isNullOrEmpty(description)) {
            builder.description(String.format("The %s operation.", operation.getLanguage().getJava().getName()));
        } else {
            builder.description(SchemaUtil.mergeSummaryWithDescription(summary, description));
        }

        ReturnTypeHolder returnTypeHolder = getReturnTypes(operation, isProtocolMethod, settings);

        // map externalDocs property
        if (operation.getExternalDocs() != null) {
            ExternalDocumentation externalDocumentation = new ExternalDocumentation.Builder()
                .description(operation.getExternalDocs().getDescription())
                .url(operation.getExternalDocs().getUrl())
                .build();
            builder.methodDocumentation(externalDocumentation);
        }


        // DPG client only requires one request per operation
        List<Request> requests = operation.getRequests();
        if (isProtocolMethod) {
            Request selectedRequest = MethodUtil.tryMergeBinaryRequests(requests, operation);
            requests = Collections.singletonList(selectedRequest);
        }

        for (Request request : requests) {
            List<ProxyMethod> proxyMethods = proxyMethodsMap.get(request);
            for (ProxyMethod proxyMethod : proxyMethods) {
                builder.proxyMethod(proxyMethod);
                List<ClientMethodParameter> parameters = new ArrayList<>();
                List<String> requiredParameterExpressions = new ArrayList<>();
                Map<String, String> validateExpressions = new HashMap<>();
                List<MethodTransformationDetail> methodTransformationDetails = new ArrayList<>();

                List<Parameter> codeModelParameters = getCodeModelParameters(request, isProtocolMethod);

                boolean isJsonPatch = request.getProtocol() != null && request.getProtocol().getHttp() != null
                    && request.getProtocol().getHttp().getMediaTypes() != null
                    && request.getProtocol().getHttp().getMediaTypes().contains("application/json-patch+json");

                boolean proxyMethodUsesBinaryData = proxyMethod.getParameters().stream()
                    .anyMatch(proxyMethodParameter -> proxyMethodParameter.getClientType() == ClassType.BinaryData);

                Set<Parameter> originalParameters = new HashSet<>();
                for (Parameter parameter : codeModelParameters) {
                    ClientMethodParameter clientMethodParameter = Mappers.getClientParameterMapper()
                        .map(parameter, isProtocolMethod);

                    if (isJsonPatch) {
                        clientMethodParameter = CustomClientParameterMapper.getInstance().map(parameter);
                    }

                    // If the codemodel parameter and proxy method parameter types don't match, update the client
                    // method param to use proxy method parameter type.
                    if (proxyMethodUsesBinaryData && clientMethodParameter.getClientType() == GenericType.FluxByteBuffer) {
                        clientMethodParameter = updateClientMethodParameter(clientMethodParameter);
                    }

                    if (request.getSignatureParameters().contains(parameter)) {
                        parameters.add(clientMethodParameter);
                    }

                    if (!(parameter.getSchema() instanceof ConstantSchema) && parameter.getGroupedBy() == null) {
                        MethodParameter methodParameter;
                        String expression;
                        if (parameter.getImplementation() != Parameter.ImplementationLocation.CLIENT) {
                            methodParameter = clientMethodParameter;
                            expression = clientMethodParameter.getName();
                        } else {
                            ProxyMethodParameter proxyParameter = Mappers.getProxyParameterMapper().map(parameter);
                            methodParameter = proxyParameter;
                            expression = proxyParameter.getParameterReference();
                        }

                        // Validations
                        if (methodParameter.isRequired() && !(methodParameter.getClientType() instanceof PrimitiveType)) {
                            requiredParameterExpressions.add(expression);
                        }
                        String validation = methodParameter.getClientType().validate(expression);
                        if (validation != null) {
                            validateExpressions.put(expression, validation);
                        }
                    }

                    // Transformations
                    if ((parameter.getOriginalParameter() != null || parameter.getGroupedBy() != null)
                        && !(parameter.getSchema() instanceof ConstantSchema) && !isProtocolMethod) {
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

                if (isProtocolMethod) {
                    ClientMethodParameter requestOptions = new ClientMethodParameter.Builder()
                        .description("The options to configure the HTTP request before HTTP client sends it")
                        .wireType(ClassType.RequestOptions)
                        .name("requestOptions")
                        .location(RequestParameterLocation.NONE)
                        .isConstant(false)
                        .isRequired(true)
                        .fromClient(false)
                        .annotations(Collections.emptyList())
                        .build();
                    parameters.add(requestOptions);
                }

                final boolean generateClientMethodWithOnlyRequiredParameters
                    = settings.getRequiredParameterClientMethods() && hasNonRequiredParameters(parameters);

                builder.parameters(parameters)
                    .requiredNullableParameterExpressions(requiredParameterExpressions)
                    .validateExpressions(validateExpressions)
                    .methodTransformationDetails(methodTransformationDetails)
                    .methodPageDetails(null);

                if (operation.getExtensions() != null && operation.getExtensions().getXmsPageable() != null
                    && shouldGeneratePagingMethods()) {
                    String pageableItemName = getPageableItemName(operation);
                    if (pageableItemName == null) {
                        // There is no pageable item name for this operation, skip it.
                        continue;
                    }

                    // If the ProxyMethod is synchronous perform a complete generation of synchronous pageable APIs.
                    if (proxyMethod.isSync()) {
                        createSyncPageableClientMethods(operation, isProtocolMethod, settings, methods, builder,
                            returnTypeHolder, proxyMethod, parameters, pageableItemName,
                            generateClientMethodWithOnlyRequiredParameters);
                    } else {
                        // Otherwise, perform a complete generation of asynchronous pageable APIs.
                        // Then if SyncMethodsGeneration is enabled and Sync Stack is not perform synchronous pageable
                        // API generation based on SyncMethodsGeneration configuration.
                        createAsyncPageableClientMethods(operation, isProtocolMethod, settings, methods, builder,
                            returnTypeHolder, proxyMethod, parameters, pageableItemName,
                            generateClientMethodWithOnlyRequiredParameters);

                        if (settings.getSyncMethods() == SyncMethodsGeneration.ALL && !settings.isSyncStackEnabled()) {
                            createSyncPageableClientMethods(operation, isProtocolMethod, settings, methods, builder,
                                returnTypeHolder, proxyMethod, parameters, pageableItemName,
                                generateClientMethodWithOnlyRequiredParameters);
                        }
                    }
                } else if (operation.getExtensions() != null && operation.getExtensions().isXmsLongRunningOperation()
                    && (settings.isFluent() || settings.getPollingConfig("default") != null)
                    && !returnTypeHolder.syncReturnType.equals(ClassType.InputStream)) {         // temporary skip InputStream, no idea how to do this in PollerFlux
                    // Skip sync ProxyMethods for polling as sync polling isn't ready yet.
                    if (proxyMethod.isSync()) {
                        continue;
                    }
                    JavaVisibility simpleAsyncMethodVisibility =
                        methodVisibility(ClientMethodType.SimpleAsyncRestResponse, false, isProtocolMethod);
                    JavaVisibility simpleAsyncMethodVisibilityWithContext =
                        methodVisibility(ClientMethodType.SimpleAsyncRestResponse, true, isProtocolMethod);
                    if (settings.isDataPlaneClient()) {
                        simpleAsyncMethodVisibility = NOT_VISIBLE;
                        simpleAsyncMethodVisibilityWithContext = NOT_GENERATE;
                    }

                    // WithResponseAsync, with required and optional parameters
                    methods.add(builder
                        .returnValue(createSimpleAsyncRestResponseReturnValue(operation,
                            returnTypeHolder.asyncRestResponseReturnType, returnTypeHolder.syncReturnType))
                        .name(proxyMethod.getSimpleAsyncRestResponseMethodName())
                        .onlyRequiredParameters(false)
                        .type(ClientMethodType.SimpleAsyncRestResponse)
                        .isGroupedParameterRequired(false)
                        .methodVisibility(simpleAsyncMethodVisibility)
                        .build());

                    if (settings.isContextClientMethodParameter()) {
                        builder.methodVisibility(simpleAsyncMethodVisibilityWithContext);
                        addClientMethodWithContext(methods, builder, parameters, getContextParameter());
                    }

                    JavaSettings.PollingDetails pollingDetails = settings.getPollingConfig(proxyMethod.getOperationId());

                    MethodPollingDetails methodPollingDetails = null;
                    MethodPollingDetails dpgMethodPollingDetailsWithModel = null;   // for additional LRO methods
                    if (pollingDetails != null) {
                        methodPollingDetails = new MethodPollingDetails(
                            pollingDetails.getStrategy(),
                            getPollingIntermediateType(pollingDetails, returnTypeHolder.syncReturnType),
                            getPollingFinalType(pollingDetails, returnTypeHolder.syncReturnType),
                            pollingDetails.getPollIntervalInSeconds());

                        if (isProtocolMethod &&
                            !(ClassType.BinaryData.equals(methodPollingDetails.getIntermediateType())
                                && ClassType.BinaryData.equals(methodPollingDetails.getFinalType()))) {
                            // a new method to be added as implementation only (not exposed to client) for developer
                            dpgMethodPollingDetailsWithModel = methodPollingDetails;

                            // DPG keep the method with BinaryData
                            methodPollingDetails = new MethodPollingDetails(
                                dpgMethodPollingDetailsWithModel.getPollingStrategy(),
                                ClassType.BinaryData, ClassType.BinaryData,
                                dpgMethodPollingDetailsWithModel.getPollIntervalInSeconds());
                        }
                    }

                    addLroMethods(operation, builder, methods,
                        "begin" + CodeNamer.toPascalCase(proxyMethod.getSimpleAsyncMethodName()),
                        "begin" + CodeNamer.toPascalCase(proxyMethod.getName()),
                        parameters, returnTypeHolder.syncReturnType, methodPollingDetails, isProtocolMethod, settings);

                    if (dpgMethodPollingDetailsWithModel != null) {
                        ImplementationDetails.Builder implDetailsBuilder = new ImplementationDetails.Builder().implementationOnly(true);

                        builder = builder.implementationDetails(implDetailsBuilder.build());

                        String modelSuffix = "WithModel";
                        addLroMethods(operation, builder, methods,
                            "begin" + CodeNamer.toPascalCase(proxyMethod.getName() + modelSuffix + "Async"),
                            "begin" + CodeNamer.toPascalCase(proxyMethod.getName() + modelSuffix),
                            parameters, returnTypeHolder.syncReturnType, dpgMethodPollingDetailsWithModel, isProtocolMethod, settings);

                        builder = builder.implementationDetails(implDetailsBuilder.implementationOnly(false).build());
                    }

                    if (settings.isFluent()) {
                        if (settings.getSyncMethods() != JavaSettings.SyncMethodsGeneration.NONE) {
                            methods.add(builder
                                .returnValue(createLongRunningAsyncReturnValue(operation, returnTypeHolder.asyncReturnType, returnTypeHolder.syncReturnType))
                                .name(proxyMethod.getSimpleAsyncMethodName())
                                .onlyRequiredParameters(false)
                                .type(ClientMethodType.LongRunningAsync)
                                .isGroupedParameterRequired(false)
                                .methodVisibility(methodVisibility(ClientMethodType.LongRunningAsync, false, isProtocolMethod))
                                .build());

                            if (generateClientMethodWithOnlyRequiredParameters) {
                                methods.add(builder
                                    .onlyRequiredParameters(true)
                                    .build());
                            }

                            if (settings.isContextClientMethodParameter()) {
                                addClientMethodWithContext(methods,
                                    builder.methodVisibility(methodVisibility(ClientMethodType.LongRunningAsync, true, isProtocolMethod)),
                                    parameters, getContextParameter());
                            }
                        }
                        if (settings.getSyncMethods() == JavaSettings.SyncMethodsGeneration.ALL) {
                            builder.methodVisibility(VISIBLE);

                            methods.add(builder
                                .returnValue(createLongRunningSyncReturnValue(operation, returnTypeHolder.syncReturnType))
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
                                addClientMethodWithContext(methods, builder, parameters, getContextParameter());
                            }
                        }
                    }
                } else {
                    // If the ProxyMethod is synchronous perform a complete generation of synchronous simple APIs.
                    if (proxyMethod.isSync()) {
                        createSimpleSyncClientMethods(operation, isProtocolMethod, settings, methods, builder,
                            returnTypeHolder, proxyMethod, parameters, generateClientMethodWithOnlyRequiredParameters);
                    } else {
                        // Otherwise, perform a complete generation of asynchronous simple APIs.
                        // Then if SyncMethodsGeneration is enabled and Sync Stack is not perform synchronous simple
                        // API generation based on SyncMethodsGeneration configuration.
                        createSimpleAsyncClientMethods(operation, isProtocolMethod, settings, methods, builder,
                            returnTypeHolder, proxyMethod, parameters, generateClientMethodWithOnlyRequiredParameters);

                        if (settings.getSyncMethods() == SyncMethodsGeneration.ALL && !settings.isSyncStackEnabled()) {
                            createSimpleSyncClientMethods(operation, isProtocolMethod, settings, methods, builder,
                                returnTypeHolder, proxyMethod, parameters, generateClientMethodWithOnlyRequiredParameters);
                        }
                    }
                }
            }
        }

        return methods.stream()
            .filter(m -> m.getMethodVisibility() != NOT_GENERATE)
            .distinct()
            .collect(Collectors.toList());
    }

    private ReturnTypeHolder getReturnTypes(Operation operation, boolean isProtocolMethod, JavaSettings settings) {
        ReturnTypeHolder returnTypeHolder = new ReturnTypeHolder();

        if (operation.getExtensions() != null && operation.getExtensions().getXmsPageable() != null) {
            // Mono<SimpleResponse<Page>>
            Schema responseBodySchema = SchemaUtil.getLowestCommonParent(operation.getResponses().stream()
                .map(Response::getSchema).filter(Objects::nonNull).collect(Collectors.toList()));
            if (!(responseBodySchema instanceof ObjectSchema)) {
                throw new IllegalArgumentException(String.format("[JavaCheck/SchemaError] no common parent found for client models %s",
                    operation.getResponses().stream().map(Response::getSchema).filter(Objects::nonNull)
                        .map(s -> s.getLanguage().getJava().getName()).collect(Collectors.toList())));
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
            if (isProtocolMethod) {
                returnTypeHolder.asyncRestResponseReturnType = createProtocolPagedRestResponseReturnType();
                returnTypeHolder.asyncReturnType = createProtocolPagedAsyncReturnType();
                returnTypeHolder.syncReturnType = createProtocolPagedSyncReturnType();
                returnTypeHolder.syncReturnWithResponse = createProtocolPagedRestResponseReturnTypeSync();
            } else {
                returnTypeHolder.asyncRestResponseReturnType = createPagedRestResponseReturnType(elementType);
                returnTypeHolder.asyncReturnType = createPagedAsyncReturnType(elementType);
                returnTypeHolder.syncReturnType = createPagedSyncReturnType(elementType);
                returnTypeHolder.syncReturnWithResponse = createPagedRestResponseReturnTypeSync(elementType);
            }

            return returnTypeHolder;
        }

        IType responseBodyType = SchemaUtil.getOperationResponseType(operation, settings);
        if (isProtocolMethod) {
            if (responseBodyType instanceof ClassType || responseBodyType instanceof ListType || responseBodyType instanceof MapType) {
                responseBodyType = ClassType.BinaryData;
            } else if (responseBodyType instanceof EnumType) {
                responseBodyType = ClassType.String;
            }
        }

        returnTypeHolder.asyncRestResponseReturnType = Mappers.getProxyMethodMapper()
            .getAsyncRestResponseReturnType(operation, responseBodyType, isProtocolMethod, settings)
            .getClientType();

        IType restAPIMethodReturnBodyClientType = responseBodyType.getClientType();
        if (responseBodyType.equals(ClassType.InputStream)) {
            returnTypeHolder.asyncReturnType = createAsyncBinaryReturnType();
            returnTypeHolder.syncReturnType = responseBodyType.getClientType();
        } else {
            if (restAPIMethodReturnBodyClientType != PrimitiveType.Void) {
                returnTypeHolder.asyncReturnType = createAsyncBodyReturnType(restAPIMethodReturnBodyClientType);
            } else {
                returnTypeHolder.asyncReturnType = createAsyncVoidReturnType();
            }
            returnTypeHolder.syncReturnType = responseBodyType.getClientType();
        }

        if (returnTypeHolder.syncReturnType == ClassType.BinaryData) {
            returnTypeHolder.syncReturnWithResponse = GenericType.Response(ClassType.BinaryData);
        } else {
            returnTypeHolder.syncReturnWithResponse = createSyncReturnWithResponseType(returnTypeHolder.syncReturnType,
                operation, isProtocolMethod, settings);
        }

        return returnTypeHolder;
    }

    private static List<Parameter> getCodeModelParameters(Request request, boolean isProtocolMethod) {
        if (isProtocolMethod) {
            // Required path, body, header and query parameters are allowed
            return request.getParameters().stream().filter(p -> {
                    RequestParameterLocation location = p.getProtocol().getHttp().getIn();

                    return p.isRequired() && (location == RequestParameterLocation.PATH
                        || location == RequestParameterLocation.BODY
                        || location == RequestParameterLocation.HEADER
                        || location == RequestParameterLocation.QUERY);
                })
                .collect(Collectors.toList());
        } else {
            return request.getParameters().stream().filter(p -> !p.isFlattened()).collect(Collectors.toList());
        }
    }

    private void createAsyncPageableClientMethods(Operation operation, boolean isProtocolMethod, JavaSettings settings,
        List<ClientMethod> methods, Builder builder, ReturnTypeHolder returnTypeHolder, ProxyMethod proxyMethod,
        List<ClientMethodParameter> parameters, String pageableItemName,
        boolean generateClientMethodWithOnlyRequiredParameters) {
        ReturnValue singlePageReturnValue = createPagingAsyncSinglePageReturnValue(operation,
            returnTypeHolder.asyncRestResponseReturnType, returnTypeHolder.syncReturnType);
        ReturnValue nextPageReturnValue = createPagingAsyncReturnValue(operation, returnTypeHolder.asyncReturnType,
            returnTypeHolder.syncReturnType);
        BiFunction<Boolean, Boolean, JavaVisibility> visibilityFunction = (firstPage, includesContext) ->
            methodVisibility(firstPage ? ClientMethodType.PagingAsyncSinglePage : ClientMethodType.PagingAsync,
                includesContext, isProtocolMethod);

        createPageableClientMethods(operation, settings, methods, builder, proxyMethod, parameters, pageableItemName,
            false, singlePageReturnValue, nextPageReturnValue, visibilityFunction, getContextParameter(),
            generateClientMethodWithOnlyRequiredParameters);
    }

    private void createSyncPageableClientMethods(Operation operation, boolean isProtocolMethod, JavaSettings settings,
        List<ClientMethod> methods, Builder builder, ReturnTypeHolder returnTypeHolder, ProxyMethod proxyMethod,
        List<ClientMethodParameter> parameters, String pageableItemName,
        boolean generateClientMethodWithOnlyRequiredParameters) {
        ReturnValue singlePageReturnValue = createPagingAsyncSinglePageReturnValue(operation,
            returnTypeHolder.syncReturnWithResponse, returnTypeHolder.syncReturnType);
        ReturnValue nextPageReturnValue = createPagingAsyncReturnValue(operation, returnTypeHolder.syncReturnType,
            returnTypeHolder.syncReturnType);
        BiFunction<Boolean, Boolean, JavaVisibility> visibilityFunction = (firstPage, includesContext) ->
            methodVisibility(firstPage ? ClientMethodType.PagingSyncSinglePage : ClientMethodType.PagingSync,
                includesContext, isProtocolMethod);

        createPageableClientMethods(operation, settings, methods, builder, proxyMethod, parameters, pageableItemName,
            true, singlePageReturnValue, nextPageReturnValue, visibilityFunction, getContextParameter(),
            generateClientMethodWithOnlyRequiredParameters);
    }

    private static void createPageableClientMethods(Operation operation, JavaSettings settings,
        List<ClientMethod> methods, Builder builder, ProxyMethod proxyMethod, List<ClientMethodParameter> parameters,
        String pageableItemName, boolean isSync, ReturnValue singlePageReturnValue, ReturnValue nextPageReturnValue,
        BiFunction<Boolean, Boolean, JavaVisibility> visibilityFunction, ClientMethodParameter contextParameter,
        boolean generateClientMethodWithOnlyRequiredParameters) {

        Operation nextOperation = operation.getExtensions().getXmsPageable().getNextOperation();
        String nextLinkName = operation.getExtensions().getXmsPageable().getNextLinkName();
        String itemName = operation.getExtensions().getXmsPageable().getItemName();
        ClientMethodType nextMethodType = isSync ? ClientMethodType.PagingSyncSinglePage : ClientMethodType.PagingAsyncSinglePage;

        boolean isNextMethod = (nextOperation == operation);

        IType lroIntermediateType = null;
        if (operation.getExtensions().isXmsLongRunningOperation() && !isNextMethod) {
            lroIntermediateType = SchemaUtil.getOperationResponseType(operation, settings);
        }

        List<ClientMethod> nextMethods = (isNextMethod || nextOperation == null)
            ? null : Mappers.getClientMethodMapper().map(nextOperation);

        ClientMethod nextMethod = (nextMethods == null) ? null
            : nextMethods.stream().filter(m -> m.getType() == nextMethodType).findFirst().orElse(null);

        MethodPageDetails details = new MethodPageDetails(CodeNamer.getPropertyName(nextLinkName), pageableItemName,
            nextMethod, lroIntermediateType, nextLinkName, itemName);
        builder.methodPageDetails(details);

        String pageMethodName = isSync ? proxyMethod.getPagingSinglePageMethodName() : proxyMethod.getPagingAsyncSinglePageMethodName();
        ClientMethodType pageMethodType = isSync ? ClientMethodType.PagingSyncSinglePage : ClientMethodType.PagingAsyncSinglePage;

        builder.returnValue(singlePageReturnValue)
            .onlyRequiredParameters(false)
            .name(pageMethodName)
            .type(pageMethodType)
            .isGroupedParameterRequired(false)
            .methodVisibility(visibilityFunction.apply(true, false));

        if (settings.getRequiredParameterClientMethods() || !settings.isContextClientMethodParameter()
            || settings.getSyncMethods() != SyncMethodsGeneration.NONE) {
            methods.add(builder.build());
        }

        // Generate an overload with all parameters always, optionally include context.
        if (settings.isContextClientMethodParameter() && !settings.isDataPlaneClient()) {
            builder.methodVisibility(visibilityFunction.apply(true, true));
            addClientMethodWithContext(methods, builder, parameters, pageMethodType, pageMethodName,
                singlePageReturnValue, details, contextParameter);
        }

        // If this was the next method there is no further work to be done.
        if (isNextMethod) {
            return;
        }

        // Otherwise repeat what we just did but for next page client methods.
        pageMethodName = isSync ? proxyMethod.getName() : proxyMethod.getSimpleAsyncMethodName();
        pageMethodType = isSync ? ClientMethodType.PagingSync : ClientMethodType.PagingAsync;

        builder.returnValue(nextPageReturnValue)
            .name(pageMethodName)
            .type(pageMethodType)
            .isGroupedParameterRequired(false)
            .methodVisibility(visibilityFunction.apply(false, false));

        if (!isSync || !settings.isFluent() || !settings.isContextClientMethodParameter() || !generateClientMethodWithOnlyRequiredParameters) {
            // in sync API, if context parameter is required, that method will do the overload with max parameters
            methods.add(builder.build());
        }

        if (generateClientMethodWithOnlyRequiredParameters) {
            methods.add(builder.onlyRequiredParameters(true).build());
        }

        builder.onlyRequiredParameters(false);
        if (settings.isContextClientMethodParameter() && !settings.isDataPlaneClient()) {
            MethodPageDetails detailsWithContext = details;
            if (nextMethods != null) {
                IType contextWireType = contextParameter.getWireType();
                nextMethod = nextMethods.stream()
                    .filter(m -> m.getType() == nextMethodType)
                    .filter(m -> m.getMethodParameters().stream().anyMatch(p -> contextWireType.equals(p.getClientType())))
                    .findFirst()
                    .orElse(null);

                if (nextMethod != null) {
                    detailsWithContext = new MethodPageDetails(CodeNamer.getPropertyName(nextLinkName),
                        pageableItemName, nextMethod, lroIntermediateType, nextLinkName, itemName);
                }
            }

            builder.methodVisibility(visibilityFunction.apply(false, true)).onlyRequiredParameters(false);
            addClientMethodWithContext(methods, builder, parameters, pageMethodType, pageMethodName,
                nextPageReturnValue, detailsWithContext, contextParameter);
        }
    }

    private void createSimpleAsyncClientMethods(Operation operation, boolean isProtocolMethod, JavaSettings settings,
        List<ClientMethod> methods, Builder builder, ReturnTypeHolder returnTypeHolder, ProxyMethod proxyMethod,
        List<ClientMethodParameter> parameters, boolean generateClientMethodWithOnlyRequiredParameters) {
        ReturnValue responseReturnValue = createSimpleAsyncRestResponseReturnValue(operation,
            returnTypeHolder.asyncRestResponseReturnType, returnTypeHolder.syncReturnType);
        ReturnValue returnValue = createSimpleAsyncReturnValue(operation, returnTypeHolder.asyncReturnType,
            returnTypeHolder.syncReturnType);
        BiFunction<Boolean, Boolean, JavaVisibility> visibilityFunction = (restResponse, includesContext) ->
            methodVisibility(restResponse ? ClientMethodType.SimpleAsyncRestResponse : ClientMethodType.SimpleAsync,
                includesContext, isProtocolMethod);

        createSimpleClientMethods(settings, methods, builder, proxyMethod, parameters, false, responseReturnValue,
            returnValue, visibilityFunction, getContextParameter(), generateClientMethodWithOnlyRequiredParameters);
    }

    private void createSimpleSyncClientMethods(Operation operation, boolean isProtocolMethod, JavaSettings settings,
        List<ClientMethod> methods, Builder builder, ReturnTypeHolder returnTypeHolder, ProxyMethod proxyMethod,
        List<ClientMethodParameter> parameters, boolean generateClientMethodWithOnlyRequiredParameters) {
        ReturnValue responseReturnValue = createSimpleSyncRestResponseReturnValue(operation,
            returnTypeHolder.syncReturnWithResponse, returnTypeHolder.syncReturnType);
        ReturnValue returnValue = createSimpleSyncReturnValue(operation, returnTypeHolder.syncReturnType);
        BiFunction<Boolean, Boolean, JavaVisibility> visibilityFunction = (restResponse, includesContext) ->
            methodVisibility(restResponse ? ClientMethodType.SimpleSyncRestResponse : ClientMethodType.SimpleSync,
                includesContext, isProtocolMethod);

        createSimpleClientMethods(settings, methods, builder, proxyMethod, parameters, true, responseReturnValue,
            returnValue, visibilityFunction, getContextParameter(), generateClientMethodWithOnlyRequiredParameters);
    }

    private static void createSimpleClientMethods(JavaSettings settings, List<ClientMethod> methods, Builder builder,
        ProxyMethod proxyMethod, List<ClientMethodParameter> parameters, boolean isSync,
        ReturnValue responseReturnValue, ReturnValue returnValue,
        BiFunction<Boolean, Boolean, JavaVisibility> visibilityFunction, ClientMethodParameter contextParameter,
        boolean generateClientMethodWithOnlyRequiredParameters) {

        String methodName = isSync ? proxyMethod.getSimpleRestResponseMethodName() : proxyMethod.getSimpleAsyncRestResponseMethodName();
        ClientMethodType methodType = isSync ? ClientMethodType.SimpleSyncRestResponse : ClientMethodType.SimpleAsyncRestResponse;

        builder.parameters(parameters)
            .returnValue(responseReturnValue)
            .onlyRequiredParameters(false)
            .name(methodName)
            .type(methodType)
            .isGroupedParameterRequired(false)
            .methodVisibility(visibilityFunction.apply(true, false));

        // Always generate an overload of WithResponse with non-required parameters without Context.
        methods.add(builder.build());

        if (settings.isContextClientMethodParameter()) {
            builder.methodVisibility(visibilityFunction.apply(true, true));
            addClientMethodWithContext(methods, builder, parameters, contextParameter);
        }

        // Repeat the same but for simple returns.
        methodName = isSync ? proxyMethod.getName() : proxyMethod.getSimpleAsyncMethodName();
        methodType = isSync ? ClientMethodType.SimpleSync : ClientMethodType.SimpleAsync;

        builder.parameters(parameters)
            .returnValue(returnValue)
            .name(methodName)
            .type(methodType)
            .isGroupedParameterRequired(false)
            .methodVisibility(visibilityFunction.apply(false, false));

        // Generate a non-WithResponse overload with non-required parameters only if one of fluent, include Context,
        // and only required parameters is false.
        if (!settings.isFluent() || !settings.isContextClientMethodParameter() || !generateClientMethodWithOnlyRequiredParameters) {
            methods.add(builder.build());
        }

        if (generateClientMethodWithOnlyRequiredParameters) {
            methods.add(builder.onlyRequiredParameters(true).build());
        }

        if (settings.isContextClientMethodParameter()) {
            builder.methodVisibility(visibilityFunction.apply(false, true)).onlyRequiredParameters(false);
            addClientMethodWithContext(methods, builder, parameters, contextParameter);
        }
    }

    private static ClientMethodParameter updateClientMethodParameter(ClientMethodParameter clientMethodParameter) {
        return clientMethodParameter.toNewBuilder()
            .rawType(ClassType.BinaryData)
            .wireType(ClassType.BinaryData)
            .build();
    }

    private void addLroMethods(Operation operation, ClientMethod.Builder builder, List<ClientMethod> methods,
        String asyncMethodName, String syncMethodName, List<ClientMethodParameter> parameters, IType syncReturnType,
        MethodPollingDetails methodPollingDetails, boolean isProtocolMethod, JavaSettings settings) {
        builder.methodPollingDetails(methodPollingDetails);
        if (settings.getSyncMethods() != JavaSettings.SyncMethodsGeneration.NONE) {
            // begin method async
            methods.add(builder
                .returnValue(createLongRunningBeginAsyncReturnValue(operation, syncReturnType, methodPollingDetails))
                .name(asyncMethodName)
                .onlyRequiredParameters(false)
                .type(ClientMethodType.LongRunningBeginAsync)
                .isGroupedParameterRequired(false)
                .methodVisibility(methodVisibility(ClientMethodType.LongRunningBeginAsync, false, isProtocolMethod))
                .build());

            if (settings.isContextClientMethodParameter()) {
                builder.methodVisibility(methodVisibility(ClientMethodType.LongRunningBeginAsync, true, isProtocolMethod));
                addClientMethodWithContext(methods, builder, parameters, getContextParameter());
            }
        }

        if (settings.getSyncMethods() == JavaSettings.SyncMethodsGeneration.ALL && !settings.isSyncStackEnabled()) {
            // begin method sync
            methods.add(builder
                .returnValue(createLongRunningBeginSyncReturnValue(operation, syncReturnType, methodPollingDetails))
                .name(syncMethodName)
                .onlyRequiredParameters(false)
                .type(ClientMethodType.LongRunningBeginSync)
                .isGroupedParameterRequired(false)
                .methodVisibility(methodVisibility(ClientMethodType.LongRunningBeginSync, false, isProtocolMethod))
                .build());

            if (settings.isContextClientMethodParameter()) {
                builder.methodVisibility(methodVisibility(ClientMethodType.LongRunningBeginSync, true, isProtocolMethod));
                addClientMethodWithContext(methods, builder, parameters, getContextParameter());
            }
        }
    }

    protected IType getContextType() {
        return ClassType.Context;
    }

    protected IType createSyncReturnWithResponseType(IType syncReturnType, Operation operation,
        boolean isProtocolMethod, JavaSettings settings) {
        boolean responseContainsHeaders = SchemaUtil.responseContainsHeaderSchemas(operation, settings);

        // If DPG is being generated or the response doesn't contain headers return Response<T>
        // If no named response types are being used return ResponseBase<H, T>
        // Else named response types are being used and return that.
        if (isProtocolMethod || !responseContainsHeaders) {
            return GenericType.Response(syncReturnType);
        } else if (settings.isGenericResponseTypes()) {

            return GenericType.RestResponse(Mappers.getSchemaMapper().map(ClientMapper.parseHeader(operation, settings)),
                syncReturnType);
        } else {
            return ClientMapper.getClientResponseClassType(operation, ClientModels.getInstance().getModels(), settings);
        }
    }

    protected ReturnValue createSimpleSyncRestResponseReturnValue(Operation operation, IType syncReturnWithResponse, IType syncReturnType) {
        return new ReturnValue(returnTypeDescription(operation, syncReturnWithResponse, syncReturnType),
            syncReturnWithResponse);
    }

    protected ReturnValue createSimpleAsyncRestResponseReturnValue(Operation operation, IType asyncRestResponseReturnType, IType syncReturnType) {
        return new ReturnValue(returnTypeDescription(operation, asyncRestResponseReturnType, syncReturnType),
            asyncRestResponseReturnType);
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

    private ReturnValue createLongRunningBeginSyncReturnValue(Operation operation, IType syncReturnType, MethodPollingDetails pollingDetails) {
        if (JavaSettings.getInstance().isFluent()) {
            IType returnType = GenericType.SyncPoller(GenericType.PollResult(syncReturnType.asNullable()), syncReturnType.asNullable());
            return new ReturnValue(returnTypeDescription(operation, returnType, syncReturnType), returnType);
        } else {
            IType returnType = GenericType.SyncPoller(pollingDetails.getIntermediateType(), pollingDetails.getFinalType());
            return new ReturnValue(returnTypeDescription(operation, returnType, pollingDetails.getFinalType()), returnType);
        }
    }

    protected ReturnValue createLongRunningBeginAsyncReturnValue(Operation operation, IType syncReturnType, MethodPollingDetails pollingDetails) {
        if (JavaSettings.getInstance().isFluent()) {
            IType returnType = GenericType.PollerFlux(GenericType.PollResult(syncReturnType.asNullable()), syncReturnType.asNullable());
            return new ReturnValue(returnTypeDescription(operation, returnType, syncReturnType), returnType);
        } else {
            IType returnType = GenericType.PollerFlux(pollingDetails.getIntermediateType(), pollingDetails.getFinalType());
            return new ReturnValue(returnTypeDescription(operation, returnType, pollingDetails.getFinalType()), returnType);
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

    protected ReturnValue createPagingSyncSinglePageReturnValue(Operation operation,
        IType syncRestResponseReturnType, IType syncReturnType) {
        return new ReturnValue(returnTypeDescription(operation, syncRestResponseReturnType, syncReturnType),
            syncRestResponseReturnType);
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

    protected IType createPagedRestResponseReturnTypeSync(IType elementType) {
        return GenericType.PagedResponse(elementType);
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

    protected IType createProtocolPagedRestResponseReturnTypeSync() {
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
     * @param isProtocolMethod whether the client method to be simplified for resilience to API changes.
     * @return method visibility, null if do not generate.
     */
    protected JavaVisibility methodVisibility(ClientMethodType methodType, boolean hasContextParameter, boolean isProtocolMethod) {
        JavaSettings settings = JavaSettings.getInstance();
        if (settings.isDataPlaneClient()) {
            if (isProtocolMethod) {
                /*
                Rule for DPG protocol method

                1. Only generate "WithResponse" method for simple API (hence exclude SimpleAsync and SimpleSync).
                2. For sync method, Context is included in "RequestOptions", hence do not generate method with Context parameter.
                3. For async method, Context is not included in method (this rule is valid for all clients).
                 */

                return (methodType == ClientMethodType.SimpleAsync
                    || methodType == ClientMethodType.SimpleSync
                    || methodType == ClientMethodType.PagingSyncSinglePage
                    || hasContextParameter)
                    ? NOT_GENERATE
                    : (methodType == ClientMethodType.PagingAsyncSinglePage) ? NOT_VISIBLE : VISIBLE;
            } else {
                // at present, only generate convenience method for simple API and pageable API (no LRO)
                return ((methodType == ClientMethodType.SimpleAsync && !hasContextParameter)
                    || (methodType == ClientMethodType.SimpleSync && !hasContextParameter)
                    || (methodType == ClientMethodType.PagingAsync && !hasContextParameter)
                    || (methodType == ClientMethodType.PagingSync && !hasContextParameter))
//                        || (methodType == ClientMethodType.SimpleSyncRestResponse && hasContextParameter))
                    ? VISIBLE
                    : NOT_GENERATE;
            }
        } else {
            return VISIBLE;
        }
    }

    private static void addClientMethodWithContext(List<ClientMethod> methods, Builder builder,
        List<ClientMethodParameter> parameters, ClientMethodType clientMethodType, String proxyMethodName,
        ReturnValue returnValue, MethodPageDetails details, ClientMethodParameter contextParameter) {
        List<ClientMethodParameter> withContextParameters = new ArrayList<>(parameters);
        withContextParameters.add(contextParameter);

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
        return ClientMethodParameter.CONTEXT_PARAMETER;
    }

    private static void addClientMethodWithContext(List<ClientMethod> methods, Builder builder,
        List<ClientMethodParameter> parameters, ClientMethodParameter contextParameter) {
        List<ClientMethodParameter> withContextParameters = new ArrayList<>(parameters);
        withContextParameters.add(contextParameter);

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
        return parameters.stream().anyMatch(p -> !p.isRequired());
    }

    protected static String returnTypeDescription(Operation operation, IType returnType, IType baseType) {
        String description = null;
        if (returnType != PrimitiveType.Void) {
            // try the description of the operation
            if (operation.getLanguage() != null && operation.getLanguage().getDefault() != null) {
                String operationDescription = operation.getLanguage().getDefault().getDescription();
                if (!CoreUtils.isNullOrEmpty(operationDescription)) {
                    if (operationDescription.toLowerCase().startsWith("get ") || operationDescription.toLowerCase().startsWith("gets ")) {
                        int startIndex = operationDescription.indexOf(" ") + 1;
                        description = formatReturnTypeDescription(operationDescription.substring(startIndex));
                    }
                }
            }

            // try the description on the schema of return type
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

            // Mono<Boolean> of HEAD method
            if (description == null
                && baseType == PrimitiveType.Boolean
                && operation.getRequests() != null && !operation.getRequests().isEmpty()
                && operation.getRequests().get(0).getProtocol() != null
                && operation.getRequests().get(0).getProtocol().getHttp() != null
                && HttpMethod.HEAD.name().equalsIgnoreCase(operation.getRequests().get(0).getProtocol().getHttp().getMethod())
            ) {
                description = "whether resource exists";
            }

            description = DESCRIPTION_ASSEMBLER.assemble(description, returnType, baseType);

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

    private static final class ReturnTypeHolder {
        IType asyncRestResponseReturnType;
        IType asyncReturnType;
        IType syncReturnType;
        IType syncReturnWithResponse;
    }
}
