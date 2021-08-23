package fixtures.url.multi;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class QueriesTests {
    private static AutoRestUrlMutliCollectionFormatTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestUrlMutliCollectionFormatTestServiceBuilder().buildClient();
    }

    @Test
    public void arrayStringMultiNull() {
        try {
            client.getQueries().arrayStringMultiNull(null);
        }
        catch (Exception ex) {
            Assert.assertTrue(ex.getMessage().equals("Parameter arrayQuery is required and cannot be null."));
        }
    }

    @Test
    public void arrayStringMultiEmpty() {
        client.getQueries().arrayStringMultiEmpty(new ArrayList<>());
    }

    @Test
    public void arrayStringMultiValid() {
        List<String> query = new ArrayList<>();
        query.add("ArrayQuery1");
        query.add("begin!*'();:@ &=+$,/?#[]end");
        query.add(null);
        query.add("");
        client.getQueries().arrayStringMultiValid(query);
    }
}
