using AutoRest.Core;
using AutoRest.Core.Utilities;
using AutoRest.Java.Azure.Fluent.Model;
using AutoRest.Java.Azure.Model;
using System.Collections.Generic;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class ServiceManagerModel
    {
        private readonly CodeModelJva codeModel;
        private readonly FluentMethodGroups fluentMethodGroups;
        private readonly string ns;

        public ServiceManagerModel(CodeModelJva codeModel, FluentMethodGroups fluentMethodGroups)
        {
            this.codeModel = codeModel;
            this.fluentMethodGroups = fluentMethodGroups;
            this.ns = Settings.Instance.Namespace.ToLower();
        }

        public HashSet<string> Imports
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (fluentMethodGroups != null)
                {
                    foreach (NestedFluentModelInterface fluentModel in fluentMethodGroups.NestedFluentModels)
                    {
                        imports.Add($"{ns}.{fluentModel.FluentMethodGroup.JavaInterfaceName}");
                    }

                    foreach (ActionOrChildAccessorOnlyMethodGroupImpl group in fluentMethodGroups.ActionOrChildAccessorOnlyMethodGroups.Values)
                    {
                        imports.Add($"{ns}.{group.Interface.JavaInterfaceName}");
                    }

                    foreach (GroupableFluentModelInterface fluentModel in fluentMethodGroups.GroupableFluentModels)
                    {
                        imports.Add($"{ns}.{fluentModel.FluentMethodGroup.JavaInterfaceName}");
                    }
                }
                return imports;
            }
        }

        public string ServiceName
        {
            get
            {
                return this.codeModel.ServiceName;
            }
        }

        public string ImplPackage
        {
            get
            {
                return this.codeModel.ImplPackage;
            }
        }

        public string BetaSinceVersion
        {
            get
            {
                return this.codeModel.BetaSinceVersion;
            }
        }

        public string ClassName
        {
            get
            {
                return $"{this.ServiceName}Manager";
            }
        }

        public string Name
        {
            get
            {
                return codeModel.Name;
            }
        }

        public IEnumerable<string> DeclareMemberVariables
        {
            get
            {
                if (fluentMethodGroups != null)
                {
                    foreach (NestedFluentModelInterface fluentModel in fluentMethodGroups.NestedFluentModels)
                    {
                        yield return $"private {fluentModel.FluentMethodGroup.JavaInterfaceName} {fluentModel.FluentMethodGroup.JavaInterfaceName.ToCamelCase()};";
                    }

                    foreach (ActionOrChildAccessorOnlyMethodGroupImpl group in fluentMethodGroups.ActionOrChildAccessorOnlyMethodGroups.Values)
                    {
                        yield return $"private {group.Interface.JavaInterfaceName} {group.Interface.JavaInterfaceName.ToCamelCase()};";
                    }

                    foreach (GroupableFluentModelInterface fluentModel in fluentMethodGroups.GroupableFluentModels)
                    {
                        yield return $"private {fluentModel.FluentMethodGroup.JavaInterfaceName} {fluentModel.FluentMethodGroup.JavaInterfaceName.ToCamelCase()};";
                    }
                }
            }
        }

        public IEnumerable<string> JvaMethods
        {
            get
            {
                if (fluentMethodGroups != null)
                {
                    foreach (NestedFluentModelInterface fluentModel in fluentMethodGroups.NestedFluentModels)
                    {
                        StringBuilder methodBuilder = new StringBuilder();
                        methodBuilder.AppendLine($"/**");
                        methodBuilder.AppendLine($" * @return Entry point to manage {fluentModel.FluentMethodGroup.JavaInterfaceName}.");
                        methodBuilder.AppendLine($" */");
                        methodBuilder.AppendLine($"public {fluentModel.FluentMethodGroup.JavaInterfaceName} {fluentModel.FluentMethodGroup.JavaInterfaceName.ToCamelCase()}() {{");
                        methodBuilder.AppendLine($"    if (this.{fluentModel.FluentMethodGroup.JavaInterfaceName.ToCamelCase()} == null) {{");
                        methodBuilder.AppendLine($"        this.{fluentModel.FluentMethodGroup.JavaInterfaceName.ToCamelCase()} = new {fluentModel.FluentMethodGroup.JavaInterfaceName}Impl(this);");
                        methodBuilder.AppendLine($"    }}");
                        methodBuilder.AppendLine($"    return this.{fluentModel.FluentMethodGroup.JavaInterfaceName.ToCamelCase()};");
                        methodBuilder.AppendLine($"}}");
                        yield return methodBuilder.ToString();
                    }

                    foreach (ActionOrChildAccessorOnlyMethodGroupImpl group in fluentMethodGroups.ActionOrChildAccessorOnlyMethodGroups.Values)
                    {

                        StringBuilder methodBuilder = new StringBuilder();
                        methodBuilder.AppendLine($"/**");
                        methodBuilder.AppendLine($" * @return Entry point to manage {group.Interface.JavaInterfaceName}.");
                        methodBuilder.AppendLine($" */");
                        methodBuilder.AppendLine($"public {group.Interface.JavaInterfaceName} {group.Interface.JavaInterfaceName.ToCamelCase()}() {{");
                        methodBuilder.AppendLine($"    if (this.{group.Interface.JavaInterfaceName.ToCamelCase()} == null) {{");
                        methodBuilder.AppendLine($"        this.{group.Interface.JavaInterfaceName.ToCamelCase()} = new {group.Interface.JavaInterfaceName}Impl(this);");
                        methodBuilder.AppendLine($"    }}");
                        methodBuilder.AppendLine($"    return this.{group.Interface.JavaInterfaceName.ToCamelCase()};");
                        methodBuilder.AppendLine($"}}");
                        yield return methodBuilder.ToString();
                    }

                    foreach (GroupableFluentModelInterface fluentModel in fluentMethodGroups.GroupableFluentModels)
                    {
                        StringBuilder methodBuilder = new StringBuilder();
                        methodBuilder.AppendLine($"/**");
                        methodBuilder.AppendLine($" * @return Entry point to manage {fluentModel.FluentMethodGroup.JavaInterfaceName}.");
                        methodBuilder.AppendLine($" */");
                        methodBuilder.AppendLine($"public {fluentModel.FluentMethodGroup.JavaInterfaceName} {fluentModel.FluentMethodGroup.JavaInterfaceName.ToCamelCase()}() {{");
                        methodBuilder.AppendLine($"    if (this.{fluentModel.FluentMethodGroup.JavaInterfaceName.ToCamelCase()} == null) {{");
                        methodBuilder.AppendLine($"        this.{fluentModel.FluentMethodGroup.JavaInterfaceName.ToCamelCase()} = new {fluentModel.FluentMethodGroup.JavaInterfaceName}Impl(this);");
                        methodBuilder.AppendLine($"    }}");
                        methodBuilder.AppendLine($"    return this.{fluentModel.FluentMethodGroup.JavaInterfaceName.ToCamelCase()};");
                        methodBuilder.AppendLine($"}}");
                        yield return methodBuilder.ToString();
                    }
                }
            }
        }
    }
}
