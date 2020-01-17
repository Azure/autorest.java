package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Contact model.
 */
@Fluent
public final class Contact {
    /*
     * Email address.
     */
    @JsonProperty(value = "email")
    private String email;

    /*
     * Name.
     */
    @JsonProperty(value = "name")
    private String name;

    /*
     * Phone number.
     */
    @JsonProperty(value = "phone")
    private String phone;

    /**
     * Get the email property: Email address.
     * 
     * @return the email value.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Set the email property: Email address.
     * 
     * @param email the email value to set.
     * @return the Contact object itself.
     */
    public Contact setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Get the name property: Name.
     * 
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: Name.
     * 
     * @param name the name value to set.
     * @return the Contact object itself.
     */
    public Contact setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the phone property: Phone number.
     * 
     * @return the phone value.
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Set the phone property: Phone number.
     * 
     * @param phone the phone value to set.
     * @return the Contact object itself.
     */
    public Contact setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public void validate() {
    }
}
