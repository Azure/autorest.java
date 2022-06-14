import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.HttpResponse;
import com.azure.core.util.FluxUtil;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import petstore.Pet;
import petstore.PetStoreServiceClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class PetStoreTests {

    @Test
    public void testPetStore() {
        HttpClient httpClient = Mockito.mock(HttpClient.class);
        ArgumentCaptor<HttpRequest> httpRequestCaptor = ArgumentCaptor.forClass(HttpRequest.class);
        Mockito.when(httpClient.send(httpRequestCaptor.capture(), Mockito.any()))
                .thenReturn(Mono.defer(() -> Mono.just(new HttpResponse(httpRequestCaptor.getValue()) {
                    @Override
                    public int getStatusCode() {
                        return 200;
                    }

                    @Override
                    public String getHeaderValue(String s) {
                        return null;
                    }

                    @Override
                    public HttpHeaders getHeaders() {
                        return new HttpHeaders();
                    }

                    @Override
                    public Flux<ByteBuffer> getBody() {
                        byte[] bytes = "{\"name\":\"Tom\",\"tag\":\"cat\",\"age\":2}".getBytes(StandardCharsets.UTF_8);
                        return Flux.just(ByteBuffer.wrap(bytes));
                    }

                    @Override
                    public Mono<byte[]> getBodyAsByteArray() {
                        return FluxUtil.collectBytesInByteBufferStream(this.getBody());
                    }

                    @Override
                    public Mono<String> getBodyAsString() {
                        return getBodyAsByteArray().map(b -> new String(b, StandardCharsets.UTF_8));
                    }

                    @Override
                    public Mono<String> getBodyAsString(Charset charset) {
                        return this.getBodyAsString();
                    }
                })));
        HttpPipeline httpPipeline = new HttpPipelineBuilder()
                .httpClient(httpClient)
                .build();
        SerializerAdapter serializerAdapter = JacksonAdapter.createDefaultSerializerAdapter();

        PetStoreServiceClient client = new PetStoreServiceClient(httpPipeline, serializerAdapter, "https://localhost");

        Pet pet = client.readAsyncWithResponse(0).block().getValue();
        Assertions.assertEquals("Tom", pet.getName());
        Assertions.assertEquals("cat", pet.getTag());
        Assertions.assertEquals(2, pet.getAge());
    }
}
