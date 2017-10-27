using System;
using System.Collections.Generic;

namespace AutoRest.Java.DanModel
{
    public class JavaClass
    {
        private readonly JavaFileContents contents;

        public JavaClass(JavaFileContents contents)
        {
            this.contents = contents;
        }

        public JavaClass Line(string text)
        {
            contents.Line(text);
            return this;
        }

        public JavaClass Line()
        {
            contents.Line();
            return this;
        }

        public JavaClass Block(string text, Action<JavaBlock> bodyAction)
        {
            contents.Block(text, bodyAction);
            return this;
        }

        public JavaClass SingleLineComment(string text)
        {
            contents.SingleLineComment(text);
            return this;
        }

        public JavaClass MultipleLineComment(Action<JavaMultipleLineComment> commentAction)
        {
            contents.MultipleLineComment(commentAction);
            return this;
        }

        public JavaClass Annotation(params string[] annotations)
        {
            contents.Annotation(annotations);
            return this;
        }

        public JavaClass Annotation(IEnumerable<string> annotations)
        {
            contents.Annotation(annotations);
            return this;
        }
    }
}
