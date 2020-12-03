package com.azure.testserverreport;

import com.azure.android.core.http.Response;
import com.azure.androidtest.fixtures.report.AutoRestReportServiceClient;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ReportCoverage {

    @Test
    public void reportTest() {
        AutoRestReportServiceClient client = new AutoRestReportServiceClient.Builder().host("http://100.64.244.219:3000").build();
        final Response<Map<String, Integer>> reportResponse = client.getReportWithRestResponse(System.getProperty("java.version"));
        assertEquals(200, reportResponse.getStatusCode());

        final Map<String, Integer> report = reportResponse.getValue();
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
