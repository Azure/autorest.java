package fixtures.bodystring.generated;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import fixtures.bodystring.AutoRestSwaggerBATServiceBuilder;
import fixtures.bodystring.StringOperationClient;

public class StringGetWhitespace {
    public static void main(String[] args) {
        StringOperationClient client =
                new AutoRestSwaggerBATServiceBuilder().host("http://localhost:3000").buildStringOperationClient();
        RequestOptions requestOptions = new RequestOptions();
        Response<BinaryData> response = client.getWhitespaceWithResponse(requestOptions, Context.NONE);
    }
}
