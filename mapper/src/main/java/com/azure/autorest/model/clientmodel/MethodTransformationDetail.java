package com.azure.autorest.model.clientmodel;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


import java.util.List;

/**
 A transformation class that contains mappings from input parameters to proxy method parameters.
*/
public class MethodTransformationDetail
{
    public MethodTransformationDetail(ClientMethod outMethod, ClientMethodParameter outParameter, List<ParameterMapping> parameterMappings)
    {
        OutParameter = outParameter;
        ParameterMappings = parameterMappings;
    }

    private ClientMethodParameter OutParameter;
    public final ClientMethodParameter getOutParameter()
    {
        return OutParameter;
    }

    private List<ParameterMapping> ParameterMappings;
    public final List<ParameterMapping> getParameterMappings()
    {
        return ParameterMappings;
    }
}