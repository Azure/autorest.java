package fixtures.bodycomplex;

import com.azure.core.util.ServiceVersion;

/** Service version of BodyComplex. */
public enum BodyComplexServiceVersion implements ServiceVersion {
    /** Enum value 2016-02-29. */
    V2016_02_29("2016-02-29");

    private final String version;

    BodyComplexServiceVersion(String version) {
        this.version = version;
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    /**
     * Gets the latest service version supported by this client library.
     *
     * @return The latest {@link BodyComplexServiceVersion}.
     */
    public static BodyComplexServiceVersion getLatest() {
        return V2016_02_29;
    }
}
