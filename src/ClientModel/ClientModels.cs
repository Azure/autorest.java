// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections;
using System.Collections.Generic;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// The collection of all client models stored for inheritance lookup.
    /// </summary>
    public class ClientModels : IEnumerable<ClientModel>
    {
        public static readonly ClientModels Instance = new ClientModels();

        private ClientModels() { }

        private readonly IDictionary<string, ClientModel> nameMap = new Dictionary<string, ClientModel>();
        private readonly IDictionary<string, List<ClientModel>> derivedTypesMap = new Dictionary<string, List<ClientModel>>();

        public ClientModel GetModel(string modelName)
        {
            return nameMap.ContainsKey(modelName) ? nameMap[modelName] : null;
        }

        public void AddModel(ClientModel model)
        {
            nameMap[model.Name] = model;

            ClientModel parentModel = model.ParentModel;
            if (parentModel != null)
            {
                List<ClientModel> derivedTypesList = GetDerivedTypeList(parentModel.Name);
                derivedTypesList.Add(model);
            }
        }

        public IEnumerable<ClientModel> GetDerivedTypes(string parentModelName)
            => GetDerivedTypeList(parentModelName);

        private List<ClientModel> GetDerivedTypeList(string parentModelName)
        {
            if (!derivedTypesMap.ContainsKey(parentModelName))
            {
                derivedTypesMap.Add(parentModelName, new List<ClientModel>());
            }
            return derivedTypesMap[parentModelName];
        }

        public IEnumerator<ClientModel> GetEnumerator()
        {
            return nameMap.Values.GetEnumerator();
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }
    }
}
