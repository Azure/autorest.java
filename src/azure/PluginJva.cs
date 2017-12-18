// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.
// 

using AutoRest.Core.Extensibility;
using AutoRest.Core.Model;

namespace AutoRest.Java.Azure
{
    public sealed class PluginJva : Plugin<IGeneratorSettings, TransformerJva, CodeGeneratorJva, CodeNamerJva, CodeModel>
    {
    }
}