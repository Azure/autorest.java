package fixtures.bodystring;

import com.azure.core.http.rest.Response;

public class StringPutEmpty {
    public static void main(String[] args) {
        StringOperationClient client =
                new AutoRestSwaggerBATServiceBuilder().host(System.getenv("HOST")).buildStringOperationClient();
        Response<Void> response = client.putEmptyWithResponse(null, null);
    }
}
