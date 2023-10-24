// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.credential.KeyCredential;
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Configuration;
import com.azure.core.util.UrlBuilder;
import com.azure.core.util.polling.LongRunningOperationStatus;
import com.azure.core.util.serializer.SerializerAdapter;
import com.azure.identity.DefaultAzureCredentialBuilder;
import fixtures.bodycomplex.AutoRestComplexTestServiceVersion;
import fixtures.bodycomplex.InheritanceAsyncClient;
import fixtures.bodycomplex.InheritanceClient;
import fixtures.bodycomplex.InheritanceClientBuilder;
import fixtures.bodycomplex.implementation.AutoRestComplexTestServiceClientImpl;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;

public class InheritancePutValid {
    public static void main(String[] args) {
        InheritanceClient inheritanceClient = new InheritanceClientBuilder().host("http://localhost:3000").buildClient();
        // BEGIN:fixtures.bodycomplex.generated.inheritanceputvalid.inheritanceputvalid
        BinaryData complexBody = BinaryData.fromString("{\"name\":\"Siameee\",\"breed\":\"persion\",\"color\":\"green\",\"hates\":[{\"name\":\"Potato\",\"food\":\"tomato\",\"id\":1},{\"name\":\"Tomato\",\"food\":\"french fries\",\"id\":-1}],\"id\":2}");
        RequestOptions requestOptions = new RequestOptions();
        Response<Void> response = inheritanceClient.putValidWithResponse(complexBody, requestOptions);
        // END:fixtures.bodycomplex.generated.inheritanceputvalid.inheritanceputvalid
    }
}
