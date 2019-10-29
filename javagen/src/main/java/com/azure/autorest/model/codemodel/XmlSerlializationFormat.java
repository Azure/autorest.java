package com.azure.autorest.model.codemodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "namespace",
    "prefix",
    "attribute",
    "wrapped"
})
public class XmlSerlializationFormat {

    @JsonProperty("name")
    private String name;
    @JsonProperty("namespace")
    private String namespace;
    @JsonProperty("prefix")
    private String prefix;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("attribute")
    private Boolean attribute;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("wrapped")
    private Boolean wrapped;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("namespace")
    public String getNamespace() {
        return namespace;
    }

    @JsonProperty("namespace")
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @JsonProperty("prefix")
    public String getPrefix() {
        return prefix;
    }

    @JsonProperty("prefix")
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("attribute")
    public Boolean getAttribute() {
        return attribute;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("attribute")
    public void setAttribute(Boolean attribute) {
        this.attribute = attribute;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("wrapped")
    public Boolean getWrapped() {
        return wrapped;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("wrapped")
    public void setWrapped(Boolean wrapped) {
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
        sb.append(((this.attribute == null)?"<null>":this.attribute));
        sb.append(',');
        sb.append("wrapped");
        sb.append('=');
        sb.append(((this.wrapped == null)?"<null>":this.wrapped));
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
        result = ((result* 31)+((this.attribute == null)? 0 :this.attribute.hashCode()));
        result = ((result* 31)+((this.wrapped == null)? 0 :this.wrapped.hashCode()));
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
        return ((((((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name)))&&((this.namespace == rhs.namespace)||((this.namespace!= null)&&this.namespace.equals(rhs.namespace))))&&((this.attribute == rhs.attribute)||((this.attribute!= null)&&this.attribute.equals(rhs.attribute))))&&((this.wrapped == rhs.wrapped)||((this.wrapped!= null)&&this.wrapped.equals(rhs.wrapped))))&&((this.prefix == rhs.prefix)||((this.prefix!= null)&&this.prefix.equals(rhs.prefix))));
    }

}
