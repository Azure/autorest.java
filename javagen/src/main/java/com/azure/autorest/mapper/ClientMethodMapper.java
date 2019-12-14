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
        for (Parameter parameter : operation.getRequest().getParameters()) {
            if (parameter.getImplementation() != Parameter.ImplementationLocation.CLIENT && ! (parameter.getSchema() instanceof ConstantSchema)) {
                parameters.add(Mappers.getClientParameterMapper().map(parameter));
            }
        }
        for (ProxyMethodParameter proxyParameter : proxyMethod.getParameters()) {
            if (!proxyParameter.getIsConstant() && proxyParameter.getIsRequired()
                && !(proxyParameter.getClientType() instanceof PrimitiveType)) {
                requiredParameterExpressions.add(proxyParameter.getParameterReference());
            }
        }

        if (operation.getExtensions() != null && operation.getExtensions().getXmsPageable() != null) {
            boolean isNextMethod = operation.getExtensions().getXmsPageable().getNextOperation() == operation;

            MethodPageDetails details = new MethodPageDetails(
                    CodeNamer.getPropertyName(operation.getExtensions().getXmsPageable().getNextLinkName()),
                    CodeNamer.getPropertyName(operation.getExtensions().getXmsPageable().getItemName()),
                    isNextMethod ? null : Mappers.getClientMethodMapper().map(operation.getExtensions().getXmsPageable().getNextOperation())
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
                    operation.getDescription(),
                    new ReturnValue(null, asyncSinglePageReturnType),
                    proxyMethod.getPagingAsyncSinglePageMethodName(),
                    parameters,
                    false,
                    ClientMethodType.PagingAsyncSinglePage,
                    proxyMethod,
                    new ArrayList<>(),
                    requiredParameterExpressions,
                    false,
                    null,
                    details,
                    new ArrayList<>()));

            if (!isNextMethod) {
                methods.add(new ClientMethod(
                        operation.getDescription(),
                        new ReturnValue(null, asyncReturnType),
                        proxyMethod.getSimpleAsyncMethodName(),
                        parameters,
                        false,
                        ClientMethodType.PagingAsync,
                        proxyMethod,
                        new ArrayList<>(),
                        requiredParameterExpressions,
                        false,
                        null,
                        details,
                        new ArrayList<>()));

                methods.add(new ClientMethod(
                        operation.getDescription(),
                        new ReturnValue(null, syncReturnType),
                        proxyMethod.getName(),
                        parameters,
                        false,
                        ClientMethodType.PagingSync,
                        proxyMethod,
                        new ArrayList<>(),
                        requiredParameterExpressions,
                        false,
                        null,
                        details,
                        new ArrayList<>()));
            }
        } else {

            // WithResponseAsync, with required and optional parameters
            methods.add(new ClientMethod(
                    operation.getDescription(),
                    new ReturnValue(null, proxyMethod.getReturnType().getClientType()),
                    proxyMethod.getSimpleAsyncRestResponseMethodName(),
                    parameters,
                    false,
                    ClientMethodType.SimpleAsyncRestResponse,
                    proxyMethod,
                    new ArrayList<>(),
                    requiredParameterExpressions,
                    false,
                    null,
                    null,
                    new ArrayList<>()));

            IType responseBodyType = Mappers.getSchemaMapper().map(SchemaUtil.getLowestCommonParent(
                    operation.getResponses().stream().map(Response::getSchema).filter(Objects::nonNull).collect(Collectors.toList())));

            if (responseBodyType == null) {
                responseBodyType = PrimitiveType.Void;
            }

            // Simple Async
            if (settings.getSyncMethods() != JavaSettings.SyncMethodsGeneration.NONE) {


                IType restAPIMethodReturnBodyClientType = responseBodyType.getClientType();
                IType asyncMethodReturnType;
                if (restAPIMethodReturnBodyClientType != PrimitiveType.Void) {
                    asyncMethodReturnType = GenericType.Mono(restAPIMethodReturnBodyClientType);
                } else {
                    asyncMethodReturnType = GenericType.Mono(ClassType.Void);
                }

                methods.add(new ClientMethod(
                        operation.getDescription(),
                        new ReturnValue(null, asyncMethodReturnType),
                        proxyMethod.getSimpleAsyncMethodName(),
                        parameters,
                        false,
                        ClientMethodType.SimpleAsync,
                        proxyMethod,
                        new ArrayList<>(),
                        requiredParameterExpressions,
                        false,
                        null,
                        null,
                        new ArrayList<>()));
            }

            // Sync
            if (settings.getSyncMethods() == JavaSettings.SyncMethodsGeneration.ALL) {
                methods.add(new ClientMethod(
                        operation.getDescription(),
                        new ReturnValue(null, responseBodyType.getClientType()),
                        proxyMethod.getName(),
                        parameters,
                        false,
                        ClientMethodType.SimpleSync,
                        proxyMethod,
                        new ArrayList<>(),
                        requiredParameterExpressions,
                        false,
                        null,
                        null,
                        new ArrayList<>()));
            }
        }

        parsed.put(operation, methods);
        return methods;
    }
}
