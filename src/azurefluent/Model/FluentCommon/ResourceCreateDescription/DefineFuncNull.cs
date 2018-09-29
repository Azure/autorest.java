// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// NullObject implementation of 'IDefineFunc'.
    /// </summary>
    public class DefineFuncNull : IDefineFunc
    {
        public static DefineFuncNull Instance { get; } = new DefineFuncNull();

        private DefineFuncNull() {}

        #region NullObject Implementation of IDefineFunc contract.

        public bool IsDefineSupported => false;

        public string MethodName => string.Empty;

        public string MethodDecl => string.Empty;

        public string MethodImpl => string.Empty;

        public string GeneralizedMethodDecl => string.Empty;

        public string GeneralizedMethodImpl => string.Empty;

        public string GeneralizedMethodName => string.Empty;

        #endregion
    }
}
