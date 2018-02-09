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

        public void PublicFinalClass(string classDeclaration, Action<JavaClass> classAction)
        {
            PublicClass(new[] { JavaModifier.Final }, classDeclaration, classAction);
        }

        public void PublicClass(IEnumerable<JavaModifier> modifiers, string classDeclaration, Action<JavaClass> classAction)
        {
            Class(JavaVisibility.Public, modifiers, classDeclaration, classAction);
        }

        public void Class(JavaVisibility visibility, IEnumerable<JavaModifier> modifiers, string classDeclaration, Action<JavaClass> classAction)
        {
            Contents.Class(visibility, modifiers, classDeclaration, classAction);
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

        public void PublicEnum(string enumName, Action<JavaEnum> enumAction)
        {
            Enum(JavaVisibility.Public, enumName, enumAction);
        }

        public void Enum(JavaVisibility visibility, string enumName, Action<JavaEnum> enumAction)
        {
            Contents.Enum(visibility, enumName, enumAction);
        }

        public void PublicInterface(string interfaceName, Action<JavaInterface> interfaceAction)
        {
            Interface(JavaVisibility.Public, interfaceName, interfaceAction);
        }

        public void Interface(JavaVisibility visibility, string interfaceName, Action<JavaInterface> interfaceAction)
        {
            Contents.Interface(visibility, interfaceName, interfaceAction);
        }
    }
}
