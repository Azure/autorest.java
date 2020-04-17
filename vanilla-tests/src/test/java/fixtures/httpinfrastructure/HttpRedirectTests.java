package fixtures.httpinfrastructure;

import org.junit.BeforeClass;
import org.junit.Test;

public class HttpRedirectTests {
    private static AutoRestHttpInfrastructureTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestHttpInfrastructureTestServiceBuilder().buildClient();
    }

    @Test
    public void head300() throws Exception {
        client.getHttpRedirects().head300();
    }

    @Test
    public void get300() throws Exception {
        client.getHttpRedirects().get300();
    }

    @Test
    public void head301() throws Exception {
        client.getHttpRedirects().head301();
    }

    @Test
    public void get301() throws Exception {
        client.getHttpRedirects().get301();
    }

    @Test
    public void put301() throws Exception {
        client.getHttpRedirects().put301();
    }

    @Test
    public void head302() throws Exception {
        client.getHttpRedirects().head302();
    }

    @Test
    public void get302() throws Exception {
        client.getHttpRedirects().get302();
    }

    @Test
    public void patch302() throws Exception {
        client.getHttpRedirects().patch302();
    }

    @Test
    public void post303() throws Exception {
        client.getHttpRedirects().post303();
    }

    @Test
    public void head307() throws Exception {
        client.getHttpRedirects().head307();
    }

    @Test
    public void get307() throws Exception {
        client.getHttpRedirects().get307();
    }

    @Test
    public void patch307() throws Exception {
        client.getHttpRedirects().patch307();
    }

    @Test
    public void post307() throws Exception {
        client.getHttpRedirects().post307();
    }

    @Test
    public void delete307() throws Exception {
        client.getHttpRedirects().delete307();
    }
}
