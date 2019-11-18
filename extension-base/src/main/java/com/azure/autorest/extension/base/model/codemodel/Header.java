
package com.azure.autorest.extension.base.model.codemodel;


import com.azure.autorest.extension.base.model.extensionmodel.XmsExtensions;

public class Header {
    private String header;
    private Schema schema;
    private XmsExtensions extensions;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public XmsExtensions getExtensions() {
        return extensions;
    }

    public void setExtensions(XmsExtensions extensions) {
        this.extensions = extensions;
    }
}
