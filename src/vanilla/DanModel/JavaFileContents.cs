using AutoRest.Core.Utilities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.DanModel
{
    public class JavaFileContents
    {
        private const string singleIndent = "    ";

        private readonly StringBuilder contents = new StringBuilder();
        private readonly StringBuilder linePrefix = new StringBuilder();

        private int? wordWrapWidth;

        public override string ToString()
        {
            return contents.ToString();
        }

        public string[] Lines
        {
            get { return ToString().Split("\n"); }
        }

        public JavaFileContents AddToPrefix(string toAdd)
        {
            linePrefix.Append(toAdd);
            return this;
        }

        private JavaFileContents RemoveFromPrefix(string toRemove)
        {
            int toRemoveLength = toRemove.Length;
            if (linePrefix.Length <= toRemoveLength)
            {
                linePrefix.Clear();
            }
            else
            {
                linePrefix.Remove(linePrefix.Length - toRemoveLength, toRemoveLength);
            }
            return this;
        }

        public JavaFileContents SetWordWrapIndex(int? wordWrapIndex)
        {
            this.wordWrapWidth = wordWrapIndex;
            return this;
        }

        private JavaFileContents WithWordWrap(int wordWrapIndex, Action action)
        {
            SetWordWrapIndex(wordWrapIndex);
            action.Invoke();
            SetWordWrapIndex(null);

            return this;
        }

        public JavaFileContents Indent(Action action)
        {
            AddToPrefix(singleIndent);
            action.Invoke();
            RemoveFromPrefix(singleIndent);
            return this;
        }

        private IEnumerable<string> WordWrap(string line)
        {
            List<string> lines = new List<string>();

            if (wordWrapWidth == null)
            {
                lines.Add(line);
            }
            else
            {
                // Subtract an extra column from the word wrap width because columns generally are
                // 1 -based instead of 0-based.
                int wordWrapIndexMinusLinePrefixLength = wordWrapWidth.Value - linePrefix.Length - 1;
                IEnumerable<string> wrappedLines = line.WordWrap(wordWrapIndexMinusLinePrefixLength);
                foreach (string wrappedLine in wrappedLines.SkipLast(1))
                {
                    lines.Add(wrappedLine + "\n");
                }

                string lastWrappedLine = wrappedLines.Last();
                if (!string.IsNullOrEmpty(lastWrappedLine))
                {
                    lines.Add(lastWrappedLine);
                }
            }

            return lines;
        }

        public JavaFileContents Text(string text)
        {
            List<string> lines = new List<string>();

            if (string.IsNullOrEmpty(text))
            {
                lines.Add("");
            }
            else
            {
                int lineStartIndex = 0;
                int textLength = text.Length;
                while (lineStartIndex < textLength)
                {
                    int newLineCharacterIndex = text.IndexOf('\n', lineStartIndex);
                    if (newLineCharacterIndex == -1)
                    {
                        string line = text.Substring(lineStartIndex);
                        IEnumerable<string> wrappedLines = WordWrap(line);
                        lines.AddRange(wrappedLines);
                        lineStartIndex = textLength;
                    }
                    else
                    {
                        int nextLineStartIndex = newLineCharacterIndex + 1;
                        string line = text.Substring(lineStartIndex, nextLineStartIndex - lineStartIndex);
                        IEnumerable<string> wrappedLines = WordWrap(line);
                        lines.AddRange(wrappedLines);
                        lineStartIndex = nextLineStartIndex;
                    }
                }
            }

            string prefix = linePrefix.ToString();
            foreach (string line in lines)
            {
                if (!string.IsNullOrWhiteSpace(prefix) || (!string.IsNullOrEmpty(prefix) && !string.IsNullOrWhiteSpace(line)))
                {
                    contents.Append(prefix);
                }

                contents.Append(line);
            }

            return this;
        }

        public JavaFileContents Line(string text)
        {
            return Text($"{text}\n");
        }

        public JavaFileContents Line()
        {
            return Line("");
        }

        public JavaFileContents Package(string package)
        {
            return Line($"package {package};");
        }

        public JavaFileContents Block(string text, Action<JavaBlock> bodyAction)
        {
            return Line($"{text} {{")
                  .Indent(() =>
                  {
                      bodyAction.Invoke(new JavaBlock(this));
                  })
                  .Line("}");
        }

        public JavaFileContents Import(params string[] imports)
        {
            return Import((IEnumerable<string>)imports);
        }

        public JavaFileContents Import(IEnumerable<string> imports)
        {
            if (imports != null && imports.Any())
            {
                ISet<string> importSet = new SortedSet<string>(imports, new JavaImportComparer());
                foreach (string import in importSet)
                {
                    if (!string.IsNullOrEmpty(import))
                    {
                        Line($"import {import};");
                    }
                }
                Line();
            }
            return this;
        }

        public JavaFileContents SingleLineComment(string text)
        {
            return Line($"/** {text} */");
        }

        public JavaFileContents MultipleLineComment(Action<JavaMultipleLineComment> commentAction)
        {
            Line("/**");
            AddToPrefix(" * ");
            commentAction.Invoke(new JavaMultipleLineComment(this));
            RemoveFromPrefix(" * ");
            return Line(" */");
        }

        public JavaFileContents WordWrappedMultipleLineComment(int wordWrapWidth, Action<JavaWordWrappedMultipleLineComment> commentAction)
        {
            return MultipleLineComment((comment) =>
            {
                WithWordWrap(wordWrapWidth, () =>
                {
                    commentAction.Invoke(new JavaWordWrappedMultipleLineComment(this));
                });
            });
        }

        public JavaFileContents Return(string text)
        {
            return Line($"return {text};");
        }

        public JavaFileContents Annotation(params string[] annotations)
        {
            return Annotation((IEnumerable<string>)annotations);
        }

        public JavaFileContents Annotation(IEnumerable<string> annotations)
        {
            if (annotations != null && annotations.Any())
            {
                foreach (string annotation in annotations)
                {
                    if (!string.IsNullOrEmpty(annotation))
                    {
                        Line($"@{annotation}");
                    }
                }
            }
            return this;
        }

        public JavaFileContents PublicFinalClass(string className, Action<JavaClass> classAction)
        {
            Block($"public final class {className}", (blockAction) =>
            {
                if (classAction != null)
                {
                    JavaClass javaClass = new JavaClass(this);
                    classAction.Invoke(javaClass);
                }
            });
            return this;
        }

        public JavaFileContents PublicClass(string className, Action<JavaClass> classAction)
        {
            Block($"public class {className}", (blockAction) =>
            {
                if (classAction != null)
                {
                    JavaClass javaClass = new JavaClass(this);
                    classAction.Invoke(javaClass);
                }
            });
            return this;
        }

        public JavaFileContents PublicEnum(string enumName, Action<JavaBlock> enumAction)
        {
            Block($"public enum {enumName}", enumAction);
            return this;
        }

        public JavaFileContents CommentParam(string parameterName, string parameterDescription)
        {
            Line($"@param {parameterName} {parameterDescription}");
            return this;
        }

        public JavaFileContents CommentReturn(string returnValueDescription)
        {
            Line($"@return {returnValueDescription}");
            return this;
        }
    }
}
