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

        private int? maximumMultipleLineCommentWidth;
        private int? wordWrapIndex;

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

        public JavaFileContents MaximumMultipleLineCommentWidth(int? maximumMultipleLineCommentWidth)
        {
            this.maximumMultipleLineCommentWidth = maximumMultipleLineCommentWidth;
            return this;
        }

        public JavaFileContents SetWordWrapIndex(int? wordWrapIndex)
        {
            this.wordWrapIndex = wordWrapIndex;
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

            if (wordWrapIndex == null)
            {
                lines.Add(line);
            }
            else
            {
                int wordWrapIndexMinusLinePrefixLength = wordWrapIndex.Value - linePrefix.Length;
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
            ISet<string> importSet = new SortedSet<string>(imports);
            foreach (string import in importSet)
            {
                Line($"import {import};");
            }
            Line();
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
            SetWordWrapIndex(maximumMultipleLineCommentWidth);
            commentAction.Invoke(new JavaMultipleLineComment(this));
            SetWordWrapIndex(null);
            RemoveFromPrefix(" * ");
            return Line(" */");
        }

        public JavaFileContents Return(string text)
        {
            return Line($"return {text};");
        }
    }
}
