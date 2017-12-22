using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.DanModel
{
    public class JavaFile
    {
        private string package;
        private int packageWithPeriodLength;

        public JavaFile(string filePath)
        {
            FilePath = filePath;
            Contents = new JavaFileContents();
        }

        public string FilePath { get; }

        public JavaFileContents Contents { get; }

        public JavaFile Text(string text)
        {
            Contents.Text(text);
            return this;
        }

        public JavaFile Line(string text)
        {
            Contents.Line(text);
            return this;
        }

        public JavaFile Line()
        {
            Contents.Line();
            return this;
        }

        public JavaFile Block(string text, Action<JavaBlock> bodyAction)
        {
            Contents.Block(text, bodyAction);
            return this;
        }

        public JavaFile PublicClass(string classDeclaration, Action<JavaClass> classAction)
        {
            Contents.PublicClass(classDeclaration, classAction);
            return this;
        }

        public JavaFile PublicFinalClass(string classDeclaration, Action<JavaClass> classAction)
        {
            Contents.PublicFinalClass(classDeclaration, classAction);
            return this;
        }

        public JavaFile Package(string package)
        {
            this.package = package;
            this.packageWithPeriodLength = (package == null ? 0 : package.Period().Length);
            Contents.Package(package);
            return this;
        }

        public JavaFile Import(params string[] imports)
        {
            Contents.Import(imports);
            return this;
        }

        public JavaFile Import(IEnumerable<string> imports)
        {
            if (!string.IsNullOrEmpty(package))
            {
                // Only import paths that don't start with this file's package, or if they do start
                // with this file's package, then they must exist within a subpackage.
                imports = imports.Where(import => !import.StartsWith(package) || import.IndexOf('.', packageWithPeriodLength) != -1);
            }
            Contents.Import(imports);
            return this;
        }

        public JavaFile SingleLineComment(string text)
        {
            Contents.SingleLineComment(text);
            return this;
        }

        public JavaFile MultipleLineComment(Action<JavaMultipleLineComment> commentAction)
        {
            Contents.MultipleLineComment(commentAction);
            return this;
        }

        public JavaFile WordWrappedMultipleLineSlashStarComment(int wordWrapWidth, Action<JavaMultipleLineComment> commentAction)
        {
            Contents.WordWrappedMultipleLineSlashStarComment(wordWrapWidth, commentAction);
            return this;
        }

        public JavaFile WordWrappedMultipleLineSlashSlashComment(int wordWrapWidth, Action<JavaMultipleLineComment> commentAction)
        {
            Contents.WordWrappedMultipleLineSlashSlashComment(wordWrapWidth, commentAction);
            return this;
        }

        public JavaFile WordWrappedMultipleLineComment(int wordWrapWidth, Action<JavaMultipleLineComment> commentAction)
        {
            Contents.WordWrappedMultipleLineComment(wordWrapWidth, commentAction);
            return this;
        }

        public JavaFile Annotation(params string[] annotations)
        {
            Contents.Annotation(annotations);
            return this;
        }

        public JavaFile PublicEnum(string enumName, Action<JavaBlock> enumAction)
        {
            Contents.PublicEnum(enumName, enumAction);
            return this;
        }

        public void PublicInterface(string interfaceName, Action<JavaInterface> interfaceAction)
        {
            Contents.PublicInterface(interfaceName, interfaceAction);
        }
    }
}
