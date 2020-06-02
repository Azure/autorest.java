package fixtures.bodycomplex;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/** Initializes a new instance of the AutoRestComplexTestService type. */
public final class AutoRestComplexTestService {
    /** server parameter. */
    private String host;

    /**
     * Gets server parameter.
     *
     * @return the host value.
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Sets server parameter.
     *
     * @param host the host value.
     * @return the service client itself.
     */
    AutoRestComplexTestService setHost(String host) {
        this.host = host;
        return this;
    }

    /** Api Version. */
    private String apiVersion;

    /**
     * Gets Api Version.
     *
     * @return the apiVersion value.
     */
    public String getApiVersion() {
        return this.apiVersion;
    }

    /**
     * Sets Api Version.
     *
     * @param apiVersion the apiVersion value.
     * @return the service client itself.
     */
    AutoRestComplexTestService setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
        return this;
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

    /** The Dictionarys object to access its operations. */
    private final Dictionarys dictionarys;

    /**
     * Gets the Dictionarys object to access its operations.
     *
     * @return the Dictionarys object.
     */
    public Dictionarys getDictionarys() {
        return this.dictionarys;
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

    /** The Readonlypropertys object to access its operations. */
    private final Readonlypropertys readonlypropertys;

    /**
     * Gets the Readonlypropertys object to access its operations.
     *
     * @return the Readonlypropertys object.
     */
    public Readonlypropertys getReadonlypropertys() {
        return this.readonlypropertys;
    }

    /** The Flattencomplexs object to access its operations. */
    private final Flattencomplexs flattencomplexs;

    /**
     * Gets the Flattencomplexs object to access its operations.
     *
     * @return the Flattencomplexs object.
     */
    public Flattencomplexs getFlattencomplexs() {
        return this.flattencomplexs;
    }

    /** Initializes an instance of AutoRestComplexTestService client. */
    AutoRestComplexTestService() {
        this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build());
    }

    /**
     * Initializes an instance of AutoRestComplexTestService client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    AutoRestComplexTestService(HttpPipeline httpPipeline) {
        this.httpPipeline = httpPipeline;
        this.basics = new Basics(this);
        this.primitives = new Primitives(this);
        this.arrays = new Arrays(this);
        this.dictionarys = new Dictionarys(this);
        this.inheritances = new Inheritances(this);
        this.polymorphisms = new Polymorphisms(this);
        this.polymorphicrecursives = new Polymorphicrecursives(this);
        this.readonlypropertys = new Readonlypropertys(this);
        this.flattencomplexs = new Flattencomplexs(this);
    }
}
