package com.azure.autorest.model.clientmodel;

import java.util.*;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/** 
 The collection of all client models stored for inheritance lookup.
*/
//C# TO JAVA CONVERTER TODO TASK: The interface type was changed to the closest equivalent Java type, but the methods implemented will need adjustment:
public class ClientModels
{
    public static final ClientModels Instance = new ClientModels();

    private ClientModels()
    {
    }

    private final Map<String, ClientModel> nameMap = new HashMap<String, ClientModel>();
    private final Map<String, ArrayList<ClientModel>> derivedTypesMap = new HashMap<String, ArrayList<ClientModel>>();

    public final ClientModel getModel(String modelName)
    {
        return nameMap.containsKey(modelName) ? nameMap.get(modelName) : null;
    }

    public final void addModel(ClientModel model)
    {
        nameMap.put(model.getName(), model);

        String parentModel = model.getParentModelName();
        if (parentModel != null)
        {
            ArrayList<ClientModel> derivedTypesList = getDerivedTypeList(parentModel);
            derivedTypesList.add(model);
        }
    }

    public final List<ClientModel> getDerivedTypes(String parentModelName)
    {
        return getDerivedTypeList(parentModelName);
    }

    public final List<ClientModel> getTypes() {
        return new ArrayList<>(nameMap.values());
    }

    private ArrayList<ClientModel> getDerivedTypeList(String parentModelName)
    {
        if (!derivedTypesMap.containsKey(parentModelName))
        {
            derivedTypesMap.put(parentModelName, new ArrayList<ClientModel>());
        }
        return derivedTypesMap.get(parentModelName);
    }
}