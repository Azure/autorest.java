package com.azure.autorest.template;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/**
 * Writes a Client Model of type ModelT to a Java syntax context.
 */
public interface IJavaTemplate<ModelT, ContextT> {
    void write(ModelT model, ContextT context);
}