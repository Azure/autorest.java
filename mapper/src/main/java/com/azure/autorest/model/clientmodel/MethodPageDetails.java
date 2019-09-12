package com.azure.autorest.model.clientmodel;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/** 
 A page class that contains results that are received from a service request.
*/
public class MethodPageDetails
{
    public MethodPageDetails(boolean isNextMethod, IType pageType, GenericType pageImplType, String nextLinkVariableName, String nextLinkParameterName, ClientMethod nextMethod, ClientMethodParameter nextGroupParameter, String nextGroupParameterTypeName, String nextMethodInvocation, java.util.function.Function<MethodPageDetails, String> nextMethodParameterInvocation)
    {
        IsNextMethod = isNextMethod;
        PageType = pageType;
        PageImplType = pageImplType;
        NextLinkVariableName = nextLinkVariableName;
        NextLinkParameterName = nextLinkParameterName;
        NextMethod = nextMethod;
        NextGroupParameter = nextGroupParameter;
        NextGroupParameterTypeName = nextGroupParameterTypeName;
        NextMethodInvocation = nextMethodInvocation;
        NextMethodParameterInvocation = nextMethodParameterInvocation.apply(this);
    }

    /**
     Get whether or not this method is a request to get the next page of a sequence of pages.
    */
    private boolean IsNextMethod;
    public final boolean getIsNextMethod()
    {
        return IsNextMethod;
    }

    private IType PageType;
    public final IType getPageType()
    {
        return PageType;
    }

    private GenericType PageImplType;
    public final GenericType getPageImplType()
    {
        return PageImplType;
    }

    private String NextLinkVariableName;
    public final String getNextLinkVariableName()
    {
        return NextLinkVariableName;
    }

    private String NextLinkParameterName;
    public final String getNextLinkParameterName()
    {
        return NextLinkParameterName;
    }

    private ClientMethod NextMethod;
    public final ClientMethod getNextMethod()
    {
        return NextMethod;
    }

    private ClientMethodParameter NextGroupParameter;
    public final ClientMethodParameter getNextGroupParameter()
    {
        return NextGroupParameter;
    }

    private String NextGroupParameterTypeName;
    public final String getNextGroupParameterTypeName()
    {
        return NextGroupParameterTypeName;
    }

    private String NextMethodInvocation;
    public final String getNextMethodInvocation()
    {
        return NextMethodInvocation;
    }

    private String NextMethodParameterInvocation;
    public final String getNextMethodParameterInvocation()
    {
        return NextMethodParameterInvocation;
    }
}