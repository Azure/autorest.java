package fixtures.paging;

import com.azure.core.http.rest.PagedIterable;
import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;


public class PagingTests {
    private static AutoRestPagingTestServiceAsyncClient asyncClient;

    private static AutoRestPagingTestServiceClient client;

    @BeforeAll
    public static void setup() {
        asyncClient = new AutoRestPagingTestServiceBuilder().buildAsyncClient();
        client = new AutoRestPagingTestServiceBuilder().buildClient();
    }

    @Test
    public void getSinglePages() {
        PagedIterable<BinaryData> response = client.getSinglePages(null);
        Assertions.assertEquals(1, response.stream().count());
    }

    @Test
    public void getMultiplePages() {
        PagedIterable<BinaryData> response = client.getMultiplePages(null, null);
        List<BinaryData> list = response.stream().collect(Collectors.toList());
        Assertions.assertEquals(10, list.size());
    }

    @Test
    public void getOdataMultiplePages() {
        PagedIterable<BinaryData> response = client.getOdataMultiplePages(null);
        List<BinaryData> list = response.stream().collect(Collectors.toList());
        Assertions.assertEquals(10, list.size());
    }
}
