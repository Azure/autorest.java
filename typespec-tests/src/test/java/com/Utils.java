// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {

    private Utils() {

    }

    public static byte[] getJpgBytes() {
        try {
            return Files.readAllBytes(Paths.get("node_modules/@azure-tools/cadl-ranch-specs/assets/image.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] getPngBytes() {
        try {
            return Files.readAllBytes(Paths.get("node_modules/@azure-tools/cadl-ranch-specs/assets/image.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
