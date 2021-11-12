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

public class HttpSuccessTests {
    private static AutoRestHttpInfrastructureTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestHttpInfrastructureTestServiceBuilder().buildClient();
    }

    @ParameterizedTest
    @MethodSource("httpSuccessSupplier")
    public void httpSuccess(Mono<Response<?>> call, int expectedStatusCode) {
        StepVerifier.create(call)
            .assertNext(response -> assertEquals(expectedStatusCode, response.getStatusCode()))
            .expectComplete()
            .verify(Duration.ofSeconds(5));
    }

    public static Stream<Arguments> httpSuccessSupplier() {
        return Stream.of(
            Arguments.of(Mono.defer(() -> client.getHttpSuccess().head200WithResponseAsync()), 200),
            Arguments.of(Mono.defer(() -> client.getHttpSuccess().get200WithResponseAsync()), 200),
            Arguments.of(Mono.defer(() -> client.getHttpSuccess().options200WithResponseAsync()), 200),
            Arguments.of(Mono.defer(() -> client.getHttpSuccess().put200WithResponseAsync()), 200),
            Arguments.of(Mono.defer(() -> client.getHttpSuccess().patch200WithResponseAsync()), 200),
            Arguments.of(Mono.defer(() -> client.getHttpSuccess().post200WithResponseAsync()), 200),
            Arguments.of(Mono.defer(() -> client.getHttpSuccess().delete200WithResponseAsync()), 200),
            Arguments.of(Mono.defer(() -> client.getHttpSuccess().put201WithResponseAsync()), 201),
            Arguments.of(Mono.defer(() -> client.getHttpSuccess().post201WithResponseAsync()), 201),
            Arguments.of(Mono.defer(() -> client.getHttpSuccess().put202WithResponseAsync()), 202),
            Arguments.of(Mono.defer(() -> client.getHttpSuccess().patch202WithResponseAsync()), 202),
            Arguments.of(Mono.defer(() -> client.getHttpSuccess().post202WithResponseAsync()), 202),
            Arguments.of(Mono.defer(() -> client.getHttpSuccess().delete202WithResponseAsync()), 202),
            Arguments.of(Mono.defer(() -> client.getHttpSuccess().head204WithResponseAsync()), 204),
            Arguments.of(Mono.defer(() -> client.getHttpSuccess().put204WithResponseAsync()), 204),
            Arguments.of(Mono.defer(() -> client.getHttpSuccess().patch204WithResponseAsync()), 204),
            Arguments.of(Mono.defer(() -> client.getHttpSuccess().post204WithResponseAsync()), 204),
            Arguments.of(Mono.defer(() -> client.getHttpSuccess().delete204WithResponseAsync()), 204),
            Arguments.of(Mono.defer(() -> client.getHttpSuccess().head404WithResponseAsync()), 404)
        );
    }
}
