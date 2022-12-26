package com.models.usage;

import com.models.usage.models.InputOutputRecord;
import com.models.usage.models.InputRecord;
import com.models.usage.models.OutputRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UsageClientTest {

    UsageClient client = new UsageClientBuilder().buildClient();

    @Test
    void input() {
        InputRecord inputRecord = new InputRecord("example-value");
        client.input(inputRecord);
    }

    @Test
    void output() {
        OutputRecord outputRecord = client.output();
        Assertions.assertEquals("example-value", outputRecord.getRequiredProp());
    }

    @Test
    void inputAndOutput() {
        InputOutputRecord inputOutputRecord = new InputOutputRecord("example-value");
        InputOutputRecord response = client.inputAndOutput(inputOutputRecord);
        Assertions.assertEquals("example-value", response.getRequiredProp());
    }
}