package com.azure.autorest.model.codemodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * custom extensible metadata for individual language generators
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "default",
    "csharp",
    "python",
    "ruby",
    "go",
    "typescript",
    "javascript",
    "powershell",
    "java",
    "c",
    "cpp",
    "swift",
    "objectivec"
})
public class LanguagesSchemaMetadata {

    /**
     * language metadata specific to schema instances
     * (Required)
     * 
     */
    @JsonProperty("default")
    @JsonPropertyDescription("language metadata specific to schema instances")
    private SchemaMetadata _default;
    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("csharp")
    @JsonPropertyDescription("language metadata specific to schema instances")
    private SchemaMetadata csharp;
    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("python")
    @JsonPropertyDescription("language metadata specific to schema instances")
    private SchemaMetadata python;
    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("ruby")
    @JsonPropertyDescription("language metadata specific to schema instances")
    private SchemaMetadata ruby;
    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("go")
    @JsonPropertyDescription("language metadata specific to schema instances")
    private SchemaMetadata go;
    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("typescript")
    @JsonPropertyDescription("language metadata specific to schema instances")
    private SchemaMetadata typescript;
    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("javascript")
    @JsonPropertyDescription("language metadata specific to schema instances")
    private SchemaMetadata javascript;
    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("powershell")
    @JsonPropertyDescription("language metadata specific to schema instances")
    private SchemaMetadata powershell;
    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("java")
    @JsonPropertyDescription("language metadata specific to schema instances")
    private SchemaMetadata java;
    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("c")
    @JsonPropertyDescription("language metadata specific to schema instances")
    private SchemaMetadata c;
    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("cpp")
    @JsonPropertyDescription("language metadata specific to schema instances")
    private SchemaMetadata cpp;
    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("swift")
    @JsonPropertyDescription("language metadata specific to schema instances")
    private SchemaMetadata swift;
    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("objectivec")
    @JsonPropertyDescription("language metadata specific to schema instances")
    private SchemaMetadata objectivec;

    /**
     * language metadata specific to schema instances
     * (Required)
     * 
     */
    @JsonProperty("default")
    public SchemaMetadata getDefault() {
        return _default;
    }

