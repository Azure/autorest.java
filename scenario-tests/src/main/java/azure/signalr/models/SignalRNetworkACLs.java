// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package azure.signalr.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** Network ACLs for the resource. */
@Fluent
public final class SignalRNetworkACLs {
    @JsonIgnore private final ClientLogger logger = new ClientLogger(SignalRNetworkACLs.class);

    /*
     * Default action when no other rule matches
     */
    @JsonProperty(value = "defaultAction")
    private AclAction defaultAction;

    /*
     * ACL for requests from public network
     */
    @JsonProperty(value = "publicNetwork")
    private NetworkAcl publicNetwork;

    /*
     * ACLs for requests from private endpoints
     */
    @JsonProperty(value = "privateEndpoints")
    private List<PrivateEndpointAcl> privateEndpoints;

    /**
     * Get the defaultAction property: Default action when no other rule matches.
     *
     * @return the defaultAction value.
     */
    public AclAction defaultAction() {
        return this.defaultAction;
    }

    /**
     * Set the defaultAction property: Default action when no other rule matches.
     *
     * @param defaultAction the defaultAction value to set.
     * @return the SignalRNetworkACLs object itself.
     */
    public SignalRNetworkACLs withDefaultAction(AclAction defaultAction) {
        this.defaultAction = defaultAction;
        return this;
    }

    /**
     * Get the publicNetwork property: ACL for requests from public network.
     *
     * @return the publicNetwork value.
     */
    public NetworkAcl publicNetwork() {
        return this.publicNetwork;
    }

    /**
     * Set the publicNetwork property: ACL for requests from public network.
     *
     * @param publicNetwork the publicNetwork value to set.
     * @return the SignalRNetworkACLs object itself.
     */
    public SignalRNetworkACLs withPublicNetwork(NetworkAcl publicNetwork) {
        this.publicNetwork = publicNetwork;
        return this;
    }

    /**
     * Get the privateEndpoints property: ACLs for requests from private endpoints.
     *
     * @return the privateEndpoints value.
     */
    public List<PrivateEndpointAcl> privateEndpoints() {
        return this.privateEndpoints;
    }

    /**
     * Set the privateEndpoints property: ACLs for requests from private endpoints.
     *
     * @param privateEndpoints the privateEndpoints value to set.
     * @return the SignalRNetworkACLs object itself.
     */
    public SignalRNetworkACLs withPrivateEndpoints(List<PrivateEndpointAcl> privateEndpoints) {
        this.privateEndpoints = privateEndpoints;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (publicNetwork() != null) {
            publicNetwork().validate();
        }
        if (privateEndpoints() != null) {
            privateEndpoints().forEach(e -> e.validate());
        }
    }
}
