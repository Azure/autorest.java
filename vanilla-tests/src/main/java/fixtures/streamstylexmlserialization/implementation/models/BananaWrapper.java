// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstylexmlserialization.implementation.models;

import com.azure.core.util.CoreUtils;
import com.azure.xml.XmlReader;
import com.azure.xml.XmlSerializable;
import com.azure.xml.XmlToken;
import com.azure.xml.XmlWriter;
import fixtures.streamstylexmlserialization.models.Banana;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLStreamException;

/** A wrapper around List&lt;Banana&gt; which provides top-level metadata for serialization. */
public final class BananaWrapper implements XmlSerializable<BananaWrapper> {
    private final List<Banana> bananas;

    /**
     * Creates an instance of BananaWrapper.
     *
     * @param bananas the list.
     */
    public BananaWrapper(List<Banana> bananas) {
        this.bananas = bananas;
    }

    /**
     * Get the List&lt;Banana&gt; contained in this wrapper.
     *
     * @return the List&lt;Banana&gt;.
     */
    public List<Banana> items() {
        return bananas;
    }

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter) throws XMLStreamException {
        return toXml(xmlWriter, null);
    }

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter, String rootElementName) throws XMLStreamException {
        rootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? "bananas" : rootElementName;
        xmlWriter.writeStartElement(rootElementName);
        if (bananas != null) {
            for (Banana element : bananas) {
                xmlWriter.writeXml(element, "banana");
            }
        }
        return xmlWriter.writeEndElement();
    }

    public static BananaWrapper fromXml(XmlReader xmlReader) throws XMLStreamException {
        return fromXml(xmlReader, null);
    }

    public static BananaWrapper fromXml(XmlReader xmlReader, String rootElementName) throws XMLStreamException {
        rootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? "bananas" : rootElementName;
        return xmlReader.readObject(
                rootElementName,
                reader -> {
                    List<Banana> items = null;

                    while (reader.nextElement() != XmlToken.END_ELEMENT) {
                        String elementName = reader.getElementName().getLocalPart();

                        if ("banana".equals(elementName)) {
                            if (items == null) {
                                items = new ArrayList<>();
                            }

                            items.add(Banana.fromXml(reader));
                        } else {
                            reader.nextElement();
                        }
                    }
                    return new BananaWrapper(items);
                });
    }
}
