package fixtures.paging;

import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.PagedIterable;
import fixtures.paging.models.CustomParameterGroup;
import fixtures.paging.models.PagingGetMultiplePagesWithOffsetOptions;
import fixtures.paging.models.Product;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.fail;

public class PagingTests {
    private static AutoRestPagingTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestPagingTestServiceBuilder().buildClient();
    }

    @Test
    public void getSinglePages() throws Exception {
        PagedIterable<Product> response = client.pagings().getSinglePages();
        Assert.assertEquals(1, response.stream().count());
    }

    @Test
    public void getMultiplePages() throws Exception {
        List<Product> response = client.pagings().getMultiplePages(null, null)
                .stream().collect(Collectors.toList());
        Assert.assertEquals(10, response.size());
    }

    @Test
    public void getOdataMultiplePages() throws Exception {
        List<Product> response = client.pagings().getOdataMultiplePages(null, null)
                .stream().collect(Collectors.toList());
        Assert.assertEquals(10, response.size());
    }

    @Test
    public void getMultiplePagesWithOffset() throws Exception {
        PagingGetMultiplePagesWithOffsetOptions options = new PagingGetMultiplePagesWithOffsetOptions();
        options.setOffset(100);
        List<Product> response = client.pagings().getMultiplePagesWithOffset(options, "client-id")
                .stream().collect(Collectors.toList());
        Assert.assertEquals(10, response.size());
        Assert.assertEquals(110, (int) response.get(response.size() - 1).getProperties().getId());
    }

    @Test
    public void getMultiplePagesAsync() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);
        client.pagings().getMultiplePagesAsync("client-id", null)
                .doOnError(throwable -> fail(throwable.getMessage()))
                .doOnComplete(lock::countDown)
                .blockLast();

        Assert.assertTrue(lock.await(10000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void getMultiplePagesRetryFirst() throws Exception {
        List<Product> response = client.pagings().getMultiplePagesRetryFirst()
                .stream().collect(Collectors.toList());
        Assert.assertEquals(10, response.size());
    }

    @Test
    public void getMultiplePagesRetrySecond() throws Exception {
        List<Product> response = client.pagings().getMultiplePagesRetrySecond()
                .stream().collect(Collectors.toList());
        Assert.assertEquals(10, response.size());
    }

    @Test
    public void getSinglePagesFailure() throws Exception {
        try {
            List<Product> response = client.pagings().getSinglePagesFailure()
                    .stream().collect(Collectors.toList());
            fail();
        } catch (HttpResponseException ex) {
            Assert.assertNotNull(ex.getResponse());
        }
    }

    @Test
    public void getMultiplePagesFailure() throws Exception {
        try {
            List<Product> response = client.pagings().getMultiplePagesFailure()
                    .stream().collect(Collectors.toList());
            response.size();
            fail();
        } catch (HttpResponseException ex) {
            Assert.assertNotNull(ex.getResponse());
        }
    }

    @Test
    public void getMultiplePagesFailureUri() {
        try {
            client.pagings().getMultiplePagesFailureUri().stream().collect(Collectors.toList());
            fail();
        } catch (Exception e) {
            Assert.assertTrue(e.getCause() instanceof MalformedURLException);
        }
    }

    @Test
    public void getMultiplePagesFragmentNextLink() throws Exception {
        PagedIterable<Product> response = client.pagings().getMultiplePagesFragmentNextLink("1.6", "test_user");
        Assert.assertEquals(10, response.stream().count());
    }

    @Test
    public void getMultiplePagesFragmentWithGroupingNextLink() throws Exception {
        PagedIterable<Product> response = client.pagings().getMultiplePagesFragmentWithGroupingNextLink(
                new CustomParameterGroup().setApiVersion("1.6").setTenant("test_user"));
        Assert.assertEquals(10, response.stream().count());
    }
}
