package com.specialheaders.conditionalrequest.generated.conditionalrequest;

import com.specialheaders.conditionalrequest.ConditionalRequestClient;
import com.specialheaders.conditionalrequest.ConditionalRequestClientBuilder;
import org.junit.jupiter.api.Test;

public class ConditionalTests {

    private final ConditionalRequestClient client = new ConditionalRequestClientBuilder().buildClient();

    @Test
    public void testConditional() {
        client.postIfMatch("\"valid\"");

        client.postIfNoneMatch("\"invalid\"");
    }
}
