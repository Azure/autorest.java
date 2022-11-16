// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.cadl.paged;

import com.azure.core.http.rest.PagedFlux;
import com.azure.core.http.rest.PagedResponse;
import com.azure.core.http.rest.PagedResponseBase;
import com.azure.core.util.BinaryData;
import com.cadl.paged.implementation.PagedClientImpl;
import com.cadl.paged.models.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PagedTests {

    @Test
    public void testPaged() {
        PagedClientImpl impl = Mockito.mock(PagedClientImpl.class);

        List<BinaryData> firstPage = Arrays.asList(
                newResource("id1", "name1", "type1"),
                newResource("id2", "name2", "type1"),
                newResource("id3", "name3", "type1")
        );
        List<BinaryData> secondPage = Arrays.asList(
                newResource("id4", "name4", "type2"),
                newResource("id5", "name5", "type2")
        );

        Mockito.when(impl.listAsync(Mockito.any()))
                .thenReturn(new PagedFlux<>(
                        (pageSize) -> Mono.just(new PagedResponseBase<Void, BinaryData>(null, 200, null, firstPage, "nextLink1",  null)),
                        (nextLink, pageSize) -> Mono.just(new PagedResponseBase<Void, BinaryData>(null, 200, null, secondPage, null,  null))));

        PagedAsyncClient client = new PagedAsyncClient(impl);

        PagedFlux<Resource> resourcePagedFlux = client.listConvenience();

        // 5 items
        Assertions.assertEquals(5, resourcePagedFlux.count().block());

        // 2 pages
        Assertions.assertEquals(2, resourcePagedFlux.byPage().count().block());

        // first page
        Assertions.assertEquals(3, resourcePagedFlux.byPage().take(1).last().map(PagedResponse::getValue).block().size());
        Assertions.assertEquals(Arrays.asList("id1", "id2", "id3"), resourcePagedFlux.byPage().take(1).last().map(PagedResponse::getValue).block().stream().map(Resource::getId).collect(Collectors.toList()));
        // second page
        Assertions.assertEquals(2, resourcePagedFlux.byPage().take(2).last().map(PagedResponse::getValue).block().size());
        Assertions.assertEquals(Arrays.asList("id4", "id5"), resourcePagedFlux.byPage().take(2).last().map(PagedResponse::getValue).block().stream().map(Resource::getId).collect(Collectors.toList()));
    }

    private static BinaryData newResource(String id, String name, String type) {
        Map<String, String> resource = new LinkedHashMap<>();
        resource.put("id", id);
        resource.put("name", name);
        resource.put("type", type);
        return BinaryData.fromObject(resource);
    }
}
