package com.azure.autorest.model.clientmodel;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/**
 * A page class that contains results that are received from a service request.
 */
public class MethodPageDetails {
    /**
     * Get whether or not this method is a request to get the next page of a sequence of pages.
     */
    private boolean isNextMethod;
    private IType pageType;
    private GenericType pageImplType;
    private String nextLinkVariableName;
    private String nextLinkParameterName;
    private ClientMethod nextMethod;
    private String nextMethodGroupName;
    private ClientMethodParameter nextGroupParameter;
    private String nextGroupParameterTypeName;
    private String nextMethodInvocation;
    private String nextMethodParameterInvocation;

    public MethodPageDetails(boolean isNextMethod, IType pageType, GenericType pageImplType, String nextLinkVariableName, String nextLinkParameterName, ClientMethod nextMethod, String nextMethodGroupName, ClientMethodParameter nextGroupParameter, String nextGroupParameterTypeName, String nextMethodInvocation, java.util.function.Function<MethodPageDetails, String> nextMethodParameterInvocation) {
        this.isNextMethod = isNextMethod;
        this.pageType = pageType;
        this.pageImplType = pageImplType;
        this.nextLinkVariableName = nextLinkVariableName;
        this.nextLinkParameterName = nextLinkParameterName;
        this.nextMethod = nextMethod;
        this.nextMethodGroupName = nextMethodGroupName;
        this.nextGroupParameter = nextGroupParameter;
        this.nextGroupParameterTypeName = nextGroupParameterTypeName;
        this.nextMethodInvocation = nextMethodInvocation;
        this.nextMethodParameterInvocation = nextMethodParameterInvocation.apply(this);
    }

    public final boolean getIsNextMethod() {
        return isNextMethod;
    }

    public final IType getPageType() {
        return pageType;
    }

    public final GenericType getPageImplType() {
        return pageImplType;
    }

    public final String getNextLinkVariableName() {
        return nextLinkVariableName;
    }

    public final String getNextLinkParameterName() {
        return nextLinkParameterName;
    }

    public final ClientMethod getNextMethod() {
        return nextMethod;
    }

    public final String nextMethodGroupName() {
        return nextMethodGroupName;
    }

    public final ClientMethodParameter getNextGroupParameter() {
        return nextGroupParameter;
    }

    public final String getNextGroupParameterTypeName() {
        return nextGroupParameterTypeName;
    }

    public final String getNextMethodInvocation() {
        return nextMethodInvocation;
    }

    public final String getNextMethodParameterInvocation() {
        return nextMethodParameterInvocation;
    }
}