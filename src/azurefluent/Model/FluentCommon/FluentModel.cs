// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Utilities;
using System;
using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class FluentModel
    {
        private string javaInterfaceName;

        public CompositeTypeJvaf InnerModel { get; private set; }

        public string JavaInterfaceName
        {
            get
            {
                return this.javaInterfaceName;
            }
        }

        internal void SetJavaInterfaceName(string name)
        {
            // Used to reset the default gen-ed name when there is a conflict
            //
            this.javaInterfaceName = name;
        }



        /// <summary>
        ///  This ctr is a hack to support PrimitiveFluentModel.
        ///  Will be removed later.
        /// </summary>
        public FluentModel()
        {
            this.javaInterfaceName = null;
            this.InnerModel = null;
        }

        public FluentModel(CompositeTypeJvaf innerModel)
        {
            var n = innerModel.Name.Value;
            if (!n.EndsWith("Inner", StringComparison.OrdinalIgnoreCase))
            {
                throw new ArgumentException($"Fluent inner model should have inner suffix {n}");
            }
            this.javaInterfaceName = n.Substring(0, n.Length - "Inner".Length);
            this.InnerModel = innerModel;
        }

        public static IEqualityComparer<FluentModel> EqualityComparer()
        {
            return new FMComparerBasedOnJvaInterfaceName();
        }
    }

    class FMComparerBasedOnJvaInterfaceName : IEqualityComparer<FluentModel>
    {
        public bool Equals(FluentModel x, FluentModel y)
        {
            return x.JavaInterfaceName.EqualsIgnoreCase(y.JavaInterfaceName);
        }

        public int GetHashCode(FluentModel obj)
        {
            return obj.JavaInterfaceName.GetHashCode();
        }
    }
}
