package fixtures.xml;

import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.*;
import com.azure.core.implementation.util.FluxUtil;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Supplier;

public class MockXMLHttpClient implements HttpClient {
    private HttpResponse response(String resource) throws IOException, URISyntaxException {
        URL url = getClass().getClassLoader().getResource(resource);
        byte[] bytes = Files.readAllBytes(Paths.get(url.toURI()));
        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", "application/xml");
        HttpResponse res = new MockHttpResponse(200, headers, bytes);
        return res;
    }

    private Mono<? extends HttpResponse> validate(String requestBody, String resource) {
        URL url = getClass().getClassLoader().getResource(resource);
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get(url.toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String expected = new String(bytes, StandardCharsets.UTF_8);
        if (requestBody.replaceAll("\\s+", "").equals(expected.replaceAll("\\s+", ""))) {
            return Mono.just(new MockHttpResponse(201));
        } else {
            return Mono.error(new HttpResponseException("Expected: " + expected + "\nReceived: " + requestBody, new MockHttpResponse(400)));
        }
    }

    @Override
    public Mono<HttpResponse> send(HttpRequest request) {
        try {
            if (request.httpMethod() == HttpMethod.GET) {
                String path = request.url().getPath();
                if (path.contains("xml/simple")) {
                    return Mono.just(response("GetXMLWithAttributes.xml"));
                } else if (path.contains("xml/wrapped-lists")) {
                    return Mono.just(response("GetXMLWrappedLists.xml"));
                } else if (path.contains("xml/empty-list")) {
                    return Mono.just(response("GetXMLEmptyList.xml"));
                } else if (path.contains("xml/empty-wrapped-lists")) {
                    return Mono.just(response("GetXMLEmptyWrappedLists.xml"));
                } else if (path.contains("xml/root-list")) {
                    return Mono.just(response("GetXMLRootList.xml"));
                } else if (path.contains("xml/empty-root-list")) {
                    return Mono.just(response("GetXMLEmptyRootList.xml"));
                } else if (path.contains("xml/empty-child-element")) {
                    return Mono.just(response("GetXMLEmptyChildElement.xml"));
                } else if (path.contains("xml/headers")) {
                    return Mono.<HttpResponse>just(new MockHttpResponse(200, new HttpHeaders().put("Custom-Header", "Custom value")));
                }
            }
            else if (request.httpMethod() == HttpMethod.PUT) {
                String path = request.url().getPath();
                if (path.contains("xml/simple")) {
                    return FluxUtil.collectBytesInByteBufferStream(request.body()).flatMap(bytes -> {
                        return validate(new String(bytes, StandardCharsets.UTF_8), "GetXMLWithAttributes.xml");
                    });
                } else if (path.contains("xml/wrapped-lists")) {
                    return FluxUtil.collectBytesInByteBufferStream(request.body()).flatMap(bytes -> {
                        return validate(new String(bytes, StandardCharsets.UTF_8), "GetXMLWrappedLists.xml");
                    });
                } else if (path.contains("xml/root-list")) {
                    return FluxUtil.collectBytesInByteBufferStream(request.body()).flatMap(bytes -> {
                        return validate(new String(bytes, StandardCharsets.UTF_8), "GetXMLRootList.xml");
                    });
                } else if (path.contains("xml/empty-root-list")) {
                    return FluxUtil.collectBytesInByteBufferStream(request.body()).flatMap(bytes -> {
                        return validate(new String(bytes, StandardCharsets.UTF_8), "GetXMLEmptyRootList.xml");
                    });
                } else if (path.contains("xml/empty-child-element")) {
                    return FluxUtil.collectBytesInByteBufferStream(request.body()).flatMap(bytes -> {
                        return validate(new String(bytes, StandardCharsets.UTF_8), "GetXMLEmptyChildElement.xml");
                    });
                }
            }
        } catch (IOException | URISyntaxException e) {
            return Mono.error(e);
        }

        return Mono.error(new HttpResponseException("Not found", new MockHttpResponse(404)));
    }

    @Override
    public HttpClient proxy(Supplier<ProxyOptions> proxyOptions) {
        return this;
    }

    @Override
    public HttpClient wiretap(boolean enableWiretap) {
        return this;
    }

    @Override
    public HttpClient port(int port) {
        return this;
    }
}
