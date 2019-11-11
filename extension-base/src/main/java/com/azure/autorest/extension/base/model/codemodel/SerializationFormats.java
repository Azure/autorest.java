
package com.azure.autorest.extension.base.model.codemodel;



/**
 * custom extensible metadata for individual serialization formats
 * 
 */
public class SerializationFormats {

    private SerializationFormat json;
    private XmlSerlializationFormat xml;
    private SerializationFormat protobuf;

    public SerializationFormat getJson() {
        return json;
    }

    public void setJson(SerializationFormat json) {
        this.json = json;
    }

    public XmlSerlializationFormat getXml() {
        return xml;
    }

    public void setXml(XmlSerlializationFormat xml) {
        this.xml = xml;
    }

    public SerializationFormat getProtobuf() {
        return protobuf;
    }

    public void setProtobuf(SerializationFormat protobuf) {
        this.protobuf = protobuf;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SerializationFormats.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("json");
        sb.append('=');
        sb.append(((this.json == null)?"<null>":this.json));
        sb.append(',');
        sb.append("xml");
        sb.append('=');
        sb.append(((this.xml == null)?"<null>":this.xml));
        sb.append(',');
        sb.append("protobuf");
        sb.append('=');
        sb.append(((this.protobuf == null)?"<null>":this.protobuf));
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
        result = ((result* 31)+((this.json == null)? 0 :this.json.hashCode()));
        result = ((result* 31)+((this.protobuf == null)? 0 :this.protobuf.hashCode()));
        result = ((result* 31)+((this.xml == null)? 0 :this.xml.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SerializationFormats) == false) {
            return false;
        }
        SerializationFormats rhs = ((SerializationFormats) other);
        return ((((this.json == rhs.json)||((this.json!= null)&&this.json.equals(rhs.json)))&&((this.protobuf == rhs.protobuf)||((this.protobuf!= null)&&this.protobuf.equals(rhs.protobuf))))&&((this.xml == rhs.xml)||((this.xml!= null)&&this.xml.equals(rhs.xml))));
    }

}
