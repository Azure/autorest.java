// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.
// 

using AutoRest.Core.Extensibility;
using AutoRest.Core.Model;

namespace AutoRest.Java
{
    public sealed class PluginJv : Plugin<IGeneratorSettings, TransformerJv, CodeGeneratorJv, CodeNamerJv, CodeModel>
    {
    }
}