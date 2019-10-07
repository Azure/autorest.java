package com.azure.autorest.model.codemodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * custom extensible metadata for individual serialization formats
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "json",
    "xml",
    "protobuf"
})
public class SerializationFormats {

    @JsonProperty("json")
    private Extensions json;
    @JsonProperty("xml")
    private XmlSerlializationFormat xml;
    @JsonProperty("protobuf")
    private Extensions protobuf;

    @JsonProperty("json")
    public Extensions getJson() {
        return json;
    }

    @JsonProperty("json")
    public void setJson(Extensions json) {
        this.json = json;
    }

    @JsonProperty("xml")
    public XmlSerlializationFormat getXml() {
        return xml;
    }

    @JsonProperty("xml")
    public void setXml(XmlSerlializationFormat xml) {
        this.xml = xml;
    }

    @JsonProperty("protobuf")
    public Extensions getProtobuf() {
        return protobuf;
    }

    @JsonProperty("protobuf")
    public void setProtobuf(Extensions protobuf) {
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
