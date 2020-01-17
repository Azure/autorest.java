package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The OrganizationDetails model.
 */
@Fluent
public final class OrganizationDetails {
    /*
     * Id of the organization.
     */
    @JsonProperty(value = "id")
    private String id;

    /*
     * Details of the organization administrator.
     */
    @JsonProperty(value = "admin_details")
    private List<AdministratorDetails> adminDetails;

    /**
     * Get the id property: Id of the organization.
     * 
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set the id property: Id of the organization.
     * 
     * @param id the id value to set.
     * @return the OrganizationDetails object itself.
     */
    public OrganizationDetails setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get the adminDetails property: Details of the organization
     * administrator.
     * 
     * @return the adminDetails value.
     */
    public List<AdministratorDetails> getAdminDetails() {
        return this.adminDetails;
    }

    /**
     * Set the adminDetails property: Details of the organization
     * administrator.
     * 
     * @param adminDetails the adminDetails value to set.
     * @return the OrganizationDetails object itself.
     */
    public OrganizationDetails setAdminDetails(List<AdministratorDetails> adminDetails) {
        this.adminDetails = adminDetails;
        return this;
    }

    public void validate() {
        if (getAdminDetails() != null) {
            getAdminDetails().forEach(e -> e.validate());
        }
    }
}
