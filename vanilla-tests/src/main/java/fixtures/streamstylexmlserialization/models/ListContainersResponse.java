// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstylexmlserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.xml.XmlReader;
import com.azure.xml.XmlSerializable;
import com.azure.xml.XmlToken;
import com.azure.xml.XmlWriter;
import java.util.ArrayList;
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

    private static final class ContainersWrapper implements XmlSerializable<ContainersWrapper> {
        private final List<Container> items;

        private ContainersWrapper(List<Container> items) {
            this.items = items;
        }

        @Override
        public XmlWriter toXml(XmlWriter xmlWriter) throws XMLStreamException {
            xmlWriter.writeStartElement("Containers");
            if (items != null) {
                for (Container element : items) {
                    xmlWriter.writeXml(element);
                }
            }
            return xmlWriter.writeEndElement();
        }

        public static ContainersWrapper fromXml(XmlReader xmlReader) throws XMLStreamException {
            return xmlReader.readObject(
                    "Containers",
                    reader -> {
                        List<Container> items = null;

                        while (reader.nextElement() != XmlToken.END_ELEMENT) {
                            String elementName = reader.getElementName().getLocalPart();

                            if ("Container".equals(elementName)) {
                                if (items == null) {
                                    items = new ArrayList<>();
                                }

                                items.add(Container.fromXml(reader));
                            } else {
                                reader.nextElement();
                            }
                        }
                        return new ContainersWrapper(items);
                    });
        }
    }

    /*
     * The Containers property.
     */
    private ContainersWrapper containers;

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
            this.containers = new ContainersWrapper(new ArrayList<Container>());
        }
        return this.containers.items;
    }

    /**
     * Set the containers property: The Containers property.
     *
     * @param containers the containers value to set.
     * @return the ListContainersResponse object itself.
     */
    public ListContainersResponse setContainers(List<Container> containers) {
        this.containers = new ContainersWrapper(containers);
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
        xmlWriter.writeStartElement("EnumerationResults");
        xmlWriter.writeStringAttribute("ServiceEndpoint", this.serviceEndpoint);
        xmlWriter.writeStringElement("Prefix", this.prefix);
        xmlWriter.writeStringElement("Marker", this.marker);
        xmlWriter.writeIntElement("MaxResults", this.maxResults);
        xmlWriter.writeXml(this.containers);
        xmlWriter.writeStringElement("NextMarker", this.nextMarker);
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of ListContainersResponse from the XmlReader.
     *
     * @param xmlReader The XmlReader being read.
     * @return An instance of ListContainersResponse if the XmlReader was pointing to an instance of it, or null if it
     *     was pointing to XML null.
     */
    public static ListContainersResponse fromXml(XmlReader xmlReader) throws XMLStreamException {
        return xmlReader.readObject(
                "EnumerationResults",
                reader -> {
                    String serviceEndpoint = null;
                    String prefix = null;
                    String marker = null;
                    int maxResults = 0;
                    List<Container> containers = null;
                    String nextMarker = null;
                    while (reader.nextElement() != XmlToken.END_ELEMENT) {
                        QName fieldName = reader.getElementName();

                        if ("ServiceEndpoint".equals(fieldName.getLocalPart())) {
                            serviceEndpoint = reader.getStringElement();
                        } else if ("Prefix".equals(fieldName.getLocalPart())) {
                            prefix = reader.getStringElement();
                        } else if ("Marker".equals(fieldName.getLocalPart())) {
                            marker = reader.getStringElement();
                        } else if ("MaxResults".equals(fieldName.getLocalPart())) {
                            maxResults = reader.getIntElement();
                        } else if ("Containers".equals(fieldName.getLocalPart())) {
                            if (containers == null) {
                                containers = new LinkedList<>();
                            }
                            containers.add(Container.fromXml(reader));
                        } else if ("NextMarker".equals(fieldName.getLocalPart())) {
                            nextMarker = reader.getStringElement();
                        } else {
                            reader.skipElement();
                        }
                    }
                    ListContainersResponse deserializedValue = new ListContainersResponse();
                    deserializedValue.serviceEndpoint = serviceEndpoint;
                    deserializedValue.prefix = prefix;
                    deserializedValue.marker = marker;
                    deserializedValue.maxResults = maxResults;
                    deserializedValue.setContainers(containers);
                    deserializedValue.nextMarker = nextMarker;

                    return deserializedValue;
                });
    }
}
