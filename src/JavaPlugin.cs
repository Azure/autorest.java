// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.
// 

using AutoRest.Core;
using AutoRest.Core.Extensibility;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Model;
using static AutoRest.Core.Utilities.DependencyInjection;

namespace AutoRest.Java
{
    public sealed class JavaPlugin : Plugin<IGeneratorSettings, TransformerJv, CodeGeneratorJv, CodeNamerJv, CodeModelJv>
    {
        public JavaPlugin()
        {
            Context = new Context
            {
                // inherit base settings
                Context,

                // set code model implementations our own implementations 
                new Factory<CodeModel, CodeModelJv>(),
                new Factory<Method, MethodJv>(),
                new Factory<CompositeType, CompositeTypeJv>(),
                new Factory<DictionaryType, DictionaryTypeJv>(),
                new Factory<SequenceType, SequenceTypeJv>(),
                new Factory<MethodGroup, MethodGroupJv>(),
                new Factory<AutoRest.Core.Model.EnumType, EnumTypeJv>(),
                new Factory<PrimaryType, PrimaryTypeJv>(),
                new Factory<Property, PropertyJv>();
            };
        }
    }
}