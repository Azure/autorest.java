// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com._specs_.azure.core.lro.rpc.legacy.models;

import com.azure.core.annotation.Generated;
import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/** The status of the processing job. */
public final class JobStatus extends ExpandableStringEnum<JobStatus> {
    /** The operation is not started. */
    @Generated public static final JobStatus NOT_STARTED = fromString("notStarted");

    /** The operation is in progress. */
    @Generated public static final JobStatus RUNNING = fromString("running");

    /** The operation has completed successfully. */
    @Generated public static final JobStatus SUCCEEDED = fromString("succeeded");

    /** The operation has failed. */
    @Generated public static final JobStatus FAILED = fromString("failed");

    /** The operation has been canceled by the user. */
    @Generated public static final JobStatus CANCELED = fromString("canceled");

    /** The operation has partially completed. */
    @Generated public static final JobStatus PARTIALLY_COMPLETED = fromString("partiallyCompleted");

    /**
     * Creates a new instance of JobStatus value.
     *
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public JobStatus() {}

    /**
     * Creates or finds a JobStatus from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding JobStatus.
     */
    @Generated
    public static JobStatus fromString(String name) {
        return fromString(name, JobStatus.class);
    }

    /**
     * Gets known JobStatus values.
     *
     * @return known JobStatus values.
     */
    @Generated
    public static Collection<JobStatus> values() {
        return values(JobStatus.class);
    }
}
