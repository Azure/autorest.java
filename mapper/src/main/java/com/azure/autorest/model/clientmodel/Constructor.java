package com.azure.autorest.model.clientmodel;

import java.util.List;
import java.util.Set;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/** 
 The constructor in a ServiceClient.
*/
public class Constructor
{
    public Constructor(List<ClientMethodParameter> parameters)
    {
        Parameters = parameters;
    }

    /**
     The parameters of this constructor.
    */
    private List<ClientMethodParameter> Parameters;
    public final List<ClientMethodParameter> getParameters()
    {
        return Parameters;
    }

    public final void AddImportsTo(Set<String> imports, boolean includeImplementationImports)
    {
        for (ClientMethodParameter parameter : getParameters())
        {
            parameter.AddImportsTo(imports, includeImplementationImports);
        }
    }
}