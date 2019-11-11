
package com.azure.autorest.extension.base.model.codemodel;



/**
 * the bare-minimum fields for per-language metadata on a given aspect
 * 
 */
public class Language {

    /**
     * name used in actual implementation
     * (Required)
     * 
     */
    private String name;
    /**
     * description text - describes this node.
     * (Required)
     * 
     */
    private String description;

    /**
     * name used in actual implementation
     * (Required)
     * 
     */
    public String getName() {
        return name;
    }

    /**
     * name used in actual implementation
     * (Required)
     * 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * description text - describes this node.
     * (Required)
     * 
     */
    public String getDescription() {
        return description;
    }

    /**
     * description text - describes this node.
     * (Required)
     * 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Language.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
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
        result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Language) == false) {
            return false;
        }
        Language rhs = ((Language) other);
        return (((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name)))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))));
    }

}
