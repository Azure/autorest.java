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
        private TemplateFactory writerFactory;
        private JavaFileFactory javaFileFactory;

        public JavaPackage(JavaSettings settings)
        {
            this.settings = settings;
            this.javaFiles = new List<JavaFile>();
            this.writerFactory = new TemplateFactory(settings);
            this.javaFileFactory = new JavaFileFactory(settings);
        }

        public void AddSourceClass<ModelT>(string package, string name, ModelT model)
        {
            var javaFile = javaFileFactory.CreateSourceFile(package, name);
            writerFactory.GetWriter<ModelT, JavaFile>().Write(model, javaFile);
            javaFiles.Add(javaFile);
        }

        public void AddSourceInterface<ModelT>(string name, ModelT model)
        {
            var javaFile = javaFileFactory.CreateSourceFile(settings.Package, name);
            writerFactory.GetWriter<ModelT, JavaFile>(true).Write(model, javaFile);
            javaFiles.Add(javaFile);
        }

        public void AddPackageInfo(string package, string name, PackageInfo model)
        {
            var javaFile = javaFileFactory.CreateEmptySourceFile(package, name);
            writerFactory.GetWriter<PackageInfo, JavaFile>().Write(model, javaFile);
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
