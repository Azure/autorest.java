// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Model;
using AutoRest.Java.Azure.Fluent.Model;
using AutoRest.Java.Azure.Model;
using AutoRest.Java.Model;
using System;
using System.Collections.Generic;

namespace AutoRest.Java.azurefluent.Model
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
    }
}
