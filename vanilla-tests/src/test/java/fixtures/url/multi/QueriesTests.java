package fixtures.url.multi;

import org.junit.BeforeClass;
import org.junit.Ignore;
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
    @Ignore("azure-core issue https://github.com/Azure/azure-sdk-for-java/issues/13124")
    public void arrayStringMultiEmpty() {
        client.getQueries().arrayStringMultiEmpty(new ArrayList<>());
    }

    @Test
    @Ignore("azure-core issue https://github.com/Azure/azure-sdk-for-java/issues/13124")
    public void arrayStringMultiValid() {
        List<String> query = new ArrayList<>();
        query.add("ArrayQuery1");
        query.add("begin!*'();:@ &=+$,/?#[]end");
        query.add(null);
        query.add("");
        client.getQueries().arrayStringMultiValid(query);
    }
}
