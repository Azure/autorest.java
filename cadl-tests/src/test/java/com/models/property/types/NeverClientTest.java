package com.models.property.types;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NeverClientTest {

    NeverClient client = new TypesClientBuilder().buildNeverClient();

    @Test
    void get() {
        Object response = client.get();
        Assertions.assertEquals("{}", response.toString());
    }

    @Test
    void put() {
        client.put(new Object());
    }
}