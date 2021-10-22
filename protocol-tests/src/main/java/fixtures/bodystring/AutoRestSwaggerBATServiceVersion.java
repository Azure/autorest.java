package fixtures.bodystring;

import com.azure.core.util.ServiceVersion;

/** Service version of AutoRestSwaggerBATService. */
public enum AutoRestSwaggerBATServiceVersion implements ServiceVersion {
    /** Enum value 1.0.0. */
    V1_0_0("1.0.0");

    private final String version;

    AutoRestSwaggerBATServiceVersion(String version) {
        this.version = version;
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    /**
     * Gets the latest service version supported by this client library.
     *
     * @return The latest {@link AutoRestSwaggerBATServiceVersion}.
     */
    public static AutoRestSwaggerBATServiceVersion getLatest() {
        return V1_0_0;
    }
}
