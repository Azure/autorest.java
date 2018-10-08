// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// A NullObject implementation of IGetInnerAsyncFuncFactory interface.
    /// </summary>
    class GetInnerAsyncFuncNullFactory : IGetInnerAsyncFuncFactory
    {
        public static GetInnerAsyncFuncNullFactory Instance { get; } = new GetInnerAsyncFuncNullFactory();

        public bool IsGetInnerSupported => false;

        public IGetInnerAsyncFunc GetFromResourceGroupAsyncFunc => GetInnerAsyncFuncNull.Instance;

        public IGetInnerAsyncFunc GetFromParentAsyncFunc => GetInnerAsyncFuncNull.Instance;
    }
}
