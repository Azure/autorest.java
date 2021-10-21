package fixtures.header;

import com.azure.core.util.ServiceVersion;

/** Service version of AutoRestSwaggerBATHeaderService. */
public enum AutoRestSwaggerBATHeaderServiceVersion implements ServiceVersion {
    /** Enum value 1.0.0. */
    V1_0_0("1.0.0");

    private final String version;

    AutoRestSwaggerBATHeaderServiceVersion(String version) {
        this.version = version;
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    /**
     * Gets the latest service version supported by this client library.
     *
     * @return The latest {@link AutoRestSwaggerBATHeaderServiceVersion}.
     */
    public static AutoRestSwaggerBATHeaderServiceVersion getLatest() {
        return V1_0_0;
    }
}
