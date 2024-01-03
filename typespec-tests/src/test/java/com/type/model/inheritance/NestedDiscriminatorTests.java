// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.model.inheritance;

import com.type.model.inheritance.nesteddiscriminator.NestedDiscriminatorClient;
import com.type.model.inheritance.nesteddiscriminator.NestedDiscriminatorClientBuilder;
import com.type.model.inheritance.nesteddiscriminator.models.Fish;
import com.type.model.inheritance.nesteddiscriminator.models.Salmon;
import com.type.model.inheritance.nesteddiscriminator.models.Shark;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class NestedDiscriminatorTests {

    NestedDiscriminatorClient client = new NestedDiscriminatorClientBuilder().buildClient();

    @Test
    void getModel() {
        Fish fish = client.getModel();
        Assertions.assertEquals(1, fish.getAge());
    }

    @Test
    @Disabled("The item `kind` is missing in the generated json file by the method `toJson` of the class `Shark`.")
    void putModel() {
        Shark body = new Shark(1, "goblin");
        client.putModel(body);
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


    @Test
    @Disabled("1. The item `kind` is missing in the generated json file by the method `toJson` of the class `Shark`. " +
            "2. The output order of each item in the generated json file by the method `toJson` of the class `Salmon` is incorrect.")
    void putRecursiveModel() {
        Salmon salmon = new Salmon(1);
        salmon.setPartner(new Shark(2, "saw"));

        List<Fish> friends = new ArrayList<>();
        Salmon friend1 = new Salmon(2);
        friend1.setPartner(new Salmon(3));
        Map<String, Fish> friend1Hate = new LinkedHashMap<>();
        friend1Hate.put("key1", new Salmon(4));
        friend1Hate.put("key2", new Shark(2, "goblin"));
        friend1.setHate(friend1Hate);
        friends.add(friend1);

        Shark friend2 = new Shark(3, "goblin");
        friends.add(friend2);
        salmon.setFriends(friends);

        Map<String, Fish> salmonHate = new LinkedHashMap<>();
        salmonHate.put("key3", new Shark(3, "saw"));
        List<Fish> salmonHateFriends = new ArrayList<>();
        salmonHateFriends.add(new Salmon(1));
        salmonHateFriends.add(new Shark(4, "goblin"));
        salmonHate.put("key4", new Salmon(2).setFriends(salmonHateFriends));
        salmon.setHate(salmonHate);

        client.putRecursiveModel(salmon);
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