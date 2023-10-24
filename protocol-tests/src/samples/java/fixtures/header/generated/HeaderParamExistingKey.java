// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.header.generated;

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
import fixtures.header.AutoRestSwaggerBatHeaderServiceAsyncClient;
import fixtures.header.AutoRestSwaggerBatHeaderServiceClient;
import fixtures.header.AutoRestSwaggerBatHeaderServiceClientBuilder;
import fixtures.header.implementation.AutoRestSwaggerBatHeaderServiceClientImpl;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;

public class HeaderParamExistingKey {
    public static void main(String[] args) {
        AutoRestSwaggerBatHeaderServiceClient autoRestSwaggerBatHeaderServiceClient = new AutoRestSwaggerBatHeaderServiceClientBuilder().host("http://localhost:3000").buildClient();
        // BEGIN:fixtures.header.generated.headerparamexistingkey.headerparamexistingkey
        RequestOptions requestOptions = new RequestOptions();
        Response<Void> response = autoRestSwaggerBatHeaderServiceClient.paramExistingKeyWithResponse("overwrite", requestOptions);
        // END:fixtures.header.generated.headerparamexistingkey.headerparamexistingkey
    }
}
