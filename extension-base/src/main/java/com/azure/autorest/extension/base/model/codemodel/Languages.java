
package com.azure.autorest.extension.base.model.codemodel;



/**
 * custom extensible metadata for individual language generators
 * 
 */
public class Languages {

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * (Required)
     * 
     */
    private Language _default;
    private CSharpLanguage csharp;
    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    private Language python;
    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    private Language ruby;
    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    private Language go;
    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    private Language typescript;
    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    private Language javascript;
    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    private Language powershell;
    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    private Language java;
    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    private Language c;
    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    private Language cpp;
    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    private Language swift;
    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    private Language objectivec;

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * (Required)
     * 
     */
    public Language getDefault() {
        return _default;
    }

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * (Required)
     * 
     */
    public void setDefault(Language _default) {
        this._default = _default;
    }

    public CSharpLanguage getCsharp() {
        return csharp;
    }

    public void setCsharp(CSharpLanguage csharp) {
        this.csharp = csharp;
    }

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    public Language getPython() {
        return python;
    }

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    public void setPython(Language python) {
        this.python = python;
    }

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    public Language getRuby() {
        return ruby;
    }

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    public void setRuby(Language ruby) {
        this.ruby = ruby;
    }

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    public Language getGo() {
        return go;
    }

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    public void setGo(Language go) {
        this.go = go;
    }

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    public Language getTypescript() {
        return typescript;
    }

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    public void setTypescript(Language typescript) {
        this.typescript = typescript;
    }

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    public Language getJavascript() {
        return javascript;
    }

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    public void setJavascript(Language javascript) {
        this.javascript = javascript;
    }

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    public Language getPowershell() {
        return powershell;
    }

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    public void setPowershell(Language powershell) {
        this.powershell = powershell;
    }

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    public Language getJava() {
        return java;
    }

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    public void setJava(Language java) {
        this.java = java;
    }

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    public Language getC() {
        return c;
    }

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    public void setC(Language c) {
        this.c = c;
    }

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    public Language getCpp() {
        return cpp;
    }

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    public void setCpp(Language cpp) {
        this.cpp = cpp;
    }

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    public Language getSwift() {
        return swift;
    }

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    public void setSwift(Language swift) {
        this.swift = swift;
    }

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    public Language getObjectivec() {
        return objectivec;
    }

    /**
     * the bare-minimum fields for per-language metadata on a given aspect
     * 
     */
    public void setObjectivec(Language objectivec) {
        this.objectivec = objectivec;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Languages.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        if ((other instanceof Languages) == false) {
            return false;
        }
        Languages rhs = ((Languages) other);
        return ((((((((((((((this._default == rhs._default)||((this._default!= null)&&this._default.equals(rhs._default)))&&((this.python == rhs.python)||((this.python!= null)&&this.python.equals(rhs.python))))&&((this.cpp == rhs.cpp)||((this.cpp!= null)&&this.cpp.equals(rhs.cpp))))&&((this.c == rhs.c)||((this.c!= null)&&this.c.equals(rhs.c))))&&((this.go == rhs.go)||((this.go!= null)&&this.go.equals(rhs.go))))&&((this.objectivec == rhs.objectivec)||((this.objectivec!= null)&&this.objectivec.equals(rhs.objectivec))))&&((this.javascript == rhs.javascript)||((this.javascript!= null)&&this.javascript.equals(rhs.javascript))))&&((this.ruby == rhs.ruby)||((this.ruby!= null)&&this.ruby.equals(rhs.ruby))))&&((this.csharp == rhs.csharp)||((this.csharp!= null)&&this.csharp.equals(rhs.csharp))))&&((this.java == rhs.java)||((this.java!= null)&&this.java.equals(rhs.java))))&&((this.powershell == rhs.powershell)||((this.powershell!= null)&&this.powershell.equals(rhs.powershell))))&&((this.typescript == rhs.typescript)||((this.typescript!= null)&&this.typescript.equals(rhs.typescript))))&&((this.swift == rhs.swift)||((this.swift!= null)&&this.swift.equals(rhs.swift))));
    }

}
