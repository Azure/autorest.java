// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstylexmlserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.CoreUtils;
import com.azure.xml.XmlReader;
import com.azure.xml.XmlSerializable;
import com.azure.xml.XmlToken;
import com.azure.xml.XmlWriter;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

/** An enumeration of blobs. */
@Fluent
public final class ListBlobsResponse implements XmlSerializable<ListBlobsResponse> {
    /*
     * The ServiceEndpoint property.
     */
    private String serviceEndpoint;

    /*
     * The ContainerName property.
     */
    private String containerName;

    /*
     * The Prefix property.
     */
    private String prefix;

    /*
     * The Marker property.
     */
    private String marker;

    /*
     * The MaxResults property.
     */
    private int maxResults;

    /*
     * The Delimiter property.
     */
    private String delimiter;

    /*
     * The Blobs property.
     */
    private Blobs blobs;

    /*
     * The NextMarker property.
     */
    private String nextMarker;

    /** Creates an instance of ListBlobsResponse class. */
    public ListBlobsResponse() {}

    /**
     * Get the serviceEndpoint property: The ServiceEndpoint property.
     *
     * @return the serviceEndpoint value.
     */
    public String getServiceEndpoint() {
        return this.serviceEndpoint;
    }

    /**
     * Set the serviceEndpoint property: The ServiceEndpoint property.
     *
     * @param serviceEndpoint the serviceEndpoint value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setServiceEndpoint(String serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
        return this;
    }

    /**
     * Get the containerName property: The ContainerName property.
     *
     * @return the containerName value.
     */
    public String getContainerName() {
        return this.containerName;
    }

    /**
     * Set the containerName property: The ContainerName property.
     *
     * @param containerName the containerName value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setContainerName(String containerName) {
        this.containerName = containerName;
        return this;
    }

    /**
     * Get the prefix property: The Prefix property.
     *
     * @return the prefix value.
     */
    public String getPrefix() {
        return this.prefix;
    }

    /**
     * Set the prefix property: The Prefix property.
     *
     * @param prefix the prefix value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    /**
     * Get the marker property: The Marker property.
     *
     * @return the marker value.
     */
    public String getMarker() {
        return this.marker;
    }

    /**
     * Set the marker property: The Marker property.
     *
     * @param marker the marker value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setMarker(String marker) {
        this.marker = marker;
        return this;
    }

    /**
     * Get the maxResults property: The MaxResults property.
     *
     * @return the maxResults value.
     */
    public int getMaxResults() {
        return this.maxResults;
    }

    /**
     * Set the maxResults property: The MaxResults property.
     *
     * @param maxResults the maxResults value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setMaxResults(int maxResults) {
        this.maxResults = maxResults;
        return this;
    }

    /**
     * Get the delimiter property: The Delimiter property.
     *
     * @return the delimiter value.
     */
    public String getDelimiter() {
        return this.delimiter;
    }

    /**
     * Set the delimiter property: The Delimiter property.
     *
     * @param delimiter the delimiter value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setDelimiter(String delimiter) {
        this.delimiter = delimiter;
        return this;
    }

    /**
     * Get the blobs property: The Blobs property.
     *
     * @return the blobs value.
     */
    public Blobs getBlobs() {
        return this.blobs;
    }

    /**
     * Set the blobs property: The Blobs property.
     *
     * @param blobs the blobs value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setBlobs(Blobs blobs) {
        this.blobs = blobs;
        return this;
    }

    /**
     * Get the nextMarker property: The NextMarker property.
     *
     * @return the nextMarker value.
     */
    public String getNextMarker() {
        return this.nextMarker;
    }

