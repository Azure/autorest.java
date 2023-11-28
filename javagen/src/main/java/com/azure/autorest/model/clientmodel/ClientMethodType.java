// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

/**
 * The different types of ClientMethod overloads that can exist in a client.
 */
public enum ClientMethodType {
    PagingSync(0),
    PagingAsync(1),
    PagingAsyncSinglePage(2),

    SimulatedPagingSync(3),
    SimulatedPagingAsync(4),

    LongRunningSync(5),
    LongRunningAsync(6),
    LongRunningBeginSync(7),
    LongRunningBeginAsync(8),

    SimpleSync(9),
    SimpleAsync(10), // will not generate when sync-methods=none, will generate when sync-methods=essential,
    SimpleAsyncRestResponse(11),
    SimpleSyncRestResponse(12),

    Resumable(13),

    SendRequestSync(14),
    SendRequestAsync(15),
    PagingSyncSinglePage(16);

    private static final ClientMethodType[] MAPPINGS;

    static {
        MAPPINGS = new ClientMethodType[17];
        for (ClientMethodType methodType : ClientMethodType.values()) {
            MAPPINGS[methodType.intValue] = methodType;
        }
    }

    private final int intValue;

    ClientMethodType(int value) {
        intValue = value;
    }

    public static ClientMethodType forValue(int value) {
        return (value < 0 || value >= MAPPINGS.length) ? null : MAPPINGS[value];
    }

    public int getValue() {
        return intValue;
    }
}
