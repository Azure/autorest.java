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
        asyncClient = new AutoRestPagingTestServiceBuilder().buildAsyncClient();
        client = new AutoRestPagingTestServiceBuilder().buildClient();
    }

    @Test
    public void listSinglePages() {
        PagedIterable<BinaryData> response = client.listSinglePages(null);
        Assertions.assertEquals(1, response.stream().count());
    }

    @Test
    public void listMultiplePages() {
        PagedIterable<BinaryData> response = client.listMultiplePages(null, null);
        List<BinaryData> list = response.stream().collect(Collectors.toList());
        Assertions.assertEquals(10, list.size());
    }

    @Test
    public void listOdataMultiplePages() {
        PagedIterable<BinaryData> response = client.listOdataMultiplePages(null);
        List<BinaryData> list = response.stream().collect(Collectors.toList());
        Assertions.assertEquals(10, list.size());
    }

    @Test
    public void listMultiplePagesWithOffset() throws Exception {
        PagedIterable<BinaryData> response = client.listMultiplePagesWithOffset(100, null);
        List<BinaryData> list = response.stream().collect(Collectors.toList());
        Assertions.assertEquals(10, list.size());
        Assertions.assertEquals("{\"properties\":{\"id\":110,\"name\":\"product\"}}", list.get(list.size() - 1).toString());
    }

    @Test
    public void listMultiplePagesAsync() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addHeader("client-request-id", "client-id");
        asyncClient.listMultiplePages(requestOptions)
                .doOnError(throwable -> Assertions.fail(throwable.getMessage()))
                .doOnComplete(lock::countDown)
                .blockLast();

        Assertions.assertTrue(lock.await(10000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void listMultiplePagesRetryFirst() throws Exception {
        PagedIterable<BinaryData> response = client.listMultiplePagesRetryFirst(null);
        List<BinaryData> list = response.stream().collect(Collectors.toList());
        Assertions.assertEquals(10, list.size());
    }

    @Test
    public void listMultiplePagesRetrySecond() throws Exception {
        PagedIterable<BinaryData> response = client.listMultiplePagesRetrySecond(null);
        List<BinaryData> list = response.stream().collect(Collectors.toList());
        Assertions.assertEquals(10, list.size());
    }

    @Test
    public void listSinglePagesFailure() throws Exception {
        try {
            PagedIterable<BinaryData> response  = client.listSinglePagesFailure(null);
            List<BinaryData> list = response.stream().collect(Collectors.toList());
            Assertions.fail();
        } catch (HttpResponseException ex) {
            Assertions.assertNotNull(ex.getResponse());
        }
    }

    @Test
    public void listMultiplePagesFailure() throws Exception {
        try {
            List<BinaryData> list = client.listMultiplePagesFailure(null).stream().collect(Collectors.toList());
            Assertions.fail();
        } catch (HttpResponseException ex) {
            Assertions.assertNotNull(ex.getResponse());
        }
    }

    @Test
    public void listMultiplePagesFailureUri() {
        try {
            client.listMultiplePagesFailureUri(null).stream().collect(Collectors.toList());
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof HttpResponseException);
            HttpResponseException httpResponseException = (HttpResponseException) e;
            Assertions.assertEquals(404, httpResponseException.getResponse().getStatusCode());
        }
    }

    @Disabled
    public void listMultiplePagesFragmentNextLink() throws Exception {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("api_version", "1.6");
        PagedIterable<BinaryData> response = client.listMultiplePagesFragmentNextLink("test_user", requestOptions);
        Assertions.assertEquals(10, response.stream().count());
    }

    @Disabled
    public void listMultiplePagesFragmentWithGroupingNextLink() throws Exception {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("api_version", "1.6");
        PagedIterable<BinaryData> response = client.listMultiplePagesFragmentWithGroupingNextLink("test_user", requestOptions);
        Assertions.assertEquals(10, response.stream().count());
    }

    @Test
    public void listNoItemNamePages() {
        long count = client.listNoItemNamePages(null).stream().count();
        Assertions.assertEquals(1, count);
    }

    @Test
    public void listNullNextLinkNamePages() {
        long count = client.listNullNextLinkNamePages(null).stream().count();
        Assertions.assertEquals(1, count);
    }

    @Disabled("NextLink has parameters")
    public void listWithQueryParams() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.addQueryParam("requiredQueryParameter", "100").addQueryParam("queryConstant", "true");
        long count = client.listWithQueryParams(requestOptions).stream().count();
        Assertions.assertEquals(2, count);
    }

    @Test
    public void listPagingModelWithItemNameWithXMSClientName() {
        long count = client.listPagingModelWithItemNameWithXMSClientName(null).stream().count();
        Assertions.assertEquals(1, count);
    }

    @Test
    public void firstResponseEmpty() {
        long count = client.firstResponseEmpty(null).stream().count();
        Assertions.assertEquals(1, count);
    }
}
