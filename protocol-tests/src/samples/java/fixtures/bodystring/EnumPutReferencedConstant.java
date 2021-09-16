package fixtures.bodystring;

import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;

public class EnumPutReferencedConstant {
    public static void main(String[] args) {
        EnumClient client = new AutoRestSwaggerBATServiceBuilder().host(System.getenv("HOST")).buildEnumClient();
        BinaryData enumStringBody = BinaryData.fromString("\"green-color\"");
        Response<Void> response = client.putReferencedConstantWithResponse(enumStringBody, null, null);
    }
}
