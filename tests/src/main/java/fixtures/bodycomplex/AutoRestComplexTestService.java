package fixtures.bodycomplex;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;

/**
 * Initializes a new instance of the AutoRestComplexTestService type.
 */
public final class AutoRestComplexTestService {
    /**
     * http://localhost:3000.
     */
    private String host;

    /**
     * Gets http://localhost:3000.
     * 
     * @return the host value.
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Sets http://localhost:3000.
     * 
     * @param host the host value.
     * @return the service client itself.
     */
    AutoRestComplexTestService setHost(String host) {
        this.host = host;
        return this;
    }

    /**
     */
    private String apiVersion;

    /**
     * Gets null.
     * 
     * @return the apiVersion value.
     */
    public String getApiVersion() {
        return this.apiVersion;
    }

    /**
     * Sets null.
     * 
     * @param apiVersion the apiVersion value.
     * @return the service client itself.
     */
    AutoRestComplexTestService setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }

    /**
     * The HTTP pipeline to send requests through.
     */
    private HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     * 
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /**
     * The Basics object to access its operations.
     */
    private Basics basics;

    /**
     * Gets the Basics object to access its operations.
     * 
     * @return the Basics object.
     */
    public Basics basics() {
        return this.basics;
    }

    /**
     * The Primitives object to access its operations.
     */
    private Primitives primitives;

    /**
     * Gets the Primitives object to access its operations.
     * 
     * @return the Primitives object.
     */
    public Primitives primitives() {
        return this.primitives;
    }

    /**
     * The Arrays object to access its operations.
     */
    private Arrays arrays;

    /**
     * Gets the Arrays object to access its operations.
     * 
     * @return the Arrays object.
     */
    public Arrays arrays() {
        return this.arrays;
    }

    /**
     * The Dictionarys object to access its operations.
     */
    private Dictionarys dictionarys;

    /**
     * Gets the Dictionarys object to access its operations.
     * 
     * @return the Dictionarys object.
     */
    public Dictionarys dictionarys() {
        return this.dictionarys;
    }

    /**
     * The Inheritances object to access its operations.
     */
    private Inheritances inheritances;

    /**
     * Gets the Inheritances object to access its operations.
     * 
     * @return the Inheritances object.
     */
    public Inheritances inheritances() {
        return this.inheritances;
    }

    /**
     * The Polymorphisms object to access its operations.
     */
    private Polymorphisms polymorphisms;

    /**
     * Gets the Polymorphisms object to access its operations.
     * 
     * @return the Polymorphisms object.
     */
    public Polymorphisms polymorphisms() {
        return this.polymorphisms;
    }

    /**
     * The Polymorphicrecursives object to access its operations.
     */
    private Polymorphicrecursives polymorphicrecursives;

    /**
     * Gets the Polymorphicrecursives object to access its operations.
     * 
     * @return the Polymorphicrecursives object.
     */
    public Polymorphicrecursives polymorphicrecursives() {
        return this.polymorphicrecursives;
    }

    /**
     * The Readonlypropertys object to access its operations.
     */
    private Readonlypropertys readonlypropertys;

    /**
     * Gets the Readonlypropertys object to access its operations.
     * 
     * @return the Readonlypropertys object.
     */
    public Readonlypropertys readonlypropertys() {
        return this.readonlypropertys;
    }

    /**
     * The Flattencomplexs object to access its operations.
     */
    private Flattencomplexs flattencomplexs;

    /**
     * Gets the Flattencomplexs object to access its operations.
     * 
     * @return the Flattencomplexs object.
     */
    public Flattencomplexs flattencomplexs() {
        return this.flattencomplexs;
    }

    /**
     * Initializes an instance of AutoRestComplexTestService client.
     */
    public AutoRestComplexTestService() {
        new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();
    }

    /**
     * Initializes an instance of AutoRestComplexTestService client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     */
    public AutoRestComplexTestService(HttpPipeline httpPipeline) {
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
