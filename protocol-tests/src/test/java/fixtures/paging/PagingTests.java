package fixtures.paging;

import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class PagingTests {
    private static AutoRestPagingTestServiceAsyncClient asyncClient;

    private static AutoRestPagingTestServiceClient client;

    @BeforeAll
    public static void setup() {
        asyncClient = new AutoRestPagingTestServiceClientBuilder().buildAsyncClient();
        client = new AutoRestPagingTestServiceClientBuilder().buildClient();
    }

    @Test
    public void getSinglePages() {
        PagedIterable<BinaryData> response = client.getSinglePages(null);
        Assertions.assertEquals(1, response.stream().count());
    }

    @Test
    public void getMultiplePages() {
        PagedIterable<BinaryData> response = client.getMultiplePages(null);
        List<BinaryData> list = response.stream().collect(Collectors.toList());
        Assertions.assertEquals(10, list.size());
    }

    @Test
    public void getOdataMultiplePages() {
        PagedIterable<BinaryData> response = client.getOdataMultiplePages(null);
        List<BinaryData> list = response.stream().collect(Collectors.toList());
        Assertions.assertEquals(10, list.size());
    }

    @Test
    public void getMultiplePagesWithOffset() throws Exception {
        PagedIterable<BinaryData> response = client.getMultiplePagesWithOffset(100, null);
        List<BinaryData> list = response.stream().collect(Collectors.toList());
        Assertions.assertEquals(10, list.size());
        Assertions.assertEquals("{\"properties\":{\"id\":110,\"name\":\"product\"}}", list.get(list.size() - 1).toString());
    }

    @Test
    public void getMultiplePagesAsync() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addHeader("client-request-id", "client-id");
        asyncClient.getMultiplePages(requestOptions)
                .doOnError(throwable -> Assertions.fail(throwable.getMessage()))
                .doOnComplete(lock::countDown)
                .blockLast();

        Assertions.assertTrue(lock.await(10000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void getMultiplePagesRetryFirst() throws Exception {
        PagedIterable<BinaryData> response = client.getMultiplePagesRetryFirst(null);
        List<BinaryData> list = response.stream().collect(Collectors.toList());
        Assertions.assertEquals(10, list.size());
    }

    @Test
    public void getMultiplePagesRetrySecond() throws Exception {
        PagedIterable<BinaryData> response = client.getMultiplePagesRetrySecond(null);
        List<BinaryData> list = response.stream().collect(Collectors.toList());
        Assertions.assertEquals(10, list.size());
    }

    @Test
    public void getSinglePagesFailure() throws Exception {
        try {
            PagedIterable<BinaryData> response  = client.getSinglePagesFailure(null);
            List<BinaryData> list = response.stream().collect(Collectors.toList());
            Assertions.fail();
        } catch (HttpResponseException ex) {
            Assertions.assertNotNull(ex.getResponse());
        }
    }

    @Test
    public void getMultiplePagesFailure() throws Exception {
        try {
            List<BinaryData> list = client.getMultiplePagesFailure(null).stream().collect(Collectors.toList());
            Assertions.fail();
        } catch (HttpResponseException ex) {
            Assertions.assertNotNull(ex.getResponse());
        }
    }

    @Test
    public void getMultiplePagesFailureUri() {
        try {
            client.getMultiplePagesFailureUri(null).stream().collect(Collectors.toList());
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof HttpResponseException);
            HttpResponseException httpResponseException = (HttpResponseException) e;
            Assertions.assertEquals(404, httpResponseException.getResponse().getStatusCode());
        }
    }

    @Disabled
    public void getMultiplePagesFragmentNextLink() throws Exception {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("api_version", "1.6");
        PagedIterable<BinaryData> response = client.getMultiplePagesFragmentNextLink("test_user", requestOptions);
        Assertions.assertEquals(10, response.stream().count());
    }

    @Disabled
    public void getMultiplePagesFragmentWithGroupingNextLink() throws Exception {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("api_version", "1.6");
        PagedIterable<BinaryData> response = client.getMultiplePagesFragmentWithGroupingNextLink("test_user", requestOptions);
        Assertions.assertEquals(10, response.stream().count());
    }

    @Test
    public void getNoItemNamePages() {
        long count = client.getNoItemNamePages(null).stream().count();
        Assertions.assertEquals(1, count);
    }

    @Test
    public void getNullNextLinkNamePages() {
        long count = client.getNullNextLinkNamePages(null).stream().count();
        Assertions.assertEquals(1, count);
    }

    @Disabled("NextLink has parameters")
    public void getWithQueryParams() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("requiredQueryParameter", "100").addQueryParam("queryConstant", "true");
        long count = client.getWithQueryParams(requestOptions).stream().count();
        Assertions.assertEquals(2, count);
    }

    @Test
    public void getPagingModelWithItemNameWithXMSClientName() {
        long count = client.getPagingModelWithItemNameWithXmsClientName(null).stream().count();
        Assertions.assertEquals(1, count);
    }

    @Test
    public void firstResponseEmpty() {
        long count = client.firstResponseEmpty(null).stream().count();
        Assertions.assertEquals(1, count);
    }
}
