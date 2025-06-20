// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.xmlconstant.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.util.logging.ClientLogger;
import com.azure.xml.XmlReader;
import com.azure.xml.XmlSerializable;
import com.azure.xml.XmlToken;
import com.azure.xml.XmlWriter;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

/**
 * Groups the set of query request settings.
 */
@Fluent
public final class QueryRequest implements XmlSerializable<QueryRequest> {
    /*
     * Required. The type of the provided query expression.
     */
    @Generated
    private final String queryType = "SQL";

    /*
     * The query expression in SQL. The maximum size of the query expression is 256KiB.
     */
    @Generated
    private String expression;

    /**
     * Creates an instance of QueryRequest class.
     */
    @Generated
    public QueryRequest() {
    }

    /**
     * Get the queryType property: Required. The type of the provided query expression.
     * 
     * @return the queryType value.
     */
    @Generated
    public String getQueryType() {
        return this.queryType;
    }

    /**
     * Get the expression property: The query expression in SQL. The maximum size of the query expression is 256KiB.
     * 
     * @return the expression value.
     */
    @Generated
    public String getExpression() {
        return this.expression;
    }

    /**
     * Set the expression property: The query expression in SQL. The maximum size of the query expression is 256KiB.
     * 
     * @param expression the expression value to set.
     * @return the QueryRequest object itself.
     */
    @Generated
    public QueryRequest setExpression(String expression) {
        this.expression = expression;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getExpression() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Missing required property expression in model QueryRequest"));
        }
    }

    private static final ClientLogger LOGGER = new ClientLogger(QueryRequest.class);

    @Generated
    @Override
    public XmlWriter toXml(XmlWriter xmlWriter) throws XMLStreamException {
        return toXml(xmlWriter, null);
    }

    @Generated
    @Override
    public XmlWriter toXml(XmlWriter xmlWriter, String rootElementName) throws XMLStreamException {
        rootElementName = rootElementName == null || rootElementName.isEmpty() ? "QueryRequest" : rootElementName;
        xmlWriter.writeStartElement(rootElementName);
        xmlWriter.writeStringElement("QueryType", this.queryType);
        xmlWriter.writeStringElement("Expression", this.expression);
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of QueryRequest from the XmlReader.
     * 
     * @param xmlReader The XmlReader being read.
     * @return An instance of QueryRequest if the XmlReader was pointing to an instance of it, or null if it was
     * pointing to XML null.
     * @throws XMLStreamException If an error occurs while reading the QueryRequest.
     */
    @Generated
    public static QueryRequest fromXml(XmlReader xmlReader) throws XMLStreamException {
        return fromXml(xmlReader, null);
    }

    /**
     * Reads an instance of QueryRequest from the XmlReader.
     * 
     * @param xmlReader The XmlReader being read.
     * @param rootElementName Optional root element name to override the default defined by the model. Used to support
     * cases where the model can deserialize from different root element names.
     * @return An instance of QueryRequest if the XmlReader was pointing to an instance of it, or null if it was
     * pointing to XML null.
     * @throws XMLStreamException If an error occurs while reading the QueryRequest.
     */
    @Generated
    public static QueryRequest fromXml(XmlReader xmlReader, String rootElementName) throws XMLStreamException {
        String finalRootElementName
            = rootElementName == null || rootElementName.isEmpty() ? "QueryRequest" : rootElementName;
        return xmlReader.readObject(finalRootElementName, reader -> {
            QueryRequest deserializedQueryRequest = new QueryRequest();
            while (reader.nextElement() != XmlToken.END_ELEMENT) {
                QName elementName = reader.getElementName();

                if ("Expression".equals(elementName.getLocalPart())) {
                    deserializedQueryRequest.expression = reader.getStringElement();
                } else {
                    reader.skipElement();
                }
            }

            return deserializedQueryRequest;
        });
    }
}
