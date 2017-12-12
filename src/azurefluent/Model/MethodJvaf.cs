// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Core.Utilities.Collections;
using AutoRest.Java.Azure.Model;
using AutoRest.Java.DanModel;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Collections.Immutable;
using System.Linq;
using System.Text.RegularExpressions;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class MethodJvaf : MethodJva
    {
        public override void Disambiguate()
        {
            if (string.IsNullOrWhiteSpace(this.MethodGroup.Name))
            {
                base.Disambiguate();

                return;
            }

            var methodType = GetMethodType(this);
            var originalName = Name;

            if (methodType != MethodType.Other)
            {
                string newName = null;

                if (this.MethodGroup.Methods.Count(x => GetMethodType(x as MethodJvaf) == methodType) == 1)
                {
                    switch (methodType)
                    {
                        case MethodType.ListBySubscription:
                            newName = WellKnowMethodNames.List;
                            break;

                        case MethodType.ListByResourceGroup:
                            newName = WellKnowMethodNames.ListByResourceGroup;
                            break;

                        case MethodType.Delete:
                            newName = WellKnowMethodNames.Delete;
                            break;

                        case MethodType.Get:
                            newName = WellKnowMethodNames.GetByResourceGroup;
                            break;

                        default:
                            throw new Exception("Flow should not hit this statement.");
                    }
                }
                if (!string.IsNullOrWhiteSpace(newName))
                {
                    if (methodType == MethodType.ListByResourceGroup || methodType == MethodType.ListBySubscription)
                    {
                        this.SimulateAsPagingOperation = true;
                    }

                    var name = CodeNamer.Instance.GetUnique(newName, this, Parent.IdentifiersInScope, Parent.Children.Except(this.SingleItemAsEnumerable()));
                    if (name != originalName)
                    {
                        Name = name;
                    }
                    return;
                }
            }

            base.Disambiguate();
        }

        [JsonIgnore]
        public override List<string> InterfaceImports
        {
            get
            {
                var imports = base.InterfaceImports;
                if (this.IsPagingOperation || this.IsPagingNextOperation || this.SimulateAsPagingOperation)
                {
                    imports.Add("com.microsoft.azure.v2.PagedList");

                    if (this.IsPagingOperation || this.IsPagingNextOperation)
                    {
                        imports.Add("com.microsoft.azure.v2.ListOperationCallback");
                    }

                    if (!this.SimulateAsPagingOperation)
                    {
                        imports.Remove("com.microsoft.rest.v2.ServiceCallback");
                    }

                    if (DanCodeGenerator.ResponseBodyClientType(ReturnType) is SequenceType pageType)
                    {
                        imports.AddRange(DanCodeGenerator.CompositeTypeImportsFluent(DanCodeGenerator.SequenceTypeGetPageImplType(pageType), null));
                    }
                }
                return imports;
            }
        }

        ImmutableArray<string> cachedImplImports = default(ImmutableArray<string>);
        [JsonIgnore]
        public override ImmutableArray<string> ImplImports
        {
            get
            {
                if (cachedImplImports.IsDefault)
                {
                    var imports = base.ImplImports.ToHashSet();
                    if (OperationExceptionTypeString != "CloudException" && OperationExceptionTypeString != "RestException")
                    {
                        imports.RemoveWhere(i => DanCodeGenerator.CompositeTypeImportsAzure(OperationExceptionTypeString, CodeModel).Contains(i));
                        imports.AddRange(DanCodeGenerator.CompositeTypeImportsFluent(OperationExceptionTypeString, CodeModel));
                    }
                    if (DanCodeGenerator.MethodIsLongRunningOperation(this))
                    {
                        imports.Remove("com.microsoft.azure.v2.AzureResponseBuilder");
                        this.Responses.Select(r => r.Value.Body).Concat(new IModelType[]{ DefaultResponse.Body })
                            .SelectMany(t => DanCodeGenerator.GetIModelTypeImports(t))
                            .Where(i => !this.Parameters.Any(p => DanCodeGenerator.GetIModelTypeImports(p.ModelType).Contains(i)))
                            .ForEach(i => imports.Remove(i));
                        // return type may have been removed as a side effect
                        imports.AddRange(DanCodeGenerator.ResponseImplImports(ReturnType));
                    }

                    SequenceType pageType = DanCodeGenerator.ResponseBodyClientType(ReturnType) as SequenceType;
                    if (this.IsPagingOperation || this.IsPagingNextOperation || SimulateAsPagingOperation)
                    {
                        imports.Add("com.microsoft.azure.v2.PagedList");

                        if (this.IsPagingOperation || this.IsPagingNextOperation)
                        {
                            imports.Add("com.microsoft.azure.v2.ListOperationCallback");
                        }

                        if (!this.SimulateAsPagingOperation)
                        {
                            imports.Remove("com.microsoft.rest.v2.ServiceCallback");
                        }

                        imports.Remove("java.util.ArrayList");
                        imports.Add("com.microsoft.azure.v2.Page");
                        if (pageType != null)
                        {
                            imports.RemoveWhere(i => DanCodeGenerator.CompositeTypeImportsAzure(DanCodeGenerator.SequenceTypeGetPageImplType(DanCodeGenerator.ResponseBodyClientType(ReturnType)), CodeModel).Contains(i));
                        }
                    }

                    if (this.IsPagingNonPollingOperation && pageType != null)
                    {
                        imports.RemoveWhere(i => DanCodeGenerator.CompositeTypeImportsAzure(DanCodeGenerator.SequenceTypeGetPageImplType(DanCodeGenerator.ResponseBodyClientType(ReturnType)), CodeModel ).Contains(i));
                    }
                    cachedImplImports = imports.OrderBy(i => i).ToImmutableArray();
                }

                return cachedImplImports;
            }
        }

        private enum MethodType
        {
            Other,
            ListBySubscription,
            ListByResourceGroup,
            Get,
            Delete
        }

        private static MethodType GetMethodType(MethodJvaf method)
        {
            Regex leading = new Regex("^/+");
            Regex trailing = new Regex("/+$");
            var methodUrl = trailing.Replace(leading.Replace(method.Url, ""), "");
            if (method.HttpMethod == HttpMethod.Get)
            {
                var urlSplits = methodUrl.Split('/');
                if ((urlSplits.Count() == 5 || urlSplits.Count() == 7)
                    && StringComparer.OrdinalIgnoreCase.Equals(urlSplits[0], "subscriptions")
                    && HasSequenceType(method.ReturnType.Body))
                {
                    if (urlSplits.Count() == 5)
                    {
                        if (StringComparer.OrdinalIgnoreCase.Equals(urlSplits[2], "providers"))
                        {
                            return MethodType.ListBySubscription;
                        }
                        else
                        {
                            return MethodType.ListByResourceGroup;
                        }
                    }
                    else if (StringComparer.OrdinalIgnoreCase.Equals(urlSplits[2], "resourceGroups"))
                    {
                        return MethodType.ListByResourceGroup;
                    }
                }
                if (IsTopLevelResourceUrl(methodUrl))
                {
                    return MethodType.Get;
                }
            }
            else if (method.HttpMethod == HttpMethod.Delete)
            {
                if (method.Name.Value.ToLowerInvariant().StartsWith("begin")
                    || method.MethodGroup.Methods.Count(x => x.HttpMethod == HttpMethod.Delete) > 1)
                {
                    return MethodType.Other;
                }

                if (IsTopLevelResourceUrl(methodUrl))
                {
                    return MethodType.Delete;
                }
            }

            return MethodType.Other;
        }

        private static bool IsTopLevelResourceUrl(string url)
        {
            var urlSplits = url.Split('/');

            return urlSplits.Count() == 8 && StringComparer.OrdinalIgnoreCase.Equals(urlSplits[0], "subscriptions")
                                && StringComparer.OrdinalIgnoreCase.Equals(urlSplits[2], "resourceGroups")
                                && StringComparer.OrdinalIgnoreCase.Equals(urlSplits[4], "providers");
        }
    }
}