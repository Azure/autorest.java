// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com._specs_.azure.core.lro.rpc;

import com._specs_.azure.core.lro.rpc.models.JobData;
import com._specs_.azure.core.lro.rpc.models.JobDataJobResultJobPollResultLongRunningFinalLocationJobResult;
import com._specs_.azure.core.lro.rpc.models.JobPollResult;
import com._specs_.azure.core.lro.rpc.models.JobResult;
import com._specs_.azure.core.lro.rpc.models.OperationState;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.util.polling.LongRunningOperationStatus;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LroRpcTests {

    private final RpcClient client = new RpcClientBuilder()
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS))
            .buildClient();

    @Test
    public void testRpc() {
        SyncPoller<JobResult, JobResult> poller = client.beginCreateJob(new JobData("async job"));

        PollResponse<JobResult> response = poller.waitForCompletion();

        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, response.getStatus());
        Assertions.assertEquals(OperationState.SUCCEEDED, response.getValue().getStatus());
        Assertions.assertNotNull(response.getValue().getResults());

        JobResult finalResult = poller.getFinalResult();
        Assertions.assertEquals(OperationState.SUCCEEDED, finalResult.getStatus());
        Assertions.assertNotNull(finalResult.getResults());
    }

    @Test
    public void testRpcFinalOnLocation() {
        RpcClient client = new RpcClientBuilder()
                .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS))
                .buildClient();

        SyncPoller<JobPollResult, JobDataJobResultJobPollResultLongRunningFinalLocationJobResult> poller = client.beginCreateJobFinalOnLocation(new JobData("async job"));

        PollResponse<JobPollResult> response = poller.waitForCompletion();

        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, response.getStatus());
        Assertions.assertEquals(OperationState.SUCCEEDED, response.getValue().getStatus());

        JobDataJobResultJobPollResultLongRunningFinalLocationJobResult finalResult = poller.getFinalResult();
        Assertions.assertNotNull(finalResult);
    }
}
