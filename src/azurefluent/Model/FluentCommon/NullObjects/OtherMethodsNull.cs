// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class OtherMethodsNull : IOtherMethods
    {
        public static OtherMethodsNull Instance { get; } = new OtherMethodsNull();

        private OtherMethodsNull() { }

        public HashSet<string> ImportsForImpl => Utils.EmptyStringSet;

        public HashSet<string> ImportsForInterface => Utils.EmptyStringSet;

        public IEnumerable<string> MethodDecls => Utils.EmptyStringList;

        public IEnumerable<string> MethodImpls => Utils.EmptyStringList;

        public IEnumerable<FluentModel> OtherFluentModels => Utils.EmptyModelList;
    }
}
