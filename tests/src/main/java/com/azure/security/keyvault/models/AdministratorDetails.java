package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The AdministratorDetails model.
 */
@Fluent
public final class AdministratorDetails {
    /*
     * First name.
     */
    @JsonProperty(value = "first_name")
    private String firstName;

    /*
     * Last name.
     */
    @JsonProperty(value = "last_name")
    private String lastName;

    /*
     * Email address.
     */
    @JsonProperty(value = "email")
    private String email;

    /*
     * Phone number.
     */
    @JsonProperty(value = "phone")
    private String phone;

    /**
     * Get the firstName property: First name.
     * 
     * @return the firstName value.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Set the firstName property: First name.
     * 
     * @param firstName the firstName value to set.
     * @return the AdministratorDetails object itself.
     */
    public AdministratorDetails setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Get the lastName property: Last name.
     * 
     * @return the lastName value.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Set the lastName property: Last name.
     * 
     * @param lastName the lastName value to set.
     * @return the AdministratorDetails object itself.
     */
    public AdministratorDetails setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

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
     * @return the AdministratorDetails object itself.
     */
    public AdministratorDetails setEmail(String email) {
        this.email = email;
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
     * @return the AdministratorDetails object itself.
     */
    public AdministratorDetails setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public void validate() {
    }
}
