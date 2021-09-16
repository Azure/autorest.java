package fixtures.bodystring;

import com.azure.core.http.rest.Response;

public class StringPutNull {
    public static void main(String[] args) {
        StringOperationClient client =
                new AutoRestSwaggerBATServiceBuilder().host(System.getenv("HOST")).buildStringOperationClient();
        Response<Void> response = client.putNullWithResponse(null, null);
    }
}
