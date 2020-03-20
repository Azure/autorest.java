package fixtures.azurereport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public final class CoverageReporter {
    private static AutoRestReportServiceForAzure client = new AutoRestReportServiceForAzureBuilder().build();

    private CoverageReporter() { }

    public static void main(String[] args) throws Exception {
        Map<String, Integer> report = client.getReport(System.getProperty("java.version"));

        int total = report.size();
        int hit = 0;
        List<String> missing = new ArrayList<>();
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
