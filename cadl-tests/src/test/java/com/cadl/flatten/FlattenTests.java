// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.cadl.flatten;

import com.azure.core.http.HttpHeaders;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.util.BinaryData;
import com.cadl.flatten.implementation.FlattenClientImpl;
import com.cadl.flatten.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;

public class FlattenTests {

    @Test
    public void testFlatten() {
        FlattenClientImpl impl = Mockito.mock(FlattenClientImpl.class);
        ArgumentCaptor<String> idCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<BinaryData> payloadCaptor = ArgumentCaptor.forClass(BinaryData.class);
        Mockito.when(impl.sendWithResponseAsync(idCaptor.capture(), payloadCaptor.capture(), Mockito.any()))
                .thenReturn(Mono.just(new SimpleResponse<>(null, 200, new HttpHeaders(), null)));

        FlattenAsyncClient client = new FlattenAsyncClient(impl);

        client.send("id1", "input1", new User("user1"));

        Assertions.assertEquals("id1", idCaptor.getValue());
        Assertions.assertEquals("{\"input\":\"input1\",\"user\":{\"user\":\"user1\"}}", payloadCaptor.getValue().toString());

        client.send("id2", "input2");

        Assertions.assertEquals("id2", idCaptor.getValue());
        Assertions.assertEquals("{\"input\":\"input2\"}", payloadCaptor.getValue().toString());
    }
}
