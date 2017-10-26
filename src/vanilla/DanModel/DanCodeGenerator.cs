using AutoRest.Core;
using AutoRest.Core.Model;
using System.Collections.Generic;

namespace AutoRest.Java.DanModel
{
    public static class DanCodeGenerator
    {
        public static IEnumerable<JavaFile> GetFiles(CodeModel codeModel, Settings settings)
        {
            IList<JavaFile> result = new List<JavaFile>();

            if (codeModel != null)
            {
                AddEnumJavaFiles(codeModel, settings, result);
            }

            return result;
        }

        public static IEnumerable<JavaFile> GetEnumJavaFiles(CodeModel codeModel, Settings settings)
        {
            List<JavaFile> enumJavaFiles = new List<JavaFile>();
            AddEnumJavaFiles(codeModel, settings, enumJavaFiles);
            return enumJavaFiles;
        }

        public static void AddEnumJavaFiles(CodeModel codeModel, Settings settings, IList<JavaFile> javaFiles)
        {
            string headerCommentText = settings.Header;

            string package = $"{codeModel.Namespace.ToLowerInvariant()}.{JavaEnum.RelativePackage}";
            int maximumMultipleLineCommentWidth = settings.MaximumCommentColumns;

            foreach (EnumType enumType in codeModel.EnumTypes)
            {
                JavaEnum javaEnum = GetEnum(enumType);
                JavaFile javaFile = javaEnum.GenerateJavaFile(headerCommentText, package, maximumMultipleLineCommentWidth);
                javaFiles.Add(javaFile);
            }
        }

        public static JavaEnum GetEnum(EnumType enumType)
        {
            string enumName = enumType.Name;

            JavaEnum result = enumType.ModelAsString ?
                new JavaExpandableStringEnum(enumName) :
                new JavaEnum(enumName);

            foreach (EnumValue value in enumType.Values)
            {
                result.AddValue(value.MemberName, value.SerializedName);
            }

            return result;
        }
    }
}
