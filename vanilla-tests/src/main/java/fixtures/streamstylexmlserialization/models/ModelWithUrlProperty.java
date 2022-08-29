// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstylexmlserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.xml.XmlReader;
import com.azure.xml.XmlSerializable;
import com.azure.xml.XmlToken;
import com.azure.xml.XmlWriter;
import java.net.URL;
import java.util.Objects;
import javax.xml.namespace.QName;

/** The ModelWithUrlProperty model. */
@Fluent
public final class ModelWithUrlProperty implements XmlSerializable<ModelWithUrlProperty> {
    /*
     * The Url property.
     */
    private URL url;

    /**
     * Get the url property: The Url property.
     *
     * @return the url value.
     */
    public URL getUrl() {
        return this.url;
    }

    /**
     * Set the url property: The Url property.
     *
     * @param url the url value to set.
     * @return the ModelWithUrlProperty object itself.
     */
    public ModelWithUrlProperty setUrl(URL url) {
        this.url = url;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter) {
        xmlWriter.writeStartElement("ModelWithUrlProperty");
        xmlWriter.writeStringElement("Url", Objects.toString(this.url, null));
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of ModelWithUrlProperty from the XmlReader.
     *
     * @param xmlReader The XmlReader being read.
     * @return An instance of ModelWithUrlProperty if the XmlReader was pointing to an instance of it, or null if it was
     *     pointing to XML null.
     */
    public static ModelWithUrlProperty fromXml(XmlReader xmlReader) {
        return xmlReader.readObject(
                "ModelWithUrlProperty",
                reader -> {
                    URL url = null;
                    while (reader.nextElement() != XmlToken.END_ELEMENT) {
                        QName fieldName = reader.getElementName();

                        if ("Url".equals(fieldName.getLocalPart())) {
                            url = reader.getNullableElement(URL::new);
                        } else {
                            reader.skipElement();
                        }
                    }
                    ModelWithUrlProperty deserializedValue = new ModelWithUrlProperty();
                    deserializedValue.url = url;

                    return deserializedValue;
                });
    }
}
