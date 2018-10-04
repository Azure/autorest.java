﻿// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class ModelLocalProperties
    {
        private readonly string package = Settings.Instance.Namespace.ToLower();

        private readonly bool wrapReturnInner;
        //
        private readonly IEnumerable<Property> innerProperties;
        private readonly SegmentFluentMethodGroups methodGroups;

        public ModelLocalProperties(IEnumerable<Property> innerProperties, SegmentFluentMethodGroups methodGroups, bool wrapReturnInner)
        {
            this.innerProperties = innerProperties;
            this.methodGroups = methodGroups;
            this.wrapReturnInner = wrapReturnInner;
        }

        public IEnumerable<string> Getters
        {
            get
            {
                StringBuilder getterBuilder = new StringBuilder();
                foreach (PropertyJv property in this.innerProperties)
                {
                    getterBuilder.Clear();
                    //
                    string fluentGetterName = $"{property.Name.ToCamelCase()}()";
                    if (fluentGetterName.EqualsIgnoreCase("key()"))
                    {
                        // Avoid conflicting with Indexable::key()
                        //
                        fluentGetterName = "keyVal()";
                    }
                    //
                    getterBuilder.AppendLine($"/**");
                    getterBuilder.AppendLine($" * @return the {property.Name} value.");
                    getterBuilder.AppendLine($" */");
                    //
                    string modelTypeName = ExtractPropertyTypeName(property, out InnerModelWrappedIn wrappedIn);
                    if (modelTypeName.EndsWith("Inner") && this.wrapReturnInner)
                    {
                        string modelInterfaceName = this.ModelJavaInterfaceName(modelTypeName);
                        if (modelInterfaceName != null)
                        {
                            string getterReturnType = ((IModelTypeJv)property.ModelType).ResponseVariant.Name;
                            getterBuilder.AppendLine($"{getterReturnType.Replace(modelTypeName, modelInterfaceName)} {fluentGetterName};");
                        }
                        else
                        {
                            string getterReturnType = ((IModelTypeJv)property.ModelType).ResponseVariant.Name;
                            getterBuilder.AppendLine($"{getterReturnType} {fluentGetterName};");
                        }
                    }
                    else
                    {
                        string getterReturnType = ((IModelTypeJv)property.ModelType).ResponseVariant.Name;
                        getterBuilder.AppendLine($"{getterReturnType} {fluentGetterName};");
                    }
                    yield return getterBuilder.ToString();
                }
            }
        }

        public IEnumerable<string> GettersImpl
        {
            get
            {
                StringBuilder getterBuilder = new StringBuilder();
                foreach (PropertyJv property in this.innerProperties)
                {
                    getterBuilder.Clear();
                    //
                    string innerGetterName = $"{property.Name.ToCamelCase()}()";
                    string fluentGetterName = innerGetterName;
                    if (fluentGetterName.EqualsIgnoreCase("key()"))
                    {
                        // Avoid conflicting with Indexable::key()
                        //
                        fluentGetterName = "keyVal()";
                    }
                    //
                    string modelTypeName = ExtractPropertyTypeName(property, out InnerModelWrappedIn wrappedIn);
                    if (modelTypeName.EndsWith("Inner") && this.wrapReturnInner)
                    {
                        // Extracted model has the suffix "Inner".
                        //
                        string modelInterfaceName = this.ModelJavaInterfaceName(modelTypeName);
                        if (modelInterfaceName != null)
                        {
                            string getterReturnType = ((IModelTypeJv)property.ModelType).ResponseVariant.Name;
                            getterReturnType = getterReturnType.Replace(modelTypeName, modelInterfaceName);
                            //
                            getterBuilder.AppendLine($"@Override");
                            getterBuilder.AppendLine($"public {getterReturnType} {fluentGetterName} {{");
                            //
                            if (property.ModelType is SequenceTypeJv)
                            {
                                SequenceTypeJv seqTypeJva = (SequenceTypeJv)property.ModelType;
                                if (seqTypeJva.ClassName.StartsWith("List<"))
                                {
                                    getterBuilder.AppendLine($"    List<{modelInterfaceName}> lst = new ArrayList<{modelInterfaceName}>();");
                                    getterBuilder.AppendLine($"    if (this.inner().{innerGetterName} != null) {{");
                                    getterBuilder.AppendLine($"        for ({modelTypeName} inner : this.inner().{innerGetterName}) {{");
                                    getterBuilder.AppendLine($"            lst.add({this.methodGroups.CtrToCreateModelFromExistingResource(modelInterfaceName + "Impl").Replace(";", "")});");
                                    getterBuilder.AppendLine($"        }}");
                                    getterBuilder.AppendLine($"    }}");
                                    getterBuilder.AppendLine($"    return lst;");
                                }
                                else
                                {
                                    throw new NotSupportedException($"Unsupported sequence type {seqTypeJva.ClassName}");
                                }
                            }
                            else if (property.ModelType is DictionaryTypeJv)
                            {
                                DictionaryTypeJv dictTypeJva = (DictionaryTypeJv)property.ModelType;
                                if (dictTypeJva.ClassName.StartsWith("Map<"))
                                {
                                    getterBuilder.AppendLine($"    Map<String, {modelInterfaceName}> mp = new HashMap<String, {modelInterfaceName}>();");
                                    getterBuilder.AppendLine($"    if (this.inner().{innerGetterName} != null) {{");
                                    getterBuilder.AppendLine($"        for (Map.Entry<String, {modelTypeName}> entry : this.inner().{innerGetterName}.entrySet()) {{");
                                    getterBuilder.AppendLine($"            {modelTypeName} inner = entry.getValue();");
                                    getterBuilder.AppendLine($"            mp.put(entry.getKey(),{this.methodGroups.CtrToCreateModelFromExistingResource(modelInterfaceName + "Impl").Replace(";", "")});");
                                    getterBuilder.AppendLine($"        }}");
                                    getterBuilder.AppendLine($"    }}");
                                    getterBuilder.AppendLine($"    return mp;");
                                }
                                else
                                {
                                    throw new NotSupportedException($"Unsupported dict type {dictTypeJva.ClassName}");
                                }
                            }
                            else
                            {
                                getterBuilder.AppendLine($"    {modelTypeName} inner = this.inner().{innerGetterName};");
                                getterBuilder.AppendLine($"    if (inner != null) {{");
                                getterBuilder.AppendLine($"        return {this.methodGroups.CtrToCreateModelFromExistingResource(modelInterfaceName + "Impl")}");
                                getterBuilder.AppendLine($"    }} else {{");
                                getterBuilder.AppendLine($"        return null;");
                                getterBuilder.AppendLine($"    }}");
                            }
                            //
                            getterBuilder.AppendLine($"}}");
                            yield return getterBuilder.ToString();
                        }
                        else
                        {
                            string getterReturnType = ((IModelTypeJv)property.ModelType).ResponseVariant.Name;
                            //
                            getterBuilder.AppendLine($"@Override");
                            getterBuilder.AppendLine($"public {getterReturnType} {fluentGetterName} {{");
                            getterBuilder.AppendLine($"    return this.inner().{FixInnerGetter(property, innerGetterName)};");
                            getterBuilder.AppendLine($"}}");
                            yield return getterBuilder.ToString();
                        }
                    }
                    else
                    {
                        string getterReturnType = ((IModelTypeJv)property.ModelType).ResponseVariant.Name;
                        //
                        getterBuilder.AppendLine($"@Override");
                        getterBuilder.AppendLine($"public {getterReturnType} {fluentGetterName} {{");
                        getterBuilder.AppendLine($"    return this.inner().{FixInnerGetter(property, innerGetterName)};");
                        getterBuilder.AppendLine($"}}");
                        yield return getterBuilder.ToString();
                    }
                }
            }
        }

        public HashSet<string> ImportsForModelInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                foreach (PropertyJv property in this.innerProperties)
                {
                    var propertyImports = this.PropertyImportsForInterface(property);
                    imports.AddRange(propertyImports);
                }
                return imports;
            }
        }

        public HashSet<string> ImportsForModelImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                foreach (PropertyJv property in this.innerProperties)
                {
                    var propertyImports = PropertyImportsForImpl(property);
                    imports.AddRange(propertyImports);
                }
                return imports;
            }
        }

        private IEnumerable<string> PropertyImportsForInterface(PropertyJv property)
        {
            string innerModelTypeFQName = string.Empty;
            string modelTypeName = ExtractPropertyTypeName(property, out InnerModelWrappedIn wrappedIn);
            if (modelTypeName.EndsWith("Inner"))
            {
                if (this.wrapReturnInner)
                {
                    innerModelTypeFQName = $"{this.package}.implementation.{modelTypeName}";
                }
                else
                {
                    yield return $"{this.package}.implementation.{modelTypeName}";
                }
            }

            foreach (string import in property.Imports)
            {
                if (import.StartsWith(this.package) && !import.Contains(".implementation."))
                {
                    // If property uses a model in NON-Implementation namespace then skip it
                    // since "composing fluent interface" and that model are in the same 
                    // namespace (NON-Implementation namespace).
                    //
                    continue;
                }
                else if (!string.IsNullOrEmpty(innerModelTypeFQName) && innerModelTypeFQName.EqualsIgnoreCase(import))
                {
                    // wrapReturnInner == true
                    //
                    // If the property extracted return type is "Inner" type then that means 
                    // we already have a fluent interface and impl wrapping that interface 
                    // so don't import the inner in "composing fluent interface".
                    continue;
                }
                else
                {
                    yield return import;
                }
            }
        }

        private IEnumerable<string> PropertyImportsForImpl(PropertyJv property)
        {
            string modelTypeName = ExtractPropertyTypeName(property, out InnerModelWrappedIn wrappedIn);
            if (modelTypeName.EndsWith("Inner") && this.wrapReturnInner)
            {
                if (wrappedIn == InnerModelWrappedIn.Map)
                {
                    yield return $"java.util.HashMap";
                }
                else if (wrappedIn == InnerModelWrappedIn.List)
                {
                    yield return $"java.util.ArrayList";
                }
                yield return $"{this.package}.{this.ModelJavaInterfaceName(modelTypeName)}";
            }

            foreach (string import in property.Imports)
            {
                if (import.StartsWith(package) && import.Contains(".implementation."))
                {
                    continue;
                }
                else
                {
                    yield return import;
                }
            }
        }

        private string PropertyInnerModelTypeFQName(PropertyJv property)
        {
            string modelTypeName = ExtractPropertyTypeName(property, out InnerModelWrappedIn wrappedIn);
            if (modelTypeName.EndsWith("Inner"))
            {
                return $"{this.package}.implementation.{modelTypeName}";
            }
            else
            {
                return string.Empty;
            }
        }

        private string ExtractPropertyTypeName(PropertyJv property, out InnerModelWrappedIn wrappedIn)
        {
            if (property.ModelType is SequenceTypeJv || property.ModelType is DictionaryTypeJv)
            {
                wrappedIn = InnerModelWrappedIn.None;
                //
                int level = 0;
                var modelType = property.ModelType;
                while (modelType is SequenceTypeJv || modelType is DictionaryTypeJv)
                {
                    level++;
                    if (modelType is SequenceTypeJv)
                    {
                        modelType = ((SequenceTypeJv)modelType).ElementType;
                        if (wrappedIn == InnerModelWrappedIn.None)
                        {
                            wrappedIn = InnerModelWrappedIn.List;
                        }
                    }
                    else if (modelType is DictionaryTypeJv)
                    {
                        modelType = ((DictionaryTypeJv)modelType).ValueType;
                        if (wrappedIn == InnerModelWrappedIn.None)
                        {
                            wrappedIn = InnerModelWrappedIn.Map;
                        }
                    }
                }
                if (level == 1)
                {
                    // For now: Supports wrapping only one level
                    //
                    return modelType.ClassName;
                }
                else
                {
                    wrappedIn = InnerModelWrappedIn.None;
                    if (this.wrapReturnInner)
                    {
                        return property.ModelTypeName;
                    }
                    else
                    {
                        return modelType.ClassName;
                    }
                }
            }
            else
            {
                wrappedIn = InnerModelWrappedIn.None;
                return property.ModelTypeName;
            }
        }

        private string ModelJavaInterfaceName(string innerModelName)
        {
            if (!innerModelName.EndsWith("Inner"))
            {
                throw new ArgumentException("innerModelName should be an Inner but provided value is {innerModelName}");
            }
            var gModelImpl = this.methodGroups.GroupableFluentModels
                .Select(m => m.Impl)
                .FirstOrDefault(impl => impl.InnerModelName.Equals(innerModelName));
            if (gModelImpl != null)
            {
                return gModelImpl.JavaInterfaceName;
            }
            else
            {
                var ngModelImpl = this.methodGroups.NonGroupableTopLevelFluentModels
                    .Select(m => m.Impl)
                    .FirstOrDefault(impl => impl.InnerModelName.Equals(innerModelName));
                if (ngModelImpl != null)
                {
                    return ngModelImpl.JavaInterfaceName;
                }
                else
                {
                    var nestedModelImpl = this.methodGroups.NestedFluentModels
                        .Select(m => m.Impl)
                        .FirstOrDefault(impl => impl.InnerModelName.Equals(innerModelName));
                    if (nestedModelImpl != null)
                    {
                        return nestedModelImpl.JavaInterfaceName;
                    }
                    else
                    {
                        var roModelImpl = this.methodGroups.ReadonlyFluentModels
                            .Select(m => m.Impl)
                            .FirstOrDefault(impl => impl.InnerModelName.Equals(innerModelName));
                        if (roModelImpl != null)
                        {
                            return roModelImpl.JavaInterfaceName;
                        }
                        else
                        {
                            return null;
                            // TODO: eventually we want fluent interface and impl for all model classes with inner suffix
                            //
                            //throw new ArgumentException($"Unable to resolve the fluent interface type for the inner model type '{innerModelName}'");
                        }
                    }
                }
            }
        }

        private string FixInnerGetter(PropertyJv innerGetterProperty, string innerGetterPropertyName)
        {
            if (innerGetterPropertyName.EqualsIgnoreCase("tags()"))
            {
                if (innerGetterProperty.Parent != null && innerGetterProperty.Parent is CompositeTypeJv parent)
                {
                    if (parent.Imports != null && parent.Imports.Contains<string>("com.microsoft.azure.Resource"))
                    {
                        return "getTags()";
                    }
                }
                return innerGetterPropertyName;
            }
            else
            {
                return innerGetterPropertyName;
            }
        }

        enum InnerModelWrappedIn
        {
            List,
            Map,
            None
        }
    }
}
