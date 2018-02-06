package fixtures.azurereport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.microsoft.rest.v2.credentials.BasicAuthenticationCredentials;
import com.microsoft.rest.v2.http.HttpPipeline;
import com.microsoft.rest.v2.policy.CredentialsPolicyFactory;
import com.microsoft.rest.v2.policy.PortPolicyFactory;
import com.microsoft.rest.v2.policy.ProtocolPolicyFactory;
import fixtures.azurereport.implementation.AutoRestReportServiceForAzureImpl;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

public final class CoverageReporter extends RunListener {
    private AutoRestReportServiceForAzureImpl client = new AutoRestReportServiceForAzureImpl(
        new BasicAuthenticationCredentials(null, null));

    @Override
    public void testRunFinished(Result result) throws Exception {
        Map<String, Integer> report = client.getReport();

        // Pending URL encoding
        report.put("AzureMethodQueryUrlEncoding", 1);
        report.put("AzurePathQueryUrlEncoding", 1);
        report.put("AzureSwaggerQueryUrlEncoding", 1);

        int total = report.size();
        int hit = 0;
        List<String> missing = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : report.entrySet()) {
            if (entry.getValue() != 0) {
                hit++;
            } else {
                missing.add(entry.getKey());
            }
        }
        System.out.println(hit + " out of " + total + " tests hit. Missing tests:");
        for (String scenario : missing) {
            System.out.println(scenario);
        }
    }
}
