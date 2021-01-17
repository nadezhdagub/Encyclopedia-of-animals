/*
 * Copyright 2020 Sreda Software Solutions. All rights reserved.
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code. The code contains
 * Sreda Software Solutions Confidential Proprietary Information.
 */

package com.trkpo.animal.encyclopedia.security.service;

import com.trkpo.animal.encyclopedia.entity.Account;
import com.trkpo.animal.encyclopedia.repository.AccountRepository;
import com.trkpo.animal.encyclopedia.security.MzUserDetails;
import com.trkpo.animal.encyclopedia.security.SecurityRole;
import com.trkpo.animal.encyclopedia.security.TokenPayload;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtAuthorization {

    public static final String ROLE_PREFIX = "ROLE_";

    private final AccountRepository accountRepository;

    public MzUserDetails authorise(@NotNull Claims claims) {
        // get aid from token
        final UUID aid = Optional
                .ofNullable((String) claims.get(TokenPayload.ACCOUNT_ID.getKey()))
                .map(UUID::fromString)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Account ID should be present in token."));

        // get Account from DB
        final Account account = accountRepository.findById(aid)
                .orElseThrow(() -> new IllegalStateException(
                        "Account not found."));


        // check whether token is expired
        Optional.ofNullable(claims.getExpiration())
                .map(Date::getTime)
                .filter(time -> System.currentTimeMillis() < time)
                .orElseThrow(() -> new IllegalStateException("Token is expired."));

        // get roles
        final Long bitfield = Optional.ofNullable(account.getAccessLevel()).orElse(0L);
        final List<SimpleGrantedAuthority> grantedAuthorities = SecurityRole
                .getRolesByAL(bitfield).stream()
                .map(e -> ROLE_PREFIX + e.toString().toUpperCase())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        log.debug("Roles for token: {}", grantedAuthorities);

        return new MzUserDetails(
                // get username (login name) from token
                Optional.ofNullable(account.getEmail())
                        .orElse("N/A"),
                "",
                grantedAuthorities,
                aid);
    }
}
