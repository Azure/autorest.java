package com.azure.autorest.models.clientmodel;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

/** 
 The different types of ClientMethod overloads that can exist in a client.
*/
public enum ClientMethodType
{
	PagingSync(0),
	PagingAsync(1),
	PagingAsyncSinglePage(2),

	SimulatedPagingSync(3),
	SimulatedPagingAsync(4),

	LongRunningSync(5),
	LongRunningAsync(6),

	SimpleSync(7),
	SimpleAsync(8), // will not generate when sync-methods=none, will generate when sync-methods=essential
	SimpleAsyncRestResponse(9),

	Resumable(10);

	public static final int SIZE = java.lang.Integer.SIZE;

	private int intValue;
	private static java.util.HashMap<Integer, ClientMethodType> mappings;
	private static java.util.HashMap<Integer, ClientMethodType> getMappings()
	{
		if (mappings == null)
		{
			synchronized (ClientMethodType.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, ClientMethodType>();
				}
			}
		}
		return mappings;
	}

	private ClientMethodType(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static ClientMethodType forValue(int value)
	{
		return getMappings().get(value);
	}
}