/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */


package com.azure.autorest.util.returntype;

import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.core.http.rest.Response;
import com.azure.core.util.logging.ClientLogger;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class ReturnTypeDescriptionAssembler {
    private static final ClientLogger LOGGER = new ClientLogger(ReturnTypeDescriptionAssembler.class);

    // generic returnType description handler registry
    private static final ReturnTypeDescriptionHandlerRegistry HANDLER_REGISTRY = new ReturnTypeDescriptionHandlerRegistry();

    static {
        // register generic description handlers
        HANDLER_REGISTRY.addFirst(new MonoDescriptionHandler());
        HANDLER_REGISTRY.addFirst(new ResponseDescriptionHandler());
    }

    /**
     * Assemble description for return types.
     * @param description   parsed swagger description of the returnType, either from operation description, or schema itself
     * @param returnType    actual returnType that needs documentation
     * @param baseType      baseType of the returnType
     * @return  assembled description
     */
    public static String assemble(String description, IType returnType, IType baseType) {
        ReturnTypeDescriptionHandler<IType> handler = HANDLER_REGISTRY.getHandler(returnType);
        if (handler != null) {
            return handler.handle(description, returnType, baseType);
        }
        return description;
    }

    private static class MonoDescriptionHandler implements ReturnTypeDescriptionHandler<GenericType> {

        @Override
        public boolean accept(IType returnType) {
            return returnType instanceof GenericType && Mono.class.isAssignableFrom(getGenericClass((GenericType) returnType));
        }

        /*
        Mono<Void> - A {@link Mono} that completes when a successful response is received
        Mono<Response> - "Response return type description" on successful completion of {@link Mono}
        Mono<T> - "something" on successful completion of {@link Mono} (something here is the description in the operation)
        Mono<OtherType> - the response body on successful completion of {@link Mono}
         */
        @Override
        public String handle(String description, GenericType returnType, IType baseType) {
            String assembledDesc;
            if (isTypeResponse(returnType.getTypeArguments()[0])) {
                assembledDesc = String.format(
                    "%s on successful completion of {@link Mono}",
                    HANDLER_REGISTRY.getHandler(returnType.getTypeArguments()[0]).handle(description, returnType.getTypeArguments()[0], baseType)
                );
            } else {
                if (description == null) {
                    if (PrimitiveType.Void == baseType) {
                        assembledDesc = String.format("A {@link %s} that completes when a successful response is received", returnType.getName());
                    } else {
                        assembledDesc = String.format("the response body on successful completion of {@link %s}", returnType.getName());
                    }
                } else {
                    assembledDesc = String.format("%s on successful completion of {@link %s}", description, returnType.getName());
                }
            }
            return assembledDesc;
        }

        private boolean isTypeResponse(IType type) {
            return type instanceof GenericType &&
                Response.class.isAssignableFrom(getGenericClass((GenericType) type));
        }
    }

    private static class ResponseDescriptionHandler implements ReturnTypeDescriptionHandler<GenericType> {

        @Override
        public boolean accept(IType returnType) {
            return returnType instanceof GenericType && Response.class.isAssignableFrom(getGenericClass((GenericType) returnType));
        }

        /*
        Response<Void> - the {@link Response}
        Response<T> - "something" along with {@link Response}
        Response<OtherType> - the response body along with {@link Response}
         */
        @Override
        public String handle(String description, GenericType returnType, IType baseType) {
            String assembledDesc;
            if (description == null) {
                if (PrimitiveType.Void == baseType) {
                    assembledDesc = String.format("the {@link %s}", returnType.getName());
                } else {
                    assembledDesc = String.format("the response body along with {@link %s}", returnType.getName());
                }
            } else {
                assembledDesc = String.format("%s along with {@link %s}", description, returnType.getName());
            }
            return assembledDesc;
        }
    }


    private static Class<?> getGenericClass(GenericType type) {
        try {
            return Class.forName(String.format("%s.%s", type.getPackage(), type.getName()));
        } catch (ClassNotFoundException e) {
            throw LOGGER.logExceptionAsError(new RuntimeException(e));
        }
    }

    private interface ReturnTypeDescriptionHandler<T extends IType> {
        boolean accept(IType returnType);
        String handle(String description, T returnType, IType baseType);
    }
    
    private static class ReturnTypeDescriptionHandlerRegistry {

        private final List<ReturnTypeDescriptionHandler<?>> handlers = new ArrayList<>();

        <T extends IType> ReturnTypeDescriptionHandler<T> getHandler(T type) {
            return (ReturnTypeDescriptionHandler<T>) handlers.stream().filter(handler -> handler.accept(type)).findFirst().orElse(null);
        }

        public <T extends IType> void addFirst(ReturnTypeDescriptionHandler<T> descriptionHandler) {
            handlers.add(0, descriptionHandler);
        }
    }

}
