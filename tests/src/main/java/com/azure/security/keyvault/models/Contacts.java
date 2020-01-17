package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The Contacts model.
 */
@Fluent
public final class Contacts {
    /*
     * Identifier for the contacts collection.
     */
    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    /*
     * The contact list for the vault certificates.
     */
    @JsonProperty(value = "contacts")
    private List<Contact> contacts;

    /**
     * Get the id property: Identifier for the contacts collection.
     * 
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get the contacts property: The contact list for the vault certificates.
     * 
     * @return the contacts value.
     */
    public List<Contact> getContacts() {
        return this.contacts;
    }

    /**
     * Set the contacts property: The contact list for the vault certificates.
     * 
     * @param contacts the contacts value to set.
     * @return the Contacts object itself.
     */
    public Contacts setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        return this;
    }

    public void validate() {
        if (getContacts() != null) {
            getContacts().forEach(e -> e.validate());
        }
    }
}
