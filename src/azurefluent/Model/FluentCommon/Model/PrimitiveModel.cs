// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Java.Model;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type representing primitive return type of a fluent method.
    /// </summary>
    public class PrimitiveModel : IModel
    {
        // TODO: anuchan: expand type to have details of primtive type it represents.
        public PrimitiveModel(PrimaryTypeJv rawModel)
        {
            if (rawModel.KnownPrimaryType == Core.Model.KnownPrimaryType.None)
            {
                throw new ArgumentException($"Cannot intialize primitive model with Void type \"{rawModel.ClassName}\"");
            }
            this.RawModel = rawModel;
        }

        public PrimaryTypeJv RawModel { get; }

        public string RawModelName
        {
            get
            {
                return this.RawModel.ClassName;
            }
        }

        public HashSet<string> ImportsForInterface
        {
            get
            {
                return this.RawModel.Imports.ToHashSet();
            }
        }

        public HashSet<string> ImportsForImpl
        {
            get
            {
                return this.RawModel.Imports.ToHashSet();
            }
        }
    }
}
