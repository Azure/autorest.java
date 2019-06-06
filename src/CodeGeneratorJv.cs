// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Java.Model;
using AutoRest.Java.Template;
using System.Linq;
using System.Threading.Tasks;

namespace AutoRest.Java
{
    public class CodeGeneratorJv : CodeGenerator
    {
        public const string targetVersion = "1.1.3";
        internal const string pomVersion = targetVersion + "-SNAPSHOT";
        private const string ClientRuntimePackage = "com.microsoft.rest.v3:client-runtime:3.0.0-SNAPSHOT from snapshot repo https://oss.sonatype.org/content/repositories/snapshots/";
        public override string UsageInstructions => $"The {ClientRuntimePackage} maven dependency is required to execute the generated code.";
        public override string ImplementationFileExtension => ".java";

        /// <summary>
        /// Generate Java client code for given ServiceClient.
        /// </summary>
        /// <param name="serviceClient"></param>
        /// <returns></returns>
        public override Task Generate(AutoRest.Core.Model.CodeModel codeModel)
        {
            var cm = (CodeModelJv) codeModel;

            var client = Mappers.ClientMapper.Map(cm);
 
            var javaPackage = new JavaPackage();

            javaPackage.AddServieClient(client.ServiceClient.Package, client.ServiceClient.ClassName, client.ServiceClient);
            javaPackage.AddServieClientBuilder(client.ServiceClient.Package, client.ServiceClient.InterfaceName + "Builder", client.ServiceClient);

            foreach (MethodGroupClient methodGroupClient in client.ServiceClient.MethodGroupClients)
            {
                javaPackage.AddMethodGroup(methodGroupClient.Package, methodGroupClient.ClassName, methodGroupClient);
            }

            foreach (ClientResponse response in client.ResponseModels)
            {
                javaPackage.AddClientResponse(response.Package, response.Name, response);
            }

            foreach (ClientModel model in client.Models)
            {
                javaPackage.AddModel(model.Package, model.Name, model);
            }

            foreach (EnumType enumType in client.Enums)
            {
                javaPackage.AddEnum(enumType.Package, enumType.Name, enumType);
            }

            foreach (XmlSequenceWrapper xmlSequenceWrapper in client.XmlSequenceWrappers)
            {
                javaPackage.AddXmlSequenceWrapper(xmlSequenceWrapper.Package, xmlSequenceWrapper.WrapperClassName, xmlSequenceWrapper);
            }

            foreach (ClientException exception in client.Exceptions)
            {
                javaPackage.AddException(exception.Package, exception.Name, exception);
            }

            if (JavaSettings.Instance.IsAzureOrFluent)
            {
                foreach (PageDetails pageClass in cm.PageClasses)
                {
                    javaPackage.AddPage(pageClass.Package, pageClass.ClassName, pageClass);
                }
            }

            if (client.Manager != null)
            {
                javaPackage.AddManager(client.Manager.Package, $"{client.Manager.ServiceName}Manager", client.Manager);
            }

            if (!JavaSettings.Instance.IsFluent)
            {
                if (JavaSettings.Instance.GenerateClientInterfaces)
                {
                    javaPackage.AddServiceClientInterface(client.ServiceClient.InterfaceName, client.ServiceClient);

                    foreach (MethodGroupClient methodGroupClient in client.ServiceClient.MethodGroupClients)
                    {
                        javaPackage.AddMethodGroupInterface(methodGroupClient.InterfaceName, methodGroupClient);
                    }
                }
            }
            else
            {
                if (JavaSettings.Instance.RegeneratePom)
                {
                    javaPackage.AddPom(new PomTemplate { Model = (CodeModelJv) codeModel });
                }
            }

            foreach (PackageInfo packageInfo in client.PackageInfos)
            {
                javaPackage.AddPackageInfo(packageInfo.Package, "package-info", packageInfo);
            }

            // string folderPrefix = "src/main/java/" + cm.JavaSettings.Package.Replace('.', '/').Trim('/');
            // ISet<string> foldersWithGeneratedFiles = new HashSet<string>(javaFiles.Select((JavaFile javaFile) => Path.GetDirectoryName(javaFile.FilePath)));
            // foreach (string folderWithGeneratedFiles in foldersWithGeneratedFiles)
            // {
            //     string subpackage = folderWithGeneratedFiles
            //         .Substring(folderPrefix.Length)
            //         .Replace('/', '.')
            //         .Replace('\\', '.')
            //         .Trim('.');
            //     javaFiles.Add(GetPackageInfoJavaFiles(client, subpackage, cm.JavaSettings));
            // }

            return Task.WhenAll(javaPackage.Select(javaFile => Write(javaFile.Contents.ToString(), javaFile.FilePath)));
        }
    }
}
