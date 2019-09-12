package com.azure.autorest.model.clientmodel;

import java.util.*;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/** 
 A model that is defined by the client.
*/
public class ClientModel
{
    /**
     Create a new ServiceModel with the provided properties.

     @param package The package that this model class belongs to.
     @param name The name of this model.
     @param imports The imports for this model.
     @param description The description of this model.
     @param isPolymorphic Whether or not this model has model types that derive from it.
     @param polymorphicDiscriminator The name of the property that determines which polymorphic model type to create.
     @param serializedName The name that is used for this model when it is serialized.
     @param needsFlatten Whether or not this model needs serialization flattening.
     @param parentModel The parent model of this model.
     @param derivedModels The models that derive from this model.
     @param xmlName The name that will be used for this model's XML element representation.
     @param properties The properties for this model.
    */
    public ClientModel(String package_Keyword, String name, List<String> imports, String description, boolean isPolymorphic, String polymorphicDiscriminator, String serializedName, boolean needsFlatten, ClientModel parentModel, List<ClientModel> derivedModels, String xmlName, List<ClientModelProperty> properties)
    {
        Package = package_Keyword;
        Name = name;
        Imports = imports;
        Description = description;
        IsPolymorphic = isPolymorphic;
        PolymorphicDiscriminator = polymorphicDiscriminator;
        SerializedName = serializedName;
        NeedsFlatten = needsFlatten;
        ParentModel = parentModel;
        DerivedModels = derivedModels;
        XmlName = xmlName;
        Properties = properties;
    }

    /**
     The package that this model class belongs to.
    */
    private String Package;
    public final String getPackage()
    {
        return Package;
    }

    /**
     Get the name of this model.
    */
    private String Name;
    public final String getName()
    {
        return Name;
    }

    /**
     The full name of this model class (package and name).
    */
    public final String getFullName()
    {
        return String.format("%1$s.%2$s", getPackage(), getName());
    }

    /**
     Get the imports for this model.
    */
    private List<String> Imports;
    public final List<String> getImports()
    {
        return Imports;
    }

    /**
     Get the description of this model.
    */
    private String Description;
    public final String getDescription()
    {
        return Description;
    }

    /**
     Get whether or not this model has model types that derive from it.
    */
    private boolean IsPolymorphic;
    public final boolean getIsPolymorphic()
    {
        return IsPolymorphic;
    }

    /**
     Get the name of the property that determines which polymorphic model type to create.
    */
    private String PolymorphicDiscriminator;
    public final String getPolymorphicDiscriminator()
    {
        return PolymorphicDiscriminator;
    }

    /**
     Get the name that is used for this model when it is serialized.
    */
    private String SerializedName;
    public final String getSerializedName()
    {
        return SerializedName;
    }

    /**
     Get whether or not this model needs serialization flattening.
    */
    private boolean NeedsFlatten;
    public final boolean getNeedsFlatten()
    {
        return NeedsFlatten;
    }

    /**
     Get the parent model of this model.
    */
    private ClientModel ParentModel;
    public final ClientModel getParentModel()
    {
        return ParentModel;
    }

    /**
     Get the models that derive from this model.
    */
    private List<ClientModel> DerivedModels;
    public final List<ClientModel> getDerivedModels()
    {
        return DerivedModels;
    }

    /**
     Get the name that will be used for this model's XML element representation.
    */
    private String XmlName;
    public final String getXmlName()
    {
        return XmlName;
    }

    /**
     Get the properties for this model.
    */
    private List<ClientModelProperty> Properties;
    public final List<ClientModelProperty> getProperties()
    {
        return Properties;
    }

    /**
     Add this ServiceModel's imports to the provided ISet of imports.

     @param imports The set of imports to add to.
     @param settings The settings for this Java generator session.
    */
    public final void AddImportsTo(Set<String> imports, JavaSettings settings)
    {
        imports.add("com.azure.core.implementation.annotation.Fluent");
        for (String import_Keyword : getImports())
        {
            imports.add(import_Keyword);
        }

        if (getParentModel() != null && settings.IsAzureOrFluent)
        {
            if (getParentModel().getName().equals(ClassType.Resource.getName()))
            {
                ClassType.Resource.AddImportsTo(imports, false);
            }
            else if (getParentModel().getName().equals(ClassType.SubResource.getName()))
            {
                ClassType.SubResource.AddImportsTo(imports, false);
            }
            else
            {
                imports.add(getParentModel().getFullName());
            }
        }

        if (getIsPolymorphic())
        {
            imports.add("com.fasterxml.jackson.annotation.JsonTypeInfo");
            imports.add("com.fasterxml.jackson.annotation.JsonTypeName");

            if (getDerivedModels() != null && getDerivedModels().Any())
            {
                imports.add("com.fasterxml.jackson.annotation.JsonSubTypes");
            }
        }

        for (ClientModelProperty property : getProperties())
        {
            property.AddImportsTo(imports, settings);
        }
    }
}