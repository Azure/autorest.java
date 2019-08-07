package com.azure.autorest.extension.base.jsonrpc;

import com.azure.core.implementation.util.TypeUtil;

import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class CallerResponse<T> extends CompletableFuture<T> {
    public String id;
    private Type type;

    public CallerResponse(String id, Type type) {
        this.id = id;
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean complete(Object result) {
        T value;
        Function<Object, Boolean> trueLikeValue = obj -> obj != null && !obj.equals(0) && !obj.equals(false) && !obj.equals("");
        if (TypeUtil.isTypeOrSubTypeOf(type, Boolean.class)) {
            value = (T) (trueLikeValue.apply(result));
        }
        else {
            value = (T) result;
        }
        return super.complete(value);
    }

    @Override
    public boolean completeExceptionally(Throwable error) {
        return super.completeExceptionally(error);
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return super.cancel(mayInterruptIfRunning);
    }
}
