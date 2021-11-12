package fixtures.httpinfrastructure;

import fixtures.httpinfrastructure.models.ErrorException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HttpClientFailureTests {
    private static AutoRestHttpInfrastructureTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestHttpInfrastructureTestServiceBuilder().buildClient();
    }

    @ParameterizedTest
    @MethodSource("httpClientFailureSupplier")
    public void httpClientFailure(Executable executable, int expectedStatusCode) {
        ErrorException ex = assertThrows(ErrorException.class, executable);
        assertEquals(expectedStatusCode, ex.getResponse().getStatusCode());
    }

    public static Stream<Arguments> httpClientFailureSupplier() {
        return Stream.of(
            Arguments.of(createExecution(() -> client.getHttpClientFailures().head400()), 400),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().get400()), 400),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().options400()), 400),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().put400()), 400),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().patch400()), 400),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().post400()), 400),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().delete400()), 400),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().head401()), 401),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().get402()), 402),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().options403()), 403),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().get403()), 403),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().put404()), 404),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().patch405()), 405),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().post406()), 406),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().delete407()), 407),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().put409()), 409),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().head410()), 410),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().get411()), 411),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().options412()), 412),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().get412()), 412),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().put413()), 413),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().patch414()), 414),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().post415()), 415),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().get416()), 416),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().delete417()), 417),
            Arguments.of(createExecution(() -> client.getHttpClientFailures().head429()), 429)
        );
    }

    private static Executable createExecution(Runnable runnable) {
        return runnable::run;
    }
}
