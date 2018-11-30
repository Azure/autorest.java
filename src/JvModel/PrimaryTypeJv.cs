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
    public class PrimaryTypeJv : PrimaryType, IModelTypeJv
    {
        public PrimaryTypeJv()
        {
            IsNullable = true;
        }

        public PrimaryTypeJv(KnownPrimaryType knownPrimaryType)
            : base(knownPrimaryType)
        {
            IsNullable = true;
        }

        public string ModelTypeName
        {
            get
            {
                string result = null;
                switch (this.KnownPrimaryType)
                {
                    case KnownPrimaryType.None:
                        result = IsNullable ? "Void" : "void";
                        break;
                    case KnownPrimaryType.Base64Url:
                        result = "Base64Url";
                        break;
                    case KnownPrimaryType.Boolean:
                        result = IsNullable ? "Boolean" : "boolean";
                        break;
                    case KnownPrimaryType.ByteArray:
                        result = "byte[]";
                        break;
                    case KnownPrimaryType.Date:
                        result = "LocalDate";
                        break;
                    case KnownPrimaryType.DateTime:
                        result = "OffsetDateTime";
                        break;
                    case KnownPrimaryType.DateTimeRfc1123:
                        result = "DateTimeRfc1123";
                        break;
                    case KnownPrimaryType.Double:
                        result = IsNullable ? "Double" : "double";
                        break;
                    case KnownPrimaryType.Decimal:
                        result = "BigDecimal";
                        break;
                    case KnownPrimaryType.Int:
                        result = IsNullable ? "Integer" : "int";
                        break;
                    case KnownPrimaryType.Long:
                        result = IsNullable ? "Long" : "long";
                        break;
                    case KnownPrimaryType.Stream:
                        result = "Flowable<ByteBuffer>";
                        break;
                    case KnownPrimaryType.String:
                        result = "String";
                        break;
                    case KnownPrimaryType.TimeSpan:
                        result = "Period";
                        break;
                    case KnownPrimaryType.UnixTime:
                        result = IsNullable ? "Long" : "long";
                        break;
                    case KnownPrimaryType.Uuid:
                        result = "UUID";
                        break;
                    case KnownPrimaryType.Object:
                        result = "Object";
                        break;
                    case KnownPrimaryType.Credentials:
                        result = ClassType.ServiceClientCredentials.Name;
                        break;

                    default:
                        throw new NotImplementedException($"Primary type {this.KnownPrimaryType} is not implemented in {this.GetType().Name}");
                }
                return result;
            }
        }

        public bool IsNullable { get; set; }
    }
}