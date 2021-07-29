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
        asyncClient = new AutoRestHttpInfrastructureTestServiceBuilder().buildHttpClientFailureAsyncClient();
        client = new AutoRestHttpInfrastructureTestServiceBuilder().buildHttpClientFailureClient();
    }

    @Test
    public void head400() throws Exception {
        try {
            client.head400(null);
            Assertions.fail();
        } catch (HttpResponseException e) {
            Assertions.assertEquals(400, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void get400() throws Exception {
        try {
            client.get400(null);
            Assertions.fail();
        } catch (HttpResponseException e) {
            Assertions.assertEquals(400, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void put400() throws Exception {
        try {
            client.put400(null);
            Assertions.fail();
        } catch (HttpResponseException e) {
            Assertions.assertEquals(400, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void patch400() throws Exception {
        try {
            client.patch400(null);
            Assertions.fail();
        } catch (HttpResponseException e) {
            Assertions.assertEquals(400, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void post400() throws Exception {
        try {
            client.post400(null);
            Assertions.fail();
        } catch (HttpResponseException e) {
            Assertions.assertEquals(400, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void delete400() throws Exception {
        try {
            client.delete400(null);
            Assertions.fail();
        } catch (HttpResponseException e) {
            Assertions.assertEquals(400, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void head401() throws Exception {
        try {
            client.head401(null);
            Assertions.fail();
        } catch (HttpResponseException e) {
            Assertions.assertEquals(401, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void get402() throws Exception {
        try {
            client.get402(null);
            Assertions.fail();
        } catch (HttpResponseException e) {
            Assertions.assertEquals(402, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void get403() throws Exception {
        try {
            client.get403(null);
            Assertions.fail();
        } catch (HttpResponseException e) {
            Assertions.assertEquals(403, e.getResponse().getStatusCode());
        }
    }

    @Test
    public void put404() throws Exception {
        try {
            client.put404(null);
            Assertions.fail();
        } catch (HttpResponseException e) {
            Assertions.assertEquals(404, e.getResponse().getStatusCode());
        }
    }
}
