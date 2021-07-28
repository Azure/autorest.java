package fixtures.discriminatorflattening.clientflatten.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Link to an application package inside the batch account. */
@Fluent
public final class ApplicationPackageReference {
    /*
     * The ID of the application package to install. This must be inside the
     * same batch account as the pool. This can either be a reference to a
     * specific version or the default version if one exists.
     */
    @JsonProperty(value = "id", required = true)
    private String id;

    /*
     * The version of the application to deploy. If omitted, the default
     * version is deployed. If this is omitted, and no default version is
     * specified for this application, the request fails with the error code
     * InvalidApplicationPackageReferences. If you are calling the REST API
     * directly, the HTTP status code is 409.
     */
    @JsonProperty(value = "version")
    private String version;

    /**
     * Get the id property: The ID of the application package to install. This must be inside the same batch account as
     * the pool. This can either be a reference to a specific version or the default version if one exists.
     *
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set the id property: The ID of the application package to install. This must be inside the same batch account as
     * the pool. This can either be a reference to a specific version or the default version if one exists.
     *
     * @param id the id value to set.
     * @return the ApplicationPackageReference object itself.
     */
    public ApplicationPackageReference setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get the version property: The version of the application to deploy. If omitted, the default version is deployed.
     * If this is omitted, and no default version is specified for this application, the request fails with the error
     * code InvalidApplicationPackageReferences. If you are calling the REST API directly, the HTTP status code is 409.
     *
     * @return the version value.
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Set the version property: The version of the application to deploy. If omitted, the default version is deployed.
     * If this is omitted, and no default version is specified for this application, the request fails with the error
     * code InvalidApplicationPackageReferences. If you are calling the REST API directly, the HTTP status code is 409.
     *
     * @param version the version value to set.
     * @return the ApplicationPackageReference object itself.
     */
    public ApplicationPackageReference setVersion(String version) {
        this.version = version;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getId() == null) {
            throw new IllegalArgumentException("Missing required property id in model ApplicationPackageReference");
        }
    }
}
