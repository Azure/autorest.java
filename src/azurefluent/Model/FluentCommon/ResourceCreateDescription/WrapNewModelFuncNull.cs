// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// NullObject implementation of IWrapNewModelFunc.
    /// </summary>
    public class WrapNewModelFuncNull : IWrapNewModelFunc
    {
        public static WrapNewModelFuncNull Instance { get; } = new WrapNewModelFuncNull();

        private WrapNewModelFuncNull() { }

        #region NullObject implementation of IWrapNewModelFunc contract.

        public bool IsWrapNewModelSupported => false;

        public string MethodName => string.Empty;

        public string MethodImpl(bool applyOverride) => string.Empty;

        public string GeneralizedMethodName => string.Empty;

        public string GeneralizedMethodImpl => string.Empty;

        #endregion
    }
}
