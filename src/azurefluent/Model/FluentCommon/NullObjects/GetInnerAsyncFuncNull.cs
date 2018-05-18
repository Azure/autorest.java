// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class GetInnerAsyncFuncNull : IGetInnerAsyncFunc
    {
        public static GetInnerAsyncFuncNull Instance { get; } = new GetInnerAsyncFuncNull();

        private GetInnerAsyncFuncNull() { }

        public string GeneralizedMethodImpl => string.Empty;

        public string GeneralizedMethodName => string.Empty;

        public bool IsGetInnerSupported => false;

        public string MethodName => string.Empty;

        public string MethodImpl(bool applyOverride) => string.Empty;
    }
}
