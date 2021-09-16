package fixtures.bodystring;

import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;

public class StringGetNull {
    public static void main(String[] args) {
        StringOperationClient client =
                new AutoRestSwaggerBATServiceBuilder().host(System.getenv("HOST")).buildStringOperationClient();
        Response<BinaryData> response = client.getNullWithResponse(null, null);
    }
}
