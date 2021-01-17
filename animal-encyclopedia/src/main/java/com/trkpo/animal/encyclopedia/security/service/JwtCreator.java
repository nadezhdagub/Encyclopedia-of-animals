/*
 * Copyright 2020 Sreda Software Solutions. All rights reserved.
 * The copyright notice above does not evidence any actual or
 * intended publication of such source code. The code contains
 * Sreda Software Solutions Confidential Proprietary Information.
 */

package com.trkpo.animal.encyclopedia.security.service;

import com.trkpo.animal.encyclopedia.config.JwtConfig;
import com.trkpo.animal.encyclopedia.entity.Account;
import com.trkpo.animal.encyclopedia.security.TokenPayload;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.validation.constraints.NotNull;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtCreator {

    private final JwtConfig.AppJwt appJwt;

    public String create(@NotNull Account account) {
        final SignatureAlgorithm algorithm = getSignatureAlgorithm();
        final Key signingKey = new SecretKeySpec(getSecretBytes(), algorithm.getJcaName());
        final long nowMillis = System.currentTimeMillis();

        final String jti = UUID.randomUUID().toString();
        log.info("Token generated: {}", jti);

        //JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setId(jti)
                .setIssuedAt(new Date(nowMillis))
                .setSubject(account.getName())
                .setExpiration(new Date(nowMillis + appJwt.getTtl()))
                // TODO: here should be company
                .claim(TokenPayload.COMPANY_ID.getKey(), "00000000-0000-0000-0000-000000000000")
                .claim(TokenPayload.ACCOUNT_ID.getKey(), account.getId())
//                .claim(TokenPayload.EMAIL.getKey(), account.getEmail())
//                .claim(TokenPayload.ROLE.getKey(), account.getAccessLevel()) // ?
//                .claim(TokenPayload.PERM.getKey(), 0) // ?
//                .claim(TokenPayload.CTP.getKey(), 0) // ?
//                .claim(TokenPayload.MITM.getKey(), "00000000000000000000000000000000") // ?
                .signWith(algorithm, signingKey);

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    private SignatureAlgorithm getSignatureAlgorithm() {
        return SignatureAlgorithm.forName(appJwt.getSelectedAlg());
    }

    private byte[] getSecretBytes() {
        return TextCodec.BASE64.decode(appJwt.getSecrets().get(appJwt.getSelectedAlg()));
    }
}
