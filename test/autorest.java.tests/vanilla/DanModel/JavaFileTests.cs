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
            Assert.AreEqual("", file.Contents.ToString());
        }

        [TestMethod]
        public void ConstructorWithEmptyFilePath()
        {
            JavaFile file = new JavaFile("");
            Assert.AreEqual("", file.FilePath);
            Assert.AreEqual("", file.Contents.ToString());
        }

        [TestMethod]
        public void ConstructorWithNonEmptyFilePath()
        {
            JavaFile file = new JavaFile("hello.java");
            Assert.AreEqual("hello.java", file.FilePath);
            Assert.AreEqual("", file.Contents.ToString());
        }

        [TestMethod]
        public void AddWithNullText()
        {
            JavaFile file = createJavaFile();

            file.Text(null);

            Assert.AreEqual("", file.Contents.ToString());
        }

        [TestMethod]
        public void AddWithEmptyText()
        {
            JavaFile file = createJavaFile();

            file.Text("");

            Assert.AreEqual("", file.Contents.ToString());
        }

        [TestMethod]
        public void AddWithNonEmptyText()
        {
            JavaFile file = createJavaFile();

            file.Text("testing, 1, 2, 3");

            Assert.AreEqual("testing, 1, 2, 3", file.Contents.ToString());
        }

        [TestMethod]
        public void LineWithNoArguments()
        {
            JavaFile file = createJavaFile();

            file.Line();

            Assert.AreEqual("\n", file.Contents.ToString());
        }

        [TestMethod]
        public void LineWithNull()
        {
            JavaFile file = createJavaFile();

            file.Line(null);

            Assert.AreEqual("\n", file.Contents.ToString());
        }

        [TestMethod]
        public void LineWithEmpty()
        {
            JavaFile file = createJavaFile();

            file.Line("");

            Assert.AreEqual("\n", file.Contents.ToString());
        }

        [TestMethod]
        public void LineWithNonEmpty()
        {
            JavaFile file = createJavaFile();

            file.Line("12345");

            Assert.AreEqual("12345\n", file.Contents.ToString());
        }

        private static JavaFile createJavaFile()
        {
            return new JavaFile("hello.java");
        }
    }
}
