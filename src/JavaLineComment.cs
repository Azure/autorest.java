namespace AutoRest.Java
{
    public class JavaLineComment
    {
        private readonly JavaFileContents contents;

        public JavaLineComment(JavaFileContents contents)
        {
            this.contents = contents;
        }

        public void Line(string text)
        {
            contents.Line(text);
        }
    }
}
