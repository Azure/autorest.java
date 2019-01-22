package fixtures.azureparametergrouping;

import com.microsoft.rest.v2.credentials.BasicAuthenticationCredentials;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.CredentialsPolicyFactory;
import com.microsoft.rest.v2.policy.DecodingPolicyFactory;
import com.microsoft.rest.v2.policy.PortPolicyFactory;
import com.microsoft.rest.v2.policy.ProtocolPolicyFactory;
import fixtures.azureparametergrouping.models.ParameterGroupingsPostMultiParamGroupsSecondParamGroup;
import fixtures.azureparametergrouping.models.ParameterGroupingsPostOptionalParameters;
import fixtures.azureparametergrouping.models.ParameterGroupingsPostRequiredParameters;
import org.junit.BeforeClass;
import org.junit.Test;

import fixtures.azureparametergrouping.implementation.AutoRestParameterGroupingTestServiceImpl;
import fixtures.azureparametergrouping.models.FirstParameterGroup;

public class ParameterGroupingTests {
    private static AutoRestParameterGroupingTestServiceImpl client;

    @BeforeClass
    public static void setup() {
        client = new AutoRestParameterGroupingTestServiceImpl(
            HttpPipeline.build(
                new ProtocolPolicyFactory("http"),
                new PortPolicyFactory(3000),
                new CredentialsPolicyFactory(new BasicAuthenticationCredentials(null, null)),
                new DecodingPolicyFactory()));
    }

    @Test
    public void postRequired() throws Exception {
        ParameterGroupingsPostRequiredParameters params = new ParameterGroupingsPostRequiredParameters();
        params.withBody(1234);
        params.withPath("path");
        params.withQuery(21);
        params.withCustomHeader("header");
        client.parameterGroupings().postRequired(params);
    }

    @Test
    public void postOptional() throws Exception {
        ParameterGroupingsPostOptionalParameters params = new ParameterGroupingsPostOptionalParameters();
        params.withQuery(21);
        params.withCustomHeader("header");
        client.parameterGroupings().postOptional(params);
    }

    @Test
    public void postMultipleParameterGroups() throws Exception {
        FirstParameterGroup first = new FirstParameterGroup();
        first.withQueryOne(21);
        first.withHeaderOne("header");
        ParameterGroupingsPostMultiParamGroupsSecondParamGroup second = new ParameterGroupingsPostMultiParamGroupsSecondParamGroup();
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
