using AutoRest.Core;
using AutoRest.Core.Utilities;
using AutoRest.Java.Azure.Model;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class ServiceManagerModel
    {
        private readonly CodeModelJvaf codeModel;
        private readonly FluentMethodGroups fluentMethodGroups;
        private readonly string ns;

        public ServiceManagerModel(CodeModelJvaf codeModel, FluentMethodGroups fluentMethodGroups)
        {
            this.codeModel = codeModel;
            this.fluentMethodGroups = fluentMethodGroups;
            this.ns = Settings.Instance.Namespace.ToLower();
        }


        private string managerName;
        public string ManagerName
        {
            get
            {
                if (this.managerName == null)
                {
                    var fluentConfig = FluentConfig.Create();
                    if (fluentConfig.ModuleName != null)
                    {
                        this.managerName = $"{fluentConfig.ModuleName}Manager";
                    }
                    else
                    {
                        this.managerName = $"{codeModel.ServiceName}Manager";
                    }
                }
                return this.managerName;
            }
        }

        public HashSet<string> Imports
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (fluentMethodGroups != null)
                {
                    foreach (IFluentMethodGroup methodGroup in fluentMethodGroups.Select( c => c.Value).Select(v => v.PrunedMethodGroup))
                    {
                        imports.Add($"{ns}.{methodGroup.JavaInterfaceName}");
                    }
                }
                if (codeModel.IsMultiApi)
                {
                    imports.Add("com.microsoft.azure.arm.resources.implementation.AzureConfigurableCoreImpl");
                    imports.Add("com.microsoft.azure.arm.resources.implementation.ManagerCore");
                }
                else
                {
                    imports.Add("com.microsoft.azure.management.resources.fluentcore.arm.implementation.AzureConfigurableImpl");
                    imports.Add("com.microsoft.azure.management.resources.fluentcore.arm.implementation.Manager");
                    imports.Add("com.microsoft.azure.management.resources.fluentcore.utils.ProviderRegistrationInterceptor");
                }
                return imports;
            }
        }

        public string ExtendsFrom
        {
            get
            {
                if (codeModel.IsMultiApi)
                {
                    return $" extends ManagerCore<{ManagerName}, {Name}Impl>";
                }
                else
                {
                    return $" extends Manager<{ManagerName}, {Name}Impl>";
                }
            }
        }

        public string ConfigurableImplExtendsFrom
        {
            get
            {
                if (codeModel.IsMultiApi)
                {
                    return $" extends AzureConfigurableCoreImpl<Configurable>";
                }
                else
                {
                    return $" extends AzureConfigurableImpl<Configurable>";
                }
            }
        }

        public IEnumerable<string> Interceptors
        {
            get
            {
                if (codeModel.IsMultiApi)
                {
                    yield break;
                }
                else
                {
                    yield return ".withInterceptor(new ProviderRegistrationInterceptor(credentials))";
                }
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
                    foreach (IFluentMethodGroup methodGroup in fluentMethodGroups.Select(c => c.Value).Select(v => v.PrunedMethodGroup))
                    {
                        yield return $"private {methodGroup.JavaInterfaceName} {methodGroup.JavaInterfaceName.ToCamelCase()};";
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
                    foreach (IFluentMethodGroup methodGroup in fluentMethodGroups.Select(c => c.Value).Select(v => v.PrunedMethodGroup))
                    {
                        string groupInterfaceName = methodGroup.JavaInterfaceName;
                        string groupImplName = $"{groupInterfaceName}Impl";
                        string groupImplVariableName = groupInterfaceName.ToCamelCase();
                        string groupAccessorName = groupInterfaceName.ToCamelCase();
                        //
                        StringBuilder methodBuilder = new StringBuilder();
                        methodBuilder.AppendLine($"/**");
                        methodBuilder.AppendLine($" * @return Entry point to manage {groupInterfaceName}.");
                        methodBuilder.AppendLine($" */");
                        methodBuilder.AppendLine($"public {groupInterfaceName} {groupAccessorName}() {{");
                        methodBuilder.AppendLine($"    if (this.{groupImplVariableName} == null) {{");
                        methodBuilder.AppendLine($"        this.{groupImplVariableName} = new {groupImplName}(this);");
                        methodBuilder.AppendLine($"    }}");
                        methodBuilder.AppendLine($"    return this.{groupImplVariableName};");
                        methodBuilder.AppendLine($"}}");
                        yield return methodBuilder.ToString();
                    }
                }
            }
        }
    }
}
