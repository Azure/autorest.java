// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type representing standard model of a fluent method group.
    /// e.g. The standard model for the fluent method group 'StorageAccounts' is 'StorageAccount'.
    /// </summary>
    public class StandardModel : WrappableFluentModel
    {
        public StandardModel(FluentMethodGroup group, CompositeTypeJvaf standardInnerModel) : base(standardInnerModel)
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

        public StanardModelType Type { get; }

        public FluentMethodGroup FluentMethodGroup
        {
            get;
        }

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
                            //
                        case StanardModelType.NonGroupableTopLevel:
                            return $"new {this.JavaClassName}(name, this.manager());";
                            //
                        case StanardModelType.Nested:
                            if (this.FluentMethodGroup.ResourceCreateDescription.CreateType == CreateType.AsNestedChild)
                            {
                                return $"new {this.JavaClassName}(name, this.manager());";
                            }
                            else
                            {
                                return string.Empty;
                            }
                            //
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
