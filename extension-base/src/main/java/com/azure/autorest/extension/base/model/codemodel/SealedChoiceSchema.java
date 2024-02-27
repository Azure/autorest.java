// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;


/**
 * Represents a choice of several values (ie, an 'enum').
 */
public class SealedChoiceSchema extends ChoiceSchema {
    @Override
    public String toString() {
        return sharedToString(this, SealedChoiceSchema.class.getName());
    }

    @Override
    public int hashCode() {
        return sharedHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SealedChoiceSchema)) {
            return false;
        }

        return sharedEquals(this, (SealedChoiceSchema) other);
    }

}
