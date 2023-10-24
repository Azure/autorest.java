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
import fixtures.bodycomplex.PrimitiveAsyncClient;
import fixtures.bodycomplex.PrimitiveClient;
import fixtures.bodycomplex.PrimitiveClientBuilder;
import fixtures.bodycomplex.implementation.AutoRestComplexTestServiceClientImpl;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;

public class PrimitivePutDateTime {
    public static void main(String[] args) {
        PrimitiveClient primitiveClient = new PrimitiveClientBuilder().host("http://localhost:3000").buildClient();
        // BEGIN:fixtures.bodycomplex.generated.primitiveputdatetime.primitiveputdatetime
        BinaryData complexBody = BinaryData.fromString("{\"field\":\"0001-01-01T12:00:00-04:00\",\"now\":\"2015-05-18T11:38:00-08:00\"}");
        RequestOptions requestOptions = new RequestOptions();
        Response<Void> response = primitiveClient.putDateTimeWithResponse(complexBody, requestOptions);
        // END:fixtures.bodycomplex.generated.primitiveputdatetime.primitiveputdatetime
    }
}
