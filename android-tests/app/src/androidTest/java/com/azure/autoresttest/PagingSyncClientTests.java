package com.azure.autoresttest;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.azure.android.core.http.Response;
import com.azure.android.core.http.exception.HttpResponseException;
import com.azure.android.core.http.responsepaging.PagedDataResponseCollection;
import com.azure.android.core.util.paging.Page;
import com.azure.androidtest.fixtures.paging.AutoRestPagingTestServiceClient;
import com.azure.androidtest.fixtures.paging.models.CustomParameterGroup;
import com.azure.androidtest.fixtures.paging.models.PagingGetMultiplePagesOptions;
import com.azure.androidtest.fixtures.paging.models.PagingGetMultiplePagesWithOffsetOptions;
import com.azure.androidtest.fixtures.paging.models.PagingGetOdataMultiplePagesOptions;
import com.azure.androidtest.fixtures.paging.models.Product;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class PagingSyncClientTests {
    private static final String TEST_TENANT = "test_user";
    private static final String TEST_APIVERSION = "1.6";
    private static AutoRestPagingTestServiceClient client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestPagingTestServiceClient.Builder().host(TestConstants.testServerRootUrl()).build();
    }

    @Test
    public void getNoItemName() {
        final PagedDataResponseCollection<Product, Page<Product>> collection = client.getNoItemNamePagesWithPageResponse();
        assertAllPages(collection);
    }

    @Test
    public void getFirstResponseEmpty() {
        final PagedDataResponseCollection<Product, Page<Product>> collection = client.firstResponseEmptyWithPageResponse();
        assertAllPages(collection);
    }

    @Test
    public void getMultiplePagesFailureUri() {
        final PagedDataResponseCollection<Product, Page<Product>> collection =
                client.getMultiplePagesFailureUriWithPageResponse();
        assertExceptionOnNextPage(collection);
    }

    @Test
    public void getMultiplePagesFailureUriWithPage() {
        final PagedDataResponseCollection<Product, Page<Product>> collection =
                client.getMultiplePagesFailureUriWithPageResponse();
        assertExceptionOnNextPage(collection);
    }

    @Test
    public void getMultiplePagesFailure() {
        final PagedDataResponseCollection<Product, Page<Product>> collection = client.getMultiplePagesFailureWithPageResponse();
        assertExceptionOnNextPage(collection);
    }

    @Test
    public void getMultiplePagesFragmentNextLink() {
        final PagedDataResponseCollection<Product, Page<Product>> collection =
                client.getMultiplePagesFragmentNextLinkWithPageResponse(TEST_APIVERSION, TEST_TENANT);
        assertExceptionOnNextPage(collection);
    }

    @Test
    public void getMultiplePagesFragmentWithGroupingNextLink() {
        CustomParameterGroup customParameterGroup = new CustomParameterGroup();
        customParameterGroup.setApiVersion(TEST_APIVERSION).setTenant(TEST_TENANT);
        final PagedDataResponseCollection<Product, Page<Product>> collection =
                client.getMultiplePagesFragmentWithGroupingNextLinkWithPageResponse(customParameterGroup);
        assertExceptionOnNextPage(collection);
    }

    @Ignore("Retry-After header handling is not implemented")
    public void getMultiplePagesRetryFirst() {
        final PagedDataResponseCollection<Product, Page<Product>> collection =
                client.getMultiplePagesRetryFirstWithPageResponse();
        try {
            collection.getFirstPage();
            fail();
        } catch (Exception ex) {
            assertEquals(HttpResponseException.class, ex.getClass());
        }
        assertAllPages(collection);
    }

    @Ignore("Even thought this passes but it hides the fact that Retry-After header handling is not implemented")
    public void getMultiplePagesRetrySecond() {
        final PagedDataResponseCollection<Product, Page<Product>> collection = client.getMultiplePagesRetrySecondWithPageResponse();
        assertExceptionOnNextPage(collection);
    }

    @Test
    public void getMultiplePagesWithOffset() {
        PagingGetMultiplePagesWithOffsetOptions offsetOptions = new PagingGetMultiplePagesWithOffsetOptions();
        offsetOptions.setOffset(2).setMaxresults(3).setTimeout(100);
        final PagedDataResponseCollection<Product, Page<Product>> collection =
                client.getMultiplePagesWithOffsetWithPageResponse(offsetOptions, "clientRequestId");
        assertAllPages(collection);
    }

    @Test
    public void getMultiplePages() {
        PagingGetMultiplePagesOptions multiplePagesOptions = new PagingGetMultiplePagesOptions();
        multiplePagesOptions.setMaxresults(3).setTimeout(100);
        final PagedDataResponseCollection<Product, Page<Product>> collection =
                client.getMultiplePagesWithPageResponse("clientRequestId", multiplePagesOptions);
        assertAllPages(collection);
    }

    @Test
    public void getNullNextLinkNamePages() {
        final PagedDataResponseCollection<Product, Page<Product>> collection =
                client.getNullNextLinkNamePagesWithPageResponse();
        assertAllPages(collection);
        final Response<Page<Product>> firstPageResponse = collection.getFirstPage();
        try {
            collection.getPage(firstPageResponse.getValue().getNextPageId());
            fail();
        } catch (Exception ex) {
            assertEquals(NullPointerException.class, ex.getClass());
        }
    }

    @Test
    public void getOdataMultiplePages() {
        PagingGetOdataMultiplePagesOptions multiplePagesOptions = new PagingGetOdataMultiplePagesOptions();
        multiplePagesOptions.setMaxresults(3).setTimeout(100);
        final PagedDataResponseCollection<Product, Page<Product>> collection =
                client.getOdataMultiplePagesWithPageResponse("whatever", multiplePagesOptions);
        assertAllPages(collection);
    }

    @Test
    public void getPagingModelWithItemNameWithXMSClientName() {
        final PagedDataResponseCollection<Product, Page<Product>> collection =
                client.getPagingModelWithItemNameWithXMSClientNameWithPageResponse();
        assertAllPages(collection);
    }

    @Test
    public void getSinglePagesFailure() {
        final PagedDataResponseCollection<Product, Page<Product>> collection =
                client.getSinglePagesFailureWithPageResponse();
        try {
            collection.getFirstPage();
            fail();
        } catch (Exception ex) {
            assertEquals(HttpResponseException.class, ex.getClass());
        }
    }

    @Test
    public void getSinglePages() {
        final PagedDataResponseCollection<Product, Page<Product>> collection =
                client.getSinglePagesWithPageResponse();
        assertAllPages(collection);
        assertNull(collection.getFirstPage().getValue().getNextPageId());
    }

    @Test
    public void getWithQueryParams() {
        final PagedDataResponseCollection<Product, Page<Product>> collection =
                client.getWithQueryParamsWithPageResponse(100); // must be 100!
        assertAllPages(collection);
    }

    private void assertExceptionOnNextPage(PagedDataResponseCollection<Product, Page<Product>> collection) {
        assertNotNull(collection);
        final Response<Page<Product>> firstPageResponse = collection.getFirstPage();
        assertEquals(200, firstPageResponse.getStatusCode());
        try {
            String nextPageId = firstPageResponse.getValue().getNextPageId();
            nextPageId = nextPageId.replace("localhost", TestConstants.hostIp());
            collection.getPage(nextPageId);
            fail();
        } catch (Exception ex) {
            assertEquals(HttpResponseException.class, ex.getClass());
        }
    }

    private void assertAllPages(PagedDataResponseCollection<Product, Page<Product>> collection) {
        assertNotNull(collection);
        final Response<Page<Product>> firstPageResponse = collection.getFirstPage();
        assertEquals(200, firstPageResponse.getStatusCode());
        final Page<Product> firstPage = firstPageResponse.getValue();
        assertNotNull(firstPage);
        String pageId = firstPage.getNextPageId();
        while (pageId != null) {
            pageId = pageId.replace("localhost", TestConstants.hostIp());
            final Response<Page<Product>> nextPageResponse = collection.getPage(pageId);
            assertNotNull(nextPageResponse);
            assertEquals(200, nextPageResponse.getStatusCode());
            final Page<Product> nextPage = nextPageResponse.getValue();
            if (nextPage != null) {
                pageId = nextPage.getNextPageId();
            }
        }
    }

}
