/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest;

import com.azure.autorest.extension.base.model.codemodel.*;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.mapper.ClientMethodMapperAccessor;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.*;
import com.azure.autorest.model.javamodel.JavaPackage;
import com.azure.core.http.HttpMethod;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

/**
 * unit test entry
 */
public class JavagenUnitTests {

    @Test
    public void generateTest(){
        MockUnitJavagen javagen = new MockUnitJavagen();
        // SOURCE SWAGGER URL: https://github.com/Azure/azure-rest-api-specs/blob/main/specification/containerregistry/data-plane/Azure.ContainerRegistry/stable/2021-07-01/containerregistry.json
        // It's generated by preprocessor. It can be found under preprocessor module after calling autorest for generation.
        String fileName = "containerregistry-code-model-processed-no-tags.yaml";

        CodeModel codeModel = javagen.parseCodeModel(fileName);
        Client client = Mappers.getClientMapper().map(codeModel);
        JavaPackage javaPackage = javagen.writeToTemplates(JavaSettings.getInstance(), codeModel, client);

        System.out.println(javaPackage.getJavaFiles().size());
    }

    /**
     * Test the correctness of generated returnType javadoc.
     * It consists of two dimensions:
     * 1. whether description exists already on operation or schema itself
     * 2. when baseType and returnType varies
     */
    @Test
    public void returnTypeDescTest(){

        Operation operation;
        IType returnType;
        IType baseType;
        String description;
        String operationDesc;
        String responseSchemaDesc;
        String expectedDescription;

        // description on operation
        // Mono
        operationDesc ="desc from operation";
        responseSchemaDesc = "desc from response schema";
        operation = operationWithDescOnOperationAndResponseSchema(operationDesc, responseSchemaDesc);
        expectedDescription = String.format("%s on successful completion of {@link Mono}", operationDesc);

        // Mono<Void>
        baseType = PrimitiveType.Void;
        returnType = GenericType.Mono(baseType);
        description = ClientMethodMapperAccessor.getDescription(operation, returnType, baseType);
        Assert.assertEquals(expectedDescription, description);        // Mono<Boolean>
        baseType = PrimitiveType.Boolean;
        returnType = GenericType.Mono(baseType);
        description = ClientMethodMapperAccessor.getDescription(operation, returnType, baseType);
        Assert.assertEquals(expectedDescription, description);
        //Response
        operation = operationWithDescOnOperationAndResponseSchema(operationDesc, responseSchemaDesc);
        expectedDescription = String.format("%s along with {@link Response}", operationDesc);
        // Response<Void>
        baseType = PrimitiveType.Void;
        returnType = GenericType.Response(baseType);
        description = ClientMethodMapperAccessor.getDescription(operation, returnType, baseType);
        Assert.assertEquals(expectedDescription, description);        // Response<Boolean>
        baseType = PrimitiveType.Boolean;
        returnType = GenericType.Response(baseType);
        description = ClientMethodMapperAccessor.getDescription(operation, returnType, baseType);
        Assert.assertEquals(expectedDescription, description);

        // description on schema
        // Mono
        operation = operationWithDescOnResponseSchema(responseSchemaDesc);
        expectedDescription = String.format("%s on successful completion of {@link Mono}", responseSchemaDesc);
        // Mono<Void>
        baseType = PrimitiveType.Void;
        returnType = GenericType.Mono(baseType);
        description = ClientMethodMapperAccessor.getDescription(operation, returnType, baseType);
        Assert.assertEquals(expectedDescription, description);        // Mono<Boolean>
        baseType = PrimitiveType.Boolean;
        returnType = GenericType.Mono(baseType);
        description = ClientMethodMapperAccessor.getDescription(operation, returnType, baseType);
        Assert.assertEquals(expectedDescription, description);        // Mono<T>
        baseType = GenericType.Response(ClassType.String);
        returnType = GenericType.Mono(baseType);
        description = ClientMethodMapperAccessor.getDescription(operation, returnType, baseType);
        expectedDescription = "desc from response schema along with {@link Response} on successful completion of {@link Mono}";
        Assert.assertEquals(expectedDescription, description);

        // Response
        operation = operationWithDescOnResponseSchema(responseSchemaDesc);
        expectedDescription = String.format("%s along with {@link Response}", responseSchemaDesc);
        // Response<Void>
        baseType = PrimitiveType.Void;
        returnType = GenericType.Response(baseType);
        description = ClientMethodMapperAccessor.getDescription(operation, returnType, baseType);
        Assert.assertEquals(expectedDescription, description);
        // Response<Boolean>
        baseType = PrimitiveType.Boolean;
        returnType = GenericType.Response(baseType);
        description = ClientMethodMapperAccessor.getDescription(operation, returnType, baseType);
        Assert.assertEquals(expectedDescription, description);
        // Response<T>
        baseType = GenericType.Mono(ClassType.String);
        returnType = GenericType.Response(baseType);
        description = ClientMethodMapperAccessor.getDescription(operation, returnType, baseType);
        Assert.assertEquals(expectedDescription, description);


        // no description on either operation or schema
        // Mono
        operation = operationWithNoDesc();
        // Mono<Void>
        baseType = PrimitiveType.Void;
        returnType = GenericType.Mono(baseType);
        description = ClientMethodMapperAccessor.getDescription(operation, returnType, baseType);
        expectedDescription = "A {@link Mono} that completes when a successful response is received";
        Assert.assertEquals(expectedDescription, description);
        // Mono<Boolean>
        baseType = PrimitiveType.Boolean;
        returnType = GenericType.Mono(baseType);
        description = ClientMethodMapperAccessor.getDescription(operation, returnType, baseType);
        expectedDescription = String.format("%s on successful completion of {@link Mono}", "whether resource exists");
        Assert.assertEquals(expectedDescription, description);
        // Mono<OtherType>
        baseType = GenericType.Response(ClassType.String);
        returnType = GenericType.Mono(baseType);
        description = ClientMethodMapperAccessor.getDescription(operation, returnType, baseType);
        expectedDescription = "the response body along with {@link Response} on successful completion of {@link Mono}";
        Assert.assertEquals(expectedDescription, description);

        // Response
        operation = operationWithNoDesc();
        // Response<Void>
        baseType = PrimitiveType.Void;
        returnType = GenericType.Response(baseType);
        description = ClientMethodMapperAccessor.getDescription(operation, returnType, baseType);
        expectedDescription = "the {@link Response}";
        Assert.assertEquals(expectedDescription, description);
        // Response<Boolean>
        baseType = PrimitiveType.Boolean;
        returnType = GenericType.Response(baseType);
        description = ClientMethodMapperAccessor.getDescription(operation, returnType, baseType);
        expectedDescription = String.format("%s along with {@link Response}", "whether resource exists");
        Assert.assertEquals(expectedDescription, description);
        // Response<T>
        baseType = ClassType.String;
        returnType = GenericType.Response(baseType);
        description = ClientMethodMapperAccessor.getDescription(operation, returnType, baseType);
        expectedDescription = "the response body along with {@link Response}";
        Assert.assertEquals(expectedDescription, description);
    }

    private Operation operationWithNoDesc() {
        return operationWithDescOnOperationAndResponseSchema(null, null);
    }

    private Operation operationWithDescOnResponseSchema(String responseSchemaDesc) {
        return operationWithDescOnOperationAndResponseSchema(null, responseSchemaDesc);
    }

    private Operation operationWithDescOnOperationAndResponseSchema(String operationDesc, String responseSchemaDesc) {
        Operation operation;
        operation = new Operation();
        Languages languages = new Languages();
        Language defaultLang = new Language();
        if (operationDesc != null) {
            defaultLang.setDescription("get " + operationDesc);
        }
        languages.setDefault(defaultLang);
        operation.setLanguage(languages);
        Response response = new Response();
        Schema schema = new Schema();
        schema.setSummary(responseSchemaDesc);
        response.setSchema(schema);
        operation.setResponses(Lists.newArrayList(response));
        Request request = new Request();
        Protocols protocols = new Protocols();
        Protocol http = new Protocol();
        http.setMethod(HttpMethod.HEAD.name());
        protocols.setHttp(http);
        request.setProtocol(protocols);
        operation.setRequests(Lists.newArrayList(request));
        return operation;
    }


}
