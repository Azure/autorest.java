using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace AutoRest.Java.DanModel
{
    public class JavaEnum
    {
        public const string RelativePackage = "models";

        private readonly IList<JavaEnumValue> values;

        public JavaEnum(string enumName)
        {
            EnumName = enumName;
            values = new List<JavaEnumValue>();
        }

        public JavaEnum AddValue(string valueName, string valueString)
        {
            values.Add(new JavaEnumValue(valueName, valueString));
            return this;
        }

        protected IEnumerable<JavaEnumValue> Values
        {
            get { return values; }
        }

        protected string EnumName { get; }

        protected string GetFilePath()
        {
            return Path.Combine(RelativePackage, $"{EnumName}.java");
        }

        protected Action<JavaMultipleLineComment> EnumTypeComment()
        {
            return (JavaMultipleLineComment comment) =>
            {
                comment.Line($"Defines values for {EnumName}.");
            };
        }

        public virtual JavaFile GenerateJavaFile(string headerComment, string package, int maximumMultipleLineCommentWidth)
        {
            return new JavaFile(GetFilePath(), headerComment, package, maximumMultipleLineCommentWidth)
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
