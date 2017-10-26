using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace AutoRest.Java.DanModel
{
    [TestClass]
    public class JavaExpandableStringEnumTests
    {
        [TestMethod]
        public void WithNoValues()
        {
            JavaExpandableStringEnum javaEnum = GetJavaExpandableStringEnum();
            JavaFile javaFile = javaEnum.GenerateJavaFile();

            string expectedFilePath = "models\\MyEnum.java";
            string actualFilePath = javaFile.FilePath;
            Assert.AreEqual(expectedFilePath, actualFilePath);

            JavaFileContents expectedContents = new JavaFileContents()
                .Line("/**")
                .Line(" * MOCK HEADER COMMENT TEXT")
                .Line(" */")
                .Line()
                .Line("package mock.package;")
                .Line()
                .Line("import com.fasterxml.jackson.annotation.JsonCreator;")
                .Line("import com.microsoft.rest.ExpandableStringEnum;")
                .Line("import java.util.Collection;")
                .Line()
                .Line("/**")
                .Line(" * Defines values for MyEnum.")
                .Line(" */")
                .Line("public final class MyEnum extends ExpandableStringEnum<MyEnum> {")
                .Line("    /**")
                .Line("     * Creates or finds a MyEnum from its string representation.")
                .Line("     * @param name a name to look for")
                .Line("     * @return the corresponding MyEnum")
                .Line("     */")
                .Line("    @JsonCreator")
                .Line("    public static MyEnum fromString(String name) {")
                .Line("        return fromString(name, MyEnum.class);")
                .Line("    }")
                .Line()
                .Line("    /**")
                .Line("     * @return known MyEnum values")
                .Line("     */")
                .Line("    public static Collection<MyEnum> values() {")
                .Line("        return values(MyEnum.class);")
                .Line("    }")
                .Line("}");
            JavaFileContents actualContents = javaFile.Contents;
            JavaFileContentsAssert.AreEqual(expectedContents, actualContents);
        }

        [TestMethod]
        public void WithOneValue()
        {
            JavaExpandableStringEnum javaEnum = GetJavaExpandableStringEnum();
            javaEnum.AddValue("Grapes", "Yummy");

            JavaFile javaFile = javaEnum.GenerateJavaFile();

            string expectedFilePath = "models\\MyEnum.java";
            string actualFilePath = javaFile.FilePath;
            Assert.AreEqual(expectedFilePath, actualFilePath);

            JavaFileContents expectedContents = new JavaFileContents()
                .Line("/**")
                .Line(" * MOCK HEADER COMMENT TEXT")
                .Line(" */")
                .Line()
                .Line("package mock.package;")
                .Line()
                .Line("import com.fasterxml.jackson.annotation.JsonCreator;")
                .Line("import com.microsoft.rest.ExpandableStringEnum;")
                .Line("import java.util.Collection;")
                .Line()
                .Line("/**")
                .Line(" * Defines values for MyEnum.")
                .Line(" */")
                .Line("public final class MyEnum extends ExpandableStringEnum<MyEnum> {")
                .Line("    /** Static value Yummy for MyEnum. */")
                .Line("    public static final MyEnum Grapes = fromString(\"Yummy\");")
                .Line()
                .Line("    /**")
                .Line("     * Creates or finds a MyEnum from its string representation.")
                .Line("     * @param name a name to look for")
                .Line("     * @return the corresponding MyEnum")
                .Line("     */")
                .Line("    @JsonCreator")
                .Line("    public static MyEnum fromString(String name) {")
                .Line("        return fromString(name, MyEnum.class);")
                .Line("    }")
                .Line()
                .Line("    /**")
                .Line("     * @return known MyEnum values")
                .Line("     */")
                .Line("    public static Collection<MyEnum> values() {")
                .Line("        return values(MyEnum.class);")
                .Line("    }")
                .Line("}");
            JavaFileContents actualContents = javaFile.Contents;
            JavaFileContentsAssert.AreEqual(expectedContents, actualContents);
        }

        [TestMethod]
        public void WithMultipleValues()
        {
            JavaExpandableStringEnum javaEnum = GetJavaExpandableStringEnum();
            javaEnum.AddValue("A", "B");
            javaEnum.AddValue("C", "D");
            javaEnum.AddValue("E", "F");

            JavaFile javaFile = javaEnum.GenerateJavaFile();

            string expectedFilePath = "models\\MyEnum.java";
            string actualFilePath = javaFile.FilePath;
            Assert.AreEqual(expectedFilePath, actualFilePath);

            JavaFileContents expectedContents = new JavaFileContents()
                .Line("/**")
                .Line(" * MOCK HEADER COMMENT TEXT")
                .Line(" */")
                .Line()
                .Line("package mock.package;")
                .Line()
                .Line("import com.fasterxml.jackson.annotation.JsonCreator;")
                .Line("import com.microsoft.rest.ExpandableStringEnum;")
                .Line("import java.util.Collection;")
                .Line()
                .Line("/**")
                .Line(" * Defines values for MyEnum.")
                .Line(" */")
                .Line("public final class MyEnum extends ExpandableStringEnum<MyEnum> {")
                .Line("    /** Static value B for MyEnum. */")
                .Line("    public static final MyEnum A = fromString(\"B\");")
                .Line()
                .Line("    /** Static value D for MyEnum. */")
                .Line("    public static final MyEnum C = fromString(\"D\");")
                .Line()
                .Line("    /** Static value F for MyEnum. */")
                .Line("    public static final MyEnum E = fromString(\"F\");")
                .Line()
                .Line("    /**")
                .Line("     * Creates or finds a MyEnum from its string representation.")
                .Line("     * @param name a name to look for")
                .Line("     * @return the corresponding MyEnum")
                .Line("     */")
                .Line("    @JsonCreator")
                .Line("    public static MyEnum fromString(String name) {")
                .Line("        return fromString(name, MyEnum.class);")
                .Line("    }")
                .Line()
                .Line("    /**")
                .Line("     * @return known MyEnum values")
                .Line("     */")
                .Line("    public static Collection<MyEnum> values() {")
                .Line("        return values(MyEnum.class);")
                .Line("    }")
                .Line("}");
            JavaFileContents actualContents = javaFile.Contents;
            JavaFileContentsAssert.AreEqual(expectedContents, actualContents);
        }

        private static JavaExpandableStringEnum GetJavaExpandableStringEnum()
        {
            return new JavaExpandableStringEnum("MOCK HEADER COMMENT TEXT", "mock.package", "MyEnum");
        }
    }
}
