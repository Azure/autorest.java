package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The IssuerCredentials model.
 */
@Fluent
public final class IssuerCredentials {
    /*
     * The user name/account name/account id.
     */
    @JsonProperty(value = "account_id")
    private String accountId;

    /*
     * The password/secret/account key.
     */
    @JsonProperty(value = "pwd")
    private String pwd;

    /**
     * Get the accountId property: The user name/account name/account id.
     * 
     * @return the accountId value.
     */
    public String getAccountId() {
        return this.accountId;
    }

    /**
     * Set the accountId property: The user name/account name/account id.
     * 
     * @param accountId the accountId value to set.
     * @return the IssuerCredentials object itself.
     */
    public IssuerCredentials setAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    /**
     * Get the pwd property: The password/secret/account key.
     * 
     * @return the pwd value.
     */
    public String getPwd() {
        return this.pwd;
    }

    /**
     * Set the pwd property: The password/secret/account key.
     * 
     * @param pwd the pwd value to set.
     * @return the IssuerCredentials object itself.
     */
    public IssuerCredentials setPwd(String pwd) {
        this.pwd = pwd;
        return this;
    }

    public void validate() {
    }
}
