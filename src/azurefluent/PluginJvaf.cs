// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.
// 

using AutoRest.Core.Extensibility;
using AutoRest.Core.Model;
using AutoRest.Java.Azure.Fluent;

namespace AutoRest.Java.Azure
{
    public sealed class PluginJvaf : Plugin<IGeneratorSettings, TransformerJvaf, CodeGeneratorJvaf, CodeNamerJva, CodeModel>
    {
    }
}