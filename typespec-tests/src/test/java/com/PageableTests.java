// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com;

import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.http.rest.PagedIterable;
import com.payload.pageable.PageableClient;
import com.payload.pageable.PageableClientBuilder;
import com.payload.pageable.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PageableTests {

    private final PageableClient client = new PageableClientBuilder().httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS)).buildClient();

    @Test
    public void testPageable() {
        PagedIterable<User> pagedIterable = client.list(5);
        Assertions.assertEquals(4, pagedIterable.streamByPage(3).mapToInt(p -> p.getValue().size()).sum());
    }
}
