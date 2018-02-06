using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java
{
    public class JavaFile
    {
        private string package;
        private int packageWithPeriodLength;

        public JavaFile(string filePath)
            : this(filePath, null)
        {
        }

        public JavaFile(string filePath, string fileContents)
        {
            FilePath = filePath;
            Contents = new JavaFileContents(fileContents);
        }

        public string FilePath { get; }

        public JavaFileContents Contents { get; }

        public void Text(string text)
        {
            Contents.Text(text);
        }

        public void Line(string text)
        {
            Contents.Line(text);
        }

        public void Line()
        {
            Contents.Line();
        }

        public void Indent(Action indentAction)
        {
            Contents.Indent(indentAction);
        }

        public void PublicClass(bool isFinal, string classDeclaration, Action<JavaClass> classAction)
        {
            Contents.PublicClass(isFinal, classDeclaration, classAction);
        }

        public void PublicFinalClass(string classDeclaration, Action<JavaClass> classAction)
        {
            PublicClass(true, classDeclaration, classAction);
        }

        public void Package(string package)
        {
            this.package = package;
            if (string.IsNullOrEmpty(package))
            {
                packageWithPeriodLength = 0;
            }
            else
            {
                packageWithPeriodLength = package.Length;
                if (!package.EndsWith('.'))
                {
                    ++packageWithPeriodLength;
                }
            }
            Contents.Package(package);
        }

        public void Import(params string[] imports)
        {
            Contents.Import(imports);
        }

        public void Import(IEnumerable<string> imports)
        {
            if (!string.IsNullOrEmpty(package))
            {
                // Only import paths that don't start with this file's package, or if they do start
                // with this file's package, then they must exist within a subpackage.
                imports = imports.Where(import => !import.StartsWith(package) || import.IndexOf('.', packageWithPeriodLength) != -1);
            }
            Contents.Import(imports);
        }

        public void JavadocComment(Action<JavaJavadocComment> commentAction)
        {
            Contents.JavadocComment(commentAction);
        }

        public void JavadocComment(int wordWrapWidth, Action<JavaJavadocComment> commentAction)
        {
            Contents.JavadocComment(wordWrapWidth, commentAction);
        }

        public void LineComment(int wordWrapWidth, Action<JavaLineComment> commentAction)
        {
            Contents.LineComment(wordWrapWidth, commentAction);
        }

        public void Annotation(params string[] annotations)
        {
            Contents.Annotation(annotations);
        }

        public void PublicEnum(string enumName, Action<JavaBlock> enumAction)
        {
            Contents.PublicEnum(enumName, enumAction);
        }

        public void PublicInterface(string interfaceName, Action<JavaInterface> interfaceAction)
        {
            Contents.PublicInterface(interfaceName, interfaceAction);
        }
    }
}
