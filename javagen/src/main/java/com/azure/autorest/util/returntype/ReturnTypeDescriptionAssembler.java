/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */


package com.azure.autorest.util.returntype;

import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.core.http.rest.Response;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class ReturnTypeDescriptionAssembler {

    // generic returnType description handler registry
    private static final Map<String, GenericReturnTypeDescriptionHandler> HANDLER_MAP = new HashMap<>();

    static {
        // register generic description handlers
        HANDLER_MAP.put(Mono.class.getName(), new MonoDescriptionHandler());
        HANDLER_MAP.put(Response.class.getName(), new ResponseDescriptionHandler());
    }

    /**
     * Assemble description for return types.
     * @param description   parsed swagger description of the returnType, either from operation description, or schema itself
     * @param returnType    actual returnType that needs documentation
     * @param baseType      baseType of the returnType
     * @return  assembled description
     */
    public static String assemble(String description, IType returnType, IType baseType) {
        if (returnType instanceof GenericType) {
            GenericReturnTypeDescriptionHandler handler = HANDLER_MAP.get(String.format("%s.%s", ((GenericType) returnType).getPackage(), ((GenericType) returnType).getName()));
            if (handler != null) {
                return handler.handle(description, (GenericType) returnType, baseType);
            }
        }
        return description;
    }

    private static class MonoDescriptionHandler implements GenericReturnTypeDescriptionHandler {

        /*
        Mono<Void> - A {@link Mono} that completes when a successful response is received
        Mono<T> - "something" on successful completion of {@link Mono} (something here is the description in the operation)
        Mono<OtherType> - the response body on successful completion of {@link Mono}
         */
        @Override
        public String handle(String description, GenericType returnType, IType baseType) {
            if (description == null) {
                if (ClassType.Void == baseType) {
                    return "A {@link Mono} that completes when a successful response is received";
                } else {
                    return "the response body on successful completion of {@link Mono}";
                }
            } else {
                return String.format("%s on successful completion of {@link Mono}", description);
            }
        }
    }

    private static class ResponseDescriptionHandler implements GenericReturnTypeDescriptionHandler {

        /*
        Response<Void> - the {@link Response}
        Response<T> - "something" along with {@link Response}
        Response<OtherType> - the response body along with {@link Response}
         */
        @Override
        public String handle(String description, GenericType returnType, IType baseType) {
            if (description == null) {
                if (ClassType.Void == baseType) {
                    return "the {@link Response}";
                } else {
                    return "the response body along with {@link Response}";
                }
            } else {
                return String.format("%s along with {@link Response}", description);
            }
        }
    }


    private interface GenericReturnTypeDescriptionHandler {
        String handle(String description, GenericType returnType, IType baseType);
    }

}
