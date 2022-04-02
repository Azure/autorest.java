// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.models.Cookiecuttershark;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class CookiecuttersharkTests {
    @Test
    public void testSerialization() {
        Cookiecuttershark model =
                BinaryData.fromString(
                                "{\"fishtype\":\"cookiecuttershark\",\"age\":1111696037,\"birthday\":\"2021-11-21T07:12:40Z\",\"species\":\"tcktvfcivf\",\"length\":95.635185,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"muctqhjfbe\",\"length\":59.656746,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"xerfuwuttt\",\"length\":82.11009,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"rbirphxepc\",\"length\":25.681759},{\"fishtype\":\"Fish\",\"species\":\"hfnljkyqxj\",\"length\":88.188835},{\"fishtype\":\"Fish\",\"species\":\"jqgidokgjl\",\"length\":75.799385},{\"fishtype\":\"Fish\",\"species\":\"xgvcltbgsn\",\"length\":67.462715}]},{\"fishtype\":\"Fish\",\"species\":\"kjeszzhbij\",\"length\":35.117786,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"vgxbfsmxne\",\"length\":85.93765},{\"fishtype\":\"Fish\",\"species\":\"vecxgodebf\",\"length\":9.272665},{\"fishtype\":\"Fish\",\"species\":\"rbmpukgriw\",\"length\":59.0843}]}]},{\"fishtype\":\"Fish\",\"species\":\"lfbxzpuzyc\",\"length\":66.18184,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"qzahmgkbrp\",\"length\":4.388815,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"ibnuqqkpik\",\"length\":52.966095},{\"fishtype\":\"Fish\",\"species\":\"gvtqagnbuy\",\"length\":37.72003},{\"fishtype\":\"Fish\",\"species\":\"jggmebfsia\",\"length\":24.347466}]},{\"fishtype\":\"Fish\",\"species\":\"trcvpnazzm\",\"length\":52.964806,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"nmpxttdbhr\",\"length\":69.24458},{\"fishtype\":\"Fish\",\"species\":\"ankxmyskpb\",\"length\":67.31485}]},{\"fishtype\":\"Fish\",\"species\":\"btkcxywnyt\",\"length\":21.35104,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"nlqidybyxc\",\"length\":99.40021}]},{\"fishtype\":\"Fish\",\"species\":\"lhaaxdbabp\",\"length\":19.07996,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"qlfktsthsu\",\"length\":97.37791},{\"fishtype\":\"Fish\",\"species\":\"mnyyazttbt\",\"length\":32.52217},{\"fishtype\":\"Fish\",\"species\":\"qpuedckzyw\",\"length\":54.958122},{\"fishtype\":\"Fish\",\"species\":\"xzfeyueaxi\",\"length\":87.90414}]}]},{\"fishtype\":\"Fish\",\"species\":\"jwbhqwalmu\",\"length\":22.792763,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"aepdkzjanc\",\"length\":96.28037,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"dwbavxbniw\",\"length\":61.30572},{\"fishtype\":\"Fish\",\"species\":\"wztsdbpgnx\",\"length\":20.087957},{\"fishtype\":\"Fish\",\"species\":\"hpzxbzpfza\",\"length\":75.475494},{\"fishtype\":\"Fish\",\"species\":\"cuhxwtctyq\",\"length\":20.55577}]},{\"fishtype\":\"Fish\",\"species\":\"bbovplwzbh\",\"length\":11.353487,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"guosvmkfss\",\"length\":32.20879}]},{\"fishtype\":\"Fish\",\"species\":\"kkfplgmgsx\",\"length\":12.549341,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"kdeslpvlop\",\"length\":3.696537}]},{\"fishtype\":\"Fish\",\"species\":\"ighxpkdwzb\",\"length\":59.623474,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"bbaumnyqup\",\"length\":20.160807}]}]},{\"fishtype\":\"Fish\",\"species\":\"ojnabckhsm\",\"length\":75.70594,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"iebtfhvpes\",\"length\":45.236958,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"rdqmhjjdht\",\"length\":48.466797},{\"fishtype\":\"Fish\",\"species\":\"kyzxuutknc\",\"length\":31.226944}]},{\"fishtype\":\"Fish\",\"species\":\"wsvlxotogt\",\"length\":71.378235,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"qsxvnmicyk\",\"length\":63.443333},{\"fishtype\":\"Fish\",\"species\":\"oveilovnot\",\"length\":6.925172},{\"fishtype\":\"Fish\",\"species\":\"fcnjbkcnxd\",\"length\":20.912487}]},{\"fishtype\":\"Fish\",\"species\":\"tkphywpnvj\",\"length\":63.92002,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"ermclfplph\",\"length\":17.466276},{\"fishtype\":\"Fish\",\"species\":\"scrpabgyep\",\"length\":84.15444}]},{\"fishtype\":\"Fish\",\"species\":\"tazqugxywp\",\"length\":71.67845,\"siblings\":[{\"fishtype\":\"Fish\",\"species\":\"fjzwfqkquj\",\"length\":67.8486}]}]}]}")
                        .toObject(Cookiecuttershark.class);
        Assertions.assertEquals("tcktvfcivf", model.getSpecies());
        Assertions.assertEquals(95.635185f, model.getLength());
        Assertions.assertEquals("muctqhjfbe", model.getSiblings().get(0).getSpecies());
        Assertions.assertEquals(59.656746f, model.getSiblings().get(0).getLength());
        Assertions.assertEquals("xerfuwuttt", model.getSiblings().get(0).getSiblings().get(0).getSpecies());
        Assertions.assertEquals(82.11009f, model.getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(
                "rbirphxepc", model.getSiblings().get(0).getSiblings().get(0).getSiblings().get(0).getSpecies());
        Assertions.assertEquals(
                25.681759f, model.getSiblings().get(0).getSiblings().get(0).getSiblings().get(0).getLength());
        Assertions.assertEquals(1111696037, model.getAge());
        Assertions.assertEquals(OffsetDateTime.parse("2021-11-21T07:12:40Z"), model.getBirthday());
    }
}
