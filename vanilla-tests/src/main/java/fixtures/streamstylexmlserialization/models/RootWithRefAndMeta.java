// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstylexmlserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.xml.XmlSerializable;
import com.azure.xml.XmlWriter;

/** I am root, and I ref a model WITH meta. */
@Fluent
public final class RootWithRefAndMeta implements XmlSerializable<RootWithRefAndMeta> {
    /*
     * XML will use XMLComplexTypeWithMeta
     */
    private ComplexTypeWithMeta refToModel;

    /*
     * Something else (just to avoid flattening)
     */
    private String something;

    /**
     * Get the refToModel property: XML will use XMLComplexTypeWithMeta.
     *
     * @return the refToModel value.
     */
    public ComplexTypeWithMeta getRefToModel() {
        return this.refToModel;
    }

    /**
     * Set the refToModel property: XML will use XMLComplexTypeWithMeta.
     *
     * @param refToModel the refToModel value to set.
     * @return the RootWithRefAndMeta object itself.
     */
    public RootWithRefAndMeta setRefToModel(ComplexTypeWithMeta refToModel) {
        this.refToModel = refToModel;
        return this;
    }

    /**
     * Get the something property: Something else (just to avoid flattening).
     *
     * @return the something value.
     */
    public String getSomething() {
        return this.something;
    }

    /**
     * Set the something property: Something else (just to avoid flattening).
     *
     * @param something the something value to set.
     * @return the RootWithRefAndMeta object itself.
     */
    public RootWithRefAndMeta setSomething(String something) {
        this.something = something;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getRefToModel() != null) {
            getRefToModel().validate();
        }
    }

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter) {
        xmlWriter.writeStartElement("RootWithRefAndMeta");
        xmlWriter.writeXml("XMLComplexTypeWithMeta", this.refToModel);
        xmlWriter.writeStringElement("Something", this.something);
        return xmlWriter.writeEndElement();
    }
}
