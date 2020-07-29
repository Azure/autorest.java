package com.azure.autorest.android.mapper;

import com.azure.autorest.android.model.AndroidClientMethod;
import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.Request;
import com.azure.autorest.extension.base.model.codemodel.Response;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.mapper.ClientMethodMapper;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
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
import com.azure.core.http.HttpMethod;
import com.azure.core.util.CoreUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class AndroidClientMethodMapper extends ClientMethodMapper {
    private static AndroidClientMethodMapper instance = new AndroidClientMethodMapper();
    private Map<Operation, List<ClientMethod>> parsed = new HashMap<>();

    protected AndroidClientMethodMapper() {
    }

    public static AndroidClientMethodMapper getInstance() {
        return instance;
    }

    protected ClientMethod.Builder createClientMethodBuilder() {
        return new AndroidClientMethod.Builder();
    }

    @Override
    public List<ClientMethod> map(Operation operation) {
        JavaSettings settings = JavaSettings.getInstance();
        if (parsed.containsKey(operation)) {
            return parsed.get(operation);
        }

        Map<Request, ProxyMethod> proxyMethods = Mappers.getProxyMethodMapper().map(operation);

        List<ClientMethod> methods = new ArrayList<>();

        ClientMethod.Builder builder = createClientMethodBuilder()
                .description(operation.getLanguage().getJava().getDescription())
                .clientReference((operation.getOperationGroup() == null
                        || operation.getOperationGroup().getLanguage().getJava().getName().isEmpty()) ?
                            "this" :
                            "this.client");

        final IType returnType;
        if (operation.getExtensions() != null && operation.getExtensions().getXmsPageable() != null) {
            Schema responseBodySchema = SchemaUtil.getLowestCommonParent(
                    operation.getResponses().stream().map(Response::getSchema).filter(Objects::nonNull).collect(Collectors.toList()));
            ClientModel responseBodyModel = Mappers.getModelMapper().map((ObjectSchema) responseBodySchema);
            IType listType = responseBodyModel.getProperties().stream()
                    .filter(p -> p.getSerializedName().equals(operation.getExtensions().getXmsPageable().getItemName()))
                    .findFirst().get().getWireType();
            IType elementType = ((ListType) listType).getElementType();
            returnType = GenericType.PagedIterable(elementType);
        } else {
            IType responseBodyType = SchemaUtil.getOperationResponseType(operation);
            if (operation.getResponses().stream().anyMatch(r -> Boolean.TRUE.equals(r.getBinary()))) {
                returnType = ClassType.OkHttp3ResponseBody;
            } else {
                returnType = responseBodyType.getClientType();
            }
        }

        for (Request request : operation.getRequests()) {
            ProxyMethod proxyMethod = proxyMethods.get(request);
            builder.proxyMethod(proxyMethod);

            List<ClientMethodParameter> parameters = new ArrayList<>();
            List<String> requiredParameterExpressions = new ArrayList<>();
            Map<String, String> validateExpressions = new HashMap<>();
            List<MethodTransformationDetail> methodTransformationDetails = new ArrayList<>();

            for (Parameter parameter : request.getParameters().stream().filter(p -> !p.isFlattened()).collect(Collectors.toList())) {
                ClientMethodParameter clientMethodParameter = Mappers.getClientParameterMapper().map(parameter);
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
                //
                //  1. From multiple client-method params    -> a single param in retrofit proxy.
                //  2. From single client-method group param -> multiple retrofit proxy method params.
                if ((parameter.getOriginalParameter() != null || parameter.getGroupedBy() != null)
                        && !(parameter.getSchema() instanceof ConstantSchema)) {
                    ClientMethodParameter outParameter;
                    if (parameter.getOriginalParameter() != null) {
                        // out-parameter is a parameter to Retrofit method.
                        // whose value will be set from multiple input parameter to client method.
                        outParameter = Mappers.getClientParameterMapper().map(parameter.getOriginalParameter());
                    } else {
                        // out-parameter is a parameter to Retrofit method.
                        // whose value will be set from a property of a group type input parameter
                        // to client method.
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
                        // the input-param to client method is a group-type.
                        mapping.setInputParameter(Mappers.getClientParameterMapper().map(parameter.getGroupedBy()));
                        ClientModel groupModel = Mappers.getModelMapper().map((ObjectSchema) parameter.getGroupedBy().getSchema());
                        ClientModelProperty inputProperty = groupModel.getProperties().stream()
                                .filter(p -> parameter.getLanguage().getJava().getName().equals(p.getName()))
                                .findFirst().get();
                        // the property of the group-type from which Retrofit out-parameter is set.
                        mapping.setInputParameterProperty(inputProperty);
                    } else {
                        // this is one of many input-param of client-method from which
                        // a group output-param to Retrofit method gets created.
                        mapping.setInputParameter(clientMethodParameter);
                    }
                    if (parameter.getOriginalParameter() != null) {
                        // The group style output-param to Retrofit
                        mapping.setOutputParameterProperty(parameter.getTargetProperty().getLanguage().getJava().getName());
                    }
                    detail.getParameterMappings().add(mapping);
                }
            }

            final boolean generateClientMethodWithOnlyRequiredParameters = settings.getRequiredParameterClientMethods() && hasNonRequiredParameters(request);

            builder.parameters(parameters)
                    .requiredNullableParameterExpressions(requiredParameterExpressions)
                    .validateExpressions(validateExpressions)
                    .methodTransformationDetails(methodTransformationDetails)
                    .methodPageDetails(null);

            if (operation.getExtensions() != null && operation.getExtensions().getXmsPageable() != null) {
                // TODO: anuchan (enable it once Pagable types are in)
//                String pageableItemName = getPageableItemName(operation);
//                if (pageableItemName != null) {
//                    boolean isNextMethod = operation.getExtensions().getXmsPageable().getNextOperation() == operation;
//                    IType lroIntermediateType = null;
//                    if (operation.getExtensions().isXmsLongRunningOperation() && !isNextMethod) {
//                        lroIntermediateType = SchemaUtil.getOperationResponseType(operation);
//                    }
//
//                    MethodPageDetails details = new MethodPageDetails(
//                            CodeNamer.getPropertyName(operation.getExtensions().getXmsPageable().getNextLinkName()),
//                            pageableItemName,
//                            (isNextMethod || operation.getExtensions().getXmsPageable().getNextOperation() == null) ? null : Mappers.getClientMethodMapper().map(operation.getExtensions().getXmsPageable().getNextOperation())
//                                    .stream().findFirst().get(),
//                            lroIntermediateType);
//                    builder.methodPageDetails(details);
//
//                    if (!(!settings.getRequiredParameterClientMethods()
//                            && settings.isContextClientMethodParameter()
//                            && JavaSettings.SyncMethodsGeneration.NONE.equals(settings.getSyncMethods()))) {
//                        methods.add(builder
//                                .returnValue(new ReturnValue(
//                                        returnTypeDescription(operation, asyncRestResponseReturnType, syncReturnType),
//                                        asyncRestResponseReturnType))
//                                .name(proxyMethod.getPagingAsyncSinglePageMethodName())
//                                .onlyRequiredParameters(false)
//                                .type(ClientMethodType.PagingAsyncSinglePage)
//                                .isGroupedParameterRequired(false)
//                                .build());
//                    }
//
//                    if (!isNextMethod) {
//                        if (settings.getSyncMethods() != JavaSettings.SyncMethodsGeneration.NONE) {
//                            methods.add(builder
//                                    .returnValue(
//                                            new ReturnValue(returnTypeDescription(operation, asyncReturnType, syncReturnType),
//                                                    asyncReturnType))
//                                    .name(proxyMethod.getSimpleAsyncMethodName())
//                                    .onlyRequiredParameters(false)
//                                    .type(ClientMethodType.PagingAsync)
//                                    .isGroupedParameterRequired(false)
//                                    .build());
//
//                            if (generateClientMethodWithOnlyRequiredParameters) {
//                                methods.add(builder
//                                        .onlyRequiredParameters(true)
//                                        .build());
//                            }
//                        }
//
//                        if (settings.getSyncMethods() == JavaSettings.SyncMethodsGeneration.ALL) {
//                            methods.add(builder
//                                    .returnValue(new ReturnValue(returnTypeDescription(operation, syncReturnType, syncReturnType),
//                                            syncReturnType))
//                                    .name(proxyMethod.getName())
//                                    .onlyRequiredParameters(false)
//                                    .type(ClientMethodType.PagingSync)
//                                    .isGroupedParameterRequired(false)
//                                    .build());
//
//                            if (generateClientMethodWithOnlyRequiredParameters) {
//                                methods.add(builder
//                                        .onlyRequiredParameters(true)
//                                        .build());
//                            }
//                        }
//                    }
//                }
            } else {
                List<ClientMethodParameter> withCallbackParameters = new ArrayList<>(parameters);
                final ClientMethodParameter callbackParam = new ClientMethodParameter.Builder()
                        .description("the Callback that receives the response.")
                        .wireType(GenericType.AndroidSimpleCallback(returnType))
                        .name("callback")
                        .annotations(new ArrayList<>())
                        .isConstant(false)
                        .defaultValue(null)
                        .fromClient(false)
                        .isFinal(true)
                        .isRequired(true)
                        .build();
                withCallbackParameters.add(callbackParam);

                // Async method with Optional parameters (always generated).
                //
                methods.add(builder
                        .parameters(withCallbackParameters)
                        .returnValue(new ReturnValue(
                                returnTypeDescription(operation,
                                        PrimitiveType.Void,
                                        PrimitiveType.Void),
                                PrimitiveType.Void))
                        .name(proxyMethod.getName())
                        .onlyRequiredParameters(false)
                        .type(ClientMethodType.SimpleAsyncRestResponse)
                        .isGroupedParameterRequired(false)
                        .build());

                // Async method with Required parameters.
                //
                if (generateClientMethodWithOnlyRequiredParameters) {
                    // generate only if the settings 'required-parameter-client-methods: true' exists.
                    methods.add(builder
                            .onlyRequiredParameters(true)
                            .build());
                }

                if (settings.getSyncMethods() == JavaSettings.SyncMethodsGeneration.ALL) {
                    // Sync method with Optional parameters.
                    //
                    GenericType responseWithResultType = GenericType.AndroidHttpResponse(returnType);
                    methods.add(builder
                            .parameters(parameters)
                            .returnValue(new ReturnValue(returnTypeDescription(operation, responseWithResultType, returnType),
                                    responseWithResultType))
                            .name(proxyMethod.getName() + "WithRestResponse")
                            .onlyRequiredParameters(false)
                            .type(ClientMethodType.SimpleSync)
                            .isGroupedParameterRequired(false)
                            .build());

                    if (generateClientMethodWithOnlyRequiredParameters) {
                        // Sync method with Required parameters.
                        //
                        methods.add(builder
                                .returnValue(new ReturnValue(returnTypeDescription(operation, returnType, returnType),
                                        returnType))
                                .name(proxyMethod.getName())
                                .onlyRequiredParameters(true)
                                .build());
                    }
                }
            }
        }
        parsed.put(operation, methods);
        return methods;
    }

    private String getPageableItemName(Operation operation) {
        Schema responseBodySchema = SchemaUtil.getLowestCommonParent(
                operation.getResponses().stream().map(Response::getSchema).filter(Objects::nonNull).collect(Collectors.toList()));
        ClientModel responseBodyModel = Mappers.getModelMapper().map((ObjectSchema) responseBodySchema);
        return responseBodyModel.getProperties().stream()
                .filter(p -> p.getSerializedName().equals(operation.getExtensions().getXmsPageable().getItemName()))
                .map(ClientModelProperty::getName).findAny().orElse(null);
    }

    private static boolean hasNonRequiredParameters(Request request) {
        return request.getParameters().stream().anyMatch(p -> p.getImplementation() == Parameter.ImplementationLocation.METHOD && !p.isRequired() && !(p.getSchema() instanceof ConstantSchema))
                && request.getParameters().stream().noneMatch(Parameter::isFlattened);   // for now, ignore operation with flattened parameters
    }

    private static String returnTypeDescription(Operation operation, IType returnType, IType baseType) {
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
                if (responseSchema != null && responseSchema.getLanguage() != null && responseSchema.getLanguage().getDefault() != null) {
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
        int endIndex = description.indexOf(".");
        if (endIndex != -1) {
            description = description.substring(0, endIndex);
        }
        if (description.length() > 0 && Character.isUpperCase(description.charAt(0))) {
            description = description.substring(0, 1).toLowerCase() + description.substring(1);
        }
        return description;
    }
}
