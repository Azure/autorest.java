package com.azure.autorest.model.clientmodel;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/** 
 A transformation class that contains mappings from input parameters to proxy method parameters.
*/
public class MethodTransformationDetail
{
    public MethodTransformationDetail(IType outParameterType, boolean outParameterIsRequired, String outParameterName/*, ArrayList<ParameterMapping> parameterMappings*/)
    {
        OutParameterType = outParameterType;
        OutParameterIsRequired = outParameterIsRequired;
        OutParameterName = outParameterName;
//        ParameterMappings = parameterMappings;
    }

    private IType OutParameterType;
    public final IType getOutParameterType()
    {
        return OutParameterType;
    }

    private boolean OutParameterIsRequired;
    public final boolean getOutParameterIsRequired()
    {
        return OutParameterIsRequired;
    }

    private String OutParameterName;
    public final String getOutParameterName()
    {
        return OutParameterName;
    }

//    private ArrayList<ParameterMapping> ParameterMappings;
//    public final ArrayList<ParameterMapping> getParameterMappings()
//    {
//        return ParameterMappings;
//    }
}