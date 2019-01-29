/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package fixtures.headexceptions.implementation;

import com.microsoft.azure.v2.AzureEnvironment;
import com.microsoft.azure.v2.AzureProxy;
import com.microsoft.azure.v2.AzureServiceClient;
import com.microsoft.rest.v2.credentials.ServiceClientCredentials;
import com.microsoft.rest.v2.http.HttpPipeline;
import io.reactivex.annotations.NonNull;

/**
 * Initializes a new instance of the AutoRestHeadExceptionTestServiceImpl type.
 */
public final class AutoRestHeadExceptionTestServiceImpl extends AzureServiceClient {
    /**
     * The preferred language for the response.
     */
    private String acceptLanguage;

    /**
     * Gets The preferred language for the response.
     *
     * @return the acceptLanguage value.
     */
    public String acceptLanguage() {
        return this.acceptLanguage;
    }

    /**
     * Sets The preferred language for the response.
     *
     * @param acceptLanguage the acceptLanguage value.
     * @return the service client itself.
     */
    public AutoRestHeadExceptionTestServiceImpl withAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
        return this;
    }

    /**
     * The retry timeout in seconds for Long Running Operations. Default value is 30.
     */
    private Integer longRunningOperationRetryTimeout;

    /**
     * Gets The retry timeout in seconds for Long Running Operations. Default value is 30.
     *
     * @return the longRunningOperationRetryTimeout value.
     */
    public Integer longRunningOperationRetryTimeout() {
        return this.longRunningOperationRetryTimeout;
    }

    /**
     * Sets The retry timeout in seconds for Long Running Operations. Default value is 30.
     *
     * @param longRunningOperationRetryTimeout the longRunningOperationRetryTimeout value.
     * @return the service client itself.
     */
    public AutoRestHeadExceptionTestServiceImpl withLongRunningOperationRetryTimeout(Integer longRunningOperationRetryTimeout) {
        this.longRunningOperationRetryTimeout = longRunningOperationRetryTimeout;
        return this;
    }

    /**
     * Whether a unique x-ms-client-request-id should be generated. When set to true a unique x-ms-client-request-id value is generated and included in each request. Default is true.
     */
    private Boolean generateClientRequestId;

    /**
     * Gets Whether a unique x-ms-client-request-id should be generated. When set to true a unique x-ms-client-request-id value is generated and included in each request. Default is true.
     *
     * @return the generateClientRequestId value.
     */
    public Boolean generateClientRequestId() {
        return this.generateClientRequestId;
    }

    /**
     * Sets Whether a unique x-ms-client-request-id should be generated. When set to true a unique x-ms-client-request-id value is generated and included in each request. Default is true.
     *
     * @param generateClientRequestId the generateClientRequestId value.
     * @return the service client itself.
     */
    public AutoRestHeadExceptionTestServiceImpl withGenerateClientRequestId(Boolean generateClientRequestId) {
        this.generateClientRequestId = generateClientRequestId;
        return this;
    }

    /**
     * The HeadExceptionsInner object to access its operations.
     */
    private HeadExceptionsInner headExceptions;

    /**
     * Gets the HeadExceptionsInner object to access its operations.
     *
     * @return the HeadExceptionsInner object.
     */
    public HeadExceptionsInner headExceptions() {
        return this.headExceptions;
    }

    /**
     * Initializes an instance of AutoRestHeadExceptionTestService client.
     *
     * @param credentials the management credentials for Azure.
     */
    public AutoRestHeadExceptionTestServiceImpl(@NonNull ServiceClientCredentials credentials) {
        this(AzureProxy.createDefaultPipeline(AutoRestHeadExceptionTestServiceImpl.class, credentials));
    }

    /**
     * Initializes an instance of AutoRestHeadExceptionTestService client.
     *
     * @param credentials the management credentials for Azure.
     * @param azureEnvironment The environment that requests will target.
     */
    public AutoRestHeadExceptionTestServiceImpl(@NonNull ServiceClientCredentials credentials, @NonNull AzureEnvironment azureEnvironment) {
        this(AzureProxy.createDefaultPipeline(AutoRestHeadExceptionTestServiceImpl.class, credentials), azureEnvironment);
    }

    /**
     * Initializes an instance of AutoRestHeadExceptionTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestHeadExceptionTestServiceImpl(@NonNull HttpPipeline httpPipeline) {
        this(httpPipeline, null);
    }

    /**
     * Initializes an instance of AutoRestHeadExceptionTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param azureEnvironment The environment that requests will target.
     */
    public AutoRestHeadExceptionTestServiceImpl(@NonNull HttpPipeline httpPipeline, @NonNull AzureEnvironment azureEnvironment) {
        super(httpPipeline, azureEnvironment);
        this.acceptLanguage = "en-US";
        this.longRunningOperationRetryTimeout = 30;
        this.generateClientRequestId = true;
        this.headExceptions = new HeadExceptionsInner(this);
    }
}
