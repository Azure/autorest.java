using System.IO;

namespace AutoRest.Java.DanModel
{
    public abstract class JavaFileGenerator
    {
        protected abstract string FileNameWithoutExtension { get; }

        protected string GetFilePath(string folderPath)
        {
            string result = Path.Combine(folderPath, $"{FileNameWithoutExtension}.java");

            result = result.Replace('\\', '/');
            result = result.Replace("//", "/");

            return result;
        }

        public abstract JavaFile GenerateJavaFile(string folderPath, string headerComment, string package, int maximumHeaderCommentWidth);

        protected JavaFile GenerateJavaFileWithHeaderAndPackage(string folderPath, string headerComment, string package, int maximumHeaderCommentWidth)
        {
            string filePath = GetFilePath(folderPath);
            JavaFile javaFile = new JavaFile(filePath);

            if (!string.IsNullOrEmpty(headerComment))
            {
                javaFile.MultipleLineComment((comment) =>
                    {
                        comment.SetWordWrapIndex(maximumHeaderCommentWidth)
                            .Line(headerComment)
                            .SetWordWrapIndex(null);
                    })
                    .Line();
            }

            if (!string.IsNullOrEmpty(package))
            {
                javaFile.Package(package)
                    .Line();
            }

            return javaFile;
        }
    }
}
