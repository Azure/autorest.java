using System.Text;

namespace AutoRest.Java.DanModel
{
    public class JavaFile
    {
        private readonly string filePath;
        private readonly StringBuilder contents;

        public JavaFile(string filePath)
        {
            this.filePath = filePath;
            contents = new StringBuilder();
        }

        public string FilePath
        {
            get { return filePath; }
        }

        public string GetContents()
        {
            return contents.ToString();
        }

        public JavaFile Add(string formattedText, params object[] formattedArguments)
        {
            if (!string.IsNullOrEmpty(formattedText))
            {
                string text = string.Format(formattedText, formattedArguments);
                contents.Append(text);
            }
            return this;
        }

        public JavaFile AddLine(string formattedText, params object[] formattedArguments)
        {
            Add(formattedText, formattedArguments);
            return AddLine();
        }

        public JavaFile AddLine()
        {
            return Add("\n");
        }
    }
}
