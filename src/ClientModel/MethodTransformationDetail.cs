// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using AutoRest.Core.Model;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A page class that contains results that are received from a service request.
    /// </summary>
    public class MethodTransformationDetail
    {
        public MethodTransformationDetail(IType outParameterType, bool outParameterIsRequired, string outParameterName, List<ParameterMapping> parameterMappings)
        {
            OutParameterType = outParameterType;
            OutParameterIsRequired = outParameterIsRequired;
            OutParameterName = outParameterName;
            ParameterMappings = parameterMappings;
        }

        public IType OutParameterType { get; }

        public bool OutParameterIsRequired { get; }

        public string OutParameterName { get; }

        public List<ParameterMapping> ParameterMappings { get; }
    }
}
