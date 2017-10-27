using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Java.Model;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.DanModel
{
    public static class DanCodeGenerator
    {
        public static IEnumerable<JavaFile> GetExceptionJavaFiles(CodeModelJv codeModel, Settings settings, string folderPath, string packageSuffix)
        {
            List<JavaFile> exceptionJavaFiles = new List<JavaFile>();
            AddExceptionJavaFiles(codeModel, settings, exceptionJavaFiles, folderPath, packageSuffix);
            return exceptionJavaFiles;
        }

        public static void AddExceptionJavaFiles(CodeModelJv codeModel, Settings settings, IList<JavaFile> javaFiles, string folderPath, string packageSuffix)
        {
            string headerCommentText = settings.Header;

            string package = GetPackage(codeModel, packageSuffix);
            int maximumMultipleLineCommentWidth = settings.MaximumCommentColumns;

            foreach (CompositeTypeJv exceptionType in codeModel.ErrorTypes)
            {
                string exceptionBodyTypeName = exceptionType.Name;
                string exceptionName = exceptionType.ExceptionTypeDefinitionName;

                // Skip any exceptions that are named "CloudErrorException" or have a body named
                // "CloudError" because those types already exist in the runtime.
                if (exceptionBodyTypeName != "CloudError" && exceptionName != "CloudErrorException")
                {
                    JavaException javaException = new JavaException(exceptionName, exceptionBodyTypeName);
                    JavaFile javaFile = javaException.GenerateJavaFile(folderPath, headerCommentText, package, maximumMultipleLineCommentWidth);
                    javaFiles.Add(javaFile);
                }
            }
        }

        public static IEnumerable<JavaFile> GetEnumJavaFiles(CodeModel codeModel, Settings settings, string folderPath, string packageSuffix)
        {
            List<JavaFile> enumJavaFiles = new List<JavaFile>();
            AddEnumJavaFiles(codeModel, settings, enumJavaFiles, folderPath, packageSuffix);
            return enumJavaFiles;
        }

        public static void AddEnumJavaFiles(CodeModel codeModel, Settings settings, IList<JavaFile> javaFiles, string folderPath, string packageSuffix)
        {
            string headerCommentText = settings.Header;

            string package = GetPackage(codeModel, packageSuffix);

            int maximumMultipleLineCommentWidth = settings.MaximumCommentColumns;

            foreach (EnumType enumType in codeModel.EnumTypes)
            {
                string enumName = enumType.Name;

                IEnumerable<JavaEnumValue> enumValues = enumType.Values
                    .Select((EnumValue value) => new JavaEnumValue(value.MemberName, value.SerializedName));

                JavaEnum javaEnum = enumType.ModelAsString ?
                    new JavaExpandableStringEnum(enumName, enumValues) :
                    new JavaEnum(enumName, enumValues);

                JavaFile javaFile = javaEnum.GenerateJavaFile(folderPath, headerCommentText, package, maximumMultipleLineCommentWidth);
                javaFiles.Add(javaFile);
            }
        }

        private static string GetPackage(CodeModel codeModel, string packageSuffix)
        {
            string package = $"{codeModel.Namespace.ToLowerInvariant()}";
            if (!string.IsNullOrEmpty(packageSuffix))
            {
                package = $"{package}.{packageSuffix}";
            }
            return package;
        }
    }
}
