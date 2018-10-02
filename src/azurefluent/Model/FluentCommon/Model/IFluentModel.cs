// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    public interface IFluentModel
    {
        ISegmentFluentMethodGroup FluentMethodGroup { get; }
        ModelLocalProperties ModelLocalProperties { get; }
        string JavaClassName { get; }
        string JavaInterfaceName { get; }
        string InnerModelName { get; }
        WrapExistingModelFunc WrapExistingModelFunc { get; }
    }
}
