package fixtures.bodycomplex;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;

/** Initializes a new instance of the AutoRestComplexTestService type. */
public final class AutoRestComplexTestService {
    /** server parameter. */
    private final String host;

    /**
     * Gets server parameter.
     *
     * @return the host value.
     */
    public String getHost() {
        return this.host;
    }

    /** Api Version. */
    private final String apiVersion;

    /**
     * Gets Api Version.
     *
     * @return the apiVersion value.
     */
    public String getApiVersion() {
        return this.apiVersion;
    }

    /** The HTTP pipeline to send requests through. */
    private final HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     *
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /** The serializer to serialize an object into a string. */
    private final SerializerAdapter serializerAdapter;

    /**
     * Gets The serializer to serialize an object into a string.
     *
     * @return the serializerAdapter value.
     */
    public SerializerAdapter getSerializerAdapter() {
        return this.serializerAdapter;
    }

    /** The Basics object to access its operations. */
    private final Basics basics;

    /**
     * Gets the Basics object to access its operations.
     *
     * @return the Basics object.
     */
    public Basics getBasics() {
        return this.basics;
    }

    /** The Primitives object to access its operations. */
    private final Primitives primitives;

    /**
     * Gets the Primitives object to access its operations.
     *
     * @return the Primitives object.
     */
    public Primitives getPrimitives() {
        return this.primitives;
    }

    /** The Arrays object to access its operations. */
    private final Arrays arrays;

    /**
     * Gets the Arrays object to access its operations.
     *
     * @return the Arrays object.
     */
    public Arrays getArrays() {
        return this.arrays;
    }

    /** The Dictionaries object to access its operations. */
    private final Dictionaries dictionaries;

    /**
     * Gets the Dictionaries object to access its operations.
     *
     * @return the Dictionaries object.
     */
    public Dictionaries getDictionaries() {
        return this.dictionaries;
    }

    /** The Inheritances object to access its operations. */
    private final Inheritances inheritances;

    /**
     * Gets the Inheritances object to access its operations.
     *
     * @return the Inheritances object.
     */
    public Inheritances getInheritances() {
        return this.inheritances;
    }

    /** The Polymorphisms object to access its operations. */
    private final Polymorphisms polymorphisms;

    /**
     * Gets the Polymorphisms object to access its operations.
     *
     * @return the Polymorphisms object.
     */
    public Polymorphisms getPolymorphisms() {
        return this.polymorphisms;
    }

    /** The Polymorphicrecursives object to access its operations. */
    private final Polymorphicrecursives polymorphicrecursives;

    /**
     * Gets the Polymorphicrecursives object to access its operations.
     *
     * @return the Polymorphicrecursives object.
     */
    public Polymorphicrecursives getPolymorphicrecursives() {
        return this.polymorphicrecursives;
    }

    /** The Readonlyproperties object to access its operations. */
    private final Readonlyproperties readonlyproperties;

    /**
     * Gets the Readonlyproperties object to access its operations.
     *
     * @return the Readonlyproperties object.
     */
    public Readonlyproperties getReadonlyproperties() {
        return this.readonlyproperties;
    }

    /** The Flattencomplexes object to access its operations. */
    private final Flattencomplexes flattencomplexes;

    /**
     * Gets the Flattencomplexes object to access its operations.
     *
     * @return the Flattencomplexes object.
     */
    public Flattencomplexes getFlattencomplexes() {
        return this.flattencomplexes;
    }

    /** Initializes an instance of AutoRestComplexTestService client. */
    AutoRestComplexTestService(String host) {
        this(
                new HttpPipelineBuilder()
                        .policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy())
                        .build(),
                JacksonAdapter.createDefaultSerializerAdapter(),
                host);
    }

    /**
     * Initializes an instance of AutoRestComplexTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    AutoRestComplexTestService(HttpPipeline httpPipeline, String host) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), host);
    }

    /**
     * Initializes an instance of AutoRestComplexTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     */
    AutoRestComplexTestService(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String host) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.host = host;
        this.apiVersion = "2016-02-29";
        this.basics = new Basics(this);
        this.primitives = new Primitives(this);
        this.arrays = new Arrays(this);
        this.dictionaries = new Dictionaries(this);
        this.inheritances = new Inheritances(this);
        this.polymorphisms = new Polymorphisms(this);
        this.polymorphicrecursives = new Polymorphicrecursives(this);
        this.readonlyproperties = new Readonlyproperties(this);
        this.flattencomplexes = new Flattencomplexes(this);
    }
}
