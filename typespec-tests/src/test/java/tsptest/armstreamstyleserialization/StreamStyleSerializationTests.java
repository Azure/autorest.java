package tsptest.armstreamstyleserialization;

import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.HttpResponse;
import com.azure.core.management.serializer.SerializerFactory;
import com.azure.core.test.http.MockHttpResponse;
import com.azure.core.util.BinaryData;
import com.azure.core.util.serializer.SerializerAdapter;
import com.azure.core.util.serializer.SerializerEncoding;
import com.azure.json.JsonProviders;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.io.StringWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.utils.ArmUtils;
import reactor.core.publisher.Mono;
import tsptest.armstreamstyleserialization.models.Error;
import tsptest.armstreamstyleserialization.models.Priority;
import tsptest.armstreamstyleserialization.models.SawShark;
import tsptest.armstreamstyleserialization.models.Shark;

public class StreamStyleSerializationTests {
    @Test
    public void testDuplicatePropertiesSerialization() throws IOException {
        // SawShark has "age" property in both itself, and its parent "Fish".
        int age = 10;
        SawShark model = new SawShark().withAge(age).withDna("upi");
        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = JsonProviders.createWriter(stringWriter);
        model.toJson(jsonWriter);
        jsonWriter.flush();
        model = BinaryData.fromString(stringWriter.toString()).toObject(SawShark.class);
        Assertions.assertEquals(age, model.age());
    }

    @Test
    public void testManagementErrorDeserialization() throws IOException {
        final String errorBodyWithError
            = "{\"error\":{\"code\":\"WepAppError\",\"message\":\"Web app error.\",\"additionalProperty\":\"Deployment error.\",\"details\":[{\"code\":\"InnerError\", \"additionalProperty\": \"nested\"}]}}";
        final String errorBodyWithoutError
            = "{\"code\":\"WepAppError\",\"message\":\"Web app error.\",\"additionalProperty\":\"Deployment error.\",\"details\":[{\"code\":\"InnerError\", \"additionalProperty\": \"nested\"}]}";
        SerializerAdapter serializerAdapter = SerializerFactory.createDefaultManagementSerializerAdapter();
        Error error = serializerAdapter.deserialize(errorBodyWithError, Error.class, SerializerEncoding.JSON);
        Assertions.assertEquals("WepAppError", error.getCode());
        Assertions.assertEquals("Deployment error.", error.getAdditionalProperty());
        Assertions.assertEquals("nested", error.getDetails().iterator().next().getAdditionalProperty());

        error = serializerAdapter.deserialize(errorBodyWithoutError, Error.class, SerializerEncoding.JSON);
        Assertions.assertEquals("WepAppError", error.getCode());
        Assertions.assertEquals("Deployment error.", error.getAdditionalProperty());
        Assertions.assertEquals("nested", error.getDetails().iterator().next().getAdditionalProperty());
    }

    @Test
    public void testValidate() {
        Shark shark = new Shark();
        Assertions.assertThrows(IllegalArgumentException.class, shark::validate);

        shark.withAge(1);
        shark.withRequiredString("any");
        Assertions.assertThrows(IllegalArgumentException.class, shark::validate);

        shark.withRequiredStringAnotherPropertiesRequiredString("any");
        shark.validate();
    }

    @Test
    public void testExpandableEnum() {
        HttpClient httpClient = request -> {
            String query = request.getUrl().getQuery();
            Assertions.assertEquals("priority=0", query);
            return Mono.just(new MockHttpResponse(request, 200, 0));
        };
        ArmStreamStyleSerializationManager manager = ArmStreamStyleSerializationManager
            .authenticate(new HttpPipelineBuilder().httpClient(httpClient).build(), ArmUtils.getAzureProfile());
        Priority priority = manager.priorities().setPriority(Priority.HIGH);
        Assertions.assertEquals(Priority.HIGH, priority);
    }
}
