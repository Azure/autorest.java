package fixtures.url.multi;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class QueriesTests {
    private static AutoRestUrlMutliCollectionFormatTestService client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestUrlMutliCollectionFormatTestServiceBuilder().buildClient();
    }

    @Test
    public void arrayStringMultiNull() {
        client.getQueries().arrayStringMultiNull(null);
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
