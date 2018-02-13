package fixtures.xml;

import com.microsoft.rest.v2.RestException;
import com.microsoft.rest.v2.http.HttpClient;
import com.microsoft.rest.v2.http.HttpHeaders;
import com.microsoft.rest.v2.http.HttpRequest;
import com.microsoft.rest.v2.http.HttpResponse;
import io.reactivex.Single;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MockXMLHttpClient extends HttpClient {
    private HttpResponse response(String resource) throws IOException, URISyntaxException {
        URL url = getClass().getClassLoader().getResource(resource);
        byte[] bytes = Files.readAllBytes(Paths.get(url.toURI()));
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/xml");
        HttpResponse res = new MockHttpResponse(200, headers, bytes);
        return res;
    }

    @Override
    public Single<HttpResponse> sendRequestAsync(HttpRequest request) {
        try {
            String path = request.url().getPath();
            if (path.contains("xml/simple")) {
                return Single.just(response("GetXMLWithAttributes.xml"));
            } else if (path.contains("xml/wrapped-lists")) {
                return Single.just(response("GetXMLWrappedLists.xml"));
            } else if (path.contains("xml/headers")) {
                return Single.<HttpResponse>just(new MockHttpResponse(200, new HttpHeaders().set("Custom-Header", "Custom value")));
            }
        } catch (IOException | URISyntaxException e) {
            return Single.error(e);
        }

        return Single.error(new RestException("Not found", new MockHttpResponse(404)));
    }
}
