// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;

namespace AutoRest.Java
{
    public class JavaCodeNamer : CodeNamer
    {
        public override string GetMethodName(string name)
            => CamelCase(name);

        public override string GetMethodGroupName(string name)
            => name;

        public override string GetEnumMemberName(string name)
            => name.Replace(',', '_').Replace('-', '_');
    }
}