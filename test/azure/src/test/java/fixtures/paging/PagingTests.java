package fixtures.paging;

import com.microsoft.azure.v2.CloudException;
import com.microsoft.azure.v2.Page;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.*;
import fixtures.paging.implementation.AutoRestPagingTestServiceImpl;
import fixtures.paging.models.CustomParameterGroup;
import fixtures.paging.models.PagingGetMultiplePagesWithOffsetOptions;
import fixtures.paging.models.Product;
import fixtures.paging.models.ProductProperties;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import rx.Observer;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class PagingTests {
    private static AutoRestPagingTestServiceImpl client;

    @BeforeClass
    public static void setup() {
        final HttpPipeline httpPipeline = HttpPipeline.build(
                new ProtocolPolicy.Factory("http"),
                new PortPolicy.Factory(3000),
                new RetryPolicy.Factory(),
                new AddCookiesPolicy.Factory());
        client = new AutoRestPagingTestServiceImpl(httpPipeline);
    }

    @Test
    public void getSinglePages() throws Exception {
        List<Product> response = client.pagings().getSinglePages();
        Assert.assertEquals(1, response.size());
    }

    @Test
    public void getMultiplePages() throws Exception {
        List<Product> response = client.pagings().getMultiplePages();
        Product p1 = new Product();
        p1.withProperties(new ProductProperties());
        response.add(p1);
        response.get(3);
        Product p4 = new Product();
        p4.withProperties(new ProductProperties());
        response.add(p4);
        int i = 0;
        for (Product p : response) {
            if (++i == 7) {
                break;
            }
        }
        Assert.assertEquals(12, response.size());
        Assert.assertEquals(1, response.indexOf(p1));
        Assert.assertEquals(4, response.indexOf(p4));
    }

    @Test
    public void getOdataMultiplePages() throws Exception {
        List<Product> response = client.pagings().getOdataMultiplePages();
        Assert.assertEquals(10, response.size());
    }

    @Test
    public void getMultiplePagesWithOffset() throws Exception {
        PagingGetMultiplePagesWithOffsetOptions options = new PagingGetMultiplePagesWithOffsetOptions();
        options.withOffset(100);
        List<Product> response = client.pagings().getMultiplePagesWithOffset(options, "client-id");
        Assert.assertEquals(10, response.size());
        Assert.assertEquals(110, (int) response.get(response.size() - 1).properties().id());
    }

    @Test
    public void getMultiplePagesAsync() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);
        client.pagings().getMultiplePagesAsync("client-id", null)
                .toBlocking()
                .subscribe(new Observer<Page<Product>>() {
                    @Override
                    public void onCompleted() {
                        lock.countDown();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        fail(throwable.getMessage());
                    }

                    @Override
                    public void onNext(Page<Product> productPage) { }
                });

        Assert.assertTrue(lock.await(10000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void getMultiplePagesRetryFirst() throws Exception {
        List<Product> response = client.pagings().getMultiplePagesRetryFirst();
        Assert.assertEquals(10, response.size());
    }

    @Test
    public void getMultiplePagesRetrySecond() throws Exception {
        List<Product> response = client.pagings().getMultiplePagesRetrySecond();
        Assert.assertEquals(10, response.size());
    }

    @Test
    public void getSinglePagesFailure() throws Exception {
        try {
            List<Product> response = client.pagings().getSinglePagesFailure();
            fail();
        } catch (CloudException ex) {
            Assert.assertNotNull(ex.response());
        }
    }

    @Test
    public void getMultiplePagesFailure() throws Exception {
        try {
            List<Product> response = client.pagings().getMultiplePagesFailure();
            response.size();
            fail();
        } catch (CloudException ex) {
            Assert.assertNotNull(ex.response());
        }
    }

    @Test(expected = CloudException.class)
    public void getMultiplePagesFailureUri() throws Exception {
        client.pagings().getMultiplePagesFailureUri();
    }

    @Test
    public void getMultiplePagesFragmentNextLink() throws Exception {
        List<Product> response = client.pagings().getMultiplePagesFragmentNextLink("test_user", "1.6");
        Assert.assertEquals(10, response.size());
    }

    @Test
    public void getMultiplePagesFragmentWithGroupingNextLink() throws Exception {
        List<Product> response = client.pagings().getMultiplePagesFragmentWithGroupingNextLink(new CustomParameterGroup().withTenant("test_user").withApiVersion("1.6"));
        Assert.assertEquals(10, response.size());
    }
}
