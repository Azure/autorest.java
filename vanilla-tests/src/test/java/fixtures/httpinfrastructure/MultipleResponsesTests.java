package fixtures.httpinfrastructure;

import com.azure.core.exception.HttpResponseException;
import fixtures.httpinfrastructure.models.C;
import fixtures.httpinfrastructure.models.D;
import fixtures.httpinfrastructure.models.ErrorException;
import fixtures.httpinfrastructure.models.MyException;
import fixtures.httpinfrastructure.models.MyExceptionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MultipleResponsesTests {
    private static AutoRestHttpInfrastructureTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestHttpInfrastructureTestServiceBuilder().buildClient();
    }

    @Test
    public void get200Model204NoModelDefaultError200Valid() {
        MyException result = client.getMultipleResponses().get200Model204NoModelDefaultError200Valid();
        assertEquals(MyException.class, result.getClass());
        assertEquals("200", result.getStatusCode());
    }

    @Test
    public void get200Model204NoModelDefaultError204Valid() {
        MyException result = client.getMultipleResponses().get200Model204NoModelDefaultError204Valid();
        assertNull(result);
    }

    @Test
    public void get200Model204NoModelDefaultError201Invalid() {
        ErrorException ex = assertThrows(ErrorException.class,
            () -> client.getMultipleResponses().get200Model204NoModelDefaultError201Invalid());
        assertEquals(201, ex.getResponse().getStatusCode());
    }

    @Test
    public void get200Model204NoModelDefaultError202None() {
        ErrorException ex = assertThrows(ErrorException.class,
            () -> client.getMultipleResponses().get200Model204NoModelDefaultError202None());
        assertEquals(202, ex.getResponse().getStatusCode());
    }

    @Test
    public void get200Model204NoModelDefaultError400Valid() {
        ErrorException ex = assertThrows(ErrorException.class,
            () -> client.getMultipleResponses().get200Model204NoModelDefaultError400Valid());
        assertEquals(400, ex.getResponse().getStatusCode());
    }

    @Test
    public void get200Model201ModelDefaultError200Valid() {
        MyException result = client.getMultipleResponses().get200Model201ModelDefaultError200Valid();
        assertEquals("200", result.getStatusCode());
    }

    @Test
    public void get200Model201ModelDefaultError201Valid() {
        MyException result = client.getMultipleResponses().get200Model201ModelDefaultError201Valid();
        assertEquals("201", result.getStatusCode());
    }

    @Test
    public void get200Model201ModelDefaultError400Valid() {
        ErrorException ex = assertThrows(ErrorException.class,
            () -> client.getMultipleResponses().get200Model201ModelDefaultError400Valid());
        assertEquals(400, ex.getResponse().getStatusCode());
        assertEquals("client error", ex.getValue().getMessage());
    }

    @Test
    @Disabled("Not currently supported by RestProxy")
    public void get200ModelA201ModelC404ModelDDefaultError200Valid() {
        Object result = client.getMultipleResponses().get200ModelA201ModelC404ModelDDefaultError200Valid();
        MyException actual = (MyException) result;
        assertEquals("200", actual.getStatusCode());
    }

    @Test
    @Disabled("Not currently supported by RestProxy")
    public void get200ModelA201ModelC404ModelDDefaultError201Valid() {
        Object result = client.getMultipleResponses().get200ModelA201ModelC404ModelDDefaultError201Valid();
        C actual = (C) result;
        assertEquals("201", actual.getHttpCode());
    }

    @Test
    @Disabled("Not currently supported by RestProxy")
    public void get200ModelA201ModelC404ModelDDefaultError404Valid() {
        Object result = client.getMultipleResponses().get200ModelA201ModelC404ModelDDefaultError404Valid();
        D actual = (D) result;
        assertEquals("404", actual.getHttpStatusCode());
    }

    @Test
    public void get200ModelA201ModelC404ModelDDefaultError400Valid() {
        ErrorException ex = assertThrows(ErrorException.class,
            () -> client.getMultipleResponses().get200ModelA201ModelC404ModelDDefaultError400Valid());
        assertEquals(400, ex.getResponse().getStatusCode());
        assertEquals("client error", ex.getValue().getMessage());
    }

    @Test
    public void get202None204NoneDefaultError202None() {
        AutoRestHttpInfrastructureTestService localClient = new AutoRestHttpInfrastructureTestServiceBuilder()
            .buildClient();
        StepVerifier.create(localClient.getMultipleResponses().get202None204NoneDefaultError202NoneWithResponseAsync())
            .assertNext(response -> assertEquals(202, response.getStatusCode()))
            .expectComplete()
            .verify(Duration.ofMillis(1000));
    }

    @Test
    public void get202None204NoneDefaultError204None() {
        AutoRestHttpInfrastructureTestService localClient = new AutoRestHttpInfrastructureTestServiceBuilder()
            .buildClient();
        StepVerifier.create(localClient.getMultipleResponses().get202None204NoneDefaultError204NoneWithResponseAsync())
            .assertNext(response -> assertEquals(204, response.getStatusCode()))
            .expectComplete()
            .verify(Duration.ofMillis(1000));
    }

    @Test
    public void get202None204NoneDefaultError400Valid() {
        ErrorException ex = assertThrows(ErrorException.class,
            () -> client.getMultipleResponses().get202None204NoneDefaultError400Valid());
        assertEquals(400, ex.getResponse().getStatusCode());
        assertEquals("client error", ex.getValue().getMessage());
    }

    @Test
    public void get202None204NoneDefaultNone202Invalid() {
        client.getMultipleResponses().get202None204NoneDefaultNone202Invalid();
    }

    @Test
    public void get202None204NoneDefaultNone204None() {
        client.getMultipleResponses().get202None204NoneDefaultNone204None();
    }

    @Test
    public void get202None204NoneDefaultNone400None() {
        HttpResponseException ex = assertThrows(HttpResponseException.class,
            () -> client.getMultipleResponses().get202None204NoneDefaultNone400None());
        assertEquals(400, ex.getResponse().getStatusCode());
    }

    @Test
    public void get202None204NoneDefaultNone400Invalid() {
        HttpResponseException ex = assertThrows(HttpResponseException.class,
            () -> client.getMultipleResponses().get202None204NoneDefaultNone400Invalid());
        assertEquals(400, ex.getResponse().getStatusCode());
    }

    @Test
    public void getDefaultModelA200Valid() {
        client.getMultipleResponses().getDefaultModelA200Valid();
    }

    @Test
    public void getDefaultModelA200None() {
        client.getMultipleResponses().getDefaultModelA200None();
    }

    @Test
    public void getDefaultModelA400Valid() {
        MyExceptionException ex = assertThrows(MyExceptionException.class,
            () -> client.getMultipleResponses().getDefaultModelA400Valid());
        assertEquals(400, ex.getResponse().getStatusCode());
    }

    @Test
    public void getDefaultModelA400None() {
        MyExceptionException ex = assertThrows(MyExceptionException.class,
            () -> client.getMultipleResponses().getDefaultModelA400None());
        assertEquals(400, ex.getResponse().getStatusCode());
    }

    @Test
    public void getDefaultNone200Invalid() {
        client.getMultipleResponses().getDefaultNone200Invalid();
    }

    @Test
    public void getDefaultNone200None() {
        client.getMultipleResponses().getDefaultNone200None();
    }

    @Test
    public void getDefaultNone400Invalid() {
        HttpResponseException ex = assertThrows(HttpResponseException.class,
            () -> client.getMultipleResponses().getDefaultNone400Invalid());
        assertEquals(400, ex.getResponse().getStatusCode());
    }

    @Test
    public void getDefaultNone400None() {
        HttpResponseException ex = assertThrows(HttpResponseException.class,
            () -> client.getMultipleResponses().getDefaultNone400None());
        assertEquals(400, ex.getResponse().getStatusCode());
    }

    @Test
    public void get200ModelA200None() {
        MyException result = client.getMultipleResponses().get200ModelA200None();
        assertNull(result);
    }

    @Test
    public void get200ModelA200Valid() {
        MyException result = client.getMultipleResponses().get200ModelA200Valid();
        assertEquals("200", result.getStatusCode());
    }

    @Test
    public void get200ModelA200Invalid() {
        assertNull(client.getMultipleResponses().get200ModelA200Invalid().getStatusCode());
    }

    @Test
    public void get200ModelA400None() {
        HttpResponseException ex = assertThrows(HttpResponseException.class,
            () -> client.getMultipleResponses().get200ModelA400None());
        assertEquals(400, ex.getResponse().getStatusCode());
    }

    @Test
    public void get200ModelA400Valid() {
        HttpResponseException ex = assertThrows(HttpResponseException.class,
            () -> client.getMultipleResponses().get200ModelA400Valid());
        assertEquals(400, ex.getResponse().getStatusCode());
    }

    @Test
    public void get200ModelA400Invalid() {
        HttpResponseException ex = assertThrows(HttpResponseException.class,
            () -> client.getMultipleResponses().get200ModelA400Invalid());
        assertEquals(400, ex.getResponse().getStatusCode());
    }

    @Test
    public void get200ModelA202Valid() {
        HttpResponseException ex = assertThrows(HttpResponseException.class,
            () -> client.getMultipleResponses().get200ModelA202Valid());
        assertEquals(202, ex.getResponse().getStatusCode());
    }
}
