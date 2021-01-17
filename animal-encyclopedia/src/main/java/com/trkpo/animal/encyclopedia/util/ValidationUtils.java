/*
 * Copyright 2020 Sreda Software Solutions. All rights reserved.
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code. The code contains
 * Sreda Software Solutions Confidential Proprietary Information.
 */

package com.trkpo.animal.encyclopedia.util;

import org.springframework.util.Assert;

public class ValidationUtils {

    public static void validatePassword(String password) {
        var message = "Password should be 4 or more characters length.";
        Assert.hasLength(password, message);
        if (password.length() < 4) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void validateEmail(String email) {
        var message = "Please provide a valid email address";
        Assert.hasLength(email, message);
        if (!email.matches(".+@.+\\..+")) {
            throw new IllegalArgumentException(message);
        }
    }
}
