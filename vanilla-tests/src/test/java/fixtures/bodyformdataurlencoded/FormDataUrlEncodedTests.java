package fixtures.bodyformdataurlencoded;

import com.azure.core.http.rest.Response;
import fixtures.bodyformdataurlencoded.models.PetFood;
import fixtures.bodyformdataurlencoded.models.PetType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link BodyFormsDataURLEncoded} client.
 */
public class FormDataUrlEncodedTests {

    @Test
    public void test() {
        BodyFormsDataURLEncoded bodyFormsDataURLEncoded = new BodyFormsDataURLEncodedBuilder()
                .buildClient();
        Response<Void> response = bodyFormsDataURLEncoded.getFormdataurlencodeds()
                .updatePetWithFormWithResponseAsync(1, PetType.DOG, PetFood.MEAT, 42, "Fido", null).block();

        assertEquals(200, response.getStatusCode());
    }
}
