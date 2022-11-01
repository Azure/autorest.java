// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.models.inheritance;

import com.models.inheritance.models.Fish;
import com.models.inheritance.models.GoblinShark;
import com.models.inheritance.models.Salmon;
import com.models.inheritance.models.Shark;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class DiscriminatedClientTest {

    ModelsInheritanceClient client = new ModelsInheritanceClientBuilder().buildClient();

    @Test
    void getModel() {
        Fish fish = client.getModel();
        Assertions.assertEquals(1, fish.getAge());

    }

    @Disabled("Polymorphic deserialization doesn't support multiple levels of inheritance in Jackson, https://github.com/FasterXML/jackson-databind/issues/1188")
    @Test
    void putModel() {
        Fish shark = new GoblinShark(1);
        client.putModel(shark);
    }

    @Test
    void getRecursiveModel() {
        Salmon salmon = (Salmon) client.getRecursiveModel();
        Assertions.assertEquals(2, salmon.getFriends().size());
        Assertions.assertEquals(2, salmon.getHate().size());
        Assertions.assertEquals(Shark.class, salmon.getPartner().getClass());
        Assertions.assertEquals(1, salmon.getAge());
        Assertions.assertEquals(2, (salmon.getPartner()).getAge());
    }


    @Disabled("Polymorphic deserialization doesn't support multiple levels of inheritance in Jackson, https://github.com/FasterXML/jackson-databind/issues/1188")
    @Test
    void putRecursiveModel() {

    }

    @Test
    void getMissingDiscriminator() {
        Fish fish = client.getMissingDiscriminator();
        Assertions.assertEquals(1, fish.getAge());
    }

    @Test
    void getWrongDiscriminator() {
        Fish fish = client.getWrongDiscriminator();
        Assertions.assertEquals(1, fish.getAge());
    }
}