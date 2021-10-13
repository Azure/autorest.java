package fixtures.bodystring.generated;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.Context;
import fixtures.bodystring.AutoRestSwaggerBATServiceBuilder;
import fixtures.bodystring.StringOperationClient;

public class StringPutNull {
    public static void main(String[] args) {
        StringOperationClient client =
                new AutoRestSwaggerBATServiceBuilder().host(System.getenv("HOST")).buildStringOperationClient();
        RequestOptions requestOptions = new RequestOptions();
        Response<Void> response = client.putNullWithResponse(requestOptions, Context.NONE);
    }
}
