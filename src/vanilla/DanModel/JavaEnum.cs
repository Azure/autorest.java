using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace AutoRest.Java.DanModel
{
    public class JavaEnum
    {
        public const string RelativePackage = "models";

        public JavaEnum(string headerCommentText, string package, string enumName, IEnumerable<JavaEnumValue> values)
        {
            HeaderCommentText = headerCommentText;
            Package = package;
            EnumName = enumName;
            Values = values ?? Enumerable.Empty<JavaEnumValue>();
        }

        public JavaEnum WithMaximumMultipleLineCommentWidth(int maximumMultipleLineCommentWidth)
        {
            MaximumMultipleLineCommentWidth = maximumMultipleLineCommentWidth;
            return this;
        }

        protected IEnumerable<JavaEnumValue> Values { get; }

        protected int? MaximumMultipleLineCommentWidth { get; private set; }

        protected string HeaderCommentText { get; }

        protected string Package { get; }

        protected string EnumName { get; }

        protected string GetFilePath()
        {
            return Path.Combine(RelativePackage, $"{EnumName}.java");
        }

        protected Action<JavaMultipleLineComment> HeaderComment()
        {
            return (JavaMultipleLineComment comment) =>
            {
                comment.Line(HeaderCommentText);
            };
        }

        protected Action<JavaMultipleLineComment> EnumTypeComment()
        {
            return (JavaMultipleLineComment comment) =>
            {
                comment.Line($"Defines values for {EnumName}.");
            };
        }

        public virtual JavaFile GenerateJavaFile()
        {
            return new JavaFile(GetFilePath())
                .MaximumMultipleLineCommentWidth(MaximumMultipleLineCommentWidth)
                .MultipleLineComment(HeaderComment())
                .Line()
                .Package(Package)
                .Line()
                .Import("com.fasterxml.jackson.annotation.JsonCreator",
                        "com.fasterxml.jackson.annotation.JsonValue")
                .MultipleLineComment(EnumTypeComment())
                .Block($"public enum {EnumName}", (enumBlock) =>
                {
                    if (Values.Any())
                    {
                        foreach (JavaEnumValue value in Values.SkipLast(1))
                        {
                            EnumValue(enumBlock, value);
                        }
                        EnumValue(enumBlock, Values.Last(), isLast: true);
                    }

                    enumBlock.SingleLineComment($"The actual serialized value for a {EnumName} instance.")
                        .Line("private String value;")
                        .Line()
                        .Block($"{EnumName}(String value)", (constructor) =>
                        {
                            constructor.Line("this.value = value;");
                        })
                        .Line()
                        .MultipleLineComment((comment) =>
                        {
                            comment.Line($"Parses a serialized value to a {EnumName} instance.")
                                    .Line()
                                    .Line("@param value the serialized value to parse.")
                                    .Line($"@return the parsed {EnumName} object, or null if unable to parse.");
                        })
                        .Line("@JsonCreator")
                        .Block($"public static {EnumName} fromString(String value)", (function) =>
                        {
                            function.Line($"{EnumName}[] items = {EnumName}.values();")
                                    .Block($"for ({EnumName} item : items)", (foreachBlock) =>
                                    {
                                        foreachBlock.Block("if (item.toString().equalsIgnoreCase(value))", (ifBlock) =>
                                        {
                                            ifBlock.Return("item");
                                        });
                                    })
                                    .Return("null");
                        })
                        .Line()
                        .Line("@JsonValue")
                        .Line("@Override")
                        .Block("public String toString()", (function) =>
                        {
                            function.Return("this.value");
                        });
                });
        }

        private void EnumValue(JavaBlock enumBlock, JavaEnumValue value, bool isLast = false)
        {
            enumBlock.SingleLineComment($"Enum value {value.Value}.")
                .Line($"{value.Name}(\"{value.Value}\")" + (isLast ? ";" : ","))
                .Line();
        }
    }
}
