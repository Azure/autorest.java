// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections;
using System.Collections.Generic;

namespace AutoRest.Java.Model
{
    public class ServiceModels : IEnumerable<ServiceModel>
    {
        private readonly IDictionary<string, ServiceModel> nameMap = new Dictionary<string, ServiceModel>();
        private readonly IDictionary<string, List<ServiceModel>> derivedTypesMap = new Dictionary<string, List<ServiceModel>>();

        public ServiceModel GetModel(string modelName)
        {
            return nameMap.ContainsKey(modelName) ? nameMap[modelName] : null;
        }

        public void AddModel(ServiceModel model)
        {
            nameMap[model.Name] = model;

            ServiceModel parentModel = model.ParentModel;
            if (parentModel != null)
            {
                List<ServiceModel> derivedTypesList = GetDerivedTypeList(parentModel.Name);
                derivedTypesList.Add(model);
            }
        }

        public IEnumerable<ServiceModel> GetDerivedTypes(string parentModelName)
            => GetDerivedTypeList(parentModelName);

        private List<ServiceModel> GetDerivedTypeList(string parentModelName)
        {
            if (!derivedTypesMap.ContainsKey(parentModelName))
            {
                derivedTypesMap.Add(parentModelName, new List<ServiceModel>());
            }
            return derivedTypesMap[parentModelName];
        }

        public IEnumerator<ServiceModel> GetEnumerator()
        {
            return nameMap.Values.GetEnumerator();
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }
    }
}
