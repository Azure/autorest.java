// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type representing composite retrun type of a fluent method which cannot be wrapped
    /// using 'WrappableFluentModel due to not having 'Inner' suffix.
    /// </summary>
    public class NonWrappableModel : IModel
    {
        private readonly string package = Settings.Instance.Namespace.ToLower();

        public NonWrappableModel(CompositeTypeJvaf rawModel)
        {
            var innerModelName = rawModel.Name.Value;
            if (innerModelName.EndsWith("Inner", StringComparison.OrdinalIgnoreCase))
            {
                throw new ArgumentException($"NonWrappableModel requires an inner model without 'Inner' suffix but received inner model '{innerModelName}'.");
            }
            this.RawModel = rawModel;
        }

        public CompositeTypeJvaf RawModel { get; }

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
                HashSet<string> imports = new HashSet<string>();
                if (this.RawModel.Imports.Any(import => !import.StartsWith(package) && import.EndsWith($".{this.RawModelName}")))
                {
                    imports.Add(this.RawModel.Imports.First(import => !import.StartsWith(package) && import.EndsWith($".{this.RawModelName}")));
                }
                return imports;
            }
        }

        public HashSet<string> ImportsForImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.RawModel.Imports.Any(import => !import.StartsWith(package) && import.EndsWith($".{this.RawModelName}")))
                {
                    imports.Add(this.RawModel.Imports.First(import => !import.StartsWith(package) && import.EndsWith($".{this.RawModelName}")));
                }
                else
                {
                    imports.Add($"{this.package}.{this.RawModelName}");
                }
                return imports;
            }
        }
    }
}
