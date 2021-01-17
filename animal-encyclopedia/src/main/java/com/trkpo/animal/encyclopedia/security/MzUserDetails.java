/*
 * Copyright 2020 Sreda Software Solutions. All rights reserved.
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code. The code contains
 * Sreda Software Solutions Confidential Proprietary Information.
 */

package com.trkpo.animal.encyclopedia.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.UUID;

/**
 * Mobiliuz X UserDetails
 */
@Getter
public class MzUserDetails extends User {
    private UUID accountId;
    private String username;

    public MzUserDetails(String username, String password,
                         Collection<? extends GrantedAuthority> authorities,
                         UUID accountId) {
        super(username, password, authorities);
        this.accountId = accountId;
        this.username = username;
    }
}
