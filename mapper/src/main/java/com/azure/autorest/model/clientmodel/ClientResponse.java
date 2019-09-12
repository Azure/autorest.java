package com.azure.autorest.model.clientmodel;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

/** 
 The response that is returned by a ClientMethod.
*/
public final class ClientResponse
{
    private String Name;
    public String getName()
    {
        return Name;
    }
    private String Package;
    public String getPackage()
    {
        return Package;
    }
    private String Description;
    public String getDescription()
    {
        return Description;
    }
    private IType HeadersType;
    public IType getHeadersType()
    {
        return HeadersType;
    }
    private IType BodyType;
    public IType getBodyType()
    {
        return BodyType;
    }

    public ClientResponse(String name, String package_Keyword, String description, IType headersType, IType bodyType)
    {
        Name = name;
        Package = package_Keyword;
        Description = description;
        HeadersType = headersType;
        BodyType = bodyType;
    }
}