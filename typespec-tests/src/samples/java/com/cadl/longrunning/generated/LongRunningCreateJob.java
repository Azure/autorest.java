// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.longrunning.generated;

import com.azure.core.util.Configuration;
import com.azure.core.util.polling.SyncPoller;
import com.cadl.longrunning.LongRunningClient;
import com.cadl.longrunning.LongRunningClientBuilder;
import com.cadl.longrunning.models.JobResult;
import com.cadl.longrunning.models.JobResultResult;
import java.util.HashMap;
import java.util.Map;

public class LongRunningCreateJob {
    public static void main(String[] args) {
        LongRunningClient longRunningClient
            = new LongRunningClientBuilder().endpoint(Configuration.getGlobalConfiguration().get("ENDPOINT"))
                .buildClient();
        // BEGIN:com.cadl.longrunning.generated.createjob.longrunningcreatejob
        SyncPoller<JobResult, JobResultResult> response = longRunningClient.beginCreateJob(mapOf(), null);
        // END:com.cadl.longrunning.generated.createjob.longrunningcreatejob
    }

    // Use "Map.of" if available
    @SuppressWarnings("unchecked")
    private static <T> Map<String, T> mapOf(Object... inputs) {
        Map<String, T> map = new HashMap<>();
        for (int i = 0; i < inputs.length; i += 2) {
            String key = (String) inputs[i];
            T value = (T) inputs[i + 1];
            map.put(key, value);
        }
        return map;
    }
}
