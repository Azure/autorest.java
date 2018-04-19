using AutoRest.Core.Utilities;
using AutoRest.Java.Azure.Model;
using AutoRest.Java.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public static class Utils
    {
        public static HashSet<string> PropertyImports(PropertyJvaf property, string innerModelPackage)
        {
            HashSet<string> imports = new HashSet<string>();
            var propertyImports = property.Imports;
            // var propertyImports = property.Imports.Where(import => !import.EqualsIgnoreCase(thisPackage));
            //
            string modelTypeName = property.ModelTypeName;
            if (property.ModelType is SequenceTypeJva)
            {
                var modelType = property.ModelType;
                while (modelType is SequenceTypeJva)
                {
                    SequenceTypeJva sequenceType = (SequenceTypeJva)modelType;
                    modelType = sequenceType.ElementType;
                }
                modelTypeName = modelType.ClassName;
            }
            if (modelTypeName.EndsWith("Inner"))
            {
                imports.Add($"{innerModelPackage}.{modelTypeName}");
            }
            imports.AddRange(propertyImports);
            return imports;
        }

        public static bool IsInPackage(string importStmt, string pacakge)
        {
            if (!importStmt.StartsWith(pacakge, StringComparison.OrdinalIgnoreCase))
            {
                return false;
            }
            else
            {
                var parts1 = importStmt.Split(new char[] { '.' });
                var parts2 = pacakge.Split(new char[] { '.' });
                return (parts1.Length - parts2.Length) == 1;
            }
        }

        public static IEnumerable<string> PropertyImportsForImpl(PropertyJvaf property, string package)
        {
            foreach (string import in property.Imports)
            {
                if (import.StartsWith(package) && import.Contains(".implementation."))
                {
                    continue;
                }
                yield return import;
            }
        }

        public static IEnumerable<string> PropertyImportsForInterface(PropertyJvaf property, string package)
        {
            foreach (string import in property.Imports)
            {
                if (import.StartsWith(package) && !import.Contains(".implementation."))
                {
                    continue;
                }
                yield return import;
            }
            //
            string modelTypeName = property.ModelTypeName;
            if (property.ModelType is SequenceTypeJva)
            {
                var modelType = property.ModelType;
                while (modelType is SequenceTypeJva)
                {
                    SequenceTypeJva sequenceType = (SequenceTypeJva)modelType;
                    modelType = sequenceType.ElementType;
                }
                modelTypeName = modelType.ClassName;
            }
            //
            if (modelTypeName.EndsWith("Inner"))
            {
                yield return $"{package}.implementation.{modelTypeName}";
            }
        }

        public static IEnumerable<string> ParameterImports(ParameterJv parameter, List<string> propertiesToSkip)
        {
            foreach (string import in parameter.InterfaceImports)
            {
                yield return import;
            }
            foreach (string import in parameter.ClientType.Imports)
            {
                yield return import;
            }
            if (parameter.ClientType is CompositeTypeJvaf composite)
            {
                foreach (string import in composite.Imports)
                {
                    yield return import;
                }
                // We do unfolding of parameters only in one level so no recursive unfolding
                //
                var composedPropertiesImports = composite.ComposedProperties
                    .OfType<PropertyJvaf>()
                    .Where(p => !propertiesToSkip.Contains(p.Name.ToLowerInvariant()))
                    .SelectMany(p => p.Imports);
                foreach (string import in composedPropertiesImports)
                {
                    yield return import;
                }
            }
        }

        public static IEnumerable<string> ParameterImportsForImpl(ParameterJv parameter, string package)
        {
            return ParameterImportsForImpl(parameter, package, new List<string>());
        }

        public static IEnumerable<string> ParameterImportsForImpl(ParameterJv parameter, string package, List<string> propertiesToSkip)
        {
            foreach (string import in Utils.ParameterImports(parameter, propertiesToSkip))
            {
                if (!import.StartsWith(package))
                {
                    yield return import;
                }
                else if (!import.Contains(".implementation."))
                {
                    yield return import;
                }
            }
        }

        public static IEnumerable<string> ParameterImportsForInterface(ParameterJv parameter, string package)
        {
            return ParameterImportsForInterface(parameter, package, new List<string>());
        }

        public static IEnumerable<string> ParameterImportsForInterface(ParameterJv parameter, string package, List<string> propertiesToSkip)
        {
            foreach (string import in Utils.ParameterImports(parameter, propertiesToSkip))
            {
                if (!import.StartsWith(package))
                {
                    yield return import;
                }
                else if (import.Contains(".implementation."))
                {
                    yield return import;
                }
            }
        }

        public static IEnumerable<string> ParametersImportsForImpl(IEnumerable<ParameterJv> parameters, string package)
        {
            return parameters
                .SelectMany(parameter => ParameterImportsForImpl(parameter, package));
        }

        public static IEnumerable<string> ParametersImportsForInterface(IEnumerable<ParameterJv> parameters, string package)
        {
            return parameters
                .SelectMany(parameter => ParameterImportsForInterface(parameter, package));
        }
    }
}
