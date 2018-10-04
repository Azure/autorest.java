// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Describes a Standard Fluent Model type (of a "Segment Fluent Method Group") that wraps a Standard Composite Inner Model.
    /// 
    /// e.g: The standard model for the "Segment Fluent Method Group" 'StorageAccounts' is 'StorageAccount'.
    /// Refer SegmentFluentMethodGroups::DeriveStandardInnerModelForMethodGroups() method to see how Standard Composite Inner Model for
    /// a "Segment Fluent Method Group" is derived.
    /// </summary>
    public class StandardModel : WrappableFluentModel
    {
        /// <summary>
        /// Creates a StandardModel.
        /// </summary>
        /// <param name="group">The "Segment Fluent Method Group" for which this type describes it's Standard Fluent Model</param>
        /// <param name="standardInnerModel">The inner model</param>
        public StandardModel(SegmentFluentMethodGroup group, CompositeTypeJvaf standardInnerModel) : base(standardInnerModel)
        {
            switch(group.Type)
            {
                case MethodGroupType.GroupableTopLevel:
                    this.Type = StanardModelType.GroupableTopLevel;
                    break;
                case MethodGroupType.NonGroupableTopLevel:
                    this.Type = StanardModelType.NonGroupableTopLevel;
                    break;
                case MethodGroupType.Nested:
                    this.Type = StanardModelType.Nested;
                    break;
                default:
                    throw new ArgumentException($"group with type '{group.Type}' is not eligible to have a standard model.");
            }
            this.FluentMethodGroup = group;
        }

        /// <summary>
        /// The category of this Standard Fluent Model.
        /// </summary>
        public StanardModelType Type { get; }

        /// <summary>
        /// The "Segment Fluent Method Group" for which this type describes it's Standard Fluent Model.
        /// </summary>
        public SegmentFluentMethodGroup FluentMethodGroup
        {
            get;
        }

        /// <summary>
        /// String indicating how to new-up an instance of Java class corrosponds to the Standard Fluent Model that wraps
        /// an existing inner model.
        /// </summary>
        public override string CtrInvocationForWrappingExistingInnerModel
        {
            get
            {
                switch (this.Type)
                {
                    case StanardModelType.GroupableTopLevel:
                        return $" new {this.JavaClassName}(inner.name(), inner, manager());";
                    case StanardModelType.NonGroupableTopLevel:
                        return $" new {this.JavaClassName}(inner, manager());";
                    case StanardModelType.Nested:
                        return $" new {this.JavaClassName}(inner, manager());";
                    default:
                        throw new NotSupportedException($"Unsupported model type '{this.Type}'");
                }
            }
        }

        /// <summary>
        /// String indicating how to new-up an instance of Java class corrosponds to the Standard Fluent Model that wraps
        /// an new inner model.
        /// </summary>
        public string CtrInvocationForWrappingNewInnerModel
        {
            get
            {
                if (this.FluentMethodGroup.ResourceCreateDescription.SupportsCreating)
                {
                    switch (this.Type)
                    {
                        case StanardModelType.GroupableTopLevel:
                            if (this.FluentMethodGroup.ResourceCreateDescription.CreateType == CreateType.WithResourceGroupAsParent)
                            {
                                return $"new {this.JavaClassName}(name, new {this.RawModelName}(), this.manager());";
                            }
                            else
                            {
                                return string.Empty;
                            }
                        case StanardModelType.NonGroupableTopLevel:
                            return $"new {this.JavaClassName}(name, this.manager());";
                        case StanardModelType.Nested:
                            if (this.FluentMethodGroup.ResourceCreateDescription.CreateType == CreateType.AsNestedChild)
                            {
                                return $"new {this.JavaClassName}(name, this.manager());";
                            }
                            else
                            {
                                return string.Empty;
                            }
                        default:
                            throw new NotSupportedException($"Unsupported model type '{this.Type}'");
                    }
                }
                else
                {
                    return string.Empty;
                }
            }
        }
    }
}
