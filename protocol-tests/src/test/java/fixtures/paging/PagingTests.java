package fixtures.paging;

import com.azure.core.http.rest.PagedIterable;
import com.azure.core.util.BinaryData;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;


public class PagingTests {
    private static AutoRestPagingTestServiceAsyncClient asyncClient;

    private static AutoRestPagingTestServiceClient client;

    @BeforeClass
    public static void setup() {
        asyncClient = new AutoRestPagingTestServiceBuilder().buildAsyncClient();
        client = new AutoRestPagingTestServiceBuilder().buildClient();
    }

    @Test
    public void getSinglePages() {
        PagedIterable<BinaryData> response = client.getSinglePages(null);
        Assert.assertEquals(1, response.stream().count());
    }

    @Test
    public void getMultiplePages() {
        PagedIterable<BinaryData> response = client.getMultiplePages(null, null);
        List<BinaryData> list = response.stream().collect(Collectors.toList());
        Assert.assertEquals(10, list.size());
    }

    @Test
    public void getOdataMultiplePages() {
        PagedIterable<BinaryData> response = client.getOdataMultiplePages(null);
        List<BinaryData> list = response.stream().collect(Collectors.toList());
        Assert.assertEquals(10, list.size());
    }
}
