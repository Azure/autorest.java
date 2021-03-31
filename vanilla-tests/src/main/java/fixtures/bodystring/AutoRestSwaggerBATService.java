package fixtures.bodystring;

import com.azure.android.core.http.HttpPipeline;
import com.azure.core.util.serializer.SerializerAdapter;

/** The interface for AutoRestSwaggerBATService class. */
public interface AutoRestSwaggerBATService {
    /**
     * Gets server parameter.
     *
     * @return the host value.
     */
    String getHost();

    /**
     * Gets The HTTP pipeline to send requests through.
     *
     * @return the httpPipeline value.
     */
    HttpPipeline getHttpPipeline();

    /**
     * Gets The serializer to serialize an object into a string.
     *
     * @return the serializerAdapter value.
     */
    SerializerAdapter getSerializerAdapter();

    /**
     * Gets the StringOperations object to access its operations.
     *
     * @return the StringOperations object.
     */
    StringOperations getStringOperations();

    /**
     * Gets the Enums object to access its operations.
     *
     * @return the Enums object.
     */
    Enums getEnums();
}
