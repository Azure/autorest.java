package com.models.visibility.automatic;

import com.models.visibility.automatic.models.VisibilityModel;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AutomaticClientTest {

    AutomaticClient client = new AutomaticClientBuilder().buildClient();

    @Test
    void getModel() {
        VisibilityModel visibilityModel = new VisibilityModel(123,null, null, false);
        client.getModel(visibilityModel);
    }

    @Test
    void headModel() {
        VisibilityModel visibilityModel = new VisibilityModel(123,null, null, false);
        client.headModel(visibilityModel);
    }

    @Test
    void putModel() {
    }

    @Test
    void patchModel() {
    }

    @Test
    void postModel() {
    }

    @Test
    void deleteModel() {
    }
}