package com.azure.autorest.android.mapper;

import com.azure.autorest.android.model.clientmodel.AndroidClientMethod;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.mapper.ClientMethodMapper;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ReturnValue;

public class AndroidClientMethodMapper extends ClientMethodMapper {
    private static ClientMethodMapper instance = new AndroidClientMethodMapper();

    private static final ClientMethodParameter ANDROID_CONTEXT_PARAM = new ClientMethodParameter.Builder()
            .description("The context to associate with this operation.")
            .wireType(ClassType.AndroidContext)
            .name("context")
            .annotations(new java.util.ArrayList<>())
            .isConstant(false)
            .defaultValue(null)
            .fromClient(false)
            .isFinal(false)
            .isRequired(false)
            .build();

    protected AndroidClientMethodMapper() {
    }

    public static ClientMethodMapper getInstance() {
        return instance;
    }

    @Override
    protected ClientMethod.Builder getClientMethodBuilder() {
        return new AndroidClientMethod.Builder();
    }

    @Override
    protected ClientMethodParameter getContextParameter() {
        return ANDROID_CONTEXT_PARAM;
    }

    @Override
    protected IType getContextType() {
        return ClassType.AndroidContext;
    }

    @Override
    protected IType createPagedRestResponseReturnType(IType elementType) {
        return GenericType.AndroidCompletableFuture(GenericType.AndroidPagedResponse(elementType));
    }

    @Override
    protected IType createPagedAsyncReturnType(IType elementType) {
        return GenericType.AndroidPagedResponse(elementType);
    }

    @Override
    protected IType createPagedSyncReturnType(IType elementType) {
        return GenericType.AndroidPagedResponse(elementType);
    }

    @Override
    protected IType createAsyncBinaryReturnType() {
        return null;
    }

    @Override
    protected IType createAsyncBodyReturnType(IType restAPIMethodReturnBodyClientType) {
        return GenericType.AndroidCompletableFuture(restAPIMethodReturnBodyClientType);
    }

    @Override
    protected IType createAsyncVoidReturnType() {
        return GenericType.AndroidCompletableFuture(ClassType.Void);
    }

    @Override
    protected IType createSyncReturnWithResponseType(IType syncReturnType, Operation operation, JavaSettings settings) {
        return (GenericType.AndroidResponse(syncReturnType));
    }

    @Override
    protected boolean shouldGeneratePagingMethods() {
        return true;
    }

    @Override
    protected ReturnValue createSimpleSyncRestResponseReturnValue(Operation operation, IType syncReturnWithResponse, IType syncReturnType) {
        return new ReturnValue(returnTypeDescription(operation, syncReturnWithResponse, syncReturnType), syncReturnWithResponse);
    }

    @Override
    protected ReturnValue createSimpleAsyncRestResponseReturnValue(Operation operation, ProxyMethod proxyMethod, IType syncReturnType) {
        IType asyncWithResponseType = GenericType.AndroidCompletableFuture(GenericType.AndroidResponse(syncReturnType));
        return new ReturnValue(returnTypeDescription(operation, proxyMethod.getReturnType().getClientType(), syncReturnType),
                asyncWithResponseType);
    }

    @Override
    protected ReturnValue createSimpleSyncReturnValue(Operation operation, IType syncReturnType) {
        return new ReturnValue(returnTypeDescription(operation, syncReturnType, syncReturnType), syncReturnType);
    }

    @Override
    protected ReturnValue createSimpleAsyncReturnValue(Operation operation, IType asyncReturnType, IType syncReturnType) {
        return new ReturnValue(returnTypeDescription(operation, asyncReturnType, syncReturnType), asyncReturnType);
    }

}
