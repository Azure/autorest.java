using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;

namespace AutoRest.Java.DanModel
{
    public static class JavaFileContentsAssert
    {
        public static void AreEqual(JavaFileContents expected, JavaFileContents actual)
        {
            if (expected != actual)
            {
                Assert.IsNotNull(expected, "expected was null, but actual was not null.");
                Assert.IsNotNull(actual, "actual was null but expected was not null.");

                string[] expectedLines = expected.Lines;
                string[] actualLines = actual.Lines;

                int commonLineCount = Math.Min(expectedLines.Length, actualLines.Length);
                for (int i = 0; i < commonLineCount; ++i)
                {
                    string expectedLine = expectedLines[i];
                    string actualLine = actualLines[i];
                    if (!expectedLine.Equals(actualLine))
                    {
                        int commonLineLength = Math.Min(expectedLine.Length, actualLine.Length);

                        int differentIndex;
                        for (differentIndex = 0; differentIndex < commonLineLength; ++differentIndex)
                        {
                            if (expectedLine[differentIndex] != actualLine[differentIndex])
                            {
                                break;
                            }
                        }

                        Assert.Fail(
                            $"Lines {i + 1} don't match at character {differentIndex + 1}:\n" +
                            $"expected: \"{expectedLine}\"\n" +
                            $"actual:   \"{actualLine}\"");
                    }
                }

                if (expectedLines.Length != actualLines.Length)
                {
                    Assert.Fail(
                        $"Line counts don't match:\n" +
                        $"expected: {expectedLines.Length}\n" +
                        $"actual:   {actualLines.Length}");
                }
            }
        }
    }
}
