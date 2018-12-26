package fixtures.bodybyte;

import com.microsoft.rest.v2.Context;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.http.HttpRequest;
import com.microsoft.rest.v2.policy.DecodingPolicyFactory;
import com.microsoft.rest.v2.policy.RequestPolicy;
import com.microsoft.rest.v2.policy.RequestPolicyFactory;
import com.microsoft.rest.v2.policy.RequestPolicyOptions;
import fixtures.bodybyte.implementation.AutoRestSwaggerBATByteServiceImpl;
import io.reactivex.Single;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ByteOperationsTests {
    @Test
    public void getEmpty() throws Exception {
        String key = "foo";
        String value = "bar";
        Context context = new Context(key, value);

        RequestPolicyFactory auditFactory = (RequestPolicy next, RequestPolicyOptions options) -> {
            return (HttpRequest request) -> {
                Optional<Object> result = request.context().getData(key);
                if (!result.isPresent() || !value.equals(result.get())) {
                    return Single.error(new RuntimeException("context contained " + result + " instead of the expected string " + value));
                } else {
                    return next.sendAsync(request);
                }
            };
        };

        AutoRestSwaggerBATByteServiceImpl client = new AutoRestSwaggerBATByteServiceImpl(HttpPipeline.build(auditFactory, new DecodingPolicyFactory()));
        client.bytes().getNull(context);
    }
}
