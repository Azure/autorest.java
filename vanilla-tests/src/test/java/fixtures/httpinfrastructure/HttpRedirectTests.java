package fixtures.httpinfrastructure;

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

public class HttpRedirectTests {
    private static AutoRestHttpInfrastructureTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestHttpInfrastructureTestServiceBuilder().buildClient();
    }

    @ParameterizedTest
    @MethodSource("httpRedirectSupplier")
    public void httpRedirect(Mono<Response<?>> call, int expectedStatusCode) {
        StepVerifier.create(call)
            .assertNext(response -> assertEquals(expectedStatusCode, response.getStatusCode()))
            .expectComplete()
            .verify(Duration.ofSeconds(10));
    }

    public static Stream<Arguments> httpRedirectSupplier() {
        return Stream.of(
            Arguments.of(Mono.defer(() -> client.getHttpRedirects().head300WithResponseAsync()), 300),
            Arguments.of(Mono.defer(() -> client.getHttpRedirects().get300WithResponseAsync()), 300),
            Arguments.of(Mono.defer(() -> client.getHttpRedirects().head301WithResponseAsync()), 301),
            Arguments.of(Mono.defer(() -> client.getHttpRedirects().get301WithResponseAsync()), 301),
            Arguments.of(Mono.defer(() -> client.getHttpRedirects().put301WithResponseAsync()), 301),
            Arguments.of(Mono.defer(() -> client.getHttpRedirects().head302WithResponseAsync()), 302),
            Arguments.of(Mono.defer(() -> client.getHttpRedirects().get302WithResponseAsync()), 302),
            Arguments.of(Mono.defer(() -> client.getHttpRedirects().patch302WithResponseAsync()), 302),
            Arguments.of(Mono.defer(() -> client.getHttpRedirects().post303WithResponseAsync()), 303),
            Arguments.of(Mono.defer(() -> client.getHttpRedirects().head307WithResponseAsync()), 307),
            Arguments.of(Mono.defer(() -> client.getHttpRedirects().get307WithResponseAsync()), 307),
            Arguments.of(Mono.defer(() -> client.getHttpRedirects().options307WithResponseAsync()), 307),
            Arguments.of(Mono.defer(() -> client.getHttpRedirects().put307WithResponseAsync()), 307),
            Arguments.of(Mono.defer(() -> client.getHttpRedirects().patch307WithResponseAsync()), 307),
            Arguments.of(Mono.defer(() -> client.getHttpRedirects().post307WithResponseAsync()), 307),
            Arguments.of(Mono.defer(() -> client.getHttpRedirects().delete307WithResponseAsync()), 307)
        );
    }
}
