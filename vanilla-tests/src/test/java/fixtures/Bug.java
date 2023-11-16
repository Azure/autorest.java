// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures;

import com.azure.core.util.BinaryData;
import fixtures.inheritance.passdiscriminator.models.MetricAlertCriteria;
import fixtures.inheritance.passdiscriminator.models.MetricAlertSingleResourceMultipleMetricCriteria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Bug {

    @Test
    public void testSerialization() {
        MetricAlertCriteria criteria = new MetricAlertSingleResourceMultipleMetricCriteria();
        String json = BinaryData.fromObject(criteria).toString();
        Assertions.assertTrue(json.contains("SingleResourceMultipleMetricCriteria"));
    }
}
