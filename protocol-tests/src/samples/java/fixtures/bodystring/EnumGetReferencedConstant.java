package fixtures.bodystring;

import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;

public class EnumGetReferencedConstant {
    public static void main(String[] args) {
        EnumClient client = new AutoRestSwaggerBATServiceBuilder().host(System.getenv("HOST")).buildEnumClient();
        Response<BinaryData> response = client.getReferencedConstantWithResponse(null, null);
    }
}
