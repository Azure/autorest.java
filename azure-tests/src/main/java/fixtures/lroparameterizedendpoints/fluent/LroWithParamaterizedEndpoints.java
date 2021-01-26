package fixtures.lroparameterizedendpoints.fluent;

import com.azure.core.http.HttpPipeline;
import java.time.Duration;

/** The interface for LroWithParamaterizedEndpoints class. */
public interface LroWithParamaterizedEndpoints {
    /**
     * Gets A string value that is used as a global part of the parameterized host. Pass in 'host:3000' to pass test.
     *
     * @return the host value.
     */
    String getHost();

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
     * Gets the ResourceProvidersClient object to access its operations.
     *
     * @return the ResourceProvidersClient object.
     */
    ResourceProvidersClient getResourceProviders();
}
