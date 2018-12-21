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
    public class SequenceTypeJv : SequenceType, IModelTypeJv
    {
        public string ModelTypeName
        {
            get
            {
                var result = $"List<{((IModelTypeJv) this.ElementType).ModelTypeName}>";
                if (IsPaged)
                {
                    result = "Paged" + result;
                }
                return result;
            }
        }

        public bool IsPaged { get; internal set; }

        public string PageImplType { get; internal set; }

        private IModelTypeJv _clientType;
        public IModelTypeJv ClientType
        {
            get
            {
                if (_clientType != null)
                {
                    return _clientType;
                }

                _clientType = this;
                IModelTypeJv elementClientType = ((IModelTypeJv) ElementType).ClientType;

                if (elementClientType != ElementType)
                {
                    bool elementClientPrimaryTypeIsNullable = true;
                    if (elementClientType is PrimaryTypeJv elementClientPrimaryType && !elementClientPrimaryType.IsNullable)
                    {
                        switch (elementClientPrimaryType.KnownPrimaryType)
                        {
                            case KnownPrimaryType.None:
                            case KnownPrimaryType.Boolean:
                            case KnownPrimaryType.Double:
                            case KnownPrimaryType.Int:
                            case KnownPrimaryType.Long:
                            case KnownPrimaryType.UnixTime:
                                elementClientPrimaryTypeIsNullable = false;
                                break;
                        }
                    }

                    if (elementClientPrimaryTypeIsNullable)
                    {
                        SequenceTypeJv sequenceType = DependencyInjection.New<SequenceTypeJv>();
                        sequenceType.ElementType = elementClientType;
                        _clientType = sequenceType;
                    }
                }
                return _clientType;
            }
        }
    }
}