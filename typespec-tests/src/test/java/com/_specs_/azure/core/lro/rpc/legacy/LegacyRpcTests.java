// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com._specs_.azure.core.lro.rpc.legacy;

import com._specs_.azure.core.lro.rpc.legacy.models.JobData;
import com._specs_.azure.core.lro.rpc.legacy.models.JobResult;
import com._specs_.azure.core.lro.rpc.legacy.models.JobStatus;
import com.azure.core.util.BinaryData;
import com.azure.core.util.polling.LongRunningOperationStatus;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LegacyRpcTests {

    private final LegacyClient client = new LegacyClientBuilder()
            .buildClient();

    @Test
    public void testRpc() {
        JobData jobData = new JobData("async job");
        SyncPoller<JobResult, Void> poller = client.beginCreateJob(jobData);

        PollResponse<JobResult> response = poller.waitForCompletion();

        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, response.getStatus());
        Assertions.assertEquals(JobStatus.SUCCEEDED, response.getValue().getStatus());
        Assertions.assertNotNull(response.getValue().getResults());

        Void finalResult = poller.getFinalResult();
//        Assertions.assertEquals(JobStatus.SUCCEEDED, finalResult.getStatus());
//        Assertions.assertNotNull(finalResult.getResults());

        // emitter need to have protocol API as SyncPoller<BinaryData, BinaryData>, to keep backward-compatible
        SyncPoller<BinaryData, BinaryData> dpgPoller = client.beginCreateJob(BinaryData.fromObject(jobData), null);
        BinaryData dpgFinalResult = dpgPoller.getFinalResult();
        JobResult dpgFinalResultAsModel = dpgFinalResult.toObject(JobResult.class);
        Assertions.assertEquals(JobStatus.SUCCEEDED, dpgFinalResultAsModel.getStatus());
        Assertions.assertNotNull(dpgFinalResultAsModel.getResults());
    }
}
