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
    public class DictionaryTypeJv : DictionaryType, IModelTypeJv
    {
        public string ModelTypeName => $"Map<String, {((IModelTypeJv) this.ValueType).ModelTypeName}>";

        public IModelTypeJv ConvertToClientType()
        {
            var result = this;
            IModelTypeJv dictionaryValueClientType = ((IModelTypeJv) ValueType).ConvertToClientType();

            if (dictionaryValueClientType != ValueType)
            {
                bool dictionaryValueClientPrimaryTypeIsNullable = true;
                if (dictionaryValueClientType is PrimaryTypeJv dictionaryValueClientPrimaryType && !dictionaryValueClientPrimaryType.IsNullable)
                {
                    switch (dictionaryValueClientPrimaryType.KnownPrimaryType)
                    {
                        case KnownPrimaryType.None:
                        case KnownPrimaryType.Boolean:
                        case KnownPrimaryType.Double:
                        case KnownPrimaryType.Int:
                        case KnownPrimaryType.Long:
                        case KnownPrimaryType.UnixTime:
                            dictionaryValueClientPrimaryTypeIsNullable = false;
                            break;
                    }
                }

                if (dictionaryValueClientPrimaryTypeIsNullable)
                {
                    DictionaryTypeJv dictionaryTypeResult = DependencyInjection.New<DictionaryTypeJv>();
                    dictionaryTypeResult.ValueType = dictionaryValueClientType;
                    result = dictionaryTypeResult;
                }
            }

            return result;
        }
    }
}