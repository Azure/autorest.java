package fixtures.bodystring;

import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;

public class EnumPutReferenced {
    public static void main(String[] args) {
        EnumClient client = new AutoRestSwaggerBATServiceBuilder().host(System.getenv("HOST")).buildEnumClient();
        BinaryData enumStringBody = BinaryData.fromString("\"red color\"");
        Response<Void> response = client.putReferencedWithResponse(enumStringBody, null, null);
    }
}
