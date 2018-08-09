package fixtures.azureparametergrouping;

import com.microsoft.rest.v2.credentials.BasicAuthenticationCredentials;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.CredentialsPolicyFactory;
import com.microsoft.rest.v2.policy.PortPolicyFactory;
import com.microsoft.rest.v2.policy.ProtocolPolicyFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import fixtures.azureparametergrouping.implementation.AutoRestParameterGroupingTestServiceImpl;


public class ParameterGroupingTests {
    private static AutoRestParameterGroupingTestServiceImpl client;

    @BeforeClass
    public static void setup() {
        final HttpPipeline httpPipeline = HttpPipeline.build(
                new ProtocolPolicyFactory("http"),
                new PortPolicyFactory(3000),
                new CredentialsPolicyFactory(new BasicAuthenticationCredentials(null, null)));
        client = new AutoRestParameterGroupingTestServiceImpl(httpPipeline);
    }

    @Test
    public void postRequired() throws Exception {
        ParameterGroupingPostRequiredParameters params = new ParameterGroupingPostRequiredParameters();
        params.withBody(1234);
        params.withPath("path");
        params.withQuery(21);
        params.withCustomHeader("header");
        client.parameterGroupings().postRequired(params);
    }

    @Test
    public void postOptional() throws Exception {
        ParameterGroupingPostOptionalParameters params = new ParameterGroupingPostOptionalParameters();
        params.withQuery(21);
        params.withCustomHeader("header");
        client.parameterGroupings().postOptional(params);
    }

    @Test
    public void postMultipleParameterGroups() throws Exception {
        FirstParameterGroup first = new FirstParameterGroup();
        first.withQueryOne(21);
        first.withHeaderOne("header");
        ParameterGroupingPostMultiParamGroupsSecondParamGroup second = new ParameterGroupingPostMultiParamGroupsSecondParamGroup();
        second.withHeaderTwo("header2");
        second.withQueryTwo(42);
        client.parameterGroupings().postMultiParamGroups(first, second);
    }

    @Test
    public void postParameterGroupWithSharedParameter() throws Exception {
        FirstParameterGroup first = new FirstParameterGroup();
        first.withQueryOne(21);
        first.withHeaderOne("header");
        client.parameterGroupings().postSharedParameterGroupObject(first);
    }
}
