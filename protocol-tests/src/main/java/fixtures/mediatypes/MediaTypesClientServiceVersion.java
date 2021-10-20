package fixtures.mediatypes;

import com.azure.core.util.ServiceVersion;

/** Service version of MediaTypesClient. */
public enum MediaTypesClientServiceVersion implements ServiceVersion {
    /** Enum value 2.0-preview. */
    V2_0_PREVIEW("2.0-preview");

    private final String version;

    MediaTypesClientServiceVersion(String version) {
        this.version = version;
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    /**
     * Gets the latest service version supported by this client library.
     *
     * @return The latest {@link MediaTypesClientServiceVersion}.
     */
    public static MediaTypesClientServiceVersion getLatest() {
        return V2_0_PREVIEW;
    }
}
