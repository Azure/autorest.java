package fixtures.bodystring.generated;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import fixtures.bodystring.AutoRestSwaggerBATServiceBuilder;
import fixtures.bodystring.EnumClient;

public class EnumPutReferencedConstant {
    public static void main(String[] args) {
        EnumClient client = new AutoRestSwaggerBATServiceBuilder().host("http://localhost:3000").buildEnumClient();
        BinaryData enumStringBody = BinaryData.fromString("\"green-color\"");
        RequestOptions requestOptions = new RequestOptions();
        Response<Void> response =
                client.putReferencedConstantWithResponse(enumStringBody, requestOptions, Context.NONE);
    }
}
