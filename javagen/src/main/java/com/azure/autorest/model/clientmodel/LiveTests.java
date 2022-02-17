/*
 * // Copyright (c) Microsoft Corporation. All rights reserved.
 * // Licensed under the MIT License.
 */

package com.azure.autorest.model.clientmodel;

import java.util.List;

public class LiveTests {

    private String testClassName;
    private List<LiveTestCase> testCases;

    public String getTestClassName() {
        return testClassName;
    }

    public void setTestClassName(String testClassName) {
        this.testClassName = testClassName;
    }

    public List<LiveTestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<LiveTestCase> testCases) {
        this.testCases = testCases;
    }
}
