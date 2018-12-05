// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Text;
using AutoRest.Core;
using AutoRest.Core.Utilities;
using AutoRest.Extensions;
using AutoRest.Core.Model;
using Newtonsoft.Json;
using AutoRest.Core.Utilities.Collections;

namespace AutoRest.Java.Model
{
    public interface IModelTypeJv : IModelType
    {
        // The name of the type that should be in the generated code
        string ModelTypeName { get; }

        // Some types should be converted before being returned to the user, e.g. DateTimeRfc1134 -> DateTime
        IModelTypeJv ConvertToClientType();

        // Generates the Java type for writing the Java source code
        IType GenerateType(JavaSettings settings);
    }
}