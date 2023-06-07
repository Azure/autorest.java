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
import java.util.LinkedList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

/** An enumeration of containers. */
@Fluent
public final class ListContainersResponse implements XmlSerializable<ListContainersResponse> {
    /*
     * The ServiceEndpoint property.
     */
    private String serviceEndpoint;

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
     * The Containers property.
     */
    private List<Container> containers = new LinkedList<>();

    /*
     * The NextMarker property.
     */
    private String nextMarker;

    /** Creates an instance of ListContainersResponse class. */
    public ListContainersResponse() {}

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
     * @return the ListContainersResponse object itself.
     */
    public ListContainersResponse setServiceEndpoint(String serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
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
     * @return the ListContainersResponse object itself.
     */
    public ListContainersResponse setPrefix(String prefix) {
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
     * @return the ListContainersResponse object itself.
     */
    public ListContainersResponse setMarker(String marker) {
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
     * @return the ListContainersResponse object itself.
     */
    public ListContainersResponse setMaxResults(int maxResults) {
        this.maxResults = maxResults;
        return this;
    }

    /**
     * Get the containers property: The Containers property.
     *
     * @return the containers value.
     */
    public List<Container> getContainers() {
        if (this.containers == null) {
            this.containers = new LinkedList<>();
        }
        return this.containers;
    }

    /**
     * Set the containers property: The Containers property.
     *
     * @param containers the containers value to set.
     * @return the ListContainersResponse object itself.
     */
    public ListContainersResponse setContainers(List<Container> containers) {
        this.containers = containers;
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
     * @return the ListContainersResponse object itself.
     */
    public ListContainersResponse setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getServiceEndpoint() == null) {
            throw new IllegalArgumentException(
                    "Missing required property serviceEndpoint in model ListContainersResponse");
        }
        if (getPrefix() == null) {
            throw new IllegalArgumentException("Missing required property prefix in model ListContainersResponse");
        }
        if (getContainers() != null) {
            getContainers().forEach(e -> e.validate());
        }
        if (getNextMarker() == null) {
            throw new IllegalArgumentException("Missing required property nextMarker in model ListContainersResponse");
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
        xmlWriter.writeStringElement("Prefix", this.prefix);
        xmlWriter.writeStringElement("Marker", this.marker);
        xmlWriter.writeIntElement("MaxResults", this.maxResults);
        if (this.containers != null) {
            xmlWriter.writeStartElement("Containers");
            for (Container element : this.containers) {
                xmlWriter.writeXml(element, "Container");
            }
            xmlWriter.writeEndElement();
        }
        xmlWriter.writeStringElement("NextMarker", this.nextMarker);
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of ListContainersResponse from the XmlReader.
     *
     * @param xmlReader The XmlReader being read.
     * @return An instance of ListContainersResponse if the XmlReader was pointing to an instance of it, or null if it
     *     was pointing to XML null.
     * @throws IllegalStateException If the deserialized XML object was missing any required properties.
     * @throws XMLStreamException If an error occurs while reading the ListContainersResponse.
     */
    public static ListContainersResponse fromXml(XmlReader xmlReader) throws XMLStreamException {
        return fromXml(xmlReader, null);
    }

    /**
     * Reads an instance of ListContainersResponse from the XmlReader.
     *
     * @param xmlReader The XmlReader being read.
     * @param rootElementName Optional root element name to override the default defined by the model. Used to support
     *     cases where the model can deserialize from different root element names.
     * @return An instance of ListContainersResponse if the XmlReader was pointing to an instance of it, or null if it
     *     was pointing to XML null.
     * @throws IllegalStateException If the deserialized XML object was missing any required properties.
     * @throws XMLStreamException If an error occurs while reading the ListContainersResponse.
     */
    public static ListContainersResponse fromXml(XmlReader xmlReader, String rootElementName)
            throws XMLStreamException {
        String finalRootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? "EnumerationResults" : rootElementName;
        return xmlReader.readObject(
                finalRootElementName,
                reader -> {
                    ListContainersResponse deserializedListContainersResponse = new ListContainersResponse();
                    deserializedListContainersResponse.serviceEndpoint =
                            reader.getStringAttribute(null, "ServiceEndpoint");
                    while (reader.nextElement() != XmlToken.END_ELEMENT) {
                        QName elementName = reader.getElementName();

                        if ("Prefix".equals(elementName.getLocalPart())) {
                            deserializedListContainersResponse.prefix = reader.getStringElement();
                        } else if ("Marker".equals(elementName.getLocalPart())) {
                            deserializedListContainersResponse.marker = reader.getStringElement();
                        } else if ("MaxResults".equals(elementName.getLocalPart())) {
                            deserializedListContainersResponse.maxResults = reader.getIntElement();
                        } else if ("Containers".equals(elementName.getLocalPart())) {
                            if (deserializedListContainersResponse.containers == null) {
                                deserializedListContainersResponse.containers = new LinkedList<>();
                            }
                            while (reader.nextElement() != XmlToken.END_ELEMENT) {
                                elementName = reader.getElementName();
                                if ("Container".equals(elementName.getLocalPart())) {
                                    deserializedListContainersResponse.containers.add(
                                            Container.fromXml(reader, "Container"));
                                } else {
                                    reader.skipElement();
                                }
                            }
                        } else if ("NextMarker".equals(elementName.getLocalPart())) {
                            deserializedListContainersResponse.nextMarker = reader.getStringElement();
                        } else {
                            reader.skipElement();
                        }
                    }

                    return deserializedListContainersResponse;
                });
    }
}
