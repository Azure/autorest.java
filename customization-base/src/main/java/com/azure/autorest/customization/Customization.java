// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.customization;

import org.slf4j.Logger;

import java.util.Map;

/**
 * The base class for customization. Extend this class to plug into AutoRest generation.
 */
public abstract class Customization {
    /**
     * Start the customization process. This is called by the post processor in AutoRest.
     *
     * @param files the map of files generated in the previous steps in AutoRest
     * @param logger the logger
     * @return the map of files after customization
     */
    public final Map<String, String> run(Map<String, String> files, Logger logger) {
        LibraryCustomization libraryCustomization = new LibraryCustomization(files);
        customize(libraryCustomization, logger);

        return libraryCustomization.getContents();
    }

    /**
     * Override this method to customize the client library.
     *
     * @param libraryCustomization the top level customization object
     * @param logger the logger
     */
    public abstract void customize(LibraryCustomization libraryCustomization, Logger logger);
}
