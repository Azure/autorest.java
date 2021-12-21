package com.azure.autorest.android.mapper;

import com.azure.autorest.android.model.clientmodel.AndroidProxyMethod;
import com.azure.autorest.mapper.ProxyMethodMapper;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.ProxyMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation.None;

public class AndroidProxyMethodMapper extends ProxyMethodMapper {
    private static ProxyMethodMapper instance = new AndroidProxyMethodMapper();

    protected AndroidProxyMethodMapper() {
    }

    public static ProxyMethodMapper getInstance() {
        return instance;
    }

    @Override
    protected ProxyMethod.Builder createProxyMethodBuilder() {
        return new AndroidProxyMethod.Builder();
    }

    @Override
    protected IType createBinaryContentAsyncReturnType() {
        return PrimitiveType.Void;
    }

    @Override
    protected IType createStreamContentAsyncReturnType() {
        return PrimitiveType.Void;
    }

    @Override
    protected IType createAsyncResponseReturnType(ClassType clientResponseClassType) {
        return PrimitiveType.Void; // return GenericType.CompletableFuture(clientResponseClassType);
    }

    @Override
    protected IType createSingleValueAsyncReturnType(IType singleValueType) {
        return PrimitiveType.Void; // return GenericType.CompletableFuture(singleValueType);
    }

    @Override
    protected ClassType getContextClass() {
        return ClassType.AndroidContext;
    }

    @Override
    protected Map<Integer, ClassType> getDefaultHttpStatusCodeToExceptionTypeMapping() {
        return new HashMap<>();
    }

    @Override
    protected ClassType getHttpResponseExceptionType() {
        return ClassType.AndroidHttpResponseException;
    }

    @Override
    protected void appendCallbackParameter(List<ProxyMethodParameter> parameters, IType responseBodyType) {
        ProxyMethodParameter callbackParameter = new ProxyMethodParameter.Builder()
                .description("The async callback associated with this operation.")
                .wireType(GenericType.AndroidCallback(GenericType.AndroidResponse(responseBodyType)))
                .clientType(GenericType.AndroidCallback(GenericType.AndroidResponse(responseBodyType)))
                .name("callback")
                .requestParameterLocation(None)
                .requestParameterName("callback")
                .alreadyEncoded(true)
                .isConstant(false)
                .isRequired(false)
                .isNullable(false)
                .fromClient(false)
                .parameterReference("callback")
                .build();
        parameters.add(callbackParameter);
    }
}
