package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * The KeyCreateParameters model.
 */
@Fluent
public final class KeyCreateParameters {
    /*
     * JsonWebKey Key Type (kty), as defined in
     * https://tools.ietf.org/html/draft-ietf-jose-json-web-algorithms-40.
     */
    @JsonProperty(value = "kty", required = true)
    private JsonWebKeyType kty;

    /*
     * The key size in bits. For example: 2048, 3072, or 4096 for RSA.
     */
    @JsonProperty(value = "key_size")
    private Integer keySize;

    /*
     * MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA
     */
    @JsonProperty(value = "key_ops")
    private List<JsonWebKeyOperation> keyOps;

    /*
     * The attributes of a key managed by the key vault service.
     */
    @JsonProperty(value = "attributes")
    private KeyAttributes keyAttributes;

    /*
     * Application specific metadata in the form of key-value pairs.
     */
    @JsonProperty(value = "tags")
    private Map<String, String> tags;

    /*
     * Elliptic curve name. For valid values, see JsonWebKeyCurveName.
     */
    @JsonProperty(value = "crv")
    private JsonWebKeyCurveName crv;

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
     * @return the KeyCreateParameters object itself.
     */
    public KeyCreateParameters setKty(JsonWebKeyType kty) {
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
     * @return the KeyCreateParameters object itself.
     */
    public KeyCreateParameters setKeySize(Integer keySize) {
        this.keySize = keySize;
        return this;
    }

    /**
     * Get the keyOps property: MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * 
     * @return the keyOps value.
     */
    public List<JsonWebKeyOperation> getKeyOps() {
        return this.keyOps;
    }

    /**
     * Set the keyOps property.
     * 
     * @param keyOps the keyOps value to set.
     * @return the KeyCreateParameters object itself.
     */
    public KeyCreateParameters setKeyOps(List<JsonWebKeyOperation> keyOps) {
        this.keyOps = keyOps;
        return this;
    }

    /**
     * Get the keyAttributes property: The attributes of a key managed by the
     * key vault service.
     * 
     * @return the keyAttributes value.
     */
    public KeyAttributes getKeyAttributes() {
        return this.keyAttributes;
    }

    /**
     * Set the keyAttributes property: The attributes of a key managed by the
     * key vault service.
     * 
     * @param keyAttributes the keyAttributes value to set.
     * @return the KeyCreateParameters object itself.
     */
    public KeyCreateParameters setKeyAttributes(KeyAttributes keyAttributes) {
        this.keyAttributes = keyAttributes;
        return this;
    }

    /**
     * Get the tags property: Application specific metadata in the form of
     * key-value pairs.
     * 
     * @return the tags value.
     */
    public Map<String, String> getTags() {
        return this.tags;
    }

    /**
     * Set the tags property: Application specific metadata in the form of
     * key-value pairs.
     * 
     * @param tags the tags value to set.
     * @return the KeyCreateParameters object itself.
     */
    public KeyCreateParameters setTags(Map<String, String> tags) {
        this.tags = tags;
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
     * @return the KeyCreateParameters object itself.
     */
    public KeyCreateParameters setCrv(JsonWebKeyCurveName crv) {
        this.crv = crv;
        return this;
    }

    public void validate() {
        if (getKty() == null) {
            throw new IllegalArgumentException("Missing required property kty in model KeyCreateParameters");
        }
        if (getKeyAttributes() != null) {
            getKeyAttributes().validate();
        }
    }
}
