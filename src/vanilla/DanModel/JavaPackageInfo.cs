namespace AutoRest.Java.DanModel
{
    public class JavaPackageInfo : JavaFileGenerator
    {
        public JavaPackageInfo(string title, string subPackage, string description)
        {
            Title = title;
            SubPackage = subPackage;
            Description = description;
        }

        protected string Title { get; }

        protected string SubPackage { get; }

        protected string Description { get; }

        protected override string FileNameWithoutExtension => "package-info";

        public override JavaFile GenerateJavaFile(string folderPath, string headerComment, string package, int maximumHeaderCommentWidth)
        {
            string filePath = GetFilePath(folderPath);
            JavaFile javaFile = new JavaFile(filePath);

            if (!string.IsNullOrEmpty(headerComment))
            {
                javaFile.WordWrappedMultipleLineSlashSlashComment(maximumHeaderCommentWidth, (comment) =>
                    {
                        comment.Line(headerComment);
                    })
                    .Line();
            }

            javaFile.WordWrappedMultipleLineComment(maximumHeaderCommentWidth, (comment) =>
                {
                    if (string.IsNullOrEmpty(SubPackage))
                    {
                        comment.Line($"This package contains the classes for {Title}.");
                    }
                    else
                    {
                        comment.Line($"This package contains the {SubPackage} classes for {Title}.");
                    }

                    if (!string.IsNullOrEmpty(Description))
                    {
                        comment.Line(Description.Period());
                    }
                });

            javaFile.Package(package);

            return javaFile;
        }
    }
}
