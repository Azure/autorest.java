// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.management;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.SimpleResponse;
import com.azure.core.management.AzureEnvironment;
import com.azure.core.management.implementation.polling.PollerFactory;
import com.azure.core.management.polling.PollResult;
import com.azure.core.management.serializer.AzureJacksonAdapter;
import com.azure.core.util.polling.PollerFlux;
import com.azure.core.util.serializer.SerializerAdapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;
import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.Enumeration;

/**
 * ServiceClient is the abstraction for accessing REST operations and their payload data types.
 */
public abstract class AzureServiceClient {

    protected AzureServiceClient(HttpPipeline httpPipeline, AzureEnvironment environment) {

    }

    /**
     * The default User-Agent header. Override this method to override the user agent.
     *
     * @return the user agent string.
     */
    public String userAgent() {
        return String.format("Azure-SDK-For-Java/%s OS:%s MacAddressHash:%s Java:%s",
                getClass().getPackage().getImplementationVersion(),
                OS,
                MAC_ADDRESS_HASH,
                JAVA_VERSION);
    }

    private static final String MAC_ADDRESS_HASH;
    private static final String OS;
    private static final String JAVA_VERSION;

    static {
        OS = System.getProperty("os.name") + "/" + System.getProperty("os.version");
        String macAddress = "Unknown";
        try {
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            while (networks.hasMoreElements()) {
                NetworkInterface network = networks.nextElement();
                byte[] mac = network.getHardwareAddress();

                if (mac != null) {
                    macAddress = getSha256(mac);
                    break;
                }
            }
        } catch (Throwable t) {
            // It's okay ignore mac address hash telemetry
        }
        MAC_ADDRESS_HASH = macAddress;
        String version = System.getProperty("java.version");
        JAVA_VERSION = version != null ? version : "Unknown";
    }

    private SerializerAdapter serializerAdapter = new AzureJacksonAdapter();

    public SerializerAdapter getSerializerAdapter() {
        return this.serializerAdapter;
    }

    public <T, U> PollerFlux<PollResult<T>, U> getLroResultAsync(Mono<SimpleResponse<Flux<ByteBuffer>>> lroInit,
                                                                 HttpPipeline httpPipeline,
                                                                 Type pollResultType, Type finalResultType) {
        return PollerFactory.create(
                getSerializerAdapter(),
                httpPipeline,
                pollResultType,
                finalResultType,
                Duration.ofSeconds(30),
                activationOperation(lroInit)
        );
    }

    private Mono<Response<Flux<ByteBuffer>>> activationOperation(Mono<SimpleResponse<Flux<ByteBuffer>>> lroInit) {
        return lroInit.flatMap(fluxSimpleResponse -> Mono.just(fluxSimpleResponse));
    }

    private static String getSha256(byte[] bytes) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bytes);
            messageDigest.digest(bytes).toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "Unknown";
    }
}