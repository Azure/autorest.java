package fixtures.lroparameterizedendpoints.implementation;

import com.azure.core.util.Context;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fixtures.lroparameterizedendpoints.LroparameterizedendpointsManager;
import fixtures.lroparameterizedendpoints.fluent.ResourceProvidersClient;
import fixtures.lroparameterizedendpoints.models.ResourceProviders;

public final class ResourceProvidersImpl implements ResourceProviders {
    @JsonIgnore private final ClientLogger logger = new ClientLogger(ResourceProvidersImpl.class);

    private final ResourceProvidersClient innerClient;

    private final LroparameterizedendpointsManager serviceManager;

    public ResourceProvidersImpl(ResourceProvidersClient innerClient, LroparameterizedendpointsManager serviceManager) {
        this.innerClient = innerClient;
        this.serviceManager = serviceManager;
    }

    public String pollWithParameterizedEndpoints(String accountName) {
        return this.serviceClient().pollWithParameterizedEndpoints(accountName);
    }

    public String pollWithParameterizedEndpoints(String accountName, Context context) {
        return this.serviceClient().pollWithParameterizedEndpoints(accountName, context);
    }

    private ResourceProvidersClient serviceClient() {
        return this.innerClient;
    }

    private LroparameterizedendpointsManager manager() {
        return this.serviceManager;
    }
}
