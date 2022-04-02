// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.Language;
import com.azure.autorest.extension.base.model.codemodel.Languages;
import com.azure.autorest.extension.base.model.codemodel.Operation;
import com.azure.autorest.extension.base.model.codemodel.Protocol;
import com.azure.autorest.extension.base.model.codemodel.Protocols;
import com.azure.autorest.extension.base.model.codemodel.Request;
import com.azure.autorest.extension.base.model.codemodel.Response;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.mapper.ClientMethodMapperAccessor;
import com.azure.autorest.mapper.Mappers;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.examplemodel.ExampleNode;
import com.azure.autorest.model.javamodel.JavaPackage;
import com.azure.autorest.util.ModelExampleUtil;
import com.azure.autorest.util.ModelTestCaseUtil;
import com.azure.core.http.HttpMethod;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * unit test entry
 */
public class JavagenUnitTests {

    @Test
    public void generateTest() {
        MockUnitJavagen javagen = new MockUnitJavagen();
        // SOURCE SWAGGER URL: https://github.com/Azure/azure-rest-api-specs/blob/main/specification/containerregistry/data-plane/Azure.ContainerRegistry/stable/2021-07-01/containerregistry.json
        // It's generated by preprocessor. It can be found under preprocessor module after calling autorest for generation.
        String fileName = "containerregistry-code-model-processed-no-tags.yaml";

        CodeModel codeModel = javagen.parseCodeModel(fileName);
        Client client = Mappers.getClientMapper().map(codeModel);
        JavaPackage javaPackage = javagen.writeToTemplates(JavaSettings.getInstance(), codeModel, client);

        System.out.println(javaPackage.getJavaFiles().size());

        boolean modelTestCaseVerified = false;
        for (ClientModel model : client.getModels()) {
            Map<String, Object> jsonObject = ModelTestCaseUtil.jsonFromModel(model);
            ExampleNode exampleNode = ModelExampleUtil.parseNode(model.getType(), jsonObject);
            if ("RefColorConstant".equals(model.getName())) {
                modelTestCaseVerified = true;
                Assert.assertTrue(jsonObject.containsKey("field1"));
//                // constant
//                Assert.assertTrue(jsonMap.containsKey("ColorConstant"));
//                Assert.assertEquals("green-color", jsonMap.get("ColorConstant").toString());
            }
        }
        Assert.assertTrue(modelTestCaseVerified);
    }

    /**
     * Test the correctness of generated returnType javadoc.
     * It consists of two dimensions:
     * 1. whether description exists already on operation or schema itself
     * 2. when baseType and returnType varies
     */
    @Test
    public void returnTypeDescTest(){
        MockUnitJavagen javagen = new MockUnitJavagen(); // set NewPlugin host

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
        // Mono<Boolean>
        Assert.assertEquals(expectedDescription, description);
        baseType = PrimitiveType.Boolean;
        returnType = GenericType.Mono(baseType);
        description = ClientMethodMapperAccessor.getDescription(operation, returnType, baseType);
        // Mono<T>
        Assert.assertEquals(expectedDescription, description);
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
        // Mono<Response>
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
