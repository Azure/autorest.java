package fixtures.paging;

import com.azure.core.http.rest.PagedIterable;
import fixtures.paging.models.Product;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PagingTests {
    private static AutoRestPagingTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestPagingTestServiceBuilder().build();
    }

    @Test
    public void getSinglePages() throws Exception {
        PagedIterable<Product> response = client.pagings().getSinglePages();
        Assert.assertEquals(1, response.stream().count());
    }
//
//    @Test
//    public void getMultiplePages() throws Exception {
//        List<Product> response = client.pagings().getMultiplePages();
//        Product p1 = new Product();
//        p1.withProperties(new ProductProperties());
//        response.add(p1);
//        response.get(3);
//        Product p4 = new Product();
//        p4.withProperties(new ProductProperties());
//        response.add(p4);
//        int i = 0;
//        for (Product p : response) {
//            if (++i == 7) {
//                break;
//            }
//        }
//        Assert.assertEquals(12, response.size());
//        Assert.assertEquals(1, response.indexOf(p1));
//        Assert.assertEquals(4, response.indexOf(p4));
//    }
//
//    @Test
//    public void getOdataMultiplePages() throws Exception {
//        List<Product> response = client.pagings().getOdataMultiplePages();
//        Assert.assertEquals(10, response.size());
//    }
//
//    @Test
//    public void getMultiplePagesWithOffset() throws Exception {
//        PagingsGetMultiplePagesWithOffsetOptions options = new PagingsGetMultiplePagesWithOffsetOptions();
//        options.withOffset(100);
//        List<Product> response = client.pagings().getMultiplePagesWithOffset(options, "client-id");
//        Assert.assertEquals(10, response.size());
//        Assert.assertEquals(110, (int) response.get(response.size() - 1).properties().id());
//    }
//
//    @Test
//    public void getMultiplePagesAsync() throws Exception {
//        final CountDownLatch lock = new CountDownLatch(1);
//        client.pagings().getMultiplePagesAsync("client-id", null)
//                .doOnError(throwable -> fail(throwable.getMessage()))
//                .doOnComplete(() -> lock.countDown())
//                .blockLast();
//
//        Assert.assertTrue(lock.await(10000, TimeUnit.MILLISECONDS));
//    }
//
//    @Test
//    public void getMultiplePagesRetryFirst() throws Exception {
//        List<Product> response = client.pagings().getMultiplePagesRetryFirst();
//        Assert.assertEquals(10, response.size());
//    }
//
//    @Test
//    public void getMultiplePagesRetrySecond() throws Exception {
//        List<Product> response = client.pagings().getMultiplePagesRetrySecond();
//        Assert.assertEquals(10, response.size());
//    }
//
//    @Test
//    public void getSinglePagesFailure() throws Exception {
//        try {
//            List<Product> response = client.pagings().getSinglePagesFailure();
//            fail();
//        } catch (CloudException ex) {
//            Assert.assertNotNull(ex.response());
//        }
//    }
//
//    @Test
//    public void getMultiplePagesFailure() throws Exception {
//        try {
//            List<Product> response = client.pagings().getMultiplePagesFailure();
//            response.size();
//            fail();
//        } catch (CloudException ex) {
//            Assert.assertNotNull(ex.response());
//        }
//    }
//
//    @Test(expected = CloudException.class)
//    public void getMultiplePagesFailureUri() throws Exception {
//        client.pagings().getMultiplePagesFailureUri();
//    }
//
//    @Test
//    public void getMultiplePagesFragmentNextLink() throws Exception {
//        List<Product> response = client.pagings().getMultiplePagesFragmentNextLink("test_user", "1.6");
//        Assert.assertEquals(10, response.size());
//    }
//
//    @Test
//    public void getMultiplePagesFragmentWithGroupingNextLink() throws Exception {
//        List<Product> response = client.pagings().getMultiplePagesFragmentWithGroupingNextLink(new CustomParameterGroup().withTenant("test_user").withApiVersion("1.6"));
//        Assert.assertEquals(10, response.size());
//    }
}
