using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.DanModel
{
    [TestClass]
    public class DanCodeGeneratorTests
    {
        [TestMethod]
        public void GetFilesWithNullCodeModel()
        {
            IEnumerable<JavaFile> files = DanCodeGenerator.GetFiles(null, null);
            Assert.IsNotNull(files);
            Assert.AreEqual(0, files.Count());
        }
    }
}
