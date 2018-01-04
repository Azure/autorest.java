using System;

namespace AutoRest.Java
{
    public class JavaInterface : JavaType
    {
        private readonly JavaFileContents contents;
        private bool addNewLine;

        public JavaInterface(JavaFileContents contents)
        {
            this.contents = contents;
        }

        private void AddExpectedNewLine()
        {
            if (addNewLine)
            {
                contents.Line();
                addNewLine = false;
            }
        }

        public void PublicMethod(string methodSignature)
            => PublicMethod(methodSignature, null);

        public void PublicMethod(string methodSignature, Action<JavaBlock> functionBlock)
        {
            AddExpectedNewLine();
            contents.Line(methodSignature + ";");

            addNewLine = true;
        }

        public void MultipleLineComment(Action<JavaMultipleLineComment> commentAction)
        {
            AddExpectedNewLine();
            contents.MultipleLineComment(commentAction);
        }

        public void SingleLineSlashSlashComment(string comment)
        {
            AddExpectedNewLine();
            contents.SingleLineSlashSlashComment(comment);
        }

        public void Annotation(params string[] annotations)
        {
            AddExpectedNewLine();
            contents.Annotation(annotations);
        }
    }
}
