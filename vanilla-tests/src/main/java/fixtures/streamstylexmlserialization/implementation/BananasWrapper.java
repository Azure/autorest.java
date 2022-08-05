// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstylexmlserialization.implementation;

import com.azure.xml.XmlReader;
import com.azure.xml.XmlSerializable;
import com.azure.xml.XmlToken;
import com.azure.xml.XmlWriter;
import fixtures.streamstylexmlserialization.models.Banana;
import java.util.ArrayList;
import java.util.List;

/** A wrapper around List&lt;Banana&gt; which provides top-level metadata for serialization. */
public final class BananasWrapper implements XmlSerializable<BananasWrapper> {
    private final List<Banana> bananas;

    /**
     * Creates an instance of BananasWrapper.
     *
     * @param bananas the list.
     */
    public BananasWrapper(List<Banana> bananas) {
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
    public XmlWriter toXml(XmlWriter xmlWriter) {
        xmlWriter.writeStartElement("bananas");
        if (bananas != null) {
            bananas.forEach(xmlWriter::writeXml);
        }
        return xmlWriter.writeEndElement();
    }

    public static BananasWrapper fromXml(XmlReader xmlReader) {
        return xmlReader.readObject(
                "bananas",
                reader -> {
                    List<Banana> items = null;

                    while (reader.nextElement() != XmlToken.END_ELEMENT) {
                        String elementName = reader.getElementName().getLocalPart();

                        if ("banana".equals(elementName)) {
                            if (items == null) {
                                items = new ArrayList<>();
                            }

                        } else {
                            reader.nextElement();
                        }
                    }
                    return new BananasWrapper(items);
                });
    }
}
