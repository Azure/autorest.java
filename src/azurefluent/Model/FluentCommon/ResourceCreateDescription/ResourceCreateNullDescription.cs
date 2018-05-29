// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// A null object implementation of IResourceCreateDescription interface.
    /// </summary>
    public class ResourceCreateNullDescription : IResourceCreateDescription
    {
        public static ResourceCreateNullDescription Instance { get; } = new ResourceCreateNullDescription();

        private ResourceCreateNullDescription() { }

        public FluentMethod CreateMethod => null;

        public CreateType CreateType => CreateType.None;

        public IDefineFunc DefineFunc => DefineFuncNull.Instance;

        public HashSet<string> ImportsForMethodGroupImpl => Utils.EmptyStringSet;

        public HashSet<string> ImportsForMethodGroupInterface => Utils.EmptyStringSet;

        public HashSet<string> ImportsForModelInterface => Utils.EmptyStringSet;

        public HashSet<string> MethodGroupInterfaceExtendsFrom => Utils.EmptyStringSet;

        public bool SupportsCreating => false;

        public IWrapNewModelFunc WrapNewModelFunc => WrapNewModelFuncNull.Instance;

        public string WrapNewModelMethodImplementation(bool applyOverride) => string.Empty;
    }
}
