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
using System.Text.RegularExpressions;
using AutoRest.Extensions.Azure;

namespace AutoRest.Java.Model
{
    public class MethodJv : Method
    {
        private static readonly Regex methodTypeLeading = new Regex("^/+");
        private static readonly Regex methodTypeTrailing = new Regex("/+$");

        public MethodType MethodType
        {
            get
            {
                MethodType methodType = MethodType.Other;
                string methodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(Url, ""), "");
                string[] methodUrlSplits = methodUrl.Split('/');
                switch (HttpMethod)
                {
                    case HttpMethod.Get:
                        if ((methodUrlSplits.Length == 5 || methodUrlSplits.Length == 7)
                            && methodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                            && ReturnType.Body.MethodHasSequenceType())
                        {
                            if (methodUrlSplits.Length == 5)
                            {
                                if (methodUrlSplits[2].EqualsIgnoreCase("providers"))
                                {
                                    methodType = MethodType.ListBySubscription;
                                }
                                else
                                {
                                    methodType = MethodType.ListByResourceGroup;
                                }
                            }
                            else if (methodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                            {
                                methodType = MethodType.ListByResourceGroup;
                            }
                        }
                        else if (methodUrlSplits.IsTopLevelResourceUrl())
                        {
                            methodType = MethodType.Get;
                        }
                        break;

                    case HttpMethod.Delete:
                        if (methodUrlSplits.IsTopLevelResourceUrl())
                        {
                            methodType = MethodType.Delete;
                        }
                        break;
                }
                return methodType;
            }
        }

        private string wellKnownMethodName;
        public string WellKnownMethodName
        {
            get
            {
                MethodGroup methodGroup = MethodGroup;
                if (!string.IsNullOrEmpty(methodGroup?.Name?.ToString()))
                {
                    if (wellKnownMethodName == null)
                    {
                        MethodType methodType = MethodType;
                        if (methodType != MethodType.Other)
                        {
                            int methodsWithSameType = methodGroup.Methods.Count(methodGroupMethod => ((MethodJv)methodGroupMethod).MethodType == methodType);

                            if (methodsWithSameType == 1)
                            {
                                switch (methodType)
                                {
                                    case MethodType.ListBySubscription:
                                        wellKnownMethodName = "List";
                                        break;

                                    case MethodType.ListByResourceGroup:
                                        wellKnownMethodName = "ListByResourceGroup";
                                        break;

                                    case MethodType.Delete:
                                        wellKnownMethodName = "Delete";
                                        break;

                                    case MethodType.Get:
                                        wellKnownMethodName = "GetByResourceGroup";
                                        break;

                                    default:
                                        throw new Exception("Flow should not hit this statement.");
                                }
                            }
                        }
                    }
                }
                return wellKnownMethodName;
            }
        }
    }
}