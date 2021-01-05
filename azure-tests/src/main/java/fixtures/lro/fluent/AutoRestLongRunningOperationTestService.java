package fixtures.lro.fluent;

import com.azure.core.http.HttpPipeline;
import com.azure.core.util.serializer.SerializerAdapter;
import java.time.Duration;

/**
 * The interface for AutoRestLongRunningOperationTestService class.
 */
public interface AutoRestLongRunningOperationTestService {
    /**
     * Gets server parameter.
     * 
     * @return the endpoint value.
     */
    String getEndpoint();

    /**
     * Gets The HTTP pipeline to send requests through.
     * 
     * @return the httpPipeline value.
     */
    HttpPipeline getHttpPipeline();

    /**
     * Gets The default poll interval for long-running operation.
     * 
     * @return the defaultPollInterval value.
     */
    Duration getDefaultPollInterval();

    /**
     * Gets the LROsClient object to access its operations.
     * 
     * @return the LROsClient object.
     */
    LROsClient getLROs();

    /**
     * Gets the LroRetrysClient object to access its operations.
     * 
     * @return the LroRetrysClient object.
     */
    LroRetrysClient getLroRetrys();

    /**
     * Gets the LrosaDsClient object to access its operations.
     * 
     * @return the LrosaDsClient object.
     */
    LrosaDsClient getLrosaDs();

    /**
     * Gets the LrosCustomHeadersClient object to access its operations.
     * 
     * @return the LrosCustomHeadersClient object.
     */
    LrosCustomHeadersClient getLrosCustomHeaders();
}
