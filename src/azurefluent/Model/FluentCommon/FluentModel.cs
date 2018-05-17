// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Utilities;
using System;
using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class FluentModel
    {
        public CompositeTypeJvaf InnerModel { get; private set; }

        public string JavaInterfaceName { get; private set; }

        public string JavaClassName
        {
            get
            {
                return $"{this.JavaInterfaceName}Impl";
            }
        }

        public string InnerModelName
        {
            get
            {
                return this.InnerModel.ClassName;
            }
        }

        public virtual string CtrInvocationForWrappingExistingInnerModel
        {
            get
            {
                return $" new {this.JavaClassName}(inner, manager());";
            }
        }

        protected WrapExistingModelFunc wrapExistingModelFunc;
        public WrapExistingModelFunc WrapExistingModelFunc
        {
            get
            {
                if (this.wrapExistingModelFunc == null)
                {
                    this.wrapExistingModelFunc = new WrapExistingModelFunc(this);
                }
                return this.wrapExistingModelFunc;
            }
        }

        public FluentModel()
        {
            this.JavaInterfaceName = null;
            this.InnerModel = null;
        }

        /// <summary>
        /// Creates a fluent model for a given inner model and derive the name of the fluent
        /// model by trimming the 'Inner' suffix of inner model name.
        /// </summary>
        /// <param name="innerModel">the inner model</param>
        public FluentModel(CompositeTypeJvaf innerModel)
        {
            var innerModelName = innerModel.Name.Value;
            if (!innerModelName.EndsWith("Inner", StringComparison.OrdinalIgnoreCase))
            {
                throw new ArgumentException($"The inner model '{innerModelName}' does not have excepted 'Inner' suffix.");
            }
            this.JavaInterfaceName = Utils.TrimInnerSuffix(innerModelName);
            this.InnerModel = innerModel;
        }

        /// <summary>
        /// Creates a fluent model for the given inner model and use the provided name as the
        /// name of the fluent model
        /// </summary>
        /// <param name="name">name for fluent model</param>
        /// <param name="innerModel">the inner model</param>
        public FluentModel(string name, CompositeTypeJvaf innerModel)
        {
            this.JavaInterfaceName = name;
            this.InnerModel = innerModel;
        }

        /// <summary>
        /// Sets the fluent model name, this will be used when there is a conflict
        /// in the model names.
        /// </summary>
        /// <param name="name">the new name for the fluent model</param>
        internal void SetJavaInterfaceName(string name)
        {
            this.JavaInterfaceName = name;
        }

        public static IEqualityComparer<FluentModel> EqualityComparer()
        {
            return new FluentModelComparer();
        }

        private class FluentModelComparer : IEqualityComparer<FluentModel>
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
}
