// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class WrapNewModelFuncNull : IWrapNewModelFunc
    {
        public static WrapNewModelFuncNull Instance { get; } = new WrapNewModelFuncNull();

        private WrapNewModelFuncNull() { }

        public string GeneralizedMethodImpl => string.Empty;

        public string GeneralizedMethodName => string.Empty;

        public bool IsWrapNewModelSupported => false;

        public string MethodName => string.Empty;

        public string MethodImpl(bool applyOverride) => string.Empty;
    }
}
