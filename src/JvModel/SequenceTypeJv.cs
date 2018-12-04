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

        private IType _itype;
        public IType Generate(JavaSettings settings)
        {
            if (_itype == null) {
                _itype = new ListType(((IModelTypeJv)ElementType).Generate(settings));
            }
            return _itype;
        }
    }
}