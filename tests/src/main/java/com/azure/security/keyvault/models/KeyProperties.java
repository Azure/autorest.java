package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The KeyProperties model.
 */
@Fluent
public final class KeyProperties {
    /*
     * Indicates if the private key can be exported.
     */
    @JsonProperty(value = "exportable")
    private Boolean exportable;

    /*
     * JsonWebKey Key Type (kty), as defined in
     * https://tools.ietf.org/html/draft-ietf-jose-json-web-algorithms-40.
     */
    @JsonProperty(value = "kty")
    private JsonWebKeyType kty;

    /*
     * The key size in bits. For example: 2048, 3072, or 4096 for RSA.
     */
    @JsonProperty(value = "key_size")
    private Integer keySize;

    /*
     * Indicates if the same key pair will be used on certificate renewal.
     */
    @JsonProperty(value = "reuse_key")
    private Boolean reuseKey;

    /*
     * Elliptic curve name. For valid values, see JsonWebKeyCurveName.
     */
    @JsonProperty(value = "crv")
    private JsonWebKeyCurveName crv;

    /**
     * Get the exportable property: Indicates if the private key can be
     * exported.
     * 
     * @return the exportable value.
     */
    public Boolean isExportable() {
        return this.exportable;
    }

    /**
     * Set the exportable property: Indicates if the private key can be
     * exported.
     * 
     * @param exportable the exportable value to set.
     * @return the KeyProperties object itself.
     */
    public KeyProperties setExportable(Boolean exportable) {
        this.exportable = exportable;
        return this;
    }

    /**
     * Get the kty property: JsonWebKey Key Type (kty), as defined in
     * https://tools.ietf.org/html/draft-ietf-jose-json-web-algorithms-40.
     * 
     * @return the kty value.
     */
    public JsonWebKeyType getKty() {
        return this.kty;
    }

    /**
     * Set the kty property: JsonWebKey Key Type (kty), as defined in
     * https://tools.ietf.org/html/draft-ietf-jose-json-web-algorithms-40.
     * 
     * @param kty the kty value to set.
     * @return the KeyProperties object itself.
     */
    public KeyProperties setKty(JsonWebKeyType kty) {
        this.kty = kty;
        return this;
    }

    /**
     * Get the keySize property: The key size in bits. For example: 2048, 3072,
     * or 4096 for RSA.
     * 
     * @return the keySize value.
     */
    public Integer getKeySize() {
        return this.keySize;
    }

    /**
     * Set the keySize property: The key size in bits. For example: 2048, 3072,
     * or 4096 for RSA.
     * 
     * @param keySize the keySize value to set.
     * @return the KeyProperties object itself.
     */
    public KeyProperties setKeySize(Integer keySize) {
        this.keySize = keySize;
        return this;
    }

    /**
     * Get the reuseKey property: Indicates if the same key pair will be used
     * on certificate renewal.
     * 
     * @return the reuseKey value.
     */
    public Boolean isReuseKey() {
        return this.reuseKey;
    }

    /**
     * Set the reuseKey property: Indicates if the same key pair will be used
     * on certificate renewal.
     * 
     * @param reuseKey the reuseKey value to set.
     * @return the KeyProperties object itself.
     */
    public KeyProperties setReuseKey(Boolean reuseKey) {
        this.reuseKey = reuseKey;
        return this;
    }

    /**
     * Get the crv property: Elliptic curve name. For valid values, see
     * JsonWebKeyCurveName.
     * 
     * @return the crv value.
     */
    public JsonWebKeyCurveName getCrv() {
        return this.crv;
    }

    /**
     * Set the crv property: Elliptic curve name. For valid values, see
     * JsonWebKeyCurveName.
     * 
     * @param crv the crv value to set.
     * @return the KeyProperties object itself.
     */
    public KeyProperties setCrv(JsonWebKeyCurveName crv) {
        this.crv = crv;
        return this;
    }

    public void validate() {
    }
}
