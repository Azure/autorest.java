package fixtures.httpinfrastructure;

import com.azure.core.exception.HttpResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HttpClientFailuresTests {
    private static HttpClientFailureAsyncClient asyncClient;

    private static HttpClientFailureClient client;

    @BeforeAll
    public static void setup() {
        asyncClient = new AutoRestHttpInfrastructureTestServiceClientBuilder().buildHttpClientFailureAsyncClient();
        client = new AutoRestHttpInfrastructureTestServiceClientBuilder().buildHttpClientFailureClient();
    }

    @Test
    public void head400() throws Exception {
        try {
            client.head400WithResponse(null).getValue();
            Assertions.fail();
        } catch (HttpResponseException e) {
            Assertions.assertEquals(400, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void get400() throws Exception {
        try {
            client.get400WithResponse(null).getValue();
            Assertions.fail();
        } catch (HttpResponseException e) {
            Assertions.assertEquals(400, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void put400() throws Exception {
        try {
            client.put400WithResponse(null).getValue();
            Assertions.fail();
        } catch (HttpResponseException e) {
            Assertions.assertEquals(400, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void patch400() throws Exception {
        try {
            client.patch400WithResponse(null).getValue();
            Assertions.fail();
        } catch (HttpResponseException e) {
            Assertions.assertEquals(400, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void post400() throws Exception {
        try {
            client.post400WithResponse(null).getValue();
            Assertions.fail();
        } catch (HttpResponseException e) {
            Assertions.assertEquals(400, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void delete400() throws Exception {
        try {
            client.delete400WithResponse(null).getValue();
            Assertions.fail();
        } catch (HttpResponseException e) {
            Assertions.assertEquals(400, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void head401() throws Exception {
        try {
            client.head401WithResponse(null).getValue();
            Assertions.fail();
        } catch (HttpResponseException e) {
            Assertions.assertEquals(401, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void get402() throws Exception {
        try {
            client.get402WithResponse(null).getValue();
            Assertions.fail();
        } catch (HttpResponseException e) {
            Assertions.assertEquals(402, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void get403() throws Exception {
        try {
            client.get403WithResponse(null).getValue();
            Assertions.fail();
        } catch (HttpResponseException e) {
            Assertions.assertEquals(403, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void put404() throws Exception {
        try {
            client.put404WithResponse(null).getValue();
            Assertions.fail();
        } catch (HttpResponseException e) {
            Assertions.assertEquals(404, e.getResponse().getStatusCode());
        }
    }
}
