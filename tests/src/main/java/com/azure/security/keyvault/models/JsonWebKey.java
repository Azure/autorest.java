package com.azure.security.keyvault.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.Base64Url;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The JsonWebKey model.
 */
@Fluent
public final class JsonWebKey {
    /*
     * Key identifier.
     */
    @JsonProperty(value = "kid")
    private String kid;

    /*
     * JsonWebKey Key Type (kty), as defined in
     * https://tools.ietf.org/html/draft-ietf-jose-json-web-algorithms-40.
     */
    @JsonProperty(value = "kty")
    private JsonWebKeyType kty;

    /*
     * MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA
     */
    @JsonProperty(value = "key_ops")
    private List<String> keyOps;

    /*
     * RSA modulus.
     */
    @JsonProperty(value = "n")
    private Base64Url n;

    /*
     * RSA public exponent.
     */
    @JsonProperty(value = "e")
    private Base64Url e;

    /*
     * RSA private exponent, or the D component of an EC private key.
     */
    @JsonProperty(value = "d")
    private Base64Url d;

    /*
     * RSA private key parameter.
     */
    @JsonProperty(value = "dp")
    private Base64Url dp;

    /*
     * RSA private key parameter.
     */
    @JsonProperty(value = "dq")
    private Base64Url dq;

    /*
     * RSA private key parameter.
     */
    @JsonProperty(value = "qi")
    private Base64Url qi;

    /*
     * RSA secret prime.
     */
    @JsonProperty(value = "p")
    private Base64Url p;

    /*
     * RSA secret prime, with p < q.
     */
    @JsonProperty(value = "q")
    private Base64Url q;

    /*
     * Symmetric key.
     */
    @JsonProperty(value = "k")
    private Base64Url k;

    /*
     * HSM Token, used with 'Bring Your Own Key'.
     */
    @JsonProperty(value = "key_hsm")
    private Base64Url keyHsm;

    /*
     * Elliptic curve name. For valid values, see JsonWebKeyCurveName.
     */
    @JsonProperty(value = "crv")
    private JsonWebKeyCurveName crv;

    /*
     * X component of an EC public key.
     */
    @JsonProperty(value = "x")
    private Base64Url x;

    /*
     * Y component of an EC public key.
     */
    @JsonProperty(value = "y")
    private Base64Url y;

    /**
     * Get the kid property: Key identifier.
     * 
     * @return the kid value.
     */
    public String getKid() {
        return this.kid;
    }

