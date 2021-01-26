package fixtures.paging;

import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.PagedIterable;
import fixtures.paging.models.CustomParameterGroup;
import fixtures.paging.models.PagingGetMultiplePagesWithOffsetOptions;
import fixtures.paging.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class PagingTests {
    private static AutoRestPagingTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestPagingTestServiceBuilder()
                .buildClient();
    }

    @Test
    public void getSinglePages() throws Exception {
        PagedIterable<Product> response = client.getPagings().getSinglePages();
        Assertions.assertEquals(1, response.stream().count());
    }

    @Test
    public void getMultiplePages() throws Exception {
        List<Product> response = client.getPagings().getMultiplePages(null, null)
                .stream().collect(Collectors.toList());
        Assertions.assertEquals(10, response.size());
    }

    @Test
    public void getOdataMultiplePages() throws Exception {
        List<Product> response = client.getPagings().getOdataMultiplePages(null, null)
                .stream().collect(Collectors.toList());
        Assertions.assertEquals(10, response.size());
    }

    @Test
    public void getMultiplePagesWithOffset() throws Exception {
        PagingGetMultiplePagesWithOffsetOptions options = new PagingGetMultiplePagesWithOffsetOptions();
        options.setOffset(100);
        List<Product> response = client.getPagings().getMultiplePagesWithOffset(options, "client-id")
                .stream().collect(Collectors.toList());
        Assertions.assertEquals(10, response.size());
        Assertions.assertEquals(110, (int) response.get(response.size() - 1).getProperties().getId());
    }

    @Test
    public void getMultiplePagesAsync() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);
        client.getPagings().getMultiplePagesAsync("client-id", null)
                .doOnError(throwable -> Assertions.fail(throwable.getMessage()))
                .doOnComplete(lock::countDown)
                .blockLast();

        Assertions.assertTrue(lock.await(10000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void getMultiplePagesRetryFirst() throws Exception {
        List<Product> response = client.getPagings().getMultiplePagesRetryFirst()
                .stream().collect(Collectors.toList());
        Assertions.assertEquals(10, response.size());
    }

    @Test
    public void getMultiplePagesRetrySecond() throws Exception {
        List<Product> response = client.getPagings().getMultiplePagesRetrySecond()
                .stream().collect(Collectors.toList());
        Assertions.assertEquals(10, response.size());
    }

    @Test
    public void getSinglePagesFailure() throws Exception {
        try {
            List<Product> response = client.getPagings().getSinglePagesFailure()
                    .stream().collect(Collectors.toList());
            Assertions.fail();
        } catch (HttpResponseException ex) {
            Assertions.assertNotNull(ex.getResponse());
        }
    }

    @Test
    public void getMultiplePagesFailure() throws Exception {
        try {
            List<Product> response = client.getPagings().getMultiplePagesFailure()
                    .stream().collect(Collectors.toList());
            response.size();
            Assertions.fail();
        } catch (HttpResponseException ex) {
            Assertions.assertNotNull(ex.getResponse());
        }
    }

    @Test
    public void getMultiplePagesFailureUri() {
        try {
            client.getPagings().getMultiplePagesFailureUri().stream().collect(Collectors.toList());
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof HttpResponseException);
            HttpResponseException httpResponseException = (HttpResponseException) e;
            Assertions.assertEquals(404, httpResponseException.getResponse().getStatusCode());
        }
    }

    @Test
    public void getMultiplePagesFragmentNextLink() throws Exception {
        PagedIterable<Product> response = client.getPagings().getMultiplePagesFragmentNextLink("1.6", "test_user");
        Assertions.assertEquals(10, response.stream().count());
    }

    @Test
    public void getMultiplePagesFragmentWithGroupingNextLink() throws Exception {
        PagedIterable<Product> response = client.getPagings().getMultiplePagesFragmentWithGroupingNextLink(
                new CustomParameterGroup().setApiVersion("1.6").setTenant("test_user"));
        Assertions.assertEquals(10, response.stream().count());
    }

    @Test
    public void getNoItemNamePages() {
        long count = client.getPagings().getNoItemNamePages().stream().count();
        Assertions.assertEquals(1, count);
    }

    @Test
    public void getNullNextLinkNamePages() {
        long count = client.getPagings().getNullNextLinkNamePages().stream().count();
        Assertions.assertEquals(1, count);
    }

    @Test
    public void getWithQueryParams() {
        long count = client.getPagings().getWithQueryParams(100).stream().count();
        Assertions.assertEquals(2, count);
    }

    @Test
    public void getPagingModelWithItemNameWithXMSClientName() {
        long count = client.getPagings().getPagingModelWithItemNameWithXMSClientName().stream().count();
        Assertions.assertEquals(1, count);
    }

    @Test
    public void firstResponseEmpty() {
        long count = client.getPagings().firstResponseEmpty().stream().count();
        Assertions.assertEquals(1, count);
    }

    // getMultiplePagesLRO requires Fluent build
}
