// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Azure.Model;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Defines various extension methods.
    /// </summary>
    public static class FluentExtensions
    {
        /// <summary>
        /// Add a value to dictionary with the given key if the key not exists already.
        /// </summary>
        /// <typeparam name="T">The key type</typeparam>
        /// <typeparam name="U">The vaule type</typeparam>
        /// <param name="dict">the dictionary</param>
        /// <param name="key">the key for the vaule to be added</param>
        /// <param name="value">the value to be addeded</param>
        /// <returns>true if value added, false if the key already exists</returns>
        public static bool AddIfNotExists<T, U>(this Dictionary<T, U> dict, T key, U value)
        {
            if (!dict.ContainsKey(key))
            {
                dict.Add(key, value);
                return true;
            }
            else
            {
                return false;
            }
        }

        public static bool AddIfNotExists<U>(this Dictionary<string, U> dict, string key, U value)
        {
            if (string.IsNullOrEmpty(key) || dict.ContainsKey(key))
            {
                return false;
            }
            else
            {
                dict.Add(key, value);
                return true;
            }
        }

        public static bool ContainsNonEmptyKey<U>(this Dictionary<string, U> dict, string key)
        {
            return !string.IsNullOrEmpty(key) && dict.ContainsKey(key);
        }

        /// <summary>
        /// Checks whether the return type of a given method can be wrapped in a Java interface impl and wrapper
        /// can expose it through HasInner interface.
        /// </summary>
        /// <param name="method">the method to check its return type eligibility to be wrappable</param>
        /// <returns></returns>
        public static bool HasWrappableReturnType(this Azure.Fluent.Model.MethodJvaf method)
        {
            if (method.ReturnTypeJva != null)
            {
                CompositeTypeJvaf returnModelType = null;
                IModelType mtype = method.ReturnTypeJva.BodyClientType;
                if (mtype is CompositeTypeJvaf)
                {
                    returnModelType = (CompositeTypeJvaf)mtype;
                }
                else if (mtype is SequenceTypeJva)
                {
                    mtype = ((SequenceTypeJva)mtype).ElementType;

                    if (mtype is CompositeTypeJvaf)
                    {
                        returnModelType = (CompositeTypeJvaf)mtype;
                    }
                }
                //
                if (returnModelType != null)
                {
                    string returnModelTypeName = returnModelType.Name.Value;
                    return returnModelTypeName.EndsWith("Inner", StringComparison.OrdinalIgnoreCase);
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }

        public static StandardMethodsInfo StandardMethodsInfo(this IFluentMethodGroup fluentMethodGroup)
        {
            StandardMethodsInfo standardMethods = new StandardMethodsInfo();
            //
            HashSet<string> knownMethodNames = new HashSet<string>();
            if (fluentMethodGroup.ResourceCreateDescription.SupportsCreating)
            {
                standardMethods.InnerMethodNames.Add(fluentMethodGroup.ResourceCreateDescription.CreateMethod.Name.ToLowerInvariant());
            }

            if (fluentMethodGroup.ResourceUpdateDescription.SupportsUpdating)
            {
                standardMethods.InnerMethodNames.Add(fluentMethodGroup.ResourceUpdateDescription.UpdateMethod.Name.ToLowerInvariant());
                //
                StandardFluentMethod updateMethod = fluentMethodGroup.ResourceUpdateDescription.UpdateMethod;
                if (updateMethod.InnerMethod.HttpMethod == HttpMethod.Put)
                {
                    // If PUT based update is supported then skip any PATCH based update method
                    // being treated as "Other methods".
                    //
                    var patchUpdateMethod = fluentMethodGroup.InnerMethods
                        .Where(m => m.HttpMethod == HttpMethod.Patch)
                        .Where(m => m.Url.EqualsIgnoreCase(updateMethod.InnerMethod.Url))
                        .FirstOrDefault();
                    if (patchUpdateMethod != null)
                    {
                        standardMethods.InnerMethodNames.Add(patchUpdateMethod.Name.ToLowerInvariant());
                    }
                }
            }

            if (fluentMethodGroup.ResourceListingDescription.SupportsListByImmediateParent)
            {
                standardMethods.InnerMethodNames.Add(fluentMethodGroup.ResourceListingDescription.ListByImmediateParentMethod.Name.ToLowerInvariant());
            }

            if (fluentMethodGroup.ResourceListingDescription.SupportsListByResourceGroup)
            {
                standardMethods.InnerMethodNames.Add(fluentMethodGroup.ResourceListingDescription.ListByResourceGroupMethod.Name.ToLowerInvariant());
                standardMethods.FluentMethodNames.Add("listByResourceGroup".ToLowerInvariant());
            }

            if (fluentMethodGroup.ResourceListingDescription.SupportsListBySubscription)
            {
                standardMethods.InnerMethodNames.Add(fluentMethodGroup.ResourceListingDescription.ListBySubscriptionMethod.Name.ToLowerInvariant());
                standardMethods.FluentMethodNames.Add("list".ToLowerInvariant());
            }

            if (fluentMethodGroup.ResourceGetDescription.SupportsGetByImmediateParent)
            {
                standardMethods.InnerMethodNames.Add(fluentMethodGroup.ResourceGetDescription.GetByImmediateParentMethod.Name.ToLowerInvariant());
            }

            if (fluentMethodGroup.ResourceGetDescription.SupportsGetByResourceGroup)
            {
                standardMethods.InnerMethodNames.Add(fluentMethodGroup.ResourceGetDescription.GetByResourceGroupMethod.Name.ToLowerInvariant());
                standardMethods.FluentMethodNames.Add("getByResourceGroup".ToLowerInvariant());
            }

            if (fluentMethodGroup.ResourceDeleteDescription.SupportsDeleteByImmediateParent)
            {
                standardMethods.InnerMethodNames.Add(fluentMethodGroup.ResourceDeleteDescription.DeleteByImmediateParentMethod.Name.ToLowerInvariant());
            }

            if (fluentMethodGroup.ResourceDeleteDescription.SupportsDeleteByResourceGroup)
            {
                standardMethods.InnerMethodNames.Add(fluentMethodGroup.ResourceDeleteDescription.DeleteByResourceGroupMethod.Name.ToLowerInvariant());
                standardMethods.FluentMethodNames.Add("deleteByResourceGroup".ToLowerInvariant());
                standardMethods.FluentMethodNames.Add("deleteByIds".ToLowerInvariant());
            }
            //
            return standardMethods;
        }
    }

    public class StandardMethodsInfo
    {
        public StandardMethodsInfo()
        {
            this.InnerMethodNames = new HashSet<string>();
            this.FluentMethodNames = new HashSet<string>();
        }

        public HashSet<string> InnerMethodNames { get; }
        public HashSet<string> FluentMethodNames { get; }


        public bool IsStandardInnerMethod(MethodJvaf method)
        {
            return InnerMethodNames.Contains(method.Name.ToLowerInvariant());
        }

        public bool IsConfictWithStandardFluentMethod(MethodJvaf method)
        {
            return FluentMethodNames.Contains(method.Name.ToLowerInvariant());
        }

        public static StandardMethodsInfo Empty { get; } = new StandardMethodsInfo();
    }
}