    /**
     * language metadata specific to schema instances
     * (Required)
     * 
     */
    @JsonProperty("default")
    public void setDefault(SchemaMetadata _default) {
        this._default = _default;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("csharp")
    public SchemaMetadata getCsharp() {
        return csharp;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("csharp")
    public void setCsharp(SchemaMetadata csharp) {
        this.csharp = csharp;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("python")
    public SchemaMetadata getPython() {
        return python;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("python")
    public void setPython(SchemaMetadata python) {
        this.python = python;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("ruby")
    public SchemaMetadata getRuby() {
        return ruby;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("ruby")
    public void setRuby(SchemaMetadata ruby) {
        this.ruby = ruby;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("go")
    public SchemaMetadata getGo() {
        return go;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("go")
    public void setGo(SchemaMetadata go) {
        this.go = go;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("typescript")
    public SchemaMetadata getTypescript() {
        return typescript;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("typescript")
    public void setTypescript(SchemaMetadata typescript) {
        this.typescript = typescript;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("javascript")
    public SchemaMetadata getJavascript() {
        return javascript;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("javascript")
    public void setJavascript(SchemaMetadata javascript) {
        this.javascript = javascript;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("powershell")
    public SchemaMetadata getPowershell() {
        return powershell;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("powershell")
    public void setPowershell(SchemaMetadata powershell) {
        this.powershell = powershell;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("java")
    public SchemaMetadata getJava() {
        return java;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("java")
    public void setJava(SchemaMetadata java) {
        this.java = java;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("c")
    public SchemaMetadata getC() {
        return c;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("c")
    public void setC(SchemaMetadata c) {
        this.c = c;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("cpp")
    public SchemaMetadata getCpp() {
        return cpp;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("cpp")
    public void setCpp(SchemaMetadata cpp) {
        this.cpp = cpp;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("swift")
    public SchemaMetadata getSwift() {
        return swift;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("swift")
    public void setSwift(SchemaMetadata swift) {
        this.swift = swift;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("objectivec")
    public SchemaMetadata getObjectivec() {
        return objectivec;
    }

    /**
     * language metadata specific to schema instances
     * 
     */
    @JsonProperty("objectivec")
    public void setObjectivec(SchemaMetadata objectivec) {
        this.objectivec = objectivec;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(LanguagesSchemaMetadata.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("_default");
        sb.append('=');
        sb.append(((this._default == null)?"<null>":this._default));
        sb.append(',');
        sb.append("csharp");
        sb.append('=');
        sb.append(((this.csharp == null)?"<null>":this.csharp));
        sb.append(',');
        sb.append("python");
        sb.append('=');
        sb.append(((this.python == null)?"<null>":this.python));
        sb.append(',');
        sb.append("ruby");
        sb.append('=');
        sb.append(((this.ruby == null)?"<null>":this.ruby));
        sb.append(',');
        sb.append("go");
        sb.append('=');
        sb.append(((this.go == null)?"<null>":this.go));
        sb.append(',');
        sb.append("typescript");
        sb.append('=');
        sb.append(((this.typescript == null)?"<null>":this.typescript));
        sb.append(',');
        sb.append("javascript");
        sb.append('=');
        sb.append(((this.javascript == null)?"<null>":this.javascript));
        sb.append(',');
        sb.append("powershell");
        sb.append('=');
        sb.append(((this.powershell == null)?"<null>":this.powershell));
        sb.append(',');
        sb.append("java");
        sb.append('=');
        sb.append(((this.java == null)?"<null>":this.java));
        sb.append(',');
        sb.append("c");
        sb.append('=');
        sb.append(((this.c == null)?"<null>":this.c));
        sb.append(',');
        sb.append("cpp");
        sb.append('=');
        sb.append(((this.cpp == null)?"<null>":this.cpp));
        sb.append(',');
        sb.append("swift");
        sb.append('=');
        sb.append(((this.swift == null)?"<null>":this.swift));
        sb.append(',');
        sb.append("objectivec");
        sb.append('=');
        sb.append(((this.objectivec == null)?"<null>":this.objectivec));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this._default == null)? 0 :this._default.hashCode()));
        result = ((result* 31)+((this.python == null)? 0 :this.python.hashCode()));
        result = ((result* 31)+((this.cpp == null)? 0 :this.cpp.hashCode()));
        result = ((result* 31)+((this.c == null)? 0 :this.c.hashCode()));
        result = ((result* 31)+((this.go == null)? 0 :this.go.hashCode()));
        result = ((result* 31)+((this.objectivec == null)? 0 :this.objectivec.hashCode()));
        result = ((result* 31)+((this.javascript == null)? 0 :this.javascript.hashCode()));
        result = ((result* 31)+((this.ruby == null)? 0 :this.ruby.hashCode()));
        result = ((result* 31)+((this.csharp == null)? 0 :this.csharp.hashCode()));
        result = ((result* 31)+((this.java == null)? 0 :this.java.hashCode()));
        result = ((result* 31)+((this.powershell == null)? 0 :this.powershell.hashCode()));
        result = ((result* 31)+((this.typescript == null)? 0 :this.typescript.hashCode()));
        result = ((result* 31)+((this.swift == null)? 0 :this.swift.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LanguagesSchemaMetadata) == false) {
            return false;
        }
        LanguagesSchemaMetadata rhs = ((LanguagesSchemaMetadata) other);
        return ((((((((((((((this._default == rhs._default)||((this._default!= null)&&this._default.equals(rhs._default)))&&((this.python == rhs.python)||((this.python!= null)&&this.python.equals(rhs.python))))&&((this.cpp == rhs.cpp)||((this.cpp!= null)&&this.cpp.equals(rhs.cpp))))&&((this.c == rhs.c)||((this.c!= null)&&this.c.equals(rhs.c))))&&((this.go == rhs.go)||((this.go!= null)&&this.go.equals(rhs.go))))&&((this.objectivec == rhs.objectivec)||((this.objectivec!= null)&&this.objectivec.equals(rhs.objectivec))))&&((this.javascript == rhs.javascript)||((this.javascript!= null)&&this.javascript.equals(rhs.javascript))))&&((this.ruby == rhs.ruby)||((this.ruby!= null)&&this.ruby.equals(rhs.ruby))))&&((this.csharp == rhs.csharp)||((this.csharp!= null)&&this.csharp.equals(rhs.csharp))))&&((this.java == rhs.java)||((this.java!= null)&&this.java.equals(rhs.java))))&&((this.powershell == rhs.powershell)||((this.powershell!= null)&&this.powershell.equals(rhs.powershell))))&&((this.typescript == rhs.typescript)||((this.typescript!= null)&&this.typescript.equals(rhs.typescript))))&&((this.swift == rhs.swift)||((this.swift!= null)&&this.swift.equals(rhs.swift))));
    }

}
