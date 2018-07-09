// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Model;
using AutoRest.Java.Azure.Model;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class MethodGroupJvaf : MethodGroupJva
    {
        private static readonly StringComparer stringComparer = StringComparer.OrdinalIgnoreCase;

        private List<string> supportedInterfaces = new List<string>();
        private List<string> interfacesToImport = new List<string>();

        public MethodGroupJvaf()
        {
        }
        public MethodGroupJvaf(string name) : base(name)
        {
        }

        [JsonIgnore]
        public override string MethodGroupDeclarationType => MethodGroupImplType;

        [JsonIgnore]
        public override string ImplClassSuffix => "Inner";

        [JsonIgnore]
        public override string ParentDeclaration
        {
            get
            {
                DiscoverAllSupportedInterfaces();

                if (supportedInterfaces.Count() > 0)
                {
                    return $" implements {string.Join(", ", supportedInterfaces)}";
                }

                return "";
            }
        }

        [JsonIgnore]
        public override string ServiceClientType => CodeModel.Name + "Impl";

        [JsonIgnore]
        public override string ImplPackage => "implementation";

        [JsonIgnore]
        public override IEnumerable<string> ImplImports
        {
            get
            {
                DiscoverAllSupportedInterfaces();
                var imports = new List<string>();
                var ns = CodeModel.Namespace.ToLowerInvariant();
                foreach (var interfaceToImport in interfacesToImport)
                {
                    imports.Add(interfaceToImport);
                }
                foreach (var i in base.ImplImports.ToList())
                {
                    if (i.StartsWith(ns + "." + ImplPackage, StringComparison.OrdinalIgnoreCase))
                    {
                        // Same package, do nothing
                    }
                    else if (i == ns + "." + this.TypeName)
                    {
                        // do nothing
                    }
                    else
                    {
                        imports.Add(i);
                    }
                }

                bool hasLroOptions = this.Methods.OfType<MethodJvaf>().Any(m => m.HasLroOptions);
                if (hasLroOptions)
                {
                    imports.Add("com.microsoft.azure.LongRunningFinalState");
                    imports.Add("com.microsoft.azure.LongRunningOperationOptions");
                }
                return imports;
            }
        }

        private bool IsMultiApi
        {
            get
            {
                return ((CodeModelJvaf)CodeModel).IsMultiApi;
            }
        }

        private void DiscoverAllSupportedInterfaces()
        {
            const string InnerSupportsGet = "InnerSupportsGet";
            const string InnerSupportsDelete = "InnerSupportsDelete";
            const string InnerSupportsListing = "InnerSupportsListing";

            // In case this method has already discovered the interfaces to be implemented, we don't need to do anything again.
            if (supportedInterfaces.Count() > 0)
            {
                return;
            }
            //
            string packageName = IsMultiApi ? "com.microsoft.azure.arm.collection" : "com.microsoft.azure.management.resources.fluentcore.collection";
            //
            Method getMethod = FindGetMethod(this.Methods);
            if (getMethod != null)
            {
                supportedInterfaces.Add($"{InnerSupportsGet}<{((ResponseJva)getMethod.ReturnType).GenericBodyClientTypeString}>");
                interfacesToImport.Add($"{packageName}.{InnerSupportsGet}");
            }

            Method deleteMethod = FindDeleteMethod(this.Methods);
            if (deleteMethod != null)
            {
                supportedInterfaces.Add($"{InnerSupportsDelete}<{((ResponseJva)deleteMethod.ReturnType).ClientCallbackTypeString}>");
                interfacesToImport.Add($"{packageName}.{InnerSupportsDelete}");
            }

            Method listMethod = FindListMethod(this.Methods);
            if (listMethod != null)
            {
                supportedInterfaces.Add($"{InnerSupportsListing}<{((ResponseJva)listMethod.ReturnType).SequenceElementTypeString}>");
                interfacesToImport.Add($"{packageName}.{InnerSupportsListing}");
            }
        }

        private static Method FindGetMethod(IEnumerable<Method> methods)
        {
            Method result = FindFirstMethodByName(methods, WellKnownMethodNames.GetByResourceGroup);
            return result != null && HasNonClientNonConstantRequiredParameters(result, 2) ? result : null;
        }

        private static Method FindDeleteMethod(IEnumerable<Method> methods)
        {
            Method deleteMethod = FindFirstMethodByName(methods, WellKnownMethodNames.Delete);
            return deleteMethod != null && HasNonClientNonConstantRequiredParameters(deleteMethod, 2) ? deleteMethod : null;
        }

        private static Method FindListMethod(IEnumerable<Method> methods)
        {
            Method result = null;

            Method list = FindFirstMethodByName(methods, WellKnownMethodNames.List);
            if (list != null && HasNonClientNonConstantRequiredParameters(list, 0))
            {
                Method listByResourceGroup = FindFirstMethodByName(methods, WellKnownMethodNames.ListByResourceGroup);
                if (listByResourceGroup != null && HasNonClientNonConstantRequiredParameters(listByResourceGroup, 1))
                {
                    string listReturnType = GetSequenceElementTypeString(list);
                    string listByResourceGroupReturnType = GetSequenceElementTypeString(listByResourceGroup);
                    if (stringComparer.Equals(listReturnType, listByResourceGroupReturnType))
                    {
                        result = list;
                    }
                }
            }

            return result;
        }

        private static String GetSequenceElementTypeString(Method method)
        {
            return ((ResponseJva)method.ReturnType).SequenceElementTypeString;
        }

        private static Method FindFirstMethodByName(IEnumerable<Method> methods, String methodName)
        {
            return methods.FirstOrDefault(method => stringComparer.Equals(method.Name, methodName));
        }

        private static bool HasNonClientNonConstantRequiredParameters(Method method, int requiredParameterCount)
        {
            // When parameters are optional we generate more methods.
            return method.Parameters.Count(x => !x.IsClientProperty && !x.IsConstant && x.IsRequired) == requiredParameterCount;
        }
    }
}