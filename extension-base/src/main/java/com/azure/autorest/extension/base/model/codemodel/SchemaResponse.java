
package com.azure.autorest.extension.base.model.codemodel;



/**
 * a response from a service.
 * 
 */
public class SchemaResponse extends Response {
    private Schema schema;

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }
}
