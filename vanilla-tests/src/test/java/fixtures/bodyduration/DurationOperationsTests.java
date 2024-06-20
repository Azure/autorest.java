package fixtures.bodyduration;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DurationOperationsTests {
    private static AutoRestDurationTestService client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestDurationTestServiceBuilder().buildClient();
    }

    @Test
    public void getNull() {
        assertNull(client.getDurationOperations().getNull());
    }

    @Test
    public void getInvalid() {
        RuntimeException e = assertThrows(RuntimeException.class, () -> client.getDurationOperations().getInvalid());
        assertInstanceOf(InvalidFormatException.class, e.getCause());
    }

    @Test
    @Disabled("The duration sent from the test server includes year and month values, which our durations don't support.")
    public void getPositiveDuration() {
        client.getDurationOperations().getPositiveDuration();
    }

    @Test
    @Disabled("The test server expects the duration to have a year and month component, which our durations don't support.")
    public void putPositiveDuration() {
        client.getDurationOperations().putPositiveDuration(Duration.ofDays(123).plusHours(22).plusMinutes(14).plusSeconds(12).plusMinutes(11));
    }
}
