/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package fixtures.custombaseuri.paging;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PagingTests {

    private static AutoRestParameterizedHostTestPagingClient client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestParameterizedHostTestPagingClientBuilder()
                .host("host:3000")
                .buildClient();
    }

    @Test
    public void getPagesPartialUrl() {
        long count = client.getPagings().getPagesPartialUrl("local").stream().count();
        Assertions.assertEquals(2, count);
    }

    @Test
    public void getPagesPartialUrlOperation() {
        long count = client.getPagings().getPagesPartialUrlOperation("local").stream().count();
        Assertions.assertEquals(2, count);
    }
}
