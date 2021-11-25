// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package fixtures.customhttpexceptionmapping;

import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.exception.ResourceExistsException;
import com.azure.core.exception.ResourceNotFoundException;
import com.azure.core.util.CoreUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Validates the unexpected exception type mapping.
 */
public class ValidateExceptionMappingTests {

    /**
     * Expected default exception for all methods is com.azure.core.exception.ResourceNotFoundException.
     */
    @Test
    public void validateDefaultExceptionType() {
        for (Method method : HeadExceptions.HeadExceptionsService.class.getDeclaredMethods()) {
            UnexpectedResponseExceptionType[] exceptionTypes = method
                .getAnnotationsByType(UnexpectedResponseExceptionType.class);

            // If the method isn't annotated with UnexpectedResponseExceptionType skip it.
            if (CoreUtils.isNullOrEmpty(exceptionTypes)) {
                continue;
            }

            assertTrue(Arrays.stream(exceptionTypes).anyMatch(uret ->
                (uret.code() == null || uret.code().length == 0) && uret.value() == ResourceNotFoundException.class));
        }
    }

    /**
     * 404 uses a custom mapping of com.azure.core.exception.ResourceExistsException that should take priority over
     * everything else.
     */
    @Test
    public void validate404UsesResourceExistsException() {
        for (Method method : HeadExceptions.HeadExceptionsService.class.getDeclaredMethods()) {
            UnexpectedResponseExceptionType[] exceptionTypes = method
                .getAnnotationsByType(UnexpectedResponseExceptionType.class);

            // If the method isn't annotated with UnexpectedResponseExceptionType skip it.
            if (CoreUtils.isNullOrEmpty(exceptionTypes)) {
                continue;
            }

            assertTrue(Arrays.stream(exceptionTypes).anyMatch(uret ->
                (uret.code() != null && Arrays.stream(uret.code()).anyMatch(code -> code == 404))
                    && uret.value() == ResourceExistsException.class));
        }
    }
}
