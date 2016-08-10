// Copyright (c) Microsoft Corporation.
// All rights reserved.
//
// This code is licensed under the MIT License.
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files(the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and / or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions :
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

package com.microsoft.identity.client;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link AuthenticationException}.
 */
public final class AuthenticationExceptionTest {
    private static final String TEST_ERROR_DESCRIPTION = "test error description";

    @Test
    public void testEmptyConstructor() {
        final AuthenticationException authenticationException = new AuthenticationException();
        Assert.assertNull(authenticationException.getErrorCode());
        Assert.assertNull(authenticationException.getMessage());
        Assert.assertNull(authenticationException.getCause());
    }

    @Test
    public void testWithErrorCode() {
        final AuthenticationException authenticationException = new AuthenticationException(
                MSALError.RETRY_FAILED_WITH_SERVER_ERROR);
        Assert.assertTrue(authenticationException.getErrorCode().equals(MSALError.RETRY_FAILED_WITH_SERVER_ERROR));
        Assert.assertNotNull(authenticationException.getMessage());
        Assert.assertTrue(authenticationException.getMessage().equals(
                MSALError.RETRY_FAILED_WITH_SERVER_ERROR.getDescription()));
        Assert.assertNull(authenticationException.getCause());
    }

    @Test
    public void testWithErrorCodeAndDescription() {
        final AuthenticationException authenticationException = new AuthenticationException(
                MSALError.RETRY_FAILED_WITH_SERVER_ERROR, TEST_ERROR_DESCRIPTION);
        Assert.assertTrue(authenticationException.getErrorCode().equals(MSALError.RETRY_FAILED_WITH_SERVER_ERROR));
        Assert.assertTrue(authenticationException.getMessage().equals(TEST_ERROR_DESCRIPTION));
        Assert.assertNull(authenticationException.getCause());
    }

    @Test
    public void testWithErrorCodeAndDescriptAndCause() {
        final Throwable throwable = new Throwable(TEST_ERROR_DESCRIPTION);
        final AuthenticationException authenticationException = new AuthenticationException(
                MSALError.RETRY_FAILED_WITH_SERVER_ERROR, TEST_ERROR_DESCRIPTION, throwable);
        Assert.assertTrue(authenticationException.getErrorCode().equals(MSALError.RETRY_FAILED_WITH_SERVER_ERROR));
        Assert.assertTrue(authenticationException.getMessage().equals(TEST_ERROR_DESCRIPTION));
        Assert.assertNotNull(authenticationException.getCause());
        Assert.assertTrue(authenticationException.getCause().getMessage().equals(TEST_ERROR_DESCRIPTION));
    }
}