    /**
     * Set the nextMarker property: The NextMarker property.
     *
     * @param nextMarker the nextMarker value to set.
     * @return the ListBlobsResponse object itself.
     */
    public ListBlobsResponse setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getContainerName() == null) {
            throw new IllegalArgumentException("Missing required property containerName in model ListBlobsResponse");
        }
        if (getPrefix() == null) {
            throw new IllegalArgumentException("Missing required property prefix in model ListBlobsResponse");
        }
        if (getMarker() == null) {
            throw new IllegalArgumentException("Missing required property marker in model ListBlobsResponse");
        }
        if (getDelimiter() == null) {
            throw new IllegalArgumentException("Missing required property delimiter in model ListBlobsResponse");
        }
        if (getBlobs() == null) {
            throw new IllegalArgumentException("Missing required property blobs in model ListBlobsResponse");
        } else {
            getBlobs().validate();
        }
        if (getNextMarker() == null) {
            throw new IllegalArgumentException("Missing required property nextMarker in model ListBlobsResponse");
        }
    }

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter) throws XMLStreamException {
        return toXml(xmlWriter, null);
    }

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter, String rootElementName) throws XMLStreamException {
        rootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? "EnumerationResults" : rootElementName;
        xmlWriter.writeStartElement(rootElementName);
        xmlWriter.writeStringAttribute("ServiceEndpoint", this.serviceEndpoint);
        xmlWriter.writeStringAttribute("ContainerName", this.containerName);
        xmlWriter.writeStringElement("Prefix", this.prefix);
        xmlWriter.writeStringElement("Marker", this.marker);
        xmlWriter.writeIntElement("MaxResults", this.maxResults);
        xmlWriter.writeStringElement("Delimiter", this.delimiter);
        xmlWriter.writeXml(this.blobs, "Blobs");
        xmlWriter.writeStringElement("NextMarker", this.nextMarker);
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of ListBlobsResponse from the XmlReader.
     *
     * @param xmlReader The XmlReader being read.
     * @return An instance of ListBlobsResponse if the XmlReader was pointing to an instance of it, or null if it was
     *     pointing to XML null.
     * @throws IllegalStateException If the deserialized XML object was missing any required properties.
     * @throws XMLStreamException If an error occurs while reading the ListBlobsResponse.
     */
    public static ListBlobsResponse fromXml(XmlReader xmlReader) throws XMLStreamException {
        return fromXml(xmlReader, null);
    }

    /**
     * Reads an instance of ListBlobsResponse from the XmlReader.
     *
     * @param xmlReader The XmlReader being read.
     * @param rootElementName Optional root element name to override the default definedby the model. Used to support
     *     cases where the model can deserialize from different root elementnames.
     * @return An instance of ListBlobsResponse if the XmlReader was pointing to an instance of it, or null if it was
     *     pointing to XML null.
     * @throws IllegalStateException If the deserialized XML object was missing any required properties.
     * @throws XMLStreamException If an error occurs while reading the ListBlobsResponse.
     */
    public static ListBlobsResponse fromXml(XmlReader xmlReader, String rootElementName) throws XMLStreamException {
        String finalRootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? "EnumerationResults" : rootElementName;
        return xmlReader.readObject(
                finalRootElementName,
                reader -> {
                    String serviceEndpoint = reader.getStringAttribute(null, "ServiceEndpoint");
                    String containerName = reader.getStringAttribute(null, "ContainerName");
                    String prefix = null;
                    String marker = null;
                    int maxResults = 0;
                    String delimiter = null;
                    Blobs blobs = null;
                    String nextMarker = null;
                    while (reader.nextElement() != XmlToken.END_ELEMENT) {
                        QName elementName = reader.getElementName();

                        if ("Prefix".equals(elementName.getLocalPart())) {
                            prefix = reader.getStringElement();
                        } else if ("Marker".equals(elementName.getLocalPart())) {
                            marker = reader.getStringElement();
                        } else if ("MaxResults".equals(elementName.getLocalPart())) {
                            maxResults = reader.getIntElement();
                        } else if ("Delimiter".equals(elementName.getLocalPart())) {
                            delimiter = reader.getStringElement();
                        } else if ("Blobs".equals(elementName.getLocalPart())) {
                            blobs = Blobs.fromXml(reader, "Blobs");
                        } else if ("NextMarker".equals(elementName.getLocalPart())) {
                            nextMarker = reader.getStringElement();
                        } else {
                            reader.skipElement();
                        }
                    }
                    ListBlobsResponse deserializedListBlobsResponse = new ListBlobsResponse();
                    deserializedListBlobsResponse.containerName = containerName;
                    deserializedListBlobsResponse.prefix = prefix;
                    deserializedListBlobsResponse.marker = marker;
                    deserializedListBlobsResponse.maxResults = maxResults;
                    deserializedListBlobsResponse.delimiter = delimiter;
                    deserializedListBlobsResponse.blobs = blobs;
                    deserializedListBlobsResponse.nextMarker = nextMarker;
                    deserializedListBlobsResponse.serviceEndpoint = serviceEndpoint;

                    return deserializedListBlobsResponse;
                });
    }
}
