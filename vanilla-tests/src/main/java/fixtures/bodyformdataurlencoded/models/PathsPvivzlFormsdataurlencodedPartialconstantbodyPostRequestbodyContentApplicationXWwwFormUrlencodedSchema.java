package fixtures.bodyformdataurlencoded.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema model.
 */
@Fluent
public final
class PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema {
    /*
     * Constant part of a formdata body.
     */
    @JsonProperty(value = "grant_type", required = true)
    private String grantType = "access_token";

    /*
     * Indicates the name of your Azure container registry.
     */
    @JsonProperty(value = "service", required = true)
    private String service;

    /*
     * AAD access token, mandatory when grant_type is
     * access_token_refresh_token or access_token.
     */
    @JsonProperty(value = "access_token", required = true)
    private String aadAccessToken;

    /**
     * Creates an instance of
     * PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema class.
     */
    public
    PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema() {
        grantType = "access_token";
    }

    /**
     * Get the grantType property: Constant part of a formdata body.
     *
     * @return the grantType value.
     */
    public String getGrantType() {
        return this.grantType;
    }

    /**
     * Set the grantType property: Constant part of a formdata body.
     *
     * @param grantType the grantType value to set.
     * @return the
     *     PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
     *     object itself.
     */
    public PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
            setGrantType(String grantType) {
        this.grantType = grantType;
        return this;
    }

    /**
     * Get the service property: Indicates the name of your Azure container registry.
     *
     * @return the service value.
     */
    public String getService() {
        return this.service;
    }

    /**
     * Set the service property: Indicates the name of your Azure container registry.
     *
     * @param service the service value to set.
     * @return the
     *     PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
     *     object itself.
     */
    public PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
            setService(String service) {
        this.service = service;
        return this;
    }

    /**
     * Get the aadAccessToken property: AAD access token, mandatory when grant_type is access_token_refresh_token or
     * access_token.
     *
     * @return the aadAccessToken value.
     */
    public String getAadAccessToken() {
        return this.aadAccessToken;
    }

    /**
     * Set the aadAccessToken property: AAD access token, mandatory when grant_type is access_token_refresh_token or
     * access_token.
     *
     * @param aadAccessToken the aadAccessToken value to set.
     * @return the
     *     PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
     *     object itself.
     */
    public PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema
            setAadAccessToken(String aadAccessToken) {
        this.aadAccessToken = aadAccessToken;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getService() == null) {
            throw new IllegalArgumentException(
                    "Missing required property service in model PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema");
        }
        if (getAadAccessToken() == null) {
            throw new IllegalArgumentException(
                    "Missing required property aadAccessToken in model PathsPvivzlFormsdataurlencodedPartialconstantbodyPostRequestbodyContentApplicationXWwwFormUrlencodedSchema");
        }
    }
}
