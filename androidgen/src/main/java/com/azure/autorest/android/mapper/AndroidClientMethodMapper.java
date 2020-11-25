package com.azure.autorest.android.mapper;

import com.azure.autorest.android.model.AndroidClientMethod;
import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.Request;
import com.azure.autorest.extension.base.model.codemodel.Response;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.mapper.ClientMethodMapper;
import com.azure.autorest.mapper.Mappers;
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
    private ClientModel optionalParametersModel;
    private final boolean shouldCollapseOptionalParameters = false;

    protected AndroidClientMethodMapper() {
    }

    public static AndroidClientMethodMapper getInstance() {
        return instance;
    }

    protected ClientMethod.Builder createClientMethodBuilder() {
        return new AndroidClientMethod.Builder();
    }

    /**
     * RestAPI mapper will map API request to an Operation. For paging case, the paging request is mapped to
     * two operations, one for get first page and one for get next page.
     */
    @Override
    public List<ClientMethod> map(Operation operation) {
        JavaSettings settings = JavaSettings.getInstance();
        if (parsed.containsKey(operation)) {
            return parsed.get(operation);
        }

        // For each operation, proxy method mapper will map the operation to one Proxy method
        // since get first page and get next page each has its own operation
        // there is only one ProxyMethod for each
        Map<Request, ProxyMethod> proxyMethods = Mappers.getProxyMethodMapper().map(operation);

        List<ClientMethod> methods = new ArrayList<>();

        for (Request request : operation.getRequests()) {
            // create service client method builder
            ClientMethod.Builder builder = createClientMethodBuilder()
                    .description(operation.getLanguage().getJava().getDescription())
                    .clientReference((operation.getOperationGroup() == null
                            || operation.getOperationGroup().getLanguage().getJava().getName().isEmpty()) ?
                            "this" :
                            "this.client");

            ProxyMethod proxyMethod = proxyMethods.get(request);
            builder.proxyMethod(proxyMethod);

            // determine common input parameters for all methods
            List<ClientMethodParameter> parameters = new ArrayList<>();
            List<Parameter> optionalParameters = new ArrayList<>();
            List<String> requiredParameterExpressions = new ArrayList<>();
            Map<String, String> validateExpressions = new HashMap<>();
            List<MethodTransformationDetail> methodTransformationDetails = new ArrayList<>();

            for (Parameter parameter : request.getParameters().stream().filter(p -> !p.isFlattened()).collect(Collectors.toList())) {
                if (this.shouldCollapseOptionalParameters && !parameter.isRequired()) {
                    optionalParameters.add(parameter);
                    continue;
                }

                // host parameters are handled by the builder
                if (parameter.getProtocol().getHttp() != null
                    && parameter.getProtocol().getHttp().getIn() == RequestParameterLocation.Uri) {
                    continue;
                }

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

            if (shouldCollapseOptionalParameters && !optionalParameters.isEmpty()) {
                collapseOptionalParameters(proxyMethod.getName(), optionalParameters, parameters);
            }

            final boolean generateClientMethodWithOnlyRequiredParameters = settings.getRequiredParameterClientMethods() && hasNonRequiredParameters(request);

            builder.parameters(parameters)
                    .requiredNullableParameterExpressions(requiredParameterExpressions)
                    .validateExpressions(validateExpressions)
                    .methodTransformationDetails(methodTransformationDetails)
                    .methodPageDetails(null);

            IType returnType = SchemaUtil.getOperationResponseType(operation);
            IType elementType = null;
            boolean isPaging = false;
            boolean isNextMethod = false;
            if (operation.getExtensions() != null && operation.getExtensions().getXmsPageable() != null) {
                if (operation.getExtensions().isXmsLongRunningOperation()) {
                    throw new UnsupportedOperationException();
                }
                // In paging case, we should return Page<elementType> and require client method template to do conversion
                // between proxy return type and paged type
                isPaging = true;
                Schema responseBodySchema = SchemaUtil.getLowestCommonParent(
                        operation.getResponses().stream().map(Response::getSchema).filter(Objects::nonNull).collect(Collectors.toList()));
                ClientModel responseBodyModel = Mappers.getModelMapper().map((ObjectSchema) responseBodySchema);

                IType listType = responseBodyModel.getProperties().stream()
                        .filter(p -> p.getSerializedName().equals(operation.getExtensions().getXmsPageable().getItemName()))
                        .findFirst().get().getWireType();
                elementType = ((ListType) listType).getElementType();
                returnType = GenericType.AndroidPage(elementType);

                final Operation nextOperation = operation.getExtensions().getXmsPageable().getNextOperation();
                isNextMethod = nextOperation == operation;
                MethodPageDetails details = new MethodPageDetails(
                        CodeNamer.getPropertyName(operation.getExtensions().getXmsPageable().getNextLinkName()),
                        getPageableItemName(operation),
                        (isNextMethod || nextOperation == null)
                                ? null
                                : Mappers.getClientMethodMapper().map(nextOperation).stream().findFirst().get(),
                        null);
                builder.methodPageDetails(details);
            }
            List<ClientMethodParameter> withCallbackParameters = new ArrayList<>(parameters);
            final ClientMethodParameter callbackParam = new ClientMethodParameter.Builder()
                    .description("the Callback that receives the response.")
                    .wireType(GenericType.AndroidCallback(returnType.getClientType()))
                    .name("callback")
                    .annotations(new ArrayList<>())
                    .isConstant(false)
                    .defaultValue(null)
                    .fromClient(false)
                    .isFinal(true)
                    .isRequired(true)
                    .build();
            withCallbackParameters.add(callbackParam);

            ClientMethodType methodType = isPaging ? ClientMethodType.PagingAsync : ClientMethodType.SimpleAsyncRestResponse;
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
                    .type(methodType)
                    .onlyRequiredParameters(false)
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
                methodType = isPaging ? ClientMethodType.PagingSync : ClientMethodType.SimpleSync;
                GenericType responseWithResultType = GenericType.AndroidHttpResponse(returnType.getClientType());
                methods.add(builder
                        .parameters(parameters)
                        .returnValue(new ReturnValue(returnTypeDescription(operation, responseWithResultType, returnType),
                                responseWithResultType))
                        .name(proxyMethod.getName() + "WithRestResponse")
                        .onlyRequiredParameters(false)
                        .type(methodType)
                        .isGroupedParameterRequired(false)
                        .build());
                if (isPaging && !isNextMethod) {
                    IType pageWithResponse = GenericType.AndroidPageResponseCollection(elementType);
                    methods.add(builder
                            .parameters(parameters)
                            .returnValue(new ReturnValue(returnTypeDescription(operation, pageWithResponse, elementType),
                                    pageWithResponse))
                            .name(proxyMethod.getName() + "WithPageResponse")
                            .onlyRequiredParameters(false)
                            .type(methodType)
                            .isGroupedParameterRequired(false)
                            .build());

                    IType pageCollection = GenericType.AndroidPageRCollection(elementType);
                    methods.add(builder
                            .parameters(parameters)
                            .returnValue(new ReturnValue(returnTypeDescription(operation, pageCollection, elementType),
                                    pageCollection))
                            .name(proxyMethod.getName() + "WithPage")
                            .onlyRequiredParameters(false)
                            .type(methodType)
                            .isGroupedParameterRequired(false)
                            .build());
                }

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

            if (isPaging && !isNextMethod) {
                final ClientMethodParameter callbackCollectionParam = new ClientMethodParameter.Builder()
                        .description("the Callback that receives the response collection.")
                        .wireType(GenericType.AndroidCallback(GenericType.AndroidAsyncPagedDataCollection(elementType)))
                        .name("callback")
                        .annotations(new ArrayList<>())
                        .isConstant(false)
                        .defaultValue(null)
                        .fromClient(false)
                        .isFinal(true)
                        .isRequired(true)
                        .build();
                List<ClientMethodParameter> withCollectionCallbackParameters = new ArrayList<>(parameters);
                withCollectionCallbackParameters.add(callbackCollectionParam);

                MethodPageDetails details = new MethodPageDetails(
                        CodeNamer.getPropertyName(operation.getExtensions().getXmsPageable().getNextLinkName()),
                        getPageableItemName(operation),null,null);
                methodType = ClientMethodType.PagingAsync;
                methods.add(builder
                        .parameters(withCollectionCallbackParameters)
                        .returnValue(new ReturnValue(
                                returnTypeDescription(operation,
                                        PrimitiveType.Void,
                                        PrimitiveType.Void),
                                PrimitiveType.Void))
                        .name(proxyMethod.getName()+"PagesAsync")
                        .type(methodType)
                        .methodPageDetails(details)
                        .onlyRequiredParameters(false)
                        .isGroupedParameterRequired(false)
                        .build());
            }
        }

        parsed.put(operation, methods);
        return methods;
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

    private void collapseOptionalParameters(String methodName, List<Parameter> optionalParameters, List<ClientMethodParameter> parameters) {
        if (optionalParameters.size() == 1) {
            Parameter parameterModel = optionalParameters.get(0);
            ClientMethodParameter clientMethodParameter = Mappers.getClientParameterMapper().map(parameterModel);
            parameters.add(clientMethodParameter);
            return;
        }

        JavaSettings settings = JavaSettings.getInstance();
        String packageName = settings.getPackage(settings.getModelsSubpackage());
        AndroidOptionalParameterMapper optionalParameterMapper = new AndroidOptionalParameterMapper();
        optionalParametersModel = optionalParameterMapper.packageName(packageName).methodName(methodName).parameters(optionalParameters).build();

        String typeName = optionalParametersModel.getName();
        ClientMethodParameter.Builder optionalParameterBuilder = new ClientMethodParameter.Builder();
        optionalParameterBuilder.name(CodeNamer.toCamelCase(typeName))
                .description(String.format("Options for %1$s", methodName))
                .annotations(new ArrayList<>())
                .wireType(optionalParameterMapper.getModelType());

        parameters.add(optionalParameterBuilder.build());
    }
}
