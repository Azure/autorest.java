using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using AutoRest.Java.Model;
using AutoRest.Java.Template;

namespace AutoRest.Java
{
    public class JavaPackage : IEnumerable<JavaFile>
    {
        private JavaSettings settings;
        private List<JavaFile> javaFiles;
        private JavaFileFactory javaFileFactory;

        public JavaPackage()
        {
            this.settings = JavaSettings.Instance;
            this.javaFiles = new List<JavaFile>();
            this.javaFileFactory = new JavaFileFactory(settings);
        }

        public void AddManager(string package, string name, Manager model)
        {
            var javaFile = javaFileFactory.CreateSourceFile(package, name);
            Templates.ManagerTemplate.Write(model, javaFile);
            javaFiles.Add(javaFile);
        }

        public void AddServieClient(string package, string name, ServiceClient model)
        {
            var javaFile = javaFileFactory.CreateSourceFile(package, name);
            Templates.ServiceClientTemplate.Write(model, javaFile);
            javaFiles.Add(javaFile);
        }

        public void AddServiceClientInterface(string name, ServiceClient model)
        {
            var javaFile = javaFileFactory.CreateSourceFile(settings.Package, name);
            Templates.ServiceClientInterfaceTemplate.Write(model, javaFile);
            javaFiles.Add(javaFile);
        }

        public void AddMethodGroup(string package, string name, MethodGroupClient model)
        {
            var javaFile = javaFileFactory.CreateSourceFile(package, name);
            Templates.MethodGroupTemplate.Write(model, javaFile);
            javaFiles.Add(javaFile);
        }

        public void AddMethodGroupInterface(string name, MethodGroupClient model)
        {
            var javaFile = javaFileFactory.CreateSourceFile(settings.Package, name);
            Templates.MethodGroupInterfaceTemplate.Write(model, javaFile);
            javaFiles.Add(javaFile);
        }

        public void AddModel(string package, string name, ClientModel model)
        {
            var javaFile = javaFileFactory.CreateSourceFile(package, name);
            Templates.ModelTemplate.Write(model, javaFile);
            javaFiles.Add(javaFile);
        }

        public void AddException(string package, string name, ClientException model)
        {
            var javaFile = javaFileFactory.CreateSourceFile(package, name);
            Templates.ExceptionTemplate.Write(model, javaFile);
            javaFiles.Add(javaFile);
        }

        public void AddEnum(string package, string name, EnumType model)
        {
            var javaFile = javaFileFactory.CreateSourceFile(package, name);
            Templates.EnumTemplate.Write(model, javaFile);
            javaFiles.Add(javaFile);
        }

        public void AddPage(string package, string name, PageDetails model)
        {
            var javaFile = javaFileFactory.CreateSourceFile(package, name);
            Templates.PageTemplate.Write(model, javaFile);
            javaFiles.Add(javaFile);
        }

        public void AddClientResponse(string package, string name, ClientResponse model)
        {
            var javaFile = javaFileFactory.CreateSourceFile(package, name);
            Templates.ResponseTemplate.Write(model, javaFile);
            javaFiles.Add(javaFile);
        }

        public void AddXmlSequenceWrapper(string package, string name, XmlSequenceWrapper model)
        {
            var javaFile = javaFileFactory.CreateSourceFile(package, name);
            Templates.XmlSequenceWrapperTemplate.Write(model, javaFile);
            javaFiles.Add(javaFile);
        }

        public void AddPackageInfo(string package, string name, PackageInfo model)
        {
            var javaFile = javaFileFactory.CreateEmptySourceFile(package, name);
            Templates.PackageInfoTemplate.Write(model, javaFile);
            javaFiles.Add(javaFile);
        }

        public void AddPom(PomTemplate pomTemplate)
        {
            StringBuilder pomContentsBuilder = new StringBuilder();
            using (pomTemplate.TextWriter = new StringWriter(pomContentsBuilder))
            {
                pomTemplate.ExecuteAsync().GetAwaiter().GetResult();
            }
            javaFiles.Add(new JavaFile("pom.xml", pomContentsBuilder.ToString()));
        }

        public IEnumerator<JavaFile> GetEnumerator()
        {
            return javaFiles.GetEnumerator();
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return javaFiles.GetEnumerator();
        }
    }
}
