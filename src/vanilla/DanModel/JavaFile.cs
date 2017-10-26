using System;

namespace AutoRest.Java.DanModel
{
    public class JavaFile
    {
        public JavaFile(string filePath, string headerComment, string package, int? headerCommentWrapWidth)
        {
            FilePath = filePath;
            Contents = new JavaFileContents();
                
            if (!string.IsNullOrEmpty(headerComment))
            {
                Contents.SetWordWrapIndex(headerCommentWrapWidth)
                    .MultipleLineComment((comment) =>
                    {
                        comment.SetWordWrapIndex(headerCommentWrapWidth)
                            .Line(headerComment)
                            .SetWordWrapIndex(null);
                    })
                    .Line();
            }

            if (!string.IsNullOrEmpty(package))
            {
                Contents.Package(package)
                    .Line();
            }   
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

        public JavaFile Import(params string[] imports)
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
    }
}
