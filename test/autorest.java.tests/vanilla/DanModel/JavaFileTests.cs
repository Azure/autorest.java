using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace AutoRest.Java.DanModel
{
    [TestClass]
    public class JavaFileTests
    {
        [TestMethod]
        public void ConstructorWithNullFilePath()
        {
            JavaFile file = new JavaFile(null);
            Assert.IsNull(file.FilePath);
            Assert.AreEqual("", file.GetContents());
        }

        [TestMethod]
        public void ConstructorWithEmptyFilePath()
        {
            JavaFile file = new JavaFile("");
            Assert.AreEqual("", file.FilePath);
            Assert.AreEqual("", file.GetContents());
        }

        [TestMethod]
        public void ConstructorWithNonEmptyFilePath()
        {
            JavaFile file = new JavaFile("hello.java");
            Assert.AreEqual("hello.java", file.FilePath);
            Assert.AreEqual("", file.GetContents());
        }

        [TestMethod]
        public void AddWithNullText()
        {
            JavaFile file = createJavaFile();

            file.Add(null);

            Assert.AreEqual("", file.GetContents());
        }

        [TestMethod]
        public void AddWithEmptyText()
        {
            JavaFile file = createJavaFile();

            file.Add("");

            Assert.AreEqual("", file.GetContents());
        }

        [TestMethod]
        public void AddWithNonEmptyText()
        {
            JavaFile file = createJavaFile();

            file.Add("testing, 1, 2, 3");

            Assert.AreEqual("testing, 1, 2, 3", file.GetContents());
        }

        [TestMethod]
        public void AddLineWithNoArguments()
        {
            JavaFile file = createJavaFile();

            file.AddLine();

            Assert.AreEqual("\n", file.GetContents());
        }

        [TestMethod]
        public void AddLineWithNull()
        {
            JavaFile file = createJavaFile();

            file.AddLine(null);

            Assert.AreEqual("\n", file.GetContents());
        }

        [TestMethod]
        public void AddLineWithEmpty()
        {
            JavaFile file = createJavaFile();

            file.AddLine("");

            Assert.AreEqual("\n", file.GetContents());
        }

        [TestMethod]
        public void AddLineWithNonEmpty()
        {
            JavaFile file = createJavaFile();

            file.AddLine("12345");

            Assert.AreEqual("12345\n", file.GetContents());
        }

        private static JavaFile createJavaFile()
        {
            return new JavaFile("hello.java");
        }
    }
}
