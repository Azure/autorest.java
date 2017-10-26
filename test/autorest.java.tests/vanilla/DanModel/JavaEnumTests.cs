using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace AutoRest.Java.DanModel
{
    [TestClass]
    public class JavaEnumTests
    {
        [TestMethod]
        public void WithNoValues()
        {
            JavaEnum javaEnum = GetJavaEnum();
            JavaFile javaFile = javaEnum.GenerateJavaFile();

            string expectedFilePath = "models\\MyEnum.java";
            string actualFilePath = javaFile.FilePath;
            Assert.AreEqual(expectedFilePath, actualFilePath);

            JavaFileContents expectedContents = new JavaFileContents()
                .Line("/**")
                .Line(" * MOCK")
                .Text(" * HEADER\r\n")
                .Line(" * COMMENT")
                .Line(" * TEXT")
                .Line(" */")
                .Line()
                .Line("package mock.package;")
                .Line()
                .Line("import com.fasterxml.jackson.annotation.JsonCreator;")
                .Line("import com.fasterxml.jackson.annotation.JsonValue;")
                .Line()
                .Line("/**")
                .Line(" * Defines values for MyEnum.")
                .Line(" */")
                .Line("public enum MyEnum {")
                .Line("    /** The actual serialized value for a MyEnum instance. */")
                .Line("    private String value;")
                .Line()
                .Line("    MyEnum(String value) {")
                .Line("        this.value = value;")
                .Line("    }")
                .Line()
                .Line("    /**")
                .Line("     * Parses a serialized value to a MyEnum instance.")
                .Line("     * ")
                .Line("     * @param value the serialized value to parse.")
                .Line("     * @return the parsed MyEnum object, or null if unable to parse.")
                .Line("     */")
                .Line("    @JsonCreator")
                .Line("    public static MyEnum fromString(String value) {")
                .Line("        MyEnum[] items = MyEnum.values();")
                .Line("        for (MyEnum item : items) {")
                .Line("            if (item.toString().equalsIgnoreCase(value)) {")
                .Line("                return item;")
                .Line("            }")
                .Line("        }")
                .Line("        return null;")
                .Line("    }")
                .Line()
                .Line("    @JsonValue")
                .Line("    @Override")
                .Line("    public String toString() {")
                .Line("        return this.value;")
                .Line("    }")
                .Line("}");
            JavaFileContents actualContents = javaFile.Contents;
            JavaFileContentsAssert.AreEqual(expectedContents, actualContents);
        }

        [TestMethod]
        public void WithOneValue()
        {
            JavaEnum javaEnum = GetJavaEnum(
                new JavaEnumValue("Grapes", "Yummy"));

            JavaFile javaFile = javaEnum.GenerateJavaFile();

            string expectedFilePath = "models\\MyEnum.java";
            string actualFilePath = javaFile.FilePath;
            Assert.AreEqual(expectedFilePath, actualFilePath);

            JavaFileContents expectedContents = new JavaFileContents()
                .Line("/**")
                .Line(" * MOCK")
                .Text(" * HEADER\r\n")
                .Line(" * COMMENT")
                .Line(" * TEXT")
                .Line(" */")
                .Line()
                .Line("package mock.package;")
                .Line()
                .Line("import com.fasterxml.jackson.annotation.JsonCreator;")
                .Line("import com.fasterxml.jackson.annotation.JsonValue;")
                .Line()
                .Line("/**")
                .Line(" * Defines values for MyEnum.")
                .Line(" */")
                .Line("public enum MyEnum {")
                .Line("    /** Enum value Yummy. */")
                .Line("    Grapes(\"Yummy\");")
                .Line()
                .Line("    /** The actual serialized value for a MyEnum instance. */")
                .Line("    private String value;")
                .Line()
                .Line("    MyEnum(String value) {")
                .Line("        this.value = value;")
                .Line("    }")
                .Line()
                .Line("    /**")
                .Line("     * Parses a serialized value to a MyEnum instance.")
                .Line("     * ")
                .Line("     * @param value the serialized value to parse.")
                .Line("     * @return the parsed MyEnum object, or null if unable to parse.")
                .Line("     */")
                .Line("    @JsonCreator")
                .Line("    public static MyEnum fromString(String value) {")
                .Line("        MyEnum[] items = MyEnum.values();")
                .Line("        for (MyEnum item : items) {")
                .Line("            if (item.toString().equalsIgnoreCase(value)) {")
                .Line("                return item;")
                .Line("            }")
                .Line("        }")
                .Line("        return null;")
                .Line("    }")
                .Line()
                .Line("    @JsonValue")
                .Line("    @Override")
                .Line("    public String toString() {")
                .Line("        return this.value;")
                .Line("    }")
                .Line("}");
            JavaFileContents actualContents = javaFile.Contents;
            JavaFileContentsAssert.AreEqual(expectedContents, actualContents);
        }

        [TestMethod]
        public void WithMultipleValues()
        {
            JavaEnum javaEnum = GetJavaEnum(
                new JavaEnumValue("A", "B"),
                new JavaEnumValue("C", "D"),
                new JavaEnumValue("E", "F"));

            JavaFile javaFile = javaEnum.GenerateJavaFile();

            string expectedFilePath = "models\\MyEnum.java";
            string actualFilePath = javaFile.FilePath;
            Assert.AreEqual(expectedFilePath, actualFilePath);

            JavaFileContents expectedContents = new JavaFileContents()
                .Line("/**")
                .Line(" * MOCK")
                .Text(" * HEADER\r\n")
                .Line(" * COMMENT")
                .Line(" * TEXT")
                .Line(" */")
                .Line()
                .Line("package mock.package;")
                .Line()
                .Line("import com.fasterxml.jackson.annotation.JsonCreator;")
                .Line("import com.fasterxml.jackson.annotation.JsonValue;")
                .Line()
                .Line("/**")
                .Line(" * Defines values for MyEnum.")
                .Line(" */")
                .Line("public enum MyEnum {")
                .Line("    /** Enum value B. */")
                .Line("    A(\"B\"),")
                .Line()
                .Line("    /** Enum value D. */")
                .Line("    C(\"D\"),")
                .Line()
                .Line("    /** Enum value F. */")
                .Line("    E(\"F\");")
                .Line()
                .Line("    /** The actual serialized value for a MyEnum instance. */")
                .Line("    private String value;")
                .Line()
                .Line("    MyEnum(String value) {")
                .Line("        this.value = value;")
                .Line("    }")
                .Line()
                .Line("    /**")
                .Line("     * Parses a serialized value to a MyEnum instance.")
                .Line("     * ")
                .Line("     * @param value the serialized value to parse.")
                .Line("     * @return the parsed MyEnum object, or null if unable to parse.")
                .Line("     */")
                .Line("    @JsonCreator")
                .Line("    public static MyEnum fromString(String value) {")
                .Line("        MyEnum[] items = MyEnum.values();")
                .Line("        for (MyEnum item : items) {")
                .Line("            if (item.toString().equalsIgnoreCase(value)) {")
                .Line("                return item;")
                .Line("            }")
                .Line("        }")
                .Line("        return null;")
                .Line("    }")
                .Line()
                .Line("    @JsonValue")
                .Line("    @Override")
                .Line("    public String toString() {")
                .Line("        return this.value;")
                .Line("    }")
                .Line("}");
            JavaFileContents actualContents = javaFile.Contents;
            JavaFileContentsAssert.AreEqual(expectedContents, actualContents);
        }

        private static JavaEnum GetJavaEnum(params JavaEnumValue[] values)
        {
            return new JavaEnum("MOCK\nHEADER\r\nCOMMENT\nTEXT", "mock.package", "MyEnum", values);
        }
    }
}
