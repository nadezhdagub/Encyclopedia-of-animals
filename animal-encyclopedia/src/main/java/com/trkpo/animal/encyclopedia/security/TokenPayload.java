/*
 * Copyright 2020 Sreda Software Solutions. All rights reserved.
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code. The code contains
 * Sreda Software Solutions Confidential Proprietary Information.
 */

package com.trkpo.animal.encyclopedia.security;

public enum TokenPayload {
    // SUBJECT(Claims.SUBJECT), // some email; android google account ?
    // EXPIRATION(Claims.EXPIRATION), // 5248907633222077534 - forever
    COMPANY_ID("cid"), // UUID
    ACCOUNT_ID("aid"), // UUID
    EMAIL("email"), // string
    ROLE("role"), // int 3 for "user, admin" - bitfield ?
    PERM("perm"), // int
    CTP("ctp"), // int
    MITM("mitm") // hex-string
    ;

    private String key;

    TokenPayload(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
