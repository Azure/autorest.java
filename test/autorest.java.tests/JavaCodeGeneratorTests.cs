using AutoRest.Java.Model;
using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace AutoRest.Java
{
    [TestClass]
    public class JavaCodeGeneratorTests
    {
        [TestMethod]
        public void ParseEnumValue()
        {
            ServiceEnumValue enumValue = CodeGeneratorJv.ParseEnumValue("apples", "sellpa");
            Assert.AreEqual("APPLES", enumValue.Name);
            Assert.AreEqual("sellpa", enumValue.Value);
        }

        [TestMethod]
        public void ParseEnumValueWithSpace()
        {
            ServiceEnumValue enumValue = CodeGeneratorJv.ParseEnumValue("app les", "sel lpa");
            Assert.AreEqual("APP_LES", enumValue.Name);
            Assert.AreEqual("sel lpa", enumValue.Value);
        }

        [TestMethod]
        public void ParseEnumValueWithPeriod()
        {
            ServiceEnumValue enumValue = CodeGeneratorJv.ParseEnumValue("app.les", "sel.lpa");
            Assert.AreEqual("APP_LES", enumValue.Name);
            Assert.AreEqual("sel.lpa", enumValue.Value);
        }

        [TestMethod]
        public void ParseEnumValueWithBackslash()
        {
            ServiceEnumValue enumValue = CodeGeneratorJv.ParseEnumValue("app\\les", "sel\\lpa");
            Assert.AreEqual("APP_LES", enumValue.Name);
            Assert.AreEqual("sel\\lpa", enumValue.Value);
        }

        [TestMethod]
        public void ParseEnumValueWithForwardSlash()
        {
            ServiceEnumValue enumValue = CodeGeneratorJv.ParseEnumValue("app/les", "sel/lpa");
            Assert.AreEqual("APP_LES", enumValue.Name);
            Assert.AreEqual("sel/lpa", enumValue.Value);
        }

        [TestMethod]
        public void ParseEnumValueWithDash()
        {
            ServiceEnumValue enumValue = CodeGeneratorJv.ParseEnumValue("app-les", "sel-lpa");
            Assert.AreEqual("APP_LES", enumValue.Name);
            Assert.AreEqual("sel-lpa", enumValue.Value);
        }

        [TestMethod]
        public void ParseEnumValueWithPlus()
        {
            ServiceEnumValue enumValue = CodeGeneratorJv.ParseEnumValue("app+les", "sel+lpa");
            Assert.AreEqual("APP_LES", enumValue.Name);
            Assert.AreEqual("sel+lpa", enumValue.Value);
        }

        [TestMethod]
        public void ParseEnumValueWithUnderscore()
        {
            ServiceEnumValue enumValue = CodeGeneratorJv.ParseEnumValue("app_les", "sel_lpa");
            Assert.AreEqual("APP_LES", enumValue.Name);
            Assert.AreEqual("sel_lpa", enumValue.Value);
        }

        [TestMethod]
        public void ParseEnumValueWithCamelCase()
        {
            ServiceEnumValue enumValue = CodeGeneratorJv.ParseEnumValue("camelCase", "camelCase");
            Assert.AreEqual("CAMEL_CASE", enumValue.Name);
            Assert.AreEqual("camelCase", enumValue.Value);
        }

        [TestMethod]
        public void ParseEnumValueWithPascalCase()
        {
            ServiceEnumValue enumValue = CodeGeneratorJv.ParseEnumValue("PascalCase", "PascalCase");
            Assert.AreEqual("PASCAL_CASE", enumValue.Name);
            Assert.AreEqual("PascalCase", enumValue.Value);
        }
    }
}
