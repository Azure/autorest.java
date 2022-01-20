/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.extension.base.model.codemodel;

import java.lang.annotation.*;

/**
 * Customize a property mapping between a yaml property and a java property
 * It's currently a hint to let specific yaml constructor to decide whether serialize or deserialize
 *  according to this annotation.
 * @see AnnotatedPropertyUtils
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface YamlProperty {

    /**
     * The property name to read from yaml.
     */
    String value();

}
