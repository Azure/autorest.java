package fixtures.bodystring.generated;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import fixtures.bodystring.AutoRestSwaggerBATServiceBuilder;
import fixtures.bodystring.EnumClient;

public class EnumGetReferencedConstant {
    public static void main(String[] args) {
        EnumClient client = new AutoRestSwaggerBATServiceBuilder().host(System.getenv("HOST")).buildEnumClient();
        RequestOptions requestOptions = new RequestOptions();
        Response<BinaryData> response = client.getReferencedConstantWithResponse(requestOptions, Context.NONE);
    }
}
