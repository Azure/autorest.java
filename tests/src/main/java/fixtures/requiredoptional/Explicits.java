package fixtures.requiredoptional;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.serializer.CollectionFormat;
import com.azure.core.util.serializer.JacksonAdapter;
import fixtures.requiredoptional.models.ArrayOptionalWrapper;
import fixtures.requiredoptional.models.ArrayWrapper;
import fixtures.requiredoptional.models.ClassOptionalWrapper;
import fixtures.requiredoptional.models.ClassWrapper;
import fixtures.requiredoptional.models.ErrorException;
import fixtures.requiredoptional.models.IntOptionalWrapper;
import fixtures.requiredoptional.models.IntWrapper;
import fixtures.requiredoptional.models.Product;
import fixtures.requiredoptional.models.StringOptionalWrapper;
import fixtures.requiredoptional.models.StringWrapper;
import java.util.List;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in
 * Explicits.
 */
public final class Explicits {
    /**
     * The proxy service used to perform REST calls.
     */
    private ExplicitsService service;

    /**
     * The service client containing this operation class.
     */
    private AutoRestRequiredOptionalTestService client;

    /**
     * Initializes an instance of Explicits.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    public Explicits(AutoRestRequiredOptionalTestService client) {
        this.service = RestProxy.create(ExplicitsService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for
     * AutoRestRequiredOptionalTestServiceExplicits to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "AutoRestRequiredOptionalTestServiceExplicits")
    private interface ExplicitsService {
        @Post("/reqopt/requied/integer/parameter")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postRequiredIntegerParameter(@HostParam("$host") String host, @BodyParam("application/json") int bodyParameter);

        @Post("/reqopt/optional/integer/parameter")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postOptionalIntegerParameter(@HostParam("$host") String host, @BodyParam("application/json") Integer bodyParameter);

        @Post("/reqopt/requied/integer/property")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postRequiredIntegerProperty(@HostParam("$host") String host, @BodyParam("application/json") IntWrapper bodyParameter);

        @Post("/reqopt/optional/integer/property")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postOptionalIntegerProperty(@HostParam("$host") String host, @BodyParam("application/json") IntOptionalWrapper bodyParameter);

        @Post("/reqopt/requied/integer/header")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postRequiredIntegerHeader(@HostParam("$host") String host, @HeaderParam("headerParameter") int headerParameter);

        @Post("/reqopt/optional/integer/header")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postOptionalIntegerHeader(@HostParam("$host") String host, @HeaderParam("headerParameter") Integer headerParameter);

        @Post("/reqopt/requied/string/parameter")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postRequiredStringParameter(@HostParam("$host") String host, @BodyParam("application/json") String bodyParameter);

        @Post("/reqopt/optional/string/parameter")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postOptionalStringParameter(@HostParam("$host") String host, @BodyParam("application/json") String bodyParameter);

        @Post("/reqopt/requied/string/property")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postRequiredStringProperty(@HostParam("$host") String host, @BodyParam("application/json") StringWrapper bodyParameter);

        @Post("/reqopt/optional/string/property")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postOptionalStringProperty(@HostParam("$host") String host, @BodyParam("application/json") StringOptionalWrapper bodyParameter);

        @Post("/reqopt/requied/string/header")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postRequiredStringHeader(@HostParam("$host") String host, @HeaderParam("headerParameter") String headerParameter);

        @Post("/reqopt/optional/string/header")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postOptionalStringHeader(@HostParam("$host") String host, @HeaderParam("bodyParameter") String bodyParameter);

        @Post("/reqopt/requied/class/parameter")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postRequiredClassParameter(@HostParam("$host") String host, @BodyParam("application/json") Product bodyParameter);

        @Post("/reqopt/optional/class/parameter")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postOptionalClassParameter(@HostParam("$host") String host, @BodyParam("application/json") Product bodyParameter);

        @Post("/reqopt/requied/class/property")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postRequiredClassProperty(@HostParam("$host") String host, @BodyParam("application/json") ClassWrapper bodyParameter);

        @Post("/reqopt/optional/class/property")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postOptionalClassProperty(@HostParam("$host") String host, @BodyParam("application/json") ClassOptionalWrapper bodyParameter);

        @Post("/reqopt/requied/array/parameter")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postRequiredArrayParameter(@HostParam("$host") String host, @BodyParam("application/json") List<String> bodyParameter);

        @Post("/reqopt/optional/array/parameter")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postOptionalArrayParameter(@HostParam("$host") String host, @BodyParam("application/json") List<String> bodyParameter);

        @Post("/reqopt/requied/array/property")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postRequiredArrayProperty(@HostParam("$host") String host, @BodyParam("application/json") ArrayWrapper bodyParameter);

        @Post("/reqopt/optional/array/property")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postOptionalArrayProperty(@HostParam("$host") String host, @BodyParam("application/json") ArrayOptionalWrapper bodyParameter);

        @Post("/reqopt/requied/array/header")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postRequiredArrayHeader(@HostParam("$host") String host, @HeaderParam("headerParameter") String headerParameter);

        @Post("/reqopt/optional/array/header")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(ErrorException.class)
        Mono<Response<Void>> postOptionalArrayHeader(@HostParam("$host") String host, @HeaderParam("headerParameter") String headerParameter);
    }

    /**
     * Test explicitly required integer. Please put null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postRequiredIntegerParameterWithResponseAsync(int bodyParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.postRequiredIntegerParameter(this.client.getHost(), bodyParameter);
    }

    /**
     * Test explicitly required integer. Please put null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postRequiredIntegerParameterAsync(int bodyParameter) {
        return postRequiredIntegerParameterWithResponseAsync(bodyParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test explicitly required integer. Please put null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postRequiredIntegerParameter(int bodyParameter) {
        postRequiredIntegerParameterAsync(bodyParameter).block();
    }

    /**
     * Test explicitly optional integer. Please put null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postOptionalIntegerParameterWithResponseAsync(Integer bodyParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.postOptionalIntegerParameter(this.client.getHost(), bodyParameter);
    }

    /**
     * Test explicitly optional integer. Please put null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postOptionalIntegerParameterAsync(Integer bodyParameter) {
        return postOptionalIntegerParameterWithResponseAsync(bodyParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test explicitly optional integer. Please put null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postOptionalIntegerParameter(Integer bodyParameter) {
        postOptionalIntegerParameterAsync(bodyParameter).block();
    }

    /**
     * Test explicitly required integer. Please put a valid int-wrapper with 'value' = null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postRequiredIntegerPropertyWithResponseAsync(IntWrapper bodyParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (bodyParameter == null) {
            throw new IllegalArgumentException("Parameter bodyParameter is required and cannot be null.");
        } else {
            bodyParameter.validate();
        }
        return service.postRequiredIntegerProperty(this.client.getHost(), bodyParameter);
    }

    /**
     * Test explicitly required integer. Please put a valid int-wrapper with 'value' = null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postRequiredIntegerPropertyAsync(IntWrapper bodyParameter) {
        return postRequiredIntegerPropertyWithResponseAsync(bodyParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test explicitly required integer. Please put a valid int-wrapper with 'value' = null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postRequiredIntegerProperty(IntWrapper bodyParameter) {
        postRequiredIntegerPropertyAsync(bodyParameter).block();
    }

    /**
     * Test explicitly optional integer. Please put a valid int-wrapper with 'value' = null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postOptionalIntegerPropertyWithResponseAsync(IntOptionalWrapper bodyParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (bodyParameter != null) {
            bodyParameter.validate();
        }
        return service.postOptionalIntegerProperty(this.client.getHost(), bodyParameter);
    }

    /**
     * Test explicitly optional integer. Please put a valid int-wrapper with 'value' = null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postOptionalIntegerPropertyAsync(IntOptionalWrapper bodyParameter) {
        return postOptionalIntegerPropertyWithResponseAsync(bodyParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test explicitly optional integer. Please put a valid int-wrapper with 'value' = null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postOptionalIntegerProperty(IntOptionalWrapper bodyParameter) {
        postOptionalIntegerPropertyAsync(bodyParameter).block();
    }

    /**
     * Test explicitly required integer. Please put a header 'headerParameter' =&gt; null and the client library should throw before the request is sent.
     * 
     * @param headerParameter MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postRequiredIntegerHeaderWithResponseAsync(int headerParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.postRequiredIntegerHeader(this.client.getHost(), headerParameter);
    }

    /**
     * Test explicitly required integer. Please put a header 'headerParameter' =&gt; null and the client library should throw before the request is sent.
     * 
     * @param headerParameter MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postRequiredIntegerHeaderAsync(int headerParameter) {
        return postRequiredIntegerHeaderWithResponseAsync(headerParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test explicitly required integer. Please put a header 'headerParameter' =&gt; null and the client library should throw before the request is sent.
     * 
     * @param headerParameter MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postRequiredIntegerHeader(int headerParameter) {
        postRequiredIntegerHeaderAsync(headerParameter).block();
    }

    /**
     * Test explicitly optional integer. Please put a header 'headerParameter' =&gt; null.
     * 
     * @param headerParameter MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postOptionalIntegerHeaderWithResponseAsync(Integer headerParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.postOptionalIntegerHeader(this.client.getHost(), headerParameter);
    }

    /**
     * Test explicitly optional integer. Please put a header 'headerParameter' =&gt; null.
     * 
     * @param headerParameter MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postOptionalIntegerHeaderAsync(Integer headerParameter) {
        return postOptionalIntegerHeaderWithResponseAsync(headerParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test explicitly optional integer. Please put a header 'headerParameter' =&gt; null.
     * 
     * @param headerParameter MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postOptionalIntegerHeader(Integer headerParameter) {
        postOptionalIntegerHeaderAsync(headerParameter).block();
    }

    /**
     * Test explicitly required string. Please put null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postRequiredStringParameterWithResponseAsync(String bodyParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (bodyParameter == null) {
            throw new IllegalArgumentException("Parameter bodyParameter is required and cannot be null.");
        }
        return service.postRequiredStringParameter(this.client.getHost(), bodyParameter);
    }

    /**
     * Test explicitly required string. Please put null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postRequiredStringParameterAsync(String bodyParameter) {
        return postRequiredStringParameterWithResponseAsync(bodyParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test explicitly required string. Please put null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postRequiredStringParameter(String bodyParameter) {
        postRequiredStringParameterAsync(bodyParameter).block();
    }

    /**
     * Test explicitly optional string. Please put null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postOptionalStringParameterWithResponseAsync(String bodyParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.postOptionalStringParameter(this.client.getHost(), bodyParameter);
    }

    /**
     * Test explicitly optional string. Please put null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postOptionalStringParameterAsync(String bodyParameter) {
        return postOptionalStringParameterWithResponseAsync(bodyParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test explicitly optional string. Please put null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postOptionalStringParameter(String bodyParameter) {
        postOptionalStringParameterAsync(bodyParameter).block();
    }

    /**
     * Test explicitly required string. Please put a valid string-wrapper with 'value' = null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postRequiredStringPropertyWithResponseAsync(StringWrapper bodyParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (bodyParameter == null) {
            throw new IllegalArgumentException("Parameter bodyParameter is required and cannot be null.");
        } else {
            bodyParameter.validate();
        }
        return service.postRequiredStringProperty(this.client.getHost(), bodyParameter);
    }

    /**
     * Test explicitly required string. Please put a valid string-wrapper with 'value' = null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postRequiredStringPropertyAsync(StringWrapper bodyParameter) {
        return postRequiredStringPropertyWithResponseAsync(bodyParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test explicitly required string. Please put a valid string-wrapper with 'value' = null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postRequiredStringProperty(StringWrapper bodyParameter) {
        postRequiredStringPropertyAsync(bodyParameter).block();
    }

    /**
     * Test explicitly optional integer. Please put a valid string-wrapper with 'value' = null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postOptionalStringPropertyWithResponseAsync(StringOptionalWrapper bodyParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (bodyParameter != null) {
            bodyParameter.validate();
        }
        return service.postOptionalStringProperty(this.client.getHost(), bodyParameter);
    }

    /**
     * Test explicitly optional integer. Please put a valid string-wrapper with 'value' = null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postOptionalStringPropertyAsync(StringOptionalWrapper bodyParameter) {
        return postOptionalStringPropertyWithResponseAsync(bodyParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test explicitly optional integer. Please put a valid string-wrapper with 'value' = null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postOptionalStringProperty(StringOptionalWrapper bodyParameter) {
        postOptionalStringPropertyAsync(bodyParameter).block();
    }

    /**
     * Test explicitly required string. Please put a header 'headerParameter' =&gt; null and the client library should throw before the request is sent.
     * 
     * @param headerParameter MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postRequiredStringHeaderWithResponseAsync(String headerParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (headerParameter == null) {
            throw new IllegalArgumentException("Parameter headerParameter is required and cannot be null.");
        }
        return service.postRequiredStringHeader(this.client.getHost(), headerParameter);
    }

    /**
     * Test explicitly required string. Please put a header 'headerParameter' =&gt; null and the client library should throw before the request is sent.
     * 
     * @param headerParameter MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postRequiredStringHeaderAsync(String headerParameter) {
        return postRequiredStringHeaderWithResponseAsync(headerParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test explicitly required string. Please put a header 'headerParameter' =&gt; null and the client library should throw before the request is sent.
     * 
     * @param headerParameter MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postRequiredStringHeader(String headerParameter) {
        postRequiredStringHeaderAsync(headerParameter).block();
    }

    /**
     * Test explicitly optional string. Please put a header 'headerParameter' =&gt; null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postOptionalStringHeaderWithResponseAsync(String bodyParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.postOptionalStringHeader(this.client.getHost(), bodyParameter);
    }

    /**
     * Test explicitly optional string. Please put a header 'headerParameter' =&gt; null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postOptionalStringHeaderAsync(String bodyParameter) {
        return postOptionalStringHeaderWithResponseAsync(bodyParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test explicitly optional string. Please put a header 'headerParameter' =&gt; null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-STRING.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postOptionalStringHeader(String bodyParameter) {
        postOptionalStringHeaderAsync(bodyParameter).block();
    }

    /**
     * Test explicitly required complex object. Please put null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postRequiredClassParameterWithResponseAsync(Product bodyParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (bodyParameter == null) {
            throw new IllegalArgumentException("Parameter bodyParameter is required and cannot be null.");
        } else {
            bodyParameter.validate();
        }
        return service.postRequiredClassParameter(this.client.getHost(), bodyParameter);
    }

    /**
     * Test explicitly required complex object. Please put null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postRequiredClassParameterAsync(Product bodyParameter) {
        return postRequiredClassParameterWithResponseAsync(bodyParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test explicitly required complex object. Please put null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postRequiredClassParameter(Product bodyParameter) {
        postRequiredClassParameterAsync(bodyParameter).block();
    }

    /**
     * Test explicitly optional complex object. Please put null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postOptionalClassParameterWithResponseAsync(Product bodyParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (bodyParameter != null) {
            bodyParameter.validate();
        }
        return service.postOptionalClassParameter(this.client.getHost(), bodyParameter);
    }

    /**
     * Test explicitly optional complex object. Please put null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postOptionalClassParameterAsync(Product bodyParameter) {
        return postOptionalClassParameterWithResponseAsync(bodyParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test explicitly optional complex object. Please put null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postOptionalClassParameter(Product bodyParameter) {
        postOptionalClassParameterAsync(bodyParameter).block();
    }

    /**
     * Test explicitly required complex object. Please put a valid class-wrapper with 'value' = null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postRequiredClassPropertyWithResponseAsync(ClassWrapper bodyParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (bodyParameter == null) {
            throw new IllegalArgumentException("Parameter bodyParameter is required and cannot be null.");
        } else {
            bodyParameter.validate();
        }
        return service.postRequiredClassProperty(this.client.getHost(), bodyParameter);
    }

    /**
     * Test explicitly required complex object. Please put a valid class-wrapper with 'value' = null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postRequiredClassPropertyAsync(ClassWrapper bodyParameter) {
        return postRequiredClassPropertyWithResponseAsync(bodyParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test explicitly required complex object. Please put a valid class-wrapper with 'value' = null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postRequiredClassProperty(ClassWrapper bodyParameter) {
        postRequiredClassPropertyAsync(bodyParameter).block();
    }

    /**
     * Test explicitly optional complex object. Please put a valid class-wrapper with 'value' = null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postOptionalClassPropertyWithResponseAsync(ClassOptionalWrapper bodyParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (bodyParameter != null) {
            bodyParameter.validate();
        }
        return service.postOptionalClassProperty(this.client.getHost(), bodyParameter);
    }

    /**
     * Test explicitly optional complex object. Please put a valid class-wrapper with 'value' = null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postOptionalClassPropertyAsync(ClassOptionalWrapper bodyParameter) {
        return postOptionalClassPropertyWithResponseAsync(bodyParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test explicitly optional complex object. Please put a valid class-wrapper with 'value' = null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postOptionalClassProperty(ClassOptionalWrapper bodyParameter) {
        postOptionalClassPropertyAsync(bodyParameter).block();
    }

    /**
     * Test explicitly required array. Please put null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postRequiredArrayParameterWithResponseAsync(List<String> bodyParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (bodyParameter == null) {
            throw new IllegalArgumentException("Parameter bodyParameter is required and cannot be null.");
        }
        return service.postRequiredArrayParameter(this.client.getHost(), bodyParameter);
    }

    /**
     * Test explicitly required array. Please put null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postRequiredArrayParameterAsync(List<String> bodyParameter) {
        return postRequiredArrayParameterWithResponseAsync(bodyParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test explicitly required array. Please put null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postRequiredArrayParameter(List<String> bodyParameter) {
        postRequiredArrayParameterAsync(bodyParameter).block();
    }

    /**
     * Test explicitly optional array. Please put null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postOptionalArrayParameterWithResponseAsync(List<String> bodyParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        return service.postOptionalArrayParameter(this.client.getHost(), bodyParameter);
    }

    /**
     * Test explicitly optional array. Please put null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postOptionalArrayParameterAsync(List<String> bodyParameter) {
        return postOptionalArrayParameterWithResponseAsync(bodyParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test explicitly optional array. Please put null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postOptionalArrayParameter(List<String> bodyParameter) {
        postOptionalArrayParameterAsync(bodyParameter).block();
    }

    /**
     * Test explicitly required array. Please put a valid array-wrapper with 'value' = null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postRequiredArrayPropertyWithResponseAsync(ArrayWrapper bodyParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (bodyParameter == null) {
            throw new IllegalArgumentException("Parameter bodyParameter is required and cannot be null.");
        } else {
            bodyParameter.validate();
        }
        return service.postRequiredArrayProperty(this.client.getHost(), bodyParameter);
    }

    /**
     * Test explicitly required array. Please put a valid array-wrapper with 'value' = null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postRequiredArrayPropertyAsync(ArrayWrapper bodyParameter) {
        return postRequiredArrayPropertyWithResponseAsync(bodyParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test explicitly required array. Please put a valid array-wrapper with 'value' = null and the client library should throw before the request is sent.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postRequiredArrayProperty(ArrayWrapper bodyParameter) {
        postRequiredArrayPropertyAsync(bodyParameter).block();
    }

    /**
     * Test explicitly optional array. Please put a valid array-wrapper with 'value' = null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postOptionalArrayPropertyWithResponseAsync(ArrayOptionalWrapper bodyParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (bodyParameter != null) {
            bodyParameter.validate();
        }
        return service.postOptionalArrayProperty(this.client.getHost(), bodyParameter);
    }

    /**
     * Test explicitly optional array. Please put a valid array-wrapper with 'value' = null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postOptionalArrayPropertyAsync(ArrayOptionalWrapper bodyParameter) {
        return postOptionalArrayPropertyWithResponseAsync(bodyParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test explicitly optional array. Please put a valid array-wrapper with 'value' = null.
     * 
     * @param bodyParameter MISSING·SCHEMA-DESCRIPTION-OBJECTSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postOptionalArrayProperty(ArrayOptionalWrapper bodyParameter) {
        postOptionalArrayPropertyAsync(bodyParameter).block();
    }

    /**
     * Test explicitly required array. Please put a header 'headerParameter' =&gt; null and the client library should throw before the request is sent.
     * 
     * @param headerParameter MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postRequiredArrayHeaderWithResponseAsync(List<String> headerParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        if (headerParameter == null) {
            throw new IllegalArgumentException("Parameter headerParameter is required and cannot be null.");
        }
        String headerParameterConverted = JacksonAdapter.createDefaultSerializerAdapter().serializeList(headerParameter, CollectionFormat.CSV);
        return service.postRequiredArrayHeader(this.client.getHost(), headerParameterConverted);
    }

    /**
     * Test explicitly required array. Please put a header 'headerParameter' =&gt; null and the client library should throw before the request is sent.
     * 
     * @param headerParameter MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postRequiredArrayHeaderAsync(List<String> headerParameter) {
        return postRequiredArrayHeaderWithResponseAsync(headerParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test explicitly required array. Please put a header 'headerParameter' =&gt; null and the client library should throw before the request is sent.
     * 
     * @param headerParameter MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postRequiredArrayHeader(List<String> headerParameter) {
        postRequiredArrayHeaderAsync(headerParameter).block();
    }

    /**
     * Test explicitly optional integer. Please put a header 'headerParameter' =&gt; null.
     * 
     * @param headerParameter MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> postOptionalArrayHeaderWithResponseAsync(List<String> headerParameter) {
        if (this.client.getHost() == null) {
            throw new IllegalArgumentException("Parameter this.client.getHost() is required and cannot be null.");
        }
        String headerParameterConverted = JacksonAdapter.createDefaultSerializerAdapter().serializeList(headerParameter, CollectionFormat.CSV);
        return service.postOptionalArrayHeader(this.client.getHost(), headerParameterConverted);
    }

    /**
     * Test explicitly optional integer. Please put a header 'headerParameter' =&gt; null.
     * 
     * @param headerParameter MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> postOptionalArrayHeaderAsync(List<String> headerParameter) {
        return postOptionalArrayHeaderWithResponseAsync(headerParameter)
            .flatMap((Response<Void> res) -> Mono.empty());
    }

    /**
     * Test explicitly optional integer. Please put a header 'headerParameter' =&gt; null.
     * 
     * @param headerParameter MISSING·SCHEMA-DESCRIPTION-ARRAYSCHEMA.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws ErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void postOptionalArrayHeader(List<String> headerParameter) {
        postOptionalArrayHeaderAsync(headerParameter).block();
    }
}
