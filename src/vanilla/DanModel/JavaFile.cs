using System;
using System.Collections.Generic;

namespace AutoRest.Java.DanModel
{
    public class JavaFile
    {
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

        public JavaFile Package(string package)
        {
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

        public JavaFile WordWrappedMultipleLineSlashStarComment(int wordWrapWidth, Action<JavaWordWrappedMultipleLineComment> commentAction)
        {
            Contents.WordWrappedMultipleLineSlashStarComment(wordWrapWidth, commentAction);
            return this;
        }

        public JavaFile WordWrappedMultipleLineSlashSlashComment(int wordWrapWidth, Action<JavaWordWrappedMultipleLineComment> commentAction)
        {
            Contents.WordWrappedMultipleLineSlashSlashComment(wordWrapWidth, commentAction);
            return this;
        }

        public JavaFile WordWrappedMultipleLineComment(int wordWrapWidth, Action<JavaWordWrappedMultipleLineComment> commentAction)
        {
            Contents.WordWrappedMultipleLineComment(wordWrapWidth, commentAction);
            return this;
        }

        public JavaFile Annotation(params string[] annotations)
        {
            Contents.Annotation(annotations);
            return this;
        }

        public JavaFile Annotation(IEnumerable<string> annotations)
        {
            Contents.Annotation(annotations);
            return this;
        }

        public JavaFile PublicFinalClass(string className, Action<JavaClass> classAction)
        {
            Contents.PublicFinalClass(className, classAction);
            return this;
        }

        public JavaFile PublicClass(string className, Action<JavaClass> classAction)
        {
            Contents.PublicClass(className, classAction);
            return this;
        }

        public JavaFile PublicEnum(string enumName, Action<JavaBlock> enumAction)
        {
            Contents.PublicEnum(enumName, enumAction);
            return this;
        }
    }
}
