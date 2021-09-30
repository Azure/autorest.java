package fixtures.bodystring;

import com.azure.core.http.rest.Response;

public class EnumGetNotExpandable {
    public static void main(String[] args) {
        EnumClient client = new AutoRestSwaggerBATServiceBuilder().host(System.getenv("HOST")).buildEnumClient();
        Response<String> response = client.getNotExpandableWithResponse(null, null);
    }
}
