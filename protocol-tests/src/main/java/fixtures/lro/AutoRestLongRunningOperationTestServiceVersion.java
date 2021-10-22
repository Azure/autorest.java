package fixtures.lro;

import com.azure.core.util.ServiceVersion;

/** Service version of AutoRestLongRunningOperationTestService. */
public enum AutoRestLongRunningOperationTestServiceVersion implements ServiceVersion {
    /** Enum value 1.0.0. */
    V1_0_0("1.0.0");

    private final String version;

    AutoRestLongRunningOperationTestServiceVersion(String version) {
        this.version = version;
    }

    @Override
    public String getVersion() {
        return this.version;
    }

    /**
     * Gets the latest service version supported by this client library.
     *
     * @return The latest {@link AutoRestLongRunningOperationTestServiceVersion}.
     */
    public static AutoRestLongRunningOperationTestServiceVersion getLatest() {
        return V1_0_0;
    }
}
