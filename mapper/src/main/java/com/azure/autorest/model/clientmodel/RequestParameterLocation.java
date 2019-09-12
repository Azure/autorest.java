package com.azure.autorest.model.clientmodel;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

/** 
 The location of a parameter within a HTTP request.
*/
public enum RequestParameterLocation
{
    Body,

    FormData,

    Host,

    Path,

    Header,

    None,

    Query;

    public static final int SIZE = java.lang.Integer.SIZE;

    public int getValue()
    {
        return this.ordinal();
    }

    public static RequestParameterLocation forValue(int value)
    {
        return values()[value];
    }
}