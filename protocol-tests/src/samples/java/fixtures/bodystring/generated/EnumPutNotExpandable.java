// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodystring.generated;

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
import fixtures.bodystring.EnumAsyncClient;
import fixtures.bodystring.EnumClient;
import fixtures.bodystring.EnumClientBuilder;
import fixtures.bodystring.implementation.AutoRestSwaggerBatServiceClientImpl;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;

public class EnumPutNotExpandable {
    public static void main(String[] args) {
        EnumClient enumClient = new EnumClientBuilder().host("http://localhost:3000").buildClient();
        // BEGIN:fixtures.bodystring.generated.enumputnotexpandable.enumputnotexpandable
        BinaryData stringBody = BinaryData.fromString("\"red color\"");
        RequestOptions requestOptions = new RequestOptions();
        Response<Void> response = enumClient.putNotExpandableWithResponse(stringBody, requestOptions);
        // END:fixtures.bodystring.generated.enumputnotexpandable.enumputnotexpandable
    }
}
