package fixtures.bodystring;

import com.azure.core.http.rest.Response;

public class EnumGetReferenced {
    public static void main(String[] args) {
        EnumClient client = new AutoRestSwaggerBATServiceBuilder().host(System.getenv("HOST")).buildEnumClient();
        Response<String> response = client.getReferencedWithResponse(null, null);
    }
}
