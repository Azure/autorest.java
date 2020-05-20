package fixtures.bodyduration;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.*;

public class DurationOperationsTests {
    private static AutoRestDurationTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestDurationTestServiceBuilder().buildClient();
    }

    @Test
    public void getNull() {
        assertNull(client.getDurationOperations().getNull());
    }

    @Test
    public void getInvalid() {
        try {
            client.getDurationOperations().getInvalid();
            fail();
        }
        catch (RuntimeException e) {
            assertEquals(InvalidFormatException.class, e.getCause().getClass());
        }
    }

    @Test
    @Ignore("The duration sent from the test server includes year and month values, which our durations don't support.")
    public void getPositiveDuration() {
        client.getDurationOperations().getPositiveDuration();
    }

    @Test
    @Ignore("The test server expects the duration to have a year and month component, which our durations don't support.")
    public void putPositiveDuration() {
        client.getDurationOperations().putPositiveDuration(Duration.ofDays(123).plusHours(22).plusMinutes(14).plusSeconds(12).plusMinutes(11));
    }
}