    /**
     * Set the kid property: Key identifier.
     * 
     * @param kid the kid value to set.
     * @return the JsonWebKey object itself.
     */
    public JsonWebKey setKid(String kid) {
        this.kid = kid;
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
     * @return the JsonWebKey object itself.
     */
    public JsonWebKey setKty(JsonWebKeyType kty) {
        this.kty = kty;
        return this;
    }

    /**
     * Get the keyOps property: MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * 
     * @return the keyOps value.
     */
    public List<String> getKeyOps() {
        return this.keyOps;
    }

    /**
     * Set the keyOps property.
     * 
     * @param keyOps the keyOps value to set.
     * @return the JsonWebKey object itself.
     */
    public JsonWebKey setKeyOps(List<String> keyOps) {
        this.keyOps = keyOps;
        return this;
    }

    /**
     * Get the n property: RSA modulus.
     * 
     * @return the n value.
     */
    public byte[] getN() {
        if (this.n == null) {
            return null;
        }
        return this.n.decodedBytes();
    }

    /**
     * Set the n property: RSA modulus.
     * 
     * @param n the n value to set.
     * @return the JsonWebKey object itself.
     */
    public JsonWebKey setN(byte[] n) {
        if (n == null) {
            this.n = null;
        } else {
            this.n = Base64Url.encode(CoreUtils.clone(n));
        }
        return this;
    }

    /**
     * Get the e property: RSA public exponent.
     * 
     * @return the e value.
     */
    public byte[] getE() {
        if (this.e == null) {
            return null;
        }
        return this.e.decodedBytes();
    }

    /**
     * Set the e property: RSA public exponent.
     * 
     * @param e the e value to set.
     * @return the JsonWebKey object itself.
     */
    public JsonWebKey setE(byte[] e) {
        if (e == null) {
            this.e = null;
        } else {
            this.e = Base64Url.encode(CoreUtils.clone(e));
        }
        return this;
    }

    /**
     * Get the d property: RSA private exponent, or the D component of an EC
     * private key.
     * 
     * @return the d value.
     */
    public byte[] getD() {
        if (this.d == null) {
            return null;
        }
        return this.d.decodedBytes();
    }

    /**
     * Set the d property: RSA private exponent, or the D component of an EC
     * private key.
     * 
     * @param d the d value to set.
     * @return the JsonWebKey object itself.
     */
    public JsonWebKey setD(byte[] d) {
        if (d == null) {
            this.d = null;
        } else {
            this.d = Base64Url.encode(CoreUtils.clone(d));
        }
        return this;
    }

    /**
     * Get the dp property: RSA private key parameter.
     * 
     * @return the dp value.
     */
    public byte[] getDp() {
        if (this.dp == null) {
            return null;
        }
        return this.dp.decodedBytes();
    }

    /**
     * Set the dp property: RSA private key parameter.
     * 
     * @param dp the dp value to set.
     * @return the JsonWebKey object itself.
     */
    public JsonWebKey setDp(byte[] dp) {
        if (dp == null) {
            this.dp = null;
        } else {
            this.dp = Base64Url.encode(CoreUtils.clone(dp));
        }
        return this;
    }

    /**
     * Get the dq property: RSA private key parameter.
     * 
     * @return the dq value.
     */
    public byte[] getDq() {
        if (this.dq == null) {
            return null;
        }
        return this.dq.decodedBytes();
    }

    /**
     * Set the dq property: RSA private key parameter.
     * 
     * @param dq the dq value to set.
     * @return the JsonWebKey object itself.
     */
    public JsonWebKey setDq(byte[] dq) {
        if (dq == null) {
            this.dq = null;
        } else {
            this.dq = Base64Url.encode(CoreUtils.clone(dq));
        }
        return this;
    }

    /**
     * Get the qi property: RSA private key parameter.
     * 
     * @return the qi value.
     */
    public byte[] getQi() {
        if (this.qi == null) {
            return null;
        }
        return this.qi.decodedBytes();
    }

    /**
     * Set the qi property: RSA private key parameter.
     * 
     * @param qi the qi value to set.
     * @return the JsonWebKey object itself.
     */
    public JsonWebKey setQi(byte[] qi) {
        if (qi == null) {
            this.qi = null;
        } else {
            this.qi = Base64Url.encode(CoreUtils.clone(qi));
        }
        return this;
    }

    /**
     * Get the p property: RSA secret prime.
     * 
     * @return the p value.
     */
    public byte[] getP() {
        if (this.p == null) {
            return null;
        }
        return this.p.decodedBytes();
    }

    /**
     * Set the p property: RSA secret prime.
     * 
     * @param p the p value to set.
     * @return the JsonWebKey object itself.
     */
    public JsonWebKey setP(byte[] p) {
        if (p == null) {
            this.p = null;
        } else {
            this.p = Base64Url.encode(CoreUtils.clone(p));
        }
        return this;
    }

    /**
     * Get the q property: RSA secret prime, with p &lt; q.
     * 
     * @return the q value.
     */
    public byte[] getQ() {
        if (this.q == null) {
            return null;
        }
        return this.q.decodedBytes();
    }

    /**
     * Set the q property: RSA secret prime, with p &lt; q.
     * 
     * @param q the q value to set.
     * @return the JsonWebKey object itself.
     */
    public JsonWebKey setQ(byte[] q) {
        if (q == null) {
            this.q = null;
        } else {
            this.q = Base64Url.encode(CoreUtils.clone(q));
        }
        return this;
    }

    /**
     * Get the k property: Symmetric key.
     * 
     * @return the k value.
     */
    public byte[] getK() {
        if (this.k == null) {
            return null;
        }
        return this.k.decodedBytes();
    }

    /**
     * Set the k property: Symmetric key.
     * 
     * @param k the k value to set.
     * @return the JsonWebKey object itself.
     */
    public JsonWebKey setK(byte[] k) {
        if (k == null) {
            this.k = null;
        } else {
            this.k = Base64Url.encode(CoreUtils.clone(k));
        }
        return this;
    }

    /**
     * Get the keyHsm property: HSM Token, used with 'Bring Your Own Key'.
     * 
     * @return the keyHsm value.
     */
    public byte[] getKeyHsm() {
        if (this.keyHsm == null) {
            return null;
        }
        return this.keyHsm.decodedBytes();
    }

    /**
     * Set the keyHsm property: HSM Token, used with 'Bring Your Own Key'.
     * 
     * @param keyHsm the keyHsm value to set.
     * @return the JsonWebKey object itself.
     */
    public JsonWebKey setKeyHsm(byte[] keyHsm) {
        if (keyHsm == null) {
            this.keyHsm = null;
        } else {
            this.keyHsm = Base64Url.encode(CoreUtils.clone(keyHsm));
        }
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
     * @return the JsonWebKey object itself.
     */
    public JsonWebKey setCrv(JsonWebKeyCurveName crv) {
        this.crv = crv;
        return this;
    }

    /**
     * Get the x property: X component of an EC public key.
     * 
     * @return the x value.
     */
    public byte[] getX() {
        if (this.x == null) {
            return null;
        }
        return this.x.decodedBytes();
    }

    /**
     * Set the x property: X component of an EC public key.
     * 
     * @param x the x value to set.
     * @return the JsonWebKey object itself.
     */
    public JsonWebKey setX(byte[] x) {
        if (x == null) {
            this.x = null;
        } else {
            this.x = Base64Url.encode(CoreUtils.clone(x));
        }
        return this;
    }

    /**
     * Get the y property: Y component of an EC public key.
     * 
     * @return the y value.
     */
    public byte[] getY() {
        if (this.y == null) {
            return null;
        }
        return this.y.decodedBytes();
    }

    /**
     * Set the y property: Y component of an EC public key.
     * 
     * @param y the y value to set.
     * @return the JsonWebKey object itself.
     */
    public JsonWebKey setY(byte[] y) {
        if (y == null) {
            this.y = null;
        } else {
            this.y = Base64Url.encode(CoreUtils.clone(y));
        }
        return this;
    }

    public void validate() {
    }
}
