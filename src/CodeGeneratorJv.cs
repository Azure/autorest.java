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
        private const string ClientRuntimePackage = "com.microsoft.rest.v2:client-runtime:2.0.0-SNAPSHOT from snapshot repo https://oss.sonatype.org/content/repositories/snapshots/";
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
            var parserFactory = new ParserFactory(cm.JavaSettings);
            var writerFactory = new TemplateFactory(cm.JavaSettings);

            var client = parserFactory.GetParser<CodeModelJv, Client>().Parse(cm);
 
            var javaPackage = new JavaPackage(cm.JavaSettings);

            javaPackage.AddSourceClass(client.ServiceClient.Package, client.ServiceClient.ClassName, client.ServiceClient);

            foreach (MethodGroupClient methodGroupClient in client.ServiceClient.MethodGroupClients)
            {
                javaPackage.AddSourceClass(methodGroupClient.Package, methodGroupClient.ClassName, methodGroupClient);
            }

            foreach (ClientResponse response in client.ResponseModels)
            {
                javaPackage.AddSourceClass(response.Package, response.Name, response);
            }

            foreach (ClientModel model in client.Models)
            {
                javaPackage.AddSourceClass(model.Package, model.Name, model);
            }

            foreach (EnumType enumType in client.Enums)
            {
                javaPackage.AddSourceClass(enumType.Package, enumType.Name, enumType);
            }

            foreach (XmlSequenceWrapper xmlSequenceWrapper in client.XmlSequenceWrappers)
            {
                javaPackage.AddSourceClass(xmlSequenceWrapper.Package, xmlSequenceWrapper.WrapperClassName, xmlSequenceWrapper);
            }

            foreach (ClientException exception in client.Exceptions)
            {
                javaPackage.AddSourceClass(exception.Package, exception.Name, exception);
            }

            if (cm.JavaSettings.IsAzureOrFluent)
            {
                foreach (PageDetails pageClass in cm.PageClasses)
                {
                    javaPackage.AddSourceClass(pageClass.Package, pageClass.ClassName, pageClass);
                }
            }

            if (client.Manager != null)
            {
                javaPackage.AddSourceClass(client.Manager.Package, $"{client.Manager.ServiceName}Manager", client.Manager);
            }

            if (!cm.JavaSettings.IsFluent)
            {
                if (cm.JavaSettings.GenerateClientInterfaces)
                {
                    javaPackage.AddSourceInterface(client.ServiceClient.InterfaceName, client.ServiceClient);

                    foreach (MethodGroupClient methodGroupClient in client.ServiceClient.MethodGroupClients)
                    {
                        javaPackage.AddSourceInterface(methodGroupClient.InterfaceName, methodGroupClient);
                    }
                }
            }
            else
            {
                if (cm.JavaSettings.RegeneratePom)
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
