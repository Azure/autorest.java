
package com.azure.autorest.model.codemodel;


public class XmlSerlializationFormat extends SerializationFormat {

    private String name;
    private String namespace;
    private String prefix;
    /**
     * 
     * (Required)
     * 
     */
    private boolean attribute;
    /**
     * 
     * (Required)
     * 
     */
    private boolean wrapped;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * 
     * (Required)
     * 
     */
    public boolean isAttribute() {
        return attribute;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setAttribute(boolean attribute) {
        this.attribute = attribute;
    }

    /**
     * 
     * (Required)
     * 
     */
    public boolean isWrapped() {
        return wrapped;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setWrapped(boolean wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(XmlSerlializationFormat.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("namespace");
        sb.append('=');
        sb.append(((this.namespace == null)?"<null>":this.namespace));
        sb.append(',');
        sb.append("prefix");
        sb.append('=');
        sb.append(((this.prefix == null)?"<null>":this.prefix));
        sb.append(',');
        sb.append("attribute");
        sb.append('=');
        sb.append(this.attribute);
        sb.append(',');
        sb.append("wrapped");
        sb.append('=');
        sb.append(this.wrapped);
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
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.namespace == null)? 0 :this.namespace.hashCode()));
        result = ((result* 31)+(this.attribute? 1 : 0));
        result = ((result* 31)+(this.wrapped? 1 : 0));
        result = ((result* 31)+((this.prefix == null)? 0 :this.prefix.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof XmlSerlializationFormat) == false) {
            return false;
        }
        XmlSerlializationFormat rhs = ((XmlSerlializationFormat) other);
        return ((((((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name)))&&((this.namespace == rhs.namespace)||((this.namespace!= null)&&this.namespace.equals(rhs.namespace))))&&(this.attribute == rhs.attribute))&&(this.wrapped == rhs.wrapped))&&((this.prefix == rhs.prefix)||((this.prefix!= null)&&this.prefix.equals(rhs.prefix))));
    }

}
