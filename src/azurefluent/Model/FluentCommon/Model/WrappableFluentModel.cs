// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Utilities;
using System;
using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type representing a fluent model (return type of a fluent method) which wraps a composite inner model.
    /// Wrapping an inner model requires it name to have 'Inner' suffix (VirtualMachineInner).
    /// </summary>
    public class WrappableFluentModel : IModel
    {
        /// <summary>
        /// Creates a fluent model for a given inner model.
        /// </summary>
        /// <param name="innerModel">the inner model (which has 'Inner' suffix in it's name)</param>
        public WrappableFluentModel(CompositeTypeJvaf innerModel)
        {
            var innerModelName = innerModel.Name.Value;
            if (!innerModelName.EndsWith("Inner", StringComparison.OrdinalIgnoreCase))
            {
                throw new ArgumentException($"WrappableFluentModel requires 'Inner' suffix for the inner model it wraps but received inner model '{innerModelName}' without suffix.");
            }
            this.JavaInterfaceName = Utils.TrimInnerSuffix(innerModelName);
            this.RawModel = innerModel;
        }

        /// <summary>
        /// The inner model that fluent model wraps.
        /// </summary>
        public CompositeTypeJvaf RawModel { get; private set; }

        /// <summary>
        /// The name for the fluent Java interface (e.g. VirtualMachine)
        /// </summary>
        public string JavaInterfaceName { get; private set; }

        /// <summary>
        /// Name of the Java class implementing fluent Java interface and wraps the inner the model (e.g. VirtualMachineImpl).
        /// </summary>
        public string JavaClassName
        {
            get
            {
                return $"{this.JavaInterfaceName}Impl";
            }
        }

        /// <summary>
        /// The class name of the inner model (e.g. VirtualMachineInner).
        /// </summary>
        public string RawModelName
        {
            get
            {
                return this.RawModel.ClassName;
            }
        }

        /// <summary>
        /// String indicate how to new up the fluent model.
        /// </summary>
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

        /// <summary>
        /// Sets name for the Java interface this model represents.
        /// </summary>
        /// <param name="name">the new name for the fluent model</param>
        internal void SetJavaInterfaceName(string name)
        {
            this.JavaInterfaceName = name;
        }

        public static IEqualityComparer<WrappableFluentModel> EqualityComparer()
        {
            return new WrappableFluentModelComparer();
        }

        private class WrappableFluentModelComparer : IEqualityComparer<WrappableFluentModel>
        {
            public bool Equals(WrappableFluentModel x, WrappableFluentModel y)
            {
                return x.JavaInterfaceName.EqualsIgnoreCase(y.JavaInterfaceName);
            }

            public int GetHashCode(WrappableFluentModel obj)
            {
                return obj.JavaInterfaceName.GetHashCode();
            }
        }
    }
}
