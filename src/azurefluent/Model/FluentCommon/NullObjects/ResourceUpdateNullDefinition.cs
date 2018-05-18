// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class ResourceUpdateNullDefinition : IResourceUpdateDescription
    {
        public static ResourceUpdateNullDefinition Instance { get; } = new ResourceUpdateNullDefinition();

        private ResourceUpdateNullDefinition() { }

        public HashSet<string> ImportsForModelInterface => Utils.EmptyStringSet;

        public bool SupportsUpdating => false;

        public FluentMethod UpdateMethod => null;

        public UpdateType UpdateType => UpdateType.None;

        private static HashSet<string> emptySet = new HashSet<string>();
    }
}
