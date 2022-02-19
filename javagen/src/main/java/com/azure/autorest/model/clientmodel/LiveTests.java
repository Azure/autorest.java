/*
 * // Copyright (c) Microsoft Corporation. All rights reserved.
 * // Licensed under the MIT License.
 */

package com.azure.autorest.model.clientmodel;

import java.util.List;

public class LiveTests {

    private String filename;
    private List<LiveTestCase> testCases;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<LiveTestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<LiveTestCase> testCases) {
        this.testCases = testCases;
    }
}
