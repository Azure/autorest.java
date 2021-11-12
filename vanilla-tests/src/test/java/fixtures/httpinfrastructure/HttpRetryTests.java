package fixtures.httpinfrastructure;

import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.rest.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpRetryTests {
    private static AutoRestHttpInfrastructureTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestHttpInfrastructureTestServiceBuilder()
            .pipeline(new HttpPipelineBuilder().policies(new RetryPolicy(), new CookiePolicy()).build()).buildClient();
    }

    @ParameterizedTest
    @MethodSource("httpRetrySupplier")
    public void httpRetry(Mono<Response<?>> call, int expectedStatusCode) {
        StepVerifier.create(call)
            .assertNext(response -> assertEquals(expectedStatusCode, response.getStatusCode()))
            .expectComplete()
            .verify(Duration.ofSeconds(1));
    }

    public static Stream<Arguments> httpRetrySupplier() {
        return Stream.of(
            Arguments.of(Mono.defer(() -> client.getHttpRetries().head408WithResponseAsync()), 200),
            Arguments.of(Mono.defer(() -> client.getHttpRetries().put500WithResponseAsync()), 200),
            Arguments.of(Mono.defer(() -> client.getHttpRetries().patch500WithResponseAsync()), 200),
            Arguments.of(Mono.defer(() -> client.getHttpRetries().get502WithResponseAsync()), 200),
            Arguments.of(Mono.defer(() -> client.getHttpRetries().options502WithResponseAsync()), 200),
            Arguments.of(Mono.defer(() -> client.getHttpRetries().post503WithResponseAsync()), 200),
            Arguments.of(Mono.defer(() -> client.getHttpRetries().delete503WithResponseAsync()), 200),
            Arguments.of(Mono.defer(() -> client.getHttpRetries().put504WithResponseAsync()), 200),
            Arguments.of(Mono.defer(() -> client.getHttpRetries().patch504WithResponseAsync()), 200)
        );
    }
}
