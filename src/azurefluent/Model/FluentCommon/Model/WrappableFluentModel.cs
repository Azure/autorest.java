// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Utilities;
using System;
using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Describes a Fluent Model type that wraps a Composite Inner Model.
    /// 
    /// Wrapping a Composite Inner Model requires it name to have 'Inner' suffix [e.g. VirtualMachineInner].
    /// The Fluent Model type describes return type of "Fluent Method".
    ///     e.g. VirtualMachine vm = computeManager.virtualMachines().getByResourceGroup(..);
    ///     'VirtualMachine' is the fluent model and 'getByResourceGroup' is a fluent method.
    /// The Java fluent model interface created from this model extends HasInner<innerT>.
    ///     e.g: "interface VirtualMachine extends HasInner<VirtualMachineInner> {..}"
    /// </summary>
    public class WrappableFluentModel : IModel
    {
        /// <summary>
        /// Creates WrappableFluentModel describing a Fluent Model that wraps the given Inner Model.
        /// </summary>
        /// <param name="innerModel">the inner model [that has 'Inner' suffix in it's name]</param>
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
        /// The Composite Inner Model that the Fluent Model (described by this WrappableFluentModel) wraps.
        /// </summary>
        public CompositeTypeJvaf RawModel { get; private set; }

        /// <summary>
        /// The name of the Java interface [e.g. VirtualMachine] for the Fluent Model.
        /// </summary>
        public string JavaInterfaceName { get; private set; }

        /// <summary>
        /// Sets name of the Java interface [e.g. VirtualMachine] for the Fluent Model.
        /// </summary>
        /// <param name="name">the name for the fluent model</param>
        internal void SetJavaInterfaceName(string name)
        {
            this.JavaInterfaceName = name;
        }

        /// <summary>
        /// Name of the Java class [e.g. VirtualMachineImpl] implementing the Java interface corrosponds to Fluent Model.
        /// </summary>
        public string JavaClassName
        {
            get
            {
                return $"{this.JavaInterfaceName}Impl";
            }
        }

        /// <summary>
        /// The name of the inner model Java class [e.g. VirtualMachineInner].
        /// </summary>
        public string RawModelName
        {
            get
            {
                return this.RawModel.ClassName;
            }
        }

        /// <summary>
        /// String indicating how to new-up an instance of Java class corrosponds to the Fluent Model.
        /// </summary>
        public virtual string CtrInvocationForWrappingExistingInnerModel
        {
            get
            {
                return $" new {this.JavaClassName}(inner, manager());";
            }
        }

        private WrapExistingModelFunc wrapExistingModelFunc;
        /// <summary>
        /// Returns description of "wrapModel(innerT inner)" method in "Fluent Method Group" collection.
        /// </summary>
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
        /// Gets equality comparer to compare two instances of WrappableFluentModel.
        /// </summary>
        /// <returns></returns>
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
