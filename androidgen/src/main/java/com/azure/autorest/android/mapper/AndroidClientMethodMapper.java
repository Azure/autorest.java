package com.azure.autorest.android.mapper;

import com.azure.autorest.android.model.clientmodel.AndroidClientMethod;
import com.azure.autorest.mapper.ClientMethodMapper;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;

public class AndroidClientMethodMapper extends ClientMethodMapper {
    private static ClientMethodMapper instance = new AndroidClientMethodMapper();

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
    protected IType createPagedRestResponseReturnType(IType elementType) {
        // REVISIT: temporary hack
        return GenericType.AndroidPagedResponse(GenericType.AndroidPagedResponse(elementType));
    }

    @Override
    protected IType createPagedAsyncReturnType(IType elementType) {
        // REVISIT: temporary hack
        return GenericType.AndroidCompletableFuture(GenericType.AndroidPagedResponse(elementType));
    }

    @Override
    protected IType createPagedSyncReturnType(IType elementType) {
        // REVISIT: temporary hack
        return GenericType.AndroidCompletableFuture(GenericType.AndroidPagedResponse(elementType));
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
    protected boolean shouldGeneratePagingMethods() {
        return false;
    }
}
