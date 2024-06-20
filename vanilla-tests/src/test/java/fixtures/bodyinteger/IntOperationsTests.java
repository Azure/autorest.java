package fixtures.bodyinteger;

import com.azure.core.exception.HttpResponseException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.exc.InputCoercionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntOperationsTests {
    private static AutoRestIntegerTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestIntegerTestServiceBuilder().buildClient();
    }

    @Test
    public void getNull() {
        assertThrows(NullPointerException.class, () -> client.getInts().getNull());
    }

    @Test
    public void getNullAsync() {
        Integer i = client.getInts().getNullAsync().block();
        assertNull(i);
    }

    @Test
    public void getInvalid() {
        HttpResponseException exception = assertThrows(HttpResponseException.class, () -> client.getInts().getInvalid());
        assertInstanceOf(JsonParseException.class, exception.getCause());
    }

    @Test
    public void getOverflowInt32() {
        Exception exception = assertThrows(Exception.class, () -> client.getInts().getOverflowInt32());
        assertInstanceOf(InputCoercionException.class, exception.getCause());
    }

    @Test
    public void getUnderflowInt32() {
        Exception exception = assertThrows(Exception.class, () -> client.getInts().getUnderflowInt32());
        assertInstanceOf(InputCoercionException.class, exception.getCause());
    }

    @Test
    public void getOverflowInt64() {
        Exception exception = assertThrows(Exception.class, () -> client.getInts().getOverflowInt64());
        assertInstanceOf(InputCoercionException.class, exception.getCause());
    }

    @Test
    public void getUnderflowInt64() {
        Exception exception = assertThrows(Exception.class, () -> client.getInts().getUnderflowInt64());
        assertInstanceOf(InputCoercionException.class, exception.getCause());
    }

    @Test
    public void putMax32() {
        StepVerifier.create(client.getInts().putMax32Async(Integer.MAX_VALUE))
            .expectComplete()
            .verify(Duration.ofMillis(1000));
    }

    @Test
    public void putMax64() {
        StepVerifier.create(client.getInts().putMax64Async(Long.MAX_VALUE))
            .expectComplete()
            .verify(Duration.ofMillis(1000));
    }

    @Test
    public void putMin32() {
        StepVerifier.create(client.getInts().putMin32Async(Integer.MIN_VALUE))
            .expectComplete()
            .verify(Duration.ofMillis(1000));
    }

    @Test
    public void putMin64() {
        StepVerifier.create(client.getInts().putMin64Async(Long.MIN_VALUE))
            .expectComplete()
            .verify(Duration.ofMillis(1000));
    }

    @Disabled
    @Test
    public void getUnixTime() {
        OffsetDateTime result = client.getInts().getUnixTime();
        assertEquals(OffsetDateTime.of(2016, 4, 13, 0, 0, 0, 0, ZoneOffset.UTC), result);
    }

    @Test
    public void putUnixTimeDate() {
        client.getInts().putUnixTimeDate(OffsetDateTime.of(2016, 4, 13, 0, 0, 0, 0, ZoneOffset.UTC));
    }

    @Test
    public void getInvalidUnixTime() {
        HttpResponseException exception = assertThrows(HttpResponseException.class, () -> client.getInts().getInvalidUnixTime());
        assertInstanceOf(JsonParseException.class, exception.getCause());
    }

    @Test
    public void getNullUnixTime() {
        assertNull(client.getInts().getNullUnixTime());
    }
}
