package fixtures.bodystring.generated;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import fixtures.bodystring.AutoRestSwaggerBATServiceBuilder;
import fixtures.bodystring.StringOperationClient;

public class StringPutWhitespace {
    public static void main(String[] args) {
        StringOperationClient client =
                new AutoRestSwaggerBATServiceBuilder().host("http://localhost:3000").buildStringOperationClient();
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.setBody(
                BinaryData.fromString(
                        "\"<tab><space><space>Now is the time for all good men to come to the aid of their country<tab><space><space>\""));
        Response<Void> response = client.putWhitespaceWithResponse(requestOptions, Context.NONE);
    }
}
