// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    public interface IFluentModel
    {
        FluentMethodGroup FluentMethodGroup { get; }
        string JavaClassName { get; }
        string JavaInterfaceName { get; }
        string InnerModelName { get; }
        string CtrInvocationForWrappingExistingInnerModel { get; }
        string CtrInvocationForWrappingNewInnerModel { get; }
    }
}
